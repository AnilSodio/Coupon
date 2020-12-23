package vc.thinker.cabbage.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vc.thinker.cabbage.se.FeedbackService;
import vc.thinker.cabbage.se.OrderService;
import vc.thinker.cabbage.se.bo.FeedbackBO;
import vc.thinker.cabbage.se.bo.OrderBO;
import vc.thinker.cabbage.se.vo.OrderVO;
import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.cabbage.sys.service.SysSettingService;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.bo.SellerBO;
import vc.thinker.cabbage.user.bo.UserCouponBO;
import vc.thinker.cabbage.user.service.MemberService;
import vc.thinker.cabbage.user.service.SellerService;
import vc.thinker.cabbage.user.service.UserCouponService;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.web.utils.UserUtils;

@Controller
@RequestMapping("${adminPath}/order")
public class OrderController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private SysSettingService sysSettingService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserCouponService userCouponService;
	
	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private SellerService sellerService;
	
	@RequestMapping("list")
	public String list(Model model,MyPage<OrderBO> page,OrderVO vo){
		vo.setRefereeId(UserUtils.getUserId());
		orderService.findPageByVo(page, vo);
		SysSetting sysSet = sysSettingService.findOne();
		model.addAttribute("sysSet",sysSet);
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		return "modules/order/orderList";
	}
	
	@RequestMapping("detail")
	public String detail(Long id,Model model){
		
		OrderBO findOne = orderService.findOne(id);
		OrderBO trip = orderService.findDetailed(findOne.getOrderCode());
		
		if(null != trip.getBorrowSellerId()){
			SellerBO borrowSeller = sellerService.findById(trip.getBorrowSellerId());
			model.addAttribute("borrowSeller",borrowSeller);
		}
		if(null != trip.getBorrowSysCode()){
//			CabinetBO cabinet = cabinetService.findBySysCode(trip.getBorrowSysCode());
//			SellerBO borrowSeller = sellerService.findById(cabinet.getSellerId());
//			model.addAttribute("borrowCabinet",cabinet);
//			model.addAttribute("borrowSeller",borrowSeller);
		}
		
		if(null != trip.getReturnSellerId()){
			SellerBO returnSeller = sellerService.findById(trip.getReturnSellerId());
			model.addAttribute("returnSeller",returnSeller);
		}
		if(null != trip.getReturnSysCode()){
//			CabinetBO cabinet = cabinetService.findBySysCode(trip.getReturnSysCode());
//			SellerBO returnSeller = sellerService.findById(cabinet.getSellerId());
//			model.addAttribute("returnCabinet",cabinet);
//			model.addAttribute("returnSeller",returnSeller);
		}
		
		MemberBO info = memberService.findByUid(trip.getUid());
		if(null == info){
			info = new MemberBO();
		}
		
		SysSetting sysSetting = sysSettingService.findOne();
		
		if(null != trip.getUserCouponId()){
			UserCouponBO coupon = userCouponService.findOne(trip.getUserCouponId());
			model.addAttribute("coupon",coupon);
		}
		
		List<FeedbackBO> feed_list = feedbackService.findByOrderId(id);
		
		model.addAttribute("sysSetting", sysSetting);
		model.addAttribute("info",info);
		model.addAttribute("trip",trip);
		model.addAttribute("feed_list",feed_list);
		
		return "modules/order/orderDetail";
	}
		
}
