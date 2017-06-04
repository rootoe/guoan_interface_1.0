package com.guoan.service.guoan;

import com.guoan.entity.base.common.Result;

/**
 * Created by zhaotongbeyond@qq.com on 2015/6/2.
 */
public interface ChatService {


    Result send(String jsonString, String savePath);
}
