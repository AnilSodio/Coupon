package vc.thinker.cabbage.money.exception;

import vc.thinker.biz.exception.ServiceException;

/**
 * 提现密码为空
 * @author james
 *
 */
public class MoneyPasswordIsNullException extends ServiceException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666612965940754913L;

	public MoneyPasswordIsNullException() {
		super();
	}

	public MoneyPasswordIsNullException(String message) {
		super(message);
	}

	public MoneyPasswordIsNullException(Throwable cause) {
		super(cause);
	}

	public MoneyPasswordIsNullException(String message, Throwable cause) {
		super(message, cause);
	}
}
