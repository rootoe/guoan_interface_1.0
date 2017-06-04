package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.OrderMassage;
import com.guoan.entity.shequ.bo.OrderMassageCriteria;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 康复按摩 
 * @author 
 *
 */
public interface OrderMassageMapper {
    int countByExample(OrderMassageCriteria example);

    int deleteByExample(OrderMassageCriteria example);

    int deleteByPrimaryKey(String massageId);

    int insert(OrderMassage record);

    int insertSelective(OrderMassage record);

    List<OrderMassage> selectByExample(OrderMassageCriteria example);

    OrderMassage selectByPrimaryKey(String massageId);

    int updateByExampleSelective(@Param("record") OrderMassage record, @Param("example") OrderMassageCriteria example);

    int updateByExample(@Param("record") OrderMassage record, @Param("example") OrderMassageCriteria example);

    int updateByPrimaryKeySelective(OrderMassage record);

    int updateByPrimaryKey(OrderMassage record);
    
    
    /**
     * 取得订单列表
     * @param record
     * @return
     */
   List<OrderMassage> getOrderList(@Param("record") Map<String, Object> record);
    
    /**
     * 取得订单列表
     * @param record
     * @return
     */
    int getOrderListCount(@Param("record") Map<String, Object> record);
    
    /**
     * 根据订单ID取得订单
     * @param massageId
     * @return
     */
    OrderMassage selectByMassageId( String massageId );
}