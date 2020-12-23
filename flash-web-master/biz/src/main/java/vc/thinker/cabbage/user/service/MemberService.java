package vc.thinker.cabbage.user.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.kevinsawicki.http.HttpRequest;
import com.github.kevinsawicki.http.HttpRequest.HttpRequestException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.common.collect.Lists;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.sinco.common.Hashids;
import com.sinco.common.area.Country;
import com.sinco.common.area.CountryUtil;
import com.sinco.common.security.PasswordUtil;
import com.sinco.common.utils.DateUtils;
import com.sinco.data.core.Page;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.net.RequestOptions;
import com.stripe.net.RequestOptions.RequestOptionsBuilder;

import net.weedfs.client.RequestResult;
import net.weedfs.client.WeedFSClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import vc.thinker.biz.exception.ServiceException;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.common.PayTotalPriceUtil;
import vc.thinker.cabbage.common.PaymentConstants;
import vc.thinker.cabbage.money.service.MoneyService;
import vc.thinker.cabbage.se.OrderConstants;
import vc.thinker.cabbage.se.OrderService;
import vc.thinker.cabbage.se.PortableBatteryConstatns;
import vc.thinker.cabbage.se.SeCommonConstants;
import vc.thinker.cabbage.se.bo.CabinetBO;
import vc.thinker.cabbage.se.bo.OrderBO;
import vc.thinker.cabbage.se.bo.OrderPbBuyBO;
import vc.thinker.cabbage.se.dao.CabinetDao;
import vc.thinker.cabbage.se.dao.OrderDao;
import vc.thinker.cabbage.se.dao.OrderPbBuyDao;
import vc.thinker.cabbage.se.dao.PortableBatteryDao;
import vc.thinker.cabbage.se.exception.CabinetNotFindException;
import vc.thinker.cabbage.se.exception.CountryInfoHasExistException;
import vc.thinker.cabbage.se.exception.CountryNotFindException;
import vc.thinker.cabbage.se.model.Order;
import vc.thinker.cabbage.se.model.OrderPbBuy;
import vc.thinker.cabbage.se.model.PortableBattery;
import vc.thinker.cabbage.sys.PromotionConstants;
import vc.thinker.cabbage.sys.SysMessageConstants;
import vc.thinker.cabbage.sys.bo.CountryBO;
import vc.thinker.cabbage.sys.bo.CouponBO;
import vc.thinker.cabbage.sys.bo.PromotionBO;
import vc.thinker.cabbage.sys.dao.CountryDao;
import vc.thinker.cabbage.sys.dao.CouponDao;
import vc.thinker.cabbage.sys.dao.PromotionDao;
import vc.thinker.cabbage.sys.dao.SysSettingDao;
import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.cabbage.sys.service.SysMessageService.MemberMessageEvent;
import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.CouponConstants;
import vc.thinker.cabbage.user.IntegralConstants;
import vc.thinker.cabbage.user.UserSource;
import vc.thinker.cabbage.user.bo.DepositPayLogBO;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.bo.MembershipCardBO;
import vc.thinker.cabbage.user.bo.UserBasicCostPayLogBO;
import vc.thinker.cabbage.user.bo.UserDepositLogBO;
import vc.thinker.cabbage.user.bo.UserMoneyBO;
import vc.thinker.cabbage.user.bo.VipPayLogBO;
import vc.thinker.cabbage.user.dao.DepositPayLogDao;
import vc.thinker.cabbage.user.dao.IntegralLogDao;
import vc.thinker.cabbage.user.dao.MemberDao;
import vc.thinker.cabbage.user.dao.MembershipCardDao;
import vc.thinker.cabbage.user.dao.UserBasicCostPayLogDao;
import vc.thinker.cabbage.user.dao.UserCouponDao;
import vc.thinker.cabbage.user.dao.UserDepositLogDao;
import vc.thinker.cabbage.user.dao.UserMoneyDao;
import vc.thinker.cabbage.user.dao.VipPayLogDao;
import vc.thinker.cabbage.user.exception.AccountExistedException;
import vc.thinker.cabbage.user.exception.AccountNotFindException;
import vc.thinker.cabbage.user.exception.DepositException;
import vc.thinker.cabbage.user.exception.DepositNotPayException;
import vc.thinker.cabbage.user.exception.MemberExistedException;
import vc.thinker.cabbage.user.exception.MemberNotBindMobileException;
import vc.thinker.cabbage.user.exception.NoOngoingOrderException;
import vc.thinker.cabbage.user.exception.PasswordErrorException;
import vc.thinker.cabbage.user.exception.PlatNotConfigDepositException;
import vc.thinker.cabbage.user.exception.UserCurrencyNotPerfectException;
import vc.thinker.cabbage.user.exception.UserHasBindedCabinetException;
import vc.thinker.cabbage.user.model.Member;
import vc.thinker.cabbage.user.model.MemberExample;
import vc.thinker.cabbage.user.model.MemberExample.Criteria;
import vc.thinker.cabbage.user.model.UserBasicCostPayLog;
import vc.thinker.cabbage.user.model.UserCoupon;
import vc.thinker.cabbage.user.model.VipPayLog;
import vc.thinker.cabbage.user.service.UpdateUserStatsService.UserBuyVipEvent;
import vc.thinker.cabbage.user.service.UpdateUserStatsService.UserDepositPayEvent;
import vc.thinker.cabbage.user.service.UpdateUserStatsService.UserDepositRefundEvent;
import vc.thinker.cabbage.user.service.UpdateUserStatsService.UserRegistEvent;
import vc.thinker.cabbage.user.vo.DepositPayLogVO;
import vc.thinker.cabbage.user.vo.MemberVO;
import vc.thinker.cabbage.user.vo.QQUserInfo;
import vc.thinker.cabbage.user.vo.VipPayLogVO;
import vc.thinker.cabbage.util.DateTimeUtils;
import vc.thinker.core.mapper.JsonMapper;
import vc.thinker.pay.PayChannel;
import vc.thinker.pay.PayException;
import vc.thinker.pay.PayHandler;
import vc.thinker.pay.PayHandlerFactory;
import vc.thinker.pay.request.CaptureRequest;
import vc.thinker.pay.request.DelCardRequest;
import vc.thinker.pay.request.DirectPayRequest;
import vc.thinker.pay.response.DirectPayResponse;
import vc.thinker.pay.response.RefundResponse;
import vc.thinker.pay.stripe.StripePayHandler;
import vc.thinker.refund.bean.RefundRequest;
import vc.thinker.sys.bo.MobileBO;
import vc.thinker.sys.bo.UserAccountBO;
import vc.thinker.sys.dao.UserAccountDao;
import vc.thinker.sys.model.UserAccount;
import vc.thinker.sys.service.SystemService;
import vc.thinker.sys.service.UserAccountService;
import weixin.popular.api.SnsAPI;
import weixin.popular.bean.user.User;
import weixin.popular.client.LocalHttpClient;

@Service
@Transactional(readOnly = true, rollbackFor = ServiceException.class)
public class MemberService {

	public static final Logger log = LoggerFactory.getLogger(MemberService.class);

	@Autowired
	private PromotionDao promotionDao;

	@Autowired
	private CouponDao couponDao;

	@Autowired
	private UserCouponDao userCouponDao;

	@Autowired
	private MemberDao memberDao;
	@Autowired
	private SystemService systemService;
	@Autowired
	private UserAccountService accountService;
	@Autowired
	private UserAccountDao accountDao;

	@Autowired
	private UserDepositLogDao userDepositLogDao;

	@Autowired
	private DepositPayLogDao depositPayLogDao;

	@Autowired
	private SysSettingDao sysSettingDao;
	
	@Autowired
	private UserMoneyDao userMoneyDao;

	@Autowired
	@Lazy(true)
	private PayHandlerFactory payHandlerFactory;

	@Autowired
	private UniqueRadomCodeService uniqueRadomCodeService;

	@Autowired
	private MembershipCardDao membershipCardDao;

	@Autowired
	private VipPayLogDao vipPayLogDao;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	@Lazy
	private WeedFSClient imageFsClient;

	@Autowired
	private RedisCacheService redisCacheService;

	@Autowired
	private IntegralLogDao integralLogDao;

	@Autowired
	private MoneyService moneyService;

	@Autowired
	private IntegralLogService integralLogService;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private UserBasicCostPayLogDao userBasicCostPayLogDao;

	@Autowired
	private OrderPbBuyDao orderPbBuyDao;

	@Autowired
	private PortableBatteryDao portableBatteryDao;

	@Autowired
	private CabinetDao cabinetDao;

	@Autowired
	private OrderService orderService;

	@Autowired
	private CountryDao countryDao;
	
	@Autowired
	private PayHelpService payHelpService;

	/**
	 * 删除stripe 绑定的银行卡
	 * 
	 * @param uid
	 * @param card
	 * @throws PayException
	 */
	public void delStripeCard(Long uid, String card) throws PayException {
		MemberBO member = memberDao.findOne(uid);
		if (member == null || StringUtils.isBlank(member.getStripeCustomerId())) {
			throw new ServiceException("delete card error");
		}
		StripePayHandler payHandler = payHandlerFactory.getPayHandler(PayChannel.STRIPE);
		DelCardRequest request = new DelCardRequest();
		request.setConfigMark(PaymentConstants.PAYMENT_MARK_STRIPE);
		request.setCusId(member.getStripeCustomerId());
		request.setCard(card);

		payHandler.delCard(request);
	}

	/**
	 * 得到 stripe 支付的customer id
	 * 
	 * @param uid
	 * @param apiKey
	 * @param token
	 * @return
	 */
	@Transactional(readOnly = false)
	public String getStripeCustomerId(Long uid, String apiKey) {
		MemberBO member = memberDao.findOne(uid);
		if (StringUtils.isBlank(member.getStripeCustomerId())) {
			UserAccountBO account = accountDao.findByUid(uid, CommonConstants.ACCOUNT_TYPE_2);

			if (account == null) {
				List<UserAccountBO> accoutList = accountDao.findByUid(uid);
				account = accoutList.isEmpty() ? null : accoutList.get(0);
			}

			RequestOptions requestOptions = (new RequestOptionsBuilder()).setApiKey(apiKey).build();
			Map<String, Object> customerParams = new HashMap<String, Object>();
			customerParams.put("email", account.getLoginName());
//			customerParams.put("source", token);
			try {
				Customer customer = Customer.create(customerParams, requestOptions);
				member.setStripeCustomerId(customer.getId());
				memberDao.update(member);
			} catch (StripeException e) {
				log.error("", e);
				throw new ServiceException(e.getMessage());
			}
		}
		return member.getStripeCustomerId();
	}

	/**
	 * 第三方账户解绑
	 * 
	 * @param uid
	 * @param userSource
	 */
	@Transactional(readOnly = false)
	public void unbundledThirdParty(Long uid, UserSource userSource) {
		UserAccountBO account = accountDao.findByUid(uid, userSource.getAccountType());
		if (account == null) {
			throw new AccountNotFindException();
		}
		accountDao.delete(account.getId());
	}

