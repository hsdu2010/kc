package com.sf.shiva.oms.psm.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.CellUtil;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *
 */
public class HBaseUtil {
	private HBaseUtil() {
		super();
	}
	
	private static final Logger logger = LoggerFactory.getLogger(HBaseUtil.class);
	
    private static Configuration conf = null;

    private static Connection connection;

    /**
     * 使用该类，必须先调用该方法
     * 
     * @param conf
     */
    public static synchronized void init(Configuration conf) {
        if (HBaseUtil.conf == null) {
            HBaseUtil.conf = conf;
            connection = initConnection();
        }
    }

    private static Connection initConnection() {
        Connection connection = null;
        try {
            connection = ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
        	logger.error("HBaseUtil initConnection error", e);
        }

        return connection;
    }

    public static Connection getConnection() {
        return connection;
    }

    /**
     * 添加一条记录
     * 
     * @param tableName
     * @param rowKey
     * @param columnFamily
     * @param column
     * @param value
     * @throws IOException
     */
    public static void put(String tableName, String rowKey, String columnFamily, Map<String, String> valueMap) {
        Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
        map.put(rowKey, valueMap);

        put(tableName, columnFamily, map);
    }

    /**
     * 添多条记录
     * 
     * @param tableName
     * @param rowKey
     * @param columnFamily
     * @param colValueMap
     * @throws IOException
     */
    public static void put(String tableName, String columnFamily, Map<String, Map<String, String>> valueMap) {
        Table table = null;

        try {
            table = connection.getTable(TableName.valueOf(tableName));

            if (null != valueMap && !valueMap.isEmpty()) {
                List<Put> putList = new ArrayList<Put>();

                byte[] cf = Bytes.toBytes(columnFamily);

                for (Map.Entry<String, Map<String, String>> entry : valueMap.entrySet()) {
                    String rowkey = entry.getKey();

                    Map<String, String> values = entry.getValue();

                    Set<String> set = values.keySet();

                    Put p = new Put(Bytes.toBytes(rowkey));
                    for (String column : set) {
                        String value = values.get(column);
                        p.addColumn(cf, Bytes.toBytes(column), Bytes.toBytes(value));
                    }

                    putList.add(p);
                }

                table.put(putList);
            }
        } catch (IOException e) {
        	logger.error("HBaseUtil put error", e);
        } finally {
            IOUtils.closeStream(table);
        }
    }

    /**
     * 读取一条记录
     * 
     * @param tableName
     * @param rowKey
     * @param columnFamily
     * @param column
     * @return
     * @throws IOException
     */
    public static Map<String, String> get(String tableName, String rowKey) {
        return get(tableName, rowKey, null, null);
    }

    /**
     * 读取一条记录
     * 
     * @param tableName
     * @param rowKey
     * @param columnFamily
     * @param column
     * @return
     * @throws IOException
     */
    public static Map<String, String> get(String tableName, String rowKey, String columnFamily, List<String> columns) {
		Map<String, List<String>> cfColumns = new HashMap<>();
        if (columnFamily != null && columns != null) {
            cfColumns.put(columnFamily, columns);
        }

        return get(tableName, rowKey, cfColumns);
    }

    public static Map<String, String> get(String tableName, String rowKey, Map<String, List<String>> cfColumns) {
        Table table = null;
        Map<String, String> mapEntry = new HashMap<>();

        try {
            table = connection.getTable(TableName.valueOf(tableName));

            // 拿到rowKey中，某个列的数据
            Get get = new Get(Bytes.toBytes(rowKey));

            if (cfColumns != null && !cfColumns.isEmpty()) {
                for (Map.Entry<String, List<String>> entry : cfColumns.entrySet()) {
                    byte[] cf = Bytes.toBytes(entry.getKey());
                    List<String> columns = entry.getValue();

                    for (String column : columns) {
                        get.addColumn(cf, Bytes.toBytes(column));
                    }
                }
            }

            Result result = table.get(get);

            // 返回值不需要rowkey
            // mapLine.put("row_key", rowKey);

            for (org.apache.hadoop.hbase.Cell cell : result.rawCells()) {
                // 注意，需要使用Bytes.toString(keyValue.getFamily())方式，如果采用new
                // String(keyValue.getFamily())会出现中文乱码
                // String key = Bytes.toString(keyValue.getFamily()) + ":" +
                // Bytes.toString(keyValue.getQualifier());
                String key = Bytes.toString(CellUtil.cloneQualifier(cell));
                String val = Bytes.toString(CellUtil.cloneValue(cell));
                mapEntry.put(key, val == null ? "" : val);
            }
        } catch (Exception e) {
        	logger.error("HBaseUtil get error", e);
        } finally {
            IOUtils.closeStream(table);
        }
        return mapEntry;
    }

