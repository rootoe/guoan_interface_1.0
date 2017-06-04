package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.AppSoftwareInfo;
import com.guoan.entity.shequ.bo.AppSoftwareInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppSoftwareInfoMapper {
    int countByExample(AppSoftwareInfoCriteria example);

    int deleteByExample(AppSoftwareInfoCriteria example);

    int deleteByPrimaryKey(String userId);

    int insert(AppSoftwareInfo record);

    int insertSelective(AppSoftwareInfo record);

    List<AppSoftwareInfo> selectByExample(AppSoftwareInfoCriteria example);

    AppSoftwareInfo selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") AppSoftwareInfo record, @Param("example") AppSoftwareInfoCriteria example);

    int updateByExample(@Param("record") AppSoftwareInfo record, @Param("example") AppSoftwareInfoCriteria example);

    int updateByPrimaryKeySelective(AppSoftwareInfo record);

    int updateByPrimaryKey(AppSoftwareInfo record);
}