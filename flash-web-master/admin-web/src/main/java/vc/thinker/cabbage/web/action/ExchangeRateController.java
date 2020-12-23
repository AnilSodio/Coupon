package vc.thinker.cabbage.web.action;

import java.math.BigDecimal;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import vc.thinker.cabbage.sys.bo.ExchangeRateBO;
import vc.thinker.cabbage.sys.model.ExchangeRate;
import vc.thinker.cabbage.sys.service.ExchangeRateService;
import vc.thinker.cabbage.sys.vo.ExchangeRateVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;

@Controller
@RequestMapping("${adminPath}/sys/exRate")
public class ExchangeRateController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private ExchangeRateService exchangeRateService;
	
	@RequiresPermissions("sys:exRate:list")
	@SecurityMapping(name="role.list",permGroup="role.exchange.rate",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("list")
	public String list(Model model,MyPage<ExchangeRateBO> page,ExchangeRateVO vo){
		
		exchangeRateService.findPageByVo(page,vo);
		
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		return "modules/sys/rate_list";
	}
	
	@RequiresPermissions("sys:exRate:modify")
	@SecurityMapping(name="role.edit",permGroup="role.exchange.rate",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("modify")
	public String modify(Model model,Long id){
		
		ExchangeRateBO info = exchangeRateService.findOne(id);
		
		model.addAttribute("info",info);
		
		return "modules/sys/rate_modify";
	}
	
	@RequiresPermissions("sys:exRate:modify")
	@SecurityMapping(name="role.edit",permGroup="role.exchange.rate",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("save")
	public String save(Long id,BigDecimal exchangeRate){
		
		ExchangeRate rate = new ExchangeRate();
		rate.setId(id);
		rate.setExchangeRate(exchangeRate);
		
		exchangeRateService.update(rate);
		
		return "redirect:" + adminPath +"/sys/exRate/list";
	}
	
}
