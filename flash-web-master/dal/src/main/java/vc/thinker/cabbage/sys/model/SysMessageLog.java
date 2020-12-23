package vc.thinker.cabbage.sys.model;

import java.util.Date;

import com.sinco.mybatis.dal.model.BaseModel;

public class SysMessageLog extends BaseModel {
    /**  **/
    private Long id;

    /** 消息 **/
    private String content;

    /** 图文ID **/
    private Long imageTextId;

    /** 是否图文 **/
    private Boolean isImageText;

    /** 要发送的用户ID **/
    private String toUserIds;

    /** 发送时间 **/
    private Date sendTime;

    /** 是否全员发送 **/
    private Boolean isSendAll;

    /** 状态 ： 1 定时发送，2已发送 **/
    private Integer status;

    /** 发送人ID(服务商) **/
    private Long fromUserId;

    /** 是否平台消息 **/
    private Boolean isPlatform;

    /** 发送类型 1维保端，2代理点，3用户端 **/
    private Integer sendType;

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

    public Long getImageTextId() {
        return imageTextId;
    }

    public void setImageTextId(Long imageTextId) {
        this.imageTextId = imageTextId;
    }

    public Boolean getIsImageText() {
        return isImageText;
    }

    public void setIsImageText(Boolean isImageText) {
        this.isImageText = isImageText;
    }

    public String getToUserIds() {
        return toUserIds;
    }

    public void setToUserIds(String toUserIds) {
        this.toUserIds = toUserIds;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Boolean getIsSendAll() {
        return isSendAll;
    }

    public void setIsSendAll(Boolean isSendAll) {
        this.isSendAll = isSendAll;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Boolean getIsPlatform() {
        return isPlatform;
    }

    public void setIsPlatform(Boolean isPlatform) {
        this.isPlatform = isPlatform;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }
}