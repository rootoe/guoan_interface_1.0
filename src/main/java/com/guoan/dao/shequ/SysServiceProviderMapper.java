package com.guoan.dao.shequ;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysServiceProviderMapper {
	
	List<Map<String, Object>> getProviderListByTypeAndArea( @Param("record") Map<String, Object> record );
	
	Map<String, Object> getServiceProviderById( Integer id );

}
