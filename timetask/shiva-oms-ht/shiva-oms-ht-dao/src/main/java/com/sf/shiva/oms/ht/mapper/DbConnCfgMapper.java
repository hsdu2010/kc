package com.sf.shiva.oms.ht.mapper;

import com.sf.shiva.oms.ht.domain.DbConnCfg;
import com.sf.shiva.oms.ht.domain.DbConnCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author 01369626
 */
public interface DbConnCfgMapper {
    int countByExample(DbConnCfgExample example);

    int deleteByExample(DbConnCfgExample example);

    int deleteByPrimaryKey(String dbId);

    int insert(DbConnCfg record);

    int insertSelective(DbConnCfg record);

    List<DbConnCfg> selectByExample(DbConnCfgExample example);

    DbConnCfg selectByPrimaryKey(String dbId);

    int updateByExampleSelective(@Param("record") DbConnCfg record, @Param("example") DbConnCfgExample example);

    int updateByExample(@Param("record") DbConnCfg record, @Param("example") DbConnCfgExample example);

    int updateByPrimaryKeySelective(DbConnCfg record);

    int updateByPrimaryKey(DbConnCfg record);
}