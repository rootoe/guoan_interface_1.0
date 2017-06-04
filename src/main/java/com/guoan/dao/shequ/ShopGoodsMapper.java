package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.ShopGoods;
import com.guoan.entity.shequ.bo.ShopGoodsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopGoodsMapper {
    int countByExample(ShopGoodsCriteria example);

    int deleteByExample(ShopGoodsCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(ShopGoods record);

    int insertSelective(ShopGoods record);

    List<ShopGoods> selectByExample(ShopGoodsCriteria example);

    ShopGoods selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ShopGoods record, @Param("example") ShopGoodsCriteria example);

    int updateByExample(@Param("record") ShopGoods record, @Param("example") ShopGoodsCriteria example);

    int updateByPrimaryKeySelective(ShopGoods record);

    int updateByPrimaryKey(ShopGoods record);
}