package vc.thinker.cabbage.web.action;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinco.common.utils.StringUtils;

import vc.thinker.cabbage.sys.bo.CountryBO;
import vc.thinker.cabbage.sys.bo.UserGuideBO;
import vc.thinker.cabbage.sys.service.CountryService;
import vc.thinker.cabbage.sys.service.UserGuideService;
import vc.thinker.cabbage.sys.vo.UserGuideVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.utils.CommUtil;

@Controller
@RequestMapping(value = "${adminPath}/sys/guide")
public class UserGuideController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private UserGuideService userGuideService;
	
	@Autowired
	private  CountryService countryService;
	
	@RequiresPermissions("sys:guide:view")
	@SecurityMapping(name="role.list",permGroup="role.userGuide",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping({"list"})
	public String list(@ModelAttribute("page") MyPage<UserGuideBO> page,Model model,UserGuideVO vo) {
		
		if(StringUtils.isEmpty(vo.getLanguage())){
			vo.setLanguage("en");
		}
		
		userGuideService.finePageByVo(page,vo);
		
		List<CountryBO> lan_list = countryService.groupByLanguage();
		
		model.addAttribute("lan_list",lan_list);
		model.addAttribute("vo",vo);
		model.addAttribute("page",page);
		
		return "modules/sys/guideList";
	}
	
	@RequiresPermissions("sys:guide:modify")
	@SecurityMapping(name="role.edit",permGroup="role.userGuide",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping({"modify"})
	public String modify(Model model,Long id) {
		
		if(null != id ) {
			//修改
			UserGuideBO info = userGuideService.findOne(id);
			model.addAttribute("info",info);
		}else {
			//新增
			UserGuideBO info = new UserGuideBO();
			model.addAttribute("info",info);
		}
		
		List<CountryBO> lan_list = countryService.groupByLanguage();
		
		model.addAttribute("lan_list",lan_list);
		return "modules/sys/guideModify";
	}
	
	@RequiresPermissions("sys:guide:modify")
	@SecurityMapping(name="role.edit",permGroup="role.userGuide",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping({"save"})
	public String save(Model model,Long id,UserGuideBO bo) {
		
		userGuideService.saveOrUpdate(bo);
		
		return "redirect:"+adminPath+"/sys/guide/list";
	}
	
	@RequiresPermissions("sys:guide:delete")
	@SecurityMapping(name="role.delete",permGroup="role.userGuide",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping({"delete"})
	@ResponseBody
	public String delete(Model model,Long id) {
		userGuideService.delete(id);
		return "success";
	}
	
	@ResponseBody
    @RequestMapping({"ajax"})
    public void ajax(Long id,String sort){
		  UserGuideBO bo = userGuideService.findOne(id);
    	if(CommUtil.isNotNull(sort)){
    		try {
				bo.setSort(Integer.parseInt(sort));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
    	}
    	userGuideService.saveOrUpdate(bo);
    }
}
