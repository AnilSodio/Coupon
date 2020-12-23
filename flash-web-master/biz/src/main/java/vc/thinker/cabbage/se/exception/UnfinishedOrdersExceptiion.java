package vc.thinker.cabbage.se.exception;

import vc.thinker.biz.exception.ServiceException;

/**
 * 未完成的订单异常
 * @author james
 *
 */
public class UnfinishedOrdersExceptiion extends ServiceException{

	public UnfinishedOrdersExceptiion() {
		super();
	}

	public UnfinishedOrdersExceptiion(String message) {
		super(message);
	}

	public UnfinishedOrdersExceptiion(Throwable cause) {
		super(cause);
	}

	public UnfinishedOrdersExceptiion(String message, Throwable cause) {
		super(message, cause);
	}
}
