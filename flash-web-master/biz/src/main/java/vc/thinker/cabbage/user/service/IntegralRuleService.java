package vc.thinker.cabbage.user.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.cabbage.user.bo.IntegralRuleBO;
import vc.thinker.cabbage.user.dao.IntegralRuleDao;
import vc.thinker.cabbage.user.vo.IntegralRuleVO;
import vc.thinker.cabbage.common.MyPage;

@Service
@Transactional
public class IntegralRuleService {

	@Autowired
	private IntegralRuleDao integralRuleDao;
	
	public IntegralRuleBO findOne(Long id){
		return integralRuleDao.findOne(id);
	}
	
	public List<IntegralRuleBO> findPageByVo(MyPage<IntegralRuleBO> page,IntegralRuleVO vo){
		return integralRuleDao.findPageByVo(page,vo);
	}

	public void saveOrUpdate(Long id,IntegralRuleBO bo) {
		
		if(null != bo.getId()){
			bo.setUpdateBy(id);
			bo.setUpdateTime(new Date());
		}else {
			bo.setCreateBy(id);
			bo.setCreateTime(new Date());
		}
		
		integralRuleDao.save(bo);
		
	}

	public void delete(Long id) {
		integralRuleDao.delete(id);
	}

	public Boolean checkRuleCode(Long id, String ruleCode) {
		if(null != id){
			//修改
			IntegralRuleBO info = integralRuleDao.findOne(id);
			if(!info.getRuleCode().equals(ruleCode)){
				
				if(integralRuleDao.countByCode(ruleCode)>0){
					return false;
				}
			}
		}else {
			if(integralRuleDao.countByCode(ruleCode)>0){
				return false;
			}
		}
		return true;
	}
}
