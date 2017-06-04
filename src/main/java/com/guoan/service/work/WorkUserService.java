package com.guoan.service.work;

import com.guoan.entity.base.common.Result;
import com.guoan.entity.shequ.bo.Order;
import com.guoan.entity.work.vo.WorkOrderVO;

import java.util.List;

/**
 * 描述信息:
 * Created by 赵彤 on 2015/5/16.
 */
public interface WorkUserService {
    /**
     * 服务人员登陆
     * @param jsonString
     * @return
     */
    Result login(String jsonString);

    /**
     * 查询服务人员订单
     * @param serviceTypeList
     * @param serviceTelephone
     * @param flowStatus
     * @param page
     * @return
     */
    List<Order> seachWorkOrderInfo(List<String> serviceTypeList, String serviceTelephone, int flowStatus, int page);

    /**
     * 查询服务人员订单数量
     * @param serviceTypeList
     * @param serviceTelephone
     * @param flowStatus
     * @return
     */
    int seachWorkOrderInfoCount(List<String> serviceTypeList, String serviceTelephone, int flowStatus);

    /**
     * 查询服务人员订单, 各个类别有多少订单
     * @param serviceTypeList
     * @param serviceTelephone
     * @param flowStatus
     * @return
     */
    List<WorkOrderVO> seachWorkOrderVOInfo(List<String> serviceTypeList, String serviceTelephone, int flowStatus);

    /**
     * 服务人员已完成的订单
     * @param jsonString
     * @return
     */
    Result orderCompleteList(String jsonString);

    /**
     * 服务人员我的订单
     * @param jsonString
     * @return
     */
    Result orderMy(String jsonString);

    /**
     * 服务人员订单列表
     * @param jsonString
     * @return
     */
    Result orderList(String jsonString);

    /**
     * 服务人员更新订单
     * @param jsonString
     * @return
     */
    Result orderUpdate(String jsonString);

    /**
     * 已完成历史订单
     * @param jsonString
     * @return
     */
    Result orderComplete(String jsonString);
}
