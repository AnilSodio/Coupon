package vc.thinker.apiv2.bo;
/**
 *
 * @author ZhangGaoXiang
 * @time Dec 16, 20194:55:14 PM
 */
public class FondyPaymetBO {

	public FondyPaymetBO() {
		super();
	}

	public FondyPaymetBO(String payUrl) {
		this.payUrl = payUrl;
	}

	private String payUrl;

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}
	
}
