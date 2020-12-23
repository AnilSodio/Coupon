package vc.thinker.cabbage.se.model;

import com.sinco.mybatis.dal.core.AbstractExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vc.thinker.cabbage.se.bo.OrderPbBuyBO;

public class OrderPbBuyExample extends AbstractExample<OrderPbBuyBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public OrderPbBuyExample() {
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

        public Criteria andPbIdIsNull() {
            addCriterion("pb_id is null");
            return (Criteria) this;
        }

        public Criteria andPbIdIsNotNull() {
            addCriterion("pb_id is not null");
            return (Criteria) this;
        }

        public Criteria andPbIdEqualTo(Long value) {
            addCriterion("pb_id =", value, "pbId");
            return (Criteria) this;
        }

        public Criteria andPbIdNotEqualTo(Long value) {
            addCriterion("pb_id <>", value, "pbId");
            return (Criteria) this;
        }

        public Criteria andPbIdGreaterThan(Long value) {
            addCriterion("pb_id >", value, "pbId");
            return (Criteria) this;
        }

        public Criteria andPbIdGreaterThanOrEqualTo(Long value) {
            addCriterion("pb_id >=", value, "pbId");
            return (Criteria) this;
        }

        public Criteria andPbIdLessThan(Long value) {
            addCriterion("pb_id <", value, "pbId");
            return (Criteria) this;
        }

        public Criteria andPbIdLessThanOrEqualTo(Long value) {
            addCriterion("pb_id <=", value, "pbId");
            return (Criteria) this;
        }

        public Criteria andPbIdIn(List<Long> values) {
            addCriterion("pb_id in", values, "pbId");
            return (Criteria) this;
        }

        public Criteria andPbIdNotIn(List<Long> values) {
            addCriterion("pb_id not in", values, "pbId");
            return (Criteria) this;
        }

        public Criteria andPbIdBetween(Long value1, Long value2) {
            addCriterion("pb_id between", value1, value2, "pbId");
            return (Criteria) this;
        }

        public Criteria andPbIdNotBetween(Long value1, Long value2) {
            addCriterion("pb_id not between", value1, value2, "pbId");
            return (Criteria) this;
        }

        public Criteria andPbCodeIsNull() {
            addCriterion("pb_code is null");
            return (Criteria) this;
        }

        public Criteria andPbCodeIsNotNull() {
            addCriterion("pb_code is not null");
            return (Criteria) this;
        }

        public Criteria andPbCodeEqualTo(String value) {
            addCriterion("pb_code =", value, "pbCode");
            return (Criteria) this;
        }

        public Criteria andPbCodeNotEqualTo(String value) {
            addCriterion("pb_code <>", value, "pbCode");
            return (Criteria) this;
        }

        public Criteria andPbCodeGreaterThan(String value) {
            addCriterion("pb_code >", value, "pbCode");
            return (Criteria) this;
        }

        public Criteria andPbCodeGreaterThanOrEqualTo(String value) {
            addCriterion("pb_code >=", value, "pbCode");
            return (Criteria) this;
        }

        public Criteria andPbCodeLessThan(String value) {
            addCriterion("pb_code <", value, "pbCode");
            return (Criteria) this;
        }

        public Criteria andPbCodeLessThanOrEqualTo(String value) {
            addCriterion("pb_code <=", value, "pbCode");
            return (Criteria) this;
        }

        public Criteria andPbCodeLike(String value) {
            addCriterion("pb_code like", value, "pbCode");
            return (Criteria) this;
        }

        public Criteria andPbCodeNotLike(String value) {
            addCriterion("pb_code not like", value, "pbCode");
            return (Criteria) this;
        }

        public Criteria andPbCodeIn(List<String> values) {
            addCriterion("pb_code in", values, "pbCode");
            return (Criteria) this;
        }

        public Criteria andPbCodeNotIn(List<String> values) {
            addCriterion("pb_code not in", values, "pbCode");
            return (Criteria) this;
        }

        public Criteria andPbCodeBetween(String value1, String value2) {
            addCriterion("pb_code between", value1, value2, "pbCode");
            return (Criteria) this;
        }