	/**
	 * @throws MoblieNotFindException
	 * 
	 * @Title: registerWxUser @Description: TODO(注册第三方用户) @param @param
	 * loginName @param @param wxAuthAccessToken @param @param wxAuthAppId
	 * 设定文件 @return void 返回类型 @author @throws
	 */
	@Transactional(readOnly = false, rollbackFor = ServiceException.class)
	public MemberBO registerThirdPartyUser(String loginName, String authAccessToken, UserSource userSource, String ip,
			String clientSource,String clientId) {
		log.info("注册第三方[{}]用户开始：openId: [{}], AccessToken: [{}]", userSource, loginName, authAccessToken);
		UserAccountBO ub = accountService.findByLoginName(loginName);

		if (ub == null) {
			User user = null;
			String accountType = null;
			switch (userSource) {
			case WEIXIN_OPEN:
				user = getWeixinUserBaseInfo(loginName, authAccessToken);
				accountType = CommonConstants.ACCOUNT_TYPE_4;
				break;
			case QQ:
				user = getQQUserInfo(loginName, authAccessToken);
				accountType = CommonConstants.ACCOUNT_TYPE_6;
				break;
			case FACEBOOK:
				user = getFasebook(loginName, authAccessToken);
				accountType = CommonConstants.ACCOUNT_TYPE_8;
				break;
			case GOOGLE:
				user = getGoogle(authAccessToken, clientId);
				accountType = CommonConstants.ACCOUNT_TYPE_10;
				break;
			default:
				break;
			}
			if (user != null) {
				Long uid = systemService.createUser(accountType, loginName, RandomStringUtils.random(15), ip, null);
				MemberBO mb = this.createMemeberByThirdPary(uid, "-", user.getNickname(), user.getHeadimgurl(), ip,
						clientSource);
				log.info("注册第三方[{}]用户成功：openId: [{}], authAccessToken: [{}]", userSource, loginName, authAccessToken);
				return mb;
			}
		} else {
			return memberDao.findOne(ub.getUid());
		}
		throw new ServiceException("注册第三方用户失败");
	}

	
	private User getGoogle(String idToken, String clientId) {
		try {
			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
					new NetHttpTransport(), JacksonFactory.getDefaultInstance())
					.setAudience(Collections.singletonList(clientId)).build();
			GoogleIdToken verify = verifier.verify(idToken);
			if(null != verifier) {
				Payload payload = verify.getPayload();
				log.info("==========payload:[{}]", JSON.toJSON(payload));
				User result = new User();
				result.setNickname((String) payload.get("name"));
				String picture = (String) payload.get("picture");
				result.setHeadimgurl((String) payload.get("picture"));
				if (StringUtils.isNotBlank(picture)) {
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					HttpRequest.get(picture).receive(bos);
					RequestResult imageResult = imageFsClient.upload(bos.toByteArray(),
							System.currentTimeMillis() + "_google", "image/jpeg");
					result.setHeadimgurl(imageResult.getUrl());
				}
				return result;
			} 
		} catch (GeneralSecurityException | IOException e) {
			log.error("获取token异常:[{}]",e.getMessage());
		}
		return null;
	}

 
	/**
	 * 绑定第三方
	 * 
	 * @param loginName
	 * @param authAccessToken
	 * @param userSource
	 * @return
	 */
	@Transactional(readOnly = false, rollbackFor = ServiceException.class)
	public void boundThirdParty(Long uid, String loginName, String authAccessToken, UserSource userSource, String clientId) {
		UserAccountBO ub = accountService.findByLoginName(loginName);
		if (ub != null) {
			throw new AccountExistedException();
		}

		User user = null;
		String accountType = null;
		switch (userSource) {
		case WEIXIN_OPEN:
			user = getWeixinUserBaseInfo(loginName, authAccessToken);
			accountType = CommonConstants.ACCOUNT_TYPE_4;
			break;
		case QQ:
			user = getQQUserInfo(loginName, authAccessToken);
			accountType = CommonConstants.ACCOUNT_TYPE_6;
			break;
		case FACEBOOK:
			user = getFasebook(loginName, authAccessToken);
			accountType = CommonConstants.ACCOUNT_TYPE_8;
			break;
		case GOOGLE:
			user = getGoogle(authAccessToken, clientId);
			accountType = CommonConstants.ACCOUNT_TYPE_10;
			break;
		default:
			break;
		}

		ub = accountService.findByUid(uid, accountType);
		if (ub != null) {
			throw new AccountExistedException();
		}
		UserAccount account = new UserAccount();
		account.setUid(uid);
		account.setLoginName(loginName);
		account.setPassword(PasswordUtil.entryptPassword(UUID.randomUUID().toString()));
		account.setCreateTime(new Date());
		account.setUpdateTime(new Date());
		account.setAccountType(accountType);
		account.setIsDeleted(false);
		accountDao.save(account);
	}

	/**
	 * 查找会员卡购买记录
	 * 
	 * @param page
	 * @param vo
	 * @return
	 */
	public List<VipPayLogBO> findVipPayList(Page<VipPayLogBO> page, VipPayLogVO vo) {
		return vipPayLogDao.findByPage(page, vo);
	}

	/**
	 * 是否完成购买会员支付
	 * 
	 * @param sn
	 * @return
	 */
	public boolean isCompleteVIPPay(String sn) {
		VipPayLogBO vipPayLog = vipPayLogDao.findBySn(sn);
		if (vipPayLog == null) {
			return false;
		}
		return CommonConstants.VIP_PAY_LOG_STATUS_2.equals(vipPayLog.getStatus());
	}

	/**
	 * 会员卡完成支付
	 * 
	 * @param cid
	 * @param propertys
	 * @param cashFee   用户实际支付的金额
	 * @return
	 */
	@Transactional(readOnly = false)
	public void completeVIPPay(String sn, String outOrderId, BigDecimal cashFee) {
		VipPayLogBO vipPayLog = vipPayLogDao.findBySn(sn);
		if (vipPayLog == null) {
			throw new ServiceException("购买会员卡记录不存在，完成支付失败");
		}

		if (vipPayLog.getStatus().equals(CommonConstants.VIP_PAY_LOG_STATUS_2)) {
			throw new DepositException("购买会员卡记录已经完成支付,请误重复支付");
		}
		vipPayLog.setStatus(CommonConstants.VIP_PAY_LOG_STATUS_2);
		vipPayLog.setOutOrderId(outOrderId);
		vipPayLog.setPayTime(new Date());
		vipPayLog.setCashFee(cashFee);
		vipPayLogDao.update(vipPayLog);

		MemberBO member = memberDao.findOne(vipPayLog.getUid());

		Date vipDate = member.getVipExpiresIn();
		// 如果到期时间在当前时候之后
		if (vipDate == null || vipDate.before(new Date())) {
			vipDate = new Date();
		}
		// 增加会员天数
		Calendar newDate = DateUtils.toCalendar(vipDate);

		// 判断是增加天还是小时
		if (CommonConstants.MEMBERSHIP_CARD_UNIT_HOUR.equals(vipPayLog.getVipCardUnit())) {
			newDate.add(Calendar.HOUR_OF_DAY, vipPayLog.getVipDay());
		} else {
			newDate.add(Calendar.DAY_OF_MONTH, vipPayLog.getVipDay());
		}
		member.setVipExpiresIn(newDate.getTime());
		member.setVipDiscount(vipPayLog.getVipDiscount());
		memberDao.update(member);
		log.info("完成[{}]记录支付，用户[{}]购买会员到[{}]", sn, member.getNickname(), vipDate);

		// TODO 增加返利金
		moneyService.rebate(vipPayLog.getAmount(), vipPayLog.getCurrency(), vipPayLog.getUid(), vipPayLog.getId(),
				CommonConstants.PLATFORM_REVENUE_LOG_VIP, "User purchase membership card");
//		SysSetting setting = sysSettingDao.findOne();
//		//加返利金
//		BigDecimal rebateMoney =  vipPayLog.getAmount().multiply(new BigDecimal(1).subtract(setting.getPlatformExtraction()));
//		moneyService.isExistAndCreate(vipPayLog.getUid());
//		moneyDao.addRebateMoney(vipPayLog.getUid(),rebateMoney);
//		UserRebateMoneyLog rlog = new UserRebateMoneyLog();
//		rlog.setUserId(vipPayLog.getUid());
//		rlog.setLogAmount(rebateMoney);
//		rlog.setLogCurrency(vipPayLog.getCurrency());
//		rlog.setOldLogAmount(rebateMoney);
//		rlog.setOldLogCurrency(vipPayLog.getCurrency());
//		rlog.setExchangeRate(new BigDecimal(1));
//		rlog.setLogInfo("buy vip");
//		rlog.setCreateTime(new Date());
//		userRebateMoneyLogDao.save(rlog);
//		
//		//获取平台返利比例
//		BigDecimal platformAmount =  vipPayLog.getAmount().multiply(setting.getPlatformExtraction());
//				
//		//添加平台收益
//		platformRevenueService.addPlatformRevenue(vipPayLog.getId(), CommonConstants.PLATFORM_REVENUE_LOG_VIP,
//				vipPayLog.getCurrency(), platformAmount, "User purchase membership card");

		// 统计
		UserBuyVipEvent userBuyVipEvent = new UserBuyVipEvent();
		userBuyVipEvent.setVipPayLogId(vipPayLog.getId());
		publisher.publishEvent(userBuyVipEvent);
	}

	/**
	 * 创建vip支付
	 * 
	 * @param uid
	 * @param vipId
	 * @param payCallback
	 * @param paymentMark
	 * @return
	 */
	@Transactional(readOnly = false)
	public MyDirectPayResponse createVIPPay(Long uid, Long cardId, String payCallback, String paymentMark) {

		checkPaymentMark(paymentMark);

		MemberBO member = memberDao.findOne(uid);

		if (member == null) {
			throw new ServiceException("用户不存在");
		}

		if (null == member.getDeposit() || member.getDeposit().compareTo(BigDecimal.ZERO) <= 0) {
			throw new DepositNotPayException();
		}

//		if(null != member.getIsPayBasicCost() && !member.getIsPayBasicCost()){
//			throw new BasicCostNotPayException();
//		}

//		String currency = CountryUtil.get(member.getCountry()).getCurrencyCode();

		MembershipCardBO card = membershipCardDao.findOne(cardId);
		if (card == null) {
			throw new ServiceException("不存在的会员卡");
		}


//		PayHandler payHandler = payHandlerFactory.getPayHandler(paymentMark);

		DirectPayRequest payRequest = new DirectPayRequest();

		// 获取系统押金
		BigDecimal totalPrice = card.getCardAmount();

		payRequest.setTotalFee(PayTotalPriceUtil.getTotalPrice(totalPrice));
		payRequest.setCurrency(card.getCurrency());
		payRequest.setConfigMark(paymentMark);

		String payOrderCode = payHelpService.getPayOrderCode(CommonConstants.PAY_TYPE_VIP, uid);
		

		// 保存支付信息
		VipPayLog vipPayLog = new VipPayLog();
		vipPayLog.setUid(uid);
		vipPayLog.setSn(payOrderCode);
		vipPayLog.setVipDay(card.getCardEffectiveTime());
		vipPayLog.setVipCardUnit(card.getCardUnit());
		vipPayLog.setCurrency(card.getCurrency());
		vipPayLog.setVipDiscount(card.getDiscount().doubleValue());
		vipPayLog.setVipCardName(card.getCardName());
		vipPayLog.setPayOrderCode(payOrderCode);
		vipPayLog.setPaymentMark(paymentMark);
		vipPayLog.setAmount(totalPrice); // sys.get(0).getDeposit()
		vipPayLog.setStatus(CommonConstants.DEPOSIT_LOG_STATUS_1);
		vipPayLog.setCreateTime(new Date());
		vipPayLogDao.save(vipPayLog);
		
		SysSetting sysSetting = sysSettingDao.findOne();

		payRequest.setBody("Membership card:" + sysSetting.getAppNameEnglish());

		String notifyUrl = payHelpService.getNotifyUrl(paymentMark, payCallback);
		payRequest.setNotifyUrl(notifyUrl);
		payRequest.setOutTradeNo(payOrderCode);
		// strpe 已token为 outTradeNo

		if (PaymentConstants.PAYMENT_MARK_STRIPE.equals(paymentMark)) {
			payRequest.setOutTradeNo(member.getStripeCustomerId());
		}  
		
		// 设置附加属性为支付类型
		payRequest.setReqAttach(CommonConstants.PAY_TYPE_VIP);
		payRequest.setSubject("会员卡购买");

		try {

			DirectPayResponse payResponse = 
					payHelpService.getPayResponse(paymentMark, uid, payRequest);

			if(null == payResponse) {
				throw new ServiceException("调用支付失败");
			}
			if(payResponse.isSuccess() && PayChannel.STRIPE == payResponse.getChannel()) {
				completeVIPPay(payOrderCode, payResponse.getPayOrderId(), payResponse.getTotalFee());
			}
			if(payResponse.isSuccess() && PayChannel.CASHFREE == payResponse.getChannel()) {
				payResponse.setNotifyUrl(notifyUrl);
				payResponse.setOrderAmount(String.valueOf(payRequest.getTotalFee()));
				payResponse.setOrderId(payOrderCode);
				payResponse.setCustomerPhone(member.getMobile());
				payResponse.setOrderCurrency(card.getCurrency());
			}
			return new MyDirectPayResponse(payResponse, payOrderCode);
			
		} catch (PayException e) {
			log.error("支付失败", e);
			throw new RuntimeException("调用支付失败 出现异常", e);
		}
	}
	

	/**
	 * 支付方式校验
	 * 
	 * @param paymentMark
	 */
	public void checkPaymentMark(String paymentMark) {

		if (!(PaymentConstants.PAYMENT_MARK_ALIPAY_APP.equals(paymentMark)
				|| PaymentConstants.PAYMENT_MARK_WX_APP.equals(paymentMark)
				|| PaymentConstants.PAYMENT_MARK_WX_JS_PAY.equals(paymentMark)
				|| PaymentConstants.PAYMENT_MARK_STRIPE.equals(paymentMark)
				|| PaymentConstants.PAYMENT_MARK_CASHFREE.equals(paymentMark)
				|| PaymentConstants.PAYMENT_MARK_FONDY.equals(paymentMark)
				|| PaymentConstants.PAYMENT_MARK_FONDY_APPLE_PAY.equals(paymentMark))) {
			throw new ServiceException("支付方式:" + paymentMark + "不存在");
		}
	}

	public MemberBO depositPayCheck(Long uid, String paymentMark) {
		MemberBO member = memberDao.findOne(uid);
		if (member == null) {
			throw new ServiceException("用户不存在");
		}
		if (member.getDeposit() != null && member.getDeposit().doubleValue() > 0) {
			throw new ServiceException("You have paid the Deposit");
		}
		if(PaymentConstants.PAYMENT_MARK_CASHFREE.equals(paymentMark)) {
			if("-".equals(member.getMobile())) {
				throw new MemberNotBindMobileException();
			}
		}
		return member;
	}
	
	@Transactional(readOnly = false)
	public DirectPayResponse createApplePay(Long uid, String payCallback, String paymentMark) {
		
		//用户校验
		depositPayCheck(uid, paymentMark);
		
		// 查询系统配置
		SysSetting sys = sysSettingDao.findOne();
		
		// 币种
		String currency = sys.getPlayformDefaultCurrency();
		// 金额
		BigDecimal totalPrice = queryDetpsit(currency);
		
		// 生成订单号
		String payOrderCode = 
				payHelpService.getPayOrderCode(CommonConstants.PAY_TYPE_DEPOSIT, uid);
		
		//保存支付信息
		saveDepositPayLog(uid, payOrderCode, paymentMark, totalPrice, currency);
		
		//获取回调地址
		String notifyUrl = payHelpService.getNotifyUrl(paymentMark, payCallback);
		
		// 组装返回报文
		DirectPayResponse payResponse = new DirectPayResponse(PayChannel.ALIPAY, null);
		payResponse.setSuccess(true);
		payResponse.setOrderCurrency(currency);
		payResponse.setNotifyUrl(notifyUrl);
		payResponse.setOrderAmount(String.valueOf(totalPrice));
		payResponse.setOrderId(payOrderCode);
			
		return payResponse;
	}
	
	// 押金支付
	@Transactional(readOnly = false)
	public DirectPayResponse createDepositPay(Long uid, String payCallback, String paymentMark) {

		try {

			// 校验支付方式
			checkPaymentMark(paymentMark);

			if(PaymentConstants.PAYMENT_MARK_FONDY_APPLE_PAY.equals(paymentMark)) {
				return createApplePay(uid, payCallback, paymentMark);
			}
			
			//用户校验
			MemberBO member = depositPayCheck(uid, paymentMark);

			SysSetting sys = sysSettingDao.findOne();
			
			String currency = sys.getPlayformDefaultCurrency();

			DirectPayRequest payRequest = new DirectPayRequest();
			payRequest.setCurrency(currency);
			BigDecimal totalPrice = queryDetpsit(currency);
			payRequest.setTotalFee(totalPrice);
			payRequest.setConfigMark(paymentMark);
			payRequest.setCapture(false);

			// 生成订单号
			String payOrderCode = 
					payHelpService.getPayOrderCode(CommonConstants.PAY_TYPE_DEPOSIT, uid);

			//保存支付信息
			saveDepositPayLog(uid, payOrderCode, paymentMark, totalPrice, currency);

			//获取回调地址
			String notifyUrl = payHelpService.getNotifyUrl(paymentMark, payCallback);
			payRequest.setNotifyUrl(notifyUrl);
			payRequest.setReturnUrl(payCallback+"/returnUrl");
			payRequest.setOutTradeNo(payOrderCode);
			
			// strpe 已token为 outTradeNo
			if (PaymentConstants.PAYMENT_MARK_STRIPE.equals(paymentMark)) {
				payRequest.setOutTradeNo(member.getStripeCustomerId());
			}  

			// 设置附加属性为支付类型
			payRequest.setReqAttach(CommonConstants.PAY_TYPE_DEPOSIT);// 押金
			payRequest.setSubject("Deposit Pay: " + sys.getAppNameEnglish());
			payRequest.setCapture(false);

			DirectPayResponse payResponse = 
					payHelpService.getPayResponse(paymentMark, uid, payRequest);
			
			if(null == payResponse) {
				throw new ServiceException("调用支付失败");
			}
			if(payResponse.isSuccess() && PayChannel.STRIPE == payResponse.getChannel()) {
				completeDepositPay(payOrderCode, payResponse.getPayOrderId(), payResponse.getTotalFee());
			}
			
			if(payResponse.isSuccess() && PayChannel.CASHFREE == payResponse.getChannel()) {
				payResponse.setOrderCurrency(currency);
				payResponse.setNotifyUrl(notifyUrl);
				payResponse.setOrderAmount(String.valueOf(payRequest.getTotalFee()));
				payResponse.setOrderId(payOrderCode);
				payResponse.setCustomerPhone(member.getMobile());
			}
			
			return payResponse;
			
		} catch (PayException e) {
			log.error("支付失败", e);
			throw new RuntimeException("调用支付失败 出现异常", e);
		}
	}
 
	public void saveDepositPayLog(Long uid, String orderCode, 
			String paymentMark, BigDecimal amount, String currency) {
		
		DepositPayLogBO dlog = new DepositPayLogBO();
		dlog.setUid(uid);
		dlog.setPayOrderCode(orderCode);
		dlog.setPaymentMark(paymentMark);
		dlog.setAmount(amount); 
		dlog.setStatus(CommonConstants.DEPOSIT_LOG_STATUS_1);
		dlog.setCreateTime(new Date());
		dlog.setCurrency(currency);

		try {
			depositPayLogDao.save(dlog);
		} catch (DuplicateKeyException e) {
			throw new ServiceException("请误频繁点击支付押金");
		}
	}
	/**
	 * 押金完成支付
	 * 
	 * @param cid
	 * @param propertys
	 * @param cashFee   用户实际支付的金额
	 * @return
	 */
	@Transactional(readOnly = false)
	public void completeDepositPay(String payOrderCode, String outOrderId, BigDecimal cashFee) {
		DepositPayLogBO dlog = depositPayLogDao.findByPayOrderCode(payOrderCode);
		if (dlog == null) {
			throw new ServiceException("订单不存在，完成支付失败");
		}
		if (dlog.getStatus() >= CommonConstants.DEPOSIT_LOG_STATUS_2) {
			throw new DepositException("订单已经完成支付,请误重复支付");
		}

		dlog.setStatus(CommonConstants.DEPOSIT_LOG_STATUS_2);
		dlog.setOutOrderId(outOrderId);
		dlog.setPayTime(new Date());
		dlog.setCashFee(cashFee);

		if (PaymentConstants.PAYMENT_MARK_STRIPE.equals(dlog.getPaymentMark())) {
			dlog.setIsCapture(false);
		} else {
			dlog.setIsCapture(true);
			dlog.setCaptureTime(new Date());
		}

		depositPayLogDao.update(dlog);

//		SysSetting setting = sysSettingDao.findOne();
//		MemberBO bo = memberDao.findOne(dlog.getUid());
		MemberBO up_bo = new MemberBO();
		up_bo.setUid(dlog.getUid());
		up_bo.setDeposit(dlog.getAmount());
//		up_bo.setDeposit(dlog.getAmount());
		up_bo.setAuthStatus(CommonConstants.MEMBER_AUTH_STATUS_SUC);// 认证成功
		up_bo.setAuthStep(CommonConstants.MEMBER_AUTH_STEP_4);// 第四步
		memberDao.update(up_bo);
//		if (!bo.getAuthStep().equals(CommonConstants.MEMBER_AUTH_STEP_4)) {
//			if (setting.getIsNeedAuthen()) {
//				bo.setAuthStep(CommonConstants.MEMBER_AUTH_STEP_3);// 第三步
//			} else {
//				bo.setAuthStatus(CommonConstants.MEMBER_AUTH_STATUS_SUC);// 认证成功
//				bo.setAuthStep(CommonConstants.MEMBER_AUTH_STEP_4);// 第四步
//			}
//			memberDao.save(bo);
//		}
		// 累加用户押金
//		memberDao.incrDepositby(dlog.getUid(), dlog.getAmount());

		// 付款成功
		UserDepositLogBO userDepositLog = new UserDepositLogBO();
		userDepositLog.setUid(dlog.getUid());
		userDepositLog.setAmount(dlog.getAmount());
		userDepositLog.setType(CommonConstants.MEMBER_DEPOSIT_TYPE_1);
		userDepositLog.setCreateTime(new Date());
		userDepositLog.setPayMark(dlog.getPaymentMark());
		userDepositLog.setCurrency(dlog.getCurrency());
		userDepositLogDao.save(userDepositLog);

		// 用户押金值统计
		UserDepositPayEvent depositPayEvent = new UserDepositPayEvent();
		depositPayEvent.setDepositPayId(dlog.getId());
		publisher.publishEvent(depositPayEvent);
	}

	/**
	 * 锁定押金
	 */
	@Transactional(readOnly = false)
	public void captureDeposit(DepositPayLogBO depositPayLog) {
		if (depositPayLog.getIsCapture()) {
			log.error("{} 押金记录已锁定，无须再执行", depositPayLog.getId());
			return;
		}
		StripePayHandler payHandler = payHandlerFactory.getPayHandler(PayChannel.STRIPE);
		CaptureRequest request = new CaptureRequest();
		request.setConfigMark(PaymentConstants.PAYMENT_MARK_STRIPE);
		request.setOrderNo(depositPayLog.getOutOrderId());

		depositPayLog.setIsCapture(true);
		depositPayLog.setCaptureTime(new Date());
		try {
			payHandler.capture(request);
			depositPayLogDao.save(depositPayLog);
		} catch (PayException e) {
			if (e.getCause() instanceof InvalidRequestException) {
				if (e.getCause().getMessage().indexOf("has already been captured") > 0) {
					depositPayLogDao.save(depositPayLog);
				}
			} else {
				log.error("执行押金锁定失败", e);
			}
		}
	}

