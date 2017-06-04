package com.guoan.service.guoan;

import com.guoan.entity.base.common.Result;

/**
 * 康复按摩事物层接口
 * @author 
 *
 */
public interface OrderMassageService {
	
	/**下单**/
    public Result addOrder(String jsonString);
	
    /**获得订单列表**/
	public Result getOrderList(String jsonString);

	/**获得某个订单**/
	public Result getOrder(String jsonString);

}
