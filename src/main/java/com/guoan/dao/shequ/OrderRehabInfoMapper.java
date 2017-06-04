package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.OrderRehabInfo;
import com.guoan.entity.shequ.bo.OrderRehabInfoCriteria;
import com.guoan.entity.shequ.bo.OrderRehabInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRehabInfoMapper {
    int countByExample(OrderRehabInfoCriteria example);

    int deleteByExample(OrderRehabInfoCriteria example);

    int deleteByPrimaryKey(OrderRehabInfoKey key);

    int insert(OrderRehabInfo record);

    int insertSelective(OrderRehabInfo record);

    List<OrderRehabInfo> selectByExample(OrderRehabInfoCriteria example);

    OrderRehabInfo selectByPrimaryKey(OrderRehabInfoKey key);

    int updateByExampleSelective(@Param("record") OrderRehabInfo record, @Param("example") OrderRehabInfoCriteria example);

    int updateByExample(@Param("record") OrderRehabInfo record, @Param("example") OrderRehabInfoCriteria example);

    int updateByPrimaryKeySelective(OrderRehabInfo record);

    int updateByPrimaryKey(OrderRehabInfo record);
}