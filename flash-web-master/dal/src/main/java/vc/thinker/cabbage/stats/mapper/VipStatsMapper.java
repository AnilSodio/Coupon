package vc.thinker.cabbage.stats.mapper;

import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.stats.bo.RealTimeGroupStatsBO;
import vc.thinker.cabbage.stats.bo.VipStatsBO;
import vc.thinker.cabbage.stats.model.VipStats;
import vc.thinker.cabbage.stats.model.VipStatsExample;

import java.math.BigDecimal;
import java.util.List;

public interface VipStatsMapper {
    int countByExample(VipStatsExample example);

    int deleteByExample(VipStatsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VipStats record);

    int insertSelective(VipStats record);

    List<VipStatsBO> selectByExample(VipStatsExample example);

    VipStatsBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VipStats record, @Param("example") VipStatsExample example);

    int updateByExample(@Param("record") VipStats record, @Param("example") VipStatsExample example);

    int updateByPrimaryKeySelective(VipStats record);

    int updateByPrimaryKey(VipStats record);

    BigDecimal sumByDate(@Param("date") String date);

    BigDecimal sumAvgByDate(@Param("beginDate") String beginDate, @Param("endDate") String endDate);

    BigDecimal findVipPayByTimeRange(@Param("day") String day,@Param("time1") String time1, @Param("time2") String time2);

    BigDecimal findVipPayByAgeRange(@Param("age1") Integer age1, @Param("age2") Integer age2, @Param("date") String date);

    List<RealTimeGroupStatsBO> findVipPayByGroupType(@Param("groupType") String groupType, @Param("date") String date);

    BigDecimal sumByTime(@Param("year") String year, @Param("month") String month);

	void deleteByDate(@Param("beginDate")String beginDate, @Param("endDate")String endDate);

	Double getTotalVipRecharge();

	Integer totalVips();

	List<VipStatsBO> sumByDateGouupByCurrency(@Param("date")String date);

	List<VipStatsBO> sumAvgByDateGroupByCurrency(@Param("beginDate")String beginDate, @Param("endDate")String endDate);

	List<VipStatsBO> sumByTimeGroupByCurrency(@Param("year") String year, @Param("month") String month);
}