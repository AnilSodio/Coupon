package vc.thinker.cabbage.stats;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.sinco.common.utils.DateUtils;

import vc.thinker.cabbage.se.OrderConstants;
import vc.thinker.cabbage.se.SeCommonConstants;
import vc.thinker.cabbage.se.bo.FeedbackBO;
import vc.thinker.cabbage.se.bo.OrderBO;
import vc.thinker.cabbage.se.bo.PercentBo;
import vc.thinker.cabbage.se.bo.UserRebateLogBO;
import vc.thinker.cabbage.se.dao.CabinetDao;
import vc.thinker.cabbage.se.dao.FeedbackDao;
import vc.thinker.cabbage.se.dao.OrderDao;
import vc.thinker.cabbage.se.dao.PortableBatteryDao;
import vc.thinker.cabbage.se.dao.UserRebateLogDao;
import vc.thinker.cabbage.se.model.UserRebateLog;
import vc.thinker.cabbage.stats.UpdateOrderStatsService.EndOrderEvent;
import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.bo.DayStatsBO;
import vc.thinker.cabbage.stats.bo.OrderStatsBO;
import vc.thinker.cabbage.stats.bo.RegisterStatsBO;
import vc.thinker.cabbage.stats.dao.BalanceStatsDao;
import vc.thinker.cabbage.stats.dao.CabinetStatsDao;
import vc.thinker.cabbage.stats.dao.DayStatsDao;
import vc.thinker.cabbage.stats.dao.DepositStatsDao;
import vc.thinker.cabbage.stats.dao.IncomeStatsDao;
import vc.thinker.cabbage.stats.dao.OrderStatsDao;
import vc.thinker.cabbage.stats.dao.RegisterStatsDao;
import vc.thinker.cabbage.stats.dao.VipStatsDao;
import vc.thinker.cabbage.stats.model.DayStats;
import vc.thinker.cabbage.stats.vo.CountStatsVO;
import vc.thinker.cabbage.stats.vo.StatsVO;
import vc.thinker.cabbage.sys.dao.PlatformRevenueDao;
import vc.thinker.cabbage.sys.dao.SysSettingDao;
import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.bo.VipPayLogBO;
import vc.thinker.cabbage.user.dao.MemberDao;
import vc.thinker.cabbage.user.dao.UserDepositLogDao;
import vc.thinker.cabbage.user.service.UpdateUserStatsService.UserBuyVipEvent;
import vc.thinker.cabbage.user.service.UpdateUserStatsService.UserRegistEvent;
import vc.thinker.sys.bo.OfficeBO;
import vc.thinker.sys.dao.OfficeDao;


