package vc.thinker.cabbage.se.model;

import com.sinco.mybatis.dal.core.AbstractExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vc.thinker.cabbage.se.bo.UserRebateLogBO;

public class UserRebateLogExample extends AbstractExample<UserRebateLogBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public UserRebateLogExample() {
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

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Long value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Long value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Long value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Long value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Long value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Long value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Long> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Long> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Long value1, Long value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Long value1, Long value2) {
            addCriterion("uid not between", value1, value2, "uid");
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

        public Criteria andRebateModelIsNull() {
            addCriterion("rebate_model is null");
            return (Criteria) this;
        }

        public Criteria andRebateModelIsNotNull() {
            addCriterion("rebate_model is not null");
            return (Criteria) this;
        }

        public Criteria andRebateModelEqualTo(String value) {
            addCriterion("rebate_model =", value, "rebateModel");
            return (Criteria) this;
        }

        public Criteria andRebateModelNotEqualTo(String value) {
            addCriterion("rebate_model <>", value, "rebateModel");
            return (Criteria) this;
        }

        public Criteria andRebateModelGreaterThan(String value) {
            addCriterion("rebate_model >", value, "rebateModel");
            return (Criteria) this;
        }

        public Criteria andRebateModelGreaterThanOrEqualTo(String value) {
            addCriterion("rebate_model >=", value, "rebateModel");
            return (Criteria) this;
        }

        public Criteria andRebateModelLessThan(String value) {
            addCriterion("rebate_model <", value, "rebateModel");
            return (Criteria) this;
        }

        public Criteria andRebateModelLessThanOrEqualTo(String value) {
            addCriterion("rebate_model <=", value, "rebateModel");
            return (Criteria) this;
        }

        public Criteria andRebateModelLike(String value) {
            addCriterion("rebate_model like", value, "rebateModel");
            return (Criteria) this;
        }

        public Criteria andRebateModelNotLike(String value) {
            addCriterion("rebate_model not like", value, "rebateModel");
            return (Criteria) this;
        }

        public Criteria andRebateModelIn(List<String> values) {
            addCriterion("rebate_model in", values, "rebateModel");
            return (Criteria) this;
        }

        public Criteria andRebateModelNotIn(List<String> values) {
            addCriterion("rebate_model not in", values, "rebateModel");
            return (Criteria) this;
        }

        public Criteria andRebateModelBetween(String value1, String value2) {
            addCriterion("rebate_model between", value1, value2, "rebateModel");
            return (Criteria) this;
        }

        public Criteria andRebateModelNotBetween(String value1, String value2) {
            addCriterion("rebate_model not between", value1, value2, "rebateModel");
            return (Criteria) this;
        }

        public Criteria andRebateRateIsNull() {
            addCriterion("rebate_rate is null");
            return (Criteria) this;
        }

        public Criteria andRebateRateIsNotNull() {
            addCriterion("rebate_rate is not null");
            return (Criteria) this;
        }

        public Criteria andRebateRateEqualTo(BigDecimal value) {
            addCriterion("rebate_rate =", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateNotEqualTo(BigDecimal value) {
            addCriterion("rebate_rate <>", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateGreaterThan(BigDecimal value) {
            addCriterion("rebate_rate >", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rebate_rate >=", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateLessThan(BigDecimal value) {
            addCriterion("rebate_rate <", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rebate_rate <=", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateIn(List<BigDecimal> values) {
            addCriterion("rebate_rate in", values, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateNotIn(List<BigDecimal> values) {
            addCriterion("rebate_rate not in", values, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebate_rate between", value1, value2, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebate_rate not between", value1, value2, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRaxRateIsNull() {
            addCriterion("rax_rate is null");
            return (Criteria) this;
        }

        public Criteria andRaxRateIsNotNull() {
            addCriterion("rax_rate is not null");
            return (Criteria) this;
        }

        public Criteria andRaxRateEqualTo(BigDecimal value) {
            addCriterion("rax_rate =", value, "raxRate");
            return (Criteria) this;
        }

        public Criteria andRaxRateNotEqualTo(BigDecimal value) {
            addCriterion("rax_rate <>", value, "raxRate");
            return (Criteria) this;
        }

        public Criteria andRaxRateGreaterThan(BigDecimal value) {
            addCriterion("rax_rate >", value, "raxRate");
            return (Criteria) this;
        }

        public Criteria andRaxRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rax_rate >=", value, "raxRate");
            return (Criteria) this;
        }

        public Criteria andRaxRateLessThan(BigDecimal value) {
            addCriterion("rax_rate <", value, "raxRate");
            return (Criteria) this;
        }

        public Criteria andRaxRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rax_rate <=", value, "raxRate");
            return (Criteria) this;
        }

        public Criteria andRaxRateIn(List<BigDecimal> values) {
            addCriterion("rax_rate in", values, "raxRate");
            return (Criteria) this;
        }

        public Criteria andRaxRateNotIn(List<BigDecimal> values) {
            addCriterion("rax_rate not in", values, "raxRate");
            return (Criteria) this;
        }

        public Criteria andRaxRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rax_rate between", value1, value2, "raxRate");
            return (Criteria) this;
        }

        public Criteria andRaxRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rax_rate not between", value1, value2, "raxRate");
            return (Criteria) this;
        }

        public Criteria andRebateAmountIsNull() {
            addCriterion("rebate_amount is null");
            return (Criteria) this;
        }

        public Criteria andRebateAmountIsNotNull() {
            addCriterion("rebate_amount is not null");
            return (Criteria) this;
        }

        public Criteria andRebateAmountEqualTo(BigDecimal value) {
            addCriterion("rebate_amount =", value, "rebateAmount");
            return (Criteria) this;
        }

        public Criteria andRebateAmountNotEqualTo(BigDecimal value) {
            addCriterion("rebate_amount <>", value, "rebateAmount");
            return (Criteria) this;
        }

        public Criteria andRebateAmountGreaterThan(BigDecimal value) {
            addCriterion("rebate_amount >", value, "rebateAmount");
            return (Criteria) this;
        }

        public Criteria andRebateAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rebate_amount >=", value, "rebateAmount");
            return (Criteria) this;
        }

        public Criteria andRebateAmountLessThan(BigDecimal value) {
            addCriterion("rebate_amount <", value, "rebateAmount");
            return (Criteria) this;
        }

        public Criteria andRebateAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rebate_amount <=", value, "rebateAmount");
            return (Criteria) this;
        }

        public Criteria andRebateAmountIn(List<BigDecimal> values) {
            addCriterion("rebate_amount in", values, "rebateAmount");
            return (Criteria) this;
        }

        public Criteria andRebateAmountNotIn(List<BigDecimal> values) {
            addCriterion("rebate_amount not in", values, "rebateAmount");
            return (Criteria) this;
        }

        public Criteria andRebateAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebate_amount between", value1, value2, "rebateAmount");
            return (Criteria) this;
        }

        public Criteria andRebateAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebate_amount not between", value1, value2, "rebateAmount");
            return (Criteria) this;
        }

        public Criteria andSendStatusIsNull() {
            addCriterion("send_status is null");
            return (Criteria) this;
        }

        public Criteria andSendStatusIsNotNull() {
            addCriterion("send_status is not null");
            return (Criteria) this;
        }

        public Criteria andSendStatusEqualTo(Boolean value) {
            addCriterion("send_status =", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotEqualTo(Boolean value) {
            addCriterion("send_status <>", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusGreaterThan(Boolean value) {
            addCriterion("send_status >", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("send_status >=", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusLessThan(Boolean value) {
            addCriterion("send_status <", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("send_status <=", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusIn(List<Boolean> values) {
            addCriterion("send_status in", values, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotIn(List<Boolean> values) {
            addCriterion("send_status not in", values, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("send_status between", value1, value2, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("send_status not between", value1, value2, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("send_time is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("send_time is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Date value) {
            addCriterion("send_time =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterion("send_time <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterion("send_time >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("send_time >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Date value) {
            addCriterion("send_time <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("send_time <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Date> values) {
            addCriterion("send_time in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterion("send_time not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterion("send_time between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("send_time not between", value1, value2, "sendTime");
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

        public Criteria andOrderCodeIsNull() {
            addCriterion("order_code is null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNotNull() {
            addCriterion("order_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeEqualTo(String value) {
            addCriterion("order_code =", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotEqualTo(String value) {
            addCriterion("order_code <>", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThan(String value) {
            addCriterion("order_code >", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("order_code >=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThan(String value) {
            addCriterion("order_code <", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("order_code <=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLike(String value) {
            addCriterion("order_code like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotLike(String value) {
            addCriterion("order_code not like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIn(List<String> values) {
            addCriterion("order_code in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotIn(List<String> values) {
            addCriterion("order_code not in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeBetween(String value1, String value2) {
            addCriterion("order_code between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotBetween(String value1, String value2) {
            addCriterion("order_code not between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNull() {
            addCriterion("currency is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNotNull() {
            addCriterion("currency is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyEqualTo(String value) {
            addCriterion("currency =", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotEqualTo(String value) {
            addCriterion("currency <>", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThan(String value) {
            addCriterion("currency >", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("currency >=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThan(String value) {
            addCriterion("currency <", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThanOrEqualTo(String value) {
            addCriterion("currency <=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLike(String value) {
            addCriterion("currency like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotLike(String value) {
            addCriterion("currency not like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyIn(List<String> values) {
            addCriterion("currency in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotIn(List<String> values) {
            addCriterion("currency not in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyBetween(String value1, String value2) {
            addCriterion("currency between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotBetween(String value1, String value2) {
            addCriterion("currency not between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andRebateTypeIsNull() {
            addCriterion("rebate_type is null");
            return (Criteria) this;
        }

        public Criteria andRebateTypeIsNotNull() {
            addCriterion("rebate_type is not null");
            return (Criteria) this;
        }

        public Criteria andRebateTypeEqualTo(String value) {
            addCriterion("rebate_type =", value, "rebateType");
            return (Criteria) this;
        }

        public Criteria andRebateTypeNotEqualTo(String value) {
            addCriterion("rebate_type <>", value, "rebateType");
            return (Criteria) this;
        }

        public Criteria andRebateTypeGreaterThan(String value) {
            addCriterion("rebate_type >", value, "rebateType");
            return (Criteria) this;
        }

        public Criteria andRebateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("rebate_type >=", value, "rebateType");
            return (Criteria) this;
        }

        public Criteria andRebateTypeLessThan(String value) {
            addCriterion("rebate_type <", value, "rebateType");
            return (Criteria) this;
        }

        public Criteria andRebateTypeLessThanOrEqualTo(String value) {
            addCriterion("rebate_type <=", value, "rebateType");
            return (Criteria) this;
        }

        public Criteria andRebateTypeLike(String value) {
            addCriterion("rebate_type like", value, "rebateType");
            return (Criteria) this;
        }

        public Criteria andRebateTypeNotLike(String value) {
            addCriterion("rebate_type not like", value, "rebateType");
            return (Criteria) this;
        }

        public Criteria andRebateTypeIn(List<String> values) {
            addCriterion("rebate_type in", values, "rebateType");
            return (Criteria) this;
        }

        public Criteria andRebateTypeNotIn(List<String> values) {
            addCriterion("rebate_type not in", values, "rebateType");
            return (Criteria) this;
        }

        public Criteria andRebateTypeBetween(String value1, String value2) {
            addCriterion("rebate_type between", value1, value2, "rebateType");
            return (Criteria) this;
        }

        public Criteria andRebateTypeNotBetween(String value1, String value2) {
            addCriterion("rebate_type not between", value1, value2, "rebateType");
            return (Criteria) this;
        }

        public Criteria andPayUidIsNull() {
            addCriterion("pay_uid is null");
            return (Criteria) this;
        }

        public Criteria andPayUidIsNotNull() {
            addCriterion("pay_uid is not null");
            return (Criteria) this;
        }

        public Criteria andPayUidEqualTo(Long value) {
            addCriterion("pay_uid =", value, "payUid");
            return (Criteria) this;
        }

        public Criteria andPayUidNotEqualTo(Long value) {
            addCriterion("pay_uid <>", value, "payUid");
            return (Criteria) this;
        }

        public Criteria andPayUidGreaterThan(Long value) {
            addCriterion("pay_uid >", value, "payUid");
            return (Criteria) this;
        }

        public Criteria andPayUidGreaterThanOrEqualTo(Long value) {
            addCriterion("pay_uid >=", value, "payUid");
            return (Criteria) this;
        }

        public Criteria andPayUidLessThan(Long value) {
            addCriterion("pay_uid <", value, "payUid");
            return (Criteria) this;
        }

        public Criteria andPayUidLessThanOrEqualTo(Long value) {
            addCriterion("pay_uid <=", value, "payUid");
            return (Criteria) this;
        }

        public Criteria andPayUidIn(List<Long> values) {
            addCriterion("pay_uid in", values, "payUid");
            return (Criteria) this;
        }

        public Criteria andPayUidNotIn(List<Long> values) {
            addCriterion("pay_uid not in", values, "payUid");
            return (Criteria) this;
        }

        public Criteria andPayUidBetween(Long value1, Long value2) {
            addCriterion("pay_uid between", value1, value2, "payUid");
            return (Criteria) this;
        }

        public Criteria andPayUidNotBetween(Long value1, Long value2) {
            addCriterion("pay_uid not between", value1, value2, "payUid");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNull() {
            addCriterion("user_type is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNotNull() {
            addCriterion("user_type is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeEqualTo(String value) {
            addCriterion("user_type =", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotEqualTo(String value) {
            addCriterion("user_type <>", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThan(String value) {
            addCriterion("user_type >", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThanOrEqualTo(String value) {
            addCriterion("user_type >=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThan(String value) {
            addCriterion("user_type <", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThanOrEqualTo(String value) {
            addCriterion("user_type <=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLike(String value) {
            addCriterion("user_type like", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotLike(String value) {
            addCriterion("user_type not like", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeIn(List<String> values) {
            addCriterion("user_type in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotIn(List<String> values) {
            addCriterion("user_type not in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeBetween(String value1, String value2) {
            addCriterion("user_type between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotBetween(String value1, String value2) {
            addCriterion("user_type not between", value1, value2, "userType");
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