    /**
     * 删除多条记录
     * 
     * @param tableName
     * @param rowKey
     * @throws IOException
     */
    public static void delete(String tableName, List<String> rowKeys) throws IOException {
        Table table = null;

        try {
            table = connection.getTable(TableName.valueOf(tableName));

            List<Delete> list = new ArrayList<Delete>();
            for (String rowKey : rowKeys) {
                Delete delete = new Delete(rowKey.getBytes());
                list.add(delete);
            }

            table.delete(list);
        } catch (Exception e) {
        	logger.error("HBaseUtil delete error", e);
        } finally {
            IOUtils.closeStream(table);
        }
    }

    /**
     * 删除单条记录
     * 
     * @param tableName
     * @param rowKey
     * @throws IOException
     */
    public static void delete(String tableName, String rowKey) throws IOException {
        Table table = null;

        try {
            table = connection.getTable(TableName.valueOf(tableName));
            table.delete(new Delete(rowKey.getBytes()));
        } catch (Exception e) {
        	logger.error("HBaseUtil delete error", e);
        } finally {
            IOUtils.closeStream(table);
        }
    }

    public static List<Map<String, String>> query(final String tableName, final String family, final List<String> columns, final String value) throws IOException {
        final List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        Table table = null;

        ResultScanner scanner = null;

        try {
            table = connection.getTable(TableName.valueOf(tableName));

            String prefix = value + "_";
            // 时间戳第一位在0-9之中
            String start = prefix + "0/";
            String stop = prefix + "9:";
            // String startRow = prefix + "/";//在ascii码中，斜杠比任何数字都要小
            // String stopRow = prefix + ":";//在ascii码中，冒号比任何数字都要大

            Scan scan = new Scan();

            if (null != family && !"".equals(family.trim())) {
                if (null != columns && !columns.isEmpty()) {
                    for (String column : columns) {
                        // 扫描列
                        scan.addColumn(family.getBytes(), column.getBytes());
                    }
                } else {
                    scan.addFamily(family.getBytes());
                }
            }

            scan.setStartRow(start.getBytes());
            scan.setStopRow(stop.getBytes());
            // scan.setFilter(filter);

            scanner = table.getScanner(scan);

            for (Result result : scanner) {
                // String rowKey = new String(result.getRow());
                Map<String, String> mapLine = new HashMap<String, String>();

                // 返回值不需要rowkey
                // mapLine.put("rowKey", rowKey);

                for (org.apache.hadoop.hbase.Cell cell : result.rawCells()) {
                    // 注意，需要使用Bytes.toString(keyValue.getFamily())方式，如果采用new
                    // String(keyValue.getFamily())会出现中文乱码
                    // String key = Bytes.toString(keyValue.getFamily()) + ":" +
                    // Bytes.toString(keyValue.getQualifier());
                    String key = Bytes.toString(CellUtil.cloneQualifier(cell));
                    String val = Bytes.toString(CellUtil.cloneValue(cell));
                    mapLine.put(key, val == null ? "" : val);
                }

                list.add(mapLine);
            }
        } catch (Exception e) {
        	logger.error("HBaseUtil query error", e);
        } finally {
            IOUtils.closeStream(scanner);
            IOUtils.closeStream(table);
        }

        return list;
    }

    /**
     * 全表扫描
     * 
     * @param tableName
     * @return
     * @throws IOException
     */
    public static List<Map<String, String>> scan(final String tableName) throws IOException {
        final List<Map<String, String>> list = new ArrayList<>();

        Table table = null;

        ResultScanner scanner = null;

        try {
            table = connection.getTable(TableName.valueOf(tableName));

            Scan scan = new Scan();
            scan.setCaching(100);

            scanner = table.getScanner(scan);

            for (Result result : scanner) {
                String rowKey = new String(result.getRow());
                Map<String, String> mapLine = new HashMap<String, String>();

                mapLine.put("row_key", rowKey);

                for (org.apache.hadoop.hbase.Cell cell : result.rawCells()) {
                    // 注意，需要使用Bytes.toString(keyValue.getFamily())方式，如果采用new
                    // String(keyValue.getFamily())会出现中文乱码
                    // String key = Bytes.toString(keyValue.getFamily()) + ":" +
                    // Bytes.toString(keyValue.getQualifier());
                    String key = Bytes.toString(CellUtil.cloneQualifier(cell));
                    String val = Bytes.toString(CellUtil.cloneValue(cell));
                    mapLine.put(key, val == null ? "" : val);
                }

                list.add(mapLine);
            }
        } catch (Exception e) {
        	logger.error("HBaseUtil scan error", e);
        } finally {
            IOUtils.closeStream(scanner);
            IOUtils.closeStream(table);
        }

        return list;
    }
    
}