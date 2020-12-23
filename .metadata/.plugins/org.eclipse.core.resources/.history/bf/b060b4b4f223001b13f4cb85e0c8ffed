package vc.thinker.cabbage.se;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import com.sinco.common.area.CountryUtil;
import com.sinco.data.core.Page;
import vc.thinker.biz.exception.ServiceException;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.common.OrderCodeUtils;
import vc.thinker.cabbage.common.PaymentConstants;
import vc.thinker.cabbage.common.exception.DataNotFindException;
import vc.thinker.cabbage.money.service.MoneyService;
import vc.thinker.cabbage.se.AppMessageService.OrderMessageEvent;
import vc.thinker.cabbage.se.DepositDeductionService.DepositDeductionEvent;
import vc.thinker.cabbage.se.bo.CabinetBO;
import vc.thinker.cabbage.se.bo.CabinetChargeRuleBO;
import vc.thinker.cabbage.se.bo.OrderBO;
import vc.thinker.cabbage.se.bo.PortableBatteryBO;
import vc.thinker.cabbage.se.dao.CabinetChargeRuleDao;
import vc.thinker.cabbage.se.dao.CabinetDao;
import vc.thinker.cabbage.se.dao.CabinetStatusDao;
import vc.thinker.cabbage.se.dao.OrderDao;
import vc.thinker.cabbage.se.dao.PortableBatteryDao;
import vc.thinker.cabbage.se.exception.BatteryIsNotEnoughExceptiion;
import vc.thinker.cabbage.se.exception.CabinetIsOfflineException;
import vc.thinker.cabbage.se.exception.CabinetNotFindException;
import vc.thinker.cabbage.se.exception.CabinetStatusNotFindException;
import vc.thinker.cabbage.se.exception.CouponExpiredException;
import vc.thinker.cabbage.se.exception.CouponNotFindException;
import vc.thinker.cabbage.se.exception.OrderHasEndExceptiion;
import vc.thinker.cabbage.se.exception.OrderHavePaidException;
import vc.thinker.cabbage.se.exception.OrderNotFindException;
import vc.thinker.cabbage.se.exception.UnfinishedOrdersExceptiion;
import vc.thinker.cabbage.se.model.CabinetStatus;
import vc.thinker.cabbage.se.model.Order;
import vc.thinker.cabbage.se.model.PortableBattery;
import vc.thinker.cabbage.se.vo.OrderVO;
import vc.thinker.cabbage.stats.UpdateOrderStatsService.EndOrderEvent;
import vc.thinker.cabbage.sys.SysMessageConstants;
import vc.thinker.cabbage.sys.service.PlatformRevenueService;
import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.CouponConstants;
import vc.thinker.cabbage.user.IntegralConstants;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.bo.SellerBO;
import vc.thinker.cabbage.user.bo.UserCouponBO;
import vc.thinker.cabbage.user.dao.AdminDao;
import vc.thinker.cabbage.user.dao.MemberDao;
import vc.thinker.cabbage.user.dao.SellerDao;
import vc.thinker.cabbage.user.dao.UserCouponDao;
import vc.thinker.cabbage.user.exception.DepositException;
import vc.thinker.cabbage.user.exception.SellerLockedException;
import vc.thinker.cabbage.user.model.Admin;
import vc.thinker.cabbage.user.model.UserCoupon;
import vc.thinker.cabbage.user.service.IntegralLogService;
import vc.thinker.cabbage.user.service.PayHelpService;
import vc.thinker.cabbage.util.CabinetTypeEnum;
import vc.thinker.opensdk.ReturnMsg;
import vc.thinker.opensdk.powerbank.relink.RelinkOpenSDK;
import vc.thinker.opensdk.powerbank.relink.RelinkSlot;
import vc.thinker.pay.PayChannel;
import vc.thinker.pay.PayException;
import vc.thinker.pay.PayHandler;
import vc.thinker.pay.PayHandlerFactory;
import vc.thinker.pay.request.DirectPayRequest;
import vc.thinker.pay.response.DirectPayResponse;
import vc.thinker.sys.bo.UserAccountBO;
import vc.thinker.sys.dao.UserAccountDao;

@Service
@Transactional
public class OrderService {
	
	private static final Logger log=LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private OrderDao orderDao;
	
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	@Lazy(true)
	private PayHandlerFactory payHandlerFactory;
	
	@Autowired
	private UserCouponDao userCouponDao;
	
	@Autowired
	private SellerDao sellerDao;
	
	@Autowired
	private CabinetDao cabinetDao;
	
	@Autowired
	private CabinetStatusDao cabinetStatusDao;
	
	@Autowired
	private PortableBatteryDao portableBatteryDao;
	
	@Autowired
	private PlatformRevenueService platformRevenueService;
	
