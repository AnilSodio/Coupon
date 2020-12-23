package vc.thinker.cabbage.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.sys.bo.ImageTextBO;
import vc.thinker.cabbage.sys.model.ImageText;
import vc.thinker.cabbage.sys.model.ImageTextExample;
import vc.thinker.cabbage.sys.vo.ImageTextVO;
import vc.thinker.cabbage.common.MyPage;

public interface ImageTextMapper {
	
    int countByExample(ImageTextExample example);

    int deleteByExample(ImageTextExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ImageText record);

    int insertSelective(ImageText record);

    List<ImageTextBO> selectByExampleWithBLOBs(ImageTextExample example);

    List<ImageTextBO> selectByExample(ImageTextExample example);

    ImageTextBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ImageText record, @Param("example") ImageTextExample example);

    int updateByExampleWithBLOBs(@Param("record") ImageText record, @Param("example") ImageTextExample example);

    int updateByExample(@Param("record") ImageText record, @Param("example") ImageTextExample example);

    int updateByPrimaryKeySelective(ImageText record);

    int updateByPrimaryKeyWithBLOBs(ImageText record);

    int updateByPrimaryKey(ImageText record);

	List<ImageTextBO> findPageByVo(@Param("page")MyPage<ImageTextBO> page, @Param("vo")ImageTextVO vo);
}