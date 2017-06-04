package com.guoan.service.guoan;

import java.util.Map;

import com.guoan.entity.base.common.Result;

public interface AppSystemService {
	
	public Result getAddressTree();
	
	public Result getProviderListByTypeAndArea( String jsonString );
	
	public Result getDictionaryByType( String jsonString );
	
	public Result getAddressByLevel( String jsonString );
	
	public Result getPricePackageListByTypeAndArea( String jsonString );
	
	public Result getProviderListByTypeAndForwardArea( String jsonString );
	
	public Result getPricePackageListByTypeAndForwardArea( String jsonString );

	public Map<String, Object> getStoreByArea( int area );
	
	public Result getRollAdList(String jsonString);
}
