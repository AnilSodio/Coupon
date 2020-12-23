package vc.thinker.cabbage.se.exception;

import vc.thinker.biz.exception.ServiceException;

/**
 * 机柜掉线异常
 * @author thinker
 *
 */
public class CabinetIsOfflineException extends ServiceException {

	private static final long serialVersionUID = -6666612965940754913L;

	public CabinetIsOfflineException() {
		super();
	}

	public CabinetIsOfflineException(String message) {
		super(message);
	}

	public CabinetIsOfflineException(Throwable cause) {
		super(cause);
	}

	public CabinetIsOfflineException(String message, Throwable cause) {
		super(message, cause);
	}

}
