package com.guoan.entity.shequ.bo;

import java.util.ArrayList;
import java.util.List;

public class OrderExpressCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public OrderExpressCriteria() {
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

        public Criteria andExpressIdIsNull() {
            addCriterion("express_id is null");
            return (Criteria) this;
        }

        public Criteria andExpressIdIsNotNull() {
            addCriterion("express_id is not null");
            return (Criteria) this;
        }

        public Criteria andExpressIdEqualTo(String value) {
            addCriterion("express_id =", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotEqualTo(String value) {
            addCriterion("express_id <>", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdGreaterThan(String value) {
            addCriterion("express_id >", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdGreaterThanOrEqualTo(String value) {
            addCriterion("express_id >=", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLessThan(String value) {
            addCriterion("express_id <", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLessThanOrEqualTo(String value) {
            addCriterion("express_id <=", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLike(String value) {
            addCriterion("express_id like", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotLike(String value) {
            addCriterion("express_id not like", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdIn(List<String> values) {
            addCriterion("express_id in", values, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotIn(List<String> values) {
            addCriterion("express_id not in", values, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdBetween(String value1, String value2) {
            addCriterion("express_id between", value1, value2, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotBetween(String value1, String value2) {
            addCriterion("express_id not between", value1, value2, "expressId");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoIsNull() {
            addCriterion("goods_info is null");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoIsNotNull() {
            addCriterion("goods_info is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoEqualTo(String value) {
            addCriterion("goods_info =", value, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoNotEqualTo(String value) {
            addCriterion("goods_info <>", value, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoGreaterThan(String value) {
            addCriterion("goods_info >", value, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoGreaterThanOrEqualTo(String value) {
            addCriterion("goods_info >=", value, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoLessThan(String value) {
            addCriterion("goods_info <", value, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoLessThanOrEqualTo(String value) {
            addCriterion("goods_info <=", value, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoLike(String value) {
            addCriterion("goods_info like", value, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoNotLike(String value) {
            addCriterion("goods_info not like", value, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoIn(List<String> values) {
            addCriterion("goods_info in", values, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoNotIn(List<String> values) {
            addCriterion("goods_info not in", values, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoBetween(String value1, String value2) {
            addCriterion("goods_info between", value1, value2, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoNotBetween(String value1, String value2) {
            addCriterion("goods_info not between", value1, value2, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightIsNull() {
            addCriterion("goods_weight is null");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightIsNotNull() {
            addCriterion("goods_weight is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightEqualTo(Double value) {
            addCriterion("goods_weight =", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightNotEqualTo(Double value) {
            addCriterion("goods_weight <>", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightGreaterThan(Double value) {
            addCriterion("goods_weight >", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightGreaterThanOrEqualTo(Double value) {
            addCriterion("goods_weight >=", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightLessThan(Double value) {
            addCriterion("goods_weight <", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightLessThanOrEqualTo(Double value) {
            addCriterion("goods_weight <=", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightIn(List<Double> values) {
            addCriterion("goods_weight in", values, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightNotIn(List<Double> values) {
            addCriterion("goods_weight not in", values, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightBetween(Double value1, Double value2) {
            addCriterion("goods_weight between", value1, value2, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightNotBetween(Double value1, Double value2) {
            addCriterion("goods_weight not between", value1, value2, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andExpressOrderIsNull() {
            addCriterion("express_order is null");
            return (Criteria) this;
        }

        public Criteria andExpressOrderIsNotNull() {
            addCriterion("express_order is not null");
            return (Criteria) this;
        }

        public Criteria andExpressOrderEqualTo(String value) {
            addCriterion("express_order =", value, "expressOrder");
            return (Criteria) this;
        }

        public Criteria andExpressOrderNotEqualTo(String value) {
            addCriterion("express_order <>", value, "expressOrder");
            return (Criteria) this;
        }

        public Criteria andExpressOrderGreaterThan(String value) {
            addCriterion("express_order >", value, "expressOrder");
            return (Criteria) this;
        }

        public Criteria andExpressOrderGreaterThanOrEqualTo(String value) {
            addCriterion("express_order >=", value, "expressOrder");
            return (Criteria) this;
        }

        public Criteria andExpressOrderLessThan(String value) {
            addCriterion("express_order <", value, "expressOrder");
            return (Criteria) this;
        }

        public Criteria andExpressOrderLessThanOrEqualTo(String value) {
            addCriterion("express_order <=", value, "expressOrder");
            return (Criteria) this;
        }

        public Criteria andExpressOrderLike(String value) {
            addCriterion("express_order like", value, "expressOrder");
            return (Criteria) this;
        }

        public Criteria andExpressOrderNotLike(String value) {
            addCriterion("express_order not like", value, "expressOrder");
            return (Criteria) this;
        }

        public Criteria andExpressOrderIn(List<String> values) {
            addCriterion("express_order in", values, "expressOrder");
            return (Criteria) this;
        }

        public Criteria andExpressOrderNotIn(List<String> values) {
            addCriterion("express_order not in", values, "expressOrder");
            return (Criteria) this;
        }

        public Criteria andExpressOrderBetween(String value1, String value2) {
            addCriterion("express_order between", value1, value2, "expressOrder");
            return (Criteria) this;
        }

        public Criteria andExpressOrderNotBetween(String value1, String value2) {
            addCriterion("express_order not between", value1, value2, "expressOrder");
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

        public Criteria andDispatcherTypeIsNull() {
            addCriterion("dispatcher_type is null");
            return (Criteria) this;
        }

        public Criteria andDispatcherTypeIsNotNull() {
            addCriterion("dispatcher_type is not null");
            return (Criteria) this;
        }

        public Criteria andDispatcherTypeEqualTo(String value) {
            addCriterion("dispatcher_type =", value, "dispatcherType");
            return (Criteria) this;
        }

        public Criteria andDispatcherTypeNotEqualTo(String value) {
            addCriterion("dispatcher_type <>", value, "dispatcherType");
            return (Criteria) this;
        }

        public Criteria andDispatcherTypeGreaterThan(String value) {
            addCriterion("dispatcher_type >", value, "dispatcherType");
            return (Criteria) this;
        }

        public Criteria andDispatcherTypeGreaterThanOrEqualTo(String value) {
            addCriterion("dispatcher_type >=", value, "dispatcherType");
            return (Criteria) this;
        }

        public Criteria andDispatcherTypeLessThan(String value) {
            addCriterion("dispatcher_type <", value, "dispatcherType");
            return (Criteria) this;
        }

        public Criteria andDispatcherTypeLessThanOrEqualTo(String value) {
            addCriterion("dispatcher_type <=", value, "dispatcherType");
            return (Criteria) this;
        }

        public Criteria andDispatcherTypeLike(String value) {
            addCriterion("dispatcher_type like", value, "dispatcherType");
            return (Criteria) this;
        }

        public Criteria andDispatcherTypeNotLike(String value) {
            addCriterion("dispatcher_type not like", value, "dispatcherType");
            return (Criteria) this;
        }

        public Criteria andDispatcherTypeIn(List<String> values) {
            addCriterion("dispatcher_type in", values, "dispatcherType");
            return (Criteria) this;
        }

        public Criteria andDispatcherTypeNotIn(List<String> values) {
            addCriterion("dispatcher_type not in", values, "dispatcherType");
            return (Criteria) this;
        }

        public Criteria andDispatcherTypeBetween(String value1, String value2) {
            addCriterion("dispatcher_type between", value1, value2, "dispatcherType");
            return (Criteria) this;
        }

        public Criteria andDispatcherTypeNotBetween(String value1, String value2) {
            addCriterion("dispatcher_type not between", value1, value2, "dispatcherType");
            return (Criteria) this;
        }

        public Criteria andTargetAddressIsNull() {
            addCriterion("target_address is null");
            return (Criteria) this;
        }

        public Criteria andTargetAddressIsNotNull() {
            addCriterion("target_address is not null");
            return (Criteria) this;
        }

        public Criteria andTargetAddressEqualTo(String value) {
            addCriterion("target_address =", value, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetAddressNotEqualTo(String value) {
            addCriterion("target_address <>", value, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetAddressGreaterThan(String value) {
            addCriterion("target_address >", value, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetAddressGreaterThanOrEqualTo(String value) {
            addCriterion("target_address >=", value, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetAddressLessThan(String value) {
            addCriterion("target_address <", value, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetAddressLessThanOrEqualTo(String value) {
            addCriterion("target_address <=", value, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetAddressLike(String value) {
            addCriterion("target_address like", value, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetAddressNotLike(String value) {
            addCriterion("target_address not like", value, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetAddressIn(List<String> values) {
            addCriterion("target_address in", values, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetAddressNotIn(List<String> values) {
            addCriterion("target_address not in", values, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetAddressBetween(String value1, String value2) {
            addCriterion("target_address between", value1, value2, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetAddressNotBetween(String value1, String value2) {
            addCriterion("target_address not between", value1, value2, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressIsNull() {
            addCriterion("source_address is null");
            return (Criteria) this;
        }

        public Criteria andSourceAddressIsNotNull() {
            addCriterion("source_address is not null");
            return (Criteria) this;
        }

        public Criteria andSourceAddressEqualTo(String value) {
            addCriterion("source_address =", value, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressNotEqualTo(String value) {
            addCriterion("source_address <>", value, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressGreaterThan(String value) {
            addCriterion("source_address >", value, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressGreaterThanOrEqualTo(String value) {
            addCriterion("source_address >=", value, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressLessThan(String value) {
            addCriterion("source_address <", value, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressLessThanOrEqualTo(String value) {
            addCriterion("source_address <=", value, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressLike(String value) {
            addCriterion("source_address like", value, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressNotLike(String value) {
            addCriterion("source_address not like", value, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressIn(List<String> values) {
            addCriterion("source_address in", values, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressNotIn(List<String> values) {
            addCriterion("source_address not in", values, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressBetween(String value1, String value2) {
            addCriterion("source_address between", value1, value2, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressNotBetween(String value1, String value2) {
            addCriterion("source_address not between", value1, value2, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andExpressIdLikeInsensitive(String value) {
            addCriterion("upper(express_id) like", value.toUpperCase(), "expressId");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoLikeInsensitive(String value) {
            addCriterion("upper(goods_info) like", value.toUpperCase(), "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andExpressOrderLikeInsensitive(String value) {
            addCriterion("upper(express_order) like", value.toUpperCase(), "expressOrder");
            return (Criteria) this;
        }

        public Criteria andProviderNameLikeInsensitive(String value) {
            addCriterion("upper(provider_name) like", value.toUpperCase(), "providerName");
            return (Criteria) this;
        }

        public Criteria andDispatcherTypeLikeInsensitive(String value) {
            addCriterion("upper(dispatcher_type) like", value.toUpperCase(), "dispatcherType");
            return (Criteria) this;
        }

        public Criteria andTargetAddressLikeInsensitive(String value) {
            addCriterion("upper(target_address) like", value.toUpperCase(), "targetAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressLikeInsensitive(String value) {
            addCriterion("upper(source_address) like", value.toUpperCase(), "sourceAddress");
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