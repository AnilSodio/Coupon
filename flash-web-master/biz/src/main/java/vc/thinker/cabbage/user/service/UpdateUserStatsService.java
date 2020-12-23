package vc.thinker.cabbage.user.service;

import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.util.StringUtils;
import vc.thinker.cabbage.stats.StatsConstants;
import vc.thinker.cabbage.stats.bo.DepositStatsBO;
import vc.thinker.cabbage.stats.bo.RegisterStatsBO;
import vc.thinker.cabbage.stats.bo.VipStatsBO;
import vc.thinker.cabbage.stats.dao.DepositStatsDao;
import vc.thinker.cabbage.stats.dao.RegisterStatsDao;
import vc.thinker.cabbage.stats.dao.VipStatsDao;
import vc.thinker.cabbage.user.bo.DepositPayLogBO;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.bo.VipPayLogBO;
import vc.thinker.cabbage.user.dao.DepositPayLogDao;
import vc.thinker.cabbage.user.dao.MemberDao;
import vc.thinker.cabbage.user.dao.VipPayLogDao;
import vc.thinker.cabbage.util.AddressUtils;
import vc.thinker.cabbage.util.DateTimeUtils;

@Service
public class UpdateUserStatsService {
	
	private final static Logger log=LoggerFactory.getLogger(UpdateUserStatsService.class);
	
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private RegisterStatsDao registerStatsDao;
	
	@Autowired
	private VipPayLogDao vipPayLogDao;
	
	@Autowired
	private VipStatsDao vipStatsDao;
	
	@Autowired
	private DepositPayLogDao depositPayLogDao;
	
	@Autowired
	private DepositStatsDao depositStatsDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Value("${lbs.baidu.ak}")
	private String baiduAK;
	
	

	/**
	 * 更新注册用户统计
	 * @param event
	 */
	@TransactionalEventListener
	@Async
	public void updateRegistStats(UserRegistEvent event)
	{
		MemberBO member = memberService.findByUid(event.getUid());
		if(member == null){
			log.error("user  [{}] not find",event.getUid());
			return;
		}
		updateRegistStats(member);
	}
	
	public void updateRegistStats(MemberBO member){
		//注册统计
		RegisterStatsBO stats = new RegisterStatsBO();
		stats.setUid(member.getUid());
		stats.setStatsDate(member.getCreateTime());
		stats.setArea("未知");
		if(!StringUtils.isEmpty(member.getRegisteredIp()))
		{
			try {
				stats.setArea(AddressUtils.getAddresses("ip="+member.getRegisteredIp(), "utf-8"));
			} catch (Exception e) {
				log.error("IP获取城市异常[{}]",e.getMessage());
			}
		}
		if(member.getClientSource()!=null&&member.getClientSource().length()>0){
			stats.setClientType(member.getClientSource().split("/")[0].toLowerCase().replaceAll("ios", "iphone"));
		}
		String card = member.getIdCard();
		if(card!=null&&card.length()==18){
			try {
				Integer year = Integer.parseInt(card.substring(6, 10));
				Calendar date=Calendar.getInstance();
				stats.setAge(date.get(Calendar.YEAR)-year);
				Integer sex = Integer.parseInt(card.substring(16, 17));
				if(sex%2==0){
					stats.setSex(StatsConstants.SEX_2);
				}else{
					stats.setSex(StatsConstants.SEX_1);
				}
			} catch (Exception e) {
				log.error("获取性别年龄.身份证格式错误 ：[{}]",card);
			}
		}
		RegisterStatsBO old = registerStatsDao.findByUid(member.getUid());
		if(old!=null){
			stats.setId(old.getId());
			registerStatsDao.update(stats);
		}else{
			registerStatsDao.save(stats);
		}
		
	}
	/**
	 * 更新会员卡金额统计
	 * @param event
	 */
	@TransactionalEventListener
	@Async
	public void updateVipStats(UserBuyVipEvent event)
	{
		VipPayLogBO viplog = vipPayLogDao.findOne(event.getVipPayLogId());
		if(viplog == null){
			log.error("viplog  [{}] not find",event.getVipPayLogId());
			return;
		}
		VipStatsBO stats = new VipStatsBO();
		stats.setUid(viplog.getUid());
		stats.setStatsDate(viplog.getPayTime());
		stats.setVipDays(viplog.getVipDay());
		stats.setPay(viplog.getAmount());
		stats.setVipType(viplog.getVipCardName());
		
		MemberBO member = memberService.findByUid(viplog.getUid());
		stats.setCurrency(member.getCurrency());
		if(!StringUtils.isEmpty(member.getClientSource())){
			stats.setClientType(member.getClientSource().split("/")[0].toLowerCase().replaceAll("ios", "iphone"));
		}
		
		if(!StringUtils.isEmpty(member.getRegisteredIp())){
			try {
				stats.setArea(AddressUtils.getAddresses("ip="+member.getRegisteredIp(), "utf-8"));
			} catch (Exception e) {
				log.error("IP获取城市异常[{}]",e.getMessage());
			}
		}
		
		vipStatsDao.save(stats);
		
		//用户信息
//		RegisterStatsBO member = registerStatsDao.findByUid(viplog.getUid());
//		if(member!=null){
//			stats.setClientType(member.getClientType());
//			stats.setAge(member.getAge());
//			stats.setSex(member.getSex());
//			stats.setArea(member.getArea());
//		}else{
//        	MemberBO memberBo = memberService.findByUid(viplog.getUid());
//        	if(memberBo.getClientSource()!=null&&memberBo.getClientSource().length()>0){
//    			stats.setClientType(memberBo.getClientSource().split("/")[0].toLowerCase().replaceAll("ios", "iphone"));
//    		}
//        	stats.setArea("未知");
//        	if(!StringUtils.isEmpty(memberBo.getRegisteredIp()))
//    		{
//    			try {
//    				stats.setArea(AddressUtils.getAddresses("ip="+memberBo.getRegisteredIp(), "utf-8"));
//    			} catch (Exception e) {
//    				log.error("IP获取城市异常[{}]",e.getMessage());
//    			}
//    		}
//        	String card = memberBo.getIdCard();
//    		if(card!=null&&card.length()==18){
//    			try {
//    				Integer year = Integer.parseInt(card.substring(6, 10));
//    				Calendar date=Calendar.getInstance();
//    				stats.setAge(date.get(Calendar.YEAR)-year);
//    				Integer sex = Integer.parseInt(card.substring(16, 17));
//    				if(sex%2==0){
//    					stats.setSex(StatsConstants.SEX_2);
//    				}else{
//    					stats.setSex(StatsConstants.SEX_1);
//    				}
//    			} catch (Exception e) {
//    				log.error("获取性别年龄.身份证格式错误 ：[{}]",card);
//    			}
//    		}
//        }
//		vipStatsDao.save(stats);
	}

	
	/**
	 * 用户支付押金统计
	 * @param event
	 */
	@TransactionalEventListener
	@Async
	public void UserDepositPayOperation(UserDepositPayEvent event){
		
		DepositPayLogBO depositPay = depositPayLogDao.findOne(event.getDepositPayId());
		if(null == depositPay){
			log.error("用户充值记录不存在  [{}] not find",event.getDepositPayId());
			return ;
		}
		
//		MemberBO member = memberService.findOne(depositPay.getUid());
//		if(StringUtils.isEmpty(member.getCurrency())){
//			log.error("用户:【"+ member.getMobile()+"】的币种:【"+member.getCurrency()+"】");
//			return ;
//		}
		
		String currency = depositPay.getCurrency();
		DepositStatsBO depositStats = depositStatsDao.findbyCurrentDay(DateTimeUtils.getDateMonth(),currency);
		
		if(null != depositStats){
			DepositStatsBO stats_up = new DepositStatsBO();
			stats_up.setId(depositStats.getId());
			stats_up.setDeposit(depositStats.getDeposit().add(depositPay.getAmount()));
			depositStatsDao.update(stats_up);
			return ;
		}
		
		DepositStatsBO stats_insert = new DepositStatsBO();
		stats_insert.setCurrentDay(DateTimeUtils.getDateMonth());
		stats_insert.setDeposit(memberDao.sumDeposit());
		stats_insert.setCreateTime(new Date());
		stats_insert.setCurrency(currency);
		
		depositStatsDao.save(stats_insert);
		
	}
	
