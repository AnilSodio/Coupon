package vc.thinker.cabbage.se.mapper;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.geo.Point;

import vc.thinker.cabbage.se.bo.CabinetBO;
import vc.thinker.cabbage.se.model.Cabinet;
import vc.thinker.cabbage.se.model.CabinetExample;
import vc.thinker.cabbage.se.vo.CabinetVO;
import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.vo.StatsVO;
import vc.thinker.cabbage.common.MyPage;

public interface CabinetMapper {
    int countByExample(CabinetExample example);

    int deleteByExample(CabinetExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Cabinet record);

    int insertSelective(Cabinet record);

    List<CabinetBO> selectByExample(CabinetExample example);

    CabinetBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Cabinet record, @Param("example") CabinetExample example);

    int updateByExample(@Param("record") Cabinet record, @Param("example") CabinetExample example);

    int updateByPrimaryKeySelective(Cabinet record);

    int updateByPrimaryKey(Cabinet record);

	List<CabinetBO> findPageByVo(@Param("page")MyPage<CabinetBO> page, @Param("vo")CabinetVO vo);

	List<CabinetBO> findByLocation(@Param("p")Point p, @Param("distance")int distance);

	CabinetBO findDetailsOne(Long id);

	List<CountStatsBO> statsNormalCabinet(@Param("vo")StatsVO vo);

	BigDecimal findTotalByTime(@Param("year")String year, @Param("month")String month);

	CabinetBO findByAliasOrSysCode(@Param("cabinetAlias")String cabinetAlias);

	List<CabinetBO> findCabinetsByType(@Param("typeId")long cabinetType);
}