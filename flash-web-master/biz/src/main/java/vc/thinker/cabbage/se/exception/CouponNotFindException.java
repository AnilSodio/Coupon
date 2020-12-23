package vc.thinker.cabbage.se.exception;

import vc.thinker.cabbage.common.exception.DataNotFindException;

/**
 * 充电柜没有找到
 * @author james
 *
 */
public class CouponNotFindException extends DataNotFindException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666612965940754913L;

	public CouponNotFindException() {
		super();
	}

	public CouponNotFindException(String message) {
		super(message);
	}

	public CouponNotFindException(Throwable cause) {
		super(cause);
	}

	public CouponNotFindException(String message, Throwable cause) {
		super(message, cause);
	}
}
