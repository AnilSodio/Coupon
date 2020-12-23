package vc.thinker.cabbage.job;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sinco.common.utils.DateUtils;

import vc.thinker.cabbage.se.dao.CabinetDao;
import vc.thinker.cabbage.stats.bo.CabinetStatsBO;
import vc.thinker.cabbage.stats.bo.DepositStatsBO;
import vc.thinker.cabbage.stats.bo.IncomeStatsBO;
import vc.thinker.cabbage.stats.dao.CabinetStatsDao;
import vc.thinker.cabbage.stats.dao.DepositStatsDao;
import vc.thinker.cabbage.stats.dao.IncomeStatsDao;
import vc.thinker.cabbage.stats.model.CabinetStats;
import vc.thinker.cabbage.sys.dao.PlatformRevenueDao;
import vc.thinker.cabbage.sys.dao.SysSettingDao;
import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.dao.MemberDao;

/**
 * 每日统计 押金值、充电宝数量、平台收益
 * @author thinker
 *
 */
@Component
public class DailyStatisticsJob {

	private static final Logger logger = LoggerFactory.getLogger(DailyStatisticsJob.class);
	
	@Autowired
	private CabinetStatsDao cabinetStatsDao;
	
	@Autowired
	private CabinetDao cabinetDao;
	
	@Autowired
	private DepositStatsDao depositStatsDao;

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private PlatformRevenueDao platformRevenueDao;
	
	@Autowired
	private IncomeStatsDao incomeStatsDao;
	
	@Autowired
	private SysSettingDao sysSettingDao;
	
	@Scheduled(cron="0 0 1 * * ?")
	public void execute(){
		
		logger.info("@@@@@每日统计正常运行");
		
		// 充电宝每日统计
		String currentDay = DateUtils.formatDate(new Date());
		CabinetStatsBO info = cabinetStatsDao.findByCurrentDay(currentDay);
		if(null == info){
			long totalNum = cabinetDao.count();
			CabinetStats stats = new CabinetStats();
			stats.setCurrentDay(currentDay);
			stats.setTotalNum(totalNum);
			stats.setCreateTime(new Date());
			cabinetStatsDao.save(stats);
		}
		
		//平台收益 每日统计
		IncomeStatsBO income = incomeStatsDao.findbyCurrentDay(currentDay);
		if(null == income){
			BigDecimal sumByDate = platformRevenueDao.sumByDate(null);
			
			SysSetting sysSetting = sysSettingDao.findOne();
			IncomeStatsBO in_bo = new IncomeStatsBO();
			in_bo.setCurrentDay(currentDay);
			in_bo.setAmount(sumByDate);
			in_bo.setCurrency(sysSetting.getPlayformDefaultCurrency());
			in_bo.setCreateTime(new Date());
			in_bo.setIncomeType(CommonConstants.INCOME_TYPE_PLATFORM);
			incomeStatsDao.save(in_bo);
		}
		
		//押金统计
		DepositStatsBO cny_deposit = depositStatsDao.findbyCurrentDay(currentDay,
				CommonConstants.CURRENCY_CNY);
		logger.info("@@@@cny_deposit:"+cny_deposit);
		if(null == cny_deposit){
			
			BigDecimal sumDeposit = memberDao.sumDepositByCurrency(CommonConstants.CURRENCY_CNY);

			DepositStatsBO in_bo = new DepositStatsBO();

			in_bo.setDeposit(sumDeposit);
			in_bo.setCurrentDay(currentDay);
			in_bo.setCreateTime(new Date());
			in_bo.setCurrency(CommonConstants.CURRENCY_CNY);

			depositStatsDao.save(in_bo);
		}
		
		DepositStatsBO myr_deposit = depositStatsDao.findbyCurrentDay(currentDay,
				CommonConstants.CURRENCY_MYR);
		logger.info("@@@@myr_deposit:"+myr_deposit);
		if(null == myr_deposit){
			
			BigDecimal sumDeposit = memberDao.sumDepositByCurrency(CommonConstants.CURRENCY_MYR);

			DepositStatsBO in_bo = new DepositStatsBO();

			in_bo.setDeposit(sumDeposit);
			in_bo.setCurrentDay(currentDay);
			in_bo.setCreateTime(new Date());
			in_bo.setCurrency(CommonConstants.CURRENCY_MYR);

			depositStatsDao.save(in_bo);
			
		}
		
		DepositStatsBO sgd_deposit = depositStatsDao.findbyCurrentDay(currentDay,
				CommonConstants.CURRENCY_SGD);
		logger.info("@@@@sgd_deposit:"+sgd_deposit);
		if(null == sgd_deposit){
			BigDecimal sumDeposit = memberDao.sumDepositByCurrency(CommonConstants.CURRENCY_SGD);

			DepositStatsBO in_bo = new DepositStatsBO();

			in_bo.setDeposit(sumDeposit);
			in_bo.setCurrentDay(currentDay);
			in_bo.setCreateTime(new Date());
			in_bo.setCurrency(CommonConstants.CURRENCY_SGD);

			depositStatsDao.save(in_bo);
		}
	}
}
