package com.guoan.dao.shequ;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.guoan.entity.shequ.bo.OrderWash;
import com.guoan.entity.shequ.bo.OrderWashCriteria;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderWashMapper {
    int countByExample(OrderWashCriteria example);

    int deleteByExample(OrderWashCriteria example);

    int deleteByPrimaryKey(String washId);

    int insert(OrderWash record);

    int insertSelective(OrderWash record);

    List<OrderWash> selectByExample(OrderWashCriteria example);

    OrderWash selectByPrimaryKey(String washId);

    int updateByExampleSelective(@Param("record") OrderWash record, @Param("example") OrderWashCriteria example);

    int updateByExample(@Param("record") OrderWash record, @Param("example") OrderWashCriteria example);

    int updateByPrimaryKeySelective(OrderWash record);

    int updateByPrimaryKey(OrderWash record);
    
    /**
     * 取得订单列表
     * @param record
     * @return
     */
    List<OrderWash> getOrderList(@Param("record") Map<String, Object> record);
    
    /**
     * 取得订单列表
     * @param record
     * @return
     */
    int getOrderListCount(@Param("record") Map<String, Object> record);
    
    /**
     * 根据订单ID取得订单
     * @param washId
     * @return
     */
    OrderWash selectByWashId( String washId );
}