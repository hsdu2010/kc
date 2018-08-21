package com.sf.shiva.oms.ht.mapper;

import com.sf.shiva.oms.ht.domain.WebserviceManager;
import com.sf.shiva.oms.ht.domain.WebserviceManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author 01369626
 */
public interface WebserviceManagerMapper {
    int countByExample(WebserviceManagerExample example);

    int deleteByExample(WebserviceManagerExample example);

    int deleteByPrimaryKey(String id);

    int insert(WebserviceManager record);

    int insertSelective(WebserviceManager record);

    List<WebserviceManager> selectByExampleWithBLOBs(WebserviceManagerExample example);

    List<WebserviceManager> selectByExample(WebserviceManagerExample example);

    WebserviceManager selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WebserviceManager record, @Param("example") WebserviceManagerExample example);

    int updateByExampleWithBLOBs(@Param("record") WebserviceManager record, @Param("example") WebserviceManagerExample example);

    int updateByExample(@Param("record") WebserviceManager record, @Param("example") WebserviceManagerExample example);

    int updateByPrimaryKeySelective(WebserviceManager record);

    int updateByPrimaryKeyWithBLOBs(WebserviceManager record);

    int updateByPrimaryKey(WebserviceManager record);
}