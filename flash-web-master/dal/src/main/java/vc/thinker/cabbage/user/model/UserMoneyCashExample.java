package vc.thinker.cabbage.user.model;

import com.sinco.mybatis.dal.core.AbstractExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vc.thinker.cabbage.user.bo.UserMoneyCashBO;

public class UserMoneyCashExample extends AbstractExample<UserMoneyCashBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public UserMoneyCashExample() {
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

        public Criteria andCashSnIsNull() {
            addCriterion("cash_sn is null");
            return (Criteria) this;
        }

        public Criteria andCashSnIsNotNull() {
            addCriterion("cash_sn is not null");
            return (Criteria) this;
        }

        public Criteria andCashSnEqualTo(String value) {
            addCriterion("cash_sn =", value, "cashSn");
            return (Criteria) this;
        }

        public Criteria andCashSnNotEqualTo(String value) {
            addCriterion("cash_sn <>", value, "cashSn");
            return (Criteria) this;
        }

        public Criteria andCashSnGreaterThan(String value) {
            addCriterion("cash_sn >", value, "cashSn");
            return (Criteria) this;
        }

        public Criteria andCashSnGreaterThanOrEqualTo(String value) {
            addCriterion("cash_sn >=", value, "cashSn");
            return (Criteria) this;
        }

        public Criteria andCashSnLessThan(String value) {
            addCriterion("cash_sn <", value, "cashSn");
            return (Criteria) this;
        }

        public Criteria andCashSnLessThanOrEqualTo(String value) {
            addCriterion("cash_sn <=", value, "cashSn");
            return (Criteria) this;
        }

        public Criteria andCashSnLike(String value) {
            addCriterion("cash_sn like", value, "cashSn");
            return (Criteria) this;
        }

        public Criteria andCashSnNotLike(String value) {
            addCriterion("cash_sn not like", value, "cashSn");
            return (Criteria) this;
        }

        public Criteria andCashSnIn(List<String> values) {
            addCriterion("cash_sn in", values, "cashSn");
            return (Criteria) this;
        }

        public Criteria andCashSnNotIn(List<String> values) {
            addCriterion("cash_sn not in", values, "cashSn");
            return (Criteria) this;
        }

        public Criteria andCashSnBetween(String value1, String value2) {
            addCriterion("cash_sn between", value1, value2, "cashSn");
            return (Criteria) this;
        }

        public Criteria andCashSnNotBetween(String value1, String value2) {
            addCriterion("cash_sn not between", value1, value2, "cashSn");
            return (Criteria) this;
        }

