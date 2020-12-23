/**
f * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.cabbage.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import vc.thinker.cabbage.se.CabinetService;
import vc.thinker.cabbage.se.CabinetTypeService;
import vc.thinker.cabbage.se.bo.CabinetBO;
import vc.thinker.cabbage.se.bo.CabinetTypeBO;
import vc.thinker.cabbage.se.vo.CabinetVO;
import vc.thinker.cabbage.user.bo.SellerBO;
import vc.thinker.cabbage.user.model.Seller;
import vc.thinker.cabbage.user.service.SellerService;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.web.security.AgentPrincipal;
import vc.thinker.core.web.BaseController;
import vc.thinker.sys.model.User;
import vc.thinker.web.utils.UserUtils;

/**
 * 充电宝Controller
 * @author lnz
 */
@Controller
@RequestMapping(value = "${adminPath}/cabinet")
public class CabinetController extends BaseController {

	
	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private CabinetService cabinetService;
	
	@Autowired
	private CabinetTypeService cabinetTypeService;
	
	@Autowired
	private SellerService sellerService;
	
	@RequestMapping("list")
	public String list(CabinetVO vo, HttpServletRequest request, HttpServletResponse response, Model model) {
		AgentPrincipal user = (AgentPrincipal)UserUtils.getPrincipal();
		vo.setAgentId(user.getAgent().getUid());
		MyPage<CabinetBO> page = new MyPage<CabinetBO>(request, response);
		cabinetService.findPageByVo(page,vo);
		 List<CabinetTypeBO> typeList = cabinetTypeService.findAll();
        model.addAttribute("page", page);
        model.addAttribute("vo", vo);
        model.addAttribute("typeList", typeList);
		return "modules/cabinet/cabinetList";
	}
	
	@RequestMapping("detail")
	public String detail(Long id,Model model){
		CabinetBO info = new CabinetBO();
		
		if(null != id){
			info = cabinetService.findDetailsOne(id);
		}
		
		model.addAttribute("info",info);
		
		return "modules/cabinet/cabinetDetail";
	}
	
	@RequestMapping("cabinetDelivery")
	public String cabinetDelivery(Long id,Model model){
		
		CabinetBO info = cabinetService.findOne(id);
		
		List<SellerBO> seller_list = sellerService.findAll();
		
		model.addAttribute("info",info);
		model.addAttribute("seller_list",seller_list);
		
		return "modules/cabinet/cabinet_delivery";
	}
	
	@RequestMapping("cabinetDeliverySave")
	public String cabinetDeliverySave(Long id,Seller seller){
		
		User user = UserUtils.getUser();
		cabinetService.cabinetDelivery(user.getId(),id,seller);
		
		return "redirect:" + adminPath + "/cabinet/list";
	}
}
