package com.guoan.dao.shequ;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.guoan.entity.shequ.bo.TreeStructure;
import org.springframework.stereotype.Repository;

@Repository
public interface SysAddressMapper {

	List<TreeStructure> getAddressTree(@Param("parentId") Integer parentId);

	List<TreeStructure> getTopNote();

	List<Map<String, String>> getAddressByLevel(Integer level);
	
	Map<String, Long> getPathNodeId(int areaId);

	List<Integer> getAreaIdByUserId(Long workUserId);

}
