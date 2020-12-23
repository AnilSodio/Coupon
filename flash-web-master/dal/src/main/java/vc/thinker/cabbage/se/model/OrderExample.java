package vc.thinker.cabbage.se.model;

import com.sinco.mybatis.dal.core.AbstractExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vc.thinker.cabbage.se.bo.OrderBO;

public class OrderExample extends AbstractExample<OrderBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public OrderExample() {
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

        public Criteria andBorrowCabinetIdIsNull() {
            addCriterion("borrow_cabinet_id is null");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetIdIsNotNull() {
            addCriterion("borrow_cabinet_id is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetIdEqualTo(Long value) {
            addCriterion("borrow_cabinet_id =", value, "borrowCabinetId");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetIdNotEqualTo(Long value) {
            addCriterion("borrow_cabinet_id <>", value, "borrowCabinetId");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetIdGreaterThan(Long value) {
            addCriterion("borrow_cabinet_id >", value, "borrowCabinetId");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetIdGreaterThanOrEqualTo(Long value) {
            addCriterion("borrow_cabinet_id >=", value, "borrowCabinetId");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetIdLessThan(Long value) {
            addCriterion("borrow_cabinet_id <", value, "borrowCabinetId");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetIdLessThanOrEqualTo(Long value) {
            addCriterion("borrow_cabinet_id <=", value, "borrowCabinetId");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetIdIn(List<Long> values) {
            addCriterion("borrow_cabinet_id in", values, "borrowCabinetId");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetIdNotIn(List<Long> values) {
            addCriterion("borrow_cabinet_id not in", values, "borrowCabinetId");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetIdBetween(Long value1, Long value2) {
            addCriterion("borrow_cabinet_id between", value1, value2, "borrowCabinetId");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetIdNotBetween(Long value1, Long value2) {
            addCriterion("borrow_cabinet_id not between", value1, value2, "borrowCabinetId");
            return (Criteria) this;
        }

        public Criteria andBorrowSysCodeIsNull() {
            addCriterion("borrow_sys_code is null");
            return (Criteria) this;
        }

        public Criteria andBorrowSysCodeIsNotNull() {
            addCriterion("borrow_sys_code is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowSysCodeEqualTo(String value) {
            addCriterion("borrow_sys_code =", value, "borrowSysCode");
            return (Criteria) this;
        }

        public Criteria andBorrowSysCodeNotEqualTo(String value) {
            addCriterion("borrow_sys_code <>", value, "borrowSysCode");
            return (Criteria) this;
        }

        public Criteria andBorrowSysCodeGreaterThan(String value) {
            addCriterion("borrow_sys_code >", value, "borrowSysCode");
            return (Criteria) this;
        }

        public Criteria andBorrowSysCodeGreaterThanOrEqualTo(String value) {
            addCriterion("borrow_sys_code >=", value, "borrowSysCode");
            return (Criteria) this;
        }

        public Criteria andBorrowSysCodeLessThan(String value) {
            addCriterion("borrow_sys_code <", value, "borrowSysCode");
            return (Criteria) this;
        }

        public Criteria andBorrowSysCodeLessThanOrEqualTo(String value) {
            addCriterion("borrow_sys_code <=", value, "borrowSysCode");
            return (Criteria) this;
        }

        public Criteria andBorrowSysCodeLike(String value) {
            addCriterion("borrow_sys_code like", value, "borrowSysCode");
            return (Criteria) this;
        }

        public Criteria andBorrowSysCodeNotLike(String value) {
            addCriterion("borrow_sys_code not like", value, "borrowSysCode");
            return (Criteria) this;
        }

        public Criteria andBorrowSysCodeIn(List<String> values) {
            addCriterion("borrow_sys_code in", values, "borrowSysCode");
            return (Criteria) this;
        }

        public Criteria andBorrowSysCodeNotIn(List<String> values) {
            addCriterion("borrow_sys_code not in", values, "borrowSysCode");
            return (Criteria) this;
        }

        public Criteria andBorrowSysCodeBetween(String value1, String value2) {
            addCriterion("borrow_sys_code between", value1, value2, "borrowSysCode");
            return (Criteria) this;
        }

        public Criteria andBorrowSysCodeNotBetween(String value1, String value2) {
            addCriterion("borrow_sys_code not between", value1, value2, "borrowSysCode");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNull() {
            addCriterion("is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(Boolean value) {
            addCriterion("is_deleted =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(Boolean value) {
            addCriterion("is_deleted <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(Boolean value) {
            addCriterion("is_deleted >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_deleted >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(Boolean value) {
            addCriterion("is_deleted <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_deleted <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<Boolean> values) {
            addCriterion("is_deleted in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<Boolean> values) {
            addCriterion("is_deleted not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_deleted between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_deleted not between", value1, value2, "isDeleted");
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

        public Criteria andBeginTimeIsNull() {
            addCriterion("begin_time is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(Date value) {
            addCriterion("begin_time =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(Date value) {
            addCriterion("begin_time <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(Date value) {
            addCriterion("begin_time >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("begin_time >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(Date value) {
            addCriterion("begin_time <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("begin_time <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<Date> values) {
            addCriterion("begin_time in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<Date> values) {
            addCriterion("begin_time not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(Date value1, Date value2) {
            addCriterion("begin_time between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("begin_time not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNull() {
            addCriterion("finish_time is null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNotNull() {
            addCriterion("finish_time is not null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeEqualTo(Date value) {
            addCriterion("finish_time =", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotEqualTo(Date value) {
            addCriterion("finish_time <>", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThan(Date value) {
            addCriterion("finish_time >", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("finish_time >=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThan(Date value) {
            addCriterion("finish_time <", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThanOrEqualTo(Date value) {
            addCriterion("finish_time <=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIn(List<Date> values) {
            addCriterion("finish_time in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotIn(List<Date> values) {
            addCriterion("finish_time not in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeBetween(Date value1, Date value2) {
            addCriterion("finish_time between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotBetween(Date value1, Date value2) {
            addCriterion("finish_time not between", value1, value2, "finishTime");
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

        public Criteria andUserCouponIdIsNull() {
            addCriterion("user_coupon_id is null");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdIsNotNull() {
            addCriterion("user_coupon_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdEqualTo(Long value) {
            addCriterion("user_coupon_id =", value, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdNotEqualTo(Long value) {
            addCriterion("user_coupon_id <>", value, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdGreaterThan(Long value) {
            addCriterion("user_coupon_id >", value, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_coupon_id >=", value, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdLessThan(Long value) {
            addCriterion("user_coupon_id <", value, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdLessThanOrEqualTo(Long value) {
            addCriterion("user_coupon_id <=", value, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdIn(List<Long> values) {
            addCriterion("user_coupon_id in", values, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdNotIn(List<Long> values) {
            addCriterion("user_coupon_id not in", values, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdBetween(Long value1, Long value2) {
            addCriterion("user_coupon_id between", value1, value2, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdNotBetween(Long value1, Long value2) {
            addCriterion("user_coupon_id not between", value1, value2, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNull() {
            addCriterion("city_id is null");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNotNull() {
            addCriterion("city_id is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdEqualTo(String value) {
            addCriterion("city_id =", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotEqualTo(String value) {
            addCriterion("city_id <>", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThan(String value) {
            addCriterion("city_id >", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThanOrEqualTo(String value) {
            addCriterion("city_id >=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThan(String value) {
            addCriterion("city_id <", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThanOrEqualTo(String value) {
            addCriterion("city_id <=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLike(String value) {
            addCriterion("city_id like", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotLike(String value) {
            addCriterion("city_id not like", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdIn(List<String> values) {
            addCriterion("city_id in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotIn(List<String> values) {
            addCriterion("city_id not in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdBetween(String value1, String value2) {
            addCriterion("city_id between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotBetween(String value1, String value2) {
            addCriterion("city_id not between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdIsNull() {
            addCriterion("office_id is null");
            return (Criteria) this;
        }

        public Criteria andOfficeIdIsNotNull() {
            addCriterion("office_id is not null");
            return (Criteria) this;
        }

        public Criteria andOfficeIdEqualTo(Long value) {
            addCriterion("office_id =", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdNotEqualTo(Long value) {
            addCriterion("office_id <>", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdGreaterThan(Long value) {
            addCriterion("office_id >", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("office_id >=", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdLessThan(Long value) {
            addCriterion("office_id <", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdLessThanOrEqualTo(Long value) {
            addCriterion("office_id <=", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdIn(List<Long> values) {
            addCriterion("office_id in", values, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdNotIn(List<Long> values) {
            addCriterion("office_id not in", values, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdBetween(Long value1, Long value2) {
            addCriterion("office_id between", value1, value2, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdNotBetween(Long value1, Long value2) {
            addCriterion("office_id not between", value1, value2, "officeId");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLonIsNull() {
            addCriterion("begin_location_lon is null");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLonIsNotNull() {
            addCriterion("begin_location_lon is not null");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLonEqualTo(BigDecimal value) {
            addCriterion("begin_location_lon =", value, "beginLocationLon");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLonNotEqualTo(BigDecimal value) {
            addCriterion("begin_location_lon <>", value, "beginLocationLon");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLonGreaterThan(BigDecimal value) {
            addCriterion("begin_location_lon >", value, "beginLocationLon");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLonGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("begin_location_lon >=", value, "beginLocationLon");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLonLessThan(BigDecimal value) {
            addCriterion("begin_location_lon <", value, "beginLocationLon");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLonLessThanOrEqualTo(BigDecimal value) {
            addCriterion("begin_location_lon <=", value, "beginLocationLon");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLonIn(List<BigDecimal> values) {
            addCriterion("begin_location_lon in", values, "beginLocationLon");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLonNotIn(List<BigDecimal> values) {
            addCriterion("begin_location_lon not in", values, "beginLocationLon");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLonBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("begin_location_lon between", value1, value2, "beginLocationLon");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLonNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("begin_location_lon not between", value1, value2, "beginLocationLon");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLatIsNull() {
            addCriterion("begin_location_lat is null");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLatIsNotNull() {
            addCriterion("begin_location_lat is not null");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLatEqualTo(BigDecimal value) {
            addCriterion("begin_location_lat =", value, "beginLocationLat");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLatNotEqualTo(BigDecimal value) {
            addCriterion("begin_location_lat <>", value, "beginLocationLat");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLatGreaterThan(BigDecimal value) {
            addCriterion("begin_location_lat >", value, "beginLocationLat");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLatGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("begin_location_lat >=", value, "beginLocationLat");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLatLessThan(BigDecimal value) {
            addCriterion("begin_location_lat <", value, "beginLocationLat");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLatLessThanOrEqualTo(BigDecimal value) {
            addCriterion("begin_location_lat <=", value, "beginLocationLat");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLatIn(List<BigDecimal> values) {
            addCriterion("begin_location_lat in", values, "beginLocationLat");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLatNotIn(List<BigDecimal> values) {
            addCriterion("begin_location_lat not in", values, "beginLocationLat");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLatBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("begin_location_lat between", value1, value2, "beginLocationLat");
            return (Criteria) this;
        }

        public Criteria andBeginLocationLatNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("begin_location_lat not between", value1, value2, "beginLocationLat");
            return (Criteria) this;
        }

        public Criteria andEndLocationLonIsNull() {
            addCriterion("end_location_lon is null");
            return (Criteria) this;
        }

        public Criteria andEndLocationLonIsNotNull() {
            addCriterion("end_location_lon is not null");
            return (Criteria) this;
        }

        public Criteria andEndLocationLonEqualTo(BigDecimal value) {
            addCriterion("end_location_lon =", value, "endLocationLon");
            return (Criteria) this;
        }

        public Criteria andEndLocationLonNotEqualTo(BigDecimal value) {
            addCriterion("end_location_lon <>", value, "endLocationLon");
            return (Criteria) this;
        }

        public Criteria andEndLocationLonGreaterThan(BigDecimal value) {
            addCriterion("end_location_lon >", value, "endLocationLon");
            return (Criteria) this;
        }

        public Criteria andEndLocationLonGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("end_location_lon >=", value, "endLocationLon");
            return (Criteria) this;
        }

        public Criteria andEndLocationLonLessThan(BigDecimal value) {
            addCriterion("end_location_lon <", value, "endLocationLon");
            return (Criteria) this;
        }

        public Criteria andEndLocationLonLessThanOrEqualTo(BigDecimal value) {
            addCriterion("end_location_lon <=", value, "endLocationLon");
            return (Criteria) this;
        }

        public Criteria andEndLocationLonIn(List<BigDecimal> values) {
            addCriterion("end_location_lon in", values, "endLocationLon");
            return (Criteria) this;
        }

        public Criteria andEndLocationLonNotIn(List<BigDecimal> values) {
            addCriterion("end_location_lon not in", values, "endLocationLon");
            return (Criteria) this;
        }

        public Criteria andEndLocationLonBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_location_lon between", value1, value2, "endLocationLon");
            return (Criteria) this;
        }

        public Criteria andEndLocationLonNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_location_lon not between", value1, value2, "endLocationLon");
            return (Criteria) this;
        }

        public Criteria andEndLocationLatIsNull() {
            addCriterion("end_location_lat is null");
            return (Criteria) this;
        }

        public Criteria andEndLocationLatIsNotNull() {
            addCriterion("end_location_lat is not null");
            return (Criteria) this;
        }

        public Criteria andEndLocationLatEqualTo(BigDecimal value) {
            addCriterion("end_location_lat =", value, "endLocationLat");
            return (Criteria) this;
        }

        public Criteria andEndLocationLatNotEqualTo(BigDecimal value) {
            addCriterion("end_location_lat <>", value, "endLocationLat");
            return (Criteria) this;
        }

        public Criteria andEndLocationLatGreaterThan(BigDecimal value) {
            addCriterion("end_location_lat >", value, "endLocationLat");
            return (Criteria) this;
        }

        public Criteria andEndLocationLatGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("end_location_lat >=", value, "endLocationLat");
            return (Criteria) this;
        }

        public Criteria andEndLocationLatLessThan(BigDecimal value) {
            addCriterion("end_location_lat <", value, "endLocationLat");
            return (Criteria) this;
        }

        public Criteria andEndLocationLatLessThanOrEqualTo(BigDecimal value) {
            addCriterion("end_location_lat <=", value, "endLocationLat");
            return (Criteria) this;
        }

        public Criteria andEndLocationLatIn(List<BigDecimal> values) {
            addCriterion("end_location_lat in", values, "endLocationLat");
            return (Criteria) this;
        }

        public Criteria andEndLocationLatNotIn(List<BigDecimal> values) {
            addCriterion("end_location_lat not in", values, "endLocationLat");
            return (Criteria) this;
        }

        public Criteria andEndLocationLatBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_location_lat between", value1, value2, "endLocationLat");
            return (Criteria) this;
        }

        public Criteria andEndLocationLatNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_location_lat not between", value1, value2, "endLocationLat");
            return (Criteria) this;
        }

        public Criteria andCheckNameIsNull() {
            addCriterion("check_name is null");
            return (Criteria) this;
        }

        public Criteria andCheckNameIsNotNull() {
            addCriterion("check_name is not null");
            return (Criteria) this;
        }

        public Criteria andCheckNameEqualTo(String value) {
            addCriterion("check_name =", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameNotEqualTo(String value) {
            addCriterion("check_name <>", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameGreaterThan(String value) {
            addCriterion("check_name >", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameGreaterThanOrEqualTo(String value) {
            addCriterion("check_name >=", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameLessThan(String value) {
            addCriterion("check_name <", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameLessThanOrEqualTo(String value) {
            addCriterion("check_name <=", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameLike(String value) {
            addCriterion("check_name like", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameNotLike(String value) {
            addCriterion("check_name not like", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameIn(List<String> values) {
            addCriterion("check_name in", values, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameNotIn(List<String> values) {
            addCriterion("check_name not in", values, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameBetween(String value1, String value2) {
            addCriterion("check_name between", value1, value2, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameNotBetween(String value1, String value2) {
            addCriterion("check_name not between", value1, value2, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNull() {
            addCriterion("check_time is null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNotNull() {
            addCriterion("check_time is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeEqualTo(Date value) {
            addCriterion("check_time =", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotEqualTo(Date value) {
            addCriterion("check_time <>", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThan(Date value) {
            addCriterion("check_time >", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("check_time >=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThan(Date value) {
            addCriterion("check_time <", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThanOrEqualTo(Date value) {
            addCriterion("check_time <=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIn(List<Date> values) {
            addCriterion("check_time in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotIn(List<Date> values) {
            addCriterion("check_time not in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeBetween(Date value1, Date value2) {
            addCriterion("check_time between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotBetween(Date value1, Date value2) {
            addCriterion("check_time not between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckDescIsNull() {
            addCriterion("check_desc is null");
            return (Criteria) this;
        }

        public Criteria andCheckDescIsNotNull() {
            addCriterion("check_desc is not null");
            return (Criteria) this;
        }

        public Criteria andCheckDescEqualTo(String value) {
            addCriterion("check_desc =", value, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andCheckDescNotEqualTo(String value) {
            addCriterion("check_desc <>", value, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andCheckDescGreaterThan(String value) {
            addCriterion("check_desc >", value, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andCheckDescGreaterThanOrEqualTo(String value) {
            addCriterion("check_desc >=", value, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andCheckDescLessThan(String value) {
            addCriterion("check_desc <", value, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andCheckDescLessThanOrEqualTo(String value) {
            addCriterion("check_desc <=", value, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andCheckDescLike(String value) {
            addCriterion("check_desc like", value, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andCheckDescNotLike(String value) {
            addCriterion("check_desc not like", value, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andCheckDescIn(List<String> values) {
            addCriterion("check_desc in", values, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andCheckDescNotIn(List<String> values) {
            addCriterion("check_desc not in", values, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andCheckDescBetween(String value1, String value2) {
            addCriterion("check_desc between", value1, value2, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andCheckDescNotBetween(String value1, String value2) {
            addCriterion("check_desc not between", value1, value2, "checkDesc");
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

        public Criteria andRideTimeIsNull() {
            addCriterion("ride_time is null");
            return (Criteria) this;
        }

        public Criteria andRideTimeIsNotNull() {
            addCriterion("ride_time is not null");
            return (Criteria) this;
        }

        public Criteria andRideTimeEqualTo(Integer value) {
            addCriterion("ride_time =", value, "rideTime");
            return (Criteria) this;
        }

        public Criteria andRideTimeNotEqualTo(Integer value) {
            addCriterion("ride_time <>", value, "rideTime");
            return (Criteria) this;
        }

        public Criteria andRideTimeGreaterThan(Integer value) {
            addCriterion("ride_time >", value, "rideTime");
            return (Criteria) this;
        }

        public Criteria andRideTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ride_time >=", value, "rideTime");
            return (Criteria) this;
        }

        public Criteria andRideTimeLessThan(Integer value) {
            addCriterion("ride_time <", value, "rideTime");
            return (Criteria) this;
        }

        public Criteria andRideTimeLessThanOrEqualTo(Integer value) {
            addCriterion("ride_time <=", value, "rideTime");
            return (Criteria) this;
        }

        public Criteria andRideTimeIn(List<Integer> values) {
            addCriterion("ride_time in", values, "rideTime");
            return (Criteria) this;
        }

        public Criteria andRideTimeNotIn(List<Integer> values) {
            addCriterion("ride_time not in", values, "rideTime");
            return (Criteria) this;
        }

        public Criteria andRideTimeBetween(Integer value1, Integer value2) {
            addCriterion("ride_time between", value1, value2, "rideTime");
            return (Criteria) this;
        }

        public Criteria andRideTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("ride_time not between", value1, value2, "rideTime");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerIdIsNull() {
            addCriterion("borrow_seller_id is null");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerIdIsNotNull() {
            addCriterion("borrow_seller_id is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerIdEqualTo(Long value) {
            addCriterion("borrow_seller_id =", value, "borrowSellerId");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerIdNotEqualTo(Long value) {
            addCriterion("borrow_seller_id <>", value, "borrowSellerId");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerIdGreaterThan(Long value) {
            addCriterion("borrow_seller_id >", value, "borrowSellerId");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("borrow_seller_id >=", value, "borrowSellerId");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerIdLessThan(Long value) {
            addCriterion("borrow_seller_id <", value, "borrowSellerId");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerIdLessThanOrEqualTo(Long value) {
            addCriterion("borrow_seller_id <=", value, "borrowSellerId");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerIdIn(List<Long> values) {
            addCriterion("borrow_seller_id in", values, "borrowSellerId");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerIdNotIn(List<Long> values) {
            addCriterion("borrow_seller_id not in", values, "borrowSellerId");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerIdBetween(Long value1, Long value2) {
            addCriterion("borrow_seller_id between", value1, value2, "borrowSellerId");
            return (Criteria) this;
        }

        public Criteria andBorrowSellerIdNotBetween(Long value1, Long value2) {
            addCriterion("borrow_seller_id not between", value1, value2, "borrowSellerId");
            return (Criteria) this;
        }

        public Criteria andReturnSellerIdIsNull() {
            addCriterion("return_seller_id is null");
            return (Criteria) this;
        }

        public Criteria andReturnSellerIdIsNotNull() {
            addCriterion("return_seller_id is not null");
            return (Criteria) this;
        }

        public Criteria andReturnSellerIdEqualTo(Long value) {
            addCriterion("return_seller_id =", value, "returnSellerId");
            return (Criteria) this;
        }

        public Criteria andReturnSellerIdNotEqualTo(Long value) {
            addCriterion("return_seller_id <>", value, "returnSellerId");
            return (Criteria) this;
        }

        public Criteria andReturnSellerIdGreaterThan(Long value) {
            addCriterion("return_seller_id >", value, "returnSellerId");
            return (Criteria) this;
        }

        public Criteria andReturnSellerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("return_seller_id >=", value, "returnSellerId");
            return (Criteria) this;
        }

        public Criteria andReturnSellerIdLessThan(Long value) {
            addCriterion("return_seller_id <", value, "returnSellerId");
            return (Criteria) this;
        }

        public Criteria andReturnSellerIdLessThanOrEqualTo(Long value) {
            addCriterion("return_seller_id <=", value, "returnSellerId");
            return (Criteria) this;
        }

        public Criteria andReturnSellerIdIn(List<Long> values) {
            addCriterion("return_seller_id in", values, "returnSellerId");
            return (Criteria) this;
        }

        public Criteria andReturnSellerIdNotIn(List<Long> values) {
            addCriterion("return_seller_id not in", values, "returnSellerId");
            return (Criteria) this;
        }

        public Criteria andReturnSellerIdBetween(Long value1, Long value2) {
            addCriterion("return_seller_id between", value1, value2, "returnSellerId");
            return (Criteria) this;
        }

        public Criteria andReturnSellerIdNotBetween(Long value1, Long value2) {
            addCriterion("return_seller_id not between", value1, value2, "returnSellerId");
            return (Criteria) this;
        }

        public Criteria andReturnCabinetIdIsNull() {
            addCriterion("return_cabinet_id is null");
            return (Criteria) this;
        }

        public Criteria andReturnCabinetIdIsNotNull() {
            addCriterion("return_cabinet_id is not null");
            return (Criteria) this;
        }

        public Criteria andReturnCabinetIdEqualTo(Long value) {
            addCriterion("return_cabinet_id =", value, "returnCabinetId");
            return (Criteria) this;
        }

        public Criteria andReturnCabinetIdNotEqualTo(Long value) {
            addCriterion("return_cabinet_id <>", value, "returnCabinetId");
            return (Criteria) this;
        }

        public Criteria andReturnCabinetIdGreaterThan(Long value) {
            addCriterion("return_cabinet_id >", value, "returnCabinetId");
            return (Criteria) this;
        }

        public Criteria andReturnCabinetIdGreaterThanOrEqualTo(Long value) {
            addCriterion("return_cabinet_id >=", value, "returnCabinetId");
            return (Criteria) this;
        }

        public Criteria andReturnCabinetIdLessThan(Long value) {
            addCriterion("return_cabinet_id <", value, "returnCabinetId");
            return (Criteria) this;
        }

        public Criteria andReturnCabinetIdLessThanOrEqualTo(Long value) {
            addCriterion("return_cabinet_id <=", value, "returnCabinetId");
            return (Criteria) this;
        }

        public Criteria andReturnCabinetIdIn(List<Long> values) {
            addCriterion("return_cabinet_id in", values, "returnCabinetId");
            return (Criteria) this;
        }

        public Criteria andReturnCabinetIdNotIn(List<Long> values) {
            addCriterion("return_cabinet_id not in", values, "returnCabinetId");
            return (Criteria) this;
        }

        public Criteria andReturnCabinetIdBetween(Long value1, Long value2) {
            addCriterion("return_cabinet_id between", value1, value2, "returnCabinetId");
            return (Criteria) this;
        }

        public Criteria andReturnCabinetIdNotBetween(Long value1, Long value2) {
            addCriterion("return_cabinet_id not between", value1, value2, "returnCabinetId");
            return (Criteria) this;
        }

        public Criteria andReturnSysCodeIsNull() {
            addCriterion("return_sys_code is null");
            return (Criteria) this;
        }

        public Criteria andReturnSysCodeIsNotNull() {
            addCriterion("return_sys_code is not null");
            return (Criteria) this;
        }

        public Criteria andReturnSysCodeEqualTo(String value) {
            addCriterion("return_sys_code =", value, "returnSysCode");
            return (Criteria) this;
        }

        public Criteria andReturnSysCodeNotEqualTo(String value) {
            addCriterion("return_sys_code <>", value, "returnSysCode");
            return (Criteria) this;
        }

        public Criteria andReturnSysCodeGreaterThan(String value) {
            addCriterion("return_sys_code >", value, "returnSysCode");
            return (Criteria) this;
        }

        public Criteria andReturnSysCodeGreaterThanOrEqualTo(String value) {
            addCriterion("return_sys_code >=", value, "returnSysCode");
            return (Criteria) this;
        }

        public Criteria andReturnSysCodeLessThan(String value) {
            addCriterion("return_sys_code <", value, "returnSysCode");
            return (Criteria) this;
        }

        public Criteria andReturnSysCodeLessThanOrEqualTo(String value) {
            addCriterion("return_sys_code <=", value, "returnSysCode");
            return (Criteria) this;
        }

        public Criteria andReturnSysCodeLike(String value) {
            addCriterion("return_sys_code like", value, "returnSysCode");
            return (Criteria) this;
        }

        public Criteria andReturnSysCodeNotLike(String value) {
            addCriterion("return_sys_code not like", value, "returnSysCode");
            return (Criteria) this;
        }

        public Criteria andReturnSysCodeIn(List<String> values) {
            addCriterion("return_sys_code in", values, "returnSysCode");
            return (Criteria) this;
        }

        public Criteria andReturnSysCodeNotIn(List<String> values) {
            addCriterion("return_sys_code not in", values, "returnSysCode");
            return (Criteria) this;
        }

        public Criteria andReturnSysCodeBetween(String value1, String value2) {
            addCriterion("return_sys_code between", value1, value2, "returnSysCode");
            return (Criteria) this;
        }

        public Criteria andReturnSysCodeNotBetween(String value1, String value2) {
            addCriterion("return_sys_code not between", value1, value2, "returnSysCode");
            return (Criteria) this;
        }

        public Criteria andBeginLocationDetailsIsNull() {
            addCriterion("begin_location_details is null");
            return (Criteria) this;
        }

        public Criteria andBeginLocationDetailsIsNotNull() {
            addCriterion("begin_location_details is not null");
            return (Criteria) this;
        }

        public Criteria andBeginLocationDetailsEqualTo(String value) {
            addCriterion("begin_location_details =", value, "beginLocationDetails");
            return (Criteria) this;
        }

        public Criteria andBeginLocationDetailsNotEqualTo(String value) {
            addCriterion("begin_location_details <>", value, "beginLocationDetails");
            return (Criteria) this;
        }

        public Criteria andBeginLocationDetailsGreaterThan(String value) {
            addCriterion("begin_location_details >", value, "beginLocationDetails");
            return (Criteria) this;
        }

        public Criteria andBeginLocationDetailsGreaterThanOrEqualTo(String value) {
            addCriterion("begin_location_details >=", value, "beginLocationDetails");
            return (Criteria) this;
        }

        public Criteria andBeginLocationDetailsLessThan(String value) {
            addCriterion("begin_location_details <", value, "beginLocationDetails");
            return (Criteria) this;
        }

        public Criteria andBeginLocationDetailsLessThanOrEqualTo(String value) {
            addCriterion("begin_location_details <=", value, "beginLocationDetails");
            return (Criteria) this;
        }

        public Criteria andBeginLocationDetailsLike(String value) {
            addCriterion("begin_location_details like", value, "beginLocationDetails");
            return (Criteria) this;
        }

        public Criteria andBeginLocationDetailsNotLike(String value) {
            addCriterion("begin_location_details not like", value, "beginLocationDetails");
            return (Criteria) this;
        }

        public Criteria andBeginLocationDetailsIn(List<String> values) {
            addCriterion("begin_location_details in", values, "beginLocationDetails");
            return (Criteria) this;
        }

        public Criteria andBeginLocationDetailsNotIn(List<String> values) {
            addCriterion("begin_location_details not in", values, "beginLocationDetails");
            return (Criteria) this;
        }

        public Criteria andBeginLocationDetailsBetween(String value1, String value2) {
            addCriterion("begin_location_details between", value1, value2, "beginLocationDetails");
            return (Criteria) this;
        }

        public Criteria andBeginLocationDetailsNotBetween(String value1, String value2) {
            addCriterion("begin_location_details not between", value1, value2, "beginLocationDetails");
            return (Criteria) this;
        }

        public Criteria andEndLocaitonDetailsIsNull() {
            addCriterion("end_locaiton_details is null");
            return (Criteria) this;
        }

        public Criteria andEndLocaitonDetailsIsNotNull() {
            addCriterion("end_locaiton_details is not null");
            return (Criteria) this;
        }

        public Criteria andEndLocaitonDetailsEqualTo(String value) {
            addCriterion("end_locaiton_details =", value, "endLocaitonDetails");
            return (Criteria) this;
        }

        public Criteria andEndLocaitonDetailsNotEqualTo(String value) {
            addCriterion("end_locaiton_details <>", value, "endLocaitonDetails");
            return (Criteria) this;
        }

        public Criteria andEndLocaitonDetailsGreaterThan(String value) {
            addCriterion("end_locaiton_details >", value, "endLocaitonDetails");
            return (Criteria) this;
        }

        public Criteria andEndLocaitonDetailsGreaterThanOrEqualTo(String value) {
            addCriterion("end_locaiton_details >=", value, "endLocaitonDetails");
            return (Criteria) this;
        }

        public Criteria andEndLocaitonDetailsLessThan(String value) {
            addCriterion("end_locaiton_details <", value, "endLocaitonDetails");
            return (Criteria) this;
        }

        public Criteria andEndLocaitonDetailsLessThanOrEqualTo(String value) {
            addCriterion("end_locaiton_details <=", value, "endLocaitonDetails");
            return (Criteria) this;
        }

        public Criteria andEndLocaitonDetailsLike(String value) {
            addCriterion("end_locaiton_details like", value, "endLocaitonDetails");
            return (Criteria) this;
        }

        public Criteria andEndLocaitonDetailsNotLike(String value) {
            addCriterion("end_locaiton_details not like", value, "endLocaitonDetails");
            return (Criteria) this;
        }

        public Criteria andEndLocaitonDetailsIn(List<String> values) {
            addCriterion("end_locaiton_details in", values, "endLocaitonDetails");
            return (Criteria) this;
        }

        public Criteria andEndLocaitonDetailsNotIn(List<String> values) {
            addCriterion("end_locaiton_details not in", values, "endLocaitonDetails");
            return (Criteria) this;
        }

        public Criteria andEndLocaitonDetailsBetween(String value1, String value2) {
            addCriterion("end_locaiton_details between", value1, value2, "endLocaitonDetails");
            return (Criteria) this;
        }

        public Criteria andEndLocaitonDetailsNotBetween(String value1, String value2) {
            addCriterion("end_locaiton_details not between", value1, value2, "endLocaitonDetails");
            return (Criteria) this;
        }

        public Criteria andPayPriceIsNull() {
            addCriterion("pay_price is null");
            return (Criteria) this;
        }

        public Criteria andPayPriceIsNotNull() {
            addCriterion("pay_price is not null");
            return (Criteria) this;
        }

        public Criteria andPayPriceEqualTo(BigDecimal value) {
            addCriterion("pay_price =", value, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceNotEqualTo(BigDecimal value) {
            addCriterion("pay_price <>", value, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceGreaterThan(BigDecimal value) {
            addCriterion("pay_price >", value, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_price >=", value, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceLessThan(BigDecimal value) {
            addCriterion("pay_price <", value, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_price <=", value, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceIn(List<BigDecimal> values) {
            addCriterion("pay_price in", values, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceNotIn(List<BigDecimal> values) {
            addCriterion("pay_price not in", values, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_price between", value1, value2, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_price not between", value1, value2, "payPrice");
            return (Criteria) this;
        }

        public Criteria andBorrowChannelIsNull() {
            addCriterion("borrow_channel is null");
            return (Criteria) this;
        }

        public Criteria andBorrowChannelIsNotNull() {
            addCriterion("borrow_channel is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowChannelEqualTo(Integer value) {
            addCriterion("borrow_channel =", value, "borrowChannel");
            return (Criteria) this;
        }

        public Criteria andBorrowChannelNotEqualTo(Integer value) {
            addCriterion("borrow_channel <>", value, "borrowChannel");
            return (Criteria) this;
        }

        public Criteria andBorrowChannelGreaterThan(Integer value) {
            addCriterion("borrow_channel >", value, "borrowChannel");
            return (Criteria) this;
        }

        public Criteria andBorrowChannelGreaterThanOrEqualTo(Integer value) {
            addCriterion("borrow_channel >=", value, "borrowChannel");
            return (Criteria) this;
        }

        public Criteria andBorrowChannelLessThan(Integer value) {
            addCriterion("borrow_channel <", value, "borrowChannel");
            return (Criteria) this;
        }

        public Criteria andBorrowChannelLessThanOrEqualTo(Integer value) {
            addCriterion("borrow_channel <=", value, "borrowChannel");
            return (Criteria) this;
        }

        public Criteria andBorrowChannelIn(List<Integer> values) {
            addCriterion("borrow_channel in", values, "borrowChannel");
            return (Criteria) this;
        }

        public Criteria andBorrowChannelNotIn(List<Integer> values) {
            addCriterion("borrow_channel not in", values, "borrowChannel");
            return (Criteria) this;
        }

        public Criteria andBorrowChannelBetween(Integer value1, Integer value2) {
            addCriterion("borrow_channel between", value1, value2, "borrowChannel");
            return (Criteria) this;
        }

        public Criteria andBorrowChannelNotBetween(Integer value1, Integer value2) {
            addCriterion("borrow_channel not between", value1, value2, "borrowChannel");
            return (Criteria) this;
        }

        public Criteria andReturnChannelIsNull() {
            addCriterion("return_channel is null");
            return (Criteria) this;
        }

        public Criteria andReturnChannelIsNotNull() {
            addCriterion("return_channel is not null");
            return (Criteria) this;
        }

        public Criteria andReturnChannelEqualTo(Integer value) {
            addCriterion("return_channel =", value, "returnChannel");
            return (Criteria) this;
        }

        public Criteria andReturnChannelNotEqualTo(Integer value) {
            addCriterion("return_channel <>", value, "returnChannel");
            return (Criteria) this;
        }

        public Criteria andReturnChannelGreaterThan(Integer value) {
            addCriterion("return_channel >", value, "returnChannel");
            return (Criteria) this;
        }

        public Criteria andReturnChannelGreaterThanOrEqualTo(Integer value) {
            addCriterion("return_channel >=", value, "returnChannel");
            return (Criteria) this;
        }

        public Criteria andReturnChannelLessThan(Integer value) {
            addCriterion("return_channel <", value, "returnChannel");
            return (Criteria) this;
        }

        public Criteria andReturnChannelLessThanOrEqualTo(Integer value) {
            addCriterion("return_channel <=", value, "returnChannel");
            return (Criteria) this;
        }

        public Criteria andReturnChannelIn(List<Integer> values) {
            addCriterion("return_channel in", values, "returnChannel");
            return (Criteria) this;
        }

        public Criteria andReturnChannelNotIn(List<Integer> values) {
            addCriterion("return_channel not in", values, "returnChannel");
            return (Criteria) this;
        }

        public Criteria andReturnChannelBetween(Integer value1, Integer value2) {
            addCriterion("return_channel between", value1, value2, "returnChannel");
            return (Criteria) this;
        }

        public Criteria andReturnChannelNotBetween(Integer value1, Integer value2) {
            addCriterion("return_channel not between", value1, value2, "returnChannel");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("pay_type is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(String value) {
            addCriterion("pay_type =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(String value) {
            addCriterion("pay_type <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(String value) {
            addCriterion("pay_type >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(String value) {
            addCriterion("pay_type >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(String value) {
            addCriterion("pay_type <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(String value) {
            addCriterion("pay_type <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLike(String value) {
            addCriterion("pay_type like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotLike(String value) {
            addCriterion("pay_type not like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<String> values) {
            addCriterion("pay_type in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<String> values) {
            addCriterion("pay_type not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(String value1, String value2) {
            addCriterion("pay_type between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(String value1, String value2) {
            addCriterion("pay_type not between", value1, value2, "payType");
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

        public Criteria andCountryIsNull() {
            addCriterion("country is null");
            return (Criteria) this;
        }

        public Criteria andCountryIsNotNull() {
            addCriterion("country is not null");
            return (Criteria) this;
        }

        public Criteria andCountryEqualTo(String value) {
            addCriterion("country =", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotEqualTo(String value) {
            addCriterion("country <>", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThan(String value) {
            addCriterion("country >", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThanOrEqualTo(String value) {
            addCriterion("country >=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThan(String value) {
            addCriterion("country <", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThanOrEqualTo(String value) {
            addCriterion("country <=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLike(String value) {
            addCriterion("country like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotLike(String value) {
            addCriterion("country not like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryIn(List<String> values) {
            addCriterion("country in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotIn(List<String> values) {
            addCriterion("country not in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryBetween(String value1, String value2) {
            addCriterion("country between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotBetween(String value1, String value2) {
            addCriterion("country not between", value1, value2, "country");
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

        public Criteria andReturnTypeIsNull() {
            addCriterion("return_type is null");
            return (Criteria) this;
        }

        public Criteria andReturnTypeIsNotNull() {
            addCriterion("return_type is not null");
            return (Criteria) this;
        }

        public Criteria andReturnTypeEqualTo(String value) {
            addCriterion("return_type =", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeNotEqualTo(String value) {
            addCriterion("return_type <>", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeGreaterThan(String value) {
            addCriterion("return_type >", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeGreaterThanOrEqualTo(String value) {
            addCriterion("return_type >=", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeLessThan(String value) {
            addCriterion("return_type <", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeLessThanOrEqualTo(String value) {
            addCriterion("return_type <=", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeLike(String value) {
            addCriterion("return_type like", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeNotLike(String value) {
            addCriterion("return_type not like", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeIn(List<String> values) {
            addCriterion("return_type in", values, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeNotIn(List<String> values) {
            addCriterion("return_type not in", values, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeBetween(String value1, String value2) {
            addCriterion("return_type between", value1, value2, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeNotBetween(String value1, String value2) {
            addCriterion("return_type not between", value1, value2, "returnType");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetStatusCodeIsNull() {
            addCriterion("borrow_cabinet_status_code is null");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetStatusCodeIsNotNull() {
            addCriterion("borrow_cabinet_status_code is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetStatusCodeEqualTo(String value) {
            addCriterion("borrow_cabinet_status_code =", value, "borrowCabinetStatusCode");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetStatusCodeNotEqualTo(String value) {
            addCriterion("borrow_cabinet_status_code <>", value, "borrowCabinetStatusCode");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetStatusCodeGreaterThan(String value) {
            addCriterion("borrow_cabinet_status_code >", value, "borrowCabinetStatusCode");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetStatusCodeGreaterThanOrEqualTo(String value) {
            addCriterion("borrow_cabinet_status_code >=", value, "borrowCabinetStatusCode");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetStatusCodeLessThan(String value) {
            addCriterion("borrow_cabinet_status_code <", value, "borrowCabinetStatusCode");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetStatusCodeLessThanOrEqualTo(String value) {
            addCriterion("borrow_cabinet_status_code <=", value, "borrowCabinetStatusCode");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetStatusCodeLike(String value) {
            addCriterion("borrow_cabinet_status_code like", value, "borrowCabinetStatusCode");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetStatusCodeNotLike(String value) {
            addCriterion("borrow_cabinet_status_code not like", value, "borrowCabinetStatusCode");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetStatusCodeIn(List<String> values) {
            addCriterion("borrow_cabinet_status_code in", values, "borrowCabinetStatusCode");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetStatusCodeNotIn(List<String> values) {
            addCriterion("borrow_cabinet_status_code not in", values, "borrowCabinetStatusCode");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetStatusCodeBetween(String value1, String value2) {
            addCriterion("borrow_cabinet_status_code between", value1, value2, "borrowCabinetStatusCode");
            return (Criteria) this;
        }

        public Criteria andBorrowCabinetStatusCodeNotBetween(String value1, String value2) {
            addCriterion("borrow_cabinet_status_code not between", value1, value2, "borrowCabinetStatusCode");
            return (Criteria) this;
        }

        public Criteria andClientSourceIsNull() {
            addCriterion("client_source is null");
            return (Criteria) this;
        }

        public Criteria andClientSourceIsNotNull() {
            addCriterion("client_source is not null");
            return (Criteria) this;
        }

        public Criteria andClientSourceEqualTo(String value) {
            addCriterion("client_source =", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceNotEqualTo(String value) {
            addCriterion("client_source <>", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceGreaterThan(String value) {
            addCriterion("client_source >", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceGreaterThanOrEqualTo(String value) {
            addCriterion("client_source >=", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceLessThan(String value) {
            addCriterion("client_source <", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceLessThanOrEqualTo(String value) {
            addCriterion("client_source <=", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceLike(String value) {
            addCriterion("client_source like", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceNotLike(String value) {
            addCriterion("client_source not like", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceIn(List<String> values) {
            addCriterion("client_source in", values, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceNotIn(List<String> values) {
            addCriterion("client_source not in", values, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceBetween(String value1, String value2) {
            addCriterion("client_source between", value1, value2, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceNotBetween(String value1, String value2) {
            addCriterion("client_source not between", value1, value2, "clientSource");
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