package vc.thinker.cabbage.stats.mapper;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.bo.RealTimeGroupStatsBO;
import vc.thinker.cabbage.stats.bo.RegisterStatsBO;
import vc.thinker.cabbage.stats.model.RegisterStats;
import vc.thinker.cabbage.stats.model.RegisterStatsExample;
import vc.thinker.cabbage.stats.vo.StatsVO;

public interface RegisterStatsMapper {
    int countByExample(RegisterStatsExample example);

    int deleteByExample(RegisterStatsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RegisterStats record);

    int insertSelective(RegisterStats record);

    List<RegisterStatsBO> selectByExample(RegisterStatsExample example);

    RegisterStatsBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RegisterStats record, @Param("example") RegisterStatsExample example);

    int updateByExample(@Param("record") RegisterStats record, @Param("example") RegisterStatsExample example);

    int updateByPrimaryKeySelective(RegisterStats record);

    int updateByPrimaryKey(RegisterStats record);

    BigDecimal countByDate(@Param("date")String date);

	BigDecimal countAvgByDate(@Param("beginDate")String beginDate, @Param("endDate")String endDate);

	List<RealTimeGroupStatsBO> findRegistInfoByGroupType(@Param("groupType")String groupType,@Param("date")String date);

	BigDecimal findRegistByTimeRange(@Param("day")String day,@Param("time1")String time1, @Param("time2")String time2);

	BigDecimal findTotalByTime(@Param("year")String year, @Param("month")String month);

	int saveOrUpdate(RegisterStats registerStats);

    List<RegisterStatsBO> getAllActiveUser();

	BigDecimal findRegistByAgeRange(@Param("age1")Integer age1, @Param("age2")Integer age2, @Param("date")String date);

	void deleteByDate(@Param("beginDate")String beginDate, @Param("endDate")String endDate);

	List<RegisterStatsBO> statsBySex();

	List<RegisterStatsBO> countUserByAge();

	List<CountStatsBO> findUserStats(@Param("vo")StatsVO vo);

	void deleteByVo(@Param("vo")StatsVO vo);
}