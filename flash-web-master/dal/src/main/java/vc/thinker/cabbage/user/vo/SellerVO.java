package vc.thinker.cabbage.user.vo;

public class SellerVO {

	private Long uid ;
	
	private String sellerName;
	
	private String contactUserName;
	
	private String contactMobile;
	
	/**推荐人Id**/
	private Long refereeUid;
	
	private String email;
	
	private String country;

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getContactUserName() {
		return contactUserName;
	}

	public void setContactUserName(String contactUserName) {
		this.contactUserName = contactUserName;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getRefereeUid() {
		return refereeUid;
	}

	public void setRefereeUid(Long refereeUid) {
		this.refereeUid = refereeUid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}