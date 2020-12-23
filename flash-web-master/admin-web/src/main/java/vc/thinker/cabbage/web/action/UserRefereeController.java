package vc.thinker.cabbage.web.action;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vc.thinker.cabbage.user.bo.RefereeBO;
import vc.thinker.cabbage.user.model.Referee;
import vc.thinker.cabbage.user.service.RefereeService;
import vc.thinker.cabbage.user.vo.RefereeVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.sys.bo.CountryBO;
import vc.thinker.cabbage.sys.service.CountryService;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;

@Controller
@RequestMapping("${adminPath}/sys/referee")
public class UserRefereeController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private RefereeService refereeService;
	
	@Autowired
	private CountryService countryService;
	
	@RequiresPermissions("sys:referee:list")
	@SecurityMapping(name="role.list",permGroup="role.introducer",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("list")
	public String list(Model model,MyPage<RefereeBO> page,RefereeVO vo){
		refereeService.findPageByVo(page,vo);
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		model.addAttribute("countries",countryService.findAll());
		return "modules/cus/referee_list";
	}
	
	@RequiresPermissions("sys:referee:modify")
	@SecurityMapping(name="role.edit",permGroup="role.introducer",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("modify")
	public String modify(Long uid,Model model){
		
		RefereeBO info = new RefereeBO();
		
		if(null != uid){
			info = refereeService.findById(uid);
		}
		
		model.addAttribute("info",info);
		
		List<CountryBO> countries = countryService.findAll();
		model.addAttribute("countries",countries);
		
		return "modules/cus/referee_modify";
	}
	
	@RequiresPermissions("sys:referee:modify")
	@SecurityMapping(name="role.edit",permGroup="role.introducer",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("save")
	public String save(Referee referee){
		
		refereeService.adminSaveOrUpdateReferee(referee);
		
		return "redirect:"+adminPath+"/sys/referee/list";
	}
	
	@RequiresPermissions("sys:referee:modify")
	@SecurityMapping(name="role.edit",permGroup="role.introducer",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("disAndEnable")
	@ResponseBody
	public String disAndEnable(Long uid,int status){
		
		Referee referee = new Referee();
		referee.setUid(uid);
		referee.setStatus(status);
		refereeService.updateReferee(referee);
		
		return "0000";
	}
	
	@RequestMapping("checkRefereeName")
	@ResponseBody
	public Boolean checkRefereeName(Long uid,String refereeName){
		return refereeService.checkRefereeName(uid,refereeName);
	}
	
	@RequestMapping("checkMobile")
	@ResponseBody
	public Boolean checkMobile(Long uid,String mobile){
		return refereeService.checkMobile(uid, mobile);
	}
	
	@RequestMapping("checkEmail")
	@ResponseBody
	public Boolean checkEmail(Long uid,String email){
		return refereeService.checkEmail(uid, email);
	}
}
