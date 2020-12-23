package vc.thinker.cabbage.se;

import org.springframework.transaction.annotation.Transactional;

import vc.thinker.cabbage.se.bo.FeedbackTypeBO;
import vc.thinker.cabbage.se.dao.FeedbackTypeDao;
import vc.thinker.cabbage.se.vo.FeedbackTypeVO;
import vc.thinker.cabbage.common.MyPage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FeedbackTypeServcie {

	@Autowired
	private FeedbackTypeDao feedbackTypeDao;

	public List<FeedbackTypeBO> findAll() {
		return feedbackTypeDao.findAll();
	}

	public List<FeedbackTypeBO> findPageByVo(MyPage<FeedbackTypeBO> page, FeedbackTypeVO vo) {
		return feedbackTypeDao.findPageByVo(page,vo);
	}
}
