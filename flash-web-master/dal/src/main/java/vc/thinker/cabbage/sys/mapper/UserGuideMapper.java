package vc.thinker.cabbage.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.sys.bo.UserGuideBO;
import vc.thinker.cabbage.sys.model.UserGuide;
import vc.thinker.cabbage.sys.model.UserGuideExample;
import vc.thinker.cabbage.sys.vo.UserGuideVO;
import vc.thinker.cabbage.common.MyPage;

public interface UserGuideMapper {
    int countByExample(UserGuideExample example);

    int deleteByExample(UserGuideExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserGuide record);

    int insertSelective(UserGuide record);

    List<UserGuideBO> selectByExample(UserGuideExample example);

    UserGuideBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserGuide record, @Param("example") UserGuideExample example);

    int updateByExample(@Param("record") UserGuide record, @Param("example") UserGuideExample example);

    int updateByPrimaryKeySelective(UserGuide record);

    int updateByPrimaryKey(UserGuide record);

	List<UserGuideBO> finePageByVo(@Param("page")MyPage<UserGuideBO> page, @Param("vo")UserGuideVO vo);
}