	@Autowired
	@Lazy
	private CabinetRemoteHandle cabinetRemoteHandle;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private CabinetChargeRuleDao cabinetChargeRuleDao;
	
	@Autowired
	private MoneyService moneyService;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private IntegralLogService integralLogService;
	
	@Autowired
	@Resource(name="relinkSDK")
	private RelinkOpenSDK relinkSDK;
	
	@Autowired
	private CabinetStatusService cabinetStatusService;
	
	@Autowired
	private PayHelpService payhaleService;
	
	/**
	 * 查询用户没有支付的的订单
	 * @param uid
	 * @return
	 */
	public OrderBO findNotPay(Long uid) {
		List<OrderBO> list = orderDao.findByStatus(uid, OrderConstants.ORDER_STATUS_40);
		return list.isEmpty() ? null : list.get(0);
	}
	
	/**
	 * 查询用户没有结束的订单
	 * 
	 * 没有支付 and 行驶中
	 * @param uid
	 * @return
	 */
	public List<OrderBO> findUserNotEndOrder(Long uid) {
		List<OrderBO> orderList= orderDao.findUserNotEnd(uid);
		for (OrderBO order : orderList) {
			if(OrderConstants.ORDER_STATUS_30.equals(order.getStatus())){
				BigDecimal price= computePrice(new Date(), order);
				order.setPrice(price);
				int rideTime =  getOrderTime(order.getBeginTime(), new Date());
				order.setRideTime(rideTime);
			}
		}
		return orderList;
	}
	
	/**
	 * 
	 * @param page
	 * @param uid
	 * @param ltFinishTime
	 * @return
	 */
	public List<OrderBO> findPageListByUid(Page<OrderBO> page,Long uid,Date ltFinishTime){
		return orderDao.findPageListByUid(page,uid,ltFinishTime);
	}
	
	
	public OrderBO findOne(Long id){
		return orderDao.findOne(id);
	}
	
	/**
	 * 分页查询
	 * @param page
	 * @param vo
	 * @return
	 */
	public List<OrderBO> findPageByVo(MyPage<OrderBO> page,OrderVO vo){
		
		List<OrderBO> list = orderDao.findPageByVo(page, vo);
		
		list.forEach(e->{ 
			if(OrderConstants.ORDER_STATUS_30.equals(e.getStatus())){
				BigDecimal price= computePrice(new Date(), e);
				e.setPrice(price);
				int rideTime =  getOrderTime(e.getBeginTime(), new Date());
				e.setRideTime(rideTime);
			}
		});
		return list;
	}
	
	/**
	 * 创建订单主体记录 & 执行借出动作
	 * @param uid
	 * @param sysCode
	 * @param batteryType
	 * @param clientSource
	 * @return
	 */
	@Transactional(readOnly=false)
	public Order createOrder(Long uid,String sysCode,String batteryType,String clientSource){
		//验证member
		MemberBO member=memberDao.findOne(uid);
		validateMember(member);
		
		CabinetBO cabinet=cabinetDao.findBySysCode(sysCode);
		if(cabinet == null){
			throw new CabinetNotFindException("not find");
		}
		SellerBO seller=sellerDao.findOne(cabinet.getSellerId());
		if(!CommonConstants.SELLER_STATUS_NORMAL.equals(seller.getStatus())){
			throw new SellerLockedException("Seller locked");
		}
		
		//检查用户有没有没结算的行程
		List<OrderBO> notEndList = orderDao.findUserNotEndOrder(uid);
		if(notEndList!=null&&notEndList.size()>0){
			throw new UnfinishedOrdersExceptiion("Unfinished orders");
		}
		
		CabinetStatus cs=cabinetStatusDao.findByCid(cabinet.getId());
		if(cs == null){
			throw new CabinetStatusNotFindException("Cabinet Status Not Find");
		}
		
		if(!cabinetStatusDao.checkIsOnline(cs)){
			throw new CabinetIsOfflineException("The device is offline. Please try again later");
		}
		//判断电池数量是否够
		int batteryCount=0;
		switch (batteryType) {
		case CabinetConstants.BATTERY_TYPE_2:
			batteryCount=cs.getBatteryType2Count();
			break;
		case CabinetConstants.BATTERY_TYPE_3:
			batteryCount=cs.getBatteryType3Count();
			break;
		case CabinetConstants.BATTERY_TYPE_4:
			batteryCount=cs.getBatteryType4Count();
			break;
		}
		if(batteryCount <= 0){
			throw new BatteryIsNotEnoughExceptiion();
		}
		
		// 创建订单主体记录
		Order order = this.createOrder(member, cabinet, seller,clientSource);
		
		
		long cabinetType = cabinet.getTypeId();
		/**
		 * 1.RELINK的设备通过http与iot中间件平台通信；
		 * 2.FTJ/YCB的设备，异步触发tcp通知再等待机器上报命令；
		 * 3.YCBB设备
		 */
		if (cabinetType == CabinetTypeEnum.RELINK.getCode()
			    || cabinetType == CabinetTypeEnum.RELINKB.getCode()
			    || cabinetType == CabinetTypeEnum.RELINKC.getCode()) {
			this.relinkOut(cabinet.getCabinetCode(), order.getOrderCode());
		} else if (cabinetType == CabinetTypeEnum.FTJ.getCode() 
				|| cabinetType == CabinetTypeEnum.YCB.getCode()) {
			// 执行借出事件（TransactionalEventListener）
			OutBatteryEvent event=new OutBatteryEvent();
			event.batteryType = batteryType;
			event.order=order;
			publisher.publishEvent(event);
		} else {
			
		}
		return order; 
	}
 
