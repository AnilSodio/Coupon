package vc.thinker.cabbage.user.model;

import com.sinco.mybatis.dal.core.AbstractExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vc.thinker.cabbage.user.bo.VipPayLogBO;

public class VipPayLogExample extends AbstractExample<VipPayLogBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public VipPayLogExample() {
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

        public Criteria andSnIsNull() {
            addCriterion("sn is null");
            return (Criteria) this;
        }

        public Criteria andSnIsNotNull() {
            addCriterion("sn is not null");
            return (Criteria) this;
        }

        public Criteria andSnEqualTo(String value) {
            addCriterion("sn =", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotEqualTo(String value) {
            addCriterion("sn <>", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnGreaterThan(String value) {
            addCriterion("sn >", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnGreaterThanOrEqualTo(String value) {
            addCriterion("sn >=", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLessThan(String value) {
            addCriterion("sn <", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLessThanOrEqualTo(String value) {
            addCriterion("sn <=", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLike(String value) {
            addCriterion("sn like", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotLike(String value) {
            addCriterion("sn not like", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnIn(List<String> values) {
            addCriterion("sn in", values, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotIn(List<String> values) {
            addCriterion("sn not in", values, "sn");
            return (Criteria) this;
        }

        public Criteria andSnBetween(String value1, String value2) {
            addCriterion("sn between", value1, value2, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotBetween(String value1, String value2) {
            addCriterion("sn not between", value1, value2, "sn");
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

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andVipDiscountIsNull() {
            addCriterion("vip_discount is null");
            return (Criteria) this;
        }

        public Criteria andVipDiscountIsNotNull() {
            addCriterion("vip_discount is not null");
            return (Criteria) this;
        }

        public Criteria andVipDiscountEqualTo(Double value) {
            addCriterion("vip_discount =", value, "vipDiscount");
            return (Criteria) this;
        }

        public Criteria andVipDiscountNotEqualTo(Double value) {
            addCriterion("vip_discount <>", value, "vipDiscount");
            return (Criteria) this;
        }

        public Criteria andVipDiscountGreaterThan(Double value) {
            addCriterion("vip_discount >", value, "vipDiscount");
            return (Criteria) this;
        }

        public Criteria andVipDiscountGreaterThanOrEqualTo(Double value) {
            addCriterion("vip_discount >=", value, "vipDiscount");
            return (Criteria) this;
        }

        public Criteria andVipDiscountLessThan(Double value) {
            addCriterion("vip_discount <", value, "vipDiscount");
            return (Criteria) this;
        }

        public Criteria andVipDiscountLessThanOrEqualTo(Double value) {
            addCriterion("vip_discount <=", value, "vipDiscount");
            return (Criteria) this;
        }

        public Criteria andVipDiscountIn(List<Double> values) {
            addCriterion("vip_discount in", values, "vipDiscount");
            return (Criteria) this;
        }

        public Criteria andVipDiscountNotIn(List<Double> values) {
            addCriterion("vip_discount not in", values, "vipDiscount");
            return (Criteria) this;
        }

        public Criteria andVipDiscountBetween(Double value1, Double value2) {
            addCriterion("vip_discount between", value1, value2, "vipDiscount");
            return (Criteria) this;
        }

        public Criteria andVipDiscountNotBetween(Double value1, Double value2) {
            addCriterion("vip_discount not between", value1, value2, "vipDiscount");
            return (Criteria) this;
        }

        public Criteria andVipDayIsNull() {
            addCriterion("vip_day is null");
            return (Criteria) this;
        }

        public Criteria andVipDayIsNotNull() {
            addCriterion("vip_day is not null");
            return (Criteria) this;
        }

        public Criteria andVipDayEqualTo(Integer value) {
            addCriterion("vip_day =", value, "vipDay");
            return (Criteria) this;
        }

        public Criteria andVipDayNotEqualTo(Integer value) {
            addCriterion("vip_day <>", value, "vipDay");
            return (Criteria) this;
        }

        public Criteria andVipDayGreaterThan(Integer value) {
            addCriterion("vip_day >", value, "vipDay");
            return (Criteria) this;
        }

        public Criteria andVipDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("vip_day >=", value, "vipDay");
            return (Criteria) this;
        }

        public Criteria andVipDayLessThan(Integer value) {
            addCriterion("vip_day <", value, "vipDay");
            return (Criteria) this;
        }

        public Criteria andVipDayLessThanOrEqualTo(Integer value) {
            addCriterion("vip_day <=", value, "vipDay");
            return (Criteria) this;
        }

        public Criteria andVipDayIn(List<Integer> values) {
            addCriterion("vip_day in", values, "vipDay");
            return (Criteria) this;
        }

        public Criteria andVipDayNotIn(List<Integer> values) {
            addCriterion("vip_day not in", values, "vipDay");
            return (Criteria) this;
        }

        public Criteria andVipDayBetween(Integer value1, Integer value2) {
            addCriterion("vip_day between", value1, value2, "vipDay");
            return (Criteria) this;
        }

        public Criteria andVipDayNotBetween(Integer value1, Integer value2) {
            addCriterion("vip_day not between", value1, value2, "vipDay");
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

        public Criteria andPayTimeIsNull() {
            addCriterion("pay_time is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("pay_time is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimeEqualTo(Date value) {
            addCriterion("pay_time =", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotEqualTo(Date value) {
            addCriterion("pay_time <>", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThan(Date value) {
            addCriterion("pay_time >", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_time >=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThan(Date value) {
            addCriterion("pay_time <", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("pay_time <=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIn(List<Date> values) {
            addCriterion("pay_time in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotIn(List<Date> values) {
            addCriterion("pay_time not in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeBetween(Date value1, Date value2) {
            addCriterion("pay_time between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotBetween(Date value1, Date value2) {
            addCriterion("pay_time not between", value1, value2, "payTime");
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

        public Criteria andVipCardNameIsNull() {
            addCriterion("vip_card_name is null");
            return (Criteria) this;
        }

        public Criteria andVipCardNameIsNotNull() {
            addCriterion("vip_card_name is not null");
            return (Criteria) this;
        }

        public Criteria andVipCardNameEqualTo(String value) {
            addCriterion("vip_card_name =", value, "vipCardName");
            return (Criteria) this;
        }

        public Criteria andVipCardNameNotEqualTo(String value) {
            addCriterion("vip_card_name <>", value, "vipCardName");
            return (Criteria) this;
        }

        public Criteria andVipCardNameGreaterThan(String value) {
            addCriterion("vip_card_name >", value, "vipCardName");
            return (Criteria) this;
        }

        public Criteria andVipCardNameGreaterThanOrEqualTo(String value) {
            addCriterion("vip_card_name >=", value, "vipCardName");
            return (Criteria) this;
        }

        public Criteria andVipCardNameLessThan(String value) {
            addCriterion("vip_card_name <", value, "vipCardName");
            return (Criteria) this;
        }

        public Criteria andVipCardNameLessThanOrEqualTo(String value) {
            addCriterion("vip_card_name <=", value, "vipCardName");
            return (Criteria) this;
        }

        public Criteria andVipCardNameLike(String value) {
            addCriterion("vip_card_name like", value, "vipCardName");
            return (Criteria) this;
        }

        public Criteria andVipCardNameNotLike(String value) {
            addCriterion("vip_card_name not like", value, "vipCardName");
            return (Criteria) this;
        }

        public Criteria andVipCardNameIn(List<String> values) {
            addCriterion("vip_card_name in", values, "vipCardName");
            return (Criteria) this;
        }

        public Criteria andVipCardNameNotIn(List<String> values) {
            addCriterion("vip_card_name not in", values, "vipCardName");
            return (Criteria) this;
        }

        public Criteria andVipCardNameBetween(String value1, String value2) {
            addCriterion("vip_card_name between", value1, value2, "vipCardName");
            return (Criteria) this;
        }

        public Criteria andVipCardNameNotBetween(String value1, String value2) {
            addCriterion("vip_card_name not between", value1, value2, "vipCardName");
            return (Criteria) this;
        }

        public Criteria andVipCardUnitIsNull() {
            addCriterion("vip_card_unit is null");
            return (Criteria) this;
        }

        public Criteria andVipCardUnitIsNotNull() {
            addCriterion("vip_card_unit is not null");
            return (Criteria) this;
        }

        public Criteria andVipCardUnitEqualTo(String value) {
            addCriterion("vip_card_unit =", value, "vipCardUnit");
            return (Criteria) this;
        }

        public Criteria andVipCardUnitNotEqualTo(String value) {
            addCriterion("vip_card_unit <>", value, "vipCardUnit");
            return (Criteria) this;
        }

        public Criteria andVipCardUnitGreaterThan(String value) {
            addCriterion("vip_card_unit >", value, "vipCardUnit");
            return (Criteria) this;
        }

        public Criteria andVipCardUnitGreaterThanOrEqualTo(String value) {
            addCriterion("vip_card_unit >=", value, "vipCardUnit");
            return (Criteria) this;
        }

        public Criteria andVipCardUnitLessThan(String value) {
            addCriterion("vip_card_unit <", value, "vipCardUnit");
            return (Criteria) this;
        }

        public Criteria andVipCardUnitLessThanOrEqualTo(String value) {
            addCriterion("vip_card_unit <=", value, "vipCardUnit");
            return (Criteria) this;
        }

        public Criteria andVipCardUnitLike(String value) {
            addCriterion("vip_card_unit like", value, "vipCardUnit");
            return (Criteria) this;
        }

        public Criteria andVipCardUnitNotLike(String value) {
            addCriterion("vip_card_unit not like", value, "vipCardUnit");
            return (Criteria) this;
        }

        public Criteria andVipCardUnitIn(List<String> values) {
            addCriterion("vip_card_unit in", values, "vipCardUnit");
            return (Criteria) this;
        }

        public Criteria andVipCardUnitNotIn(List<String> values) {
            addCriterion("vip_card_unit not in", values, "vipCardUnit");
            return (Criteria) this;
        }

        public Criteria andVipCardUnitBetween(String value1, String value2) {
            addCriterion("vip_card_unit between", value1, value2, "vipCardUnit");
            return (Criteria) this;
        }

        public Criteria andVipCardUnitNotBetween(String value1, String value2) {
            addCriterion("vip_card_unit not between", value1, value2, "vipCardUnit");
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