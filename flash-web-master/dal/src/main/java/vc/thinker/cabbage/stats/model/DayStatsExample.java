package vc.thinker.cabbage.stats.model;

import com.sinco.mybatis.dal.core.AbstractExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import vc.thinker.cabbage.stats.bo.DayStatsBO;

public class DayStatsExample extends AbstractExample<DayStatsBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public DayStatsExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andStatsDateIsNull() {
            addCriterion("stats_date is null");
            return (Criteria) this;
        }

        public Criteria andStatsDateIsNotNull() {
            addCriterion("stats_date is not null");
            return (Criteria) this;
        }

        public Criteria andStatsDateEqualTo(Date value) {
            addCriterionForJDBCDate("stats_date =", value, "statsDate");
            return (Criteria) this;
        }

        public Criteria andStatsDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("stats_date <>", value, "statsDate");
            return (Criteria) this;
        }

        public Criteria andStatsDateGreaterThan(Date value) {
            addCriterionForJDBCDate("stats_date >", value, "statsDate");
            return (Criteria) this;
        }

        public Criteria andStatsDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("stats_date >=", value, "statsDate");
            return (Criteria) this;
        }

        public Criteria andStatsDateLessThan(Date value) {
            addCriterionForJDBCDate("stats_date <", value, "statsDate");
            return (Criteria) this;
        }

        public Criteria andStatsDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("stats_date <=", value, "statsDate");
            return (Criteria) this;
        }

        public Criteria andStatsDateIn(List<Date> values) {
            addCriterionForJDBCDate("stats_date in", values, "statsDate");
            return (Criteria) this;
        }

        public Criteria andStatsDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("stats_date not in", values, "statsDate");
            return (Criteria) this;
        }

        public Criteria andStatsDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("stats_date between", value1, value2, "statsDate");
            return (Criteria) this;
        }

        public Criteria andStatsDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("stats_date not between", value1, value2, "statsDate");
            return (Criteria) this;
        }

        public Criteria andCabinetNumIsNull() {
            addCriterion("cabinet_num is null");
            return (Criteria) this;
        }

        public Criteria andCabinetNumIsNotNull() {
            addCriterion("cabinet_num is not null");
            return (Criteria) this;
        }

        public Criteria andCabinetNumEqualTo(Integer value) {
            addCriterion("cabinet_num =", value, "cabinetNum");
            return (Criteria) this;
        }

        public Criteria andCabinetNumNotEqualTo(Integer value) {
            addCriterion("cabinet_num <>", value, "cabinetNum");
            return (Criteria) this;
        }

        public Criteria andCabinetNumGreaterThan(Integer value) {
            addCriterion("cabinet_num >", value, "cabinetNum");
            return (Criteria) this;
        }

        public Criteria andCabinetNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("cabinet_num >=", value, "cabinetNum");
            return (Criteria) this;
        }

        public Criteria andCabinetNumLessThan(Integer value) {
            addCriterion("cabinet_num <", value, "cabinetNum");
            return (Criteria) this;
        }

        public Criteria andCabinetNumLessThanOrEqualTo(Integer value) {
            addCriterion("cabinet_num <=", value, "cabinetNum");
            return (Criteria) this;
        }

        public Criteria andCabinetNumIn(List<Integer> values) {
            addCriterion("cabinet_num in", values, "cabinetNum");
            return (Criteria) this;
        }

        public Criteria andCabinetNumNotIn(List<Integer> values) {
            addCriterion("cabinet_num not in", values, "cabinetNum");
            return (Criteria) this;
        }

        public Criteria andCabinetNumBetween(Integer value1, Integer value2) {
            addCriterion("cabinet_num between", value1, value2, "cabinetNum");
            return (Criteria) this;
        }

        public Criteria andCabinetNumNotBetween(Integer value1, Integer value2) {
            addCriterion("cabinet_num not between", value1, value2, "cabinetNum");
            return (Criteria) this;
        }

        public Criteria andDepositIsNull() {
            addCriterion("deposit is null");
            return (Criteria) this;
        }

        public Criteria andDepositIsNotNull() {
            addCriterion("deposit is not null");
            return (Criteria) this;
        }

        public Criteria andDepositEqualTo(BigDecimal value) {
            addCriterion("deposit =", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotEqualTo(BigDecimal value) {
            addCriterion("deposit <>", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThan(BigDecimal value) {
            addCriterion("deposit >", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit >=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThan(BigDecimal value) {
            addCriterion("deposit <", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit <=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositIn(List<BigDecimal> values) {
            addCriterion("deposit in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotIn(List<BigDecimal> values) {
            addCriterion("deposit not in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit not between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andPbNumIsNull() {
            addCriterion("pb_num is null");
            return (Criteria) this;
        }

        public Criteria andPbNumIsNotNull() {
            addCriterion("pb_num is not null");
            return (Criteria) this;
        }

        public Criteria andPbNumEqualTo(Integer value) {
            addCriterion("pb_num =", value, "pbNum");
            return (Criteria) this;
        }

        public Criteria andPbNumNotEqualTo(Integer value) {
            addCriterion("pb_num <>", value, "pbNum");
            return (Criteria) this;
        }

        public Criteria andPbNumGreaterThan(Integer value) {
            addCriterion("pb_num >", value, "pbNum");
            return (Criteria) this;
        }

        public Criteria andPbNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("pb_num >=", value, "pbNum");
            return (Criteria) this;
        }

        public Criteria andPbNumLessThan(Integer value) {
            addCriterion("pb_num <", value, "pbNum");
            return (Criteria) this;
        }

        public Criteria andPbNumLessThanOrEqualTo(Integer value) {
            addCriterion("pb_num <=", value, "pbNum");
            return (Criteria) this;
        }

        public Criteria andPbNumIn(List<Integer> values) {
            addCriterion("pb_num in", values, "pbNum");
            return (Criteria) this;
        }

        public Criteria andPbNumNotIn(List<Integer> values) {
            addCriterion("pb_num not in", values, "pbNum");
            return (Criteria) this;
        }

        public Criteria andPbNumBetween(Integer value1, Integer value2) {
            addCriterion("pb_num between", value1, value2, "pbNum");
            return (Criteria) this;
        }

        public Criteria andPbNumNotBetween(Integer value1, Integer value2) {
            addCriterion("pb_num not between", value1, value2, "pbNum");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
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