	@TransactionalEventListener
	@Async
	public void UserDepositRefundOperation(UserDepositRefundEvent event){
		
		DepositPayLogBO depositPay = depositPayLogDao.findOne(event.getDepositPayId());
		if(null == depositPay){
			log.error("用户充值记录不存在  [{}] not find",event.getDepositPayId());
			return ;
		}
		
		String currency = depositPay.getCurrency();
		
		DepositStatsBO depositStats = depositStatsDao.findbyCurrentDay(DateTimeUtils.getDateMonth(),currency);
		
		if(null != depositStats){
			DepositStatsBO up_bo = new DepositStatsBO();
			up_bo.setId(depositStats.getId());
			up_bo.setDeposit(depositStats.getDeposit().subtract(depositPay.getAmount()));
			depositStatsDao.update(up_bo);
			return ;
		}
		DepositStatsBO stats_insert = new DepositStatsBO();
		stats_insert.setCurrentDay(DateTimeUtils.getDateMonth());
		stats_insert.setDeposit(memberDao.sumDeposit());
		stats_insert.setCreateTime(new Date());
		stats_insert.setCurrency(currency);
		depositStatsDao.save(stats_insert);
		
	}
	
	/**
	 * 用户注册事件  
	 * @author nanzhi
	 *
	 */
	public static class UserRegistEvent{
		//用户id
		private Long uid;

		public Long getUid() {
			return uid;
		}
		public void setUid(Long uid) {
			this.uid = uid;
		}
	}
	
	/**
	 * 用户购买会员事件  
	 * @author nanzhi
	 *
	 */
	public static class UserBuyVipEvent{
		//用户id
		private Long vipPayLogId;

		public Long getVipPayLogId() {
			return vipPayLogId;
		}

		public void setVipPayLogId(Long vipPayLogId) {
			this.vipPayLogId = vipPayLogId;
		}

	}

	/**
	 * 用户充押金
	 * @author thinker
	 *
	 */
	public static class UserDepositPayEvent{
		
		private Long depositPayId;

		public Long getDepositPayId() {
			return depositPayId;
		}

		public void setDepositPayId(Long depositPayId) {
			this.depositPayId = depositPayId;
		}
	}
	
	/**
	 * 用户退押金
	 * @author thinker
	 *
	 */
	public static class UserDepositRefundEvent{
		
		private Long depositPayId;
		
		public Long getDepositPayId() {
			return depositPayId;
		}

		public void setDepositPayId(Long depositPayId) {
			this.depositPayId = depositPayId;
		}
	}
}
