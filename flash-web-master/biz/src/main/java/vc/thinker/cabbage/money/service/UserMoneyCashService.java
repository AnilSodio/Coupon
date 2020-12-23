package vc.thinker.cabbage.money.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sinco.common.utils.DateUtils;
import vc.thinker.cabbage.user.BankCondeUtils;
import vc.thinker.cabbage.user.UserMoneyConstants;
import vc.thinker.cabbage.user.bo.UserMoneyBO;
import vc.thinker.cabbage.user.bo.UserMoneyCashBO;
import vc.thinker.cabbage.user.bo.UserMoneyLogBO;
import vc.thinker.cabbage.user.dao.UserMoneyCashDao;
import vc.thinker.cabbage.user.dao.UserMoneyDao;
import vc.thinker.cabbage.user.dao.UserMoneyLogDao;
import vc.thinker.cabbage.user.vo.UserMoneyCashVO;
import vc.thinker.cabbage.util.DateTimeUtils;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.pay.PayChannel;
import vc.thinker.pay.PayHandlerFactory;
import vc.thinker.pay.PayMchHandler;
import vc.thinker.pay.request.PayBankRequest;
import vc.thinker.pay.response.PayBankResponse;

@Service
@Transactional
public class UserMoneyCashService {

	@Autowired
	private UserMoneyCashDao userMoneyCashDao;
	
	@Autowired
	private UserMoneyDao userMoneyDao;
	
	@Autowired
	@Lazy(true)
	private PayHandlerFactory payHandlerFactory;
	
	@Autowired
	private UserMoneyLogDao userMoneyLogDao;
	

	/**
	 * 商户后台分页查询
	 * @param page
	 * @param vo
	 * @return
	 */
	public List<UserMoneyCashBO> findPageByVo(MyPage<UserMoneyCashBO> page, UserMoneyCashVO vo) {
		return userMoneyCashDao.findPageByVo(page,vo);
	}

	public UserMoneyCashBO findOne(Long id) {
		return userMoneyCashDao.findOne(id);
	}

