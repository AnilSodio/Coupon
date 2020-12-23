package vc.thinker.cabbage.web.action;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vc.thinker.cabbage.sys.bo.InitImgBO;
import vc.thinker.cabbage.sys.service.InitImgService;
import vc.thinker.cabbage.sys.vo.InitImgVO;
import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;

@Controller
@RequestMapping(value = "${adminPath}/sys/initImg")
public class InitImgController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private InitImgService initImgService;
	
	@RequiresPermissions("sys:initImg:list")
	@SecurityMapping(name = "role.list", permGroup = "role.ad.start", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("list")
	public String list(Model model,MyPage<InitImgBO> page,InitImgVO vo){
		
		List<InitImgBO> img_list = initImgService.findPageByVo(page,vo);
		
		Boolean is_open_add = true;
		
		Integer imgSize = 3;
		if(CommonConstants.AD_TYPE_HOME_PAGE == vo.getImgType()){
			imgSize = 1;
		}
		
		if(img_list.size() >= imgSize){
			is_open_add = false;
		}
		
		model.addAttribute("is_open_add",is_open_add);
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		return "modules/set/initImgList";
	}
	
	@RequiresPermissions("sys:initImg:modify")
	@SecurityMapping(name = "role.edit", permGroup = "role.ad.start", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("modify")
	public String modify(Model model,Long id,Integer imgType){
		
		InitImgBO info = new InitImgBO();
		if(null != id){
			info = initImgService.findOne(id);
		}
		
		model.addAttribute("info",info);
		model.addAttribute("imgType",imgType);
		
		if(imgType == CommonConstants.AD_TYPE_START_PAGE){
			return "modules/set/initImgAdd";
		}else {
			return "modules/set/homeImgAdd";
		}
	}
	
	
	@RequiresPermissions("sys:initImg:modify")
	@SecurityMapping(name = "role.edit", permGroup = "role.ad.start", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("save")
	public String save(InitImgBO bo){
		
		initImgService.saveOrUpdate(bo);
		
		return "redirect:"+adminPath+"/sys/initImg/list?imgType="+bo.getImgType();
	}
	
	
	@RequiresPermissions("sys:initImg:delete")
	@SecurityMapping(name = "role.delete", permGroup = "role.ad.start", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("delete")
	public String delete(long id){
		
		InitImgBO info = initImgService.findOne(id);
		
		Integer imgType = info.getImgType();
		
		initImgService.delete(id);
		
		return "redirect:"+adminPath+"/sys/initImg/list?imgType="+imgType;
	}
}
