package vc.thinker.cabbage.web.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinco.common.utils.StringUtils;

import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.money.service.MoneyService;
import vc.thinker.cabbage.money.service.UserRebateMoneyLogServcie;
import vc.thinker.cabbage.sys.service.CountryService;
import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.bo.UserMoneyBO;
import vc.thinker.cabbage.user.bo.UserRebateMoneyLogBO;
import vc.thinker.cabbage.user.vo.UserRebateMoneyLogVO;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;

@Controller
@RequestMapping("${adminPath}/sys/rebateMoney")
public class RebateMoneyLogController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private MoneyService moneyService;
	
	@Autowired
	private UserRebateMoneyLogServcie userRebateMoneyLogServcie;
	
	
	@RequiresPermissions("sys:repairer:view")
	@SecurityMapping(name="role.list",permGroup="role.rebateMoney",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("list")
	public String list(Model model,MyPage<UserRebateMoneyLogBO> page,UserRebateMoneyLogVO vo){
		
		userRebateMoneyLogServcie.findPageByVo(page, vo);
		
		List<UserMoneyBO> rebateGold = moneyService.sumRebateMoney();
		
		Map<String, BigDecimal> rebateMap = new HashMap<String, BigDecimal>();
		
		
		if(null != rebateGold && rebateGold.size()>0){
			 
			for (UserMoneyBO e : rebateGold) {
				if(!StringUtils.isEmpty(e.getCurrency())){
					rebateMap.put(e.getCurrency(), e.getRebateMoney());
				}
			}
		}
		
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		model.addAttribute("rebateMap",rebateMap);
		
		return "modules/money/rebate_money_log";
	}
}
