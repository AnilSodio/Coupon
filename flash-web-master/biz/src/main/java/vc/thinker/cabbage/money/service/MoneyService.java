package vc.thinker.cabbage.money.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinco.common.Hashids;
import com.sinco.common.area.Country;
import com.sinco.common.area.CountryUtil;
import com.sinco.common.security.PasswordUtil;
import com.sinco.data.core.Page;

import vc.thinker.biz.exception.ServiceException;
import vc.thinker.cabbage.common.PaymentConstants;
import vc.thinker.cabbage.money.exception.BalanceNotEnoughException;
import vc.thinker.cabbage.money.exception.ExchangeRateNotFindException;
import vc.thinker.cabbage.money.exception.MoneyPasswordIsNullException;
import vc.thinker.cabbage.se.OrderRebateConstants;
import vc.thinker.cabbage.se.RebateConstants;
import vc.thinker.cabbage.se.bo.CabinetBO;
import vc.thinker.cabbage.se.bo.UserRebateLogBO;
import vc.thinker.cabbage.se.dao.CabinetDao;
import vc.thinker.cabbage.se.dao.UserRebateLogDao;
import vc.thinker.cabbage.se.model.UserRebateLog;
import vc.thinker.cabbage.stats.UpdateBalanceStatsService.BalanceStatsEvent;
import vc.thinker.cabbage.sys.bo.ExchangeRateBO;
import vc.thinker.cabbage.sys.dao.ExchangeRateDao;
import vc.thinker.cabbage.sys.dao.SysSettingDao;
import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.cabbage.sys.service.PlatformRevenueService;
import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.bo.AgentBO;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.bo.PayAmountBO;
import vc.thinker.cabbage.user.bo.RefereeBO;
import vc.thinker.cabbage.user.bo.SellerBO;
import vc.thinker.cabbage.user.bo.UserMoneyBO;
import vc.thinker.cabbage.user.bo.UserMoneyCashBO;
import vc.thinker.cabbage.user.bo.UserMoneyLogBO;
import vc.thinker.cabbage.user.bo.UserMoneyRechargeBO;
import vc.thinker.cabbage.user.dao.AgentDao;
import vc.thinker.cabbage.user.dao.MemberDao;
import vc.thinker.cabbage.user.dao.PayAmountDao;
import vc.thinker.cabbage.user.dao.RefereeDao;
import vc.thinker.cabbage.user.dao.SellerDao;
import vc.thinker.cabbage.user.dao.UserMoneyCashDao;
import vc.thinker.cabbage.user.dao.UserMoneyDao;
import vc.thinker.cabbage.user.dao.UserMoneyLogDao;
import vc.thinker.cabbage.user.dao.UserMoneyRechargeDao;
import vc.thinker.cabbage.user.exception.DepositNotPayException;
import vc.thinker.cabbage.user.model.UserMoney;
import vc.thinker.cabbage.user.model.UserMoneyCash;
import vc.thinker.cabbage.user.model.UserMoneyLog;
import vc.thinker.cabbage.user.model.UserMoneyRecharge;
import vc.thinker.cabbage.user.service.PayHelpService;
import vc.thinker.cabbage.user.service.MemberService.MyDirectPayResponse;
import vc.thinker.cabbage.user.vo.UserMoneyCashVO;
import vc.thinker.cabbage.user.vo.UserMoneyLogVO;
import vc.thinker.pay.PayChannel;
import vc.thinker.pay.PayException;
import vc.thinker.pay.PayHandlerFactory;
import vc.thinker.pay.request.DirectPayRequest;
import vc.thinker.pay.response.DirectPayResponse;
import vc.thinker.sys.bo.UserBO;
import vc.thinker.sys.model.User;
import vc.thinker.sys.service.SystemService;
import vc.thinker.utils.CommUtil;

@Service
@Transactional(rollbackFor = ServiceException.class)
public class MoneyService {
	
	private static final Logger log=LoggerFactory.getLogger(MoneyService.class);

	@Autowired
	private UserMoneyLogDao userMoneyLogDao;

	@Autowired
	private SystemService systemService;

	@Autowired
	private UserMoneyDao moneyDao;

	@Autowired
	private UserMoneyCashDao userMoneyCashDao;
	
	@Autowired
	private UserMoneyRechargeDao userMoneyRechargeDao;
	
	@Autowired
	private SysSettingDao sysSettingDao;
	
	@Autowired
	private PayAmountDao payAmountDao;
	
	@Autowired
	private ExchangeRateDao exchangeRateDao;
	
	@Autowired
	private PlatformRevenueService platformRevenueService;
	
	@Autowired
	@Lazy(true)
	private PayHandlerFactory payHandlerFactory;
	
	@Autowired
	private PayHelpService payHelpService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private CabinetDao cabinetDao;
	
	@Autowired
	private SellerDao sellerDao;
	
	@Autowired
	private AgentDao agentDao;
	
	@Autowired
	private RefereeDao refereeDao;
	
