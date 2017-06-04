package com.guoan.entity.shequ.bo;

import java.util.ArrayList;
import java.util.List;

public class OrderRehabCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public OrderRehabCriteria() {
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

        public Criteria andRehabIdIsNull() {
            addCriterion("rehab_id is null");
            return (Criteria) this;
        }

        public Criteria andRehabIdIsNotNull() {
            addCriterion("rehab_id is not null");
            return (Criteria) this;
        }

        public Criteria andRehabIdEqualTo(String value) {
            addCriterion("rehab_id =", value, "rehabId");
            return (Criteria) this;
        }

        public Criteria andRehabIdNotEqualTo(String value) {
            addCriterion("rehab_id <>", value, "rehabId");
            return (Criteria) this;
        }

        public Criteria andRehabIdGreaterThan(String value) {
            addCriterion("rehab_id >", value, "rehabId");
            return (Criteria) this;
        }

        public Criteria andRehabIdGreaterThanOrEqualTo(String value) {
            addCriterion("rehab_id >=", value, "rehabId");
            return (Criteria) this;
        }

        public Criteria andRehabIdLessThan(String value) {
            addCriterion("rehab_id <", value, "rehabId");
            return (Criteria) this;
        }

        public Criteria andRehabIdLessThanOrEqualTo(String value) {
            addCriterion("rehab_id <=", value, "rehabId");
            return (Criteria) this;
        }

        public Criteria andRehabIdLike(String value) {
            addCriterion("rehab_id like", value, "rehabId");
            return (Criteria) this;
        }

        public Criteria andRehabIdNotLike(String value) {
            addCriterion("rehab_id not like", value, "rehabId");
            return (Criteria) this;
        }

        public Criteria andRehabIdIn(List<String> values) {
            addCriterion("rehab_id in", values, "rehabId");
            return (Criteria) this;
        }

        public Criteria andRehabIdNotIn(List<String> values) {
            addCriterion("rehab_id not in", values, "rehabId");
            return (Criteria) this;
        }

        public Criteria andRehabIdBetween(String value1, String value2) {
            addCriterion("rehab_id between", value1, value2, "rehabId");
            return (Criteria) this;
        }

