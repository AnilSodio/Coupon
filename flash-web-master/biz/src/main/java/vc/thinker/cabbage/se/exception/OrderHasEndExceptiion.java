package vc.thinker.cabbage.se.exception;

import vc.thinker.biz.exception.ServiceException;

/**
 * 订单已结束
 * @author james
 *
 */
public class OrderHasEndExceptiion extends ServiceException{

	public OrderHasEndExceptiion() {
		super();
	}

	public OrderHasEndExceptiion(String message) {
		super(message);
	}

	public OrderHasEndExceptiion(Throwable cause) {
		super(cause);
	}

	public OrderHasEndExceptiion(String message, Throwable cause) {
		super(message, cause);
	}
}
