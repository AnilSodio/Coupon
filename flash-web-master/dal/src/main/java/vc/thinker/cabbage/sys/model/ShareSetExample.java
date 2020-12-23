package vc.thinker.cabbage.sys.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sinco.mybatis.dal.core.AbstractExample;

import vc.thinker.cabbage.sys.bo.ShareSetBO;

public class ShareSetExample extends AbstractExample<ShareSetBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public ShareSetExample() {
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

        public Criteria andShareWayIsNull() {
            addCriterion("share_way is null");
            return (Criteria) this;
        }

        public Criteria andShareWayIsNotNull() {
            addCriterion("share_way is not null");
            return (Criteria) this;
        }

        public Criteria andShareWayEqualTo(String value) {
            addCriterion("share_way =", value, "shareWay");
            return (Criteria) this;
        }

        public Criteria andShareWayNotEqualTo(String value) {
            addCriterion("share_way <>", value, "shareWay");
            return (Criteria) this;
        }

        public Criteria andShareWayGreaterThan(String value) {
            addCriterion("share_way >", value, "shareWay");
            return (Criteria) this;
        }

        public Criteria andShareWayGreaterThanOrEqualTo(String value) {
            addCriterion("share_way >=", value, "shareWay");
            return (Criteria) this;
        }

        public Criteria andShareWayLessThan(String value) {
            addCriterion("share_way <", value, "shareWay");
            return (Criteria) this;
        }

        public Criteria andShareWayLessThanOrEqualTo(String value) {
            addCriterion("share_way <=", value, "shareWay");
            return (Criteria) this;
        }

        public Criteria andShareWayLike(String value) {
            addCriterion("share_way like", value, "shareWay");
            return (Criteria) this;
        }

        public Criteria andShareWayNotLike(String value) {
            addCriterion("share_way not like", value, "shareWay");
            return (Criteria) this;
        }

        public Criteria andShareWayIn(List<String> values) {
            addCriterion("share_way in", values, "shareWay");
            return (Criteria) this;
        }

        public Criteria andShareWayNotIn(List<String> values) {
            addCriterion("share_way not in", values, "shareWay");
            return (Criteria) this;
        }

        public Criteria andShareWayBetween(String value1, String value2) {
            addCriterion("share_way between", value1, value2, "shareWay");
            return (Criteria) this;
        }

        public Criteria andShareWayNotBetween(String value1, String value2) {
            addCriterion("share_way not between", value1, value2, "shareWay");
            return (Criteria) this;
        }

        public Criteria andShareTitleIsNull() {
            addCriterion("share_title is null");
            return (Criteria) this;
        }

        public Criteria andShareTitleIsNotNull() {
            addCriterion("share_title is not null");
            return (Criteria) this;
        }

