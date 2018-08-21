package com.sf.shiva.oms.ht.mapper;

import com.sf.shiva.oms.ht.domain.HbaseQueryCfg;
import com.sf.shiva.oms.ht.domain.HbaseQueryCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author 01369626
 */
public interface HbaseQueryCfgMapper {
    int countByExample(HbaseQueryCfgExample example);

    int deleteByExample(HbaseQueryCfgExample example);

    int deleteByPrimaryKey(String id);

    int insert(HbaseQueryCfg record);

    int insertSelective(HbaseQueryCfg record);

    List<HbaseQueryCfg> selectByExample(HbaseQueryCfgExample example);

    HbaseQueryCfg selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") HbaseQueryCfg record, @Param("example") HbaseQueryCfgExample example);

    int updateByExample(@Param("record") HbaseQueryCfg record, @Param("example") HbaseQueryCfgExample example);

    int updateByPrimaryKeySelective(HbaseQueryCfg record);

    int updateByPrimaryKey(HbaseQueryCfg record);
}