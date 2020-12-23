package vc.thinker.cabbage.se.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.se.bo.PortableBatteryTypeBO;
import vc.thinker.cabbage.se.model.PortableBatteryType;
import vc.thinker.cabbage.se.model.PortableBatteryTypeExample;
import vc.thinker.cabbage.se.vo.PortableBatteryTypeVO;
import vc.thinker.cabbage.common.MyPage;

public interface PortableBatteryTypeMapper {
    int countByExample(PortableBatteryTypeExample example);

    int deleteByExample(PortableBatteryTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PortableBatteryType record);

    int insertSelective(PortableBatteryType record);

    List<PortableBatteryTypeBO> selectByExample(PortableBatteryTypeExample example);

    PortableBatteryTypeBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PortableBatteryType record, @Param("example") PortableBatteryTypeExample example);

    int updateByExample(@Param("record") PortableBatteryType record, @Param("example") PortableBatteryTypeExample example);

    int updateByPrimaryKeySelective(PortableBatteryType record);

    int updateByPrimaryKey(PortableBatteryType record);

	List<PortableBatteryTypeBO> findPageByVo(@Param("page")MyPage<PortableBatteryTypeBO> page, 
												@Param("vo")PortableBatteryTypeVO vo);
}