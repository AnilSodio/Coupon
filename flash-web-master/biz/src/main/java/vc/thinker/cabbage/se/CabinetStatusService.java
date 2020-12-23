package vc.thinker.cabbage.se;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import cn.jiguang.common.utils.StringUtils;
import vc.thinker.cabbage.se.bo.CabinetBO;
import vc.thinker.cabbage.se.bo.CabinetTypeBO;
import vc.thinker.cabbage.se.dao.CabinetDao;
import vc.thinker.cabbage.se.dao.CabinetStatusDao;
import vc.thinker.cabbage.se.dao.CabinetTypeDao;
import vc.thinker.cabbage.se.dao.PortableBatteryDao;
import vc.thinker.cabbage.se.exception.CabinetNotFindException;
import vc.thinker.cabbage.se.model.CabinetStatus;
import vc.thinker.cabbage.se.model.CabinetStatus.ChannelStatus;
import vc.thinker.cabbage.se.model.CabinetStatus.RunStatus;
import vc.thinker.cabbage.se.model.PortableBattery;
import vc.thinker.cabbage.se.vo.CabinetFindVO;
import vc.thinker.cabbage.util.CabinetTypeEnum;

@Service
public class CabinetStatusService {

	@Autowired
	private CabinetStatusDao cabinetStatusDao;
	
	@Autowired
	private PortableBatteryDao portableBatteryDao;
	
	@Autowired
	private CabinetDao cabinetDao;
	
	@Autowired
	private CabinetTypeDao cabinetTypeDao;
	
	
	/**
	 * 同步配制
	 * @param cabinetId
	 * @param version
	 */
	public void syncSetting(Long cabinetId,String version){
		CabinetStatus status=cabinetStatusDao.findByCid(cabinetId);
		if(status == null){
			status=create(cabinetId, null);
		}
		status.setVersion(version);
		cabinetStatusDao.save(status);
	}
	
	/**
	 * 更新电池信息
	 * @param pb
	 */
	public void updateBattery(PortableBattery pb){
		pb.setUpdateTime(new Date());
		portableBatteryDao.insertUpdate(pb);
	}
	
	
	/**
	 * 心跳
	 * @param cabinetId
	 * @param channelStatusList
	 * @param usable_battery_new
	 * @param idlePositionNum
	 * @param existPositionNum
	 * @param serviceCode
	 */
	public void heartbeat(Long cabinetId,List<ChannelStatus> channelStatusList,Map<String, Integer> usable_battery_new
			,int idlePositionNum,int existPositionNum,String serviceCode){
		//带此参数表示同步整个充电柜电池信息，不带则只是增量同步
		//String TYPE=cmd.get("TYPE");
		//槽位状态
		CabinetStatus status=cabinetStatusDao.findByCid(cabinetId);
		if(status == null){
			status=create(cabinetId, serviceCode);
		}
		
		setBatteryTypeCount(usable_battery_new, status);
		
		status.setLastUpdateTime(new Date());
		status.setLastHeartbeat(new Date());
		status.setIdlePositionNum(idlePositionNum);
		status.setExistPositionNum(existPositionNum);
		status.setServiceCode(serviceCode);
		if(channelStatusList != null){
			status.setChannelStatusList(channelStatusList);
		}
		cabinetStatusDao.save(status);
	}
	
	/**
	 * 归还更新数据
	 * @param cabinetId
	 * @param channelStatusList
	 * @param usable_battery_new
	 * @param idlePositionNum
	 * @param existPositionNum
	 * @param serviceCode
	 */
	public void returnBack(Long cabinetId,Map<String, Integer> usable_battery_new
			,int idlePositionNum,int existPositionNum,String batteryCode,Integer channel,String serviceCode){
		CabinetStatus status=cabinetStatusDao.findByCid(cabinetId);
		if(status == null){
			status=create(cabinetId, serviceCode);
		}
		
		setBatteryTypeCount(usable_battery_new, status);
		status.setLastUpdateTime(new Date());
		status.setIdlePositionNum(idlePositionNum);
		status.setExistPositionNum(existPositionNum);
		status.setServiceCode(serviceCode);
		cabinetStatusDao.save(status);
		
		PortableBattery pb=new PortableBattery();
		pb.setPortableBatteryCode(batteryCode);
		pb.setLocationType(PortableBatteryConstatns.LOCATION_TYPE_IN_CABINET);
		pb.setLastLocationTime(new Date());
		pb.setCabinetId(cabinetId);
		pb.setCabinetChannel(channel);
		portableBatteryDao.updateByCode(pb, batteryCode);
	}
	
