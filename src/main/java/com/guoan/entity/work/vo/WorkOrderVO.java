package com.guoan.entity.work.vo;

import java.util.List;

/**
 * 描述信息: 服务人员订单vo
 * Created by 赵彤 on 2015/5/18.
 */

public class WorkOrderVO {
    // 服务类型
    private String serviceType;
    // 服务类型数量
    private int serviceTypeCount;

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public int getServiceTypeCount() {
        return serviceTypeCount;
    }

    public void setServiceTypeCount(int serviceTypeCount) {
        this.serviceTypeCount = serviceTypeCount;
    }
}
