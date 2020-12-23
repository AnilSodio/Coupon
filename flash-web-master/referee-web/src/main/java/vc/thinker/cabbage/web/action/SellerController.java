package vc.thinker.cabbage.web.action;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vc.thinker.cabbage.se.CabinetService;
import vc.thinker.cabbage.se.FeedbackService;
import vc.thinker.cabbage.se.OrderService;
import vc.thinker.cabbage.se.bo.CabinetBO;
import vc.thinker.cabbage.se.bo.FeedbackBO;
import vc.thinker.cabbage.se.bo.OrderBO;
import vc.thinker.cabbage.se.vo.OrderVO;
import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.cabbage.sys.service.SysSettingService;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.bo.SellerBO;
import vc.thinker.cabbage.user.bo.UserCouponBO;
import vc.thinker.cabbage.user.dao.AdminDao;
import vc.thinker.cabbage.user.model.Admin;
import vc.thinker.cabbage.user.service.MemberService;
import vc.thinker.cabbage.user.service.SellerService;
import vc.thinker.cabbage.user.service.UserCouponService;
import vc.thinker.cabbage.user.vo.SellerVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.web.security.RefereePrincipal;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.model.User;
import vc.thinker.web.utils.UserUtils;

@Controller
@RequestMapping("${adminPath}/seller")
public class SellerController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private SellerService sellerService;
	
	
	@RequestMapping("list")
	public String list(Model model,MyPage<SellerBO> page,SellerVO vo){
		RefereePrincipal user = (RefereePrincipal)UserUtils.getPrincipal();
		vo.setRefereeUid(user.getReferee().getUid());
		sellerService.findPageByVo(page, vo);
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		return "modules/seller/sellerList";
	}
	
	
}
