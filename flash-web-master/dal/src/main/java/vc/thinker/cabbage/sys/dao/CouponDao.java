package vc.thinker.cabbage.sys.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import vc.thinker.cabbage.sys.bo.CouponBO;
import vc.thinker.cabbage.sys.mapper.CouponMapper;
import vc.thinker.cabbage.sys.model.Coupon;
import vc.thinker.cabbage.sys.model.CouponExample;
import vc.thinker.cabbage.user.bo.UserCouponBO;
import vc.thinker.cabbage.user.mapper.UserCouponMapper;
import vc.thinker.cabbage.user.vo.UserCouponVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class CouponDao {

	@Autowired
	private CouponMapper mapper;
	@Autowired
	private UserCouponMapper ucMapper;
	
	public List<CouponBO> findCouponPage(MyPage<CouponBO> page) {
		CouponExample example=new CouponExample();
		example.setPage(page);
		example.createCriteria().andIsDeletedEqualTo(false);
		example.setOrderByClause("create_time desc");
		return mapper.selectByExample(example);
	}
   /** generate code begin**/
	public List<CouponBO> findAll(){
		CouponExample example=new CouponExample();
		example.createCriteria().andIsDeletedEqualTo(false);
		return mapper.selectByExample(example);
	}
	List<CouponBO> findAll(Iterable<java.lang.Long> ids){
		CouponExample example=new CouponExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		CouponExample example=new CouponExample();
		return mapper.countByExample(example);
	}

	public List<Coupon> save(Iterable<Coupon> entities){
		List<Coupon> list=new ArrayList<Coupon>();
		for (Coupon Coupon : entities) {
			list.add(save(Coupon));
		}
		return list;
	}
	
	public Coupon save(Coupon record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(Coupon record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public CouponBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		CouponExample example=new CouponExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(Coupon entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<Coupon> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (Coupon  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		CouponExample example=new CouponExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		CouponExample example=new CouponExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<UserCouponBO> findCouponList(MyPage<UserCouponBO> page, UserCouponVO vo) {
		return ucMapper.findCouponList(page,vo);
	}
	public List<UserCouponBO> findCouponStatus() {
		return ucMapper.findCouponStatus();
	}
	public List<CouponBO> selectByExample(CouponExample example) {
		return mapper.selectByExample(example);
	}
	public List<CouponBO> findAllByCurrency(String currency) {
		CouponExample example=new CouponExample();
		example.createCriteria().andCurrencyEqualTo(currency);
		return mapper.selectByExample(example);
	}
	
}
