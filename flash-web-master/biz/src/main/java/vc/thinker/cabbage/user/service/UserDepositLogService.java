package vc.thinker.cabbage.user.service;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sinco.data.core.Page;

import vc.thinker.cabbage.common.PaymentConstants;
import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.bo.DepositPayLogBO;
import vc.thinker.cabbage.user.bo.UserDepositLogBO;
import vc.thinker.cabbage.user.dao.DepositPayLogDao;
import vc.thinker.cabbage.user.dao.UserDepositLogDao;
import vc.thinker.cabbage.user.vo.UserDepositLogVO;
import vc.thinker.cabbage.util.DateTimeUtils;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.pay.PayChannel;
import vc.thinker.pay.PayException;
import vc.thinker.pay.PayHandler;
import vc.thinker.pay.PayHandlerFactory;
import vc.thinker.pay.response.RefundResponse;
import vc.thinker.refund.bean.RefundRequest;
import vc.thinker.sys.model.User;
import vc.thinker.cabbage.user.service.UpdateUserStatsService.UserDepositRefundEvent;

@Service
@Transactional
public class UserDepositLogService {
	
	private Logger log = LoggerFactory.getLogger(UserDepositLogService.class);
	
	@Autowired
	private UserDepositLogDao userDepositLogDao;
	
	@Autowired
	@Lazy(true)
	private PayHandlerFactory payHandlerFactory;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private DepositPayLogDao depositPayLogDao;
	
	public List<UserDepositLogBO> findPageByVO(Page<UserDepositLogBO> page,UserDepositLogVO vo ){
		return userDepositLogDao.findPageByVO(page,vo);
	}
	
	/**
	 * 根据用户id 分页查询用户的押金操作记录
	 * @param uid 用户id
	 * @return
	 */
	public List<UserDepositLogBO> selectPageByUid(MyPage<UserDepositLogBO> page, UserDepositLogVO vo) {
		return userDepositLogDao.selectPageByUid(page,vo);
	}
	
	/**
	 * 主键查询
	 * @param id 主键id
	 * @return
	 */
	public UserDepositLogBO findOne(Long id) {
		return userDepositLogDao.findOne(id);
	}
	
	/**
	 * 退款审核
	 * @param user  操作人
	 * @param payLog  支付流水
	 * @param refundRemark 退款备注
	 * @param refundAccount 退款账户
	 * @return
	 */
	public String weixinRefund(User user,DepositPayLogBO payLog, String refundRemark, String refundAccount)  {
		
		//查询支付信息
		log.info(" 押金编号为 【"+payLog.getId()+"】的记录开始退款");
		
		RefundRequest request = new RefundRequest();
		
		// 微信订单号
		request.setTransaction_id(payLog.getOutOrderId());
		
		//商户退款单号
		request.setOut_refund_no(DateTimeUtils.getDateTimeMils());
		
		//订单金额和退款金额
		BigDecimal total_fee = payLog.getAmount().multiply(new BigDecimal(100));
		BigDecimal refund_fee = null != payLog.getCashFee() ? payLog.getCashFee():total_fee;
	
		request.setTotal_fee(total_fee.intValue());
		request.setRefund_fee(refund_fee.intValue());
		request.setRefund_account(refundAccount);
//			请求微信
		PayHandler payHandler=  payHandlerFactory.getPayHandler(payLog.getPaymentMark());
		
		request.setConfigMark(payLog.getPaymentMark());
		
		try{
			//发起请求
			RefundResponse resp = payHandler.refund(request);
			
			if(resp.isSuccess()){
				
				/**
				 * 退款成功，修改退款记录状态
				 */
				DepositPayLogBO up_info = new DepositPayLogBO();
				up_info.setId(payLog.getId());
				up_info.setStatus(CommonConstants.DEPOSIT_PAY_STATUS_4);
				up_info.setRefundAccount(refundAccount);
				up_info.setRefundOperator(user.getId());
				up_info.setRefundSuccTime(new Date());
				up_info.setRefundId(resp.getOutOrderId());
				up_info.setRefundRemark(refundRemark);
				depositPayLogDao.update(up_info);
				
				UserDepositLogBO userDeposit = userDepositLogDao.findbyOutRefundNo(payLog.getOutOrderId());
				if(null != userDeposit){
					UserDepositLogBO up_bo = new UserDepositLogBO();
					up_bo.setId(userDeposit.getId());
					up_bo.setType(CommonConstants.MEMBER_DEPOSIT_TYPE_3);
					
					userDepositLogDao.update(up_bo);
				}
				
				UserDepositRefundEvent depositRefundEvent = new UserDepositRefundEvent();
				depositRefundEvent.setDepositPayId(payLog.getId());
				publisher.publishEvent(depositRefundEvent);
				
				return "true";
				
			}else {
				//错误码
				String err_code = resp.getErrorCode();
				//错误信息
				String err_code_des = resp.getMsg();
				
				log.info("退款错误信息为:"+ err_code+" "+err_code_des);
				
				DepositPayLogBO up_info = new DepositPayLogBO();
				up_info.setId(payLog.getId());
				up_info.setRefundErrorCode(err_code);
				up_info.setRefundErrorMessage(err_code_des);
				up_info.setRefundOperator(user.getId());
				
				depositPayLogDao.update(up_info);
				
				return err_code_des;
			}
			
		}catch (PayException e) {
			log.error("微信退款异常",e);
			return "微信退款异常";
		}
		
	}
	
	
	/**
	 * 支付宝支付退款
	 * @param user 当前操作人
	 * @param payLog 支付流水
	 * @param refundRemark 退款备注
	 * @return
	 */
	public String alipayRefund (User user,DepositPayLogBO payLog, String refundRemark) {
		
		
			RefundRequest request = new RefundRequest();
			
			request.setTrade_no(payLog.getOutOrderId());
			request.setOut_trade_no(payLog.getPayOrderCode());
			BigDecimal refund_amount = payLog.getCashFee();
			if(null != refund_amount){
//				refund_amount = refund_amount.divide(new BigDecimal(100));
			}else {
				refund_amount = payLog.getAmount();
			}
			request.setRefund_amount(refund_amount);
			request.setConfigMark(payLog.getPaymentMark());
			request.setOut_refund_no(DateTimeUtils.getDateTimeMils());
			
			
			PayHandler payHandler = payHandlerFactory.getPayHandler(PayChannel.ALIPAY);
			
			try {
				
				RefundResponse resp = payHandler.refund(request);
				
				if(resp.isSuccess()){
					
					DepositPayLogBO up_bo = new DepositPayLogBO();
					
					up_bo.setId(payLog.getId());
					up_bo.setStatus(CommonConstants.DEPOSIT_PAY_STATUS_4);
					up_bo.setRefundOperator(user.getId());
					up_bo.setRefundSuccTime(new Date());
					up_bo.setRefundRemark(refundRemark);
					
					depositPayLogDao.update(up_bo);
					
					
					UserDepositLogBO userDeposit = userDepositLogDao.findbyOutRefundNo(payLog.getOutOrderId());
					if(null != userDeposit){
						UserDepositLogBO user_deposit = new UserDepositLogBO();
						user_deposit.setId(userDeposit.getId());
						user_deposit.setType(CommonConstants.MEMBER_DEPOSIT_TYPE_3);
						
						userDepositLogDao.update(user_deposit);
					}
					
					UserDepositRefundEvent depositRefundEvent = new UserDepositRefundEvent();
					depositRefundEvent.setDepositPayId(payLog.getId());
					publisher.publishEvent(depositRefundEvent);
					
					return "true";
				}else {
					
					DepositPayLogBO up_info = new DepositPayLogBO();
					up_info.setId(payLog.getId());
					up_info.setRefundErrorMessage(resp.getMsg());
					up_info.setRefundOperator(user.getId());
					
					depositPayLogDao.update(up_info);
					
					return resp.getMsg();
				}
			} catch (PayException e) {
				e.printStackTrace();
			}
			
		return "系统异常，请稍后再试";
	}
	
