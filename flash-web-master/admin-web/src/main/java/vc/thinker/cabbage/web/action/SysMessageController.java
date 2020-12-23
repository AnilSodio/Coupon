package vc.thinker.cabbage.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vc.thinker.cabbage.sys.bo.ImageTextBO;
import vc.thinker.cabbage.sys.bo.SysMessageBO;
import vc.thinker.cabbage.sys.bo.SysMessageLogBO;
import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.cabbage.sys.service.ImageTextService;
import vc.thinker.cabbage.sys.service.SysMessageLogService;
import vc.thinker.cabbage.sys.service.SysMessageService;
import vc.thinker.cabbage.sys.service.SysSettingService;
import vc.thinker.cabbage.sys.vo.SysMessageLogVO;
import vc.thinker.cabbage.sys.vo.SysMessageVO;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.service.MemberService;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.model.User;
import vc.thinker.web.utils.UserUtils;

/**
 * 系统消息控制器
 * @author thinker
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysMessage")
public class SysMessageController {
	
//	private Logger logger = LoggerFactory.getLogger(SysMessageController.class);
	
	@Autowired
	private SysMessageService sysMessageService;
	
	@Autowired
	private SysMessageLogService sysLogService;
	
	@Autowired
	private ImageTextService imageTextService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private SysSettingService sysSettingService;
	
	@Value("${adminPath}")
	private String adminPath;
	
	@RequiresPermissions("sys:sysMessage:view")
	@SecurityMapping(name="role.list",permGroup="role.sysMess",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping({"list"})
	public String list(Model model,MyPage<SysMessageBO> page,SysMessageVO vo){
		
		sysMessageService.findPageByVO(page, vo);
		
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		if(null != vo.getLogIdIsNull() && vo.getLogIdIsNull()){
			//从首页消息中心进入
			return "modules/sysMessage/myOrderMessageList";
		}else if(null != vo.getLogIdIsNull()){
			return "modules/sysMessage/myImageMessageList";
		}
		return "modules/sysMessage/messageList";
	}
	
	@RequiresPermissions("sys:sysMessage:view")
	@SecurityMapping(name="role.list",permGroup="role.sysMess",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping({"slist"})
	public String slist(Model model,SysMessageLogVO vo,MyPage<SysMessageLogBO> page) {
		
		sysLogService.findPageByVo(page,vo);
		
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		return "modules/sysMessage/sysMessageList";
	}
	
	@RequiresPermissions("sys:sysMessage:send")
	@SecurityMapping(name="role.send",permGroup="role.sysMess",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping({"addSysMessage"})
	public String addSysMessage(Model model) {
		
		SysSetting info = sysSettingService.findOne();
		
		if(null != info.getIsOpenMemberCard() && info.getIsOpenMemberCard()){
			//查询所有购买vip的会员
			List<MemberBO> vip_list = memberService.findAllVipOrNotVip(true);
			model.addAttribute("vip_list",vip_list);
			
			//查询所有没有购买 vip的会员
			List<MemberBO> noVip_list = memberService.findAllVipOrNotVip(false);
			model.addAttribute("noVip_list",noVip_list);
		}
		
		List<MemberBO> all_list = memberService.findAllOnlyUid();
		model.addAttribute("all_list",all_list);
		
		model.addAttribute("imageText", new ImageTextBO());
		model.addAttribute("isOpVip",info.getIsOpenMemberCard());
		
		return "modules/sysMessage/addSysMessage";
	}
	
	@RequestMapping("selectImageText")
	public String selectImageText(Model model){
		
		//查询所有的图文消息
		List<ImageTextBO> img_list = imageTextService.findAllEffective();
		model.addAttribute("img_list",img_list);
		return "modules/sysMessage/selectTemplate";
	}
	
	@RequiresPermissions("sys:sysMessage:send")
	@SecurityMapping(name="role.send",permGroup="role.sysMess",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping({"sendSysMessage"})
	public String sendSysMessage(SysMessageLogBO bo){
		
		User user = UserUtils.getUser();
		bo.setFromUserId(user.getId());
		bo.setSendTime(new Date());
		bo.setIsPlatform(true);
		
		if(StringUtils.isEmpty(bo.getToUserIds())){
			bo.setIsSendAll(true);
		}else {
			bo.setIsSendAll(false);
		}
		
		sysLogService.sendSysMessage(bo);
		
		return "redirect:"+adminPath+"/sys/sysMessage/slist";
	}
	
	@RequestMapping("showPeople")
	public String showPeople(Model model,Long id) {
		
		SysMessageLogBO info = sysLogService.findOne(id);
		
		List<MemberBO> info_list = new ArrayList<>();
		
		if(null != info && null != info.getSendType()){
			
			List<Long> uidList = new ArrayList<Long>();
			
			String[] uids = info.getToUserIds().split(",");
			for (String s : uids) {
				uidList.add(Long.parseLong(s));
			}
			
			info_list = memberService.findByUids(uidList);
			
		}
		
		model.addAttribute("info_list",info_list);
		
		return "modules/sysMessage/showPeople";
	}
}
