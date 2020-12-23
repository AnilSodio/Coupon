package vc.thinker.cabbage.money.exception;

import vc.thinker.biz.exception.ServiceException;

/**
 * 余额不足的异常
 * @author james
 *
 */
public class BalanceNotEnoughException extends ServiceException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666612965940754913L;

	public BalanceNotEnoughException() {
		super();
	}

	public BalanceNotEnoughException(String message) {
		super(message);
	}

	public BalanceNotEnoughException(Throwable cause) {
		super(cause);
	}

	public BalanceNotEnoughException(String message, Throwable cause) {
		super(message, cause);
	}
}
