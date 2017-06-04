package com.guoan.service.guoan;

import com.guoan.entity.base.common.Result;

/**
 * 管理用户地址
 * @author gaochao
 *
 */
public interface AppUserAddressService {
	
	public Result setDefault(String jsonString);
	
	public Result save(String jsonString);
	
	public Result update(String jsonString);
	
	public Result delete(String jsonString);
	
	public Result getById(String jsonString);
	
	public Result getListByUser(String jsonString);
	
	public Result getListByUserFilterArea(String jsonString);

}
