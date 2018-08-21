package com.sf.shiva.oms.ht.hbase;

import java.util.List;
import java.util.Map;

import com.sf.shiva.oms.ht.dto.HBaseQueryResultDto;

/**
 * 
 * 描述：hbase操作类
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年5月31日      01369626         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369626
 * @since 1.0
 */
public interface HBaseExecutorDao {

    /**
     * 根据命名空间，表名和rowkey查询记录
     * 
     * @param tableName
     *            表全名
     * @param rowKey
     *            行键
     * @return 查询结果
     * @author 01369626
     * @date 2018年5月31日
     */
    public Map<String, String> get(String tableName, String rowKey);

    /**
     * 根据rowkey前缀查询记录
     * 
     * @param tableName
     *            表全名
     * @param rowKeyPrefix
     *            rowKey前缀
     * @param limitNum
     *            限制条数
     * @return 结果列表
     * @author 01369626
     * @date 2018年6月1日
     */
    public List<HBaseQueryResultDto> scan(String tableName, String rowKeyPrefix, int limitNum);

    /**
     * 根据部分rowkey模糊搜索
     * 
     * @param tableName
     *            表名
     * @param subRowKey
     *            rowkey子串
     * @param limitNum
     *            限制条数
     * @return
     * @author 01369626
     * @date 2018年6月14日
     */
    public List<HBaseQueryResultDto> fuzzyScan(String tableName, String subRowKey, int limitNum);

    /**
     * 根据列查询数据
     * 
     * @param tableName
     *            表全名
     * @param family
     *            列族
     * @param columnName
     *            列名
     * @param columnValue
     *            列值
     * @param limitNum
     *            查询条数
     * @return 查询结构
     * @author 01369626
     * @date 2018年6月1日
     */
    public List<HBaseQueryResultDto> findByColumn(String tableName, String family, String columnName, String columnValue, int limitNum);

    /**
     * 根据列和值模糊查询数据
     * 
     * @param tableName
     *            表全名
     * @param family
     *            列族
     * @param columnName
     *            列名
     * @param value
     *            列值
     * @param limitNum
     *            查询条数
     * @return
     * @author 01369626
     * @date 2018年6月14日
     */
    public List<HBaseQueryResultDto> fuzzyFindByColumn(String tableName, String family, String columnName, String value, int limitNum);

}