        public Criteria andPbCodeNotBetween(String value1, String value2) {
            addCriterion("pb_code not between", value1, value2, "pbCode");
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

        public Criteria andOrderAmountIsNull() {
            addCriterion("order_amount is null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIsNotNull() {
            addCriterion("order_amount is not null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountEqualTo(BigDecimal value) {
            addCriterion("order_amount =", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotEqualTo(BigDecimal value) {
            addCriterion("order_amount <>", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountGreaterThan(BigDecimal value) {
            addCriterion("order_amount >", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("order_amount >=", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountLessThan(BigDecimal value) {
            addCriterion("order_amount <", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("order_amount <=", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIn(List<BigDecimal> values) {
            addCriterion("order_amount in", values, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotIn(List<BigDecimal> values) {
            addCriterion("order_amount not in", values, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_amount between", value1, value2, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_amount not between", value1, value2, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andCashFeeIsNull() {
            addCriterion("cash_fee is null");
            return (Criteria) this;
        }

        public Criteria andCashFeeIsNotNull() {
            addCriterion("cash_fee is not null");
            return (Criteria) this;
        }

        public Criteria andCashFeeEqualTo(BigDecimal value) {
            addCriterion("cash_fee =", value, "cashFee");
            return (Criteria) this;
        }

        public Criteria andCashFeeNotEqualTo(BigDecimal value) {
            addCriterion("cash_fee <>", value, "cashFee");
            return (Criteria) this;
        }

        public Criteria andCashFeeGreaterThan(BigDecimal value) {
            addCriterion("cash_fee >", value, "cashFee");
            return (Criteria) this;
        }

        public Criteria andCashFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cash_fee >=", value, "cashFee");
            return (Criteria) this;
        }

        public Criteria andCashFeeLessThan(BigDecimal value) {
            addCriterion("cash_fee <", value, "cashFee");
            return (Criteria) this;
        }

        public Criteria andCashFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cash_fee <=", value, "cashFee");
            return (Criteria) this;
        }

        public Criteria andCashFeeIn(List<BigDecimal> values) {
            addCriterion("cash_fee in", values, "cashFee");
            return (Criteria) this;
        }

        public Criteria andCashFeeNotIn(List<BigDecimal> values) {
            addCriterion("cash_fee not in", values, "cashFee");
            return (Criteria) this;
        }

        public Criteria andCashFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cash_fee between", value1, value2, "cashFee");
            return (Criteria) this;
        }

        public Criteria andCashFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cash_fee not between", value1, value2, "cashFee");
            return (Criteria) this;
        }

        public Criteria andPbAmountIsNull() {
            addCriterion("pb_amount is null");
            return (Criteria) this;
        }

        public Criteria andPbAmountIsNotNull() {
            addCriterion("pb_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPbAmountEqualTo(BigDecimal value) {
            addCriterion("pb_amount =", value, "pbAmount");
            return (Criteria) this;
        }

        public Criteria andPbAmountNotEqualTo(BigDecimal value) {
            addCriterion("pb_amount <>", value, "pbAmount");
            return (Criteria) this;
        }

        public Criteria andPbAmountGreaterThan(BigDecimal value) {
            addCriterion("pb_amount >", value, "pbAmount");
            return (Criteria) this;
        }

        public Criteria andPbAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pb_amount >=", value, "pbAmount");
            return (Criteria) this;
        }

        public Criteria andPbAmountLessThan(BigDecimal value) {
            addCriterion("pb_amount <", value, "pbAmount");
            return (Criteria) this;
        }

        public Criteria andPbAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pb_amount <=", value, "pbAmount");
            return (Criteria) this;
        }

        public Criteria andPbAmountIn(List<BigDecimal> values) {
            addCriterion("pb_amount in", values, "pbAmount");
            return (Criteria) this;
        }

        public Criteria andPbAmountNotIn(List<BigDecimal> values) {
            addCriterion("pb_amount not in", values, "pbAmount");
            return (Criteria) this;
        }

        public Criteria andPbAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pb_amount between", value1, value2, "pbAmount");
            return (Criteria) this;
        }

        public Criteria andPbAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pb_amount not between", value1, value2, "pbAmount");
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

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andPayOrderCodeIsNull() {
            addCriterion("pay_order_code is null");
            return (Criteria) this;
        }

        public Criteria andPayOrderCodeIsNotNull() {
            addCriterion("pay_order_code is not null");
            return (Criteria) this;
        }

        public Criteria andPayOrderCodeEqualTo(String value) {
            addCriterion("pay_order_code =", value, "payOrderCode");
            return (Criteria) this;
        }

        public Criteria andPayOrderCodeNotEqualTo(String value) {
            addCriterion("pay_order_code <>", value, "payOrderCode");
            return (Criteria) this;
        }

        public Criteria andPayOrderCodeGreaterThan(String value) {
            addCriterion("pay_order_code >", value, "payOrderCode");
            return (Criteria) this;
        }

        public Criteria andPayOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("pay_order_code >=", value, "payOrderCode");
            return (Criteria) this;
        }

        public Criteria andPayOrderCodeLessThan(String value) {
            addCriterion("pay_order_code <", value, "payOrderCode");
            return (Criteria) this;
        }

        public Criteria andPayOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("pay_order_code <=", value, "payOrderCode");
            return (Criteria) this;
        }

        public Criteria andPayOrderCodeLike(String value) {
            addCriterion("pay_order_code like", value, "payOrderCode");
            return (Criteria) this;
        }

        public Criteria andPayOrderCodeNotLike(String value) {
            addCriterion("pay_order_code not like", value, "payOrderCode");
            return (Criteria) this;
        }

        public Criteria andPayOrderCodeIn(List<String> values) {
            addCriterion("pay_order_code in", values, "payOrderCode");
            return (Criteria) this;
        }

        public Criteria andPayOrderCodeNotIn(List<String> values) {
            addCriterion("pay_order_code not in", values, "payOrderCode");
            return (Criteria) this;
        }

        public Criteria andPayOrderCodeBetween(String value1, String value2) {
            addCriterion("pay_order_code between", value1, value2, "payOrderCode");
            return (Criteria) this;
        }

        public Criteria andPayOrderCodeNotBetween(String value1, String value2) {
            addCriterion("pay_order_code not between", value1, value2, "payOrderCode");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdIsNull() {
            addCriterion("out_order_id is null");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdIsNotNull() {
            addCriterion("out_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdEqualTo(String value) {
            addCriterion("out_order_id =", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdNotEqualTo(String value) {
            addCriterion("out_order_id <>", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdGreaterThan(String value) {
            addCriterion("out_order_id >", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("out_order_id >=", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdLessThan(String value) {
            addCriterion("out_order_id <", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdLessThanOrEqualTo(String value) {
            addCriterion("out_order_id <=", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdLike(String value) {
            addCriterion("out_order_id like", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdNotLike(String value) {
            addCriterion("out_order_id not like", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdIn(List<String> values) {
            addCriterion("out_order_id in", values, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdNotIn(List<String> values) {
            addCriterion("out_order_id not in", values, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdBetween(String value1, String value2) {
            addCriterion("out_order_id between", value1, value2, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdNotBetween(String value1, String value2) {
            addCriterion("out_order_id not between", value1, value2, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andPaymentMarkIsNull() {
            addCriterion("payment_mark is null");
            return (Criteria) this;
        }

        public Criteria andPaymentMarkIsNotNull() {
            addCriterion("payment_mark is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentMarkEqualTo(String value) {
            addCriterion("payment_mark =", value, "paymentMark");
            return (Criteria) this;
        }

        public Criteria andPaymentMarkNotEqualTo(String value) {
            addCriterion("payment_mark <>", value, "paymentMark");
            return (Criteria) this;
        }

        public Criteria andPaymentMarkGreaterThan(String value) {
            addCriterion("payment_mark >", value, "paymentMark");
            return (Criteria) this;
        }

        public Criteria andPaymentMarkGreaterThanOrEqualTo(String value) {
            addCriterion("payment_mark >=", value, "paymentMark");
            return (Criteria) this;
        }

        public Criteria andPaymentMarkLessThan(String value) {
            addCriterion("payment_mark <", value, "paymentMark");
            return (Criteria) this;
        }

        public Criteria andPaymentMarkLessThanOrEqualTo(String value) {
            addCriterion("payment_mark <=", value, "paymentMark");
            return (Criteria) this;
        }

        public Criteria andPaymentMarkLike(String value) {
            addCriterion("payment_mark like", value, "paymentMark");
            return (Criteria) this;
        }

        public Criteria andPaymentMarkNotLike(String value) {
            addCriterion("payment_mark not like", value, "paymentMark");
            return (Criteria) this;
        }

        public Criteria andPaymentMarkIn(List<String> values) {
            addCriterion("payment_mark in", values, "paymentMark");
            return (Criteria) this;
        }

        public Criteria andPaymentMarkNotIn(List<String> values) {
            addCriterion("payment_mark not in", values, "paymentMark");
            return (Criteria) this;
        }

        public Criteria andPaymentMarkBetween(String value1, String value2) {
            addCriterion("payment_mark between", value1, value2, "paymentMark");
            return (Criteria) this;
        }

        public Criteria andPaymentMarkNotBetween(String value1, String value2) {
            addCriterion("payment_mark not between", value1, value2, "paymentMark");
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