	/**
	 * 1、@TransactionalEventListener控制event事件的处理方式
	 * 2、@Async异步线程处理
	 * @param event
	 */
	@TransactionalEventListener()
	@Async 
	private void outBattery(OutBatteryEvent event){
		cabinetRemoteHandle.out(event.order.getBorrowSysCode()
				,event.order.getOrderCode(), event.batteryType);
	}
	
	/**
	 * 从relink的设备中借出充电宝
	 * @param sdk
	 * @param borrowSysCode
	 * @param orderCode
	 */
	private void relinkOut(String cabinetCode, String orderCode) {
		ReturnMsg<RelinkSlot> ret = relinkSDK.callBorrow(cabinetCode);
		//正常借出
		if (ret.getRetCode() == 0) {
			RelinkSlot slot = ret.getData();
			PortableBattery pb=new PortableBattery();
			pb.setPortableBatteryCode(slot.getSnn());
			pb.setAdapter(true);
			pb.setBattType(CabinetConstants.BATTERY_TYPE_4);
			cabinetStatusService.updateBattery(pb);
			this.beginOrder(orderCode, slot.getSnn(), slot.getNo());
		} else {
			this.updateBorrowCabinetStatus(orderCode, String.valueOf(ret.getRetCode()));
		}
	}
	
	
	/**
	 * 创建订单主体记录
	 * @param orderCode
	 * @param uid
	 * @param borrowType
	 * @param umbrella
	 * @param agent
	 * @return
	 */
	private Order createOrder(MemberBO member,CabinetBO cabinet,SellerBO seller,String clientSource){
		
		//将开锁中的行程设置为失败
		orderDao.updateOrderFail(member.getUid());
		
		Order order = new Order();
		order.setOrderCode(OrderCodeUtils.getOrderCode(member.getUid()));
		order.setUid(member.getUid());
		order.setBeginLocationLat(cabinet.getLocationLat());
		order.setBeginLocationLon(cabinet.getLocationLon());
		order.setBeginLocationDetails(cabinet.getLocationAddress()+cabinet.getLocationDesc());
		order.setCountry(seller.getCountry());
		order.setCurrency(CountryUtil.get(seller.getCountry()).getCurrencyCode());
		order.setBorrowSysCode(cabinet.getSysCode());
		order.setBorrowCabinetId(cabinet.getId());
		order.setBorrowSellerId(cabinet.getSellerId());
		
		order.setIsDeleted(false);
		order.setCreateTime(new Date());
		order.setBeginTime(new Date());
		order.setClientSource(clientSource);
		order.setStatus(OrderConstants.ORDER_STATUS_10);//开锁中
		
		//确定支付方式
		if(member.getIsVIP()){
			order.setPayType(OrderConstants.PAY_TYPE_VIP);
		}else{
			order.setPayType(OrderConstants.PAY_TYPE_BALANCE);
//			order.setPayType(OrderConstants.PAY_TYPE_CASH);
		}
		orderDao.save(order);
		
		return order;
	}
	
	/**
	 * 修改订单失败的数据
	 * @param date
	 * @return
	 */
	public int updateOrderFail(Date date){
		return orderDao.updateOrderFail(date);
	}
	/**
	 * 订单验证member
	 * @param member
	 */
	private void validateMember(MemberBO member){
		if(member.getDeposit().doubleValue() <= 0){
			throw new DepositException("您还未交押金");
		}
	}
	
	/**
	 * 修改订单机柜借出电池状态
	 * @param sysCode
	 */
	public void updateBorrowCabinetStatus(String orderCode,String status){
		OrderBO bo=orderDao.findByOrderCode(orderCode);
		if(bo == null){
			throw new DataNotFindException("Order["+orderCode+"] not find");
		}
		bo.setBorrowCabinetStatusCode(status);
		orderDao.update(bo);
	}
	
