package vc.thinker.cabbage.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import vc.thinker.cabbage.se.CabinetConstants;
import vc.thinker.cabbage.se.CabinetService;
import vc.thinker.cabbage.se.CabinetStatusService;
import vc.thinker.cabbage.se.PortableBatteryConstatns;
import vc.thinker.cabbage.se.bo.CabinetBO;
import vc.thinker.cabbage.se.model.CabinetStatus.ChannelStatus;
import vc.thinker.cabbage.se.model.PortableBattery;
import vc.thinker.cabbage.util.CabinetTypeEnum;
import vc.thinker.opensdk.ReturnMsg;
import vc.thinker.opensdk.powerbank.PowerbankStatic;
import vc.thinker.opensdk.powerbank.relink.RelinkOpenSDK;
import vc.thinker.opensdk.powerbank.relink.RelinkPowerbank;
import vc.thinker.opensdk.powerbank.relink.RelinkSlot;

@Component
public class RelinkSyncStatusJob {
	private static final Logger log = LoggerFactory.getLogger(RelinkSyncStatusJob.class);

	@Value("${order.fail.second}")
	private int second = 120;
	
	@Autowired
	private CabinetService cabinetService;
	
	@Autowired
	private CabinetStatusService cabinetStatusService;
	
	@Autowired
	@Resource(name="relinkSDK")
	private RelinkOpenSDK relinkSDK;


	@Scheduled(cron = "0 0/3 * * * ? ")
	public void execute() {
		List<CabinetBO> relinkCabinets =  cabinetService.findCabinetsByType(CabinetTypeEnum.RELINK.getCode());
		List<CabinetBO> relinkbCabinets =  cabinetService.findCabinetsByType(CabinetTypeEnum.RELINKB.getCode());
		List<CabinetBO> relinkcCabinets =  cabinetService.findCabinetsByType(CabinetTypeEnum.RELINKC.getCode());
		List<CabinetBO> cabinets = new ArrayList<CabinetBO>();
		cabinets.addAll(relinkCabinets);
		cabinets.addAll(relinkbCabinets);
		cabinets.addAll(relinkcCabinets);
		if (!cabinets.isEmpty()) {
			for (CabinetBO cb : cabinets) {
				String sn = cb.getCabinetCode();
				ReturnMsg<RelinkPowerbank> ret = relinkSDK.callInfo(sn);
				
				log.info("充电柜编号:[{}], 同步数据返回:[{}]", sn, JSON.toJSONString(ret) );
				
				if (ret.getRetCode() == 0 && ret.getData() != null && ret.getData().getState() == 1) {
					this.sync_cabinet(ret.getData());
				}
			}
		}
	}
	
	
	
    /**
     * 同步机柜信息
     * @param model
     * @param device_id
     * @param json
     * @return
     */
    private void sync_cabinet(RelinkPowerbank powerbank){
    	log.info("同步机柜信息, 机柜编码" + powerbank.getSn(), JSONObject.toJSONString(powerbank));
    	CabinetBO cabinet=cabinetService.findByCabinetCode(powerbank.getSn());
    	
    	if(cabinet == null){
    		log.info("不存在的机柜编码 " + powerbank.getSn(), powerbank.getSn());
    	} else {
    		// get cabinet type
        	int typeId = cabinet.getTypeId().intValue();
        	CabinetTypeEnum typeEnum = CabinetTypeEnum.getValue(typeId);
        	// get cabinet type --> capacity 
        	int capacity = typeEnum.getCapacity(); // cabinet capacity
        	Integer exist = powerbank.getNum(); // slots num
        	Integer usable = 0; // available slots (>80%)
        	Integer empty=capacity - exist; //empty channels num

        	// 处理槽位中有状态的情况
        	List<ChannelStatus> channelStatusList = Lists.newArrayListWithCapacity(capacity);
        	List<RelinkSlot> slots = powerbank.getSlots();
        	Map<Integer, RelinkSlot> map = slots.stream().collect(Collectors.toMap(RelinkSlot::getNo, relinkSlot -> relinkSlot));
        	for (int i = 0; i < capacity; i++) {
        		ChannelStatus status=new ChannelStatus();
    			if (map.containsKey(i+1)) {
    				// 充电柜中的槽位（通道）组信息
    	    		String snn=map.get(i+1).getSnn();// 充电宝id（对应协议zhun中snn）
    	    		Integer slot= map.get(i+1).getNo();// 槽位号
    	    		status.setStatus(String.valueOf(PortableBatteryConstatns.PB_STATUS_ENABLE));
    	    		status.setIsReadId(StringUtils.isNotBlank(snn) && !"0".equals(snn));
    	    		channelStatusList.add(slot-1, status); //根据槽位号更新指定槽位信息 	
    			} else {
    				channelStatusList.add(status);
    			}
    		}

        	
        	List<PortableBattery> pbList=Lists.newArrayList();
        	for (RelinkSlot key : powerbank.getSlots()) {
        		if (key.getEq() > PowerbankStatic.IOT_EQ_OK) {
        			usable++; //电量充足的充电宝数量
    			}
        		
        		// 充电柜中的槽位（通道）组信息
        		String snn=key.getSnn();// 充电宝id（对应协议中snn）
        		Integer slot= key.getNo();// 槽位号
        		// 充电柜中的充电宝组信息
        		if(StringUtils.isNotBlank(snn) && !"0".equals(snn)){
        			PortableBattery pb=new PortableBattery();
        			pb.setBattType(CabinetConstants.BATTERY_TYPE_4);
        			pb.setPortableBatteryCode(snn);
        			pb.setVoltage(null);
        			pb.setAdapter(true);
        			pb.setIsdamage(null);//暂时这个字段，还不知道有什么用
        			pb.setTemperature(null);
        			pb.setElectricity(null);
        			pb.setCabinetChannel(slot);
        			pbList.add(pb);
        		}
    		}
        	
        	Map<String, Integer> usable_battery_new=Maps.newHashMap();
        	usable_battery_new.put(CabinetConstants.BATTERY_TYPE_4, usable);
        	usable_battery_new.put(CabinetConstants.BATTERY_TYPE_3, 0);
        	usable_battery_new.put(CabinetConstants.BATTERY_TYPE_2, 0);
        	usable_battery_new.put(CabinetConstants.BATTERY_TYPE_1, 0);
        	
        	Integer idlePositionNum=empty;
        	Integer existPositionNum=usable;
        	
        	cabinetStatusService.syncBattery(cabinet.getId(), channelStatusList, usable_battery_new, 
        			idlePositionNum, existPositionNum, pbList, null);
    	}
    }
}
