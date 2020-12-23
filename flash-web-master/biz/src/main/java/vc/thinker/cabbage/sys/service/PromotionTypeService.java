package vc.thinker.cabbage.sys.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.cabbage.sys.bo.PromotionTypeBO;
import vc.thinker.cabbage.sys.dao.PromotionTypeDao;
import vc.thinker.cabbage.sys.mapper.PromotionTypeMapper;
import vc.thinker.cabbage.sys.model.PromotionTypeExample;

@Service
@Transactional
public class PromotionTypeService {

	@Autowired
	private PromotionTypeMapper promotionTypeMapper;
	
	@Autowired
	private PromotionTypeDao promotionTypeDao;

	public List<PromotionTypeBO> findAll() {
		PromotionTypeExample example=new PromotionTypeExample();
		example.setOrderByClause("create_time desc");
		return promotionTypeMapper.selectByExample(example);
		
	}

	/**
	 * 主键查询
	 * @param id 主键
	 * @return
	 */
	public PromotionTypeBO findOne(Long id) {
		return promotionTypeMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存或者修改
	 * @param bo
	 */
	public void saveOrUpdate(PromotionTypeBO bo) {
		bo.setCreateTime(new Date());
		promotionTypeDao.save(bo);
	}

	public void delete(Long id) {
		promotionTypeDao.delete(id);
	}
}
