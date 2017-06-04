package com.guoan.dao.work;

import com.guoan.entity.work.bo.WorkUserInfo;
import com.guoan.entity.work.bo.WorkUserInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface WorkUserInfoMapper {
    int countByExample(WorkUserInfoCriteria example);

    int deleteByExample(WorkUserInfoCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(WorkUserInfo record);

    int insertSelective(WorkUserInfo record);

    List<WorkUserInfo> selectByExample(WorkUserInfoCriteria example);

    WorkUserInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WorkUserInfo record, @Param("example") WorkUserInfoCriteria example);

    int updateByExample(@Param("record") WorkUserInfo record, @Param("example") WorkUserInfoCriteria example);

    int updateByPrimaryKeySelective(WorkUserInfo record);

    int updateByPrimaryKey(WorkUserInfo record);

    /**
     * 根据电话获得服务人员的信息(服务人员app使用)
     * @param telephone
     * @return
     */
    WorkUserInfo getSysUserByTelephone(String telephone);

    /**
     * 根据deviceToken把和这个deviceToken一致的用户的deviceToken都删除
     * @param deviceToken
     */
    void updateByWorkUserDeviceToken(String deviceToken);
}