package vc.thinker.cabbage.user.exception;


import vc.thinker.biz.exception.ServiceException;

/**
 * 用户币种信息不完善异常
 * @author thinker
 *
 */
public class UserCurrencyNotPerfectException extends ServiceException {

	private static final long serialVersionUID = -6666612965940754913L;

	public UserCurrencyNotPerfectException() {
		super();
	}

	public UserCurrencyNotPerfectException(String message) {
		super(message);
	}

	public UserCurrencyNotPerfectException(Throwable cause) {
		super(cause);
	}

	public UserCurrencyNotPerfectException(String message, Throwable cause) {
		super(message, cause);
	}

}
