package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.OrderClean;
import com.guoan.entity.shequ.bo.OrderCleanCriteria;
import java.util.List;
import java.util.Map;

import com.guoan.entity.shequ.bo.OrderExpress;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCleanMapper {
    int countByExample(OrderCleanCriteria example);

    int deleteByExample(OrderCleanCriteria example);

    int deleteByPrimaryKey(String cleanId);

    int insert(OrderClean record);

    int insertSelective(OrderClean record);

    List<OrderClean> selectByExample(OrderCleanCriteria example);

    OrderClean selectByPrimaryKey(String cleanId);

    int updateByExampleSelective(@Param("record") OrderClean record, @Param("example") OrderCleanCriteria example);

    int updateByExample(@Param("record") OrderClean record, @Param("example") OrderCleanCriteria example);

    int updateByPrimaryKeySelective(OrderClean record);

    int updateByPrimaryKey(OrderClean record);

    /**
     * 取得订单列表
     * @param record
     * @return
     */
    List<OrderClean> getOrderList(@Param("record") Map<String, Object> record);

    /**
     * 取得订单列表
     * @param record
     * @return
     */
    int getOrderListCount(@Param("record") Map<String, Object> record);

    /**
     * 根据订单ID取得订单
     * @param cleanId
     * @return
     */
    OrderClean selectByCleanId( String cleanId );
}