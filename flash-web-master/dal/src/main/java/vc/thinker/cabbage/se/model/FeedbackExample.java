package vc.thinker.cabbage.se.model;

import com.sinco.mybatis.dal.core.AbstractExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vc.thinker.cabbage.se.bo.FeedbackBO;

public class FeedbackExample extends AbstractExample<FeedbackBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public FeedbackExample() {
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

        public Criteria andFeedTypeIsNull() {
            addCriterion("feed_type is null");
            return (Criteria) this;
        }

        public Criteria andFeedTypeIsNotNull() {
            addCriterion("feed_type is not null");
            return (Criteria) this;
        }

        public Criteria andFeedTypeEqualTo(String value) {
            addCriterion("feed_type =", value, "feedType");
            return (Criteria) this;
        }

        public Criteria andFeedTypeNotEqualTo(String value) {
            addCriterion("feed_type <>", value, "feedType");
            return (Criteria) this;
        }

        public Criteria andFeedTypeGreaterThan(String value) {
            addCriterion("feed_type >", value, "feedType");
            return (Criteria) this;
        }

        public Criteria andFeedTypeGreaterThanOrEqualTo(String value) {
            addCriterion("feed_type >=", value, "feedType");
            return (Criteria) this;
        }

        public Criteria andFeedTypeLessThan(String value) {
            addCriterion("feed_type <", value, "feedType");
            return (Criteria) this;
        }

        public Criteria andFeedTypeLessThanOrEqualTo(String value) {
            addCriterion("feed_type <=", value, "feedType");
            return (Criteria) this;
        }

        public Criteria andFeedTypeLike(String value) {
            addCriterion("feed_type like", value, "feedType");
            return (Criteria) this;
        }

        public Criteria andFeedTypeNotLike(String value) {
            addCriterion("feed_type not like", value, "feedType");
            return (Criteria) this;
        }

        public Criteria andFeedTypeIn(List<String> values) {
            addCriterion("feed_type in", values, "feedType");
            return (Criteria) this;
        }

        public Criteria andFeedTypeNotIn(List<String> values) {
            addCriterion("feed_type not in", values, "feedType");
            return (Criteria) this;
        }

        public Criteria andFeedTypeBetween(String value1, String value2) {
            addCriterion("feed_type between", value1, value2, "feedType");
            return (Criteria) this;
        }

        public Criteria andFeedTypeNotBetween(String value1, String value2) {
            addCriterion("feed_type not between", value1, value2, "feedType");
            return (Criteria) this;
        }

        public Criteria andFeedDescIsNull() {
            addCriterion("feed_desc is null");
            return (Criteria) this;
        }

        public Criteria andFeedDescIsNotNull() {
            addCriterion("feed_desc is not null");
            return (Criteria) this;
        }

        public Criteria andFeedDescEqualTo(String value) {
            addCriterion("feed_desc =", value, "feedDesc");
            return (Criteria) this;
        }

        public Criteria andFeedDescNotEqualTo(String value) {
            addCriterion("feed_desc <>", value, "feedDesc");
            return (Criteria) this;
        }

        public Criteria andFeedDescGreaterThan(String value) {
            addCriterion("feed_desc >", value, "feedDesc");
            return (Criteria) this;
        }

        public Criteria andFeedDescGreaterThanOrEqualTo(String value) {
            addCriterion("feed_desc >=", value, "feedDesc");
            return (Criteria) this;
        }

        public Criteria andFeedDescLessThan(String value) {
            addCriterion("feed_desc <", value, "feedDesc");
            return (Criteria) this;
        }

        public Criteria andFeedDescLessThanOrEqualTo(String value) {
            addCriterion("feed_desc <=", value, "feedDesc");
            return (Criteria) this;
        }

        public Criteria andFeedDescLike(String value) {
            addCriterion("feed_desc like", value, "feedDesc");
            return (Criteria) this;
        }

