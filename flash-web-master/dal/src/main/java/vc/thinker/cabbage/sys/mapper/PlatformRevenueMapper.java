package vc.thinker.cabbage.sys.mapper;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.vo.StatsVO;
import vc.thinker.cabbage.sys.bo.PlatformRevenueBO;
import vc.thinker.cabbage.sys.model.PlatformRevenue;
import vc.thinker.cabbage.sys.model.PlatformRevenueExample;
import vc.thinker.cabbage.sys.vo.PlatformRevenueVO;
import vc.thinker.cabbage.common.MyPage;

public interface PlatformRevenueMapper {
    int countByExample(PlatformRevenueExample example);

    int deleteByExample(PlatformRevenueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PlatformRevenue record);

    int insertSelective(PlatformRevenue record);

    List<PlatformRevenueBO> selectByExampleWithBLOBs(PlatformRevenueExample example);

    List<PlatformRevenueBO> selectByExample(PlatformRevenueExample example);

    PlatformRevenueBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PlatformRevenue record, @Param("example") PlatformRevenueExample example);

    int updateByExampleWithBLOBs(@Param("record") PlatformRevenue record, @Param("example") PlatformRevenueExample example);

    int updateByExample(@Param("record") PlatformRevenue record, @Param("example") PlatformRevenueExample example);

    int updateByPrimaryKeySelective(PlatformRevenue record);

    int updateByPrimaryKeyWithBLOBs(PlatformRevenue record);

    int updateByPrimaryKey(PlatformRevenue record);

	List<PlatformRevenueBO> findPageByVo(@Param("page")MyPage<PlatformRevenueBO> page, @Param("vo")PlatformRevenueVO vo);

	BigDecimal sumByDate(@Param("date")String date);

	List<CountStatsBO> findIncomeByStats(@Param("vo")StatsVO vo);
}