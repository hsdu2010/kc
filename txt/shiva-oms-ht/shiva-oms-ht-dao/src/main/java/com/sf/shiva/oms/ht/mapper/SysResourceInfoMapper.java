package com.sf.shiva.oms.ht.mapper;

import com.sf.shiva.oms.ht.domain.SysResourceInfo;
import com.sf.shiva.oms.ht.domain.SysResourceInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author 01369626
 */
public interface SysResourceInfoMapper {
    int countByExample(SysResourceInfoExample example);

    int deleteByExample(SysResourceInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysResourceInfo record);

    int insertSelective(SysResourceInfo record);

    List<SysResourceInfo> selectByExample(SysResourceInfoExample example);

    SysResourceInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysResourceInfo record, @Param("example") SysResourceInfoExample example);

    int updateByExample(@Param("record") SysResourceInfo record, @Param("example") SysResourceInfoExample example);

    int updateByPrimaryKeySelective(SysResourceInfo record);

    int updateByPrimaryKey(SysResourceInfo record);
}