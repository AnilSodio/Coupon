package vc.thinker.cabbage.se.exception;

import vc.thinker.biz.exception.ServiceException;

/**
 * 优惠券过期
 * @author james
 *
 */
public class CouponExpiredException extends ServiceException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666612965940754913L;

	public CouponExpiredException() {
		super();
	}

	public CouponExpiredException(String message) {
		super(message);
	}

	public CouponExpiredException(Throwable cause) {
		super(cause);
	}

	public CouponExpiredException(String message, Throwable cause) {
		super(message, cause);
	}
}