@Service
@Transactional(readOnly=false)
public class StatsService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private DayStatsDao dayStatsDao;
	
	@Autowired
	private  OfficeDao officeDao;
	
	@Autowired
	private CabinetDao cabinetDao;
	
	@Autowired
	private PortableBatteryDao portableBatteryDao;
	
	@Autowired
	private DepositStatsDao depositStatsDao;
	
	@Autowired
	private CabinetStatsDao cabinetStatsDao;
	
	@Autowired
	private UserRebateLogDao userRebateLogDao;
	
	@Autowired
	private RegisterStatsDao registerStatsDao;
	
	@Autowired
	private VipStatsDao vipStatsDao;
	
	@Autowired
	private SysSettingDao sysSettingDao;
	
	@Autowired
	private BalanceStatsDao balanceStatsDao;
	
	@Autowired
	private FeedbackDao feedbackDao;
	
	@Autowired
	private UserDepositLogDao userDepositLogDao;
	
	@Autowired
	private OrderStatsDao orderStatsDao;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private IncomeStatsDao incomeStatsDao;
	
	@Autowired
	private PlatformRevenueDao platformRevenueDao;
	
	public Integer countMembers() {
		return memberDao.countMembers();
	}
	
	/**
	 * 根据日期获取当天活跃数
	 * @param day
	 * @return
	 */
	public Integer countActiveMembers(String day) {
		return memberDao.countActiveMembers(day);
	}

	public Integer countHadDepositeMembers() {
		return memberDao.countHadDepositeMembers();
	}

	public BigDecimal sumDeposit() {
		return memberDao.sumDeposit();
	}

	public BigDecimal sumConsume(String day) {
		return orderDao.sumConsume(day);
	}
	
	/**
	 * 用户统计
	 * @param vo
	 * @return
	 */
	public List<CountStatsBO> findUserStats(StatsVO vo) {
//		return memberDao.findUserStats(vo);
		return registerStatsDao.findUserStats(vo);
	}

	public List<CountStatsBO> findActiveUserStats(StatsVO vo) {
		return memberDao.findActiveUserStats(vo);
	}


	public List<CountStatsBO> findDepositStats(StatsVO vo) {
		return userDepositLogDao.findDepositStats(vo);
	}

	public List<CountStatsBO> findConsumeStats(StatsVO vo) {
		return orderDao.findConsumeStats(vo);
	}

	@Transactional(readOnly=false)
	public void sumDayStats() {
		//把这个月所有的都改成type1
		 Calendar calendar = Calendar.getInstance();  
		 dayStatsDao.updateByMonth(DateUtils.formatDate(calendar.getTime(),"yyyy-MM"));
		//查询押金总量，判断是不是当月最后一天，存起来
		BigDecimal deposit = memberDao.sumDeposit();
		//查询单车总量
		Integer cabinetNum = cabinetDao.countByStatus(null);
		
		Integer pbNum = portableBatteryDao.countByStatus();
		
		DayStats stats = new DayStats();
		stats.setStatsDate(new Date());
		stats.setOfficeId(1L);
		stats.setDeposit(deposit);
		stats.setCabinetNum(cabinetNum);
		stats.setPbNum(pbNum);
		stats.setType(2);
		
		DayStatsBO old = dayStatsDao.findByStatsDateAndOid(new Date(),1L);
		
		if(old!=null){
			dayStatsDao.update(stats);
		}else{
			dayStatsDao.save(stats);
		}
		List<OfficeBO>  list = officeDao.findAll();
		for(OfficeBO o :list){
			if(o.getId()!=1L&&!o.getId().equals(1)&&!o.getId().equals(1L)){
				DayStatsBO obo = new DayStatsBO();
				obo.setStatsDate(new Date());
				obo.setOfficeId(o.getId());
				obo.setDeposit(deposit);
				obo.setCabinetNum(cabinetNum);
				obo.setPbNum(pbNum);
				obo.setType(2);
				DayStatsBO oold = dayStatsDao.findByStatsDateAndOid(new Date(),o.getId());
				if(oold!=null){
					dayStatsDao.update(obo);
				}else{
					dayStatsDao.save(obo);
				}
			}
		}
	}
 
	public int findRideCountYesToday(String date) {
		return orderDao.findRideCountYesToday(date);
	}

	public List<CountStatsBO> statisticsCurrentDeposit(StatsVO vo) {
		return depositStatsDao.statisticsCurrentDeposit(vo);
	}

	/**
	 * 校对统计数据
	 * @param beginDate 开始时间
	 * @param endDate 结束时间
	 */
	public void resetData(String beginDate, String endDate) {
		
		//订单
		orderStatsDao.deleteByDate(beginDate,endDate);
		
		List<OrderBO> order_list= 
				orderDao.findOnlyIdByStatusAndPayTime(beginDate,endDate,OrderConstants.ORDER_STATUS_50);
		
		if(null != order_list && order_list.size()>0){
			
			order_list.forEach(e->{
				EndOrderEvent event = new EndOrderEvent();
				event.setOrderId(e.getId());
				publisher.publishEvent(event);
			});
		}
		
		//用户
		registerStatsDao.deleteByDate(beginDate,endDate);
		List<MemberBO> memberList = memberDao.findByCreateTime(beginDate, endDate);
		if(null != memberList && memberList.size()>0){
			
			memberList.forEach(e->{
				UserRegistEvent event = new UserRegistEvent();
				event.setUid(e.getUid());
				publisher.publishEvent(event);
			});
		}
		
//		//vip
//		vipStatsDao.deleteByDate(beginDate,endDate);
//		List<VipPayLogBO> logs = vipPayLogDao.findByStatusAndPayTime(beginDate,endDate,CommonConstants.VIP_PAY_LOG_STATUS_2);
//		logs.forEach(e->{
//			UserBuyVipEvent userBuyVipEvent=new UserBuyVipEvent();
//			userBuyVipEvent.setVipPayLogId(e.getId());
//			publisher.publishEvent(userBuyVipEvent);
//		});
//		//行程
//		tripStatsDao.deleteByDate(beginDate,endDate);
//		List<TripBO> trips =tripDao.findTidByStatusAndPayTime(beginDate,endDate, TripConstants.TRIP_STATUS_50);
//		trips.forEach(e->{
//			EndTripEvent tripEvent=new EndTripEvent();
//			tripEvent.setTripId(e.getId());
//			publisher.publishEvent(tripEvent);
//		});
//		//feedback
//		complainStatsDao.deleteByDate(beginDate,endDate);
//		List<FeedbackBO> feeds = feedbackDao.findByCreateTime(beginDate,endDate);
//		feeds.forEach(e->{
//			UserComplainEvent event=new UserComplainEvent();
//			event.setUid(e.getUid());
//			event.setInRiding(FeedbackConstants.FEED_TYPE_2.equals(e.getFeedType()));
//			event.setComplainDate(e.getCreateTime());
//			publisher.publishEvent(event);
//		});
//		//设备投放
//		bicycleLaunchStatsDao.deleteByDate(beginDate,endDate);
//		if(sysSettingDao.findOne().getIsOpenBicycle()){
//			List<BicycleBO> bicycles = bicycleDao.findByDisTime(beginDate,endDate);
//			bicycles.forEach(e->{
//				BikeLaunchEvent event=new BikeLaunchEvent();
//				event.setBicycleId(e.getId());
//				event.setLaunchDate(e.getDisTime());
//				publisher.publishEvent(event);
//			});
//		}else{
//			List<BatteryBO> bas = batteryDao.findByCreateTime(beginDate,endDate);
//			bas.forEach(e->{
//				BatteryLaunchEvent event = new BatteryLaunchEvent();
//				event.setBatteryId(e.getId());
//				event.setLaunchDate(e.getCreateTime());
//				publisher.publishEvent(event);
//			});
//		}
		//预存款
	}

	public List<CountStatsBO> statsTotalCabinet(StatsVO vo) {
		return cabinetStatsDao.statsTotalCabinet(vo);
	}

	public List<CountStatsBO> statsNormalCabinet(StatsVO vo) {
		return portableBatteryDao.statsPb(vo);
	}

	public List<CountStatsBO> findRebateStats(StatsVO vo) {
		return userRebateLogDao.findRebateStats(vo);
	}

	public BigDecimal sumRebate(Long uid, String date) {
		return userRebateLogDao.sumRebate(uid,date);
	}

	public List<RegisterStatsBO> countUserByAge() {
		return registerStatsDao.countUserByAge();
	}

	public int countByBuyMemberCard() {
		return vipStatsDao.totalVips();
	}

	//饼图统计
	public List<String> percentStats() {
		
		List<String> result_list = new ArrayList<String>();
		
		//用户占比统计
    	String percent_sex  = percentCountBySex();
    	//用户年龄段统计
    	String percent_age = percentCountByAge();
    	
    	result_list.add(percent_sex);
    	result_list.add(percent_age);
    	
    	
    	SysSetting sysSetBo = sysSettingDao.findOne();
    	//会员卡
    	if(null != sysSetBo.getIsOpenMemberCard() && sysSetBo.getIsOpenMemberCard()){
    		String list = percentByIsBuyMemberCard();
    		result_list.add(list);
    	}
    	
    	//余额充值
    	if(null != sysSetBo.getIsOpenBalance() && sysSetBo.getIsOpenBalance()){
    		String list = percentByIsBuyPayBalance();
    	}
		return null;
	}
	
	private String percentByIsBuyPayBalance() {
		
		List<PercentBo> re_list = new ArrayList<>();
		
		long totalNum = memberDao.count();
		
		
		return null;
	}

	
	public String percentByIspayBalance(){
		
		List<PercentBo> re_list = new ArrayList<>();
		
		return JSON.toJSONString(re_list);
	}
	
	public String percentByIsBuyMemberCard() {
		
		List<PercentBo> re_list = new ArrayList<>();
		
		long totalNum = memberDao.count();
		
		Integer totalVips = vipStatsDao.totalVips();
		
		PercentBo bo_1  = new PercentBo();
		bo_1.setValue(totalVips);
		bo_1.setDesc("会员");
		re_list.add(bo_1);
		
		PercentBo bo_2  = new PercentBo();
		bo_2.setValue((int)(totalNum - totalVips));
		bo_2.setDesc("非会员");
		
		re_list.add(bo_2);
		return JSON.toJSONString(re_list);
	}

	public String percentCountByAge() {
		
		List<PercentBo> re_list = new ArrayList<>();
		
		List<RegisterStatsBO> ageList = registerStatsDao.countUserByAge();
		
		if(null != ageList && ageList.size()>0){
			ageList.forEach(e->{
				PercentBo bo = new PercentBo();
				bo.setValue(e.getTotalNum());
				bo.setDesc(e.getAgeDesc());
				re_list.add(bo);
			});
		}
		return JSON.toJSONString(re_list);
	}

	public String percentCountBySex(){
		
		List<PercentBo> re_list = new ArrayList<>();
		
		List<RegisterStatsBO> stats = registerStatsDao.statsBySex();
		
		if(null != stats && stats.size()>0){
			stats.forEach(e->{
				PercentBo bo = new PercentBo();
				bo.setValue(e.getTotalNum());
				bo.setDesc(e.getAgeDesc());
				re_list.add(bo);
			});
			
			return JSON.toJSONString(re_list);
		}
		return JSON.toJSONString(re_list);
	}

	
	public String percentByIsPayBalance() {
		
		List<PercentBo> re_list = new ArrayList<>();
		
		long totalNum = memberDao.count();
		
		int buyNum = balanceStatsDao.countIsBuyNum();
		
		PercentBo bo_1  = new PercentBo();
		bo_1.setValue(buyNum);
		bo_1.setDesc("Recharged");
		re_list.add(bo_1);
		
		PercentBo bo_2  = new PercentBo();
		bo_2.setValue((int)(totalNum - buyNum));
		bo_2.setDesc("Not refilled");
		
		re_list.add(bo_2);
		return JSON.toJSONString(re_list);
	}

	public String percentByFeedback() {
		
		List<PercentBo> re_list = new ArrayList<>();
		
		List<FeedbackBO> f_list = feedbackDao.groupByFeedType();
		if(null != f_list&& f_list.size()>0){
			f_list.forEach(e->{
				PercentBo bo = new PercentBo();
				if(SeCommonConstants.FEEDBACK_FEED_TYPE_1.equals(e.getFeedType())){
					bo.setValue(e.getNum());
					bo.setDesc("Home");
					re_list.add(bo);
				}
				if(SeCommonConstants.FEEDBACK_FEED_TYPE_2.equals(e.getFeedType())){
					bo.setValue(e.getNum());
					bo.setDesc("Using");
					re_list.add(bo);
				}
				if(SeCommonConstants.FEEDBACK_FEED_TYPE_3.equals(e.getFeedType())){
					
					bo.setValue(e.getNum());
					bo.setDesc("completed");
					re_list.add(bo);
				}
			});
		}
		return JSON.toJSONString(re_list);
	}

	public List<CountStatsBO> feedbackStats(StatsVO vo) {
		return feedbackDao.feedbackStats(vo);
	}

	public List<CountStatsBO> orderConsumeStats(StatsVO vo) {
		return orderStatsDao.orderConsumeStats(vo);
	}

	public List<UserRebateLogBO> agentIncomeStats(StatsVO vo){
		if(!StringUtils.isEmpty(vo.getQueryModel())){
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			if("1".equals(vo.getQueryModel())){
				cal.add(Calendar.DATE, -7);
			}else if("2".equals(vo.getQueryModel())){
				cal.add(Calendar.MONTH, -1);
			}else if("3".equals(vo.getQueryModel())){
				cal.add(Calendar.MONTH, -3);
			}
			cal.set(Calendar.HOUR_OF_DAY, 23);  
			cal.set(Calendar.MINUTE, 0);  
			cal.set(Calendar.SECOND, 0);  
			
			vo.setGtTime(cal.getTime());
			
			return userRebateLogDao.agentIncomeStats(vo);
		}
		return userRebateLogDao.agentIncomeStats(vo);
	}
	public List<UserRebateLogBO> refereeIncomeStats(StatsVO vo){
		
		if(!StringUtils.isEmpty(vo.getQueryModel())){
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			if("1".equals(vo.getQueryModel())){
				cal.add(Calendar.DATE, -7);
			}else if("2".equals(vo.getQueryModel())){
				cal.add(Calendar.MONTH, -1);
			}else if("3".equals(vo.getQueryModel())){
				cal.add(Calendar.MONTH, -3);
			}
			cal.set(Calendar.HOUR_OF_DAY, 23);  
			cal.set(Calendar.MINUTE, 0);  
			cal.set(Calendar.SECOND, 0);  
			
			vo.setGtTime(cal.getTime());
			
			return userRebateLogDao.refereeIncomeStats(vo);
		}
		return userRebateLogDao.refereeIncomeStats(vo);
	}
	
	public List<UserRebateLogBO> sellerIncomeStats(StatsVO vo) {
		
		if(!StringUtils.isEmpty(vo.getQueryModel())){
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			if("1".equals(vo.getQueryModel())){
				cal.add(Calendar.DATE, -7);
			}else if("2".equals(vo.getQueryModel())){
				cal.add(Calendar.MONTH, -1);
			}else if("3".equals(vo.getQueryModel())){
				cal.add(Calendar.MONTH, -3);
			}
			cal.set(Calendar.HOUR_OF_DAY, 23);  
			cal.set(Calendar.MINUTE, 0);  
			cal.set(Calendar.SECOND, 0);  
			
			vo.setGtTime(cal.getTime());
			
			return userRebateLogDao.sellerIncomeStats(vo);
		}
		return userRebateLogDao.sellerIncomeStats(vo);
	}

	public List<CountStatsBO> findDailyIncome(StatsVO vo) {
		return incomeStatsDao.findDailyIncome(vo);
	}

	public List<CountStatsBO> findIncomeByStats(StatsVO vo) {
		return platformRevenueDao.findIncomeByStats(vo);
	}

}