	/**
	 * 归还更新数据
	 * @param cabinetId
	 * @param channelStatusList
	 * @param usable_battery_new
	 * @param idlePositionNum
	 * @param existPositionNum
	 * @param serviceCode
	 */
	public void returnBack(Long cabinetId,String batteryCode,Integer channel,String serviceCode){
		CabinetStatus status=cabinetStatusDao.findByCid(cabinetId);
		if(status == null){
			status=create(cabinetId, serviceCode);
		}
		returnBack(status, batteryCode, channel, serviceCode);
	}
	
	/**
	 * 归还更新数据
	 * @param cabinetId
	 * @param channelStatusList
	 * @param usable_battery_new
	 * @param idlePositionNum
	 * @param existPositionNum
	 * @param serviceCode
	 */
	public void returnBack(CabinetStatus status,String batteryCode,Integer channel,String serviceCode){
		PortableBattery pb=new PortableBattery();
		pb.setPortableBatteryCode(batteryCode);
		pb.setLocationType(PortableBatteryConstatns.LOCATION_TYPE_IN_CABINET);
		pb.setLastLocationTime(new Date());
		pb.setCabinetId(status.getCid());
		pb.setCabinetChannel(channel);
		portableBatteryDao.updateByCode(pb, batteryCode);
	}
	
	/**
	 * 同步电池配制
	 * @param cabinetId
	 * @param channelStatusList
	 * @param usable_battery_new
	 * @param idlePositionNum
	 * @param existPositionNum
	 * @param pbList
	 * @param serviceCode
	 */
	public void syncBattery(Long cabinetId,List<ChannelStatus> channelStatusList,Map<String, Integer> usable_battery_new
			,int idlePositionNum,int existPositionNum,List<PortableBattery> pbList,String serviceCode){
		//带此参数表示同步整个充电柜电池信息，不带则只是增量同步
		//String TYPE=cmd.get("TYPE");
		//槽位状态
		CabinetStatus status=cabinetStatusDao.findByCid(cabinetId);
		if(status == null){
			status=create(cabinetId, serviceCode);
		}
		
		setBatteryTypeCount(usable_battery_new, status);
		
		status.setLastUpdateTime(new Date());
		status.setIdlePositionNum(idlePositionNum);
		status.setExistPositionNum(existPositionNum);
		status.setPositionTotal(channelStatusList.size());
		status.setChannelStatusList(channelStatusList);
		if(serviceCode != null){
			status.setServiceCode(serviceCode);
		}
		status.setLastHeartbeat(new Date());
		cabinetStatusDao.save(status);
		
		for (PortableBattery pb : pbList) {
			pb.setCabinetId(status.getCid());
			pb.setLocationType(PortableBatteryConstatns.LOCATION_TYPE_IN_CABINET);
			pb.setLastLocationTime(new Date());
			updateBattery(pb);
		}
		
	}
	
	/**
	 * 设置各类型数量
	 * @param usable_battery_new
	 * @param status
	 */
	private void setBatteryTypeCount(Map<String, Integer> param,CabinetStatus status){
		
		for (Map.Entry<String, Integer> ubn : param.entrySet()) {
			switch (ubn.getKey()) {
			case CabinetConstants.BATTERY_TYPE_1:
				status.setBatteryType1Count(ubn.getValue());
				break;
			case CabinetConstants.BATTERY_TYPE_2:
				status.setBatteryType2Count(ubn.getValue());
				break;
			case CabinetConstants.BATTERY_TYPE_3:
				status.setBatteryType3Count(ubn.getValue());
				break;
			case CabinetConstants.BATTERY_TYPE_4:
				status.setBatteryType4Count(ubn.getValue());
				break;
			}
		}
	}
	
