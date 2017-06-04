package com.guoan.entity.shequ.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppDeviceInfoCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public AppDeviceInfoCriteria() {
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

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andClientIpIsNull() {
            addCriterion("client_ip is null");
            return (Criteria) this;
        }

        public Criteria andClientIpIsNotNull() {
            addCriterion("client_ip is not null");
            return (Criteria) this;
        }

        public Criteria andClientIpEqualTo(String value) {
            addCriterion("client_ip =", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpNotEqualTo(String value) {
            addCriterion("client_ip <>", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpGreaterThan(String value) {
            addCriterion("client_ip >", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpGreaterThanOrEqualTo(String value) {
            addCriterion("client_ip >=", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpLessThan(String value) {
            addCriterion("client_ip <", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpLessThanOrEqualTo(String value) {
            addCriterion("client_ip <=", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpLike(String value) {
            addCriterion("client_ip like", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpNotLike(String value) {
            addCriterion("client_ip not like", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpIn(List<String> values) {
            addCriterion("client_ip in", values, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpNotIn(List<String> values) {
            addCriterion("client_ip not in", values, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpBetween(String value1, String value2) {
            addCriterion("client_ip between", value1, value2, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpNotBetween(String value1, String value2) {
            addCriterion("client_ip not between", value1, value2, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientTokenIsNull() {
            addCriterion("client_token is null");
            return (Criteria) this;
        }

        public Criteria andClientTokenIsNotNull() {
            addCriterion("client_token is not null");
            return (Criteria) this;
        }

        public Criteria andClientTokenEqualTo(String value) {
            addCriterion("client_token =", value, "clientToken");
            return (Criteria) this;
        }

        public Criteria andClientTokenNotEqualTo(String value) {
            addCriterion("client_token <>", value, "clientToken");
            return (Criteria) this;
        }

        public Criteria andClientTokenGreaterThan(String value) {
            addCriterion("client_token >", value, "clientToken");
            return (Criteria) this;
        }

        public Criteria andClientTokenGreaterThanOrEqualTo(String value) {
            addCriterion("client_token >=", value, "clientToken");
            return (Criteria) this;
        }

        public Criteria andClientTokenLessThan(String value) {
            addCriterion("client_token <", value, "clientToken");
            return (Criteria) this;
        }

        public Criteria andClientTokenLessThanOrEqualTo(String value) {
            addCriterion("client_token <=", value, "clientToken");
            return (Criteria) this;
        }

        public Criteria andClientTokenLike(String value) {
            addCriterion("client_token like", value, "clientToken");
            return (Criteria) this;
        }

        public Criteria andClientTokenNotLike(String value) {
            addCriterion("client_token not like", value, "clientToken");
            return (Criteria) this;
        }

        public Criteria andClientTokenIn(List<String> values) {
            addCriterion("client_token in", values, "clientToken");
            return (Criteria) this;
        }

        public Criteria andClientTokenNotIn(List<String> values) {
            addCriterion("client_token not in", values, "clientToken");
            return (Criteria) this;
        }

        public Criteria andClientTokenBetween(String value1, String value2) {
            addCriterion("client_token between", value1, value2, "clientToken");
            return (Criteria) this;
        }

        public Criteria andClientTokenNotBetween(String value1, String value2) {
            addCriterion("client_token not between", value1, value2, "clientToken");
            return (Criteria) this;
        }

        public Criteria andPhoneModelIsNull() {
            addCriterion("phone_model is null");
            return (Criteria) this;
        }

        public Criteria andPhoneModelIsNotNull() {
            addCriterion("phone_model is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneModelEqualTo(String value) {
            addCriterion("phone_model =", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelNotEqualTo(String value) {
            addCriterion("phone_model <>", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelGreaterThan(String value) {
            addCriterion("phone_model >", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelGreaterThanOrEqualTo(String value) {
            addCriterion("phone_model >=", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelLessThan(String value) {
            addCriterion("phone_model <", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelLessThanOrEqualTo(String value) {
            addCriterion("phone_model <=", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelLike(String value) {
            addCriterion("phone_model like", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelNotLike(String value) {
            addCriterion("phone_model not like", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelIn(List<String> values) {
            addCriterion("phone_model in", values, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelNotIn(List<String> values) {
            addCriterion("phone_model not in", values, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelBetween(String value1, String value2) {
            addCriterion("phone_model between", value1, value2, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelNotBetween(String value1, String value2) {
            addCriterion("phone_model not between", value1, value2, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandIsNull() {
            addCriterion("phone_brand is null");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandIsNotNull() {
            addCriterion("phone_brand is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandEqualTo(String value) {
            addCriterion("phone_brand =", value, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandNotEqualTo(String value) {
            addCriterion("phone_brand <>", value, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandGreaterThan(String value) {
            addCriterion("phone_brand >", value, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandGreaterThanOrEqualTo(String value) {
            addCriterion("phone_brand >=", value, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandLessThan(String value) {
            addCriterion("phone_brand <", value, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandLessThanOrEqualTo(String value) {
            addCriterion("phone_brand <=", value, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandLike(String value) {
            addCriterion("phone_brand like", value, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandNotLike(String value) {
            addCriterion("phone_brand not like", value, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandIn(List<String> values) {
            addCriterion("phone_brand in", values, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandNotIn(List<String> values) {
            addCriterion("phone_brand not in", values, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandBetween(String value1, String value2) {
            addCriterion("phone_brand between", value1, value2, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandNotBetween(String value1, String value2) {
            addCriterion("phone_brand not between", value1, value2, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenWidthIsNull() {
            addCriterion("phone_screen_width is null");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenWidthIsNotNull() {
            addCriterion("phone_screen_width is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenWidthEqualTo(String value) {
            addCriterion("phone_screen_width =", value, "phoneScreenWidth");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenWidthNotEqualTo(String value) {
            addCriterion("phone_screen_width <>", value, "phoneScreenWidth");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenWidthGreaterThan(String value) {
            addCriterion("phone_screen_width >", value, "phoneScreenWidth");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenWidthGreaterThanOrEqualTo(String value) {
            addCriterion("phone_screen_width >=", value, "phoneScreenWidth");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenWidthLessThan(String value) {
            addCriterion("phone_screen_width <", value, "phoneScreenWidth");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenWidthLessThanOrEqualTo(String value) {
            addCriterion("phone_screen_width <=", value, "phoneScreenWidth");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenWidthLike(String value) {
            addCriterion("phone_screen_width like", value, "phoneScreenWidth");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenWidthNotLike(String value) {
            addCriterion("phone_screen_width not like", value, "phoneScreenWidth");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenWidthIn(List<String> values) {
            addCriterion("phone_screen_width in", values, "phoneScreenWidth");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenWidthNotIn(List<String> values) {
            addCriterion("phone_screen_width not in", values, "phoneScreenWidth");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenWidthBetween(String value1, String value2) {
            addCriterion("phone_screen_width between", value1, value2, "phoneScreenWidth");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenWidthNotBetween(String value1, String value2) {
            addCriterion("phone_screen_width not between", value1, value2, "phoneScreenWidth");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenHeightIsNull() {
            addCriterion("phone_screen_height is null");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenHeightIsNotNull() {
            addCriterion("phone_screen_height is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenHeightEqualTo(String value) {
            addCriterion("phone_screen_height =", value, "phoneScreenHeight");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenHeightNotEqualTo(String value) {
            addCriterion("phone_screen_height <>", value, "phoneScreenHeight");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenHeightGreaterThan(String value) {
            addCriterion("phone_screen_height >", value, "phoneScreenHeight");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenHeightGreaterThanOrEqualTo(String value) {
            addCriterion("phone_screen_height >=", value, "phoneScreenHeight");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenHeightLessThan(String value) {
            addCriterion("phone_screen_height <", value, "phoneScreenHeight");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenHeightLessThanOrEqualTo(String value) {
            addCriterion("phone_screen_height <=", value, "phoneScreenHeight");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenHeightLike(String value) {
            addCriterion("phone_screen_height like", value, "phoneScreenHeight");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenHeightNotLike(String value) {
            addCriterion("phone_screen_height not like", value, "phoneScreenHeight");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenHeightIn(List<String> values) {
            addCriterion("phone_screen_height in", values, "phoneScreenHeight");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenHeightNotIn(List<String> values) {
            addCriterion("phone_screen_height not in", values, "phoneScreenHeight");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenHeightBetween(String value1, String value2) {
            addCriterion("phone_screen_height between", value1, value2, "phoneScreenHeight");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenHeightNotBetween(String value1, String value2) {
            addCriterion("phone_screen_height not between", value1, value2, "phoneScreenHeight");
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

        public Criteria andUserIdLikeInsensitive(String value) {
            addCriterion("upper(user_id) like", value.toUpperCase(), "userId");
            return (Criteria) this;
        }

        public Criteria andClientIpLikeInsensitive(String value) {
            addCriterion("upper(client_ip) like", value.toUpperCase(), "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientTokenLikeInsensitive(String value) {
            addCriterion("upper(client_token) like", value.toUpperCase(), "clientToken");
            return (Criteria) this;
        }

        public Criteria andPhoneModelLikeInsensitive(String value) {
            addCriterion("upper(phone_model) like", value.toUpperCase(), "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandLikeInsensitive(String value) {
            addCriterion("upper(phone_brand) like", value.toUpperCase(), "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenWidthLikeInsensitive(String value) {
            addCriterion("upper(phone_screen_width) like", value.toUpperCase(), "phoneScreenWidth");
            return (Criteria) this;
        }

        public Criteria andPhoneScreenHeightLikeInsensitive(String value) {
            addCriterion("upper(phone_screen_height) like", value.toUpperCase(), "phoneScreenHeight");
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