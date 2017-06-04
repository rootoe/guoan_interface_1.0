package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.OrderClean;
import com.guoan.entity.shequ.bo.OrderExpress;
import com.guoan.entity.shequ.bo.OrderExpressCriteria;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderExpressMapper {
    int countByExample(OrderExpressCriteria example);

    int deleteByExample(OrderExpressCriteria example);

    int deleteByPrimaryKey(String expressId);

    int insert(OrderExpress record);

    int insertSelective(OrderExpress record);

    List<OrderExpress> selectByExample(OrderExpressCriteria example);

    OrderExpress selectByPrimaryKey(String expressId);

    int updateByExampleSelective(@Param("record") OrderExpress record, @Param("example") OrderExpressCriteria example);

    int updateByExample(@Param("record") OrderExpress record, @Param("example") OrderExpressCriteria example);

    int updateByPrimaryKeySelective(OrderExpress record);

    int updateByPrimaryKey(OrderExpress record);
    
    /**
     * 取得订单列表
     * @param record
     * @return
     */
    List<OrderExpress> getOrderList(@Param("record") Map<String, Object> record);
    
    /**
     * 取得订单列表
     * @param record
     * @return
     */
    int getOrderListCount(@Param("record") Map<String, Object> record);
    
    /**
     * 根据订单ID取得订单
     * @param expressId
     * @return
     */
    OrderExpress selectByExpressId( String expressId );
}