package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.SysUser;
import com.guoan.entity.shequ.bo.SysUserCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper {
    int countByExample(SysUserCriteria example);

    int deleteByExample(SysUserCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserCriteria example);

    SysUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserCriteria example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserCriteria example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    /**
     * 根据服务区域取对应人员
     * @param area_id
     * @return
     */
    List<SysUser> getSysUserByArea( String area_id );
    
    /**
     * 根据服务提供商取对应人员
     * @param providerId
     * @return
     */
    List<SysUser> getSysUserByProvider( int providerId );
}