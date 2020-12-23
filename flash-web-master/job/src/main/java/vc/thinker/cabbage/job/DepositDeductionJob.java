package vc.thinker.cabbage.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vc.thinker.cabbage.se.OrderConstants;
import vc.thinker.cabbage.se.OrderService;
import vc.thinker.cabbage.se.bo.OrderBO;
import vc.thinker.cabbage.se.dao.OrderDao;
import vc.thinker.cabbage.sys.dao.SysSettingDao;
import vc.thinker.cabbage.sys.model.SysSetting;

/**
 * 押金抵扣任务
 * 
 * @author ZhangGaoXiang
 * @time Dec 9, 20192:59:55 PM
 */
//@Component
public class DepositDeductionJob {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private SysSettingDao sysSettingDao;
	
	@Autowired
	private OrderService orderService;
	
	private static final Logger logger = LoggerFactory.getLogger(DepositDeductionJob.class);

	@Scheduled(cron = "0 0/1 * * * ? ")
	public void execute() {

		logger.debug("=========押金抵扣===定时任务开启======");
		
		SysSetting set = sysSettingDao.findOne();

		if (null == set.getIsOpenDepositDeduction() || !set.getIsOpenDepositDeduction()) {
			//未开启押金抵扣
			return;
		}
		
		List<OrderBO> list = orderDao.listByStatus(OrderConstants.ORDER_STATUS_30);
		if (list.isEmpty()) {
			return;
		}
		
		list.forEach(e -> {
			// 订单超时,押金抵扣
			orderService.orderDeduction(e.getId(), e.getUid(), set.getDeductionMinute(), e.getBeginTime());
		});
		
		logger.debug("=========押金抵扣===定时任务结束======");
	}

}
