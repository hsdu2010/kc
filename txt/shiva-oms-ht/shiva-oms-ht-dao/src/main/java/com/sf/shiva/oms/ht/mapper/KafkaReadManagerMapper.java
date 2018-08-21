package com.sf.shiva.oms.ht.mapper;

import com.sf.shiva.oms.ht.domain.KafkaReadManager;
import com.sf.shiva.oms.ht.domain.KafkaReadManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author 01369626
 */
public interface KafkaReadManagerMapper {
    int countByExample(KafkaReadManagerExample example);

    int deleteByExample(KafkaReadManagerExample example);

    int deleteByPrimaryKey(String id);

    int insert(KafkaReadManager record);

    int insertSelective(KafkaReadManager record);

    List<KafkaReadManager> selectByExample(KafkaReadManagerExample example);

    KafkaReadManager selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") KafkaReadManager record, @Param("example") KafkaReadManagerExample example);

    int updateByExample(@Param("record") KafkaReadManager record, @Param("example") KafkaReadManagerExample example);

    int updateByPrimaryKeySelective(KafkaReadManager record);

    int updateByPrimaryKey(KafkaReadManager record);
}