	/**
	 * 开始订单
	 * @param sysCode
	 */
	public void beginOrder(String orderCode,String batteryCode,Integer borrowChannel){
		
		OrderBO bo=orderDao.findByOrderCode(orderCode);
		if(bo == null){
			throw new DataNotFindException("Order["+orderCode+"] not find");
		}
		
		PortableBatteryBO portableBattery=portableBatteryDao.findByCode(batteryCode);
		if(portableBattery == null){
			throw new DataNotFindException("Battery["+batteryCode+"] not find");
		}
		bo.setBeginTime(new Date());
		bo.setPbCode(portableBattery.getPortableBatteryCode());
		bo.setPbId(portableBattery.getId());
		bo.setBorrowChannel(borrowChannel);
		bo.setStatus(OrderConstants.ORDER_STATUS_30);
		orderDao.save(bo);
		
		portableBattery.setLocationType(PortableBatteryConstatns.LOCATION_TYPE_IN_USE);
		portableBattery.setMemberId(bo.getUid());
		portableBattery.setLastLocationTime(new Date());
		portableBatteryDao.save(portableBattery);
		
		//如果多个订单设置其它订单失败
		orderDao.updateOrderFail(bo.getUid());
		
		// TODO 创建反润
		//orderRebateService.createRebate(bo);
		
		//积分
		integralLogService.addIntegral(IntegralConstants.INTEGER_CODE_PAY_ORDER, bo.getUid(),
				"Usage", IntegralConstants.INTEGER_TYPE_ORDER, bo.getOrderCode());
		
		//发送消息到个人用户
		OrderMessageEvent event=new OrderMessageEvent();
		event.setContent("订单["+bo.getOrderCode()+"]开始");
		event.setMobileMsgType(SysMessageConstants.MOBILE_MESSAGE_UM_ORDER_CREATE);
		event.setOrderCode(bo.getOrderCode());
		event.setToMember(true);
		publisher.publishEvent(event);
		
	}
	
	/**
	 * 平台结束订单
	 * @param orderCode 订单编号
	 * @param checkDesc 审核备注
	 * @param uid 审核人
	 */
	public OrderBO platformEndOrder(String orderCode,String checkDesc,Long uid){
		//计算时间 计算距离 计算价格
		OrderBO order = orderDao.findByOrderCode(orderCode);
		if(order == null){
			throw new OrderNotFindException("订单编码不存在");
		}
		
		order.setCheckDesc(checkDesc);
		Admin admin = adminDao.get(uid);
		order.setCheckName(admin.getName());
		order.setCheckTime(new Date());
		
		return endOrder(order,OrderConstants.RETURN_TYPE_PLATFORM,null, null);
	}
	
	
	/**
	 * 机柜结束订单，转入未支付
	 */
	public void cabinetEndOrder(Long cabinetId,String batteryCode, Integer slot){
		//校验
		List<OrderBO> list = orderDao.findDoingByBattery(batteryCode);
		if(list.isEmpty()){
			throw new OrderNotFindException("该伞没有进行中的订单");
		}
		//计算时间 计算距离 计算价格
		OrderBO order = list.get(0);
		endOrder(order, OrderConstants.RETURN_TYPE_CABINET,cabinetId, slot);
	}
	
	/**
	 * 结束订单，转入未支付
	 * @param order
	 * @param returnType
	 * @param umMachineCode
	 * @param agendId
	 */
	public OrderBO endOrder(OrderBO order,String returnType,Long cabinetId, Integer slot){
		if(order.getStatus() > OrderConstants.ORDER_STATUS_30){
			throw new OrderHasEndExceptiion("Order has ended");
		}
		
		CabinetBO borrowCabine=cabinetDao.findOne(order.getBorrowCabinetId());
		
		//收费规则
		CabinetChargeRuleBO cabinetChargeRule= 
				cabinetChargeRuleDao.findOne(borrowCabine.getRuleId());
		
		MemberBO member=memberDao.findOne(order.getUid());
		
		Date endTime =new Date();
		
		//还伞机
		if(OrderConstants.RETURN_TYPE_CABINET.equals(returnType)){
			CabinetBO cabinet=cabinetDao.findOne(cabinetId);
			order.setReturnSysCode(cabinet.getSysCode());
			order.setReturnCabinetId(cabinet.getId());
			order.setReturnSellerId(cabinet.getSellerId());
			order.setReturnChannel(slot);
			order.setEndLocationLon(cabinet.getLocationLon());
			order.setEndLocationLat(cabinet.getLocationLat());
			order.setEndLocaitonDetails(cabinet.getLocationAddress()+cabinet.getLocationDesc());
		//还平台
		}else if(OrderConstants.RETURN_TYPE_PLATFORM.equals(returnType)){
//			umbrella.setLocationType(UmbrellaConstants.LOCATION_TYPE_4);
		}
		
		//计算时间 计算距离 计算价格
		order.setFinishTime(endTime);
		order.setReturnType(returnType);
		order.setRideTime(getOrderTime(order.getBeginTime(), order.getFinishTime()));
		order.setStatus(OrderConstants.ORDER_STATUS_40);
		
		
		BigDecimal price=this.computePrice(endTime,order, cabinetChargeRule);
		order.setPrice(price);
		
		//判断是否需要支付,计算支付金额
		BigDecimal payAmout=null;
		switch (order.getPayType()) {
		case OrderConstants.PAY_TYPE_FREE:
			payAmout=new BigDecimal(0);
			break;
		case OrderConstants.PAY_TYPE_VIP:
			payAmout=price.multiply(new BigDecimal(member.getVipDiscount()));
			break;
		case OrderConstants.PAY_TYPE_BALANCE:
			payAmout=price;
			break;
		default:
			payAmout=price;
			break;
		}
		order.setPayPrice(payAmout);
		
		//结束行程 改成未支付
		orderDao.save(order);
		
//		Long time = new Long(getOrderTime(order.getBeginTime(), order.getFinishTime()));
		
		//如果金额为0，行程直接完成
		if(payAmout.doubleValue() <= 0){
			completePay(order,order.getPayType());
		}
		
		//发送消息到个人用户
		OrderMessageEvent event=new OrderMessageEvent();
		event.setContent(order.getOrderCode()+" End order");
		event.setMobileMsgType(SysMessageConstants.MOBILE_MESSAGE_UM_ORDER_END);
		event.setOrderCode(order.getOrderCode());
		event.setToMember(true);
		publisher.publishEvent(event);

		
		return orderDao.findOne(order.getId());
	}