//
//	/**
//	 * 发送短信验证码
//	 * @param uid
//	 */
//	public void sendValidateCode(Long uid,String mobile,String ip){
//		String code = SmsUtils.createRandom(true, 6);
//		redisCacheService.setSmsCode(uid, code, mobile);
//		msgTools.sendTemplateSms(CommonConstants.SMS_VALIDATE_CODE, mobile, 
//				ip, "{code:\""+code+"\", product:\"唯秘\"}",
//				CommonConstants.SMS_SIGN_NAME);
//	}

	/**
	 * 修改用户手机号,不修改账户
	 * 
	 * @param uid
	 * @param mobile
	 * @param validateCode
	 */
	@Transactional(readOnly = false, rollbackFor = ServiceException.class)
	public void updateMobile(Long uid, String mobile, String validateCode) {
		MemberBO member = memberDao.findOne(uid);
		if (member == null) {
			throw new ServiceException("不存在的用户");
		}
		String cacheCode = redisCacheService.getSmsCode(uid, mobile);
		if (!validateCode.equals(cacheCode)) {
			throw new ServiceException("验证码不正确");
		}

		member.setMobile(mobile);
		member.setUpdateTime(new Date());
		;
		memberDao.update(member);

		redisCacheService.delSmsCode(uid, mobile);
	}

	@Transactional(readOnly = false)
	public boolean freezeMember(Long userId) {
		MemberBO bo = findByUid(userId);
		Member member = new Member();
		member.setUid(userId);
		if (CommonConstants.MEMBER_STATUS_ZC.equals(bo.getStatus())
				|| CommonConstants.MEMBER_STATUS_JY.equals(bo.getStatus())) {
			switch (bo.getStatus()) {
			case CommonConstants.MEMBER_STATUS_ZC:
				member.setStatus(CommonConstants.MEMBER_STATUS_JY);
				break;
			case CommonConstants.MEMBER_STATUS_JY:
				member.setStatus(CommonConstants.MEMBER_STATUS_ZC);
				break;
			}
			memberDao.update(member);
			return true;
		}
		return false;
	}

	public List<MemberBO> findMemberPageByVO(MyPage<MemberBO> page, MemberVO vo) {
		return memberDao.findMemberPageByVO(page, vo);
	}

	public MemberBO findMemberByMobile(String mobile) {
		return memberDao.findMemberByMobile(mobile);
	}

	public MemberBO findMemberByNickname(String nickname) {
		return memberDao.findMemberByNickname(nickname);
	}

	public MemberBO findByUid(Long uid) {
		return memberDao.findOne(uid);
	}

	public MemberBO findDetails(Long uid) {
		MemberBO member = memberDao.findOne(uid);
		if (member != null) {
			List<UserAccountBO> accountList = accountDao.findByUid(uid);
			for (UserAccountBO e : accountList) {
				switch (e.getAccountType()) {
				case CommonConstants.ACCOUNT_TYPE_4:
					member.setIsBindWeixin(true);
					break;
				case CommonConstants.ACCOUNT_TYPE_6:
					member.setIsBindQQ(true);
					break;
				case CommonConstants.ACCOUNT_TYPE_8:
					member.setIsBindFasebook(true);
					break;
				case CommonConstants.ACCOUNT_TYPE_2:
					member.setIsBindMobile(true);
					break;
				case CommonConstants.ACCOUNT_TYPE_10:
					member.setIsBindGoogle(true);
					break;
				default:
					break;
				}
			}
			member.setIntegral(integralLogDao.sumByUid(uid));
			member.setBalance(moneyService.getAvailableBalance(uid));
			SysSetting findOne = sysSettingDao.findOne();
			member.setCurrency(findOne.getPlayformDefaultCurrency());
//			if (!StringUtils.isEmpty(member.getSysCode())) {
//				member.setCabinetAlias(cabinetDao.findBySysCode(member.getSysCode()).getCabinetAlias());
//			}
		}
		return member;
	}

	/**
	 *
	 * @Title: updateMemberPassword @Description: TODO(修改密码) @param @param
	 * userId @param @param password 设定文件 @return void 返回类型 @author Tang @throws
	 */
	@Transactional(readOnly = false)
	public boolean updateMemberPassword(Long userId, String password) {
		if (accountService.passwordUpdate(userId, password) > 0) {
			return true;
		}
		return false;
	}

	/**
	 *
	 * @Title: updateMemberPassword @Description: TODO(修改密码) @param @param
	 * userId @param @param password 设定文件 @return void 返回类型 @author Tang @throws
	 */
	@Transactional(readOnly = false)
	public boolean updateMemberPassword(Long userId, String password, String oldPassword) {
		UserAccountBO account = accountDao.findByUid(userId, CommonConstants.ACCOUNT_TYPE_2);
		if (account == null) {
			throw new AccountNotFindException();
		}
		if (!PasswordUtil.validatePassword(oldPassword, account.getPassword())) {
			throw new PasswordErrorException();
		}
		return updateMemberPassword(userId, password);
	}

	/**
	 *
	 * @Title: updateMemberPassword @Description: TODO(修改密码) @param @param
	 * userId @param @param password 设定文件 @return void 返回类型 @author Tang @throws
	 */
	@Transactional(readOnly = false)
	public boolean updateMemberPassword(MobileBO mobile, String password) {
		UserAccountBO account = accountDao.findByLoginName(mobile.getFullMobile(), CommonConstants.ACCOUNT_TYPE_2);
		if (account == null) {
			throw new AccountNotFindException();
		}
		return updateMemberPassword(account.getUid(), password);
	}

