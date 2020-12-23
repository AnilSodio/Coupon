package vc.thinker.cabbage.stats;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinco.common.utils.StringUtils;

import vc.thinker.cabbage.stats.bo.BalanceStatsBO;
import vc.thinker.cabbage.stats.bo.DepositStatsBO;
import vc.thinker.cabbage.stats.bo.OrderStatsBO;
import vc.thinker.cabbage.stats.bo.RealTimeGroupStatsBO;
import vc.thinker.cabbage.stats.bo.RealTimeStatsBO;
import vc.thinker.cabbage.stats.bo.VipStatsBO;
import vc.thinker.cabbage.stats.dao.BalanceStatsDao;
import vc.thinker.cabbage.stats.dao.DepositStatsDao;
import vc.thinker.cabbage.stats.dao.OrderStatsDao;
import vc.thinker.cabbage.stats.dao.RegisterStatsDao;
import vc.thinker.cabbage.stats.dao.VipStatsDao;
import vc.thinker.cabbage.sys.dao.PlatformRevenueDao;
import vc.thinker.cabbage.user.dao.DepositPayLogDao;
import vc.thinker.cabbage.user.dao.UserMoneyRechargeDao;


/**
 * 实时数据统计
 */
@Service
@Transactional
public class CubeOfDataService {

	@Autowired
	private OrderStatsDao orderStatsDao;
	
	@Autowired
	private RegisterStatsDao registerStatsDao;
	
	@Autowired 
	private DepositStatsDao depositStatsDao;
	
	@Autowired
	private BalanceStatsDao balanceStatsDao;
	
	@Autowired
	private VipStatsDao vipStatsDao;
	
	@Autowired
	private PlatformRevenueDao platformRevenueDao;
	
	@Autowired
	private DepositPayLogDao depositPayLogDao;
	
	@Autowired
	private UserMoneyRechargeDao userMoneyRechargeDao;
	
	public RealTimeStatsBO countByDate(String date) {
		RealTimeStatsBO stats = new RealTimeStatsBO();
		//使用次数 已经消费金额
		OrderStatsBO order = orderStatsDao.countByDate(date);
		stats.setTripTimes(order.getOrderNum());
//		stats.setConsume(order.getActualConsume());
		//注册人数
		BigDecimal users = registerStatsDao.countByDate(date);
		stats.setRegistNums(users);
		//押金充值金额
		BigDecimal deposit = depositPayLogDao.sumByData(date);
		stats.setDeposit(deposit);
//		Map<String, BigDecimal> countryDepositMap = new HashMap<String, BigDecimal>();
//		List<DepositStatsBO> deposits = depositStatsDao.sumByDateGouupByCurrency(date);
//		if(null != deposits && deposits.size() > 0 && null != deposits.get(0)) {
//			deposits.forEach(e->{
//				if(!StringUtils.isEmpty(e.getCurrency())){
//					countryDepositMap.put(e.getCurrency(), e.getDeposit());
//				}
//			});
//		}
		
		//充会员卡购买金额
//		List<VipStatsBO> vipPay = vipStatsDao.sumByDateGouupByCurrency(date);
//		Map<String, BigDecimal> countryVIPPayMap = new HashMap<String, BigDecimal>();
//		if(null != vipPay && vipPay.size()>0) {
//			vipPay.forEach(e -> {
//				if(!StringUtils.isEmpty(e.getCurrency())){
//					countryVIPPayMap.put(e.getCurrency(), e.getPay());
//				}
//			});
//		}
		//余额充值金额
		BigDecimal balance = userMoneyRechargeDao.sumByDate(date);
		stats.setBalance(balance);
//		List<BalanceStatsBO> balance = balanceStatsDao.sumByDateGouupByCurrency(date);
//		Map<String, BigDecimal> countryBalanceMap = new HashMap<String, BigDecimal>();
//		if(null != balance && balance.size()>0){
//			balance.forEach(e -> {
//				if(!StringUtils.isEmpty(e.getCurrency())){
//					countryBalanceMap.put(e.getCurrency(), e.getPayAmount());
//				}
//			});
//		}
		
		//平台收益
		BigDecimal platformIncome = platformRevenueDao.sumByDate(date);
		stats.setPlatform_income(platformIncome);
		
		return stats;
	}

