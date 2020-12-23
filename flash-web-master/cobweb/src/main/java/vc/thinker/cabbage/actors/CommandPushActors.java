package vc.thinker.cabbage.actors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import akka.actor.UntypedActor;
import vc.thinker.cabbage.cmd.TcpCommandPush;
import vc.thinker.cabbage.se.CabinetRemoteHandle.CabinetNoticeContent;

@Component
@Scope("prototype")
public class CommandPushActors extends UntypedActor{

	private static final Logger log = LoggerFactory.getLogger(CommandPushActors.class);
	
    @Autowired
    @Lazy(true)
    private TcpCommandPush tcpCommandPush;

	@Override
	public void onReceive(Object message) throws Exception {
		CabinetNoticeContent content = (CabinetNoticeContent)message;
		
		switch (content.getNoticeType()) {
		case out:
			tcpCommandPush.sendOut(content.getCabinetId(), content.getOrderCode(), content.getCable());
			break;
		case sync:
			tcpCommandPush.sendSync(content.getCabinetId());
			break;
		case query:
			tcpCommandPush.sendQuery(content.getCabinetId(), String.valueOf(content.getChannel()));
			break;
		case lock:
			tcpCommandPush.sendLock(content.getCabinetId(), String.valueOf(content.getChannel()));
			break;
		case unlock:
			tcpCommandPush.sendUnlock(content.getCabinetId(), String.valueOf(content.getChannel()));
			break;
		case sys_out:
			tcpCommandPush.sendSysOut(content.getCabinetId(), String.valueOf(content.getChannel()));
			break;
		case upgrade:
			//commandPush.upgrade(content.getUmMachineCode(), content.getUrl(), null);
			break;
		default:
			break;
		}
		unhandled(message);
	}
}
