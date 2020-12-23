package vc.thinker.cabbage.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.sys.bo.SysMessageLogBO;
import vc.thinker.cabbage.sys.model.SysMessageLog;
import vc.thinker.cabbage.sys.model.SysMessageLogExample;
import vc.thinker.cabbage.sys.vo.SysMessageLogVO;
import vc.thinker.cabbage.common.MyPage;

public interface SysMessageLogMapper {
    int countByExample(SysMessageLogExample example);

    int deleteByExample(SysMessageLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysMessageLog record);

    int insertSelective(SysMessageLog record);

    List<SysMessageLogBO> selectByExample(SysMessageLogExample example);

    SysMessageLogBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysMessageLog record, @Param("example") SysMessageLogExample example);

    int updateByExample(@Param("record") SysMessageLog record, @Param("example") SysMessageLogExample example);

    int updateByPrimaryKeySelective(SysMessageLog record);

    int updateByPrimaryKey(SysMessageLog record);

	List<SysMessageLogBO> findPageByVo(@Param("page")MyPage<SysMessageLogBO> page, @Param("vo")SysMessageLogVO vo);
}