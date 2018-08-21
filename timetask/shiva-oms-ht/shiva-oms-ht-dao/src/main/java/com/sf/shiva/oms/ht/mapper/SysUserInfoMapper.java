package com.sf.shiva.oms.ht.mapper;

import com.sf.shiva.oms.ht.domain.SysUserInfo;
import com.sf.shiva.oms.ht.domain.SysUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author 01139932
 */
public interface SysUserInfoMapper {
    int countByExample(SysUserInfoExample example);

    int deleteByExample(SysUserInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUserInfo record);

    int insertSelective(SysUserInfo record);

    List<SysUserInfo> selectByExample(SysUserInfoExample example);

    SysUserInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysUserInfo record, @Param("example") SysUserInfoExample example);

    int updateByExample(@Param("record") SysUserInfo record, @Param("example") SysUserInfoExample example);

    int updateByPrimaryKeySelective(SysUserInfo record);

    int updateByPrimaryKey(SysUserInfo record);
}