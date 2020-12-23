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
import vc.thinker.cabbage.se.CabinetStatusService;
import vc.thinker.cabbage.se.CabinetTypeService;
import vc.thinker.cabbage.se.PortableBatteryService;
import vc.thinker.cabbage.se.bo.CabinetBO;
import vc.thinker.cabbage.se.bo.CabinetTypeBO;
import vc.thinker.cabbage.se.vo.CabinetVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.web.security.RefereePrincipal;
import vc.thinker.core.web.BaseController;
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
	
	@RequestMapping("list")
	public String list(CabinetVO vo, HttpServletRequest request, HttpServletResponse response, Model model) {
		RefereePrincipal user = (RefereePrincipal)UserUtils.getPrincipal();
		vo.setSellerId(user.getReferee().getUid());
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

}