	/**
	 * 支付
	 * @param tid
	 * @param payCallback 回调url
	 * @param paymentMark 支付方式
	 * @return
	 */
	public DirectPayResponse pay(String orderCode,Long cid,String payCallback,String paymentMark){
		OrderBO order=orderDao.findByCode(orderCode);
		
		if(order == null){
			throw new OrderNotFindException("not find order");
		}
		
		if(order.getStatus() > OrderConstants.ORDER_STATUS_40){
			throw new OrderHavePaidException("Order has pay");
		}
		
		BigDecimal totalPrice= order.getPayPrice();
		
		log.info("====cid:"+cid);
		if(cid != null){
			UserCouponBO coupon=userCouponDao.findOne(cid);
			if(coupon == null || !coupon.getUid().equals(order.getUid())||!order.getCurrency().equals(coupon.getCurrency())){
				throw new CouponNotFindException();
			}
			if(!CouponConstants.USER_COUPON_STATUS_1.equals(coupon.getStatus())){
				throw new CouponNotFindException();
			}
			if(coupon.getExpireDate().before(new Date())){
				throw new CouponExpiredException("抱歉，优惠劵已过期");
			}
			totalPrice = totalPrice.subtract(coupon.getAmount());
			
			log.info("======最终的结算金额"+totalPrice);
		}
		
		if(totalPrice.doubleValue() < 0){
			totalPrice = new BigDecimal(0);
		}
		//最后支付金额大于0，发起支付，反之完成支付
		if(totalPrice.doubleValue() > 0){
			//余额
			if(CommonConstants.PAYMENT_MARK_BALANCE.equals(paymentMark)){
				completePayByBalance(order, cid, totalPrice);
				return null;
			}else{
				return thirdPartyPay(order, totalPrice, cid, payCallback,paymentMark);
			}
		}else{
			order.setPaymentMark(paymentMark);
			order.setPayPrice(totalPrice);
			completePay(order, cid);
			return null;
		}
	}
	
	/**
	 * 第三方支付
	 */
	public DirectPayResponse thirdPartyPay(OrderBO order,BigDecimal totalPrice,Long cid,String payCallback,String paymentMark){
		
		String payOrderCode = payhaleService.getPayOrderCode(CommonConstants.PAY_TYPE_ORDER, order.getUid());
		//保存支付信息
		
		OrderBO up_order = new OrderBO();
		up_order.setId(order.getId());
		up_order.setPayOrderCode(payOrderCode);
		up_order.setPaymentMark(paymentMark);
		up_order.setPayPrice(totalPrice);
		up_order.setUserCouponId(cid); //使用的优惠劵
		orderDao.update(up_order);
		
		if(cid == null){
			orderDao.updateUserCouponNull(order.getId());
		}
		
		String notifyUrl = payhaleService.getNotifyUrl(paymentMark, payCallback);
		
		DirectPayRequest payRequest=new DirectPayRequest();
		payRequest.setBody("订单："+order.getOrderCode());
		payRequest.setNotifyUrl(notifyUrl);
		payRequest.setOutTradeNo(payOrderCode);
		payRequest.setReqAttach(CommonConstants.PAY_TYPE_ORDER);
		payRequest.setSubject("Order Pay");
		payRequest.setTotalFee(totalPrice);
		payRequest.setConfigMark(paymentMark);
		
		//strpe 已token为 outTradeNo
		String mobile = "";
		if(PaymentConstants.PAYMENT_MARK_STRIPE.equals(paymentMark)){
			MemberBO member=memberDao.findOne(order.getUid());
			payRequest.setOutTradeNo(member.getStripeCustomerId());
			mobile = member.getMobile();
		} 
		
		try {
			
			DirectPayResponse payResponse = 
					payhaleService.getPayResponse(paymentMark, order.getUid(), payRequest);
			
			if(null == payResponse) {
				throw new ServiceException("调用支付失败");
			}
			
			if(payResponse.isSuccess() && PayChannel.STRIPE == payResponse.getChannel()) {
				completePay(payOrderCode,payResponse.getPayOrderId());
			}
			
			if(payResponse.isSuccess() && PayChannel.CASHFREE == payResponse.getChannel()) {
				payResponse.setOrderCurrency(order.getCurrency());
				payResponse.setNotifyUrl(notifyUrl);
				payResponse.setOrderAmount(String.valueOf(payRequest.getTotalFee()));
				payResponse.setOrderId(payOrderCode);
				payResponse.setCustomerPhone(mobile);
			}
			
			throw new RuntimeException("调用支付失败:"+payResponse.getMsg());
			
		} catch (PayException e) {
			log.error("支付失败",e);
			throw new RuntimeException("调用支付失败",e);
		}
	}
	
