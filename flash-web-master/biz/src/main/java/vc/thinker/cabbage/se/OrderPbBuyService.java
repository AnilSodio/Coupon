package vc.thinker.cabbage.se;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinco.data.core.Page;

import vc.thinker.cabbage.se.bo.OrderPbBuyBO;
import vc.thinker.cabbage.se.dao.OrderPbBuyDao;
import vc.thinker.cabbage.se.vo.OrderPbBuyVO;

@Service
@Transactional
public class OrderPbBuyService {

	@Autowired
	private OrderPbBuyDao orderPbBuyDao;
	
	public List<OrderPbBuyBO> findPageByVo(Page<OrderPbBuyBO> page,OrderPbBuyVO vo){
		return orderPbBuyDao.findPageByVo(page, vo);
	}
}
