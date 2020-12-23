package vc.thinker.apiv2.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import vc.thinker.apiv2.bo.APIOrderBO;
import vc.thinker.cabbage.se.OrderService;
import vc.thinker.cabbage.se.bo.OrderBO;
import vc.thinker.cabbage.sys.dao.SysSettingDao;
import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.cabbage.sys.service.UserGuideService;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.service.MemberService;

@RestController
@RequestMapping(value="/share",produces = { APPLICATION_JSON_VALUE })
public class ShareController {
	
	private static Logger log = LoggerFactory.getLogger(ShareController.class);

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private Mapper mapper;
	
	@Autowired
	private MemberService memberService;
	
	@Value("${lbs.baidu.ak}")
	private String baiduAK;
	
	@Autowired
	private UserGuideService userGuideService;
	
	@Autowired
	private SysSettingDao sysSettingDao;
	
	/**
	 * 雨伞订单分享
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/um_order_share",method = RequestMethod.GET,produces = {"text/html; charset=UTF-8"})
	public ModelAndView orderShare(@RequestParam String orderCode,Model model) {
			
		OrderBO re_bo = orderService.findDetailed(orderCode);
		if(re_bo == null){
			return null;
		}
		APIOrderBO item = mapper.map(re_bo, APIOrderBO.class);
		
		int rideTime = item.getRideTime();
		
		model.addAttribute("order",item);
		
		MemberBO mb = memberService.findByUid(re_bo.getUid());
		String nickName = mb.getNickname();
		if(!StringUtils.isEmpty(nickName) && nickName.length() >= 11){
			nickName = nickName.substring(0, 3)+"****"+nickName.substring(7);
		}
		
		mb.setNickname(nickName);
		model.addAttribute("member",mb);
		model.addAttribute("ak",baiduAK);
		model.addAttribute("rideTime",rideTime);
		
		SysSetting settingBO = sysSettingDao.findOne();
		model.addAttribute("settingBO",settingBO);
		
		return new ModelAndView("orderShare");
	}
	
	/**
	 * 邀请好友分享
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/invite_share",method = RequestMethod.GET,produces = {"text/html; charset=UTF-8"})
	public ModelAndView invite_share(Model model,String inviteCode){
		
		/**
		 * 查询邀请好友金额
		 */
		log.info("客户端上送 inviteCode：" + inviteCode);
		model.addAttribute("inviteCode",inviteCode);
		
		SysSetting settingBO = sysSettingDao.findOne();
		model.addAttribute("settingBO",settingBO);
		return new ModelAndView("inviteShare");
	}
	
	/**
	 * 服务条例查询
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/use_car_rules",method = RequestMethod.GET,produces = {"text/html; charset=UTF-8"})
	public ModelAndView use_car_rules(HttpServletRequest request,Model model,
			@RequestHeader(name="Accept-Language",required=true) String language){
//		Locale locale= RequestContextUtils.getLocale(request);
		List<vc.thinker.cabbage.sys.bo.UserGuideBO> bo = userGuideService.findListByType(4,language);
		
		if(!bo.isEmpty()){
//			handleLanguage(bo, locale);
			model.addAttribute("sm",bo.get(0));
		}else {
			model.addAttribute("sm", new vc.thinker.cabbage.sys.bo.UserGuideBO() );
		}
		
		return new ModelAndView("guide");
	}
	
	/**
	 * 充值协议
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/balance_pay_rules", method = { RequestMethod.GET },produces = {"text/html; charset=UTF-8"})
	public ModelAndView balancePayRules(HttpServletRequest request, Model model ,
			@RequestHeader(name="Accept-Language",required=true) String language){
		Locale locale= RequestContextUtils.getLocale(request);
		List<vc.thinker.cabbage.sys.bo.UserGuideBO> bo = userGuideService.findListByType(10,language);
		if(!bo.isEmpty()){
//			handleLanguage(bo, locale);
			model.addAttribute("sm",bo.get(0));
		}else {
			model.addAttribute("sm",new vc.thinker.cabbage.sys.bo.UserGuideBO());
		}
		return new ModelAndView("guide");
	}
	
	/**
	 * 积分规则
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/integra_rules", method = { RequestMethod.GET },produces = {"text/html; charset=UTF-8"})
	public ModelAndView integraRules(HttpServletRequest request, Model model ,
			@RequestHeader(name="Accept-Language",required=true) String language){
//		Locale locale= RequestContextUtils.getLocale(request);
		List<vc.thinker.cabbage.sys.bo.UserGuideBO> bo = userGuideService.findListByType(5,language);
		if(!bo.isEmpty()){
//			handleLanguage(bo, locale);
			model.addAttribute("sm",bo.get(0));
		}
		return new ModelAndView("guide");
	}
	
	/**
	 * 处理语言
	 * @param userGuide
	 * @param locale
	 */
//	private void handleLanguage(vc.thinker.cabbage.sys.bo.UserGuideBO userGuide,Locale locale){
//		if("en".equals(locale.getLanguage())){
//			userGuide.setTitle(userGuide.getEnglishTitle());
//			userGuide.setContent(userGuide.getEnglishContent());
//		}
//	}
//	
//	private void handleLanguage(List<vc.thinker.cabbage.sys.bo.UserGuideBO> list,Locale locale){
//		list.forEach(e->handleLanguage(e, locale));
//	}
}
