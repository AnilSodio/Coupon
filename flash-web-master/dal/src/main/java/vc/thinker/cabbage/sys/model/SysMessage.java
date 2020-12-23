package vc.thinker.cabbage.sys.model;

import java.util.Date;

import com.sinco.mybatis.dal.model.BaseModel;

public class SysMessage extends BaseModel {
    /**  **/
    private Long id;

    /** 文字消息 **/
    private String content;

    /** 是否图文 **/
    private Boolean isImageText;

    /** 图文ID **/
    private Long imageTextId;

    /** 要发送的用户ID **/
    private Long toUserId;

    /** 发送时间 **/
    private Date sendTime;

    /** 发送人ID **/
    private Long fromUserId;

    /** 是否平台消息（非用户发送的都是平台消息） **/
    private Boolean isPlatform;

    /** 接收用户的类型(1:平台 2:业主 3:维保 4:运营) **/
    private String toUserType;

    /** 是否已读 **/
    private Boolean isRead;

    /** 业务类型
（11:报警提醒 12:保养提醒 13:合同消息14:工单状态消息 15:其他
21:停车消息 22:取车消息 23:系统消息
31:工单消息 32:协助消息 33:系统消息
41:车位任务消息 42:维保管理消息 43:报警提醒消息 44:合同消息） **/
    private Integer bizType;

    /** 业务ID **/
    private String bizId;

    /** logId **/
    private Long logId;

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

    public Boolean getIsImageText() {
        return isImageText;
    }

    public void setIsImageText(Boolean isImageText) {
        this.isImageText = isImageText;
    }

    public Long getImageTextId() {
        return imageTextId;
    }

    public void setImageTextId(Long imageTextId) {
        this.imageTextId = imageTextId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
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

    public String getToUserType() {
        return toUserType;
    }

    public void setToUserType(String toUserType) {
        this.toUserType = toUserType;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
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

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }
}
