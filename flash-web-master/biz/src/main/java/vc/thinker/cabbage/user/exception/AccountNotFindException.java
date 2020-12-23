package vc.thinker.cabbage.user.exception;

import vc.thinker.biz.exception.ServiceException;

/**
 * 账户不存在
 * @author james
 *
 */
public class AccountNotFindException extends ServiceException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666612965940754913L;

	public AccountNotFindException() {
		super();
	}

	public AccountNotFindException(String message) {
		super(message);
	}

	public AccountNotFindException(Throwable cause) {
		super(cause);
	}

	public AccountNotFindException(String message, Throwable cause) {
		super(message, cause);
	}
}
