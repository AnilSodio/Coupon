package vc.thinker.cabbage.sys.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.sys.dao.SettingKeyValDao;

@Repository
public class SysSettingDao {

	@Autowired
	private SettingKeyValDao settingKeyValDao;
	
	private static final String type="sys";
	
	public SysSetting findOne(){
		return settingKeyValDao.findByType(SysSetting.class,type);
	}

	public void save(SysSetting record){
		settingKeyValDao.saveByType(record, type);;
	}
}
