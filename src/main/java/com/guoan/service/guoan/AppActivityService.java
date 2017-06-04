package com.guoan.service.guoan;

import com.guoan.entity.base.common.Result;

/**
 * Created by zhaotongbeyond@qq.com on 2015/5/27.
 */
public interface AppActivityService {
    /**
     * 获得活动列表
     * @param jsonString
     * @return
     */
    Result list(String jsonString);
}
