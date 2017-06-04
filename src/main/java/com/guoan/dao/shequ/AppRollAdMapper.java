package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.AppRollAd;
import com.guoan.entity.shequ.bo.AppRollAdCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRollAdMapper {
    int countByExample(AppRollAdCriteria example);

    int deleteByExample(AppRollAdCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(AppRollAd record);

    int insertSelective(AppRollAd record);

    List<AppRollAd> selectByExample(AppRollAdCriteria example);

    AppRollAd selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AppRollAd record, @Param("example") AppRollAdCriteria example);

    int updateByExample(@Param("record") AppRollAd record, @Param("example") AppRollAdCriteria example);

    int updateByPrimaryKeySelective(AppRollAd record);

    int updateByPrimaryKey(AppRollAd record);
}