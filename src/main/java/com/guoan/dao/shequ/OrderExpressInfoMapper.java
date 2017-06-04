package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.OrderExpressInfo;
import com.guoan.entity.shequ.bo.OrderExpressInfoCriteria;
import com.guoan.entity.shequ.bo.OrderExpressInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderExpressInfoMapper {
    int countByExample(OrderExpressInfoCriteria example);

    int deleteByExample(OrderExpressInfoCriteria example);

    int deleteByPrimaryKey(OrderExpressInfoKey key);

    int insert(OrderExpressInfo record);

    int insertSelective(OrderExpressInfo record);

    List<OrderExpressInfo> selectByExample(OrderExpressInfoCriteria example);

    OrderExpressInfo selectByPrimaryKey(OrderExpressInfoKey key);

    int updateByExampleSelective(@Param("record") OrderExpressInfo record, @Param("example") OrderExpressInfoCriteria example);

    int updateByExample(@Param("record") OrderExpressInfo record, @Param("example") OrderExpressInfoCriteria example);

    int updateByPrimaryKeySelective(OrderExpressInfo record);

    int updateByPrimaryKey(OrderExpressInfo record);
}