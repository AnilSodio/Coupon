package vc.thinker.cabbage.web.action;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vc.thinker.cabbage.money.service.UserRebateLogService;
import vc.thinker.cabbage.se.bo.UserRebateLogBO;
import vc.thinker.cabbage.se.vo.UserRebateLogVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;

@Controller
@RequestMapping("${adminPath}/sys/userRebateLog")
public class UserRebateLogController {

	@Autowired
	private UserRebateLogService userRebateLogService;
	
	
	@RequiresPermissions("sys:userRebate:list")
	@SecurityMapping(name="list",permGroup="role.rebate",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("list")
	public  String list(Model model,MyPage<UserRebateLogBO> page,UserRebateLogVO vo){
		
		List<UserRebateLogBO> sum_list = userRebateLogService.sumByCurrency();
		
		userRebateLogService.findPageByAdmin(page, vo);
		
		model.addAttribute("sum_list",sum_list);
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		return "modules/se/user_rebate_log";
	}
	
	
	@RequiresPermissions("sys:userRebate:list")
	@SecurityMapping(name="role.list",permGroup="role.rebate",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("sellerList")
	public  String sellerList(Model model,MyPage<UserRebateLogBO> page,UserRebateLogVO vo){
		
		userRebateLogService.findPageBySeller(page, vo);
		
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		return "modules/se/seller_reven_list";
	}
	
	@RequiresPermissions("sys:userRebate:list")
	@SecurityMapping(name="role.list",permGroup="role.rebate",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("agentList")
	public String agentList(Model model,MyPage<UserRebateLogBO> page,UserRebateLogVO vo){
		
		userRebateLogService.findPageByAgent(page,vo);
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		return "modules/se/agent_reven_list";
	}
	
	@RequiresPermissions("sys:userRebate:list")
	@SecurityMapping(name="role.list",permGroup="role.rebate",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("refereeList")
	public String refereeList(Model model,MyPage<UserRebateLogBO> page,UserRebateLogVO vo){
		
		userRebateLogService.findPageByReferee(page,vo);
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		return "modules/se/referee_reven_list";
	}
}
