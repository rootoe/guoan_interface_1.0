package com.guoan.dao.shequ;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.guoan.entity.shequ.bo.OrderRehab;
import com.guoan.entity.shequ.bo.OrderRehabCriteria;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRehabMapper {
    int countByExample(OrderRehabCriteria example);

    int deleteByExample(OrderRehabCriteria example);

    int deleteByPrimaryKey(String rehabId);

    int insert(OrderRehab record);

    int insertSelective(OrderRehab record);

    List<OrderRehab> selectByExample(OrderRehabCriteria example);

    OrderRehab selectByPrimaryKey(String rehabId);

    int updateByExampleSelective(@Param("record") OrderRehab record, @Param("example") OrderRehabCriteria example);

    int updateByExample(@Param("record") OrderRehab record, @Param("example") OrderRehabCriteria example);

    int updateByPrimaryKeySelective(OrderRehab record);

    int updateByPrimaryKey(OrderRehab record);
    
    /**
     * 取得订单列表
     * @param record
     * @return
     */
    List<OrderRehab> getOrderList(@Param("record") Map<String, Object> record);
    
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
    OrderRehab selectByRehabId( String expressId );
}