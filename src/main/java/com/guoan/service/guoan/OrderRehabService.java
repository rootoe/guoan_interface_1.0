package com.guoan.service.guoan;

import com.guoan.entity.base.common.Result;

public interface OrderRehabService {

	public Result addOrder(String jsonString);
	
	public Result cancelOrder(String jsonString);
	
	public Result deleteOrder(String jsonString);
	
	public Result commentOrder(String jsonString);
	
	public Result getOrderList(String jsonString);
	
	public Result updateOrderStatus(String jsonString);
	
	public Result updateOrder(String jsonString);
	
	public Result getOrder(String jsonString);
	
}
