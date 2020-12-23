package vc.thinker.cabbage.se.exception;

import vc.thinker.cabbage.common.exception.DataNotFindException;

/**
 * 充电柜没有找到
 * @author james
 *
 */
public class OrderNotFindException extends DataNotFindException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666612965940754913L;

	public OrderNotFindException() {
		super();
	}

	public OrderNotFindException(String message) {
		super(message);
	}

	public OrderNotFindException(Throwable cause) {
		super(cause);
	}

	public OrderNotFindException(String message, Throwable cause) {
		super(message, cause);
	}
}
