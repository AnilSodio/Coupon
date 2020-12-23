package vc.thinker.cabbage.user.model;

import com.sinco.mybatis.dal.core.AbstractExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vc.thinker.cabbage.user.bo.IntegralLogBO;

public class IntegralLogExample extends AbstractExample<IntegralLogBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public IntegralLogExample() {
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

        public Criteria andBizSourceIdIsNull() {
            addCriterion("biz_source_id is null");
            return (Criteria) this;
        }

        public Criteria andBizSourceIdIsNotNull() {
            addCriterion("biz_source_id is not null");
            return (Criteria) this;
        }

        public Criteria andBizSourceIdEqualTo(Long value) {
            addCriterion("biz_source_id =", value, "bizSourceId");
            return (Criteria) this;
        }

        public Criteria andBizSourceIdNotEqualTo(Long value) {
            addCriterion("biz_source_id <>", value, "bizSourceId");
            return (Criteria) this;
        }

        public Criteria andBizSourceIdGreaterThan(Long value) {
            addCriterion("biz_source_id >", value, "bizSourceId");
            return (Criteria) this;
        }

        public Criteria andBizSourceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("biz_source_id >=", value, "bizSourceId");
            return (Criteria) this;
        }

        public Criteria andBizSourceIdLessThan(Long value) {
            addCriterion("biz_source_id <", value, "bizSourceId");
            return (Criteria) this;
        }

        public Criteria andBizSourceIdLessThanOrEqualTo(Long value) {
            addCriterion("biz_source_id <=", value, "bizSourceId");
            return (Criteria) this;
        }

        public Criteria andBizSourceIdIn(List<Long> values) {
            addCriterion("biz_source_id in", values, "bizSourceId");
            return (Criteria) this;
        }

        public Criteria andBizSourceIdNotIn(List<Long> values) {
            addCriterion("biz_source_id not in", values, "bizSourceId");
            return (Criteria) this;
        }

        public Criteria andBizSourceIdBetween(Long value1, Long value2) {
            addCriterion("biz_source_id between", value1, value2, "bizSourceId");
            return (Criteria) this;
        }

        public Criteria andBizSourceIdNotBetween(Long value1, Long value2) {
            addCriterion("biz_source_id not between", value1, value2, "bizSourceId");
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

        public Criteria andLogIntegralIsNull() {
            addCriterion("log_integral is null");
            return (Criteria) this;
        }

        public Criteria andLogIntegralIsNotNull() {
            addCriterion("log_integral is not null");
            return (Criteria) this;
        }

        public Criteria andLogIntegralEqualTo(Long value) {
            addCriterion("log_integral =", value, "logIntegral");
            return (Criteria) this;
        }

        public Criteria andLogIntegralNotEqualTo(Long value) {
            addCriterion("log_integral <>", value, "logIntegral");
            return (Criteria) this;
        }

        public Criteria andLogIntegralGreaterThan(Long value) {
            addCriterion("log_integral >", value, "logIntegral");
            return (Criteria) this;
        }

        public Criteria andLogIntegralGreaterThanOrEqualTo(Long value) {
            addCriterion("log_integral >=", value, "logIntegral");
            return (Criteria) this;
        }

        public Criteria andLogIntegralLessThan(Long value) {
            addCriterion("log_integral <", value, "logIntegral");
            return (Criteria) this;
        }

        public Criteria andLogIntegralLessThanOrEqualTo(Long value) {
            addCriterion("log_integral <=", value, "logIntegral");
            return (Criteria) this;
        }

        public Criteria andLogIntegralIn(List<Long> values) {
            addCriterion("log_integral in", values, "logIntegral");
            return (Criteria) this;
        }

        public Criteria andLogIntegralNotIn(List<Long> values) {
            addCriterion("log_integral not in", values, "logIntegral");
            return (Criteria) this;
        }

        public Criteria andLogIntegralBetween(Long value1, Long value2) {
            addCriterion("log_integral between", value1, value2, "logIntegral");
            return (Criteria) this;
        }

        public Criteria andLogIntegralNotBetween(Long value1, Long value2) {
            addCriterion("log_integral not between", value1, value2, "logIntegral");
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

        public Criteria andBizSourceCodeIsNull() {
            addCriterion("biz_source_code is null");
            return (Criteria) this;
        }

        public Criteria andBizSourceCodeIsNotNull() {
            addCriterion("biz_source_code is not null");
            return (Criteria) this;
        }

        public Criteria andBizSourceCodeEqualTo(String value) {
            addCriterion("biz_source_code =", value, "bizSourceCode");
            return (Criteria) this;
        }

        public Criteria andBizSourceCodeNotEqualTo(String value) {
            addCriterion("biz_source_code <>", value, "bizSourceCode");
            return (Criteria) this;
        }

        public Criteria andBizSourceCodeGreaterThan(String value) {
            addCriterion("biz_source_code >", value, "bizSourceCode");
            return (Criteria) this;
        }

        public Criteria andBizSourceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("biz_source_code >=", value, "bizSourceCode");
            return (Criteria) this;
        }

        public Criteria andBizSourceCodeLessThan(String value) {
            addCriterion("biz_source_code <", value, "bizSourceCode");
            return (Criteria) this;
        }

        public Criteria andBizSourceCodeLessThanOrEqualTo(String value) {
            addCriterion("biz_source_code <=", value, "bizSourceCode");
            return (Criteria) this;
        }

        public Criteria andBizSourceCodeLike(String value) {
            addCriterion("biz_source_code like", value, "bizSourceCode");
            return (Criteria) this;
        }

        public Criteria andBizSourceCodeNotLike(String value) {
            addCriterion("biz_source_code not like", value, "bizSourceCode");
            return (Criteria) this;
        }

        public Criteria andBizSourceCodeIn(List<String> values) {
            addCriterion("biz_source_code in", values, "bizSourceCode");
            return (Criteria) this;
        }

        public Criteria andBizSourceCodeNotIn(List<String> values) {
            addCriterion("biz_source_code not in", values, "bizSourceCode");
            return (Criteria) this;
        }

        public Criteria andBizSourceCodeBetween(String value1, String value2) {
            addCriterion("biz_source_code between", value1, value2, "bizSourceCode");
            return (Criteria) this;
        }

        public Criteria andBizSourceCodeNotBetween(String value1, String value2) {
            addCriterion("biz_source_code not between", value1, value2, "bizSourceCode");
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