package vc.thinker.cabbage.sys.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.cabbage.money.exception.ExchangeRateNotFindException;
import vc.thinker.cabbage.sys.bo.ExchangeRateBO;
import vc.thinker.cabbage.sys.bo.PlatformRevenueBO;
import vc.thinker.cabbage.sys.dao.ExchangeRateDao;
import vc.thinker.cabbage.sys.dao.PlatformRevenueDao;
import vc.thinker.cabbage.sys.dao.SysSettingDao;
import vc.thinker.cabbage.sys.model.PlatformRevenue;
import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.cabbage.sys.vo.PlatformRevenueVO;
import vc.thinker.cabbage.common.MyPage;

@Service
@Transactional
public class PlatformRevenueService {
	
	private static final Logger log=LoggerFactory.getLogger(PlatformRevenueService.class);

	@Autowired
	private PlatformRevenueDao platformRevenueDao;
	
	@Autowired
	private SysSettingDao sysSettingDao;
	
	/**
	 * 系统币种
	 */
	public static String sysCurrency="CNY";
	
	@PostConstruct
	public void init(){
		SysSetting setting=sysSettingDao.findOne();
		sysCurrency=setting.getPlayformDefaultCurrency();
		log.info("系统币种初始化为=="+sysCurrency);
	}
	
	/**
	 * 添加平台收益
	 * @param sourceId
	 * @param logType
	 * @param currency
	 * @param amount
	 * @param logInfo
	 */
	public void addPlatformRevenue(Long sourceId,String logType,
			String currency,BigDecimal amount,String logInfo){
		PlatformRevenue pr=new PlatformRevenue();
		
		//汇率
		BigDecimal rate=new BigDecimal(1);
		
		BigDecimal addAmount=new BigDecimal(amount.doubleValue());
		
		//进行币种转换
//		if(!currency.equals(sysCurrency)){
//			ExchangeRateBO exchangeRate = exchangeRateDao.findOneByCurrency(currency, sysCurrency);
//			if(exchangeRate == null){
//				throw new ExchangeRateNotFindException(currency, sysCurrency);
//			}
//			rate=exchangeRate.getExchangeRate();
//			addAmount=amount.multiply(rate).setScale(2, BigDecimal.ROUND_DOWN);
//		}
		Date date=new Date();
		pr.setExchangeRate(rate);
		pr.setLogAmount(addAmount);
		pr.setLogCurrency(sysCurrency);
		pr.setLogInfo(logInfo);
		pr.setLogType(logType);
		pr.setOldLogAmount(amount);
		pr.setOldLogCurrency(currency);
		pr.setCreateTime(date);
		pr.setSourceId(sourceId);
		platformRevenueDao.save(pr);
	}

	public List<PlatformRevenueBO> findPageByVo(MyPage<PlatformRevenueBO> page, PlatformRevenueVO vo) {
		return platformRevenueDao.findPageByVo(page,vo);
	}
}
