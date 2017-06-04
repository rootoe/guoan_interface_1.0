package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.Order;
import com.guoan.entity.shequ.bo.OrderCriteria;
import java.util.List;
import java.util.Map;

import com.guoan.entity.work.vo.WorkOrderFlowVO;
import com.guoan.entity.work.vo.WorkOrderVO;
import org.apache.ibatis.annotations.Param;

import com.guoan.entity.shequ.bo.Order;
import com.guoan.entity.shequ.bo.OrderCriteria;
import com.guoan.entity.shequ.param.OrderParam;
import com.guoan.entity.work.vo.WorkOrderVO;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    int countByExample(OrderCriteria example);

    int deleteByExample(OrderCriteria example);

    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderCriteria example);

    Order selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderCriteria example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderCriteria example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    /**
     * 取消订单
     * @param record
     * @return
     */
    int cancelOrder(@Param("record") Map<String, Object> record);

    /**
     * 删除订单
     * @param id
     * @return
     */
    int deleteOrder( String id );

    /**
     * 评价订单
     * @param orderParam
     * @return
     */
    int commentOrder(@Param("record") OrderParam orderParam);

    /**
     * 更新订单状态
     * @param record
     * @return
     */
    int updateOrderStatus(@Param("record") Map<String, Object> record);
	/**
	 * 更新金额
	 * @param amount
	 * @return
	 */
	int updateAmountById(@Param("id") String id, @Param("amount") double amount); // Map<String, Object> record);

    List<WorkOrderVO> seachWorkOrderVOInfo(@Param("record") Map<String, Object> record);


    List<WorkOrderFlowVO> seachWorkOrderTypeVOInfo(@Param("record") Map<String, Object> record);


    /**
     * 取得订单列表
     * @param record
     * @return
     */
    List<Order> getOrderList(@Param("record") Map<String, Object> record);

    /**
     * 取得订单列表
     * @param record
     * @return
     */
    int getOrderListCount(@Param("record") Map<String, Object> record);

}