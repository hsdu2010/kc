package com.sf.shiva.oms.ht.mapper;

import com.sf.shiva.oms.ht.domain.OperationInfo;
import com.sf.shiva.oms.ht.domain.OperationInfoExample;
import com.sf.shiva.oms.ht.domain.OperationInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author 01369626
 */
public interface OperationInfoMapper {
    int countByExample(OperationInfoExample example);

    int deleteByExample(OperationInfoExample example);

    int deleteByPrimaryKey(OperationInfoKey key);

    int insert(OperationInfo record);

    int insertSelective(OperationInfo record);

    List<OperationInfo> selectByExample(OperationInfoExample example);

    OperationInfo selectByPrimaryKey(OperationInfoKey key);

    int updateByExampleSelective(@Param("record") OperationInfo record, @Param("example") OperationInfoExample example);

    int updateByExample(@Param("record") OperationInfo record, @Param("example") OperationInfoExample example);

    int updateByPrimaryKeySelective(OperationInfo record);

    int updateByPrimaryKey(OperationInfo record);
}