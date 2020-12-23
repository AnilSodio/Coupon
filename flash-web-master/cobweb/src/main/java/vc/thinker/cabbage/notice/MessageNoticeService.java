package vc.thinker.cabbage.notice;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import vc.thinker.cabbage.CobwebApplication;
import vc.thinker.cabbage.extension.SpringExtension;
import vc.thinker.cabbage.se.CabinetRemoteHandle;
import vc.thinker.cabbage.se.CabinetRemoteHandle.CabinetNoticeContent;
import vc.thinker.cabbage.se.CabinetRemoteHandle.NoticeDelegate;

/**
 * 用于各应用服务于通讯服务器交互
 * 
 * 通迅服务器需采用分布式架构
 *  serviceCode 为服务器唯一标识
 * @author james
 *
 */
@Component
public class MessageNoticeService {
	
	private static final Logger log=LoggerFactory.getLogger(MessageNoticeService.class);
    
    @Autowired
    private CabinetRemoteHandle cabinetRemoteHandle;
    
    @Value("${redis.message.listener-pool-number}")
    private int poolNumber;
    
	@Autowired
	private SpringExtension springExtension;
	
	@Autowired
	private ActorSystem actorSystem;
	
	/**
	 * 
	 */
	private static int commandExpiration = 10 * 60 * 1000;
    
    @PostConstruct
    public void init(){
    	log.info("Message listener [{}]",CobwebApplication.serviceCode);
    	ActorRef ref = actorSystem.actorOf(springExtension.props("commandPushActors"));
    	cabinetRemoteHandle.listener(CobwebApplication.serviceCode, poolNumber, new NoticeDelegate() {
			@Override
			public void handle(CabinetNoticeContent content) {
				log.info("listener handle {}",content);
				if(content.getNoticeTime() != null){
					if(System.currentTimeMillis() - content.getNoticeTime().getTime() > commandExpiration){
						log.error("command {} 已过期",content);
						return;
					}
				}
				ref.tell(content, ActorRef.noSender());
			}
		});
    }
}
