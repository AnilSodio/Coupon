package vc.thinker.cabbage.sys.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
import com.sinco.data.core.Page;
import com.sinco.messager.mobile.MobileMessage;
import com.sinco.messager.mobile.MobileMessageHandler;
import com.sinco.messager.mobile.jpush.AlertMobileMessage;
import com.sinco.messager.mobile.jpush.AliasMobileChannel;

import vc.thinker.cabbage.sys.SysMessageConstants;
import vc.thinker.cabbage.sys.bo.ImageTextBO;
import vc.thinker.cabbage.sys.bo.SysMessageBO;
import vc.thinker.cabbage.sys.dao.ImageTextDao;
import vc.thinker.cabbage.sys.dao.SysMessageDao;
import vc.thinker.cabbage.sys.model.SysMessage;
import vc.thinker.cabbage.sys.vo.SysMessageVO;
import vc.thinker.cabbage.user.CommonConstants;

@Service
public class SysMessageService {
	
	private final static Logger log=LoggerFactory.getLogger(SysMessageService.class);
	
	@Resource(name="memberAppMessageHandler")
	@Lazy(true)
	private MobileMessageHandler memberAppMessageHandler;
	
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
	private SysMessageDao sysMessageDao;
	
	@Autowired
	private ImageTextDao imageTextDao;
	
	/**
	 * 查找首页广告消息
	 * @param uid
	 * @param timestamp
	 * @return
	 */
	public SysMessageBO findHomeMessage(Long uid,Date timestamp){
		//查找最新的首页广告
		ImageTextBO imageText = imageTextDao.findNewHomeMessage(timestamp);
		if(imageText == null){
			return null;
		}
		//判断用户是否拥有该消息
		if(sysMessageDao.countByImageTextId(uid, imageText.getId()) < 0){
			return null;
		}
		
		SysMessage sysMessage=new SysMessage();
		sysMessage.setToUserId(uid);
		sysMessage.setIsImageText(true);
		sysMessage.setImageTextId(imageText.getId());
		sysMessage.setToUserType(String.valueOf(CommonConstants.USER_TYPE_2));
		sysMessage.setSendTime(new Date());
		sysMessage.setIsPlatform(false);
		sysMessage.setIsRead(false);
		sysMessageDao.save(sysMessage);
		
		return sysMessageDao.findDetails(sysMessage.getId());
	}
	
	/**
	 * 个人消息到个人用户
	 * @param event
	 */
	@TransactionalEventListener()
	@Async
	public void sendApplyMessageToMember(MemberMessageEvent event){
		
		AlertMobileMessage message=new AlertMobileMessage(event.getContent());
		
		//附加参数，需要什么自己加
		Map<String, String> exts=Maps.newHashMap();
		message.setExts(exts);
		message.setTitle("用户消息");
		message.setSound("default");
		
		sendMessageToMobile(CommonConstants.ROLE_MEMBER_USER,event.getMsgType(),message,event.getUid());
	}
	
	
	/**
	 * 发送消息到手机
	 * @param uid
	 * @param client
	 * @param message
	 */
	public void sendMessageToMobile(String client, String msgType,MobileMessage message,String ... accessTokens){
		
		if(accessTokens.length == 0){
			log.error("accessToken is null");
			return;
		}
		
		//附加参数，需要什么自己加
		if(StringUtils.isNotBlank(msgType)){
			if(message.getExts() != null){
				message.getExts().put(SysMessageConstants.MOBILE_MESSAGE_TYPE_KEY, msgType);
			}else{
				Map<String, String> exts=Maps.newHashMap();
				exts.put(SysMessageConstants.MOBILE_MESSAGE_TYPE_KEY, msgType);
				message.setExts(exts);
			}
		}

		log.info("jpush to {},message {}",accessTokens,JSON.toJSON(message));
		switch (client) {
//		case CommonConstants.ROLE_REPAIRER_USER:
//			repairerAppMessageHandler.sendMessage(new AliasMobileChannel(accessTokens), message);
//			break;
		case CommonConstants.ROLE_MEMBER_USER:
			memberAppMessageHandler.sendMessage(new AliasMobileChannel(accessTokens), message);
			break;
		}
		
	}
	public void sendMessageToMobile(String client,String msgType,MobileMessage message,Long ... uids){
		
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
		sendMessageToMobile(client, msgType, message,  accessList.toArray(new String[accessList.size()]));
	}
	
	public List<SysMessageBO> findPageByVO(Page<SysMessageBO> page, SysMessageVO vo) {
		return sysMessageDao.findPageByVO(page,vo);
	}
	
    public List<SysMessageBO> findPageByToUser(Page<SysMessageBO> page,SysMessageVO vo){
    	return sysMessageDao.findPageByToUser(page, vo);
    }
	
    public ImageTextBO findImageTextById(Long imageTextId){
    	return imageTextDao.findOne(imageTextId);
    }
	
	public static class TripMessageEvent{
		private Long tripId;
		private String mobileMsgType;
		private String content = "";
		public String getMobileMsgType() {
			return mobileMsgType;
		}
		public void setMobileMsgType(String mobileMsgType) {
			this.mobileMsgType = mobileMsgType;
		}
		public Long getTripId() {
			return tripId;
		}
		public void setTripId(Long tripId) {
			this.tripId = tripId;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
	}
	
	public static class MemberMessageEvent{
		private Long uid;
		private String msgType;
		private String content="";
		
		public String getMsgType() {
			return msgType;
		}

		public void setMsgType(String msgType) {
			this.msgType = msgType;
		}

		public Long getUid() {
			return uid;
		}

		public void setUid(Long uid) {
			this.uid = uid;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
	}
}
