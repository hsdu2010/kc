package com.sf.shiva.oms.ht.mapper;

import com.sf.shiva.oms.ht.domain.HttpManager;
import com.sf.shiva.oms.ht.domain.HttpManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author 01369626
 */
public interface HttpManagerMapper {
    int countByExample(HttpManagerExample example);

    int deleteByExample(HttpManagerExample example);

    int deleteByPrimaryKey(String id);

    int insert(HttpManager record);

    int insertSelective(HttpManager record);

    List<HttpManager> selectByExample(HttpManagerExample example);

    HttpManager selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") HttpManager record, @Param("example") HttpManagerExample example);

    int updateByExample(@Param("record") HttpManager record, @Param("example") HttpManagerExample example);

    int updateByPrimaryKeySelective(HttpManager record);

    int updateByPrimaryKey(HttpManager record);
}