package vc.thinker.cabbage.web.action;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vc.thinker.cabbage.money.service.MoneyService;
import vc.thinker.cabbage.money.service.UserMoneyCashService;
import vc.thinker.cabbage.se.CabinetService;
import vc.thinker.cabbage.se.FeedbackService;
import vc.thinker.cabbage.se.OrderService;
import vc.thinker.cabbage.se.bo.CabinetBO;
import vc.thinker.cabbage.se.bo.FeedbackBO;
import vc.thinker.cabbage.se.bo.OrderBO;
import vc.thinker.cabbage.se.bo.UserRebateLogBO;
import vc.thinker.cabbage.se.dao.UserRebateLogDao;
import vc.thinker.cabbage.se.vo.OrderVO;
import vc.thinker.cabbage.se.vo.UserRebateLogVO;
import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.cabbage.sys.service.SysSettingService;
import vc.thinker.cabbage.user.UserMoneyConstants;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.bo.SellerBO;
import vc.thinker.cabbage.user.bo.UserCouponBO;
import vc.thinker.cabbage.user.bo.UserMoneyBO;
import vc.thinker.cabbage.user.bo.UserMoneyCashBO;
import vc.thinker.cabbage.user.bo.UserMoneyLogBO;
import vc.thinker.cabbage.user.dao.AdminDao;
import vc.thinker.cabbage.user.dao.UserMoneyDao;
import vc.thinker.cabbage.user.dao.UserMoneyLogDao;
import vc.thinker.cabbage.user.model.Admin;
import vc.thinker.cabbage.user.model.UserMoney;
import vc.thinker.cabbage.user.service.MemberService;
import vc.thinker.cabbage.user.service.SellerService;
import vc.thinker.cabbage.user.service.UserCouponService;
import vc.thinker.cabbage.user.vo.UserMoneyCashVO;
import vc.thinker.cabbage.user.vo.UserMoneyLogVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.web.security.AgentPrincipal;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.core.web.BaseController;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.model.User;
import vc.thinker.web.utils.UserUtils;

@Controller
@RequestMapping("${adminPath}/rebate")
public class RebateController extends BaseController{

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private UserRebateLogDao userRebateLogDao;
	
	@Autowired
	private UserMoneyCashService userMoneyCashService;
	
	@Autowired
	private UserMoneyDao userMoneyDao;
	@Autowired
	private UserMoneyLogDao userMoneyLogDao;
	@Autowired
	private MoneyService moneyService;
	
	@RequestMapping("list")
	public String list(Model model,MyPage<UserRebateLogBO> page,UserRebateLogVO vo){
		AgentPrincipal user = (AgentPrincipal)UserUtils.getPrincipal();
		vo.setUid(user.getAgent().getUid());
		userRebateLogDao.findPageByVo(page,vo);
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		return "modules/rebate/rebateList";
	}
	
	
	@RequestMapping("cashLog")
	public String cashLog(Model model,MyPage<UserMoneyCashBO> page,UserMoneyCashVO vo){
		AgentPrincipal user = (AgentPrincipal)UserUtils.getPrincipal();
		vo.setUserId(user.getAgent().getUid());
		userMoneyCashService.findPageByVo(page, vo);
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		return "modules/rebate/cashLogList";
	}
	
	
	@RequestMapping("addCash")
	public String addCash(Model model){
		AgentPrincipal user = (AgentPrincipal)UserUtils.getPrincipal();
		UserMoney money = moneyService.isExistAndCreate(user.getAgent().getUid());
		model.addAttribute("money",money);
		return "modules/rebate/addCash";
	}
	
	@RequestMapping("saveCash")
	public String saveCash(Model model,UserMoneyCashBO obj,RedirectAttributes redirectAttributes){
		AgentPrincipal user = (AgentPrincipal)UserUtils.getPrincipal();
		UserMoneyBO oldMoney = userMoneyDao.findOne(user.getAgent().getUid());
		if(oldMoney.getAvailableBalance().intValue()<obj.getCashAmount().intValue()){
			addMessage(redirectAttributes, "The amount of cash can not be greater than the available balance");
			return "redirect:" +adminPath + "/rebate/addCash";
		}else{
			obj.setCashUserId(user.getAgent().getUid());
			obj.setCashUserType(UserMoneyConstants.CASH_USER_TYPE_AGENT);
			obj.setCurrency(oldMoney.getCurrency());
			userMoneyCashService.addCash(obj);
		}
		addMessage(redirectAttributes, "The application is successful, please wait for the review");
		return "redirect:" +adminPath + "/rebate/cashLog";
	}
	
	@RequestMapping("moneyLog")
	public String moneyLog(Model model,MyPage<UserMoneyLogBO> page,UserMoneyLogVO vo){
		AgentPrincipal user = (AgentPrincipal)UserUtils.getPrincipal();
		vo.setUserId(user.getAgent().getUid());
		userMoneyLogDao.findPageByVO(vo, page);
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		return "modules/rebate/moneyLogList";
	}
}
