package com.guoan.entity.shequ.bo;

import java.util.ArrayList;
import java.util.List;

public class OrderPayCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public OrderPayCriteria() {
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

        public Criteria andPayIdIsNull() {
            addCriterion("pay_id is null");
            return (Criteria) this;
        }

        public Criteria andPayIdIsNotNull() {
            addCriterion("pay_id is not null");
            return (Criteria) this;
        }

        public Criteria andPayIdEqualTo(String value) {
            addCriterion("pay_id =", value, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdNotEqualTo(String value) {
            addCriterion("pay_id <>", value, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdGreaterThan(String value) {
            addCriterion("pay_id >", value, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdGreaterThanOrEqualTo(String value) {
            addCriterion("pay_id >=", value, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdLessThan(String value) {
            addCriterion("pay_id <", value, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdLessThanOrEqualTo(String value) {
            addCriterion("pay_id <=", value, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdLike(String value) {
            addCriterion("pay_id like", value, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdNotLike(String value) {
            addCriterion("pay_id not like", value, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdIn(List<String> values) {
            addCriterion("pay_id in", values, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdNotIn(List<String> values) {
            addCriterion("pay_id not in", values, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdBetween(String value1, String value2) {
            addCriterion("pay_id between", value1, value2, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdNotBetween(String value1, String value2) {
            addCriterion("pay_id not between", value1, value2, "payId");
            return (Criteria) this;
        }

        public Criteria andPayOrderIsNull() {
            addCriterion("pay_order is null");
            return (Criteria) this;
        }

        public Criteria andPayOrderIsNotNull() {
            addCriterion("pay_order is not null");
            return (Criteria) this;
        }

        public Criteria andPayOrderEqualTo(String value) {
            addCriterion("pay_order =", value, "payOrder");
            return (Criteria) this;
        }

        public Criteria andPayOrderNotEqualTo(String value) {
            addCriterion("pay_order <>", value, "payOrder");
            return (Criteria) this;
        }

        public Criteria andPayOrderGreaterThan(String value) {
            addCriterion("pay_order >", value, "payOrder");
            return (Criteria) this;
        }

        public Criteria andPayOrderGreaterThanOrEqualTo(String value) {
            addCriterion("pay_order >=", value, "payOrder");
            return (Criteria) this;
        }

        public Criteria andPayOrderLessThan(String value) {
            addCriterion("pay_order <", value, "payOrder");
            return (Criteria) this;
        }

        public Criteria andPayOrderLessThanOrEqualTo(String value) {
            addCriterion("pay_order <=", value, "payOrder");
            return (Criteria) this;
        }

        public Criteria andPayOrderLike(String value) {
            addCriterion("pay_order like", value, "payOrder");
            return (Criteria) this;
        }

        public Criteria andPayOrderNotLike(String value) {
            addCriterion("pay_order not like", value, "payOrder");
            return (Criteria) this;
        }

        public Criteria andPayOrderIn(List<String> values) {
            addCriterion("pay_order in", values, "payOrder");
            return (Criteria) this;
        }

        public Criteria andPayOrderNotIn(List<String> values) {
            addCriterion("pay_order not in", values, "payOrder");
            return (Criteria) this;
        }

        public Criteria andPayOrderBetween(String value1, String value2) {
            addCriterion("pay_order between", value1, value2, "payOrder");
            return (Criteria) this;
        }

        public Criteria andPayOrderNotBetween(String value1, String value2) {
            addCriterion("pay_order not between", value1, value2, "payOrder");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("pay_type is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(String value) {
            addCriterion("pay_type =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(String value) {
            addCriterion("pay_type <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(String value) {
            addCriterion("pay_type >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(String value) {
            addCriterion("pay_type >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(String value) {
            addCriterion("pay_type <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(String value) {
            addCriterion("pay_type <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLike(String value) {
            addCriterion("pay_type like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotLike(String value) {
            addCriterion("pay_type not like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<String> values) {
            addCriterion("pay_type in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<String> values) {
            addCriterion("pay_type not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(String value1, String value2) {
            addCriterion("pay_type between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(String value1, String value2) {
            addCriterion("pay_type not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNameIsNull() {
            addCriterion("pay_type_name is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeNameIsNotNull() {
            addCriterion("pay_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeNameEqualTo(String value) {
            addCriterion("pay_type_name =", value, "payTypeName");
            return (Criteria) this;
        }

        public Criteria andPayTypeNameNotEqualTo(String value) {
            addCriterion("pay_type_name <>", value, "payTypeName");
            return (Criteria) this;
        }

        public Criteria andPayTypeNameGreaterThan(String value) {
            addCriterion("pay_type_name >", value, "payTypeName");
            return (Criteria) this;
        }

        public Criteria andPayTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("pay_type_name >=", value, "payTypeName");
            return (Criteria) this;
        }

        public Criteria andPayTypeNameLessThan(String value) {
            addCriterion("pay_type_name <", value, "payTypeName");
            return (Criteria) this;
        }

        public Criteria andPayTypeNameLessThanOrEqualTo(String value) {
            addCriterion("pay_type_name <=", value, "payTypeName");
            return (Criteria) this;
        }

        public Criteria andPayTypeNameLike(String value) {
            addCriterion("pay_type_name like", value, "payTypeName");
            return (Criteria) this;
        }

        public Criteria andPayTypeNameNotLike(String value) {
            addCriterion("pay_type_name not like", value, "payTypeName");
            return (Criteria) this;
        }

        public Criteria andPayTypeNameIn(List<String> values) {
            addCriterion("pay_type_name in", values, "payTypeName");
            return (Criteria) this;
        }

        public Criteria andPayTypeNameNotIn(List<String> values) {
            addCriterion("pay_type_name not in", values, "payTypeName");
            return (Criteria) this;
        }

        public Criteria andPayTypeNameBetween(String value1, String value2) {
            addCriterion("pay_type_name between", value1, value2, "payTypeName");
            return (Criteria) this;
        }

        public Criteria andPayTypeNameNotBetween(String value1, String value2) {
            addCriterion("pay_type_name not between", value1, value2, "payTypeName");
            return (Criteria) this;
        }

        public Criteria andPayIdLikeInsensitive(String value) {
            addCriterion("upper(pay_id) like", value.toUpperCase(), "payId");
            return (Criteria) this;
        }

        public Criteria andPayOrderLikeInsensitive(String value) {
            addCriterion("upper(pay_order) like", value.toUpperCase(), "payOrder");
            return (Criteria) this;
        }

        public Criteria andPayTypeLikeInsensitive(String value) {
            addCriterion("upper(pay_type) like", value.toUpperCase(), "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNameLikeInsensitive(String value) {
            addCriterion("upper(pay_type_name) like", value.toUpperCase(), "payTypeName");
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