package vc.thinker.cabbage.actors;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import akka.actor.UntypedActor;
import vc.thinker.cabbage.CobwebApplication;
import vc.thinker.cabbage.cmd.YunchongbaiTCPCommandPush;
import vc.thinker.cabbage.se.CabinetConstants;
import vc.thinker.cabbage.se.CabinetStatusService;
import vc.thinker.cabbage.se.OrderService;
import vc.thinker.cabbage.se.exception.CabinetNotFindException;
import vc.thinker.cabbage.se.exception.OrderNotFindException;
import vc.thinker.cabbage.se.model.CabinetStatus;
import vc.thinker.cabbage.se.model.CabinetStatus.ChannelStatus;
import vc.thinker.cabbage.se.model.PortableBattery;
import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.cabbage.sys.service.SysSettingService;
import vc.thinker.cabbage.tcp.SessionStoreManager;

@Component
@Scope("prototype")
public class YunchongbaiTcpActors extends UntypedActor{

	private static final Logger log = LoggerFactory.getLogger(YunchongbaiTcpActors.class);
	
    @Autowired
    private SessionStoreManager sessionStoreManager;
    
    @Autowired
    private YunchongbaiTCPCommandPush yunchongbaiTCPCommandPush;
    
    @Autowired
    private CabinetStatusService cabinetStatusService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private SysSettingService sysSettingService;
	
	private IoSession session;

	public YunchongbaiTcpActors(IoSession session){
		this.session = session;
	}

	@Override
	public void onReceive(Object message) throws Exception {
		//System.out.println("message from " + session.getRemoteAddress() + " :" + message);
		// session.write( "*CMDS,ML,863725031181827,000000000000,L0,0#" );
		byte[] msg = (byte[]) message;
		String orgmsg =  new String(msg).trim();
		//log.info("message from " + session.getRemoteAddress() + " :" + message);
		log.info("message from " + session.getRemoteAddress() + " :" + orgmsg);
		
		String[] ss1 = StringUtils.split(orgmsg, "\r\n");
		
		Set<String> sets = Sets.newHashSet();
		for(String s1:ss1){
			 sets.add(StringUtils.stripEnd(s1.trim(), "\r\n"));
		}
		
		String STATIONID=null;
		for(String mmsg : sets){
			Map<String, String> cmd=getCmd(mmsg);
			YunchongbaiCmd cmdType=YunchongbaiCmd.valueOf(cmd.get("ACT"));

			switch(cmdType){
			case login:
				//签到
				String mac=cmd.get("MAC");
				try {
					CabinetStatus cs=cabinetStatusService.login(mac, CobwebApplication.serviceCode);
					yunchongbaiTCPCommandPush.sendLoginResp(session, String.valueOf(cs.getCid()));
				} catch (CabinetNotFindException e) {
					log.error("MAC[{}] not find",mac);
				}
				break;
			case sync_setting:
				//同步配制
				String lastStopTimep=cmd.get("L_PD");
				String stationid=cmd.get("STATIONID");
				String device_ver=cmd.get("DEVICE_VER");
				String soft_ver=cmd.get("SOFT_VER");
				
				//临时加入
				if(stationid.equals("7") || stationid.equals("85") || stationid.equals("50")){
					yunchongbaiTCPCommandPush.sendSyncSettingResp(session, 
							"mini.dev.yunchongba.com",
							"120.25.71.101",
							"16325", soft_ver,150);
					break;
				}
				
				SysSetting sysSetting=sysSettingService.findOne();
				
				yunchongbaiTCPCommandPush.sendSyncSettingResp(session, 
						sysSetting.getCommunicationDomainName(),
						sysSetting.getCommunicationIp(),
						sysSetting.getCommunicationPort(), soft_ver,150);
				break;
			case sync_battery:
				//带此参数表示同步整个充电柜电池信息，不带则只是增量同步
				STATIONID=syncBattery(cmd);
				break;
			case heartbeat:
				log.info("heartbeat ==========");
				STATIONID=heartbeat(cmd);
				break;
			case rent_confirm:
				rentConfirm(cmd);
				break;
			case return_back:
				returnBack(cmd);
				break;
			case slot_lock:
				log.info("通道[{}]锁住调用结果 STATUS[{}] ",cmd.get("SLOT"),cmd.get("STATUS"));
				yunchongbaiTCPCommandPush.sendChannelResp(session, "0", cmd.get("SLOT"),YunchongbaiCmd.slot_lock.name());
				break;
			case slot_unlock:
				log.info("通道[{}]解锁调用结果 STATUS[{}] ",cmd.get("SLOT"),cmd.get("STATUS"));
				yunchongbaiTCPCommandPush.sendChannelResp(session, "0", cmd.get("SLOT"),YunchongbaiCmd.slot_unlock.name());
				break;
			case popup_confirm:
				log.info("通道[{}]人工弹出调用结果 STATUS[{}] ",cmd.get("SLOT"),cmd.get("STATUS"));
				yunchongbaiTCPCommandPush.sendChannelResp(session, "0", cmd.get("SLOT"),YunchongbaiCmd.popup_confirm.name());
				break;
			case battery_start:
				//签到
				//带此参数表示同步整个充电柜电池信息，不带则只是增量同步
				String STATUS1=cmd.get("STATUS");
//				sendMap.put("ERRCODE", "0");
//				sendMap.put("ERRMSG", "");
//				sendMap.put("ACK",cmdType.name());
//				System.out.println(makeSendContent(sendMap));
				break;
			default:
				break;
			}
			
			//更新内存会话
			if(STATIONID != null){
				sessionStoreManager.setSession(STATIONID, session);
			}
		}
	}
	
