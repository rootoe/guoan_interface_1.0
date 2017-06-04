package com.guoan.entity.shequ.bo;

import java.util.ArrayList;
import java.util.List;

public class OrderWashCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public OrderWashCriteria() {
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

        public Criteria andWashIdIsNull() {
            addCriterion("wash_id is null");
            return (Criteria) this;
        }

        public Criteria andWashIdIsNotNull() {
            addCriterion("wash_id is not null");
            return (Criteria) this;
        }

        public Criteria andWashIdEqualTo(String value) {
            addCriterion("wash_id =", value, "washId");
            return (Criteria) this;
        }

        public Criteria andWashIdNotEqualTo(String value) {
            addCriterion("wash_id <>", value, "washId");
            return (Criteria) this;
        }

        public Criteria andWashIdGreaterThan(String value) {
            addCriterion("wash_id >", value, "washId");
            return (Criteria) this;
        }

        public Criteria andWashIdGreaterThanOrEqualTo(String value) {
            addCriterion("wash_id >=", value, "washId");
            return (Criteria) this;
        }

        public Criteria andWashIdLessThan(String value) {
            addCriterion("wash_id <", value, "washId");
            return (Criteria) this;
        }

        public Criteria andWashIdLessThanOrEqualTo(String value) {
            addCriterion("wash_id <=", value, "washId");
            return (Criteria) this;
        }

        public Criteria andWashIdLike(String value) {
            addCriterion("wash_id like", value, "washId");
            return (Criteria) this;
        }

        public Criteria andWashIdNotLike(String value) {
            addCriterion("wash_id not like", value, "washId");
            return (Criteria) this;
        }

        public Criteria andWashIdIn(List<String> values) {
            addCriterion("wash_id in", values, "washId");
            return (Criteria) this;
        }

        public Criteria andWashIdNotIn(List<String> values) {
            addCriterion("wash_id not in", values, "washId");
            return (Criteria) this;
        }

        public Criteria andWashIdBetween(String value1, String value2) {
            addCriterion("wash_id between", value1, value2, "washId");
            return (Criteria) this;
        }

        public Criteria andWashIdNotBetween(String value1, String value2) {
            addCriterion("wash_id not between", value1, value2, "washId");
            return (Criteria) this;
        }

        public Criteria andOrderQuantityIsNull() {
            addCriterion("order_quantity is null");
            return (Criteria) this;
        }

        public Criteria andOrderQuantityIsNotNull() {
            addCriterion("order_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andOrderQuantityEqualTo(Integer value) {
            addCriterion("order_quantity =", value, "orderQuantity");
            return (Criteria) this;
        }

        public Criteria andOrderQuantityNotEqualTo(Integer value) {
            addCriterion("order_quantity <>", value, "orderQuantity");
            return (Criteria) this;
        }

        public Criteria andOrderQuantityGreaterThan(Integer value) {
            addCriterion("order_quantity >", value, "orderQuantity");
            return (Criteria) this;
        }

        public Criteria andOrderQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_quantity >=", value, "orderQuantity");
            return (Criteria) this;
        }

        public Criteria andOrderQuantityLessThan(Integer value) {
            addCriterion("order_quantity <", value, "orderQuantity");
            return (Criteria) this;
        }

