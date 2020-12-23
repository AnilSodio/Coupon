package vc.thinker.cabbage.stats.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.bo.IncomeStatsBO;
import vc.thinker.cabbage.stats.model.IncomeStats;
import vc.thinker.cabbage.stats.model.IncomeStatsExample;
import vc.thinker.cabbage.stats.vo.StatsVO;

public interface IncomeStatsMapper {
    int countByExample(IncomeStatsExample example);

    int deleteByExample(IncomeStatsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(IncomeStats record);

    int insertSelective(IncomeStats record);

    List<IncomeStatsBO> selectByExample(IncomeStatsExample example);

    IncomeStatsBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") IncomeStats record, @Param("example") IncomeStatsExample example);

    int updateByExample(@Param("record") IncomeStats record, @Param("example") IncomeStatsExample example);

    int updateByPrimaryKeySelective(IncomeStats record);

    int updateByPrimaryKey(IncomeStats record);

	List<CountStatsBO> findDailyIncome(@Param("vo")StatsVO vo);
}