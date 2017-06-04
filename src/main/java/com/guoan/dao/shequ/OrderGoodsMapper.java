package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.Order;
import com.guoan.entity.shequ.bo.OrderGoods;
import com.guoan.entity.shequ.bo.OrderGoodsCriteria;
import com.guoan.entity.shequ.bo.OrderGoodsKey;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderGoodsMapper {
    int countByExample(OrderGoodsCriteria example);

    int deleteByExample(OrderGoodsCriteria example);

    int deleteByPrimaryKey(OrderGoodsKey key);

    int insert(OrderGoods record);

    int insertSelective(OrderGoods record);

    List<OrderGoods> selectByExample(OrderGoodsCriteria example);

    OrderGoods selectByPrimaryKey(OrderGoodsKey key);

    int updateByExampleSelective(@Param("record") OrderGoods record, @Param("example") OrderGoodsCriteria example);

    int updateByExample(@Param("record") OrderGoods record, @Param("example") OrderGoodsCriteria example);

    int updateByPrimaryKeySelective(OrderGoods record);

    int updateByPrimaryKey(OrderGoods record);

    List<Order> getOrderList(@Param("record") Map<String, Object> record);

    int getOrderListCount(@Param("record") Map<String, Object> record);
}