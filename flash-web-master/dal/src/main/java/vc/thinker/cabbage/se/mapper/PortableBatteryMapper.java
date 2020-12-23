package vc.thinker.cabbage.se.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.se.bo.PortableBatteryBO;
import vc.thinker.cabbage.se.model.PortableBattery;
import vc.thinker.cabbage.se.model.PortableBatteryExample;
import vc.thinker.cabbage.se.vo.PortableBatteryVO;
import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.vo.StatsVO;
import vc.thinker.cabbage.common.MyPage;

public interface PortableBatteryMapper {
    int countByExample(PortableBatteryExample example);

    int deleteByExample(PortableBatteryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PortableBattery record);

    int insertSelective(PortableBattery record);

    List<PortableBatteryBO> selectByExample(PortableBatteryExample example);

    PortableBatteryBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PortableBattery record, @Param("example") PortableBatteryExample example);

    int updateByExample(@Param("record") PortableBattery record, @Param("example") PortableBatteryExample example);

    int updateByPrimaryKeySelective(PortableBattery record);

    int updateByPrimaryKey(PortableBattery record);
    
    
    int insertUpdateSelective(PortableBattery record);

	List<PortableBatteryBO> findPageByVo(@Param("page")MyPage<PortableBatteryBO> page, @Param("vo")PortableBatteryVO vo);

	List<CountStatsBO> statsPb(@Param("vo")StatsVO vo);
}