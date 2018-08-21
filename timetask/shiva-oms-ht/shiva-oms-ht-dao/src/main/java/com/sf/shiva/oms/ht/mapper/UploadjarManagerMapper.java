package com.sf.shiva.oms.ht.mapper;

import com.sf.shiva.oms.ht.domain.UploadjarManager;
import com.sf.shiva.oms.ht.domain.UploadjarManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author 01369626
 */
public interface UploadjarManagerMapper {
    int countByExample(UploadjarManagerExample example);

    int deleteByExample(UploadjarManagerExample example);

    int deleteByPrimaryKey(String id);

    int insert(UploadjarManager record);

    int insertSelective(UploadjarManager record);

    List<UploadjarManager> selectByExample(UploadjarManagerExample example);

    UploadjarManager selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UploadjarManager record, @Param("example") UploadjarManagerExample example);

    int updateByExample(@Param("record") UploadjarManager record, @Param("example") UploadjarManagerExample example);

    int updateByPrimaryKeySelective(UploadjarManager record);

    int updateByPrimaryKey(UploadjarManager record);
}