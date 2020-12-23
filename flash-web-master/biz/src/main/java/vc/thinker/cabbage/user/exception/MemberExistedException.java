package vc.thinker.cabbage.user.exception;

import vc.thinker.biz.exception.ServiceException;

/**
 * 用户已存在
 * @author james
 *
 */
public class MemberExistedException extends ServiceException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666612965940754913L;

	public MemberExistedException() {
		super();
	}

	public MemberExistedException(String message) {
		super(message);
	}

	public MemberExistedException(Throwable cause) {
		super(cause);
	}

	public MemberExistedException(String message, Throwable cause) {
		super(message, cause);
	}
}