	/**
	 * 根据行程id查询行程
	 * @param id 行程id
	 * @return
	 */
	public OrderBO findDetailed(String orderCode) {
		OrderBO order = orderDao.findByCode(orderCode);
		if(order != null){
			if(OrderConstants.ORDER_STATUS_30.equals(order.getStatus())){
				BigDecimal price= computePrice(new Date(), order);
				order.setPrice(price);
				
				int rideTime =  getOrderTime(order.getBeginTime(), new Date());
				order.setRideTime(rideTime);
			}
		}
		return order;
	}
	
	/**
	 * 计算行程总时间
	 * @param begin 开始时间 
	 * @param end   结束时间
	 * @return
	 */
	private int getOrderTime(Date begin,Date end) {
		
		if(null != begin && null != end){
			//计算毫秒值
			long time = (end.getTime() - begin.getTime())/1000;
			//转换分钟
			int min = new Double(Math.ceil(time/60d)).intValue();
			return min;
		}
		return 0;
	}
	
	/**
	 * 余额支付
	 * @param trip
	 * @param cid
	 * @param totalPrice 总支付金额
	 */
	public void completePayByBalance(OrderBO order,Long cid,BigDecimal totalPrice){
//		MemberBO member=memberDao.findOne(order.getUid());
		//减钱
		moneyService.consumptionMoney(order.getUid(),order.getCurrency(),totalPrice, "Consume");
		// 返润
		moneyService.orderRebate(totalPrice, order.getBorrowCabinetId(), order.getId(), order.getOrderCode(), order.getUid());
		order.setPayPrice(totalPrice);
		//  结束订单
		completePay(order,cid);
	}

	/**
	 * 完成支付,第三方支付回调
	 * @param orderCode
	 * @param outOrderId
	 * @return
	 */
	public OrderBO completePay(String orderCode,String outOrderId){
		OrderBO order=orderDao.findByCode(orderCode);
		
		if(order == null){
			throw new ServiceException("订单不存在，完成支付失败");
		}
		return this.completePay(order, OrderConstants.PAY_TYPE_CASH, outOrderId);
	}
	
	
	/**
	 * 完成支付
	 * @param cid
	 * @param propertys
	 * @return
	 */
	public OrderBO completePay(OrderBO order,Long cid){
		
		if(order.getStatus() > OrderConstants.ORDER_STATUS_40){
			throw new OrderHavePaidException("行程已经完成支付,请误重复支付");
		}
		
		order.setUserCouponId(cid);
		//保存支付信息
		orderDao.update(order);
		
		return this.completePay(order, order.getPayType());
	}
	
	/**
	 * 完成支付
	 * @param cid
	 * @param propertys
	 * @return
	 */
	private OrderBO completePay(OrderBO order,String payType){
		return completePay(order, payType, null);
	}
	
