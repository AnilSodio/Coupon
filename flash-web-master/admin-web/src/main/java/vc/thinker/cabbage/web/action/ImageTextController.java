package vc.thinker.cabbage.web.action;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vc.thinker.cabbage.sys.SysMessageConstants;
import vc.thinker.cabbage.sys.bo.ImageTextBO;
import vc.thinker.cabbage.sys.service.ImageTextService;
import vc.thinker.cabbage.sys.vo.ImageTextVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.core.web.BaseController;
import vc.thinker.sys.contants.SysUserContant;

@Controller
@RequestMapping(value = "${adminPath}/sys/imageText")
public class ImageTextController extends BaseController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private ImageTextService imageTextService;
	
	@RequiresPermissions("sys:imageText:view")
	@SecurityMapping(name="role.list",permGroup="role.graphic",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping({"list"})
	public String list(Model model,MyPage<ImageTextBO> page,ImageTextVO vo){
		
		imageTextService.findPageByVo(page,vo);
		
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		if(null != vo.getImageType() && SysMessageConstants.IMAGE_TEXT_TYPE_1 == vo.getImageType()){
			return "modules/sysMessage/imageTextList";
		}else {
			return "modules/sysMessage/imageHomeList";
		}
	}
	
	@RequiresPermissions("sys:imageText:modify")
	@SecurityMapping(name="role.edit",permGroup="role.graphic",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping({"modify"})
	public String modify(Model model,Long id,Integer imageType) {
		
		ImageTextBO info ;
		if(null != id){
			info = imageTextService.findOne(id);
		}else {
			info = new ImageTextBO();
		}
		
		model.addAttribute("info",info);
		
		if(null != imageType && imageType == SysMessageConstants.IMAGE_TEXT_TYPE_1){
			return "modules/sysMessage/imageTextModify";
		}
		
		return "modules/sysMessage/imageAsModify";
	}
	
	@RequiresPermissions("sys:imageText:modify")
	@SecurityMapping(name="role.edit",permGroup="role.graphic",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping({"save"})
	public String save(ImageTextBO bo,RedirectAttributes redirectAttributes){
		
		if(null == bo.getId()){
			//新增
			if(null != bo.getStatus() && bo.getStatus() == SysMessageConstants.IMATE_TEXT_STATUS_2){
				List<ImageTextBO> img_list = 
						imageTextService.findbyStatusAndType(SysMessageConstants.IMATE_TEXT_STATUS_2,
								SysMessageConstants.IMAGE_TEXT_TYPE_2);
				if(null != img_list && img_list.size() > 0){
					
					imageTextService.createAdImageText(bo);
					
					addMessage(redirectAttributes, "由于已经存在一个已生效的广告，所以新增广告状态默认为为未生效");
					return "redirect:" + adminPath +"/sys/imageText/list?imageType="+bo.getImageType()	;
				}
			}
			
		}else {
			// 修改
			List<ImageTextBO> img_list = 
					imageTextService.findbyStatusAndType(SysMessageConstants.IMATE_TEXT_STATUS_2,
							SysMessageConstants.IMAGE_TEXT_TYPE_2);
			if(null != img_list && img_list.size() -1 >0){
				addMessage(redirectAttributes, "已经存在一个已生效的广告");
				return "redirect:" + adminPath +"/sys/imageText/list?imageType="+bo.getImageType()	;
			}
		}
		
		imageTextService.saveOrUpdate(bo);
		
		return "redirect:" + adminPath +"/sys/imageText/list?imageType="+bo.getImageType();
	}
	
	@RequestMapping({"save_image"})
	public String save_image(ImageTextBO bo,RedirectAttributes redirectAttributes){
		
		bo.setStatus(SysMessageConstants.IMATE_TEXT_STATUS_2);
		imageTextService.saveOrUpdate(bo);
		
		return "redirect:" + adminPath +"/sys/imageText/list?imageType="+bo.getImageType();
	}
	
	
	
	@RequiresPermissions("sys:imageText:delete")
	@SecurityMapping(name="role.delete",permGroup="role.graphic",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping({"delete"})
	@ResponseBody
	public String delete(Long id){
	
		ImageTextBO info = imageTextService.findOne(id);
		
		if(null != info){
			imageTextService.delete(id);
			return "1";
		}else {
			return "无效的参数";
		}
	}
	
	@RequiresPermissions("sys:imageText:modify")
	@SecurityMapping(name="role.edit",permGroup="role.graphic",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping({"stopOrStart"})
	@ResponseBody
	public String stopOrStart(Long id,Integer status){
		
		if(status == SysMessageConstants.IMATE_TEXT_STATUS_2){
			List<ImageTextBO> img_list = 
					imageTextService.findbyStatusAndType(SysMessageConstants.IMATE_TEXT_STATUS_2,
							SysMessageConstants.IMAGE_TEXT_TYPE_2);
			if(null != img_list && img_list.size()>0){
				return "There is already an effective advertisement";
			}
		}
		imageTextService.stopOrStart(id,status);
		
		return "1";
	}
}
