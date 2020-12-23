package vc.thinker.apiv2.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import springfox.documentation.annotations.ApiIgnore;
import vc.thinker.cabbage.se.CabinetConstants;
import vc.thinker.cabbage.se.CabinetFirmwareService;
import vc.thinker.cabbage.se.CabinetService;
import vc.thinker.cabbage.se.CabinetStatusService;
import vc.thinker.cabbage.se.OrderService;
import vc.thinker.cabbage.se.PortableBatteryService;
import vc.thinker.cabbage.se.bo.CabinetBO;
import vc.thinker.cabbage.se.bo.CabinetFirmwareBO;
import vc.thinker.cabbage.se.bo.PortableBatteryBO;
import vc.thinker.cabbage.se.exception.OrderNotFindException;
import vc.thinker.cabbage.se.model.CabinetStatus.ChannelStatus;
import vc.thinker.cabbage.se.model.PortableBattery;
import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.cabbage.sys.service.SysSettingService;


/**
 * 伏特加充电柜协议
 * @author james
 * 伏特加设备采用tcp + http形式交互，相关方法为设备应答http回调
 */
@RestController
@ApiIgnore
public class FutejiaController {
	
	private static final Logger log=LoggerFactory.getLogger(FutejiaController.class);
	
	@Autowired
	public CabinetService cabinetService;
	
	@Autowired
	private CabinetStatusService cabinetStatusService;
	
	@Autowired
	private PortableBatteryService portableBatteryService;
	
	@Autowired
	private OrderService orderService;
	
    @Autowired
    private SysSettingService sysSettingService;
    
    @Autowired
    private CabinetFirmwareService cabinetFirmwareService;
	
    
	/**
	 * 同步配制
	 * @param model
	 * @param device_id
	 * @param json
	 * @return
	 */
    @RequestMapping(value="/api/device/sync_setting",method=RequestMethod.POST)
    @ResponseBody
	public JSONObject sync_setting(Model model,
			@RequestBody JSONObject json){
    	
    	log.info("sync_setting={}",json.toJSONString());
    	String mac=json.getString("mac");
    	
    	String soft_ver=json.getJSONObject("device").getString("soft_ver");
    	
    	Integer device_ver=json.getJSONObject("device").getInteger("device_ver");
    	
    	CabinetFirmwareBO cabinetFirmware=cabinetFirmwareService.findLastByMouble("futejia");
    	
    	CabinetBO cabinet=cabinetService.findByCabinetCode(mac);
    	if(cabinet == null){
    		log.info("不存在的机柜编码[{}]",mac);
    		return null;
    	}
    	
    	cabinetStatusService.syncSetting(cabinet.getId(), soft_ver);
    	
    	SysSetting setting=sysSettingService.findOne();
    	
    	JSONObject reuslt=new JSONObject();
    	reuslt.put("sync_interval", 3600);
    	reuslt.put("app_version", 1);
    	reuslt.put("app_package", "null");
    	reuslt.put("app_url", "null");
    	if(cabinetFirmware != null){
    		try {
    			Integer lastVer=Integer.parseInt(cabinetFirmware.getFirmwareName());
    			if(Integer.valueOf(soft_ver) < lastVer){
    				log.info("{}当前版本号为{}，升级为服务版本号{}",mac,soft_ver,lastVer);
    				reuslt.put("app_url", cabinetFirmware.getFirmwareUrl());
    			}
			} catch (NumberFormatException e) {
				log.error("不规范的伏特加版本号[{}]",cabinetFirmware.getFirmwareName());
			}
    	}
    	
    	reuslt.put("app_start_class", "MainActivity");
    	reuslt.put("device_id", cabinet.getId());
    	reuslt.put("server_time", System.currentTimeMillis() * 0.0001);
    	reuslt.put("ip",setting.getCommunicationIp());
    	reuslt.put("port",setting.getCommunicationPort());
    	reuslt.put("ip","47.74.183.131");
    	reuslt.put("port","7003");
    	log.info("sync_setting return{}",reuslt.toJSONString());
		return reuslt;
	}
    
