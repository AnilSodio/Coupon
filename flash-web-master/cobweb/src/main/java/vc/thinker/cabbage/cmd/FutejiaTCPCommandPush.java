package vc.thinker.cabbage.cmd;

import org.apache.mina.core.session.IoSession;
import org.apache.tomcat.util.buf.HexUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import vc.thinker.cabbage.tcp.SessionStoreManager;
import vc.thinker.cabbage.util.ByteUtil;

/**
 * 伏特加
 * @author james
 *
 */
public class FutejiaTCPCommandPush implements TcpCommandPush {
	
	private static final Logger log=LoggerFactory.getLogger(FutejiaTCPCommandPush.class);

	@Autowired
	private SessionStoreManager sessionStoreManager;
	
	/**
	 * 出电池
	 * @param stationid
	 * @param orderCode
	 * @param cable
	 */
	public void sendOut(String cabinetId,String orderCode,String cable) {
		StringBuilder msg=new StringBuilder("11|");
		msg.append(orderCode);
		sendMessage(cabinetId,  makeSendContent(msg.toString()));
	}
	
	/**
	 * 同步
	 * @param stationid
	 * @param orderCode
	 * @param cable
	 */
	public void sendSync(String cabinetId) {
		log.error("伏特加未实现该协议");
	}
	
	/**	
	 * 查询消息
	 * @param cabinetId
	 * @param channle
	 */
	public void sendQuery(String cabinetId,String channle) {
		log.error("伏特加未实现该协议");
	}
	
	/**	
	 * 锁住槽位
	 * @param cabinetId
	 * @param channle
	 */
	public void sendLock(String cabinetId,String channle) {
		StringBuilder msg=new StringBuilder("16|");
		msg.append(channle);
		sendMessage(cabinetId,  makeSendContent(msg.toString()));
	}
	
	/**	
	 * 解锁槽位
	 * @param cabinetId
	 * @param channle
	 */
	public void sendUnlock(String cabinetId,String channle) {
		log.error("伏特加未实现该协议");
	}
	
	/**	
	 * 系统行为弹出
	 * @param cabinetId
	 * @param channle
	 */
	public void sendSysOut(String cabinetId,String channle) {
		StringBuilder msg=new StringBuilder("12|");
		msg.append(channle);
		sendMessage(cabinetId,  makeSendContent(msg.toString()));
	}
	
	/**
	 * login 响应
	 * @param session
	 * @param stationid
	 */
	public void sendLoginResp(IoSession session,String status) {
		StringBuilder msg=new StringBuilder("20|");
		msg.append(status);
		session.write(makeSendContent(msg.toString()));
	}
	
	/**
	 * 心跳 响应
	 * @param session
	 * @param stationid
	 */
	public void sendHeartbeatResp(IoSession session) {
		StringBuilder msg=new StringBuilder("15");
		byte [] sendContent=makeSendContent(msg.toString());
		session.write(sendContent);
		log.info("send [{}] to [{}]",HexUtils.toHexString(sendContent),session);
	}
	
	
	public void sendMessage(String code,byte [] msg) {
		IoSession session = sessionStoreManager.getSession(code);
		if(session == null || !session.isActive()){
			log.error("Code [{}] not find session",code);
			return;
		}
		session.write(msg);
		log.info("send [{}] to [{}]",HexUtils.toHexString(msg),code);
	}

	private byte [] makeSendContent(String msg){
		//校验位处理
		msg=msg+"|";
		byte[] bs= msg.getBytes();
		int check=0;
		for (byte b : bs) {
			check+=b;
		}
		msg=msg+check;
		int length=msg.getBytes().length;
		byte []  reslut=new byte[4+length];
		ByteUtil.putInt(reslut, reslut.length, 0);
		System.arraycopy(msg.getBytes(), 0, reslut, 4, msg.getBytes().length);
		return reslut;
	}
}