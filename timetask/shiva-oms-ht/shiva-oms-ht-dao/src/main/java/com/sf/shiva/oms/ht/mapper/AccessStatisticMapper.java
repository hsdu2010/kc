package com.sf.shiva.oms.ht.mapper;

import com.sf.shiva.oms.ht.domain.AccessStatistic;
import com.sf.shiva.oms.ht.domain.AccessStatisticExample;
import com.sf.shiva.oms.ht.domain.AccessStatisticKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author 01369626
 */
public interface AccessStatisticMapper {
    int countByExample(AccessStatisticExample example);

    int deleteByExample(AccessStatisticExample example);

    int deleteByPrimaryKey(AccessStatisticKey key);

    int insert(AccessStatistic record);

    int insertSelective(AccessStatistic record);

    List<AccessStatistic> selectByExample(AccessStatisticExample example);

    AccessStatistic selectByPrimaryKey(AccessStatisticKey key);

    int updateByExampleSelective(@Param("record") AccessStatistic record, @Param("example") AccessStatisticExample example);

    int updateByExample(@Param("record") AccessStatistic record, @Param("example") AccessStatisticExample example);

    int updateByPrimaryKeySelective(AccessStatistic record);

    int updateByPrimaryKey(AccessStatistic record);
}