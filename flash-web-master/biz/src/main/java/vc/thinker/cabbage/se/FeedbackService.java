package vc.thinker.cabbage.se;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.biz.exception.ServiceException;
import vc.thinker.cabbage.se.bo.CabinetBO;
import vc.thinker.cabbage.se.bo.FeedbackBO;
import vc.thinker.cabbage.se.bo.FeedbackTypeBO;
import vc.thinker.cabbage.se.bo.OrderBO;
import vc.thinker.cabbage.se.bo.PortableBatteryBO;
import vc.thinker.cabbage.se.dao.CabinetDao;
import vc.thinker.cabbage.se.dao.FeedbackDao;
import vc.thinker.cabbage.se.dao.FeedbackTypeDao;
import vc.thinker.cabbage.se.dao.OrderDao;
import vc.thinker.cabbage.se.dao.PortableBatteryDao;
import vc.thinker.cabbage.se.exception.CabinetNotFindException;
import vc.thinker.cabbage.se.exception.OrderNotFindException;
import vc.thinker.cabbage.se.model.Feedback;
import vc.thinker.cabbage.se.vo.FeedbackVO;
import vc.thinker.cabbage.sys.bo.CouponBO;
import vc.thinker.cabbage.sys.dao.CouponDao;
import vc.thinker.cabbage.user.bo.UserCouponBO;
import vc.thinker.cabbage.user.dao.UserCouponDao;
import vc.thinker.cabbage.util.DateTimeUtils;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.sys.model.User;
import vc.thinker.cabbage.stats.UpdateFeedbackMessageService.FeedbackMessageEvent;

@Service
@Transactional
public class FeedbackService {

	private static final Logger log = LoggerFactory.getLogger(FeedbackService.class);
	
	@Autowired
	private FeedbackDao feedbackDao;
	
	@Autowired
	private FeedbackTypeDao typeDao;

	@Autowired
	private CouponDao couponDao;
	
	@Autowired
	private UserCouponDao userCouponDao;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private PortableBatteryDao portableBatteryDao;
	
	@Autowired
	private CabinetDao cabinetDao;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	/**
	 * 查找订单code进行中的反馈
	 * @param orderCode
	 * @return
	 */
	public List<FeedbackBO> findDoingByOrderCode(String orderCode){
		return feedbackDao.findByOrderCode(orderCode, FeedbackConstants.FEED_TYPE_2);
	}
	
	/**
	 * 创建反馈
	 * @param bo
	 */
	public void create(Feedback bo){
		
		FeedbackTypeBO type=typeDao.findOne(bo.getTypeId());
		if(type == null){
			throw new ServiceException("不存在的问题类型");
		}
		if(FeedbackConstants.FEED_TYPE_2.equals(type.getType()) || FeedbackConstants.FEED_TYPE_3.equals(type.getType())){
			if(StringUtils.isBlank(bo.getOrderCode())){
				throw new ServiceException("订单编码为空");
			}
			OrderBO order=orderDao.findByCode(bo.getOrderCode());
			if(order == null || order.getStatus() < OrderConstants.ORDER_STATUS_30){
				throw new OrderNotFindException("找不到订单");
			}
			bo.setOrderId(order.getId());
			bo.setPbCode(bo.getPbCode());
			bo.setPbId(bo.getPbId());
		} 
		if(FeedbackConstants.FEED_TYPE_1.equals(type.getType()) || FeedbackConstants.FEED_TYPE_2.equals(type.getType()) ){
			if(StringUtils.isBlank(bo.getSysCode())){
				throw new ServiceException("机柜编码不能为空");
			}
			CabinetBO machine=cabinetDao.findBySysCode(bo.getSysCode());
			if(machine == null){
				throw new CabinetNotFindException("The Box Id does not exist");
			}
			bo.setCabinetCode(bo.getCabinetCode());
		}
		//设置问题大类
		bo.setFeedType(type.getType());
		
		log.info("========feedType:"+bo.getFeedType());
		
		bo.setStatus(SeCommonConstants.FEEDBACK_STATUS_1);
		bo.setCreateTime(new Date());
		
		feedbackDao.save(bo);
		
		FeedbackMessageEvent event = new FeedbackMessageEvent();
		event.setFeedbackId(bo.getId());
		publisher.publishEvent(event);
	}
	
	
	public List<FeedbackTypeBO> findFeedbackTypeTypeByType(String type,String language){
		return typeDao.findByType(type,language);
	}
	
	
	public List<FeedbackTypeBO> findPageByVo(MyPage<FeedbackBO> page, FeedbackVO vo) {
		return feedbackDao.findPageByVo(page,vo);
	}

	public FeedbackBO findDetailedOne(Long id) {
		return feedbackDao.findDetailedOne(id);
	}

	public List<FeedbackBO> findByOrderId(Long id) {
		return feedbackDao.findByOrderId(id);
	}

	/**
	 * 后台处理反馈
	 * @param bo
	 */
	public void adminUpdateFeedback(FeedbackBO bo,User user) {
		
		FeedbackBO info = feedbackDao.findOne(bo.getId());
		
		if(null == info){
			log.info("反馈不存在，不予处理！");
			return ;
		}
		
		if(!StringUtils.isEmpty(bo.getDealType())){
			
			if(bo.getDealType().contains("1") && null != bo.getTicketId()){
				
				CouponBO couponBO = couponDao.findOne(bo.getTicketId());
				
				UserCouponBO in_bo = new UserCouponBO();
				
				in_bo.setUid(bo.getUid());
				in_bo.setCouponId(bo.getTicketId());
				in_bo.setExpireDate(DateTimeUtils.getDateAfter(DateTimeUtils.get59SecondDate(), couponBO.getDayLimit()));
				in_bo.setAmount(couponBO.getAmount());
				in_bo.setCityId("1");
				in_bo.setStatus(SeCommonConstants.USER_COUPON_STATUS_1);
				in_bo.setSource("system released");
				in_bo.setCurrency(couponBO.getCurrency());
				
				userCouponDao.save(in_bo);
			}
			
			if(bo.getDealType().contains("2")){
				// 调用关锁流程
				if(null != info.getOrderId()){
					OrderBO order = orderService.findOne(info.getOrderId());
					orderService.platformEndOrder(order.getOrderCode(), bo.getRemark(), user.getId());
				}
			}
			
			if(bo.getDealType().contains("3")){
				//标记故障
				PortableBatteryBO up_bo = new PortableBatteryBO();
				up_bo.setId(bo.getPbId());
				up_bo.setStatus(PortableBatteryConstatns.PB_STATUS_DISABLE);
				portableBatteryDao.update(up_bo);
				
			}
		}
		
		FeedbackBO up_feed = new FeedbackBO();
		up_feed.setRemark(bo.getRemark());
		up_feed.setId(bo.getId());
		up_feed.setUpdateTime(new Date());
		up_feed.setStatus(SeCommonConstants.FEEDBACK_STATUS_0);
		up_feed.setTicketId(bo.getTicketId());
		up_feed.setDealType(bo.getDealType());
		feedbackDao.save(up_feed);
	}

}
