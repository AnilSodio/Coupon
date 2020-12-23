package vc.thinker.cabbage.se;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.cabbage.se.bo.CabinetTypeBO;
import vc.thinker.cabbage.se.dao.CabinetChargeRuleDao;
import vc.thinker.cabbage.se.dao.CabinetTypeDao;
import vc.thinker.cabbage.se.model.CabinetType;
import vc.thinker.cabbage.util.CabinetTypeEnum;

@Service
@Transactional
public class CabinetTypeService {

	@Autowired
	private CabinetTypeDao cabinetTypeDao;
	
	@Autowired
	private CabinetChargeRuleDao cabinetChargeRuledao;
	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	public CabinetTypeBO findOne(Long id){
		return cabinetTypeDao.findOne(id);
	}
	
//	/**
//	 * query charge rules with cabinet type
//	 * @param types
//	 * @return
//	 */
//	public Map<Integer, List<CabinetChargeRuleBO>> findMapByVo(){
//		Map<Integer, List<CabinetChargeRuleBO>> map = new HashMap<Integer, List<CabinetChargeRuleBO>>();
//		for (CabinetTypeEnum type : CabinetTypeEnum.values()) {
//			List<CabinetChargeRuleBO> ruleList = cabinetChargeRuledao.findByCabinetTypeId(Long.valueOf(type.getCode()));
//			map.put(type.getCode(), ruleList);
//		}
//		return map;
//	}
	
//	/**
//	 * 分页查询
//	 * @param page
//	 * @param vo
//	 * @return
//	 */
//	@Deprecated
//	public List<CabinetTypeBO> findPageByVo(MyPage<CabinetTypeBO> page,CabinetTypeVO vo){
//		List<CabinetTypeBO> type_list = cabinetTypeDao.findPageByVo(page,vo);
//		if(null != type_list && type_list.size()>0){
//			type_list.forEach(e->{
//				List<CabinetChargeRuleBO> ruleList = cabinetChargeRuledao.findByCabinetTypeId(e.getId());
//				e.setRuleList(ruleList);
//			});
//		}
//		page.setContent(type_list);
//		return type_list;
//	}

	/**
	 * 平台后台修改
	 * @param uid 当前操作人
	 * @param bo
	 */
	public void adminSaveOrUpdate(Long id, CabinetTypeBO bo) {
		
		if(null != bo.getId()){
			bo.setUpdateBy(id);
			bo.setUpdateTime(new Date());
			
			//修改mongo数据
			
		}else {
			bo.setCreateBy(id);
			bo.setCreateTime(new Date());
		}
		cabinetTypeDao.save(bo);
	}

	/**
	 * 主键删除
	 * @param id
	 */
	public void delete(Long id) {
		cabinetTypeDao.delete(id);	
	}


	public Boolean checkTypeName(Long id, String typeName) {
		if(null != id){
			//修改
			CabinetTypeBO info = cabinetTypeDao.findOne(id);
			if(!info.getTypeName().equals(typeName)){
				
				if(cabinetTypeDao.countByTypename(typeName) > 0){
					return false;
				}
			}
		}else{
			if(cabinetTypeDao.countByTypename(typeName) > 0){
				return false;
			}
		}
		return true;
	}


	/**
	 * query all cabinet types
	 * @return
	 */
	public List<CabinetTypeBO> findAll() {
		List<CabinetTypeBO> types = cabinetTypeDao.findAll();
		return types;
	}

	/**
	 * according type enum import into db
	 * author david
	 */
	public void init() {
		List<CabinetType> types = new ArrayList<CabinetType>();
		// delete db data
		cabinetTypeDao.deleteAll();
		// import db data
		for (CabinetTypeEnum typeEnum : CabinetTypeEnum.values()) {
			CabinetType type = new CabinetType();
			type.setId((long) typeEnum.getCode());
			type.setTypeName(typeEnum.getName());
			type.setCapacity(typeEnum.getCapacity());
			type.setStatus(typeEnum.getStatus());
			types.add(type);
		}
		
		cabinetTypeDao.initSave(types);
	}
}

