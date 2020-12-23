package vc.thinker.cabbage.user.service;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sinco.messager.mobile.MobileMessage;
import com.sinco.messager.mobile.MobileMessageHandler;
import com.sinco.messager.mobile.jpush.AliasMobileChannel;
import com.sinco.messager.mobile.jpush.MessageMobileMessage;

import vc.thinker.cabbage.sys.SysMessageConstants;
import vc.thinker.cabbage.user.CommonConstants;

@Service
@Lazy(false)
public class UserAppMessageService {
	
	private final static Logger log=LoggerFactory.getLogger(UserAppMessageService.class);
	
	@Resource(name="memberAppMessageHandler")
	@Lazy(true)
	private MobileMessageHandler memberAppMessageHandler;
	
	@Autowired
	@Lazy(true)
	private TokenStore tokenStore;
	
	@EventListener(condition="#event.toMember")
	@Async
	public void sendLogoutToMember(LogoutEvent event){
		
		MessageMobileMessage message=new MessageMobileMessage(event.getContent());
		
		sendMessageToMobile(CommonConstants.ROLE_MEMBER_USER, SysMessageConstants.MOBILE_MESSAGE_LOGOUT, message,event.getAccessToken());
	}
	
	/**
	 * 发送消息到手机
	 * @param uid
	 * @param client
	 * @param message
	 */
	public void sendMessageToMobile(String client, String msgType,MobileMessage message,String accessToken){
		
		//附加参数，需要什么自己加
		Map<String, String> exts=Maps.newHashMap();
		exts.put("msgType", msgType+"");
		exts.put("accessToken", accessToken);
		message.setExts(exts);

		log.info("jpush to {},message {}",accessToken,JSON.toJSON(message));
		switch (client) {
		case CommonConstants.ROLE_MEMBER_USER:
			memberAppMessageHandler.sendMessage(new AliasMobileChannel(accessToken), message);
			break;
		}
		
	}
	
	public void sendMessageToMobile(String client, Integer msgType,MobileMessage message,Long ... uids){
		
		if(uids.length == 0){
			log.info("uids is null");
			return;
		}
		
		//附加参数，需要什么自己加
		Map<String, String> exts=Maps.newHashMap();
		exts.put("msgType", msgType+"");
		message.setExts(exts);
		
		Set<String> accessList=Sets.newHashSet();
		for (Long uid : uids) {
			Collection<OAuth2AccessToken> tokens = tokenStore
					.findTokensByClientIdAndUserName(client, String.valueOf(uid));
			Set<OAuth2AccessToken> tks = Sets.newHashSet(tokens.iterator());
			tks.forEach(e -> accessList.add(e.getValue().replaceAll("\\-", "")));
		}
		
		if(accessList.isEmpty()){
			log.info("access token is null,send message to mobile error");
			return;
		}
		
		log.info("jpush to {},message {}",accessList,JSON.toJSON(message));
		switch (client) {
		case CommonConstants.ROLE_MEMBER_USER:
			memberAppMessageHandler.sendMessage(new AliasMobileChannel(accessList.toArray(new String[accessList.size()])), message);
			break;
		}
	}
	

	/**
	 * 登出消息事件
	 * @author james
	 *
	 */
	public static class LogoutEvent{
		private boolean toMember  = false;
		private String content;
		private String accessToken;
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
		public String getAccessToken() {
			return accessToken;
		}
		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}
	}
}
