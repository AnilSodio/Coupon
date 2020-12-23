package vc.thinker.cabbage.se;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinco.common.utils.DateUtils;

import cn.jiguang.common.utils.StringUtils;
import vc.thinker.biz.exception.ServiceException;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.se.bo.CabinetBO;
import vc.thinker.cabbage.se.bo.CabinetTypeBO;
import vc.thinker.cabbage.se.bo.SysCodeBO;
import vc.thinker.cabbage.se.dao.CabinetDao;
import vc.thinker.cabbage.se.dao.CabinetStatusDao;
import vc.thinker.cabbage.se.dao.CabinetTypeDao;
import vc.thinker.cabbage.se.dao.SysCodeDao;
import vc.thinker.cabbage.se.model.CabinetStatus;
import vc.thinker.cabbage.se.vo.CabinetFindVO;
import vc.thinker.cabbage.se.vo.CabinetVO;
import vc.thinker.cabbage.stats.UpdateCainetStatsService.CabinetCreateEvent;
import vc.thinker.cabbage.sys.dao.SysSettingDao;
import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.cabbage.user.bo.SellerBO;
import vc.thinker.cabbage.user.dao.SellerDao;
import vc.thinker.cabbage.user.model.Seller;

@Service
@Transactional
public class CabinetService {

	private static Logger logger = LoggerFactory.getLogger(CabinetService.class);
	
	@Autowired
	private CabinetDao cabinetDao;
	
	@Autowired
	private CabinetStatusDao cabinetStatusDao;
	
	@Autowired
	private SysCodeDao sysCodeDao;
	
	@Autowired
	private SellerDao sellerDao;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private CabinetTypeDao cabinetTypeDao;
	
	@Autowired
	private SysSettingDao sysSettingDao;
	
	/**
	 * 机柜详情
	 * @param sysCode
	 * @return
	 */
	public CabinetBO findDetailsBySysCode(String sysCode){
		CabinetBO cabinet=cabinetDao.findBySysCode(sysCode);
		if(cabinet != null){
			cabinet.setOnline(false);
			CabinetStatus cs=cabinetStatusDao.findBySysCode(sysCode);
			if(cs != null){
				cabinet.setBatteryType2Count(cs.getBatteryType2Count());
				cabinet.setBatteryType3Count(cs.getBatteryType3Count());
				cabinet.setBatteryType4Count(cs.getBatteryType4Count());
				cabinet.setIdlePositionNum(cs.getIdlePositionNum());
				cabinet.setPositionTotal(cs.getPositionTotal());
				cabinet.setChannelStatusList(cs.getChannelStatusList());
				cabinet.setLastHeartbeat(cs.getLastHeartbeat());
				SysSetting sysSet = sysSettingDao.findOne();
				int isOnlineTime = null != sysSet.getIsOnlineTime() ? sysSet.getIsOnlineTime():30;
				cabinet.setOnline(cabinetStatusDao.checkIsOnline(cs, isOnlineTime));
			}
		}
		return cabinet;
	}
	
	
	public CabinetBO findOne(Long id){
		return cabinetDao.findOne(id);
	}
	
	public CabinetBO findByCabinetCode(String code){
		return cabinetDao.findByCabinetCode(code);
	}
	
	public CabinetBO findBySysCode(String sysCode){
		return cabinetDao.findBySysCode(sysCode);
	}
	
	public List<CabinetBO> findPageByVo(MyPage<CabinetBO> page,CabinetVO vo){
		return cabinetDao.findPageByVo(page,vo);
	}
	
