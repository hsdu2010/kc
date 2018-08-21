package com.sf.shiva.oms.ht.hbase.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.FilterList.Operator;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.sf.shiva.oms.ht.common.utils.JsonUtil;
import com.sf.shiva.oms.ht.constants.CommonConstants;
import com.sf.shiva.oms.ht.dto.HBaseQueryResultDto;
import com.sf.shiva.oms.ht.hbase.HBaseExecutorDao;

/**
 * 
 * 描述：hbase操作类实现
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
@Repository
public class HBaseExecutorDaoImpl implements HBaseExecutorDao, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(HBaseExecutorDaoImpl.class);

    // hbase连接
    private Connection hbaseConnection;
    // zk集群
    @Value("${hbase.zk.server}")
    private String quorum;
    // zk端口
    @Value("${hbase.zk.port}")
    private String clientPort;
    // zk目录
    @Value("${hbase.zk.parent}")
    private String hbaseParent;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", quorum);
        configuration.set("hbase.zookeeper.property.clientPort", clientPort);
        configuration.set("zookeeper.znode.parent", hbaseParent);
        configuration.set("hbase.client.retries.number", "11");
        hbaseConnection = ConnectionFactory.createConnection(configuration);
    }
    
    public HBaseExecutorDaoImpl() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "cnsz22pl0122,cnsz22pl0123,cnsz22pl0124,cnsz22pl0125");
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("zookeeper.znode.parent", "/hbase");
        configuration.set("hbase.client.retries.number", "11");// 设置Hbase重试次数
        hbaseConnection = ConnectionFactory.createConnection(configuration);
    }

    @Override
    public Map<String, String> get(String tableName, String rowKey) {
        Table table = null;
        try {
            table = hbaseConnection.getTable(TableName.valueOf(tableName));// 获取hbase表对象
            Get get = new Get(Bytes.toBytes(rowKey));// 创建get对象
            Result result = table.get(get);
            Map<String, String> resultMap = new HashMap<>();
            for (Cell cell : result.rawCells()) {
                resultMap.put(buildColumnName(cell), Bytes.toString(CellUtil.cloneValue(cell)));
            }
            return resultMap;
        } catch (Exception e) {
            logger.error("HBaseExecutorDaoImpl get error. tableName={}, rowkey={}", tableName, rowKey, e);
        } finally {
            closeResource(table, null);
        }
        return null;
    }

    @Override
    public List<HBaseQueryResultDto> scan(String tableName, String rowKeyPrefix, int limitNum) {
        Table table = null;
        ResultScanner scanner = null;
        try {
            table = hbaseConnection.getTable(TableName.valueOf(tableName));
            Scan scan = new Scan();
            scan.setFilter(new PageFilter(limitNum));
            if(StringUtils.isNotEmpty(rowKeyPrefix)) {//没有输入rowkey前缀时，直接扫描数据
                scan.setStartRow(Bytes.toBytes(new StringBuilder(rowKeyPrefix).append("!").toString()));//"!"是ascii码可见字符中最小的
                scan.setStopRow(Bytes.toBytes(new StringBuilder(rowKeyPrefix).append("~").toString()));//"~"是ascii码可见字符中最大的
            }
            scanner = table.getScanner(scan);
            List<HBaseQueryResultDto> resultList = new ArrayList<>();
            for(Result result : scanner.next(limitNum)) {
                resultList.add(buildQueryResult(result));
            }
//            scanner.forEach(result -> resultList.add(buildQueryResult(result)));
            return resultList;
        } catch (Exception e) {
            logger.error("HBaseExecutorDaoImpl scan error. tableName={}, rowKeyPrefix={}", tableName, rowKeyPrefix, e);
        } finally {
            closeResource(table, scanner);
        }
        return Collections.emptyList();
    }
    
    @Override
    public List<HBaseQueryResultDto> fuzzyScan(String tableName, String subRowKey, int limitNum) {
        Table table = null;
        ResultScanner scanner = null;
        try {
            table = hbaseConnection.getTable(TableName.valueOf(tableName));
            Scan scan = new Scan();
            FilterList filterList = new FilterList();
            if(StringUtils.isNotEmpty(subRowKey)) {
                filterList.addFilter(new RowFilter(CompareOp.EQUAL, new SubstringComparator(subRowKey)));
            }
            filterList.addFilter(new PageFilter(limitNum));
            scan.setFilter(filterList);
            scanner = table.getScanner(scan);
            List<HBaseQueryResultDto> resultList = new ArrayList<>();
            scanner.forEach(result -> resultList.add(buildQueryResult(result)));
            return resultList;
        }catch (Exception e) {
            logger.error("HBaseExecutorDaoImpl fuzzyScan error. tableName={}, subRowKey={}, limitNum={}", tableName, subRowKey, limitNum, e);
        }finally {
            closeResource(table, scanner);
        }
        return Collections.emptyList();
    }

    @Override
    public List<HBaseQueryResultDto> findByColumn(String tableName, String family, String columnName, String columnValue, int limitNum) {
        Table table = null;
        ResultScanner scanner = null;
        try {
            table = hbaseConnection.getTable(TableName.valueOf(tableName));
            Scan scan = new Scan();
            FilterList filterList = new FilterList(Operator.MUST_PASS_ALL);
            if(StringUtils.isNotEmpty(family) && StringUtils.isNotEmpty(columnName) && StringUtils.isNotEmpty(columnValue)) {
                SingleColumnValueFilter columnFilter = new SingleColumnValueFilter(Bytes.toBytes(family), Bytes.toBytes(columnName), CompareOp.EQUAL, Bytes.toBytes(columnValue));
                columnFilter.setFilterIfMissing(true);
                filterList.addFilter(columnFilter); 
            }
            filterList.addFilter(new PageFilter(limitNum));
            scan.setFilter(filterList);
            scanner = table.getScanner(scan);
            List<HBaseQueryResultDto> resultList = new ArrayList<>();
            scanner.forEach(result -> resultList.add(buildQueryResult(result)));
            return resultList;
        } catch (Exception e) {
            logger.error("HBaseExecutorDaoImpl findByColumn error. tableName={}, family={}, columnName={}, columnValue={}", tableName, family, columnName, columnValue, e);
        } finally {
            closeResource(table, scanner);
        }
        return Collections.emptyList();
    }
    
    @Override
    public List<HBaseQueryResultDto> fuzzyFindByColumn(String tableName, String family, String columnName, String value, int limitNum) {
        Table table = null;
        ResultScanner scanner = null;
        try {
            table = hbaseConnection.getTable(TableName.valueOf(tableName));
            Scan scan = new Scan();
            FilterList filterList = new FilterList(Operator.MUST_PASS_ALL);
            if(StringUtils.isNotEmpty(family) && StringUtils.isNotEmpty(columnName) && StringUtils.isNotEmpty(value)) {
                SingleColumnValueFilter columnValueFilter = new SingleColumnValueFilter(Bytes.toBytes(family), Bytes.toBytes(columnName), CompareOp.EQUAL, new SubstringComparator(value));
                filterList.addFilter(columnValueFilter);
            }
            filterList.addFilter(new PageFilter(limitNum));
            scan.setFilter(filterList);
            scanner = table.getScanner(scan);
            List<HBaseQueryResultDto> resultList = new ArrayList<>();
            scanner.forEach(result -> resultList.add(buildQueryResult(result)));
            return resultList;
        }catch (Exception e) {
            logger.error("HBaseExecutorDaoImpl fuzzyFindByColumn error. tableName={}, family={}, columnName={}, value={}", tableName, family, columnName, value, e);
        }
        return Collections.emptyList();
    }

    /**
     * 组装dto
     * 
     * @param result
     *            查询结果
     * @return hbase查询结果dto
     * @author 01369626
     * @date 2018年6月1日
     */
    private static HBaseQueryResultDto buildQueryResult(Result result) {
        HBaseQueryResultDto dto = new HBaseQueryResultDto();
        dto.setRowKey(Bytes.toString(result.getRow()));
        Map<String, String> resultMap = new HashMap<>();
        for (Cell cell : result.rawCells()) {
            resultMap.put(buildColumnName(cell), Bytes.toString(CellUtil.cloneValue(cell)));
        }
        dto.setData(resultMap);
        return dto;
    }

    /**
     * 组装列名
     * 
     * @param cell
     *            查询结果
     * @return 列族:列名
     * @author 01369626
     * @date 2018年5月31日
     */
    private static String buildColumnName(Cell cell) {
        String columnFamily = Bytes.toString(CellUtil.cloneFamily(cell));
        String columnName = Bytes.toString(CellUtil.cloneQualifier(cell));
        return new StringBuilder(columnFamily).append(CommonConstants.COLON).append(columnName).toString();
    }

    public static void main(String[] args) throws IOException {
        HBaseExecutorDao dao = new HBaseExecutorDaoImpl();
        List<HBaseQueryResultDto> list = dao.scan("shiva_oms_psm:package_status_record", "104_20180601170", 5);
//        List<HBaseQueryResultDto> list2 = dao.findByColumn("shiva_oms_psm:package_status_record", "cf1", "packageNo", "755065355896", 10);
        System.out.println(JsonUtil.object2Json(list));
//        System.out.println(JsonUtil.object2Json(list2));
//        List<HBaseQueryResultDto> list2 = dao.fuzzyScan("shiva_oms_psm:package_status_record", "201806", 10);
//        System.out.println(JsonUtil.object2Json(list2));
        List<HBaseQueryResultDto> list2 = dao.fuzzyFindByColumn("shiva_oms_psm:package_status_record", "cf1", "eventCode", "30_", 10);
        System.out.println(JsonUtil.object2Json(list2));
    }

    /**
     * 关闭资源
     * 
     * @param table
     *            hbase表
     * @param scanner
     *            hbase扫描器
     * @author 01369626
     * @date 2018年6月1日
     */
    private static void closeResource(Table table, ResultScanner scanner) {
        if (scanner != null) {
            IOUtils.closeStream(scanner);
        }
        if (table != null) {
            IOUtils.closeStream(table);
        }
    }

}