//	/**
//	 * 创建member
//	 * @param uid
//	 * @param username
//	 * @return
//	 */
//	@Transactional(readOnly = false)
//	public MemberBO createMemeber(MobileBO mobile,String nickname,String password,String ip,String clientSource){
//		UserAccountBO credentials = accountDao.findByLoginName(mobile.getFullMobile());
//		Long uid=null;
//		if(credentials == null){
//			uid = systemService.createUser(CommonConstants.ACCOUNT_TYPE_2, null, null, null,
//					mobile.getFullMobile(),password, ip, null);
//		}else{
//			uid = credentials.getUid();
//			if(memberDao.findOne(uid) != null){
//				throw new MemberExistedException();
//			}
//		}
//		return createMemeber(uid, mobile, nickname, password, ip, clientSource);
//	}

	/**
	 * 创建member
	 * 
	 * @param uid
	 * @param username
	 * @return
	 */
	@Transactional(readOnly = false)
	public MemberBO createMemeber(MobileBO mobile, String nickname, String password, String ip, String clientSource,
			String sysCode) {
		UserAccountBO credentials = accountDao.findByLoginName(mobile.getFullMobile());
		Long uid = null;
		if (credentials == null) {
			uid = systemService.createUser(CommonConstants.ACCOUNT_TYPE_2, mobile.getFullMobile(), password, ip,
					mobile.getCountry());
		} else {
			uid = credentials.getUid();
			if (memberDao.findOne(uid) != null) {
				throw new MemberExistedException();
			}
		}
		return createMemeber(uid, mobile, nickname, password, ip, clientSource, sysCode);
	}

	/**
	 * 第三方登陆创建 member 由于客户端根据手机号是否为空判断是否进行手机号绑定
	 */
	@Transactional(readOnly = false)
	public MemberBO createMemeberByThirdPary(Long uid, String mobile, String nickname, String password, String ip,
			String clientSource) {
		Member member = new MemberBO();
		member.setUid(uid);
		member.setCreateTime(new Date());
		member.setMobile(mobile);
		member.setNickname(nickname);
		member.setIsDeleted(false);
		member.setStatus(CommonConstants.MEMBER_STATUS_ZC);
		member.setAuthStatus(CommonConstants.MEMBER_AUTH_STATUS_ING);
		member.setAuthStep(CommonConstants.MEMBER_AUTH_STEP_2);
		member.setClientSource(clientSource);
		member.setRegisteredIp(ip);
		member.setInviteCode(uniqueRadomCodeService.getCode(CommonConstants.CODE_TYPE_INVITION));
		// 无需缴纳基础会员费
		member.setIsPayBasicCost(true);

		memberDao.save(member);
		// 注册积分
		integralLogService.addIntegral(IntegralConstants.INTEGER_CODE_REGIST, uid, "new user",
				IntegralConstants.INTEGER_TYPE_REGIST, String.valueOf(uid));
		// 人员统计
		UserRegistEvent userRegistEvent = new UserRegistEvent();
		userRegistEvent.setUid(member.getUid());
		publisher.publishEvent(userRegistEvent);

		return memberDao.findOne(uid);
	}

	/**
	 * 创建member
	 * 
	 * @param uid
	 * @param username
	 * @return
	 */
	@Transactional(readOnly = false)
	public MemberBO createMemeber(Long uid, MobileBO mobile, String nickname, String password, String ip,
			String clientSource, String sysCode) {
		Member member = new MemberBO();
		member.setUid(uid);
		member.setCreateTime(new Date());

		if (mobile != null) {
			member.setMobile(mobile.getFullMobile());
			member.setCountry(mobile.getCountry().getCode());
			member.setCurrency(mobile.getCountry().getCurrencyCode());
			if (StringUtils.isBlank(nickname)) {
				nickname = mobile.getMobile();
			}
		}

		member.setNickname(nickname);
		member.setIsDeleted(false);
		member.setStatus(CommonConstants.MEMBER_STATUS_ZC);
		member.setAuthStatus(CommonConstants.MEMBER_AUTH_STATUS_ING);
		member.setAuthStep(CommonConstants.MEMBER_AUTH_STEP_2);
		member.setClientSource(clientSource);
		member.setRegisteredIp(ip);
		member.setSysCode(sysCode);
		// 无需缴纳基础会员费
		member.setIsPayBasicCost(true);

		// 生成邀请码
		member.setInviteCode(uniqueRadomCodeService.getCode(CommonConstants.CODE_TYPE_INVITION));
		memberDao.save(member);
		
		SysSetting sys = sysSettingDao.findOne();
		UserMoneyBO money = new UserMoneyBO();
		money.setUid(uid);
		money.setAvailableBalance(BigDecimal.ZERO);
		money.setCurrency(sys.getPlayformDefaultCurrency());
		userMoneyDao.save(money);
		
		// 注册赠送优惠券
		List<PromotionBO> promotionList = promotionDao.findHaveInHand(PromotionConstants.PROMOTION_TYPE_REGIST_CODE,
				null);
		for (PromotionBO promotion : promotionList) {
			CouponBO coupon = couponDao.findOne(promotion.getCouponId());
			if (coupon != null) {
				sendCoupon(member.getUid(), coupon, PromotionConstants.PROMOTION_TYPE_REGIST_CODE, promotion.getId());
			}
		}

		// 绑定机柜赠送优惠券
//		if (!StringUtils.isEmpty(sysCode)) {
//			sendCouponOfBindCabinet(member.getUid(), member.getCurrency(), sysCode);
//		}

		// 注册积分
//		integralLogService.addIntegral(IntegralConstants.INTEGER_CODE_REGIST, uid, "new user",
//				IntegralConstants.INTEGER_TYPE_REGIST, String.valueOf(uid));
		// 人员统计
		UserRegistEvent userRegistEvent = new UserRegistEvent();
		userRegistEvent.setUid(member.getUid());
		publisher.publishEvent(userRegistEvent);

		return memberDao.findOne(uid);
	}

	/**
	 * 绑定机柜发送优惠券
	 * 
	 * @param sysCode
	 */
	public void sendCouponOfBindCabinet(Long uid, String currency, String sysCode) {

		if (StringUtils.isEmpty(sysCode)) {
			return;
		}

		List<PromotionBO> prolist = promotionDao.findHaveInHand(PromotionConstants.PROMOTION_TYPE_BIND_CABINET, null);

		if (null != prolist && prolist.size() > 0) {

			for (PromotionBO e : prolist) {
				CouponBO coupon = couponDao.findOne(e.getCouponId());
				if (null == coupon) {
					continue;
				}

				if (currency.equals(coupon.getCurrency())) {
					// 发放优惠券
					sendCoupon(uid, coupon, PromotionConstants.PROMOTION_TYPE_BIND_CABINET, e.getId());
					break;
				}
			}
		}

	}

	public void sendCoupon(Long uid, CouponBO coupon, String source, Long pId) {

		UserCoupon userCoupon = new UserCoupon();
		userCoupon.setAmount(coupon.getAmount());
		Calendar date = Calendar.getInstance();

		date.add(Calendar.DAY_OF_MONTH, coupon.getDayLimit());
		date.set(Calendar.HOUR_OF_DAY, 23);
		date.set(Calendar.MINUTE, 59);
		date.set(Calendar.SECOND, 59);

		userCoupon.setExpireDate(date.getTime());
		userCoupon.setUid(uid);
		userCoupon.setCityId(CommonConstants.AREA_1);
		userCoupon.setCouponId(coupon.getId());
		userCoupon.setStatus(CouponConstants.USER_COUPON_STATUS_1);
		userCoupon.setSource(source);
		userCoupon.setPid(pId);
		userCoupon.setCurrency(coupon.getCurrency());
		userCouponDao.save(userCoupon);
	}

	@Transactional(readOnly = false)
	public Long createWeixinUser(String openId, String host) {

		Long userId = null;
		UserAccountBO userAccount = accountDao.findByLoginName(openId, CommonConstants.ACCOUNT_TYPE_3);
		if (null != userAccount) {
			userId = userAccount.getUid();
		} else {
			userId = systemService.createUser(CommonConstants.ACCOUNT_TYPE_3, openId, UUID.randomUUID().toString(),
					host, null);

			MemberBO member = new MemberBO();
			member.setUid(userId);
			member.setIsDeleted(false);
			member.setStatus(CommonConstants.MEMBER_STATUS_ZC);
			member.setUpdateTime(new Date());
			member.setCreateTime(new Date());
			memberDao.save(member);
		}
		return userId;

	}

	/**
	 *
	 * @Title: updateMemberMobileById @Description: TODO(修改普通用户手机号码) @param @param
	 * uid @param @param mobile @param @return 设定文件 @author Tang @throws
	 */
	@Transactional(readOnly = false)
	public void updateMemberMobileById(Long uid, String mobile) {
		accountService.updateLoginName(uid, mobile, CommonConstants.ACCOUNT_TYPE_2);
		MemberBO member = new MemberBO();
		member.setUid(uid);
		member.setMobile(mobile);
		member.setUpdateTime(new Date());
		;
		memberDao.save(member);
	}

	/**
	 *
	 * @Title: updateMemberBaseInfo @Description: TODO(更新会员信息) @param @param member
	 * 设定文件 @return void 返回类型 @author Tang @throws
	 */
	@Transactional(readOnly = false)
	public void updateMemberBaseInfo(MemberBO member) {
		memberDao.update(member);
	}

	/**
	 * 实名认证修改用户信息
	 * 
	 * @param uid               用户id
	 * @param name              用户姓名
	 * @param idcard            用户身份证号
	 * @param schoolName        学校名称
	 * @param studentId         学生号
	 * @param credentialsImages 证件图片
	 */
	@Transactional(readOnly = false)
	public void realnameUpdateMember(Long uid, String name, String idcard, String schoolName, String studentId,
			List<String> credentialsImages) {

		SysSetting setting = sysSettingDao.findOne();

		MemberBO member = memberDao.findOne(uid);

		member.setUid(uid);
		member.setName(name);
		member.setIdCard(idcard);
		member.setSchoolName(schoolName);
		member.setStudentId(studentId);

		if (credentialsImages != null) {
			member.setCredentialsImages(JSON.toJSONString(credentialsImages));
		}
		if (setting.getIsNeedAuthenCheck()) {
			member.setAuthApplyStatus(CommonConstants.MEMBER_AUTH_APPLY_STATUS_ING);
		} else {
			member.setAuthStatus(CommonConstants.MEMBER_AUTH_STATUS_SUC);
			member.setAuthStep(CommonConstants.MEMBER_AUTH_STEP_4);
		}
		if (StringUtils.isEmpty(member.getInviteCode())) {
			member.setInviteCode(uniqueRadomCodeService.getCode(CommonConstants.CODE_TYPE_INVITION));
		}

		memberDao.update(member);

		UserRegistEvent userRegistEvent = new UserRegistEvent();
		userRegistEvent.setUid(member.getUid());
		publisher.publishEvent(userRegistEvent);

	}

	/**
	 * 实名认证
	 */
	@Transactional(readOnly = false)
	public void realname(Long uid, String name, String idcard) {

		MemberBO member = memberDao.findOne(uid);
		member.setUid(uid);
		member.setName(name);
		member.setIdCard(idcard);
		member.setAuthStatus(CommonConstants.MEMBER_AUTH_STATUS_SUC);
		member.setAuthStep(CommonConstants.MEMBER_AUTH_STEP_4);
		if (StringUtils.isEmpty(member.getInviteCode())) {
			member.setInviteCode(uniqueRadomCodeService.getCode(CommonConstants.CODE_TYPE_INVITION));
		}
		memberDao.update(member);
		// 人员统计
		UserRegistEvent userRegistEvent = new UserRegistEvent();
		userRegistEvent.setUid(member.getUid());
		publisher.publishEvent(userRegistEvent);
	}

	/**
	 * 绑定手机号
	 * 
	 * @param mobile
	 * @return 手机号已存在返回第三方的account,否则返回新建的手机Account
	 */
	@Transactional(readOnly = false, rollbackFor = ServiceException.class)
	public UserAccount thirdPartyBoundModile(Long uid, MobileBO mobile) {
		return thirdPartyBoundModile(uid, mobile, null, null, null);
	}

	public void boundCheck(Long uid) {
		UserAccountBO account = accountService.findByUid(uid, CommonConstants.ACCOUNT_TYPE_2);
		if(null != account) {
			throw new ServiceException("You have bound the phone number");
		}
	}
	
	/**
	 * 绑定手机号
	 * 
	 * @param mobile
	 * @return 手机号已存在返回第三方的account,否则返回新建的手机Account
	 */
	@Transactional(readOnly = false, rollbackFor = ServiceException.class)
	public UserAccount thirdPartyBoundModile(Long uid, MobileBO mobile, String password, String ip,
			String clientSource) {
		
		//绑定校验
		boundCheck(uid);
		
		List<UserAccountBO> accounts = accountDao.findByUid(uid);
		
		// 处理手机是否已经注册过
		UserAccountBO mobileAcc = accountService.findByLoginName(mobile.getFullMobile());
		
		if(null != mobileAcc) {
			// 更新uid
			UserAccount up_account = new UserAccount();
			up_account.setId(accounts.get(0).getId());
			up_account.setUid(mobileAcc.getUid());
			accountDao.save(up_account);
			return accounts.get(0);
		}
		
		// 手机号未注册
		UserAccount account = new UserAccount();
		account.setLoginName(mobile.getFullMobile());
		account.setPassword(PasswordUtil.entryptPassword(password));
		account.setAccountType(CommonConstants.ACCOUNT_TYPE_2);
		account.setIsDeleted(false);
		account.setUid(uid);
		accountDao.save(account);
		
		// 完善用户的手机号信息
		MemberBO member = new MemberBO();
		member.setUid(uid);
		member.setMobile(mobile.getFullMobile());
		member.setAuthStatus(CommonConstants.MEMBER_AUTH_STATUS_ING);
		member.setAuthStep(CommonConstants.MEMBER_AUTH_STEP_2);
		member.setClientSource(clientSource);
		member.setRegisteredIp(ip);
		memberDao.save(member);
		
		return account;
		
	}

	/**
	 * 完善用户手机认识信息
	 * 
	 * @param uid
	 * @param mobile
	 * @param clientSource
	 * @param ip
	 */
