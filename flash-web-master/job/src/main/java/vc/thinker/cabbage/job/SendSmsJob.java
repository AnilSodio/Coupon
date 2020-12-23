package vc.thinker.cabbage.job;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import vc.thinker.cabbage.se.OrderConstants;
import vc.thinker.cabbage.se.bo.OrderBO;
import vc.thinker.cabbage.se.dao.OrderDao;
import vc.thinker.cabbage.sys.dao.SysSettingDao;
import vc.thinker.cabbage.sys.model.SysSetting;

/**
 * 定时发送订单短信提醒
 * 
 * @author ZhangGaoXiang
 * @time Dec 9, 20194:28:47 PM
 */
//@Component
public class SendSmsJob {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private SysSettingDao sysSettingDao;

	private static final Logger logger = LoggerFactory.getLogger(SendSmsJob.class);

	@Scheduled(cron = "0 0/20 * * * ? ")
	public void execute() {

		logger.debug("=========短信提醒====定时任务开启=====");

		SysSetting set = sysSettingDao.findOne();

		if (null == set.getIsOpenSendSms() || !set.getIsOpenSendSms()) {
			// 未开启短信提醒
			return;
		}

		List<OrderBO> list = orderDao.listByStatus(OrderConstants.ORDER_STATUS_30);
		if (list.isEmpty()) {
			return;
		}

		list.forEach(e -> {
			// 发送短信
			sendSms(e.getUid(), set.getSendSmsMinute(), e.getBeginTime());
		});
	}

	/**
	 * 发送提醒短信
	 * 
	 * @param uid
	 * @param sendSmsMinute
	 * @param beginTime
	 */
	public void sendSms(Long uid, Integer sendSmsMinute, Date beginTime) {
		if (sendSmsMinute >= getOrderTime(beginTime)) {
			// 查询用户
		}
	}

	public Integer getOrderTime(Date beginTime) {
		beginTime = null != beginTime ? beginTime : new Date();
		long time = (new Date().getTime() - beginTime.getTime()) / 1000;
		return new Double(Math.ceil(time / 60d)).intValue();
	}
}