        public Criteria andShareTitleEqualTo(String value) {
            addCriterion("share_title =", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleNotEqualTo(String value) {
            addCriterion("share_title <>", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleGreaterThan(String value) {
            addCriterion("share_title >", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleGreaterThanOrEqualTo(String value) {
            addCriterion("share_title >=", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleLessThan(String value) {
            addCriterion("share_title <", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleLessThanOrEqualTo(String value) {
            addCriterion("share_title <=", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleLike(String value) {
            addCriterion("share_title like", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleNotLike(String value) {
            addCriterion("share_title not like", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleIn(List<String> values) {
            addCriterion("share_title in", values, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleNotIn(List<String> values) {
            addCriterion("share_title not in", values, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleBetween(String value1, String value2) {
            addCriterion("share_title between", value1, value2, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleNotBetween(String value1, String value2) {
            addCriterion("share_title not between", value1, value2, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareContentIsNull() {
            addCriterion("share_content is null");
            return (Criteria) this;
        }

        public Criteria andShareContentIsNotNull() {
            addCriterion("share_content is not null");
            return (Criteria) this;
        }

        public Criteria andShareContentEqualTo(String value) {
            addCriterion("share_content =", value, "shareContent");
            return (Criteria) this;
        }

        public Criteria andShareContentNotEqualTo(String value) {
            addCriterion("share_content <>", value, "shareContent");
            return (Criteria) this;
        }

        public Criteria andShareContentGreaterThan(String value) {
            addCriterion("share_content >", value, "shareContent");
            return (Criteria) this;
        }

        public Criteria andShareContentGreaterThanOrEqualTo(String value) {
            addCriterion("share_content >=", value, "shareContent");
            return (Criteria) this;
        }

        public Criteria andShareContentLessThan(String value) {
            addCriterion("share_content <", value, "shareContent");
            return (Criteria) this;
        }

        public Criteria andShareContentLessThanOrEqualTo(String value) {
            addCriterion("share_content <=", value, "shareContent");
            return (Criteria) this;
        }

        public Criteria andShareContentLike(String value) {
            addCriterion("share_content like", value, "shareContent");
            return (Criteria) this;
        }

        public Criteria andShareContentNotLike(String value) {
            addCriterion("share_content not like", value, "shareContent");
            return (Criteria) this;
        }

        public Criteria andShareContentIn(List<String> values) {
            addCriterion("share_content in", values, "shareContent");
            return (Criteria) this;
        }

        public Criteria andShareContentNotIn(List<String> values) {
            addCriterion("share_content not in", values, "shareContent");
            return (Criteria) this;
        }

        public Criteria andShareContentBetween(String value1, String value2) {
            addCriterion("share_content between", value1, value2, "shareContent");
            return (Criteria) this;
        }

        public Criteria andShareContentNotBetween(String value1, String value2) {
            addCriterion("share_content not between", value1, value2, "shareContent");
            return (Criteria) this;
        }

        public Criteria andInviteWayIsNull() {
            addCriterion("invite_way is null");
            return (Criteria) this;
        }

        public Criteria andInviteWayIsNotNull() {
            addCriterion("invite_way is not null");
            return (Criteria) this;
        }

        public Criteria andInviteWayEqualTo(String value) {
            addCriterion("invite_way =", value, "inviteWay");
            return (Criteria) this;
        }

        public Criteria andInviteWayNotEqualTo(String value) {
            addCriterion("invite_way <>", value, "inviteWay");
            return (Criteria) this;
        }

        public Criteria andInviteWayGreaterThan(String value) {
            addCriterion("invite_way >", value, "inviteWay");
            return (Criteria) this;
        }

        public Criteria andInviteWayGreaterThanOrEqualTo(String value) {
            addCriterion("invite_way >=", value, "inviteWay");
            return (Criteria) this;
        }

        public Criteria andInviteWayLessThan(String value) {
            addCriterion("invite_way <", value, "inviteWay");
            return (Criteria) this;
        }

        public Criteria andInviteWayLessThanOrEqualTo(String value) {
            addCriterion("invite_way <=", value, "inviteWay");
            return (Criteria) this;
        }

        public Criteria andInviteWayLike(String value) {
            addCriterion("invite_way like", value, "inviteWay");
            return (Criteria) this;
        }

        public Criteria andInviteWayNotLike(String value) {
            addCriterion("invite_way not like", value, "inviteWay");
            return (Criteria) this;
        }

        public Criteria andInviteWayIn(List<String> values) {
            addCriterion("invite_way in", values, "inviteWay");
            return (Criteria) this;
        }

        public Criteria andInviteWayNotIn(List<String> values) {
            addCriterion("invite_way not in", values, "inviteWay");
            return (Criteria) this;
        }

        public Criteria andInviteWayBetween(String value1, String value2) {
            addCriterion("invite_way between", value1, value2, "inviteWay");
            return (Criteria) this;
        }

        public Criteria andInviteWayNotBetween(String value1, String value2) {
            addCriterion("invite_way not between", value1, value2, "inviteWay");
            return (Criteria) this;
        }

        public Criteria andInviteTitleIsNull() {
            addCriterion("invite_title is null");
            return (Criteria) this;
        }

        public Criteria andInviteTitleIsNotNull() {
            addCriterion("invite_title is not null");
            return (Criteria) this;
        }

        public Criteria andInviteTitleEqualTo(String value) {
            addCriterion("invite_title =", value, "inviteTitle");
            return (Criteria) this;
        }

        public Criteria andInviteTitleNotEqualTo(String value) {
            addCriterion("invite_title <>", value, "inviteTitle");
            return (Criteria) this;
        }

        public Criteria andInviteTitleGreaterThan(String value) {
            addCriterion("invite_title >", value, "inviteTitle");
            return (Criteria) this;
        }

        public Criteria andInviteTitleGreaterThanOrEqualTo(String value) {
            addCriterion("invite_title >=", value, "inviteTitle");
            return (Criteria) this;
        }

        public Criteria andInviteTitleLessThan(String value) {
            addCriterion("invite_title <", value, "inviteTitle");
            return (Criteria) this;
        }

        public Criteria andInviteTitleLessThanOrEqualTo(String value) {
            addCriterion("invite_title <=", value, "inviteTitle");
            return (Criteria) this;
        }

        public Criteria andInviteTitleLike(String value) {
            addCriterion("invite_title like", value, "inviteTitle");
            return (Criteria) this;
        }

        public Criteria andInviteTitleNotLike(String value) {
            addCriterion("invite_title not like", value, "inviteTitle");
            return (Criteria) this;
        }

        public Criteria andInviteTitleIn(List<String> values) {
            addCriterion("invite_title in", values, "inviteTitle");
            return (Criteria) this;
        }

        public Criteria andInviteTitleNotIn(List<String> values) {
            addCriterion("invite_title not in", values, "inviteTitle");
            return (Criteria) this;
        }

        public Criteria andInviteTitleBetween(String value1, String value2) {
            addCriterion("invite_title between", value1, value2, "inviteTitle");
            return (Criteria) this;
        }

        public Criteria andInviteTitleNotBetween(String value1, String value2) {
            addCriterion("invite_title not between", value1, value2, "inviteTitle");
            return (Criteria) this;
        }

        public Criteria andInviteContentIsNull() {
            addCriterion("invite_content is null");
            return (Criteria) this;
        }

        public Criteria andInviteContentIsNotNull() {
            addCriterion("invite_content is not null");
            return (Criteria) this;
        }

        public Criteria andInviteContentEqualTo(String value) {
            addCriterion("invite_content =", value, "inviteContent");
            return (Criteria) this;
        }

        public Criteria andInviteContentNotEqualTo(String value) {
            addCriterion("invite_content <>", value, "inviteContent");
            return (Criteria) this;
        }

        public Criteria andInviteContentGreaterThan(String value) {
            addCriterion("invite_content >", value, "inviteContent");
            return (Criteria) this;
        }

        public Criteria andInviteContentGreaterThanOrEqualTo(String value) {
            addCriterion("invite_content >=", value, "inviteContent");
            return (Criteria) this;
        }

        public Criteria andInviteContentLessThan(String value) {
            addCriterion("invite_content <", value, "inviteContent");
            return (Criteria) this;
        }

        public Criteria andInviteContentLessThanOrEqualTo(String value) {
            addCriterion("invite_content <=", value, "inviteContent");
            return (Criteria) this;
        }

        public Criteria andInviteContentLike(String value) {
            addCriterion("invite_content like", value, "inviteContent");
            return (Criteria) this;
        }

        public Criteria andInviteContentNotLike(String value) {
            addCriterion("invite_content not like", value, "inviteContent");
            return (Criteria) this;
        }

        public Criteria andInviteContentIn(List<String> values) {
            addCriterion("invite_content in", values, "inviteContent");
            return (Criteria) this;
        }

        public Criteria andInviteContentNotIn(List<String> values) {
            addCriterion("invite_content not in", values, "inviteContent");
            return (Criteria) this;
        }

        public Criteria andInviteContentBetween(String value1, String value2) {
            addCriterion("invite_content between", value1, value2, "inviteContent");
            return (Criteria) this;
        }

        public Criteria andInviteContentNotBetween(String value1, String value2) {
            addCriterion("invite_content not between", value1, value2, "inviteContent");
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

        public Criteria andIsAllowShareIsNull() {
            addCriterion("is_allow_share is null");
            return (Criteria) this;
        }

        public Criteria andIsAllowShareIsNotNull() {
            addCriterion("is_allow_share is not null");
            return (Criteria) this;
        }

        public Criteria andIsAllowShareEqualTo(Boolean value) {
            addCriterion("is_allow_share =", value, "isAllowShare");
            return (Criteria) this;
        }

        public Criteria andIsAllowShareNotEqualTo(Boolean value) {
            addCriterion("is_allow_share <>", value, "isAllowShare");
            return (Criteria) this;
        }

        public Criteria andIsAllowShareGreaterThan(Boolean value) {
            addCriterion("is_allow_share >", value, "isAllowShare");
            return (Criteria) this;
        }

        public Criteria andIsAllowShareGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_allow_share >=", value, "isAllowShare");
            return (Criteria) this;
        }

        public Criteria andIsAllowShareLessThan(Boolean value) {
            addCriterion("is_allow_share <", value, "isAllowShare");
            return (Criteria) this;
        }

        public Criteria andIsAllowShareLessThanOrEqualTo(Boolean value) {
            addCriterion("is_allow_share <=", value, "isAllowShare");
            return (Criteria) this;
        }

        public Criteria andIsAllowShareIn(List<Boolean> values) {
            addCriterion("is_allow_share in", values, "isAllowShare");
            return (Criteria) this;
        }

        public Criteria andIsAllowShareNotIn(List<Boolean> values) {
            addCriterion("is_allow_share not in", values, "isAllowShare");
            return (Criteria) this;
        }

        public Criteria andIsAllowShareBetween(Boolean value1, Boolean value2) {
            addCriterion("is_allow_share between", value1, value2, "isAllowShare");
            return (Criteria) this;
        }

        public Criteria andIsAllowShareNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_allow_share not between", value1, value2, "isAllowShare");
            return (Criteria) this;
        }

        public Criteria andIsAllowInviteIsNull() {
            addCriterion("is_allow_invite is null");
            return (Criteria) this;
        }

        public Criteria andIsAllowInviteIsNotNull() {
            addCriterion("is_allow_invite is not null");
            return (Criteria) this;
        }

        public Criteria andIsAllowInviteEqualTo(Boolean value) {
            addCriterion("is_allow_invite =", value, "isAllowInvite");
            return (Criteria) this;
        }

        public Criteria andIsAllowInviteNotEqualTo(Boolean value) {
            addCriterion("is_allow_invite <>", value, "isAllowInvite");
            return (Criteria) this;
        }

        public Criteria andIsAllowInviteGreaterThan(Boolean value) {
            addCriterion("is_allow_invite >", value, "isAllowInvite");
            return (Criteria) this;
        }

        public Criteria andIsAllowInviteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_allow_invite >=", value, "isAllowInvite");
            return (Criteria) this;
        }

        public Criteria andIsAllowInviteLessThan(Boolean value) {
            addCriterion("is_allow_invite <", value, "isAllowInvite");
            return (Criteria) this;
        }

        public Criteria andIsAllowInviteLessThanOrEqualTo(Boolean value) {
            addCriterion("is_allow_invite <=", value, "isAllowInvite");
            return (Criteria) this;
        }

        public Criteria andIsAllowInviteIn(List<Boolean> values) {
            addCriterion("is_allow_invite in", values, "isAllowInvite");
            return (Criteria) this;
        }

        public Criteria andIsAllowInviteNotIn(List<Boolean> values) {
            addCriterion("is_allow_invite not in", values, "isAllowInvite");
            return (Criteria) this;
        }

        public Criteria andIsAllowInviteBetween(Boolean value1, Boolean value2) {
            addCriterion("is_allow_invite between", value1, value2, "isAllowInvite");
            return (Criteria) this;
        }

        public Criteria andIsAllowInviteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_allow_invite not between", value1, value2, "isAllowInvite");
            return (Criteria) this;
        }

        public Criteria andShareAppImgIsNull() {
            addCriterion("share_app_img is null");
            return (Criteria) this;
        }

        public Criteria andShareAppImgIsNotNull() {
            addCriterion("share_app_img is not null");
            return (Criteria) this;
        }

        public Criteria andShareAppImgEqualTo(String value) {
            addCriterion("share_app_img =", value, "shareAppImg");
            return (Criteria) this;
        }

        public Criteria andShareAppImgNotEqualTo(String value) {
            addCriterion("share_app_img <>", value, "shareAppImg");
            return (Criteria) this;
        }

        public Criteria andShareAppImgGreaterThan(String value) {
            addCriterion("share_app_img >", value, "shareAppImg");
            return (Criteria) this;
        }

        public Criteria andShareAppImgGreaterThanOrEqualTo(String value) {
            addCriterion("share_app_img >=", value, "shareAppImg");
            return (Criteria) this;
        }

        public Criteria andShareAppImgLessThan(String value) {
            addCriterion("share_app_img <", value, "shareAppImg");
            return (Criteria) this;
        }

        public Criteria andShareAppImgLessThanOrEqualTo(String value) {
            addCriterion("share_app_img <=", value, "shareAppImg");
            return (Criteria) this;
        }

        public Criteria andShareAppImgLike(String value) {
            addCriterion("share_app_img like", value, "shareAppImg");
            return (Criteria) this;
        }

        public Criteria andShareAppImgNotLike(String value) {
            addCriterion("share_app_img not like", value, "shareAppImg");
            return (Criteria) this;
        }

        public Criteria andShareAppImgIn(List<String> values) {
            addCriterion("share_app_img in", values, "shareAppImg");
            return (Criteria) this;
        }

        public Criteria andShareAppImgNotIn(List<String> values) {
            addCriterion("share_app_img not in", values, "shareAppImg");
            return (Criteria) this;
        }

        public Criteria andShareAppImgBetween(String value1, String value2) {
            addCriterion("share_app_img between", value1, value2, "shareAppImg");
            return (Criteria) this;
        }

        public Criteria andShareAppImgNotBetween(String value1, String value2) {
            addCriterion("share_app_img not between", value1, value2, "shareAppImg");
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