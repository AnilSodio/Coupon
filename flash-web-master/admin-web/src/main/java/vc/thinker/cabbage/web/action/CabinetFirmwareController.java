package vc.thinker.cabbage.web.action;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URLConnection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sinco.data.core.Page;

import net.weedfs.client.RequestResult;
import net.weedfs.client.WeedFSClient;
import vc.thinker.cabbage.se.CabinetFirmwareService;
import vc.thinker.cabbage.se.bo.CabinetFirmwareBO;
import vc.thinker.cabbage.se.bo.FirmwareModuleBO;
import vc.thinker.cabbage.se.model.CabinetFirmware;
import vc.thinker.cabbage.se.vo.CabinetFirmwareVO;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.core.web.BaseController;
import vc.thinker.sys.contants.SysUserContant;

@Controller
@RequestMapping("${adminPath}/sys/firmware")
public class CabinetFirmwareController extends BaseController{

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private CabinetFirmwareService cabinetFirmwareService;
	
	@Autowired
	private WeedFSClient fileClient;
	
	@RequiresPermissions("sys:firmware:list")
	@SecurityMapping(name="list",permGroup="firmware",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("list") 
	public String list(Page<CabinetFirmwareBO> page,CabinetFirmwareVO vo,Model model){
		
		
		cabinetFirmwareService.findPageByVo(page,vo);
		
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		return "modules/se/firmware_list";
	}
	
	
	@RequiresPermissions("sys:firmware:modify")
	@SecurityMapping(name="modify",permGroup="firmware",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("modify") 
	public String modify(Model model,Long id){
		
		CabinetFirmwareBO info = new CabinetFirmwareBO();
		if(null != id){
			info = cabinetFirmwareService.findOne(id);
		}
		
		List<FirmwareModuleBO> moudle_list = cabinetFirmwareService.findAllModule();
		
		model.addAttribute("moudle_list",moudle_list);
		model.addAttribute("info",info);
		
		return "modules/se/firmware_modify";
	}
	
	
	@RequiresPermissions("sys:firmware:modify")
	@SecurityMapping(name="save",permGroup="firmware",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("save") 
	public String save(
				CabinetFirmware firmware,
				@RequestParam("file") 
				MultipartFile file, HttpServletRequest request,
				RedirectAttributes redirectAttributes){
		
		if (!file.isEmpty()) {
			String url = fileUpload(file);
			if("error".equals(url)){
				addMessage(redirectAttributes, "Server connection exception");
				return "redirect:" + adminPath +"/sys/firmware/list";
			}
			firmware.setFirmwareUrl(url);
		}
		
		cabinetFirmwareService.saveOrUpdate(firmware);
		
		return "redirect:" + adminPath +"/sys/firmware/list";
	}
	
	
	public String fileUpload(MultipartFile file) {

		try {
			RequestResult result = fileClient.upload(file.getInputStream(), file.getName(),
					URLConnection.guessContentTypeFromName((file.getOriginalFilename())));
			return result.getUrl();
		}catch (ConnectException e) {
			return "error";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequiresPermissions("sys:firmware:delete")
	@SecurityMapping(name="delete",permGroup="firmware",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("delete") 
	@ResponseBody
	public String delete(Long id){
		
		CabinetFirmwareBO info = cabinetFirmwareService.findOne(id);
		
		if(null == info){
			return "data error";
		}
		
		cabinetFirmwareService.delete(id);
		
		return "0000";
	}
}
