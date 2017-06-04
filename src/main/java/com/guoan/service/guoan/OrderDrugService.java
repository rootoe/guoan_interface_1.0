package com.guoan.service.guoan;

import com.guoan.entity.base.common.Result;

/**
 * 药品订单
 *
 */
public interface OrderDrugService {
	
	/**添加订单**/
    public Result addOrder(String jsonString);
	
    /**获得订单列表**/
	public Result getOrderList(String jsonString);

	/**得到订单**/
	public Result getOrder(String jsonString);

}
