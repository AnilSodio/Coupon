package vc.thinker.cabbage.tcp;

import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import scala.collection.concurrent.TrieMap;
import vc.thinker.cabbage.extension.SpringExtension;

public class ConductorHandler extends IoHandlerAdapter {
	
	private static final Logger log = LoggerFactory.getLogger(ConductorHandler.class);
	
	private ActorSystem system;
	private SpringExtension ext;
	private SessionStoreManager sessionManager;
	
	//配制的actors
	private String tcpAkkaReceive;
	
	TrieMap<IoSession,ActorRef> clients = new TrieMap<IoSession, ActorRef>();
	public ConductorHandler(SpringExtension ext,ActorSystem system,
			SessionStoreManager sessionManager,String tcpAkkaReceive){
		this.system = system;
		this.ext = ext;
		this.sessionManager = sessionManager;
		this.tcpAkkaReceive = tcpAkkaReceive;
	}
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		log.info("Connection Exception : " + getAddrString(session) + " " + cause);
	    clients.remove(session);
	    session.close(true);
	}
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		if(message instanceof byte[]){
			byte[] msg = (byte[]) message;
			ActorRef fsm = clients.get(session).get();
			fsm.tell(message,ActorRef.noSender());
		}else{
			log.info("client " + getAddrString(session) + " sent garbage " + message);
		}
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		log.info("Connection close : " + getAddrString(session));
		 ActorRef fsm = clients.get(session).get();
		 system.stop(fsm);
		 clients.remove(session);
		 log.info("close " + Thread.currentThread().getName());

	}
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		super.sessionCreated(session);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		 log.info("Connection from : " + getAddrString(session));
		 log.info("created " + Thread.currentThread().getName());
		 ActorRef fsm = system.actorOf(ext.props(tcpAkkaReceive,session));
		 clients.put(session, fsm);
	}


	public static String getAddrString(IoSession session){
		java.net.SocketAddress address = session.getRemoteAddress();
		if(address instanceof InetSocketAddress) return address.toString();
		else return "[unknown]";
	}
}
