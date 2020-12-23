package vc.thinker.cabbage.se.exception;

import vc.thinker.biz.exception.ServiceException;

/**
 * 订单已支付的异常
 * @author james
 *
 */
public class OrderHavePaidException extends ServiceException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666612965940754913L;

	public OrderHavePaidException() {
		super();
	}

	public OrderHavePaidException(String message) {
		super(message);
	}

	public OrderHavePaidException(Throwable cause) {
		super(cause);
	}

	public OrderHavePaidException(String message, Throwable cause) {
		super(message, cause);
	}
}