	@Autowired
	private UserRebateLogDao userRebateLogDao;
	/**
	 * 是否完成支付
	 * @param sn
	 * @return
	 */
	@Transactional(readOnly=false)
	public boolean isCompleteBalancePay(String sn){
		UserMoneyRechargeBO recharge=userMoneyRechargeDao.findOne(sn);
		if(recharge == null){
			throw new ServiceException("不存在的充值记录");
		}
		return CommonConstants.BALANCE_PAY_LOG_STATUS_2.equals(recharge.getStatus());
	}
	
	@Transactional(readOnly=false)
	public UserMoneyCash sellerCashApply(Long userId, UserMoneyCashVO vo)
	{
		// 验证余额
		BigDecimal money = this.getAvailableBalance(userId);
		if (money.doubleValue() < vo.getCashAmount().doubleValue()) 
		{
			throw new BalanceNotEnoughException("对不起，余额不足!");
		}
		// 验证密码
		boolean falg = this.validatePassword(userId, vo.getCashPassword());
		if (!falg) {
			throw new ServiceException("对不起，提现密码不正确!");
		}

		UserMoneyCash obj = vo;
		obj.setCashSn(CommUtil.formatTime("yyyyMMddHHmmsss", new Date()));
		obj.setCreateTime(new Date());
		obj.setCashUserId(userId);
		UserMoneyCash result = this.userMoneyCashDao.save(obj);
		if (result.getId() != null) 
		{
			// 减去金额
			moneyDao.subtractMoney(userId, new BigDecimal(String.valueOf(vo.getCashAmount())));
		}
		return obj;
	}

	/**
	 * 验证密码
	 * 
	 * @param uid
	 * @param password
	 * @return
	 */
	public boolean validatePassword(Long uid, String password) {
		UserMoney money = this.isExistAndCreate(uid);
		if (StringUtils.isBlank(money.getPassword())) {
			throw new MoneyPasswordIsNullException();
		}
		return PasswordUtil.validatePassword(password, money.getPassword());
	}

	/**
	 * 是否设置过密码
	 * 
	 * @param uid
	 * @return
	 */
	public boolean isPasswordNull(Long uid) {
		UserMoney userMoney = moneyDao.findOne(uid);
		if (userMoney == null) {
			return true;
		}
		if (StringUtils.isBlank(userMoney.getPassword())) {
			return true;
		}
		return false;
	}

	/**
	 * 得到用户余额
	 * 
	 * @param uid
	 * @return
	 */
	public BigDecimal getAvailableBalance(Long uid) {
		UserMoney userMoney = moneyDao.findOne(uid);
		if (userMoney == null) {
			return BigDecimal.ZERO;
		}
		return userMoney.getAvailableBalance();
	}
	
	/**
	 * 得到用户余额
	 * 
	 * @param uid
	 * @return
	 */
	public UserMoney findOne(Long uid) {
		return moneyDao.findOne(uid);
	}

	/**
	 * 消费钱,检查密码
	 * 
	 * @param amount
	 * @param pdLogInfo
	 * @throws ServiceException
	 */
	public boolean consumptionMoney(Long uid,String currency,BigDecimal amount, String password, String pdLogInfo)  
	{
		User user = this.systemService.getUser(uid);
		if (null == user) {
			return false;
		}

		UserMoney money = this.isExistAndCreate(uid);

		// 用户支付密码是否为空
		if (StringUtils.isBlank(money.getPassword())) {
			throw new ServiceException("");
		}

		if (!PasswordUtil.validatePassword(password, money.getPassword())) {
			throw new ServiceException("支付密码错误!");
		}

		return consumptionMoney(uid,currency,amount, pdLogInfo);
	}

	/**
	 * 消费
	 * 
	 * @param amount
	 * @param pdLogInfo
	 * @throws ServiceException
	 */
	@Transactional(readOnly = false)
	public boolean consumptionMoney(Long uid,String currency,BigDecimal amount, String pdLogInfo){
		
//		User user = this.systemService.getUser(uid);
		
//		SysSetting sysSetting = sysSettingDao.findOne();
		
//		String userCurrency= CountryUtil.get(user.getCountry()).getCurrencyCode();
		BigDecimal rate=new BigDecimal(1);
		
		BigDecimal addAmount=new BigDecimal(amount.doubleValue());
		
		//进行币种转换
//		if(!currency.equals(userCurrency)){
//			ExchangeRateBO exchangeRate = exchangeRateDao.findOneByCurrency(currency, userCurrency);
//			if(exchangeRate == null){
//				throw new ExchangeRateNotFindException(currency, userCurrency);
//			}
//			rate=exchangeRate.getExchangeRate();
//			addAmount=amount.multiply(rate).setScale(2, BigDecimal.ROUND_DOWN);
//		}
		
		UserMoneyBO userMoney = moneyDao.findOne(uid);
		if(null == userMoney) {
			throw new BalanceNotEnoughException("Insufficient account balance");
		}
		if(userMoney.getAvailableBalance().compareTo(addAmount) < 0) {
			throw new BalanceNotEnoughException("Insufficient account balance");
		}
		
		moneyDao.subtractMoney(uid, addAmount);
		
		this.addMoneyLog(uid ,null,null,CommonConstants.MONEY_LOG_CONSUME, 
				BigDecimal.ZERO.subtract(addAmount),currency,BigDecimal.ZERO.subtract(amount)
				,currency,rate,pdLogInfo);
		
		return true;
	}

