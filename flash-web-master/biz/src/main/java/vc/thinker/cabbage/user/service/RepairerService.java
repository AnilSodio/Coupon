package vc.thinker.cabbage.user.service;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sinco.common.area.Country;
import com.sinco.common.area.CountryUtil;
import com.sinco.common.security.PasswordUtil;
import com.sinco.common.utils.StringUtils;
import vc.thinker.cabbage.se.SeCommonConstants;
import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.bo.RepairerBO;
import vc.thinker.cabbage.user.dao.RepairerDao;
import vc.thinker.cabbage.user.model.Repairer;
import vc.thinker.cabbage.user.model.RepairerExample;
import vc.thinker.cabbage.user.vo.RepairerVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.sys.bo.UserAccountBO;
import vc.thinker.sys.dao.UserAccountDao;
import vc.thinker.sys.dao.UserDao;
import vc.thinker.sys.model.User;
import vc.thinker.sys.service.SystemService;

/**
 * 巡检员业务层
 * @author thinker
 *
 */
@Service
@Transactional
public class RepairerService {
	
	private Logger logger = LoggerFactory.getLogger(RepairerService.class);
	
	@Autowired
	private RepairerDao repairerDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserAccountDao userAccountDao;	
	
	@Autowired
	private SystemService systemService;
	
	/**
	 * 分页查询
	 * @param page
	 * @param vo
	 * @return
	 */
	public List<RepairerBO> findPageByVo(MyPage<RepairerBO> page, RepairerVO vo) {
		return repairerDao.findPageByVo(page,vo);
	}
	
	/**
	 * 根据 uid 查询
	 * @param uid
	 * @return
	 */
	public RepairerBO fndByUid(Long uid) {
		RepairerBO repairer = repairerDao.findDetailInfo(uid);
		return repairer;
	}

	/**
	 * 修改或新增
	 * @param bo
	 */
	public void saveOrUpdae(RepairerBO bo) {
		repairerDao.save(bo);
	}

	/**
	 * 校验手机号是否存在
	 * @param uid
	 * @param mobile
	 * @return
	 */
	public Boolean checkMobile(Long uid, String mobile) {
		if(null != uid){
			//修改
			RepairerBO repaire = repairerDao.findOne(uid);
			if(mobile.equals(repaire.getMobile())){
				return true;
			}else {
				RepairerExample example = new RepairerExample();
				example.createCriteria().andMobileEqualTo(mobile);
				List<RepairerBO> res_list = repairerDao.selectByExample(example);
				if(!res_list.isEmpty()){
					return false;
				}else {
					return true;
				}
			}
		}else {
			RepairerExample example = new RepairerExample();
			example.createCriteria().andMobileEqualTo(mobile);
			List<RepairerBO> res_list = repairerDao.selectByExample(example);
			if(!res_list.isEmpty()){
				return false;
			}else {
				return true;
			}
		}
	}

	public Boolean checkName(Long uid, String name) {
		if(null != uid){
			RepairerBO repaire = repairerDao.findOne(uid);
			if(!name.equals(repaire.getName())){
				RepairerExample example = new RepairerExample();
				example.createCriteria().andNameEqualTo(name);
				List<RepairerBO> res_list = repairerDao.selectByExample(example);
				if(!res_list.isEmpty()){
					return false;
				}
			}
			
			UserAccountBO user_acc = userAccountDao.findByLoginName(name, CommonConstants.ACCOUNT_TYPE_1);
			if(null != user_acc && uid.intValue() != user_acc.getUid().intValue()){
				return false;
			}
		}else {
			RepairerExample example = new RepairerExample();
			example.createCriteria().andNameEqualTo(name);
			List<RepairerBO> res_list = repairerDao.selectByExample(example);
			if(!res_list.isEmpty()){
				return false;
			}
			UserAccountBO user_acc = userAccountDao.findByLoginName(name, CommonConstants.ACCOUNT_TYPE_1);
			if(null != user_acc){
				return false;
			}
			
		}
		
		return true;
	}

	/**
	 * 停启用
	 * @param uid
	 * @param status
	 */
	public void startOrStop(Long uid, int status) {
		
		RepairerBO up_bo = new RepairerBO();
			
		up_bo.setUid(uid);
		up_bo.setStatus(status);
		up_bo.setUpdateTime(new Date());
		
		repairerDao.save(up_bo);
	}

