package vc.thinker.cabbage.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.cabbage.sys.bo.ExchangeRateBO;
import vc.thinker.cabbage.sys.dao.ExchangeRateDao;
import vc.thinker.cabbage.sys.model.ExchangeRate;
import vc.thinker.cabbage.sys.vo.ExchangeRateVO;
import vc.thinker.cabbage.common.MyPage;

@Service
@Transactional
public class ExchangeRateService {

	@Autowired
	private ExchangeRateDao exchangeRateDao;

	public List<ExchangeRateBO> findPageByVo(MyPage<ExchangeRateBO> page, ExchangeRateVO vo) {
		return exchangeRateDao.findPageByVo(page,vo);
	}

	public ExchangeRateBO findOne(Long id) {
		return exchangeRateDao.findOne(id);
	}
	
	public ExchangeRateBO findOne(String fromCurrency, String toCurrency) {
		return exchangeRateDao.findOneByCurrency(fromCurrency, toCurrency);
	}

	public void update(ExchangeRate rate) {
		exchangeRateDao.update(rate);
	}
	
}
