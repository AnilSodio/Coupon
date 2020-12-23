package vc.thinker.cabbage.user.exception;


import vc.thinker.biz.exception.ServiceException;

/**
 * 用户币种信息不完善异常
 * @author thinker
 *
 */
public class PlatNotConfigDepositException extends ServiceException {

	private static final long serialVersionUID = -6666612965940754913L;

	public PlatNotConfigDepositException() {
		super();
	}

	public PlatNotConfigDepositException(String message) {
		super(message);
	}

	public PlatNotConfigDepositException(Throwable cause) {
		super(cause);
	}

	public PlatNotConfigDepositException(String message, Throwable cause) {
		super(message, cause);
	}

}
