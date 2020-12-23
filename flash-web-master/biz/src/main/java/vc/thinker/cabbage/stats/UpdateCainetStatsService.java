package vc.thinker.cabbage.stats;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import cn.jiguang.common.utils.StringUtils;
import vc.thinker.cabbage.se.dao.CabinetDao;
import vc.thinker.cabbage.stats.bo.CabinetStatsBO;
import vc.thinker.cabbage.stats.dao.CabinetStatsDao;

@Service
public class UpdateCainetStatsService {

	private Logger logger = LoggerFactory.getLogger(UpdateCainetStatsService.class);
	
	@Autowired
	private CabinetStatsDao cabinetStatsDao;
	
	@Autowired
	private CabinetDao cabinetDao;
	
	@TransactionalEventListener
	@Async
	public void updateCabinetCreateStats(CabinetCreateEvent event) {
		
		String currentDay = event.getCurrentDay();
		
		if(!StringUtils.isEmpty(currentDay)){
			logger.debug("数据 currentDay:【"+currentDay+"】为空，跳过");
			return ;
		}
		
		CabinetStatsBO stats = cabinetStatsDao.findByCurrentDay(currentDay);
		if(null != stats){
			CabinetStatsBO up_stats = new CabinetStatsBO();
			up_stats.setId(stats.getId());
			up_stats.setTotalNum(stats.getTotalNum() + 1);
			cabinetStatsDao.update(up_stats);
		}else {
			long totalNum = cabinetDao.count();
			CabinetStatsBO inset_stats = new CabinetStatsBO();
			inset_stats.setCurrentDay(currentDay);
			inset_stats.setTotalNum(totalNum+1);
			inset_stats.setCreateTime(new Date());
			
			cabinetStatsDao.save(inset_stats);
		}
		
	}
	
	public static class CabinetCreateEvent{
		
		private String currentDay;

		public String getCurrentDay() {
			return currentDay;
		}

		public void setCurrentDay(String currentDay) {
			this.currentDay = currentDay;
		}
		
	}
}
