package vc.thinker.cabbage.web.action;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.se.CabinetTypeService;
import vc.thinker.cabbage.se.bo.CabinetTypeBO;
import vc.thinker.cabbage.se.vo.CabinetTypeVO;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.model.User;
import vc.thinker.web.utils.UserUtils;

/**
 * 充电柜类型
 * @author thinker
 *
 */

@Controller
@RequestMapping("${adminPath}/sys/cabinetType")
public class CabinetTypeController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private CabinetTypeService cabinetTypeService;
	
	@RequiresPermissions("sys:cabinetType:list")
	@SecurityMapping(name="role.list",permGroup="role.nomoBoxType",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("list")
	public  String list(Model model,CabinetTypeVO vo,MyPage<CabinetTypeBO> page){
		page.setContent(cabinetTypeService.findAll());
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		return "modules/se/cabinet_type_list";
	}
	
	@RequiresPermissions("sys:cabinetType:modify")
	@SecurityMapping(name="role.edit",permGroup="role.nomoBoxType",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("modify")
	public String modify(Model model,Long id){
		
		CabinetTypeBO info = new CabinetTypeBO();
		
		if(null != id){
			
			info = cabinetTypeService.findOne(id);
		}
		
		model.addAttribute("info",info);
		
		return "modules/se/cabinet_type_modify";
	}
	
	@RequiresPermissions("sys:cabinetType:modify")
	@SecurityMapping(name="role.edit",permGroup="role.nomoBoxType",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("save")
	public String save(CabinetTypeBO bo){
		
		User user = UserUtils.getUser();
		
		cabinetTypeService.adminSaveOrUpdate(user.getId(),bo);
		
		return "redirect:" + adminPath+"/sys/cabinetType/list";
	}
	
	@RequiresPermissions("sys:cabinetType:modify")
	@SecurityMapping(name="role.ableOrDisable",permGroup="role.nomoBoxType",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("disAndEnable")
	@ResponseBody
	public String disAndEnable(Long id,int status){
		
		CabinetTypeBO info = cabinetTypeService.findOne(id);
		if(null == info){
			return "数据不存在，请联系后台";
		}
		
		User user = UserUtils.getUser();
		
		CabinetTypeBO up_info = new CabinetTypeBO();
		up_info.setId(info.getId());
		up_info.setStatus(status);
		
		cabinetTypeService.adminSaveOrUpdate(user.getId(), up_info);
		
		return "0000";
	}
	
	@RequiresPermissions("sys:cabinetType:delete")
	@SecurityMapping(name="role.delete",permGroup="role.nomoBoxType",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("delete")
	@ResponseBody
	public String delete(Long id){
		
		cabinetTypeService.delete(id);
		
		return "0000";
	}
	
	@RequestMapping("checkTypeName")
	@ResponseBody
	public Boolean checkTypeName(Long id,String typeName){
		
		Boolean result = cabinetTypeService.checkTypeName(id,typeName);
		
		return result;
	}
	
}
