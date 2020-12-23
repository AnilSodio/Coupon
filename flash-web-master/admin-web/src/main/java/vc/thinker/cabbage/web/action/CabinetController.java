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

import vc.thinker.biz.exception.ServiceException;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.common.QRCodeUtil;
import vc.thinker.cabbage.se.CabinetChargeRuleService;
import vc.thinker.cabbage.se.CabinetService;
import vc.thinker.cabbage.se.CabinetStatusService;
import vc.thinker.cabbage.se.CabinetTypeService;
import vc.thinker.cabbage.se.PortableBatteryService;
import vc.thinker.cabbage.se.SysCodeService;
import vc.thinker.cabbage.se.bo.CabinetBO;
import vc.thinker.cabbage.se.bo.CabinetChargeRuleBO;
import vc.thinker.cabbage.se.bo.CabinetTypeBO;
import vc.thinker.cabbage.se.bo.PortableBatteryBO;
import vc.thinker.cabbage.se.bo.SysCodeBO;
import vc.thinker.cabbage.se.model.CabinetStatus;
import vc.thinker.cabbage.se.model.CabinetStatus.ChannelStatus;
import vc.thinker.cabbage.se.vo.CabinetVO;
import vc.thinker.cabbage.user.bo.AgentBO;
import vc.thinker.cabbage.user.bo.SellerBO;
import vc.thinker.cabbage.user.model.Seller;
import vc.thinker.cabbage.user.service.AgentService;
import vc.thinker.cabbage.user.service.SellerService;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.core.web.BaseController;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.model.User;
import vc.thinker.web.utils.UserUtils;

@Controller
@RequestMapping("${adminPath}/sys/cabinet")
public class CabinetController extends BaseController{

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private CabinetService cabinetService;
	
	@Autowired
	private CabinetChargeRuleService cabinetChargeRuleService;
	
	@Autowired
	private CabinetTypeService cabinetTypeService;
	
	@Autowired
	private SysCodeService sysCodeService;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private PortableBatteryService portableBatteryService;
	
	@Value("${applet_redirect_url}")
	private String applet_redirect_url;
	
	@Autowired
	private CabinetStatusService cabinetStatusService;
	
	@Autowired
	private AgentService agentService;
	
