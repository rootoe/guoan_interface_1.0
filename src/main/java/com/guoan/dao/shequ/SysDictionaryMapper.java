package com.guoan.dao.shequ;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;
import java.util.Map;

@Repository
public interface SysDictionaryMapper {

	List<Map<String, Object>> getDictionaryByType( String code );
	List<Map<String, Object>> getDictionaryById( Integer id );
}
