package vc.thinker.cabbage.sys.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sinco.mybatis.dal.core.AbstractExample;

import vc.thinker.cabbage.sys.bo.SysMessageLogBO;

public class SysMessageLogExample extends AbstractExample<SysMessageLogBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public SysMessageLogExample() {
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

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andImageTextIdIsNull() {
            addCriterion("image_text_id is null");
            return (Criteria) this;
        }

        public Criteria andImageTextIdIsNotNull() {
            addCriterion("image_text_id is not null");
            return (Criteria) this;
        }

        public Criteria andImageTextIdEqualTo(Long value) {
            addCriterion("image_text_id =", value, "imageTextId");
            return (Criteria) this;
        }

        public Criteria andImageTextIdNotEqualTo(Long value) {
            addCriterion("image_text_id <>", value, "imageTextId");
            return (Criteria) this;
        }

        public Criteria andImageTextIdGreaterThan(Long value) {
            addCriterion("image_text_id >", value, "imageTextId");
            return (Criteria) this;
        }

        public Criteria andImageTextIdGreaterThanOrEqualTo(Long value) {
            addCriterion("image_text_id >=", value, "imageTextId");
            return (Criteria) this;
        }

        public Criteria andImageTextIdLessThan(Long value) {
            addCriterion("image_text_id <", value, "imageTextId");
            return (Criteria) this;
        }

        public Criteria andImageTextIdLessThanOrEqualTo(Long value) {
            addCriterion("image_text_id <=", value, "imageTextId");
            return (Criteria) this;
        }

        public Criteria andImageTextIdIn(List<Long> values) {
            addCriterion("image_text_id in", values, "imageTextId");
            return (Criteria) this;
        }

        public Criteria andImageTextIdNotIn(List<Long> values) {
            addCriterion("image_text_id not in", values, "imageTextId");
            return (Criteria) this;
        }

        public Criteria andImageTextIdBetween(Long value1, Long value2) {
            addCriterion("image_text_id between", value1, value2, "imageTextId");
            return (Criteria) this;
        }

        public Criteria andImageTextIdNotBetween(Long value1, Long value2) {
            addCriterion("image_text_id not between", value1, value2, "imageTextId");
            return (Criteria) this;
        }

        public Criteria andIsImageTextIsNull() {
            addCriterion("is_image_text is null");
            return (Criteria) this;
        }

        public Criteria andIsImageTextIsNotNull() {
            addCriterion("is_image_text is not null");
            return (Criteria) this;
        }

        public Criteria andIsImageTextEqualTo(Boolean value) {
            addCriterion("is_image_text =", value, "isImageText");
            return (Criteria) this;
        }

        public Criteria andIsImageTextNotEqualTo(Boolean value) {
            addCriterion("is_image_text <>", value, "isImageText");
            return (Criteria) this;
        }

        public Criteria andIsImageTextGreaterThan(Boolean value) {
            addCriterion("is_image_text >", value, "isImageText");
            return (Criteria) this;
        }

