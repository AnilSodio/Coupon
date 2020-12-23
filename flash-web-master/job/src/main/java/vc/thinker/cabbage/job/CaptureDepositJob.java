package vc.thinker.cabbage.job;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import vc.thinker.cabbage.common.PaymentConstants;
import vc.thinker.cabbage.user.DepositLogConstants;
import vc.thinker.cabbage.user.bo.DepositPayLogBO;
import vc.thinker.cabbage.user.dao.DepositPayLogDao;
import vc.thinker.cabbage.user.service.MemberService;

/**
 * 锁定押金任务
 * @author james
 *
 */
//@Component
public class CaptureDepositJob {
	
	private static final Logger log = LoggerFactory.getLogger(CaptureDepositJob.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private DepositPayLogDao depositPayLogDao;
	
	//支付多少天后执行锁定
	private int day = 5;
	
	@Scheduled(cron="0 0/5 * * * ? ")
	public void execute(){
		Calendar date=Calendar.getInstance();
		date.add(Calendar.DAY_OF_YEAR, -day);
		List<DepositPayLogBO> list=depositPayLogDao.findByCapture(false,
				DepositLogConstants.PAY_LOG_STATUS_2
				,PaymentConstants.PAYMENT_MARK_STRIPE
				,date.getTime());
		log.info("查找需要锁定押金记录[{}]条",list.size());
		for (DepositPayLogBO depositPayLog : list) {
			try {
				memberService.captureDeposit(depositPayLog);
			} catch (Exception e) {
				log.error("锁定押金执行失败",e);
			}
		}
	}
}