	public RealTimeStatsBO countAvgByDate(String beginDate, String endDate) {
		RealTimeStatsBO stats = new RealTimeStatsBO();
		//行程
		OrderStatsBO trip = orderStatsDao.countAvgByDate(beginDate,endDate);
		stats.setTripTimes(trip.getOrderNum());
//		stats.setConsume(trip.getActualConsume());
		//注册人数
		BigDecimal users = registerStatsDao.countAvgByDate(beginDate,endDate);
		stats.setRegistNums(users);
		//押金
		List<DepositStatsBO> deposit = depositStatsDao.sumAvgByDateGroupByCurrency(beginDate,endDate);
		Map<String, BigDecimal> countryDepositMap = new HashMap<String, BigDecimal>();
		if(null != deposit && deposit.size()>0){
			deposit.forEach(e->{
				if(!StringUtils.isEmpty(e.getCurrency())){
					countryDepositMap.put(e.getCurrency(), e.getDeposit());
				}
			});
		}
		//充会员卡金额
		List<VipStatsBO> vipPay = vipStatsDao.sumAvgByDateGroupByCurrency(beginDate,endDate);
		Map<String, BigDecimal> countryVIPPayMap = new HashMap<String, BigDecimal>();
		if(null != vipPay && vipPay.size()>0 && null != vipPay.get(0)){
			vipPay.forEach(e->{
				if(!StringUtils.isEmpty(e.getCurrency())){
					countryVIPPayMap.put(e.getCurrency(), e.getPay());
				}
			});
		}
		//余额充值金额
		List<BalanceStatsBO> balance = balanceStatsDao.sumAvgByDateGroupByCurrency(beginDate,endDate);
		Map<String, BigDecimal> countryBalanceMap = new HashMap<String, BigDecimal>();
		if(null != balance && balance.size()>0){
			balance.forEach(e->{
				if(!StringUtils.isEmpty(e.getCurrency())){
					countryBalanceMap.put(e.getCurrency(), e.getPayAmount());
				}
			});
		}
		
		return stats;
	}
	
	public List<RealTimeGroupStatsBO> findTripInfoByGroupType(String groupType, String date,String type) {
		return orderStatsDao.findTripInfoByGroupType(groupType,date,type);
	}

	public BigDecimal findTripInfoByTimeRange(String day,String time1, String time2,String type ) {
		return orderStatsDao.findTripInfoByTimeRange(day,time1,time2,type);
	}

	public BigDecimal findTripInfoByAgeRange(Integer age1, Integer age2, String date,String type) {
		return orderStatsDao.findTripInfoByAgeRange(age1,age2,date,type);
	}

	public List<RealTimeGroupStatsBO> findRegistInfoByGroupType(String groupType, String date) {
		return registerStatsDao.findRegistInfoByGroupType(groupType,date);
	}

	public BigDecimal findRegistByTimeRange(String day,String date1, String date2) {
		return registerStatsDao.findRegistByTimeRange(day,date1,date2);
	}
	public BigDecimal findRegistByAgeRange(Integer age1, Integer age2, String date) {
		return registerStatsDao.findRegistByAgeRange(age1,age2,date);
	}

	public BigDecimal findVipPayByTimeRange(String day,String date1, String date2) {
		return vipStatsDao.findVipPayByTimeRange(day,date1,date2);
	}

	public BigDecimal findVipPayByAgeRange(Integer age1, Integer age2, String date) {
		return vipStatsDao.findVipPayByAgeRange(age1,age2,date);
	}

	public List<RealTimeGroupStatsBO> findVipPayByGroupType(String groupType, String date) {
		return vipStatsDao.findVipPayByGroupType(groupType,date);
	}

	public List<RealTimeGroupStatsBO> findBalanceByGoupType(String groupType, String date) {
		return balanceStatsDao.findBalanceByGoupType(groupType,date);
	}

	
	
}
