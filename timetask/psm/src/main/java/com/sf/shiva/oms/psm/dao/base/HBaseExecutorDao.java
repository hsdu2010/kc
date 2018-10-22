package com.sf.shiva.oms.psm.dao.base;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 
 * 描述：HBase操作类
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月29日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 * @since 1.0
 */
public interface HBaseExecutorDao {

    /**
     * 添加记录
     * 
     * @param nameSpace
     *            命名空间
     * @param tableName
     *            表名
     * @param columnFamily
     *            列族
     * @param valueMap
     *            Map<RowKey,Map<column,value>> 表记录
     * @author 80002137
     * @throws IOException
     * @date 2017年9月18日
     */
    public void put(String nameSpace, String tableName, String columnFamily, Map<String, Map<String, String>> valueMap) throws IOException;

    /**
     * 删除记录
     * 
     * @param nameSpace
     *            命名空间
     * @param tableName
     *            表名
     * @param rowKeys
     *            行健
     * @author 80002137
     * @throws IOException
     * @date 2017年9月18日
     */
    public void delete(String nameSpace, String tableName, List<String> rowKeys) throws IOException;

    /**
     * 根据rowKey 查询一条记录，可以指定获取的列族及该列族下的指定字段
     * 
     * @param nameSpace
     *            命名空间
     * @param tableName
     *            表名
     * @param rowKey
     *            行健
     * @param cfColumns
     *            key：列族名， value：该列族下的 字段集合
     * @return
     * @author 80002137
     * @throws IOException
     * @date 2017年9月19日
     */
    public Map<String, String> get(String nameSpace, String tableName, String rowKey, Map<String, List<String>> cfColumns) throws IOException;

    /**
     * 查询多行记录
     * 
     * @param nameSpace
     *            命名空间
     * @param tableName
     *            表名
     * @param columnFamily
     *            列族
     * @param rowKeyPrefix
     *            rowkey的前缀，匹配前缀相同的数据
     * @return 结果集
     * @throws IOException
     * @author 80002137
     * @date 2017年9月20日
     */
    public List<Map<String, String>> scan(String nameSpace, String tableName, String columnFamily, String rowKeyPrefix) throws IOException;

    /**
     * 查询多行记录
     * 
     * @param nameSpace
     *            命名空间
     * @param tableName
     *            表名
     * @param columnFamily
     *            列族
     * @param querySize
     *            查询条数
     * @return 结果集
     * @throws IOException
     * @author 80002517
     * @date 2018年1月17日
     */
    public List<Map<String, String>> scan(String nameSpace, String tableName, String columnFamily, int querySize) throws IOException;
}
