package vc.thinker.cabbage.stats;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.cabbage.se.dao.CabinetDao;
import vc.thinker.cabbage.se.dao.FeedbackDao;
import vc.thinker.cabbage.stats.bo.OrderStatsBO;
import vc.thinker.cabbage.stats.bo.ReportDataBO;
import vc.thinker.cabbage.stats.bo.ReportStatsBO;
import vc.thinker.cabbage.stats.bo.VipStatsBO;
import vc.thinker.cabbage.stats.dao.OrderStatsDao;
import vc.thinker.cabbage.stats.dao.RegisterStatsDao;
import vc.thinker.cabbage.stats.dao.VipStatsDao;
import vc.thinker.cabbage.sys.dao.SysSettingDao;


/**
 * 报表统计
 *
 */
@Service
@Transactional(readOnly=false)
public class ReportStatsService {
	
	private static Logger logger = LoggerFactory.getLogger(ReportStatsService.class);
	
	@Autowired
	private VipStatsDao vipStatsDao;
	
	@Autowired
	private RegisterStatsDao registerStatsDao;
	
	@Autowired
	private FeedbackDao feedbackDao;
	
	@Autowired
	private SysSettingDao sysSettingDao;
	
	@Autowired
	private CabinetDao cabinetDao;
	
	@Autowired
	private OrderStatsDao orderStatsDao;
	 
	/**
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public ReportDataBO getReportStats(String year, String month) {
		//List<ReportStatsBO> list  = Lists.newArrayList();
		ReportDataBO reportData = new ReportDataBO();
		//上个月 
		String lastYear =year;
		String lastMoth ="";
		if(month.equals("01")){
			lastMoth = "12";
			lastYear = (Integer.parseInt(year)-1)+"";
		}else{
			lastMoth = (Integer.parseInt(month)-1)+"";
			if(lastMoth.length()==1){
				lastMoth = "0"+lastMoth;
			}
		}
		
		/*****************device data***************************/
		ReportStatsBO touru = new ReportStatsBO();
		touru.setStatsType("Equipment into the total");
		touru.setUnit("个");
		//年
		BigDecimal yearTouru = cabinetDao.findTotalByTime(year,null);
		touru.setYearTotal(yearTouru);
		//月投入量
		BigDecimal monthTouru = cabinetDao.findTotalByTime(year,month);
		touru.setMonthTotal(monthTouru);
		//上月
		BigDecimal lastMonthTouru = cabinetDao.findTotalByTime(lastYear,lastMoth);
		touru.setLastMonthTotal(lastMonthTouru);
		//去年本月
		BigDecimal lastYearMonthTouru = cabinetDao.findTotalByTime((Integer.parseInt(year)-1)+"",month);
	
		//同比
		if(monthTouru!=null&&lastYearMonthTouru!=null&&lastYearMonthTouru.compareTo(BigDecimal.ZERO)>0){
			touru.setMonthAn(monthTouru.subtract(lastYearMonthTouru).divide(lastYearMonthTouru,2, RoundingMode.HALF_UP));
		}
		//环比
		if(monthTouru!=null&&lastMonthTouru!=null&&lastMonthTouru.compareTo(BigDecimal.ZERO)>0){
			touru.setMonthMom(monthTouru.subtract(lastMonthTouru).divide(lastMonthTouru,2, RoundingMode.HALF_UP));
		}
		//list.add(touru);
		// devices
		reportData.setDeviceData(touru);
		
		/*****************user data***************************/
		ReportStatsBO userNum = new ReportStatsBO();
		userNum.setStatsType("Number of registered users");
		userNum.setUnit(" people ");
		//年
		BigDecimal yearUserNum = registerStatsDao.findTotalByTime(year,null);
		userNum.setYearTotal(yearUserNum);
		//月
		BigDecimal monthUserNum = registerStatsDao.findTotalByTime(year,month);
		userNum.setMonthTotal(monthUserNum);
		//上月
		BigDecimal lastMonthUserNum = registerStatsDao.findTotalByTime(lastYear,lastMoth);
		userNum.setLastMonthTotal(lastMonthUserNum);
		//去年本月
		BigDecimal lastYearMonthUserNum = registerStatsDao.findTotalByTime((Integer.parseInt(year)-1)+"",month);
	
