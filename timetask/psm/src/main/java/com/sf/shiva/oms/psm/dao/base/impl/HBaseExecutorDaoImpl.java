package com.sf.shiva.oms.psm.dao.base.impl;

import java.io.IOException;
import java.util.ArrayList;
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
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.InitializingBean;

import com.sf.shiva.oms.psm.common.constants.PackageStatusConstant;
import com.sf.shiva.oms.psm.dao.base.HBaseExecutorDao;

/**
 * 
 * 描述： hbase 执行器，执行hbase crud操作具体实现类，调用了hbase jar包的接口
 * 注：该类不对任何数据的准确性做校验，例如是否为null等。
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年9月18日      80002137         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 80002137
 * @since 1.0
 */
public class HBaseExecutorDaoImpl implements InitializingBean, HBaseExecutorDao {

    /** hbase 连接 */
    private Connection hbaseConnection;
    private String quorum;
    private String clientPort;
    private String hbaseParent;
    private String systemEnv;// 系统运行环境，如果是DEV环境，需要在命名空间后面增加_DEV，否者不增加

    @Override
    public void afterPropertiesSet() throws Exception {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", quorum);
        configuration.set("hbase.zookeeper.property.clientPort", clientPort);
        configuration.set("zookeeper.znode.parent", hbaseParent);
        configuration.set("simple.user.name", "package-status926");
        configuration.set("hbase.client.userprovider.class", "org.apache.hadoop.hbase.security.SimpleUserProvider");
        configuration.set("hbase.client.retries.number", "11");// 设置Hbase重试次数
        hbaseConnection = ConnectionFactory.createConnection(configuration);
    }

    /**
     * 将命名空间和表名组装为：命名空间:表名；如果为研发环境，则为命名空间增加后缀_DEV
     * 
     * @return 命名空间:表名
     * @author 568677
     * @date 2017年12月4日
     */
    private String getTableName(String nameSpace, String tableName) {
        StringBuilder hbaseTableName = new StringBuilder(nameSpace);
        if (StringUtils.equals(PackageStatusConstant.SYSTEM_ENV_DEV, systemEnv)) {
            hbaseTableName.append(PackageStatusConstant.UNDER_LINE).append(PackageStatusConstant.SYSTEM_ENV_DEV);
        }
        hbaseTableName.append(":").append(tableName);
        return hbaseTableName.toString();
    }

    @Override
    public void put(String nameSpace, String tableName, String columnFamily, Map<String, Map<String, String>> valueMap) throws IOException {
        Table table = null;
        try {
            table = hbaseConnection.getTable(TableName.valueOf(getTableName(nameSpace, tableName)));// 获取hbase表对象
            byte[] columnFamilyBytes = Bytes.toBytes(columnFamily);// 列族转换为byte数组
            List<Put> puts = new ArrayList<>(valueMap.size());// 创建插入记录集合
            valueMap.forEach((rowKey, columnMap) -> {// 遍历valueMap中的所有记录，转换为可以插入到hbase的put对象
                Put keyPut = new Put(Bytes.toBytes(rowKey)); // 创建一条插入记录keyPut
                columnMap.forEach((columnName, columnValue) -> keyPut.addColumn(columnFamilyBytes, Bytes.toBytes(columnName), Bytes.toBytes(columnValue)));// 遍历一条记录中的所有字段，添加到keyPut中
                puts.add(keyPut);
            });
            table.put(puts);
        } finally {
            if (null != table) {
                IOUtils.closeStream(table);
            }
        }
    }

    @Override
    public void delete(String nameSpace, String tableName, List<String> rowKeys) throws IOException {
        Table table = null;
        try {
            table = hbaseConnection.getTable(TableName.valueOf(getTableName(nameSpace, tableName)));// 获取hbase表对象
            List<Delete> deleteList = new ArrayList<>(rowKeys.size());// 创建删除记录集合
            rowKeys.forEach(rowKey -> deleteList.add(new Delete(Bytes.toBytes(rowKey))));// 遍历rowKeys中的所有记录，转换为可以给table对象删除的delete对象
            table.delete(deleteList);// 根据rowKey集合删除记录
        } finally {
            if (null != table) {
                IOUtils.closeStream(table);
            }
        }
    }

