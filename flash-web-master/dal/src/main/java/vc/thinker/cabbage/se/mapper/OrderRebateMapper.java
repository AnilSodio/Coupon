package vc.thinker.cabbage.se.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.se.bo.OrderRebateBO;
import vc.thinker.cabbage.se.model.OrderRebate;
import vc.thinker.cabbage.se.model.OrderRebateExample;

public interface OrderRebateMapper {
    int countByExample(OrderRebateExample example);

    int deleteByExample(OrderRebateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderRebate record);

    int insertSelective(OrderRebate record);

    List<OrderRebateBO> selectByExample(OrderRebateExample example);

    OrderRebateBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderRebate record, @Param("example") OrderRebateExample example);

    int updateByExample(@Param("record") OrderRebate record, @Param("example") OrderRebateExample example);

    int updateByPrimaryKeySelective(OrderRebate record);

    int updateByPrimaryKey(OrderRebate record);
}