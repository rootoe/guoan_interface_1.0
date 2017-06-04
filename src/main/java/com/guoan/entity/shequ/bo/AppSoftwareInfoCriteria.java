package com.guoan.entity.shequ.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppSoftwareInfoCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public AppSoftwareInfoCriteria() {
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

        public Criteria andClientVersionIsNull() {
            addCriterion("client_version is null");
            return (Criteria) this;
        }

        public Criteria andClientVersionIsNotNull() {
            addCriterion("client_version is not null");
            return (Criteria) this;
        }

        public Criteria andClientVersionEqualTo(String value) {
            addCriterion("client_version =", value, "clientVersion");
            return (Criteria) this;
        }

        public Criteria andClientVersionNotEqualTo(String value) {
            addCriterion("client_version <>", value, "clientVersion");
            return (Criteria) this;
        }

        public Criteria andClientVersionGreaterThan(String value) {
            addCriterion("client_version >", value, "clientVersion");
            return (Criteria) this;
        }

        public Criteria andClientVersionGreaterThanOrEqualTo(String value) {
            addCriterion("client_version >=", value, "clientVersion");
            return (Criteria) this;
        }

        public Criteria andClientVersionLessThan(String value) {
            addCriterion("client_version <", value, "clientVersion");
            return (Criteria) this;
        }

        public Criteria andClientVersionLessThanOrEqualTo(String value) {
            addCriterion("client_version <=", value, "clientVersion");
            return (Criteria) this;
        }

        public Criteria andClientVersionLike(String value) {
            addCriterion("client_version like", value, "clientVersion");
            return (Criteria) this;
        }

        public Criteria andClientVersionNotLike(String value) {
            addCriterion("client_version not like", value, "clientVersion");
            return (Criteria) this;
        }

        public Criteria andClientVersionIn(List<String> values) {
            addCriterion("client_version in", values, "clientVersion");
            return (Criteria) this;
        }

        public Criteria andClientVersionNotIn(List<String> values) {
            addCriterion("client_version not in", values, "clientVersion");
            return (Criteria) this;
        }

        public Criteria andClientVersionBetween(String value1, String value2) {
            addCriterion("client_version between", value1, value2, "clientVersion");
            return (Criteria) this;
        }

        public Criteria andClientVersionNotBetween(String value1, String value2) {
            addCriterion("client_version not between", value1, value2, "clientVersion");
            return (Criteria) this;
        }

        public Criteria andClientPlatformIsNull() {
            addCriterion("client_platform is null");
            return (Criteria) this;
        }

        public Criteria andClientPlatformIsNotNull() {
            addCriterion("client_platform is not null");
            return (Criteria) this;
        }

        public Criteria andClientPlatformEqualTo(String value) {
            addCriterion("client_platform =", value, "clientPlatform");
            return (Criteria) this;
        }

        public Criteria andClientPlatformNotEqualTo(String value) {
            addCriterion("client_platform <>", value, "clientPlatform");
            return (Criteria) this;
        }

        public Criteria andClientPlatformGreaterThan(String value) {
            addCriterion("client_platform >", value, "clientPlatform");
            return (Criteria) this;
        }

        public Criteria andClientPlatformGreaterThanOrEqualTo(String value) {
            addCriterion("client_platform >=", value, "clientPlatform");
            return (Criteria) this;
        }

        public Criteria andClientPlatformLessThan(String value) {
            addCriterion("client_platform <", value, "clientPlatform");
            return (Criteria) this;
        }

        public Criteria andClientPlatformLessThanOrEqualTo(String value) {
            addCriterion("client_platform <=", value, "clientPlatform");
            return (Criteria) this;
        }

        public Criteria andClientPlatformLike(String value) {
            addCriterion("client_platform like", value, "clientPlatform");
            return (Criteria) this;
        }

        public Criteria andClientPlatformNotLike(String value) {
            addCriterion("client_platform not like", value, "clientPlatform");
            return (Criteria) this;
        }

        public Criteria andClientPlatformIn(List<String> values) {
            addCriterion("client_platform in", values, "clientPlatform");
            return (Criteria) this;
        }

        public Criteria andClientPlatformNotIn(List<String> values) {
            addCriterion("client_platform not in", values, "clientPlatform");
            return (Criteria) this;
        }

        public Criteria andClientPlatformBetween(String value1, String value2) {
            addCriterion("client_platform between", value1, value2, "clientPlatform");
            return (Criteria) this;
        }

        public Criteria andClientPlatformNotBetween(String value1, String value2) {
            addCriterion("client_platform not between", value1, value2, "clientPlatform");
            return (Criteria) this;
        }

        public Criteria andHannelsIsNull() {
            addCriterion("hannels is null");
            return (Criteria) this;
        }

        public Criteria andHannelsIsNotNull() {
            addCriterion("hannels is not null");
            return (Criteria) this;
        }

        public Criteria andHannelsEqualTo(String value) {
            addCriterion("hannels =", value, "hannels");
            return (Criteria) this;
        }

        public Criteria andHannelsNotEqualTo(String value) {
            addCriterion("hannels <>", value, "hannels");
            return (Criteria) this;
        }

        public Criteria andHannelsGreaterThan(String value) {
            addCriterion("hannels >", value, "hannels");
            return (Criteria) this;
        }

        public Criteria andHannelsGreaterThanOrEqualTo(String value) {
            addCriterion("hannels >=", value, "hannels");
            return (Criteria) this;
        }

        public Criteria andHannelsLessThan(String value) {
            addCriterion("hannels <", value, "hannels");
            return (Criteria) this;
        }

        public Criteria andHannelsLessThanOrEqualTo(String value) {
            addCriterion("hannels <=", value, "hannels");
            return (Criteria) this;
        }

        public Criteria andHannelsLike(String value) {
            addCriterion("hannels like", value, "hannels");
            return (Criteria) this;
        }

        public Criteria andHannelsNotLike(String value) {
            addCriterion("hannels not like", value, "hannels");
            return (Criteria) this;
        }

        public Criteria andHannelsIn(List<String> values) {
            addCriterion("hannels in", values, "hannels");
            return (Criteria) this;
        }

        public Criteria andHannelsNotIn(List<String> values) {
            addCriterion("hannels not in", values, "hannels");
            return (Criteria) this;
        }

        public Criteria andHannelsBetween(String value1, String value2) {
            addCriterion("hannels between", value1, value2, "hannels");
            return (Criteria) this;
        }

        public Criteria andHannelsNotBetween(String value1, String value2) {
            addCriterion("hannels not between", value1, value2, "hannels");
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

        public Criteria andClientVersionLikeInsensitive(String value) {
            addCriterion("upper(client_version) like", value.toUpperCase(), "clientVersion");
            return (Criteria) this;
        }

        public Criteria andClientPlatformLikeInsensitive(String value) {
            addCriterion("upper(client_platform) like", value.toUpperCase(), "clientPlatform");
            return (Criteria) this;
        }

        public Criteria andHannelsLikeInsensitive(String value) {
            addCriterion("upper(hannels) like", value.toUpperCase(), "hannels");
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