    @Override
    public Map<String, String> get(String nameSpace, String tableName, String rowKey, Map<String, List<String>> cfColumns) throws IOException {
        Table table = null;
        try {
            table = hbaseConnection.getTable(TableName.valueOf(getTableName(nameSpace, tableName)));// 获取hbase表对象
            Get get = new Get(Bytes.toBytes(rowKey));// 创建get对象
            if (null != cfColumns && !cfColumns.isEmpty()) {// 如果需要指定查询的列族和字段，则get对象中添加该信息
                cfColumns.forEach((columnFamily, columns) -> columns.forEach(column -> get.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column))));
            }
            Result result = table.get(get);// 获取查询结果
            Map<String, String> resultMap = new HashMap<>();
            for (Cell cell : result.rawCells()) {// 组装结果集
                resultMap.put(Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
            }
            return resultMap;
        } finally {
            if (null != table) {
                IOUtils.closeStream(table);
            }
        }
    }

    @Override
    public List<Map<String, String>> scan(String nameSpace, String tableName, String columnFamily, String rowKeyPrefix) throws IOException {
        Table table = null;
        ResultScanner scanner = null;
        try {
            table = hbaseConnection.getTable(TableName.valueOf(getTableName(nameSpace, tableName)));// 获取hbase表对象
            Scan scan = new Scan();
            if (StringUtils.isNotEmpty(rowKeyPrefix)) {// 根据rowkye前缀获取匹配数据
                scan.setStartRow(Bytes.toBytes(new StringBuilder(rowKeyPrefix).append("_0/").toString()));// 在ascii码中，斜杠比任何数字都要小
                scan.setStopRow(Bytes.toBytes(new StringBuilder(rowKeyPrefix).append("_9:").toString()));// 在ascii码中，冒号比任何数字都要大
            }
            if (null != columnFamily && columnFamily.trim().length() > 0) { // 设置列族
                scan.addFamily(Bytes.toBytes(columnFamily));
            }
            scanner = table.getScanner(scan);// 查询hbase,看源码可知该对象都是new出来的，必不为空
            List<Map<String, String>> resultList = new ArrayList<>();
            scanner.forEach(result -> {// 遍历结果集，转换为resultList
                Map<String, String> resultMap = new HashMap<>();
                for (Cell cell : result.rawCells()) {// 遍历cell，转换为resultMap
                    resultMap.put(Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
                }
                resultList.add(resultMap);
            });
            return resultList;
        } finally {
            if (null != scanner) {
                IOUtils.closeStream(scanner);
            }
            if (null != table) {
                IOUtils.closeStream(table);
            }
        }
    }

    @Override
    public List<Map<String, String>> scan(String nameSpace, String tableName, String columnFamily, int querySize) throws IOException {
        Table table = null;
        ResultScanner scanner = null;
        try {
            table = hbaseConnection.getTable(TableName.valueOf(getTableName(nameSpace, tableName)));// 获取hbase表对象
            Scan scan = new Scan();
            if (null != columnFamily && columnFamily.trim().length() > 0) { // 设置列族
                scan.addFamily(Bytes.toBytes(columnFamily));
            }
            scanner = table.getScanner(scan);// 查询hbase,看源码可知该对象都是new出来的，必不为空
            List<Map<String, String>> resultList = new ArrayList<>();
            for (Result result : scanner.next(querySize)) {
                Map<String, String> resultMap = new HashMap<>();
                for (Cell cell : result.rawCells()) {// 遍历cell，转换为resultMap
                    resultMap.put(Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
                }
                resultList.add(resultMap);
            }
            return resultList;
        } finally {
            if (null != scanner) {
                IOUtils.closeStream(scanner);
            }
            if (null != table) {
                IOUtils.closeStream(table);
            }
        }
    }

    public String getQuorum() {
        return quorum;
    }

    public void setQuorum(String quorum) {
        this.quorum = quorum;
    }

    public String getClientPort() {
        return clientPort;
    }

    public void setClientPort(String clientPort) {
        this.clientPort = clientPort;
    }

    public String getHbaseParent() {
        return hbaseParent;
    }

    public void setHbaseParent(String hbaseParent) {
        this.hbaseParent = hbaseParent;
    }

    public String getSystemEnv() {
        return systemEnv;
    }

    public void setSystemEnv(String systemEnv) {
        this.systemEnv = systemEnv;
    }

}
