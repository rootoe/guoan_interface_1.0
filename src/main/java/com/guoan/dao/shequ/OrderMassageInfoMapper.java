package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.OrderMassageInfo;
import com.guoan.entity.shequ.bo.OrderMassageInfoCriteria;
import com.guoan.entity.shequ.bo.OrderMassageInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 康复按摩信息
 * @author 
 *
 */
public interface OrderMassageInfoMapper {
    
	int countByExample(OrderMassageInfoCriteria example);

    int deleteByExample(OrderMassageInfoCriteria example);

    int deleteByPrimaryKey(OrderMassageInfoKey key);

    int insert(OrderMassageInfo record);

    int insertSelective(OrderMassageInfo record);

    List<OrderMassageInfo> selectByExample(OrderMassageInfoCriteria example);

    OrderMassageInfo selectByPrimaryKey(OrderMassageInfoKey key);

    int updateByExampleSelective(@Param("record") OrderMassageInfo record, @Param("example") OrderMassageInfoCriteria example);

    int updateByExample(@Param("record") OrderMassageInfo record, @Param("example") OrderMassageInfoCriteria example);

    int updateByPrimaryKeySelective(OrderMassageInfo record);

    int updateByPrimaryKey(OrderMassageInfo record);
}