	public static void main(String args[]){
		BigDecimal a = new BigDecimal(990);
		System.out.println(a.divide(new BigDecimal(100)).intValue());
	}

	/**
	 * stripe 退款
	 * @param user
	 * @param info
	 * @param refundRemark
	 * @return
	 */
	public String stripeRefund(User user, DepositPayLogBO payLog, String refundRemark) {
		
		RefundRequest refundRequest  = new RefundRequest();
		
		refundRequest.setTransaction_id(payLog.getOutOrderId());
		refundRequest.setConfigMark(PaymentConstants.PAYMENT_MARK_STRIPE);
		
		PayHandler payHandler=  payHandlerFactory.getPayHandler(PaymentConstants.PAYMENT_MARK_STRIPE);
		
		try {
			RefundResponse resp = payHandler.refund(refundRequest);
			
			if(resp.isSuccess()){
				DepositPayLogBO up_info = new DepositPayLogBO();
				up_info.setId(payLog.getId());
				up_info.setStatus(CommonConstants.DEPOSIT_PAY_STATUS_4);
				up_info.setRefundOperator(user.getId());
				up_info.setRefundSuccTime(new Date());
				up_info.setRefundId(resp.getOutOrderId());
				up_info.setRefundRemark(refundRemark);
				up_info.setRefundAccount(PaymentConstants.PAYMENT_MARK_STRIPE);
				depositPayLogDao.update(up_info);
				
				UserDepositLogBO userDeposit = userDepositLogDao.findbyOutRefundNo(payLog.getOutOrderId());
				if(null != userDeposit){
					UserDepositLogBO up_bo = new UserDepositLogBO();
					up_bo.setId(userDeposit.getId());
					up_bo.setType(CommonConstants.MEMBER_DEPOSIT_TYPE_3);
					userDepositLogDao.update(up_bo);
				}
				
				UserDepositRefundEvent depositRefundEvent = new UserDepositRefundEvent();
				depositRefundEvent.setDepositPayId(payLog.getId());
				publisher.publishEvent(depositRefundEvent);
				
				return "true";
			}else {
				DepositPayLogBO up_info = new DepositPayLogBO();
				up_info.setId(payLog.getId());
				up_info.setRefundErrorMessage(resp.getMsg());
				up_info.setRefundOperator(user.getId());
				
				depositPayLogDao.update(up_info);
				
				return resp.getMsg();
			}
			
		} catch (PayException e) {
			log.error("STRIPE 退款异常:"+e.getMessage());
			return "STRIPE 退款异常" + e.getMessage();
		}
	}
}
