package vc.thinker.cabbage.stats;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import vc.thinker.cabbage.se.bo.FeedbackBO;
import vc.thinker.cabbage.se.bo.FeedbackTypeBO;
import vc.thinker.cabbage.se.dao.FeedbackDao;
import vc.thinker.cabbage.se.dao.FeedbackMessageDao;
import vc.thinker.cabbage.se.dao.FeedbackTypeDao;
import vc.thinker.cabbage.se.model.FeedbackMessage;


@Service
public class UpdateFeedbackMessageService {

	private final static Logger log = LoggerFactory.getLogger(UpdateFeedbackMessageService.class);
	
	@Autowired
	private FeedbackDao feedbackDao;
	
	@Autowired
	private FeedbackMessageDao feedbackMessageDao;
	
	@Autowired
	private FeedbackTypeDao feedbackTypeDao;
	
	@TransactionalEventListener
	@Async
	public void updateTripStats(FeedbackMessageEvent event) {

		FeedbackBO feedback = feedbackDao.findOne(event.getFeedbackId());
		if(null == feedback){
			log.info("反馈信息不存在:"+event.getFeedbackId());
			return ;
		}
		
		String typeName = "";
		
		if(null != feedback.getTypeId()){
			FeedbackTypeBO feedbackType = feedbackTypeDao.findOne(feedback.getTypeId());
			typeName = feedbackType.getTypeName();
		}
		
		FeedbackMessage in_bo = new FeedbackMessage();
		in_bo.setFeedbackId(feedback.getId());
		in_bo.setUid(feedback.getUid());
		in_bo.setContent(feedback.getSysCode()+ typeName +feedback.getFeedDesc());
		in_bo.setIsRead(false);
		in_bo.setIsDelete(false);
		in_bo.setCreateTime(new Date());
		
		feedbackMessageDao.save(in_bo);
	}

	public static class FeedbackMessageEvent {

		private Long feedbackId;

		public Long getFeedbackId() {
			return feedbackId;
		}

		public void setFeedbackId(Long feedbackId) {
			this.feedbackId = feedbackId;
		}

	}
}
