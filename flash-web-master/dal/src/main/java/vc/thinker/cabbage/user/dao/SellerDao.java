package vc.thinker.cabbage.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import vc.thinker.cabbage.user.bo.SellerBO;
import vc.thinker.cabbage.user.mapper.SellerMapper;
import vc.thinker.cabbage.user.model.Seller;
import vc.thinker.cabbage.user.model.SellerExample;
import vc.thinker.cabbage.user.vo.SellerVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class SellerDao {

	@Autowired
	private SellerMapper mapper;
	
	/**
	 * 邀请码查找
	 * @param inviteCode
	 * @return
	 */
	public SellerBO findByInviteCode(String inviteCode){
		SellerExample example=new SellerExample();
		example.createCriteria().andInviteCodeEqualTo(inviteCode);
		
		List<SellerBO> list= mapper.selectByExample(example);
		return list.isEmpty() ? null:list.get(0); 
	}
	
	public List<SellerBO> findNormalByLocation(Point p,Integer distance){
		return mapper.findNormalByLocation(p, distance);
	}

   /** generate code begin**/
	public List<SellerBO> findAll(){
		SellerExample example=new SellerExample();
		return mapper.selectByExample(example);
	}
	List<SellerBO> findAll(Iterable<java.lang.Long> ids){
		SellerExample example=new SellerExample();
		example.createCriteria().andUidIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		SellerExample example=new SellerExample();
		return mapper.countByExample(example);
	}

	public List<Seller> save(Iterable<Seller> entities){
		List<Seller> list=new ArrayList<Seller>();
		for (Seller Seller : entities) {
			list.add(save(Seller));
		}
		return list;
	}
	
	public Seller save(Seller record){
		if(!exists(record.getUid())){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(Seller record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public SellerBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		SellerExample example=new SellerExample();
		example.createCriteria().andUidEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(Seller entity){
		 mapper.deleteByPrimaryKey(entity.getUid());
	}

	public void delete(Iterable<Seller> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (Seller  entity: entities) {
			ids.add(entity.getUid());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		SellerExample example=new SellerExample();
		example.createCriteria().andUidIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		SellerExample example=new SellerExample();
		mapper.deleteByExample(example);
	}
	
	public List<SellerBO> findPageByVo(MyPage<SellerBO> page, SellerVO vo) {
		return mapper.findPageByVo(page,vo);
	}
	public int countBySellerName(String sellerName) {
		SellerExample example=new SellerExample();
		example.createCriteria().andSellerNameEqualTo(sellerName);
		return mapper.countByExample(example);
	}
	
	public int countByContactMobile(String contactMobile){
		SellerExample example=new SellerExample();
		example.createCriteria().andContactMobileEqualTo(contactMobile);
		return mapper.countByExample(example);
	}

	public SellerBO findOnlyUidAndNameByUid(Long uid) {
		return mapper.findOnlyUidAndNameByUid(uid);
	}

	public SellerBO findbyEmail(String email) {
		SellerExample example=new SellerExample();
		example.createCriteria().andEmailEqualTo(email);
		List<SellerBO> selectByExample = mapper.selectByExample(example);
		return null != selectByExample && selectByExample.size()>0?selectByExample.get(0):null;
	}
}
