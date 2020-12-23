package vc.thinker.cabbage.user.model;

import com.sinco.mybatis.dal.core.AbstractExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vc.thinker.cabbage.user.bo.UserMoneyLogBO;

public class UserMoneyLogExample extends AbstractExample<UserMoneyLogBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public UserMoneyLogExample() {
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

        public Criteria andLogUserIdIsNull() {
            addCriterion("log_user_id is null");
            return (Criteria) this;
        }

        public Criteria andLogUserIdIsNotNull() {
            addCriterion("log_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andLogUserIdEqualTo(Long value) {
            addCriterion("log_user_id =", value, "logUserId");
            return (Criteria) this;
        }

        public Criteria andLogUserIdNotEqualTo(Long value) {
            addCriterion("log_user_id <>", value, "logUserId");
            return (Criteria) this;
        }

        public Criteria andLogUserIdGreaterThan(Long value) {
            addCriterion("log_user_id >", value, "logUserId");
            return (Criteria) this;
        }

        public Criteria andLogUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("log_user_id >=", value, "logUserId");
            return (Criteria) this;
        }

        public Criteria andLogUserIdLessThan(Long value) {
            addCriterion("log_user_id <", value, "logUserId");
            return (Criteria) this;
        }

        public Criteria andLogUserIdLessThanOrEqualTo(Long value) {
            addCriterion("log_user_id <=", value, "logUserId");
            return (Criteria) this;
        }

        public Criteria andLogUserIdIn(List<Long> values) {
            addCriterion("log_user_id in", values, "logUserId");
            return (Criteria) this;
        }

        public Criteria andLogUserIdNotIn(List<Long> values) {
            addCriterion("log_user_id not in", values, "logUserId");
            return (Criteria) this;
        }

        public Criteria andLogUserIdBetween(Long value1, Long value2) {
            addCriterion("log_user_id between", value1, value2, "logUserId");
            return (Criteria) this;
        }

        public Criteria andLogUserIdNotBetween(Long value1, Long value2) {
            addCriterion("log_user_id not between", value1, value2, "logUserId");
            return (Criteria) this;
        }

        public Criteria andLogSourceIdIsNull() {
            addCriterion("log_source_id is null");
            return (Criteria) this;
        }

        public Criteria andLogSourceIdIsNotNull() {
            addCriterion("log_source_id is not null");
            return (Criteria) this;
        }

        public Criteria andLogSourceIdEqualTo(Long value) {
            addCriterion("log_source_id =", value, "logSourceId");
            return (Criteria) this;
        }

        public Criteria andLogSourceIdNotEqualTo(Long value) {
            addCriterion("log_source_id <>", value, "logSourceId");
            return (Criteria) this;
        }

        public Criteria andLogSourceIdGreaterThan(Long value) {
            addCriterion("log_source_id >", value, "logSourceId");
            return (Criteria) this;
        }

        public Criteria andLogSourceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("log_source_id >=", value, "logSourceId");
            return (Criteria) this;
        }

        public Criteria andLogSourceIdLessThan(Long value) {
            addCriterion("log_source_id <", value, "logSourceId");
            return (Criteria) this;
        }

        public Criteria andLogSourceIdLessThanOrEqualTo(Long value) {
            addCriterion("log_source_id <=", value, "logSourceId");
            return (Criteria) this;
        }

        public Criteria andLogSourceIdIn(List<Long> values) {
            addCriterion("log_source_id in", values, "logSourceId");
            return (Criteria) this;
        }

        public Criteria andLogSourceIdNotIn(List<Long> values) {
            addCriterion("log_source_id not in", values, "logSourceId");
            return (Criteria) this;
        }

        public Criteria andLogSourceIdBetween(Long value1, Long value2) {
            addCriterion("log_source_id between", value1, value2, "logSourceId");
            return (Criteria) this;
        }

        public Criteria andLogSourceIdNotBetween(Long value1, Long value2) {
            addCriterion("log_source_id not between", value1, value2, "logSourceId");
            return (Criteria) this;
        }

        public Criteria andLogTypeIsNull() {
            addCriterion("log_type is null");
            return (Criteria) this;
        }

        public Criteria andLogTypeIsNotNull() {
            addCriterion("log_type is not null");
            return (Criteria) this;
        }

        public Criteria andLogTypeEqualTo(String value) {
            addCriterion("log_type =", value, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeNotEqualTo(String value) {
            addCriterion("log_type <>", value, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeGreaterThan(String value) {
            addCriterion("log_type >", value, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeGreaterThanOrEqualTo(String value) {
            addCriterion("log_type >=", value, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeLessThan(String value) {
            addCriterion("log_type <", value, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeLessThanOrEqualTo(String value) {
            addCriterion("log_type <=", value, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeLike(String value) {
            addCriterion("log_type like", value, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeNotLike(String value) {
            addCriterion("log_type not like", value, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeIn(List<String> values) {
            addCriterion("log_type in", values, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeNotIn(List<String> values) {
            addCriterion("log_type not in", values, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeBetween(String value1, String value2) {
            addCriterion("log_type between", value1, value2, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeNotBetween(String value1, String value2) {
            addCriterion("log_type not between", value1, value2, "logType");
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