        public Criteria andCashUserIdIsNull() {
            addCriterion("cash_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCashUserIdIsNotNull() {
            addCriterion("cash_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCashUserIdEqualTo(Long value) {
            addCriterion("cash_user_id =", value, "cashUserId");
            return (Criteria) this;
        }

        public Criteria andCashUserIdNotEqualTo(Long value) {
            addCriterion("cash_user_id <>", value, "cashUserId");
            return (Criteria) this;
        }

        public Criteria andCashUserIdGreaterThan(Long value) {
            addCriterion("cash_user_id >", value, "cashUserId");
            return (Criteria) this;
        }

        public Criteria andCashUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("cash_user_id >=", value, "cashUserId");
            return (Criteria) this;
        }

        public Criteria andCashUserIdLessThan(Long value) {
            addCriterion("cash_user_id <", value, "cashUserId");
            return (Criteria) this;
        }

        public Criteria andCashUserIdLessThanOrEqualTo(Long value) {
            addCriterion("cash_user_id <=", value, "cashUserId");
            return (Criteria) this;
        }

        public Criteria andCashUserIdIn(List<Long> values) {
            addCriterion("cash_user_id in", values, "cashUserId");
            return (Criteria) this;
        }

        public Criteria andCashUserIdNotIn(List<Long> values) {
            addCriterion("cash_user_id not in", values, "cashUserId");
            return (Criteria) this;
        }

        public Criteria andCashUserIdBetween(Long value1, Long value2) {
            addCriterion("cash_user_id between", value1, value2, "cashUserId");
            return (Criteria) this;
        }

        public Criteria andCashUserIdNotBetween(Long value1, Long value2) {
            addCriterion("cash_user_id not between", value1, value2, "cashUserId");
            return (Criteria) this;
        }

        public Criteria andCashUserTypeIsNull() {
            addCriterion("cash_user_type is null");
            return (Criteria) this;
        }

        public Criteria andCashUserTypeIsNotNull() {
            addCriterion("cash_user_type is not null");
            return (Criteria) this;
        }

        public Criteria andCashUserTypeEqualTo(String value) {
            addCriterion("cash_user_type =", value, "cashUserType");
            return (Criteria) this;
        }

        public Criteria andCashUserTypeNotEqualTo(String value) {
            addCriterion("cash_user_type <>", value, "cashUserType");
            return (Criteria) this;
        }

        public Criteria andCashUserTypeGreaterThan(String value) {
            addCriterion("cash_user_type >", value, "cashUserType");
            return (Criteria) this;
        }

        public Criteria andCashUserTypeGreaterThanOrEqualTo(String value) {
            addCriterion("cash_user_type >=", value, "cashUserType");
            return (Criteria) this;
        }

        public Criteria andCashUserTypeLessThan(String value) {
            addCriterion("cash_user_type <", value, "cashUserType");
            return (Criteria) this;
        }

        public Criteria andCashUserTypeLessThanOrEqualTo(String value) {
            addCriterion("cash_user_type <=", value, "cashUserType");
            return (Criteria) this;
        }

        public Criteria andCashUserTypeLike(String value) {
            addCriterion("cash_user_type like", value, "cashUserType");
            return (Criteria) this;
        }

        public Criteria andCashUserTypeNotLike(String value) {
            addCriterion("cash_user_type not like", value, "cashUserType");
            return (Criteria) this;
        }

        public Criteria andCashUserTypeIn(List<String> values) {
            addCriterion("cash_user_type in", values, "cashUserType");
            return (Criteria) this;
        }

        public Criteria andCashUserTypeNotIn(List<String> values) {
            addCriterion("cash_user_type not in", values, "cashUserType");
            return (Criteria) this;
        }

        public Criteria andCashUserTypeBetween(String value1, String value2) {
            addCriterion("cash_user_type between", value1, value2, "cashUserType");
            return (Criteria) this;
        }

        public Criteria andCashUserTypeNotBetween(String value1, String value2) {
            addCriterion("cash_user_type not between", value1, value2, "cashUserType");
            return (Criteria) this;
        }

        public Criteria andCashAmountIsNull() {
            addCriterion("cash_amount is null");
            return (Criteria) this;
        }

        public Criteria andCashAmountIsNotNull() {
            addCriterion("cash_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCashAmountEqualTo(BigDecimal value) {
            addCriterion("cash_amount =", value, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountNotEqualTo(BigDecimal value) {
            addCriterion("cash_amount <>", value, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountGreaterThan(BigDecimal value) {
            addCriterion("cash_amount >", value, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cash_amount >=", value, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountLessThan(BigDecimal value) {
            addCriterion("cash_amount <", value, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cash_amount <=", value, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountIn(List<BigDecimal> values) {
            addCriterion("cash_amount in", values, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountNotIn(List<BigDecimal> values) {
            addCriterion("cash_amount not in", values, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cash_amount between", value1, value2, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cash_amount not between", value1, value2, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashUserNameIsNull() {
            addCriterion("cash_user_name is null");
            return (Criteria) this;
        }

        public Criteria andCashUserNameIsNotNull() {
            addCriterion("cash_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andCashUserNameEqualTo(String value) {
            addCriterion("cash_user_name =", value, "cashUserName");
            return (Criteria) this;
        }

        public Criteria andCashUserNameNotEqualTo(String value) {
            addCriterion("cash_user_name <>", value, "cashUserName");
            return (Criteria) this;
        }

        public Criteria andCashUserNameGreaterThan(String value) {
            addCriterion("cash_user_name >", value, "cashUserName");
            return (Criteria) this;
        }

        public Criteria andCashUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("cash_user_name >=", value, "cashUserName");
            return (Criteria) this;
        }

        public Criteria andCashUserNameLessThan(String value) {
            addCriterion("cash_user_name <", value, "cashUserName");
            return (Criteria) this;
        }

        public Criteria andCashUserNameLessThanOrEqualTo(String value) {
            addCriterion("cash_user_name <=", value, "cashUserName");
            return (Criteria) this;
        }

        public Criteria andCashUserNameLike(String value) {
            addCriterion("cash_user_name like", value, "cashUserName");
            return (Criteria) this;
        }

        public Criteria andCashUserNameNotLike(String value) {
            addCriterion("cash_user_name not like", value, "cashUserName");
            return (Criteria) this;
        }

        public Criteria andCashUserNameIn(List<String> values) {
            addCriterion("cash_user_name in", values, "cashUserName");
            return (Criteria) this;
        }

        public Criteria andCashUserNameNotIn(List<String> values) {
            addCriterion("cash_user_name not in", values, "cashUserName");
            return (Criteria) this;
        }

        public Criteria andCashUserNameBetween(String value1, String value2) {
            addCriterion("cash_user_name between", value1, value2, "cashUserName");
            return (Criteria) this;
        }

        public Criteria andCashUserNameNotBetween(String value1, String value2) {
            addCriterion("cash_user_name not between", value1, value2, "cashUserName");
            return (Criteria) this;
        }

        public Criteria andCashAccountIsNull() {
            addCriterion("cash_account is null");
            return (Criteria) this;
        }

        public Criteria andCashAccountIsNotNull() {
            addCriterion("cash_account is not null");
            return (Criteria) this;
        }

        public Criteria andCashAccountEqualTo(String value) {
            addCriterion("cash_account =", value, "cashAccount");
            return (Criteria) this;
        }

        public Criteria andCashAccountNotEqualTo(String value) {
            addCriterion("cash_account <>", value, "cashAccount");
            return (Criteria) this;
        }

        public Criteria andCashAccountGreaterThan(String value) {
            addCriterion("cash_account >", value, "cashAccount");
            return (Criteria) this;
        }

        public Criteria andCashAccountGreaterThanOrEqualTo(String value) {
            addCriterion("cash_account >=", value, "cashAccount");
            return (Criteria) this;
        }

        public Criteria andCashAccountLessThan(String value) {
            addCriterion("cash_account <", value, "cashAccount");
            return (Criteria) this;
        }

        public Criteria andCashAccountLessThanOrEqualTo(String value) {
            addCriterion("cash_account <=", value, "cashAccount");
            return (Criteria) this;
        }

        public Criteria andCashAccountLike(String value) {
            addCriterion("cash_account like", value, "cashAccount");
            return (Criteria) this;
        }

        public Criteria andCashAccountNotLike(String value) {
            addCriterion("cash_account not like", value, "cashAccount");
            return (Criteria) this;
        }

        public Criteria andCashAccountIn(List<String> values) {
            addCriterion("cash_account in", values, "cashAccount");
            return (Criteria) this;
        }

        public Criteria andCashAccountNotIn(List<String> values) {
            addCriterion("cash_account not in", values, "cashAccount");
            return (Criteria) this;
        }

        public Criteria andCashAccountBetween(String value1, String value2) {
            addCriterion("cash_account between", value1, value2, "cashAccount");
            return (Criteria) this;
        }

        public Criteria andCashAccountNotBetween(String value1, String value2) {
            addCriterion("cash_account not between", value1, value2, "cashAccount");
            return (Criteria) this;
        }

        public Criteria andCashStatusIsNull() {
            addCriterion("cash_status is null");
            return (Criteria) this;
        }

        public Criteria andCashStatusIsNotNull() {
            addCriterion("cash_status is not null");
            return (Criteria) this;
        }

        public Criteria andCashStatusEqualTo(String value) {
            addCriterion("cash_status =", value, "cashStatus");
            return (Criteria) this;
        }

        public Criteria andCashStatusNotEqualTo(String value) {
            addCriterion("cash_status <>", value, "cashStatus");
            return (Criteria) this;
        }

        public Criteria andCashStatusGreaterThan(String value) {
            addCriterion("cash_status >", value, "cashStatus");
            return (Criteria) this;
        }

        public Criteria andCashStatusGreaterThanOrEqualTo(String value) {
            addCriterion("cash_status >=", value, "cashStatus");
            return (Criteria) this;
        }

        public Criteria andCashStatusLessThan(String value) {
            addCriterion("cash_status <", value, "cashStatus");
            return (Criteria) this;
        }

        public Criteria andCashStatusLessThanOrEqualTo(String value) {
            addCriterion("cash_status <=", value, "cashStatus");
            return (Criteria) this;
        }

        public Criteria andCashStatusLike(String value) {
            addCriterion("cash_status like", value, "cashStatus");
            return (Criteria) this;
        }

        public Criteria andCashStatusNotLike(String value) {
            addCriterion("cash_status not like", value, "cashStatus");
            return (Criteria) this;
        }

        public Criteria andCashStatusIn(List<String> values) {
            addCriterion("cash_status in", values, "cashStatus");
            return (Criteria) this;
        }

        public Criteria andCashStatusNotIn(List<String> values) {
            addCriterion("cash_status not in", values, "cashStatus");
            return (Criteria) this;
        }

        public Criteria andCashStatusBetween(String value1, String value2) {
            addCriterion("cash_status between", value1, value2, "cashStatus");
            return (Criteria) this;
        }

        public Criteria andCashStatusNotBetween(String value1, String value2) {
            addCriterion("cash_status not between", value1, value2, "cashStatus");
            return (Criteria) this;
        }

        public Criteria andCashBankIsNull() {
            addCriterion("cash_bank is null");
            return (Criteria) this;
        }

        public Criteria andCashBankIsNotNull() {
            addCriterion("cash_bank is not null");
            return (Criteria) this;
        }

        public Criteria andCashBankEqualTo(String value) {
            addCriterion("cash_bank =", value, "cashBank");
            return (Criteria) this;
        }

        public Criteria andCashBankNotEqualTo(String value) {
            addCriterion("cash_bank <>", value, "cashBank");
            return (Criteria) this;
        }

        public Criteria andCashBankGreaterThan(String value) {
            addCriterion("cash_bank >", value, "cashBank");
            return (Criteria) this;
        }

        public Criteria andCashBankGreaterThanOrEqualTo(String value) {
            addCriterion("cash_bank >=", value, "cashBank");
            return (Criteria) this;
        }

        public Criteria andCashBankLessThan(String value) {
            addCriterion("cash_bank <", value, "cashBank");
            return (Criteria) this;
        }

        public Criteria andCashBankLessThanOrEqualTo(String value) {
            addCriterion("cash_bank <=", value, "cashBank");
            return (Criteria) this;
        }

        public Criteria andCashBankLike(String value) {
            addCriterion("cash_bank like", value, "cashBank");
            return (Criteria) this;
        }

        public Criteria andCashBankNotLike(String value) {
            addCriterion("cash_bank not like", value, "cashBank");
            return (Criteria) this;
        }

        public Criteria andCashBankIn(List<String> values) {
            addCriterion("cash_bank in", values, "cashBank");
            return (Criteria) this;
        }

        public Criteria andCashBankNotIn(List<String> values) {
            addCriterion("cash_bank not in", values, "cashBank");
            return (Criteria) this;
        }

        public Criteria andCashBankBetween(String value1, String value2) {
            addCriterion("cash_bank between", value1, value2, "cashBank");
            return (Criteria) this;
        }

        public Criteria andCashBankNotBetween(String value1, String value2) {
            addCriterion("cash_bank not between", value1, value2, "cashBank");
            return (Criteria) this;
        }

        public Criteria andCashInfoIsNull() {
            addCriterion("cash_info is null");
            return (Criteria) this;
        }

        public Criteria andCashInfoIsNotNull() {
            addCriterion("cash_info is not null");
            return (Criteria) this;
        }

        public Criteria andCashInfoEqualTo(String value) {
            addCriterion("cash_info =", value, "cashInfo");
            return (Criteria) this;
        }

        public Criteria andCashInfoNotEqualTo(String value) {
            addCriterion("cash_info <>", value, "cashInfo");
            return (Criteria) this;
        }

        public Criteria andCashInfoGreaterThan(String value) {
            addCriterion("cash_info >", value, "cashInfo");
            return (Criteria) this;
        }

        public Criteria andCashInfoGreaterThanOrEqualTo(String value) {
            addCriterion("cash_info >=", value, "cashInfo");
            return (Criteria) this;
        }

        public Criteria andCashInfoLessThan(String value) {
            addCriterion("cash_info <", value, "cashInfo");
            return (Criteria) this;
        }

        public Criteria andCashInfoLessThanOrEqualTo(String value) {
            addCriterion("cash_info <=", value, "cashInfo");
            return (Criteria) this;
        }

        public Criteria andCashInfoLike(String value) {
            addCriterion("cash_info like", value, "cashInfo");
            return (Criteria) this;
        }

        public Criteria andCashInfoNotLike(String value) {
            addCriterion("cash_info not like", value, "cashInfo");
            return (Criteria) this;
        }

        public Criteria andCashInfoIn(List<String> values) {
            addCriterion("cash_info in", values, "cashInfo");
            return (Criteria) this;
        }

        public Criteria andCashInfoNotIn(List<String> values) {
            addCriterion("cash_info not in", values, "cashInfo");
            return (Criteria) this;
        }

        public Criteria andCashInfoBetween(String value1, String value2) {
            addCriterion("cash_info between", value1, value2, "cashInfo");
            return (Criteria) this;
        }

        public Criteria andCashInfoNotBetween(String value1, String value2) {
            addCriterion("cash_info not between", value1, value2, "cashInfo");
            return (Criteria) this;
        }

        public Criteria andCashPaymentTypeIsNull() {
            addCriterion("cash_payment_type is null");
            return (Criteria) this;
        }

        public Criteria andCashPaymentTypeIsNotNull() {
            addCriterion("cash_payment_type is not null");
            return (Criteria) this;
        }

        public Criteria andCashPaymentTypeEqualTo(String value) {
            addCriterion("cash_payment_type =", value, "cashPaymentType");
            return (Criteria) this;
        }

        public Criteria andCashPaymentTypeNotEqualTo(String value) {
            addCriterion("cash_payment_type <>", value, "cashPaymentType");
            return (Criteria) this;
        }

        public Criteria andCashPaymentTypeGreaterThan(String value) {
            addCriterion("cash_payment_type >", value, "cashPaymentType");
            return (Criteria) this;
        }

        public Criteria andCashPaymentTypeGreaterThanOrEqualTo(String value) {
            addCriterion("cash_payment_type >=", value, "cashPaymentType");
            return (Criteria) this;
        }

        public Criteria andCashPaymentTypeLessThan(String value) {
            addCriterion("cash_payment_type <", value, "cashPaymentType");
            return (Criteria) this;
        }

        public Criteria andCashPaymentTypeLessThanOrEqualTo(String value) {
            addCriterion("cash_payment_type <=", value, "cashPaymentType");
            return (Criteria) this;
        }

        public Criteria andCashPaymentTypeLike(String value) {
            addCriterion("cash_payment_type like", value, "cashPaymentType");
            return (Criteria) this;
        }

        public Criteria andCashPaymentTypeNotLike(String value) {
            addCriterion("cash_payment_type not like", value, "cashPaymentType");
            return (Criteria) this;
        }

        public Criteria andCashPaymentTypeIn(List<String> values) {
            addCriterion("cash_payment_type in", values, "cashPaymentType");
            return (Criteria) this;
        }

        public Criteria andCashPaymentTypeNotIn(List<String> values) {
            addCriterion("cash_payment_type not in", values, "cashPaymentType");
            return (Criteria) this;
        }

        public Criteria andCashPaymentTypeBetween(String value1, String value2) {
            addCriterion("cash_payment_type between", value1, value2, "cashPaymentType");
            return (Criteria) this;
        }

        public Criteria andCashPaymentTypeNotBetween(String value1, String value2) {
            addCriterion("cash_payment_type not between", value1, value2, "cashPaymentType");
            return (Criteria) this;
        }

        public Criteria andCashAdminIdIsNull() {
            addCriterion("cash_admin_id is null");
            return (Criteria) this;
        }

        public Criteria andCashAdminIdIsNotNull() {
            addCriterion("cash_admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andCashAdminIdEqualTo(Long value) {
            addCriterion("cash_admin_id =", value, "cashAdminId");
            return (Criteria) this;
        }

        public Criteria andCashAdminIdNotEqualTo(Long value) {
            addCriterion("cash_admin_id <>", value, "cashAdminId");
            return (Criteria) this;
        }

        public Criteria andCashAdminIdGreaterThan(Long value) {
            addCriterion("cash_admin_id >", value, "cashAdminId");
            return (Criteria) this;
        }

        public Criteria andCashAdminIdGreaterThanOrEqualTo(Long value) {
            addCriterion("cash_admin_id >=", value, "cashAdminId");
            return (Criteria) this;
        }

        public Criteria andCashAdminIdLessThan(Long value) {
            addCriterion("cash_admin_id <", value, "cashAdminId");
            return (Criteria) this;
        }

        public Criteria andCashAdminIdLessThanOrEqualTo(Long value) {
            addCriterion("cash_admin_id <=", value, "cashAdminId");
            return (Criteria) this;
        }

        public Criteria andCashAdminIdIn(List<Long> values) {
            addCriterion("cash_admin_id in", values, "cashAdminId");
            return (Criteria) this;
        }

        public Criteria andCashAdminIdNotIn(List<Long> values) {
            addCriterion("cash_admin_id not in", values, "cashAdminId");
            return (Criteria) this;
        }

        public Criteria andCashAdminIdBetween(Long value1, Long value2) {
            addCriterion("cash_admin_id between", value1, value2, "cashAdminId");
            return (Criteria) this;
        }

        public Criteria andCashAdminIdNotBetween(Long value1, Long value2) {
            addCriterion("cash_admin_id not between", value1, value2, "cashAdminId");
            return (Criteria) this;
        }

        public Criteria andCashAdminInfoIsNull() {
            addCriterion("cash_admin_info is null");
            return (Criteria) this;
        }

        public Criteria andCashAdminInfoIsNotNull() {
            addCriterion("cash_admin_info is not null");
            return (Criteria) this;
        }

        public Criteria andCashAdminInfoEqualTo(String value) {
            addCriterion("cash_admin_info =", value, "cashAdminInfo");
            return (Criteria) this;
        }

        public Criteria andCashAdminInfoNotEqualTo(String value) {
            addCriterion("cash_admin_info <>", value, "cashAdminInfo");
            return (Criteria) this;
        }

        public Criteria andCashAdminInfoGreaterThan(String value) {
            addCriterion("cash_admin_info >", value, "cashAdminInfo");
            return (Criteria) this;
        }

        public Criteria andCashAdminInfoGreaterThanOrEqualTo(String value) {
            addCriterion("cash_admin_info >=", value, "cashAdminInfo");
            return (Criteria) this;
        }

        public Criteria andCashAdminInfoLessThan(String value) {
            addCriterion("cash_admin_info <", value, "cashAdminInfo");
            return (Criteria) this;
        }

        public Criteria andCashAdminInfoLessThanOrEqualTo(String value) {
            addCriterion("cash_admin_info <=", value, "cashAdminInfo");
            return (Criteria) this;
        }

        public Criteria andCashAdminInfoLike(String value) {
            addCriterion("cash_admin_info like", value, "cashAdminInfo");
            return (Criteria) this;
        }

        public Criteria andCashAdminInfoNotLike(String value) {
            addCriterion("cash_admin_info not like", value, "cashAdminInfo");
            return (Criteria) this;
        }

        public Criteria andCashAdminInfoIn(List<String> values) {
            addCriterion("cash_admin_info in", values, "cashAdminInfo");
            return (Criteria) this;
        }

        public Criteria andCashAdminInfoNotIn(List<String> values) {
            addCriterion("cash_admin_info not in", values, "cashAdminInfo");
            return (Criteria) this;
        }

        public Criteria andCashAdminInfoBetween(String value1, String value2) {
            addCriterion("cash_admin_info between", value1, value2, "cashAdminInfo");
            return (Criteria) this;
        }

        public Criteria andCashAdminInfoNotBetween(String value1, String value2) {
            addCriterion("cash_admin_info not between", value1, value2, "cashAdminInfo");
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

        public Criteria andTransTimeIsNull() {
            addCriterion("trans_time is null");
            return (Criteria) this;
        }

        public Criteria andTransTimeIsNotNull() {
            addCriterion("trans_time is not null");
            return (Criteria) this;
        }

        public Criteria andTransTimeEqualTo(Date value) {
            addCriterion("trans_time =", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeNotEqualTo(Date value) {
            addCriterion("trans_time <>", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeGreaterThan(Date value) {
            addCriterion("trans_time >", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("trans_time >=", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeLessThan(Date value) {
            addCriterion("trans_time <", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeLessThanOrEqualTo(Date value) {
            addCriterion("trans_time <=", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeIn(List<Date> values) {
            addCriterion("trans_time in", values, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeNotIn(List<Date> values) {
            addCriterion("trans_time not in", values, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeBetween(Date value1, Date value2) {
            addCriterion("trans_time between", value1, value2, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeNotBetween(Date value1, Date value2) {
            addCriterion("trans_time not between", value1, value2, "transTime");
            return (Criteria) this;
        }

        public Criteria andPartnerTradeNoIsNull() {
            addCriterion("partner_trade_no is null");
            return (Criteria) this;
        }

        public Criteria andPartnerTradeNoIsNotNull() {
            addCriterion("partner_trade_no is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerTradeNoEqualTo(String value) {
            addCriterion("partner_trade_no =", value, "partnerTradeNo");
            return (Criteria) this;
        }

        public Criteria andPartnerTradeNoNotEqualTo(String value) {
            addCriterion("partner_trade_no <>", value, "partnerTradeNo");
            return (Criteria) this;
        }

        public Criteria andPartnerTradeNoGreaterThan(String value) {
            addCriterion("partner_trade_no >", value, "partnerTradeNo");
            return (Criteria) this;
        }

        public Criteria andPartnerTradeNoGreaterThanOrEqualTo(String value) {
            addCriterion("partner_trade_no >=", value, "partnerTradeNo");
            return (Criteria) this;
        }

        public Criteria andPartnerTradeNoLessThan(String value) {
            addCriterion("partner_trade_no <", value, "partnerTradeNo");
            return (Criteria) this;
        }

        public Criteria andPartnerTradeNoLessThanOrEqualTo(String value) {
            addCriterion("partner_trade_no <=", value, "partnerTradeNo");
            return (Criteria) this;
        }

        public Criteria andPartnerTradeNoLike(String value) {
            addCriterion("partner_trade_no like", value, "partnerTradeNo");
            return (Criteria) this;
        }

        public Criteria andPartnerTradeNoNotLike(String value) {
            addCriterion("partner_trade_no not like", value, "partnerTradeNo");
            return (Criteria) this;
        }

        public Criteria andPartnerTradeNoIn(List<String> values) {
            addCriterion("partner_trade_no in", values, "partnerTradeNo");
            return (Criteria) this;
        }

        public Criteria andPartnerTradeNoNotIn(List<String> values) {
            addCriterion("partner_trade_no not in", values, "partnerTradeNo");
            return (Criteria) this;
        }

        public Criteria andPartnerTradeNoBetween(String value1, String value2) {
            addCriterion("partner_trade_no between", value1, value2, "partnerTradeNo");
            return (Criteria) this;
        }

        public Criteria andPartnerTradeNoNotBetween(String value1, String value2) {
            addCriterion("partner_trade_no not between", value1, value2, "partnerTradeNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoIsNull() {
            addCriterion("payment_no is null");
            return (Criteria) this;
        }

        public Criteria andPaymentNoIsNotNull() {
            addCriterion("payment_no is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentNoEqualTo(String value) {
            addCriterion("payment_no =", value, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoNotEqualTo(String value) {
            addCriterion("payment_no <>", value, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoGreaterThan(String value) {
            addCriterion("payment_no >", value, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoGreaterThanOrEqualTo(String value) {
            addCriterion("payment_no >=", value, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoLessThan(String value) {
            addCriterion("payment_no <", value, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoLessThanOrEqualTo(String value) {
            addCriterion("payment_no <=", value, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoLike(String value) {
            addCriterion("payment_no like", value, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoNotLike(String value) {
            addCriterion("payment_no not like", value, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoIn(List<String> values) {
            addCriterion("payment_no in", values, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoNotIn(List<String> values) {
            addCriterion("payment_no not in", values, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoBetween(String value1, String value2) {
            addCriterion("payment_no between", value1, value2, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoNotBetween(String value1, String value2) {
            addCriterion("payment_no not between", value1, value2, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andCmmsAmtIsNull() {
            addCriterion("cmms_amt is null");
            return (Criteria) this;
        }

        public Criteria andCmmsAmtIsNotNull() {
            addCriterion("cmms_amt is not null");
            return (Criteria) this;
        }

        public Criteria andCmmsAmtEqualTo(Integer value) {
            addCriterion("cmms_amt =", value, "cmmsAmt");
            return (Criteria) this;
        }

        public Criteria andCmmsAmtNotEqualTo(Integer value) {
            addCriterion("cmms_amt <>", value, "cmmsAmt");
            return (Criteria) this;
        }

        public Criteria andCmmsAmtGreaterThan(Integer value) {
            addCriterion("cmms_amt >", value, "cmmsAmt");
            return (Criteria) this;
        }

        public Criteria andCmmsAmtGreaterThanOrEqualTo(Integer value) {
            addCriterion("cmms_amt >=", value, "cmmsAmt");
            return (Criteria) this;
        }

        public Criteria andCmmsAmtLessThan(Integer value) {
            addCriterion("cmms_amt <", value, "cmmsAmt");
            return (Criteria) this;
        }

        public Criteria andCmmsAmtLessThanOrEqualTo(Integer value) {
            addCriterion("cmms_amt <=", value, "cmmsAmt");
            return (Criteria) this;
        }

        public Criteria andCmmsAmtIn(List<Integer> values) {
            addCriterion("cmms_amt in", values, "cmmsAmt");
            return (Criteria) this;
        }

        public Criteria andCmmsAmtNotIn(List<Integer> values) {
            addCriterion("cmms_amt not in", values, "cmmsAmt");
            return (Criteria) this;
        }

        public Criteria andCmmsAmtBetween(Integer value1, Integer value2) {
            addCriterion("cmms_amt between", value1, value2, "cmmsAmt");
            return (Criteria) this;
        }

        public Criteria andCmmsAmtNotBetween(Integer value1, Integer value2) {
            addCriterion("cmms_amt not between", value1, value2, "cmmsAmt");
            return (Criteria) this;
        }

        public Criteria andTransUidIsNull() {
            addCriterion("trans_uid is null");
            return (Criteria) this;
        }

        public Criteria andTransUidIsNotNull() {
            addCriterion("trans_uid is not null");
            return (Criteria) this;
        }

        public Criteria andTransUidEqualTo(Long value) {
            addCriterion("trans_uid =", value, "transUid");
            return (Criteria) this;
        }

        public Criteria andTransUidNotEqualTo(Long value) {
            addCriterion("trans_uid <>", value, "transUid");
            return (Criteria) this;
        }

        public Criteria andTransUidGreaterThan(Long value) {
            addCriterion("trans_uid >", value, "transUid");
            return (Criteria) this;
        }

        public Criteria andTransUidGreaterThanOrEqualTo(Long value) {
            addCriterion("trans_uid >=", value, "transUid");
            return (Criteria) this;
        }

        public Criteria andTransUidLessThan(Long value) {
            addCriterion("trans_uid <", value, "transUid");
            return (Criteria) this;
        }

        public Criteria andTransUidLessThanOrEqualTo(Long value) {
            addCriterion("trans_uid <=", value, "transUid");
            return (Criteria) this;
        }

        public Criteria andTransUidIn(List<Long> values) {
            addCriterion("trans_uid in", values, "transUid");
            return (Criteria) this;
        }

        public Criteria andTransUidNotIn(List<Long> values) {
            addCriterion("trans_uid not in", values, "transUid");
            return (Criteria) this;
        }

        public Criteria andTransUidBetween(Long value1, Long value2) {
            addCriterion("trans_uid between", value1, value2, "transUid");
            return (Criteria) this;
        }

        public Criteria andTransUidNotBetween(Long value1, Long value2) {
            addCriterion("trans_uid not between", value1, value2, "transUid");
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