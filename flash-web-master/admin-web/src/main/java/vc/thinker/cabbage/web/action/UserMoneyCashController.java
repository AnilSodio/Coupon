package vc.thinker.cabbage.web.action;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.money.service.UserMoneyCashService;
import vc.thinker.cabbage.user.bo.UserMoneyCashBO;
import vc.thinker.cabbage.user.vo.UserMoneyCashVO;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.model.User;
import vc.thinker.web.utils.UserUtils;

@Controller
@RequestMapping("${adminPath}/sys/userMoneyCash")
public class UserMoneyCashController {

	@Autowired
	private UserMoneyCashService userMoneyCashService;
	
	
	@RequiresPermissions("sys:userMoneyCash:list")
	@SecurityMapping(name = "role.list", permGroup = "role.userCash", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "sellerCash" })
	public String sellerCash(Model model,MyPage<UserMoneyCashBO> page,UserMoneyCashVO vo){
		
		userMoneyCashService.findPageBySeller(page,vo);
		
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		return "modules/money/seller_cash_list";
	}
	
	@RequiresPermissions("sys:userMoneyCash:list")
	@SecurityMapping(name = "role.list", permGroup = "role.userCash", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "refereeCash" })
	public String refereeCash(Model model,MyPage<UserMoneyCashBO> page,UserMoneyCashVO vo){
		
		userMoneyCashService.findPageByReferee(page,vo);
		
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		return "modules/money/referee_cash_list";
	}
	
	@RequiresPermissions("sys:userMoneyCash:list")
	@SecurityMapping(name = "role.list", permGroup = "role.userCash", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "agentCash" })
	public String agentCash(Model model,MyPage<UserMoneyCashBO> page,UserMoneyCashVO vo){
		
		userMoneyCashService.findPageByAgent(page,vo);
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		return "modules/money/agent_cash_list"; 
	}
	
	
	@RequiresPermissions("sys:userMoneyCash:check")
	@SecurityMapping(name="role.check",permGroup="role.userCash",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("checkForm")
	public String checkForm(Long id, Model model) {
		model.addAttribute("id", id);
		return "modules/money/cash_form";
	}
	
	@RequiresPermissions("sys:userMoneyCash:check")
	@SecurityMapping(name="role.check",permGroup="role.userCash",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("cashFormSave")
	@ResponseBody
	public String cashFormSave(Long id, String cashAdminInfo,String cashStatus) {
		
		User user = UserUtils.getUser();
		
		userMoneyCashService.adminCheck(user.getId(),id,cashAdminInfo,cashStatus);
		
		return "0000";
	}
	
	@RequiresPermissions("sys:userMoneyCash:transfer")
	@SecurityMapping(name="role.transfer",permGroup="role.userCash",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("transfer")
	@ResponseBody
	public String transfer(Long id){
	
		User user = UserUtils.getUser();
		String result = userMoneyCashService.adminTransfer(user.getId(),id);
		
		return result;
	}
	
	@RequiresPermissions("sys:userMoneyCash:detail")
	@SecurityMapping(name="role.detail",permGroup="role.userCash",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("detail") 
	public String detail(Long id,Model model){
		
		UserMoneyCashBO info = userMoneyCashService.findDetailOne(id);
		
		model.addAttribute("info",info);
		
		return "modules/money/cash_detail";
	}
}
