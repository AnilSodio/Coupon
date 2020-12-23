package vc.thinker.cabbage.web.action;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vc.thinker.cabbage.sys.bo.ShareSetBO;
import vc.thinker.cabbage.sys.service.ShareService;
import vc.thinker.cabbage.sys.vo.ShareSetVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;

@Controller
@RequestMapping("${adminPath}/sys/share")
public class ShareController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private ShareService shareService;
	
	@RequiresPermissions("sys:share:modify")
	@SecurityMapping(name = "role.edit", permGroup = "role.share", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("modify")
	public String modify(MyPage<ShareSetBO> page,ShareSetVO vo,Model model) {
		
		ShareSetBO info = shareService.findShareSetInfo();
		if(null == info){
			info = new ShareSetBO();
		}
		
		model.addAttribute("info",info);
		
		return "modules/set/shareList";
	}
	
	@RequiresPermissions("sys:share:modify")
	@SecurityMapping(name = "role.edit", permGroup = "role.share", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("save")
	public String save(ShareSetBO bo){
		
		shareService.saveOrUpdate(bo);
		
		return "redirect:"+adminPath+"/sys/share/list";
	}
	
}
