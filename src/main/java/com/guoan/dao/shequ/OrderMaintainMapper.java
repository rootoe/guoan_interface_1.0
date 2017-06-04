package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.OrderClean;
import com.guoan.entity.shequ.bo.OrderExpress;
import com.guoan.entity.shequ.bo.OrderMaintain;
import com.guoan.entity.shequ.bo.OrderMaintainCriteria;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMaintainMapper {
    int countByExample(OrderMaintainCriteria example);

    int deleteByExample(OrderMaintainCriteria example);

    int deleteByPrimaryKey(String maintainId);

    int insert(OrderMaintain record);

    int insertSelective(OrderMaintain record);

    List<OrderMaintain> selectByExample(OrderMaintainCriteria example);

    OrderMaintain selectByPrimaryKey(String maintainId);

    int updateByExampleSelective(@Param("record") OrderMaintain record, @Param("example") OrderMaintainCriteria example);

    int updateByExample(@Param("record") OrderMaintain record, @Param("example") OrderMaintainCriteria example);

    int updateByPrimaryKeySelective(OrderMaintain record);

    int updateByPrimaryKey(OrderMaintain record);

    /**
     * 取得订单列表
     * @param record
     * @return
     */
    List<OrderMaintain> getOrderList(@Param("record") Map<String, Object> record);

    /**
     * 取得订单列表
     * @param record
     * @return
     */
    int getOrderListCount(@Param("record") Map<String, Object> record);

    /**
     * 根据订单ID取得订单
     * @param maintainId
     * @return
     */
    OrderMaintain selectByMaintainId( String maintainId );
}