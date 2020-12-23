package vc.thinker.cabbage.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.sys.bo.ShareSetBO;
import vc.thinker.cabbage.sys.model.ShareSet;
import vc.thinker.cabbage.sys.model.ShareSetExample;
import vc.thinker.cabbage.sys.vo.ShareSetVO;
import vc.thinker.cabbage.common.MyPage;

public interface ShareSetMapper {
    int countByExample(ShareSetExample example);

    int deleteByExample(ShareSetExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShareSet record);

    int insertSelective(ShareSet record);

    List<ShareSetBO> selectByExample(ShareSetExample example);

    ShareSetBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShareSet record, @Param("example") ShareSetExample example);

    int updateByExample(@Param("record") ShareSet record, @Param("example") ShareSetExample example);

    int updateByPrimaryKeySelective(ShareSet record);

    int updateByPrimaryKey(ShareSet record);

	List<ShareSetBO> findPageByVo(@Param("page")MyPage<ShareSetBO> page, @Param("vo")ShareSetVO vo);

	void updateNoAllowInvite(Long id);

	void updateNoALlowShare(Long id);
}