    /**
     * 借出确认
     * @param model
     * @param device_id
     * @param json
     * @return
     */
    @RequestMapping(value="/api/device/borrow_confirm",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject borrow_confirm(Model model,
    		@RequestParam("device_id") String device_id,
    		@RequestBody JSONObject json){
    	log.info("borrow_confirm={}",json.toJSONString());
    	JSONObject reuslt=new JSONObject();
    	String orderCode=json.getString("orderid");
    	String status=json.getString("status");
    	JSONObject battery=json.getJSONObject("battery");
    	String batteryCode=battery.getString("id");
    	Integer slot=battery.getInteger("slot");
    	
    	PortableBatteryBO pb= portableBatteryService.findByCode(batteryCode);
    	if(pb == null){
        	reuslt.put("errcode", 1);
        	reuslt.put("errmsg", "confirm error,battery id not find");    	
        	log.error("充电宝编码[{}]不存在",batteryCode);
        	return reuslt;
    	}
    	
		if(CabinetConstants.CABINET_FUTIEJIA_BORROW_STATUS_22.equals(status)){
			orderService.beginOrder(orderCode, batteryCode,slot);
		}else{
			orderService.updateBorrowCabinetStatus(orderCode, status);
		}
    	
    	reuslt.put("errcode", 0);
    	reuslt.put("errmsg", "confirm success");    	
    	return reuslt;
    }
    
    /**
     * 归还确认
     * @param model
     * @param device_id
     * @param json
     * @return
     */
    @RequestMapping(value="/api/device/return_back",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject return_back(Model model,
    		@RequestParam("device_id") String device_id,
    		@RequestBody JSONObject json){
    	
    	log.info("return_back={}",json.toJSONString());
    	
    	JSONObject reuslt=new JSONObject();
    	JSONObject battery=json.getJSONObject("battery");
    	String batteryCode=battery.getString("id");
    	Integer slot=battery.getInteger("slot");
    	
    	PortableBatteryBO pb= portableBatteryService.findByCode(batteryCode);
    	if(pb == null){
    		reuslt.put("errcode", 1);
    		reuslt.put("errmsg", "confirm error,battery id not find");    	
    		log.error("充电宝编码[{}]不存在",batteryCode);
    		return reuslt;
    	}
    	
		cabinetStatusService.returnBack(Long.parseLong(device_id),batteryCode,slot,null);
    	
		try {
			orderService.cabinetEndOrder(Long.parseLong(device_id), batteryCode,slot);
		} catch (OrderNotFindException e) {
			log.info("没有找到电池["+batteryCode+"]的相关订单");
		}
    	
    	reuslt.put("errcode", 0);
    	reuslt.put("errmsg", "confirm success");    	
    	return reuslt;
    }
    
    /**
     * 归还确认
     * @param model
     * @param device_id
     * @param json
     * @return
     */
    @RequestMapping(value="/api/device/remove_battery",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject remove_battery(Model model,
    		@RequestParam("device_id") String device_id,
    		@RequestBody JSONObject json){
    	log.info("remove_battery={}",json.toJSONString());
    	JSONObject reuslt=new JSONObject();
    	log.info("机柜[{}]人工弹出调用结果{}",device_id,json.toJSONString());
    	
    	reuslt.put("errcode", 0);
    	reuslt.put("errmsg", "remove battery successfully");    	
    	return reuslt;
    }
    
    /**
     * 同步机柜信息
     * @param model
     * @param device_id
     * @param json
     * @return
     */
    @RequestMapping(value="/api/device/sync_battery",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject sync_battery(Model model,
    		@RequestParam("device_id") Long device_id,
    		@RequestBody JSONObject json){
    	log.info("sysc_battery={}",json.toJSONString());
    	CabinetBO cabinet=cabinetService.findOne(device_id);
    	JSONObject reuslt=new JSONObject();
    	
    	if(cabinet == null){
    		log.info("不存在的机柜编码[{}]",device_id);
        	reuslt.put("errcode", 1);
    		return reuslt;
    	}
    	
    	JSONObject device=json.getJSONObject("device");
    	if(device == null){
    		log.info("上报的协议格式有误[{}]",json.toJSONString());
        	reuslt.put("errcode", 1);
    		return reuslt;
    	}
    	Integer slot_count=device.getInteger("slot_count");//总的卡槽数量
    	Integer total=device.getInteger("total");//充电宝个数
    	Integer usable=device.getInteger("usable");//电量大于80%的充电宝sdcard
    	Integer empty=device.getInteger("empty"); //没有电池的槽数量
    	Integer sdcard=device.getInteger("sdcard"); //sd存储卡数量
    	
    	Integer idlePositionNum=empty;
    	Integer existPositionNum=usable;
    	
    	Map<String, Integer> usable_battery_new=Maps.newHashMap();
    	usable_battery_new.put(CabinetConstants.BATTERY_TYPE_4, usable);
    	usable_battery_new.put(CabinetConstants.BATTERY_TYPE_3, 0);
    	usable_battery_new.put(CabinetConstants.BATTERY_TYPE_2, 0);
    	usable_battery_new.put(CabinetConstants.BATTERY_TYPE_1, 0);

    	JSONObject batteries=json.getJSONObject("batteries");
    	
    	List<ChannelStatus> channelStatusList=Lists.newArrayList();
    	List<PortableBattery> pbList=Lists.newArrayList();
    	for (String key : batteries.keySet()) {
    		JSONObject batterie=batteries.getJSONObject(key);
    		String id=batterie.getString("id");
    		Integer slot=batterie.getInteger("slot");
    		Integer power=batterie.getInteger("power");
    		Integer voltage=batterie.getInteger("voltage");
    		Integer current=batterie.getInteger("current"); //根据硬件情况自由调整
    		Integer temperature=batterie.getInteger("temperature");
    		Integer battery_status=batterie.getInteger("battery_status");
    		String slot_status=batterie.getString("slot_status");
    		String sensors=batterie.getString("sensors");//代表传感器的状态，由硬件解释，后台仅显示
    		
    		ChannelStatus status=new ChannelStatus();
    		status.setStatus(slot_status);
    		status.setIsReadId(StringUtils.isNotBlank(id) && !"0".equals(id));
    		channelStatusList.add(status);
    		
    		if(StringUtils.isNotBlank(id) && !"0".equals(id)){
    			PortableBattery pb=new PortableBattery();
    			pbList.add(pb);
    			pb.setBattType(CabinetConstants.BATTERY_TYPE_4);
    			pb.setPortableBatteryCode(id);
    			pb.setVoltage(voltage);
    			pb.setAdapter(true);
    			pb.setIsdamage(sensors);//暂时这个字段，还不知道有什么用
    			pb.setTemperature(String.valueOf(temperature));
    			pb.setElectricity(power);
    			pb.setCabinetChannel(slot);
    		}
		}
    	
    	
    	cabinetStatusService.syncBattery(cabinet.getId(), channelStatusList, usable_battery_new, 
    			idlePositionNum, existPositionNum, pbList, null);
    	
    	reuslt.put("errcode", 0);
    	reuslt.put("errmsg", "sync battery successfully");    	
    	return reuslt;
    }
}
