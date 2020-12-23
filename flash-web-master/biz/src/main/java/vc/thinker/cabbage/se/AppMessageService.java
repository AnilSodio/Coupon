package vc.thinker.cabbage.se;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sinco.messager.mobile.MobileMessage;
import com.sinco.messager.mobile.MobileMessageHandler;
import com.sinco.messager.mobile.jpush.AliasMobileChannel;
import com.sinco.messager.mobile.jpush.MessageMobileMessage;

import vc.thinker.cabbage.se.bo.OrderBO;
import vc.thinker.cabbage.se.dao.OrderDao;
import vc.thinker.cabbage.sys.SysMessageConstants;
import vc.thinker.cabbage.user.CommonConstants;

/**
 * APP 相关消息
 * @author james
 *
 */
@Service
public class AppMessageService {
	
	private final static Logger log=LoggerFactory.getLogger(AppMessageService.class);
	@Resource(name="memberAppMessageHandler")
	@Lazy(true)
	private MobileMessageHandler memberAppMessageHandler;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private TokenStore tokenStore;
	
	/**
	 * 订单消息到个人用户
	 * @param event
	 */
	@TransactionalEventListener(condition="#event.toMember")
	@Async
	public void sendOrderMessageToMember(OrderMessageEvent event){
		
		OrderBO order=orderDao.findByOrderCode(event.getOrderCode());
		
		if(order == null){
			log.error("order code [{}] not find",event.getOrderCode());
			return;
		}
		MessageMobileMessage message=new MessageMobileMessage(event.getContent());
		
		//附加参数，需要什么自己加
		Map<String, String> exts=Maps.newHashMap();
		exts.put(SysMessageConstants.MOBILE_MESSAGE_TYPE_KEY,event.getMobileMsgType());
		message.setExts(exts);
		message.setTitle("Order message");
		message.setMessage(event.getContent());
		
		sendMessageToMobile(CommonConstants.ROLE_MEMBER_USER,message,order.getUid());
	}
	
	/**
	 * 发送消息到手机
	 * @param uid
	 * @param client
	 * @param message
	 */
	public void sendMessageToMobile(String client,MobileMessage message,String ... accessTokens){
		
		if(accessTokens.length == 0){
			log.error("accessToken is null");
			return;
		}

		log.info("jpush to {},message {}",accessTokens,JSON.toJSON(message));
		switch (client) {
		case CommonConstants.ROLE_MEMBER_USER:
			memberAppMessageHandler.sendMessage(new AliasMobileChannel(accessTokens), message);
			break;
		}
		
	}
	
	/**
	 * 根据uid推送消息
	 * @param client
	 * @param message
	 * @param uids
	 */
	public void sendMessageToMobile(String client,MobileMessage message,Long ... uids){
		
		if(uids.length == 0){
			log.info("uids is null");
			return;
		}
		
		Set<String> accessList=Sets.newHashSet();
		for (Long uid : uids) {
			Collection<OAuth2AccessToken> tokens = tokenStore
					.findTokensByClientIdAndUserName(client, String.valueOf(uid));
			
			log.info("=====消息推送 client:【"+client+"】uid:【"+uid+"】tokens:【"+tokens+"】");
			
			Set<OAuth2AccessToken> tks = Sets.newHashSet(tokens.iterator());
			tks.forEach(e -> accessList.add(e.getValue().replaceAll("\\-", "")));
		}
		
		if(accessList.isEmpty()){
			log.info("access token is null,send message to mobile error");
			return;
		}
		
		sendMessageToMobile(client, message, accessList.toArray(new String[accessList.size()]));
	}
	
	
	/**
	 * 雨伞订单消息事件
	 * @author james
	 *
	 */
	public static class OrderMessageEvent{
		private boolean toMember = true;
		private String content;
		private String mobileMsgType;
		private String orderCode;
		public boolean isToMember() {
			return toMember;
		}
		public void setToMember(boolean toMember) {
			this.toMember = toMember;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getMobileMsgType() {
			return mobileMsgType;
		}
		public void setMobileMsgType(String mobileMsgType) {
			this.mobileMsgType = mobileMsgType;
		}
		public String getOrderCode() {
			return orderCode;
		}
		public void setOrderCode(String orderCode) {
			this.orderCode = orderCode;
		}
	}
}
