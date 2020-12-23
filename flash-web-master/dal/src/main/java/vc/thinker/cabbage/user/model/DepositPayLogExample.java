package vc.thinker.cabbage.user.model;

import com.sinco.mybatis.dal.core.AbstractExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vc.thinker.cabbage.user.bo.DepositPayLogBO;

public class DepositPayLogExample extends AbstractExample<DepositPayLogBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public DepositPayLogExample() {
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

        public Criteria andRefundApplyTimeIsNull() {
            addCriterion("refund_apply_time is null");
            return (Criteria) this;
        }

        public Criteria andRefundApplyTimeIsNotNull() {
            addCriterion("refund_apply_time is not null");
            return (Criteria) this;
        }

        public Criteria andRefundApplyTimeEqualTo(Date value) {
            addCriterion("refund_apply_time =", value, "refundApplyTime");
            return (Criteria) this;
        }

        public Criteria andRefundApplyTimeNotEqualTo(Date value) {
            addCriterion("refund_apply_time <>", value, "refundApplyTime");
            return (Criteria) this;
        }

        public Criteria andRefundApplyTimeGreaterThan(Date value) {
            addCriterion("refund_apply_time >", value, "refundApplyTime");
            return (Criteria) this;
        }

        public Criteria andRefundApplyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("refund_apply_time >=", value, "refundApplyTime");
            return (Criteria) this;
        }

        public Criteria andRefundApplyTimeLessThan(Date value) {
            addCriterion("refund_apply_time <", value, "refundApplyTime");
            return (Criteria) this;
        }

        public Criteria andRefundApplyTimeLessThanOrEqualTo(Date value) {
            addCriterion("refund_apply_time <=", value, "refundApplyTime");
            return (Criteria) this;
        }

        public Criteria andRefundApplyTimeIn(List<Date> values) {
            addCriterion("refund_apply_time in", values, "refundApplyTime");
            return (Criteria) this;
        }

        public Criteria andRefundApplyTimeNotIn(List<Date> values) {
            addCriterion("refund_apply_time not in", values, "refundApplyTime");
            return (Criteria) this;
        }

        public Criteria andRefundApplyTimeBetween(Date value1, Date value2) {
            addCriterion("refund_apply_time between", value1, value2, "refundApplyTime");
            return (Criteria) this;
        }

        public Criteria andRefundApplyTimeNotBetween(Date value1, Date value2) {
            addCriterion("refund_apply_time not between", value1, value2, "refundApplyTime");
            return (Criteria) this;
        }

        public Criteria andRefundIdIsNull() {
            addCriterion("refund_id is null");
            return (Criteria) this;
        }

        public Criteria andRefundIdIsNotNull() {
            addCriterion("refund_id is not null");
            return (Criteria) this;
        }

        public Criteria andRefundIdEqualTo(String value) {
            addCriterion("refund_id =", value, "refundId");
            return (Criteria) this;
        }

        public Criteria andRefundIdNotEqualTo(String value) {
            addCriterion("refund_id <>", value, "refundId");
            return (Criteria) this;
        }

        public Criteria andRefundIdGreaterThan(String value) {
            addCriterion("refund_id >", value, "refundId");
            return (Criteria) this;
        }

        public Criteria andRefundIdGreaterThanOrEqualTo(String value) {
            addCriterion("refund_id >=", value, "refundId");
            return (Criteria) this;
        }

        public Criteria andRefundIdLessThan(String value) {
            addCriterion("refund_id <", value, "refundId");
            return (Criteria) this;
        }

        public Criteria andRefundIdLessThanOrEqualTo(String value) {
            addCriterion("refund_id <=", value, "refundId");
            return (Criteria) this;
        }

        public Criteria andRefundIdLike(String value) {
            addCriterion("refund_id like", value, "refundId");
            return (Criteria) this;
        }

        public Criteria andRefundIdNotLike(String value) {
            addCriterion("refund_id not like", value, "refundId");
            return (Criteria) this;
        }

        public Criteria andRefundIdIn(List<String> values) {
            addCriterion("refund_id in", values, "refundId");
            return (Criteria) this;
        }

        public Criteria andRefundIdNotIn(List<String> values) {
            addCriterion("refund_id not in", values, "refundId");
            return (Criteria) this;
        }

        public Criteria andRefundIdBetween(String value1, String value2) {
            addCriterion("refund_id between", value1, value2, "refundId");
            return (Criteria) this;
        }

        public Criteria andRefundIdNotBetween(String value1, String value2) {
            addCriterion("refund_id not between", value1, value2, "refundId");
            return (Criteria) this;
        }

        public Criteria andRefundAccountIsNull() {
            addCriterion("refund_account is null");
            return (Criteria) this;
        }

        public Criteria andRefundAccountIsNotNull() {
            addCriterion("refund_account is not null");
            return (Criteria) this;
        }

        public Criteria andRefundAccountEqualTo(String value) {
            addCriterion("refund_account =", value, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundAccountNotEqualTo(String value) {
            addCriterion("refund_account <>", value, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundAccountGreaterThan(String value) {
            addCriterion("refund_account >", value, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundAccountGreaterThanOrEqualTo(String value) {
            addCriterion("refund_account >=", value, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundAccountLessThan(String value) {
            addCriterion("refund_account <", value, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundAccountLessThanOrEqualTo(String value) {
            addCriterion("refund_account <=", value, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundAccountLike(String value) {
            addCriterion("refund_account like", value, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundAccountNotLike(String value) {
            addCriterion("refund_account not like", value, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundAccountIn(List<String> values) {
            addCriterion("refund_account in", values, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundAccountNotIn(List<String> values) {
            addCriterion("refund_account not in", values, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundAccountBetween(String value1, String value2) {
            addCriterion("refund_account between", value1, value2, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundAccountNotBetween(String value1, String value2) {
            addCriterion("refund_account not between", value1, value2, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundSuccTimeIsNull() {
            addCriterion("refund_succ_time is null");
            return (Criteria) this;
        }

        public Criteria andRefundSuccTimeIsNotNull() {
            addCriterion("refund_succ_time is not null");
            return (Criteria) this;
        }

        public Criteria andRefundSuccTimeEqualTo(Date value) {
            addCriterion("refund_succ_time =", value, "refundSuccTime");
            return (Criteria) this;
        }

        public Criteria andRefundSuccTimeNotEqualTo(Date value) {
            addCriterion("refund_succ_time <>", value, "refundSuccTime");
            return (Criteria) this;
        }

        public Criteria andRefundSuccTimeGreaterThan(Date value) {
            addCriterion("refund_succ_time >", value, "refundSuccTime");
            return (Criteria) this;
        }

        public Criteria andRefundSuccTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("refund_succ_time >=", value, "refundSuccTime");
            return (Criteria) this;
        }

        public Criteria andRefundSuccTimeLessThan(Date value) {
            addCriterion("refund_succ_time <", value, "refundSuccTime");
            return (Criteria) this;
        }

        public Criteria andRefundSuccTimeLessThanOrEqualTo(Date value) {
            addCriterion("refund_succ_time <=", value, "refundSuccTime");
            return (Criteria) this;
        }

        public Criteria andRefundSuccTimeIn(List<Date> values) {
            addCriterion("refund_succ_time in", values, "refundSuccTime");
            return (Criteria) this;
        }

        public Criteria andRefundSuccTimeNotIn(List<Date> values) {
            addCriterion("refund_succ_time not in", values, "refundSuccTime");
            return (Criteria) this;
        }

        public Criteria andRefundSuccTimeBetween(Date value1, Date value2) {
            addCriterion("refund_succ_time between", value1, value2, "refundSuccTime");
            return (Criteria) this;
        }

        public Criteria andRefundSuccTimeNotBetween(Date value1, Date value2) {
            addCriterion("refund_succ_time not between", value1, value2, "refundSuccTime");
            return (Criteria) this;
        }

        public Criteria andRefundOperatorIsNull() {
            addCriterion("refund_operator is null");
            return (Criteria) this;
        }

        public Criteria andRefundOperatorIsNotNull() {
            addCriterion("refund_operator is not null");
            return (Criteria) this;
        }

        public Criteria andRefundOperatorEqualTo(Long value) {
            addCriterion("refund_operator =", value, "refundOperator");
            return (Criteria) this;
        }

        public Criteria andRefundOperatorNotEqualTo(Long value) {
            addCriterion("refund_operator <>", value, "refundOperator");
            return (Criteria) this;
        }

        public Criteria andRefundOperatorGreaterThan(Long value) {
            addCriterion("refund_operator >", value, "refundOperator");
            return (Criteria) this;
        }

        public Criteria andRefundOperatorGreaterThanOrEqualTo(Long value) {
            addCriterion("refund_operator >=", value, "refundOperator");
            return (Criteria) this;
        }

        public Criteria andRefundOperatorLessThan(Long value) {
            addCriterion("refund_operator <", value, "refundOperator");
            return (Criteria) this;
        }

        public Criteria andRefundOperatorLessThanOrEqualTo(Long value) {
            addCriterion("refund_operator <=", value, "refundOperator");
            return (Criteria) this;
        }

        public Criteria andRefundOperatorIn(List<Long> values) {
            addCriterion("refund_operator in", values, "refundOperator");
            return (Criteria) this;
        }

        public Criteria andRefundOperatorNotIn(List<Long> values) {
            addCriterion("refund_operator not in", values, "refundOperator");
            return (Criteria) this;
        }

        public Criteria andRefundOperatorBetween(Long value1, Long value2) {
            addCriterion("refund_operator between", value1, value2, "refundOperator");
            return (Criteria) this;
        }

        public Criteria andRefundOperatorNotBetween(Long value1, Long value2) {
            addCriterion("refund_operator not between", value1, value2, "refundOperator");
            return (Criteria) this;
        }

        public Criteria andRefundRemarkIsNull() {
            addCriterion("refund_remark is null");
            return (Criteria) this;
        }

        public Criteria andRefundRemarkIsNotNull() {
            addCriterion("refund_remark is not null");
            return (Criteria) this;
        }

        public Criteria andRefundRemarkEqualTo(String value) {
            addCriterion("refund_remark =", value, "refundRemark");
            return (Criteria) this;
        }

        public Criteria andRefundRemarkNotEqualTo(String value) {
            addCriterion("refund_remark <>", value, "refundRemark");
            return (Criteria) this;
        }

        public Criteria andRefundRemarkGreaterThan(String value) {
            addCriterion("refund_remark >", value, "refundRemark");
            return (Criteria) this;
        }

        public Criteria andRefundRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("refund_remark >=", value, "refundRemark");
            return (Criteria) this;
        }

        public Criteria andRefundRemarkLessThan(String value) {
            addCriterion("refund_remark <", value, "refundRemark");
            return (Criteria) this;
        }

        public Criteria andRefundRemarkLessThanOrEqualTo(String value) {
            addCriterion("refund_remark <=", value, "refundRemark");
            return (Criteria) this;
        }

        public Criteria andRefundRemarkLike(String value) {
            addCriterion("refund_remark like", value, "refundRemark");
            return (Criteria) this;
        }

        public Criteria andRefundRemarkNotLike(String value) {
            addCriterion("refund_remark not like", value, "refundRemark");
            return (Criteria) this;
        }

        public Criteria andRefundRemarkIn(List<String> values) {
            addCriterion("refund_remark in", values, "refundRemark");
            return (Criteria) this;
        }

        public Criteria andRefundRemarkNotIn(List<String> values) {
            addCriterion("refund_remark not in", values, "refundRemark");
            return (Criteria) this;
        }

        public Criteria andRefundRemarkBetween(String value1, String value2) {
            addCriterion("refund_remark between", value1, value2, "refundRemark");
            return (Criteria) this;
        }

        public Criteria andRefundRemarkNotBetween(String value1, String value2) {
            addCriterion("refund_remark not between", value1, value2, "refundRemark");
            return (Criteria) this;
        }

        public Criteria andRefundErrorCodeIsNull() {
            addCriterion("refund_error_code is null");
            return (Criteria) this;
        }

        public Criteria andRefundErrorCodeIsNotNull() {
            addCriterion("refund_error_code is not null");
            return (Criteria) this;
        }

        public Criteria andRefundErrorCodeEqualTo(String value) {
            addCriterion("refund_error_code =", value, "refundErrorCode");
            return (Criteria) this;
        }

        public Criteria andRefundErrorCodeNotEqualTo(String value) {
            addCriterion("refund_error_code <>", value, "refundErrorCode");
            return (Criteria) this;
        }

        public Criteria andRefundErrorCodeGreaterThan(String value) {
            addCriterion("refund_error_code >", value, "refundErrorCode");
            return (Criteria) this;
        }

        public Criteria andRefundErrorCodeGreaterThanOrEqualTo(String value) {
            addCriterion("refund_error_code >=", value, "refundErrorCode");
            return (Criteria) this;
        }

        public Criteria andRefundErrorCodeLessThan(String value) {
            addCriterion("refund_error_code <", value, "refundErrorCode");
            return (Criteria) this;
        }

        public Criteria andRefundErrorCodeLessThanOrEqualTo(String value) {
            addCriterion("refund_error_code <=", value, "refundErrorCode");
            return (Criteria) this;
        }

        public Criteria andRefundErrorCodeLike(String value) {
            addCriterion("refund_error_code like", value, "refundErrorCode");
            return (Criteria) this;
        }

        public Criteria andRefundErrorCodeNotLike(String value) {
            addCriterion("refund_error_code not like", value, "refundErrorCode");
            return (Criteria) this;
        }

        public Criteria andRefundErrorCodeIn(List<String> values) {
            addCriterion("refund_error_code in", values, "refundErrorCode");
            return (Criteria) this;
        }

        public Criteria andRefundErrorCodeNotIn(List<String> values) {
            addCriterion("refund_error_code not in", values, "refundErrorCode");
            return (Criteria) this;
        }

        public Criteria andRefundErrorCodeBetween(String value1, String value2) {
            addCriterion("refund_error_code between", value1, value2, "refundErrorCode");
            return (Criteria) this;
        }

        public Criteria andRefundErrorCodeNotBetween(String value1, String value2) {
            addCriterion("refund_error_code not between", value1, value2, "refundErrorCode");
            return (Criteria) this;
        }

        public Criteria andRefundErrorMessageIsNull() {
            addCriterion("refund_error_message is null");
            return (Criteria) this;
        }

        public Criteria andRefundErrorMessageIsNotNull() {
            addCriterion("refund_error_message is not null");
            return (Criteria) this;
        }

        public Criteria andRefundErrorMessageEqualTo(String value) {
            addCriterion("refund_error_message =", value, "refundErrorMessage");
            return (Criteria) this;
        }

        public Criteria andRefundErrorMessageNotEqualTo(String value) {
            addCriterion("refund_error_message <>", value, "refundErrorMessage");
            return (Criteria) this;
        }

        public Criteria andRefundErrorMessageGreaterThan(String value) {
            addCriterion("refund_error_message >", value, "refundErrorMessage");
            return (Criteria) this;
        }

        public Criteria andRefundErrorMessageGreaterThanOrEqualTo(String value) {
            addCriterion("refund_error_message >=", value, "refundErrorMessage");
            return (Criteria) this;
        }

        public Criteria andRefundErrorMessageLessThan(String value) {
            addCriterion("refund_error_message <", value, "refundErrorMessage");
            return (Criteria) this;
        }

        public Criteria andRefundErrorMessageLessThanOrEqualTo(String value) {
            addCriterion("refund_error_message <=", value, "refundErrorMessage");
            return (Criteria) this;
        }

        public Criteria andRefundErrorMessageLike(String value) {
            addCriterion("refund_error_message like", value, "refundErrorMessage");
            return (Criteria) this;
        }

        public Criteria andRefundErrorMessageNotLike(String value) {
            addCriterion("refund_error_message not like", value, "refundErrorMessage");
            return (Criteria) this;
        }

        public Criteria andRefundErrorMessageIn(List<String> values) {
            addCriterion("refund_error_message in", values, "refundErrorMessage");
            return (Criteria) this;
        }

        public Criteria andRefundErrorMessageNotIn(List<String> values) {
            addCriterion("refund_error_message not in", values, "refundErrorMessage");
            return (Criteria) this;
        }

        public Criteria andRefundErrorMessageBetween(String value1, String value2) {
            addCriterion("refund_error_message between", value1, value2, "refundErrorMessage");
            return (Criteria) this;
        }

        public Criteria andRefundErrorMessageNotBetween(String value1, String value2) {
            addCriterion("refund_error_message not between", value1, value2, "refundErrorMessage");
            return (Criteria) this;
        }

        public Criteria andIsCaptureIsNull() {
            addCriterion("is_capture is null");
            return (Criteria) this;
        }

        public Criteria andIsCaptureIsNotNull() {
            addCriterion("is_capture is not null");
            return (Criteria) this;
        }

        public Criteria andIsCaptureEqualTo(Boolean value) {
            addCriterion("is_capture =", value, "isCapture");
            return (Criteria) this;
        }

        public Criteria andIsCaptureNotEqualTo(Boolean value) {
            addCriterion("is_capture <>", value, "isCapture");
            return (Criteria) this;
        }

        public Criteria andIsCaptureGreaterThan(Boolean value) {
            addCriterion("is_capture >", value, "isCapture");
            return (Criteria) this;
        }

        public Criteria andIsCaptureGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_capture >=", value, "isCapture");
            return (Criteria) this;
        }

        public Criteria andIsCaptureLessThan(Boolean value) {
            addCriterion("is_capture <", value, "isCapture");
            return (Criteria) this;
        }

        public Criteria andIsCaptureLessThanOrEqualTo(Boolean value) {
            addCriterion("is_capture <=", value, "isCapture");
            return (Criteria) this;
        }

        public Criteria andIsCaptureIn(List<Boolean> values) {
            addCriterion("is_capture in", values, "isCapture");
            return (Criteria) this;
        }

        public Criteria andIsCaptureNotIn(List<Boolean> values) {
            addCriterion("is_capture not in", values, "isCapture");
            return (Criteria) this;
        }

        public Criteria andIsCaptureBetween(Boolean value1, Boolean value2) {
            addCriterion("is_capture between", value1, value2, "isCapture");
            return (Criteria) this;
        }

        public Criteria andIsCaptureNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_capture not between", value1, value2, "isCapture");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeIsNull() {
            addCriterion("capture_time is null");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeIsNotNull() {
            addCriterion("capture_time is not null");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeEqualTo(Date value) {
            addCriterion("capture_time =", value, "captureTime");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeNotEqualTo(Date value) {
            addCriterion("capture_time <>", value, "captureTime");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeGreaterThan(Date value) {
            addCriterion("capture_time >", value, "captureTime");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("capture_time >=", value, "captureTime");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeLessThan(Date value) {
            addCriterion("capture_time <", value, "captureTime");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeLessThanOrEqualTo(Date value) {
            addCriterion("capture_time <=", value, "captureTime");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeIn(List<Date> values) {
            addCriterion("capture_time in", values, "captureTime");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeNotIn(List<Date> values) {
            addCriterion("capture_time not in", values, "captureTime");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeBetween(Date value1, Date value2) {
            addCriterion("capture_time between", value1, value2, "captureTime");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeNotBetween(Date value1, Date value2) {
            addCriterion("capture_time not between", value1, value2, "captureTime");
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