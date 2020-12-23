package vc.thinker.cabbage.web.action;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vc.thinker.cabbage.money.service.MembershipCardService;
import vc.thinker.cabbage.money.service.VipPayLogService;
import vc.thinker.cabbage.user.bo.MembershipCardBO;
import vc.thinker.cabbage.user.bo.VipPayLogBO;
import vc.thinker.cabbage.user.vo.VipPayLogVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;

@Controller
@RequestMapping("${adminPath}/sys/vipPay")
public class VipPayLogController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private VipPayLogService vipPayLogService;
	
	@Autowired
	private  MembershipCardService membershipCardService;
	
	
	@RequiresPermissions("sys:vipPay:list")
	@SecurityMapping(name="role.list",permGroup="role.vipPayLog",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("list")
	public String list(Model model,VipPayLogVO vo,MyPage<VipPayLogBO> page) {
		
		//查询所有的会员卡
		List<MembershipCardBO> card_list = membershipCardService.findAll();
		
		vipPayLogService.findPageByVo(page,vo);
		
		model.addAttribute("vo",vo);
		model.addAttribute("page",page);
		model.addAttribute("card_list",card_list);
		
		return "modules/vipPay/vip_pay_list";
	}
	
	@RequiresPermissions("sys:vipPay:list")
	@SecurityMapping(name="role.list",permGroup="role.vipPayLog",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("log_list")
	public String log_list(Model model,VipPayLogVO vo,MyPage<VipPayLogBO> page) {
		
		vipPayLogService.findPageByVo(page,vo);
		
		model.addAttribute("vo",vo);
		model.addAttribute("page",page);
		
		return "modules/vipPay/vip_pay_log_list";
	}
	
}
