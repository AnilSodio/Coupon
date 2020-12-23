package vc.thinker.cabbage.web.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vc.thinker.cabbage.money.service.MoneyService;
import vc.thinker.cabbage.money.service.UserMoneyCashService;
import vc.thinker.cabbage.se.bo.UserRebateLogBO;
import vc.thinker.cabbage.se.dao.UserRebateLogDao;
import vc.thinker.cabbage.se.vo.UserRebateLogVO;
import vc.thinker.cabbage.user.UserMoneyConstants;
import vc.thinker.cabbage.user.bo.UserMoneyBO;
import vc.thinker.cabbage.user.bo.UserMoneyCashBO;
import vc.thinker.cabbage.user.bo.UserMoneyLogBO;
import vc.thinker.cabbage.user.dao.UserMoneyDao;
import vc.thinker.cabbage.user.dao.UserMoneyLogDao;
import vc.thinker.cabbage.user.model.UserMoney;
import vc.thinker.cabbage.user.vo.UserMoneyCashVO;
import vc.thinker.cabbage.user.vo.UserMoneyLogVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.web.security.SellerPrincipal;
import vc.thinker.core.web.BaseController;
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
		SellerPrincipal user = (SellerPrincipal)UserUtils.getPrincipal();
		vo.setUid(user.getSeller().getUid());
		userRebateLogDao.findPageByVo(page,vo);
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		return "modules/rebate/rebateList";
	}
	
	
	@RequestMapping("cashLog")
	public String cashLog(Model model,MyPage<UserMoneyCashBO> page,UserMoneyCashVO vo){
		SellerPrincipal user = (SellerPrincipal)UserUtils.getPrincipal();
		vo.setUserId(user.getSeller().getUid());
		userMoneyCashService.findPageByVo(page, vo);
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		return "modules/rebate/cashLogList";
	}
	
	
	@RequestMapping("addCash")
	public String addCash(Model model){
		SellerPrincipal user = (SellerPrincipal)UserUtils.getPrincipal();
		UserMoney money = moneyService.isExistAndCreate(user.getSeller().getUid());
		model.addAttribute("money",money);
		return "modules/rebate/addCash";
	}
	
	@RequestMapping("saveCash")
	public String saveCash(Model model,UserMoneyCashBO obj,RedirectAttributes redirectAttributes){
		SellerPrincipal user = (SellerPrincipal)UserUtils.getPrincipal();
		UserMoneyBO oldMoney = userMoneyDao.findOne(user.getSeller().getUid());
		if(oldMoney.getAvailableBalance().intValue()<obj.getCashAmount().intValue()){
			addMessage(redirectAttributes, "The amount of cash can not be greater than the available balance");
			return "redirect:" +adminPath + "/rebate/addCash";
		}else{
			obj.setCashUserId(user.getSeller().getUid());
			obj.setCashUserType(UserMoneyConstants.CASH_USER_TYPE_SELLER);
			obj.setCurrency(oldMoney.getCurrency());
			userMoneyCashService.addCash(obj);
		}
		addMessage(redirectAttributes, "The application is successful, please wait for the review");
		return "redirect:" +adminPath + "/rebate/cashLog";
	}
	
	@RequestMapping("moneyLog")
	public String moneyLog(Model model,MyPage<UserMoneyLogBO> page,UserMoneyLogVO vo){
		SellerPrincipal user = (SellerPrincipal)UserUtils.getPrincipal();
		vo.setUserId(user.getSeller().getUid());
		userMoneyLogDao.listPageByVo(page,vo);
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		return "modules/rebate/moneyLogList";
	}
}
