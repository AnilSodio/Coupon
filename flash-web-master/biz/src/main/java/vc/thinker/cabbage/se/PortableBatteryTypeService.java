package vc.thinker.cabbage.se;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.cabbage.se.bo.PortableBatteryTypeBO;
import vc.thinker.cabbage.se.dao.PortableBatteryTypeDao;
import vc.thinker.cabbage.se.vo.PortableBatteryTypeVO;
import vc.thinker.cabbage.common.MyPage;

@Service
@Transactional
public class PortableBatteryTypeService {

	@Autowired
	private PortableBatteryTypeDao portableBatteryTypeDao;
	
	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	public PortableBatteryTypeBO findOne(Long id){
		return portableBatteryTypeDao.findOne(id);
	}
	
	/**
	 * 分页查询
	 * @param page
	 * @param vo
	 * @return
	 */
	public List<PortableBatteryTypeBO> findPageByVo(MyPage<PortableBatteryTypeBO> page,PortableBatteryTypeVO vo){
		return portableBatteryTypeDao.findPageByVo(page,vo);
	}
	
	/**
	 * 查询所有
	 */
	public List<PortableBatteryTypeBO> findAll(){
		return portableBatteryTypeDao.findAll();
	}
	
	/**
	 * 主键删除
	 * @param id
	 */
	public void delete(Long id){
		portableBatteryTypeDao.delete(id);
	}
	
	/**
	 * typeName 校验
	 * @param id
	 * @param typeName
	 * @return
	 */
	public Boolean checkTypeName(Long id,String typeName){
		if(null != id){
			PortableBatteryTypeBO info = portableBatteryTypeDao.findOne(id);
			if(!info.getTypeName().equals(typeName)){
				if(portableBatteryTypeDao.countByTypeName(typeName) > 0){
					return false;
				}
			}
		}else {
			if(portableBatteryTypeDao.countByTypeName(typeName) > 0){
				return false;
			}
		}
		
		return true;
	}

	/**
	 * 修改或者新增操作
	 * @param bo
	 */
	public void adminSaveOrUpdate(Long uid,PortableBatteryTypeBO bo) {
		
		if(null == bo.getId()){
			bo.setCreateBy(uid);
			bo.setCreateTime(new Date());
			bo.setStatus(PortableBatteryConstatns.PB_STATUS_ENABLE);
		}
		
		portableBatteryTypeDao.save(bo);
	}
}
