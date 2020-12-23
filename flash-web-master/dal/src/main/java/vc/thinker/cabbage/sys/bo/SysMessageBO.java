package vc.thinker.cabbage.sys.bo;


import java.util.Date;

import vc.thinker.cabbage.sys.model.SysMessage;
/**
 * 
 * BO 用于返回数据
 *
 */
public class SysMessageBO extends SysMessage{

	//巡检人员姓名
	private String repairerName;
	
	private String repairerMoile;
	
	//发起人姓名
	private String launchName;
	
	private String cover;
	
    private String adCover;
    
	private String remark;
	
	private String title;
	
	private String nickname;
	
	private String userMobile;
	
    /** 生效时间 **/
    private Date startDate;
    
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getAdCover() {
		return adCover;
	}

	public void setAdCover(String adCover) {
		this.adCover = adCover;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRepairerName() {
		return repairerName;
	}

	public void setRepairerName(String repairerName) {
		this.repairerName = repairerName;
	}

	public String getLaunchName() {
		return launchName;
	}

	public void setLaunchName(String launchName) {
		this.launchName = launchName;
	}

	public String getRepairerMoile() {
		return repairerMoile;
	}

	public void setRepairerMoile(String repairerMoile) {
		this.repairerMoile = repairerMoile;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	
}