package vc.thinker.apiv2.bo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * BO 用于返回数据
 *
 */
public class APIMessageBO{

	/**  **/
    private Long id;

    /** 文字消息 **/
    private String content;

    /** 发送时间 **/
    private Date sendTime;

    @ApiModelProperty("接收用户的类型(3:维保) ")
    private String toUserType;
    
    @ApiModelProperty("31:工单消息 ")
    private Integer bizType;

    @ApiModelProperty("业务ID")
    private String bizId;
    
    @ApiModelProperty("是否图文")
    private Boolean isImageText;
    
    @ApiModelProperty("封面")
	private String cover;
    
    @ApiModelProperty("首页广告图片或者其它广告图片")
    private String adCover;
	
    @ApiModelProperty("摘要")
	private String remark;
	
    @ApiModelProperty("标题")
	private String title;
    
    @ApiModelProperty("图文消息ID")
    private Long imageTextId;
    
    @ApiModelProperty("时间生效时间，该字段用做首页广告时间戳")
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

	public Boolean getIsImageText() {
		return isImageText;
	}

	public void setIsImageText(Boolean isImageText) {
		this.isImageText = isImageText;
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

	public Long getImageTextId() {
		return imageTextId;
	}

	public void setImageTextId(Long imageTextId) {
		this.imageTextId = imageTextId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getToUserType() {
		return toUserType;
	}

	public void setToUserType(String toUserType) {
		this.toUserType = toUserType;
	}

	public Integer getBizType() {
		return bizType;
	}

	public void setBizType(Integer bizType) {
		this.bizType = bizType;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
}