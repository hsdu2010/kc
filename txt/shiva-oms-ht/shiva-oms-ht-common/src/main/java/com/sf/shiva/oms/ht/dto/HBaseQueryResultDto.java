package com.sf.shiva.oms.ht.dto;

import java.util.Map;

/**
 * 
 * 描述：hbase查询结果dto
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年5月31日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
public class HBaseQueryResultDto {
    
    private String rowKey;
    private Map<String, String> data;
    public String getRowKey() {
        return rowKey;
    }
    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }
    public Map<String, String> getData() {
        return data;
    }
    public void setData(Map<String, String> data) {
        this.data = data;
    }
    
    
    
}
