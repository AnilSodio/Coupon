package vc.thinker.cabbage.se;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import vc.thinker.cabbage.se.bo.OrderBO;
import vc.thinker.cabbage.se.dao.OrderDao;
import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.bo.DepositPayLogBO;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.dao.DepositPayLogDao;
import vc.thinker.cabbage.user.dao.MemberDao;
import vc.thinker.cabbage.user.dao.UserDepositLogDao;
import vc.thinker.cabbage.user.model.UserDepositLog;

/**
 *
 * @author ZhangGaoXiang
 * @time Dec 9, 20193:49:47 PM
 */
@Service
public class DepositDeductionService {

	private static final Logger logger = LoggerFactory.getLogger(DepositDeductionService.class);
	
	@Autowired
	private OrderDao orderDao;

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private DepositPayLogDao depositPayLogDao;
	
	@Autowired
	private UserDepositLogDao userDepositLogDao;

	@TransactionalEventListener()
	@Async
	public void sendOrderMessageToMember(DepositDeductionEvent event) {
	
		logger.debug("开始执行押金抵扣业务");
		
		// 结束订单
		OrderBO order = new OrderBO();
		order.setId(event.getOrderId());
		order.setFinishTime(new Date());
		order.setPayTime(new Date());
		order.setRideTime(getOrderTime(event.getBeginTime()));
		order.setPayType(OrderConstants.PAY_TYPE_PB_BUY);
		order.setStatus(OrderConstants.ORDER_STATUS_50);
		orderDao.update(order);

		// 扣除押金
		MemberBO member = new MemberBO();
		member.setUid(event.getUid());
		member.setDeposit(BigDecimal.ZERO);
		memberDao.update(member);

		
		DepositPayLogBO payLog = depositPayLogDao.getLastPaySuccessByUid(event.getUid());
		if (null != payLog) {
			// 修改支付记录
			DepositPayLogBO up_log = new DepositPayLogBO();
			up_log.setId(payLog.getId());
			up_log.setIsCapture(true);
			up_log.setCaptureTime(new Date());
			up_log.setStatus(CommonConstants.DEPOSIT_PAY_STATUS_5);
			depositPayLogDao.update(up_log);
			
			// 新增押金日志
			UserDepositLog depositLog = new UserDepositLog();
			depositLog.setUid(event.getUid());
			depositLog.setCreateTime(new Date());
			depositLog.setPayMark(payLog.getPaymentMark());
			depositLog.setAmount(payLog.getAmount());
			depositLog.setType(CommonConstants.MEMBER_DEPOSIT_TYPE_4);
			userDepositLogDao.save(depositLog);
		}
		
		
	}

	public Integer getOrderTime(Date beginTime) {
		long time = (new Date().getTime() - beginTime.getTime()) / 1000;
		return new Double(Math.ceil(time / 60d)).intValue();
	}

	public static class DepositDeductionEvent {

		private Long uid;

		private Long orderId;

		private Date beginTime;

		public Long getUid() {
			return uid;
		}

		public void setUid(Long uid) {
			this.uid = uid;
		}

		public Long getOrderId() {
			return orderId;
		}

		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}

		public Date getBeginTime() {
			return beginTime;
		}

		public void setBeginTime(Date beginTime) {
			this.beginTime = beginTime;
		}

	}
}
