package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.AppFeedback;
import com.guoan.entity.shequ.bo.AppFeedbackCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppFeedbackMapper {
    int countByExample(AppFeedbackCriteria example);

    int deleteByExample(AppFeedbackCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(AppFeedback record);

    int insertSelective(AppFeedback record);

    List<AppFeedback> selectByExample(AppFeedbackCriteria example);

    AppFeedback selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AppFeedback record, @Param("example") AppFeedbackCriteria example);

    int updateByExample(@Param("record") AppFeedback record, @Param("example") AppFeedbackCriteria example);

    int updateByPrimaryKeySelective(AppFeedback record);

    int updateByPrimaryKey(AppFeedback record);
}