		//同比
		if(monthUserNum!=null&&lastYearMonthUserNum!=null&&lastYearMonthUserNum.compareTo(BigDecimal.ZERO)>0){
			userNum.setMonthAn(monthUserNum.subtract(lastYearMonthUserNum).divide(lastYearMonthUserNum,2, RoundingMode.HALF_UP));
		}
		//环比
		if(monthUserNum!=null&&lastMonthUserNum!=null&&lastMonthUserNum.compareTo(BigDecimal.ZERO)>0){
			userNum.setMonthMom(monthUserNum.subtract(lastMonthUserNum).divide(lastMonthUserNum,2, RoundingMode.HALF_UP));
		}
		//list.add(userNum);
		reportData.setDeviceData(touru);
		
		/*****************order & consume data***************************/
		ReportStatsBO tripNum = new ReportStatsBO();
		tripNum.setStatsType("usage count");
		userNum.setUnit(" Times ");
		ReportStatsBO consume = new ReportStatsBO();
		consume.setStatsType("Amount of consumption");
//		userNum.setUnit("元");
		//本年
		List<OrderStatsBO> yearTripList = orderStatsDao.countByDateGoupByCurrency(year,null);
		if(null != yearTripList && yearTripList.size()>0){
			for (OrderStatsBO e : yearTripList) {
				consume.getYearTotalDiffMap().put(e.getCurrency(), e.getActualConsume());
			}
		}
		
		//本月
		OrderStatsBO monthTrip = orderStatsDao.countByTime(year, month);
		List<OrderStatsBO> monthTripList = orderStatsDao.countByDateGoupByCurrency(year,month);
		if(null != monthTripList && monthTripList.size()>0){
			for (OrderStatsBO e : monthTripList) {
				consume.getMonthTotalDiffMap().put(e.getCurrency(), e.getActualConsume());
			}
		}
		tripNum.setMonthTotal(monthTrip.getOrderNum());
		
		//上月
		OrderStatsBO lastMonthTrip = orderStatsDao.countByTime(lastYear,lastMoth);
		List<OrderStatsBO> lastMonthTripList = orderStatsDao.countByDateGoupByCurrency(lastYear,lastMoth);
		if(null != lastMonthTripList && lastMonthTripList.size()>0){
			for (OrderStatsBO e : lastMonthTripList) {
				consume.getLastMonthTotalDiffMap().put(e.getCurrency(), e.getActualConsume());
			}
		}
		
		tripNum.setLastMonthTotal(lastMonthTrip.getOrderNum());
		
		//去年本月
		ReportStatsBO lastYearConsume = new ReportStatsBO();
		OrderStatsBO lastYearMonthTrip= orderStatsDao.countByTime((Integer.parseInt(year)-1)+"",month);
		List<OrderStatsBO> lastYearMonthTripList = orderStatsDao.countByDateGoupByCurrency((Integer.parseInt(year)-1)+"",month);
		
