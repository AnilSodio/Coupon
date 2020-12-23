package vc.thinker.cabbage.sys.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import vc.thinker.cabbage.sys.SysMessageConstants;
import vc.thinker.cabbage.sys.bo.ImageTextBO;
import vc.thinker.cabbage.sys.mapper.ImageTextMapper;
import vc.thinker.cabbage.sys.model.ImageText;
import vc.thinker.cabbage.sys.model.ImageTextExample;
import vc.thinker.cabbage.sys.vo.ImageTextVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class ImageTextDao {

	@Autowired
	private ImageTextMapper mapper;
	
	/**
	 * 查找最新的首页广告消息
	 * @param timestamp
	 * @return
	 */
	public ImageTextBO findNewHomeMessage(Date timestamp){
		ImageTextExample example=new ImageTextExample();
		ImageTextExample.Criteria c=example.createCriteria().andImageTypeEqualTo(SysMessageConstants.IMAGE_TEXT_TYPE_2)
				.andStatusEqualTo(SysMessageConstants.IMATE_TEXT_STATUS_2);
		if(timestamp != null){
			c.andStartDateGreaterThan(timestamp);
		}
		List<ImageTextBO> list=mapper.selectByExample(example);
		return list.isEmpty() ? null:list.get(0);
	}


   /** generate code begin**/
	public List<ImageTextBO> findAll(){
		ImageTextExample example=new ImageTextExample();
		return mapper.selectByExample(example);
	}
	List<ImageTextBO> findAll(Iterable<java.lang.Long> ids){
		ImageTextExample example=new ImageTextExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		ImageTextExample example=new ImageTextExample();
		return mapper.countByExample(example);
	}

	public List<ImageText> save(Iterable<ImageText> entities){
		List<ImageText> list=new ArrayList<ImageText>();
		for (ImageText ImageText : entities) {
			list.add(save(ImageText));
		}
		return list;
	}
	
	public ImageText save(ImageText record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(ImageText record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public ImageTextBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		ImageTextExample example=new ImageTextExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(ImageText entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<ImageText> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (ImageText  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		ImageTextExample example=new ImageTextExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		ImageTextExample example=new ImageTextExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<ImageTextBO> findPageByVo(MyPage<ImageTextBO> page, ImageTextVO vo) {
		return mapper.findPageByVo(page,vo);
	}
	
	public List<ImageTextBO> findbyStatusAndType(Integer status, Integer type) {
		ImageTextExample example = new ImageTextExample();
		example.createCriteria().andImageTypeEqualTo(type).andStatusEqualTo(status);
		return mapper.selectByExample(example);
	}


	public List<ImageTextBO> selectByExample(ImageTextExample example) {
		return mapper.selectByExample(example);
	}
}
