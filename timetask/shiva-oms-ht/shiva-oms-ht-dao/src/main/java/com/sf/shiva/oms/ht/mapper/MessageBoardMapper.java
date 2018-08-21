package com.sf.shiva.oms.ht.mapper;

import com.sf.shiva.oms.ht.domain.MessageBoard;
import com.sf.shiva.oms.ht.domain.MessageBoardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author 01369626
 */
public interface MessageBoardMapper {
    int countByExample(MessageBoardExample example);

    int deleteByExample(MessageBoardExample example);

    int deleteByPrimaryKey(String id);

    int insert(MessageBoard record);

    int insertSelective(MessageBoard record);

    List<MessageBoard> selectByExampleWithBLOBs(MessageBoardExample example);

    List<MessageBoard> selectByExample(MessageBoardExample example);

    MessageBoard selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MessageBoard record, @Param("example") MessageBoardExample example);

    int updateByExampleWithBLOBs(@Param("record") MessageBoard record, @Param("example") MessageBoardExample example);

    int updateByExample(@Param("record") MessageBoard record, @Param("example") MessageBoardExample example);

    int updateByPrimaryKeySelective(MessageBoard record);

    int updateByPrimaryKeyWithBLOBs(MessageBoard record);

    int updateByPrimaryKey(MessageBoard record);
}