	/**
	 * 提现扣钱
	 * 
	 * @return
	 */
	@Transactional(readOnly = false)
	public void cashMoney(String cashAdminInfo, int cashStatus, Long cashId, Long adminId) 
	{
		UserMoneyCashBO cashBO = userMoneyCashDao.findOne(cashId);
		if (CommonConstants.MONEY_CASH_STATUS_PAY.equals(cashBO.getCashStatus()))// 已经提现
			return;
		// 更改提现申请状态
		if (CommonConstants.MONEY_CASH_STATUS_NO_PAY.equals(cashStatus)) 
		{// 扣钱
			cashStatus = 1;
			if (this.moneyDao.countByUid(cashBO.getCashUserId(), cashBO.getCashAmount()) <= 0) {
				throw new BalanceNotEnoughException("用户余额不足，无法完成提现!");
			}
			
			UserMoney money=isExistAndCreate(cashBO.getCashUserId());
			
			// 减去金额
			moneyDao.subtractMoney(cashBO.getCashUserId(), cashBO.getCashAmount());
			
			this.addMoneyLog(cashBO.getCashUserId(), cashId,null,CommonConstants.MONEY_LOG_CASH,
					cashBO.getCashAmount().multiply(new BigDecimal(-1)),money.getCurrency(), "申请提现审核通过");
			//TODO 此处应该发短信同志
		}
		cashBO.setCashAdminId(adminId);
		cashBO.setCashStatus(CommonConstants.MONEY_CASH_STATUS_PAY);
		cashBO.setCashAdminInfo(cashAdminInfo);
		userMoneyCashDao.update(cashBO);
	}

	@Transactional(readOnly = false)
	public boolean addMoney(Long uid,String currency,BigDecimal amount,Long sourceId,
			String outOrderId,String logType, String info) {
		User user = this.systemService.getUser(uid);
		
		if(StringUtils.isNotBlank(outOrderId)){
			if(userMoneyLogDao.countByOutOrderId(outOrderId) > 0){
				throw new ServiceException("外部订单编号["+outOrderId+"]已存在充值记录");
			}
		}
		
		if (user == null) {
			return false;
		}
		isExistAndCreate(user.getId());
		
		String userCurrency= CountryUtil.get(user.getCountry()).getCurrencyCode();
		BigDecimal rate=new BigDecimal(1);
		
		BigDecimal addAmount=new BigDecimal(amount.doubleValue());
		
		//进行币种转换
		if(!currency.equals(userCurrency)){
			ExchangeRateBO exchangeRate = exchangeRateDao.findOneByCurrency(currency, userCurrency);
			if(exchangeRate == null){
				throw new ExchangeRateNotFindException(currency, userCurrency);
			}
			rate=exchangeRate.getExchangeRate();
			addAmount=amount.multiply(rate).setScale(2, BigDecimal.ROUND_DOWN);
		}
		
		moneyDao.addMoney(user.getId(), addAmount);
		
		this.addMoneyLog(uid, sourceId,outOrderId,logType, addAmount,userCurrency,amount,currency,rate,info);
		
		return true;
	}
	
	
	/**
	 * 增加用户余额
	 * 
	 * @param uid
	 *            用户ID
	 * @param amount
	 *            数量
	 * @param pdOpType
	 *            类型
	 * @param info
	 *            信息
	 * @return
	 */
	public boolean addMoney(Long uid, String currency,BigDecimal amount,String logType, String info) {
		return addMoney(uid,currency,amount,null,null, logType, info);
	}
	
	public MyDirectPayResponse createApplePay(Long uid, String paymentMark, 
			PayAmountBO amount, String currency, String payCallback) {
		
		// 生成支付订单号
		String payOrderCode =  payHelpService.getPayOrderCode( CommonConstants.PAYMENT_MARK_BALANCE, uid);
		// 保存支付信息
		savePayLog(uid, payOrderCode, paymentMark, amount);
		
		// 组装返回豹纹
		DirectPayResponse response = new DirectPayResponse(PayChannel.ALIPAY, null);
		response.setSuccess(true);
		response.setOrderCurrency(currency);
		response.setOrderAmount(String.valueOf(amount.getPayAmount()));
		response.setOrderId(payOrderCode);
		response.setNotifyUrl(payHelpService.getNotifyUrl(paymentMark, payCallback));
		MyDirectPayResponse payResponse = new MyDirectPayResponse(response, payOrderCode);
		return payResponse;
	}
	
