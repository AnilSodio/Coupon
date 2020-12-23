package vc.thinker.cabbage.se;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vc.thinker.cabbage.se.bo.FeedbackBO;
import vc.thinker.cabbage.se.bo.FeedbackMessageBO;
import vc.thinker.cabbage.se.dao.FeedbackMessageDao;
import vc.thinker.cabbage.se.model.FeedbackMessage;
import vc.thinker.cabbage.se.vo.FeedbackMessageVO;
import vc.thinker.cabbage.common.MyPage;

@Service
public class FeedbackMessageService {

	@Autowired
	private FeedbackMessageDao feedbackMessageDao;

	public List<FeedbackMessageBO> findPageByVo(MyPage<FeedbackMessageBO> page,FeedbackMessageVO vo) {
		return feedbackMessageDao.findPageByVo(page,vo);
	}

	public void updateIsReaded(MyPage<FeedbackMessageBO> page) {
		feedbackMessageDao.updateIsReaded(page);
	}

	public void delet(Long id) {
		FeedbackMessage feedback = new FeedbackMessage();
		feedback.setId(id);
		feedback.setIsDelete(true);
		feedbackMessageDao.update(feedback);
	}

	public int countByUnRead() {
		return feedbackMessageDao.countByUnRead();
	}
}
