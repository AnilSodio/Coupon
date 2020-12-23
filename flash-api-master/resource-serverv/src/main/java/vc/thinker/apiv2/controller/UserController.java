package vc.thinker.apiv2.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.security.Principal;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import vc.thinker.apiv2.http.response.ListResponse;
import vc.thinker.apiv2.http.response.SingleResponse;
import vc.thinker.apiv2.utils.MapperUtils;
import vc.thinker.apiv2.web.BaseController;
import vc.thinker.apiv2.web.Responses;
import vc.thinker.apiv2.web.UserAuthentication;
import vc.thinker.cabbage.sys.bo.UserGuideBO;
import vc.thinker.cabbage.sys.service.UserGuideService;
import vc.thinker.oauth.AuthContants;
import vc.thinker.sys.service.UserAccountService;


/**
 * 为用户公共操作类
 * @author james
 *
 */
@RestController
@RequestMapping(value = "/api", produces = { APPLICATION_JSON_VALUE })
@Api(value = "用户操作类", description = "提供与用户相关的api")
public class UserController extends BaseController{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected UserAccountService userAccountService;
	
	@Autowired
	private Mapper mapper;
	
	@Autowired
	protected TokenStore tokenStore;
	
	@Autowired
	private UserGuideService userGuideService;
	
	@RequestMapping(value = "/logout", method = { RequestMethod.GET })
	@PreAuthorize("hasAnyRole('"+AuthContants.ROLE_END_USER+"')")
	@ApiOperation(value = "用户注销", notes = "注销用户.<br/> 用户级别")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "注销失败"),
			@ApiResponse(code = 200, message = "注销成功") })
	public ResponseEntity<SingleResponse<Boolean>> logout(
				@UserAuthentication User user,
				OAuth2Authentication auth,Principal principal) {
		
		SingleResponse<Boolean> resp = new SingleResponse<Boolean>();
		
		if(principal instanceof OAuth2Authentication){
			OAuth2AccessToken token = tokenStore
					.getAccessToken((OAuth2Authentication) principal);
			tokenStore.removeAccessToken(token);
			
		}
	
		return Responses.ok(resp);
	}
	
	@RequestMapping(value = "/guide_list", method = { RequestMethod.GET })
	@PreAuthorize("hasAnyRole('"+AuthContants.ROLE_END_USER+"')")
	@ApiOperation(value = "文章列表")
	public ResponseEntity<ListResponse<UserGuideBO>> userGuideList(
			HttpServletRequest request,
			@ApiParam(value="文章类型，1.用户指南 2.设置3.维保端设置",required=true) @RequestParam(value="type") Integer type
			,@UserAuthentication User user,
			@RequestHeader(name="Accept-Language",required=true) String language){
		ListResponse<UserGuideBO> resp=new ListResponse<>();
//		Locale locale= RequestContextUtils.getLocale(request);
		List<vc.thinker.cabbage.sys.bo.UserGuideBO> list=userGuideService.findListByType(type,language);
//		handleLanguage(list, locale);
		resp.setItems(MapperUtils.map(mapper, list, UserGuideBO.class));
		return Responses.ok(resp);
	}
	
	@RequestMapping(value = "/guide_detail", method = { RequestMethod.GET })
	@PreAuthorize("hasAnyRole('"+AuthContants.ROLE_END_USER+"')")
	@ApiOperation(value = "文章详情(微信小程序使用)")
	public ResponseEntity<SingleResponse<UserGuideBO>> userGuideDetail(
			HttpServletRequest request,
			@ApiParam(value="文章ID",required=true) @RequestParam(value="id") Long id
			,@UserAuthentication User user){
		SingleResponse<UserGuideBO> resp = new SingleResponse<>();
//		Locale locale= RequestContextUtils.getLocale(request);
		vc.thinker.cabbage.sys.bo.UserGuideBO bo = userGuideService.findOne(id);
//		handleLanguage(bo, locale);
		UserGuideBO item= new UserGuideBO();
		item.setId(bo.getId());
		item.setTitle(bo.getTitle());
		item.setContent(bo.getContent());
		resp.setItem(item);
		return Responses.ok(resp);
	}
	
	@RequestMapping(value = "/guide_detail_app", method = { RequestMethod.GET })
	@PreAuthorize("hasRole('"+AuthContants.ROLE_END_USER+"')")
	@ApiOperation( hidden=true, value = "用户指南和设置-详情（app客户端使用）")
	public ModelAndView userGuideDetailApp(
			HttpServletRequest request,
			@ApiParam(value="文章ID",required=true) @RequestParam(value="id") Long id
			,@UserAuthentication User user,Model model){
		Locale locale= RequestContextUtils.getLocale(request);
		vc.thinker.cabbage.sys.bo.UserGuideBO bo = userGuideService.findOne(id);
//		handleLanguage(bo, locale);
		model.addAttribute("sm",bo);
		
		return new ModelAndView("guide");
	}
	
	/**
	 * 处理语言
	 * @param userGuide
	 * @param locale
	 */
//	private void handleLanguage(vc.thinker.cabbage.sys.bo.UserGuideBO userGuide,Locale locale){
//		if("en".equals(locale.getLanguage())){
//			userGuide.setTitle(userGuide.getEnglishTitle());
//			userGuide.setContent(userGuide.getEnglishContent());
//		}
//	}
	
//	private void handleLanguage(List<vc.thinker.cabbage.sys.bo.UserGuideBO> list,Locale locale){
//		list.forEach(e->handleLanguage(e, locale));
//	}
}