	/**
	 * 创建充值支付
	 * @param uid
	 * @param vipId
	 * @param payCallback
	 * @param paymentMark
	 * @return
	 */
	@Transactional(readOnly=false)
	public MyDirectPayResponse createRechargePay(Long uid,Long payAmountId,String payCallback,String paymentMark) {
		
		checkPaymentMark(paymentMark);
		
		log.info("调用充值====={},{}",uid,payAmountId);
		
		MemberBO member = memberDao.findOne(uid);
		if(null == member.getDeposit() || member.getDeposit().compareTo(BigDecimal.ZERO) <= 0){
			throw new DepositNotPayException();
		}
		
		PayAmountBO payAmount=payAmountDao.findOne(payAmountId);
		if(payAmount == null){
			throw new ServiceException("充值金额不存在");
		}
		
		SysSetting sys = sysSettingDao.findOne();
		if(!sys.getIsOpenBalance()){
			throw new ServiceException("平台未开放余额充值功能");
		}
		
		if(PaymentConstants.PAYMENT_MARK_FONDY_APPLE_PAY.equals(paymentMark)) {
			return createApplePay(uid, paymentMark, payAmount, sys.getPlayformDefaultCurrency(), payCallback);
		}
		
		DirectPayRequest payRequest=new DirectPayRequest();
		payRequest.setCurrency(sys.getPlayformDefaultCurrency());
		payRequest.setTotalFee(payAmount.getPayAmount());
		payRequest.setConfigMark(paymentMark);
		payRequest.setBody("Balance recharge:"+sys.getAppNameEnglish());
		
		String notifyUrl = payHelpService.getNotifyUrl(paymentMark, payCallback);
		payRequest.setNotifyUrl(notifyUrl);
		payRequest.setReturnUrl(payCallback+"/returnUrl");
		
		String payOrderCode =  payHelpService.getPayOrderCode( CommonConstants.PAYMENT_MARK_BALANCE, uid);
		
		payRequest.setOutTradeNo(payOrderCode);
		//strpe 已token为 outTradeNo
		if(PaymentConstants.PAYMENT_MARK_STRIPE.equals(paymentMark)){
			payRequest.setOutTradeNo(member.getStripeCustomerId());
		} 
		
		//保存支付信息
		savePayLog(uid, payOrderCode, paymentMark, payAmount);
		
		//设置附加属性为支付类型
		payRequest.setReqAttach(CommonConstants.PAY_TYPE_BALANCE);
		payRequest.setSubject("Balance recharge");
		
		try {
			DirectPayResponse payResponse  = payHelpService.getPayResponse(paymentMark, uid, payRequest);
			
			if(null == payResponse) {
				throw new ServiceException("调用支付失败");
			}
			
			if(payResponse.isSuccess() && PayChannel.STRIPE == payResponse.getChannel()) {
				rechargeMoney(payOrderCode, payResponse.getPayOrderId(),payResponse.getTotalFee());
			}
			if(payResponse.isSuccess() && PayChannel.CASHFREE == payResponse.getChannel()) {
				payResponse.setNotifyUrl(notifyUrl);
				payResponse.setOrderAmount(String.valueOf(payRequest.getTotalFee()));
				payResponse.setOrderId(payOrderCode);
				payResponse.setCustomerPhone(member.getMobile());
				payResponse.setOrderCurrency(sys.getPlayformDefaultCurrency());
			}
			return new MyDirectPayResponse(payResponse, payOrderCode);
		} catch (PayException e) {
			log.error("支付失败",e);
			throw new ServiceException("调用支付失败 出现异常",e);
		}
	}

