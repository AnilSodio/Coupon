package vc.thinker.cabbage.se.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.sinco.common.utils.StringUtils;

import vc.thinker.cabbage.se.CabinetConstants;
import vc.thinker.cabbage.se.model.CabinetStatus;
import vc.thinker.cabbage.se.vo.CabinetFindVO;
import vc.thinker.cabbage.sys.dao.SysSettingDao;
import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.lbs.LbsBiz;

@Repository
public class CabinetStatusDao {
	
	private static final Logger log = LoggerFactory.getLogger(CabinetStatusDao.class);

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	@Lazy(true)
	private LbsBiz lbsBiz;
	
	@Autowired
	private SysSettingDao sysSettingDao;
	
	public CabinetStatus save(CabinetStatus cabinetStatus) {
		mongoTemplate.save(cabinetStatus);
		return cabinetStatus;
	}
	
	/**
	 * 
	 * @param vo
	 * @return
	 */
	public List<CabinetStatus> findByVO(CabinetFindVO vo){
		
		Criteria c= new Criteria();
		if(vo.getP() != null && vo.getDistance() != null){
			c.and(CabinetStatus.F_LOCATION).nearSphere(new GeoJsonPoint(vo.getP()))
			.maxDistance(vo.getDistance());
		}
		if(null != vo.getIsIdle()){
			if(vo.getIsIdle()){
				c.and(CabinetStatus.F_IDLE_POSITION_NUM).lte(0);
			}else{
				c.and(CabinetStatus.F_IDLE_POSITION_NUM).gt(0);
			}
		}
		if(null != vo.getIsExist()){
			if(vo.getIsExist()){
				c.and(CabinetStatus.F_EXIST_POSITION_NUM).lte(0);
			}else{
				c.and(CabinetStatus.F_EXIST_POSITION_NUM).gt(0);
			}
		}
		Query query = new Query();
		query.addCriteria(c);
		
		if(vo.getCount() != null){
			query.limit(vo.getCount());
		}
		return mongoTemplate.find(query, CabinetStatus.class);
	}
	
	
	/**
	 * 修改
	 * @param id
	 * @param param
	 */
	public void update(String id,Map<String, Object> param){
		Query query=new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		
		Update update=new Update();
		for (Map.Entry<String, Object> entry : param.entrySet()) {
			update.set(entry.getKey(), entry.getValue());
		}
		update.set(CabinetStatus.F_LAST_UPDATE_TIME, new Date());
		mongoTemplate.updateFirst(query, update, CabinetStatus.class);
	}
	
	/**
	 * 修改心跳
	 * @param lockCode
	 */
	public void updateHeartbeat(String code,String serviceCode){
		Query query=new Query();
		query.addCriteria(Criteria.where(CabinetStatus.F_CABINET_CODE).is(code));
		
		Update update=new Update();
		Date date = new Date();
		update.set(CabinetStatus.F_LAST_HEART_BEAT, date);
		log.info("[{}] update [{}] beartbeat",DateFormatUtils.format(date, "yyyy-MM-dd hh:mm:ss"),code);
		mongoTemplate.updateFirst(query, update, CabinetStatus.class);
	}
	
	/**
	 * 
	 * @param lockSysCode
	 * @return
	 */
	public CabinetStatus findByCabinetCode(String code){
		Query query=new Query();
		query.addCriteria(Criteria.where(CabinetStatus.F_CABINET_CODE).is(code));
		return mongoTemplate.findOne(query, CabinetStatus.class);
	}
	
	public CabinetStatus findBySysCode(String code){
		Query query=new Query();
		query.addCriteria(Criteria.where(CabinetStatus.F_SYS_CODE).is(code));
		return mongoTemplate.findOne(query, CabinetStatus.class);
	}
	
	public CabinetStatus findByCid(Long cid){
		Query query=new Query();
		query.addCriteria(Criteria.where(CabinetStatus.F_CID).is(cid));
		return mongoTemplate.findOne(query, CabinetStatus.class);
	}
	/**
	 * 服务商机柜
	 * @param sellerId
	 * @return
	 */
	public List<CabinetStatus> findBySellerId(Long sellerId){
		Query query=new Query();
		query.addCriteria(Criteria.where(CabinetStatus.F_SELLER_ID).is(sellerId));
		return mongoTemplate.find(query, CabinetStatus.class);
	}
	
	public List<CabinetStatus> findNormalBySellerId(Long sellerId){
		Query query=new Query();
		Criteria c= new Criteria();
		c.and(CabinetStatus.F_SELLER_ID).is(sellerId);
		c.and(CabinetStatus.F_STATUS).is(CabinetConstants.CABINET_STATUS_1);
		c.and(CabinetStatus.F_RUN_STATUS).is(CabinetStatus.RunStatus.normal);
		query.addCriteria(c);
		return mongoTemplate.find(query, CabinetStatus.class);
	}

	public List<CabinetStatus> findByQuery(Query query) {
		return mongoTemplate.find(query, CabinetStatus.class);
	}

	public long countByQuery(Query query) {
		return mongoTemplate.count(query, CabinetStatus.class);
	}

	public Page<CabinetStatus> findPageByVo(PageRequest pr, CabinetFindVO vo) {
		
		SysSetting sysSet = sysSettingDao.findOne();
		int isOnlineTime = null != sysSet.getIsOnlineTime() ? sysSet.getIsOnlineTime():30;
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -(isOnlineTime));
		Date lastHeartbeat = cal.getTime();
		
		Query query = new Query();
		Criteria c= new Criteria();
		
		if(null != vo.getOnLine() && vo.getOnLine()){
			c.and(CabinetStatus.F_LAST_UPDATE_TIME).gte(lastHeartbeat);
		}else {
			Criteria or = new Criteria().orOperator(
				Criteria.where(CabinetStatus.F_LAST_UPDATE_TIME).is(null),
				Criteria.where(CabinetStatus.F_LAST_UPDATE_TIME).lte(lastHeartbeat)
			);
			c.orOperator(or);
		}
		
		if(!StringUtils.isEmpty(vo.getSysCode())){
			c.and(CabinetStatus.F_SYS_CODE).regex(vo.getSysCode());
		}
		if(!StringUtils.isEmpty(vo.getLocationDetails())){
			c.and(CabinetStatus.F_LOCATION_DETAILS).regex(vo.getLocationDetails());
		}
		
		if(null != vo.getStatus()){
			c.and(CabinetStatus.F_STATUS).is(vo.getStatus());
		}
		query.addCriteria(c);
		long total = mongoTemplate.count(query, CabinetStatus.class);
		
		if(null != pr){
			query.with(pr);
		}
		List<CabinetStatus> messages =  mongoTemplate.find(query, CabinetStatus.class);
		
		Page<CabinetStatus> resu_list = new PageImpl<CabinetStatus>(messages, pr, total);
		
		return resu_list;
		
	}
	
	/**
	 * 校验机柜是否在线
	 * @param c
	 * @return
	 */
	public Boolean checkIsOnline(CabinetStatus c){
		
		SysSetting setting = sysSettingDao.findOne();
		return checkIsOnline(c,setting.getIsOnlineTime());
	}
	
	/**
	 * 校验机柜是否在线
	 * @param c
	 * @return
	 */
	public Boolean checkIsOnline(CabinetStatus c,Integer onlineTime){
		
		if(null == c){
			return false;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -(onlineTime));
		Date lastHeartbeat = cal.getTime();
		

		if(null != c.getLastHeartbeat() && 
				c.getLastHeartbeat().after(lastHeartbeat)){
			return true;
		}
		
		return false;
	}
}
