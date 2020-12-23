package vc.thinker.cabbage.user.exception;

import vc.thinker.biz.exception.ServiceException;

public class UserHasBindedCabinetException extends ServiceException{


	private static final long serialVersionUID = -6666612965940754913L;

	public UserHasBindedCabinetException() {
		super();
	}

	public UserHasBindedCabinetException(String message) {
		super(message);
	}

	public UserHasBindedCabinetException(Throwable cause) {
		super(cause);
	}

	public UserHasBindedCabinetException(String message, Throwable cause) {
		super(message, cause);
	}

}