	/**
	 * 商户提现审核
	 * @param uid 审核人
	 * @param id 提现记录
	 * @param cashAdminInfo 审核备注
	 * @param cashStatus   审核结果
	 */
	/**
	 * @param uid
	 * @param id
	 * @param cashAdminInfo
	 * @param cashStatus
	 */
	public void adminCheck(Long uid,Long id, String cashAdminInfo, String cashStatus) {
		
		UserMoneyCashBO info = userMoneyCashDao.findOne(id);
		
		UserMoneyCashBO up_bo = new UserMoneyCashBO();
		up_bo.setId(id);
		up_bo.setCashAdminId(uid);
		up_bo.setCashAdminInfo(cashAdminInfo);
		up_bo.setCashStatus(cashStatus);
		up_bo.setCheckTime(new Date());
		
		if(null != cashStatus && cashStatus.equals(UserMoneyConstants.MONEY_CASH_STATUS_CHECK_NO)){
			//审核不通过，返还用户的钱
			userMoneyDao.addMoney(info.getCashUserId(), info.getCashAmount());
		}
		if(null != cashStatus && cashStatus.equals(UserMoneyConstants.MONEY_CASH_STATUS_NO_PAY)){
			//审核通过，添加 user_money_log记录
			UserMoneyLogBO userMoneyLog = new UserMoneyLogBO();
			
			userMoneyLog.setLogUserId(info.getCashUserId());
			userMoneyLog.setLogType(UserMoneyConstants.LOG_TYPE_CASH);
			userMoneyLog.setLogAmount(info.getCashAmount());
			
			switch (info.getCashUserType()) {
			case UserMoneyConstants.CASH_USER_TYPE_SELLER:
				userMoneyLog.setLogInfo("Merchant Withdrawal");
				break;
			case UserMoneyConstants.CASH_USER_TYPE_AGENT:
				userMoneyLog.setLogInfo("Agent Withdrawal");
				break;
			case UserMoneyConstants.CASH_USER_TYPE_REFEREE:
				userMoneyLog.setLogInfo("Introducer Withdrawal");
			default:
				userMoneyLog.setLogInfo("User Withdrawal");
				break;
			}
			 
			userMoneyLog.setCreateTime(new Date());
			userMoneyLog.setIsDeleted(false);
			userMoneyLog.setOldLogAmount(info.getCashAmount());
			userMoneyLog.setOldLogCurrency(info.getCurrency());
			userMoneyLog.setExchangeRate(new BigDecimal("1"));
			userMoneyLog.setLogCurrency(info.getCurrency());
			userMoneyLogDao.save(userMoneyLog);
		}
		userMoneyCashDao.update(up_bo);
	}
	
	
	/**
	 * 服务商提现转账
	 * @param uid 操作人
	 * @param id 记录id
	 * @return
	 */
	public String adminTransfer(Long uid,Long id){
		
		UserMoneyCashBO info = userMoneyCashDao.findOne(id);
		
		if(null == info){
			return "数据不存在，请联系后台!";
		}
		
		if(!info.getCashStatus().equals(UserMoneyConstants.MONEY_CASH_STATUS_NO_PAY)){
			return "交易状态异常，请联系后台!";
		}
		
		try{
			
			PayMchHandler paMchHandler=payHandlerFactory.getPayMchHandler(PayChannel.WEIXIN);
//			paMchHandler.getPublieKey();
			
			PayBankRequest request = new PayBankRequest();
			request.setPartner_trade_no(DateTimeUtils.getDateTimeMils());
			request.setEnc_bank_no(info.getCashAccount());
			request.setEnc_true_name(info.getCashUserName());
			request.setAmount(info.getCashAmount().multiply(new BigDecimal("100")).intValue());
			request.setDesc("服务商提现");
			request.setConfigMark("wx_js");
			request.setBank_code(BankCondeUtils.getCode(info.getCashBank()));
			
			PayBankResponse response = paMchHandler.payBank(request);
			
			if(response.isSuccess()){
				//成功
				UserMoneyCashBO up_info = new UserMoneyCashBO();
				up_info.setId(id);
				up_info.setTransTime(new Date());
				up_info.setTransUid(uid);
				up_info.setPartnerTradeNo(response.getPartner_trade_no());
				up_info.setPaymentNo(response.getPayment_no());
				up_info.setCmmsAmt(response.getCmms_amt());
				up_info.setCashStatus(UserMoneyConstants.MONEY_CASH_STATUS_PAY);
				
				userMoneyCashDao.update(up_info);
				
				return "0000";
			}else {
				//失败
				return response.getMsg();
			}
		}catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	public UserMoneyCashBO findDetailOne(Long id) {
		return userMoneyCashDao.findDetailOne(id);
	}

	public void addCash(UserMoneyCashBO obj) {
		//扣钱
		UserMoneyBO userMoney = userMoneyDao.findOne(obj.getCashUserId());
		userMoney.setAvailableBalance(userMoney.getAvailableBalance().subtract(obj.getCashAmount()));
		userMoneyDao.save(userMoney);
		//增加提现记录
		obj.setCashSn("cash"+obj.getCashUserId()+DateUtils.formatDate(new Date(),"yyyyMMddHHmmss"));
		obj.setCashStatus("-1");
		obj.setCreateTime(new Date());
		userMoneyCashDao.save(obj);
	}

	/**
	 * 商户提现列表
	 * @param page
	 * @param vo
	 */
	public List<UserMoneyCashBO> findPageBySeller(MyPage<UserMoneyCashBO> page, UserMoneyCashVO vo) {
		return userMoneyCashDao.findPageBySeller(page,vo);
	}

	/**
	 * 推荐人提现列表
	 * @param page
	 * @param vo
	 */
	public List<UserMoneyCashBO> findPageByReferee(MyPage<UserMoneyCashBO> page, UserMoneyCashVO vo) {
		return userMoneyCashDao.findPageByReferee(page,vo);
	}

	/**
	 * 代理商提现列表
	 * @param page
	 * @param vo
	 */
	public List<UserMoneyCashBO> findPageByAgent(MyPage<UserMoneyCashBO> page, UserMoneyCashVO vo) {
		return userMoneyCashDao.findPageByAgent(page,vo);
	}
	
}
