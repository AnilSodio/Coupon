package vc.thinker.cabbage.stats.mapper;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.stats.bo.BalanceStatsBO;
import vc.thinker.cabbage.stats.bo.RealTimeGroupStatsBO;
import vc.thinker.cabbage.stats.model.BalanceStats;
import vc.thinker.cabbage.stats.model.BalanceStatsExample;

public interface BalanceStatsMapper {
    int countByExample(BalanceStatsExample example);

    int deleteByExample(BalanceStatsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BalanceStats record);

    int insertSelective(BalanceStats record);

    List<BalanceStatsBO> selectByExample(BalanceStatsExample example);

    BalanceStatsBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BalanceStats record, @Param("example") BalanceStatsExample example);

    int updateByExample(@Param("record") BalanceStats record, @Param("example") BalanceStatsExample example);

    int updateByPrimaryKeySelective(BalanceStats record);

    int updateByPrimaryKey(BalanceStats record);

	BigDecimal sumByDate(@Param("date")String date);

	BigDecimal sumAvgByDate(@Param("beginDate")String beginDate, @Param("endDate")String endDate);

	List<RealTimeGroupStatsBO> findBalanceByGoupType(@Param("groupType")String groupType, @Param("date")String date);

	int countIsBuyNum();

	List<BalanceStatsBO> sumByDateGouupByCurrency(@Param("date")String date);

	List<BalanceStatsBO> sumAvgByDateGroupByCurrency(@Param("beginDate")String beginDate, @Param("endDate")String endDate);
}