/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.cabbage.user.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sinco.common.area.Country;
import com.sinco.common.area.CountryUtil;

//git.thinker.vc/dengwei/nomo.git
import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.bo.AgentBO;
import vc.thinker.cabbage.user.dao.AgentDao;
import vc.thinker.cabbage.user.dao.UserMoneyDao;
import vc.thinker.cabbage.user.model.Agent;
import vc.thinker.cabbage.user.model.UserMoney;
import vc.thinker.cabbage.user.vo.AgentVO;
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
public class AgentService{
	
	@Autowired
	private AgentDao agentDao;
	
	@Autowired
	private UserAccountDao userAccountDao;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private UserMoneyDao userMoneyDao;
	
	public AgentBO findById(Long id) {
		return agentDao.findOne(id);
	}

	public List<AgentBO> findPageByVo(MyPage<AgentBO> page, AgentVO vo) {
		return agentDao.findPageByVo(page,vo);
	}

	@Transactional(readOnly = false)
	public void adminSaveOrUpdateAgent(Agent agent) {
		if(null == agent.getUid()){
			//创建
			createAgent(agent);
			return ;
		}
		
		agent.setUpdateTime(new Date());
		agentDao.update(agent);
	}


	@Transactional(readOnly = false)
	public void createAgent(Agent agent) {
		
		UserAccountBO account = userAccountDao.findByLoginName(agent.getEmail());
		
		Long uid;
		if(null == account){
			Country country=CountryUtil.get(agent.getCountry());
			String pwd = agent.getMobile().substring(agent.getMobile().length() - 6);
			uid = systemService.createUser(CommonConstants.ACCOUNT_TYPE_9,agent.getEmail(),pwd, "", country);
		
			UserMoney money = new UserMoney();
			money.setUid(uid);
			money.setCurrency(country.getCurrencyCode());
			userMoneyDao.save(money);
		}else {
			uid = account.getUid();
		}
		
		agent.setUid(uid);
		agent.setCreateTime(new Date());
		agent.setStatus(CommonConstants.AGENT_STATUS_1);
		agentDao.save(agent);
	}


	public Boolean checkAgentName(Long uid, String agentName) {
		if(null != uid){
			AgentBO info = agentDao.findOne(uid);
			if(!agentName.equals(info.getAgentName())){
				if(agentDao.countByAgentName(agentName)>0){
					return false;
				}
			}
		}else {
			if(agentDao.countByAgentName(agentName)>0){
				return false;
			}
		}
		return true;
	}



	public Boolean checkMobile(Long uid, String mobile) {
		if(null != uid){
			AgentBO info = agentDao.findOne(uid);
			if(!mobile.equals(info.getMobile())){
				
				if(agentDao.countByMobile(mobile)>0){
					return false;
				}
			}
		}else {
			if(agentDao.countByMobile(mobile)>0){
				return false;
			}
		}
		return true;
	}

	public Boolean checkEmail(Long uid,String email){
		
		if(null != uid){
			AgentBO info = agentDao.findOne(uid);
			if(!email.equals(info.getEmail())){
				if(agentDao.countByEmail(email)>0){
					return false;
				}
			}
		}else {
			if(agentDao.countByEmail(email)>0){
				return false;
			}
		}
		
		return true;
	}

	public List<AgentBO> findAll() {
		return agentDao.findAll();
	}

}
