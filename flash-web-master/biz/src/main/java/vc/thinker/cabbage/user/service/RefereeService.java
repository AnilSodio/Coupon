/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.cabbage.user.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sinco.common.area.Country;
import com.sinco.common.area.CountryUtil;
import vc.thinker.cabbage.sys.dao.SysSettingDao;
import vc.thinker.cabbage.sys.model.SysSetting;
//git.thinker.vc/dengwei/nomo.git
import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.bo.RefereeBO;
import vc.thinker.cabbage.user.dao.RefereeDao;
import vc.thinker.cabbage.user.dao.UserMoneyDao;
import vc.thinker.cabbage.user.model.Referee;
import vc.thinker.cabbage.user.model.UserMoney;
import vc.thinker.cabbage.user.vo.RefereeVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.sys.bo.UserAccountBO;
import vc.thinker.sys.dao.UserAccountDao;
import vc.thinker.sys.service.SystemService;

/**
 * 推荐人service
 * @author nanzhi
 * @version 2018-1-5
 */
@Service
@Transactional(readOnly = true)
public class RefereeService{
	
	@Autowired
	private RefereeDao refereeDao;
	
	@Autowired
	private UserAccountDao userAccountDao;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private UserMoneyDao userMoneyDao;
	
	@Autowired
	private SysSettingDao sysSettingDao;
	
	
	public RefereeBO findById(Long id) {
		return refereeDao.findOne(id);
	}


	public List<RefereeBO> findPageByVo(MyPage<RefereeBO> page, RefereeVO vo) {
		return refereeDao.findPageByVo(page,vo);
	}

	@Transactional(readOnly = false)
	public void adminSaveOrUpdateReferee(Referee referee) {
		if(null == referee.getUid()){
			createReferee(referee);
			return ;
		}
		referee.setUpdateTime(new Date());
		updateReferee(referee);
	}

	//新增
	@Transactional(readOnly = false)
	public void createReferee(Referee referee) {
		//创建系统用户
		UserAccountBO account = userAccountDao.findByLoginName(referee.getEmail());
		
		Long uid;
		
		if(null == account){
			
			Country country=CountryUtil.get(referee.getCountry());
			String pwd = referee.getMobile().substring(referee.getMobile().length() - 6);
			uid = systemService.createUser(CommonConstants.ACCOUNT_TYPE_9,referee.getEmail(),pwd, "", country);
		
			UserMoney money = new UserMoney();
			money.setUid(uid);
			money.setCurrency(country.getCurrencyCode());
			userMoneyDao.save(money);
		}else {
			uid = account.getUid();
		}
		
		referee.setUid(uid);
		referee.setCreateTime(new Date());
		referee.setStatus(CommonConstants.REFEREE_STATUS_1);
		
		SysSetting sysSet = sysSettingDao.findOne();
		referee.setRebateRate(null != sysSet.getIntroductroRebateRate() ? sysSet.getIntroductroRebateRate(): new BigDecimal("0.15"));
		
		refereeDao.save(referee);
	}


	//修改
	@Transactional(readOnly = false)
	public void updateReferee(Referee referee) {
		refereeDao.update(referee);
	}

	
	/**
	 * 校验推荐人名称
	 * @param uid
	 * @param refereeName
	 * @return
	 */
	public Boolean checkRefereeName(Long uid, String refereeName) {
		if(null != uid ){
			RefereeBO info = refereeDao.findOne(uid);
			if(!refereeName.equals(info.getRefereeName())){
				if(refereeDao.countByRefereeName(refereeName)>0){
					return false;
				}
			}
		}else {
			if(refereeDao.countByRefereeName(refereeName)>0){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 校验手机号
	 * @param uid
	 * @param mobile
	 * @return
	 */
	public Boolean checkMobile(Long uid,String mobile){
		
		if(null != uid){
			RefereeBO info = refereeDao.findOne(uid);
			if(!mobile.equals(info.getMobile())){
				
				if(refereeDao.countByMobile(mobile) > 0){
					return false;
				}
			}
		}else {

			if(refereeDao.countByMobile(mobile) > 0){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 校验邮箱
	 * @param uid
	 * @param email
	 * @return
	 */
	public Boolean checkEmail(Long uid,String email){
		if(null != uid){
			RefereeBO info = refereeDao.findOne(uid);
			if(!email.equals(info.getEmail())){
				if(refereeDao.countByEmail(email) > 0){
					return false;
				}
				
				UserAccountBO account = userAccountDao.findByLoginName(info.getEmail());
				if(!email.equals(account.getLoginName())){
					if(null != userAccountDao.findByLoginName(email)){
						return false;
					}
				}
				
			}
		}else {
			if(refereeDao.countByEmail(email) > 0){
				return false;
			}
			if(null != userAccountDao.findByLoginName(email)){
				return false;
			}
		}
		return true;	
	}


	public List<RefereeBO> findAll() {
		return refereeDao.findAll();
	}


	public RefereeBO findOne(Long uid) {
		return refereeDao.findOne(uid);
	}
}
