package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.OrderWashInfo;
import com.guoan.entity.shequ.bo.OrderWashInfoCriteria;
import com.guoan.entity.shequ.bo.OrderWashInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderWashInfoMapper {
    int countByExample(OrderWashInfoCriteria example);

    int deleteByExample(OrderWashInfoCriteria example);

    int deleteByPrimaryKey(OrderWashInfoKey key);

    int insert(OrderWashInfo record);

    int insertSelective(OrderWashInfo record);

    List<OrderWashInfo> selectByExample(OrderWashInfoCriteria example);

    OrderWashInfo selectByPrimaryKey(OrderWashInfoKey key);

    int updateByExampleSelective(@Param("record") OrderWashInfo record, @Param("example") OrderWashInfoCriteria example);

    int updateByExample(@Param("record") OrderWashInfo record, @Param("example") OrderWashInfoCriteria example);

    int updateByPrimaryKeySelective(OrderWashInfo record);

    int updateByPrimaryKey(OrderWashInfo record);
}