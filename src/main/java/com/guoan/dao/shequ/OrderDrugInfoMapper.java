package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.OrderDrugInfo;
import com.guoan.entity.shequ.bo.OrderDrugInfoCriteria;
import com.guoan.entity.shequ.bo.OrderDrugInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderDrugInfoMapper {
    int countByExample(OrderDrugInfoCriteria example);

    int deleteByExample(OrderDrugInfoCriteria example);

    int deleteByPrimaryKey(OrderDrugInfoKey key);

    int insert(OrderDrugInfo record);

    int insertSelective(OrderDrugInfo record);

    List<OrderDrugInfo> selectByExample(OrderDrugInfoCriteria example);

    OrderDrugInfo selectByPrimaryKey(OrderDrugInfoKey key);

    int updateByExampleSelective(@Param("record") OrderDrugInfo record, @Param("example") OrderDrugInfoCriteria example);

    int updateByExample(@Param("record") OrderDrugInfo record, @Param("example") OrderDrugInfoCriteria example);

    int updateByPrimaryKeySelective(OrderDrugInfo record);

    int updateByPrimaryKey(OrderDrugInfo record);
}