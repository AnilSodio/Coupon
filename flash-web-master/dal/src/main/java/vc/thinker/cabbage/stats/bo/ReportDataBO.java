package vc.thinker.cabbage.stats.bo;

import com.alibaba.fastjson.annotation.JSONField;

public class ReportDataBO {

    @JSONField(ordinal = 1, name="devices")
	private ReportStatsBO deviceData;
	
    @JSONField(ordinal = 2, name="members")
	private ReportStatsBO userData;
	
    @JSONField(ordinal = 3, name="orders")
	private ReportStatsBO orderData;
	
    @JSONField(ordinal = 4, name="cashes")
	private ReportStatsBO consumeData;
	
    @JSONField(ordinal = 5, name="vips")
	private ReportStatsBO vipData;
	
    @JSONField(ordinal = 6, name="feedbacks")
	private ReportStatsBO feedbackData;
	
    @JSONField(ordinal = 7, name="feedbackInUse")
	private ReportStatsBO feedbackInUseData;

	public ReportStatsBO getDeviceData() {
		return deviceData;
	}

	public void setDeviceData(ReportStatsBO deviceData) {
		this.deviceData = deviceData;
	}

	public ReportStatsBO getUserData() {
		return userData;
	}

	public void setUserData(ReportStatsBO userData) {
		this.userData = userData;
	}

	public ReportStatsBO getOrderData() {
		return orderData;
	}

	public void setOrderData(ReportStatsBO orderData) {
		this.orderData = orderData;
	}

	public ReportStatsBO getConsumeData() {
		return consumeData;
	}

	public void setConsumeData(ReportStatsBO consumeData) {
		this.consumeData = consumeData;
	}

	public ReportStatsBO getVipData() {
		return vipData;
	}

	public void setVipData(ReportStatsBO vipData) {
		this.vipData = vipData;
	}

	public ReportStatsBO getFeedbackData() {
		return feedbackData;
	}

	public void setFeedbackData(ReportStatsBO feedbackData) {
		this.feedbackData = feedbackData;
	}

	public ReportStatsBO getFeedbackInUseData() {
		return feedbackInUseData;
	}

	public void setFeedbackInUseData(ReportStatsBO feedbackInUseData) {
		this.feedbackInUseData = feedbackInUseData;
	}
}