        public Criteria andIsImageTextGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_image_text >=", value, "isImageText");
            return (Criteria) this;
        }

        public Criteria andIsImageTextLessThan(Boolean value) {
            addCriterion("is_image_text <", value, "isImageText");
            return (Criteria) this;
        }

        public Criteria andIsImageTextLessThanOrEqualTo(Boolean value) {
            addCriterion("is_image_text <=", value, "isImageText");
            return (Criteria) this;
        }

        public Criteria andIsImageTextIn(List<Boolean> values) {
            addCriterion("is_image_text in", values, "isImageText");
            return (Criteria) this;
        }

        public Criteria andIsImageTextNotIn(List<Boolean> values) {
            addCriterion("is_image_text not in", values, "isImageText");
            return (Criteria) this;
        }

        public Criteria andIsImageTextBetween(Boolean value1, Boolean value2) {
            addCriterion("is_image_text between", value1, value2, "isImageText");
            return (Criteria) this;
        }

        public Criteria andIsImageTextNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_image_text not between", value1, value2, "isImageText");
            return (Criteria) this;
        }

        public Criteria andToUserIdsIsNull() {
            addCriterion("to_user_ids is null");
            return (Criteria) this;
        }

        public Criteria andToUserIdsIsNotNull() {
            addCriterion("to_user_ids is not null");
            return (Criteria) this;
        }

        public Criteria andToUserIdsEqualTo(String value) {
            addCriterion("to_user_ids =", value, "toUserIds");
            return (Criteria) this;
        }

        public Criteria andToUserIdsNotEqualTo(String value) {
            addCriterion("to_user_ids <>", value, "toUserIds");
            return (Criteria) this;
        }

        public Criteria andToUserIdsGreaterThan(String value) {
            addCriterion("to_user_ids >", value, "toUserIds");
            return (Criteria) this;
        }

        public Criteria andToUserIdsGreaterThanOrEqualTo(String value) {
            addCriterion("to_user_ids >=", value, "toUserIds");
            return (Criteria) this;
        }

        public Criteria andToUserIdsLessThan(String value) {
            addCriterion("to_user_ids <", value, "toUserIds");
            return (Criteria) this;
        }

        public Criteria andToUserIdsLessThanOrEqualTo(String value) {
            addCriterion("to_user_ids <=", value, "toUserIds");
            return (Criteria) this;
        }

        public Criteria andToUserIdsLike(String value) {
            addCriterion("to_user_ids like", value, "toUserIds");
            return (Criteria) this;
        }

        public Criteria andToUserIdsNotLike(String value) {
            addCriterion("to_user_ids not like", value, "toUserIds");
            return (Criteria) this;
        }

        public Criteria andToUserIdsIn(List<String> values) {
            addCriterion("to_user_ids in", values, "toUserIds");
            return (Criteria) this;
        }

        public Criteria andToUserIdsNotIn(List<String> values) {
            addCriterion("to_user_ids not in", values, "toUserIds");
            return (Criteria) this;
        }

        public Criteria andToUserIdsBetween(String value1, String value2) {
            addCriterion("to_user_ids between", value1, value2, "toUserIds");
            return (Criteria) this;
        }

        public Criteria andToUserIdsNotBetween(String value1, String value2) {
            addCriterion("to_user_ids not between", value1, value2, "toUserIds");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("send_time is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("send_time is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Date value) {
            addCriterion("send_time =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterion("send_time <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterion("send_time >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("send_time >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Date value) {
            addCriterion("send_time <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("send_time <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Date> values) {
            addCriterion("send_time in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterion("send_time not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterion("send_time between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("send_time not between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andIsSendAllIsNull() {
            addCriterion("is_send_all is null");
            return (Criteria) this;
        }

        public Criteria andIsSendAllIsNotNull() {
            addCriterion("is_send_all is not null");
            return (Criteria) this;
        }

        public Criteria andIsSendAllEqualTo(Boolean value) {
            addCriterion("is_send_all =", value, "isSendAll");
            return (Criteria) this;
        }

        public Criteria andIsSendAllNotEqualTo(Boolean value) {
            addCriterion("is_send_all <>", value, "isSendAll");
            return (Criteria) this;
        }

        public Criteria andIsSendAllGreaterThan(Boolean value) {
            addCriterion("is_send_all >", value, "isSendAll");
            return (Criteria) this;
        }

        public Criteria andIsSendAllGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_send_all >=", value, "isSendAll");
            return (Criteria) this;
        }

        public Criteria andIsSendAllLessThan(Boolean value) {
            addCriterion("is_send_all <", value, "isSendAll");
            return (Criteria) this;
        }

        public Criteria andIsSendAllLessThanOrEqualTo(Boolean value) {
            addCriterion("is_send_all <=", value, "isSendAll");
            return (Criteria) this;
        }

        public Criteria andIsSendAllIn(List<Boolean> values) {
            addCriterion("is_send_all in", values, "isSendAll");
            return (Criteria) this;
        }

        public Criteria andIsSendAllNotIn(List<Boolean> values) {
            addCriterion("is_send_all not in", values, "isSendAll");
            return (Criteria) this;
        }

        public Criteria andIsSendAllBetween(Boolean value1, Boolean value2) {
            addCriterion("is_send_all between", value1, value2, "isSendAll");
            return (Criteria) this;
        }

        public Criteria andIsSendAllNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_send_all not between", value1, value2, "isSendAll");
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

        public Criteria andFromUserIdIsNull() {
            addCriterion("from_user_id is null");
            return (Criteria) this;
        }

        public Criteria andFromUserIdIsNotNull() {
            addCriterion("from_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andFromUserIdEqualTo(Long value) {
            addCriterion("from_user_id =", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdNotEqualTo(Long value) {
            addCriterion("from_user_id <>", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdGreaterThan(Long value) {
            addCriterion("from_user_id >", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("from_user_id >=", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdLessThan(Long value) {
            addCriterion("from_user_id <", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdLessThanOrEqualTo(Long value) {
            addCriterion("from_user_id <=", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdIn(List<Long> values) {
            addCriterion("from_user_id in", values, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdNotIn(List<Long> values) {
            addCriterion("from_user_id not in", values, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdBetween(Long value1, Long value2) {
            addCriterion("from_user_id between", value1, value2, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdNotBetween(Long value1, Long value2) {
            addCriterion("from_user_id not between", value1, value2, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andIsPlatformIsNull() {
            addCriterion("is_platform is null");
            return (Criteria) this;
        }

        public Criteria andIsPlatformIsNotNull() {
            addCriterion("is_platform is not null");
            return (Criteria) this;
        }

        public Criteria andIsPlatformEqualTo(Boolean value) {
            addCriterion("is_platform =", value, "isPlatform");
            return (Criteria) this;
        }

        public Criteria andIsPlatformNotEqualTo(Boolean value) {
            addCriterion("is_platform <>", value, "isPlatform");
            return (Criteria) this;
        }

        public Criteria andIsPlatformGreaterThan(Boolean value) {
            addCriterion("is_platform >", value, "isPlatform");
            return (Criteria) this;
        }

        public Criteria andIsPlatformGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_platform >=", value, "isPlatform");
            return (Criteria) this;
        }

        public Criteria andIsPlatformLessThan(Boolean value) {
            addCriterion("is_platform <", value, "isPlatform");
            return (Criteria) this;
        }

        public Criteria andIsPlatformLessThanOrEqualTo(Boolean value) {
            addCriterion("is_platform <=", value, "isPlatform");
            return (Criteria) this;
        }

        public Criteria andIsPlatformIn(List<Boolean> values) {
            addCriterion("is_platform in", values, "isPlatform");
            return (Criteria) this;
        }

        public Criteria andIsPlatformNotIn(List<Boolean> values) {
            addCriterion("is_platform not in", values, "isPlatform");
            return (Criteria) this;
        }

        public Criteria andIsPlatformBetween(Boolean value1, Boolean value2) {
            addCriterion("is_platform between", value1, value2, "isPlatform");
            return (Criteria) this;
        }

        public Criteria andIsPlatformNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_platform not between", value1, value2, "isPlatform");
            return (Criteria) this;
        }

        public Criteria andSendTypeIsNull() {
            addCriterion("send_type is null");
            return (Criteria) this;
        }

        public Criteria andSendTypeIsNotNull() {
            addCriterion("send_type is not null");
            return (Criteria) this;
        }

        public Criteria andSendTypeEqualTo(Integer value) {
            addCriterion("send_type =", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotEqualTo(Integer value) {
            addCriterion("send_type <>", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeGreaterThan(Integer value) {
            addCriterion("send_type >", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_type >=", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeLessThan(Integer value) {
            addCriterion("send_type <", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeLessThanOrEqualTo(Integer value) {
            addCriterion("send_type <=", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeIn(List<Integer> values) {
            addCriterion("send_type in", values, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotIn(List<Integer> values) {
            addCriterion("send_type not in", values, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeBetween(Integer value1, Integer value2) {
            addCriterion("send_type between", value1, value2, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("send_type not between", value1, value2, "sendType");
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
