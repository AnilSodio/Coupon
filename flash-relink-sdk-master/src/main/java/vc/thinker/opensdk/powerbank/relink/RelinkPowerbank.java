package vc.thinker.opensdk.powerbank.relink;

import java.util.List;



/**
 * 充电柜
 * @author wangdawei
 *
 */
public class RelinkPowerbank {

	/**
	 * 充电柜编码
	 */
	private String sn;
	
	/**
	 * 充电柜槽位数（有效）
	 */
	private int num;
	
	/**
	 * 最后心跳时间
	 */
	private long alive;
	
	/**
	 * 软件版本
	 */
	private String softVer;
	
	/**
	 * 设备状态
	 */
	private  int state;
	
	
	/**
	 * 充电柜内的充电宝信息集合
	 */
	private List<RelinkSlot> slots;
	
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public List<RelinkSlot> getSlots() {
		return slots;
	}

	public void setSlots(List<RelinkSlot> slots) {
		this.slots = slots;
	}

	public long getAlive() {
		return alive;
	}

	public void setAlive(long alive) {
		this.alive = alive;
	}

	public String getSoftVer() {
		return softVer;
	}

	public void setSoftVer(String softVer) {
		this.softVer = softVer;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
