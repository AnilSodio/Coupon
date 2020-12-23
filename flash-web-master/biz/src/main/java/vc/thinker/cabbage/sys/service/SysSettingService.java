package vc.thinker.cabbage.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.cabbage.sys.bo.CountryBO;
import vc.thinker.cabbage.sys.dao.CountryDao;
import vc.thinker.cabbage.sys.dao.SysSettingDao;
import vc.thinker.cabbage.sys.model.SysSetting;

@Service
@Transactional
public class SysSettingService {
	
	@Autowired
	private SysSettingDao sysSettingDao;

	@Autowired
	private CountryDao countryDao;
	
	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	public SysSetting findOne() {
		return sysSettingDao.findOne();
	}

	/**
	 * 保存或修改设置
	 * @param bo
	 */
	public void saveOrUpdate(SysSetting bo) {
		if(null != bo.getIsOpenDepositDeduction() && bo.getIsOpenDepositDeduction()) {
			Integer days = null == bo.getDeductionDays() ? 3 : bo.getDeductionDays();
			Integer hour = null == bo.getHourOfDay()? 12: bo.getHourOfDay();
			bo.setDeductionMinute(days * hour * 60);
		}
		if(null != bo.getSendSmsHour()) {
			bo.setSendSmsMinute(bo.getSendSmsHour()*60);
		}
		sysSettingDao.save(bo);
	}

	
	public List<String> queryCallCenter() {
		
		List<CountryBO> c_list = countryDao.findAll();
		
		List<String> list = new ArrayList<String>();
		
		c_list.forEach(e->{
			
			if(!StringUtils.isEmpty(e.getCallCenter())){
				list.add(e.getCallCenter());
			}
		});
	 
		return list;
	}
	
}
