package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.OrderGoodsInfo;
import com.guoan.entity.shequ.bo.OrderGoodsInfoCriteria;
import com.guoan.entity.shequ.bo.OrderGoodsInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderGoodsInfoMapper {
    int countByExample(OrderGoodsInfoCriteria example);

    int deleteByExample(OrderGoodsInfoCriteria example);

    int deleteByPrimaryKey(OrderGoodsInfoKey key);

    int insert(OrderGoodsInfo record);

    int insertSelective(OrderGoodsInfo record);

    List<OrderGoodsInfo> selectByExample(OrderGoodsInfoCriteria example);

    OrderGoodsInfo selectByPrimaryKey(OrderGoodsInfoKey key);

    int updateByExampleSelective(@Param("record") OrderGoodsInfo record, @Param("example") OrderGoodsInfoCriteria example);

    int updateByExample(@Param("record") OrderGoodsInfo record, @Param("example") OrderGoodsInfoCriteria example);

    int updateByPrimaryKeySelective(OrderGoodsInfo record);

    int updateByPrimaryKey(OrderGoodsInfo record);
}