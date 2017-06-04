package com.guoan.entity.shequ.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public OrderCriteria() {
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

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("order_id like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("order_id not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderSnIsNull() {
            addCriterion("order_sn is null");
            return (Criteria) this;
        }

        public Criteria andOrderSnIsNotNull() {
            addCriterion("order_sn is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSnEqualTo(String value) {
            addCriterion("order_sn =", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnNotEqualTo(String value) {
            addCriterion("order_sn <>", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnGreaterThan(String value) {
            addCriterion("order_sn >", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnGreaterThanOrEqualTo(String value) {
            addCriterion("order_sn >=", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnLessThan(String value) {
            addCriterion("order_sn <", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnLessThanOrEqualTo(String value) {
            addCriterion("order_sn <=", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnLike(String value) {
            addCriterion("order_sn like", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnNotLike(String value) {
            addCriterion("order_sn not like", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnIn(List<String> values) {
            addCriterion("order_sn in", values, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnNotIn(List<String> values) {
            addCriterion("order_sn not in", values, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnBetween(String value1, String value2) {
            addCriterion("order_sn between", value1, value2, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnNotBetween(String value1, String value2) {
            addCriterion("order_sn not between", value1, value2, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderTelephoneIsNull() {
            addCriterion("order_telephone is null");
            return (Criteria) this;
        }

        public Criteria andOrderTelephoneIsNotNull() {
            addCriterion("order_telephone is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTelephoneEqualTo(String value) {
            addCriterion("order_telephone =", value, "orderTelephone");
            return (Criteria) this;
        }

        public Criteria andOrderTelephoneNotEqualTo(String value) {
            addCriterion("order_telephone <>", value, "orderTelephone");
            return (Criteria) this;
        }

        public Criteria andOrderTelephoneGreaterThan(String value) {
            addCriterion("order_telephone >", value, "orderTelephone");
            return (Criteria) this;
        }

        public Criteria andOrderTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("order_telephone >=", value, "orderTelephone");
            return (Criteria) this;
        }

        public Criteria andOrderTelephoneLessThan(String value) {
            addCriterion("order_telephone <", value, "orderTelephone");
            return (Criteria) this;
        }

        public Criteria andOrderTelephoneLessThanOrEqualTo(String value) {
            addCriterion("order_telephone <=", value, "orderTelephone");
            return (Criteria) this;
        }

        public Criteria andOrderTelephoneLike(String value) {
            addCriterion("order_telephone like", value, "orderTelephone");
            return (Criteria) this;
        }

        public Criteria andOrderTelephoneNotLike(String value) {
            addCriterion("order_telephone not like", value, "orderTelephone");
            return (Criteria) this;
        }

        public Criteria andOrderTelephoneIn(List<String> values) {
            addCriterion("order_telephone in", values, "orderTelephone");
            return (Criteria) this;
        }

        public Criteria andOrderTelephoneNotIn(List<String> values) {
            addCriterion("order_telephone not in", values, "orderTelephone");
            return (Criteria) this;
        }

        public Criteria andOrderTelephoneBetween(String value1, String value2) {
            addCriterion("order_telephone between", value1, value2, "orderTelephone");
            return (Criteria) this;
        }

        public Criteria andOrderTelephoneNotBetween(String value1, String value2) {
            addCriterion("order_telephone not between", value1, value2, "orderTelephone");
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

        public Criteria andOrderContactIsNull() {
            addCriterion("order_contact is null");
            return (Criteria) this;
        }

        public Criteria andOrderContactIsNotNull() {
            addCriterion("order_contact is not null");
            return (Criteria) this;
        }

        public Criteria andOrderContactEqualTo(String value) {
            addCriterion("order_contact =", value, "orderContact");
            return (Criteria) this;
        }

        public Criteria andOrderContactNotEqualTo(String value) {
            addCriterion("order_contact <>", value, "orderContact");
            return (Criteria) this;
        }

        public Criteria andOrderContactGreaterThan(String value) {
            addCriterion("order_contact >", value, "orderContact");
            return (Criteria) this;
        }

        public Criteria andOrderContactGreaterThanOrEqualTo(String value) {
            addCriterion("order_contact >=", value, "orderContact");
            return (Criteria) this;
        }

        public Criteria andOrderContactLessThan(String value) {
            addCriterion("order_contact <", value, "orderContact");
            return (Criteria) this;
        }

        public Criteria andOrderContactLessThanOrEqualTo(String value) {
            addCriterion("order_contact <=", value, "orderContact");
            return (Criteria) this;
        }

        public Criteria andOrderContactLike(String value) {
            addCriterion("order_contact like", value, "orderContact");
            return (Criteria) this;
        }

        public Criteria andOrderContactNotLike(String value) {
            addCriterion("order_contact not like", value, "orderContact");
            return (Criteria) this;
        }

        public Criteria andOrderContactIn(List<String> values) {
            addCriterion("order_contact in", values, "orderContact");
            return (Criteria) this;
        }

        public Criteria andOrderContactNotIn(List<String> values) {
            addCriterion("order_contact not in", values, "orderContact");
            return (Criteria) this;
        }

        public Criteria andOrderContactBetween(String value1, String value2) {
            addCriterion("order_contact between", value1, value2, "orderContact");
            return (Criteria) this;
        }

        public Criteria andOrderContactNotBetween(String value1, String value2) {
            addCriterion("order_contact not between", value1, value2, "orderContact");
            return (Criteria) this;
        }

        public Criteria andOrderAddressIsNull() {
            addCriterion("order_address is null");
            return (Criteria) this;
        }

        public Criteria andOrderAddressIsNotNull() {
            addCriterion("order_address is not null");
            return (Criteria) this;
        }

        public Criteria andOrderAddressEqualTo(String value) {
            addCriterion("order_address =", value, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderAddressNotEqualTo(String value) {
            addCriterion("order_address <>", value, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderAddressGreaterThan(String value) {
            addCriterion("order_address >", value, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderAddressGreaterThanOrEqualTo(String value) {
            addCriterion("order_address >=", value, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderAddressLessThan(String value) {
            addCriterion("order_address <", value, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderAddressLessThanOrEqualTo(String value) {
            addCriterion("order_address <=", value, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderAddressLike(String value) {
            addCriterion("order_address like", value, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderAddressNotLike(String value) {
            addCriterion("order_address not like", value, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderAddressIn(List<String> values) {
            addCriterion("order_address in", values, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderAddressNotIn(List<String> values) {
            addCriterion("order_address not in", values, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderAddressBetween(String value1, String value2) {
            addCriterion("order_address between", value1, value2, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderAddressNotBetween(String value1, String value2) {
            addCriterion("order_address not between", value1, value2, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkIsNull() {
            addCriterion("order_remark is null");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkIsNotNull() {
            addCriterion("order_remark is not null");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkEqualTo(String value) {
            addCriterion("order_remark =", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkNotEqualTo(String value) {
            addCriterion("order_remark <>", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkGreaterThan(String value) {
            addCriterion("order_remark >", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("order_remark >=", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkLessThan(String value) {
            addCriterion("order_remark <", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkLessThanOrEqualTo(String value) {
            addCriterion("order_remark <=", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkLike(String value) {
            addCriterion("order_remark like", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkNotLike(String value) {
            addCriterion("order_remark not like", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkIn(List<String> values) {
            addCriterion("order_remark in", values, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkNotIn(List<String> values) {
            addCriterion("order_remark not in", values, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkBetween(String value1, String value2) {
            addCriterion("order_remark between", value1, value2, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkNotBetween(String value1, String value2) {
            addCriterion("order_remark not between", value1, value2, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeIsNull() {
            addCriterion("appointment_begin_time is null");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeIsNotNull() {
            addCriterion("appointment_begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeEqualTo(Date value) {
            addCriterion("appointment_begin_time =", value, "appointmentBeginTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeNotEqualTo(Date value) {
            addCriterion("appointment_begin_time <>", value, "appointmentBeginTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeGreaterThan(Date value) {
            addCriterion("appointment_begin_time >", value, "appointmentBeginTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("appointment_begin_time >=", value, "appointmentBeginTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeLessThan(Date value) {
            addCriterion("appointment_begin_time <", value, "appointmentBeginTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("appointment_begin_time <=", value, "appointmentBeginTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeIn(List<Date> values) {
            addCriterion("appointment_begin_time in", values, "appointmentBeginTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeNotIn(List<Date> values) {
            addCriterion("appointment_begin_time not in", values, "appointmentBeginTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeBetween(Date value1, Date value2) {
            addCriterion("appointment_begin_time between", value1, value2, "appointmentBeginTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("appointment_begin_time not between", value1, value2, "appointmentBeginTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeIsNull() {
            addCriterion("appointment_end_time is null");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeIsNotNull() {
            addCriterion("appointment_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeEqualTo(Date value) {
            addCriterion("appointment_end_time =", value, "appointmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeNotEqualTo(Date value) {
            addCriterion("appointment_end_time <>", value, "appointmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeGreaterThan(Date value) {
            addCriterion("appointment_end_time >", value, "appointmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("appointment_end_time >=", value, "appointmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeLessThan(Date value) {
            addCriterion("appointment_end_time <", value, "appointmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("appointment_end_time <=", value, "appointmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeIn(List<Date> values) {
            addCriterion("appointment_end_time in", values, "appointmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeNotIn(List<Date> values) {
            addCriterion("appointment_end_time not in", values, "appointmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeBetween(Date value1, Date value2) {
            addCriterion("appointment_end_time between", value1, value2, "appointmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("appointment_end_time not between", value1, value2, "appointmentEndTime");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNull() {
            addCriterion("order_type is null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNotNull() {
            addCriterion("order_type is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeEqualTo(Integer value) {
            addCriterion("order_type =", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotEqualTo(Integer value) {
            addCriterion("order_type <>", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThan(Integer value) {
            addCriterion("order_type >", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_type >=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThan(Integer value) {
            addCriterion("order_type <", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThanOrEqualTo(Integer value) {
            addCriterion("order_type <=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIn(List<Integer> values) {
            addCriterion("order_type in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotIn(List<Integer> values) {
            addCriterion("order_type not in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeBetween(Integer value1, Integer value2) {
            addCriterion("order_type between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("order_type not between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andServiceUserIdIsNull() {
            addCriterion("service_user_id is null");
            return (Criteria) this;
        }

        public Criteria andServiceUserIdIsNotNull() {
            addCriterion("service_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andServiceUserIdEqualTo(String value) {
            addCriterion("service_user_id =", value, "serviceUserId");
            return (Criteria) this;
        }

        public Criteria andServiceUserIdNotEqualTo(String value) {
            addCriterion("service_user_id <>", value, "serviceUserId");
            return (Criteria) this;
        }

        public Criteria andServiceUserIdGreaterThan(String value) {
            addCriterion("service_user_id >", value, "serviceUserId");
            return (Criteria) this;
        }

        public Criteria andServiceUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("service_user_id >=", value, "serviceUserId");
            return (Criteria) this;
        }

        public Criteria andServiceUserIdLessThan(String value) {
            addCriterion("service_user_id <", value, "serviceUserId");
            return (Criteria) this;
        }

        public Criteria andServiceUserIdLessThanOrEqualTo(String value) {
            addCriterion("service_user_id <=", value, "serviceUserId");
            return (Criteria) this;
        }

        public Criteria andServiceUserIdLike(String value) {
            addCriterion("service_user_id like", value, "serviceUserId");
            return (Criteria) this;
        }

        public Criteria andServiceUserIdNotLike(String value) {
            addCriterion("service_user_id not like", value, "serviceUserId");
            return (Criteria) this;
        }

        public Criteria andServiceUserIdIn(List<String> values) {
            addCriterion("service_user_id in", values, "serviceUserId");
            return (Criteria) this;
        }

        public Criteria andServiceUserIdNotIn(List<String> values) {
            addCriterion("service_user_id not in", values, "serviceUserId");
            return (Criteria) this;
        }

        public Criteria andServiceUserIdBetween(String value1, String value2) {
            addCriterion("service_user_id between", value1, value2, "serviceUserId");
            return (Criteria) this;
        }

        public Criteria andServiceUserIdNotBetween(String value1, String value2) {
            addCriterion("service_user_id not between", value1, value2, "serviceUserId");
            return (Criteria) this;
        }

        public Criteria andServiceTelephoneIsNull() {
            addCriterion("service_telephone is null");
            return (Criteria) this;
        }

        public Criteria andServiceTelephoneIsNotNull() {
            addCriterion("service_telephone is not null");
            return (Criteria) this;
        }

        public Criteria andServiceTelephoneEqualTo(String value) {
            addCriterion("service_telephone =", value, "serviceTelephone");
            return (Criteria) this;
        }

        public Criteria andServiceTelephoneNotEqualTo(String value) {
            addCriterion("service_telephone <>", value, "serviceTelephone");
            return (Criteria) this;
        }

        public Criteria andServiceTelephoneGreaterThan(String value) {
            addCriterion("service_telephone >", value, "serviceTelephone");
            return (Criteria) this;
        }

        public Criteria andServiceTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("service_telephone >=", value, "serviceTelephone");
            return (Criteria) this;
        }

        public Criteria andServiceTelephoneLessThan(String value) {
            addCriterion("service_telephone <", value, "serviceTelephone");
            return (Criteria) this;
        }

        public Criteria andServiceTelephoneLessThanOrEqualTo(String value) {
            addCriterion("service_telephone <=", value, "serviceTelephone");
            return (Criteria) this;
        }

        public Criteria andServiceTelephoneLike(String value) {
            addCriterion("service_telephone like", value, "serviceTelephone");
            return (Criteria) this;
        }

        public Criteria andServiceTelephoneNotLike(String value) {
            addCriterion("service_telephone not like", value, "serviceTelephone");
            return (Criteria) this;
        }

        public Criteria andServiceTelephoneIn(List<String> values) {
            addCriterion("service_telephone in", values, "serviceTelephone");
            return (Criteria) this;
        }

        public Criteria andServiceTelephoneNotIn(List<String> values) {
            addCriterion("service_telephone not in", values, "serviceTelephone");
            return (Criteria) this;
        }

        public Criteria andServiceTelephoneBetween(String value1, String value2) {
            addCriterion("service_telephone between", value1, value2, "serviceTelephone");
            return (Criteria) this;
        }

        public Criteria andServiceTelephoneNotBetween(String value1, String value2) {
            addCriterion("service_telephone not between", value1, value2, "serviceTelephone");
            return (Criteria) this;
        }

        public Criteria andServiceContactIsNull() {
            addCriterion("service_contact is null");
            return (Criteria) this;
        }

        public Criteria andServiceContactIsNotNull() {
            addCriterion("service_contact is not null");
            return (Criteria) this;
        }

        public Criteria andServiceContactEqualTo(String value) {
            addCriterion("service_contact =", value, "serviceContact");
            return (Criteria) this;
        }

        public Criteria andServiceContactNotEqualTo(String value) {
            addCriterion("service_contact <>", value, "serviceContact");
            return (Criteria) this;
        }

        public Criteria andServiceContactGreaterThan(String value) {
            addCriterion("service_contact >", value, "serviceContact");
            return (Criteria) this;
        }

        public Criteria andServiceContactGreaterThanOrEqualTo(String value) {
            addCriterion("service_contact >=", value, "serviceContact");
            return (Criteria) this;
        }

        public Criteria andServiceContactLessThan(String value) {
            addCriterion("service_contact <", value, "serviceContact");
            return (Criteria) this;
        }

        public Criteria andServiceContactLessThanOrEqualTo(String value) {
            addCriterion("service_contact <=", value, "serviceContact");
            return (Criteria) this;
        }

        public Criteria andServiceContactLike(String value) {
            addCriterion("service_contact like", value, "serviceContact");
            return (Criteria) this;
        }

        public Criteria andServiceContactNotLike(String value) {
            addCriterion("service_contact not like", value, "serviceContact");
            return (Criteria) this;
        }

        public Criteria andServiceContactIn(List<String> values) {
            addCriterion("service_contact in", values, "serviceContact");
            return (Criteria) this;
        }

        public Criteria andServiceContactNotIn(List<String> values) {
            addCriterion("service_contact not in", values, "serviceContact");
            return (Criteria) this;
        }

        public Criteria andServiceContactBetween(String value1, String value2) {
            addCriterion("service_contact between", value1, value2, "serviceContact");
            return (Criteria) this;
        }

        public Criteria andServiceContactNotBetween(String value1, String value2) {
            addCriterion("service_contact not between", value1, value2, "serviceContact");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIsNull() {
            addCriterion("service_type is null");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIsNotNull() {
            addCriterion("service_type is not null");
            return (Criteria) this;
        }

        public Criteria andServiceTypeEqualTo(String value) {
            addCriterion("service_type =", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotEqualTo(String value) {
            addCriterion("service_type <>", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeGreaterThan(String value) {
            addCriterion("service_type >", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("service_type >=", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLessThan(String value) {
            addCriterion("service_type <", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLessThanOrEqualTo(String value) {
            addCriterion("service_type <=", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLike(String value) {
            addCriterion("service_type like", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotLike(String value) {
            addCriterion("service_type not like", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIn(List<String> values) {
            addCriterion("service_type in", values, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotIn(List<String> values) {
            addCriterion("service_type not in", values, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeBetween(String value1, String value2) {
            addCriterion("service_type between", value1, value2, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotBetween(String value1, String value2) {
            addCriterion("service_type not between", value1, value2, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceRemarkIsNull() {
            addCriterion("service_remark is null");
            return (Criteria) this;
        }

        public Criteria andServiceRemarkIsNotNull() {
            addCriterion("service_remark is not null");
            return (Criteria) this;
        }

        public Criteria andServiceRemarkEqualTo(String value) {
            addCriterion("service_remark =", value, "serviceRemark");
            return (Criteria) this;
        }

        public Criteria andServiceRemarkNotEqualTo(String value) {
            addCriterion("service_remark <>", value, "serviceRemark");
            return (Criteria) this;
        }

        public Criteria andServiceRemarkGreaterThan(String value) {
            addCriterion("service_remark >", value, "serviceRemark");
            return (Criteria) this;
        }

        public Criteria andServiceRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("service_remark >=", value, "serviceRemark");
            return (Criteria) this;
        }

        public Criteria andServiceRemarkLessThan(String value) {
            addCriterion("service_remark <", value, "serviceRemark");
            return (Criteria) this;
        }

        public Criteria andServiceRemarkLessThanOrEqualTo(String value) {
            addCriterion("service_remark <=", value, "serviceRemark");
            return (Criteria) this;
        }

        public Criteria andServiceRemarkLike(String value) {
            addCriterion("service_remark like", value, "serviceRemark");
            return (Criteria) this;
        }

        public Criteria andServiceRemarkNotLike(String value) {
            addCriterion("service_remark not like", value, "serviceRemark");
            return (Criteria) this;
        }

        public Criteria andServiceRemarkIn(List<String> values) {
            addCriterion("service_remark in", values, "serviceRemark");
            return (Criteria) this;
        }

        public Criteria andServiceRemarkNotIn(List<String> values) {
            addCriterion("service_remark not in", values, "serviceRemark");
            return (Criteria) this;
        }

        public Criteria andServiceRemarkBetween(String value1, String value2) {
            addCriterion("service_remark between", value1, value2, "serviceRemark");
            return (Criteria) this;
        }

        public Criteria andServiceRemarkNotBetween(String value1, String value2) {
            addCriterion("service_remark not between", value1, value2, "serviceRemark");
            return (Criteria) this;
        }

        public Criteria andCouponIdIsNull() {
            addCriterion("coupon_id is null");
            return (Criteria) this;
        }

        public Criteria andCouponIdIsNotNull() {
            addCriterion("coupon_id is not null");
            return (Criteria) this;
        }

        public Criteria andCouponIdEqualTo(String value) {
            addCriterion("coupon_id =", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotEqualTo(String value) {
            addCriterion("coupon_id <>", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThan(String value) {
            addCriterion("coupon_id >", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_id >=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThan(String value) {
            addCriterion("coupon_id <", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThanOrEqualTo(String value) {
            addCriterion("coupon_id <=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLike(String value) {
            addCriterion("coupon_id like", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotLike(String value) {
            addCriterion("coupon_id not like", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdIn(List<String> values) {
            addCriterion("coupon_id in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotIn(List<String> values) {
            addCriterion("coupon_id not in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdBetween(String value1, String value2) {
            addCriterion("coupon_id between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotBetween(String value1, String value2) {
            addCriterion("coupon_id not between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIsNull() {
            addCriterion("order_amount is null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIsNotNull() {
            addCriterion("order_amount is not null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountEqualTo(Double value) {
            addCriterion("order_amount =", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotEqualTo(Double value) {
            addCriterion("order_amount <>", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountGreaterThan(Double value) {
            addCriterion("order_amount >", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("order_amount >=", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountLessThan(Double value) {
            addCriterion("order_amount <", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountLessThanOrEqualTo(Double value) {
            addCriterion("order_amount <=", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIn(List<Double> values) {
            addCriterion("order_amount in", values, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotIn(List<Double> values) {
            addCriterion("order_amount not in", values, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountBetween(Double value1, Double value2) {
            addCriterion("order_amount between", value1, value2, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotBetween(Double value1, Double value2) {
            addCriterion("order_amount not between", value1, value2, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderReduceIsNull() {
            addCriterion("order_reduce is null");
            return (Criteria) this;
        }

        public Criteria andOrderReduceIsNotNull() {
            addCriterion("order_reduce is not null");
            return (Criteria) this;
        }

        public Criteria andOrderReduceEqualTo(Double value) {
            addCriterion("order_reduce =", value, "orderReduce");
            return (Criteria) this;
        }

        public Criteria andOrderReduceNotEqualTo(Double value) {
            addCriterion("order_reduce <>", value, "orderReduce");
            return (Criteria) this;
        }

        public Criteria andOrderReduceGreaterThan(Double value) {
            addCriterion("order_reduce >", value, "orderReduce");
            return (Criteria) this;
        }

        public Criteria andOrderReduceGreaterThanOrEqualTo(Double value) {
            addCriterion("order_reduce >=", value, "orderReduce");
            return (Criteria) this;
        }

        public Criteria andOrderReduceLessThan(Double value) {
            addCriterion("order_reduce <", value, "orderReduce");
            return (Criteria) this;
        }

        public Criteria andOrderReduceLessThanOrEqualTo(Double value) {
            addCriterion("order_reduce <=", value, "orderReduce");
            return (Criteria) this;
        }

        public Criteria andOrderReduceIn(List<Double> values) {
            addCriterion("order_reduce in", values, "orderReduce");
            return (Criteria) this;
        }

        public Criteria andOrderReduceNotIn(List<Double> values) {
            addCriterion("order_reduce not in", values, "orderReduce");
            return (Criteria) this;
        }

        public Criteria andOrderReduceBetween(Double value1, Double value2) {
            addCriterion("order_reduce between", value1, value2, "orderReduce");
            return (Criteria) this;
        }

        public Criteria andOrderReduceNotBetween(Double value1, Double value2) {
            addCriterion("order_reduce not between", value1, value2, "orderReduce");
            return (Criteria) this;
        }

        public Criteria andOrderPaidIsNull() {
            addCriterion("order_paid is null");
            return (Criteria) this;
        }

        public Criteria andOrderPaidIsNotNull() {
            addCriterion("order_paid is not null");
            return (Criteria) this;
        }

        public Criteria andOrderPaidEqualTo(Double value) {
            addCriterion("order_paid =", value, "orderPaid");
            return (Criteria) this;
        }

        public Criteria andOrderPaidNotEqualTo(Double value) {
            addCriterion("order_paid <>", value, "orderPaid");
            return (Criteria) this;
        }

        public Criteria andOrderPaidGreaterThan(Double value) {
            addCriterion("order_paid >", value, "orderPaid");
            return (Criteria) this;
        }

        public Criteria andOrderPaidGreaterThanOrEqualTo(Double value) {
            addCriterion("order_paid >=", value, "orderPaid");
            return (Criteria) this;
        }

        public Criteria andOrderPaidLessThan(Double value) {
            addCriterion("order_paid <", value, "orderPaid");
            return (Criteria) this;
        }

        public Criteria andOrderPaidLessThanOrEqualTo(Double value) {
            addCriterion("order_paid <=", value, "orderPaid");
            return (Criteria) this;
        }

        public Criteria andOrderPaidIn(List<Double> values) {
            addCriterion("order_paid in", values, "orderPaid");
            return (Criteria) this;
        }

        public Criteria andOrderPaidNotIn(List<Double> values) {
            addCriterion("order_paid not in", values, "orderPaid");
            return (Criteria) this;
        }

        public Criteria andOrderPaidBetween(Double value1, Double value2) {
            addCriterion("order_paid between", value1, value2, "orderPaid");
            return (Criteria) this;
        }

        public Criteria andOrderPaidNotBetween(Double value1, Double value2) {
            addCriterion("order_paid not between", value1, value2, "orderPaid");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNull() {
            addCriterion("order_status is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNotNull() {
            addCriterion("order_status is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusEqualTo(Integer value) {
            addCriterion("order_status =", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotEqualTo(Integer value) {
            addCriterion("order_status <>", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThan(Integer value) {
            addCriterion("order_status >", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_status >=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThan(Integer value) {
            addCriterion("order_status <", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThanOrEqualTo(Integer value) {
            addCriterion("order_status <=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIn(List<Integer> values) {
            addCriterion("order_status in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotIn(List<Integer> values) {
            addCriterion("order_status not in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusBetween(Integer value1, Integer value2) {
            addCriterion("order_status between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("order_status not between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andFlowStatusIsNull() {
            addCriterion("flow_status is null");
            return (Criteria) this;
        }

        public Criteria andFlowStatusIsNotNull() {
            addCriterion("flow_status is not null");
            return (Criteria) this;
        }

        public Criteria andFlowStatusEqualTo(Integer value) {
            addCriterion("flow_status =", value, "flowStatus");
            return (Criteria) this;
        }

        public Criteria andFlowStatusNotEqualTo(Integer value) {
            addCriterion("flow_status <>", value, "flowStatus");
            return (Criteria) this;
        }

        public Criteria andFlowStatusGreaterThan(Integer value) {
            addCriterion("flow_status >", value, "flowStatus");
            return (Criteria) this;
        }

        public Criteria andFlowStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("flow_status >=", value, "flowStatus");
            return (Criteria) this;
        }

        public Criteria andFlowStatusLessThan(Integer value) {
            addCriterion("flow_status <", value, "flowStatus");
            return (Criteria) this;
        }

        public Criteria andFlowStatusLessThanOrEqualTo(Integer value) {
            addCriterion("flow_status <=", value, "flowStatus");
            return (Criteria) this;
        }

        public Criteria andFlowStatusIn(List<Integer> values) {
            addCriterion("flow_status in", values, "flowStatus");
            return (Criteria) this;
        }

        public Criteria andFlowStatusNotIn(List<Integer> values) {
            addCriterion("flow_status not in", values, "flowStatus");
            return (Criteria) this;
        }

        public Criteria andFlowStatusBetween(Integer value1, Integer value2) {
            addCriterion("flow_status between", value1, value2, "flowStatus");
            return (Criteria) this;
        }

        public Criteria andFlowStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("flow_status not between", value1, value2, "flowStatus");
            return (Criteria) this;
        }

        public Criteria andFlowStatusNameIsNull() {
            addCriterion("flow_status_name is null");
            return (Criteria) this;
        }

        public Criteria andFlowStatusNameIsNotNull() {
            addCriterion("flow_status_name is not null");
            return (Criteria) this;
        }

        public Criteria andFlowStatusNameEqualTo(String value) {
            addCriterion("flow_status_name =", value, "flowStatusName");
            return (Criteria) this;
        }

        public Criteria andFlowStatusNameNotEqualTo(String value) {
            addCriterion("flow_status_name <>", value, "flowStatusName");
            return (Criteria) this;
        }

        public Criteria andFlowStatusNameGreaterThan(String value) {
            addCriterion("flow_status_name >", value, "flowStatusName");
            return (Criteria) this;
        }

        public Criteria andFlowStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("flow_status_name >=", value, "flowStatusName");
            return (Criteria) this;
        }

        public Criteria andFlowStatusNameLessThan(String value) {
            addCriterion("flow_status_name <", value, "flowStatusName");
            return (Criteria) this;
        }

        public Criteria andFlowStatusNameLessThanOrEqualTo(String value) {
            addCriterion("flow_status_name <=", value, "flowStatusName");
            return (Criteria) this;
        }

        public Criteria andFlowStatusNameLike(String value) {
            addCriterion("flow_status_name like", value, "flowStatusName");
            return (Criteria) this;
        }

        public Criteria andFlowStatusNameNotLike(String value) {
            addCriterion("flow_status_name not like", value, "flowStatusName");
            return (Criteria) this;
        }

        public Criteria andFlowStatusNameIn(List<String> values) {
            addCriterion("flow_status_name in", values, "flowStatusName");
            return (Criteria) this;
        }

        public Criteria andFlowStatusNameNotIn(List<String> values) {
            addCriterion("flow_status_name not in", values, "flowStatusName");
            return (Criteria) this;
        }

        public Criteria andFlowStatusNameBetween(String value1, String value2) {
            addCriterion("flow_status_name between", value1, value2, "flowStatusName");
            return (Criteria) this;
        }

        public Criteria andFlowStatusNameNotBetween(String value1, String value2) {
            addCriterion("flow_status_name not between", value1, value2, "flowStatusName");
            return (Criteria) this;
        }

        public Criteria andIsEvaluationIsNull() {
            addCriterion("is_evaluation is null");
            return (Criteria) this;
        }

        public Criteria andIsEvaluationIsNotNull() {
            addCriterion("is_evaluation is not null");
            return (Criteria) this;
        }

        public Criteria andIsEvaluationEqualTo(Integer value) {
            addCriterion("is_evaluation =", value, "isEvaluation");
            return (Criteria) this;
        }

        public Criteria andIsEvaluationNotEqualTo(Integer value) {
            addCriterion("is_evaluation <>", value, "isEvaluation");
            return (Criteria) this;
        }

        public Criteria andIsEvaluationGreaterThan(Integer value) {
            addCriterion("is_evaluation >", value, "isEvaluation");
            return (Criteria) this;
        }

        public Criteria andIsEvaluationGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_evaluation >=", value, "isEvaluation");
            return (Criteria) this;
        }

        public Criteria andIsEvaluationLessThan(Integer value) {
            addCriterion("is_evaluation <", value, "isEvaluation");
            return (Criteria) this;
        }

        public Criteria andIsEvaluationLessThanOrEqualTo(Integer value) {
            addCriterion("is_evaluation <=", value, "isEvaluation");
            return (Criteria) this;
        }

        public Criteria andIsEvaluationIn(List<Integer> values) {
            addCriterion("is_evaluation in", values, "isEvaluation");
            return (Criteria) this;
        }

        public Criteria andIsEvaluationNotIn(List<Integer> values) {
            addCriterion("is_evaluation not in", values, "isEvaluation");
            return (Criteria) this;
        }

        public Criteria andIsEvaluationBetween(Integer value1, Integer value2) {
            addCriterion("is_evaluation between", value1, value2, "isEvaluation");
            return (Criteria) this;
        }

        public Criteria andIsEvaluationNotBetween(Integer value1, Integer value2) {
            addCriterion("is_evaluation not between", value1, value2, "isEvaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationAttitudeIsNull() {
            addCriterion("evaluation_attitude is null");
            return (Criteria) this;
        }

        public Criteria andEvaluationAttitudeIsNotNull() {
            addCriterion("evaluation_attitude is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluationAttitudeEqualTo(Integer value) {
            addCriterion("evaluation_attitude =", value, "evaluationAttitude");
            return (Criteria) this;
        }

        public Criteria andEvaluationAttitudeNotEqualTo(Integer value) {
            addCriterion("evaluation_attitude <>", value, "evaluationAttitude");
            return (Criteria) this;
        }

        public Criteria andEvaluationAttitudeGreaterThan(Integer value) {
            addCriterion("evaluation_attitude >", value, "evaluationAttitude");
            return (Criteria) this;
        }

        public Criteria andEvaluationAttitudeGreaterThanOrEqualTo(Integer value) {
            addCriterion("evaluation_attitude >=", value, "evaluationAttitude");
            return (Criteria) this;
        }

        public Criteria andEvaluationAttitudeLessThan(Integer value) {
            addCriterion("evaluation_attitude <", value, "evaluationAttitude");
            return (Criteria) this;
        }

        public Criteria andEvaluationAttitudeLessThanOrEqualTo(Integer value) {
            addCriterion("evaluation_attitude <=", value, "evaluationAttitude");
            return (Criteria) this;
        }

        public Criteria andEvaluationAttitudeIn(List<Integer> values) {
            addCriterion("evaluation_attitude in", values, "evaluationAttitude");
            return (Criteria) this;
        }

        public Criteria andEvaluationAttitudeNotIn(List<Integer> values) {
            addCriterion("evaluation_attitude not in", values, "evaluationAttitude");
            return (Criteria) this;
        }

        public Criteria andEvaluationAttitudeBetween(Integer value1, Integer value2) {
            addCriterion("evaluation_attitude between", value1, value2, "evaluationAttitude");
            return (Criteria) this;
        }

        public Criteria andEvaluationAttitudeNotBetween(Integer value1, Integer value2) {
            addCriterion("evaluation_attitude not between", value1, value2, "evaluationAttitude");
            return (Criteria) this;
        }

        public Criteria andEvaluationQualityIsNull() {
            addCriterion("evaluation_quality is null");
            return (Criteria) this;
        }

        public Criteria andEvaluationQualityIsNotNull() {
            addCriterion("evaluation_quality is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluationQualityEqualTo(Integer value) {
            addCriterion("evaluation_quality =", value, "evaluationQuality");
            return (Criteria) this;
        }

        public Criteria andEvaluationQualityNotEqualTo(Integer value) {
            addCriterion("evaluation_quality <>", value, "evaluationQuality");
            return (Criteria) this;
        }

        public Criteria andEvaluationQualityGreaterThan(Integer value) {
            addCriterion("evaluation_quality >", value, "evaluationQuality");
            return (Criteria) this;
        }

        public Criteria andEvaluationQualityGreaterThanOrEqualTo(Integer value) {
            addCriterion("evaluation_quality >=", value, "evaluationQuality");
            return (Criteria) this;
        }

        public Criteria andEvaluationQualityLessThan(Integer value) {
            addCriterion("evaluation_quality <", value, "evaluationQuality");
            return (Criteria) this;
        }

        public Criteria andEvaluationQualityLessThanOrEqualTo(Integer value) {
            addCriterion("evaluation_quality <=", value, "evaluationQuality");
            return (Criteria) this;
        }

        public Criteria andEvaluationQualityIn(List<Integer> values) {
            addCriterion("evaluation_quality in", values, "evaluationQuality");
            return (Criteria) this;
        }

        public Criteria andEvaluationQualityNotIn(List<Integer> values) {
            addCriterion("evaluation_quality not in", values, "evaluationQuality");
            return (Criteria) this;
        }

        public Criteria andEvaluationQualityBetween(Integer value1, Integer value2) {
            addCriterion("evaluation_quality between", value1, value2, "evaluationQuality");
            return (Criteria) this;
        }

        public Criteria andEvaluationQualityNotBetween(Integer value1, Integer value2) {
            addCriterion("evaluation_quality not between", value1, value2, "evaluationQuality");
            return (Criteria) this;
        }

        public Criteria andEvaluationRemarkIsNull() {
            addCriterion("evaluation_remark is null");
            return (Criteria) this;
        }

        public Criteria andEvaluationRemarkIsNotNull() {
            addCriterion("evaluation_remark is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluationRemarkEqualTo(String value) {
            addCriterion("evaluation_remark =", value, "evaluationRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationRemarkNotEqualTo(String value) {
            addCriterion("evaluation_remark <>", value, "evaluationRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationRemarkGreaterThan(String value) {
            addCriterion("evaluation_remark >", value, "evaluationRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("evaluation_remark >=", value, "evaluationRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationRemarkLessThan(String value) {
            addCriterion("evaluation_remark <", value, "evaluationRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationRemarkLessThanOrEqualTo(String value) {
            addCriterion("evaluation_remark <=", value, "evaluationRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationRemarkLike(String value) {
            addCriterion("evaluation_remark like", value, "evaluationRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationRemarkNotLike(String value) {
            addCriterion("evaluation_remark not like", value, "evaluationRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationRemarkIn(List<String> values) {
            addCriterion("evaluation_remark in", values, "evaluationRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationRemarkNotIn(List<String> values) {
            addCriterion("evaluation_remark not in", values, "evaluationRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationRemarkBetween(String value1, String value2) {
            addCriterion("evaluation_remark between", value1, value2, "evaluationRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationRemarkNotBetween(String value1, String value2) {
            addCriterion("evaluation_remark not between", value1, value2, "evaluationRemark");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeIsNull() {
            addCriterion("payment_type is null");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeIsNotNull() {
            addCriterion("payment_type is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeEqualTo(Integer value) {
            addCriterion("payment_type =", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotEqualTo(Integer value) {
            addCriterion("payment_type <>", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeGreaterThan(Integer value) {
            addCriterion("payment_type >", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("payment_type >=", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeLessThan(Integer value) {
            addCriterion("payment_type <", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeLessThanOrEqualTo(Integer value) {
            addCriterion("payment_type <=", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeIn(List<Integer> values) {
            addCriterion("payment_type in", values, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotIn(List<Integer> values) {
            addCriterion("payment_type not in", values, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeBetween(Integer value1, Integer value2) {
            addCriterion("payment_type between", value1, value2, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("payment_type not between", value1, value2, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNameIsNull() {
            addCriterion("payment_type_name is null");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNameIsNotNull() {
            addCriterion("payment_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNameEqualTo(String value) {
            addCriterion("payment_type_name =", value, "paymentTypeName");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNameNotEqualTo(String value) {
            addCriterion("payment_type_name <>", value, "paymentTypeName");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNameGreaterThan(String value) {
            addCriterion("payment_type_name >", value, "paymentTypeName");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("payment_type_name >=", value, "paymentTypeName");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNameLessThan(String value) {
            addCriterion("payment_type_name <", value, "paymentTypeName");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNameLessThanOrEqualTo(String value) {
            addCriterion("payment_type_name <=", value, "paymentTypeName");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNameLike(String value) {
            addCriterion("payment_type_name like", value, "paymentTypeName");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNameNotLike(String value) {
            addCriterion("payment_type_name not like", value, "paymentTypeName");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNameIn(List<String> values) {
            addCriterion("payment_type_name in", values, "paymentTypeName");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNameNotIn(List<String> values) {
            addCriterion("payment_type_name not in", values, "paymentTypeName");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNameBetween(String value1, String value2) {
            addCriterion("payment_type_name between", value1, value2, "paymentTypeName");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNameNotBetween(String value1, String value2) {
            addCriterion("payment_type_name not between", value1, value2, "paymentTypeName");
            return (Criteria) this;
        }

        public Criteria andIsCancelIsNull() {
            addCriterion("is_cancel is null");
            return (Criteria) this;
        }

        public Criteria andIsCancelIsNotNull() {
            addCriterion("is_cancel is not null");
            return (Criteria) this;
        }

        public Criteria andIsCancelEqualTo(Integer value) {
            addCriterion("is_cancel =", value, "isCancel");
            return (Criteria) this;
        }

        public Criteria andIsCancelNotEqualTo(Integer value) {
            addCriterion("is_cancel <>", value, "isCancel");
            return (Criteria) this;
        }

        public Criteria andIsCancelGreaterThan(Integer value) {
            addCriterion("is_cancel >", value, "isCancel");
            return (Criteria) this;
        }

        public Criteria andIsCancelGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_cancel >=", value, "isCancel");
            return (Criteria) this;
        }

        public Criteria andIsCancelLessThan(Integer value) {
            addCriterion("is_cancel <", value, "isCancel");
            return (Criteria) this;
        }

        public Criteria andIsCancelLessThanOrEqualTo(Integer value) {
            addCriterion("is_cancel <=", value, "isCancel");
            return (Criteria) this;
        }

        public Criteria andIsCancelIn(List<Integer> values) {
            addCriterion("is_cancel in", values, "isCancel");
            return (Criteria) this;
        }

        public Criteria andIsCancelNotIn(List<Integer> values) {
            addCriterion("is_cancel not in", values, "isCancel");
            return (Criteria) this;
        }

        public Criteria andIsCancelBetween(Integer value1, Integer value2) {
            addCriterion("is_cancel between", value1, value2, "isCancel");
            return (Criteria) this;
        }

        public Criteria andIsCancelNotBetween(Integer value1, Integer value2) {
            addCriterion("is_cancel not between", value1, value2, "isCancel");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkIsNull() {
            addCriterion("cancel_remark is null");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkIsNotNull() {
            addCriterion("cancel_remark is not null");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkEqualTo(String value) {
            addCriterion("cancel_remark =", value, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkNotEqualTo(String value) {
            addCriterion("cancel_remark <>", value, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkGreaterThan(String value) {
            addCriterion("cancel_remark >", value, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("cancel_remark >=", value, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkLessThan(String value) {
            addCriterion("cancel_remark <", value, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkLessThanOrEqualTo(String value) {
            addCriterion("cancel_remark <=", value, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkLike(String value) {
            addCriterion("cancel_remark like", value, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkNotLike(String value) {
            addCriterion("cancel_remark not like", value, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkIn(List<String> values) {
            addCriterion("cancel_remark in", values, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkNotIn(List<String> values) {
            addCriterion("cancel_remark not in", values, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkBetween(String value1, String value2) {
            addCriterion("cancel_remark between", value1, value2, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkNotBetween(String value1, String value2) {
            addCriterion("cancel_remark not between", value1, value2, "cancelRemark");
            return (Criteria) this;
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

        public Criteria andStoreIdIsNull() {
            addCriterion("store_id is null");
            return (Criteria) this;
        }

        public Criteria andStoreIdIsNotNull() {
            addCriterion("store_id is not null");
            return (Criteria) this;
        }

        public Criteria andStoreIdEqualTo(Long value) {
            addCriterion("store_id =", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotEqualTo(Long value) {
            addCriterion("store_id <>", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThan(Long value) {
            addCriterion("store_id >", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThanOrEqualTo(Long value) {
            addCriterion("store_id >=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThan(Long value) {
            addCriterion("store_id <", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThanOrEqualTo(Long value) {
            addCriterion("store_id <=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdIn(List<Long> values) {
            addCriterion("store_id in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotIn(List<Long> values) {
            addCriterion("store_id not in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdBetween(Long value1, Long value2) {
            addCriterion("store_id between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotBetween(Long value1, Long value2) {
            addCriterion("store_id not between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andChargeIdIsNull() {
            addCriterion("charge_id is null");
            return (Criteria) this;
        }

        public Criteria andChargeIdIsNotNull() {
            addCriterion("charge_id is not null");
            return (Criteria) this;
        }

        public Criteria andChargeIdEqualTo(String value) {
            addCriterion("charge_id =", value, "chargeId");
            return (Criteria) this;
        }

        public Criteria andChargeIdNotEqualTo(String value) {
            addCriterion("charge_id <>", value, "chargeId");
            return (Criteria) this;
        }

        public Criteria andChargeIdGreaterThan(String value) {
            addCriterion("charge_id >", value, "chargeId");
            return (Criteria) this;
        }

        public Criteria andChargeIdGreaterThanOrEqualTo(String value) {
            addCriterion("charge_id >=", value, "chargeId");
            return (Criteria) this;
        }

        public Criteria andChargeIdLessThan(String value) {
            addCriterion("charge_id <", value, "chargeId");
            return (Criteria) this;
        }

        public Criteria andChargeIdLessThanOrEqualTo(String value) {
            addCriterion("charge_id <=", value, "chargeId");
            return (Criteria) this;
        }

        public Criteria andChargeIdLike(String value) {
            addCriterion("charge_id like", value, "chargeId");
            return (Criteria) this;
        }

        public Criteria andChargeIdNotLike(String value) {
            addCriterion("charge_id not like", value, "chargeId");
            return (Criteria) this;
        }

        public Criteria andChargeIdIn(List<String> values) {
            addCriterion("charge_id in", values, "chargeId");
            return (Criteria) this;
        }

        public Criteria andChargeIdNotIn(List<String> values) {
            addCriterion("charge_id not in", values, "chargeId");
            return (Criteria) this;
        }

        public Criteria andChargeIdBetween(String value1, String value2) {
            addCriterion("charge_id between", value1, value2, "chargeId");
            return (Criteria) this;
        }

        public Criteria andChargeIdNotBetween(String value1, String value2) {
            addCriterion("charge_id not between", value1, value2, "chargeId");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNull() {
            addCriterion("pay_time is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("pay_time is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimeEqualTo(Date value) {
            addCriterion("pay_time =", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotEqualTo(Date value) {
            addCriterion("pay_time <>", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThan(Date value) {
            addCriterion("pay_time >", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_time >=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThan(Date value) {
            addCriterion("pay_time <", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("pay_time <=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIn(List<Date> values) {
            addCriterion("pay_time in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotIn(List<Date> values) {
            addCriterion("pay_time not in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeBetween(Date value1, Date value2) {
            addCriterion("pay_time between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotBetween(Date value1, Date value2) {
            addCriterion("pay_time not between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andOrderIdLikeInsensitive(String value) {
            addCriterion("upper(order_id) like", value.toUpperCase(), "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderSnLikeInsensitive(String value) {
            addCriterion("upper(order_sn) like", value.toUpperCase(), "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderTelephoneLikeInsensitive(String value) {
            addCriterion("upper(order_telephone) like", value.toUpperCase(), "orderTelephone");
            return (Criteria) this;
        }

        public Criteria andOrderContactLikeInsensitive(String value) {
            addCriterion("upper(order_contact) like", value.toUpperCase(), "orderContact");
            return (Criteria) this;
        }

        public Criteria andOrderAddressLikeInsensitive(String value) {
            addCriterion("upper(order_address) like", value.toUpperCase(), "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkLikeInsensitive(String value) {
            addCriterion("upper(order_remark) like", value.toUpperCase(), "orderRemark");
            return (Criteria) this;
        }

        public Criteria andServiceUserIdLikeInsensitive(String value) {
            addCriterion("upper(service_user_id) like", value.toUpperCase(), "serviceUserId");
            return (Criteria) this;
        }

        public Criteria andServiceTelephoneLikeInsensitive(String value) {
            addCriterion("upper(service_telephone) like", value.toUpperCase(), "serviceTelephone");
            return (Criteria) this;
        }

        public Criteria andServiceContactLikeInsensitive(String value) {
            addCriterion("upper(service_contact) like", value.toUpperCase(), "serviceContact");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLikeInsensitive(String value) {
            addCriterion("upper(service_type) like", value.toUpperCase(), "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceRemarkLikeInsensitive(String value) {
            addCriterion("upper(service_remark) like", value.toUpperCase(), "serviceRemark");
            return (Criteria) this;
        }

        public Criteria andCouponIdLikeInsensitive(String value) {
            addCriterion("upper(coupon_id) like", value.toUpperCase(), "couponId");
            return (Criteria) this;
        }

        public Criteria andFlowStatusNameLikeInsensitive(String value) {
            addCriterion("upper(flow_status_name) like", value.toUpperCase(), "flowStatusName");
            return (Criteria) this;
        }

        public Criteria andEvaluationRemarkLikeInsensitive(String value) {
            addCriterion("upper(evaluation_remark) like", value.toUpperCase(), "evaluationRemark");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNameLikeInsensitive(String value) {
            addCriterion("upper(payment_type_name) like", value.toUpperCase(), "paymentTypeName");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkLikeInsensitive(String value) {
            addCriterion("upper(cancel_remark) like", value.toUpperCase(), "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andUserIdLikeInsensitive(String value) {
            addCriterion("upper(user_id) like", value.toUpperCase(), "userId");
            return (Criteria) this;
        }

        public Criteria andChargeIdLikeInsensitive(String value) {
            addCriterion("upper(charge_id) like", value.toUpperCase(), "chargeId");
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