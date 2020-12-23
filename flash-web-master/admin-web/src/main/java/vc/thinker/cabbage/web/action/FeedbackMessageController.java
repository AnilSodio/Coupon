package vc.thinker.cabbage.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vc.thinker.cabbage.se.FeedbackMessageService;
import vc.thinker.cabbage.se.bo.FeedbackMessageBO;
import vc.thinker.cabbage.se.vo.FeedbackMessageVO;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.cabbage.common.MyPage;

@Controller
@RequestMapping("${adminPath}/sys/feedbackMessage")
public class FeedbackMessageController {

	private Logger logger = LoggerFactory.getLogger(FeedbackMessageController.class);
	
	@Autowired
	private FeedbackMessageService feedbackMessageService;
	
	@Value("${adminPath}")
	private String adminPath;
	
	@RequiresPermissions("sys:feedback:list")
	@SecurityMapping(name="role.list",permGroup="role.feedback.message",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping({"list"})
	public String list(MyPage<FeedbackMessageBO> page,FeedbackMessageVO vo,Model model){
		
		feedbackMessageService.findPageByVo(page,vo);
		
		feedbackMessageService.updateIsReaded(page);
		
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		
		return "modules/se/feedback_message";
	}
	
	@RequiresPermissions("sys:feedback:delete")
	@SecurityMapping(name="role.delete",permGroup="role.feedback.message",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping({"delete"})
	@ResponseBody
	public String  list(Long id){
		
		feedbackMessageService.delet(id);
		
		return "000";
	}
	
	@RequestMapping("checkIsNewMessage")
	@ResponseBody
	public Boolean checkIsNewMessage(HttpServletRequest request){
		return feedbackMessageService.countByUnRead()>0;
	}
}
