package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.OrderMaintainInfo;
import com.guoan.entity.shequ.bo.OrderMaintainInfoCriteria;
import com.guoan.entity.shequ.bo.OrderMaintainInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMaintainInfoMapper {
    int countByExample(OrderMaintainInfoCriteria example);

    int deleteByExample(OrderMaintainInfoCriteria example);

    int deleteByPrimaryKey(OrderMaintainInfoKey key);

    int insert(OrderMaintainInfo record);

    int insertSelective(OrderMaintainInfo record);

    List<OrderMaintainInfo> selectByExample(OrderMaintainInfoCriteria example);

    OrderMaintainInfo selectByPrimaryKey(OrderMaintainInfoKey key);

    int updateByExampleSelective(@Param("record") OrderMaintainInfo record, @Param("example") OrderMaintainInfoCriteria example);

    int updateByExample(@Param("record") OrderMaintainInfo record, @Param("example") OrderMaintainInfoCriteria example);

    int updateByPrimaryKeySelective(OrderMaintainInfo record);

    int updateByPrimaryKey(OrderMaintainInfo record);
}