        public Criteria andOrderQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("order_quantity <=", value, "orderQuantity");
            return (Criteria) this;
        }

        public Criteria andOrderQuantityIn(List<Integer> values) {
            addCriterion("order_quantity in", values, "orderQuantity");
            return (Criteria) this;
        }

        public Criteria andOrderQuantityNotIn(List<Integer> values) {
            addCriterion("order_quantity not in", values, "orderQuantity");
            return (Criteria) this;
        }

        public Criteria andOrderQuantityBetween(Integer value1, Integer value2) {
            addCriterion("order_quantity between", value1, value2, "orderQuantity");
            return (Criteria) this;
        }

        public Criteria andOrderQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("order_quantity not between", value1, value2, "orderQuantity");
            return (Criteria) this;
        }

        public Criteria andWashOrderIsNull() {
            addCriterion("wash_order is null");
            return (Criteria) this;
        }

        public Criteria andWashOrderIsNotNull() {
            addCriterion("wash_order is not null");
            return (Criteria) this;
        }

        public Criteria andWashOrderEqualTo(String value) {
            addCriterion("wash_order =", value, "washOrder");
            return (Criteria) this;
        }

        public Criteria andWashOrderNotEqualTo(String value) {
            addCriterion("wash_order <>", value, "washOrder");
            return (Criteria) this;
        }

        public Criteria andWashOrderGreaterThan(String value) {
            addCriterion("wash_order >", value, "washOrder");
            return (Criteria) this;
        }

        public Criteria andWashOrderGreaterThanOrEqualTo(String value) {
            addCriterion("wash_order >=", value, "washOrder");
            return (Criteria) this;
        }

        public Criteria andWashOrderLessThan(String value) {
            addCriterion("wash_order <", value, "washOrder");
            return (Criteria) this;
        }

        public Criteria andWashOrderLessThanOrEqualTo(String value) {
            addCriterion("wash_order <=", value, "washOrder");
            return (Criteria) this;
        }

        public Criteria andWashOrderLike(String value) {
            addCriterion("wash_order like", value, "washOrder");
            return (Criteria) this;
        }

        public Criteria andWashOrderNotLike(String value) {
            addCriterion("wash_order not like", value, "washOrder");
            return (Criteria) this;
        }

        public Criteria andWashOrderIn(List<String> values) {
            addCriterion("wash_order in", values, "washOrder");
            return (Criteria) this;
        }

        public Criteria andWashOrderNotIn(List<String> values) {
            addCriterion("wash_order not in", values, "washOrder");
            return (Criteria) this;
        }

        public Criteria andWashOrderBetween(String value1, String value2) {
            addCriterion("wash_order between", value1, value2, "washOrder");
            return (Criteria) this;
        }

        public Criteria andWashOrderNotBetween(String value1, String value2) {
            addCriterion("wash_order not between", value1, value2, "washOrder");
            return (Criteria) this;
        }

        public Criteria andProviderIdIsNull() {
            addCriterion("provider_id is null");
            return (Criteria) this;
        }

        public Criteria andProviderIdIsNotNull() {
            addCriterion("provider_id is not null");
            return (Criteria) this;
        }

        public Criteria andProviderIdEqualTo(Integer value) {
            addCriterion("provider_id =", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdNotEqualTo(Integer value) {
            addCriterion("provider_id <>", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdGreaterThan(Integer value) {
            addCriterion("provider_id >", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("provider_id >=", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdLessThan(Integer value) {
            addCriterion("provider_id <", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdLessThanOrEqualTo(Integer value) {
            addCriterion("provider_id <=", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdIn(List<Integer> values) {
            addCriterion("provider_id in", values, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdNotIn(List<Integer> values) {
            addCriterion("provider_id not in", values, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdBetween(Integer value1, Integer value2) {
            addCriterion("provider_id between", value1, value2, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("provider_id not between", value1, value2, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderNameIsNull() {
            addCriterion("provider_name is null");
            return (Criteria) this;
        }

        public Criteria andProviderNameIsNotNull() {
            addCriterion("provider_name is not null");
            return (Criteria) this;
        }

        public Criteria andProviderNameEqualTo(String value) {
            addCriterion("provider_name =", value, "providerName");
            return (Criteria) this;
        }

        public Criteria andProviderNameNotEqualTo(String value) {
            addCriterion("provider_name <>", value, "providerName");
            return (Criteria) this;
        }

        public Criteria andProviderNameGreaterThan(String value) {
            addCriterion("provider_name >", value, "providerName");
            return (Criteria) this;
        }

        public Criteria andProviderNameGreaterThanOrEqualTo(String value) {
            addCriterion("provider_name >=", value, "providerName");
            return (Criteria) this;
        }

        public Criteria andProviderNameLessThan(String value) {
            addCriterion("provider_name <", value, "providerName");
            return (Criteria) this;
        }

        public Criteria andProviderNameLessThanOrEqualTo(String value) {
            addCriterion("provider_name <=", value, "providerName");
            return (Criteria) this;
        }

        public Criteria andProviderNameLike(String value) {
            addCriterion("provider_name like", value, "providerName");
            return (Criteria) this;
        }

        public Criteria andProviderNameNotLike(String value) {
            addCriterion("provider_name not like", value, "providerName");
            return (Criteria) this;
        }

        public Criteria andProviderNameIn(List<String> values) {
            addCriterion("provider_name in", values, "providerName");
            return (Criteria) this;
        }

        public Criteria andProviderNameNotIn(List<String> values) {
            addCriterion("provider_name not in", values, "providerName");
            return (Criteria) this;
        }

        public Criteria andProviderNameBetween(String value1, String value2) {
            addCriterion("provider_name between", value1, value2, "providerName");
            return (Criteria) this;
        }

        public Criteria andProviderNameNotBetween(String value1, String value2) {
            addCriterion("provider_name not between", value1, value2, "providerName");
            return (Criteria) this;
        }

        public Criteria andWashIdLikeInsensitive(String value) {
            addCriterion("upper(wash_id) like", value.toUpperCase(), "washId");
            return (Criteria) this;
        }

        public Criteria andWashOrderLikeInsensitive(String value) {
            addCriterion("upper(wash_order) like", value.toUpperCase(), "washOrder");
            return (Criteria) this;
        }

        public Criteria andProviderNameLikeInsensitive(String value) {
            addCriterion("upper(provider_name) like", value.toUpperCase(), "providerName");
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