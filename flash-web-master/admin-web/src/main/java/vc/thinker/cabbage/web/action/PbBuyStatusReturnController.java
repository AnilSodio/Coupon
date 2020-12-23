package vc.thinker.cabbage.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vc.thinker.biz.exception.ServiceException;
import vc.thinker.cabbage.se.PbBuyStatusReturnService;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.core.web.BaseController;

@Controller
@RequestMapping("${adminPath}/sys/pbreturn")
public class PbBuyStatusReturnController extends BaseController{

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private PbBuyStatusReturnService returnService;
	
	@RequestMapping("skipPage")
	public String list(Model model){
		
		MemberBO info = new MemberBO();
		
		model.addAttribute("info",info);
		
		return "modules/test/mobile";
	}
	
	@RequestMapping("returnStats")
	@ResponseBody
	public String returnStats(String mobile,RedirectAttributes redirectAttributes){
		
		try{
			returnService.returnStatus(mobile);
		}catch (ServiceException e) {
			return e.getMessage();
		}
		
		return "0000";
	}
}
