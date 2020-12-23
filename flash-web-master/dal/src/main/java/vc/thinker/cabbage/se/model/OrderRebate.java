package vc.thinker.cabbage.se.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

public class OrderRebate extends BaseModel {
    /**  **/
    private Long id;

    /**  **/
    private Long orderId;

    /** 平台反润模式:f固定金额；p比率 **/
    private String plantformModel;

    /** 返润值 **/
    private BigDecimal plantformRebate;

    /** 商品反润模式f固定金额；p比率 **/
    private String borrowSellerModel;

    /** 反润值 **/
    private BigDecimal borrowSellerRebate;

    /**  **/
    private Date createTime;

    /** 还伞代理点反润 **/
    private String returnSellerModel;

    /** 反润值 **/
    private BigDecimal returnSellerRabate;

    /** 代理点反润方式 **/
    private String agentRebateType;

    /** 代理人 **/
    private String agentModel;

    /** 代理人 **/
    private BigDecimal agentRabate;

    /** 推荐人模式 **/
    private String recommendedModel;

    /** 推荐人金额 **/
    private BigDecimal recommendedRabate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPlantformModel() {
        return plantformModel;
    }

    public void setPlantformModel(String plantformModel) {
        this.plantformModel = plantformModel;
    }

    public BigDecimal getPlantformRebate() {
        return plantformRebate;
    }

    public void setPlantformRebate(BigDecimal plantformRebate) {
        this.plantformRebate = plantformRebate;
    }

    public String getBorrowSellerModel() {
        return borrowSellerModel;
    }

    public void setBorrowSellerModel(String borrowSellerModel) {
        this.borrowSellerModel = borrowSellerModel;
    }

    public BigDecimal getBorrowSellerRebate() {
        return borrowSellerRebate;
    }

    public void setBorrowSellerRebate(BigDecimal borrowSellerRebate) {
        this.borrowSellerRebate = borrowSellerRebate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReturnSellerModel() {
        return returnSellerModel;
    }

    public void setReturnSellerModel(String returnSellerModel) {
        this.returnSellerModel = returnSellerModel;
    }

    public BigDecimal getReturnSellerRabate() {
        return returnSellerRabate;
    }

    public void setReturnSellerRabate(BigDecimal returnSellerRabate) {
        this.returnSellerRabate = returnSellerRabate;
    }

    public String getAgentRebateType() {
        return agentRebateType;
    }

    public void setAgentRebateType(String agentRebateType) {
        this.agentRebateType = agentRebateType;
    }

    public String getAgentModel() {
        return agentModel;
    }

    public void setAgentModel(String agentModel) {
        this.agentModel = agentModel;
    }

    public BigDecimal getAgentRabate() {
        return agentRabate;
    }

    public void setAgentRabate(BigDecimal agentRabate) {
        this.agentRabate = agentRabate;
    }

    public String getRecommendedModel() {
        return recommendedModel;
    }

    public void setRecommendedModel(String recommendedModel) {
        this.recommendedModel = recommendedModel;
    }

    public BigDecimal getRecommendedRabate() {
        return recommendedRabate;
    }

    public void setRecommendedRabate(BigDecimal recommendedRabate) {
        this.recommendedRabate = recommendedRabate;
    }
}