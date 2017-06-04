package com.guoan.entity.work.vo;

/**
 * 描述信息: 服务人员订单类型统计vo
 * Created by 赵彤 on 2015/5/18.
 */

public class WorkOrderFlowVO {
    // 服务类型
    private String flowStatus;

    private int flowStatusCount;

    public String getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(String flowStatus) {
        this.flowStatus = flowStatus;
    }

    public int getFlowStatusCount() {
        return flowStatusCount;
    }

    public void setFlowStatusCount(int flowStatusCount) {
        this.flowStatusCount = flowStatusCount;
    }
}
