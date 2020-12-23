package vc.thinker.cabbage.user.exception;

import vc.thinker.biz.exception.ServiceException;

public class DepositNotPayException extends ServiceException{


	private static final long serialVersionUID = -6666612965940754913L;

	public DepositNotPayException() {
		super();
	}

	public DepositNotPayException(String message) {
		super(message);
	}

	public DepositNotPayException(Throwable cause) {
		super(cause);
	}

	public DepositNotPayException(String message, Throwable cause) {
		super(message, cause);
	}


}
