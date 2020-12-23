package vc.thinker.cabbage.money.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vc.thinker.cabbage.se.bo.UserRebateLogBO;
import vc.thinker.cabbage.se.dao.UserRebateLogDao;
import vc.thinker.cabbage.se.vo.UserRebateLogVO;
import vc.thinker.cabbage.common.MyPage;

@Service
@Transactional
public class UserRebateLogService {

	@Autowired
	private UserRebateLogDao userRebateLogDao;

	/**
	 * 商户返润记录
	 * @param page
	 * @param vo
	 * @return
	 */
	public List<UserRebateLogBO> findPageByVo(MyPage<UserRebateLogBO> page, UserRebateLogVO vo) {
		return userRebateLogDao.findPageByVo(page, vo);
	}

	/**
	 * 代理商返润记录
	 * @param page
	 * @param vo
	 * @return
	 */
	public List<UserRebateLogBO> findPageByAgent(MyPage<UserRebateLogBO> page, UserRebateLogVO vo) {
		return userRebateLogDao.findPageByAgent(page,vo);
	}

	/**
	 * 推荐人返润记录
	 * @param page
	 * @param vo
	 * @return
	 */
	public List<UserRebateLogBO> findPageByReferee(MyPage<UserRebateLogBO> page, UserRebateLogVO vo) {
		return userRebateLogDao.findPageByReferee(page,vo);
	}

	public List<UserRebateLogBO> findPageBySeller(MyPage<UserRebateLogBO> page, UserRebateLogVO vo) {
		return userRebateLogDao.findPageBySeller(page,vo);
	}

	/**
	 * 平台后台查询返润记录
	 * @param page
	 * @param vo
	 */
	public List<UserRebateLogBO> findPageByAdmin(MyPage<UserRebateLogBO> page, UserRebateLogVO vo) {
		return userRebateLogDao.findPageByAdmin(page,vo);
	}

	public List<UserRebateLogBO> sumByCurrency() {
		return userRebateLogDao.sumByCurrency();
	}
}
