package vc.thinker.cabbage.user.vo;
/**
 * 客服VO
 * 
 * @version v100 2015/10/26
 * @author t2w
 */
public class CustomerVO {
	private Long uid;
	private String name;
	private String nickName;
	private String allergicHistory;
	private Long sex;
	private Long height;
	private Long weight;
	private String birthDay;
	private String email;
	private String mobile;
	private String status;
	private String transHistory;
	private String transGenetic;
	private String headImgPath;
	private String blood;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAllergicHistory() {
		return allergicHistory;
	}

	public void setAllergicHistory(String allergicHistory) {
		this.allergicHistory = allergicHistory;
	}

	public Long getSex() {
		return sex;
	}

	public void setSex(Long sex) {
		this.sex = sex;
	}

	public Long getHeight() {
		return height;
	}

	public void setHeight(Long height) {
		this.height = height;
	}

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransHistory() {
		return transHistory;
	}

	public void setTransHistory(String transHistory) {
		this.transHistory = transHistory;
	}

	public String getTransGenetic() {
		return transGenetic;
	}

	public void setTransGenetic(String transGenetic) {
		this.transGenetic = transGenetic;
	}

	public String getHeadImgPath() {
		return headImgPath;
	}

	public void setHeadImgPath(String headImgPath) {
		this.headImgPath = headImgPath;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}
}
