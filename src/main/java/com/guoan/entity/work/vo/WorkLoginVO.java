package com.guoan.entity.work.vo;

import com.guoan.entity.shequ.bo.Order;

import java.util.List;
import java.util.Map;

/**
 * 描述信息: 登陆账号后返回的服务人员的数据.
 * Created by 赵彤 on 2015/5/16.
 */

public class WorkLoginVO {
    // 门店信息
    private String storeName;
    // 服务商信息
    private String providerName;
    // 头像
    private String avator;
    // 姓名
    private String nickname;
    // 星级
    private String starLevel;
    // 公告
    private String notice;
    // 关于我们
    private String aboutUs;
    // 常见问题
    private String faq;
    // token
    private String token;

    // 需要统计已完成的订单数量
    private int completedOrderCount;
    // 需要已完成的所有订单数量O
    private List<WorkOrderVO> completedOrderList;

    // 一个数据字典
    // 比如: 支付方式 : 1. 支付宝 2. 现金
    // 比如: 快递公司 : 1. A公司 2. B公司
    private Map<String, List<Map<String, Object>>> dictionary;

    public Map<String, List<Map<String, Object>>> getDictionary() {
        return dictionary;
    }

    public void setDictionary(Map<String, List<Map<String, Object>>> dictionary) {
        this.dictionary = dictionary;
    }

    public int getCompletedOrderCount() {
        return completedOrderCount;
    }

    public void setCompletedOrderCount(int completedOrderCount) {
        this.completedOrderCount = completedOrderCount;
    }

    public List<WorkOrderVO> getCompletedOrderList() {
        return completedOrderList;
    }

    public void setCompletedOrderList(List<WorkOrderVO> completedOrderList) {
        this.completedOrderList = completedOrderList;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    public String getFaq() {
        return faq;
    }

    public void setFaq(String faq) {
        this.faq = faq;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
