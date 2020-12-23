package vc.thinker.cabbage.user.exception;

import vc.thinker.biz.exception.ServiceException;

/**
 * 没有进行中的订单异常
 */
public class NoOngoingOrderException extends ServiceException{

	private static final long serialVersionUID = -6666612965940754913L;

	public NoOngoingOrderException() {
		super();
	}

	public NoOngoingOrderException(String message) {
		super(message);
	}

	public NoOngoingOrderException(Throwable cause) {
		super(cause);
	}

	public NoOngoingOrderException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
