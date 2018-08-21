package com.sf.shiva.oms.ht.mapper;

import com.sf.shiva.oms.ht.domain.RpcManager;
import com.sf.shiva.oms.ht.domain.RpcManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author 01369626
 */
public interface RpcManagerMapper {
    int countByExample(RpcManagerExample example);

    int deleteByExample(RpcManagerExample example);

    int deleteByPrimaryKey(String id);

    int insert(RpcManager record);

    int insertSelective(RpcManager record);

    List<RpcManager> selectByExample(RpcManagerExample example);

    RpcManager selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RpcManager record, @Param("example") RpcManagerExample example);

    int updateByExample(@Param("record") RpcManager record, @Param("example") RpcManagerExample example);

    int updateByPrimaryKeySelective(RpcManager record);

    int updateByPrimaryKey(RpcManager record);
}