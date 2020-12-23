package vc.thinker.cabbage.user.model;

import com.sinco.mybatis.dal.core.AbstractExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import vc.thinker.cabbage.user.bo.MemberBO;

public class MemberExample extends AbstractExample<MemberBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public MemberExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("nickname is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("nickname is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("nickname =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("nickname <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("nickname >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("nickname >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("nickname <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("nickname <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("nickname like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("nickname not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("nickname in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("nickname not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("nickname between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("nickname not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(Integer value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(Integer value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(Integer value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(Integer value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(Integer value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(Integer value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<Integer> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<Integer> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(Integer value1, Integer value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(Integer value1, Integer value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andHeightIsNull() {
            addCriterion("height is null");
            return (Criteria) this;
        }

        public Criteria andHeightIsNotNull() {
            addCriterion("height is not null");
            return (Criteria) this;
        }

        public Criteria andHeightEqualTo(Integer value) {
            addCriterion("height =", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotEqualTo(Integer value) {
            addCriterion("height <>", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThan(Integer value) {
            addCriterion("height >", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("height >=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThan(Integer value) {
            addCriterion("height <", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThanOrEqualTo(Integer value) {
            addCriterion("height <=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightIn(List<Integer> values) {
            addCriterion("height in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotIn(List<Integer> values) {
            addCriterion("height not in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightBetween(Integer value1, Integer value2) {
            addCriterion("height between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotBetween(Integer value1, Integer value2) {
            addCriterion("height not between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(Integer value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(Integer value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(Integer value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(Integer value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(Integer value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<Integer> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<Integer> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(Integer value1, Integer value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andBirthdateIsNull() {
            addCriterion("birthdate is null");
            return (Criteria) this;
        }

        public Criteria andBirthdateIsNotNull() {
            addCriterion("birthdate is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdateEqualTo(Date value) {
            addCriterionForJDBCDate("birthdate =", value, "birthdate");
            return (Criteria) this;
        }

        public Criteria andBirthdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("birthdate <>", value, "birthdate");
            return (Criteria) this;
        }

        public Criteria andBirthdateGreaterThan(Date value) {
            addCriterionForJDBCDate("birthdate >", value, "birthdate");
            return (Criteria) this;
        }

        public Criteria andBirthdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthdate >=", value, "birthdate");
            return (Criteria) this;
        }

        public Criteria andBirthdateLessThan(Date value) {
            addCriterionForJDBCDate("birthdate <", value, "birthdate");
            return (Criteria) this;
        }

        public Criteria andBirthdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthdate <=", value, "birthdate");
            return (Criteria) this;
        }

        public Criteria andBirthdateIn(List<Date> values) {
            addCriterionForJDBCDate("birthdate in", values, "birthdate");
            return (Criteria) this;
        }

        public Criteria andBirthdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("birthdate not in", values, "birthdate");
            return (Criteria) this;
        }

        public Criteria andBirthdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthdate between", value1, value2, "birthdate");
            return (Criteria) this;
        }

        public Criteria andBirthdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthdate not between", value1, value2, "birthdate");
            return (Criteria) this;
        }

        public Criteria andHeadImgPathIsNull() {
            addCriterion("head_img_path is null");
            return (Criteria) this;
        }

        public Criteria andHeadImgPathIsNotNull() {
            addCriterion("head_img_path is not null");
            return (Criteria) this;
        }

        public Criteria andHeadImgPathEqualTo(String value) {
            addCriterion("head_img_path =", value, "headImgPath");
            return (Criteria) this;
        }

        public Criteria andHeadImgPathNotEqualTo(String value) {
            addCriterion("head_img_path <>", value, "headImgPath");
            return (Criteria) this;
        }

        public Criteria andHeadImgPathGreaterThan(String value) {
            addCriterion("head_img_path >", value, "headImgPath");
            return (Criteria) this;
        }

        public Criteria andHeadImgPathGreaterThanOrEqualTo(String value) {
            addCriterion("head_img_path >=", value, "headImgPath");
            return (Criteria) this;
        }

        public Criteria andHeadImgPathLessThan(String value) {
            addCriterion("head_img_path <", value, "headImgPath");
            return (Criteria) this;
        }

        public Criteria andHeadImgPathLessThanOrEqualTo(String value) {
            addCriterion("head_img_path <=", value, "headImgPath");
            return (Criteria) this;
        }

        public Criteria andHeadImgPathLike(String value) {
            addCriterion("head_img_path like", value, "headImgPath");
            return (Criteria) this;
        }

        public Criteria andHeadImgPathNotLike(String value) {
            addCriterion("head_img_path not like", value, "headImgPath");
            return (Criteria) this;
        }

        public Criteria andHeadImgPathIn(List<String> values) {
            addCriterion("head_img_path in", values, "headImgPath");
            return (Criteria) this;
        }

        public Criteria andHeadImgPathNotIn(List<String> values) {
            addCriterion("head_img_path not in", values, "headImgPath");
            return (Criteria) this;
        }

        public Criteria andHeadImgPathBetween(String value1, String value2) {
            addCriterion("head_img_path between", value1, value2, "headImgPath");
            return (Criteria) this;
        }

        public Criteria andHeadImgPathNotBetween(String value1, String value2) {
            addCriterion("head_img_path not between", value1, value2, "headImgPath");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
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

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
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

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
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

        public Criteria andIdCardIsNull() {
            addCriterion("id_card is null");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNotNull() {
            addCriterion("id_card is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardEqualTo(String value) {
            addCriterion("id_card =", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotEqualTo(String value) {
            addCriterion("id_card <>", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThan(String value) {
            addCriterion("id_card >", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("id_card >=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThan(String value) {
            addCriterion("id_card <", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThanOrEqualTo(String value) {
            addCriterion("id_card <=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLike(String value) {
            addCriterion("id_card like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotLike(String value) {
            addCriterion("id_card not like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardIn(List<String> values) {
            addCriterion("id_card in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotIn(List<String> values) {
            addCriterion("id_card not in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardBetween(String value1, String value2) {
            addCriterion("id_card between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotBetween(String value1, String value2) {
            addCriterion("id_card not between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andPayOpenIdIsNull() {
            addCriterion("pay_open_id is null");
            return (Criteria) this;
        }

        public Criteria andPayOpenIdIsNotNull() {
            addCriterion("pay_open_id is not null");
            return (Criteria) this;
        }

        public Criteria andPayOpenIdEqualTo(String value) {
            addCriterion("pay_open_id =", value, "payOpenId");
            return (Criteria) this;
        }

        public Criteria andPayOpenIdNotEqualTo(String value) {
            addCriterion("pay_open_id <>", value, "payOpenId");
            return (Criteria) this;
        }

        public Criteria andPayOpenIdGreaterThan(String value) {
            addCriterion("pay_open_id >", value, "payOpenId");
            return (Criteria) this;
        }

        public Criteria andPayOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("pay_open_id >=", value, "payOpenId");
            return (Criteria) this;
        }

        public Criteria andPayOpenIdLessThan(String value) {
            addCriterion("pay_open_id <", value, "payOpenId");
            return (Criteria) this;
        }

        public Criteria andPayOpenIdLessThanOrEqualTo(String value) {
            addCriterion("pay_open_id <=", value, "payOpenId");
            return (Criteria) this;
        }

        public Criteria andPayOpenIdLike(String value) {
            addCriterion("pay_open_id like", value, "payOpenId");
            return (Criteria) this;
        }

        public Criteria andPayOpenIdNotLike(String value) {
            addCriterion("pay_open_id not like", value, "payOpenId");
            return (Criteria) this;
        }

        public Criteria andPayOpenIdIn(List<String> values) {
            addCriterion("pay_open_id in", values, "payOpenId");
            return (Criteria) this;
        }

        public Criteria andPayOpenIdNotIn(List<String> values) {
            addCriterion("pay_open_id not in", values, "payOpenId");
            return (Criteria) this;
        }

        public Criteria andPayOpenIdBetween(String value1, String value2) {
            addCriterion("pay_open_id between", value1, value2, "payOpenId");
            return (Criteria) this;
        }

        public Criteria andPayOpenIdNotBetween(String value1, String value2) {
            addCriterion("pay_open_id not between", value1, value2, "payOpenId");
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

        public Criteria andInviteCodeIsNull() {
            addCriterion("invite_code is null");
            return (Criteria) this;
        }

        public Criteria andInviteCodeIsNotNull() {
            addCriterion("invite_code is not null");
            return (Criteria) this;
        }

        public Criteria andInviteCodeEqualTo(String value) {
            addCriterion("invite_code =", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeNotEqualTo(String value) {
            addCriterion("invite_code <>", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeGreaterThan(String value) {
            addCriterion("invite_code >", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeGreaterThanOrEqualTo(String value) {
            addCriterion("invite_code >=", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeLessThan(String value) {
            addCriterion("invite_code <", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeLessThanOrEqualTo(String value) {
            addCriterion("invite_code <=", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeLike(String value) {
            addCriterion("invite_code like", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeNotLike(String value) {
            addCriterion("invite_code not like", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeIn(List<String> values) {
            addCriterion("invite_code in", values, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeNotIn(List<String> values) {
            addCriterion("invite_code not in", values, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeBetween(String value1, String value2) {
            addCriterion("invite_code between", value1, value2, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeNotBetween(String value1, String value2) {
            addCriterion("invite_code not between", value1, value2, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andAuthStatusIsNull() {
            addCriterion("auth_status is null");
            return (Criteria) this;
        }

        public Criteria andAuthStatusIsNotNull() {
            addCriterion("auth_status is not null");
            return (Criteria) this;
        }

        public Criteria andAuthStatusEqualTo(String value) {
            addCriterion("auth_status =", value, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusNotEqualTo(String value) {
            addCriterion("auth_status <>", value, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusGreaterThan(String value) {
            addCriterion("auth_status >", value, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusGreaterThanOrEqualTo(String value) {
            addCriterion("auth_status >=", value, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusLessThan(String value) {
            addCriterion("auth_status <", value, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusLessThanOrEqualTo(String value) {
            addCriterion("auth_status <=", value, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusLike(String value) {
            addCriterion("auth_status like", value, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusNotLike(String value) {
            addCriterion("auth_status not like", value, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusIn(List<String> values) {
            addCriterion("auth_status in", values, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusNotIn(List<String> values) {
            addCriterion("auth_status not in", values, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusBetween(String value1, String value2) {
            addCriterion("auth_status between", value1, value2, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusNotBetween(String value1, String value2) {
            addCriterion("auth_status not between", value1, value2, "authStatus");
            return (Criteria) this;
        }

        public Criteria andRideDistanceIsNull() {
            addCriterion("ride_distance is null");
            return (Criteria) this;
        }

        public Criteria andRideDistanceIsNotNull() {
            addCriterion("ride_distance is not null");
            return (Criteria) this;
        }

        public Criteria andRideDistanceEqualTo(Double value) {
            addCriterion("ride_distance =", value, "rideDistance");
            return (Criteria) this;
        }

        public Criteria andRideDistanceNotEqualTo(Double value) {
            addCriterion("ride_distance <>", value, "rideDistance");
            return (Criteria) this;
        }

        public Criteria andRideDistanceGreaterThan(Double value) {
            addCriterion("ride_distance >", value, "rideDistance");
            return (Criteria) this;
        }

        public Criteria andRideDistanceGreaterThanOrEqualTo(Double value) {
            addCriterion("ride_distance >=", value, "rideDistance");
            return (Criteria) this;
        }

        public Criteria andRideDistanceLessThan(Double value) {
            addCriterion("ride_distance <", value, "rideDistance");
            return (Criteria) this;
        }

        public Criteria andRideDistanceLessThanOrEqualTo(Double value) {
            addCriterion("ride_distance <=", value, "rideDistance");
            return (Criteria) this;
        }

        public Criteria andRideDistanceIn(List<Double> values) {
            addCriterion("ride_distance in", values, "rideDistance");
            return (Criteria) this;
        }

        public Criteria andRideDistanceNotIn(List<Double> values) {
            addCriterion("ride_distance not in", values, "rideDistance");
            return (Criteria) this;
        }

        public Criteria andRideDistanceBetween(Double value1, Double value2) {
            addCriterion("ride_distance between", value1, value2, "rideDistance");
            return (Criteria) this;
        }

        public Criteria andRideDistanceNotBetween(Double value1, Double value2) {
            addCriterion("ride_distance not between", value1, value2, "rideDistance");
            return (Criteria) this;
        }

        public Criteria andRideTimeIsNull() {
            addCriterion("ride_time is null");
            return (Criteria) this;
        }

        public Criteria andRideTimeIsNotNull() {
            addCriterion("ride_time is not null");
            return (Criteria) this;
        }

        public Criteria andRideTimeEqualTo(Long value) {
            addCriterion("ride_time =", value, "rideTime");
            return (Criteria) this;
        }

        public Criteria andRideTimeNotEqualTo(Long value) {
            addCriterion("ride_time <>", value, "rideTime");
            return (Criteria) this;
        }

        public Criteria andRideTimeGreaterThan(Long value) {
            addCriterion("ride_time >", value, "rideTime");
            return (Criteria) this;
        }

        public Criteria andRideTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("ride_time >=", value, "rideTime");
            return (Criteria) this;
        }

        public Criteria andRideTimeLessThan(Long value) {
            addCriterion("ride_time <", value, "rideTime");
            return (Criteria) this;
        }

        public Criteria andRideTimeLessThanOrEqualTo(Long value) {
            addCriterion("ride_time <=", value, "rideTime");
            return (Criteria) this;
        }

        public Criteria andRideTimeIn(List<Long> values) {
            addCriterion("ride_time in", values, "rideTime");
            return (Criteria) this;
        }

        public Criteria andRideTimeNotIn(List<Long> values) {
            addCriterion("ride_time not in", values, "rideTime");
            return (Criteria) this;
        }

        public Criteria andRideTimeBetween(Long value1, Long value2) {
            addCriterion("ride_time between", value1, value2, "rideTime");
            return (Criteria) this;
        }

        public Criteria andRideTimeNotBetween(Long value1, Long value2) {
            addCriterion("ride_time not between", value1, value2, "rideTime");
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

        public Criteria andAuthStepIsNull() {
            addCriterion("auth_step is null");
            return (Criteria) this;
        }

        public Criteria andAuthStepIsNotNull() {
            addCriterion("auth_step is not null");
            return (Criteria) this;
        }

        public Criteria andAuthStepEqualTo(Integer value) {
            addCriterion("auth_step =", value, "authStep");
            return (Criteria) this;
        }

        public Criteria andAuthStepNotEqualTo(Integer value) {
            addCriterion("auth_step <>", value, "authStep");
            return (Criteria) this;
        }

        public Criteria andAuthStepGreaterThan(Integer value) {
            addCriterion("auth_step >", value, "authStep");
            return (Criteria) this;
        }

        public Criteria andAuthStepGreaterThanOrEqualTo(Integer value) {
            addCriterion("auth_step >=", value, "authStep");
            return (Criteria) this;
        }

        public Criteria andAuthStepLessThan(Integer value) {
            addCriterion("auth_step <", value, "authStep");
            return (Criteria) this;
        }

        public Criteria andAuthStepLessThanOrEqualTo(Integer value) {
            addCriterion("auth_step <=", value, "authStep");
            return (Criteria) this;
        }

        public Criteria andAuthStepIn(List<Integer> values) {
            addCriterion("auth_step in", values, "authStep");
            return (Criteria) this;
        }

        public Criteria andAuthStepNotIn(List<Integer> values) {
            addCriterion("auth_step not in", values, "authStep");
            return (Criteria) this;
        }

        public Criteria andAuthStepBetween(Integer value1, Integer value2) {
            addCriterion("auth_step between", value1, value2, "authStep");
            return (Criteria) this;
        }

        public Criteria andAuthStepNotBetween(Integer value1, Integer value2) {
            addCriterion("auth_step not between", value1, value2, "authStep");
            return (Criteria) this;
        }

        public Criteria andInvitedInviteCodeIsNull() {
            addCriterion("invited_invite_code is null");
            return (Criteria) this;
        }

        public Criteria andInvitedInviteCodeIsNotNull() {
            addCriterion("invited_invite_code is not null");
            return (Criteria) this;
        }

        public Criteria andInvitedInviteCodeEqualTo(String value) {
            addCriterion("invited_invite_code =", value, "invitedInviteCode");
            return (Criteria) this;
        }

        public Criteria andInvitedInviteCodeNotEqualTo(String value) {
            addCriterion("invited_invite_code <>", value, "invitedInviteCode");
            return (Criteria) this;
        }

        public Criteria andInvitedInviteCodeGreaterThan(String value) {
            addCriterion("invited_invite_code >", value, "invitedInviteCode");
            return (Criteria) this;
        }

        public Criteria andInvitedInviteCodeGreaterThanOrEqualTo(String value) {
            addCriterion("invited_invite_code >=", value, "invitedInviteCode");
            return (Criteria) this;
        }

        public Criteria andInvitedInviteCodeLessThan(String value) {
            addCriterion("invited_invite_code <", value, "invitedInviteCode");
            return (Criteria) this;
        }

        public Criteria andInvitedInviteCodeLessThanOrEqualTo(String value) {
            addCriterion("invited_invite_code <=", value, "invitedInviteCode");
            return (Criteria) this;
        }

        public Criteria andInvitedInviteCodeLike(String value) {
            addCriterion("invited_invite_code like", value, "invitedInviteCode");
            return (Criteria) this;
        }

        public Criteria andInvitedInviteCodeNotLike(String value) {
            addCriterion("invited_invite_code not like", value, "invitedInviteCode");
            return (Criteria) this;
        }

        public Criteria andInvitedInviteCodeIn(List<String> values) {
            addCriterion("invited_invite_code in", values, "invitedInviteCode");
            return (Criteria) this;
        }

        public Criteria andInvitedInviteCodeNotIn(List<String> values) {
            addCriterion("invited_invite_code not in", values, "invitedInviteCode");
            return (Criteria) this;
        }

        public Criteria andInvitedInviteCodeBetween(String value1, String value2) {
            addCriterion("invited_invite_code between", value1, value2, "invitedInviteCode");
            return (Criteria) this;
        }

        public Criteria andInvitedInviteCodeNotBetween(String value1, String value2) {
            addCriterion("invited_invite_code not between", value1, value2, "invitedInviteCode");
            return (Criteria) this;
        }

        public Criteria andJobNumberIsNull() {
            addCriterion("job_number is null");
            return (Criteria) this;
        }

        public Criteria andJobNumberIsNotNull() {
            addCriterion("job_number is not null");
            return (Criteria) this;
        }

        public Criteria andJobNumberEqualTo(String value) {
            addCriterion("job_number =", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberNotEqualTo(String value) {
            addCriterion("job_number <>", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberGreaterThan(String value) {
            addCriterion("job_number >", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberGreaterThanOrEqualTo(String value) {
            addCriterion("job_number >=", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberLessThan(String value) {
            addCriterion("job_number <", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberLessThanOrEqualTo(String value) {
            addCriterion("job_number <=", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberLike(String value) {
            addCriterion("job_number like", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberNotLike(String value) {
            addCriterion("job_number not like", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberIn(List<String> values) {
            addCriterion("job_number in", values, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberNotIn(List<String> values) {
            addCriterion("job_number not in", values, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberBetween(String value1, String value2) {
            addCriterion("job_number between", value1, value2, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberNotBetween(String value1, String value2) {
            addCriterion("job_number not between", value1, value2, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andRegistChannelIsNull() {
            addCriterion("regist_channel is null");
            return (Criteria) this;
        }

        public Criteria andRegistChannelIsNotNull() {
            addCriterion("regist_channel is not null");
            return (Criteria) this;
        }

        public Criteria andRegistChannelEqualTo(String value) {
            addCriterion("regist_channel =", value, "registChannel");
            return (Criteria) this;
        }

        public Criteria andRegistChannelNotEqualTo(String value) {
            addCriterion("regist_channel <>", value, "registChannel");
            return (Criteria) this;
        }

        public Criteria andRegistChannelGreaterThan(String value) {
            addCriterion("regist_channel >", value, "registChannel");
            return (Criteria) this;
        }

        public Criteria andRegistChannelGreaterThanOrEqualTo(String value) {
            addCriterion("regist_channel >=", value, "registChannel");
            return (Criteria) this;
        }

        public Criteria andRegistChannelLessThan(String value) {
            addCriterion("regist_channel <", value, "registChannel");
            return (Criteria) this;
        }

        public Criteria andRegistChannelLessThanOrEqualTo(String value) {
            addCriterion("regist_channel <=", value, "registChannel");
            return (Criteria) this;
        }

        public Criteria andRegistChannelLike(String value) {
            addCriterion("regist_channel like", value, "registChannel");
            return (Criteria) this;
        }

        public Criteria andRegistChannelNotLike(String value) {
            addCriterion("regist_channel not like", value, "registChannel");
            return (Criteria) this;
        }

        public Criteria andRegistChannelIn(List<String> values) {
            addCriterion("regist_channel in", values, "registChannel");
            return (Criteria) this;
        }

        public Criteria andRegistChannelNotIn(List<String> values) {
            addCriterion("regist_channel not in", values, "registChannel");
            return (Criteria) this;
        }

        public Criteria andRegistChannelBetween(String value1, String value2) {
            addCriterion("regist_channel between", value1, value2, "registChannel");
            return (Criteria) this;
        }

        public Criteria andRegistChannelNotBetween(String value1, String value2) {
            addCriterion("regist_channel not between", value1, value2, "registChannel");
            return (Criteria) this;
        }

        public Criteria andVipExpiresInIsNull() {
            addCriterion("vip_expires_in is null");
            return (Criteria) this;
        }

        public Criteria andVipExpiresInIsNotNull() {
            addCriterion("vip_expires_in is not null");
            return (Criteria) this;
        }

        public Criteria andVipExpiresInEqualTo(Date value) {
            addCriterion("vip_expires_in =", value, "vipExpiresIn");
            return (Criteria) this;
        }

        public Criteria andVipExpiresInNotEqualTo(Date value) {
            addCriterion("vip_expires_in <>", value, "vipExpiresIn");
            return (Criteria) this;
        }

        public Criteria andVipExpiresInGreaterThan(Date value) {
            addCriterion("vip_expires_in >", value, "vipExpiresIn");
            return (Criteria) this;
        }

        public Criteria andVipExpiresInGreaterThanOrEqualTo(Date value) {
            addCriterion("vip_expires_in >=", value, "vipExpiresIn");
            return (Criteria) this;
        }

        public Criteria andVipExpiresInLessThan(Date value) {
            addCriterion("vip_expires_in <", value, "vipExpiresIn");
            return (Criteria) this;
        }

        public Criteria andVipExpiresInLessThanOrEqualTo(Date value) {
            addCriterion("vip_expires_in <=", value, "vipExpiresIn");
            return (Criteria) this;
        }

        public Criteria andVipExpiresInIn(List<Date> values) {
            addCriterion("vip_expires_in in", values, "vipExpiresIn");
            return (Criteria) this;
        }

        public Criteria andVipExpiresInNotIn(List<Date> values) {
            addCriterion("vip_expires_in not in", values, "vipExpiresIn");
            return (Criteria) this;
        }

        public Criteria andVipExpiresInBetween(Date value1, Date value2) {
            addCriterion("vip_expires_in between", value1, value2, "vipExpiresIn");
            return (Criteria) this;
        }

        public Criteria andVipExpiresInNotBetween(Date value1, Date value2) {
            addCriterion("vip_expires_in not between", value1, value2, "vipExpiresIn");
            return (Criteria) this;
        }

        public Criteria andVipDiscountIsNull() {
            addCriterion("vip_discount is null");
            return (Criteria) this;
        }

        public Criteria andVipDiscountIsNotNull() {
            addCriterion("vip_discount is not null");
            return (Criteria) this;
        }

        public Criteria andVipDiscountEqualTo(Double value) {
            addCriterion("vip_discount =", value, "vipDiscount");
            return (Criteria) this;
        }

        public Criteria andVipDiscountNotEqualTo(Double value) {
            addCriterion("vip_discount <>", value, "vipDiscount");
            return (Criteria) this;
        }

        public Criteria andVipDiscountGreaterThan(Double value) {
            addCriterion("vip_discount >", value, "vipDiscount");
            return (Criteria) this;
        }

        public Criteria andVipDiscountGreaterThanOrEqualTo(Double value) {
            addCriterion("vip_discount >=", value, "vipDiscount");
            return (Criteria) this;
        }

        public Criteria andVipDiscountLessThan(Double value) {
            addCriterion("vip_discount <", value, "vipDiscount");
            return (Criteria) this;
        }

        public Criteria andVipDiscountLessThanOrEqualTo(Double value) {
            addCriterion("vip_discount <=", value, "vipDiscount");
            return (Criteria) this;
        }

        public Criteria andVipDiscountIn(List<Double> values) {
            addCriterion("vip_discount in", values, "vipDiscount");
            return (Criteria) this;
        }

        public Criteria andVipDiscountNotIn(List<Double> values) {
            addCriterion("vip_discount not in", values, "vipDiscount");
            return (Criteria) this;
        }

        public Criteria andVipDiscountBetween(Double value1, Double value2) {
            addCriterion("vip_discount between", value1, value2, "vipDiscount");
            return (Criteria) this;
        }

        public Criteria andVipDiscountNotBetween(Double value1, Double value2) {
            addCriterion("vip_discount not between", value1, value2, "vipDiscount");
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

        public Criteria andRegisteredIpIsNull() {
            addCriterion("registered_ip is null");
            return (Criteria) this;
        }

        public Criteria andRegisteredIpIsNotNull() {
            addCriterion("registered_ip is not null");
            return (Criteria) this;
        }

        public Criteria andRegisteredIpEqualTo(String value) {
            addCriterion("registered_ip =", value, "registeredIp");
            return (Criteria) this;
        }

        public Criteria andRegisteredIpNotEqualTo(String value) {
            addCriterion("registered_ip <>", value, "registeredIp");
            return (Criteria) this;
        }

        public Criteria andRegisteredIpGreaterThan(String value) {
            addCriterion("registered_ip >", value, "registeredIp");
            return (Criteria) this;
        }

        public Criteria andRegisteredIpGreaterThanOrEqualTo(String value) {
            addCriterion("registered_ip >=", value, "registeredIp");
            return (Criteria) this;
        }

        public Criteria andRegisteredIpLessThan(String value) {
            addCriterion("registered_ip <", value, "registeredIp");
            return (Criteria) this;
        }

        public Criteria andRegisteredIpLessThanOrEqualTo(String value) {
            addCriterion("registered_ip <=", value, "registeredIp");
            return (Criteria) this;
        }

        public Criteria andRegisteredIpLike(String value) {
            addCriterion("registered_ip like", value, "registeredIp");
            return (Criteria) this;
        }

        public Criteria andRegisteredIpNotLike(String value) {
            addCriterion("registered_ip not like", value, "registeredIp");
            return (Criteria) this;
        }

        public Criteria andRegisteredIpIn(List<String> values) {
            addCriterion("registered_ip in", values, "registeredIp");
            return (Criteria) this;
        }

        public Criteria andRegisteredIpNotIn(List<String> values) {
            addCriterion("registered_ip not in", values, "registeredIp");
            return (Criteria) this;
        }

        public Criteria andRegisteredIpBetween(String value1, String value2) {
            addCriterion("registered_ip between", value1, value2, "registeredIp");
            return (Criteria) this;
        }

        public Criteria andRegisteredIpNotBetween(String value1, String value2) {
            addCriterion("registered_ip not between", value1, value2, "registeredIp");
            return (Criteria) this;
        }

        public Criteria andAuthApplyRemarkIsNull() {
            addCriterion("auth_apply_remark is null");
            return (Criteria) this;
        }

        public Criteria andAuthApplyRemarkIsNotNull() {
            addCriterion("auth_apply_remark is not null");
            return (Criteria) this;
        }

        public Criteria andAuthApplyRemarkEqualTo(String value) {
            addCriterion("auth_apply_remark =", value, "authApplyRemark");
            return (Criteria) this;
        }

        public Criteria andAuthApplyRemarkNotEqualTo(String value) {
            addCriterion("auth_apply_remark <>", value, "authApplyRemark");
            return (Criteria) this;
        }

        public Criteria andAuthApplyRemarkGreaterThan(String value) {
            addCriterion("auth_apply_remark >", value, "authApplyRemark");
            return (Criteria) this;
        }

        public Criteria andAuthApplyRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("auth_apply_remark >=", value, "authApplyRemark");
            return (Criteria) this;
        }

        public Criteria andAuthApplyRemarkLessThan(String value) {
            addCriterion("auth_apply_remark <", value, "authApplyRemark");
            return (Criteria) this;
        }

        public Criteria andAuthApplyRemarkLessThanOrEqualTo(String value) {
            addCriterion("auth_apply_remark <=", value, "authApplyRemark");
            return (Criteria) this;
        }

        public Criteria andAuthApplyRemarkLike(String value) {
            addCriterion("auth_apply_remark like", value, "authApplyRemark");
            return (Criteria) this;
        }

        public Criteria andAuthApplyRemarkNotLike(String value) {
            addCriterion("auth_apply_remark not like", value, "authApplyRemark");
            return (Criteria) this;
        }

        public Criteria andAuthApplyRemarkIn(List<String> values) {
            addCriterion("auth_apply_remark in", values, "authApplyRemark");
            return (Criteria) this;
        }

        public Criteria andAuthApplyRemarkNotIn(List<String> values) {
            addCriterion("auth_apply_remark not in", values, "authApplyRemark");
            return (Criteria) this;
        }

        public Criteria andAuthApplyRemarkBetween(String value1, String value2) {
            addCriterion("auth_apply_remark between", value1, value2, "authApplyRemark");
            return (Criteria) this;
        }

        public Criteria andAuthApplyRemarkNotBetween(String value1, String value2) {
            addCriterion("auth_apply_remark not between", value1, value2, "authApplyRemark");
            return (Criteria) this;
        }

        public Criteria andCredentialsImagesIsNull() {
            addCriterion("credentials_images is null");
            return (Criteria) this;
        }

        public Criteria andCredentialsImagesIsNotNull() {
            addCriterion("credentials_images is not null");
            return (Criteria) this;
        }

        public Criteria andCredentialsImagesEqualTo(String value) {
            addCriterion("credentials_images =", value, "credentialsImages");
            return (Criteria) this;
        }

        public Criteria andCredentialsImagesNotEqualTo(String value) {
            addCriterion("credentials_images <>", value, "credentialsImages");
            return (Criteria) this;
        }

        public Criteria andCredentialsImagesGreaterThan(String value) {
            addCriterion("credentials_images >", value, "credentialsImages");
            return (Criteria) this;
        }

        public Criteria andCredentialsImagesGreaterThanOrEqualTo(String value) {
            addCriterion("credentials_images >=", value, "credentialsImages");
            return (Criteria) this;
        }

        public Criteria andCredentialsImagesLessThan(String value) {
            addCriterion("credentials_images <", value, "credentialsImages");
            return (Criteria) this;
        }

        public Criteria andCredentialsImagesLessThanOrEqualTo(String value) {
            addCriterion("credentials_images <=", value, "credentialsImages");
            return (Criteria) this;
        }

        public Criteria andCredentialsImagesLike(String value) {
            addCriterion("credentials_images like", value, "credentialsImages");
            return (Criteria) this;
        }

        public Criteria andCredentialsImagesNotLike(String value) {
            addCriterion("credentials_images not like", value, "credentialsImages");
            return (Criteria) this;
        }

        public Criteria andCredentialsImagesIn(List<String> values) {
            addCriterion("credentials_images in", values, "credentialsImages");
            return (Criteria) this;
        }

        public Criteria andCredentialsImagesNotIn(List<String> values) {
            addCriterion("credentials_images not in", values, "credentialsImages");
            return (Criteria) this;
        }

        public Criteria andCredentialsImagesBetween(String value1, String value2) {
            addCriterion("credentials_images between", value1, value2, "credentialsImages");
            return (Criteria) this;
        }

        public Criteria andCredentialsImagesNotBetween(String value1, String value2) {
            addCriterion("credentials_images not between", value1, value2, "credentialsImages");
            return (Criteria) this;
        }

        public Criteria andSchoolNameIsNull() {
            addCriterion("school_name is null");
            return (Criteria) this;
        }

        public Criteria andSchoolNameIsNotNull() {
            addCriterion("school_name is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolNameEqualTo(String value) {
            addCriterion("school_name =", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameNotEqualTo(String value) {
            addCriterion("school_name <>", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameGreaterThan(String value) {
            addCriterion("school_name >", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameGreaterThanOrEqualTo(String value) {
            addCriterion("school_name >=", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameLessThan(String value) {
            addCriterion("school_name <", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameLessThanOrEqualTo(String value) {
            addCriterion("school_name <=", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameLike(String value) {
            addCriterion("school_name like", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameNotLike(String value) {
            addCriterion("school_name not like", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameIn(List<String> values) {
            addCriterion("school_name in", values, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameNotIn(List<String> values) {
            addCriterion("school_name not in", values, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameBetween(String value1, String value2) {
            addCriterion("school_name between", value1, value2, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameNotBetween(String value1, String value2) {
            addCriterion("school_name not between", value1, value2, "schoolName");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNull() {
            addCriterion("student_id is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("student_id is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(String value) {
            addCriterion("student_id =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(String value) {
            addCriterion("student_id <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(String value) {
            addCriterion("student_id >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(String value) {
            addCriterion("student_id >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(String value) {
            addCriterion("student_id <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(String value) {
            addCriterion("student_id <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLike(String value) {
            addCriterion("student_id like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotLike(String value) {
            addCriterion("student_id not like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<String> values) {
            addCriterion("student_id in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<String> values) {
            addCriterion("student_id not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(String value1, String value2) {
            addCriterion("student_id between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(String value1, String value2) {
            addCriterion("student_id not between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andAuthApplyStatusIsNull() {
            addCriterion("auth_apply_status is null");
            return (Criteria) this;
        }

        public Criteria andAuthApplyStatusIsNotNull() {
            addCriterion("auth_apply_status is not null");
            return (Criteria) this;
        }

        public Criteria andAuthApplyStatusEqualTo(Integer value) {
            addCriterion("auth_apply_status =", value, "authApplyStatus");
            return (Criteria) this;
        }

        public Criteria andAuthApplyStatusNotEqualTo(Integer value) {
            addCriterion("auth_apply_status <>", value, "authApplyStatus");
            return (Criteria) this;
        }

        public Criteria andAuthApplyStatusGreaterThan(Integer value) {
            addCriterion("auth_apply_status >", value, "authApplyStatus");
            return (Criteria) this;
        }

        public Criteria andAuthApplyStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("auth_apply_status >=", value, "authApplyStatus");
            return (Criteria) this;
        }

        public Criteria andAuthApplyStatusLessThan(Integer value) {
            addCriterion("auth_apply_status <", value, "authApplyStatus");
            return (Criteria) this;
        }

        public Criteria andAuthApplyStatusLessThanOrEqualTo(Integer value) {
            addCriterion("auth_apply_status <=", value, "authApplyStatus");
            return (Criteria) this;
        }

        public Criteria andAuthApplyStatusIn(List<Integer> values) {
            addCriterion("auth_apply_status in", values, "authApplyStatus");
            return (Criteria) this;
        }

        public Criteria andAuthApplyStatusNotIn(List<Integer> values) {
            addCriterion("auth_apply_status not in", values, "authApplyStatus");
            return (Criteria) this;
        }

        public Criteria andAuthApplyStatusBetween(Integer value1, Integer value2) {
            addCriterion("auth_apply_status between", value1, value2, "authApplyStatus");
            return (Criteria) this;
        }

        public Criteria andAuthApplyStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("auth_apply_status not between", value1, value2, "authApplyStatus");
            return (Criteria) this;
        }

        public Criteria andMotorPowerIsNull() {
            addCriterion("motor_power is null");
            return (Criteria) this;
        }

        public Criteria andMotorPowerIsNotNull() {
            addCriterion("motor_power is not null");
            return (Criteria) this;
        }

        public Criteria andMotorPowerEqualTo(Integer value) {
            addCriterion("motor_power =", value, "motorPower");
            return (Criteria) this;
        }

        public Criteria andMotorPowerNotEqualTo(Integer value) {
            addCriterion("motor_power <>", value, "motorPower");
            return (Criteria) this;
        }

        public Criteria andMotorPowerGreaterThan(Integer value) {
            addCriterion("motor_power >", value, "motorPower");
            return (Criteria) this;
        }

        public Criteria andMotorPowerGreaterThanOrEqualTo(Integer value) {
            addCriterion("motor_power >=", value, "motorPower");
            return (Criteria) this;
        }

        public Criteria andMotorPowerLessThan(Integer value) {
            addCriterion("motor_power <", value, "motorPower");
            return (Criteria) this;
        }

        public Criteria andMotorPowerLessThanOrEqualTo(Integer value) {
            addCriterion("motor_power <=", value, "motorPower");
            return (Criteria) this;
        }

        public Criteria andMotorPowerIn(List<Integer> values) {
            addCriterion("motor_power in", values, "motorPower");
            return (Criteria) this;
        }

        public Criteria andMotorPowerNotIn(List<Integer> values) {
            addCriterion("motor_power not in", values, "motorPower");
            return (Criteria) this;
        }

        public Criteria andMotorPowerBetween(Integer value1, Integer value2) {
            addCriterion("motor_power between", value1, value2, "motorPower");
            return (Criteria) this;
        }

        public Criteria andMotorPowerNotBetween(Integer value1, Integer value2) {
            addCriterion("motor_power not between", value1, value2, "motorPower");
            return (Criteria) this;
        }

        public Criteria andCountryIsNull() {
            addCriterion("country is null");
            return (Criteria) this;
        }

        public Criteria andCountryIsNotNull() {
            addCriterion("country is not null");
            return (Criteria) this;
        }

        public Criteria andCountryEqualTo(String value) {
            addCriterion("country =", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotEqualTo(String value) {
            addCriterion("country <>", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThan(String value) {
            addCriterion("country >", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThanOrEqualTo(String value) {
            addCriterion("country >=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThan(String value) {
            addCriterion("country <", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThanOrEqualTo(String value) {
            addCriterion("country <=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLike(String value) {
            addCriterion("country like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotLike(String value) {
            addCriterion("country not like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryIn(List<String> values) {
            addCriterion("country in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotIn(List<String> values) {
            addCriterion("country not in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryBetween(String value1, String value2) {
            addCriterion("country between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotBetween(String value1, String value2) {
            addCriterion("country not between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andStripeCustomerIdIsNull() {
            addCriterion("stripe_customer_id is null");
            return (Criteria) this;
        }

        public Criteria andStripeCustomerIdIsNotNull() {
            addCriterion("stripe_customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andStripeCustomerIdEqualTo(String value) {
            addCriterion("stripe_customer_id =", value, "stripeCustomerId");
            return (Criteria) this;
        }

        public Criteria andStripeCustomerIdNotEqualTo(String value) {
            addCriterion("stripe_customer_id <>", value, "stripeCustomerId");
            return (Criteria) this;
        }

        public Criteria andStripeCustomerIdGreaterThan(String value) {
            addCriterion("stripe_customer_id >", value, "stripeCustomerId");
            return (Criteria) this;
        }

        public Criteria andStripeCustomerIdGreaterThanOrEqualTo(String value) {
            addCriterion("stripe_customer_id >=", value, "stripeCustomerId");
            return (Criteria) this;
        }

        public Criteria andStripeCustomerIdLessThan(String value) {
            addCriterion("stripe_customer_id <", value, "stripeCustomerId");
            return (Criteria) this;
        }

        public Criteria andStripeCustomerIdLessThanOrEqualTo(String value) {
            addCriterion("stripe_customer_id <=", value, "stripeCustomerId");
            return (Criteria) this;
        }

        public Criteria andStripeCustomerIdLike(String value) {
            addCriterion("stripe_customer_id like", value, "stripeCustomerId");
            return (Criteria) this;
        }

        public Criteria andStripeCustomerIdNotLike(String value) {
            addCriterion("stripe_customer_id not like", value, "stripeCustomerId");
            return (Criteria) this;
        }

        public Criteria andStripeCustomerIdIn(List<String> values) {
            addCriterion("stripe_customer_id in", values, "stripeCustomerId");
            return (Criteria) this;
        }

        public Criteria andStripeCustomerIdNotIn(List<String> values) {
            addCriterion("stripe_customer_id not in", values, "stripeCustomerId");
            return (Criteria) this;
        }

        public Criteria andStripeCustomerIdBetween(String value1, String value2) {
            addCriterion("stripe_customer_id between", value1, value2, "stripeCustomerId");
            return (Criteria) this;
        }

        public Criteria andStripeCustomerIdNotBetween(String value1, String value2) {
            addCriterion("stripe_customer_id not between", value1, value2, "stripeCustomerId");
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

        public Criteria andRewardAmountIsNull() {
            addCriterion("reward_amount is null");
            return (Criteria) this;
        }

        public Criteria andRewardAmountIsNotNull() {
            addCriterion("reward_amount is not null");
            return (Criteria) this;
        }

        public Criteria andRewardAmountEqualTo(Long value) {
            addCriterion("reward_amount =", value, "rewardAmount");
            return (Criteria) this;
        }

        public Criteria andRewardAmountNotEqualTo(Long value) {
            addCriterion("reward_amount <>", value, "rewardAmount");
            return (Criteria) this;
        }

        public Criteria andRewardAmountGreaterThan(Long value) {
            addCriterion("reward_amount >", value, "rewardAmount");
            return (Criteria) this;
        }

        public Criteria andRewardAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("reward_amount >=", value, "rewardAmount");
            return (Criteria) this;
        }

        public Criteria andRewardAmountLessThan(Long value) {
            addCriterion("reward_amount <", value, "rewardAmount");
            return (Criteria) this;
        }

        public Criteria andRewardAmountLessThanOrEqualTo(Long value) {
            addCriterion("reward_amount <=", value, "rewardAmount");
            return (Criteria) this;
        }

        public Criteria andRewardAmountIn(List<Long> values) {
            addCriterion("reward_amount in", values, "rewardAmount");
            return (Criteria) this;
        }

        public Criteria andRewardAmountNotIn(List<Long> values) {
            addCriterion("reward_amount not in", values, "rewardAmount");
            return (Criteria) this;
        }

        public Criteria andRewardAmountBetween(Long value1, Long value2) {
            addCriterion("reward_amount between", value1, value2, "rewardAmount");
            return (Criteria) this;
        }

        public Criteria andRewardAmountNotBetween(Long value1, Long value2) {
            addCriterion("reward_amount not between", value1, value2, "rewardAmount");
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

        public Criteria andIsPayBasicCostIsNull() {
            addCriterion("is_pay_basic_cost is null");
            return (Criteria) this;
        }

        public Criteria andIsPayBasicCostIsNotNull() {
            addCriterion("is_pay_basic_cost is not null");
            return (Criteria) this;
        }

        public Criteria andIsPayBasicCostEqualTo(Boolean value) {
            addCriterion("is_pay_basic_cost =", value, "isPayBasicCost");
            return (Criteria) this;
        }

        public Criteria andIsPayBasicCostNotEqualTo(Boolean value) {
            addCriterion("is_pay_basic_cost <>", value, "isPayBasicCost");
            return (Criteria) this;
        }

        public Criteria andIsPayBasicCostGreaterThan(Boolean value) {
            addCriterion("is_pay_basic_cost >", value, "isPayBasicCost");
            return (Criteria) this;
        }

        public Criteria andIsPayBasicCostGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_pay_basic_cost >=", value, "isPayBasicCost");
            return (Criteria) this;
        }

        public Criteria andIsPayBasicCostLessThan(Boolean value) {
            addCriterion("is_pay_basic_cost <", value, "isPayBasicCost");
            return (Criteria) this;
        }

        public Criteria andIsPayBasicCostLessThanOrEqualTo(Boolean value) {
            addCriterion("is_pay_basic_cost <=", value, "isPayBasicCost");
            return (Criteria) this;
        }

        public Criteria andIsPayBasicCostIn(List<Boolean> values) {
            addCriterion("is_pay_basic_cost in", values, "isPayBasicCost");
            return (Criteria) this;
        }

        public Criteria andIsPayBasicCostNotIn(List<Boolean> values) {
            addCriterion("is_pay_basic_cost not in", values, "isPayBasicCost");
            return (Criteria) this;
        }

        public Criteria andIsPayBasicCostBetween(Boolean value1, Boolean value2) {
            addCriterion("is_pay_basic_cost between", value1, value2, "isPayBasicCost");
            return (Criteria) this;
        }

        public Criteria andIsPayBasicCostNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_pay_basic_cost not between", value1, value2, "isPayBasicCost");
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