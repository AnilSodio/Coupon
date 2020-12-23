package vc.thinker.cabbage.user.exception;

import vc.thinker.biz.exception.ServiceException;

/**
 * 邀请码已使用
 * @author james
 *
 */
public class InviteCodeUsedException extends ServiceException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666612965940754913L;

	public InviteCodeUsedException() {
		super();
	}

	public InviteCodeUsedException(String message) {
		super(message);
	}

	public InviteCodeUsedException(Throwable cause) {
		super(cause);
	}

	public InviteCodeUsedException(String message, Throwable cause) {
		super(message, cause);
	}
}
