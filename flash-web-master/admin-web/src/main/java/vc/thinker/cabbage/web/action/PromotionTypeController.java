package vc.thinker.cabbage.web.action;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vc.thinker.cabbage.sys.bo.PromotionTypeBO;
import vc.thinker.cabbage.sys.service.PromotionTypeService;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;

@Controller
@RequestMapping(value = "${adminPath}/sys/proType")
public class PromotionTypeController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private PromotionTypeService promotionTypeService;
	
	@RequiresPermissions("sys:proType:list")
	@SecurityMapping(name = "role.list", permGroup = "role.activityType", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("list")
	public String listPromotion(Model model) {
		
		List<PromotionTypeBO> type_list = promotionTypeService.findAll();
		
		model.addAttribute("type_list",type_list);
		return "modules/promotion/promotionTypeList";
	}
	
	@RequiresPermissions("sys:proType:edit")
	@SecurityMapping(name = "role.edit", permGroup = "role.activityType", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("modify")
	public String modify(Model model,Long id) {
		
		PromotionTypeBO type_bo = null;
		
		if(null != id ) {
			type_bo = promotionTypeService.findOne(id);
		}else {
			type_bo = new PromotionTypeBO();
			
		}
		model.addAttribute("info",type_bo);
		return "modules/promotion/promotionTypeAdd";
	}
	
	@RequiresPermissions("sys:proType:edit")
	@SecurityMapping(name = "role.edit", permGroup = "role.activityType", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("save")
	public String save(PromotionTypeBO bo) {
		
		
		promotionTypeService.saveOrUpdate(bo);
		
		return "redirect:"+adminPath+"/sys/proType/list";
	}
	
	@RequiresPermissions("sys:proType:delete")
	@SecurityMapping(name = "role.delete", permGroup = "role.activityType", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("delete")
	@ResponseBody
	public String delete(Long id) {
		
		promotionTypeService.delete(id);
		
		return "1";
//		return "redirect:"+adminPath+"/sys/proType/list";
	}
	
}