		if(null != lastYearMonthTripList && lastYearMonthTripList.size()>0){
			for (OrderStatsBO e : lastYearMonthTripList) {
				lastYearConsume.getMonthTotalDiffMap().put(e.getCurrency(), e.getActualConsume());
			}
		}
		//同比
		if(monthTrip.getOrderNum()!=null&&lastYearMonthTrip.getOrderNum()!=null&&lastYearMonthTrip.getOrderNum().compareTo(BigDecimal.ZERO)>0){
			tripNum.setMonthAn(monthTrip.getOrderNum().subtract(lastYearMonthTrip.getOrderNum()).divide(lastYearMonthTrip.getOrderNum(),2, RoundingMode.HALF_UP));
		}
		for(Map.Entry<String, BigDecimal> entry : consume.getMonthTotalDiffMap().entrySet()){
		    String key = entry.getKey();
		    BigDecimal val = entry.getValue();
		    if (lastYearConsume.getMonthTotalDiffMap().containsKey(key)) {
		    	BigDecimal ratio = val.subtract(lastYearConsume.getMonthTotalDiffMap().get(key))
		    		.divide(lastYearConsume.getMonthTotalDiffMap().get(key), 2, RoundingMode.HALF_UP);
		    	consume.getMonthAnDiffMap().put(key, ratio);
			}
		}
		
		
//		if(null != consume.getMonthTotalDiffMap() && null != cnyLastYearMonthTotal && cnyLastYearMonthTotal.compareTo(BigDecimal.ZERO) >0){
//			consume.setCnyMonthAn(cnyMonthTotal.subtract(cnyLastYearMonthTotal).divide(cnyLastYearMonthTotal,2, RoundingMode.HALF_UP));
//		}
//		if(null != myrMonthTotal && null != myrLastYearMonthTotal && myrLastYearMonthTotal.compareTo(BigDecimal.ZERO) > 0){
//			consume.setMyrMonthAn(myrMonthTotal.subtract(myrLastYearMonthTotal).divide(myrLastYearMonthTotal,2, RoundingMode.HALF_UP));
//		}
//		if(null != sgdMonthTotal && null != sgdLastYearMonthTotal && sgdLastYearMonthTotal.compareTo(BigDecimal.ZERO) > 0){
//			consume.setSgdMonthAn(sgdMonthTotal.subtract(sgdLastYearMonthTotal).divide(sgdLastYearMonthTotal,2, RoundingMode.HALF_UP));
//		}
		//环比
		if(monthTrip.getOrderNum()!=null&&lastMonthTrip.getOrderNum()!=null&&lastMonthTrip.getOrderNum().compareTo(BigDecimal.ZERO)>0){
			tripNum.setMonthMom(monthTrip.getOrderNum().subtract(lastMonthTrip.getOrderNum()).divide(lastMonthTrip.getOrderNum(),2, RoundingMode.HALF_UP));
		}
		for(Map.Entry<String, BigDecimal> entry : consume.getMonthTotalDiffMap().entrySet()){
		    String key = entry.getKey();
		    BigDecimal val = entry.getValue();
		    if (consume.getLastMonthTotalDiffMap().containsKey(key)) {
		    	BigDecimal ratio = val.subtract(consume.getLastMonthTotalDiffMap().get(key))
		    		.divide(consume.getLastMonthTotalDiffMap().get(key), 2, RoundingMode.HALF_UP);
		    	consume.getMonthMomDiffMap().put(key, ratio);
			}
		}
		
//		if(null != cnyMonthTotal && null != cnyLastMonthTotal && cnyLastMonthTotal.compareTo(BigDecimal.ZERO) > 0){
//			consume.setCnyMonthMom(cnyMonthTotal.subtract(cnyLastMonthTotal).divide(cnyLastMonthTotal,2, RoundingMode.HALF_UP));
//		}
//		if(null != myrMonthTotal && null != myrLastMonthTotal && myrLastMonthTotal.compareTo(BigDecimal.ZERO) > 0){
//			consume.setMyrMonthMom(myrMonthTotal.subtract(myrLastMonthTotal).divide(myrLastMonthTotal,2, RoundingMode.HALF_UP));
//		}
//		if(null != sgdMonthTotal && null != sgdLastMonthTotal && sgdLastMonthTotal.compareTo(BigDecimal.ZERO) > 0){
//			consume.setSgdMonthMom(sgdMonthTotal.subtract(sgdLastMonthTotal).divide(sgdLastMonthTotal,2, RoundingMode.HALF_UP));
//		}
		
		//list.add(tripNum);
		//list.add(consume);
		reportData.setOrderData(tripNum);
		reportData.setConsumeData(consume);
		
		/*****************vip data***************************/
		ReportStatsBO vipPay = new ReportStatsBO();
		if(sysSettingDao.findOne().getIsOpenMemberCard()){
			vipPay.setStatsType("Member card recharge amount");
			vipPay.setUnit("元");
			BigDecimal yearVipPay = vipStatsDao.sumByTime(year,null);
			vipPay.setYearTotal(yearVipPay);
			//月
			BigDecimal monthVipPay = vipStatsDao.sumByTime(year,month);
			vipPay.setMonthTotal(monthVipPay);
			//上月
			BigDecimal lastMonthVipPay = vipStatsDao.sumByTime(lastYear,lastMoth);
			vipPay.setLastMonthTotal(lastMonthVipPay);
			//去年本月
			BigDecimal lastYearMonthVipPay = vipStatsDao.sumByTime((Integer.parseInt(year)-1)+"",month);
		
			//同比
			if(monthVipPay!=null&&lastYearMonthVipPay!=null&&lastYearMonthVipPay.compareTo(BigDecimal.ZERO)>0){
				vipPay.setMonthAn(monthVipPay.subtract(lastYearMonthVipPay).divide(lastYearMonthVipPay,2, RoundingMode.HALF_UP));
			}
			//环比
			if(monthVipPay!=null&&lastMonthVipPay!=null&&lastMonthVipPay.compareTo(BigDecimal.ZERO)>0){
				vipPay.setMonthMom(monthVipPay.subtract(lastMonthVipPay).divide(lastMonthVipPay,2, RoundingMode.HALF_UP));
			}
		}
		reportData.setVipData(vipPay);
		
