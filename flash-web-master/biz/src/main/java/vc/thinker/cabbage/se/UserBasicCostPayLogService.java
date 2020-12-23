package vc.thinker.cabbage.se;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.cabbage.user.bo.UserBasicCostPayLogBO;
import vc.thinker.cabbage.user.dao.UserBasicCostPayLogDao;
import vc.thinker.cabbage.user.vo.UserBasicCostPayLogVO;
import vc.thinker.cabbage.common.MyPage;

@Service
@Transactional
public class UserBasicCostPayLogService {

	@Autowired
	private UserBasicCostPayLogDao userBasicCostPayLogDao;
	
	public List<UserBasicCostPayLogBO> findPageByVo(MyPage<UserBasicCostPayLogBO> page,UserBasicCostPayLogVO vo){
		return userBasicCostPayLogDao.findPageByVo(page,vo);
	}
}