        public Criteria andFeedDescNotLike(String value) {
            addCriterion("feed_desc not like", value, "feedDesc");
            return (Criteria) this;
        }

        public Criteria andFeedDescIn(List<String> values) {
            addCriterion("feed_desc in", values, "feedDesc");
            return (Criteria) this;
        }

        public Criteria andFeedDescNotIn(List<String> values) {
            addCriterion("feed_desc not in", values, "feedDesc");
            return (Criteria) this;
        }

        public Criteria andFeedDescBetween(String value1, String value2) {
            addCriterion("feed_desc between", value1, value2, "feedDesc");
            return (Criteria) this;
        }

        public Criteria andFeedDescNotBetween(String value1, String value2) {
            addCriterion("feed_desc not between", value1, value2, "feedDesc");
            return (Criteria) this;
        }

        public Criteria andImgUrl1IsNull() {
            addCriterion("img_url1 is null");
            return (Criteria) this;
        }

        public Criteria andImgUrl1IsNotNull() {
            addCriterion("img_url1 is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrl1EqualTo(String value) {
            addCriterion("img_url1 =", value, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl1NotEqualTo(String value) {
            addCriterion("img_url1 <>", value, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl1GreaterThan(String value) {
            addCriterion("img_url1 >", value, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl1GreaterThanOrEqualTo(String value) {
            addCriterion("img_url1 >=", value, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl1LessThan(String value) {
            addCriterion("img_url1 <", value, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl1LessThanOrEqualTo(String value) {
            addCriterion("img_url1 <=", value, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl1Like(String value) {
            addCriterion("img_url1 like", value, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl1NotLike(String value) {
            addCriterion("img_url1 not like", value, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl1In(List<String> values) {
            addCriterion("img_url1 in", values, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl1NotIn(List<String> values) {
            addCriterion("img_url1 not in", values, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl1Between(String value1, String value2) {
            addCriterion("img_url1 between", value1, value2, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl1NotBetween(String value1, String value2) {
            addCriterion("img_url1 not between", value1, value2, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl2IsNull() {
            addCriterion("img_url2 is null");
            return (Criteria) this;
        }

        public Criteria andImgUrl2IsNotNull() {
            addCriterion("img_url2 is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrl2EqualTo(String value) {
            addCriterion("img_url2 =", value, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl2NotEqualTo(String value) {
            addCriterion("img_url2 <>", value, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl2GreaterThan(String value) {
            addCriterion("img_url2 >", value, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl2GreaterThanOrEqualTo(String value) {
            addCriterion("img_url2 >=", value, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl2LessThan(String value) {
            addCriterion("img_url2 <", value, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl2LessThanOrEqualTo(String value) {
            addCriterion("img_url2 <=", value, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl2Like(String value) {
            addCriterion("img_url2 like", value, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl2NotLike(String value) {
            addCriterion("img_url2 not like", value, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl2In(List<String> values) {
            addCriterion("img_url2 in", values, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl2NotIn(List<String> values) {
            addCriterion("img_url2 not in", values, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl2Between(String value1, String value2) {
            addCriterion("img_url2 between", value1, value2, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl2NotBetween(String value1, String value2) {
            addCriterion("img_url2 not between", value1, value2, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl3IsNull() {
            addCriterion("img_url3 is null");
            return (Criteria) this;
        }

        public Criteria andImgUrl3IsNotNull() {
            addCriterion("img_url3 is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrl3EqualTo(String value) {
            addCriterion("img_url3 =", value, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl3NotEqualTo(String value) {
            addCriterion("img_url3 <>", value, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl3GreaterThan(String value) {
            addCriterion("img_url3 >", value, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl3GreaterThanOrEqualTo(String value) {
            addCriterion("img_url3 >=", value, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl3LessThan(String value) {
            addCriterion("img_url3 <", value, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl3LessThanOrEqualTo(String value) {
            addCriterion("img_url3 <=", value, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl3Like(String value) {
            addCriterion("img_url3 like", value, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl3NotLike(String value) {
            addCriterion("img_url3 not like", value, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl3In(List<String> values) {
            addCriterion("img_url3 in", values, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl3NotIn(List<String> values) {
            addCriterion("img_url3 not in", values, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl3Between(String value1, String value2) {
            addCriterion("img_url3 between", value1, value2, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl3NotBetween(String value1, String value2) {
            addCriterion("img_url3 not between", value1, value2, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl4IsNull() {
            addCriterion("img_url4 is null");
            return (Criteria) this;
        }

        public Criteria andImgUrl4IsNotNull() {
            addCriterion("img_url4 is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrl4EqualTo(String value) {
            addCriterion("img_url4 =", value, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl4NotEqualTo(String value) {
            addCriterion("img_url4 <>", value, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl4GreaterThan(String value) {
            addCriterion("img_url4 >", value, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl4GreaterThanOrEqualTo(String value) {
            addCriterion("img_url4 >=", value, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl4LessThan(String value) {
            addCriterion("img_url4 <", value, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl4LessThanOrEqualTo(String value) {
            addCriterion("img_url4 <=", value, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl4Like(String value) {
            addCriterion("img_url4 like", value, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl4NotLike(String value) {
            addCriterion("img_url4 not like", value, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl4In(List<String> values) {
            addCriterion("img_url4 in", values, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl4NotIn(List<String> values) {
            addCriterion("img_url4 not in", values, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl4Between(String value1, String value2) {
            addCriterion("img_url4 between", value1, value2, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl4NotBetween(String value1, String value2) {
            addCriterion("img_url4 not between", value1, value2, "imgUrl4");
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

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andDealTypeIsNull() {
            addCriterion("deal_type is null");
            return (Criteria) this;
        }

        public Criteria andDealTypeIsNotNull() {
            addCriterion("deal_type is not null");
            return (Criteria) this;
        }

        public Criteria andDealTypeEqualTo(String value) {
            addCriterion("deal_type =", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeNotEqualTo(String value) {
            addCriterion("deal_type <>", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeGreaterThan(String value) {
            addCriterion("deal_type >", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeGreaterThanOrEqualTo(String value) {
            addCriterion("deal_type >=", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeLessThan(String value) {
            addCriterion("deal_type <", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeLessThanOrEqualTo(String value) {
            addCriterion("deal_type <=", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeLike(String value) {
            addCriterion("deal_type like", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeNotLike(String value) {
            addCriterion("deal_type not like", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeIn(List<String> values) {
            addCriterion("deal_type in", values, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeNotIn(List<String> values) {
            addCriterion("deal_type not in", values, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeBetween(String value1, String value2) {
            addCriterion("deal_type between", value1, value2, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeNotBetween(String value1, String value2) {
            addCriterion("deal_type not between", value1, value2, "dealType");
            return (Criteria) this;
        }

        public Criteria andTicketIdIsNull() {
            addCriterion("ticket_id is null");
            return (Criteria) this;
        }

        public Criteria andTicketIdIsNotNull() {
            addCriterion("ticket_id is not null");
            return (Criteria) this;
        }

        public Criteria andTicketIdEqualTo(Long value) {
            addCriterion("ticket_id =", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdNotEqualTo(Long value) {
            addCriterion("ticket_id <>", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdGreaterThan(Long value) {
            addCriterion("ticket_id >", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ticket_id >=", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdLessThan(Long value) {
            addCriterion("ticket_id <", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdLessThanOrEqualTo(Long value) {
            addCriterion("ticket_id <=", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdIn(List<Long> values) {
            addCriterion("ticket_id in", values, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdNotIn(List<Long> values) {
            addCriterion("ticket_id not in", values, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdBetween(Long value1, Long value2) {
            addCriterion("ticket_id between", value1, value2, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdNotBetween(Long value1, Long value2) {
            addCriterion("ticket_id not between", value1, value2, "ticketId");
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