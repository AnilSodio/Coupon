package vc.thinker.cabbage.stats;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import cn.jiguang.common.utils.StringUtils;
import vc.thinker.cabbage.money.service.UserMoneyRechargeService;
import vc.thinker.cabbage.stats.bo.BalanceStatsBO;
import vc.thinker.cabbage.stats.dao.BalanceStatsDao;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.bo.UserMoneyRechargeBO;
import vc.thinker.cabbage.user.dao.MemberDao;
import vc.thinker.cabbage.util.AddressUtils;


@Service
public class UpdateBalanceStatsService {

	private static final Logger logger = LoggerFactory.getLogger(UpdateBalanceStatsService.class);

	@Autowired
	private UserMoneyRechargeService userMoneyRechargeService;
	
//	@Autowired
//	private MemberDao memberDao;
	
	@Autowired
	private BalanceStatsDao balanceStatsDao;
	
	@TransactionalEventListener
	@Async
	public void updateBalanceStats(BalanceStatsEvent event) {
		
		UserMoneyRechargeBO info = userMoneyRechargeService.findOne(event.getRechargeId());
		
		if (info == null) {
			
			logger.error("充值记录   [{}] not find", event.getRechargeId());
            return;
        }
		
		BalanceStatsBO in_bo = new BalanceStatsBO();
		in_bo.setUid(info.getUserId());
		in_bo.setStatsDate(info.getPayTime());
		in_bo.setPayAmount(info.getCashFee());
		in_bo.setSendAmount(info.getSendAmount());
		
//		MemberBO member = memberDao.findOne(info.getUserId());
		in_bo.setCurrency(info.getCurrency());
		
//		if(!StringUtils.isEmpty(member.getClientSource())){
//			in_bo.setClientType(member.getClientSource().split("/")[0].toLowerCase().replaceAll("ios", "iphone"));
//		}
		
//		if(!StringUtils.isEmpty(member.getRegisteredIp())){
//			
//			try {
//				
//				in_bo.setArea(AddressUtils.getAddresses("ip="+member.getRegisteredIp(), "utf-8"));
//				
//			} catch (Exception e) {
//				logger.error("IP获取城市异常[{}]",e.getMessage());
//				in_bo.setArea("未知");
//			}
//		}
		
		balanceStatsDao.save(in_bo);
		
//		if(!StringUtils.isEmpty(member.getRegisteredIp())){
//				
//			try {
//				in_bo.setArea(AddressUtils.getAddresses("ip="+member.getRegisteredIp(), "utf-8"));
//				
//			} catch (Exception e) {
//				
//			}
//		}
//		
//		if(!StringUtils.isEmpty(member.getClientSource())){
//			in_bo.setClientType(member.getClientSource().split("/")[0].toLowerCase().replaceAll("ios", "iphone"));
//		}
//		
//		RegisterStatsBO resistStats = registerStatsDao.findByUid(info.getUserId());
//		
//		if(null != resistStats){
//			in_bo.setAge(resistStats.getAge());
//			in_bo.setSex(resistStats.getSex());
//			balanceStatsDao.save(in_bo);
//			return ;
//		}
//		
//		if(!StringUtils.isEmpty(member.getIdCard()) && member.getIdCard().length() == 18){
//			Integer year = Integer.parseInt(member.getIdCard().substring(6, 10));
//			Calendar date=Calendar.getInstance();
//			in_bo.setAge(date.get(Calendar.YEAR)-year);
//			Integer sex = Integer.parseInt(member.getIdCard().substring(16, 17));
//			if(sex%2==0){
//				in_bo.setSex(StatsConstants.SEX_2);
//			}else{
//				in_bo.setSex(StatsConstants.SEX_1);
//			}
//		}
		
//		balanceStatsDao.save(in_bo);
		
	}

	public static class BalanceStatsEvent {

		private Long rechargeId;

		public Long getRechargeId() {
			return rechargeId;
		}

		public void setRechargeId(Long rechargeId) {
			this.rechargeId = rechargeId;
		}
	}
}
