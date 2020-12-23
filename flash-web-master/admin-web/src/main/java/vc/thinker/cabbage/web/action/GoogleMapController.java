package vc.thinker.cabbage.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jiguang.common.utils.StringUtils;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.se.CabinetFirmwareService;
import vc.thinker.cabbage.se.CabinetService;
import vc.thinker.cabbage.se.PortableBatteryService;
import vc.thinker.cabbage.se.SeCommonConstants;
import vc.thinker.cabbage.se.bo.CabinetBO;
import vc.thinker.cabbage.se.bo.CabinetFirmwareBO;
import vc.thinker.cabbage.se.bo.PortableBatteryBO;
import vc.thinker.cabbage.se.model.PortableBattery;
import vc.thinker.cabbage.se.vo.CabinetVO;
import vc.thinker.cabbage.se.vo.PortableBatteryVO;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.bo.OfficeBO;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.service.OfficeService;
import vc.thinker.web.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/sys/googleMap")
public class GoogleMapController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Value("${google.js.key}")
	private String googleJsKye;
	
	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private CabinetService cabinetService;
	
	@Autowired
	private PortableBatteryService portableBatteryService;
	
	@Autowired
	private CabinetFirmwareService cabinetFirmwareService;
	
	@RequiresPermissions("sys:liveData:initMap")
	@SecurityMapping(name="role.liveMap",permGroup="role.liveData",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("initMap")
	public String initMap(Model model){
		
		model.addAttribute("googleJsKye",googleJsKye);
		
		return "modules/map_init";
	}
	
	@RequestMapping("mapQuery")
	@ResponseBody
	public List<Map<String,Object>> mapQuery(Model model,Double lat ,Double lng,String sysCode){
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		if(!StringUtils.isEmpty(sysCode)){
			list = cabinetService.findLocationBySysCode(sysCode);
		}

		if(null != lat && null != lng){
			list = cabinetService.findByLocation(lat, lng);
		}
		return list;
	}
	
	@RequiresPermissions("sys:liveData:cabinetOnline")
	@SecurityMapping(name="role.liveNomoBox",permGroup="role.liveData",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("cabinetOnline")
	public String cabinetOnline(Model model,MyPage<CabinetBO> page,CabinetVO vo){
		
		//查询当前登录人的机构信息
		OfficeBO office = officeService.get(UserUtils.getUser().getOfficeId());
		if(null != office){
			String officeName = office.getName();
			model.addAttribute("officeName",officeName);
		}
				
		int normal = cabinetService.countByStatusAndDelivery(SeCommonConstants.CABINET_STATUS_ENABLE,true);
		int abnormal = cabinetService.countByStatusAndDelivery(SeCommonConstants.CABINET_STATUS_DISABLE, true);
		
		vo.setIsDelivery(true);
		cabinetService.findOnlineDate(page,vo);
		
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		model.addAttribute("normal",normal);
		model.addAttribute("abnormal",abnormal);
		
		return "modules/online/cabinet_online";
	}
	
	@RequiresPermissions("sys:liveData:PowerbankOnline")
	@SecurityMapping(name="role.livePb",permGroup="role.liveData",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("pbOnline")
	public String pbOnline(Model model,MyPage<PortableBatteryBO> page,PortableBatteryVO vo){
		
		if(null != vo.getCabinetId()){
			CabinetBO info = cabinetService.findOne(vo.getCabinetId());
			vo.setSysCode(info.getSysCode());
		}
		portableBatteryService.findOnlineData(page, vo);
		
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		return "modules/online/pb_online";
	}
	
	
	@RequiresPermissions("sys:liveData:pbModify")
	@SecurityMapping(name="role.ableOrDisable",permGroup="role.liveData",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("pbDisAndEnable")
	@ResponseBody
	public String pbDisAndEnable(Long id,int status){
		
		PortableBattery bp = new PortableBattery();
		bp.setId(id);
		bp.setUpdateTime(new Date());
		bp.setStatus(status);
		
		portableBatteryService.update(bp);
		
		return "0000";
	}
	@RequiresPermissions("sys:liveData:upgrade")
	@SecurityMapping(name="Powerbank upgrade",permGroup="Live Data",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("updateForm")
	public String updateForm(Model model){
		
		List<CabinetFirmwareBO> firmwares = cabinetFirmwareService.findAll();
		
		model.addAttribute("firmwares",firmwares);
		
		return "modules/online/upgrade";
	}
	
	@RequiresPermissions("sys:liveData:upgrade")
	@SecurityMapping(name="Powerbank upgrade",permGroup="Live Data",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("upgrade")
	@ResponseBody
	public String upgrade(String sysCode,Long id){
		
		return "0000";
	}
}

