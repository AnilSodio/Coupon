package vc.thinker.cabbage.se.model;

import com.sinco.mybatis.dal.core.AbstractExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vc.thinker.cabbage.se.bo.CabinetBO;

public class CabinetExample extends AbstractExample<CabinetBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public CabinetExample() {
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

        public Criteria andCabinetCodeIsNull() {
            addCriterion("cabinet_code is null");
            return (Criteria) this;
        }

        public Criteria andCabinetCodeIsNotNull() {
            addCriterion("cabinet_code is not null");
            return (Criteria) this;
        }

        public Criteria andCabinetCodeEqualTo(String value) {
            addCriterion("cabinet_code =", value, "cabinetCode");
            return (Criteria) this;
        }

        public Criteria andCabinetCodeNotEqualTo(String value) {
            addCriterion("cabinet_code <>", value, "cabinetCode");
            return (Criteria) this;
        }

        public Criteria andCabinetCodeGreaterThan(String value) {
            addCriterion("cabinet_code >", value, "cabinetCode");
            return (Criteria) this;
        }

        public Criteria andCabinetCodeGreaterThanOrEqualTo(String value) {
            addCriterion("cabinet_code >=", value, "cabinetCode");
            return (Criteria) this;
        }

        public Criteria andCabinetCodeLessThan(String value) {
            addCriterion("cabinet_code <", value, "cabinetCode");
            return (Criteria) this;
        }

        public Criteria andCabinetCodeLessThanOrEqualTo(String value) {
            addCriterion("cabinet_code <=", value, "cabinetCode");
            return (Criteria) this;
        }

        public Criteria andCabinetCodeLike(String value) {
            addCriterion("cabinet_code like", value, "cabinetCode");
            return (Criteria) this;
        }

        public Criteria andCabinetCodeNotLike(String value) {
            addCriterion("cabinet_code not like", value, "cabinetCode");
            return (Criteria) this;
        }

        public Criteria andCabinetCodeIn(List<String> values) {
            addCriterion("cabinet_code in", values, "cabinetCode");
            return (Criteria) this;
        }

        public Criteria andCabinetCodeNotIn(List<String> values) {
            addCriterion("cabinet_code not in", values, "cabinetCode");
            return (Criteria) this;
        }

        public Criteria andCabinetCodeBetween(String value1, String value2) {
            addCriterion("cabinet_code between", value1, value2, "cabinetCode");
            return (Criteria) this;
        }