	/**
	 * 校验车辆编码是否存在
	 * @param sysCode
	 * @return
	 */
//	public Boolean checkSysCode(String sysCode) {
//		BicycleStatus bicycle = bicycleStatusDao.findByLockSysCode(sysCode);
//		if(null != bicycle){
//			return true;
//		}
//		
//		return false;
//	}
	
//	/**
//	 * 校验电池编码是否存在
//	 * @param sysCode
//	 * @return
//	 */
//	public Boolean checkBatterySysCode(String sysCode) {
////		BatteryStatus batteryStatus = batteryStatusDao.findBySysCode(sysCode);
//		BatteryBO battery = batteryDao.findBySysCode(sysCode);
//		if(null != battery){
//			return true;
//		}
//		return false;
//	}
	
	/**
	 * 
	 * @param uid
	 * @return
	 */
	public RepairerBO findOne(Long uid){
		return repairerDao.findOne(uid);
	}
	
	/**
	 * 修改头像
	 * @param uid
	 * @param headImg
	 */
	public void updateHeadImg(Long uid,String headImg){
		Repairer r=new Repairer();
		r.setUid(uid);
		r.setHeadImgPath(headImg);
		repairerDao.update(r);
	}

	/***
	 * 保存维保人员轨迹
	 * @param uid
	 * @param x
	 * @param y
	 */
//	public void savePoint(Long uid,Point p){
//		TrackPoint trackPoint=new TrackPoint();
//		trackPoint.setUid(uid);
//		trackPoint.setLongitude(new BigDecimal(p.getX()));
//		trackPoint.setLatitude(new BigDecimal(p.getY()));
//		trackPoint.setCreateTime(new Date());
//		trackPointDao.save(trackPoint);
//	}
	
	/**
	 * 开始开锁
	 * @param uid
	 * @param p
	 */
//	public void startUnlock(Long uid,String sysCode,Point p,String orderCode){
//		
//		BicycleBO bicycle=bicycleDao.findBySysCode(sysCode);
//		if(bicycle == null){
//			throw new ServiceException("您输入的车编码有误，请重新输入");
//		}
//		int openIn = tripDao.countOpenIn(sysCode, uid);
//		if(openIn>0){
//			throw new ServiceException("抱歉，该单车有进行中的行程，无法开锁");
//		}
//		
//		List<ReserveBO> listr1 = reserveDao.findBySysCode(sysCode, CommonConstants.RESERVE_STATUS_1,new Date());
//		if(listr1.size()>0){
//			throw new ServiceException("抱歉，该单车有预约，无法开锁");
//		}
//		if(orderCode != null){
//			OrderWorkBO order=orderWorkDao.findByOrderCode(orderCode);
//			if(order == null){
//				throw new ServiceException("不存在工单号");
//			}
//		}
//		
//		RepairerSetBO repairerSet=repairerSetDao.findRepairerSet();
//		
//		if(!repairerSet.getIsUnlock()){
//			throw new ServiceException("没有权限开锁，请联系后台管理");
//		}
//		
//		RepairerLockLog lockLog=new RepairerLockLog();
//		
//		if(orderCode != null){
//			OrderWorkBO order=orderWorkDao.findByOrderCode(orderCode);
//			if(order == null){
//				throw new ServiceException("不存在工单号");
//			}
//			lockLog.setOrderId(order.getOrderId());
//		}
//		lockLog.setUid(uid);
//		lockLog.setSysCode(sysCode);
//		lockLog.setLongitude(new BigDecimal(p.getX()));
//		lockLog.setLatitude(new BigDecimal(p.getY()));
//		lockLog.setStatus(BicycleConstants.BICYCLE_STATUS_1);
//		lockLog.setOperateType(BicycleConstants.REPAIRER_LOCK_OPERATE_TYPE_UNLOCK);
//		lockLog.setCreateTime(new Date());
//		repairerLockLogDao.save(lockLog);
//	}
	
	/**
	 * 开锁
	 * @param sysCode
	 */
//	@Async
//	public void unlock(String sysCode){
//		
//		RepairerLockLog lockLog = repairerLockLogDao.findLastDo(sysCode);
//		if(lockLog == null){
//			log.info("没有对应的运维人员开锁记录");
//			return;
//		}
//		lockLog.setUnlockTime(new Date());
//		lockLog.setStatus(BicycleConstants.BICYCLE_STATUS_3);
//		repairerLockLogDao.save(lockLog);
//		
//		log.info("保存[{}] 运维人员开锁成功记录,记录ID[{}]",sysCode,lockLog.getId());
//	}
	
