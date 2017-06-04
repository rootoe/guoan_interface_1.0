package com.guoan.service.guoan.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.guoan.utils.StringUtils;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guoan.dao.shequ.AppRollAdMapper;
import com.guoan.dao.shequ.SysAddressMapper;
import com.guoan.dao.shequ.SysDictionaryMapper;
import com.guoan.dao.shequ.SysPricePackageMapper;
import com.guoan.dao.shequ.SysServiceProviderMapper;
import com.guoan.dao.shequ.SysStoreMapper;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.entity.shequ.bo.AppRollAd;
import com.guoan.entity.shequ.bo.AppRollAdCriteria;
import com.guoan.entity.shequ.bo.TreePricePackage;
import com.guoan.entity.shequ.bo.TreeStructure;
import com.guoan.service.guoan.AppSystemService;
import com.guoan.service.redis.RedisService;
import com.guoan.utils.Const;
import com.guoan.utils.collections.ListUtils;

@Service
public class AppSystemServiceImpl extends RedisService implements AppSystemService {

	private static final Logger logger = LoggerFactory.getLogger(AppSystemServiceImpl.class);

	@Autowired
	private SysAddressMapper sysAreaMapper;

	@Autowired
	private SysServiceProviderMapper providerMapper;

	@Autowired
	private SysPricePackageMapper packageMapper;

	@Autowired
	private SysDictionaryMapper dictionaryMapper;

	@Autowired
	private SysStoreMapper storeMapper;

	@Autowired
	private AppRollAdMapper rollAdMapper;

	@Override
	public Result getAddressTree() {
		// update by zhaotong
		// 修改部分逻辑.
		Result result = new Result();
		List<TreeStructure> treeList = new ArrayList<TreeStructure>();
		super.getObject(Const.SYS_ADDRESS_TREE, treeList.getClass());
		List<Object> list = super.getList(Const.SYS_ADDRESS_TREE);
		if (ListUtils.isEmpty(list)) {
			logger.debug("未从redis key-> " + Const.SYS_ADDRESS_TREE + " 中取到地址tree...");
			treeList = sysAreaMapper.getTopNote();
			for (Object tree : treeList) {
				this.findChildTree((TreeStructure) tree);
				// super.setList(Const.SYS_ADDRESS_TREE, tree);
			}
			// super.setList(Const.SYS_ADDRESS_TREE, treeList);
			// super.setObject(Const.SYS_ADDRESS_TREE, treeList);
		} else {
			logger.debug("从redis key-> " + Const.SYS_ADDRESS_TREE + " 中取到地址tree...");
			treeList = new ArrayList<TreeStructure>();
			for (Object object : list) {
				TreeStructure treeStructure = new TreeStructure();
				treeStructure = (TreeStructure) object;
				treeList.add(treeStructure);
			}
		}

		if(ListUtils.isEmpty(treeList)){
			result.setCode(CodeEnum.AddressGetNull.getValue());
			result.setMessage(CodeEnum.AddressGetNull.getDescription());
			return result;
		}

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());
		Map<String, List<TreeStructure>> treeMap = new HashMap<String, List<TreeStructure>>();
		treeMap.put("tree", treeList);
		result.setData(treeMap);

