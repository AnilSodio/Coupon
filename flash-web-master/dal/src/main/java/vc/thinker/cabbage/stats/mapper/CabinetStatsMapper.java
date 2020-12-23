package vc.thinker.cabbage.stats.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.stats.bo.CabinetStatsBO;
import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.model.CabinetStats;
import vc.thinker.cabbage.stats.model.CabinetStatsExample;
import vc.thinker.cabbage.stats.vo.StatsVO;

public interface CabinetStatsMapper {
    int countByExample(CabinetStatsExample example);

    int deleteByExample(CabinetStatsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CabinetStats record);

    int insertSelective(CabinetStats record);

    List<CabinetStatsBO> selectByExample(CabinetStatsExample example);

    CabinetStatsBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CabinetStats record, @Param("example") CabinetStatsExample example);

    int updateByExample(@Param("record") CabinetStats record, @Param("example") CabinetStatsExample example);

    int updateByPrimaryKeySelective(CabinetStats record);

    int updateByPrimaryKey(CabinetStats record);

	List<CountStatsBO> statsTotalCabinet(@Param("vo")StatsVO vo);
}