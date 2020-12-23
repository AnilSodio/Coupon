package vc.thinker.cabbage.stats.mapper;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.stats.bo.DayStatsBO;
import vc.thinker.cabbage.stats.model.DayStats;
import vc.thinker.cabbage.stats.model.DayStatsExample;

public interface DayStatsMapper {
    int countByExample(DayStatsExample example);

    int deleteByExample(DayStatsExample example);

    int deleteByPrimaryKey(@Param("officeId") Long officeId, @Param("statsDate") Date statsDate);

    int insert(DayStats record);

    int insertSelective(DayStats record);

    List<DayStatsBO> selectByExample(DayStatsExample example);

    DayStatsBO selectByPrimaryKey(@Param("officeId") Long officeId, @Param("statsDate") Date statsDate);

    int updateByExampleSelective(@Param("record") DayStats record, @Param("example") DayStatsExample example);

    int updateByExample(@Param("record") DayStats record, @Param("example") DayStatsExample example);

    int updateByPrimaryKeySelective(DayStats record);

    int updateByPrimaryKey(DayStats record);

	void updateByMonth(String month);
}