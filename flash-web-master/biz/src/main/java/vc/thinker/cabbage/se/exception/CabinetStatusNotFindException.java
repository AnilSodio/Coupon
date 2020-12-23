package vc.thinker.cabbage.se.exception;

import vc.thinker.cabbage.common.exception.DataNotFindException;

/**
 * 充电柜实时信息没有找到
 * @author james
 *
 */
public class CabinetStatusNotFindException extends DataNotFindException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666612965940754913L;

	public CabinetStatusNotFindException() {
		super();
	}

	public CabinetStatusNotFindException(String message) {
		super(message);
	}

	public CabinetStatusNotFindException(Throwable cause) {
		super(cause);
	}

	public CabinetStatusNotFindException(String message, Throwable cause) {
		super(message, cause);
	}
}
