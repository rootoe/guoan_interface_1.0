package com.guoan.service.guoan.impl;

import com.guoan.dao.shequ.ActivityMapper;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.entity.shequ.bo.Activity;
import com.guoan.entity.shequ.bo.ActivityCriteria;
import com.guoan.service.guoan.AppActivityService;
import com.guoan.service.guoan.AppSystemService;
import com.guoan.service.redis.RedisService;
import com.guoan.utils.collections.ListUtils;
import com.guoan.utils.json.JsonDateValueProcessor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 活动
 * Created by zhaotongbeyond@qq.com on 2015/5/27.
 */
@Service
public class AppActivityServiceImpl extends RedisService implements AppActivityService {

    @Autowired
    AppSystemService appSystemService;

    @Autowired
    ActivityMapper activityMapper;

    @Override
    public Result list(String jsonString) {
        Result result = new Result();
        JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
        JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));
        // 分页
        int page = paramsObject.getInt("page");
        // 区域
        int area = paramsObject.getInt("area");

        JSONArray WorkOrderVOJSONArray = new JSONArray();

        List<Activity> activityList = new ArrayList<Activity>();

        ActivityCriteria activityCriteria = new ActivityCriteria();
        if(0 != area){
            // 根据地区id获得门店id
            Map<String, Object> storeMap = appSystemService.getStoreByArea(area);
            Object storeId = storeMap.get("id");
            if(null == storeId){
                result.setCode(CodeEnum.ActivityGetNull.getValue());
                result.setMessage(CodeEnum.ActivityGetNull.getDescription());
                return result;
            }
            activityCriteria.createCriteria().andStoreIdEqualTo((Long) storeId);
        }else {
            activityCriteria.createCriteria();
        }

        int count = activityMapper.countByExample(activityCriteria);

        if (page < 1) {
            page = 1;
        }
        activityCriteria.setOrderByClause("update_time desc");
        activityCriteria.setLimitStart((page - 1) * 10);
        activityCriteria.setLimitEnd(10);
        activityList = activityMapper.selectByExample(activityCriteria);

        if(ListUtils.isEmpty(activityList)){
            result.setCode(CodeEnum.ActivityGetNull.getValue());
            result.setMessage(CodeEnum.ActivityGetNull.getDescription());
            return result;
        }


        int pageCount = (int) Math.ceil((double) count / 10);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("pageNum", page);
        resultMap.put("pageCount", pageCount);

        // 返回json前对日期数据进行格式化
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        resultMap.put("activityList", JSONArray.fromObject(activityList, jsonConfig));

        result.setCode(CodeEnum.success.getValue());
        result.setMessage(CodeEnum.success.getDescription());
        result.setData(resultMap);

        return result;
    }
}
