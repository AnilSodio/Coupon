package vc.thinker.cabbage.se.model;

import com.sinco.mybatis.dal.core.AbstractExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vc.thinker.cabbage.se.bo.PortableBatteryTypeBO;

public class PortableBatteryTypeExample extends AbstractExample<PortableBatteryTypeBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public PortableBatteryTypeExample() {
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

        public Criteria andTypeNameIsNull() {
            addCriterion("type_name is null");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNotNull() {
            addCriterion("type_name is not null");
            return (Criteria) this;
        }

        public Criteria andTypeNameEqualTo(String value) {
            addCriterion("type_name =", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotEqualTo(String value) {
            addCriterion("type_name <>", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThan(String value) {
            addCriterion("type_name >", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("type_name >=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThan(String value) {
            addCriterion("type_name <", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThanOrEqualTo(String value) {
            addCriterion("type_name <=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLike(String value) {
            addCriterion("type_name like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotLike(String value) {
            addCriterion("type_name not like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameIn(List<String> values) {
            addCriterion("type_name in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotIn(List<String> values) {
            addCriterion("type_name not in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameBetween(String value1, String value2) {
            addCriterion("type_name between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotBetween(String value1, String value2) {
            addCriterion("type_name not between", value1, value2, "typeName");
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

        public Criteria andMaxElectricityIsNull() {
            addCriterion("max_electricity is null");
            return (Criteria) this;
        }

        public Criteria andMaxElectricityIsNotNull() {
            addCriterion("max_electricity is not null");
            return (Criteria) this;
        }

        public Criteria andMaxElectricityEqualTo(Integer value) {
            addCriterion("max_electricity =", value, "maxElectricity");
            return (Criteria) this;
        }

        public Criteria andMaxElectricityNotEqualTo(Integer value) {
            addCriterion("max_electricity <>", value, "maxElectricity");
            return (Criteria) this;
        }

        public Criteria andMaxElectricityGreaterThan(Integer value) {
            addCriterion("max_electricity >", value, "maxElectricity");
            return (Criteria) this;
        }

        public Criteria andMaxElectricityGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_electricity >=", value, "maxElectricity");
            return (Criteria) this;
        }

        public Criteria andMaxElectricityLessThan(Integer value) {
            addCriterion("max_electricity <", value, "maxElectricity");
            return (Criteria) this;
        }

        public Criteria andMaxElectricityLessThanOrEqualTo(Integer value) {
            addCriterion("max_electricity <=", value, "maxElectricity");
            return (Criteria) this;
        }

        public Criteria andMaxElectricityIn(List<Integer> values) {
            addCriterion("max_electricity in", values, "maxElectricity");
            return (Criteria) this;
        }

        public Criteria andMaxElectricityNotIn(List<Integer> values) {
            addCriterion("max_electricity not in", values, "maxElectricity");
            return (Criteria) this;
        }

        public Criteria andMaxElectricityBetween(Integer value1, Integer value2) {
            addCriterion("max_electricity between", value1, value2, "maxElectricity");
            return (Criteria) this;
        }

        public Criteria andMaxElectricityNotBetween(Integer value1, Integer value2) {
            addCriterion("max_electricity not between", value1, value2, "maxElectricity");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageIsNull() {
            addCriterion("max_voltage is null");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageIsNotNull() {
            addCriterion("max_voltage is not null");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageEqualTo(Integer value) {
            addCriterion("max_voltage =", value, "maxVoltage");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageNotEqualTo(Integer value) {
            addCriterion("max_voltage <>", value, "maxVoltage");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageGreaterThan(Integer value) {
            addCriterion("max_voltage >", value, "maxVoltage");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_voltage >=", value, "maxVoltage");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageLessThan(Integer value) {
            addCriterion("max_voltage <", value, "maxVoltage");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageLessThanOrEqualTo(Integer value) {
            addCriterion("max_voltage <=", value, "maxVoltage");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageIn(List<Integer> values) {
            addCriterion("max_voltage in", values, "maxVoltage");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageNotIn(List<Integer> values) {
            addCriterion("max_voltage not in", values, "maxVoltage");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageBetween(Integer value1, Integer value2) {
            addCriterion("max_voltage between", value1, value2, "maxVoltage");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageNotBetween(Integer value1, Integer value2) {
            addCriterion("max_voltage not between", value1, value2, "maxVoltage");
            return (Criteria) this;
        }

        public Criteria andIsHasLineIsNull() {
            addCriterion("is_has_line is null");
            return (Criteria) this;
        }

        public Criteria andIsHasLineIsNotNull() {
            addCriterion("is_has_line is not null");
            return (Criteria) this;
        }

        public Criteria andIsHasLineEqualTo(Boolean value) {
            addCriterion("is_has_line =", value, "isHasLine");
            return (Criteria) this;
        }

        public Criteria andIsHasLineNotEqualTo(Boolean value) {
            addCriterion("is_has_line <>", value, "isHasLine");
            return (Criteria) this;
        }

        public Criteria andIsHasLineGreaterThan(Boolean value) {
            addCriterion("is_has_line >", value, "isHasLine");
            return (Criteria) this;
        }

        public Criteria andIsHasLineGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_has_line >=", value, "isHasLine");
            return (Criteria) this;
        }

        public Criteria andIsHasLineLessThan(Boolean value) {
            addCriterion("is_has_line <", value, "isHasLine");
            return (Criteria) this;
        }

        public Criteria andIsHasLineLessThanOrEqualTo(Boolean value) {
            addCriterion("is_has_line <=", value, "isHasLine");
            return (Criteria) this;
        }

        public Criteria andIsHasLineIn(List<Boolean> values) {
            addCriterion("is_has_line in", values, "isHasLine");
            return (Criteria) this;
        }

        public Criteria andIsHasLineNotIn(List<Boolean> values) {
            addCriterion("is_has_line not in", values, "isHasLine");
            return (Criteria) this;
        }

        public Criteria andIsHasLineBetween(Boolean value1, Boolean value2) {
            addCriterion("is_has_line between", value1, value2, "isHasLine");
            return (Criteria) this;
        }

        public Criteria andIsHasLineNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_has_line not between", value1, value2, "isHasLine");
            return (Criteria) this;
        }

        public Criteria andLineTypeIsNull() {
            addCriterion("line_type is null");
            return (Criteria) this;
        }

        public Criteria andLineTypeIsNotNull() {
            addCriterion("line_type is not null");
            return (Criteria) this;
        }

        public Criteria andLineTypeEqualTo(String value) {
            addCriterion("line_type =", value, "lineType");
            return (Criteria) this;
        }

        public Criteria andLineTypeNotEqualTo(String value) {
            addCriterion("line_type <>", value, "lineType");
            return (Criteria) this;
        }

        public Criteria andLineTypeGreaterThan(String value) {
            addCriterion("line_type >", value, "lineType");
            return (Criteria) this;
        }

        public Criteria andLineTypeGreaterThanOrEqualTo(String value) {
            addCriterion("line_type >=", value, "lineType");
            return (Criteria) this;
        }

        public Criteria andLineTypeLessThan(String value) {
            addCriterion("line_type <", value, "lineType");
            return (Criteria) this;
        }

        public Criteria andLineTypeLessThanOrEqualTo(String value) {
            addCriterion("line_type <=", value, "lineType");
            return (Criteria) this;
        }

        public Criteria andLineTypeLike(String value) {
            addCriterion("line_type like", value, "lineType");
            return (Criteria) this;
        }

        public Criteria andLineTypeNotLike(String value) {
            addCriterion("line_type not like", value, "lineType");
            return (Criteria) this;
        }

        public Criteria andLineTypeIn(List<String> values) {
            addCriterion("line_type in", values, "lineType");
            return (Criteria) this;
        }

        public Criteria andLineTypeNotIn(List<String> values) {
            addCriterion("line_type not in", values, "lineType");
            return (Criteria) this;
        }

        public Criteria andLineTypeBetween(String value1, String value2) {
            addCriterion("line_type between", value1, value2, "lineType");
            return (Criteria) this;
        }

        public Criteria andLineTypeNotBetween(String value1, String value2) {
            addCriterion("line_type not between", value1, value2, "lineType");
            return (Criteria) this;
        }

        public Criteria andTypeDescIsNull() {
            addCriterion("type_desc is null");
            return (Criteria) this;
        }

        public Criteria andTypeDescIsNotNull() {
            addCriterion("type_desc is not null");
            return (Criteria) this;
        }

        public Criteria andTypeDescEqualTo(String value) {
            addCriterion("type_desc =", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescNotEqualTo(String value) {
            addCriterion("type_desc <>", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescGreaterThan(String value) {
            addCriterion("type_desc >", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescGreaterThanOrEqualTo(String value) {
            addCriterion("type_desc >=", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescLessThan(String value) {
            addCriterion("type_desc <", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescLessThanOrEqualTo(String value) {
            addCriterion("type_desc <=", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescLike(String value) {
            addCriterion("type_desc like", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescNotLike(String value) {
            addCriterion("type_desc not like", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescIn(List<String> values) {
            addCriterion("type_desc in", values, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescNotIn(List<String> values) {
            addCriterion("type_desc not in", values, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescBetween(String value1, String value2) {
            addCriterion("type_desc between", value1, value2, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescNotBetween(String value1, String value2) {
            addCriterion("type_desc not between", value1, value2, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(Long value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(Long value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(Long value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(Long value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(Long value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(Long value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<Long> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<Long> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(Long value1, Long value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(Long value1, Long value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
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