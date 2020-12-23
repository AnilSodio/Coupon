package vc.thinker.cabbage.user.exception;

import vc.thinker.biz.exception.ServiceException;

/**
 * 服务商被锁定
 * @author james
 *
 */
public class SellerLockedException extends ServiceException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666612965940754913L;

	public SellerLockedException() {
		super();
	}

	public SellerLockedException(String message) {
		super(message);
	}

	public SellerLockedException(Throwable cause) {
		super(cause);
	}

	public SellerLockedException(String message, Throwable cause) {
		super(message, cause);
	}
}
