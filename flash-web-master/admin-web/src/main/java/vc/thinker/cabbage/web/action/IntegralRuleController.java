package vc.thinker.cabbage.web.action;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vc.thinker.cabbage.user.bo.IntegralRuleBO;
import vc.thinker.cabbage.user.service.IntegralRuleService;
import vc.thinker.cabbage.user.vo.IntegralRuleVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.model.User;
import vc.thinker.web.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/sys/inteRule")
public class IntegralRuleController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private IntegralRuleService integralRuleService;
	
	@RequiresPermissions("sys:inteRule:list")
	@SecurityMapping(name="role.list",permGroup="role.integralRules",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping({"list"})
	public String list(Model model,IntegralRuleVO vo,MyPage<IntegralRuleBO> page){
		
		integralRuleService.findPageByVo(page, vo);
		
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		return "modules/cus/rule_list";
	}
	
	@RequiresPermissions("sys:inteRule:modify")
	@SecurityMapping(name="role.edit",permGroup="role.integralRules",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping({"modify"})
	public String modify(Long id,Model model){
		
		IntegralRuleBO info = new IntegralRuleBO();
		
		if(null != id){
			info = integralRuleService.findOne(id);
		}
		
		model.addAttribute("info",info);
		
		return "modules/cus/rule_modify";
	}
	

	@RequiresPermissions("sys:inteRule:modify")
	@SecurityMapping(name="role.edit",permGroup="role.integralRules",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping({"save"})
	public String save(IntegralRuleBO bo){
		
		User user = UserUtils.getUser();
		integralRuleService.saveOrUpdate(user.getId(),bo);
		return "redirect:" + adminPath +"/sys/inteRule/list";
	}
	
	@RequiresPermissions("sys:inteRule:delete")
	@SecurityMapping(name="role.delete",permGroup="role.integralRules",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping({"delete"})
	@ResponseBody
	public String delete(Long id){
		
		integralRuleService.delete(id);
		
		return "0000";
	}
	
	@RequestMapping("checkRuleCode")
	@ResponseBody
	public Boolean checkRuleCode(Long id,String ruleCode){
		
		Boolean result = integralRuleService.checkRuleCode(id,ruleCode);
		return result;
	}
	
}
