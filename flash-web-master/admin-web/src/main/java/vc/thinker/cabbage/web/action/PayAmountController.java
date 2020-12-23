package vc.thinker.cabbage.web.action;

import java.math.BigDecimal;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vc.thinker.cabbage.money.service.PayAmountService;
import vc.thinker.cabbage.sys.service.CountryService;
import vc.thinker.cabbage.user.bo.PayAmountBO;
import vc.thinker.cabbage.user.vo.PayAmountVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.model.User;
import vc.thinker.web.utils.UserUtils;

@Controller
@RequestMapping("${adminPath}/sys/payAmount")
public class PayAmountController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private PayAmountService payAmountService;
	
	@Autowired
	private CountryService countryService;
	
	@RequiresPermissions("sys:payAmount:list")
	@SecurityMapping(name="role.list",permGroup="role.balance",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("list")
	public String list(MyPage<PayAmountBO> page,PayAmountVO vo,Model model){
		
		payAmountService.findPageByVo(page,vo);
		
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		return "modules/vipPay/pay_amount_list";
	}
	
	@RequiresPermissions("sys:payAmount:modify")
	@SecurityMapping(name="role.edit",permGroup="role.balance",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("modify")
	public String modify(Long id,Model model){
		
		PayAmountBO info = null;
		if(null != id){
			info = payAmountService.findOne(id);
		}else {
			info = new PayAmountBO();
		}
		
		model.addAttribute("info",info);
		model.addAttribute("currencies",countryService.findCurrencies());
		return "modules/vipPay/pay_amount_mod";
	}
	
	@RequiresPermissions("sys:payAmount:modify")
	@SecurityMapping(name="role.edit",permGroup="role.balance",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("save")
	public String save(PayAmountBO bo){
		
		User user = UserUtils.getUser();
		
		payAmountService.saveOrUpdate(user,bo);
		
		return "redirect:" + adminPath + "/sys/payAmount/list";
	}
	
	
	@RequiresPermissions("sys:payAmount:delete")
	@SecurityMapping(name="role.delete",permGroup="role.balance",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("delete")
	@ResponseBody
	public String delete(Long id){
		
		PayAmountBO info = payAmountService.findOne(id);
		if(info == null){
			return "数据有误，请联系后台";
		}
		
		payAmountService.delete(id);
		
		return "true";
		
	}
	
	@RequestMapping("checkPayAmount")
	@ResponseBody
	public Boolean checkPayAmount(Long id,BigDecimal payAmount){
		
		Boolean result = payAmountService.checkPayAmount(id,payAmount);
		
		return result;
	}
	
}

