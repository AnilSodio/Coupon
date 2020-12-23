package vc.thinker.cabbage.common.exception;

import vc.thinker.biz.exception.ServiceException;

/**
 * 充电柜没有找到
 * @author james
 *
 */
public class DataNotFindException extends ServiceException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666612965940754913L;

	public DataNotFindException() {
		super();
	}

	public DataNotFindException(String message) {
		super(message);
	}

	public DataNotFindException(Throwable cause) {
		super(cause);
	}

	public DataNotFindException(String message, Throwable cause) {
		super(message, cause);
	}
}
