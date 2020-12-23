package vc.thinker.cabbage.user.model;

import com.sinco.mybatis.dal.core.AbstractExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vc.thinker.cabbage.user.bo.SellerBO;

public class SellerExample extends AbstractExample<SellerBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public SellerExample() {
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

        public Criteria andSellerNameIsNull() {
            addCriterion("seller_name is null");
            return (Criteria) this;
        }

        public Criteria andSellerNameIsNotNull() {
            addCriterion("seller_name is not null");
            return (Criteria) this;
        }

        public Criteria andSellerNameEqualTo(String value) {
            addCriterion("seller_name =", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameNotEqualTo(String value) {
            addCriterion("seller_name <>", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameGreaterThan(String value) {
            addCriterion("seller_name >", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameGreaterThanOrEqualTo(String value) {
            addCriterion("seller_name >=", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameLessThan(String value) {
            addCriterion("seller_name <", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameLessThanOrEqualTo(String value) {
            addCriterion("seller_name <=", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameLike(String value) {
            addCriterion("seller_name like", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameNotLike(String value) {
            addCriterion("seller_name not like", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameIn(List<String> values) {
            addCriterion("seller_name in", values, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameNotIn(List<String> values) {
            addCriterion("seller_name not in", values, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameBetween(String value1, String value2) {
            addCriterion("seller_name between", value1, value2, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameNotBetween(String value1, String value2) {
            addCriterion("seller_name not between", value1, value2, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerLogoIsNull() {
            addCriterion("seller_logo is null");
            return (Criteria) this;
        }

        public Criteria andSellerLogoIsNotNull() {
            addCriterion("seller_logo is not null");
            return (Criteria) this;
        }

        public Criteria andSellerLogoEqualTo(String value) {
            addCriterion("seller_logo =", value, "sellerLogo");
            return (Criteria) this;
        }

        public Criteria andSellerLogoNotEqualTo(String value) {
            addCriterion("seller_logo <>", value, "sellerLogo");
            return (Criteria) this;
        }

        public Criteria andSellerLogoGreaterThan(String value) {
            addCriterion("seller_logo >", value, "sellerLogo");
            return (Criteria) this;
        }

        public Criteria andSellerLogoGreaterThanOrEqualTo(String value) {
            addCriterion("seller_logo >=", value, "sellerLogo");
            return (Criteria) this;
        }

        public Criteria andSellerLogoLessThan(String value) {
            addCriterion("seller_logo <", value, "sellerLogo");
            return (Criteria) this;
        }

        public Criteria andSellerLogoLessThanOrEqualTo(String value) {
            addCriterion("seller_logo <=", value, "sellerLogo");
            return (Criteria) this;
        }

        public Criteria andSellerLogoLike(String value) {
            addCriterion("seller_logo like", value, "sellerLogo");
            return (Criteria) this;
        }

        public Criteria andSellerLogoNotLike(String value) {
            addCriterion("seller_logo not like", value, "sellerLogo");
            return (Criteria) this;
        }

        public Criteria andSellerLogoIn(List<String> values) {
            addCriterion("seller_logo in", values, "sellerLogo");
            return (Criteria) this;
        }

        public Criteria andSellerLogoNotIn(List<String> values) {
            addCriterion("seller_logo not in", values, "sellerLogo");
            return (Criteria) this;
        }

        public Criteria andSellerLogoBetween(String value1, String value2) {
            addCriterion("seller_logo between", value1, value2, "sellerLogo");
            return (Criteria) this;
        }

        public Criteria andSellerLogoNotBetween(String value1, String value2) {
            addCriterion("seller_logo not between", value1, value2, "sellerLogo");
            return (Criteria) this;
        }

        public Criteria andSellerCoverIsNull() {
            addCriterion("seller_cover is null");
            return (Criteria) this;
        }

        public Criteria andSellerCoverIsNotNull() {
            addCriterion("seller_cover is not null");
            return (Criteria) this;
        }

        public Criteria andSellerCoverEqualTo(String value) {
            addCriterion("seller_cover =", value, "sellerCover");
            return (Criteria) this;
        }

        public Criteria andSellerCoverNotEqualTo(String value) {
            addCriterion("seller_cover <>", value, "sellerCover");
            return (Criteria) this;
        }

        public Criteria andSellerCoverGreaterThan(String value) {
            addCriterion("seller_cover >", value, "sellerCover");
            return (Criteria) this;
        }

        public Criteria andSellerCoverGreaterThanOrEqualTo(String value) {
            addCriterion("seller_cover >=", value, "sellerCover");
            return (Criteria) this;
        }

        public Criteria andSellerCoverLessThan(String value) {
            addCriterion("seller_cover <", value, "sellerCover");
            return (Criteria) this;
        }

        public Criteria andSellerCoverLessThanOrEqualTo(String value) {
            addCriterion("seller_cover <=", value, "sellerCover");
            return (Criteria) this;
        }

        public Criteria andSellerCoverLike(String value) {
            addCriterion("seller_cover like", value, "sellerCover");
            return (Criteria) this;
        }

        public Criteria andSellerCoverNotLike(String value) {
            addCriterion("seller_cover not like", value, "sellerCover");
            return (Criteria) this;
        }

        public Criteria andSellerCoverIn(List<String> values) {
            addCriterion("seller_cover in", values, "sellerCover");
            return (Criteria) this;
        }

        public Criteria andSellerCoverNotIn(List<String> values) {
            addCriterion("seller_cover not in", values, "sellerCover");
            return (Criteria) this;
        }

        public Criteria andSellerCoverBetween(String value1, String value2) {
            addCriterion("seller_cover between", value1, value2, "sellerCover");
            return (Criteria) this;
        }

        public Criteria andSellerCoverNotBetween(String value1, String value2) {
            addCriterion("seller_cover not between", value1, value2, "sellerCover");
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

        public Criteria andContactUserNameIsNull() {
            addCriterion("contact_user_name is null");
            return (Criteria) this;
        }

        public Criteria andContactUserNameIsNotNull() {
            addCriterion("contact_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andContactUserNameEqualTo(String value) {
            addCriterion("contact_user_name =", value, "contactUserName");
            return (Criteria) this;
        }

        public Criteria andContactUserNameNotEqualTo(String value) {
            addCriterion("contact_user_name <>", value, "contactUserName");
            return (Criteria) this;
        }

        public Criteria andContactUserNameGreaterThan(String value) {
            addCriterion("contact_user_name >", value, "contactUserName");
            return (Criteria) this;
        }

        public Criteria andContactUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("contact_user_name >=", value, "contactUserName");
            return (Criteria) this;
        }

        public Criteria andContactUserNameLessThan(String value) {
            addCriterion("contact_user_name <", value, "contactUserName");
            return (Criteria) this;
        }

        public Criteria andContactUserNameLessThanOrEqualTo(String value) {
            addCriterion("contact_user_name <=", value, "contactUserName");
            return (Criteria) this;
        }

        public Criteria andContactUserNameLike(String value) {
            addCriterion("contact_user_name like", value, "contactUserName");
            return (Criteria) this;
        }

        public Criteria andContactUserNameNotLike(String value) {
            addCriterion("contact_user_name not like", value, "contactUserName");
            return (Criteria) this;
        }

        public Criteria andContactUserNameIn(List<String> values) {
            addCriterion("contact_user_name in", values, "contactUserName");
            return (Criteria) this;
        }

        public Criteria andContactUserNameNotIn(List<String> values) {
            addCriterion("contact_user_name not in", values, "contactUserName");
            return (Criteria) this;
        }

        public Criteria andContactUserNameBetween(String value1, String value2) {
            addCriterion("contact_user_name between", value1, value2, "contactUserName");
            return (Criteria) this;
        }

        public Criteria andContactUserNameNotBetween(String value1, String value2) {
            addCriterion("contact_user_name not between", value1, value2, "contactUserName");
            return (Criteria) this;
        }

        public Criteria andContactMobileIsNull() {
            addCriterion("contact_mobile is null");
            return (Criteria) this;
        }

        public Criteria andContactMobileIsNotNull() {
            addCriterion("contact_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andContactMobileEqualTo(String value) {
            addCriterion("contact_mobile =", value, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactMobileNotEqualTo(String value) {
            addCriterion("contact_mobile <>", value, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactMobileGreaterThan(String value) {
            addCriterion("contact_mobile >", value, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactMobileGreaterThanOrEqualTo(String value) {
            addCriterion("contact_mobile >=", value, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactMobileLessThan(String value) {
            addCriterion("contact_mobile <", value, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactMobileLessThanOrEqualTo(String value) {
            addCriterion("contact_mobile <=", value, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactMobileLike(String value) {
            addCriterion("contact_mobile like", value, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactMobileNotLike(String value) {
            addCriterion("contact_mobile not like", value, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactMobileIn(List<String> values) {
            addCriterion("contact_mobile in", values, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactMobileNotIn(List<String> values) {
            addCriterion("contact_mobile not in", values, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactMobileBetween(String value1, String value2) {
            addCriterion("contact_mobile between", value1, value2, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactMobileNotBetween(String value1, String value2) {
            addCriterion("contact_mobile not between", value1, value2, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactTelephoneIsNull() {
            addCriterion("contact_telephone is null");
            return (Criteria) this;
        }

        public Criteria andContactTelephoneIsNotNull() {
            addCriterion("contact_telephone is not null");
            return (Criteria) this;
        }

        public Criteria andContactTelephoneEqualTo(String value) {
            addCriterion("contact_telephone =", value, "contactTelephone");
            return (Criteria) this;
        }

        public Criteria andContactTelephoneNotEqualTo(String value) {
            addCriterion("contact_telephone <>", value, "contactTelephone");
            return (Criteria) this;
        }

        public Criteria andContactTelephoneGreaterThan(String value) {
            addCriterion("contact_telephone >", value, "contactTelephone");
            return (Criteria) this;
        }

        public Criteria andContactTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("contact_telephone >=", value, "contactTelephone");
            return (Criteria) this;
        }

        public Criteria andContactTelephoneLessThan(String value) {
            addCriterion("contact_telephone <", value, "contactTelephone");
            return (Criteria) this;
        }

        public Criteria andContactTelephoneLessThanOrEqualTo(String value) {
            addCriterion("contact_telephone <=", value, "contactTelephone");
            return (Criteria) this;
        }

        public Criteria andContactTelephoneLike(String value) {
            addCriterion("contact_telephone like", value, "contactTelephone");
            return (Criteria) this;
        }

        public Criteria andContactTelephoneNotLike(String value) {
            addCriterion("contact_telephone not like", value, "contactTelephone");
            return (Criteria) this;
        }

        public Criteria andContactTelephoneIn(List<String> values) {
            addCriterion("contact_telephone in", values, "contactTelephone");
            return (Criteria) this;
        }

        public Criteria andContactTelephoneNotIn(List<String> values) {
            addCriterion("contact_telephone not in", values, "contactTelephone");
            return (Criteria) this;
        }

        public Criteria andContactTelephoneBetween(String value1, String value2) {
            addCriterion("contact_telephone between", value1, value2, "contactTelephone");
            return (Criteria) this;
        }

        public Criteria andContactTelephoneNotBetween(String value1, String value2) {
            addCriterion("contact_telephone not between", value1, value2, "contactTelephone");
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

        public Criteria andRebateRateIsNull() {
            addCriterion("rebate_rate is null");
            return (Criteria) this;
        }

        public Criteria andRebateRateIsNotNull() {
            addCriterion("rebate_rate is not null");
            return (Criteria) this;
        }

        public Criteria andRebateRateEqualTo(BigDecimal value) {
            addCriterion("rebate_rate =", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateNotEqualTo(BigDecimal value) {
            addCriterion("rebate_rate <>", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateGreaterThan(BigDecimal value) {
            addCriterion("rebate_rate >", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rebate_rate >=", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateLessThan(BigDecimal value) {
            addCriterion("rebate_rate <", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rebate_rate <=", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateIn(List<BigDecimal> values) {
            addCriterion("rebate_rate in", values, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateNotIn(List<BigDecimal> values) {
            addCriterion("rebate_rate not in", values, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebate_rate between", value1, value2, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebate_rate not between", value1, value2, "rebateRate");
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

        public Criteria andServiceTimeIsNull() {
            addCriterion("service_time is null");
            return (Criteria) this;
        }

        public Criteria andServiceTimeIsNotNull() {
            addCriterion("service_time is not null");
            return (Criteria) this;
        }

        public Criteria andServiceTimeEqualTo(String value) {
            addCriterion("service_time =", value, "serviceTime");
            return (Criteria) this;
        }

        public Criteria andServiceTimeNotEqualTo(String value) {
            addCriterion("service_time <>", value, "serviceTime");
            return (Criteria) this;
        }

        public Criteria andServiceTimeGreaterThan(String value) {
            addCriterion("service_time >", value, "serviceTime");
            return (Criteria) this;
        }

        public Criteria andServiceTimeGreaterThanOrEqualTo(String value) {
            addCriterion("service_time >=", value, "serviceTime");
            return (Criteria) this;
        }

        public Criteria andServiceTimeLessThan(String value) {
            addCriterion("service_time <", value, "serviceTime");
            return (Criteria) this;
        }

        public Criteria andServiceTimeLessThanOrEqualTo(String value) {
            addCriterion("service_time <=", value, "serviceTime");
            return (Criteria) this;
        }

        public Criteria andServiceTimeLike(String value) {
            addCriterion("service_time like", value, "serviceTime");
            return (Criteria) this;
        }

        public Criteria andServiceTimeNotLike(String value) {
            addCriterion("service_time not like", value, "serviceTime");
            return (Criteria) this;
        }

        public Criteria andServiceTimeIn(List<String> values) {
            addCriterion("service_time in", values, "serviceTime");
            return (Criteria) this;
        }

        public Criteria andServiceTimeNotIn(List<String> values) {
            addCriterion("service_time not in", values, "serviceTime");
            return (Criteria) this;
        }

        public Criteria andServiceTimeBetween(String value1, String value2) {
            addCriterion("service_time between", value1, value2, "serviceTime");
            return (Criteria) this;
        }

        public Criteria andServiceTimeNotBetween(String value1, String value2) {
            addCriterion("service_time not between", value1, value2, "serviceTime");
            return (Criteria) this;
        }

        public Criteria andRefereeUidIsNull() {
            addCriterion("referee_uid is null");
            return (Criteria) this;
        }

        public Criteria andRefereeUidIsNotNull() {
            addCriterion("referee_uid is not null");
            return (Criteria) this;
        }

        public Criteria andRefereeUidEqualTo(Long value) {
            addCriterion("referee_uid =", value, "refereeUid");
            return (Criteria) this;
        }

        public Criteria andRefereeUidNotEqualTo(Long value) {
            addCriterion("referee_uid <>", value, "refereeUid");
            return (Criteria) this;
        }

        public Criteria andRefereeUidGreaterThan(Long value) {
            addCriterion("referee_uid >", value, "refereeUid");
            return (Criteria) this;
        }

        public Criteria andRefereeUidGreaterThanOrEqualTo(Long value) {
            addCriterion("referee_uid >=", value, "refereeUid");
            return (Criteria) this;
        }

        public Criteria andRefereeUidLessThan(Long value) {
            addCriterion("referee_uid <", value, "refereeUid");
            return (Criteria) this;
        }

        public Criteria andRefereeUidLessThanOrEqualTo(Long value) {
            addCriterion("referee_uid <=", value, "refereeUid");
            return (Criteria) this;
        }

        public Criteria andRefereeUidIn(List<Long> values) {
            addCriterion("referee_uid in", values, "refereeUid");
            return (Criteria) this;
        }

        public Criteria andRefereeUidNotIn(List<Long> values) {
            addCriterion("referee_uid not in", values, "refereeUid");
            return (Criteria) this;
        }

        public Criteria andRefereeUidBetween(Long value1, Long value2) {
            addCriterion("referee_uid between", value1, value2, "refereeUid");
            return (Criteria) this;
        }

        public Criteria andRefereeUidNotBetween(Long value1, Long value2) {
            addCriterion("referee_uid not between", value1, value2, "refereeUid");
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