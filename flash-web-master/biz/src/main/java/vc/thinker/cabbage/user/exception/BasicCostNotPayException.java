package vc.thinker.cabbage.user.exception;

import vc.thinker.biz.exception.ServiceException;

/**
 * 未支付基础会员费异常
 * @author thinker
 *
 */
public class BasicCostNotPayException extends ServiceException{

	private static final long serialVersionUID = -6666612965940754913L;

	public BasicCostNotPayException() {
		super();
	}

	public BasicCostNotPayException(String message) {
		super(message);
	}

	public BasicCostNotPayException(Throwable cause) {
		super(cause);
	}

	public BasicCostNotPayException(String message, Throwable cause) {
		super(message, cause);
	}

}