	/**
	 * 归还
	 * @param cmd
	 * @return
	 */
	private String returnBack(Map<String, String> cmd){
		//机柜
		String STATIONID=cmd.get("STATIONID");
		//电池id
		String ID=cmd.get("ID");
		Map<String, Integer> usable_battery_new=getBatteryTypeCount(cmd.get("USABLE_BATTERY_NEW"));
		int usable_battery=Integer.parseInt(cmd.get("USABLE_BATTERY"));
		int empty_slot_count=Integer.parseInt(cmd.get("EMPTY_SLOT_COUNT")); //可还数量
		String SLOT=cmd.get("SLOT"); //对应槽位号
		
		Integer channel=null;
		if(StringUtils.isNotBlank(SLOT)){
			channel=Integer.parseInt(SLOT);
		}
		cabinetStatusService.returnBack(Long.parseLong(STATIONID), usable_battery_new
				, usable_battery, empty_slot_count,ID,channel,CobwebApplication.serviceCode);
		
		try {
			orderService.cabinetEndOrder(Long.parseLong(STATIONID), ID, channel);
		} catch (OrderNotFindException e) {
			log.info("没有找到电池["+ID+"]的相关订单");
		}finally{
			yunchongbaiTCPCommandPush.sendReturnBackResp(session, "0", YunchongbaiCmd.return_back.name(), ID);
		}
		return STATIONID;
	}
	/**
	 * 借出反馈
	 * @param cmd
	 * @return
	 */
	private String rentConfirm(Map<String, String> cmd){
		String STATIONID=cmd.get("STATIONID");
		String STATUS=cmd.get("STATUS");
		//订单编码
		String ORDERID=cmd.get("ORDERID");
		//电池id
		String ID=cmd.get("ID");
		String COLORID=cmd.get("COLORID");
		String POWER=cmd.get("POWER"); //电量
		String ISDAMAGE=cmd.get("ISDAMAGE"); //电量
		String VOLTAGE=cmd.get("VOLTAGE"); //电压
		String ADAPTER=cmd.get("ADAPTER"); //是否带充电头 0或1
		String CABLE=cmd.get("CABLE"); //是否点充电线 0或1
		String SLOT=cmd.get("SLOT"); //对应槽位号
		String TEMPERATURE=cmd.get("TEMPERATURE"); //温度
		String BATT_TYPE=cmd.get("BATT_TYPE"); //温度
		
		
		PortableBattery pb=new PortableBattery();
		pb.setPortableBatteryCode(ID);
		pb.setColorId(COLORID);
		pb.setIsdamage(ISDAMAGE);
		pb.setVoltage(Integer.parseInt(VOLTAGE));
		pb.setAdapter("1".equals(ADAPTER));
		pb.setCable(CABLE);
		pb.setTemperature(TEMPERATURE);
		pb.setBattType(BATT_TYPE);
		pb.setElectricity(Integer.parseInt(POWER));
		try {
			//正常供出
			if(CabinetConstants.CABINET_BORROW_STATUS_1.equals(STATUS)){
				cabinetStatusService.updateBattery(pb);
				orderService.beginOrder(ORDERID, ID,Integer.parseInt(SLOT));
			}else{
				orderService.updateBorrowCabinetStatus(ORDERID, STATUS);
			}
		}finally{
			yunchongbaiTCPCommandPush.sendResp(session, "0", YunchongbaiCmd.rent_confirm.name(),ORDERID);
		}
		return STATIONID;
	}
	
