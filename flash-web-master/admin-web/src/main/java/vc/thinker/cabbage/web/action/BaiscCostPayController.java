//package vc.thinker.cabbage.web.action;
//
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import vc.thinker.cabbage.se.UserBasicCostPayLogService;
//import vc.thinker.cabbage.user.bo.UserBasicCostPayLogBO;
//import vc.thinker.cabbage.user.vo.UserBasicCostPayLogVO;
//import vc.thinker.cabbage.common.MyPage;
//import vc.thinker.core.security.SecurityMapping;
//import vc.thinker.sys.contants.SysUserContant;
//
//@Controller
//@RequestMapping("${adminPath}/sys/basicCost")
//public class BaiscCostPayController {
//
//	@Value("${adminPath}")
//	private String adminPath;
//	
//	@Autowired
//	private UserBasicCostPayLogService userBasicCostPayLogService;
//	
//	
//	@RequiresPermissions("sys:basicCost:list")
//	@SecurityMapping(name="list",permGroup="Basic cost",userType=SysUserContant.USER_TYPE_1)
//	@RequestMapping("list")
//	public String list(Model model,MyPage<UserBasicCostPayLogBO> page,UserBasicCostPayLogVO vo){
//		
//		userBasicCostPayLogService.findPageByVo(page, vo);
//		
//		model.addAttribute("page",page);
//		model.addAttribute("vo",vo);
//		
//		return "modules/money/basicCostPay";
//	}
//}
