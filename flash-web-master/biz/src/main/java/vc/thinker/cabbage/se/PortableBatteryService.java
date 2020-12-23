package vc.thinker.cabbage.se;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.se.bo.OrderBO;
import vc.thinker.cabbage.se.bo.PortableBatteryBO;
import vc.thinker.cabbage.se.dao.OrderDao;
import vc.thinker.cabbage.se.dao.PortableBatteryDao;
import vc.thinker.cabbage.se.model.PortableBattery;
import vc.thinker.cabbage.se.vo.PortableBatteryVO;

@Service
@Transactional
public class PortableBatteryService {

	@Autowired
	private PortableBatteryDao portableBatteryDao;
	
	@Autowired
	private OrderDao orderDao;
	
	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	public PortableBatteryBO findOne(Long id){
		return portableBatteryDao.findOne(id);
	}
	
	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	public PortableBatteryBO findByCode(String code){
		return portableBatteryDao.findByCode(code);
	}
	
	/**
	 * 分页查询
	 * @param page
	 * @param vo
	 * @return
	 */
	public List<PortableBatteryBO> findPageByVo(MyPage<PortableBatteryBO> page,PortableBatteryVO vo){
		return portableBatteryDao.findPageByVo(page,vo);
	}
	
	/**
	 * 主键删除
	 * @param id
	 */
	public void delete(Long id){
		portableBatteryDao.delete(id);
	}
	
	/**
	 * 校验充电宝资产编号
	 * @param id
	 * @param portableBatteryCode
	 * @return
	 */
	public Boolean checkPortableBatteryCode(Long id,String portableBatteryCode){
		if(null != id){
			PortableBatteryBO info = portableBatteryDao.findOne(id);
			if(!info.getPortableBatteryCode().equals(portableBatteryCode)){
				if(portableBatteryDao.countByPortableBatteryCode(portableBatteryCode) > 0){
					return false;
				}
			}
		}else {
			if(portableBatteryDao.countByPortableBatteryCode(portableBatteryCode) > 0){
				return false;
			}
		}
		
		return true;
	}

	/**
	 * 新增或者保存
	 */
	public void adminSaveOrUpdate(Long uid, PortableBatteryBO bo) {
		if(null == bo.getId()){
			bo.setStatus(PortableBatteryConstatns.PB_STATUS_ENABLE);
			bo.setLocationType(PortableBatteryConstatns.LOCATION_TYPE_IN_CABINET);
		}
		
		portableBatteryDao.save(bo);
	}

	public List<PortableBatteryBO> findBycabinetId(Long cabinetId) {
		return portableBatteryDao.findBycabinetId(cabinetId);
	}

	public List<PortableBatteryBO> findInCabinetByCabinetId(Long cabinetId) {
		return portableBatteryDao.findInCabinetByCabinetId(cabinetId);
	}

	/**
	 * 在线数据查询
	 * @param page
	 * @param vo
	 * @return
	 */
	public List<PortableBatteryBO> findOnlineData(MyPage<PortableBatteryBO> page, PortableBatteryVO vo) {
		
		List<PortableBatteryBO> pb_list = portableBatteryDao.findPageByVo(page,vo);
		if(null != pb_list && pb_list.size() > 0) {
			pb_list.forEach(e->{
				if(null != e.getMemberId()) {
					OrderBO order = orderDao.findLastUseByPbId(e.getId());
					if(null != order){
						e.setLastUseName(order.getNickname());
						e.setLastUseTime(order.getCreateTime());
						e.setLastUseCabinetCode(order.getReturnSysCode());
						e.setLastUseMobile(order.getMobile());
					}
				}
			});
		}
		
		page.setContent(pb_list);
		return pb_list;
	}

	public void update(PortableBattery pb) {
		portableBatteryDao.update(pb);
	}
	
}
