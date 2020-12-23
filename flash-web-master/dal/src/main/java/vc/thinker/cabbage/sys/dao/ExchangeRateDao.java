package vc.thinker.cabbage.sys.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import vc.thinker.cabbage.sys.bo.ExchangeRateBO;
import vc.thinker.cabbage.sys.mapper.ExchangeRateMapper;
import vc.thinker.cabbage.sys.model.ExchangeRate;
import vc.thinker.cabbage.sys.model.ExchangeRateExample;
import vc.thinker.cabbage.sys.vo.ExchangeRateVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class ExchangeRateDao {

	@Autowired
	private ExchangeRateMapper mapper;


   /** generate code begin**/
	public List<ExchangeRateBO> findAll(){
		ExchangeRateExample example=new ExchangeRateExample();
		return mapper.selectByExample(example);
	}
	List<ExchangeRateBO> findAll(Iterable<java.lang.Long> ids){
		ExchangeRateExample example=new ExchangeRateExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		ExchangeRateExample example=new ExchangeRateExample();
		return mapper.countByExample(example);
	}

	public List<ExchangeRate> save(Iterable<ExchangeRate> entities){
		List<ExchangeRate> list=new ArrayList<ExchangeRate>();
		for (ExchangeRate ExchangeRate : entities) {
			list.add(save(ExchangeRate));
		}
		return list;
	}
	
	public ExchangeRate save(ExchangeRate record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(ExchangeRate record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public ExchangeRateBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		ExchangeRateExample example=new ExchangeRateExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(ExchangeRate entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<ExchangeRate> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (ExchangeRate  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		ExchangeRateExample example=new ExchangeRateExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		ExchangeRateExample example=new ExchangeRateExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<ExchangeRateBO> findPageByVo(MyPage<ExchangeRateBO> page, ExchangeRateVO vo) {
		return mapper.findPageByVo(page,vo);
	}
	public ExchangeRateBO findOneByCurrency(String fromCurrency, String toCurrency) {
		ExchangeRateExample example=new ExchangeRateExample();
		example.createCriteria().andCurrencyFromEqualTo(fromCurrency).andCurrencyToEqualTo(toCurrency);
		List<ExchangeRateBO> list = mapper.selectByExample(example);
		return list.isEmpty() ? null : list.get(0);
	}
}
