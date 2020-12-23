package vc.thinker.cabbage.stats.mapper;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.bo.OrderStatsBO;
import vc.thinker.cabbage.stats.bo.RealTimeGroupStatsBO;
import vc.thinker.cabbage.stats.model.OrderStats;
import vc.thinker.cabbage.stats.model.OrderStatsExample;
import vc.thinker.cabbage.stats.vo.StatsVO;

public interface OrderStatsMapper {
    int countByExample(OrderStatsExample example);

    int deleteByExample(OrderStatsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderStats record);

    int insertSelective(OrderStats record);

    List<OrderStatsBO> selectByExample(OrderStatsExample example);

    OrderStatsBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderStats record, @Param("example") OrderStatsExample example);

    int updateByExample(@Param("record") OrderStats record, @Param("example") OrderStatsExample example);

    int updateByPrimaryKeySelective(OrderStats record);

    int updateByPrimaryKey(OrderStats record);

	OrderStatsBO countByDate(@Param("date")String date);

	OrderStatsBO countAvgByDate(@Param("beginDate")String beginDate, @Param("endDate")String endDate);

	List<RealTimeGroupStatsBO> findTripInfoByGroupType(@Param("groupType")String groupType, @Param("date")String date, @Param("type")String type);

	BigDecimal findTripInfoByTimeRange(@Param("day")String day, @Param("time1")String time1, @Param("time2")String time2, @Param("type")String type);

	BigDecimal findTripInfoByAgeRange(@Param("age1")Integer age1, @Param("age2")Integer age2, @Param("date")String date, @Param("type")String type);

	List<OrderStatsBO> groupByPayType();

	List<CountStatsBO> orderConsumeStats(@Param("vo")StatsVO vo);

	OrderStatsBO countByTime(@Param("year")String year, @Param("month")String month);

	List<OrderStatsBO> countByDateGoupByCurrency(@Param("year")String year, @Param("month")String month);

	void deleteByDate(@Param("beginDate")String beginDate, @Param("endDate")String endDate);
}