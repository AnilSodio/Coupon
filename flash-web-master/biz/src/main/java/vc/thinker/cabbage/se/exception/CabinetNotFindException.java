package vc.thinker.cabbage.se.exception;

import vc.thinker.cabbage.common.exception.DataNotFindException;

/**
 * 充电柜没有找到
 * @author james
 *
 */
public class CabinetNotFindException extends DataNotFindException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666612965940754913L;

	public CabinetNotFindException() {
		super();
	}

	public CabinetNotFindException(String message) {
		super(message);
	}

	public CabinetNotFindException(Throwable cause) {
		super(cause);
	}

	public CabinetNotFindException(String message, Throwable cause) {
		super(message, cause);
	}
}
