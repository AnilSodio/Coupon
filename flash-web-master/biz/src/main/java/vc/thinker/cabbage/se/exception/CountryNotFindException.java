package vc.thinker.cabbage.se.exception;


import vc.thinker.cabbage.common.exception.DataNotFindException;

public class CountryNotFindException extends DataNotFindException{

	private static final long serialVersionUID = -6666612965940754913L;

	public CountryNotFindException() {
		super();
	}

	public CountryNotFindException(String message) {
		super(message);
	}

	public CountryNotFindException(Throwable cause) {
		super(cause);
	}

	public CountryNotFindException(String message, Throwable cause) {
		super(message, cause);
	}

}
