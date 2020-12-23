package vc.thinker.apiv2.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinco.data.core.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import redis.clients.jedis.JedisPool;
import vc.thinker.apiv2.bo.APIPayAmountBO;
import vc.thinker.apiv2.bo.APIUserMoneyBO;
import vc.thinker.apiv2.bo.APIUserMoneyLogBO;
import vc.thinker.apiv2.bo.ApplePaymentBO;
import vc.thinker.apiv2.bo.CashfreePaymentBO;
import vc.thinker.apiv2.bo.FondyPaymetBO;
import vc.thinker.apiv2.bo.PayDetailsBO;
import vc.thinker.apiv2.http.response.ListResponse;
import vc.thinker.apiv2.http.response.PageResponse;
import vc.thinker.apiv2.http.response.SingleResponse;
import vc.thinker.apiv2.utils.MapperUtils;
import vc.thinker.apiv2.utils.WeixinPayUtil;
import vc.thinker.apiv2.web.BaseController;
import vc.thinker.apiv2.web.Responses;
import vc.thinker.apiv2.web.UserAuthentication;
import vc.thinker.biz.exception.ServiceException;
import vc.thinker.cabbage.common.PaymentConstants;
import vc.thinker.cabbage.money.service.MoneyService;
import vc.thinker.cabbage.money.service.PayAmountService;
import vc.thinker.cabbage.user.bo.PayAmountBO;
import vc.thinker.cabbage.user.bo.UserMoneyLogBO;
import vc.thinker.cabbage.user.exception.DepositNotPayException;
import vc.thinker.cabbage.user.exception.MemberNotBindMobileException;
import vc.thinker.cabbage.user.model.UserMoney;
import vc.thinker.cabbage.user.service.MemberService;
import vc.thinker.cabbage.user.service.MemberService.MyDirectPayResponse;
import vc.thinker.cabbage.user.vo.UserMoneyLogVO;
import vc.thinker.oauth.AuthContants;
import vc.thinker.pay.cashfree.CashfreeConfig;
import vc.thinker.pay.response.DirectPayResponse;
import vc.thinker.pay.weixin.WeixinConfig;


@RestController
@RequestMapping(value = "/api/money", produces = { APPLICATION_JSON_VALUE })
@Api(value = "用户金额操作类", description = "提供用户金额操作类")
public class MoneyController extends BaseController{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private Mapper mapper;
	
	@Autowired
	protected TokenStore tokenStore;
	
	
	@Resource(name = "messageSource")
    MessageSource messageSource;
	
	@Autowired
	private PayAmountService payAmountService;
	
	@Autowired
	private MoneyService moneyService;
	
	@Value("${pay.callback}")
	private String payCallback;
	
	@Autowired
	private JedisPool jedisPool;
	
	@PreAuthorize("hasRole('"+AuthContants.ROLE_END_USER+"')")
	@RequestMapping(value = "/balance_pay_result", method = { RequestMethod.GET })
	@ApiOperation(value = "余额充值是否成功",notes = "余额充值是否成功")
	public ResponseEntity<SingleResponse<Boolean>> isCompleteBalancePay(
			@ApiParam(value="支付的流水号") @RequestParam(value="sn") String sn,
			@UserAuthentication User user){
//		long uid = Long.valueOf(user.getUsername());
		SingleResponse<Boolean> resp = new SingleResponse<>();
		resp.setItem(moneyService.isCompleteBalancePay(sn));
		return Responses.ok(resp);
	}
	