	/**
	 * 完成支付
	 * @param cid
	 * @param propertys
	 * @return
	 */
	private OrderBO completePay(OrderBO order,String payType,String outOrderId){
		
		if(order.getStatus() > OrderConstants.ORDER_STATUS_40){
			throw new OrderHavePaidException("Order have paid");
		}
		
		Date date=new Date();
		
		//保存支付信息
		OrderBO payOrder=new OrderBO();
		payOrder.setId(order.getId());
		payOrder.setStatus(OrderConstants.ORDER_STATUS_50);
		payOrder.setPayType(payType);
		payOrder.setOutOrderId(outOrderId);
		payOrder.setPayTime(date);
		orderDao.update(payOrder);
		
		if(order.getUserCouponId() != null){
			//修改为已使用
			UserCoupon coupon=new UserCoupon();
			coupon.setId(order.getUserCouponId());
			coupon.setStatus(CouponConstants.USER_COUPON_STATUS_2);
			userCouponDao.update(coupon);
		}
		
		order=orderDao.findOne(order.getId());
		
		//如果现金支付，添加平台收益
		if(order.getPayPrice() != null && order.getPayPrice().doubleValue() > 0 
				&& OrderConstants.PAY_TYPE_CASH.equals(order.getPayType())){
			platformRevenueService.addPlatformRevenue(order.getId(), CommonConstants.PLATFORM_REVENUE_LOG_ORDER,
					order.getCurrency(), order.getPayPrice(), "User order["+order.getOrderCode()+"] revenue");
		}
		
		//orderRebateService.rebate(order);
		
		//使用统计
		EndOrderEvent event = new EndOrderEvent();
		event.setOrderId(order.getId());
		publisher.publishEvent(event);
		
		return order;
	}

//	/**
//	 * 查询用户没有结束的行程
//	 * 
//	 * 没有支付 and 行驶中
//	 * @param uid
//	 * @return
//	 */
//	public List<UmOrderBO> findUserNotEndOrder(Long uid) {
//		List<UmOrderBO> orderList= orderDao.findUserNotEndTrip(uid);
//		SysSetting setting=sysSettingDao.findOne();
//		for (UmOrderBO order : orderList) {
//			if(OrderConstants.ORDER_STATUS_30.equals(order.getStatus())){
//				BigDecimal price= computePrice(new Date(), order);
//				order.setPrice(price);
//				int rideTime = (int) ((System.currentTimeMillis() - order.getBeginTime().getTime())/1000/60);
//				order.setRideTime(rideTime);
//				order.setIsShowBuy(true);
//				//order.setIsShowBuy(setting.getReminderBuyingTime() * 60 < rideTime);
//				order.setUmbrellaBuy(orderUmbrellaBuyDao.findOneByOrderId(order.getId()));
//			}
//		}
//		return orderList;
//	}
//	
//	/**
//	 * 查询用户没有支付的的订单
//	 * @param uid
//	 * @return
//	 */
//	public UmOrderBO findNotPayTrip(Long uid) {
//		List<UmOrderBO> list = orderDao.findByStatus(uid, OrderConstants.ORDER_STATUS_40);
//		return list.isEmpty() ? null : list.get(0);
//	}
//	
//	/**
//	 * 查询用户进行中的订单
//	 * @param uid
//	 * @return
//	 */
//	public UmOrderBO findHaveInHand(Long uid) {
//		List<UmOrderBO> list = orderDao.findByStatus(uid, OrderConstants.ORDER_STATUS_40);
//		
//		UmOrderBO order= list.isEmpty() ? null : list.get(0);
//		
//		if(order != null){
//			BigDecimal price= computePrice( new Date(),order);
//			order.setPrice(price);
//		}
//		return order;
//	}
	
	/**
	 * 计算行程费用 
	 * @param trip
	 * @param bicycleType
	 * @return
	 */
	public BigDecimal computePrice(Date endTime,OrderBO order,CabinetChargeRuleBO chargeRule){
		if(chargeRule == null){
			throw new OrderHasEndExceptiion(order.getCurrency() + " Charges rules not find");
		}
		
		endTime = endTime == null ? new Date():endTime;
		
		int minute = new Double(Math.ceil((endTime.getTime() - order.getBeginTime().getTime()) /1000d/60d)).intValue();
		
		//判断免费时间
		int surplusMinute=minute - chargeRule.getFreeUseTime();
		if(surplusMinute <= 0){
			return new BigDecimal(0);
		}
		
		//计算每24小时的总价 
		BigDecimal dayPrice =chargeRule.getUnitPrice().multiply(
				new BigDecimal( new Double(Math.ceil(new Double(1440) / chargeRule.getUnitMinute()))
						.intValue()));
		//判断每24小时总价是否超过每天上限价格
		if(dayPrice.doubleValue() >  chargeRule.getCostTop().doubleValue()){
			//计算天单位的总价
			int b = surplusMinute/1440;
			BigDecimal costUppePrice = chargeRule.getCostTop().multiply(new BigDecimal(b));
			//计算未满一天剩余分钟的总价
			int d = new Double(Math.ceil(new Double(surplusMinute%1440) / chargeRule.getUnitMinute())).intValue();
			BigDecimal price = chargeRule.getUnitPrice().multiply( new BigDecimal(d));
			//如果未满一天剩余分钟的总价大于每天上限价格，以上限价格为准
			if(price.doubleValue() > chargeRule.getCostTop().doubleValue()){
				price=chargeRule.getCostTop();
			}
			//未满一天剩余分钟的总价 + 天总价
			price=price.add(costUppePrice);
			return price;
		}else{
			//正常计算
			int d = new Double(Math.ceil(new Double(surplusMinute) / chargeRule.getUnitMinute())).intValue();
			BigDecimal price = chargeRule.getUnitPrice().multiply( new BigDecimal(d));
			return price;
		}
	}
	
