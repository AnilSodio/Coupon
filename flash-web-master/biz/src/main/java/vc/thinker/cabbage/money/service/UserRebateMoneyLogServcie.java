package vc.thinker.cabbage.money.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.cabbage.user.bo.UserRebateMoneyLogBO;
import vc.thinker.cabbage.user.dao.UserRebateMoneyLogDao;
import vc.thinker.cabbage.user.vo.UserRebateMoneyLogVO;
import vc.thinker.cabbage.common.MyPage;

@Service
@Transactional
public class UserRebateMoneyLogServcie {

	@Autowired
	private UserRebateMoneyLogDao userRebateMoneyLogDao;
	
	public List<UserRebateMoneyLogBO> findPageByVo(MyPage<UserRebateMoneyLogBO> page,UserRebateMoneyLogVO vo){
		return userRebateMoneyLogDao.findPageByVo(page,vo);
	}
}
