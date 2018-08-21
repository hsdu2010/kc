package com.sf.shiva.oms.ht.mapper;

import com.sf.shiva.oms.ht.domain.KafkaWriteManager;
import com.sf.shiva.oms.ht.domain.KafkaWriteManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author 01369626
 */
public interface KafkaWriteManagerMapper {
    int countByExample(KafkaWriteManagerExample example);

    int deleteByExample(KafkaWriteManagerExample example);

    int deleteByPrimaryKey(String id);

    int insert(KafkaWriteManager record);

    int insertSelective(KafkaWriteManager record);

    List<KafkaWriteManager> selectByExample(KafkaWriteManagerExample example);

    KafkaWriteManager selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") KafkaWriteManager record, @Param("example") KafkaWriteManagerExample example);

    int updateByExample(@Param("record") KafkaWriteManager record, @Param("example") KafkaWriteManagerExample example);

    int updateByPrimaryKeySelective(KafkaWriteManager record);

    int updateByPrimaryKey(KafkaWriteManager record);
}