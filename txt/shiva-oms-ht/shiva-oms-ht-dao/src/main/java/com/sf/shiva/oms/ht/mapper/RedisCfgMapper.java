package com.sf.shiva.oms.ht.mapper;

import com.sf.shiva.oms.ht.domain.RedisCfg;
import com.sf.shiva.oms.ht.domain.RedisCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author 01369626
 */
public interface RedisCfgMapper {
    int countByExample(RedisCfgExample example);

    int deleteByExample(RedisCfgExample example);

    int deleteByPrimaryKey(String id);

    int insert(RedisCfg record);

    int insertSelective(RedisCfg record);

    List<RedisCfg> selectByExample(RedisCfgExample example);

    RedisCfg selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RedisCfg record, @Param("example") RedisCfgExample example);

    int updateByExample(@Param("record") RedisCfg record, @Param("example") RedisCfgExample example);

    int updateByPrimaryKeySelective(RedisCfg record);

    int updateByPrimaryKey(RedisCfg record);
}