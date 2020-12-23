package vc.thinker.cabbage.user.exception;

import vc.thinker.biz.exception.ServiceException;

/**
 *
 * @author ZhangGaoXiang
 * @time Dec 16, 201911:09:19 AM
 */
public class MemberNotBindMobileException extends ServiceException{

	private static final long serialVersionUID = -6666612965940754913L;

	public MemberNotBindMobileException() {
		super();
	}

	public MemberNotBindMobileException(String message) {
		super(message);
	}

	public MemberNotBindMobileException(Throwable cause) {
		super(cause);
	}

	public MemberNotBindMobileException(String message, Throwable cause) {
		super(message, cause);
	}
}
