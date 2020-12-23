package vc.thinker.cabbage.user.exception;

import vc.thinker.biz.exception.ServiceException;

/**
 * 邀请码不存在
 * @author james
 *
 */
public class InviteCodeNotFindException extends ServiceException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666612965940754913L;

	public InviteCodeNotFindException() {
		super();
	}

	public InviteCodeNotFindException(String message) {
		super(message);
	}

	public InviteCodeNotFindException(Throwable cause) {
		super(cause);
	}

	public InviteCodeNotFindException(String message, Throwable cause) {
		super(message, cause);
	}
}