        public Criteria andRehabIdNotBetween(String value1, String value2) {
            addCriterion("rehab_id not between", value1, value2, "rehabId");
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

        public Criteria andPackageIdIsNull() {
            addCriterion("package_id is null");
            return (Criteria) this;
        }

        public Criteria andPackageIdIsNotNull() {
            addCriterion("package_id is not null");
            return (Criteria) this;
        }

        public Criteria andPackageIdEqualTo(Long value) {
            addCriterion("package_id =", value, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdNotEqualTo(Long value) {
            addCriterion("package_id <>", value, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdGreaterThan(Long value) {
            addCriterion("package_id >", value, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdGreaterThanOrEqualTo(Long value) {
            addCriterion("package_id >=", value, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdLessThan(Long value) {
            addCriterion("package_id <", value, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdLessThanOrEqualTo(Long value) {
            addCriterion("package_id <=", value, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdIn(List<Long> values) {
            addCriterion("package_id in", values, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdNotIn(List<Long> values) {
            addCriterion("package_id not in", values, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdBetween(Long value1, Long value2) {
            addCriterion("package_id between", value1, value2, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdNotBetween(Long value1, Long value2) {
            addCriterion("package_id not between", value1, value2, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageNameIsNull() {
            addCriterion("package_name is null");
            return (Criteria) this;
        }

        public Criteria andPackageNameIsNotNull() {
            addCriterion("package_name is not null");
            return (Criteria) this;
        }

        public Criteria andPackageNameEqualTo(String value) {
            addCriterion("package_name =", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameNotEqualTo(String value) {
            addCriterion("package_name <>", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameGreaterThan(String value) {
            addCriterion("package_name >", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameGreaterThanOrEqualTo(String value) {
            addCriterion("package_name >=", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameLessThan(String value) {
            addCriterion("package_name <", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameLessThanOrEqualTo(String value) {
            addCriterion("package_name <=", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameLike(String value) {
            addCriterion("package_name like", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameNotLike(String value) {
            addCriterion("package_name not like", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameIn(List<String> values) {
            addCriterion("package_name in", values, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameNotIn(List<String> values) {
            addCriterion("package_name not in", values, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameBetween(String value1, String value2) {
            addCriterion("package_name between", value1, value2, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameNotBetween(String value1, String value2) {
            addCriterion("package_name not between", value1, value2, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackagePriceIdIsNull() {
            addCriterion("package_price_id is null");
            return (Criteria) this;
        }

        public Criteria andPackagePriceIdIsNotNull() {
            addCriterion("package_price_id is not null");
            return (Criteria) this;
        }

        public Criteria andPackagePriceIdEqualTo(Long value) {
            addCriterion("package_price_id =", value, "packagePriceId");
            return (Criteria) this;
        }

        public Criteria andPackagePriceIdNotEqualTo(Long value) {
            addCriterion("package_price_id <>", value, "packagePriceId");
            return (Criteria) this;
        }

        public Criteria andPackagePriceIdGreaterThan(Long value) {
            addCriterion("package_price_id >", value, "packagePriceId");
            return (Criteria) this;
        }

        public Criteria andPackagePriceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("package_price_id >=", value, "packagePriceId");
            return (Criteria) this;
        }

        public Criteria andPackagePriceIdLessThan(Long value) {
            addCriterion("package_price_id <", value, "packagePriceId");
            return (Criteria) this;
        }

        public Criteria andPackagePriceIdLessThanOrEqualTo(Long value) {
            addCriterion("package_price_id <=", value, "packagePriceId");
            return (Criteria) this;
        }

        public Criteria andPackagePriceIdIn(List<Long> values) {
            addCriterion("package_price_id in", values, "packagePriceId");
            return (Criteria) this;
        }

        public Criteria andPackagePriceIdNotIn(List<Long> values) {
            addCriterion("package_price_id not in", values, "packagePriceId");
            return (Criteria) this;
        }

        public Criteria andPackagePriceIdBetween(Long value1, Long value2) {
            addCriterion("package_price_id between", value1, value2, "packagePriceId");
            return (Criteria) this;
        }

        public Criteria andPackagePriceIdNotBetween(Long value1, Long value2) {
            addCriterion("package_price_id not between", value1, value2, "packagePriceId");
            return (Criteria) this;
        }

        public Criteria andPackagePriceNameIsNull() {
            addCriterion("package_price_name is null");
            return (Criteria) this;
        }

        public Criteria andPackagePriceNameIsNotNull() {
            addCriterion("package_price_name is not null");
            return (Criteria) this;
        }

        public Criteria andPackagePriceNameEqualTo(String value) {
            addCriterion("package_price_name =", value, "packagePriceName");
            return (Criteria) this;
        }

        public Criteria andPackagePriceNameNotEqualTo(String value) {
            addCriterion("package_price_name <>", value, "packagePriceName");
            return (Criteria) this;
        }

        public Criteria andPackagePriceNameGreaterThan(String value) {
            addCriterion("package_price_name >", value, "packagePriceName");
            return (Criteria) this;
        }

        public Criteria andPackagePriceNameGreaterThanOrEqualTo(String value) {
            addCriterion("package_price_name >=", value, "packagePriceName");
            return (Criteria) this;
        }

        public Criteria andPackagePriceNameLessThan(String value) {
            addCriterion("package_price_name <", value, "packagePriceName");
            return (Criteria) this;
        }

        public Criteria andPackagePriceNameLessThanOrEqualTo(String value) {
            addCriterion("package_price_name <=", value, "packagePriceName");
            return (Criteria) this;
        }

        public Criteria andPackagePriceNameLike(String value) {
            addCriterion("package_price_name like", value, "packagePriceName");
            return (Criteria) this;
        }

        public Criteria andPackagePriceNameNotLike(String value) {
            addCriterion("package_price_name not like", value, "packagePriceName");
            return (Criteria) this;
        }

        public Criteria andPackagePriceNameIn(List<String> values) {
            addCriterion("package_price_name in", values, "packagePriceName");
            return (Criteria) this;
        }

        public Criteria andPackagePriceNameNotIn(List<String> values) {
            addCriterion("package_price_name not in", values, "packagePriceName");
            return (Criteria) this;
        }

        public Criteria andPackagePriceNameBetween(String value1, String value2) {
            addCriterion("package_price_name between", value1, value2, "packagePriceName");
            return (Criteria) this;
        }

        public Criteria andPackagePriceNameNotBetween(String value1, String value2) {
            addCriterion("package_price_name not between", value1, value2, "packagePriceName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameIsNull() {
            addCriterion("worker_name is null");
            return (Criteria) this;
        }

        public Criteria andWorkerNameIsNotNull() {
            addCriterion("worker_name is not null");
            return (Criteria) this;
        }

        public Criteria andWorkerNameEqualTo(String value) {
            addCriterion("worker_name =", value, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameNotEqualTo(String value) {
            addCriterion("worker_name <>", value, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameGreaterThan(String value) {
            addCriterion("worker_name >", value, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameGreaterThanOrEqualTo(String value) {
            addCriterion("worker_name >=", value, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameLessThan(String value) {
            addCriterion("worker_name <", value, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameLessThanOrEqualTo(String value) {
            addCriterion("worker_name <=", value, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameLike(String value) {
            addCriterion("worker_name like", value, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameNotLike(String value) {
            addCriterion("worker_name not like", value, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameIn(List<String> values) {
            addCriterion("worker_name in", values, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameNotIn(List<String> values) {
            addCriterion("worker_name not in", values, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameBetween(String value1, String value2) {
            addCriterion("worker_name between", value1, value2, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameNotBetween(String value1, String value2) {
            addCriterion("worker_name not between", value1, value2, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerPhoneIsNull() {
            addCriterion("worker_phone is null");
            return (Criteria) this;
        }

        public Criteria andWorkerPhoneIsNotNull() {
            addCriterion("worker_phone is not null");
            return (Criteria) this;
        }

        public Criteria andWorkerPhoneEqualTo(String value) {
            addCriterion("worker_phone =", value, "workerPhone");
            return (Criteria) this;
        }

        public Criteria andWorkerPhoneNotEqualTo(String value) {
            addCriterion("worker_phone <>", value, "workerPhone");
            return (Criteria) this;
        }

        public Criteria andWorkerPhoneGreaterThan(String value) {
            addCriterion("worker_phone >", value, "workerPhone");
            return (Criteria) this;
        }

        public Criteria andWorkerPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("worker_phone >=", value, "workerPhone");
            return (Criteria) this;
        }

        public Criteria andWorkerPhoneLessThan(String value) {
            addCriterion("worker_phone <", value, "workerPhone");
            return (Criteria) this;
        }

        public Criteria andWorkerPhoneLessThanOrEqualTo(String value) {
            addCriterion("worker_phone <=", value, "workerPhone");
            return (Criteria) this;
        }

        public Criteria andWorkerPhoneLike(String value) {
            addCriterion("worker_phone like", value, "workerPhone");
            return (Criteria) this;
        }

        public Criteria andWorkerPhoneNotLike(String value) {
            addCriterion("worker_phone not like", value, "workerPhone");
            return (Criteria) this;
        }

        public Criteria andWorkerPhoneIn(List<String> values) {
            addCriterion("worker_phone in", values, "workerPhone");
            return (Criteria) this;
        }

        public Criteria andWorkerPhoneNotIn(List<String> values) {
            addCriterion("worker_phone not in", values, "workerPhone");
            return (Criteria) this;
        }

        public Criteria andWorkerPhoneBetween(String value1, String value2) {
            addCriterion("worker_phone between", value1, value2, "workerPhone");
            return (Criteria) this;
        }

        public Criteria andWorkerPhoneNotBetween(String value1, String value2) {
            addCriterion("worker_phone not between", value1, value2, "workerPhone");
            return (Criteria) this;
        }

        public Criteria andWorkerAmountIsNull() {
            addCriterion("worker_amount is null");
            return (Criteria) this;
        }

        public Criteria andWorkerAmountIsNotNull() {
            addCriterion("worker_amount is not null");
            return (Criteria) this;
        }

        public Criteria andWorkerAmountEqualTo(Integer value) {
            addCriterion("worker_amount =", value, "workerAmount");
            return (Criteria) this;
        }

        public Criteria andWorkerAmountNotEqualTo(Integer value) {
            addCriterion("worker_amount <>", value, "workerAmount");
            return (Criteria) this;
        }

        public Criteria andWorkerAmountGreaterThan(Integer value) {
            addCriterion("worker_amount >", value, "workerAmount");
            return (Criteria) this;
        }

        public Criteria andWorkerAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("worker_amount >=", value, "workerAmount");
            return (Criteria) this;
        }

        public Criteria andWorkerAmountLessThan(Integer value) {
            addCriterion("worker_amount <", value, "workerAmount");
            return (Criteria) this;
        }

        public Criteria andWorkerAmountLessThanOrEqualTo(Integer value) {
            addCriterion("worker_amount <=", value, "workerAmount");
            return (Criteria) this;
        }

        public Criteria andWorkerAmountIn(List<Integer> values) {
            addCriterion("worker_amount in", values, "workerAmount");
            return (Criteria) this;
        }

        public Criteria andWorkerAmountNotIn(List<Integer> values) {
            addCriterion("worker_amount not in", values, "workerAmount");
            return (Criteria) this;
        }

        public Criteria andWorkerAmountBetween(Integer value1, Integer value2) {
            addCriterion("worker_amount between", value1, value2, "workerAmount");
            return (Criteria) this;
        }

        public Criteria andWorkerAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("worker_amount not between", value1, value2, "workerAmount");
            return (Criteria) this;
        }

        public Criteria andCustomerServicePhoneIsNull() {
            addCriterion("customer_service_phone is null");
            return (Criteria) this;
        }

        public Criteria andCustomerServicePhoneIsNotNull() {
            addCriterion("customer_service_phone is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerServicePhoneEqualTo(String value) {
            addCriterion("customer_service_phone =", value, "customerServicePhone");
            return (Criteria) this;
        }

        public Criteria andCustomerServicePhoneNotEqualTo(String value) {
            addCriterion("customer_service_phone <>", value, "customerServicePhone");
            return (Criteria) this;
        }

        public Criteria andCustomerServicePhoneGreaterThan(String value) {
            addCriterion("customer_service_phone >", value, "customerServicePhone");
            return (Criteria) this;
        }

        public Criteria andCustomerServicePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("customer_service_phone >=", value, "customerServicePhone");
            return (Criteria) this;
        }

        public Criteria andCustomerServicePhoneLessThan(String value) {
            addCriterion("customer_service_phone <", value, "customerServicePhone");
            return (Criteria) this;
        }

        public Criteria andCustomerServicePhoneLessThanOrEqualTo(String value) {
            addCriterion("customer_service_phone <=", value, "customerServicePhone");
            return (Criteria) this;
        }

        public Criteria andCustomerServicePhoneLike(String value) {
            addCriterion("customer_service_phone like", value, "customerServicePhone");
            return (Criteria) this;
        }

        public Criteria andCustomerServicePhoneNotLike(String value) {
            addCriterion("customer_service_phone not like", value, "customerServicePhone");
            return (Criteria) this;
        }

        public Criteria andCustomerServicePhoneIn(List<String> values) {
            addCriterion("customer_service_phone in", values, "customerServicePhone");
            return (Criteria) this;
        }

        public Criteria andCustomerServicePhoneNotIn(List<String> values) {
            addCriterion("customer_service_phone not in", values, "customerServicePhone");
            return (Criteria) this;
        }

        public Criteria andCustomerServicePhoneBetween(String value1, String value2) {
            addCriterion("customer_service_phone between", value1, value2, "customerServicePhone");
            return (Criteria) this;
        }

        public Criteria andCustomerServicePhoneNotBetween(String value1, String value2) {
            addCriterion("customer_service_phone not between", value1, value2, "customerServicePhone");
            return (Criteria) this;
        }

        public Criteria andRehabIdLikeInsensitive(String value) {
            addCriterion("upper(rehab_id) like", value.toUpperCase(), "rehabId");
            return (Criteria) this;
        }

        public Criteria andProviderNameLikeInsensitive(String value) {
            addCriterion("upper(provider_name) like", value.toUpperCase(), "providerName");
            return (Criteria) this;
        }

        public Criteria andPackageNameLikeInsensitive(String value) {
            addCriterion("upper(package_name) like", value.toUpperCase(), "packageName");
            return (Criteria) this;
        }

        public Criteria andPackagePriceNameLikeInsensitive(String value) {
            addCriterion("upper(package_price_name) like", value.toUpperCase(), "packagePriceName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameLikeInsensitive(String value) {
            addCriterion("upper(worker_name) like", value.toUpperCase(), "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerPhoneLikeInsensitive(String value) {
            addCriterion("upper(worker_phone) like", value.toUpperCase(), "workerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerServicePhoneLikeInsensitive(String value) {
            addCriterion("upper(customer_service_phone) like", value.toUpperCase(), "customerServicePhone");
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