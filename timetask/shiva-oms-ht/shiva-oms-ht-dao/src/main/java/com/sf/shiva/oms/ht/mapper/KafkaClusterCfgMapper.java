package com.sf.shiva.oms.ht.mapper;

import com.sf.shiva.oms.ht.domain.KafkaClusterCfg;
import com.sf.shiva.oms.ht.domain.KafkaClusterCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author 01369626
 */
public interface KafkaClusterCfgMapper {
    int countByExample(KafkaClusterCfgExample example);

    int deleteByExample(KafkaClusterCfgExample example);

    int deleteByPrimaryKey(String id);

    int insert(KafkaClusterCfg record);

    int insertSelective(KafkaClusterCfg record);

    List<KafkaClusterCfg> selectByExample(KafkaClusterCfgExample example);

    KafkaClusterCfg selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") KafkaClusterCfg record, @Param("example") KafkaClusterCfgExample example);

    int updateByExample(@Param("record") KafkaClusterCfg record, @Param("example") KafkaClusterCfgExample example);

    int updateByPrimaryKeySelective(KafkaClusterCfg record);

    int updateByPrimaryKey(KafkaClusterCfg record);
}