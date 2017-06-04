package com.guoan.dao.shequ;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysStoreMapper {
	
	List<Map<String, Object>> getStoreByArea( @Param("areaList") List<Integer> areaList );

	Integer getStoreAreaByStoreId(Long storeId);
}
