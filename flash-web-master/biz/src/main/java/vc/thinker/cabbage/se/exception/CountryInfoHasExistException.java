package vc.thinker.cabbage.se.exception;

import vc.thinker.biz.exception.ServiceException;

public class CountryInfoHasExistException extends ServiceException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666612965940754913L;

	public CountryInfoHasExistException() {
		super();
	}

	public CountryInfoHasExistException(String message) {
		super(message);
	}

	public CountryInfoHasExistException(Throwable cause) {
		super(cause);
	}

	public CountryInfoHasExistException(String message, Throwable cause) {
		super(message, cause);
	}
}