	/**
	 * save or update cabinet info
	 * @param uid
	 * @param bo
	 */
	public void adminSaveOrUpdate(Long uid, CabinetBO bo) {
		if(null == bo.getId()){
			if(StringUtils.isEmpty(bo.getSysCode())){
				throw new ServiceException("系统编号，不能为空！");
			}
			
			bo.setCreateBy(uid);
			bo.setCreateTime(new Date());
			bo.setStatus(SeCommonConstants.CABINET_STATUS_ENABLE);
			bo.setIsDelivery(SeCommonConstants.CABINET_IS_DELIVERY_FALSE);
			
			SysCodeBO codeBo = sysCodeDao.findBySysCode(bo.getSysCode());
			SysCodeBO up_bo = new SysCodeBO();
			up_bo.setId(codeBo.getId());
			up_bo.setIsBinding(true);
			up_bo.setBindTime(new Date());
			sysCodeDao.update(up_bo);
			
			//统计
			CabinetCreateEvent event = new CabinetCreateEvent();
			event.setCurrentDay(DateUtils.formatDate(new Date()));
			publisher.publishEvent(event);
			
			cabinetDao.save(bo);
		}else {
			cabinetDao.update(bo);
			CabinetBO cabinet = cabinetDao.findOne(bo.getId());
			CabinetStatus cabinetstatus = cabinetStatusDao.findBySysCode(cabinet.getSysCode());
			if(null != cabinetstatus){
				CabinetTypeBO cabinetType = cabinetTypeDao.findOne(cabinet.getTypeId());
				Map<String, Object> param=new HashMap<>();
				if(null != cabinetType.getCapacity()){
					param.put(CabinetStatus.F_POSITIONTOTAL, cabinetType.getCapacity());
				}
				if(!StringUtils.isEmpty(bo.getLocationAddress())){
					param.put(CabinetStatus.F_LOCATION_DETAILS, bo.getLocationAddress()+bo.getLocationDesc());
				}
				
				if(null == bo.getStatus()){
					param.put(CabinetStatus.F_STATUS,CabinetConstants.CABINET_STATUS_1);
				}else {
					param.put(CabinetStatus.F_STATUS, bo.getStatus());
				}
				
				cabinetStatusDao.update(cabinetstatus.getId(), param);
			}
		}
	}

	public Boolean checkCabinetCode(Long id, String cabinetCode) {
		if(null != id){
			CabinetBO info = cabinetDao.findOne(id);
			if(!info.getCabinetCode().equals(cabinetCode)){
				if(cabinetDao.countByCabinetCode(cabinetCode) > 0){
					return false;
				}
			}
			
		}else {
			if(cabinetDao.countByCabinetCode(cabinetCode) > 0){
				return false;
			}
		}
		return true;
	}

	public void delete(Long id) {
		cabinetDao.delete(id);
	}

