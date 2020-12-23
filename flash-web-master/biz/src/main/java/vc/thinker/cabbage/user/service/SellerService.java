/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.cabbage.user.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sinco.common.area.Country;
import com.sinco.common.area.CountryUtil;
import com.sinco.common.security.PasswordUtil;

import cn.jiguang.common.utils.StringUtils;
import vc.thinker.cabbage.se.CabinetService;
import vc.thinker.cabbage.se.SeCommonConstants;
import vc.thinker.cabbage.se.dao.CabinetStatusDao;
import vc.thinker.cabbage.se.model.CabinetStatus;
import vc.thinker.cabbage.sys.dao.SysSettingDao;
import vc.thinker.cabbage.sys.model.SysSetting;
//git.thinker.vc/dengwei/nomo.git
import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.bo.SellerBO;
import vc.thinker.cabbage.user.dao.SellerDao;
import vc.thinker.cabbage.user.dao.UserMoneyDao;
import vc.thinker.cabbage.user.model.Seller;
import vc.thinker.cabbage.user.model.UserMoney;
import vc.thinker.cabbage.user.vo.SellerVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.sys.bo.UserAccountBO;
import vc.thinker.sys.dao.UserAccountDao;
import vc.thinker.sys.service.SystemService;

/**
 * 商户service
 * @author nanzhi
 * @version 2017-12-14
 */
@Service
@Transactional(readOnly = true)
public class SellerService{
	
	private Logger logger = LoggerFactory.getLogger(SellerService.class);

	
	@Autowired
	private SellerDao sellerDao;
	
	@Autowired
	private CabinetStatusDao cabinetStatusDao;
	
	@Autowired
	private UniqueRadomCodeService uniqueRadomCodeService; 
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private UserMoneyDao userMoneyDao;
	
	@Autowired
	private UserAccountDao userAccountDao;
	
	@Autowired
	private SysSettingDao sysSettingDao;
	
	@Autowired
	private CabinetService cabinetService;
	
	/**
	 * 查找正常的服务商
	 * @param p
	 * @param distance
	 * @return
	 */
	public List<SellerBO> findNormalByLocation(Point p,Integer distance){
		return sellerDao.findNormalByLocation(p, distance);
	}

	public SellerBO findById(Long id) {
		return sellerDao.findOne(id);
	}
	
	/**
	 * 查找服务商充电宝详情
	 * @param id
	 * @return
	 */
	public SellerBO findCabinetDetails(Long id) {
		
		SysSetting sys = sysSettingDao.findOne();
		int onlineTime = null != sys.getIsOnlineTime() ? sys.getIsOnlineTime():30;
		
		SellerBO seller=sellerDao.findOne(id);
		if(seller != null){
			List<CabinetStatus> csList=cabinetStatusDao.findNormalBySellerId(seller.getUid());
			int type2=0;
			int type3=0;
			int type4=0;
			int idlePositionNum=0;
			for (CabinetStatus e : csList) {
				//校验是否在线

				Boolean isonline = cabinetService.checkIsOnline(onlineTime,e);
				
//				logger.info(e.getSysCode()+"【"+isonline+"】");
				
				if(isonline){

					type2+=null != e.getBatteryType2Count()?e.getBatteryType2Count():0;
					type3+=null != e.getBatteryType3Count()?e.getBatteryType3Count():0;
					type4+=null != e.getBatteryType4Count()?e.getBatteryType4Count():0;
					idlePositionNum+=null != e.getIdlePositionNum()?e.getIdlePositionNum():0;
				}
			}
			seller.setBatteryType2Count(type2);
			seller.setBatteryType3Count(type3);
			seller.setBatteryType4Count(type4);
			seller.setIdlePositionNum(idlePositionNum);
		}
		return seller;
	}

	/**
	 * 分页查询
	 * @param page
	 * @param vo
	 * @return
	 */
	public List<SellerBO> findPageByVo(MyPage<SellerBO> page, SellerVO vo) {
		return sellerDao.findPageByVo(page,vo);
	}

