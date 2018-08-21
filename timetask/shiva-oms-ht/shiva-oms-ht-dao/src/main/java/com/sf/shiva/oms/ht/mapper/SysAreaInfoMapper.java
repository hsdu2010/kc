package com.sf.shiva.oms.ht.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.sf.shiva.oms.ht.domain.SysAreaInfo;
import com.sf.shiva.oms.ht.domain.SysAreaInfoExample;

/**
 * 
 * @author 01139932
 */
public interface SysAreaInfoMapper {
    int countByExample(SysAreaInfoExample example);

    int deleteByExample(SysAreaInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysAreaInfo record);

    int insertSelective(SysAreaInfo record);

    List<SysAreaInfo> selectByExample(SysAreaInfoExample example);

    SysAreaInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysAreaInfo record, @Param("example") SysAreaInfoExample example);

    int updateByExample(@Param("record") SysAreaInfo record, @Param("example") SysAreaInfoExample example);

    int updateByPrimaryKeySelective(SysAreaInfo record);

    int updateByPrimaryKey(SysAreaInfo record);
}