	/**
	 * 充电柜投放
	 * @param uid 操作人
	 * @param id 充电柜id
	 * @param sellerId 商户id
	 */
	public void cabinetDelivery(Long uid,Long id, Seller seller) {
		seller = sellerDao.findOne(seller.getUid());
		
		CabinetBO up_info = new CabinetBO();
		up_info.setId(id);
		up_info.setIsDelivery(true);
		up_info.setDeliveryId(uid);
		up_info.setDeliveryTime(new Date());
		up_info.setSellerId(seller.getUid());
		up_info.setLocationLat(seller.getLocationLat());
		up_info.setLocationLon(seller.getLocationLon());
		up_info.setLocationAddress(seller.getLocationAddress());
		up_info.setLocationDesc(seller.getLocationDesc());
		
		cabinetDao.update(up_info);
		
		CabinetBO cabinet = cabinetDao.findOne(id);
		CabinetStatus cabinetstatus = cabinetStatusDao.findBySysCode(cabinet.getSysCode());
		if(null != cabinetstatus){
			Map<String, Object> param=new HashMap<>();
			param.put(CabinetStatus.F_SELLER_ID, cabinet.getSellerId());
			cabinetStatusDao.update(cabinetstatus.getId(), param);
		}
	}
	
	
	/**
	 * 充电柜投放
	 * @param uid 操作人
	 * @param id 充电柜id
	 * @param sellerId 商户id
	 */
	public void cabinetDelivery(Long uid,Long id, Long sellerId,String address) {
		
		SellerBO seller = sellerDao.findOne(sellerId);
		
		CabinetBO up_info = new CabinetBO();
		up_info.setId(id);
		up_info.setIsDelivery(true);
		up_info.setDeliveryId(uid);
		up_info.setDeliveryTime(new Date());
		up_info.setSellerId(sellerId);
		up_info.setLocationLat(seller.getLocationLat());
		up_info.setLocationLon(seller.getLocationLon());
		up_info.setLocationAddress(seller.getLocationAddress());
		if(!StringUtils.isEmpty(address)){
			up_info.setLocationAddress(address);
		}
		up_info.setLocationDesc(seller.getLocationDesc());
		
		cabinetDao.update(up_info);
		
		CabinetBO cabinet = cabinetDao.findOne(id);
		CabinetStatus cabinetstatus = cabinetStatusDao.findBySysCode(cabinet.getSysCode());
		if(null != cabinetstatus){
			Map<String, Object> param=new HashMap<>();
			param.put(CabinetStatus.F_SELLER_ID, cabinet.getSellerId());
			cabinetStatusDao.update(cabinetstatus.getId(), param);
		}
	}
	/**
	 * 根据经纬度查询 
	 * @param lat 维度
	 * @param lng 经度
	 * @return
	 */
	public List<Map<String,Object>> findByLocation(Double lat,Double lng) {
		
		List<Map<String,Object>> list = new ArrayList<>();
		
		Point p = new Point(lng, lat);
		List<CabinetBO> cabinet_list = cabinetDao.findByLocation(p,10000);
		if(!cabinet_list.isEmpty()){
			cabinet_list.forEach(e->{
				
				Map<String, Object> map = new LinkedHashMap<>();
				map.put("lat", e.getLocationLat());
				map.put("lng", e.getLocationLon());
				map.put("sysCode", e.getSysCode());
				map.put("address", e.getLocationAddress());
				map.put("id", e.getId());
				list.add(map);
				
			});
		}
			
		return list;
	}

	public List<Map<String, Object>> findLocationBySysCode(String sysCode) {
		
		List<Map<String,Object>> list = new ArrayList<>();
		
		CabinetBO e = cabinetDao.findBySysCode(sysCode);
		if(null != e){
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("lat", e.getLocationLat());
			map.put("lng", e.getLocationLon());
			map.put("sysCode", e.getSysCode());
			map.put("address", e.getLocationAddress());
			map.put("id", e.getId());
			list.add(map);
			
		}
		return list;
	}

	/**
	 * 在线数据查询
	 */
	public List<CabinetBO> findOnlineDate(MyPage<CabinetBO> page, CabinetVO vo) {
		
		List<CabinetBO> list = new ArrayList<CabinetBO>();
		// 是否在线查询，只能从 mongo 中查询
		if(null != vo.getOnline()){
			
			PageRequest pr = new PageRequest(page.getPageNo() -1,20);
			CabinetFindVO findVo = new CabinetFindVO();
			findVo.setOnLine(vo.getOnline());
			findVo.setSysCode(vo.getSysCode());
			findVo.setLocationDetails(vo.getLocationAddress());
			findVo.setStatus(vo.getStatus());
			
			Page<CabinetStatus> onlineStatus = cabinetStatusDao.findPageByVo(pr,findVo);
			
			if(null != onlineStatus.getContent() && onlineStatus.getContent().size()>0){
				for (CabinetStatus e : onlineStatus.getContent()) {
					CabinetBO cabinet = new CabinetBO();
					cabinet.setPositionTotal(e.getPositionTotal());
					cabinet.setId(e.getCid());
					cabinet.setExistPositionNum(e.getExistPositionNum());
					cabinet.setIdlePositionNum(e.getIdlePositionNum());
					// low power num
					cabinet.setDisablePositionNum(cabinet.getPositionTotal() - e.getExistPositionNum() - e.getIdlePositionNum());
					if(null != e.getSellerId()){
						SellerBO selelr = sellerDao.findOne(e.getSellerId());
						if(null != selelr){
							cabinet.setSellerName(selelr.getSellerName());
						}
					}
					cabinet.setStatus(e.getStatus());
					cabinet.setOnline(vo.getOnline());
					cabinet.setSysCode(e.getSysCode());
					
//					CabinetBO c = cabinetDao.findBySysCode(e.getSysCode());
					cabinet.setLocationAddress(e.getLocationDetails());
					cabinet.setLastUpdateTime(e.getLastUpdateTime());
					cabinet.setLastHeartbeat(e.getLastHeartbeat());
					list.add(cabinet);
				}
			}
			page.setCount(onlineStatus.getTotalElements());
			page.setContent(list);
			return list;
		}
		
		list = cabinetDao.findPageByVo(page, vo);
		
		SysSetting sysSet = sysSettingDao.findOne();
		int isOnlineTime = null != sysSet.getIsOnlineTime() ? sysSet.getIsOnlineTime():30;
		
		if(!list.isEmpty()){
			list.forEach(e->{
				CabinetStatus cabinet = cabinetStatusDao.findBySysCode(e.getSysCode());
				if(null != cabinet){
					e.setOnline(cabinet.getOnline());
					e.setIdlePositionNum(cabinet.getIdlePositionNum());
					e.setExistPositionNum(cabinet.getExistPositionNum());
					e.setPositionTotal(cabinet.getPositionTotal());
					e.setDisablePositionNum(cabinet.getPositionTotal()- cabinet.getExistPositionNum() -cabinet.getIdlePositionNum());
					e.setLastUpdateTime(cabinet.getLastUpdateTime());
					e.setLastHeartbeat(cabinet.getLastHeartbeat());
					e.setVersion(cabinet.getVersion());
					e.setOnline(cabinetStatusDao.checkIsOnline(cabinet, isOnlineTime));
				}
			});
		}
		page.setContent(list);
		return list;
	}

