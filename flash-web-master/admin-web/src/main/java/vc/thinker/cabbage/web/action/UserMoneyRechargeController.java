package vc.thinker.cabbage.web.action;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vc.thinker.cabbage.money.service.UserMoneyRechargeService;
import vc.thinker.cabbage.user.bo.UserMoneyRechargeBO;
import vc.thinker.cabbage.user.vo.UserMoneyRechargeVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;

/**
 * 用户Top up record相关操作
 * @author thinker
 *
 */

@Controller
@RequestMapping("${adminPath}/sys/recharge")
public class UserMoneyRechargeController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private UserMoneyRechargeService userMoneyRechargeService;
	
	@RequiresPermissions("sys:recharge:list")
	@SecurityMapping(name="role.list",permGroup="role.balanceLog",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("list")
	public String list(Model model,MyPage<UserMoneyRechargeBO> page,UserMoneyRechargeVO vo){
		
		userMoneyRechargeService.findPageByVo(page,vo);
		
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		return "modules/vipPay/user_money_recharge";
	}
	
	@RequiresPermissions("sys:recharge:list")
	@SecurityMapping(name="role.list",permGroup="role.balanceLog",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("log_list")
	public String log_list(Model model,MyPage<UserMoneyRechargeBO> page,UserMoneyRechargeVO vo){
		
		userMoneyRechargeService.findPageByVo(page, vo);
		
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		return "modules/vipPay/user_money_recharge_log";
	}
	
}
