package vc.thinker.cabbage.se.exception;

import vc.thinker.biz.exception.ServiceException;

/**
 * 电池数量不够
 * @author james
 *
 */
public class BatteryIsNotEnoughExceptiion extends ServiceException{

	public BatteryIsNotEnoughExceptiion() {
		super();
	}

	public BatteryIsNotEnoughExceptiion(String message) {
		super(message);
	}

	public BatteryIsNotEnoughExceptiion(Throwable cause) {
		super(cause);
	}

	public BatteryIsNotEnoughExceptiion(String message, Throwable cause) {
		super(message, cause);
	}
}
