package vc.thinker.cabbage.web.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.se.CabinetChargeRuleService;
import vc.thinker.cabbage.se.bo.CabinetChargeRuleBO;
import vc.thinker.cabbage.se.vo.CabinetChargeRuleVO;
import vc.thinker.cabbage.sys.bo.CountryBO;
import vc.thinker.cabbage.sys.service.CountryService;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.model.User;
import vc.thinker.web.utils.UserUtils;

@Controller
@RequestMapping("${adminPath}/sys/rules")
public class ChargesRulesController {

	@Autowired
	private CabinetChargeRuleService cabinetChargeRuleService;
	
	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private CountryService countryService;
	
	@RequiresPermissions("sys:rules:chargeRules")
	@SecurityMapping(name="role.list",permGroup="role.rules",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("ruleList")
	public String ruleList(Model model,MyPage<CabinetChargeRuleBO> page, CabinetChargeRuleVO vo){
		List<CabinetChargeRuleBO> ruleList = cabinetChargeRuleService.findAll();
		model.addAttribute("ruleList",ruleList);
		model.addAttribute("vo",vo);
		
		return "modules/se/cabinet_rule_list";
	}
	
	@RequiresPermissions("sys:rules:ruleDelete")
	@SecurityMapping(name="role.delete",permGroup="role.rules",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("deleteRule")
	@ResponseBody
	public String deleteRule(Long id){
		cabinetChargeRuleService.delete(id);
		return "0000";
	}
	
	@RequiresPermissions("sys:rules:ruleAdd")
	@SecurityMapping(name="role.edit",permGroup="role.rules",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("ruleAdd")
	public String ruleAdd(Model model){
		model.addAttribute("currencies",countryService.findCurrencies());
		CabinetChargeRuleBO info = new CabinetChargeRuleBO();
		model.addAttribute("info",info);
		return "modules/se/cabinet_rule_modify";
	}
	
	@RequiresPermissions("sys:rules:ruleModify")
	@SecurityMapping(name="role.edit",permGroup="role.rules",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("ruleModify")
	public String ruleModify(Model model,Long id){
		model.addAttribute("currencies",countryService.findCurrencies());
		CabinetChargeRuleBO info = cabinetChargeRuleService.findOne(id);// modify
		model.addAttribute("info",info);
		return "modules/se/cabinet_rule_modify";
	}
	
	@RequiresPermissions("sys:rules:ruleModify")
	@SecurityMapping(name="role.edit",permGroup="role.rules",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("ruleSave")
	public String ruleSave(CabinetChargeRuleBO bo){
		User user = UserUtils.getUser();
		cabinetChargeRuleService.saveOrUpdate(user.getId(),bo);
		return "redirect:" + adminPath + "/sys/rules/ruleList?cabinetTypeId="+bo.getCabinetTypeId();
	}
}
