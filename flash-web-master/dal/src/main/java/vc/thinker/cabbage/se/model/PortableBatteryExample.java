package vc.thinker.cabbage.se.model;

import com.sinco.mybatis.dal.core.AbstractExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vc.thinker.cabbage.se.bo.PortableBatteryBO;

public class PortableBatteryExample extends AbstractExample<PortableBatteryBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public PortableBatteryExample() {
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

        public Criteria andPortableBatteryCodeIsNull() {
            addCriterion("portable_battery_code is null");
            return (Criteria) this;
        }

        public Criteria andPortableBatteryCodeIsNotNull() {
            addCriterion("portable_battery_code is not null");
            return (Criteria) this;
        }

        public Criteria andPortableBatteryCodeEqualTo(String value) {
            addCriterion("portable_battery_code =", value, "portableBatteryCode");
            return (Criteria) this;
        }

        public Criteria andPortableBatteryCodeNotEqualTo(String value) {
            addCriterion("portable_battery_code <>", value, "portableBatteryCode");
            return (Criteria) this;
        }

        public Criteria andPortableBatteryCodeGreaterThan(String value) {
            addCriterion("portable_battery_code >", value, "portableBatteryCode");
            return (Criteria) this;
        }

        public Criteria andPortableBatteryCodeGreaterThanOrEqualTo(String value) {
            addCriterion("portable_battery_code >=", value, "portableBatteryCode");
            return (Criteria) this;
        }

        public Criteria andPortableBatteryCodeLessThan(String value) {
            addCriterion("portable_battery_code <", value, "portableBatteryCode");
            return (Criteria) this;
        }

        public Criteria andPortableBatteryCodeLessThanOrEqualTo(String value) {
            addCriterion("portable_battery_code <=", value, "portableBatteryCode");
            return (Criteria) this;
        }

        public Criteria andPortableBatteryCodeLike(String value) {
            addCriterion("portable_battery_code like", value, "portableBatteryCode");
            return (Criteria) this;
        }

        public Criteria andPortableBatteryCodeNotLike(String value) {
            addCriterion("portable_battery_code not like", value, "portableBatteryCode");
            return (Criteria) this;
        }

        public Criteria andPortableBatteryCodeIn(List<String> values) {
            addCriterion("portable_battery_code in", values, "portableBatteryCode");
            return (Criteria) this;
        }

        public Criteria andPortableBatteryCodeNotIn(List<String> values) {
            addCriterion("portable_battery_code not in", values, "portableBatteryCode");
            return (Criteria) this;
        }

        public Criteria andPortableBatteryCodeBetween(String value1, String value2) {
            addCriterion("portable_battery_code between", value1, value2, "portableBatteryCode");
            return (Criteria) this;
        }

        public Criteria andPortableBatteryCodeNotBetween(String value1, String value2) {
            addCriterion("portable_battery_code not between", value1, value2, "portableBatteryCode");
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

        public Criteria andLocationTypeIsNull() {
            addCriterion("location_type is null");
            return (Criteria) this;
        }

        public Criteria andLocationTypeIsNotNull() {
            addCriterion("location_type is not null");
            return (Criteria) this;
        }

        public Criteria andLocationTypeEqualTo(Integer value) {
            addCriterion("location_type =", value, "locationType");
            return (Criteria) this;
        }

        public Criteria andLocationTypeNotEqualTo(Integer value) {
            addCriterion("location_type <>", value, "locationType");
            return (Criteria) this;
        }

        public Criteria andLocationTypeGreaterThan(Integer value) {
            addCriterion("location_type >", value, "locationType");
            return (Criteria) this;
        }

        public Criteria andLocationTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("location_type >=", value, "locationType");
            return (Criteria) this;
        }

        public Criteria andLocationTypeLessThan(Integer value) {
            addCriterion("location_type <", value, "locationType");
            return (Criteria) this;
        }

        public Criteria andLocationTypeLessThanOrEqualTo(Integer value) {
            addCriterion("location_type <=", value, "locationType");
            return (Criteria) this;
        }

