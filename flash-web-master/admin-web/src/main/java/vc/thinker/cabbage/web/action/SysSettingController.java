package vc.thinker.cabbage.web.action;

import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinco.common.security.PasswordUtil;
import com.sinco.common.utils.StringUtils;

import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.cabbage.sys.service.CountryService;
import vc.thinker.cabbage.sys.service.SysSettingService;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.bo.UserAccountBO;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.dao.UserAccountDao;
import vc.thinker.sys.model.UserAccount;

/**
 * 系统配置相关操作类
 * @author thinkerzgx
 *
 */
@Controller
@RequestMapping("${adminPath}/sys/warning")
public class SysSettingController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private SysSettingService sysSettingService;
	
	@Autowired
	private UserAccountDao userAccountDao;
	
	@Autowired
	private CountryService countryService;
	
	@RequiresPermissions("sys:warning:modify")
	@SecurityMapping(name="role.edit",permGroup="role.sysSet",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("modify")
	public String modify(SysSetting bo,Model model) {
		
		SysSetting info = sysSettingService.findOne();
		
		StringBuffer businessModel = new StringBuffer();
		
		model.addAttribute("businessModel",businessModel.toString());
		model.addAttribute("info",info);
		return "modules/set/setModify";
	}
	
	@RequiresPermissions("sys:warning:modify")
	@SecurityMapping(name="role.edit",permGroup="role.sysSet",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("save")
	public String save(SysSetting bo,Model model) {
		
		sysSettingService.saveOrUpdate(bo);
		
		return "redirect:"+ adminPath +"/sys/warning/modify";
	}
	
	
	@RequestMapping("modify_admin")
	public String modify_admin(SysSetting bo,Model model) {
		
		SysSetting info = sysSettingService.findOne();
		StringBuffer businessModel = new StringBuffer();
		model.addAttribute("businessModel",businessModel.toString());
		model.addAttribute("info",info);
		Set<String> currencies = countryService.findCurrencies();
		model.addAttribute("currencies",currencies);
		
		return "modules/set/setModify_admin";
	}
	
	@RequestMapping("save_admin")
	public String save_admin(SysSetting bo,Model model) {
		
		sysSettingService.saveOrUpdate(bo);
		
		return "redirect:"+ adminPath +"/sys/warning/modify_admin";
	}
	
	@RequestMapping("cipherEncryption")
	@ResponseBody
	public String cipherEncryption(){
		
		List<UserAccountBO> findAll = userAccountDao.findAll();
		if(null != findAll){
			findAll.forEach(e->{
				if( e.getUid().intValue() != 1 && !StringUtils.isEmpty(e.getPassword()) && e.getPassword().length()<=20){
					
					if(null != e.getPassword()){
						UserAccount up_bo = new UserAccount();
						up_bo.setId(e.getId());
						up_bo.setPassword(PasswordUtil.entryptPassword(e.getPassword()));
						userAccountDao.update(up_bo);
					}
				}
			});
		}
		return "处理成功";
	}
}
