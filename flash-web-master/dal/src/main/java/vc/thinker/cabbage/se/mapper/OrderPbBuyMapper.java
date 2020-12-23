package vc.thinker.cabbage.se.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.sinco.data.core.Page;

import vc.thinker.cabbage.se.bo.OrderPbBuyBO;
import vc.thinker.cabbage.se.model.OrderPbBuy;
import vc.thinker.cabbage.se.model.OrderPbBuyExample;
import vc.thinker.cabbage.se.vo.OrderPbBuyVO;

public interface OrderPbBuyMapper {
    int countByExample(OrderPbBuyExample example);

    int deleteByExample(OrderPbBuyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderPbBuy record);

    int insertSelective(OrderPbBuy record);

    List<OrderPbBuyBO> selectByExample(OrderPbBuyExample example);

    OrderPbBuyBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderPbBuy record, @Param("example") OrderPbBuyExample example);

    int updateByExample(@Param("record") OrderPbBuy record, @Param("example") OrderPbBuyExample example);

    int updateByPrimaryKeySelective(OrderPbBuy record);

    int updateByPrimaryKey(OrderPbBuy record);

	List<OrderPbBuyBO> findPageByVo(@Param("page")Page<OrderPbBuyBO> page, 
				@Param("vo")OrderPbBuyVO vo);
}