        public Criteria andLocationTypeIn(List<Integer> values) {
            addCriterion("location_type in", values, "locationType");
            return (Criteria) this;
        }

        public Criteria andLocationTypeNotIn(List<Integer> values) {
            addCriterion("location_type not in", values, "locationType");
            return (Criteria) this;
        }

        public Criteria andLocationTypeBetween(Integer value1, Integer value2) {
            addCriterion("location_type between", value1, value2, "locationType");
            return (Criteria) this;
        }

        public Criteria andLocationTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("location_type not between", value1, value2, "locationType");
            return (Criteria) this;
        }

        public Criteria andCabinetIdIsNull() {
            addCriterion("cabinet_id is null");
            return (Criteria) this;
        }

        public Criteria andCabinetIdIsNotNull() {
            addCriterion("cabinet_id is not null");
            return (Criteria) this;
        }

        public Criteria andCabinetIdEqualTo(Long value) {
            addCriterion("cabinet_id =", value, "cabinetId");
            return (Criteria) this;
        }

        public Criteria andCabinetIdNotEqualTo(Long value) {
            addCriterion("cabinet_id <>", value, "cabinetId");
            return (Criteria) this;
        }

        public Criteria andCabinetIdGreaterThan(Long value) {
            addCriterion("cabinet_id >", value, "cabinetId");
            return (Criteria) this;
        }

        public Criteria andCabinetIdGreaterThanOrEqualTo(Long value) {
            addCriterion("cabinet_id >=", value, "cabinetId");
            return (Criteria) this;
        }

        public Criteria andCabinetIdLessThan(Long value) {
            addCriterion("cabinet_id <", value, "cabinetId");
            return (Criteria) this;
        }

        public Criteria andCabinetIdLessThanOrEqualTo(Long value) {
            addCriterion("cabinet_id <=", value, "cabinetId");
            return (Criteria) this;
        }

        public Criteria andCabinetIdIn(List<Long> values) {
            addCriterion("cabinet_id in", values, "cabinetId");
            return (Criteria) this;
        }

        public Criteria andCabinetIdNotIn(List<Long> values) {
            addCriterion("cabinet_id not in", values, "cabinetId");
            return (Criteria) this;
        }

        public Criteria andCabinetIdBetween(Long value1, Long value2) {
            addCriterion("cabinet_id between", value1, value2, "cabinetId");
            return (Criteria) this;
        }

        public Criteria andCabinetIdNotBetween(Long value1, Long value2) {
            addCriterion("cabinet_id not between", value1, value2, "cabinetId");
            return (Criteria) this;
        }

        public Criteria andCabinetChannelIsNull() {
            addCriterion("cabinet_channel is null");
            return (Criteria) this;
        }

        public Criteria andCabinetChannelIsNotNull() {
            addCriterion("cabinet_channel is not null");
            return (Criteria) this;
        }

        public Criteria andCabinetChannelEqualTo(Integer value) {
            addCriterion("cabinet_channel =", value, "cabinetChannel");
            return (Criteria) this;
        }

        public Criteria andCabinetChannelNotEqualTo(Integer value) {
            addCriterion("cabinet_channel <>", value, "cabinetChannel");
            return (Criteria) this;
        }

        public Criteria andCabinetChannelGreaterThan(Integer value) {
            addCriterion("cabinet_channel >", value, "cabinetChannel");
            return (Criteria) this;
        }

        public Criteria andCabinetChannelGreaterThanOrEqualTo(Integer value) {
            addCriterion("cabinet_channel >=", value, "cabinetChannel");
            return (Criteria) this;
        }

        public Criteria andCabinetChannelLessThan(Integer value) {
            addCriterion("cabinet_channel <", value, "cabinetChannel");
            return (Criteria) this;
        }

        public Criteria andCabinetChannelLessThanOrEqualTo(Integer value) {
            addCriterion("cabinet_channel <=", value, "cabinetChannel");
            return (Criteria) this;
        }

        public Criteria andCabinetChannelIn(List<Integer> values) {
            addCriterion("cabinet_channel in", values, "cabinetChannel");
            return (Criteria) this;
        }

        public Criteria andCabinetChannelNotIn(List<Integer> values) {
            addCriterion("cabinet_channel not in", values, "cabinetChannel");
            return (Criteria) this;
        }

        public Criteria andCabinetChannelBetween(Integer value1, Integer value2) {
            addCriterion("cabinet_channel between", value1, value2, "cabinetChannel");
            return (Criteria) this;
        }

        public Criteria andCabinetChannelNotBetween(Integer value1, Integer value2) {
            addCriterion("cabinet_channel not between", value1, value2, "cabinetChannel");
            return (Criteria) this;
        }

        public Criteria andElectricityIsNull() {
            addCriterion("electricity is null");
            return (Criteria) this;
        }

        public Criteria andElectricityIsNotNull() {
            addCriterion("electricity is not null");
            return (Criteria) this;
        }

        public Criteria andElectricityEqualTo(Integer value) {
            addCriterion("electricity =", value, "electricity");
            return (Criteria) this;
        }

        public Criteria andElectricityNotEqualTo(Integer value) {
            addCriterion("electricity <>", value, "electricity");
            return (Criteria) this;
        }

        public Criteria andElectricityGreaterThan(Integer value) {
            addCriterion("electricity >", value, "electricity");
            return (Criteria) this;
        }

        public Criteria andElectricityGreaterThanOrEqualTo(Integer value) {
            addCriterion("electricity >=", value, "electricity");
            return (Criteria) this;
        }

        public Criteria andElectricityLessThan(Integer value) {
            addCriterion("electricity <", value, "electricity");
            return (Criteria) this;
        }

        public Criteria andElectricityLessThanOrEqualTo(Integer value) {
            addCriterion("electricity <=", value, "electricity");
            return (Criteria) this;
        }

        public Criteria andElectricityIn(List<Integer> values) {
            addCriterion("electricity in", values, "electricity");
            return (Criteria) this;
        }

        public Criteria andElectricityNotIn(List<Integer> values) {
            addCriterion("electricity not in", values, "electricity");
            return (Criteria) this;
        }

        public Criteria andElectricityBetween(Integer value1, Integer value2) {
            addCriterion("electricity between", value1, value2, "electricity");
            return (Criteria) this;
        }

        public Criteria andElectricityNotBetween(Integer value1, Integer value2) {
            addCriterion("electricity not between", value1, value2, "electricity");
            return (Criteria) this;
        }

        public Criteria andVoltageIsNull() {
            addCriterion("voltage is null");
            return (Criteria) this;
        }

        public Criteria andVoltageIsNotNull() {
            addCriterion("voltage is not null");
            return (Criteria) this;
        }

        public Criteria andVoltageEqualTo(Integer value) {
            addCriterion("voltage =", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageNotEqualTo(Integer value) {
            addCriterion("voltage <>", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageGreaterThan(Integer value) {
            addCriterion("voltage >", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageGreaterThanOrEqualTo(Integer value) {
            addCriterion("voltage >=", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageLessThan(Integer value) {
            addCriterion("voltage <", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageLessThanOrEqualTo(Integer value) {
            addCriterion("voltage <=", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageIn(List<Integer> values) {
            addCriterion("voltage in", values, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageNotIn(List<Integer> values) {
            addCriterion("voltage not in", values, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageBetween(Integer value1, Integer value2) {
            addCriterion("voltage between", value1, value2, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageNotBetween(Integer value1, Integer value2) {
            addCriterion("voltage not between", value1, value2, "voltage");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCableIsNull() {
            addCriterion("cable is null");
            return (Criteria) this;
        }

        public Criteria andCableIsNotNull() {
            addCriterion("cable is not null");
            return (Criteria) this;
        }

        public Criteria andCableEqualTo(String value) {
            addCriterion("cable =", value, "cable");
            return (Criteria) this;
        }

        public Criteria andCableNotEqualTo(String value) {
            addCriterion("cable <>", value, "cable");
            return (Criteria) this;
        }

        public Criteria andCableGreaterThan(String value) {
            addCriterion("cable >", value, "cable");
            return (Criteria) this;
        }

        public Criteria andCableGreaterThanOrEqualTo(String value) {
            addCriterion("cable >=", value, "cable");
            return (Criteria) this;
        }

        public Criteria andCableLessThan(String value) {
            addCriterion("cable <", value, "cable");
            return (Criteria) this;
        }

        public Criteria andCableLessThanOrEqualTo(String value) {
            addCriterion("cable <=", value, "cable");
            return (Criteria) this;
        }

        public Criteria andCableLike(String value) {
            addCriterion("cable like", value, "cable");
            return (Criteria) this;
        }

        public Criteria andCableNotLike(String value) {
            addCriterion("cable not like", value, "cable");
            return (Criteria) this;
        }

        public Criteria andCableIn(List<String> values) {
            addCriterion("cable in", values, "cable");
            return (Criteria) this;
        }

        public Criteria andCableNotIn(List<String> values) {
            addCriterion("cable not in", values, "cable");
            return (Criteria) this;
        }

        public Criteria andCableBetween(String value1, String value2) {
            addCriterion("cable between", value1, value2, "cable");
            return (Criteria) this;
        }

        public Criteria andCableNotBetween(String value1, String value2) {
            addCriterion("cable not between", value1, value2, "cable");
            return (Criteria) this;
        }

        public Criteria andAdapterIsNull() {
            addCriterion("adapter is null");
            return (Criteria) this;
        }

        public Criteria andAdapterIsNotNull() {
            addCriterion("adapter is not null");
            return (Criteria) this;
        }

        public Criteria andAdapterEqualTo(Boolean value) {
            addCriterion("adapter =", value, "adapter");
            return (Criteria) this;
        }

        public Criteria andAdapterNotEqualTo(Boolean value) {
            addCriterion("adapter <>", value, "adapter");
            return (Criteria) this;
        }

        public Criteria andAdapterGreaterThan(Boolean value) {
            addCriterion("adapter >", value, "adapter");
            return (Criteria) this;
        }

        public Criteria andAdapterGreaterThanOrEqualTo(Boolean value) {
            addCriterion("adapter >=", value, "adapter");
            return (Criteria) this;
        }

        public Criteria andAdapterLessThan(Boolean value) {
            addCriterion("adapter <", value, "adapter");
            return (Criteria) this;
        }

        public Criteria andAdapterLessThanOrEqualTo(Boolean value) {
            addCriterion("adapter <=", value, "adapter");
            return (Criteria) this;
        }

        public Criteria andAdapterIn(List<Boolean> values) {
            addCriterion("adapter in", values, "adapter");
            return (Criteria) this;
        }

        public Criteria andAdapterNotIn(List<Boolean> values) {
            addCriterion("adapter not in", values, "adapter");
            return (Criteria) this;
        }

        public Criteria andAdapterBetween(Boolean value1, Boolean value2) {
            addCriterion("adapter between", value1, value2, "adapter");
            return (Criteria) this;
        }

        public Criteria andAdapterNotBetween(Boolean value1, Boolean value2) {
            addCriterion("adapter not between", value1, value2, "adapter");
            return (Criteria) this;
        }

        public Criteria andIsdamageIsNull() {
            addCriterion("isdamage is null");
            return (Criteria) this;
        }

        public Criteria andIsdamageIsNotNull() {
            addCriterion("isdamage is not null");
            return (Criteria) this;
        }

        public Criteria andIsdamageEqualTo(String value) {
            addCriterion("isdamage =", value, "isdamage");
            return (Criteria) this;
        }

        public Criteria andIsdamageNotEqualTo(String value) {
            addCriterion("isdamage <>", value, "isdamage");
            return (Criteria) this;
        }

        public Criteria andIsdamageGreaterThan(String value) {
            addCriterion("isdamage >", value, "isdamage");
            return (Criteria) this;
        }

        public Criteria andIsdamageGreaterThanOrEqualTo(String value) {
            addCriterion("isdamage >=", value, "isdamage");
            return (Criteria) this;
        }

        public Criteria andIsdamageLessThan(String value) {
            addCriterion("isdamage <", value, "isdamage");
            return (Criteria) this;
        }

        public Criteria andIsdamageLessThanOrEqualTo(String value) {
            addCriterion("isdamage <=", value, "isdamage");
            return (Criteria) this;
        }

        public Criteria andIsdamageLike(String value) {
            addCriterion("isdamage like", value, "isdamage");
            return (Criteria) this;
        }

        public Criteria andIsdamageNotLike(String value) {
            addCriterion("isdamage not like", value, "isdamage");
            return (Criteria) this;
        }

        public Criteria andIsdamageIn(List<String> values) {
            addCriterion("isdamage in", values, "isdamage");
            return (Criteria) this;
        }

        public Criteria andIsdamageNotIn(List<String> values) {
            addCriterion("isdamage not in", values, "isdamage");
            return (Criteria) this;
        }

        public Criteria andIsdamageBetween(String value1, String value2) {
            addCriterion("isdamage between", value1, value2, "isdamage");
            return (Criteria) this;
        }

        public Criteria andIsdamageNotBetween(String value1, String value2) {
            addCriterion("isdamage not between", value1, value2, "isdamage");
            return (Criteria) this;
        }

        public Criteria andColorIdIsNull() {
            addCriterion("color_Id is null");
            return (Criteria) this;
        }

        public Criteria andColorIdIsNotNull() {
            addCriterion("color_Id is not null");
            return (Criteria) this;
        }

        public Criteria andColorIdEqualTo(String value) {
            addCriterion("color_Id =", value, "colorId");
            return (Criteria) this;
        }

        public Criteria andColorIdNotEqualTo(String value) {
            addCriterion("color_Id <>", value, "colorId");
            return (Criteria) this;
        }

        public Criteria andColorIdGreaterThan(String value) {
            addCriterion("color_Id >", value, "colorId");
            return (Criteria) this;
        }

        public Criteria andColorIdGreaterThanOrEqualTo(String value) {
            addCriterion("color_Id >=", value, "colorId");
            return (Criteria) this;
        }

        public Criteria andColorIdLessThan(String value) {
            addCriterion("color_Id <", value, "colorId");
            return (Criteria) this;
        }

        public Criteria andColorIdLessThanOrEqualTo(String value) {
            addCriterion("color_Id <=", value, "colorId");
            return (Criteria) this;
        }

        public Criteria andColorIdLike(String value) {
            addCriterion("color_Id like", value, "colorId");
            return (Criteria) this;
        }

        public Criteria andColorIdNotLike(String value) {
            addCriterion("color_Id not like", value, "colorId");
            return (Criteria) this;
        }

        public Criteria andColorIdIn(List<String> values) {
            addCriterion("color_Id in", values, "colorId");
            return (Criteria) this;
        }

        public Criteria andColorIdNotIn(List<String> values) {
            addCriterion("color_Id not in", values, "colorId");
            return (Criteria) this;
        }

        public Criteria andColorIdBetween(String value1, String value2) {
            addCriterion("color_Id between", value1, value2, "colorId");
            return (Criteria) this;
        }

        public Criteria andColorIdNotBetween(String value1, String value2) {
            addCriterion("color_Id not between", value1, value2, "colorId");
            return (Criteria) this;
        }

        public Criteria andTemperatureIsNull() {
            addCriterion("temperature is null");
            return (Criteria) this;
        }

        public Criteria andTemperatureIsNotNull() {
            addCriterion("temperature is not null");
            return (Criteria) this;
        }

        public Criteria andTemperatureEqualTo(String value) {
            addCriterion("temperature =", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotEqualTo(String value) {
            addCriterion("temperature <>", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureGreaterThan(String value) {
            addCriterion("temperature >", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureGreaterThanOrEqualTo(String value) {
            addCriterion("temperature >=", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureLessThan(String value) {
            addCriterion("temperature <", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureLessThanOrEqualTo(String value) {
            addCriterion("temperature <=", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureLike(String value) {
            addCriterion("temperature like", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotLike(String value) {
            addCriterion("temperature not like", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureIn(List<String> values) {
            addCriterion("temperature in", values, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotIn(List<String> values) {
            addCriterion("temperature not in", values, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureBetween(String value1, String value2) {
            addCriterion("temperature between", value1, value2, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotBetween(String value1, String value2) {
            addCriterion("temperature not between", value1, value2, "temperature");
            return (Criteria) this;
        }

        public Criteria andBattTypeIsNull() {
            addCriterion("batt_type is null");
            return (Criteria) this;
        }

        public Criteria andBattTypeIsNotNull() {
            addCriterion("batt_type is not null");
            return (Criteria) this;
        }

        public Criteria andBattTypeEqualTo(String value) {
            addCriterion("batt_type =", value, "battType");
            return (Criteria) this;
        }

        public Criteria andBattTypeNotEqualTo(String value) {
            addCriterion("batt_type <>", value, "battType");
            return (Criteria) this;
        }

        public Criteria andBattTypeGreaterThan(String value) {
            addCriterion("batt_type >", value, "battType");
            return (Criteria) this;
        }

        public Criteria andBattTypeGreaterThanOrEqualTo(String value) {
            addCriterion("batt_type >=", value, "battType");
            return (Criteria) this;
        }

        public Criteria andBattTypeLessThan(String value) {
            addCriterion("batt_type <", value, "battType");
            return (Criteria) this;
        }

        public Criteria andBattTypeLessThanOrEqualTo(String value) {
            addCriterion("batt_type <=", value, "battType");
            return (Criteria) this;
        }

        public Criteria andBattTypeLike(String value) {
            addCriterion("batt_type like", value, "battType");
            return (Criteria) this;
        }

        public Criteria andBattTypeNotLike(String value) {
            addCriterion("batt_type not like", value, "battType");
            return (Criteria) this;
        }

        public Criteria andBattTypeIn(List<String> values) {
            addCriterion("batt_type in", values, "battType");
            return (Criteria) this;
        }

        public Criteria andBattTypeNotIn(List<String> values) {
            addCriterion("batt_type not in", values, "battType");
            return (Criteria) this;
        }

        public Criteria andBattTypeBetween(String value1, String value2) {
            addCriterion("batt_type between", value1, value2, "battType");
            return (Criteria) this;
        }

        public Criteria andBattTypeNotBetween(String value1, String value2) {
            addCriterion("batt_type not between", value1, value2, "battType");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(Long value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(Long value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(Long value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(Long value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(Long value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(Long value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<Long> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<Long> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(Long value1, Long value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(Long value1, Long value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andLastLocationTimeIsNull() {
            addCriterion("last_location_time is null");
            return (Criteria) this;
        }

        public Criteria andLastLocationTimeIsNotNull() {
            addCriterion("last_location_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastLocationTimeEqualTo(Date value) {
            addCriterion("last_location_time =", value, "lastLocationTime");
            return (Criteria) this;
        }

        public Criteria andLastLocationTimeNotEqualTo(Date value) {
            addCriterion("last_location_time <>", value, "lastLocationTime");
            return (Criteria) this;
        }

        public Criteria andLastLocationTimeGreaterThan(Date value) {
            addCriterion("last_location_time >", value, "lastLocationTime");
            return (Criteria) this;
        }

        public Criteria andLastLocationTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_location_time >=", value, "lastLocationTime");
            return (Criteria) this;
        }

        public Criteria andLastLocationTimeLessThan(Date value) {
            addCriterion("last_location_time <", value, "lastLocationTime");
            return (Criteria) this;
        }

        public Criteria andLastLocationTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_location_time <=", value, "lastLocationTime");
            return (Criteria) this;
        }

        public Criteria andLastLocationTimeIn(List<Date> values) {
            addCriterion("last_location_time in", values, "lastLocationTime");
            return (Criteria) this;
        }

        public Criteria andLastLocationTimeNotIn(List<Date> values) {
            addCriterion("last_location_time not in", values, "lastLocationTime");
            return (Criteria) this;
        }

        public Criteria andLastLocationTimeBetween(Date value1, Date value2) {
            addCriterion("last_location_time between", value1, value2, "lastLocationTime");
            return (Criteria) this;
        }

        public Criteria andLastLocationTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_location_time not between", value1, value2, "lastLocationTime");
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