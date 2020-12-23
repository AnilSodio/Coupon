package vc.thinker.cabbage.user.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.bo.DepositPayLogBO;
import vc.thinker.cabbage.user.mapper.DepositPayLogMapper;
import vc.thinker.cabbage.user.model.DepositPayLog;
import vc.thinker.cabbage.user.model.DepositPayLogExample;
import vc.thinker.cabbage.user.vo.DepositPayLogVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class DepositPayLogDao {

	@Autowired
	private DepositPayLogMapper mapper;


	public List<DepositPayLogBO> findByCapture(Boolean capture,Integer status,String payMark,Date ltTime) {
		DepositPayLogExample example=new DepositPayLogExample();
		example.createCriteria().andPayTimeLessThan(ltTime).andPaymentMarkEqualTo(payMark)
		.andStatusEqualTo(status)
		.andIsCaptureEqualTo(capture);
		return mapper.selectByExample(example);
	}
	
	public DepositPayLogBO findLastOneByUidAndStutus(Long uid,int status) {
		return mapper.findLastOneByUidAndStutus(uid,status);
	}

   /** generate code begin**/
	public List<DepositPayLogBO> findAll(){
		DepositPayLogExample example=new DepositPayLogExample();
		return mapper.selectByExample(example);
	}
	List<DepositPayLogBO> findAll(Iterable<java.lang.Long> ids){
		DepositPayLogExample example=new DepositPayLogExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		DepositPayLogExample example=new DepositPayLogExample();
		return mapper.countByExample(example);
	}

	public List<DepositPayLog> save(Iterable<DepositPayLog> entities){
		List<DepositPayLog> list=new ArrayList<DepositPayLog>();
		for (DepositPayLog DepositPayLog : entities) {
			list.add(save(DepositPayLog));
		}
		return list;
	}
	
	public DepositPayLog save(DepositPayLog record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(DepositPayLog record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public DepositPayLogBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		DepositPayLogExample example=new DepositPayLogExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(DepositPayLog entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<DepositPayLog> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (DepositPayLog  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		DepositPayLogExample example=new DepositPayLogExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		DepositPayLogExample example=new DepositPayLogExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public DepositPayLogBO findByPayOrderCode(String payOrderCode) {
		DepositPayLogExample example=new DepositPayLogExample();
		example.createCriteria().andPayOrderCodeEqualTo(payOrderCode);
		List<DepositPayLogBO> list = mapper.selectByExample(example);
		return list.isEmpty()?null:list.get(0);
	}

	public DepositPayLogBO findByOutOrdreId(String outRefundNo) {
		DepositPayLogExample example=new DepositPayLogExample();
		example.createCriteria().andOutOrderIdEqualTo(outRefundNo);
		List<DepositPayLogBO> list = mapper.selectByExample(example);
		return list.isEmpty()?null:list.get(0);
	}

	public List<DepositPayLogBO> findPageByVo(MyPage<DepositPayLogBO> page, DepositPayLogVO vo) {
		return mapper.findPageByVo(page,vo);
	}

	public List<DepositPayLogBO> findPaySuccAndNoRefundByUid(Long uid) {
		DepositPayLogExample example=new DepositPayLogExample();
		example.createCriteria().andStatusEqualTo(CommonConstants.DEPOSIT_PAY_STATUS_2)
		.andUidEqualTo(uid);
		return mapper.selectByExample(example);
	}

	public DepositPayLogBO findLastOneNoSuccByUid(Long uid, Integer status,String paymentMark) {
		DepositPayLogExample example=new DepositPayLogExample();
		example.createCriteria().andUidEqualTo(uid).andStatusEqualTo(status).andPaymentMarkEqualTo(paymentMark);
		example.setOrderByClause("create_time desc");
		List<DepositPayLogBO> result_list  = mapper.selectByExample(example);
		return null != result_list && result_list.size()>0 ? result_list.get(0):null;
	}

	public List<DepositPayLogBO> findLogListByVo(MyPage<DepositPayLogBO> page, DepositPayLogVO vo) {
		return mapper.findLogListByVo(page,vo);
	}

	public DepositPayLogBO getLastPaySuccessByUid(Long uid) {
		DepositPayLogExample example=new DepositPayLogExample();
		example.createCriteria()
		.andUidEqualTo(uid)
		.andStatusEqualTo(CommonConstants.DEPOSIT_LOG_STATUS_2);
		example.setOrderByClause("create_time desc ");
		example.setLimit(1);
		List<DepositPayLogBO> list = mapper.selectByExample(example);
		return list.isEmpty()?null:list.get(0);
	}

	public BigDecimal sumByData(String date) {
		return mapper.sumByData(date);
	}
}
