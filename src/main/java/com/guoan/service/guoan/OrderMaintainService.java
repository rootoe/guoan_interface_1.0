package com.guoan.service.guoan;

import com.guoan.entity.base.common.Result;

public interface OrderMaintainService {

	public Result addOrder(String jsonString);
	
	public Result getOrderList(String jsonString);
	
	public Result getOrder(String jsonString);
	
}
