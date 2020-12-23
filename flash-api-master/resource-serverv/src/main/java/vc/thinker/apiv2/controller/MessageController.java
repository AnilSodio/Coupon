package vc.thinker.apiv2.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sinco.common.utils.DateUtils;
import com.sinco.data.core.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import vc.thinker.apiv2.bo.APIMessageBO;
import vc.thinker.apiv2.http.response.PageResponse;
import vc.thinker.apiv2.http.response.SingleResponse;
import vc.thinker.apiv2.utils.MapperUtils;
import vc.thinker.apiv2.web.BaseController;
import vc.thinker.apiv2.web.Responses;
import vc.thinker.apiv2.web.UserAuthentication;
import vc.thinker.cabbage.sys.bo.ImageTextBO;
import vc.thinker.cabbage.sys.bo.SysMessageBO;
import vc.thinker.cabbage.sys.service.SysMessageService;
import vc.thinker.cabbage.sys.vo.SysMessageVO;
import vc.thinker.oauth.AuthContants;



@RestController
@RequestMapping(value = "/api/message", produces = { APPLICATION_JSON_VALUE})
@Api(value = "消息获取类")
public class MessageController extends BaseController{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SysMessageService messageService;
	@Autowired
	private Mapper mapper;

	@PreAuthorize("hasAnyRole('"+AuthContants.ROLE_END_USER+"')")
	@RequestMapping(value = "/sys_list", method = { RequestMethod.GET })
	@ApiOperation(value = "拉取系统消息", notes = "拉取系统消息.基于 sendTime 倒序查询")
	public ResponseEntity<PageResponse<APIMessageBO>> sysList(
			@UserAuthentication User user,
			@ApiParam(value="userType,2:个人用户,3:维保",required=true) @RequestParam(value="userType",required=false) String userType,
			@ApiParam(value="ltTime",required=false) @RequestParam(value="ltTime",required=false) Long ltTime){
		Long uid=Long.parseLong(user.getUsername());
		PageResponse<APIMessageBO> resp = new PageResponse<APIMessageBO>();
		
		resp.setSuccess(true);
		
		Page<SysMessageBO> page=new Page<>();
		page.setPageNumber(1);
		page.setPageSize(10);
		
		SysMessageVO vo = new SysMessageVO();
		vo.setToUserType(userType);
		vo.setToUserId(uid);
		//查看最近30天的
		Calendar day=Calendar.getInstance();
		vo.setEndDate(DateUtils.formatDate(day.getTime(), "yyyy-MM-dd"));
		day.add(Calendar.DAY_OF_MONTH, -30);
		vo.setBeginDate(DateUtils.formatDate(day.getTime(), "yyyy-MM-dd"));
		if (ltTime != null) {
			vo.setLtTime(new Date(ltTime));
		}
		List<SysMessageBO> list = messageService.findPageByToUser(page, vo);
		resp.init(page, MapperUtils.map(mapper, list, APIMessageBO.class));
		
		return Responses.ok(resp);
	}
	
	@PreAuthorize("hasAnyRole('"+AuthContants.ROLE_END_USER+"')")
	@RequestMapping(value = "/home_message", method = { RequestMethod.GET })
	@ApiOperation(value = "拉取首页广告消息", notes = "拉取首页广告消息")
	public ResponseEntity<SingleResponse<APIMessageBO>> findHomeMessage(
			@UserAuthentication User user,
			@ApiParam(value="时间戳",required=false) @RequestParam(value="timestamp",required=false) Long timestamp){
		Long uid=Long.parseLong(user.getUsername());
		SingleResponse<APIMessageBO> resp = new SingleResponse<APIMessageBO>();
		
		Date date=null;
		if(timestamp != null){
			date=new Date(timestamp);
		}
		
		SysMessageBO message = messageService.findHomeMessage(uid, date);
		if(message != null){
			resp.setItem(mapper.map(message, APIMessageBO.class));
		}
		
		return Responses.ok(resp);
	}
	
	@PreAuthorize("hasAnyRole('"+AuthContants.ROLE_END_USER+"')")
	@RequestMapping(value = "/sys_view/{imageTextId}", method = { RequestMethod.GET },produces = {"text/html; charset=UTF-8"})
	@ApiOperation(hidden=true,value="系统消息详情")
	public ModelAndView sysView(@PathVariable("imageTextId") Long imageTextId,Model model){
		ImageTextBO sm=messageService.findImageTextById(imageTextId);
		if(sm != null){
			sm.setContent(StringEscapeUtils.unescapeHtml4(sm.getContent()));
		}
		model.addAttribute("sm", sm);
		return new ModelAndView("sysMessageView");
	}
}
