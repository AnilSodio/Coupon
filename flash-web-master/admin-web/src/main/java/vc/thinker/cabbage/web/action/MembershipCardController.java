package vc.thinker.cabbage.web.action;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vc.thinker.cabbage.money.service.MembershipCardService;
import vc.thinker.cabbage.sys.service.CountryService;
import vc.thinker.cabbage.user.bo.MembershipCardBO;
import vc.thinker.cabbage.user.vo.MembershipCardVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.model.User;
import vc.thinker.web.utils.UserUtils;

/**
 * 会员卡相关操作类
 * @author thinker
 */

@Controller
@RequestMapping("${adminPath}/sys/card")
public class MembershipCardController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private MembershipCardService memberCardService;
	
	@Autowired
	private CountryService countryService;
	
	
	@RequiresPermissions("sys:card:list")
	@SecurityMapping(name="role.list",permGroup="role.card",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("list")
	public String list(Model model,MyPage<MembershipCardBO> page,MembershipCardVO vo){
		
		memberCardService.findPageByVo(page,vo);
		
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		return "modules/vipPay/card_list";
	}
	
	@RequiresPermissions("sys:card:modify")
	@SecurityMapping(name="role.edit",permGroup="role.card",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("modify")
	public String modify(Model model,Long id){
		
		MembershipCardBO info;
		
		if(null != id){
			info = memberCardService.findOne(id);
		}else {
			info = new MembershipCardBO();
		}
		model.addAttribute("currencies",countryService.findCurrencies());
		model.addAttribute("info",info);
		
		return "modules/vipPay/card_mod";
	}
	
	@RequiresPermissions("sys:card:modify")
	@SecurityMapping(name="role.edit",permGroup="role.card",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("save")
	public String save(Model model, MembershipCardBO bo){
		User user = UserUtils.getUser();
		memberCardService.saveOrUpdate(user.getId(),bo);
		
		return "redirect:"+ adminPath +"/sys/card/list";
	}
	
	@RequiresPermissions("sys:card:delete")
	@SecurityMapping(name="role.delete",permGroup="role.card",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("delete")
	@ResponseBody
	public String delete(Long id){
		memberCardService.delete(id);
		return "true";
	}
	
	@RequestMapping("checkCardName")
	@ResponseBody
	public Boolean checkCardName(Long id,String cardName){
		
		Boolean result = memberCardService.checkCardName(id,cardName);
		return result;
	}
}