        public Criteria andCabinetCodeNotBetween(String value1, String value2) {
            addCriterion("cabinet_code not between", value1, value2, "cabinetCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeIsNull() {
            addCriterion("sys_code is null");
            return (Criteria) this;
        }

        public Criteria andSysCodeIsNotNull() {
            addCriterion("sys_code is not null");
            return (Criteria) this;
        }

        public Criteria andSysCodeEqualTo(String value) {
            addCriterion("sys_code =", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeNotEqualTo(String value) {
            addCriterion("sys_code <>", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeGreaterThan(String value) {
            addCriterion("sys_code >", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sys_code >=", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeLessThan(String value) {
            addCriterion("sys_code <", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeLessThanOrEqualTo(String value) {
            addCriterion("sys_code <=", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeLike(String value) {
            addCriterion("sys_code like", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeNotLike(String value) {
            addCriterion("sys_code not like", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeIn(List<String> values) {
            addCriterion("sys_code in", values, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeNotIn(List<String> values) {
            addCriterion("sys_code not in", values, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeBetween(String value1, String value2) {
            addCriterion("sys_code between", value1, value2, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeNotBetween(String value1, String value2) {
            addCriterion("sys_code not between", value1, value2, "sysCode");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNull() {
            addCriterion("type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(Long value) {
            addCriterion("type_id =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(Long value) {
            addCriterion("type_id <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(Long value) {
            addCriterion("type_id >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("type_id >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(Long value) {
            addCriterion("type_id <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("type_id <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<Long> values) {
            addCriterion("type_id in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<Long> values) {
            addCriterion("type_id not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(Long value1, Long value2) {
            addCriterion("type_id between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("type_id not between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andSellerIdIsNull() {
            addCriterion("seller_id is null");
            return (Criteria) this;
        }

        public Criteria andSellerIdIsNotNull() {
            addCriterion("seller_id is not null");
            return (Criteria) this;
        }

        public Criteria andSellerIdEqualTo(Long value) {
            addCriterion("seller_id =", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotEqualTo(Long value) {
            addCriterion("seller_id <>", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdGreaterThan(Long value) {
            addCriterion("seller_id >", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("seller_id >=", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdLessThan(Long value) {
            addCriterion("seller_id <", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdLessThanOrEqualTo(Long value) {
            addCriterion("seller_id <=", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdIn(List<Long> values) {
            addCriterion("seller_id in", values, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotIn(List<Long> values) {
            addCriterion("seller_id not in", values, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdBetween(Long value1, Long value2) {
            addCriterion("seller_id between", value1, value2, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotBetween(Long value1, Long value2) {
            addCriterion("seller_id not between", value1, value2, "sellerId");
            return (Criteria) this;
        }

        public Criteria andAgentIdIsNull() {
            addCriterion("agent_id is null");
            return (Criteria) this;
        }

        public Criteria andAgentIdIsNotNull() {
            addCriterion("agent_id is not null");
            return (Criteria) this;
        }

        public Criteria andAgentIdEqualTo(Long value) {
            addCriterion("agent_id =", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotEqualTo(Long value) {
            addCriterion("agent_id <>", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdGreaterThan(Long value) {
            addCriterion("agent_id >", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("agent_id >=", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLessThan(Long value) {
            addCriterion("agent_id <", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLessThanOrEqualTo(Long value) {
            addCriterion("agent_id <=", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdIn(List<Long> values) {
            addCriterion("agent_id in", values, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotIn(List<Long> values) {
            addCriterion("agent_id not in", values, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdBetween(Long value1, Long value2) {
            addCriterion("agent_id between", value1, value2, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotBetween(Long value1, Long value2) {
            addCriterion("agent_id not between", value1, value2, "agentId");
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

        public Criteria andIsDeliveryIsNull() {
            addCriterion("is_delivery is null");
            return (Criteria) this;
        }

        public Criteria andIsDeliveryIsNotNull() {
            addCriterion("is_delivery is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeliveryEqualTo(Boolean value) {
            addCriterion("is_delivery =", value, "isDelivery");
            return (Criteria) this;
        }

        public Criteria andIsDeliveryNotEqualTo(Boolean value) {
            addCriterion("is_delivery <>", value, "isDelivery");
            return (Criteria) this;
        }

        public Criteria andIsDeliveryGreaterThan(Boolean value) {
            addCriterion("is_delivery >", value, "isDelivery");
            return (Criteria) this;
        }

        public Criteria andIsDeliveryGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_delivery >=", value, "isDelivery");
            return (Criteria) this;
        }

        public Criteria andIsDeliveryLessThan(Boolean value) {
            addCriterion("is_delivery <", value, "isDelivery");
            return (Criteria) this;
        }

        public Criteria andIsDeliveryLessThanOrEqualTo(Boolean value) {
            addCriterion("is_delivery <=", value, "isDelivery");
            return (Criteria) this;
        }

        public Criteria andIsDeliveryIn(List<Boolean> values) {
            addCriterion("is_delivery in", values, "isDelivery");
            return (Criteria) this;
        }

        public Criteria andIsDeliveryNotIn(List<Boolean> values) {
            addCriterion("is_delivery not in", values, "isDelivery");
            return (Criteria) this;
        }

        public Criteria andIsDeliveryBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delivery between", value1, value2, "isDelivery");
            return (Criteria) this;
        }

        public Criteria andIsDeliveryNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delivery not between", value1, value2, "isDelivery");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIsNull() {
            addCriterion("delivery_time is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIsNotNull() {
            addCriterion("delivery_time is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeEqualTo(Date value) {
            addCriterion("delivery_time =", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotEqualTo(Date value) {
            addCriterion("delivery_time <>", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeGreaterThan(Date value) {
            addCriterion("delivery_time >", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("delivery_time >=", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLessThan(Date value) {
            addCriterion("delivery_time <", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLessThanOrEqualTo(Date value) {
            addCriterion("delivery_time <=", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIn(List<Date> values) {
            addCriterion("delivery_time in", values, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotIn(List<Date> values) {
            addCriterion("delivery_time not in", values, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeBetween(Date value1, Date value2) {
            addCriterion("delivery_time between", value1, value2, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotBetween(Date value1, Date value2) {
            addCriterion("delivery_time not between", value1, value2, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdIsNull() {
            addCriterion("delivery_id is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdIsNotNull() {
            addCriterion("delivery_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdEqualTo(Long value) {
            addCriterion("delivery_id =", value, "deliveryId");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdNotEqualTo(Long value) {
            addCriterion("delivery_id <>", value, "deliveryId");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdGreaterThan(Long value) {
            addCriterion("delivery_id >", value, "deliveryId");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("delivery_id >=", value, "deliveryId");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdLessThan(Long value) {
            addCriterion("delivery_id <", value, "deliveryId");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdLessThanOrEqualTo(Long value) {
            addCriterion("delivery_id <=", value, "deliveryId");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdIn(List<Long> values) {
            addCriterion("delivery_id in", values, "deliveryId");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdNotIn(List<Long> values) {
            addCriterion("delivery_id not in", values, "deliveryId");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdBetween(Long value1, Long value2) {
            addCriterion("delivery_id between", value1, value2, "deliveryId");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdNotBetween(Long value1, Long value2) {
            addCriterion("delivery_id not between", value1, value2, "deliveryId");
            return (Criteria) this;
        }

        public Criteria andLocationLonIsNull() {
            addCriterion("location_lon is null");
            return (Criteria) this;
        }

        public Criteria andLocationLonIsNotNull() {
            addCriterion("location_lon is not null");
            return (Criteria) this;
        }

        public Criteria andLocationLonEqualTo(BigDecimal value) {
            addCriterion("location_lon =", value, "locationLon");
            return (Criteria) this;
        }

        public Criteria andLocationLonNotEqualTo(BigDecimal value) {
            addCriterion("location_lon <>", value, "locationLon");
            return (Criteria) this;
        }

        public Criteria andLocationLonGreaterThan(BigDecimal value) {
            addCriterion("location_lon >", value, "locationLon");
            return (Criteria) this;
        }

        public Criteria andLocationLonGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("location_lon >=", value, "locationLon");
            return (Criteria) this;
        }

        public Criteria andLocationLonLessThan(BigDecimal value) {
            addCriterion("location_lon <", value, "locationLon");
            return (Criteria) this;
        }

        public Criteria andLocationLonLessThanOrEqualTo(BigDecimal value) {
            addCriterion("location_lon <=", value, "locationLon");
            return (Criteria) this;
        }

        public Criteria andLocationLonIn(List<BigDecimal> values) {
            addCriterion("location_lon in", values, "locationLon");
            return (Criteria) this;
        }

        public Criteria andLocationLonNotIn(List<BigDecimal> values) {
            addCriterion("location_lon not in", values, "locationLon");
            return (Criteria) this;
        }

        public Criteria andLocationLonBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("location_lon between", value1, value2, "locationLon");
            return (Criteria) this;
        }

        public Criteria andLocationLonNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("location_lon not between", value1, value2, "locationLon");
            return (Criteria) this;
        }

        public Criteria andLocationLatIsNull() {
            addCriterion("location_lat is null");
            return (Criteria) this;
        }

        public Criteria andLocationLatIsNotNull() {
            addCriterion("location_lat is not null");
            return (Criteria) this;
        }

        public Criteria andLocationLatEqualTo(BigDecimal value) {
            addCriterion("location_lat =", value, "locationLat");
            return (Criteria) this;
        }

        public Criteria andLocationLatNotEqualTo(BigDecimal value) {
            addCriterion("location_lat <>", value, "locationLat");
            return (Criteria) this;
        }

        public Criteria andLocationLatGreaterThan(BigDecimal value) {
            addCriterion("location_lat >", value, "locationLat");
            return (Criteria) this;
        }

        public Criteria andLocationLatGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("location_lat >=", value, "locationLat");
            return (Criteria) this;
        }

        public Criteria andLocationLatLessThan(BigDecimal value) {
            addCriterion("location_lat <", value, "locationLat");
            return (Criteria) this;
        }

        public Criteria andLocationLatLessThanOrEqualTo(BigDecimal value) {
            addCriterion("location_lat <=", value, "locationLat");
            return (Criteria) this;
        }

        public Criteria andLocationLatIn(List<BigDecimal> values) {
            addCriterion("location_lat in", values, "locationLat");
            return (Criteria) this;
        }

        public Criteria andLocationLatNotIn(List<BigDecimal> values) {
            addCriterion("location_lat not in", values, "locationLat");
            return (Criteria) this;
        }

        public Criteria andLocationLatBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("location_lat between", value1, value2, "locationLat");
            return (Criteria) this;
        }

        public Criteria andLocationLatNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("location_lat not between", value1, value2, "locationLat");
            return (Criteria) this;
        }

        public Criteria andLocationAddressIsNull() {
            addCriterion("location_address is null");
            return (Criteria) this;
        }

        public Criteria andLocationAddressIsNotNull() {
            addCriterion("location_address is not null");
            return (Criteria) this;
        }

        public Criteria andLocationAddressEqualTo(String value) {
            addCriterion("location_address =", value, "locationAddress");
            return (Criteria) this;
        }

        public Criteria andLocationAddressNotEqualTo(String value) {
            addCriterion("location_address <>", value, "locationAddress");
            return (Criteria) this;
        }

        public Criteria andLocationAddressGreaterThan(String value) {
            addCriterion("location_address >", value, "locationAddress");
            return (Criteria) this;
        }

        public Criteria andLocationAddressGreaterThanOrEqualTo(String value) {
            addCriterion("location_address >=", value, "locationAddress");
            return (Criteria) this;
        }

        public Criteria andLocationAddressLessThan(String value) {
            addCriterion("location_address <", value, "locationAddress");
            return (Criteria) this;
        }

        public Criteria andLocationAddressLessThanOrEqualTo(String value) {
            addCriterion("location_address <=", value, "locationAddress");
            return (Criteria) this;
        }

        public Criteria andLocationAddressLike(String value) {
            addCriterion("location_address like", value, "locationAddress");
            return (Criteria) this;
        }

        public Criteria andLocationAddressNotLike(String value) {
            addCriterion("location_address not like", value, "locationAddress");
            return (Criteria) this;
        }

        public Criteria andLocationAddressIn(List<String> values) {
            addCriterion("location_address in", values, "locationAddress");
            return (Criteria) this;
        }

        public Criteria andLocationAddressNotIn(List<String> values) {
            addCriterion("location_address not in", values, "locationAddress");
            return (Criteria) this;
        }

        public Criteria andLocationAddressBetween(String value1, String value2) {
            addCriterion("location_address between", value1, value2, "locationAddress");
            return (Criteria) this;
        }

        public Criteria andLocationAddressNotBetween(String value1, String value2) {
            addCriterion("location_address not between", value1, value2, "locationAddress");
            return (Criteria) this;
        }

        public Criteria andLocationDescIsNull() {
            addCriterion("location_desc is null");
            return (Criteria) this;
        }

        public Criteria andLocationDescIsNotNull() {
            addCriterion("location_desc is not null");
            return (Criteria) this;
        }

        public Criteria andLocationDescEqualTo(String value) {
            addCriterion("location_desc =", value, "locationDesc");
            return (Criteria) this;
        }

        public Criteria andLocationDescNotEqualTo(String value) {
            addCriterion("location_desc <>", value, "locationDesc");
            return (Criteria) this;
        }

        public Criteria andLocationDescGreaterThan(String value) {
            addCriterion("location_desc >", value, "locationDesc");
            return (Criteria) this;
        }

        public Criteria andLocationDescGreaterThanOrEqualTo(String value) {
            addCriterion("location_desc >=", value, "locationDesc");
            return (Criteria) this;
        }

        public Criteria andLocationDescLessThan(String value) {
            addCriterion("location_desc <", value, "locationDesc");
            return (Criteria) this;
        }

        public Criteria andLocationDescLessThanOrEqualTo(String value) {
            addCriterion("location_desc <=", value, "locationDesc");
            return (Criteria) this;
        }

        public Criteria andLocationDescLike(String value) {
            addCriterion("location_desc like", value, "locationDesc");
            return (Criteria) this;
        }

        public Criteria andLocationDescNotLike(String value) {
            addCriterion("location_desc not like", value, "locationDesc");
            return (Criteria) this;
        }

        public Criteria andLocationDescIn(List<String> values) {
            addCriterion("location_desc in", values, "locationDesc");
            return (Criteria) this;
        }

        public Criteria andLocationDescNotIn(List<String> values) {
            addCriterion("location_desc not in", values, "locationDesc");
            return (Criteria) this;
        }

        public Criteria andLocationDescBetween(String value1, String value2) {
            addCriterion("location_desc between", value1, value2, "locationDesc");
            return (Criteria) this;
        }

        public Criteria andLocationDescNotBetween(String value1, String value2) {
            addCriterion("location_desc not between", value1, value2, "locationDesc");
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

        public Criteria andCabinetAliasIsNull() {
            addCriterion("cabinet_alias is null");
            return (Criteria) this;
        }

        public Criteria andCabinetAliasIsNotNull() {
            addCriterion("cabinet_alias is not null");
            return (Criteria) this;
        }

        public Criteria andCabinetAliasEqualTo(String value) {
            addCriterion("cabinet_alias =", value, "cabinetAlias");
            return (Criteria) this;
        }

        public Criteria andCabinetAliasNotEqualTo(String value) {
            addCriterion("cabinet_alias <>", value, "cabinetAlias");
            return (Criteria) this;
        }

        public Criteria andCabinetAliasGreaterThan(String value) {
            addCriterion("cabinet_alias >", value, "cabinetAlias");
            return (Criteria) this;
        }

        public Criteria andCabinetAliasGreaterThanOrEqualTo(String value) {
            addCriterion("cabinet_alias >=", value, "cabinetAlias");
            return (Criteria) this;
        }

        public Criteria andCabinetAliasLessThan(String value) {
            addCriterion("cabinet_alias <", value, "cabinetAlias");
            return (Criteria) this;
        }

        public Criteria andCabinetAliasLessThanOrEqualTo(String value) {
            addCriterion("cabinet_alias <=", value, "cabinetAlias");
            return (Criteria) this;
        }

        public Criteria andCabinetAliasLike(String value) {
            addCriterion("cabinet_alias like", value, "cabinetAlias");
            return (Criteria) this;
        }

        public Criteria andCabinetAliasNotLike(String value) {
            addCriterion("cabinet_alias not like", value, "cabinetAlias");
            return (Criteria) this;
        }

        public Criteria andCabinetAliasIn(List<String> values) {
            addCriterion("cabinet_alias in", values, "cabinetAlias");
            return (Criteria) this;
        }

        public Criteria andCabinetAliasNotIn(List<String> values) {
            addCriterion("cabinet_alias not in", values, "cabinetAlias");
            return (Criteria) this;
        }

        public Criteria andCabinetAliasBetween(String value1, String value2) {
            addCriterion("cabinet_alias between", value1, value2, "cabinetAlias");
            return (Criteria) this;
        }

        public Criteria andCabinetAliasNotBetween(String value1, String value2) {
            addCriterion("cabinet_alias not between", value1, value2, "cabinetAlias");
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