		/*****************vip data***************************/
		ReportStatsBO complaint = new ReportStatsBO();
		complaint.setStatsType("Feedback quantity");
		complaint.setUnit("Times");
		BigDecimal yearComplaint = feedbackDao.countByTime(year,null,null);
		complaint.setYearTotal(yearComplaint);
		//月
		BigDecimal monthComplaint = feedbackDao.countByTime(year,month,null);
		complaint.setMonthTotal(monthComplaint);
		//上月
		BigDecimal lastMonthComplaint = feedbackDao.countByTime(lastYear,lastMoth,null);
		complaint.setLastMonthTotal(lastMonthComplaint);
		//去年本月
		BigDecimal lastYearMonthComplaint = feedbackDao.countByTime((Integer.parseInt(year)-1)+"",month,null);
	
		//同比
		if(monthComplaint!=null&&lastYearMonthComplaint!=null&&lastYearMonthComplaint.compareTo(BigDecimal.ZERO)>0){
			complaint.setMonthAn(monthComplaint.subtract(lastYearMonthComplaint).divide(lastYearMonthComplaint,2, RoundingMode.HALF_UP));
		}
		//环比
		if(monthComplaint!=null&&lastMonthComplaint!=null&&lastMonthComplaint.compareTo(BigDecimal.ZERO)>0){
			complaint.setMonthMom(monthComplaint.subtract(lastMonthComplaint).divide(lastMonthComplaint,2, RoundingMode.HALF_UP));
		}
		reportData.setFeedbackData(complaint);
		
		//问题反馈（Using）
		ReportStatsBO incomplaint = new ReportStatsBO();
		incomplaint.setStatsType("The amount of feedback in use");
		incomplaint.setUnit(" Times ");
		BigDecimal yearInComplaint = feedbackDao.countByTime(year,null,true);
		incomplaint.setYearTotal(yearInComplaint);
		//月
		BigDecimal monthInComplaint = feedbackDao.countByTime(year,month,true);
		incomplaint.setMonthTotal(monthInComplaint);
		//上月
		BigDecimal lastMonthInComplaint = feedbackDao.countByTime(lastYear,lastMoth,true);
		incomplaint.setLastMonthTotal(lastMonthInComplaint);
		//去年本月
		BigDecimal lastYearMonthInComplaint = feedbackDao.countByTime((Integer.parseInt(year)-1)+"",month,true);
	
		//同比
		if(monthInComplaint!=null&&lastYearMonthInComplaint!=null&&lastYearMonthInComplaint.compareTo(BigDecimal.ZERO)>0){
			incomplaint.setMonthAn(monthInComplaint.subtract(lastYearMonthInComplaint).divide(lastYearMonthInComplaint,2, RoundingMode.HALF_UP));
		}
		//环比
		if(monthInComplaint!=null&&lastMonthInComplaint!=null&&lastMonthInComplaint.compareTo(BigDecimal.ZERO)>0){
			incomplaint.setMonthMom(monthInComplaint.subtract(lastMonthInComplaint).divide(lastMonthInComplaint,2, RoundingMode.HALF_UP));
		}
		reportData.setFeedbackInUseData(incomplaint);
		