	/**
	 * 心跳
	 * @param cmd
	 * @return
	 */
	private String heartbeat(Map<String, String> cmd){
		String STATIONID=cmd.get("STATIONID");
		if(STATIONID != null){
			String slotstatus=cmd.get("SLOTSTATUS");
			//List<ChannelStatus> channelStatusList=makeChannelStatus(slotstatus);
			Map<String, Integer> usable_battery_new=getBatteryTypeCount(cmd.get("USABLE_BATTERY_NEW"));
			int usable_battery=Integer.parseInt(cmd.get("USABLE_BATTERY"));
			int empty_slot_count=Integer.parseInt(cmd.get("EMPTY_SLOT_COUNT")); //可还数量
			cabinetStatusService.heartbeat(Long.parseLong(STATIONID), null, 
					usable_battery_new, empty_slot_count, usable_battery, CobwebApplication.serviceCode);
		}
		yunchongbaiTCPCommandPush.sendResp(session, "0",YunchongbaiCmd.heartbeat.name());
		return STATIONID;
	}
	
	/**
	 * 同步电池
	 * @param cmd
	 * @return
	 */
	private String syncBattery(Map<String, String> cmd){
		try {
			
			//带此参数表示同步整个充电柜电池信息，不带则只是增量同步
			String STATIONID=cmd.get("STATIONID");
			String TYPE=cmd.get("TYPE");
			//槽位状态
			String slotstatus=cmd.get("SLOTSTATUS");
			Map<String, Integer> usable_battery_new=getBatteryTypeCount(cmd.get("USABLE_BATTERY_NEW"));
			int usable_battery=Integer.parseInt(cmd.get("USABLE_BATTERY"));
			int empty_slot_count=Integer.parseInt(cmd.get("EMPTY_SLOT_COUNT")); //可还数量
			Long cabinetId=Long.parseLong(STATIONID);
			List<ChannelStatus> channelStatusList=makeChannelStatus(slotstatus);
			
			List<PortableBattery> pbList=Lists.newArrayList();
			for (int i = 0; i < channelStatusList.size(); i++) {
				ChannelStatus channelStatus=channelStatusList.get(i);
				if(channelStatus.getIsReadId()){
					int channel = i+1;
					if(cmd.containsKey("B"+channel+"ID")){
						String id=cmd.get("B"+channel+"ID"); //电池ID，中间的数字为对应槽位号
						String COLORID=cmd.get("B"+channel+"COLORID"); //颜色ID，中间的数字为对应槽位号
						String POWER=cmd.get("B"+channel+"POWER"); //电量
						String ISDAMAGE=cmd.get("B"+channel+"ISDAMAGE");//是否故障 0或1，中间的数字为对应槽位号
						String VOLTAGE=cmd.get("B"+channel+"VOLTAGE");//电压
						String ADAPTER=cmd.get("B"+channel+"ADAPTER");//是否带充电头 0或1，中间的数字为对应槽位号
						String CABLE=cmd.get("B"+channel+"CABLE");//是否带充电线 0或1，中间的数字为对应槽位号
						String TEMPERATURE=cmd.get("B"+channel+"TEMPERATURE");//温度，中间的数字为对应槽位号
						String BATT_TYPE=cmd.get("B"+channel+"BATT_TYPE");//电池类型
						
						PortableBattery pb=new PortableBattery();
						pb.setPortableBatteryCode(id);
						pb.setColorId(COLORID);
						pb.setIsdamage(ISDAMAGE);
						pb.setVoltage(Integer.parseInt(VOLTAGE));
						pb.setCabinetChannel(channel);
						pb.setAdapter("1".equals(ADAPTER));
						pb.setCable(CABLE);
						pb.setTemperature(TEMPERATURE);
						pb.setBattType(BATT_TYPE);
						pb.setElectricity(Integer.parseInt(POWER));
						pb.setCreateTime(new Date());
						pbList.add(pb);
					}
				}
			}
			
			cabinetStatusService.syncBattery(cabinetId, channelStatusList, usable_battery_new,empty_slot_count,usable_battery,pbList,CobwebApplication.serviceCode);
			return STATIONID;
		} finally {
			yunchongbaiTCPCommandPush.sendResp(session, "0", YunchongbaiCmd.sync_battery.name());
		}
	}
	
