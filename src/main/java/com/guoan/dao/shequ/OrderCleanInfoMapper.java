package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.OrderCleanInfo;
import com.guoan.entity.shequ.bo.OrderCleanInfoCriteria;
import com.guoan.entity.shequ.bo.OrderCleanInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCleanInfoMapper {
    int countByExample(OrderCleanInfoCriteria example);

    int deleteByExample(OrderCleanInfoCriteria example);

    int deleteByPrimaryKey(OrderCleanInfoKey key);

    int insert(OrderCleanInfo record);

    int insertSelective(OrderCleanInfo record);

    List<OrderCleanInfo> selectByExample(OrderCleanInfoCriteria example);

    OrderCleanInfo selectByPrimaryKey(OrderCleanInfoKey key);

    int updateByExampleSelective(@Param("record") OrderCleanInfo record, @Param("example") OrderCleanInfoCriteria example);

    int updateByExample(@Param("record") OrderCleanInfo record, @Param("example") OrderCleanInfoCriteria example);

    int updateByPrimaryKeySelective(OrderCleanInfo record);

    int updateByPrimaryKey(OrderCleanInfo record);
}