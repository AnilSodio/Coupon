package vc.thinker.cabbage.sys.exception;

import vc.thinker.biz.exception.ServiceException;

/**
 * 国家信息不存在
 * @author thinker
 *
 */
public class CountryNotExitException extends ServiceException{
	 
	private static final long serialVersionUID = -6666612965940754913L;

	public CountryNotExitException() {
		super();
	}

	public CountryNotExitException(String message) {
		super(message);
	}

	public CountryNotExitException(Throwable cause) {
		super(cause);
	}

	public CountryNotExitException(String message, Throwable cause) {
		super(message, cause);
	}

}
