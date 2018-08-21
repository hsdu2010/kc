package com.sf.shiva.oms.ht.manager.extend.hbase.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.dto.HBaseQueryResultDto;
import com.sf.shiva.oms.ht.hbase.HBaseExecutorDao;
import com.sf.shiva.oms.ht.manager.extend.hbase.HBaseQueryManager;

/**
 * 
 * 描述：hbase查询Manager实现
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年6月4日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
@Component
public class HBaseQueryManagerImpl implements HBaseQueryManager{
    
    @Autowired
    private HBaseExecutorDao hbaseExecutorDaoImpl;

    @Override
    public HBaseQueryResultDto get(String tableName, String rowKey) {
        Map<String, String> data = hbaseExecutorDaoImpl.get(tableName, rowKey);
        if(data != null) {
            HBaseQueryResultDto dto = new HBaseQueryResultDto();
            dto.setRowKey(rowKey);
            dto.setData(hbaseExecutorDaoImpl.get(tableName, rowKey));
            return dto;
        }
        return null;
    }

    @Override
    public List<HBaseQueryResultDto> scanByRowKey(String tableName, String rowKey, int limitNum, boolean isFuzzySearch) {
        if(isFuzzySearch) {
            return hbaseExecutorDaoImpl.fuzzyScan(tableName, rowKey, limitNum);
        }
        return hbaseExecutorDaoImpl.scan(tableName, rowKey, limitNum);
    }

    @Override
    public List<HBaseQueryResultDto> findByColumn(String tableName, String family, String columnName, String value, int limitNum, boolean isFuzzySearch) {
        if(isFuzzySearch) {
            return hbaseExecutorDaoImpl.fuzzyFindByColumn(tableName, family, columnName, value, limitNum);
        }
        return hbaseExecutorDaoImpl.findByColumn(tableName, family, columnName, value, limitNum);
    }

}
