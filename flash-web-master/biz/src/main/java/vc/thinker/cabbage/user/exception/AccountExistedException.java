package vc.thinker.cabbage.user.exception;

import vc.thinker.biz.exception.ServiceException;

/**
 * 账户已存在
 * @author james
 *
 */
public class AccountExistedException extends ServiceException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666612965940754913L;

	public AccountExistedException() {
		super();
	}

	public AccountExistedException(String message) {
		super(message);
	}

	public AccountExistedException(Throwable cause) {
		super(cause);
	}

	public AccountExistedException(String message, Throwable cause) {
		super(message, cause);
	}
}
