package vc.thinker.cabbage.common;

import vc.thinker.biz.exception.ServiceException;

/**
 * 用户支付密码为空
 * @author james
 *
 */
public class MoneyPasswordNullConsumption extends ServiceException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MoneyPasswordNullConsumption(){
		super("用户支付密码为空");
	}

}
