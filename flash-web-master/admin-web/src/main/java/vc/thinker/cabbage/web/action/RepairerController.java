package vc.thinker.cabbage.web.action;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.sys.service.CountryService;
import vc.thinker.cabbage.user.bo.AdminBO;
import vc.thinker.cabbage.user.bo.RepairerBO;
import vc.thinker.cabbage.user.model.Repairer;
import vc.thinker.cabbage.user.service.RepairerService;
import vc.thinker.cabbage.user.vo.RepairerVO;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.core.web.BaseController;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.model.User;
import vc.thinker.sys.service.OfficeService;
import vc.thinker.sys.service.SystemService;
import vc.thinker.web.utils.UserUtils;

/**
 * 巡检员管理控制器
 * @author zgx
 *
 */
@Controller
@RequestMapping("${adminPath}/sys/repairer")
public class RepairerController extends BaseController{
	
	@Autowired
	private RepairerService repairerService;
	
	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private CountryService countryService;
	
	@RequiresPermissions("sys:repairer:view")
	@SecurityMapping(name="role.list",permGroup="role.maintenance",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("list")
	public String list(@ModelAttribute("page") MyPage<RepairerBO> page,Model model,RepairerVO vo) {
		
		repairerService.findPageByVo(page, vo);
		
		model.addAttribute("vo",vo);
		model.addAttribute("page",page);
		model.addAttribute("countries",countryService.findAll());
		return "modules/se/repairerList";
	}
	
	@RequiresPermissions("sys:repairer:modify")
	@SecurityMapping(name="role.edit",permGroup="role.maintenance",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("modify")
	public String modify(RepairerBO bo,Model model){
		
		User currentUser = UserUtils.getUser();
		AdminBO user = new AdminBO();
		
		RepairerBO info = null;
		
		if(null != bo.getUid()){
			//修改
			info = repairerService.fndByUid(bo.getUid());
			user.setUid(info.getUid());
			user.setAreaId(info.getAreaId());
			user.setName(info.getName());
			user.setMobile(info.getMobile());
			
			user.setCompany(officeService.get(info.getCompanyId()));
			user.setOffice(officeService.get(info.getOfficeId()));
			
		}else {
			//新增
			info = new RepairerBO();
			user.setCompany(officeService.get(currentUser.getCompanyId()));
			user.setOffice(officeService.get(currentUser.getOfficeId()));
			
		}
		
		user.setAreaId(info.getAreaId());
		model.addAttribute("countries",countryService.findAll());
		model.addAttribute("info",info);
		model.addAttribute("officeList", officeService.findAll(currentUser));
		model.addAttribute("user", user);
		model.addAttribute("allRoles",systemService.findRoleList(currentUser.getId()));
		
		return "modules/se/repairerMod";
	}
	
	@RequiresPermissions("sys:repairer:modify")
	@SecurityMapping(name="role.edit",permGroup="role.maintenance",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("save")
	public String save(Repairer repairer,Long officeId,Long companyId,String password) {
		
		repairerService.adminCreateOrUpdateRepairer(UserUtils.getUser(),repairer,officeId,companyId,password);
		
		return "redirect:"+ adminPath + "/sys/repairer/list";
	}
	
	@RequestMapping("checkMobile")
	@ResponseBody
	public Boolean checkMobile(Long uid,String mobile){
		
		Boolean result = repairerService.checkMobile(uid,mobile);
		
		return result;
	}
	
	@RequestMapping("checkName")
	@ResponseBody
	public Boolean checkName(Long uid,String name){
		
		Boolean result = repairerService.checkName(uid,name);
		
		return result;
	}
	
	@RequiresPermissions("sys:repairer:modify")
	@SecurityMapping(name="role.edit",permGroup="role.maintenance",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("startOrStop")
	@ResponseBody
	public String startOrStop(Long uid,int status) {
	
		repairerService.startOrStop(uid,status);
		
		return "0000";
	}
	
	
	@RequiresPermissions("sys:repairer:detail")
	@SecurityMapping(name="role.detail",permGroup="role.maintenance",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("detail")
	public String detail( Model model,Long uid){
		RepairerBO info = repairerService.findDetailInfo(uid);
		//查询公司及机构信息
		model.addAttribute("info",info);
		model.addAttribute("countries",countryService.findAll());
		return "modules/se/repairerDetail";
	}
	
}
