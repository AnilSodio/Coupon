package vc.thinker.cabbage.web.action;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vc.thinker.cabbage.se.OrderPbBuyService;
import vc.thinker.cabbage.se.bo.OrderPbBuyBO;
import vc.thinker.cabbage.se.vo.OrderPbBuyVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;

@Controller
@RequestMapping("${adminPath}/sys/pbBuy")
public class PbBuyController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private OrderPbBuyService orderPbBuyService;
	
	@RequiresPermissions("sys:pbBuy:list")
	@SecurityMapping(name = "role.list", permGroup = "role.pbBuy", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "list" })
	public String typeList(Model model,MyPage<OrderPbBuyBO> page,OrderPbBuyVO vo){
		
		orderPbBuyService.findPageByVo(page, vo);
		
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		return "modules/se/pb_buy_list";
	}
}
