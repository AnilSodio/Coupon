//package vc.thinker.cabbage.job;
//
//import java.util.Date;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.sinco.common.utils.DateUtils;
//
//import vc.thinker.cabbage.stats.StatsService;
//
///**
// * 每日统计
// *
// */
//@Component
//public class SumStatsJob {
//	
//	private static final Logger log = LoggerFactory.getLogger(SumStatsJob.class);
//	
//	@Autowired
//	private StatsService statsService;
//	
//	@Scheduled(cron="0/20 * * * * ? ")
//	public void execute1(){
//		statsService.sumDayStats();
//	}
//	
//	
//	//每天23点50执行
//	@Scheduled(cron="0 50 23 * * ? ") 
//	public void execute2(){
//		String today = DateUtils.formatDate(new Date());
//		statsService.resetData( today, today);
//	}
//}
