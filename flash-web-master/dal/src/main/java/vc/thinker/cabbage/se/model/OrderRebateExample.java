package vc.thinker.cabbage.se.model;

import com.sinco.mybatis.dal.core.AbstractExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vc.thinker.cabbage.se.bo.OrderRebateBO;

public class OrderRebateExample extends AbstractExample<OrderRebateBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public OrderRebateExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public void setLimit(int count) {
        this.limit = String.valueOf(count);
    }

    public void setLimit(int offset, int rows) {
        this.limit = new StringBuilder().append(String.valueOf(offset)).append(",").append(String.valueOf(rows)).toString();
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Long value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Long value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Long value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Long value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Long> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Long> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Long value1, Long value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andPlantformModelIsNull() {
            addCriterion("plantform_model is null");
            return (Criteria) this;
        }

        public Criteria andPlantformModelIsNotNull() {
            addCriterion("plantform_model is not null");
            return (Criteria) this;
        }

        public Criteria andPlantformModelEqualTo(String value) {
            addCriterion("plantform_model =", value, "plantformModel");
            return (Criteria) this;
        }

        public Criteria andPlantformModelNotEqualTo(String value) {
            addCriterion("plantform_model <>", value, "plantformModel");
            return (Criteria) this;
        }

        public Criteria andPlantformModelGreaterThan(String value) {
            addCriterion("plantform_model >", value, "plantformModel");
            return (Criteria) this;
        }

        public Criteria andPlantformModelGreaterThanOrEqualTo(String value) {
            addCriterion("plantform_model >=", value, "plantformModel");
            return (Criteria) this;
        }

        public Criteria andPlantformModelLessThan(String value) {
            addCriterion("plantform_model <", value, "plantformModel");
            return (Criteria) this;
        }

        public Criteria andPlantformModelLessThanOrEqualTo(String value) {
            addCriterion("plantform_model <=", value, "plantformModel");
            return (Criteria) this;
        }

        public Criteria andPlantformModelLike(String value) {
            addCriterion("plantform_model like", value, "plantformModel");
            return (Criteria) this;
        }

        public Criteria andPlantformModelNotLike(String value) {
            addCriterion("plantform_model not like", value, "plantformModel");
            return (Criteria) this;
        }

        public Criteria andPlantformModelIn(List<String> values) {
            addCriterion("plantform_model in", values, "plantformModel");
            return (Criteria) this;
        }

        public Criteria andPlantformModelNotIn(List<String> values) {
            addCriterion("plantform_model not in", values, "plantformModel");
            return (Criteria) this;
        }

        public Criteria andPlantformModelBetween(String value1, String value2) {
            addCriterion("plantform_model between", value1, value2, "plantformModel");
            return (Criteria) this;
        }

        public Criteria andPlantformModelNotBetween(String value1, String value2) {
            addCriterion("plantform_model not between", value1, value2, "plantformModel");
            return (Criteria) this;
        }

        public Criteria andPlantformRebateIsNull() {
            addCriterion("plantform_rebate is null");
            return (Criteria) this;
        }

        public Criteria andPlantformRebateIsNotNull() {
            addCriterion("plantform_rebate is not null");
            return (Criteria) this;
        }

        public Criteria andPlantformRebateEqualTo(BigDecimal value) {
            addCriterion("plantform_rebate =", value, "plantformRebate");
            return (Criteria) this;
        }

        public Criteria andPlantformRebateNotEqualTo(BigDecimal value) {
            addCriterion("plantform_rebate <>", value, "plantformRebate");
            return (Criteria) this;
        }

        public Criteria andPlantformRebateGreaterThan(BigDecimal value) {
            addCriterion("plantform_rebate >", value, "plantformRebate");
            return (Criteria) this;
        }

        public Criteria andPlantformRebateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("plantform_rebate >=", value, "plantformRebate");
            return (Criteria) this;
        }

        public Criteria andPlantformRebateLessThan(BigDecimal value) {
            addCriterion("plantform_rebate <", value, "plantformRebate");
            return (Criteria) this;
        }

        public Criteria andPlantformRebateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("plantform_rebate <=", value, "plantformRebate");
            return (Criteria) this;
        }

        public Criteria andPlantformRebateIn(List<BigDecimal> values) {
            addCriterion("plantform_rebate in", values, "plantformRebate");
            return (Criteria) this;
        }

        public Criteria andPlantformRebateNotIn(List<BigDecimal> values) {
            addCriterion("plantform_rebate not in", values, "plantformRebate");
            return (Criteria) this;
        }

        public Criteria andPlantformRebateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plantform_rebate between", value1, value2, "plantformRebate");
            return (Criteria) this;
        }

        public Criteria andPlantformRebateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plantform_rebate not between", value1, value2, "plantformRebate");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerModelIsNull() {
            addCriterion("borrow_seller_model is null");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerModelIsNotNull() {
            addCriterion("borrow_seller_model is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerModelEqualTo(String value) {
            addCriterion("borrow_seller_model =", value, "borrowSellerModel");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerModelNotEqualTo(String value) {
            addCriterion("borrow_seller_model <>", value, "borrowSellerModel");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerModelGreaterThan(String value) {
            addCriterion("borrow_seller_model >", value, "borrowSellerModel");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerModelGreaterThanOrEqualTo(String value) {
            addCriterion("borrow_seller_model >=", value, "borrowSellerModel");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerModelLessThan(String value) {
            addCriterion("borrow_seller_model <", value, "borrowSellerModel");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerModelLessThanOrEqualTo(String value) {
            addCriterion("borrow_seller_model <=", value, "borrowSellerModel");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerModelLike(String value) {
            addCriterion("borrow_seller_model like", value, "borrowSellerModel");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerModelNotLike(String value) {
            addCriterion("borrow_seller_model not like", value, "borrowSellerModel");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerModelIn(List<String> values) {
            addCriterion("borrow_seller_model in", values, "borrowSellerModel");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerModelNotIn(List<String> values) {
            addCriterion("borrow_seller_model not in", values, "borrowSellerModel");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerModelBetween(String value1, String value2) {
            addCriterion("borrow_seller_model between", value1, value2, "borrowSellerModel");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerModelNotBetween(String value1, String value2) {
            addCriterion("borrow_seller_model not between", value1, value2, "borrowSellerModel");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerRebateIsNull() {
            addCriterion("borrow_seller_rebate is null");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerRebateIsNotNull() {
            addCriterion("borrow_seller_rebate is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerRebateEqualTo(BigDecimal value) {
            addCriterion("borrow_seller_rebate =", value, "borrowSellerRebate");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerRebateNotEqualTo(BigDecimal value) {
            addCriterion("borrow_seller_rebate <>", value, "borrowSellerRebate");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerRebateGreaterThan(BigDecimal value) {
            addCriterion("borrow_seller_rebate >", value, "borrowSellerRebate");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerRebateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("borrow_seller_rebate >=", value, "borrowSellerRebate");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerRebateLessThan(BigDecimal value) {
            addCriterion("borrow_seller_rebate <", value, "borrowSellerRebate");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerRebateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("borrow_seller_rebate <=", value, "borrowSellerRebate");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerRebateIn(List<BigDecimal> values) {
            addCriterion("borrow_seller_rebate in", values, "borrowSellerRebate");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerRebateNotIn(List<BigDecimal> values) {
            addCriterion("borrow_seller_rebate not in", values, "borrowSellerRebate");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerRebateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("borrow_seller_rebate between", value1, value2, "borrowSellerRebate");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerRebateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("borrow_seller_rebate not between", value1, value2, "borrowSellerRebate");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andReturnSellerModelIsNull() {
            addCriterion("return_seller_model is null");
            return (Criteria) this;
        }

        public Criteria andReturnSellerModelIsNotNull() {
            addCriterion("return_seller_model is not null");
            return (Criteria) this;
        }

        public Criteria andReturnSellerModelEqualTo(String value) {
            addCriterion("return_seller_model =", value, "returnSellerModel");
            return (Criteria) this;
        }

        public Criteria andReturnSellerModelNotEqualTo(String value) {
            addCriterion("return_seller_model <>", value, "returnSellerModel");
            return (Criteria) this;
        }

        public Criteria andReturnSellerModelGreaterThan(String value) {
            addCriterion("return_seller_model >", value, "returnSellerModel");
            return (Criteria) this;
        }

        public Criteria andReturnSellerModelGreaterThanOrEqualTo(String value) {
            addCriterion("return_seller_model >=", value, "returnSellerModel");
            return (Criteria) this;
        }

        public Criteria andReturnSellerModelLessThan(String value) {
            addCriterion("return_seller_model <", value, "returnSellerModel");
            return (Criteria) this;
        }

        public Criteria andReturnSellerModelLessThanOrEqualTo(String value) {
            addCriterion("return_seller_model <=", value, "returnSellerModel");
            return (Criteria) this;
        }

        public Criteria andReturnSellerModelLike(String value) {
            addCriterion("return_seller_model like", value, "returnSellerModel");
            return (Criteria) this;
        }

        public Criteria andReturnSellerModelNotLike(String value) {
            addCriterion("return_seller_model not like", value, "returnSellerModel");
            return (Criteria) this;
        }

        public Criteria andReturnSellerModelIn(List<String> values) {
            addCriterion("return_seller_model in", values, "returnSellerModel");
            return (Criteria) this;
        }

        public Criteria andReturnSellerModelNotIn(List<String> values) {
            addCriterion("return_seller_model not in", values, "returnSellerModel");
            return (Criteria) this;
        }

        public Criteria andReturnSellerModelBetween(String value1, String value2) {
            addCriterion("return_seller_model between", value1, value2, "returnSellerModel");
            return (Criteria) this;
        }

        public Criteria andReturnSellerModelNotBetween(String value1, String value2) {
            addCriterion("return_seller_model not between", value1, value2, "returnSellerModel");
            return (Criteria) this;
        }

        public Criteria andReturnSellerRabateIsNull() {
            addCriterion("return_seller_rabate is null");
            return (Criteria) this;
        }

        public Criteria andReturnSellerRabateIsNotNull() {
            addCriterion("return_seller_rabate is not null");
            return (Criteria) this;
        }

        public Criteria andReturnSellerRabateEqualTo(BigDecimal value) {
            addCriterion("return_seller_rabate =", value, "returnSellerRabate");
            return (Criteria) this;
        }

        public Criteria andReturnSellerRabateNotEqualTo(BigDecimal value) {
            addCriterion("return_seller_rabate <>", value, "returnSellerRabate");
            return (Criteria) this;
        }

        public Criteria andReturnSellerRabateGreaterThan(BigDecimal value) {
            addCriterion("return_seller_rabate >", value, "returnSellerRabate");
            return (Criteria) this;
        }

        public Criteria andReturnSellerRabateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("return_seller_rabate >=", value, "returnSellerRabate");
            return (Criteria) this;
        }

        public Criteria andReturnSellerRabateLessThan(BigDecimal value) {
            addCriterion("return_seller_rabate <", value, "returnSellerRabate");
            return (Criteria) this;
        }

        public Criteria andReturnSellerRabateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("return_seller_rabate <=", value, "returnSellerRabate");
            return (Criteria) this;
        }

        public Criteria andReturnSellerRabateIn(List<BigDecimal> values) {
            addCriterion("return_seller_rabate in", values, "returnSellerRabate");
            return (Criteria) this;
        }

        public Criteria andReturnSellerRabateNotIn(List<BigDecimal> values) {
            addCriterion("return_seller_rabate not in", values, "returnSellerRabate");
            return (Criteria) this;
        }

        public Criteria andReturnSellerRabateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_seller_rabate between", value1, value2, "returnSellerRabate");
            return (Criteria) this;
        }

        public Criteria andReturnSellerRabateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_seller_rabate not between", value1, value2, "returnSellerRabate");
            return (Criteria) this;
        }

        public Criteria andAgentRebateTypeIsNull() {
            addCriterion("agent_rebate_type is null");
            return (Criteria) this;
        }

        public Criteria andAgentRebateTypeIsNotNull() {
            addCriterion("agent_rebate_type is not null");
            return (Criteria) this;
        }

        public Criteria andAgentRebateTypeEqualTo(String value) {
            addCriterion("agent_rebate_type =", value, "agentRebateType");
            return (Criteria) this;
        }

        public Criteria andAgentRebateTypeNotEqualTo(String value) {
            addCriterion("agent_rebate_type <>", value, "agentRebateType");
            return (Criteria) this;
        }

        public Criteria andAgentRebateTypeGreaterThan(String value) {
            addCriterion("agent_rebate_type >", value, "agentRebateType");
            return (Criteria) this;
        }

        public Criteria andAgentRebateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("agent_rebate_type >=", value, "agentRebateType");
            return (Criteria) this;
        }

        public Criteria andAgentRebateTypeLessThan(String value) {
            addCriterion("agent_rebate_type <", value, "agentRebateType");
            return (Criteria) this;
        }

        public Criteria andAgentRebateTypeLessThanOrEqualTo(String value) {
            addCriterion("agent_rebate_type <=", value, "agentRebateType");
            return (Criteria) this;
        }

        public Criteria andAgentRebateTypeLike(String value) {
            addCriterion("agent_rebate_type like", value, "agentRebateType");
            return (Criteria) this;
        }

        public Criteria andAgentRebateTypeNotLike(String value) {
            addCriterion("agent_rebate_type not like", value, "agentRebateType");
            return (Criteria) this;
        }

        public Criteria andAgentRebateTypeIn(List<String> values) {
            addCriterion("agent_rebate_type in", values, "agentRebateType");
            return (Criteria) this;
        }

        public Criteria andAgentRebateTypeNotIn(List<String> values) {
            addCriterion("agent_rebate_type not in", values, "agentRebateType");
            return (Criteria) this;
        }

        public Criteria andAgentRebateTypeBetween(String value1, String value2) {
            addCriterion("agent_rebate_type between", value1, value2, "agentRebateType");
            return (Criteria) this;
        }

        public Criteria andAgentRebateTypeNotBetween(String value1, String value2) {
            addCriterion("agent_rebate_type not between", value1, value2, "agentRebateType");
            return (Criteria) this;
        }

        public Criteria andAgentModelIsNull() {
            addCriterion("agent_model is null");
            return (Criteria) this;
        }

        public Criteria andAgentModelIsNotNull() {
            addCriterion("agent_model is not null");
            return (Criteria) this;
        }

        public Criteria andAgentModelEqualTo(String value) {
            addCriterion("agent_model =", value, "agentModel");
            return (Criteria) this;
        }

        public Criteria andAgentModelNotEqualTo(String value) {
            addCriterion("agent_model <>", value, "agentModel");
            return (Criteria) this;
        }

        public Criteria andAgentModelGreaterThan(String value) {
            addCriterion("agent_model >", value, "agentModel");
            return (Criteria) this;
        }

        public Criteria andAgentModelGreaterThanOrEqualTo(String value) {
            addCriterion("agent_model >=", value, "agentModel");
            return (Criteria) this;
        }

        public Criteria andAgentModelLessThan(String value) {
            addCriterion("agent_model <", value, "agentModel");
            return (Criteria) this;
        }

        public Criteria andAgentModelLessThanOrEqualTo(String value) {
            addCriterion("agent_model <=", value, "agentModel");
            return (Criteria) this;
        }

        public Criteria andAgentModelLike(String value) {
            addCriterion("agent_model like", value, "agentModel");
            return (Criteria) this;
        }

        public Criteria andAgentModelNotLike(String value) {
            addCriterion("agent_model not like", value, "agentModel");
            return (Criteria) this;
        }

        public Criteria andAgentModelIn(List<String> values) {
            addCriterion("agent_model in", values, "agentModel");
            return (Criteria) this;
        }

        public Criteria andAgentModelNotIn(List<String> values) {
            addCriterion("agent_model not in", values, "agentModel");
            return (Criteria) this;
        }

        public Criteria andAgentModelBetween(String value1, String value2) {
            addCriterion("agent_model between", value1, value2, "agentModel");
            return (Criteria) this;
        }

        public Criteria andAgentModelNotBetween(String value1, String value2) {
            addCriterion("agent_model not between", value1, value2, "agentModel");
            return (Criteria) this;
        }

        public Criteria andAgentRabateIsNull() {
            addCriterion("agent_rabate is null");
            return (Criteria) this;
        }

        public Criteria andAgentRabateIsNotNull() {
            addCriterion("agent_rabate is not null");
            return (Criteria) this;
        }

        public Criteria andAgentRabateEqualTo(BigDecimal value) {
            addCriterion("agent_rabate =", value, "agentRabate");
            return (Criteria) this;
        }

        public Criteria andAgentRabateNotEqualTo(BigDecimal value) {
            addCriterion("agent_rabate <>", value, "agentRabate");
            return (Criteria) this;
        }

        public Criteria andAgentRabateGreaterThan(BigDecimal value) {
            addCriterion("agent_rabate >", value, "agentRabate");
            return (Criteria) this;
        }

        public Criteria andAgentRabateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("agent_rabate >=", value, "agentRabate");
            return (Criteria) this;
        }

        public Criteria andAgentRabateLessThan(BigDecimal value) {
            addCriterion("agent_rabate <", value, "agentRabate");
            return (Criteria) this;
        }

        public Criteria andAgentRabateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("agent_rabate <=", value, "agentRabate");
            return (Criteria) this;
        }

        public Criteria andAgentRabateIn(List<BigDecimal> values) {
            addCriterion("agent_rabate in", values, "agentRabate");
            return (Criteria) this;
        }

        public Criteria andAgentRabateNotIn(List<BigDecimal> values) {
            addCriterion("agent_rabate not in", values, "agentRabate");
            return (Criteria) this;
        }

        public Criteria andAgentRabateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agent_rabate between", value1, value2, "agentRabate");
            return (Criteria) this;
        }

        public Criteria andAgentRabateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agent_rabate not between", value1, value2, "agentRabate");
            return (Criteria) this;
        }

        public Criteria andRecommendedModelIsNull() {
            addCriterion("recommended_model is null");
            return (Criteria) this;
        }

        public Criteria andRecommendedModelIsNotNull() {
            addCriterion("recommended_model is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendedModelEqualTo(String value) {
            addCriterion("recommended_model =", value, "recommendedModel");
            return (Criteria) this;
        }

        public Criteria andRecommendedModelNotEqualTo(String value) {
            addCriterion("recommended_model <>", value, "recommendedModel");
            return (Criteria) this;
        }

        public Criteria andRecommendedModelGreaterThan(String value) {
            addCriterion("recommended_model >", value, "recommendedModel");
            return (Criteria) this;
        }

        public Criteria andRecommendedModelGreaterThanOrEqualTo(String value) {
            addCriterion("recommended_model >=", value, "recommendedModel");
            return (Criteria) this;
        }

        public Criteria andRecommendedModelLessThan(String value) {
            addCriterion("recommended_model <", value, "recommendedModel");
            return (Criteria) this;
        }

        public Criteria andRecommendedModelLessThanOrEqualTo(String value) {
            addCriterion("recommended_model <=", value, "recommendedModel");
            return (Criteria) this;
        }

        public Criteria andRecommendedModelLike(String value) {
            addCriterion("recommended_model like", value, "recommendedModel");
            return (Criteria) this;
        }

        public Criteria andRecommendedModelNotLike(String value) {
            addCriterion("recommended_model not like", value, "recommendedModel");
            return (Criteria) this;
        }

        public Criteria andRecommendedModelIn(List<String> values) {
            addCriterion("recommended_model in", values, "recommendedModel");
            return (Criteria) this;
        }

        public Criteria andRecommendedModelNotIn(List<String> values) {
            addCriterion("recommended_model not in", values, "recommendedModel");
            return (Criteria) this;
        }

        public Criteria andRecommendedModelBetween(String value1, String value2) {
            addCriterion("recommended_model between", value1, value2, "recommendedModel");
            return (Criteria) this;
        }

        public Criteria andRecommendedModelNotBetween(String value1, String value2) {
            addCriterion("recommended_model not between", value1, value2, "recommendedModel");
            return (Criteria) this;
        }

        public Criteria andRecommendedRabateIsNull() {
            addCriterion("recommended_rabate is null");
            return (Criteria) this;
        }

        public Criteria andRecommendedRabateIsNotNull() {
            addCriterion("recommended_rabate is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendedRabateEqualTo(BigDecimal value) {
            addCriterion("recommended_rabate =", value, "recommendedRabate");
            return (Criteria) this;
        }

        public Criteria andRecommendedRabateNotEqualTo(BigDecimal value) {
            addCriterion("recommended_rabate <>", value, "recommendedRabate");
            return (Criteria) this;
        }

        public Criteria andRecommendedRabateGreaterThan(BigDecimal value) {
            addCriterion("recommended_rabate >", value, "recommendedRabate");
            return (Criteria) this;
        }

        public Criteria andRecommendedRabateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("recommended_rabate >=", value, "recommendedRabate");
            return (Criteria) this;
        }

        public Criteria andRecommendedRabateLessThan(BigDecimal value) {
            addCriterion("recommended_rabate <", value, "recommendedRabate");
            return (Criteria) this;
        }

        public Criteria andRecommendedRabateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("recommended_rabate <=", value, "recommendedRabate");
            return (Criteria) this;
        }

        public Criteria andRecommendedRabateIn(List<BigDecimal> values) {
            addCriterion("recommended_rabate in", values, "recommendedRabate");
            return (Criteria) this;
        }

        public Criteria andRecommendedRabateNotIn(List<BigDecimal> values) {
            addCriterion("recommended_rabate not in", values, "recommendedRabate");
            return (Criteria) this;
        }

        public Criteria andRecommendedRabateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("recommended_rabate between", value1, value2, "recommendedRabate");
            return (Criteria) this;
        }

        public Criteria andRecommendedRabateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("recommended_rabate not between", value1, value2, "recommendedRabate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}