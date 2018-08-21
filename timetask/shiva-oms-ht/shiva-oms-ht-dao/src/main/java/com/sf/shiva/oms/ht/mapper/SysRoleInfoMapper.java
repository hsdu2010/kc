package com.sf.shiva.oms.ht.mapper;

import com.sf.shiva.oms.ht.domain.SysRoleInfo;
import com.sf.shiva.oms.ht.domain.SysRoleInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author 01139932
 */
public interface SysRoleInfoMapper {
    int countByExample(SysRoleInfoExample example);

    int deleteByExample(SysRoleInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysRoleInfo record);

    int insertSelective(SysRoleInfo record);

    List<SysRoleInfo> selectByExample(SysRoleInfoExample example);

    SysRoleInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysRoleInfo record, @Param("example") SysRoleInfoExample example);

    int updateByExample(@Param("record") SysRoleInfo record, @Param("example") SysRoleInfoExample example);

    int updateByPrimaryKeySelective(SysRoleInfo record);

    int updateByPrimaryKey(SysRoleInfo record);
}