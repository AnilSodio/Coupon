package vc.thinker.apiv2.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import springfox.documentation.annotations.ApiIgnore;
import vc.thinker.cabbage.se.CabinetService;
import vc.thinker.cabbage.se.CabinetStatusService;
import vc.thinker.cabbage.se.OrderService;
import vc.thinker.cabbage.se.PortableBatteryService;
import vc.thinker.cabbage.se.bo.CabinetBO;
import vc.thinker.cabbage.se.bo.PortableBatteryBO;
import vc.thinker.cabbage.se.exception.OrderNotFindException;
import vc.thinker.opensdk.ReturnCode;
import vc.thinker.opensdk.powerbank.relink.RelinkOpenSDK;


/**
 * relink充电柜协议
 * @author wangdawei
 * relink设备采用http形式交互，相关方法为设备应答http回调
 */
@RestController
@ApiIgnore
public class RelinkController {
	
	private static final Logger log=LoggerFactory.getLogger(RelinkController.class);
	
	@Autowired
	public CabinetService cabinetService;
	
	@Autowired
	private CabinetStatusService cabinetStatusService;
	
	@Autowired
	private PortableBatteryService portableBatteryService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	@Resource(name="relinkSDK")
	private RelinkOpenSDK relinkSDK;
    
    /**
     * 充电宝归还回调
     * @param model
     * @param json
     * @return
     */
    @RequestMapping(value="/api/device/relink/callback",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject return_back(Model model, @RequestBody JSONObject json){
    	log.info("return_back={}",json.toJSONString());
    	JSONObject reuslt=new JSONObject();
    	String cabinetCode = json.getString("sn");
    	String batteryCode=json.getString("snn");
    	Integer slot=json.getInteger("slot");
    	
    	PortableBatteryBO pb= portableBatteryService.findByCode(batteryCode);
    	if(pb == null){
    		reuslt.put("retCode", ReturnCode.NOT_FIND_SLOT_DATA.getRetCode());
    		reuslt.put("retMsg", ReturnCode.NOT_FIND_SLOT_DATA.getRetMsg());    	
    		log.error("充电宝编码[{}]不存在",batteryCode);
    		return reuslt;
    	}
    	
    	CabinetBO cabinet = cabinetService.findByCabinetCode(cabinetCode);
		cabinetStatusService.returnBack(cabinet.getId(),batteryCode,slot,null);
    	
		try {
			orderService.cabinetEndOrder(cabinet.getId(), batteryCode,slot);
		} catch (OrderNotFindException e) {
			log.info("没有找到电池["+batteryCode+"]的相关订单");
		}
    	
    	reuslt.put("retCode", ReturnCode.SUCCESS.getRetCode());
    	reuslt.put("retMsg", ReturnCode.SUCCESS.getRetMsg());    	
    	return reuslt;
    }
}
