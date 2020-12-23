package vc.thinker.cabbage.user.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinco.data.core.Page;

import vc.thinker.biz.exception.ServiceException;
import vc.thinker.cabbage.user.IntegralConstants;
import vc.thinker.cabbage.user.bo.IntegralLogBO;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.bo.SellerBO;
import vc.thinker.cabbage.user.dao.IntegralLogDao;
import vc.thinker.cabbage.user.dao.IntegralRuleDao;
import vc.thinker.cabbage.user.dao.MemberDao;
import vc.thinker.cabbage.user.dao.SellerDao;
import vc.thinker.cabbage.user.exception.InviteCodeNotFindException;
import vc.thinker.cabbage.user.exception.InviteCodeUsedException;
import vc.thinker.cabbage.user.model.IntegralLog;
import vc.thinker.cabbage.user.model.IntegralRule;
import vc.thinker.cabbage.user.vo.IntegralLogVO;

@Service
@Transactional
public class IntegralLogService {

	@Autowired
	private IntegralLogDao integralLogDao;
	
	@Autowired
	private IntegralRuleDao integralRuleDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private SellerDao sellerDao;
	
	/**
	 * 添加积分
	 * @param integerRuleCode
	 * @param uid
	 * @param logInfo
	 * @param logType
	 * @param bizSourceCode
	 */
	public void addIntegral(String integerRuleCode,Long uid,String logInfo,String logType,String bizSourceCode){
		
		IntegralRule integralRule=integralRuleDao.
				findByCode(integerRuleCode);
		if(integralRule == null){
			throw new ServiceException("积分规则未配制");
		}
		
		IntegralLog log=new IntegralLog();
		log.setBizSourceCode(bizSourceCode);
		log.setCreateTime(new Date());
		log.setUid(uid);
		log.setLogInfo(logInfo);
		log.setLogIntegral(Long.valueOf(integralRule.getIntegralNum()));
		log.setLogType(logType);
		integralLogDao.save(log);
	}
	
	public List<IntegralLogBO> findPageByVo(Page<IntegralLogBO> page,IntegralLogVO vo){
		return integralLogDao.findPageByVo(page,vo);
	}
	
	/**
	 * 兑换邀请码
	 * @param uid
	 * @param inviteCode
	 */
	public void exchangeCode(Long uid,String inviteCode){
		MemberBO inviteMember=memberDao.findByInviteCode(inviteCode);
		
		MemberBO member=memberDao.findOne(uid);
		if(inviteCode.equals(member.getInviteCode())){
			throw new InviteCodeNotFindException();
		}
		
		if(inviteMember != null){
			exchangeMemberCode(uid, inviteMember);
			return;
		}else{
			SellerBO inviteSeller=sellerDao.findByInviteCode(inviteCode);
			if(inviteSeller != null){
				exchangeSellerCode(uid, inviteSeller);
				return;
			}
		}
		throw new InviteCodeNotFindException();
	}
	/**
	 * 兑换商户邀请码
	 * @param uid
	 * @param inviteCode
	 */
	public void exchangeSellerCode(Long uid,SellerBO sellelr){
		try {
			addIntegral(IntegralConstants.INTEGER_CODE_SELLER_INVITE_CODE, uid,
					"Redemption Code Used", IntegralConstants.INTEGER_TYPE_EXCHANGE, sellelr.getInviteCode());
		} catch (DuplicateKeyException e) {
			throw new InviteCodeUsedException();
		}
	}
	/**
	 * 兑换个人邀请码
	 * @param uid
	 * @param inviteCode
	 */
	public void exchangeMemberCode(Long uid,MemberBO member){
		try {
			addIntegral(IntegralConstants.INTEGER_CODE_FRIEND_INVITE_CODE, uid, "Redemption Code Used",
					IntegralConstants.INTEGER_TYPE_EXCHANGE, member.getInviteCode());
		} catch (DuplicateKeyException e) {
			throw new InviteCodeUsedException();
		}
		
		//邀请积分
		addIntegral(IntegralConstants.INTEGER_CODE_INVITE, member.getUid(), 
				"Invitation", IntegralConstants.INTEGER_TYPE_INVITE, String.valueOf(uid));
	}
}
