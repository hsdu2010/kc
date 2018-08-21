package com.sf.shiva.oms.ht.mapper;

import com.sf.shiva.oms.ht.domain.SqlCfg;
import com.sf.shiva.oms.ht.domain.SqlCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author 01369626
 */
public interface SqlCfgMapper {
    int countByExample(SqlCfgExample example);

    int deleteByExample(SqlCfgExample example);

    int deleteByPrimaryKey(String sqlId);

    int insert(SqlCfg record);

    int insertSelective(SqlCfg record);

    List<SqlCfg> selectByExample(SqlCfgExample example);

    SqlCfg selectByPrimaryKey(String sqlId);

    int updateByExampleSelective(@Param("record") SqlCfg record, @Param("example") SqlCfgExample example);

    int updateByExample(@Param("record") SqlCfg record, @Param("example") SqlCfgExample example);

    int updateByPrimaryKeySelective(SqlCfg record);

    int updateByPrimaryKey(SqlCfg record);
}