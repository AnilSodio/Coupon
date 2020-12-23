package vc.thinker.opensdk.powerbank.relink;


/**
 * 充电柜充电宝信息类
 * @author wangdawei
 *
 */
public class RelinkSlot {

	/**
	 * 槽位号【1-8】
	 */
	private int no;
	
	/**
	 * 充电宝电量值
	 */
	private int eq;
	
	/**
	 * 充电宝编码id
	 */
	private String snn;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getEq() {
		return eq;
	}

	public void setEq(int eq) {
		this.eq = eq;
	}

	public String getSnn() {
		return snn;
	}

	public void setSnn(String snn) {
		this.snn = snn;
	}
	
	
}
