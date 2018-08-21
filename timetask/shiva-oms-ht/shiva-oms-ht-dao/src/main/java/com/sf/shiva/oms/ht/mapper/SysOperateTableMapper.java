package com.sf.shiva.oms.ht.mapper;

import com.sf.shiva.oms.ht.domain.SysOperateTable;
import com.sf.shiva.oms.ht.domain.SysOperateTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author 01139932
 */
public interface SysOperateTableMapper {
    int countByExample(SysOperateTableExample example);

    int deleteByExample(SysOperateTableExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysOperateTable record);

    int insertSelective(SysOperateTable record);

    List<SysOperateTable> selectByExample(SysOperateTableExample example);

    SysOperateTable selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysOperateTable record, @Param("example") SysOperateTableExample example);

    int updateByExample(@Param("record") SysOperateTable record, @Param("example") SysOperateTableExample example);

    int updateByPrimaryKeySelective(SysOperateTable record);

    int updateByPrimaryKey(SysOperateTable record);
}