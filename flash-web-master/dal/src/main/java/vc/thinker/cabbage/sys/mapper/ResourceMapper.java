package vc.thinker.cabbage.sys.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.x.XMapper;
import vc.thinker.cabbage.sys.bo.ResourceBO;
import vc.thinker.cabbage.sys.model.Resource;
import vc.thinker.cabbage.sys.model.ResourceExample;
import vc.thinker.cabbage.sys.vo.ResourceVO;

public interface ResourceMapper extends XMapper<ResourceBO,ResourceVO> {
    int countByExample(ResourceExample example);

    int deleteByExample(ResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Resource record);

    int insertSelective(Resource record);

    List<ResourceBO> selectByExample(ResourceExample example);

    ResourceBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByExample(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

	List<ResourceBO> groupByModule(@Param("platType")String platType,
			@Param("language")String language);

	List<ResourceBO> groupBySecondMenu(@Param("platType")String platType, 
			@Param("language")String language, @Param("moudle")String moudle);
}