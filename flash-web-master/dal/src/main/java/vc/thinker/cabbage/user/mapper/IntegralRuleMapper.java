package vc.thinker.cabbage.user.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.user.bo.IntegralRuleBO;
import vc.thinker.cabbage.user.model.IntegralRule;
import vc.thinker.cabbage.user.model.IntegralRuleExample;
import vc.thinker.cabbage.user.vo.IntegralRuleVO;
import vc.thinker.cabbage.common.MyPage;

public interface IntegralRuleMapper {
    int countByExample(IntegralRuleExample example);

    int deleteByExample(IntegralRuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(IntegralRule record);

    int insertSelective(IntegralRule record);

    List<IntegralRuleBO> selectByExample(IntegralRuleExample example);

    IntegralRuleBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") IntegralRule record, @Param("example") IntegralRuleExample example);

    int updateByExample(@Param("record") IntegralRule record, @Param("example") IntegralRuleExample example);

    int updateByPrimaryKeySelective(IntegralRule record);

    int updateByPrimaryKey(IntegralRule record);

	List<IntegralRuleBO> findPageByVo(@Param("page")MyPage<IntegralRuleBO> page, @Param("vo")IntegralRuleVO vo);
}