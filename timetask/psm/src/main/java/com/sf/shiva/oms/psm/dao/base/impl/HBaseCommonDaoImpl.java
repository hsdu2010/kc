package com.sf.shiva.oms.psm.dao.base.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.psm.common.annotation.ColumnFamily;
import com.sf.shiva.oms.psm.common.annotation.NameSpace;
import com.sf.shiva.oms.psm.common.annotation.TableName;
import com.sf.shiva.oms.psm.common.exception.BusinessException;
import com.sf.shiva.oms.psm.common.utils.BeanMapUtil;
import com.sf.shiva.oms.psm.dao.base.HBaseCommonDao;
import com.sf.shiva.oms.psm.dao.base.HBaseExecutorDao;

/**
 * 描述： hbase CRUD 操作模板类 现在HBase并不能很好的处理两个或者三个以上的列族，所以尽量让你的列族数量少一些。
 * 尽量在你的应用中使用一个列族。只有你的所有查询操作只访问一个列族的时候，可以引入第二个和第三个列族.
 * 例如，你有两个列族,但你查询的时候总是访问其中的一个，从来不会两个一起访问。
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年9月20日      80002137         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 80002137
 * @since 1.0
 */
@Service
public class HBaseCommonDaoImpl implements HBaseCommonDao {

    @Autowired
    private HBaseExecutorDao hBaseExecutorDaoImpl;

    @Override
    public <T> void saveAny(String rowKey, T entity) {
        String nameSpace = entity.getClass().getAnnotation(NameSpace.class).value();// 获取entity的命名空间
        String tableName = entity.getClass().getAnnotation(TableName.class).value(); // 获取entity的tableName
        String columnFamily = entity.getClass().getAnnotation(ColumnFamily.class).value();// 获取entity的columnFamily
        Map<String, Map<String, String>> valueMap = new HashMap<>(2);// 转换entity为map
        valueMap.put(rowKey, BeanMapUtil.beanToMap(entity));
        try {
            hBaseExecutorDaoImpl.put(nameSpace, tableName, columnFamily, valueMap);// 调用hBaseExecutor保存
        } catch (Exception e) {
            throw new BusinessException("HBase save record error.", e);
        }
    }

    @Override
    public <T> void saveAll(Map<String, T> maps, Class<T> clazz) {
        String nameSpace = clazz.getAnnotation(NameSpace.class).value();// 获取entity的命名空间
        String tableName = clazz.getAnnotation(TableName.class).value(); // 获取entity的tableName
        String columnFamily = clazz.getAnnotation(ColumnFamily.class).value();// 获取entity的columnFamily
        Map<String, Map<String, String>> valueMap = new HashMap<>(maps.size());
        maps.forEach((rowKey, entity) -> {
            try {
                valueMap.put(rowKey, BeanMapUtil.beanToMap(entity));
            } catch (Exception e) {
                throw new BusinessException("bean convert to map error.", e);
            }
        });
        try {
            hBaseExecutorDaoImpl.put(nameSpace, tableName, columnFamily, valueMap);// 调用hBaseExecutor保存
        } catch (Exception e) {
            throw new BusinessException("HBase batch save records error.", e);
        }
    }

    @Override
    public <T> void removeAny(String rowKey, T entity) {
        String nameSpace = entity.getClass().getAnnotation(NameSpace.class).value();// 获取entity的命名空间
        String tableName = entity.getClass().getAnnotation(TableName.class).value();// 获取entity的tableName
        try {
            hBaseExecutorDaoImpl.delete(nameSpace, tableName, Arrays.asList(rowKey));// 调用hBaseExecutor删除
        } catch (Exception e) {
            throw new BusinessException("HBase delete error.", e);
        }
    }

    @Override
    public <T> T get(String rowKey, Class<T> clazz) {
        String nameSpace = clazz.getAnnotation(NameSpace.class).value();// 获取entity的命名空间
        String tableName = clazz.getAnnotation(TableName.class).value();// 获取entity的tableName
        try {
            return BeanMapUtil.mapToBean(hBaseExecutorDaoImpl.get(nameSpace, tableName, rowKey, null), clazz);
        } catch (IOException e) {
            throw new BusinessException("HBase get error.", e);
        }
    }

    @Override
    public <T> List<T> scan(String rowKeyPrefix, Class<T> clazz) {
        String tableName = clazz.getAnnotation(TableName.class).value();// 获取entity的tableName
        String columnFamily = clazz.getAnnotation(ColumnFamily.class).value();// 获取entity的columnFamily
        String nameSpace = clazz.getAnnotation(NameSpace.class).value();
        List<Map<String, String>> resultList;
        try {
            resultList = hBaseExecutorDaoImpl.scan(nameSpace, tableName, columnFamily, rowKeyPrefix);
        } catch (IOException ex) {
            throw new BusinessException("HBase scan error.", ex);
        }
        return getEntityList(clazz, resultList);
    }

    @Override
    public <T> List<T> scan(Class<T> clazz, int querySize) {
        String tableName = clazz.getAnnotation(TableName.class).value();// 获取entity的tableName
        String columnFamily = clazz.getAnnotation(ColumnFamily.class).value();// 获取entity的columnFamily
        String nameSpace = clazz.getAnnotation(NameSpace.class).value();
        List<Map<String, String>> resultList;
        try {
            resultList = hBaseExecutorDaoImpl.scan(nameSpace, tableName, columnFamily, querySize);
        } catch (IOException ex) {
            throw new BusinessException("HBase scan error.", ex);
        }
        return getEntityList(clazz, resultList);
    }

    /**
     * 将查询结果转换成实体类list
     * 
     * @param resultList
     *            查询结果
     * @return 转换结果
     * @author 01369626
     * @date 2018年3月30日
     */
    private <T> List<T> getEntityList(Class<T> clazz, List<Map<String, String>> resultList) {
        List<T> entityList = new ArrayList<>(resultList.size());
        resultList.forEach(result -> {
            try {
                entityList.add(BeanMapUtil.mapToBean(result, clazz));
            } catch (Exception e) {
                throw new BusinessException("map convert to bean error.", e);
            }
        });
        return entityList;
    }

}
