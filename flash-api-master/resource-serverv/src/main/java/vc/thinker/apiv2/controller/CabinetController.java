package vc.thinker.apiv2.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContextUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import vc.thinker.apiv2.bo.APICabinetBO;
import vc.thinker.apiv2.bo.APISellerBO;
import vc.thinker.apiv2.http.response.SingleResponse;
import vc.thinker.apiv2.utils.ChargeRuleUtil;
import vc.thinker.apiv2.web.BaseController;
import vc.thinker.apiv2.web.UserAuthentication;
import vc.thinker.cabbage.common.SysQrCodeUtil;
import vc.thinker.cabbage.se.CabinetChargeRuleService;
import vc.thinker.cabbage.se.CabinetService;
import vc.thinker.cabbage.se.bo.CabinetBO;
import vc.thinker.cabbage.se.bo.CabinetChargeRuleBO;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.bo.SellerBO;
import vc.thinker.cabbage.user.service.MemberService;
import vc.thinker.cabbage.user.service.SellerService;
import vc.thinker.oauth.AuthContants;

@RestController
@RequestMapping(value = "/api/cabinet/", produces = { APPLICATION_JSON_VALUE })
@Api(value = "机柜操作相关",description="机柜操作相关")
public class CabinetController extends BaseController{
	protected Logger logger = LoggerFactory.getLogger(CabinetController.class);
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private CabinetService cabinetService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CabinetChargeRuleService cabinetChargeRuleService;
	
	@Autowired
	private Mapper mapper;
	
	@Autowired
    private MessageSource messageSource;
	
	@RequestMapping(value = "/profile", method = { RequestMethod.GET })
	@PreAuthorize("hasRole('"+AuthContants.ROLE_END_USER+"')")
	@ApiOperation(value = "获取充电柜详情信息")
	public SingleResponse<APICabinetBO> profile(
			@UserAuthentication User user,
			HttpServletRequest request,
			@ApiParam(value = "二维码", required = true) @RequestParam("qrCode") String qrCode){
		Long uid = Long.valueOf(user.getUsername());
		
		String sysCode=SysQrCodeUtil.getSysCode(qrCode);
		
		SingleResponse<APICabinetBO> resp = new SingleResponse<APICabinetBO>(messageSource,request);
		
		CabinetBO cabinet=cabinetService.findDetailsBySysCode(sysCode);
		if(cabinet != null){
			SellerBO seller=sellerService.findById(cabinet.getSellerId());
			if(seller == null){
				resp.setErrorInfo("406", "order.cabinetSellerNotFind","not find seller");
				return resp;
			}
			resp.setItem(mapper.map(cabinet, APICabinetBO.class));
			MemberBO member=memberService.findOne(uid);
			// query charge rule
			CabinetChargeRuleBO cabinetChargeRule = cabinetChargeRuleService.findOne(cabinet.getRuleId());
			
			if(cabinetChargeRule == null){
				resp.setErrorInfo("406", "找不到充电柜"+member.getCurrency()+"币的收费规则");
				return resp;
			}
			
			Locale locale= RequestContextUtils.getLocale(request);
			String freeUseTime=ChargeRuleUtil.formatDateMinute(cabinetChargeRule.getFreeUseTime(),locale);
			String unitMinute=ChargeRuleUtil.formatDateMinute(cabinetChargeRule.getUnitMinute(),locale);
			String unitPrice=ChargeRuleUtil.formatPrice(cabinetChargeRule.getCurrency(),cabinetChargeRule.getUnitPrice());
			String chargeRuleDesc=messageSource.getMessage("cabinet.chargeRule", new Object[]{freeUseTime,unitMinute,unitPrice},locale);
			resp.getItem().setChargeRuleDesc(chargeRuleDesc);
			resp.getItem().setSeller(mapper.map(seller, APISellerBO.class));
		}else{
			resp.setErrorInfo("406", "order.cabinetNotFind", "找不到充电柜");
			return resp;
		}
		return resp;
	}
	
}