//	private MemberBO completeMobileAuth(Long uid, MobileBO mobile, 
//			String clientSource, String ip) {
//		MemberBO member = memberDao.findOne(uid);
//		member.setAuthStatus(CommonConstants.MEMBER_AUTH_STATUS_ING);
//		member.setAuthStep(CommonConstants.MEMBER_AUTH_STEP_2);
//		member.setClientSource(clientSource);
//		member.setRegisteredIp(ip);
//		member.setMobile(mobile.getFullMobile());
//		member.setCountry(mobile.getCountry().getCode());
//		member.setCurrency(mobile.getCountry().getCurrencyCode());
//
//		if (StringUtils.isEmpty(member.getInviteCode())) {
//			member.setInviteCode(uniqueRadomCodeService.getCode(CommonConstants.CODE_TYPE_INVITION));
//		}
//		memberDao.save(member);
//
//		// 查找活动，增加优惠劵
//		List<PromotionBO> promotionList = promotionDao.findHaveInHand(PromotionConstants.PROMOTION_TYPE_REGIST_CODE,
//				null);
//		for (PromotionBO promotion : promotionList) {
//			CouponBO coupon = couponDao.findOne(promotion.getCouponId());
//			if (coupon != null) {
//				UserCoupon userCoupon = new UserCoupon();
//				userCoupon.setAmount(coupon.getAmount());
//				Calendar date = Calendar.getInstance();
//
//				date.add(Calendar.DAY_OF_MONTH, coupon.getDayLimit());
//				date.set(Calendar.HOUR_OF_DAY, 23);
//				date.set(Calendar.MINUTE, 59);
//				date.set(Calendar.SECOND, 59);
//
//				userCoupon.setExpireDate(date.getTime());
//				userCoupon.setUid(uid);
//				userCoupon.setCityId(CommonConstants.AREA_1);
//				userCoupon.setCouponId(coupon.getId());
//				userCoupon.setStatus(CouponConstants.USER_COUPON_STATUS_1);
//				userCoupon.setSource(promotion.getProName());
//				userCoupon.setPid(promotion.getId());
//				userCouponDao.save(userCoupon);
//			}
//		}
//		// 人员统计
////		UserRegistEvent userRegistEvent=new UserRegistEvent();
////		userRegistEvent.setUid(member.getUid());
////		publisher.publishEvent(userRegistEvent);
//
//		return member;
//	}
	
	/**
	 * 用户退押金校验
	 * 
	 * @param uid
	 */
	public void validMemberRefund(Long uid) {

		MemberBO member = this.findByUid(uid);
		if (null == member.getDeposit() || member.getDeposit().compareTo(BigDecimal.ZERO) == 0) {
			throw new ServiceException("没有押金可退");
		}
//		List<ReserveBO> list = reserveDao.findByUid(uid, CommonConstants.RESERVE_STATUS_1);
//		if (list != null && list.size() > 0) {
//			throw new ReserveExistException("有预约的车辆");
//		}
		if (orderDao.countByStatusList(uid, Lists.newArrayList(OrderConstants.ORDER_STATUS_10,
				OrderConstants.ORDER_STATUS_30, OrderConstants.ORDER_STATUS_40)) > 0) {
			throw new ServiceException("You have an unfinished order, can't refund");
		}
	}
	
	/**
	 * 将用户押金值置空
	 * 
	 * @param uid
	 */
	@Transactional(readOnly = false)
	public void updateMemberDepositNull(Long uid) {

		MemberBO member = new MemberBO();
		member.setUid(uid);
		member.setDeposit(BigDecimal.ZERO);

		memberDao.update(member);
	}

	
	@Transactional(readOnly = false)
	public void refundDeposit(Long uid) {
		
		// 校验用户
		validMemberRefund(uid);

		// 将用户的押金值置空
		updateMemberDepositNull(uid);

		// 查询用户支付成功未退款的交易
		List<DepositPayLogBO> pay_list = depositPayLogDao.findPaySuccAndNoRefundByUid(uid);

		if (null == pay_list || pay_list.isEmpty()) {
			throw new ServiceException("407", "未查询到支付信息");
		}

		pay_list.forEach(e -> {

			DepositPayLogBO up_info = new DepositPayLogBO();
			up_info.setId(e.getId());
			up_info.setRefundApplyTime(new Date());
			up_info.setStatus(CommonConstants.DEPOSIT_PAY_STATUS_3);

			depositPayLogDao.update(up_info);

			UserDepositLogBO log = new UserDepositLogBO();
			log.setAmount(e.getAmount());
			log.setUid(uid);
			log.setCreateTime(new Date());
			log.setType(CommonConstants.MEMBER_DEPOSIT_TYPE_2);
			log.setOutRefundNo(e.getOutOrderId());
			log.setPayMark(e.getPaymentMark());
			userDepositLogDao.save(log);

		});

		SysSetting sysSet = sysSettingDao.findOne();

		if (null !=  sysSet.getIsAutomaticRefund() && sysSet.getIsAutomaticRefund()) {
			pay_list.forEach(e -> {
				RefundResponse response = refund(e, 1L, "系统自动退款");
				if (!response.isSuccess()) {
					throw new ServiceException(response.getMsg());
				}
			});
		}
	}

	@Transactional(readOnly = false)
	public RefundResponse refund(DepositPayLogBO payLog, long uid, String refundMark) {
		
		// 拼接退款请求
		RefundRequest request = new RefundRequest();
		// 支付宝或者微信商户号
		request.setTransaction_id(payLog.getOutOrderId());
		// 支付方式
		request.setConfigMark(payLog.getPaymentMark());
		// 商户退款单号
		request.setOut_refund_no(DateTimeUtils.getDateTimeMils());

		// 退款金额设置
		switch (payLog.getPaymentMark()) {
		case PaymentConstants.PAYMENT_MARK_ALIPAY_APP:
			// 支付宝 单位为元
			request.setRefund_amount(payLog.getAmount());
			break;
		case PaymentConstants.PAYMENT_MARK_CASHFREE:
			log.info("退款金额:[{}]", payLog.getAmount());
			request.setRefund_amount(payLog.getAmount());
			request.setOut_refund_no(payLog.getOutOrderId());
			break;
		default:
			// 微信 单位为分
			BigDecimal total_fee = payLog.getAmount().multiply(new BigDecimal(100));
			request.setTotal_fee(total_fee.intValue());
			request.setRefund_fee(total_fee.intValue());
			request.setOut_refund_no(payLog.getPayOrderCode());
			request.setCurrency(payLog.getCurrency());
			break;
		}
		
		try {
			
			@SuppressWarnings("rawtypes")
			PayHandler payHandler = payHandlerFactory.getPayHandler(payLog.getPaymentMark());
			RefundResponse response = payHandler.refund(request);
			if(response.isSuccess()) {
				// 修改用户的支付记录
				updatePayLogRefunded(payLog.getId(), uid, refundMark, response.getOutOrderId());
				// 修改用户的订单记录
				updateDepositLog(payLog.getOutOrderId());
				// 退款统计
				refundEvent(payLog.getId());
			}
			
			return response;

		} catch (Exception e) {
			log.error("订单号[{}]退款异常,异常信息[{}]",payLog.getPayOrderCode(),e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		
	}

	private void refundEvent(Long id) {
		
		UserDepositRefundEvent depositRefundEvent = new UserDepositRefundEvent();
		depositRefundEvent.setDepositPayId(id);
		publisher.publishEvent(depositRefundEvent);
	}

	/**
	 * 更新用户的押金记录
	 * 
	 * @param outOrderId
	 */
	@Transactional(readOnly = false)
	public void updateDepositLog(String outOrderId) {

		UserDepositLogBO userDeposit = userDepositLogDao.findbyOutRefundNo(outOrderId, CommonConstants.MEMBER_DEPOSIT_TYPE_2);
		UserDepositLogBO up_bo = new UserDepositLogBO();
		up_bo.setId(userDeposit.getId());
		up_bo.setType(CommonConstants.MEMBER_DEPOSIT_TYPE_3);
		userDepositLogDao.update(up_bo);
	}

	/**
	 * 退款成功更新支付记录
	 * 
	 * @param id         退款记录id
	 * @param uid        退款操作人
	 * @param refundMark 备注信息
	 */
	@Transactional(readOnly = false)
	public void updatePayLogRefunded(Long id, Long uid, String refundMark, String refundId) {

		DepositPayLogBO up_log = new DepositPayLogBO();
		up_log.setId(id);
		up_log.setRefundRemark(refundMark);
		up_log.setRefundSuccTime(new Date());
		up_log.setRefundOperator(uid);
		up_log.setRefundId(refundId);
		up_log.setStatus(CommonConstants.DEPOSIT_PAY_STATUS_4);

		depositPayLogDao.update(up_log);
	}

	/**
	 * 绑定邀请人
	 * 
	 * @param uid
	 * @param inviteCode
	 */
	@Transactional(readOnly = false)
	public void boundInviteCode(Long uid, String inviteCode) {
		// 邀请人
		MemberBO fromMember = memberDao.findByInviteCode(inviteCode);
		if (fromMember == null || !CommonConstants.MEMBER_STATUS_ZC.equals(fromMember.getStatus())) {
			throw new ServiceException("您输入的邀请码不存在");
		}

		// 受邀人
		MemberBO toMember = memberDao.findOne(uid);
		if (StringUtils.isNotBlank(toMember.getInvitedInviteCode())) {
			throw new ServiceException("您已经绑定过邀请码");
		}
		toMember.setInvitedInviteCode(inviteCode);
		memberDao.save(toMember);

		// 查找活动，增加优惠劵
		List<PromotionBO> promotionList = promotionDao.findHaveInHand(PromotionConstants.PROMOTION_TYPE_INVITE_CODE,
				null);
		for (PromotionBO promotion : promotionList) {
			CouponBO coupon = couponDao.findOne(promotion.getCouponId());
			if (coupon != null) {
				UserCoupon userCoupon = new UserCoupon();
				userCoupon.setAmount(coupon.getAmount());
				Calendar date = Calendar.getInstance();

				date.add(Calendar.DAY_OF_MONTH, coupon.getDayLimit());
				date.set(Calendar.HOUR_OF_DAY, 23);
				date.set(Calendar.MINUTE, 59);
				date.set(Calendar.SECOND, 59);

				userCoupon.setExpireDate(date.getTime());
				userCoupon.setUid(uid);
				userCoupon.setCityId(CommonConstants.AREA_1);
				userCoupon.setCouponId(coupon.getId());
				userCoupon.setStatus(CouponConstants.USER_COUPON_STATUS_1);
				userCoupon.setSource(promotion.getProName());
				userCoupon.setPid(promotion.getId());
				userCouponDao.save(userCoupon);
				userCoupon.setId(null);
				userCoupon.setUid(fromMember.getUid());
				// msgTools.sendTemplateSms("sms_coupon", fromMember.getMobile(), "127.0.0.1",
				// "{amount:\""+coupon.getAmount()+"\",day:\""+coupon.getDayLimit()+"\"}",
				// "小毛驴单车");
				userCouponDao.save(userCoupon);
			}
		}
	}

	/**
	 * 根据币种查询押金值
	 * 
	 * @param currency 币种
	 * @return
	 */
	public BigDecimal queryDetpsit(String currency) {

		if(StringUtils.isBlank(currency)) {
			throw new PlatNotConfigDepositException();
		}
		
		CountryBO country = countryDao.findByCurrency(currency);

		if (country == null) {
			throw new ServiceException("系统不支持的币种");
		}

		return country.getDeposit();
	}

	public BigDecimal findSysBasicCost(Long uid) {
		
		MemberBO member = memberDao.findOne(uid);
		if (StringUtils.isEmpty(member.getCurrency())) {
			throw new UserCurrencyNotPerfectException();
		}
		List<CountryBO> countries = countryDao.findAll();
		for (CountryBO country : countries) {
			if (country.getCurrency().equals(member.getCurrency())) {
				return country.getDeposit();
			}
		}

//		switch (member.getCurrency()) {
//		case "CNY":
//			return setting.getBasicCostRMB();
//		case "SGD":
//			return setting.getBasicCostSingapore();
//		case "MYR":
//			return setting.getBasicCostMalaysia();
//		default:
		throw new ServiceException("系统不支持的币种");
//		}
	}

	private static final Hashids snHashId = new Hashids("radish-saas-2.0.0 member sn mnbvcxzlkjhgfdsa", 4,
			"12345678900123456789abcdef");

	/**
	 * 产生工单号
	 * 
	 * @return
	 */
	public static String getSn(Long id) {
		// 组件id,为2位
		String cidStr = snHashId.encode(id);
		if (cidStr.length() > 4) {
			cidStr = cidStr.substring(0, 4);
		}
		return DateFormatUtils.format(new Date(), "yMMddHHmmss") + cidStr;
	}

	public static class MyDirectPayResponse {
		private DirectPayResponse directPayResponse;

		private String sn;

		public MyDirectPayResponse(DirectPayResponse directPayResponse, String sn) {
			super();
			this.directPayResponse = directPayResponse;
			this.sn = sn;
		}

		public DirectPayResponse getDirectPayResponse() {
			return directPayResponse;
		}

		public void setDirectPayResponse(DirectPayResponse directPayResponse) {
			this.directPayResponse = directPayResponse;
		}

		public String getSn() {
			return sn;
		}

		public void setSn(String sn) {
			this.sn = sn;
		}
	}

	/**
	 * 查询所有用户信息（只查询 uid，用户名，昵称，手机号）
	 * 
	 * @param isVip true 查询所有的vip 会员 false 查询所有的 普通会员
	 * @return
	 */
	public List<MemberBO> findAllVipOrNotVip(boolean isVip) {
		return memberDao.findAllVipOrNotVip(isVip);
	}

	/**
	 * 微信 sns 获取用户信息 @Title: getUserBaseInfo @Description:
	 * TODO(查询用户基本信息) @param @param loginName @param @param
	 * wxAuthAccessToken @param @return 设定文件 @return User 返回类型 @author Tang @throws
	 */
	private User getWeixinUserBaseInfo(String loginName, String wxAuthAccessToken) {
		User user = SnsAPI.userinfo(wxAuthAccessToken, loginName, "zh_CN"); // UserAPI 修改为 SnsAPI by james
		if (user.isSuccess()) {
			String headImgUrl = user.getHeadimgurl();
			try {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				headImgUrl = headImgUrl.replace("https", "http");
				HttpRequest.get(headImgUrl).receive(bos);
				RequestResult result = imageFsClient.upload(bos.toByteArray(), System.currentTimeMillis() + "_WX",
						"image/jpeg");
				user.setHeadimgurl(result.getUrl());
			} catch (HttpRequestException e) {
				log.error("下载微信头像出错:{}", e);
			}

			log.info("注册微信查询用户基本信息：openId: [{}]，用户信息：[{}]", loginName, JSON.toJSONString(user));
			return user;
		}
		throw new ServiceException("微信获取用户信息出错");
	}

	/**
	 * 得到fasebook的用户信息
	 * 
	 * @param openid
	 * @param accessToken
	 * @return
	 */
	private User getFasebook(String openid, String accessToken) {
		FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Version.LATEST);
		com.restfb.types.User fbUser = facebookClient.fetchObject("me", com.restfb.types.User.class,
				Parameter.with("fields", "id,name,cover,gender"));
		User result = new User();
		result.setNickname(fbUser.getName());
		String headImgUrl = null;
		if (fbUser.getCover() != null) {
			headImgUrl = fbUser.getCover().getSource();
		}
		if (StringUtils.isNotBlank(headImgUrl)) {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			HttpRequest.get(headImgUrl).receive(bos);
			RequestResult imageResult = imageFsClient.upload(bos.toByteArray(),
					System.currentTimeMillis() + "_fasebook", "image/jpeg");
			result.setHeadimgurl(imageResult.getUrl());
		}
		if ("男".equals(fbUser.getGender())) {
			result.setSex(1);
		} else if ("女".equals(fbUser.getGender())) {
			result.setSex(2);
		}

		return result;
	}

	/**
	 * 得到qq用户信息
	 * 
	 * @param openId
	 * @param accessToken
	 * @return
	 */
	public User getQQUserInfo(String openId, String accessToken) {

		SysSetting setting = sysSettingDao.findOne();

		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri("https://graph.qq.com/user/get_user_info?access_token=" + accessToken + "&openid=" + openId
						+ "&oauth_consumer_key=" + setting.getQqAppId() + "&format=json")
				.build();
		try {
			CloseableHttpResponse resp = LocalHttpClient.execute(httpUriRequest);
			String entity = EntityUtils.toString(resp.getEntity());
			QQUserInfo userInfo = JsonMapper.getInstance().fromJson(entity, QQUserInfo.class);
			if (userInfo.getRet() != 0) {
				throw new ServiceException("得到QQ用户信息失败,错误[" + userInfo.getRet() + "],msg[" + userInfo.getMsg() + "]");
			}

			String headImgUrl = userInfo.getFigureurl_qq_2();

			User user = new User();
			user.setNickname(userInfo.getNickname());
			if (StringUtils.isNotBlank(headImgUrl)) {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				HttpRequest.get(headImgUrl).receive(bos);
				RequestResult result = imageFsClient.upload(bos.toByteArray(), System.currentTimeMillis() + "_WX",
						"image/jpeg");
				user.setHeadimgurl(result.getUrl());
			}
			if ("男".equals(userInfo.getGender())) {
				user.setSex(1);
			} else if ("女".equals(userInfo.getGender())) {
				user.setSex(2);
			}
			return user;
		} catch (Exception e) {
			log.error("得到QQ用户信息失败", e);
			throw new ServiceException("得到QQ用户信息失败");
		}
	}

	/**
	 * 分页查询客户信息（联合多表查询）
	 * 
	 * @param page 分页参数
	 * @param vo   客户信息相关参数
	 * @return
	 */
	public List<MemberBO> findUnionInfoByPage(MyPage<MemberBO> page, MemberVO vo) {

		List<MemberBO> usList = memberDao.findHaveNicknameAndMobile(page, vo);

		Boolean isOpenCard = false;
		SysSetting sysSet = sysSettingDao.findOne();
		if (null != sysSet && null != sysSet.getIsOpenMemberCard() && sysSet.getIsOpenMemberCard()) {
			isOpenCard = true;
		}

		if (isOpenCard) {
			usList.forEach(e -> {
				e.setIsVIP(null != e.getVipExpiresIn() && e.getVipExpiresIn().before(new Date()));
			});
		}

		return usList;
	}

	/**
	 * 停用或者启用
	 * 
	 * @param status
	 * @param uid
	 */
	@Transactional(readOnly = false, rollbackFor = ServiceException.class)
	public void enableOrDisable(String status, Long uid) {
		MemberBO up_bo = new MemberBO();
		up_bo.setUid(uid);
		// 设置用户状态
		if (CommonConstants.MEMBER_STATUS_ZC.equals(status)) {
			up_bo.setStatus(CommonConstants.MEMBER_STATUS_JY);
		}
		if (CommonConstants.MEMBER_STATUS_JY.equals(status)) {
			up_bo.setStatus(CommonConstants.MEMBER_STATUS_ZC);
		}

		up_bo.setUpdateTime(new Date());

		memberDao.save(up_bo);
	}

	/**
	 * 主键查询
	 * 
	 * @param uid
	 * @return
	 */
	public MemberBO findOne(Long uid) {
		return memberDao.findOne(uid);
	}

	/**
	 * 平台后台创建用户
	 * 
	 * @param user
	 * @param bo
	 */
	public void adminCreateUser(vc.thinker.sys.model.User user, MemberBO bo) {
		MobileBO mobile = new MobileBO(bo.getMobile());
		createMemeber(mobile, bo.getNickname(), "123456", null, "admin", null);
	}

	/**
	 * 校验工号是否已经存在
	 * 
	 * @param uid
	 * @param idCard
	 * @return
	 */
	public Boolean checkIdCard(Long uid, String idCard) {
		if (null != uid) {
			// 修改
			MemberBO member = memberDao.findOne(uid);
			if (!member.getIdCard().equals(idCard)) {
				if (isExit(null, idCard, null, null, null)) {
					return false;
				}
			}
		} else {
			// 新增
			if (isExit(null, idCard, null, null, null)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 校验
	 * 
	 * @param uid
	 * @param nickname
	 * @return
	 */
	public Boolean checkNickname(Long uid, String nickname) {

		if (null != uid) {
			MemberBO member = memberDao.findOne(uid);
			if (!member.getNickname().equals(nickname)) {
				if (isExit(null, null, nickname, null, null)) {
					return false;
				}
			}
		} else {
			if (isExit(null, null, nickname, null, null)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 校验手机号是否已经存在
	 * 
	 * @param uid
	 * @param mobile
	 * @return
	 */
	public Boolean checkMobile(Long uid, String mobile) {

		if (null != uid) {
			MemberBO member = memberDao.findOne(uid);
			if (!member.getMobile().equals(mobile)) {
				if (isExit(null, null, null, mobile, null)) {
					return false;
				}
			}
		} else {
			if (isExit(null, null, null, mobile, null)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 工号校验
	 * 
	 * @param uid       用户id
	 * @param jobNumber 工号
	 * @return
	 */
	public Boolean checkJobNumber(Long uid, String jobNumber) {
		if (null != uid) {
			MemberBO member = memberDao.findOne(uid);
			if (!member.getJobNumber().equals(jobNumber)) {
				if (isExit(null, null, null, null, jobNumber)) {
					return false;
				}
			}
		} else {
			if (isExit(null, null, null, null, jobNumber)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 校验用户是否已经存在
	 * 
	 * @param uid      用户的uid
	 * @param idCard   工号
	 * @param nickname 昵称
	 * @param mobile   手机号
	 * @return
	 */
	private Boolean isExit(Long uid, String idCard, String nickname, String mobile, String jobNumber) {

		MemberExample example = new MemberExample();
		Criteria c = example.createCriteria().andIsDeletedEqualTo(false);
		if (null != uid) {
			c.andUidEqualTo(uid);
		}
		if (!StringUtils.isEmpty(idCard)) {
			c.andIdCardEqualTo(idCard);
		}
		if (!StringUtils.isEmpty(nickname)) {
			c.andNicknameEqualTo(nickname);
		}
		if (!StringUtils.isEmpty(mobile)) {
			c.andMobileEqualTo(mobile);
		}
		if (!StringUtils.isEmpty(jobNumber)) {
			c.andJobNumberEqualTo(jobNumber);
		}

		if (memberDao.countByExample(example) > 0) {
			return true;
		}

		return false;
	}

	/**
	 * 审核
	 * 
	 * @param bo
	 */
	public void adminCheckMemer(MemberBO bo) {
		MemberBO up_bo = new MemberBO();

		up_bo.setUid(bo.getUid());
		up_bo.setAuthApplyRemark(bo.getAuthApplyRemark());
		up_bo.setAuthApplyStatus(bo.getAuthApplyStatus());
		up_bo.setUpdateTime(new Date());
		String content = "";
		if (CommonConstants.MEMBER_AUTH_APPLY_STATUS_SUC == bo.getAuthApplyStatus()) {
			up_bo.setAuthStep(CommonConstants.MEMBER_AUTH_STEP_4);
			up_bo.setAuthStatus(CommonConstants.MEMBER_AUTH_STATUS_SUC);
			content = "您的实名认证申请已经通过，请登录app查看。";
		} else {
			content = "您的实名认证申请已经被拒，请及时登录app查看原因。";
		}

		memberDao.update(up_bo);

		MemberMessageEvent event = new MemberMessageEvent();
		event.setUid(bo.getUid());
		event.setMsgType(SysMessageConstants.MOBILE_MESSAGE_UM_MEMBER_AUTH_APPLY);
		event.setContent(content);
		publisher.publishEvent(event);
	}

	public List<MemberBO> findCheckListByPageAndVo(MyPage<MemberBO> page, MemberVO vo) {
		return memberDao.findCheckListByPageAndVo(page, vo);
	}

	public List<MemberBO> findByUids(List<Long> uidList) {
		return memberDao.findByUids(uidList);
	}

	public List<MemberBO> findAllOnlyUid() {
		return memberDao.findAllOnlyUid();
	}


	/**
	 * 充值流水记录查询
	 * 
	 * @param page 分页参数
	 * @param vo   查询参数
	 */
	public List<DepositPayLogBO> findloglist(MyPage<DepositPayLogBO> page, DepositPayLogVO vo) {
		try {

			if (!StringUtils.isEmpty(vo.getStartTime())) {
				vo.setLtTime(DateTimeUtils.getAppointDateMonth(vo.getStartTime()));
			}
			if (!StringUtils.isEmpty(vo.getEndTime())) {
				vo.setGtTime(DateTimeUtils.getAppoint59SecondDate(vo.getEndTime()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return depositPayLogDao.findLogListByVo(page, vo);
	}

	public long count() {
		return memberDao.count();
	}

	/**
	 * 缴纳基础会员费
	 * 
	 * @param uid
	 * @param payCallback
	 * @param paymentMark
	 * @return
	 */
	@Transactional(readOnly = false)
	public DirectPayResponse createBasicCostPay(Long uid, String payCallback, String paymentMark) {

		checkPaymentMark(paymentMark);

		MemberBO member = memberDao.findOne(uid);

		if (member == null) {
			throw new ServiceException("用户不存在");
		}

		if (null == member.getDeposit() || member.getDeposit().compareTo(BigDecimal.ZERO) <= 0) {
			throw new DepositNotPayException();
		}

		if (null != member.getIsPayBasicCost() && member.getIsPayBasicCost()) {
			throw new ServiceException("用户已经缴纳基础会员费");
		}

//		String currency = CountryUtil.get(member.getCountry()).getCurrencyCode();

		SysSetting sys = sysSettingDao.findOne();
		
		String currency = sys.getPlayformDefaultCurrency();

//		PayHandler payHandler = payHandlerFactory.getPayHandler(paymentMark);

		DirectPayRequest payRequest = new DirectPayRequest();

		BigDecimal totalPrice = null;
		// 获取系统押金
		List<CountryBO> countries = countryDao.findAll();
		for (CountryBO country : countries) {
			if (country.getCurrency().equals(currency)) {
				totalPrice = country.getDeposit();
				break;
			}
		}

		payRequest.setTotalFee(PayTotalPriceUtil.getTotalPrice(totalPrice));
		payRequest.setConfigMark(paymentMark);
		payRequest.setCapture(false);

		String payOrderCode = payHelpService.getPayOrderCode(CommonConstants.PAY_TYPE_BASIC_COST, uid);

		// 保存支付信息
		UserBasicCostPayLog log = new UserBasicCostPayLog();
		log.setUid(uid);
		log.setPayOrderCode(payOrderCode);
		log.setPaymentMark(paymentMark);
		log.setAmount(new BigDecimal(String.valueOf(totalPrice)));
		log.setCurrency(currency);
		log.setStatus(CommonConstants.BASIC_COST_PAY_1);
		log.setCreateTime(new Date());

		userBasicCostPayLogDao.save(log);

		String appName = !StringUtils.isEmpty(sys.getAppName()) ? sys.getAppName() : "彩虹单车";

		payRequest.setBody("Basic membership fee:" + appName);

		String notifyUrl = payHelpService.getNotifyUrl(paymentMark, payCallback);
		payRequest.setNotifyUrl(notifyUrl);
//		payRequest.setReturnUrl(returnUrl);
		payRequest.setCurrency(currency);
	 
		// strpe 已token为 outTradeNo
		if (PaymentConstants.PAYMENT_MARK_STRIPE.equals(paymentMark)) {
			payRequest.setOutTradeNo(member.getStripeCustomerId());
		} else {
			payRequest.setOutTradeNo(payOrderCode);
		}

		// 设置附加属性为支付类型
		payRequest.setReqAttach(CommonConstants.PAY_TYPE_BASIC_COST);// 基础会员费
		payRequest.setSubject("Basic membership fee:" + appName);
		payRequest.setCurrency(currency);
		payRequest.setCapture(false);
		try {

			DirectPayResponse payResponse = 
					payHelpService.getPayResponse(paymentMark, uid, payRequest);
			
			if(null == payResponse) {
				throw new ServiceException("调用支付失败");
			}
			if(payResponse.isSuccess() && PayChannel.STRIPE == payResponse.getChannel()) {
				completeDepositPay(payOrderCode, payResponse.getPayOrderId(), payResponse.getTotalFee());
			}
			
			if(payResponse.isSuccess() && PayChannel.CASHFREE == payResponse.getChannel()) {
				payResponse.setOrderCurrency(currency);
				payResponse.setNotifyUrl(notifyUrl);
				payResponse.setOrderAmount(String.valueOf(payRequest.getTotalFee()));
				payResponse.setOrderId(payOrderCode);
				payResponse.setCustomerPhone(member.getMobile());
			}

			return payResponse;
			
		} catch (PayException e) {
			throw new RuntimeException("调用支付失败 出现异常", e);
		}
	}

	/**
	 * 完成基础会员费用的缴纳
	 * 
	 * @param payOrderCode   内部订单号
	 * @param transaction_id 外部订单号
	 * @param cashFee        实际支付的金额
	 */
	/**
	 * @param payOrderCode
	 * @param transaction_id
	 * @param cashFee
	 */
	@Transactional(readOnly = false)
	public void completeBasicCostPay(String payOrderCode, String transaction_id, BigDecimal cashFee) {

		UserBasicCostPayLogBO info = userBasicCostPayLogDao.findByPayOrderCode(payOrderCode);

		if (null == info) {
			throw new ServiceException("订单不存在，完成支付失败");
		}

		if (info.getStatus() >= CommonConstants.BASIC_COST_PAY_2) {
			throw new DepositException("订单已经完成支付,请误重复支付");
		}

		// 更新支付记录
		UserBasicCostPayLog log = new UserBasicCostPayLog();
		log.setId(info.getId());
		log.setOutOrderId(transaction_id);
		log.setStatus(CommonConstants.BASIC_COST_PAY_2);
		log.setCashFee(cashFee);

		userBasicCostPayLogDao.update(log);

		// 更新用户的支付信息
		Member member = new Member();
		member.setUid(info.getUid());
		member.setIsPayBasicCost(true);
		memberDao.update(member);

		// 反润
		moneyService.rebate(cashFee, info.getCurrency(), info.getUid(), log.getId(),
				CommonConstants.PLATFORM_REVENUE_LOG_BASIC_VIP, "base vip");

	}

	/**
	 * 完成雨伞的购买
	 * 
	 * @param uid
	 */
//	@Transactional(readOnly = false)
//	public void completePbPay(Long uid) {
//		
//		MemberBO member = memberDao.findOne(uid);
//		
//		if(member.getDeposit().compareTo(BigDecimal.ZERO)<=0){
//			throw new DepositNotPayException();
//		}
//		
//		List<OrderBO> list = orderDao.findByStatus(uid, OrderConstants.ORDER_STATUS_30);
//		if(list.isEmpty()){
//			throw new NoOngoingOrderException();
//		}
//		
//		//结束订单的时候
//		OrderBO up_order = new OrderBO();
//		up_order.setId(list.get(0).getId());
//		up_order.setPayType(OrderConstants.PAY_TYPE_PB_BUY);
//		up_order.setStatus(OrderConstants.ORDER_STATUS_50);
//		up_order.setPrice(member.getDeposit());
//		up_order.setRideTime(new Double(Math.ceil((new Date().getTime() - list.get(0).getBeginTime().getTime()) / 1000 / 60D)).intValue());
//		up_order.setFinishTime(new Date());
//		up_order.setPayTime(new Date());
//		BigDecimal price = orderService.computePrice(new Date(), list.get(0));
//		up_order.setPrice(price);
//		up_order.setPayPrice(member.getDeposit());
//		orderDao.update(up_order);
//		
//		//修改充电宝的状态
//		PortableBattery pb = new PortableBattery();
//		pb.setId(list.get(0).getPbId());
//		pb.setLocationType(PortableBatteryConstatns.LOCATION_TYPE_BUY);
//		pb.setStatus(PortableBatteryConstatns.PB_STATUS_BUY);
//		pb.setLastLocationTime(new Date());
//		
//		portableBatteryDao.update(pb);
//		
//		//将押金支付记录修改为订单抵扣
//		DepositPayLogBO paylog = depositPayLogDao.findLastOneByUidAndStutus(uid,CommonConstants.DEPOSIT_PAY_STATUS_2);
//		DepositPayLog up_log = new DepositPayLog();
//		up_log.setId(paylog.getId());
//		up_log.setStatus(Integer.parseInt(CommonConstants.MEMBER_DEPOSIT_TYPE_4));
//		depositPayLogDao.update(up_log);
//				
//		//插入一条押金的订单抵扣记录
//		UserDepositLogBO log = new UserDepositLogBO();
//		log.setUid(uid);
//		log.setAmount(member.getDeposit());
//		log.setType(CommonConstants.MEMBER_DEPOSIT_TYPE_4);
//		log.setCurrency(member.getCurrency());
//		log.setPayMark(OrderConstants.PAY_TYPE_PB_BUY);
//		log.setCreateTime(new Date());
//		userDepositLogDao.save(log);
//		
//		//新增雨伞的购买记录
//		OrderPbBuy pb_buy = new OrderPbBuy();
//		pb_buy.setUid(uid);
//		pb_buy.setPbId(list.get(0).getPbId());
//		pb_buy.setPbCode(list.get(0).getPbCode());
//		pb_buy.setOrderId(list.get(0).getId());
//		pb_buy.setOrderCode(list.get(0).getOrderCode());
//		pb_buy.setCreateTime(new Date());
//		pb_buy.setOrderAmount(price);
//		pb_buy.setCashFee(member.getDeposit());
//		pb_buy.setCurrency(member.getCurrency());
//		
//		orderPbBuyDao.save(pb_buy);
//		
//		//将用户的押金值修改为 0
//		Member up_member = new Member();
//		up_member.setUid(uid);
//		up_member.setDeposit(BigDecimal.ZERO);
//		up_member.setUpdateTime(new Date());
//		memberDao.update(up_member);
//		
//	}

	/**
	 * 完善第三方信息
	 * 
	 * @param uid
	 * @param country
	 * @param cabinetAlias
	 */
	@Transactional(readOnly = false)
	public void thirdPartyPerfectInfo(Long uid, String countryCode, String cabinetAlias) {

		MemberBO member = memberDao.findOne(uid);

		if(StringUtils.isNotBlank(member.getCurrency())) {
			throw new CountryInfoHasExistException();
		}
	 
		String sysCode = "";
		// 校验机柜
		if (!StringUtils.isEmpty(cabinetAlias)) {
			CabinetBO cabinet = cabinetDao.findbyCabinetAlias(cabinetAlias);
			if (null == cabinet) {
				throw new CabinetNotFindException();
			}
			sysCode = cabinet.getSysCode();
		}

		Country country = CountryUtil.get(countryCode);

		if (country == null) {
			throw new CountryNotFindException();
		}

		// 修改个人信息
		MemberBO up_me = new MemberBO();
		up_me.setUid(uid);
		up_me.setCountry(countryCode);
		up_me.setCurrency(country.getCurrencyCode());
		up_me.setSysCode(sysCode);
		up_me.setUpdateTime(new Date());

		memberDao.update(up_me);

		// 修改 sys_user信息
		systemService.updateCountry(uid, country);

		// 绑定机柜，发送优惠券
		if (!StringUtils.isEmpty(sysCode)) {
			sendCouponOfBindCabinet(uid, country.getCurrencyCode(), sysCode);
		}

	}

	/**
	 * 修改用户绑定的机柜
	 * 
	 * @param uid
	 * @param cabinetAlias
	 */
	@Transactional(readOnly = false)
	public void updateBindingSysCode(Long uid, String cabinetAlias) {

		// 用户绑定机柜时，发送优惠券
		MemberBO memberBo = memberDao.findOne(uid);

		if (!StringUtils.isEmpty(memberBo.getSysCode())) {
			throw new UserHasBindedCabinetException();
		}

		CabinetBO cabient = cabinetDao.findByAliasOrSysCode(cabinetAlias);

		if (null == cabient) {
			throw new CabinetNotFindException();
		}

		// 修改用户的基本信息
		MemberBO up_bo = new MemberBO();
		up_bo.setUid(uid);
		up_bo.setSysCode(cabient.getSysCode());
		up_bo.setUpdateTime(new Date());
		updateMemberBaseInfo(up_bo);

		sendCouponOfBindCabinet(uid, memberBo.getCurrency(), cabient.getSysCode());
	}

	public List<MemberBO> findListByMemberVO(MyPage<MemberBO> page, MemberVO vo) {
		return memberDao.findListByMemberVO(page, vo);
	}

	/**
	 * 充电宝购买
	 * 
	 * @param uid
	 * @param payCallback
	 * @param paymentMark
	 * @return
	 */
	@Transactional(readOnly = false)
	public DirectPayResponse createPbBuyPay(Long uid, String payCallback, String paymentMark) {

		MemberBO member = memberDao.findOne(uid);

		if (member.getDeposit().compareTo(BigDecimal.ZERO) <= 0) {
			throw new DepositNotPayException();
		}

		List<OrderBO> list = orderDao.findByStatus(uid, OrderConstants.ORDER_STATUS_30);
		if (list.isEmpty()) {
			throw new NoOngoingOrderException();
		}

		DirectPayRequest payRequest = new DirectPayRequest();

		SysSetting sysSet = sysSettingDao.findOne();

		BigDecimal totalPrice = queryPbBuyCost(member.getCurrency(), sysSet);

		payRequest.setTotalFee(PayTotalPriceUtil.getTotalPrice(totalPrice));
		payRequest.setConfigMark(paymentMark);
		payRequest.setCapture(false);

		String payOrderCode = 
				payHelpService.getPayOrderCode(CommonConstants.PAY_TYPE_PB_BUY, uid);

		// 保存支付记录
		OrderPbBuy pb_buy = new OrderPbBuy();
		pb_buy.setUid(uid);
		pb_buy.setPbId(list.get(0).getPbId());
		pb_buy.setPbCode(list.get(0).getPbCode());
		pb_buy.setOrderId(list.get(0).getId());
		pb_buy.setOrderCode(list.get(0).getOrderCode());
		pb_buy.setCreateTime(new Date());
		pb_buy.setCurrency(member.getCurrency());
		pb_buy.setPaymentMark(paymentMark);
		pb_buy.setStatus(PortableBatteryConstatns.PB_BUY_STATUS_1);
		pb_buy.setPbAmount(totalPrice);
		pb_buy.setPayOrderCode(payOrderCode);

		orderPbBuyDao.save(pb_buy);


		payRequest.setBody("Portable Pay:" + sysSet.getAppNameEnglish());

		String notifyUrl = payHelpService.getNotifyUrl(paymentMark, payCallback);
		payRequest.setNotifyUrl(notifyUrl);
		payRequest.setCurrency(sysSet.getPlayformDefaultCurrency());
		
		if (PaymentConstants.PAYMENT_MARK_STRIPE.equals(paymentMark)) {
			payRequest.setOutTradeNo(member.getStripeCustomerId());
		} else {
			payRequest.setOutTradeNo(payOrderCode);
		}

		// 设置附加属性为支付类型
		payRequest.setReqAttach(CommonConstants.PAY_TYPE_PB_BUY);// 充电宝购买
		payRequest.setSubject("Portable Pay:" + sysSet.getAppNameEnglish());
		payRequest.setCapture(false);

		try {

			DirectPayResponse payResponse = 
					payHelpService.getPayResponse(paymentMark, uid, payRequest);
			
			if(null == payResponse) {
				throw new ServiceException("调用支付失败");
			}
			if(payResponse.isSuccess() && PayChannel.STRIPE == payResponse.getChannel()) {
				completeDepositPay(payOrderCode, payResponse.getPayOrderId(), payResponse.getTotalFee());
			}
			
			if(payResponse.isSuccess() && PayChannel.CASHFREE == payResponse.getChannel()) {
				payResponse.setOrderCurrency(sysSet.getPlayformDefaultCurrency());
				payResponse.setNotifyUrl(notifyUrl);
				payResponse.setOrderAmount(String.valueOf(payRequest.getTotalFee()));
				payResponse.setOrderId(payOrderCode);
				payResponse.setCustomerPhone(member.getMobile());
				
			}
			
			return payResponse;
			
		} catch (PayException e) {
			throw new RuntimeException("调用支付失败 出现异常", e);
		}

	}

	/**
	 * 完成充电宝的购买
	 * 
	 * @param payOrderCode 支付订单号
	 * @param outOrderId   外部订单号
	 * @param totalFee     实际支付金额
	 */
	public void completePbPay(String payOrderCode, String outOrderId, BigDecimal totalFee) {

		OrderPbBuyBO pbBuy = orderPbBuyDao.findByPayOrderCode(payOrderCode);

		if (null == pbBuy) {
			throw new ServiceException("支付信息不存在");
		}

		if (PortableBatteryConstatns.PB_BUY_STATUS_2 == pbBuy.getStatus()) {
			throw new ServiceException("支付已经完成");
		}

		// 结束订单
		OrderBO order = orderDao.findOngoingOrder(pbBuy.getUid());

		// 结束订单的时候
		Order up_order = new Order();
		up_order.setId(order.getId());
		up_order.setPayType(OrderConstants.PAY_TYPE_PB_BUY);
		up_order.setStatus(OrderConstants.ORDER_STATUS_50);
		up_order.setRideTime(
				new Double(Math.ceil((new Date().getTime() - order.getBeginTime().getTime()) / 1000 / 60D)).intValue());
		up_order.setFinishTime(new Date());
		up_order.setPayTime(new Date());
		BigDecimal price = orderService.computePrice(new Date(), order);
		up_order.setPrice(price);
		up_order.setPayPrice(totalFee);
		orderDao.update(up_order);

		// 修改充电宝的状态
		PortableBattery pb = new PortableBattery();
		pb.setId(order.getPbId());
		pb.setLocationType(PortableBatteryConstatns.LOCATION_TYPE_BUY);
		pb.setStatus(PortableBatteryConstatns.PB_STATUS_BUY);
		pb.setLastLocationTime(new Date());

		portableBatteryDao.update(pb);

		// 完成支付信息
		OrderPbBuy buy_lot = new OrderPbBuy();
		buy_lot.setId(pbBuy.getId());
		buy_lot.setStatus(PortableBatteryConstatns.PB_BUY_STATUS_2);
		buy_lot.setOutOrderId(outOrderId);
		buy_lot.setOrderAmount(price);
		buy_lot.setCashFee(totalFee);

		orderPbBuyDao.update(buy_lot);
	}

	/**
	 * 查询用户购买充电宝需要支付的金额
	 * 
	 * @param uid
	 * @param sysSet
	 * @return
	 */
	public BigDecimal queryPbBuyCost(Long uid) {

		MemberBO member = memberDao.findOne(uid);

		if (StringUtils.isEmpty(member.getCurrency())) {
			throw new UserCurrencyNotPerfectException();
		}

		SysSetting setting = sysSettingDao.findOne();

		return queryPbBuyCost(member.getCurrency(), setting);
	}

	public BigDecimal queryPbBuyCost(String currency, SysSetting setting) {
		List<CountryBO> countries = countryDao.findAll();
		for (CountryBO country : countries) {
			if (country.getCurrency().equals(currency)) {
				return country.getDeposit();
			}
		}
//		switch (currency) {
//		case "CNY":
//			return setting.getPbBuyCostRMB();
//		case "SGD":
//			return setting.getPbBuyCostSingapore();
//		case "MYR":
//			return setting.getPbBuyCostRMB();
//		default:
		throw new ServiceException("系统不支持的币种");
//		}
	}

	
	/**
	 * 保存返回地址
	 * @param uid
	 * @param url
	 */
	public void saveReturnUrl(Long uid, String returnUrl, JedisPool jedisPool) {
		if(StringUtils.isBlank(returnUrl)) {
			return;
		}
		Jedis jedis = null;
		try {
			// 记录缓存
			jedis = jedisPool.getResource();
			jedis.set(String.valueOf(uid)+SeCommonConstants.FONDY_RETURN_URL, returnUrl);
			jedis.expire(String.valueOf(uid)+SeCommonConstants.FONDY_RETURN_URL, 60 * 5);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}
	
	public String getReturnUrl(Long uid, JedisPool jedisPool) {
		Jedis jedis = null;
		try {
			// 记录缓存
			jedis = jedisPool.getResource();
			return jedis.get(String.valueOf(uid)+SeCommonConstants.FONDY_RETURN_URL);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public List<MemberBO> findByPage(Page<MemberBO> page, MemberVO vo) {
		return memberDao.findByPage(page, vo);
	}
}
