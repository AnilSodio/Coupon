package vc.thinker.cabbage.sys.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.util.Date;

public class ShareSet extends BaseModel {
    /**  **/
    private Long id;

    /** 行程分享途径 1，微信，2朋友圈，3QQ,4QQ空间 **/
    private String shareWay;

    /** 行程分享标题 **/
    private String shareTitle;

    /** 行程分享内容 **/
    private String shareContent;

    /** 邀请好友途径 1，微信，2朋友圈，3QQ,4QQ空间 **/
    private String inviteWay;

    /** 邀请好友标题 **/
    private String inviteTitle;

    /** 邀请好友内容 **/
    private String inviteContent;

    /** 创建时间 **/
    private Date createTime;

    /** 修改时间 **/
    private Date updateTime;

    /** 是否允许分享  true 允许,false 不允许 **/
    private Boolean isAllowShare;

    /** 是否允许邀请 true:允许，false 不允许 **/
    private Boolean isAllowInvite;

    /** 分享的图标 **/
    private String shareAppImg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShareWay() {
        return shareWay;
    }

    public void setShareWay(String shareWay) {
        this.shareWay = shareWay;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }

    public String getInviteWay() {
        return inviteWay;
    }

    public void setInviteWay(String inviteWay) {
        this.inviteWay = inviteWay;
    }

    public String getInviteTitle() {
        return inviteTitle;
    }

    public void setInviteTitle(String inviteTitle) {
        this.inviteTitle = inviteTitle;
    }

    public String getInviteContent() {
        return inviteContent;
    }

    public void setInviteContent(String inviteContent) {
        this.inviteContent = inviteContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsAllowShare() {
        return isAllowShare;
    }

    public void setIsAllowShare(Boolean isAllowShare) {
        this.isAllowShare = isAllowShare;
    }

    public Boolean getIsAllowInvite() {
        return isAllowInvite;
    }

    public void setIsAllowInvite(Boolean isAllowInvite) {
        this.isAllowInvite = isAllowInvite;
    }

    public String getShareAppImg() {
        return shareAppImg;
    }

    public void setShareAppImg(String shareAppImg) {
        this.shareAppImg = shareAppImg;
    }
}