	public CabinetBO findDetailsOne(Long id) {
		return cabinetDao.findDetailsOne(id);
	}

	public int countByStatusAndDelivery(int status,Boolean isDelivery) {
		return cabinetDao.countByStatusAndDelivery(status,isDelivery);
	}

	public Boolean checkCabinetAlias(Long id, String cabinetAlias) {
		if(null != id){
			CabinetBO info = cabinetDao.findOne(id);
			if(!cabinetAlias.equals(info.getCabinetAlias())){
				if(cabinetDao.countByCabinetAlias(cabinetAlias)>0){
					return false;
				}
			}
		}else {
			if(cabinetDao.countByCabinetAlias(cabinetAlias)>0){
				return false;
			}
		}
		return true;
	}

	public CabinetBO findbyCabinetAlias(String cabinetAlias) {
		return cabinetDao.findbyCabinetAlias(cabinetAlias);
	}

	public int countByCabinetAlias(String cabinetAlias) {
		return cabinetDao.countByCabinetAlias(cabinetAlias);
	}

	public CabinetBO findByAliasOrSysCode(String cabinetAlias) {
		return cabinetDao.findByAliasOrSysCode(cabinetAlias);
	}
	
	/**
	 * 校验是否在线
	 * @param onlineTime
	 * @param c
	 * @return
	 */
	public Boolean checkIsOnline(Integer onlineTime,CabinetStatus c){
		return cabinetStatusDao.checkIsOnline(c, onlineTime);
		
	}

	public void disAndEnable(Long id, int status) {
		
		CabinetBO up_bo = new CabinetBO();
		up_bo.setId(id);
		up_bo.setStatus(status);
		cabinetDao.update(up_bo);
		
		CabinetBO cabinet = cabinetDao.findOne(id);
		CabinetStatus cabinetstatus = cabinetStatusDao.findBySysCode(cabinet.getSysCode());
		if(null != cabinetstatus){
			Map<String, Object> param=new HashMap<>();
			param.put(CabinetStatus.F_STATUS, status);
			cabinetStatusDao.update(cabinetstatus.getId(), param);
		}
	}
	
	/**
	 * 根据充电柜类型查询所有的充电柜
	 * @return
	 */
	public List<CabinetBO> findCabinetsByType(long cabinetType) {
		return cabinetDao.findCabinetsByType(cabinetType);
	}
}
