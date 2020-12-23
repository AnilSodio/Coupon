package vc.thinker.apiv2.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.sinco.common.utils.MD5Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import vc.thinker.apiv2.ResourceServerMain;
import vc.thinker.apiv2.bo.ImgBo;
import vc.thinker.apiv2.bo.InitImgBO;
import vc.thinker.apiv2.bo.SysSettingBO;
import vc.thinker.apiv2.http.response.SingleResponse;
import vc.thinker.apiv2.utils.MapperUtils;
import vc.thinker.apiv2.web.BaseController;
import vc.thinker.apiv2.web.Responses;
import vc.thinker.cabbage.sys.ResourceContants;
import vc.thinker.cabbage.sys.bo.ShareSetBO;
import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.cabbage.sys.service.CountryService;
import vc.thinker.cabbage.sys.service.InitImgService;
import vc.thinker.cabbage.sys.service.ShareService;
import vc.thinker.cabbage.sys.service.SysSettingService;
import vc.thinker.cabbage.user.CommonConstants;

@RestController
@RequestMapping(value = "/api/param", produces = { APPLICATION_JSON_VALUE })
@Api(value = "app参数相关操作类", description = "提供与app参数相关的api")
public class AppParamController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(AppParamController.class);
	
	@Autowired
	private Mapper mapper;
	
	@Autowired
	private InitImgService initImgService;
	
	@Autowired
	private SysSettingService sysSettingService; 
	
	@Autowired
	private ShareService shareService;
	
	@Autowired
	private CountryService countryService;
	
	@RequestMapping(value = "/query_app_img", method = { RequestMethod.GET })
	@ApiOperation(value = "查询app启动时图片",notes = "查询app启动时图片",
	authorizations=@Authorization(value = ResourceServerMain.securitySchemaOAuth2Cc))
	public ResponseEntity<SingleResponse<ImgBo>> queryInitImg() {
		SingleResponse<ImgBo> resp = new SingleResponse<ImgBo>();
		ImgBo res_bo = new ImgBo();
		/**
		 * 查询图片结果集
		 */
		List<vc.thinker.cabbage.sys.bo.InitImgBO> img_list = 
							initImgService.findNormalImg(CommonConstants.AD_TYPE_START_PAGE);
		String md5=MD5Util.getMD5String(JSON.toJSONString(img_list));
		
		res_bo.setImgList(MapperUtils.map(mapper, img_list, InitImgBO.class));
		res_bo.setMd5(md5);
		
		/**
		 * 查询图片展示时间
		 */
		SysSetting bo = sysSettingService.findOne();
		
		if(null != bo && null != bo.getImgTime()){
			res_bo.setTime(bo.getImgTime());
		}else {
			res_bo.setTime(5);
		}
		
		resp.setItem(res_bo);
		
		return Responses.ok(resp);
	}
	
	@RequestMapping(value = "/query_ad_img", method = { RequestMethod.GET })
	@ApiOperation(value = "首页广告查询",notes = "首页广告查询",
	authorizations=@Authorization(value = ResourceServerMain.securitySchemaOAuth2Cc))
	public ResponseEntity<SingleResponse<InitImgBO>> queryAdImg(){
		
		SingleResponse<InitImgBO> resp = new SingleResponse<InitImgBO>();
		
		List<vc.thinker.cabbage.sys.bo.InitImgBO> img_list = 
				initImgService.findNormalImg(CommonConstants.AD_TYPE_HOME_PAGE);
		
		if(null != img_list && img_list.size() > 0){
			resp.setItem(mapper.map(img_list.get(0), InitImgBO.class));
		}else {
			resp.setItem(new InitImgBO());
		}
		
		return Responses.ok(resp);
		
	}
	
	@RequestMapping(value = "/query_app_share", method = { RequestMethod.GET })
	@ApiOperation(value = "查询分享或邀请相关设置",notes = "查询分享或邀请相关设置",
	authorizations=@Authorization(value = ResourceServerMain.securitySchemaOAuth2Cc))
	@ApiResponses(value = { @ApiResponse(code = 400, message = "系统配置不存在") })
	public ResponseEntity<SingleResponse<ShareSetBO>> queryAppShare(){
		
		SingleResponse<ShareSetBO> resp = new SingleResponse<ShareSetBO>();
		
		ShareSetBO share = shareService.findShareSetInfo();
		if(null != share){
			resp.setItem(mapper.map(share, ShareSetBO.class));
			return Responses.ok(resp);
		}else {
			resp.setError("400");
			resp.setErrorDescription("配置不存在");
			resp.setSuccess(false);
			
			return Responses.ok(resp);
		}
		
	}

	@RequestMapping(value = "/query_sys_set", method = { RequestMethod.GET })
	@ApiOperation(value = "查询系统配置相关",notes = "查询系统配置相关（支付方式，联系我们电话，默认语言等）",
			authorizations=@Authorization(value = ResourceServerMain.securitySchemaOAuth2Cc))
	@ApiResponses(value = { @ApiResponse(code = 400, message = "系统配置不存在") })
	public ResponseEntity<SingleResponse<SysSettingBO>> querySysSet(
			HttpServletRequest request){
		
		logger.info("系统配置用户上送的 locale：[{}]",request.getLocale().getLanguage());
		
		SingleResponse<SysSettingBO> resp = new SingleResponse<SysSettingBO>();
		
		SysSetting sysSetting = sysSettingService.findOne();
		
		if(null != sysSetting) {
			SysSettingBO item = mapper.map(sysSetting, SysSettingBO.class);
			List<String> contactUs = sysSettingService.queryCallCenter();
			logger.info("contactUs:[{}]",contactUs.toString());
			item.setContactUs(contactUs);
			Date lastUpdate =  countryService.queryUpdateTimeByLanguage(
					ResourceContants.RESOURCE_PLAY_TYEP_APP, request.getLocale().getLanguage());
			item.setLanguageLastUpdateTime(lastUpdate);
			resp.setItem(item);
			return Responses.ok(resp);
		}else {
			//异常处理
			resp.setError("400");
			resp.setErrorDescription("系统配置不存在");
			resp.setSuccess(false);
			return Responses.ok(resp);
		}
	}
}
