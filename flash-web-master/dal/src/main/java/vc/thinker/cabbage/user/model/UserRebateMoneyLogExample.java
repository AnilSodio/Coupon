package vc.thinker.cabbage.user.model;

import com.sinco.mybatis.dal.core.AbstractExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vc.thinker.cabbage.user.bo.UserRebateMoneyLogBO;

public class UserRebateMoneyLogExample extends AbstractExample<UserRebateMoneyLogBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public UserRebateMoneyLogExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andToUserIdIsNull() {
            addCriterion("to_user_id is null");
            return (Criteria) this;
        }

        public Criteria andToUserIdIsNotNull() {
            addCriterion("to_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andToUserIdEqualTo(Long value) {
            addCriterion("to_user_id =", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdNotEqualTo(Long value) {
            addCriterion("to_user_id <>", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdGreaterThan(Long value) {
            addCriterion("to_user_id >", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("to_user_id >=", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdLessThan(Long value) {
            addCriterion("to_user_id <", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdLessThanOrEqualTo(Long value) {
            addCriterion("to_user_id <=", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdIn(List<Long> values) {
            addCriterion("to_user_id in", values, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdNotIn(List<Long> values) {
            addCriterion("to_user_id not in", values, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdBetween(Long value1, Long value2) {
            addCriterion("to_user_id between", value1, value2, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdNotBetween(Long value1, Long value2) {
            addCriterion("to_user_id not between", value1, value2, "toUserId");
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

        public Criteria andLogAmountIsNull() {
            addCriterion("log_amount is null");
            return (Criteria) this;
        }

        public Criteria andLogAmountIsNotNull() {
            addCriterion("log_amount is not null");
            return (Criteria) this;
        }

        public Criteria andLogAmountEqualTo(BigDecimal value) {
            addCriterion("log_amount =", value, "logAmount");
            return (Criteria) this;
        }

        public Criteria andLogAmountNotEqualTo(BigDecimal value) {
            addCriterion("log_amount <>", value, "logAmount");
            return (Criteria) this;
        }

        public Criteria andLogAmountGreaterThan(BigDecimal value) {
            addCriterion("log_amount >", value, "logAmount");
            return (Criteria) this;
        }

        public Criteria andLogAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("log_amount >=", value, "logAmount");
            return (Criteria) this;
        }

        public Criteria andLogAmountLessThan(BigDecimal value) {
            addCriterion("log_amount <", value, "logAmount");
            return (Criteria) this;
        }

        public Criteria andLogAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("log_amount <=", value, "logAmount");
            return (Criteria) this;
        }

        public Criteria andLogAmountIn(List<BigDecimal> values) {
            addCriterion("log_amount in", values, "logAmount");
            return (Criteria) this;
        }

        public Criteria andLogAmountNotIn(List<BigDecimal> values) {
            addCriterion("log_amount not in", values, "logAmount");
            return (Criteria) this;
        }

        public Criteria andLogAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("log_amount between", value1, value2, "logAmount");
            return (Criteria) this;
        }

        public Criteria andLogAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("log_amount not between", value1, value2, "logAmount");
            return (Criteria) this;
        }

        public Criteria andLogCurrencyIsNull() {
            addCriterion("log_currency is null");
            return (Criteria) this;
        }

        public Criteria andLogCurrencyIsNotNull() {
            addCriterion("log_currency is not null");
            return (Criteria) this;
        }

        public Criteria andLogCurrencyEqualTo(String value) {
            addCriterion("log_currency =", value, "logCurrency");
            return (Criteria) this;
        }

        public Criteria andLogCurrencyNotEqualTo(String value) {
            addCriterion("log_currency <>", value, "logCurrency");
            return (Criteria) this;
        }

        public Criteria andLogCurrencyGreaterThan(String value) {
            addCriterion("log_currency >", value, "logCurrency");
            return (Criteria) this;
        }

        public Criteria andLogCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("log_currency >=", value, "logCurrency");
            return (Criteria) this;
        }

        public Criteria andLogCurrencyLessThan(String value) {
            addCriterion("log_currency <", value, "logCurrency");
            return (Criteria) this;
        }

        public Criteria andLogCurrencyLessThanOrEqualTo(String value) {
            addCriterion("log_currency <=", value, "logCurrency");
            return (Criteria) this;
        }

        public Criteria andLogCurrencyLike(String value) {
            addCriterion("log_currency like", value, "logCurrency");
            return (Criteria) this;
        }

        public Criteria andLogCurrencyNotLike(String value) {
            addCriterion("log_currency not like", value, "logCurrency");
            return (Criteria) this;
        }

        public Criteria andLogCurrencyIn(List<String> values) {
            addCriterion("log_currency in", values, "logCurrency");
            return (Criteria) this;
        }

        public Criteria andLogCurrencyNotIn(List<String> values) {
            addCriterion("log_currency not in", values, "logCurrency");
            return (Criteria) this;
        }

        public Criteria andLogCurrencyBetween(String value1, String value2) {
            addCriterion("log_currency between", value1, value2, "logCurrency");
            return (Criteria) this;
        }

        public Criteria andLogCurrencyNotBetween(String value1, String value2) {
            addCriterion("log_currency not between", value1, value2, "logCurrency");
            return (Criteria) this;
        }

        public Criteria andExchangeRateIsNull() {
            addCriterion("exchange_rate is null");
            return (Criteria) this;
        }

        public Criteria andExchangeRateIsNotNull() {
            addCriterion("exchange_rate is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeRateEqualTo(BigDecimal value) {
            addCriterion("exchange_rate =", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateNotEqualTo(BigDecimal value) {
            addCriterion("exchange_rate <>", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateGreaterThan(BigDecimal value) {
            addCriterion("exchange_rate >", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("exchange_rate >=", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateLessThan(BigDecimal value) {
            addCriterion("exchange_rate <", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("exchange_rate <=", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateIn(List<BigDecimal> values) {
            addCriterion("exchange_rate in", values, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateNotIn(List<BigDecimal> values) {
            addCriterion("exchange_rate not in", values, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("exchange_rate between", value1, value2, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("exchange_rate not between", value1, value2, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andOldLogAmountIsNull() {
            addCriterion("old_log_amount is null");
            return (Criteria) this;
        }

        public Criteria andOldLogAmountIsNotNull() {
            addCriterion("old_log_amount is not null");
            return (Criteria) this;
        }

        public Criteria andOldLogAmountEqualTo(BigDecimal value) {
            addCriterion("old_log_amount =", value, "oldLogAmount");
            return (Criteria) this;
        }

        public Criteria andOldLogAmountNotEqualTo(BigDecimal value) {
            addCriterion("old_log_amount <>", value, "oldLogAmount");
            return (Criteria) this;
        }

        public Criteria andOldLogAmountGreaterThan(BigDecimal value) {
            addCriterion("old_log_amount >", value, "oldLogAmount");
            return (Criteria) this;
        }

        public Criteria andOldLogAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("old_log_amount >=", value, "oldLogAmount");
            return (Criteria) this;
        }

        public Criteria andOldLogAmountLessThan(BigDecimal value) {
            addCriterion("old_log_amount <", value, "oldLogAmount");
            return (Criteria) this;
        }

        public Criteria andOldLogAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("old_log_amount <=", value, "oldLogAmount");
            return (Criteria) this;
        }

        public Criteria andOldLogAmountIn(List<BigDecimal> values) {
            addCriterion("old_log_amount in", values, "oldLogAmount");
            return (Criteria) this;
        }

        public Criteria andOldLogAmountNotIn(List<BigDecimal> values) {
            addCriterion("old_log_amount not in", values, "oldLogAmount");
            return (Criteria) this;
        }

        public Criteria andOldLogAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("old_log_amount between", value1, value2, "oldLogAmount");
            return (Criteria) this;
        }

        public Criteria andOldLogAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("old_log_amount not between", value1, value2, "oldLogAmount");
            return (Criteria) this;
        }

        public Criteria andOldLogCurrencyIsNull() {
            addCriterion("old_log_currency is null");
            return (Criteria) this;
        }

        public Criteria andOldLogCurrencyIsNotNull() {
            addCriterion("old_log_currency is not null");
            return (Criteria) this;
        }

        public Criteria andOldLogCurrencyEqualTo(String value) {
            addCriterion("old_log_currency =", value, "oldLogCurrency");
            return (Criteria) this;
        }

        public Criteria andOldLogCurrencyNotEqualTo(String value) {
            addCriterion("old_log_currency <>", value, "oldLogCurrency");
            return (Criteria) this;
        }

        public Criteria andOldLogCurrencyGreaterThan(String value) {
            addCriterion("old_log_currency >", value, "oldLogCurrency");
            return (Criteria) this;
        }

        public Criteria andOldLogCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("old_log_currency >=", value, "oldLogCurrency");
            return (Criteria) this;
        }

        public Criteria andOldLogCurrencyLessThan(String value) {
            addCriterion("old_log_currency <", value, "oldLogCurrency");
            return (Criteria) this;
        }

        public Criteria andOldLogCurrencyLessThanOrEqualTo(String value) {
            addCriterion("old_log_currency <=", value, "oldLogCurrency");
            return (Criteria) this;
        }

        public Criteria andOldLogCurrencyLike(String value) {
            addCriterion("old_log_currency like", value, "oldLogCurrency");
            return (Criteria) this;
        }

        public Criteria andOldLogCurrencyNotLike(String value) {
            addCriterion("old_log_currency not like", value, "oldLogCurrency");
            return (Criteria) this;
        }

        public Criteria andOldLogCurrencyIn(List<String> values) {
            addCriterion("old_log_currency in", values, "oldLogCurrency");
            return (Criteria) this;
        }

        public Criteria andOldLogCurrencyNotIn(List<String> values) {
            addCriterion("old_log_currency not in", values, "oldLogCurrency");
            return (Criteria) this;
        }

        public Criteria andOldLogCurrencyBetween(String value1, String value2) {
            addCriterion("old_log_currency between", value1, value2, "oldLogCurrency");
            return (Criteria) this;
        }

        public Criteria andOldLogCurrencyNotBetween(String value1, String value2) {
            addCriterion("old_log_currency not between", value1, value2, "oldLogCurrency");
            return (Criteria) this;
        }

        public Criteria andLogInfoIsNull() {
            addCriterion("log_info is null");
            return (Criteria) this;
        }

        public Criteria andLogInfoIsNotNull() {
            addCriterion("log_info is not null");
            return (Criteria) this;
        }

        public Criteria andLogInfoEqualTo(String value) {
            addCriterion("log_info =", value, "logInfo");
            return (Criteria) this;
        }

        public Criteria andLogInfoNotEqualTo(String value) {
            addCriterion("log_info <>", value, "logInfo");
            return (Criteria) this;
        }

        public Criteria andLogInfoGreaterThan(String value) {
            addCriterion("log_info >", value, "logInfo");
            return (Criteria) this;
        }

        public Criteria andLogInfoGreaterThanOrEqualTo(String value) {
            addCriterion("log_info >=", value, "logInfo");
            return (Criteria) this;
        }

        public Criteria andLogInfoLessThan(String value) {
            addCriterion("log_info <", value, "logInfo");
            return (Criteria) this;
        }

        public Criteria andLogInfoLessThanOrEqualTo(String value) {
            addCriterion("log_info <=", value, "logInfo");
            return (Criteria) this;
        }

        public Criteria andLogInfoLike(String value) {
            addCriterion("log_info like", value, "logInfo");
            return (Criteria) this;
        }

        public Criteria andLogInfoNotLike(String value) {
            addCriterion("log_info not like", value, "logInfo");
            return (Criteria) this;
        }

        public Criteria andLogInfoIn(List<String> values) {
            addCriterion("log_info in", values, "logInfo");
            return (Criteria) this;
        }

        public Criteria andLogInfoNotIn(List<String> values) {
            addCriterion("log_info not in", values, "logInfo");
            return (Criteria) this;
        }

        public Criteria andLogInfoBetween(String value1, String value2) {
            addCriterion("log_info between", value1, value2, "logInfo");
            return (Criteria) this;
        }

        public Criteria andLogInfoNotBetween(String value1, String value2) {
            addCriterion("log_info not between", value1, value2, "logInfo");
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

        public Criteria andUserTypeIsNull() {
            addCriterion("user_type is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNotNull() {
            addCriterion("user_type is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeEqualTo(Integer value) {
            addCriterion("user_type =", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotEqualTo(Integer value) {
            addCriterion("user_type <>", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThan(Integer value) {
            addCriterion("user_type >", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_type >=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThan(Integer value) {
            addCriterion("user_type <", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThanOrEqualTo(Integer value) {
            addCriterion("user_type <=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeIn(List<Integer> values) {
            addCriterion("user_type in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotIn(List<Integer> values) {
            addCriterion("user_type not in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeBetween(Integer value1, Integer value2) {
            addCriterion("user_type between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotBetween(Integer value1, Integer value2) {
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