	/**
	 * 计算行程费用 
	 * @param trip
	 * @param bicycleType
	 * @return
	 */
	public BigDecimal computePrice(Date endTime,OrderBO order){
		CabinetBO borrowCabine=cabinetDao.findOne(order.getBorrowCabinetId());
		CabinetChargeRuleBO cabinetChargeRule= 
				cabinetChargeRuleDao.findOne(borrowCabine.getRuleId());
		return computePrice(endTime, order, cabinetChargeRule);
	}
	
//	/**
//	 * 分页查询订单列表
//	 */
//	public List<UmOrderBO> findPageByVo(MyPage<UmOrderBO> page, UmOrderVO vo) {
//		
//		List<UmOrderBO> order_list = umOrderDao.findPageByVo(page,vo);
//		if(null != order_list && order_list.size() > 0){
//			List<SysSettingBO> sysSet = sysSettingDao.findAll();
//			int abnormalTime = null != sysSet && null != sysSet.get(0).getAbnormalTime() ? sysSet.get(0).getAbnormalTime():240;
//			// 计算骑行时间
//			order_list.forEach(e->{
//				
//				if(e.getStatus() == OrderConstants.ORDER_STATUS_30){
//					
//					int rideTime = new Double(Math.ceil((System.currentTimeMillis() - e.getBeginTime().getTime()) /1000d/60d)).intValue();
//					
//					e.setRideTime(rideTime);
//					e.setIsAbnormal(rideTime >= abnormalTime);
//					
//					//计算费用
//					UmbrellaBO umbrella = umbrellaDao.findOne(e.getUmbrellaId());
//					UmbrellaTypeBO typeBO = umbrellaTypeDao.findOne(umbrella.getTypeId());
//					
//					int d = new Double(Math.ceil(new Double(rideTime) / typeBO.getCostMinute())).intValue();
//					
//					e.setPrice(typeBO.getCostPrice().multiply(new BigDecimal(String.valueOf(d))));
//					
//				}else if(e.getStatus() > OrderConstants.ORDER_STATUS_30){
//					if(null != e.getRideTime() && e.getRideTime() > abnormalTime){
//						e.setIsAbnormal(true);
//					}
//				}
//			});
//		}
//		
//		return order_list;
//	}
//
//	/**
//	 * 主键查询
//	 * @param id
//	 * @return
//	 */
//	public UmOrderBO findOne(Long id) {
//		return umOrderDao.findOne(id);
//	}
//	
//	/**
//	 * 订单编码查询
//	 * @param id
//	 * @return
//	 */
//	public UmOrderBO findByCode(String orderCode) {
//		return umOrderDao.findByCode(orderCode);
//	}
//
//	/**
//	 * 异常数据查询
//	 * @param page
//	 * @param vo
//	 * @return
//	 */
//	public List<UmOrderBO> findAbnormalList(MyPage<UmOrderBO> page, UmOrderVO vo) {
//		
//		SysSettingBO settingBO = sysSettingDao.findOne();
//		
//		Integer abnormalTime = 
//				 null != settingBO.getAbnormalTime() ? settingBO.getAbnormalTime():240;
//				
//		vo.setAbnormalTime(abnormalTime);
//		
//		List<UmOrderBO> ab_list = umOrderDao.findAbnormalListByPage(page,vo);
//		
//		ab_list.forEach(e->{
//			e.setIsAbnormal(true);
//		});
//		return ab_list;
//	}
//
//	/**
//	 * 详细信息查询
//	 * @param id
//	 * @return
//	 */
//	public UmOrderBO findDetailInfoById(Long id) {
//		return umOrderDao.findDetailInfoById(id);
//	}
//	
	
	public static class OutBatteryEvent{
		private Order order;
		private String batteryType;
	}

	/**
	 * 订单抵扣
	 * 
	 * @param id
	 * @param uid
	 * @param deductionMinute
	 * @param beginTime
	 */
	public void orderDeduction(Long id, Long uid, Integer deductionMinute, Date beginTime) {
		Integer orderTime = getOrderTime(beginTime, new Date());
		log.debug("deductionMinute:[{}], orderTime;[{}]", deductionMinute, orderTime);
		if (deductionMinute <= orderTime) {
			DepositDeductionEvent event = new DepositDeductionEvent();
			event.setUid(uid);
			event.setOrderId(id);
			event.setBeginTime(beginTime);
			publisher.publishEvent(event);
		}
	}
}
