package vc.thinker.cabbage.money.exception;

import vc.thinker.biz.exception.ServiceException;

/**
 * 汇率找不到
 * @author james
 *
 */
public class ExchangeRateNotFindException extends ServiceException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666612965940754913L;

	public ExchangeRateNotFindException() {
		super();
	}
	
	public ExchangeRateNotFindException(String fromCurrency,String toCurrency) {
		super(fromCurrency+" to "+toCurrency+" exchange rate not find");
	}

	public ExchangeRateNotFindException(String message) {
		super(message);
	}

	public ExchangeRateNotFindException(Throwable cause) {
		super(cause);
	}

	public ExchangeRateNotFindException(String message, Throwable cause) {
		super(message, cause);
	}
}
