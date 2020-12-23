package vc.thinker.cabbage.se.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.se.bo.OrderBO;
import vc.thinker.cabbage.se.model.Order;
import vc.thinker.cabbage.se.model.OrderExample;
import vc.thinker.cabbage.se.vo.OrderVO;
import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.vo.StatsVO;
import vc.thinker.cabbage.common.MyPage;

public interface OrderMapper {
    int countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    List<OrderBO> selectByExample(OrderExample example);

    OrderBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
	int updateOrderFail(@Param("time")Date time,@Param("uid")Long uid);

	List<OrderBO> findPageByVo(@Param("page")MyPage<OrderBO> page, @Param("vo")OrderVO vo);
	
	int updateUserCouponNull(Long id);

	OrderBO findLastUseByPbId(Long pbId);

	BigDecimal sumConsume(@Param("day")String day);

	List<CountStatsBO> findConsumeStats(@Param("vo")StatsVO vo);

	int findRideCountYesToday(@Param("date")String date);

	List<OrderBO> findOnlyIdByStatusAndPayTime(@Param("beginDate")String beginDate, 
				@Param("endDate")String endDate, @Param("status")Integer status);
}