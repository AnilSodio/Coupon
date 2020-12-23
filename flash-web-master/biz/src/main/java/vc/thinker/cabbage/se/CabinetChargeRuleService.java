package vc.thinker.cabbage.se;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.se.bo.CabinetChargeRuleBO;
import vc.thinker.cabbage.se.dao.CabinetChargeRuleDao;
import vc.thinker.cabbage.se.vo.CabinetChargeRuleVO;

@Service
@Transactional
public class CabinetChargeRuleService {

	@Autowired
	private CabinetChargeRuleDao cabinetChargeRuleDao;
	
//	/**
//	 * 币种和充电柜类型
//	 * @param currency
//	 * @param cabinetTypeId
//	 * @return
//	 */
//	public CabinetChargeRuleBO findByCurrencyAndCabinet(String currency,Long cabinetTypeId){
//		return cabinetChargeRuleDao.findByCabinetType(cabinetTypeId, currency);
//	}
	
//	/**
//	 * According cabinet type to get charge rule
//	 * @param cabinetTypeId
//	 * @return
//	 */
//	public CabinetChargeRuleBO findByCabinetType(Long cabinetTypeId){
//		return cabinetChargeRuleDao.findByCabinetType(cabinetTypeId);
//	}
	
	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	public CabinetChargeRuleBO findOne(Long id){
		return cabinetChargeRuleDao.findOne(id); 
	}
	
	public List<CabinetChargeRuleBO> findPageByVo(MyPage<CabinetChargeRuleBO> page,CabinetChargeRuleVO vo){
		return cabinetChargeRuleDao.findPageByVo(page,vo);
	}

	/**
	 * 主键删除
	 * @param id
	 */
	public void delete(Long id) {
		cabinetChargeRuleDao.delete(id);
	}

	/**
	 * 修改操作
	 * @param uid
	 * @param bo
	 */
	public void saveOrUpdate(Long uid, CabinetChargeRuleBO bo) {
		if(null != bo.getId()){
			bo.setUpdateBy(uid);
			bo.setUpdateTime(new Date());
		}else {
			bo.setCreateBy(uid);
			bo.setCreateTime(new Date());
		}
		
		cabinetChargeRuleDao.save(bo);
	}

	/**
	 * 查询所有费率规则
	 * @return
	 */
	public List<CabinetChargeRuleBO> findAll() {
		return cabinetChargeRuleDao.findAll();
	}
}
