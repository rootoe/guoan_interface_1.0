package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.OrderPayInfo;
import com.guoan.entity.shequ.bo.OrderPayInfoCriteria;
import com.guoan.entity.shequ.bo.OrderPayInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPayInfoMapper {
    int countByExample(OrderPayInfoCriteria example);

    int deleteByExample(OrderPayInfoCriteria example);

    int deleteByPrimaryKey(OrderPayInfoKey key);

    int insert(OrderPayInfo record);

    int insertSelective(OrderPayInfo record);

    List<OrderPayInfo> selectByExample(OrderPayInfoCriteria example);

    OrderPayInfo selectByPrimaryKey(OrderPayInfoKey key);

    int updateByExampleSelective(@Param("record") OrderPayInfo record, @Param("example") OrderPayInfoCriteria example);

    int updateByExample(@Param("record") OrderPayInfo record, @Param("example") OrderPayInfoCriteria example);

    int updateByPrimaryKeySelective(OrderPayInfo record);

    int updateByPrimaryKey(OrderPayInfo record);
}