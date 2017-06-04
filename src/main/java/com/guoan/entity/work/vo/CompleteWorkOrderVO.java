package com.guoan.entity.work.vo;

import com.guoan.entity.shequ.bo.Order;

import java.util.List;

/**
 * 描述信息:
 * Created by 赵彤 on 2015/5/19.
 */

public class CompleteWorkOrderVO {
    private List<Order> orderList;

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
