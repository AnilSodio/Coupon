package vc.thinker.cabbage.sys.bo;


import com.sinco.dic.client.model.DicBase;

import vc.thinker.cabbage.sys.model.Country;
/**
 * 
 * BO 用于返回数据
 *
 */
public class  CountryBO extends Country implements DicBase{

	@Override
	public String getCode() {
		return this.getIsDefault()?"1":"0";
	}

	@Override
	public String getName() {
		return this.getDefaultLanguage();
	}

}