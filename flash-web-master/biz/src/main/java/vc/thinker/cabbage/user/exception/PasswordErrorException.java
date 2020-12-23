package vc.thinker.cabbage.user.exception;

import vc.thinker.biz.exception.ServiceException;

/**
 * 账户不存在
 * @author james
 *
 */
public class PasswordErrorException extends ServiceException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666612965940754913L;

	public PasswordErrorException() {
		super();
	}

	public PasswordErrorException(String message) {
		super(message);
	}

	public PasswordErrorException(Throwable cause) {
		super(cause);
	}

	public PasswordErrorException(String message, Throwable cause) {
		super(message, cause);
	}
}
