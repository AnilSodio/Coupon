package vc.thinker.cabbage.cmd;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import vc.thinker.cabbage.tcp.SessionStoreManager;

public class YunchongbaiTCPCommandPush implements TcpCommandPush {
	
	private static final Logger log=LoggerFactory.getLogger(YunchongbaiTCPCommandPush.class);

	@Autowired
	private SessionStoreManager sessionStoreManager;
	
	/**
	 * 出电池
	 * @param stationid
	 * @param orderCode
	 * @param cable
	 */
	public void sendOut(String cabinetId,String orderCode,String cable) {
		Map<String, String> sendMap=new LinkedHashMap<>();
		
		sendMap.put("EVENT_CODE", "1");
		sendMap.put("ORDERID", orderCode);
		
		//方格尔约定  cable == 4 时需要发送 cable=2 或者 3
		if("4".equals( cable)) {
			cable = "2";
		}
		
		sendMap.put("CABLE", cable);
		sendMap.put("COLORID", "7");
		sendMap.put("MSG_ID",  String.valueOf(new Date().getTime() / 1000));
		String msg= makeSendContent(sendMap);
		log.info(msg+"=====");
		sendMessage(cabinetId, msg);
	}
	
	/**
	 * 同步
	 * @param stationid
	 * @param orderCode
	 * @param cable
	 */
	public void sendSync(String cabinetId) {
		Map<String, String> sendMap=new LinkedHashMap<>();
		sendMap.put("EVENT_CODE", "58");
		sendMap.put("MSG_ID",  String.valueOf(new Date().getTime() / 1000));
		String msg= makeSendContent(sendMap);
		log.info(msg+"=====");
		sendMessage(cabinetId, msg);
	}
	
	/**	
	 * 查询消息
	 * @param cabinetId
	 * @param channle
	 */
	public void sendQuery(String cabinetId,String channle) {
		Map<String, String> sendMap=new LinkedHashMap<>();
		sendMap.put("EVENT_CODE", "52");
		sendMap.put("SLOT", channle);
		sendMap.put("MSG_ID",  String.valueOf(new Date().getTime() / 1000));
		String msg= makeSendContent(sendMap);
		log.info(msg+"=====");
		sendMessage(cabinetId, msg);
	}
	/**	
	 * 锁住槽位
	 * @param cabinetId
	 * @param channle
	 */
	public void sendLock(String cabinetId,String channle) {
		Map<String, String> sendMap=new LinkedHashMap<>();
		sendMap.put("EVENT_CODE", "53");
		sendMap.put("SLOT", channle);
		sendMap.put("MSG_ID",  String.valueOf(new Date().getTime() / 1000));
		String msg= makeSendContent(sendMap);
		log.info(msg+"=====");
		sendMessage(cabinetId, msg);
	}
	/**	
	 * 解锁槽位
	 * @param cabinetId
	 * @param channle
	 */
	public void sendUnlock(String cabinetId,String channle) {
		Map<String, String> sendMap=new LinkedHashMap<>();
		sendMap.put("EVENT_CODE", "54");
		sendMap.put("SLOT", channle);
		sendMap.put("MSG_ID",  String.valueOf(new Date().getTime() / 1000));
		String msg= makeSendContent(sendMap);
		log.info(msg+"=====");
		sendMessage(cabinetId, msg);
	}
	
	/**	
	 * 系统行为弹出
	 * @param cabinetId
	 * @param channle
	 */
	public void sendSysOut(String cabinetId,String channle) {
		Map<String, String> sendMap=new LinkedHashMap<>();
		sendMap.put("EVENT_CODE", "55");
		sendMap.put("SLOT", channle);
		sendMap.put("MSG_ID",  String.valueOf(new Date().getTime() / 1000));
		String msg= makeSendContent(sendMap);
		log.info(msg+"=====");
		sendMessage(cabinetId, msg);
	}
	
	/**
	 * login 响应
	 * @param session
	 * @param stationid
	 */
	public void sendLoginResp(IoSession session,String stationid) {
		Map<String, String> sendMap=new LinkedHashMap<>();
		sendMap.put("BINDADDRESS", "1");
		sendMap.put("STATIONID", stationid);
		String msg= makeSendContent(sendMap);
		log.info(msg+"=====");
		session.write(msg.getBytes());
	}
	
	public void sendResp(IoSession session,String errcode,String ack) {
		Map<String, String> sendMap=new LinkedHashMap<>();
		sendMap.put("ERRCODE", errcode);
		sendMap.put("ERRMSG", "success");
		sendMap.put("ACK",ack);
		String msg= makeSendContent(sendMap);
		log.info(msg);
		session.write(msg.getBytes());
	}
	
	public void sendChannelResp(IoSession session,String errcode,String channle,String ack) {
		Map<String, String> sendMap=new LinkedHashMap<>();
		sendMap.put("ERRCODE", errcode);
		sendMap.put("ERRMSG", "success");
		sendMap.put("SLOT",channle);
		sendMap.put("ACK",ack);
		String msg= makeSendContent(sendMap);
		log.info(msg);
		session.write(msg.getBytes());
	}
	
	public void sendResp(IoSession session,String errcode,String ack,String orderid) {
		Map<String, String> sendMap=new LinkedHashMap<>();
		sendMap.put("ERRCODE", errcode);
		sendMap.put("ERRMSG", "success");
		sendMap.put("ACK",ack);
		sendMap.put("ORDERID",orderid);
		String msg= makeSendContent(sendMap);
		log.info(msg);
		session.write(msg.getBytes());
	}
	
	public void sendReturnBackResp(IoSession session,String errcode,String ack,String id) {
		Map<String, String> sendMap=new LinkedHashMap<>();
		sendMap.put("ERRCODE", errcode);
		sendMap.put("ERRMSG", "update battery info success");
		sendMap.put("ID",id);
		sendMap.put("ACK",ack);
		String msg= makeSendContent(sendMap);
		log.info(msg);
		session.write(msg.getBytes());
	}
	
	
	public void sendSyncSettingResp(IoSession session,String domain,String ip
			,String port,String soft_ver,int heatbeat) {
		Map<String, String> sendMap=new LinkedHashMap<>();
		sendMap.put("TIME", String.valueOf(new Date().getTime() / 1000));
		sendMap.put("DOMAIN", domain);
		sendMap.put("IP", ip);
		sendMap.put("PORT", port);
		//sendMap.put("checkupdatedelay", "");
		//服务器上的设备版本号，默认1
		sendMap.put("SOFT_VER", soft_ver);
		//升级文件名
		//sendMap.put("FILE_NAME", "1");
		//心跳周期,单位秒
		sendMap.put("HEARTBEAT", String.valueOf(heatbeat));
		String msg= makeSendContent(sendMap);
		log.info(msg);
		session.write(msg.getBytes());
	}
	
	public void sendMessage(String code,String msg) {
		IoSession session = sessionStoreManager.getSession(code);
		if(session == null || !session.isActive()){
			log.error("Code [{}] not find session",code);
			return;
		}
		session.write(msg.getBytes());
	}

	private String makeSendContent(Map<String, String> params){
		StringBuilder msg=new StringBuilder("");
		params.forEach((k,v) -> {
			msg.append(k).append(":").append(v).append(";");
		});
		String result=StringUtils.stripEnd(msg.toString(), ";");
		return result+"\r\n";
	}
}