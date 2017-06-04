package com.guoan.dao.shequ;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.guoan.entity.shequ.bo.TreePricePackage;
import org.springframework.stereotype.Repository;

@Repository
public interface SysPricePackageMapper {
	
	List<TreePricePackage> getPricePackageListByParent( Integer parentId );
	
	List<TreePricePackage> getTopPricePackageListByTypeAndArear( @Param("record")  Map<String, Object> record );

}
