package vc.thinker.cabbage.stats;

import cn.jiguang.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import vc.thinker.cabbage.se.OrderConstants;
import vc.thinker.cabbage.se.OrderService;
import vc.thinker.cabbage.se.bo.OrderBO;
import vc.thinker.cabbage.stats.bo.RegisterStatsBO;
import vc.thinker.cabbage.stats.dao.OrderStatsDao;
import vc.thinker.cabbage.stats.dao.RegisterStatsDao;
import vc.thinker.cabbage.stats.model.OrderStats;
import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.bo.UserCouponBO;
import vc.thinker.cabbage.user.dao.MemberDao;
import vc.thinker.cabbage.user.dao.UserCouponDao;

import java.math.BigDecimal;
import java.util.Calendar;


@Service
public class UpdateOrderStatsService {

    private final static Logger log = LoggerFactory.getLogger(UpdateOrderStatsService.class);

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private RegisterStatsDao registerStatsDao;

    @Autowired
    private OrderStatsDao orderStatsDao;
    
    @Autowired
    private MemberDao memberDao;
    
    @Autowired
    private UserCouponDao userCouponDao;

    @Value("${lbs.baidu.ak}")
    private String baiduAK;


    /**
     * 更新使用统计
     *
     * @param event
     */
    @TransactionalEventListener
    @Async
    public void updateTripStats(EndOrderEvent event) {
    	
    	OrderBO order = orderService.findOne(event.getOrderId());

        if (order == null) {
            log.error("trip  [{}] not find", event.getOrderId());
            return;
        }
        
       	OrderStats in_stats = new OrderStats();
       	
       	in_stats.setUid(order.getUid());
       	in_stats.setStatsDate(order.getPayTime());
       	in_stats.setDuration(order.getRideTime());
       	in_stats.setOrderStatus(order.getStatus());
       	in_stats.setOrderId(order.getId());
    	in_stats.setTripAmount(order.getPrice());
       	in_stats.setPayType(order.getPayType());
       	in_stats.setCurrency(order.getCurrency());
       	
       	if(!StringUtils.isEmpty(order.getClientSource())){
       		in_stats.setClientType(order.getClientSource().split("/")[0].toLowerCase().replaceAll("ios", "iphone"));
       	}
       	//会员卡、余额、免费 、用户实际支付的金额都为 0 
       	if(!StringUtils.isEmpty(order.getPayType()) && order.getPayType().equals(OrderConstants.PAY_TYPE_CASH)){
       		if(null != order.getUserCouponId()){
       			UserCouponBO coupon = userCouponDao.findOne(order.getUserCouponId());
       			if(null != coupon && null != coupon.getAmount()){
       				if(coupon.getAmount().compareTo(order.getPrice()) > -1){
       					in_stats.setActualConsume(BigDecimal.ZERO);
       				}else {
       					in_stats.setActualConsume(order.getPrice().subtract(coupon.getAmount()));
       				}
       			}
       		}
       	}else {
       		in_stats.setActualConsume(BigDecimal.ZERO);
       	}
       	
       	
       	RegisterStatsBO resistStats = registerStatsDao.findByUid(order.getUid());
       	if(null != resistStats){
       		in_stats.setAge(resistStats.getAge());
       		in_stats.setSex(resistStats.getSex());
       	}else {
       		MemberBO memberBo = memberDao.findOne(order.getUid());
       		if(!StringUtils.isEmpty(memberBo.getIdCard()) &&  memberBo.getIdCard().length() == 18){
       			
       			Integer year = Integer.parseInt(memberBo.getIdCard().substring(6, 10));
				Calendar date=Calendar.getInstance();
				in_stats.setAge(date.get(Calendar.YEAR)-year);
				Integer sex = Integer.parseInt(memberBo.getIdCard().substring(16, 17));
				if(sex%2==0){
					in_stats.setSex(StatsConstants.SEX_2);
				}else{
					in_stats.setSex(StatsConstants.SEX_1);
				}
				
       		}
       	}
       	
       	orderStatsDao.save(in_stats);
       	
    }
    
    /**
     * 结束使用事件
     */
    public static class EndOrderEvent {
        //行程id
        private Long orderId;

		public Long getOrderId() {
			return orderId;
		}

		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}

    }


}
