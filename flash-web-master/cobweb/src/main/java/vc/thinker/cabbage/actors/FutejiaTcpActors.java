package vc.thinker.cabbage.actors;

import java.util.Arrays;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import akka.actor.UntypedActor;
import vc.thinker.cabbage.CobwebApplication;
import vc.thinker.cabbage.cmd.FutejiaTCPCommandPush;
import vc.thinker.cabbage.se.CabinetStatusService;
import vc.thinker.cabbage.se.OrderService;
import vc.thinker.cabbage.se.exception.CabinetNotFindException;
import vc.thinker.cabbage.se.model.CabinetStatus;
import vc.thinker.cabbage.tcp.SessionStoreManager;

/**
 * 伏特加处理
 * @author james
 *
 */
@Component
@Scope("prototype")
public class FutejiaTcpActors extends UntypedActor{

	private static final Logger log = LoggerFactory.getLogger(FutejiaTcpActors.class);
	
    @Autowired
    private SessionStoreManager sessionStoreManager;
    
    @Autowired
    private CabinetStatusService cabinetStatusService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private FutejiaTCPCommandPush futiejiaTCPCommandPush;
    
	private IoSession session;

	public FutejiaTcpActors(IoSession session){
		this.session = session;
	}

	@Override
	public void onReceive(Object message) throws Exception {
		byte[] msg = (byte[]) message;
		
		String orgmsg = new String(Arrays.copyOfRange(msg,4,msg.length)).trim();
		//log.info("message from " + session.getRemoteAddress() + " :" + message);
		log.info("message from " + session.getRemoteAddress() + " :" + orgmsg);
		
		String [] array=orgmsg.split("\\|");
		
		String stationid=array[1];

		switch(array[0]){
		case "10":
			//签到
			try {
				CabinetStatus cs=cabinetStatusService.login(Long.parseLong(stationid), CobwebApplication.serviceCode);
				futiejiaTCPCommandPush.sendLoginResp(session,"0");
			} catch (CabinetNotFindException e) {
				futiejiaTCPCommandPush.sendLoginResp(session,"1");
				log.error("MAC[{}] not find",stationid);
			}
			break;
		case "25":
			futiejiaTCPCommandPush.sendHeartbeatResp(session);
			break;
		default:
			log.info("未实现协议，{}",orgmsg);
			break;
		}
		//更新内存会话
		if(stationid != null){
			sessionStoreManager.setSession(stationid, session);
		}
	}
}
