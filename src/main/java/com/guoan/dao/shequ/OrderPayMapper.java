package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.OrderPay;
import com.guoan.entity.shequ.bo.OrderPayCriteria;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderPayMapper {
    int countByExample(OrderPayCriteria example);

    int deleteByExample(OrderPayCriteria example);

    int deleteByPrimaryKey(String payId);

    int insert(OrderPay record);

    int insertSelective(OrderPay record);

    List<OrderPay> selectByExample(OrderPayCriteria example);

    OrderPay selectByPrimaryKey(String payId);

    int updateByExampleSelective(@Param("record") OrderPay record, @Param("example") OrderPayCriteria example);

    int updateByExample(@Param("record") OrderPay record, @Param("example") OrderPayCriteria example);

    int updateByPrimaryKeySelective(OrderPay record);

    int updateByPrimaryKey(OrderPay record);

    /**
     * 取得订单列表
     *
     * @param record
     * @return
     */
    List<OrderPay> getOrderList(@Param("record") Map<String, Object> record);

    /**
     * 取得订单列表
     *
     * @param record
     * @return
     */
    int getOrderListCount(@Param("record") Map<String, Object> record);

    /**
     * 根据订单ID取得订单
     *
     * @param payId
     * @return
     */
    OrderPay selectByPayId(String payId);

}