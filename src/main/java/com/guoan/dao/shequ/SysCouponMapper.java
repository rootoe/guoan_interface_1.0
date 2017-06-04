package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.SysCoupon;
import com.guoan.entity.shequ.bo.SysCouponCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysCouponMapper {
    int countByExample(SysCouponCriteria example);

    int deleteByExample(SysCouponCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(SysCoupon record);

    int insertSelective(SysCoupon record);

    List<SysCoupon> selectByExample(SysCouponCriteria example);

    SysCoupon selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysCoupon record, @Param("example") SysCouponCriteria example);

    int updateByExample(@Param("record") SysCoupon record, @Param("example") SysCouponCriteria example);

    int updateByPrimaryKeySelective(SysCoupon record);

    int updateByPrimaryKey(SysCoupon record);
    
    int updateBindUserId(String userId);
}