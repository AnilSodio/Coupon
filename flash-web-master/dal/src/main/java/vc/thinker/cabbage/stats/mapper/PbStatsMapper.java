package vc.thinker.cabbage.stats.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.stats.bo.PbStatsBO;
import vc.thinker.cabbage.stats.model.PbStats;
import vc.thinker.cabbage.stats.model.PbStatsExample;

public interface PbStatsMapper {
    int countByExample(PbStatsExample example);

    int deleteByExample(PbStatsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PbStats record);

    int insertSelective(PbStats record);

    List<PbStatsBO> selectByExample(PbStatsExample example);

    PbStatsBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PbStats record, @Param("example") PbStatsExample example);

    int updateByExample(@Param("record") PbStats record, @Param("example") PbStatsExample example);

    int updateByPrimaryKeySelective(PbStats record);

    int updateByPrimaryKey(PbStats record);
}