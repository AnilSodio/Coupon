package vc.thinker.cabbage.sys.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;

import vc.thinker.cabbage.sys.PromotionConstants;
import vc.thinker.cabbage.sys.bo.CouponBO;
import vc.thinker.cabbage.sys.bo.PromotionBO;
import vc.thinker.cabbage.sys.bo.PromotionTypeBO;
import vc.thinker.cabbage.sys.dao.CouponDao;
import vc.thinker.cabbage.sys.dao.PromotionDao;
import vc.thinker.cabbage.sys.dao.PromotionTypeDao;
import vc.thinker.cabbage.sys.model.CouponExample;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.bo.UserCouponBO;
import vc.thinker.cabbage.user.dao.MemberDao;
import vc.thinker.cabbage.user.dao.UserCouponDao;
import vc.thinker.cabbage.user.model.UserCoupon;
import vc.thinker.cabbage.user.vo.UserCouponVO;
import vc.thinker.cabbage.util.DateTimeUtils;
import vc.thinker.cabbage.common.MyPage;

@Service
@Transactional(readOnly = false)
public class PromotionService {

	private Logger logger = LoggerFactory.getLogger(PromotionService.class);
	
	@Autowired
	private CouponDao couponDao;
	@Autowired
	private UserCouponDao userCouponDao;
	@Autowired
	private PromotionDao promotionDao;
	@Autowired
	private PromotionTypeDao promotionTypeDao;
	@Autowired
	private MemberDao memberDao;

	public List<PromotionTypeBO> findProType() {
		return promotionTypeDao.findAll();
	}

	public List<CouponBO> findAllCoupon() {
		return couponDao.findAll();
	}

	public List<PromotionBO> findPromotionPage(MyPage<PromotionBO> page) {
		return promotionDao.findPromotionPage(page);
	}

	public PromotionBO finPromotionById(Long pid) {
		return promotionDao.findOne(pid);
	}

	public void savePromotion(PromotionBO bo) {
		if (null != bo.getAreaId() && !"".equals(bo.getAreaId().trim())) {
			bo.setAllCountry(PromotionConstants.ALL_COUNTRY_FALSE);
		} else {
			bo.setAllCountry(PromotionConstants.ALL_COUNTRY_TRUE);
		}
		promotionDao.save(bo);
	}

	public List<CouponBO> findCouponPage(MyPage<CouponBO> page) {
		return couponDao.findCouponPage(page);
	}

	public CouponBO finCouponById(Long cid) {
		return couponDao.findOne(cid);
	}

	/**
	 * 保存优惠券
	 * @param coupon 优惠券
	 */
	public void saveCoupon(CouponBO coupon) {
		
		if(null == coupon.getId()){
			coupon.setCreateTime(new Date());
			coupon.setIsDeleted(false);
		}
		couponDao.save(coupon);
	}

	public void sendCoupon(Long cid, String[] userIds,int num) {
		CouponBO c = couponDao.findOne(cid);

		List<UserCoupon> listUserCoupon = new ArrayList<>();

		for (String uid : userIds) {
			if (!StringUtils.isEmpty(uid)) {
				List<MemberBO> memberList = memberDao.findById(Long.parseLong(uid));
				for (MemberBO mb : memberList) {
//					msgTools.sendTemplateSms("sms_coupon", mb.getMobile(), "127.0.0.1", "{amount:\""+c.getAmount()+"\",day:\""+c.getDayLimit()+"\"}", CommonConstants.SMS_SING_NAME_SERVER);
					break;
				}
				
				for(int i=0;i<num;i++){
					UserCoupon uc = new UserCoupon();
					uc.setCouponId(cid);
					uc.setExpireDate(DateTimeUtils.getDateAfter(DateTimeUtils.get59SecondDate(), c.getDayLimit()));
					uc.setUid(Long.parseLong(uid));
					uc.setAmount(c.getAmount());
					uc.setSource("System release");
					uc.setCurrency(c.getCurrency());
					listUserCoupon.add(uc);
				}
			}
		}

		userCouponDao.save(listUserCoupon);
	}

	public CouponBO findOne(Long id) {
		return couponDao.findOne(id);
	}

	public List<UserCouponBO> findCouponList(MyPage<UserCouponBO> page, UserCouponVO vo) {
		try{
			if(!StringUtils.isEmpty(vo.getStartTimeString())){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				vo.setStartTime(sdf.parse(vo.getStartTimeString()));
			}
			if(!StringUtils.isEmpty(vo.getEndTimeString())){
				vo.setEndTime(DateTimeUtils.getAppoint59SecondDate(vo.getEndTimeString()));
			}
		}catch (ParseException e) {
			logger.info("已发放优惠券查询 日期转换异常");
		}
		return couponDao.findCouponList(page, vo);
	}

	public List<UserCouponBO> findCouponStatus() {
		return couponDao.findCouponStatus();
	}

	/**
	 * 校验优惠券名称是否已经存在
	 * @param id 
	 * @param name
	 * @return
	 */
	public Boolean checkName(Long id, String name) {
		if(null != id){
			//修改
			CouponBO coupon = couponDao.findOne(id);
			if(name.equals(coupon.getName())){
				return true;
			}else {
				CouponExample example = new CouponExample();
				example.createCriteria().andNameEqualTo(name);
				List<CouponBO> c_list = couponDao.selectByExample(example);
				if(null != c_list && c_list.size() > 0){
					return false;
				}else {
					return true;
				}
			}
		}else {
			//新增
			CouponExample example = new CouponExample();
			example.createCriteria().andNameEqualTo(name);
			List<CouponBO> c_list = couponDao.selectByExample(example);
			if(null != c_list && c_list.size() > 0){
				return false;
			}else {
				return true;
			}
		}
	}

	/**
	 * 
	 * @param typeCode
	 * @param areaId
	 * @return
	 */
	public List<PromotionBO> findHaveInHand(String typeCode,String areaId) {
		
		return promotionDao.findHaveInHand(typeCode, areaId);
	}

	public void delete(Long pid) {
		promotionDao.delete(pid);
	}
	
	public void deleteCoupe(Long cid){
		couponDao.delete(cid);
	}

	public List<CouponBO> findAllByCurrency(String currency) {
		return couponDao.findAllByCurrency(currency);
	}

}
