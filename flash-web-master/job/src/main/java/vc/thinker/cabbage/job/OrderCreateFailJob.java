package vc.thinker.cabbage.job;

import java.util.Calendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import vc.thinker.cabbage.se.OrderService;

/**
 * 行程开锁失败处理
 * @author james
 *
 */
//@Component
public class OrderCreateFailJob {
	
	private static final Logger log = LoggerFactory.getLogger(OrderCreateFailJob.class);
	
	@Autowired
	private OrderService orderService;
	
	@Value("${order.fail.second}")
	private int second = 120;
	
	@Scheduled(cron="0/5 * * * * ? ")
	public void execute(){
		Calendar date=Calendar.getInstance();
		date.add(Calendar.SECOND, -second);
		int result = orderService.updateOrderFail(date.getTime());
		log.info("修改订单创建失败记录[{}]条",result);
			
		
	}
}
