package vc.thinker.cabbage.web.action;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.se.CabinetStatusService;
import vc.thinker.cabbage.se.FeedbackService;
import vc.thinker.cabbage.se.FeedbackTypeServcie;
import vc.thinker.cabbage.se.OrderConstants;
import vc.thinker.cabbage.se.OrderService;
import vc.thinker.cabbage.se.bo.FeedbackBO;
import vc.thinker.cabbage.se.bo.FeedbackTypeBO;
import vc.thinker.cabbage.se.bo.OrderBO;
import vc.thinker.cabbage.se.model.CabinetStatus;
import vc.thinker.cabbage.se.vo.FeedbackTypeVO;
import vc.thinker.cabbage.se.vo.FeedbackVO;
import vc.thinker.cabbage.sys.bo.CouponBO;
import vc.thinker.cabbage.sys.service.CountryService;
import vc.thinker.cabbage.sys.service.PromotionService;
import vc.thinker.cabbage.user.bo.SellerBO;
import vc.thinker.cabbage.user.service.SellerService;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.model.User;
import vc.thinker.web.utils.UserUtils;

@Controller
@RequestMapping("${adminPath}/sys/feddback")
public class FeedbackController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private FeedbackTypeServcie feedbackTypeServcie;
	
	@Autowired
	private PromotionService promotionService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CabinetStatusService cabinetStatusService;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private CountryService countryService;
	
	@RequiresPermissions("sys:feed:list")
	@SecurityMapping(name = "role.list", permGroup = "role.feedback", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "typeList" })
	public String typeList(Model model,MyPage<FeedbackTypeBO> page,FeedbackTypeVO vo){
		
		feedbackTypeServcie.findPageByVo(page,vo);
		
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		return "modules/se/feedback_type_list";
	}
	
	@RequiresPermissions("sys:feed:list")
	@SecurityMapping(name = "role.list", permGroup = "role.feedback", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "list" })
	public String list(Model model,MyPage<FeedbackBO> page,FeedbackVO vo){
		
		
		feedbackService.findPageByVo(page,vo);
		
		List<FeedbackTypeBO> t_list = feedbackTypeServcie.findAll();
		
		model.addAttribute("t_list",t_list);
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		return "modules/se/feedback_list";
	}
	
	@RequiresPermissions("sys:feed:modify")
	@SecurityMapping(name = "role.edit", permGroup = "role.feedback", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "modify"})
	public String modify(Model model,Long id){
		
		FeedbackBO info = feedbackService.findDetailedOne(id);
		
//		MemberBO memberBO = memberService.findOne(info.getUid());
//		
//		try{
//			MobileBO mobile = new MobileBO(memberBO.getMobile());
//			List<CouponBO> tickets = promotionService.findAllByCurrency(mobile.getCountry().getCurrencyCode());
//			model.addAttribute("tickets", tickets);
//		}catch (IllegalArgumentException e) {
//			
//		}
		
		List<CouponBO> tickets = promotionService.findAllCoupon();
		model.addAttribute("tickets", tickets);
		
		//如何是使用中的role.feedback
		if(null != info.getOrderId()){
			OrderBO order = orderService.findOne(info.getOrderId());
			model.addAttribute("order",order);
			
			if(null != order){
				
				SellerBO seller = sellerService.findOne(order.getBorrowSellerId());
				model.addAttribute("seller",seller);
				
				if(OrderConstants.ORDER_STATUS_30 == order.getStatus()){
					CabinetStatus cabinet = cabinetStatusService.findBySysCode(info.getSysCode());
					if(null != cabinet  && null != cabinet.getChannelStatusList()){
						model.addAttribute("channelStatusList",cabinet.getChannelStatusList());
					}
				}
			}
		}
		
		model.addAttribute("info", info);
		model.addAttribute("countries",countryService.findAll());
		return "modules/se/feedback_modify";
	}
	
	@RequiresPermissions("sys:feed:modify")
	@SecurityMapping(name = "role.edit", permGroup = "role.feedback", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "save" })
	public String save(FeedbackBO bo) {
		
		User user = UserUtils.getUser();
		feedbackService.adminUpdateFeedback(bo,user);
		
		return "redirect:" + adminPath + "/sys/feddback/list";
	}
	
	@RequiresPermissions("sys:feed:detail")
	@SecurityMapping(name = "role.detail", permGroup = "role.feedback", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "queryDetail" })
	public String queryDetail(Long id, Model model) {

		FeedbackBO bo = feedbackService.findDetailedOne(id);

		CouponBO coupon = promotionService.findOne(bo.getTicketId());

		model.addAttribute("coupon", coupon);
		model.addAttribute("info", bo);
		return "modules/se/feedback_detail";
	}
	
}
