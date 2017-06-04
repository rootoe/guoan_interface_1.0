package com.guoan.service.guoan.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guoan.dao.shequ.ShopGoodsMapper;
import com.guoan.dao.shequ.ShopGoodsTypeMapper;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.entity.shequ.bo.AppUserInfo;
import com.guoan.entity.shequ.bo.ShopGoods;
import com.guoan.entity.shequ.bo.ShopGoodsCriteria;
import com.guoan.entity.shequ.bo.ShopGoodsType;
import com.guoan.entity.shequ.bo.ShopGoodsTypeCriteria;
import com.guoan.service.guoan.AppSystemService;
import com.guoan.service.guoan.CommunityBuyOnSbService;
import com.guoan.service.redis.RedisService;
import com.guoan.utils.StringUtils;
import com.guoan.utils.collections.ListUtils;
import com.guoan.utils.json.JsonDateValueProcessor;
import com.guoan.utils.redis.RedisContants;

@Service
public class CommunityBuyOnSbServiceImpl extends RedisService implements CommunityBuyOnSbService {

	
	    @Autowired
	    AppSystemService appSystemService;

	    @Autowired
	    ShopGoodsMapper shopGoodsMapper;

	    @Autowired
	    ShopGoodsTypeMapper shopGoodsTypeMapper;
	    
	    
	    @Override
	    public Result index(String jsonString) {
	        Result result = new Result();

	        JSONObject paramsJson = JSONObject.fromObject(jsonString).getJSONObject("params");
	        String token = JSONObject.fromObject(jsonString).getString("token");

	        // 获得用户
	        AppUserInfo user = new AppUserInfo();
	        user = super.getRedisUserObject(RedisContants.USER_APP, token,user.getClass());

	        if (null == user || StringUtils.isEmpty(user.getUserId())) {
	            result.setCode(CodeEnum.tokenErr.getValue());
	            result.setMessage(CodeEnum.tokenErr.getDescription());
	            return result;
	        }
	        // 区域
	        int area = paramsJson.getInt("area");

	        // 返回json前对日期数据进行格式化
	        JsonConfig jsonConfig = new JsonConfig();
	        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());

	        JSONArray resultJsonArray = new JSONArray();

	        ShopGoodsTypeCriteria shopGoodsTypeCriteria = new ShopGoodsTypeCriteria();

	        if(0 != area){
	            // 根据地区id获得门店id
	            Map<String, Object> storeMap = appSystemService.getStoreByArea(area);
	            Object storeId = storeMap.get("id");
	            if(null == storeId){
	                result.setCode(CodeEnum.ShopGoodsGetNull.getValue());
	                result.setMessage(CodeEnum.ShopGoodsGetNull.getDescription());
	                return result;
	            }
	            shopGoodsTypeCriteria.createCriteria().andStoreIdEqualTo((Long) storeId);
	        } else {
	            shopGoodsTypeCriteria.createCriteria();
	        }
	        shopGoodsTypeCriteria.setOrderByClause("order_no asc");
	        List<ShopGoodsType> shopGoodsTypeList = shopGoodsTypeMapper.selectByExample(shopGoodsTypeCriteria);

	        if(ListUtils.isEmpty(shopGoodsTypeList)){
	            result.setCode(CodeEnum.ShopGoodsTypeGetNull.getValue());
	            result.setMessage(CodeEnum.ShopGoodsTypeGetNull.getDescription());
	            return result;
	        }


	        for (ShopGoodsType shopGoodsType : shopGoodsTypeList){
	            JSONObject shopGoodsTypeJSONObject = new JSONObject();
	            ShopGoodsCriteria shopGoodsCriteria = new ShopGoodsCriteria();

	            
	            shopGoodsCriteria.createCriteria().andTypeIdEqualTo(shopGoodsType.getId()).andStatusEqualTo(0).andIsRecommendEqualTo((short)1);

	            shopGoodsCriteria.setLimitStart(0);
	            shopGoodsCriteria.setLimitEnd(4);
	            shopGoodsCriteria.setOrderByClause("price asc");
	            List<ShopGoods> shopGoodsList = shopGoodsMapper.selectByExample(shopGoodsCriteria);

	            if(!ListUtils.isEmpty(shopGoodsList)){
	            	shopGoodsTypeJSONObject.put("shopGoodsTypeId", shopGoodsType.getId());
	                shopGoodsTypeJSONObject.put("shopGoodsTypeName", shopGoodsType.getName());
	                shopGoodsTypeJSONObject.put("shopGoodsList", JSONArray.fromObject(shopGoodsList, jsonConfig));
	                resultJsonArray.add(shopGoodsTypeJSONObject);
	            }
	        }

	        result.setCode(CodeEnum.success.getValue());
	        result.setMessage(CodeEnum.success.getDescription());
	        result.setData(JSONArray.fromObject(resultJsonArray));

