package vc.thinker.cabbage.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.sys.bo.PromotionTypeBO;
import vc.thinker.cabbage.sys.model.PromotionType;
import vc.thinker.cabbage.sys.model.PromotionTypeExample;

public interface PromotionTypeMapper {
    int countByExample(PromotionTypeExample example);

    int deleteByExample(PromotionTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PromotionType record);

    int insertSelective(PromotionType record);

    List<PromotionTypeBO> selectByExample(PromotionTypeExample example);

    PromotionTypeBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PromotionType record, @Param("example") PromotionTypeExample example);

    int updateByExample(@Param("record") PromotionType record, @Param("example") PromotionTypeExample example);

    int updateByPrimaryKeySelective(PromotionType record);

    int updateByPrimaryKey(PromotionType record);
}