package vc.thinker.cabbage.se;

import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinco.common.utils.StringUtils;

import vc.thinker.biz.exception.ServiceException;
import vc.thinker.cabbage.se.bo.OrderBO;
import vc.thinker.cabbage.se.dao.OrderDao;
import vc.thinker.cabbage.se.dao.PortableBatteryDao;
import vc.thinker.cabbage.se.model.PortableBattery;
import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.bo.DepositPayLogBO;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.bo.UserDepositLogBO;
import vc.thinker.cabbage.user.dao.DepositPayLogDao;
import vc.thinker.cabbage.user.dao.MemberDao;
import vc.thinker.cabbage.user.dao.UserDepositLogDao;
import vc.thinker.cabbage.user.model.DepositPayLog;

@Service
@Transactional
public class PbBuyStatusReturnService {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private PortableBatteryDao portableBatteryDao;
	
	@Autowired
	private DepositPayLogDao depositPayLogDao;
	
	@Autowired
	private UserDepositLogDao userDepositLogDao;
	
	/**
	 * 为了便于测试，用户购买充电宝后，各个状态的还原
	 * @param mobile
	 * @throws SerialException 
	 */
	public void returnStatus(String mobile) {
		
		if(StringUtils.isEmpty(mobile)){
			throw new ServiceException("手机号不能为空！");
		}
		
		MemberBO member = memberDao.findMemberByMobile(mobile);
		if(null == member){
			throw new ServiceException("手机号不存在！");
		}
		
		//查询订单
		List<OrderBO> o_list = orderDao.findAllPbBuyByUid(member.getUid());
		if(o_list.isEmpty()){
			throw new ServiceException("用户没有订单！");
		}
		
		//充电宝状态还原
		PortableBattery battery = new PortableBattery();
		battery.setId(o_list.get(0).getPbId());
		battery.setStatus(PortableBatteryConstatns.PB_STATUS_ENABLE);
		battery.setLocationType(PortableBatteryConstatns.LOCATION_TYPE_IN_CABINET);
		battery.setUpdateTime(new Date());
		portableBatteryDao.update(battery);
		
		//押金还原
		MemberBO up_member = new MemberBO();
		up_member.setUid(member.getUid());
		up_member.setDeposit(o_list.get(0).getPayPrice());
		up_member.setUpdateTime(new Date());
		memberDao.update(up_member);
		
		//支付记录还原
		DepositPayLogBO paylog = 
				depositPayLogDao.findLastOneByUidAndStutus(member.getUid(),CommonConstants.DEPOSIT_PAY_STATUS_4);
		DepositPayLog up_log = new DepositPayLog();
		up_log.setId(paylog.getId());
		up_log.setStatus(CommonConstants.DEPOSIT_LOG_STATUS_2); //付款成功
		depositPayLogDao.update(up_log);
		
		//删除伪造的退款记录
//		List<UserDepositLogBO> list = 
//				userDepositLogDao.findListByUid(member.getUid(), CommonConstants.MEMBER_DEPOSIT_TYPE_4);
//		list.forEach(e->{
//			userDepositLogDao.delete(e.getId());
//		});
		
	}

	
}
