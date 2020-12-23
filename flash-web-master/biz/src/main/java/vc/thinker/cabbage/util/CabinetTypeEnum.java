package vc.thinker.cabbage.util;

/**
 * 定义充电柜类型枚举（充电柜类型与协议适配）
 * 在实际过程中不允许自定义增加类型
 * @author wangdawei
 *
 */
public enum CabinetTypeEnum {

	YCB(1, "ycb", 10, 2), 
	FTJ(2, "ftj", 10, 2), 
	RELINK(3, "relink", 8, 1), 
	RELINKB(5, "relinkb", 48, 1), 
	RELINKC(6, "relinkc", 24, 1), 
	YCBB(4, "ycbb", 40, 2);

	private int code;
	
	private String name;
	
	private int capacity;
	
	private int status;

	CabinetTypeEnum(int code, String name, int capacity, int status) { 
		this.code = code;
		this.name = name;
		this.capacity = capacity;
		this.status = status;
	}

	/**
	 * 自己定义一个静态方法,通过code返回枚举常量对象
	 * @param code
	 * @return
	 */
	public static CabinetTypeEnum getValue(int code) {

		for (CabinetTypeEnum type : values()) {
			if (type.getCode() == code) {
				return type;
			}
		}
		return null;
	}
	
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
