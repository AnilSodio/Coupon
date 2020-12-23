package vc.thinker.cabbage.web.action;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vc.thinker.cabbage.sys.bo.PlatformRevenueBO;
import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.cabbage.sys.service.PlatformRevenueService;
import vc.thinker.cabbage.sys.service.SysSettingService;
import vc.thinker.cabbage.sys.vo.PlatformRevenueVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;

@Controller
@RequestMapping("${adminPath}/sys/playReven")
public class PlatformRevenueController {

	@Autowired
	private PlatformRevenueService platformRevenueService;
	
	@Autowired
	private SysSettingService sysSettingService;
	

	@RequiresPermissions("sys:playReven:list")
	@SecurityMapping(name="list",permGroup="role.financial",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("list")
	public String list(Model model,MyPage<PlatformRevenueBO> page,PlatformRevenueVO vo){
		
		platformRevenueService.findPageByVo(page,vo);
		
		SysSetting sysSet = sysSettingService.findOne();
		
		model.addAttribute("sysSet",sysSet);
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		return "modules/se/platform_reven_list";
	}
}
