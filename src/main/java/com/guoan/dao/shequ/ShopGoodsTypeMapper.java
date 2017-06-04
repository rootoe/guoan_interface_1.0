package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.ShopGoodsType;
import com.guoan.entity.shequ.bo.ShopGoodsTypeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopGoodsTypeMapper {
    int countByExample(ShopGoodsTypeCriteria example);

    int deleteByExample(ShopGoodsTypeCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopGoodsType record);

    int insertSelective(ShopGoodsType record);

    List<ShopGoodsType> selectByExample(ShopGoodsTypeCriteria example);

    ShopGoodsType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopGoodsType record, @Param("example") ShopGoodsTypeCriteria example);

    int updateByExample(@Param("record") ShopGoodsType record, @Param("example") ShopGoodsTypeCriteria example);

    int updateByPrimaryKeySelective(ShopGoodsType record);

    int updateByPrimaryKey(ShopGoodsType record);
}