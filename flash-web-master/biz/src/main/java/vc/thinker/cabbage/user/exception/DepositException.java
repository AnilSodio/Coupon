package vc.thinker.cabbage.user.exception;

import vc.thinker.biz.exception.ServiceException;

/**
 * 押金的异常
 * @author james
 *
 */
public class DepositException extends ServiceException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666612965940754913L;

	public DepositException() {
		super();
	}

	public DepositException(String message) {
		super(message);
	}

	public DepositException(Throwable cause) {
		super(cause);
	}

	public DepositException(String message, Throwable cause) {
		super(message, cause);
	}
}