	@PreAuthorize("hasRole('"+AuthContants.ROLE_END_USER+"')")
	@RequestMapping(value = "/money_log_list", method = { RequestMethod.GET })
	@ApiOperation(value = "获取用户余额日志列表",notes = "获取用户余额日志列表,使用createTime 降序排列")
	public ResponseEntity<PageResponse<APIUserMoneyLogBO>> findMoneyLogList(
			@UserAuthentication User user,
			@ApiParam(value="createTime小于该时间的数据",required=false) @RequestParam(value="ltTime",required=false) Long ltTime){
		long uid = Long.valueOf(user.getUsername());
		PageResponse<APIUserMoneyLogBO> resp = new PageResponse<APIUserMoneyLogBO>();
		resp.setSuccess(true);
		
		Page<UserMoneyLogBO> page=new Page<>();
		page.setPageNumber(1);
		page.setPageSize(10);
	
		UserMoneyLogVO vo = new UserMoneyLogVO();
		vo.setUid(uid);
		if(ltTime!=null){
			vo.setLtTime(new Date(ltTime));
		}
		List<UserMoneyLogBO> list = moneyService.findMoneyLogListByVO(vo, page);
		resp.init(page, MapperUtils.map(mapper, list, APIUserMoneyLogBO.class));
		return Responses.ok(resp);
	}
	
	
	@PreAuthorize("hasRole('"+AuthContants.ROLE_END_USER+"')")
	@RequestMapping(value = "/user_balance", method = { RequestMethod.GET })
	@ApiOperation(value = "获取余额", notes = "获取余额")
	public ResponseEntity<SingleResponse<APIUserMoneyBO>> getMyBalance(@UserAuthentication User user){
		SingleResponse<APIUserMoneyBO> resp = new SingleResponse<APIUserMoneyBO>();
		long uid = Long.valueOf(user.getUsername());
		UserMoney money = moneyService.findOne(uid);
		if(money == null){
			money=new UserMoney();
			vc.thinker.cabbage.user.bo.MemberBO member=memberService.findOne(uid);
			money.setAvailableBalance(new BigDecimal(0));
			money.setCurrency(member.getCurrency());
		}
		resp.setItem(mapper.map(money, APIUserMoneyBO.class));
		return Responses.ok(resp);
	}
	
	
	@PreAuthorize("hasRole('"+AuthContants.ROLE_END_USER+"')")
	@RequestMapping(value = "/pay_amount_list", method = { RequestMethod.GET })
	@ApiOperation(value = "获取充值金额列表",notes = "获取充值金额列表")
	public ResponseEntity<ListResponse<APIPayAmountBO>> findPayAmountList(
			@UserAuthentication User user){
		long uid = Long.valueOf(user.getUsername());
		ListResponse<APIPayAmountBO> resp = new ListResponse<>();
		
		List<PayAmountBO> list=payAmountService.findNormal(uid);
		
		resp.setItems(MapperUtils.map(mapper, list, APIPayAmountBO.class));
		return Responses.ok(resp);
	}
	
	
	@RequestMapping(value = "/paymet_balance", method = { RequestMethod.POST })
	@PreAuthorize("hasRole('"+AuthContants.ROLE_END_USER+"')")
	@ApiOperation(value = "余额充值")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "参数错误"),
			@ApiResponse(code = 406, message = "业务操作错误")})
	public ResponseEntity<SingleResponse<PayDetailsBO>> paymetBalance(
			@UserAuthentication User user,
			HttpServletRequest request,
			@ApiParam(value = "成功返回地址", required = false) @RequestParam(value="returnUrl",required=false) String returnUrl,
			@ApiParam(value="余额记录id") @RequestParam(value="payAmountId") Long payAmountId,
			@ApiParam(value="支付方式  alipay_app,wx_app,cashfree,fondy,fondy_apple_pay",required=false) @RequestParam(value="paymentMark",
			required=false,defaultValue=PaymentConstants.PAYMENT_MARK_WX_JS_PAY) String paymentMark){
		Long uid=Long.parseLong(user.getUsername());
		
		SingleResponse<PayDetailsBO> resp = new SingleResponse<>(messageSource,request);
		
		PayDetailsBO payDetails=new PayDetailsBO();
	 
		try {
			
			memberService.saveReturnUrl(uid, returnUrl, jedisPool);
			
			MyDirectPayResponse result = moneyService.createRechargePay(uid, payAmountId, payCallback, paymentMark);
			
			DirectPayResponse payResponse=result.getDirectPayResponse();
			if(!payResponse.isSuccess()) {
				resp.setErrorInfo("406", payResponse.getMsg());
				return Responses.ok(resp);
			}
			
			payDetails.setSn(result.getSn());
			
			switch (payResponse.getChannel()) {
			
			case ALIPAY:
				payDetails.setAlipaPpaySignature(payResponse.getContent());
				break;
			case WEIXIN:
				if(PaymentConstants.PAYMENT_MARK_WX_JS_PAY.equals(paymentMark)){
					payDetails.setWeiXinPaymet(WeixinPayUtil.makeWeixinJsPaymet((WeixinConfig)payResponse.getPayConfig(),
							payResponse.getContent()));
				}else {
					payDetails.setWeiXinPaymet(WeixinPayUtil.makeWeixinAppPaymet((WeixinConfig)payResponse.getPayConfig(),
							payResponse.getContent()));
				}
				break;
			case CASHFREE:
				payDetails.setCashfreePaymentBO(makeCashfreePaymentBO(payResponse));
				break;
			case FONDY:
				payDetails.setFondyPaymetBO(new FondyPaymetBO(payResponse.getContent()));
				break;
			//case FONDYAPPLEPAY:
				//payDetails.setApplePaymentBO(makeApplePaymentBO(payResponse));
				//break;
			default:
				break;
			}
			
			resp.setItem(payDetails);
			
			resp.setSuccess(true);
		} catch (DepositNotPayException e) {
			resp.setSuccess(false);
			resp.setErrorInfo("406", "member.notDeposit","您还未交押金");
			return Responses.ok(resp);
		}catch (MemberNotBindMobileException e) {
			resp.setSuccess(false);
			resp.setErrorInfo("412", "user.currenty.notFind", "User Not bound mobile");
			return Responses.ok(resp); 
		}catch (ServiceException e) {
			resp.setSuccess(false);
			resp.setError("406");
			resp.setErrorDescription(e.getMessage());
			return Responses.ok(resp);
		}
		return Responses.ok(resp);
	}
	
	private CashfreePaymentBO makeCashfreePaymentBO(DirectPayResponse payResponse ) {
		
		CashfreePaymentBO item = new CashfreePaymentBO();
		item.setToken(payResponse.getContent());
		item.setOrderCurrency(payResponse.getOrderCurrency());
		item.setOrderId(payResponse.getOrderId());
		item.setOrderAmount(payResponse.getOrderAmount());
		item.setNotifyUrl(payResponse.getNotifyUrl());
		item.setCustomerPhone(payResponse.getCustomerPhone());
		item.setCustomerEmail("anoopvootkuri95@gmail.com");
		CashfreeConfig payConfig = (CashfreeConfig) payResponse.getPayConfig();
		item.setAppId(payConfig.getAppId());
		return item;
	}
	
	private ApplePaymentBO makeApplePaymentBO(DirectPayResponse payResponse) {
		ApplePaymentBO item = new ApplePaymentBO();
		item.setOrderId(payResponse.getOrderId());
		item.setCurrency(payResponse.getOrderCurrency());
		item.setAmount(payResponse.getOrderAmount());
		item.setServerCallBackUrl(payResponse.getNotifyUrl());
		return item;
	}
}