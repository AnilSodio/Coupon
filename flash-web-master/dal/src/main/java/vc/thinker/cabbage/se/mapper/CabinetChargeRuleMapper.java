package vc.thinker.cabbage.se.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.se.bo.CabinetChargeRuleBO;
import vc.thinker.cabbage.se.model.CabinetChargeRule;
import vc.thinker.cabbage.se.model.CabinetChargeRuleExample;
import vc.thinker.cabbage.se.vo.CabinetChargeRuleVO;
import vc.thinker.cabbage.common.MyPage;

public interface CabinetChargeRuleMapper {
    int countByExample(CabinetChargeRuleExample example);

    int deleteByExample(CabinetChargeRuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CabinetChargeRule record);

    int insertSelective(CabinetChargeRule record);

    List<CabinetChargeRuleBO> selectByExample(CabinetChargeRuleExample example);

    CabinetChargeRuleBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CabinetChargeRule record, @Param("example") CabinetChargeRuleExample example);

    int updateByExample(@Param("record") CabinetChargeRule record, @Param("example") CabinetChargeRuleExample example);

    int updateByPrimaryKeySelective(CabinetChargeRule record);

    int updateByPrimaryKey(CabinetChargeRule record);

	List<CabinetChargeRuleBO> findPageByVo(@Param("page")MyPage<CabinetChargeRuleBO> page, 
											@Param("vo")CabinetChargeRuleVO vo);
}