	/**
	 * 关锁
	 * @param sysCode
	 */
//	@Async
//	public void lock(String sysCode){
//		RepairerLockLog lockLog = repairerLockLogDao.findLastUnlock(sysCode);
//		if(lockLog == null){
//			log.info("没有对应的运维人员开锁成功记录");
//			return;
//		}
//		lockLog.setLockTime(new Date());
//		lockLog.setStatus(BicycleConstants.BICYCLE_STATUS_4);
//		repairerLockLogDao.save(lockLog);
//		
//		log.info("保存[{}] 运维人员关锁成功记录,记录ID[{}]",sysCode,lockLog.getId());
//	}
	
	/**
	 * 修改开锁失败的数据
	 * @param date
	 * @return
	 */
//	public int updateUnlockFail(Date date){
//		return tripDao.updateUnlockFail(date);
//	}

	/**
	 * 查询维保人员的详细信息（包括公司名称和机构名称）
	 * @param uid 维保人员的 uid
	 * @return
	 */
	public RepairerBO findDetailInfo(Long uid) {
		return repairerDao.findDetailInfo(uid);
	}


	/**
	 * 创建维保人员
	 * @param uid
	 * @param bo
	 */
	public void createRepairer(Long uid, RepairerBO bo) {
		
		//创建维保人员
		RepairerBO in_bo = new RepairerBO();
		in_bo.setUid(uid);
		in_bo.setName(bo.getName());
		in_bo.setHeadImgPath(bo.getHeadImgPath());
		in_bo.setMobile(bo.getMobile());
		in_bo.setStatus(SeCommonConstants.USER_REPAIRER_STATUS_1);
		in_bo.setAreaId(bo.getAreaId());
		in_bo.setCreateTime(new Date());
		repairerDao.save(in_bo);
		
		//更新用户的机构信息
		if(null != bo.getCompanyId() && null != bo.getOfficeId()){
			User user = new User();
			user.setId(uid);
			user.setCompanyId(bo.getCompanyId());
			user.setOfficeId(bo.getOfficeId());
			user.setUpdateTime(new Date());
			userDao.save(user);
		}
		
	}

	/**
	 * 后台创建或者新增维保人员 
	 * @param currentUser 当前操作人
	 * @param repairer
	 * @param officeId 机构
	 * @param companyId 公司
	 * @param password 密码
	 */
	public void adminCreateOrUpdateRepairer(User currentUser, Repairer repairer,Long officeId,Long companyId,String password) {
		if(null == repairer.getUid()){
			//创建维保人员
			UserAccountBO user_acc = userAccountDao.findByLoginName(repairer.getName(), CommonConstants.ACCOUNT_TYPE_1);
			if(null != user_acc ){
				logger.info("【"+repairer.getName()+"】对应的账户，已经存在");
				return ;
			}
			//创建账户信息
			Country country = CountryUtil.get(repairer.getCountry());
			Long uid = systemService.createUser(CommonConstants.ACCOUNT_TYPE_1, 
					companyId, officeId, null, repairer.getName(), password, "", country, currentUser);
			
			//创建基本信息
			repairer.setUid(uid);
			repairer.setStatus(SeCommonConstants.USER_REPAIRER_STATUS_1);
			repairer.setCreateTime(new Date());
			repairerDao.save(repairer);
			
			return ;
		}
		
		adminUpdateRepairer(repairer,officeId,companyId,password);
		
	}
	
	/**
	 * 维保用户的修改
	 * 1、更新机构信息
	 * 2、更新账户密码
	 * 3、更新基本信息
	 */
	public void adminUpdateRepairer(Repairer repairer,Long officeId,Long companyId,String password){
		
		User user = new User();
		user.setId(repairer.getUid());
		user.setCompanyId(companyId);
		user.setOfficeId(officeId);
		user.setUpdateTime(new Date());
		userDao.update(user);
		
		repairer.setUpdateTime(new Date());
		repairerDao.update(repairer);
		
		if(StringUtils.isEmpty(password)){
			return ;
		}
		
		UserAccountBO account = userAccountDao.findByUid(repairer.getUid(), CommonConstants.ACCOUNT_TYPE_1);
		UserAccountBO ac_up = new UserAccountBO();
		ac_up.setId(account.getId());
		ac_up.setPassword(PasswordUtil.entryptPassword(password));
		userAccountDao.update(ac_up);
		
	}
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<RepairerBO> findAll() {
		return repairerDao.findAll();
	}

	/**
	 * 根据uids 查询
	 * @param uidList
	 * @return
	 */
//	public List<RepairerBO> findByUids(List<Long> uidList) {
//		return repairerDao.findByUids(uidList);
//	}

	
//	public Boolean checkElectrombileCode(String sysCode) {
//		
//		if(electrombileDao.countBySysCode(sysCode)>0){
//			
//			return true;
//		}
//		return false;
//	}

}
