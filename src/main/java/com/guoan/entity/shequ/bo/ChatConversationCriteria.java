package com.guoan.entity.shequ.bo;

import java.util.ArrayList;
import java.util.List;

public class ChatConversationCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public ChatConversationCriteria() {
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

        public Criteria andConversationIdIsNull() {
            addCriterion("conversation_id is null");
            return (Criteria) this;
        }

        public Criteria andConversationIdIsNotNull() {
            addCriterion("conversation_id is not null");
            return (Criteria) this;
        }

        public Criteria andConversationIdEqualTo(String value) {
            addCriterion("conversation_id =", value, "conversationId");
            return (Criteria) this;
        }

        public Criteria andConversationIdNotEqualTo(String value) {
            addCriterion("conversation_id <>", value, "conversationId");
            return (Criteria) this;
        }

        public Criteria andConversationIdGreaterThan(String value) {
            addCriterion("conversation_id >", value, "conversationId");
            return (Criteria) this;
        }

        public Criteria andConversationIdGreaterThanOrEqualTo(String value) {
            addCriterion("conversation_id >=", value, "conversationId");
            return (Criteria) this;
        }

        public Criteria andConversationIdLessThan(String value) {
            addCriterion("conversation_id <", value, "conversationId");
            return (Criteria) this;
        }

        public Criteria andConversationIdLessThanOrEqualTo(String value) {
            addCriterion("conversation_id <=", value, "conversationId");
            return (Criteria) this;
        }

        public Criteria andConversationIdLike(String value) {
            addCriterion("conversation_id like", value, "conversationId");
            return (Criteria) this;
        }

        public Criteria andConversationIdNotLike(String value) {
            addCriterion("conversation_id not like", value, "conversationId");
            return (Criteria) this;
        }

        public Criteria andConversationIdIn(List<String> values) {
            addCriterion("conversation_id in", values, "conversationId");
            return (Criteria) this;
        }

        public Criteria andConversationIdNotIn(List<String> values) {
            addCriterion("conversation_id not in", values, "conversationId");
            return (Criteria) this;
        }

        public Criteria andConversationIdBetween(String value1, String value2) {
            addCriterion("conversation_id between", value1, value2, "conversationId");
            return (Criteria) this;
        }

        public Criteria andConversationIdNotBetween(String value1, String value2) {
            addCriterion("conversation_id not between", value1, value2, "conversationId");
            return (Criteria) this;
        }

        public Criteria andAUserIdIsNull() {
            addCriterion("a_user_id is null");
            return (Criteria) this;
        }

        public Criteria andAUserIdIsNotNull() {
            addCriterion("a_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andAUserIdEqualTo(String value) {
            addCriterion("a_user_id =", value, "aUserId");
            return (Criteria) this;
        }

        public Criteria andAUserIdNotEqualTo(String value) {
            addCriterion("a_user_id <>", value, "aUserId");
            return (Criteria) this;
        }

        public Criteria andAUserIdGreaterThan(String value) {
            addCriterion("a_user_id >", value, "aUserId");
            return (Criteria) this;
        }

        public Criteria andAUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("a_user_id >=", value, "aUserId");
            return (Criteria) this;
        }

        public Criteria andAUserIdLessThan(String value) {
            addCriterion("a_user_id <", value, "aUserId");
            return (Criteria) this;
        }

        public Criteria andAUserIdLessThanOrEqualTo(String value) {
            addCriterion("a_user_id <=", value, "aUserId");
            return (Criteria) this;
        }

        public Criteria andAUserIdLike(String value) {
            addCriterion("a_user_id like", value, "aUserId");
            return (Criteria) this;
        }

        public Criteria andAUserIdNotLike(String value) {
            addCriterion("a_user_id not like", value, "aUserId");
            return (Criteria) this;
        }

        public Criteria andAUserIdIn(List<String> values) {
            addCriterion("a_user_id in", values, "aUserId");
            return (Criteria) this;
        }

        public Criteria andAUserIdNotIn(List<String> values) {
            addCriterion("a_user_id not in", values, "aUserId");
            return (Criteria) this;
        }

        public Criteria andAUserIdBetween(String value1, String value2) {
            addCriterion("a_user_id between", value1, value2, "aUserId");
            return (Criteria) this;
        }

        public Criteria andAUserIdNotBetween(String value1, String value2) {
            addCriterion("a_user_id not between", value1, value2, "aUserId");
            return (Criteria) this;
        }

        public Criteria andBUserIdIsNull() {
            addCriterion("b_user_id is null");
            return (Criteria) this;
        }

        public Criteria andBUserIdIsNotNull() {
            addCriterion("b_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andBUserIdEqualTo(String value) {
            addCriterion("b_user_id =", value, "bUserId");
            return (Criteria) this;
        }

        public Criteria andBUserIdNotEqualTo(String value) {
            addCriterion("b_user_id <>", value, "bUserId");
            return (Criteria) this;
        }

        public Criteria andBUserIdGreaterThan(String value) {
            addCriterion("b_user_id >", value, "bUserId");
            return (Criteria) this;
        }

        public Criteria andBUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("b_user_id >=", value, "bUserId");
            return (Criteria) this;
        }

        public Criteria andBUserIdLessThan(String value) {
            addCriterion("b_user_id <", value, "bUserId");
            return (Criteria) this;
        }

