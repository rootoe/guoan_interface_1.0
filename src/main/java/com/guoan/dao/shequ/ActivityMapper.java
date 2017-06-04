package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.Activity;
import com.guoan.entity.shequ.bo.ActivityCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityMapper {
    int countByExample(ActivityCriteria example);

    int deleteByExample(ActivityCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Activity record);

    int insertSelective(Activity record);

    List<Activity> selectByExampleWithBLOBs(ActivityCriteria example);

    List<Activity> selectByExample(ActivityCriteria example);

    Activity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Activity record, @Param("example") ActivityCriteria example);

    int updateByExampleWithBLOBs(@Param("record") Activity record, @Param("example") ActivityCriteria example);

    int updateByExample(@Param("record") Activity record, @Param("example") ActivityCriteria example);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKeyWithBLOBs(Activity record);

    int updateByPrimaryKey(Activity record);


}