		return result;
	}

	private void findChildTree(TreeStructure node) {
		
		List<TreeStructure> list = sysAreaMapper.getAddressTree(Integer.valueOf(node.getId()));
		
		if (list != null && !list.isEmpty()) {
			list.parallelStream().forEach(child -> findChildTree(child));
			node.setChildren(list);
		}
	}

	/**
	 * 根据类型和区域,取得服务商列表
	 * 
	 * @author gaochao
	 */
	@Override
	public Result getProviderListByTypeAndArea(String jsonString) {

		Result result = new Result();

		JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
		JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));

		String type = paramsObject.getString("type");
		int area = paramsObject.getInt("area");
		Map<String, Object> providerMap = new HashMap<String, Object>();
		providerMap.put("type", type);
		providerMap.put("areaList", Collections.singletonList(area));
		List<Map<String, Object>> providerList = this.providerMapper.getProviderListByTypeAndArea(providerMap);

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		// Map<String, List<Map<String, Object>>> providerMap = new
		// HashMap<String, List<Map<String, Object>>>();
		// providerMap.put("providerList", providerList);

		result.setData(providerList);

		return result;
	}

	/**
	 * 根据类型、区域及父区域,取得服务商列表
	 * 
	 * @author gaochao
	 */
	@Override
	public Result getProviderListByTypeAndForwardArea(String jsonString) {

		Result result = new Result();

		JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
		JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));

		String type = paramsObject.getString("type");
		int area = paramsObject.getInt("area");
		List<Integer> parentAreaIdList = this.getAllParentAreaId(area);

		Map<String, Object> providerMap = new HashMap<String, Object>();
		providerMap.put("type", type);
		providerMap.put("areaList", parentAreaIdList);
		List<Map<String, Object>> providerList = this.providerMapper.getProviderListByTypeAndArea(providerMap);

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		result.setData(providerList);

		return result;
	}

	/**
	 * 根据code,取得数据字典
	 * 
	 * @author gaochao
	 */
	@Override
	public Result getDictionaryByType(String jsonString) {

		Result result = new Result();

		JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
		JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));

		String code = paramsObject.getString("code");
		List<Map<String, Object>> dicList = this.dictionaryMapper.getDictionaryByType(code);

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		Map<String, List<Map<String, Object>>> dicMap = new HashMap<String, List<Map<String, Object>>>();
		dicMap.put("dicList", dicList);

		result.setData(dicMap);

		return result;
	}

	/**
	 * 根据级别,取得区域
	 * 
	 * @author gaochao
	 */
	@Override
	public Result getAddressByLevel(String jsonString) {

		Result result = new Result();

		JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
		JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));

		int level = paramsObject.getInt("level");

		List<Map<String, String>> addrList = this.sysAreaMapper.getAddressByLevel(level);

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());
		result.setData(addrList);

		return result;
	}

	/**
	 * 根据类型和区域,取得套餐树
	 * 
	 * @author gaochao
	 */
	@Override
	public Result getPricePackageListByTypeAndArea(String jsonString) {

		Result result = new Result();

		JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
		JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));

		String type = paramsObject.getString("type");
		int area = paramsObject.getInt("area");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("type", type);
		paramMap.put("areaList", Collections.singletonList(area));
		List<TreePricePackage> topList = this.packageMapper.getTopPricePackageListByTypeAndArear(paramMap);

		topList.parallelStream().forEach(top -> this.findChildPackage(top));

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());
		result.setData(topList);

		return result;
	}

	/**
	 * 根据类型类型、区域及父区域,取得套餐树
	 * 
	 * @author gaochao
	 */
	@Override
	public Result getPricePackageListByTypeAndForwardArea(String jsonString) {

		Result result = new Result();

		JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
		JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));

		String type = paramsObject.getString("type");
		int area = paramsObject.getInt("area");
		List<Integer> parentAreaIdList = this.getAllParentAreaId(area);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("type", type);
		paramMap.put("areaList", parentAreaIdList);
		List<TreePricePackage> topList = this.packageMapper.getTopPricePackageListByTypeAndArear(paramMap);

		topList.parallelStream().forEach(top -> this.findChildPackage(top));

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());
		result.setData(topList);

		return result;
	}
	
	/**
	 * 根据地区, 返回所属店面
	 * @param area
	 * @return
	 */
	@Override
	public Map<String, Object> getStoreByArea( int area ){
		
		Map<String, Object> storeMap = new HashMap<String, Object>();
		
		List<Integer> parentAreaIdList = this.getAllParentAreaId(area);
		List<Map<String,Object>> storeList = this.storeMapper.getStoreByArea(parentAreaIdList);
		
		if( storeList != null && storeList.size() > 0 ){
			storeMap = storeList.get(0);
		}
		return storeMap;
	}

	@Override
	public Result getRollAdList(String jsonString) {

		Result result = new Result();

		JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
		JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));
		String position = paramsObject.getString("position");

		if(StringUtils.isEmpty(position)){
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
			return result;
		}

		AppRollAdCriteria adCriteria = new AppRollAdCriteria();
		adCriteria.createCriteria().andStatusEqualTo(0).andPositionEqualTo(position);
		adCriteria.setOrderByClause("seq");

		List<AppRollAd> adList = this.rollAdMapper.selectByExample(adCriteria);

		result.setData(adList);
		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		return result;
	}

	private void findChildPackage(TreePricePackage node) {

		List<TreePricePackage> list = this.packageMapper.getPricePackageListByParent(Integer.valueOf(node.getId()));

		if (list != null && !list.isEmpty()) {
			
			list.parallelStream().forEach(child -> findChildPackage(child));
			node.setChildren(list);
		}
	}

	private List<Integer> getAllParentAreaId(int areaId) {

		List<Integer> pathNodeIdList = new ArrayList<Integer>();

		Map<String, Long> pathNodeId = this.sysAreaMapper.getPathNodeId(areaId);
		pathNodeId.forEach((id, val) -> {
			if (val != null)
				pathNodeIdList.add(val.intValue());
		});

		return pathNodeIdList;
	}

}
