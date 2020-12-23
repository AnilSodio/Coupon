//package vc.thinker.cabbage.web.action;
//
//import java.util.List;
//
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import vc.thinker.cabbage.se.PortableBatteryService;
//import vc.thinker.cabbage.se.PortableBatteryTypeService;
//import vc.thinker.cabbage.se.bo.PortableBatteryBO;
//import vc.thinker.cabbage.se.bo.PortableBatteryTypeBO;
//import vc.thinker.cabbage.se.vo.PortableBatteryTypeVO;
//import vc.thinker.cabbage.se.vo.PortableBatteryVO;
//import vc.thinker.cabbage.common.MyPage;
//import vc.thinker.core.security.SecurityMapping;
//import vc.thinker.sys.contants.SysUserContant;
//import vc.thinker.sys.model.User;
//import vc.thinker.web.utils.UserUtils;
//
//@Controller
//@RequestMapping(value = "${adminPath}/sys/battery")
//public class PortableBatteryController {
//
//	@Value("${adminPath}")
//	private String adminPath;
//	
//	@Autowired
//	private PortableBatteryTypeService portableBatteryTypeService;
//	
//	@Autowired
//	private PortableBatteryService portableBatteryService;
//	
//	@RequiresPermissions("sys:battery:list")
//	@SecurityMapping(name="list",permGroup="Powebank management",userType=SysUserContant.USER_TYPE_1)
//	@RequestMapping("typeList")
//	public String typeList(Model model,MyPage<PortableBatteryTypeBO> page, PortableBatteryTypeVO vo){
//		
//		portableBatteryTypeService.findPageByVo(page, vo);
//		
//		model.addAttribute("page",page);
//		model.addAttribute("vo",vo);
//		
//		return "modules/se/battery_type_list";
//	}
//	
//	@RequiresPermissions("sys:battery:list")
//	@SecurityMapping(name="list",permGroup="Powebank management",userType=SysUserContant.USER_TYPE_1)
//	@RequestMapping("typeModify")
//	public String typeModify(Model model,Long id){
//		
//		PortableBatteryTypeBO info = null;
//		
//		if(null != id){
//			info = portableBatteryTypeService.findOne(id);
//		}
//		
//		if(null == info){
//			info = new PortableBatteryTypeBO();
//		}
//		
//		model.addAttribute("info",info);
//		
//		return "modules/se/battery_type_modify"; 
//	}
//	
//	
//	@RequiresPermissions("sys:battery:list")
//	@SecurityMapping(name="list",permGroup="Powebank management",userType=SysUserContant.USER_TYPE_1)
//	@RequestMapping("typeSave")
//	public String typeSave(PortableBatteryTypeBO bo){
//		
//		Long uid = UserUtils.getUser().getId();
//		
//		portableBatteryTypeService.adminSaveOrUpdate(uid,bo);
//		
//		return "redirect:" + adminPath +"/sys/battery/typeList";
//	}
//	
//	@RequestMapping("checkTypeName")
//	@ResponseBody
//	public Boolean checkTypeName(Long id,String typeName){
//		
//		Boolean result = portableBatteryTypeService.checkTypeName(id, typeName);
//		
//		return result;
//	}
//	
//	@RequestMapping("typeDisAndEnable")
//	@ResponseBody
//	public String typeDisAndEnable(Long id,int status){
//		
//		PortableBatteryTypeBO info = portableBatteryTypeService.findOne(id);
//		
//		if(null == info){
//			return "数据不存在，请联系后台";
//		}
//		
//		User user = UserUtils.getUser();
//		
//		PortableBatteryTypeBO up_info = new PortableBatteryTypeBO();
//		up_info.setId(info.getId());
//		up_info.setStatus(status);
//		
//		portableBatteryTypeService.adminSaveOrUpdate(user.getId(), up_info);
//		
//		return "0000";
//	}
//	
//	@RequestMapping("typeDelete")
//	@ResponseBody
//	public String typeDelete(Long id){
//		
//		PortableBatteryTypeBO info = portableBatteryTypeService.findOne(id);
//		if(null == info){
//			return "数据有误，请联系后台";
//		}
//		
//		portableBatteryTypeService.delete(id);
//		
//		return "0000";
//	}
//	
//	@RequiresPermissions("sys:battery:list")
//	@SecurityMapping(name="list",permGroup="Powebank management",userType=SysUserContant.USER_TYPE_1)
//	@RequestMapping("list")
//	public String list(Model model,MyPage<PortableBatteryBO> page,PortableBatteryVO vo){
//		
//		portableBatteryService.findPageByVo(page, vo);
//		
//		List<PortableBatteryTypeBO> typeList = portableBatteryTypeService.findAll();
//		model.addAttribute("typeList",typeList);
//		model.addAttribute("page",page);
//		model.addAttribute("vo",vo);
//		
//		return "modules/se/battery_list";
//	}
//	
//	
//	@RequiresPermissions("sys:battery:list")
//	@SecurityMapping(name="list",permGroup="Powebank management",userType=SysUserContant.USER_TYPE_1)
//	@RequestMapping("modify")
//	public String modify(Model model,Long id){
//		
//		PortableBatteryBO info = null;
//		if(null != id){
//			info = portableBatteryService.findOne(id);;
//		}
//		
//		if(null == info){
//			info = new PortableBatteryBO();
//		}
//		
//		List<PortableBatteryTypeBO> typeList = portableBatteryTypeService.findAll();
//		model.addAttribute("typeList",typeList);
//		model.addAttribute("info",info);
//		
//		return "modules/se/battery_modify";
//	}
//	
//	@RequiresPermissions("sys:battery:list")
//	@SecurityMapping(name="list",permGroup="Powebank management",userType=SysUserContant.USER_TYPE_1)
//	@RequestMapping("save")
//	public String save(PortableBatteryBO bo){
//		
//		Long uid = UserUtils.getUser().getId();
//		
//		portableBatteryService.adminSaveOrUpdate(uid,bo);
//		
//		return "redirect:" + adminPath + "/sys/battery/list";
//	}
//	
//	
//	@RequestMapping("checkPortableBatteryCode")
//	@ResponseBody
//	public Boolean checkPortableBatteryCode(Long id,String portableBatteryCode){
//		
//		Boolean result = portableBatteryService.checkPortableBatteryCode(id, portableBatteryCode);
//		
//		return result;
//	}
//	
//	
//	@RequestMapping("disAndEnable")
//	@ResponseBody
//	public String disAndEnable(Long id,int status){
//		
//		PortableBatteryBO info = portableBatteryService.findOne(id);
//		
//		if(null == info){
//			return "数据不存在，请联系后台";
//		}
//		
//		User user = UserUtils.getUser();
//		
//		PortableBatteryBO up_info = new PortableBatteryBO();
//		up_info.setId(info.getId());
//		up_info.setStatus(status);
//		
//		portableBatteryService.adminSaveOrUpdate(user.getId(), up_info);
//		
//		return "0000";
//	}
//	
//	@RequestMapping("delete")
//	@ResponseBody
//	public String delete(Long id){
//		
//		PortableBatteryBO info = portableBatteryService.findOne(id);
//		
//		if(null == info){
//			return "数据有误，请联系后台";
//		}
//		
//		portableBatteryService.delete(id);
//		
//		return "0000";
//	}
//	
//}