	public void savePayLog(Long uid, String orderCode, String paymentMark, PayAmountBO payAmount) {
		UserMoneyRecharge recharge=new UserMoneyRecharge();
		recharge.setUserId(uid);
		recharge.setSn(orderCode);
		recharge.setPayOrderCode(orderCode);
		recharge.setPaymentType(paymentMark);
		recharge.setAmount(payAmount.getPayAmount());
		recharge.setSendAmount(payAmount.getSendAmount());
		recharge.setPayAmount(payAmount.getPayAmount());
		recharge.setCurrency(payAmount.getCurrency());
		recharge.setStatus(CommonConstants.BALANCE_PAY_LOG_STATUS_1);
		recharge.setCreateTime(new Date());
		userMoneyRechargeDao.save(recharge);
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
	/**
	 * 充值
	 * 
	 * @throws ServiceException
	 */
	@Transactional(readOnly = false)
	public UserMoneyRecharge rechargeMoney(String sn,String outOrderId,BigDecimal cashFee) {

		UserMoneyRechargeBO recharge = this.userMoneyRechargeDao.findOne(sn);
		
		if (recharge == null) {
			throw new ServiceException("未找到充值记录！");
		}
		
		if (CommonConstants.MONEY_RECHARGE_STATUS_PAY_SUCCESS.equals(recharge.getStatus())) {
			return recharge;
		}
		
		UserMoneyRechargeBO recharge_up = new UserMoneyRechargeBO();
		recharge_up.setId(recharge.getId());
		recharge_up.setCashFee(cashFee);
		recharge_up.setStatus(CommonConstants.MONEY_RECHARGE_STATUS_PAY_SUCCESS);
		recharge_up.setOutOrderId(outOrderId);
		recharge_up.setPayTime(new Date());
		this.userMoneyRechargeDao.update(recharge_up);

//		User user = this.systemService.getUser(recharge.getUserId());
		
		UserMoney money=isExistAndCreate(recharge.getUserId());

		BigDecimal amount=recharge.getAmount();
		if(recharge.getSendAmount() != null){
			amount=amount.add(recharge.getSendAmount());
		}
		 
		log.info("CashFree recharge_uid:[{}], amount:[{}]",  recharge.getUserId(), amount);
		
		moneyDao.addMoney(recharge.getUserId(), amount);

		this.addMoneyLog(recharge.getUserId(), null,null, CommonConstants.MONEY_LOG_RECHARGE,
				recharge.getAmount(),money.getCurrency(),"Top-Up");
		if(recharge.getSendAmount() != null && recharge.getSendAmount().doubleValue() > 0){
			this.addMoneyLog(recharge.getUserId(), null,null, CommonConstants.MONEY_LOG_GIVE,
					recharge.getSendAmount(),money.getCurrency(),"Bonus");
		}
		
		//TODO 增加返利金
//		SysSetting setting = sysSettingDao.findOne();
//		
//		//加返利金
//		BigDecimal rebateMoney =  recharge.getAmount().multiply(new BigDecimal(1).subtract(setting.getPlatformExtraction()));
//		moneyDao.addRebateMoney(user.getId(),rebateMoney);
//		UserRebateMoneyLog rlog = new UserRebateMoneyLog();
//		rlog.setUserId(user.getId());
//		rlog.setLogAmount(rebateMoney);
//		rlog.setLogCurrency(money.getCurrency());
//		rlog.setOldLogAmount(rebateMoney);
//		rlog.setOldLogCurrency(money.getCurrency());
//		rlog.setExchangeRate(new BigDecimal(1));
//		rlog.setLogInfo("Top-Up");
//		rlog.setCreateTime(new Date());
//		userRebateMoneyLogDao.save(rlog);
//		
//		//获取平台返利比例
//		BigDecimal platformAmount =  recharge.getAmount().multiply(setting.getPlatformExtraction());
//		
//		//添加平台收益
//		platformRevenueService.addPlatformRevenue(recharge.getId(), CommonConstants.PLATFORM_REVENUE_LOG_RECHARGE,
//				money.getCurrency(), platformAmount, "User recharge");
		
		
		//TODO 返利
		rebate(recharge.getAmount(),money.getCurrency(), recharge.getUserId(),recharge.getId(),CommonConstants.PLATFORM_REVENUE_LOG_RECHARGE,"User recharge");
		
		//用户余额充值统计
		BalanceStatsEvent event = new BalanceStatsEvent();
		event.setRechargeId(recharge.getId());
		publisher.publishEvent(event);
		
		return recharge;
	}

	/**
	 * 修改密码
	 * 
	 * @param uid
	 * @param password
	 * @return
	 */
	public boolean updatePassword(Long uid, String password) {
		isExistAndCreate(uid);

		UserMoney um = new UserMoney();
		um.setUid(uid);
		um.setPassword(PasswordUtil.entryptPassword(password));
		moneyDao.update(um);
		return true;
	}

	/**
	 * 判断是否存在，不存在创建
	 * 
	 * @param uid
	 */
	public UserMoney isExistAndCreate(Long uid) {

		UserMoney userMoney = moneyDao.findOne(uid);

		SysSetting sysSet = sysSettingDao.findOne();
		
		if (userMoney == null) {
			userMoney = new UserMoney();
			userMoney.setUid(uid);
			userMoney.setAvailableBalance(new BigDecimal(0));
			userMoney.setRebateMoney(new BigDecimal(0));
			userMoney.setCurrency(sysSet.getPlayformDefaultCurrency());
			// 空密码
			moneyDao.save(userMoney);
		}
		return userMoney;
	}

	public UserMoneyBO findUserMoneyByUid(Long uid) {
		return moneyDao.findOne(uid);
	}
	
	/**
	 *  管理员审核体现申请
	 * @param userId 当前登录用户（管理员）
	 * @param cashId 体现记录ID
	 * @param memo 管理员备注
	 * @param cashStatus 操作体现状态
	 * @param cashPayStatus 操作支付状态
	 * @throws ServiceException 
	 */
//	@Transactional(readOnly = false)
//	public void audit(Long userId, Long cashId,String memo,int checkStatus) throws ServiceException{
//		UserMoneyCashBO cashBO = userMoneyCashDao.findOne(cashId);
//		if (null == cashBO || CommonConstants.MONEY_CASH_STATUS_NO_PAY.equals(cashBO.getCashStatus()) || CommonConstants.MONEY_CASH_STATUS_PAY.equals(cashBO.getCashStatus()))// 已经提现
//			throw new ServiceException("参数有误");
//		
//		if(checkStatus==-1){//审核拒绝
////			if(StringUtils.isEmpty(memo)){
////				throw new ServiceException("审核拒绝时，备注必填");
////			}
//			if(!addMoney(cashBO.getCashUserId(), cashBO.getCashAmount(), userId, CommonConstants.MONEY_LOG_CASH, "提现申请被拒绝")){
//				throw new ServiceException("审核失败");
//			}
//		}
//		cashBO.setCashAdminId(userId);
//		cashBO.setCashStatus(CommonConstants.MONEY_RECHARGE_STATUS_PAY_SUCCESS);;
//		cashBO.setCashAdminInfo(memo);
//		userMoneyCashDao.update(cashBO);
//	}
	
	@Transactional(readOnly = false)
	private void addMoneyLog(Long userId, Long sourceId,String outOrderId,
			String logType, BigDecimal logAmount,String logCurrency,BigDecimal oldAmount
			,String oldCurrency,BigDecimal exchangeRate,String info){
		// 保存提现日志
		UserMoneyLog log = new UserMoneyLog();
		log.setLogUserId(userId);
		log.setLogSourceId(sourceId);
		log.setLogType(logType);
		log.setLogAmount(logAmount);
		log.setLogCurrency(logCurrency);
		log.setOldLogCurrency(oldCurrency);
		log.setOldLogAmount(oldAmount);
		log.setExchangeRate(exchangeRate);
		log.setLogInfo(info);
		log.setCreateTime(new Date());
		log.setOutOrderId(outOrderId);
		userMoneyLogDao.save(log);
	}
	
	@Transactional(readOnly = false)
	private void addMoneyLog(Long userId, Long sourceId,String outOrderId,
			String logType, BigDecimal logAmount,String logCurrency,String info){
		addMoneyLog(userId, sourceId, outOrderId, logType, logAmount, logCurrency, 
				logAmount, logCurrency, new BigDecimal(1), info);
	}
	
	@Transactional(readOnly = false)
	public List<UserMoneyLogBO> findMoneyLogListByVO(UserMoneyLogVO vo, Page<UserMoneyLogBO> page)
	{
		return userMoneyLogDao.findPageByVO(vo, page);
	}
	
	@Transactional(readOnly = false)
	public List<UserMoneyCashBO> findCashMoneyListByVO(UserMoneyCashVO vo, Page<UserMoneyCashBO> page)
	{
		return userMoneyCashDao.findPageByVO(vo, page);
	}
	
	@Transactional(readOnly = false)
	public UserMoneyCashBO findMoneyCashBySn(String cashSn){
		return userMoneyCashDao.findBySn(cashSn);
	}
	
	@Transactional(readOnly = false)
	public boolean checkCash(Long id, boolean cashResult, Long adminId,String cashAdminInfo)
	{
		UserMoneyCashBO cash = userMoneyCashDao.findOne(id);
		if (cash != null && CommonConstants.MONEY_CASH_STATUS_NO_CHECK.equals(cash.getCashStatus())) 
		{
			UserMoney money = this.isExistAndCreate(cash.getCashUserId());
			UserMoneyCash ca = new UserMoneyCash();
			ca.setId(id);
			ca.setCashAdminId(adminId);
			if (cashResult) 
			{
				ca.setCashStatus(CommonConstants.MONEY_CASH_STATUS_NO_PAY);
			}
			else
			{
				ca.setCashStatus(CommonConstants.MONEY_CASH_STATUS_CHECK_NO);
				ca.setCashAdminInfo(cashAdminInfo);
				addMoney(cash.getCashUserId(),money.getCurrency(),cash.getCashAmount(), cash.getId(),null,CommonConstants.MONEY_LOG_CASH, "提现申请被拒绝");
			}
			ca.setCheckTime(new Date());
			userMoneyCashDao.update(ca);
			return true;
		}
		return false;
	}
	@Transactional(readOnly = false)
	public boolean cashPayOver(Long id, Long adminId)
	{
		UserMoneyCashBO cash = userMoneyCashDao.findOne(id);
		if (cash != null && CommonConstants.MONEY_CASH_STATUS_NO_PAY.equals(cash.getCashStatus())) 
		{
			UserMoneyCash ca = new UserMoneyCash();
			ca.setId(id);
			ca.setCashAdminId(adminId);
			ca.setCashStatus(CommonConstants.MONEY_CASH_STATUS_PAY);
			ca.setTransTime(new Date());
			userMoneyCashDao.update(ca);
			return true;
		}
		return false;
	}
	
	private static final Hashids snHashId = new Hashids("radish-saas-2.0.0 money sn mnbvcxzlkjhgfdsa",4,"12345678900123456789abcdef");
	/**
	 * 产生流水号
	 * @return
	 */
	public static String getSn(Long id){
		//组件id,为2位
		String cidStr=snHashId.encode(id);
		if(cidStr.length() > 4){
			cidStr=cidStr.substring(0,4);
		}
		return DateFormatUtils.format(new Date(), "yMMddHHmmss")+cidStr;
	}
	
	public void substractRebateMoney(Long uid,BigDecimal money){
		moneyDao.substractRebateMoney(uid,money);
	}

	public List<UserMoneyBO> sumRebateMoney() {
		return moneyDao.sumRebateMoney();
	}
	
	
	public void rebate(BigDecimal amount,String currency,Long uid,Long bizId,String type,String info){
		
		//获取用户机柜
		MemberBO user = memberDao.findOne(uid);
		
		//平台返利
		BigDecimal platAmount = amount;
				
		//通过机柜获取服务商
		if(StringUtils.isEmpty(user.getSysCode())){//机柜编号为空
			//增加平台收益
			platformRevenueService.addPlatformRevenue(bizId, type,
					currency, platAmount, info);
			return;
		}
		
		CabinetBO cabinet = cabinetDao.findBySysCode(user.getSysCode());
		if(null == cabinet){
			//增加平台收益
			platformRevenueService.addPlatformRevenue(bizId, type,
					currency, platAmount, info);
			return ;
		}
		//商户ID
		SellerBO seller = sellerDao.findOne(cabinet.getSellerId());
		//推荐人
		RefereeBO referee = new RefereeBO();
		
		if(seller!=null&&seller.getRefereeUid()!=null){
		   referee = refereeDao.findOne(seller.getRefereeUid());
		}
		
		//代理商
		AgentBO agent = new AgentBO();
		if(cabinet.getAgentId()!=null){
			 agent  = agentDao.findOne(cabinet.getAgentId());
		}
		
		

		Date time=new Date();
		
		//根据支付金额进行反润
		if(amount == null || amount.doubleValue() <= 0){
			log.info("支付金额小于0，无法进行反润");
			return;
		}
		
		
		//商户返利
		if(seller!=null && seller.getRebateRate() != null && seller.getRebateRate().doubleValue() > 0){
			UserRebateLog rebateLog=new UserRebateLog();
			rebateLog.setUid(seller.getUid());
			rebateLog.setCreateTime(time);
			//rebateLog.setOrderId(order.getId());
			//rebateLog.setOrderCode(order.getOrderCode());
			rebateLog.setRebateRate(seller.getRebateRate());
			BigDecimal rebateAmount = new BigDecimal(0);
			
			rebateAmount =amount.multiply(seller.getRebateRate());
			
			rebateAmount=rebateAmount.setScale(2, BigDecimal.ROUND_DOWN);
			
			platAmount = platAmount.subtract(rebateAmount);
			
			rebateLog.setRebateAmount(rebateAmount);
			rebateLog.setRebateModel(OrderRebateConstants.REBATE_MODEL_P);
			rebateLog.setSendStatus(true);
			rebateLog.setSendTime(time);
			rebateLog.setCurrency(currency);
			rebateLog.setRebateType(type);
			rebateLog.setPayUid(uid);
			rebateLog.setUserType(RebateConstants.rebate_user_type_seller);
			userRebateLogDao.save(rebateLog);
			
			//增加代理点余额
			this.addMoney(seller.getUid(), currency,
					rebateAmount, rebateLog.getId(), null, CommonConstants.MONEY_LOG_REBATE,
					"top-up rebate");
			
			//减少平台收益
//			platformRevenueService.addPlatformRevenue(rebateLog.getId(), CommonConstants.PLATFORM_REVENUE_LOG_REBATE,
//					rebateLog.getCurrency(), rebateAmount.negate(), order.getOrderCode()+ " Order merchants rebate");
		}
		//代理返利
		if( agent.getRebateRate() != null &&  agent.getRebateRate().doubleValue() > 0){
			UserRebateLog rebateLog=new UserRebateLog();
			rebateLog.setUid(agent.getUid());
			rebateLog.setCreateTime(time);
			//rebateLog.setOrderId(order.getId());
			//rebateLog.setOrderCode(order.getOrderCode());
			rebateLog.setRebateRate(agent.getRebateRate());
			BigDecimal rebateAmount = new BigDecimal(0);
			
			rebateAmount = amount.multiply(agent.getRebateRate());
			rebateAmount=rebateAmount.setScale(2, BigDecimal.ROUND_DOWN);
			
			platAmount = platAmount.subtract(rebateAmount);
			
			rebateLog.setRebateAmount(rebateAmount);
			rebateLog.setRebateModel(OrderRebateConstants.REBATE_MODEL_P);
			rebateLog.setSendStatus(true);
			rebateLog.setSendTime(time);
			rebateLog.setCurrency(currency);
			rebateLog.setRebateType(type);
			rebateLog.setPayUid(uid);
			rebateLog.setUserType(RebateConstants.rebate_user_type_agent);
			userRebateLogDao.save(rebateLog);
			
			//增加代理点余额
			this.addMoney(cabinet.getAgentId(), currency,
					rebateAmount, rebateLog.getId(), null, CommonConstants.MONEY_LOG_REBATE,
					" top-up rebate");
			
			//减少平台收益
//			platformRevenueService.addPlatformRevenue(rebateLog.getId(), CommonConstants.PLATFORM_REVENUE_LOG_REBATE,
//					rebateLog.getCurrency(), rebateAmount.negate(), order.getOrderCode()+" Order agent rebate");
		}
		//推荐人返利
		if( referee.getRebateRate() != null && referee.getRebateRate().doubleValue() > 0){
			UserRebateLog rebateLog=new UserRebateLog();
			rebateLog.setUid(seller.getRefereeUid());
			rebateLog.setCreateTime(time);
			//rebateLog.setOrderId(order.getId());
			//rebateLog.setOrderCode(order.getOrderCode());
			rebateLog.setRebateRate(referee.getRebateRate());
			BigDecimal rebateAmount = new BigDecimal(0);
			
			rebateAmount = amount.multiply(referee.getRebateRate());
			rebateAmount=rebateAmount.setScale(2, BigDecimal.ROUND_DOWN);
			
			platAmount = platAmount.subtract(rebateAmount);
			
			rebateLog.setRebateAmount(rebateAmount);
			rebateLog.setRebateModel(OrderRebateConstants.REBATE_MODEL_P);
			rebateLog.setSendStatus(true);
			rebateLog.setSendTime(time);
			rebateLog.setCurrency(currency);
			rebateLog.setRebateType(type);
			rebateLog.setPayUid(uid);
			rebateLog.setUserType(RebateConstants.rebate_user_type_referee);
			userRebateLogDao.save(rebateLog);
			
			//增加代理点余额
			this.addMoney(seller.getRefereeUid(), currency,
					rebateAmount, rebateLog.getId(), null, CommonConstants.MONEY_LOG_REBATE,
					"top-up rebate");
			
			//减少平台收益
//			platformRevenueService.addPlatformRevenue(rebateLog.getId(), CommonConstants.PLATFORM_REVENUE_LOG_REBATE,
//					rebateLog.getCurrency(), rebateAmount.negate(), order.getOrderCode()+" Order recommend rebate");
		}
		if(platAmount.doubleValue()>0){
			//增加平台收益
			platformRevenueService.addPlatformRevenue(bizId, type,
					currency, platAmount, info);
		}
		
	}

	/**
	 * 订单返润
	 * @param totalPrice 订单总金额
	 * @param borrowCabinetId 充电柜id
	 */
	public void orderRebate(BigDecimal totalPrice, Long borrowCabinetId, Long orderId, String orderCode, Long payUid) {		
		CabinetBO cabinet = cabinetDao.findOne(borrowCabinetId);
		if (null != cabinet.getSellerId()) {
			// 商户返润
			SellerBO seller = sellerDao.findOne(cabinet.getSellerId());
			if (null != seller && seller.getRebateRate().compareTo(BigDecimal.ZERO) > 0) {
				BigDecimal multiply = totalPrice.multiply(seller.getRebateRate());
				totalPrice = totalPrice.subtract(multiply);
				// 添加余额
				moneyDao.addMoney(seller.getUid(), multiply);
				// 添加收益记录
				saveMoneyLog(seller.getUid(), orderId, multiply, CommonConstants.MONEY_LOG_REBATE, orderCode);
				// 添加返润记录
				saveRebateLog(seller.getUid(), orderId, orderCode, multiply, RebateConstants.rebate_user_type_seller,payUid);
				//平台收益
				BigDecimal multiply2 = multiply.multiply(new BigDecimal("-1"));
				platformRevenueService.addPlatformRevenue(orderId, CommonConstants.PLATFORM_REVENUE_LOG_REBATE,
						null, multiply2, "Seller Rebate");
			}
		}
		
		if(null != cabinet.getAgentId()) {
			AgentBO agent = agentDao.findOne(cabinet.getAgentId());
			if(null != agent && agent.getRebateRate().compareTo(BigDecimal.ZERO) > 0 
					&& totalPrice.compareTo(BigDecimal.ZERO)>0) {
				BigDecimal multiply = totalPrice.multiply(agent.getRebateRate());
				// 添加余额
				moneyDao.addMoney(agent.getUid(), multiply);
				// 添加收益记录
				saveMoneyLog(agent.getUid(), orderId, multiply, CommonConstants.MONEY_LOG_REBATE, orderCode);
				// 添加返润记录
				saveRebateLog(agent.getUid(), orderId, orderCode, multiply, RebateConstants.rebate_user_type_agent,payUid);
				// 平台支出
				BigDecimal multiply2 = multiply.multiply(new BigDecimal("-1"));
				platformRevenueService.addPlatformRevenue(orderId, CommonConstants.PLATFORM_REVENUE_LOG_REBATE,
						null, multiply2, "Agent Rebate");
			}
		}
	}	
	
	/**
	 *  保存余额流水
	 * @param uid
	 * @param orderId
	 * @param amount
	 * @param logType
	 */
	public void saveMoneyLog(Long uid, Long orderId, BigDecimal amount, String logType, String orderCode) {
		UserMoneyLogBO log = new UserMoneyLogBO();
		log.setLogUserId(uid);
		log.setLogSourceId(orderId);
		log.setLogAmount(amount);
		log.setLogType(logType);
		log.setLogInfo("Order :["+orderCode+"] Rebate");
		log.setCreateTime(new Date());
		userMoneyLogDao.save(log);
	}
	
	/**
	 * 保存返润日志
	 * @param uid
	 * @param orderId
	 * @param orderCode
	 * @param amount
	 * @param userType
	 */
	public void saveRebateLog(Long uid, Long orderId, String orderCode, BigDecimal amount, String userType, Long payUid) {
		UserRebateLogBO rebate = new UserRebateLogBO();
		rebate.setUid(uid);
		rebate.setOrderId(orderId);
		rebate.setOrderCode(orderCode);
		rebate.setRebateAmount(amount);
		rebate.setUserType(userType);
		rebate.setSendStatus(true);
		rebate.setCreateTime(new Date());
		rebate.setSendTime(new Date());
		rebate.setPayUid(payUid);
		rebate.setRebateRate(BigDecimal.ONE);
		rebate.setRebateModel(OrderRebateConstants.REBATE_MODEL_P);
		rebate.setRebateType(CommonConstants.PLATFORM_REVENUE_LOG_ORDER);
		userRebateLogDao.save(rebate);
	}
}