		return reportData;
	}
	
	/**
	 * 
	 * @param year
	 * @return
	 */
	public ReportDataBO getReportStats(String year) {
		
		ReportDataBO reportData = new ReportDataBO();
		
		/*****************device data***************************/
		ReportStatsBO touru = new ReportStatsBO();
		touru.setStatsType("Total input of equipment");
		//年
		BigDecimal yearTouru = cabinetDao.findTotalByTime(year,null);
		touru.setYearTotal(yearTouru);
		//去年
		BigDecimal lastYearTouru = cabinetDao.findTotalByTime((Integer.parseInt(year)-1)+"",null);
		touru.setLastYearTotal(lastYearTouru);
		//同比
		if(lastYearTouru.compareTo(BigDecimal.ZERO)>0){
			BigDecimal divide = yearTouru.subtract(lastYearTouru).divide(lastYearTouru,2, RoundingMode.HALF_UP);
			touru.setYearAn(divide);
		}
		
		reportData.setDeviceData(touru);
		
		/*****************device data***************************/
		ReportStatsBO userNum = new ReportStatsBO();
		userNum.setStatsType("Number of registered users");
		userNum.setUnit(" people ");
		//年
		BigDecimal yearUserNum = registerStatsDao.findTotalByTime(year,null);
		userNum.setYearTotal(yearUserNum);
		//去年
		BigDecimal lastYearUserNum = registerStatsDao.findTotalByTime((Integer.parseInt(year)-1)+"",null);
		userNum.setLastYearTotal(lastYearUserNum);
		//同比
		if(yearUserNum!=null&&lastYearUserNum!=null&&lastYearUserNum.compareTo(BigDecimal.ZERO)>0){
			
			userNum.setYearAn(yearUserNum.subtract(lastYearUserNum).divide(lastYearUserNum,2, RoundingMode.HALF_UP));
		}
		reportData.setUserData(userNum);
		
		//订单 & 消费金额
		ReportStatsBO tripNum = new ReportStatsBO();
		tripNum.setStatsType("usage count");
		tripNum.setUnit(" Times ");
		ReportStatsBO distance = new ReportStatsBO();
		ReportStatsBO consume = new ReportStatsBO();
		consume.setStatsType("Amount of consumption");
//		consume.setUnit("元");
		//本年
		OrderStatsBO yearTrip = orderStatsDao.countByTime(year, null);
		List<OrderStatsBO> yearTipeList = orderStatsDao.countByDateGoupByCurrency(year,null);
		tripNum.setYearTotal(yearTrip.getOrderNum());
		
		if(null != yearTipeList && yearTipeList.size()>0){
			for (OrderStatsBO e : yearTipeList) {
				consume.getYearTotalDiffMap().put(e.getCurrency(), e.getActualConsume());
			}
		}
		
		//去年
		ReportStatsBO lastYearConsume = new ReportStatsBO();
		OrderStatsBO lastYearTrip= orderStatsDao.countByTime((Integer.parseInt(year)-1)+"",null);
		List<OrderStatsBO> lastYearTripList = orderStatsDao.countByDateGoupByCurrency((Integer.parseInt(year)-1)+"",null);
		if(null != lastYearTripList && lastYearTripList.size()>0){
			for (OrderStatsBO e : lastYearTripList) {
				lastYearConsume.getYearTotalDiffMap().put(e.getCurrency(), e.getActualConsume());
			}
		}
		tripNum.setLastYearTotal(yearTrip.getOrderNum());
		if(lastYearTrip.getDistance()!=null){
			distance.setLastYearTotal(lastYearTrip.getDistance().divide(new BigDecimal(1000),2, RoundingMode.HALF_UP));
		}
		consume.setLastYearTotal(lastYearTrip.getActualConsume());
		//同比
		if(yearTrip.getOrderNum()!=null&&lastYearTrip.getOrderNum()!=null&&lastYearTrip.getOrderNum().compareTo(BigDecimal.ZERO)>0){
			tripNum.setYearAn(yearTrip.getOrderNum().subtract(lastYearTrip.getOrderNum()).divide(lastYearTrip.getOrderNum(),2, RoundingMode.HALF_UP));
		}
		for(Map.Entry<String, BigDecimal> entry : consume.getYearTotalDiffMap().entrySet()){
		    String key = entry.getKey();
		    BigDecimal val = entry.getValue();
		    if (lastYearConsume.getYearTotalDiffMap().containsKey(key)) {
		    	BigDecimal ratio = val.subtract(lastYearConsume.getYearTotalDiffMap().get(key))
		    		.divide(lastYearConsume.getYearTotalDiffMap().get(key), 2, RoundingMode.HALF_UP);
		    	consume.getMonthAnDiffMap().put(key, ratio);
			}
		}
		reportData.setOrderData(tripNum);
		reportData.setConsumeData(consume);
		
		//会员卡充值
		ReportStatsBO vipPay = new ReportStatsBO();
		if(sysSettingDao.findOne().getIsOpenMemberCard()){
			vipPay.setStatsType("Member card recharge amount");
//			vipPay.setUnit("元");
			BigDecimal yearVipPay = vipStatsDao.sumByTime(year,null);
			List<VipStatsBO> yearVipPayList = vipStatsDao.sumByTimeGroupByCurrency(year, null);
			if(null != yearVipPayList && yearVipPayList.size() > 0){
				for (VipStatsBO e : yearVipPayList) {
					vipPay.getYearTotalDiffMap().put(e.getCurrency(), e.getPay());
				}
			}
//			vipPay.setYearTotal(yearVipPay);
			//去年本月
			ReportStatsBO lastYearVipPayConsume = new ReportStatsBO();
			List<VipStatsBO> lastYearVipPayList = vipStatsDao.sumByTimeGroupByCurrency(year, null);
			if(null != lastYearVipPayList && lastYearVipPayList.size() > 0){
				for (VipStatsBO e : lastYearVipPayList) {
					lastYearVipPayConsume.getYearTotalDiffMap().put(e.getCurrency(), e.getPay());
				}
			}
			//同比
			for(Map.Entry<String, BigDecimal> entry : consume.getYearTotalDiffMap().entrySet()){
			    String key = entry.getKey();
			    BigDecimal val = entry.getValue();
			    if (lastYearConsume.getYearTotalDiffMap().containsKey(key)) {
			    	BigDecimal ratio = val.subtract(lastYearConsume.getYearTotalDiffMap().get(key))
			    		.divide(lastYearVipPayConsume.getYearTotalDiffMap().get(key), 2, RoundingMode.HALF_UP);
			    	vipPay.getMonthAnDiffMap().put(key, ratio);
				}
			}
			
//			if(null != vipPay.getCnyYearTotal() && null != vipPay.getCnyLastYearTotal() && vipPay.getCnyLastYearTotal().compareTo(BigDecimal.ZERO) >0){
//				vipPay.setCnyYearAn(vipPay.getCnyYearTotal().subtract(vipPay.getCnyLastYearTotal()).divide(vipPay.getCnyLastYearTotal(),2, RoundingMode.HALF_UP));
//			}
//			if(null != vipPay.getMyrYearTotal() && null != vipPay.getMyrLastYearTotal() && vipPay.getMyrLastYearTotal().compareTo(BigDecimal.ZERO)> 0){
//				vipPay.setMyrYearAn(vipPay.getMyrYearTotal().subtract(vipPay.getMyrLastYearTotal()).divide(vipPay.getMyrLastYearTotal(),2, RoundingMode.HALF_UP));
//			}
//			if(null != vipPay.getSgdYearTotal() && null != vipPay.getSgdLastYearTotal() && vipPay.getSgdLastYearTotal().compareTo(BigDecimal.ZERO) >0){
//				vipPay.setSgdYearAn(vipPay.getSgdYearTotal().subtract(vipPay.getSgdLastYearTotal()).divide(vipPay.getSgdLastYearTotal(),2, RoundingMode.HALF_UP));
//			}
		}
		reportData.setVipData(vipPay);
		
		//问题反馈 
		ReportStatsBO complaint = new ReportStatsBO();
		complaint.setStatsType("Feedback quantity");
		complaint.setUnit("Times");
		BigDecimal yearComplaint = feedbackDao.countByTime(year,null,null);
		complaint.setYearTotal(yearComplaint);
		//去年
		BigDecimal lastYearComplaint = feedbackDao.countByTime((Integer.parseInt(year)-1)+"",null,null);
		complaint.setLastYearTotal(lastYearComplaint);
		//同比
		if(yearComplaint!=null&&lastYearComplaint!=null&&lastYearComplaint.compareTo(BigDecimal.ZERO)>0){
			complaint.setYearAn(yearComplaint.subtract(lastYearComplaint).divide(lastYearComplaint,2, RoundingMode.HALF_UP));
		}
		reportData.setFeedbackData(complaint);
		
		//行程中问题反馈
		ReportStatsBO incomplaint = new ReportStatsBO();
		incomplaint.setStatsType("The amount of feedback in use");
		incomplaint.setUnit("Times");
		BigDecimal yearInComplaint = feedbackDao.countByTime(year,null,true);
		incomplaint.setYearTotal(yearInComplaint);
		//去年本月
		BigDecimal lastYearInComplaint = feedbackDao.countByTime((Integer.parseInt(year)-1)+"",null,true);
		incomplaint.setLastYearTotal(lastYearInComplaint);
		//同比
		if(yearInComplaint!=null&&lastYearInComplaint!=null&&lastYearInComplaint.compareTo(BigDecimal.ZERO)>0){
			incomplaint.setYearAn(yearInComplaint.subtract(lastYearInComplaint).divide(lastYearInComplaint,2, RoundingMode.HALF_UP));
		}
		reportData.setFeedbackInUseData(incomplaint);
		
		
		return reportData;
	}
	
}
