package vc.thinker.apiv2.vo;

import io.swagger.annotations.ApiModelProperty;

public class FeedbackVO{

    @ApiModelProperty("订单编号")
    private String orderCode;

    @ApiModelProperty("系统编号")
    private String sysCode;

    @ApiModelProperty("问题类型id")
    private String typeId;

    @ApiModelProperty("问题表述")
    private String feedDesc;

    /** 用户上送的照片 **/
    private String imgUrl1;

    /** 用户上送的照片 **/
    private String imgUrl2;

    /** 用户上送的照片 **/
    private String imgUrl3;

    /** 用户上送的照片 **/
    private String imgUrl4;
    
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getFeedDesc() {
		return feedDesc;
	}

	public void setFeedDesc(String feedDesc) {
		this.feedDesc = feedDesc;
	}

	public String getImgUrl1() {
		return imgUrl1;
	}

	public void setImgUrl1(String imgUrl1) {
		this.imgUrl1 = imgUrl1;
	}

	public String getImgUrl2() {
		return imgUrl2;
	}

	public void setImgUrl2(String imgUrl2) {
		this.imgUrl2 = imgUrl2;
	}

	public String getImgUrl3() {
		return imgUrl3;
	}

	public void setImgUrl3(String imgUrl3) {
		this.imgUrl3 = imgUrl3;
	}

	public String getImgUrl4() {
		return imgUrl4;
	}

	public void setImgUrl4(String imgUrl4) {
		this.imgUrl4 = imgUrl4;
	}
}