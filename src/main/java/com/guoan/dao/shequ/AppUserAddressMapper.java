package com.guoan.dao.shequ;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guoan.entity.shequ.bo.AppUserAddress;
import com.guoan.entity.shequ.bo.AppUserAddressCriteria;
import com.guoan.entity.shequ.bo.AppUserAddressFull;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserAddressMapper {
    int countByExample(AppUserAddressCriteria example);

    int deleteByExample(AppUserAddressCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(AppUserAddress record);

    int insertSelective(AppUserAddress record);

    List<AppUserAddress> selectByExample(AppUserAddressCriteria example);

    AppUserAddress selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AppUserAddress record, @Param("example") AppUserAddressCriteria example);

    int updateByExample(@Param("record") AppUserAddress record, @Param("example") AppUserAddressCriteria example);

    int updateByPrimaryKeySelective(AppUserAddress record);

    int updateByPrimaryKey(AppUserAddress record);
	
	/**
	 * 取消默认地址
	 * @param userId
	 * @return
	 */
	int cancelDefault( @Param("userId") String userId );
	
	/**
	 * 设置默认地址
	 * @param addressId
	 * @return
	 */
	int setDefault( @Param("id") String addressId );

	/**
	 * 取得用户所有地址
	 * @param userId
	 * @return
	 */
	List<AppUserAddressFull> selectByUser(@Param("userId") String userId);

	/**
	 * 取得地址详情
	 * @param userId
	 * @return
	 */
	AppUserAddressFull selectById(@Param("id") String id);

	/**
	 * 取得默认地址
	 * @param userId
	 * @return
	 */
    List<AppUserAddressFull>  getDefaultAddr(@Param("userId") String userId);
}