	private List<ChannelStatus> makeChannelStatus(String slotstatus){
		String [] slotstatusArray=slotstatus.split("-");
		List<ChannelStatus> channelStatusList=new ArrayList<>();
		for (String ss : slotstatusArray) {
			if(StringUtils.isNotBlank(ss)){
				List<Boolean> onofList=binaryString2BoolenList(hexString2binaryString(ss));
				ChannelStatus cs=new ChannelStatus();
				cs.setIsReadId(onofList.get(7));
				cs.setIsButton(onofList.get(6));
				cs.setLeftOrigin(onofList.get(5));
				cs.setRightOrigin(onofList.get(4));
				cs.setLock(onofList.get(0));
				channelStatusList.add(cs);
			}
		}
		return channelStatusList;
	}
	
	/**
	 * 设置各类型数量
	 * @param usable_battery_new
	 * @param status
	 */
	private Map<String, Integer> getBatteryTypeCount(String usable_battery_new){
		String [] ubnArray=usable_battery_new.split("#");
		Map<String, Integer> result=Maps.newHashMap();
		for (String ubn : ubnArray) {
			String [] sarray=ubn.split("_");
			Integer count=Integer.parseInt(sarray[1]);
			result.put(sarray[0], count);
		}
		return result;
	}
	
	private Map<String,String> getCmd(String content){
		String [] groups=content.split(";");
		Map<String, String> result=new HashMap<>();
		for (String group : groups) {
			String [] groupArray=group.split(":");
			if(groupArray.length > 1){
				result.put(groupArray[0], groupArray[1]);
			}
		}
		return result;
	}
	
	private String makeSendContent(Map<String, String> params){
		StringBuilder msg=new StringBuilder("");
		params.forEach((k,v) -> {
			msg.append(k).append(":").append(v).append(";");
		});
		String result=StringUtils.stripEnd(msg.toString(), ";");
		return result+"\r\n";
	}
	
   public static List<Boolean> binaryString2BoolenList(String str) {
	   	char [] cs=str.toCharArray();
		List<Boolean> list=Lists.newArrayList();
		for (char c : cs) {
			list.add('1' == c);
		}
        return list;
    }
   
   public static String hexString2binaryString(String hexString)  
   {  
       if (hexString == null || hexString.length() % 2 != 0)  
           return null;  
       String bString = "", tmp;  
       for (int i = 0; i < hexString.length(); i++)  
       {  
           tmp = "0000"  
                   + Integer.toBinaryString(Integer.parseInt(hexString  
                           .substring(i, i + 1), 16));  
           bString += tmp.substring(tmp.length() - 4);  
       }  
       return bString;  
   }  
}
