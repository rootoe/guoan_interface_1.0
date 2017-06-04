package com.guoan.dao.shequ;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.guoan.entity.shequ.bo.AppUserInfo;
import com.guoan.entity.shequ.bo.AppUserInfoCriteria;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserInfoMapper {
    int countByExample(AppUserInfoCriteria example);

    int deleteByExample(AppUserInfoCriteria example);

    int deleteByPrimaryKey(String userId);

    int insert(AppUserInfo record);

    int insertSelective(AppUserInfo record);

    List<AppUserInfo> selectByExample(AppUserInfoCriteria example);

    AppUserInfo selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") AppUserInfo record, @Param("example") AppUserInfoCriteria example);

    int updateByExample(@Param("record") AppUserInfo record, @Param("example") AppUserInfoCriteria example);

    int updateByPrimaryKeySelective(AppUserInfo record);

    int updateByPrimaryKey(AppUserInfo record);
    
	/**
	  * @Description : 验证手机号是否存在
	  * @创建人：赵彤  
	  * @创建时间：2015年4月30日 下午3:35:16  
	  * @return int
	  * @throws
	 */
	int isTelephoneInDB(String telephone);
	
	int updatePasswordByTelephone( @Param("record") Map record );
	
	int updateSex( @Param("record") Map record );
	
	int isNicknameInDB( @Param("record") Map record );
	
	int updateNickname( @Param("record") Map record );
	
	int updateAvatar( @Param("record") Map record );
}