        public Criteria andBUserIdLessThanOrEqualTo(String value) {
            addCriterion("b_user_id <=", value, "bUserId");
            return (Criteria) this;
        }

        public Criteria andBUserIdLike(String value) {
            addCriterion("b_user_id like", value, "bUserId");
            return (Criteria) this;
        }

        public Criteria andBUserIdNotLike(String value) {
            addCriterion("b_user_id not like", value, "bUserId");
            return (Criteria) this;
        }

        public Criteria andBUserIdIn(List<String> values) {
            addCriterion("b_user_id in", values, "bUserId");
            return (Criteria) this;
        }

        public Criteria andBUserIdNotIn(List<String> values) {
            addCriterion("b_user_id not in", values, "bUserId");
            return (Criteria) this;
        }

        public Criteria andBUserIdBetween(String value1, String value2) {
            addCriterion("b_user_id between", value1, value2, "bUserId");
            return (Criteria) this;
        }

        public Criteria andBUserIdNotBetween(String value1, String value2) {
            addCriterion("b_user_id not between", value1, value2, "bUserId");
            return (Criteria) this;
        }

        public Criteria andAUnreadIsNull() {
            addCriterion("a_unread is null");
            return (Criteria) this;
        }

        public Criteria andAUnreadIsNotNull() {
            addCriterion("a_unread is not null");
            return (Criteria) this;
        }

        public Criteria andAUnreadEqualTo(Integer value) {
            addCriterion("a_unread =", value, "aUnread");
            return (Criteria) this;
        }

        public Criteria andAUnreadNotEqualTo(Integer value) {
            addCriterion("a_unread <>", value, "aUnread");
            return (Criteria) this;
        }

        public Criteria andAUnreadGreaterThan(Integer value) {
            addCriterion("a_unread >", value, "aUnread");
            return (Criteria) this;
        }

        public Criteria andAUnreadGreaterThanOrEqualTo(Integer value) {
            addCriterion("a_unread >=", value, "aUnread");
            return (Criteria) this;
        }

        public Criteria andAUnreadLessThan(Integer value) {
            addCriterion("a_unread <", value, "aUnread");
            return (Criteria) this;
        }

        public Criteria andAUnreadLessThanOrEqualTo(Integer value) {
            addCriterion("a_unread <=", value, "aUnread");
            return (Criteria) this;
        }

        public Criteria andAUnreadIn(List<Integer> values) {
            addCriterion("a_unread in", values, "aUnread");
            return (Criteria) this;
        }

        public Criteria andAUnreadNotIn(List<Integer> values) {
            addCriterion("a_unread not in", values, "aUnread");
            return (Criteria) this;
        }

        public Criteria andAUnreadBetween(Integer value1, Integer value2) {
            addCriterion("a_unread between", value1, value2, "aUnread");
            return (Criteria) this;
        }

        public Criteria andAUnreadNotBetween(Integer value1, Integer value2) {
            addCriterion("a_unread not between", value1, value2, "aUnread");
            return (Criteria) this;
        }

        public Criteria andBUnreadIsNull() {
            addCriterion("b_unread is null");
            return (Criteria) this;
        }

        public Criteria andBUnreadIsNotNull() {
            addCriterion("b_unread is not null");
            return (Criteria) this;
        }

        public Criteria andBUnreadEqualTo(Integer value) {
            addCriterion("b_unread =", value, "bUnread");
            return (Criteria) this;
        }

        public Criteria andBUnreadNotEqualTo(Integer value) {
            addCriterion("b_unread <>", value, "bUnread");
            return (Criteria) this;
        }

        public Criteria andBUnreadGreaterThan(Integer value) {
            addCriterion("b_unread >", value, "bUnread");
            return (Criteria) this;
        }

        public Criteria andBUnreadGreaterThanOrEqualTo(Integer value) {
            addCriterion("b_unread >=", value, "bUnread");
            return (Criteria) this;
        }

        public Criteria andBUnreadLessThan(Integer value) {
            addCriterion("b_unread <", value, "bUnread");
            return (Criteria) this;
        }

        public Criteria andBUnreadLessThanOrEqualTo(Integer value) {
            addCriterion("b_unread <=", value, "bUnread");
            return (Criteria) this;
        }

        public Criteria andBUnreadIn(List<Integer> values) {
            addCriterion("b_unread in", values, "bUnread");
            return (Criteria) this;
        }

        public Criteria andBUnreadNotIn(List<Integer> values) {
            addCriterion("b_unread not in", values, "bUnread");
            return (Criteria) this;
        }

        public Criteria andBUnreadBetween(Integer value1, Integer value2) {
            addCriterion("b_unread between", value1, value2, "bUnread");
            return (Criteria) this;
        }

        public Criteria andBUnreadNotBetween(Integer value1, Integer value2) {
            addCriterion("b_unread not between", value1, value2, "bUnread");
            return (Criteria) this;
        }

        public Criteria andConversationIdLikeInsensitive(String value) {
            addCriterion("upper(conversation_id) like", value.toUpperCase(), "conversationId");
            return (Criteria) this;
        }

        public Criteria andAUserIdLikeInsensitive(String value) {
            addCriterion("upper(a_user_id) like", value.toUpperCase(), "aUserId");
            return (Criteria) this;
        }

        public Criteria andBUserIdLikeInsensitive(String value) {
            addCriterion("upper(b_user_id) like", value.toUpperCase(), "bUserId");
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