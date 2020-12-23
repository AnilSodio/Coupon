package vc.thinker.cabbage.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.sys.bo.PromotionBO;
import vc.thinker.cabbage.sys.model.Promotion;
import vc.thinker.cabbage.sys.model.PromotionExample;
import vc.thinker.cabbage.common.MyPage;

public interface PromotionMapper {
	
	List<PromotionBO> findPromotionPage(MyPage<PromotionBO> page);
	
    int countByExample(PromotionExample example);

    int deleteByExample(PromotionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Promotion record);

    int insertSelective(Promotion record);

    List<PromotionBO> selectByExample(PromotionExample example);

    PromotionBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Promotion record, @Param("example") PromotionExample example);

    int updateByExample(@Param("record") Promotion record, @Param("example") PromotionExample example);

    int updateByPrimaryKeySelective(Promotion record);

    int updateByPrimaryKey(Promotion record);
}