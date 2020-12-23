package vc.thinker.cabbage.web.action;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.sys.service.CountryService;
import vc.thinker.cabbage.user.bo.AgentBO;
import vc.thinker.cabbage.user.model.Agent;
import vc.thinker.cabbage.user.service.AgentService;
import vc.thinker.cabbage.user.vo.AgentVO;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;

@Controller
@RequestMapping("${adminPath}/sys/agent")
public class UserAgentController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private CountryService countryService;
	
	@RequiresPermissions("sys:agent:list")
	@SecurityMapping(name="role.list",permGroup="role.agent",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("list")
	public String list(Model model,MyPage<AgentBO> page,AgentVO vo){
		
		agentService.findPageByVo(page,vo);
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		model.addAttribute("countries",countryService.findAll());
		return "modules/cus/agent_list";
		
	}
	
	@RequiresPermissions("sys:agent:modify")
	@SecurityMapping(name="role.edit",permGroup="role.agent",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("modify")
	public String modify(Long uid,Model model){
		AgentBO info = new AgentBO();
		if(null != uid){
			info = agentService.findById(uid);
		}
		model.addAttribute("info",info);
		model.addAttribute("countries",countryService.findAll());
		return "modules/cus/agent_modify";
	}
	
	@RequiresPermissions("sys:agent:modify")
	@SecurityMapping(name="role.edit",permGroup="role.agent",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("save")
	public String save(Agent agent){
		
		agentService.adminSaveOrUpdateAgent(agent);
		
		return "redirect:" + adminPath +"/sys/agent/list";
	}
	
	@RequestMapping("checkAgentName")
	@ResponseBody
	public Boolean checkAgentName(Long uid,String agentName){
		return agentService.checkAgentName(uid,agentName);
	}
	
	@RequestMapping("checkMobile")
	@ResponseBody
	public Boolean checkMobile(Long uid,String mobile){
		return agentService.checkMobile(uid, mobile);
	}
	
	@RequestMapping("checkEmail")
	@ResponseBody
	public Boolean checkEmail(Long uid,String email){
		return agentService.checkEmail(uid, email);
	}
	
	@RequiresPermissions("sys:agent:modify")
	@SecurityMapping(name="role.edit",permGroup="role.agent",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("disAndEnable")
	@ResponseBody
	public String disAndEnable(Long uid,int status){
		
		Agent agent = new Agent();
		agent.setUid(uid);
		agent.setStatus(status);
		agentService.adminSaveOrUpdateAgent(agent);
		
		return "0000";
	}
}
