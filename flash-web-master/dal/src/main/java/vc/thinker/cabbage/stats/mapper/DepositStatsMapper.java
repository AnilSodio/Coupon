package vc.thinker.cabbage.stats.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.bo.DepositStatsBO;
import vc.thinker.cabbage.stats.model.DepositStats;
import vc.thinker.cabbage.stats.model.DepositStatsExample;
import vc.thinker.cabbage.stats.vo.StatsVO;

public interface DepositStatsMapper {
    int countByExample(DepositStatsExample example);

    int deleteByExample(DepositStatsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DepositStats record);

    int insertSelective(DepositStats record);

    List<DepositStatsBO> selectByExample(DepositStatsExample example);

    DepositStatsBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DepositStats record, @Param("example") DepositStatsExample example);

    int updateByExample(@Param("record") DepositStats record, @Param("example") DepositStatsExample example);

    int updateByPrimaryKeySelective(DepositStats record);

    int updateByPrimaryKey(DepositStats record);

    List<DepositStatsBO> sumByDateGouupByCurrency(@Param("date")String date);
    
	BigDecimal sumByDate(@Param("date")String date);

	BigDecimal sumAvgByDate(@Param("beginDate")String beginDate, @Param("endDate")String endDate);

	List<CountStatsBO> statisticsCurrentDeposit(@Param("vo")StatsVO vo);

	List<DepositStatsBO> sumAvgByDateGroupByCurrency(@Param("beginDate")String beginDate, @Param("endDate")String endDate);
}