	/**
	 * 登录
	 * @param code
	 * @param serviceCode
	 */
	public CabinetStatus login(String cabinetCode,String serviceCode){
		CabinetStatus status=cabinetStatusDao.findByCabinetCode(cabinetCode);
		if(status == null){
			status=create(cabinetCode, serviceCode);
		}
		return status;
	}
	/**
	 * 登录
	 * @param code
	 * @param serviceCode
	 */
	public CabinetStatus login(Long cabinetId,String serviceCode){
		CabinetStatus status=cabinetStatusDao.findByCid(cabinetId);
		if(status == null){
			status=create(cabinetId, serviceCode);
		}
		status.setServiceCode(serviceCode);
		cabinetStatusDao.save(status);
		return status;
	}
	
	/**
	 * 创建充电柜状态
	 * @param lockCode
	 * @return
	 */
	public CabinetStatus create(String code,String serviceCode){
		return create(null,code, serviceCode);
	}
	
	/**
	 * 创建充电柜状态
	 * @param lockCode
	 * @return
	 */
	public CabinetStatus create(Long cid,String serviceCode){
		return create(cid,null, serviceCode);
	}
	
	/**
	 * 创建充电柜状态
	 * @param lockCode
	 * @return
	 */
	public CabinetStatus create(Long cid,String code,String serviceCode){
		CabinetStatus cs=new CabinetStatus();
		CabinetBO cabinet =null;
		if(cid != null){
			cabinet = cabinetDao.findOne(cid);
		}else{
			cabinet = cabinetDao.findByCabinetCode(code);
		}
		if(cabinet == null){
			throw new CabinetNotFindException("Cabinet not find");
		}
		CabinetTypeBO cabinetType=cabinetTypeDao.findOne(cabinet.getTypeId());
		
		cs.setPositionTotal(cabinetType.getCapacity());
		if(cabinet.getLocationLon() != null && cabinet.getLocationLat() != null){
			cs.setLocation(new org.springframework.data.geo.Point(cabinet.getLocationLon().doubleValue(),cabinet.getLocationLat().doubleValue()));
			cs.setLocationDetails(cabinet.getLocationAddress()+cabinet.getLocationDesc());
		}
		cs.setRunStatus(RunStatus.normal);
		cs.setSysCode(cabinet.getSysCode());
		cs.setServiceCode(serviceCode);
		cs.setCid(cabinet.getId());
		cs.setSellerId(cabinet.getSellerId());
		cs.setCabinetCode(cabinet.getCabinetCode().trim());
		cs.setStatus(cabinet.getStatus());
		return cabinetStatusDao.save(cs);
	}

	/**
	 * 在线数据分页查询
	 * @param page
	 * @param vo
	 * @return
	 */
	public Page<CabinetStatus> findPageByVo(Pageable pageable, CabinetFindVO vo) {
		
		Query query = new Query();
		
		Criteria c= new Criteria();
		
		if(!StringUtils.isEmpty(vo.getSysCode())){
			c.and(CabinetStatus.F_SYS_CODE).regex(vo.getSysCode());
		}
		if(!StringUtils.isEmpty(vo.getLocationDetails())){
			c.and(CabinetStatus.F_LOCATION_DETAILS).regex(vo.getLocationDetails());
		}
		query.addCriteria(c);
		
		long total = cabinetStatusDao.countByQuery(query);
		
		query.with(pageable);
		List<CabinetStatus> messages = cabinetStatusDao.findByQuery(query);
		
		Page<CabinetStatus> resu_list = new PageImpl<CabinetStatus>(messages, pageable, total);
		
		return resu_list;
	}


	public CabinetStatus findBySysCode(String sysCode) {
		return cabinetStatusDao.findBySysCode(sysCode);
	}
	
	public CabinetStatus findByCabinetCode(String cabinetCode) {
		return cabinetStatusDao.findByCabinetCode(cabinetCode);
	}
}
