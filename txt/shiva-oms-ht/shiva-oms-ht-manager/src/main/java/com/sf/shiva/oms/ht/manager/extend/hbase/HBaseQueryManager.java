package com.sf.shiva.oms.ht.manager.extend.hbase;

import java.util.List;

import com.sf.shiva.oms.ht.dto.HBaseQueryResultDto;

/**
 * 
 * 描述：hbase查询manager层接口
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年6月4日      01369626         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369626
 * @since 1.0
 */
public interface HBaseQueryManager {

    /**
     * 根据rowkey查询记录
     * 
     * @param namespace
     *            命名空间
     * @param tableName
     *            表名
     * @param rowKey
     *            行健
     * @return 查询结果
     * @author 01369626
     * @date 2018年6月4日
     */
    public HBaseQueryResultDto get(String tableName, String rowKey);

    /**
     * 根据rowkey前缀查询记录
     * 
     * @param namespace
     *            命名空间
     * @param tableName
     *            表名
     * @param rowKeyPrefix
     *            rowkey前缀
     * @param limitNum
     *            查询条数
     * @param isFuzzySearch
     *            是否模糊搜索
     * @return 查询结果
     * @author 01369626
     * @date 2018年6月4日
     */
    public List<HBaseQueryResultDto> scanByRowKey(String tableName, String rowKey, int limitNum, boolean isFuzzySearch);

    /**
     * 根据列查询记录
     * 
     * @param namespace
     *            命名空间
     * @param tableName
     *            表名
     * @param family
     *            列族
     * @param columnName
     *            列名
     * @param value
     *            列值
     * @param limitNum
     *            查询条数
     * @param isFuzzySearch
     *            是否模糊搜索
     * @return 查询结果
     * @author 01369626
     * @date 2018年6月4日
     */
    public List<HBaseQueryResultDto> findByColumn(String tableName, String family, String columnName, String value, int limitNum, boolean isFuzzySearch);

}
