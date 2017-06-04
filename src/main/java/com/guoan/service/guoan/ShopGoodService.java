package com.guoan.service.guoan;

import com.guoan.entity.base.common.Result;

/**
 * Created by zhaotongbeyond@qq.com on 2015/6/4.
 */
public interface ShopGoodService {

    /**
     * 获得首页商品列表
     * @param jsonString
     * @return
     */
    Result index(String jsonString);

    /**
     * 获得详情
     * @param jsonString
     * @return
     */
    Result info(String jsonString);

    /**
     * 购买商品
     * @param jsonString
     * @return
     */
    Result buy(String jsonString);

    /**
     * 获得商品列表
     * @param jsonString
     * @return
     */
    Result list(String jsonString);

    /**
     * 搜索商品
     * @param jsonString
     * @return
     */
    Result search(String jsonString);
}