	        return result;
	    }

	    @Override
	    public Result info(String jsonString) {
	        Result result = new Result();

	        JSONObject paramsJson = JSONObject.fromObject(jsonString).getJSONObject("params");
	        String token = JSONObject.fromObject(jsonString).getString("token");

	        // 获得用户
	        AppUserInfo user = new AppUserInfo();
	        user = super.getRedisUserObject(RedisContants.USER_APP, token,user.getClass());

	        if (null == user || StringUtils.isEmpty(user.getUserId())) {
	            result.setCode(CodeEnum.tokenErr.getValue());
	            result.setMessage(CodeEnum.tokenErr.getDescription());
	            return result;
	        }

	        // 商品id
	        String shopGoodsId = paramsJson.getString("shopGoodsId");

	        if("".equals(shopGoodsId)){
	            result.setCode(CodeEnum.ShopGoodsGetNull.getValue());
	            result.setMessage(CodeEnum.ShopGoodsGetNull.getDescription());
	            return result;
	        }

	        ShopGoods shopGoods = shopGoodsMapper.selectByPrimaryKey(shopGoodsId);

	        if(null == shopGoods){
	            result.setCode(CodeEnum.ShopGoodsGetNull.getValue());
	            result.setMessage(CodeEnum.ShopGoodsGetNull.getDescription());
	            return result;
	        }

	        // 返回json前对日期数据进行格式化
	        JsonConfig jsonConfig = new JsonConfig();
	        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());

	        result.setCode(CodeEnum.success.getValue());
	        result.setMessage(CodeEnum.success.getDescription());
	        result.setData(JSONObject.fromObject(shopGoods,jsonConfig));

	        return result;
	    }

	    @Override
	    public Result buy(String jsonString) {
	        return null;
	    }

	    @Override
	    public Result list(String jsonString) {
	        Result result = new Result();

	        JSONObject paramsJson = JSONObject.fromObject(jsonString).getJSONObject("params");
	        String token = JSONObject.fromObject(jsonString).getString("token");

	        // 获得用户
	        AppUserInfo user = new AppUserInfo();
	        user = super.getRedisUserObject(RedisContants.USER_APP, token,user.getClass());

	        if (null == user || StringUtils.isEmpty(user.getUserId())) {
	            result.setCode(CodeEnum.tokenErr.getValue());
	            result.setMessage(CodeEnum.tokenErr.getDescription());
	            return result;
	        }

	        // 分页
	        int page = paramsJson.getInt("page");
	        // 商品分类id
	        long typeId = paramsJson.getInt("typeId");

	        ShopGoodsCriteria shopGoodsCriteria = new ShopGoodsCriteria();
	        shopGoodsCriteria.createCriteria().andTypeIdEqualTo(typeId).andStatusEqualTo(0);
	        shopGoodsCriteria.setOrderByClause("price asc");
	        int count = shopGoodsMapper.countByExample(shopGoodsCriteria);

	        if (page < 1) {
	            page = 1;
	        }

	        JSONArray resultJsonArray = new JSONArray();

	        shopGoodsCriteria.setOrderByClause("create_time desc");
	        shopGoodsCriteria.setLimitStart((page - 1) * 10);
	        shopGoodsCriteria.setLimitEnd(10);
	        List<ShopGoods> shopGoodsList = shopGoodsMapper.selectByExample(shopGoodsCriteria);

	        if(ListUtils.isEmpty(shopGoodsList)){
	            result.setCode(CodeEnum.ShopGoodsGetNull.getValue());
	            result.setMessage(CodeEnum.ShopGoodsGetNull.getDescription());
	            return result;
	        }

	        // 返回json前对日期数据进行格式化
	        JsonConfig jsonConfig = new JsonConfig();
	        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());


	        int pageCount = (int) Math.ceil((double) count / 10);

	        Map<String, Object> resultMap = new HashMap<String, Object>();
	        resultMap.put("pageNum", page);
	        resultMap.put("pageCount", pageCount);
	        resultMap.put("shopGoodsList", JSONArray.fromObject(shopGoodsList, jsonConfig));

	        result.setCode(CodeEnum.success.getValue());
	        result.setMessage(CodeEnum.success.getDescription());
	        result.setData(resultMap);

	        return result;
	    }

	    @Override
	    public Result search(String jsonString) {
	        Result result = new Result();

	        JSONObject paramsJson = JSONObject.fromObject(jsonString).getJSONObject("params");
	        String token = JSONObject.fromObject(jsonString).getString("token");

	        // 获得用户
	        AppUserInfo user = new AppUserInfo();
	        user = super.getRedisUserObject(RedisContants.USER_APP, token,user.getClass());

	        if (null == user || StringUtils.isEmpty(user.getUserId())) {
	            result.setCode(CodeEnum.tokenErr.getValue());
	            result.setMessage(CodeEnum.tokenErr.getDescription());
	            return result;
	        }

	        // 地区id
	        Integer area = paramsJson.getInt("area");
	        String name = paramsJson.getString("name");

	        ShopGoodsCriteria shopGoodsCriteria = new ShopGoodsCriteria();

	        if(StringUtils.isEmpty(name.trim())){
	            result.setCode(CodeEnum.ShopGoodsNameIsNull.getValue());
	            result.setMessage(CodeEnum.ShopGoodsNameIsNull.getDescription());
	            return result;
	        }

	        if(0 != area){
	            // 根据地区id获得门店id
	            Map<String, Object> storeMap = appSystemService.getStoreByArea(area);
	            Object storeId = storeMap.get("id");
	            if(null == storeId){
	                result.setCode(CodeEnum.ShopGoodsGetNull.getValue());
	                result.setMessage(CodeEnum.ShopGoodsGetNull.getDescription());
	                return result;
	            }
	            shopGoodsCriteria.createCriteria().andNameLike("%" + name + "%").andStoreIdEqualTo((Long) storeId).andStatusEqualTo(0);
	        }

	        List<ShopGoods> shopGoodsList = shopGoodsMapper.selectByExample(shopGoodsCriteria);

	        if(ListUtils.isEmpty(shopGoodsList)){
	            result.setCode(CodeEnum.ShopGoodsGetNull.getValue());
	            result.setMessage(CodeEnum.ShopGoodsGetNull.getDescription());
	            return result;
	        }

	        // 返回json前对日期数据进行格式化
	        JsonConfig jsonConfig = new JsonConfig();
	        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());

	        result.setCode(CodeEnum.success.getValue());
	        result.setMessage(CodeEnum.success.getDescription());
	        result.setData(JSONArray.fromObject(shopGoodsList,jsonConfig));

	        return result;
	    }

}
