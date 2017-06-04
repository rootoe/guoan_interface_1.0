package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.AppDeviceInfo;
import com.guoan.entity.shequ.bo.AppDeviceInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppDeviceInfoMapper {
	int countByExample(AppDeviceInfoCriteria example);

	int deleteByExample(AppDeviceInfoCriteria example);

	int deleteByPrimaryKey(String userId);

	int insert(AppDeviceInfo record);

	int insertSelective(AppDeviceInfo record);

	List<AppDeviceInfo> selectByExample(AppDeviceInfoCriteria example);

	AppDeviceInfo selectByPrimaryKey(String userId);

	int updateByExampleSelective(@Param("record") AppDeviceInfo record, @Param("example") AppDeviceInfoCriteria example);

	int updateByExample(@Param("record") AppDeviceInfo record, @Param("example") AppDeviceInfoCriteria example);

	int updateByPrimaryKeySelective(AppDeviceInfo record);

	int updateByPrimaryKey(AppDeviceInfo record);
}