	@RequiresPermissions("sys:cabinet:list")
	@SecurityMapping(name="role.list",permGroup="role.nomoBox",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("list")
	public String list(Model model,MyPage<CabinetBO> page,CabinetVO vo){
		List<CabinetTypeBO> typeList = cabinetTypeService.findAll();
		cabinetService.findPageByVo(page, vo);
		model.addAttribute("typeList",typeList);
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		return "modules/se/cabinet_list";
	}
	
	
	@RequiresPermissions("sys:cabinet:modify")
	@SecurityMapping(name="role.edit",permGroup="role.nomoBox",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("modify")
	public String modify(Model model,Long id){
		CabinetBO info = new CabinetBO();
		// query sys code
		if(null != id){
			info = cabinetService.findDetailsOne(id);
		} else {
			List<SysCodeBO> code_list = sysCodeService.findAllNoBinding();
			model.addAttribute("code_list",code_list);
		}
		model.addAttribute("info",info);
		
		// query agents
		List<AgentBO> agent_list = agentService.findAll();
		model.addAttribute("agent_list",agent_list);
		
		// query cabinet types
		List<CabinetTypeBO> typeList = cabinetTypeService.findAll();
		model.addAttribute("typeList",typeList);
		
		// query cabinet charge rules
		List<CabinetChargeRuleBO> ruleList = cabinetChargeRuleService.findAll();
		model.addAttribute("ruleList",ruleList);
		
		return "modules/se/cabinet_modify";
	}
	
	@RequiresPermissions("sys:cabinet:modify")
	@SecurityMapping(name="role.edit",permGroup="role.nomoBox",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("save")
	public String save(CabinetBO bo,RedirectAttributes redirectAttributes){
	
		User user = UserUtils.getUser();
		try{
			cabinetService.adminSaveOrUpdate(user.getId(),bo);
		}catch (ServiceException e) {
			addMessage(redirectAttributes,e.getMessage());
		}
		
		return "redirect:" + adminPath + "/sys/cabinet/list";
	}
	
	@RequestMapping("checkCabinetCode")
	@ResponseBody
	public Boolean checkCabinetCode(Long id,String cabinetCode){
		Boolean result = cabinetService.checkCabinetCode(id,cabinetCode);
		return result;
	}
	
	@RequestMapping("checkCabinetAlias")
	@ResponseBody
	public Boolean checkCabinetAlias(Long id,String cabinetAlias){
		Boolean result = cabinetService.checkCabinetAlias(id,cabinetAlias);
		return result;
	}
	
	@RequiresPermissions("sys:cabinet:modify")
	@SecurityMapping(name="role.edit",permGroup="role.nomoBox",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("disAndEnable")
	@ResponseBody
	public String disAndEnable(Long id,int status){
		cabinetService.disAndEnable(id,status);
		return "0000";
	}
	
	@RequiresPermissions("sys:cabinet:delete")
	@SecurityMapping(name="role.delete",permGroup="role.nomoBox",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("delete")
	@ResponseBody
	public String delete(Long  id){
		cabinetService.delete(id);
		return "0000";
	}
	
	@RequiresPermissions("sys:cabinet:modify")
	@SecurityMapping(name="role.edit",permGroup="role.nomoBox",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("cabinetDelivery")
	public String cabinetDelivery(Long id,Model model){
		
		CabinetBO info = cabinetService.findOne(id);
		
		List<SellerBO> seller_list = sellerService.findAll();
		
		model.addAttribute("info",info);
		model.addAttribute("seller_list",seller_list);
		
		return "modules/se/cabinet_delivery";
	}
	
	@RequiresPermissions("sys:cabinet:modify")
	@SecurityMapping(name="role.edit",permGroup="role.nomoBox",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("cabinetDeliverySave")
	public String cabinetDeliverySave(Long id,Seller seller){
		
		User user = UserUtils.getUser();
		cabinetService.cabinetDelivery(user.getId(),id,seller);
		
		return "redirect:" + adminPath + "/sys/cabinet/list";
	}
	
	
	@RequiresPermissions("sys:cabinet:print")
	@SecurityMapping(name="role.print",permGroup="role.nomoBox",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("cabinetPrint")
	public String cabinetPrint(Model model,Long id){
		
		CabinetBO info = cabinetService.findOne(id);
		CabinetTypeBO infoType = cabinetTypeService.findOne(info.getTypeId());
		info.setTypeName(infoType.getTypeName());
		
		//生成二维码
		String twoCode = QRCodeUtil.generateToBase64(applet_redirect_url + info.getSysCode(), 200, 200);
		
		model.addAttribute("twoCode",twoCode);
		model.addAttribute("info",info);
		
		return "modules/se/code_print";
	}
	
	@RequiresPermissions("sys:cabinet:detail")
	@SecurityMapping(name="role.detail",permGroup="role.nomoBox",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("detail")
	public String detail(Long id,Model model,String type){
		
		//查询基本信息
		CabinetBO info = cabinetService.findDetailsOne(id);
		
		//从 mongo 信息中查询卡槽信息
		CabinetStatus cabinetStatus = 
				cabinetStatusService.findBySysCode(info.getSysCode());
		
		if(null != cabinetStatus){
			//卡槽信息
			List<ChannelStatus> channelList = 
					cabinetStatus.getChannelStatusList();
			
			if(null != channelList && channelList.size()>0){
				//查询此充电柜下的充电宝
				List<PortableBatteryBO> pb_list = 
						portableBatteryService.findInCabinetByCabinetId(id);
				
				if(null != pb_list && pb_list.size() > 0){
					
					pb_list.forEach(e->{
						
						if(null != e.getCabinetChannel()){
							channelList.get(e.getCabinetChannel()-1)
							.setPbCode(e.getPortableBatteryCode());
						}
					});
				}
				
				model.addAttribute("channelList",channelList);
			}
		}
		
		model.addAttribute("info",info);
		model.addAttribute("type",type);
		
		return "modules/se/cabinet_detail";
	}
}