	@Transactional(readOnly = false)
	public void createSeller(Seller seller,String ip){
		
		UserAccountBO user_acc = userAccountDao.findByLoginName(seller.getEmail());
		
		Long uid = null;
		
		if(null == user_acc){
			
			Country country=CountryUtil.get(seller.getCountry());
			
			String pwd = seller.getContactMobile().substring(seller.getContactMobile().length() - 6).trim();
			uid = systemService.createUser(CommonConstants.ACCOUNT_TYPE_9,seller.getEmail(),pwd, ip, country);
			
			UserMoney money = new UserMoney();
			money.setUid(uid);
			money.setCurrency(country.getCurrencyCode());
			userMoneyDao.save(money);
			
		}else {
			uid = user_acc.getUid();
		}
		
		seller.setUid(uid);
		seller.setCreateTime(new Date());
		seller.setStatus(SeCommonConstants.USER_SELLER_STATUS_1);
		seller.setInviteCode(uniqueRadomCodeService.getCode(CommonConstants.CODE_TYPE_INVITION));
//		seller.setRebateRate(new BigDecimal("0.2"));
		sellerDao.save(seller);
	}
	
	
	/**
	 * 修改或者新增
	 * @param up_info
	 */
	@Transactional(readOnly = false)
	public void saveOrUpdate(Seller seller,String ip) {
		
		if(null == seller.getUid()){
			createSeller(seller,ip);
			return ;
		}
		
		SellerBO info = sellerDao.findOne(seller.getUid());
		if(!info.getContactMobile().equals(seller.getContactMobile())) {
			// 修改登陆密码
			UserAccountBO account = userAccountDao.findByLoginName(info.getEmail(), CommonConstants.ACCOUNT_TYPE_9);
			UserAccountBO up_bo = new UserAccountBO();
			up_bo.setId(account.getId());
			String pwd = seller.getContactMobile().substring(seller.getContactMobile().length() - 6);
			up_bo.setPassword(PasswordUtil.entryptPassword(pwd.trim()));
			userAccountDao.update(up_bo);
		}
		
		seller.setUpdateTime(new Date());
		sellerDao.update(seller);
		
	}

	@Transactional(readOnly = false)
	public void save(SellerBO seller) {
		seller.setUpdateTime(new Date());
		sellerDao.save(seller);
		
	}
	public Boolean checkSellerName(Long uid, String sellerName) {
		if(null != uid){
			SellerBO info = sellerDao.findOne(uid);
			if(!sellerName.equals(info.getSellerName())){
				if(sellerDao.countBySellerName(sellerName) > 0){
					return false;
				}
			}
		}else {
			if(sellerDao.countBySellerName(sellerName) > 0){
				return false;
			}
		}
		
		return true;
	}
	
	
	public Boolean checkContactMobile(Long uid,String contactMobile){
		
		if(null != uid){
			SellerBO info = sellerDao.findOne(uid);
			if(!contactMobile.equals(info.getContactMobile())){
				if(sellerDao.countByContactMobile(contactMobile)>0){
					return false;
				}
			}
		}else {
			if(sellerDao.countByContactMobile(contactMobile)>0){
				return false;
			}
		}
		
		return true;
	}
	

	public List<SellerBO> findAll() {
		return sellerDao.findAll();
	}

	/**
	 * 主键查询
	 * @param uid
	 * @return
	 */
	public SellerBO findOne(Long uid) {
		
		SellerBO info = sellerDao.findOne(uid);
		
		if(StringUtils.isEmpty(info.getSellerCover())){
			return info;
		}

		//处理商户的封面
		if(!StringUtils.isEmpty(info.getSellerCover())){
			
			JSONArray array = (JSONArray) JSON.parse(info.getSellerCover());
			switch (array.size()) {
			case 1:
				info.setSellerCover1(array.getString(0));
				break;
			case 2:
				info.setSellerCover1(array.getString(0));
				info.setSellerCover2(array.getString(1));
				break;
			case 3:
				info.setSellerCover1(array.getString(0));
				info.setSellerCover2(array.getString(1));
				info.setSellerCover3(array.getString(2));
				break;
			case 4:
				info.setSellerCover1(array.getString(0));
				info.setSellerCover2(array.getString(1));
				info.setSellerCover3(array.getString(2));
				info.setSellerCover4(array.getString(3));
				break;
			case 5:
				info.setSellerCover1(array.getString(0));
				info.setSellerCover2(array.getString(1));
				info.setSellerCover3(array.getString(2));
				info.setSellerCover4(array.getString(3));
				info.setSellerCover5(array.getString(4));
				break;
			default:
				break;
			}
		}
		return info;
	}

	public SellerBO findOnlyUidAndNameByUid(Long uid) {
		return sellerDao.findOnlyUidAndNameByUid(uid);
	}

	public Boolean checkEmail(Long uid, String email) {
		
		if(null != uid){
			return true;
		}
		
		SellerBO seller = sellerDao.findbyEmail(email);
		if(null != seller){
			return false;
		}
		
		UserAccountBO userAccount = userAccountDao.findByLoginName(email);
		if(null != userAccount){
			return false;
		}
		
		return true;
		
	}
	
}
