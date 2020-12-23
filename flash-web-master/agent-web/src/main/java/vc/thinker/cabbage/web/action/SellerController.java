package vc.thinker.cabbage.web.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.sinco.common.utils.IPUtil;

import cn.jiguang.common.utils.StringUtils;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.sys.service.CountryService;
import vc.thinker.cabbage.user.bo.SellerBO;
import vc.thinker.cabbage.user.model.Seller;
import vc.thinker.cabbage.user.service.SellerService;
import vc.thinker.cabbage.user.vo.SellerVO;
import vc.thinker.cabbage.web.security.AgentPrincipal;
import vc.thinker.web.utils.UserUtils;

@Controller
@RequestMapping("${adminPath}/seller")
public class SellerController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private SellerService sellerService;
	
	@Value("${google.js.key}")
	private String googleJsKye;
	
	@Autowired
	private CountryService countryService;
	
	@RequestMapping("list")
	public String list(Model model,MyPage<SellerBO> page,SellerVO vo){
		AgentPrincipal user = (AgentPrincipal)UserUtils.getPrincipal();
		vo.setRefereeUid(user.getAgent().getUid());
		sellerService.findPageByVo(page, vo);
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		return "modules/seller/sellerList";
	}
	
	@RequestMapping("add")
	public String add(Model model,Long cid){
		model.addAttribute("info",new SellerBO());
		model.addAttribute("cid",cid);
		model.addAttribute("countries",countryService.findAll());
		return "modules/seller/addSeller";
	}
	
	@RequestMapping("selectLonAndLat")
	public String selectLonAndLat(Model model) {
		model.addAttribute("googleJsKye",googleJsKye);
		return "modules/selectLatAndLng";
	}
	
	@RequestMapping("save")
	public String save(Seller seller,Long cid,HttpServletRequest request,String sellerCover1,String sellerCover2,String sellerCover3,String sellerCover4,String sellerCover5){
		
		JSONArray array = new JSONArray();
		
		if(!StringUtils.isEmpty(sellerCover1)){
			array.add(sellerCover1);
		}
		if(!StringUtils.isEmpty(sellerCover2)){
			array.add(sellerCover2);
		}
		if(!StringUtils.isEmpty(sellerCover3)){
			array.add(sellerCover3);
		}
		if(!StringUtils.isEmpty(sellerCover4)){
			array.add(sellerCover4);
		}
		if(!StringUtils.isEmpty(sellerCover5)){
			array.add(sellerCover5);
		}
		
		seller.setSellerCover(array.toString());
		String ip=IPUtil.getIpAddr(request);
		
		sellerService.saveOrUpdate(seller,ip);
		
		return "redirect:" + adminPath + "/cabinet/cabinetDelivery?id="+cid;
	}
	
	@RequestMapping("checkSellerName")
	@ResponseBody
	public Boolean checkSellerName(Long uid,String sellerName){
		
		return sellerService.checkSellerName(uid,sellerName);
	}
	
	@RequestMapping("checkContactMobile")
	@ResponseBody
	public Boolean checkContactMobile(Long uid,String contactMobile){
		
		return sellerService.checkContactMobile(uid,contactMobile);
	}
	
	@RequestMapping("checkEmail")
	@ResponseBody
	public Boolean checkEmail(Long uid,String email){
		
		return sellerService.checkEmail(uid,email);
	}
	
	@RequestMapping("findOne")
	@ResponseBody
	public SellerBO findOne(Long sellerId){
		SellerBO seller = sellerService.findOne(sellerId);
		return seller;
	}
	
}
