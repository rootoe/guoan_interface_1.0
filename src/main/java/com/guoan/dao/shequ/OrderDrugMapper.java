package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.OrderDrug;
import com.guoan.entity.shequ.bo.OrderDrugCriteria;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderDrugMapper {
    int countByExample(OrderDrugCriteria example);

    int deleteByExample(OrderDrugCriteria example);

    int deleteByPrimaryKey(String drugsId);

    int insert(OrderDrug record);

    int insertSelective(OrderDrug record);

    List<OrderDrug> selectByExample(OrderDrugCriteria example);

    OrderDrug selectByPrimaryKey(String drugsId);

    int updateByExampleSelective(@Param("record") OrderDrug record, @Param("example") OrderDrugCriteria example);

    int updateByExample(@Param("record") OrderDrug record, @Param("example") OrderDrugCriteria example);

    int updateByPrimaryKeySelective(OrderDrug record);

    int updateByPrimaryKey(OrderDrug record);
    
    /**
     * 取得订单列表
     * @param record
     * @return
     */
   List<OrderDrug> getOrderList(@Param("record") Map<String, Object> record);
    
    /**
     * 取得订单列表
     * @param record
     * @return
     */
    int getOrderListCount(@Param("record") Map<String, Object> record);
    
    /**
     * 根据订单ID取得订单
     * @param drugsId
     * @return
     */
    OrderDrug selectByDrugId( String drugsId );
}