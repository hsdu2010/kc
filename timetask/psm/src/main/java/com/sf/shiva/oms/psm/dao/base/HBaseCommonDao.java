package com.sf.shiva.oms.psm.dao.base;

import java.util.List;
import java.util.Map;

/**
 * 描述：hbase CRUD公共dao接口
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年9月21日      80002137         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 80002137
 * @since 1.0
 */
public interface HBaseCommonDao {

    /**
     * 保存 hbase bean对象
     * 
     * @param rowKey
     *            行健
     * @param entity
     *            实体
     * @throws Exception
     *             请处理异常
     * @author 80002137
     * @date 2017年9月21日
     */
    public <T> void saveAny(String rowKey, T entity);

    /**
     * 保存 hbase bean对象集合
     * 
     * @param maps
     *            rowKey 与 对象 的 map集合
     * @param clazz
     *            对象的类型
     * @throws Exception
     *             请处理异常
     * @author 80002137
     * @date 2017年9月23日
     */
    public <T> void saveAll(Map<String, T> maps, Class<T> clazz);

    /**
     * 根据rowKey 删除对象
     * 
     * @param rowKey
     *            行健
     * @param entity
     *            实体 请处理异常
     * @author 80002137
     * @date 2017年9月23日
     */
    public <T> void removeAny(String rowKey, T entity);

    /**
     * 根据rowKey 和对象类型 获取单个对象
     * 
     * @param rowKey
     *            行健
     * @param clazz
     *            对象类型
     * @return rowKey对应的对象
     * @throws Exception
     * @author 80002137
     * @date 2017年9月23日
     */
    public <T> T get(String rowKey, Class<T> clazz);

    /**
     * 根据rowKey 前缀、对象类型 查询对象集合
     * 
     * @param rowKeyPrefix
     *            rowKey前缀
     * @param clazz
     *            对象类型
     * @return 对象集合
     * @author 80002137
     * @date 2017年9月23日
     */
    public <T> List<T> scan(String rowKeyPrefix, Class<T> clazz);

    /**
     * 查询指定条数对象集合
     * 
     * @param clazz
     *            对象类型
     * @param querySize
     *            查询条数
     * @return 对象集合
     * @author 80002517
     * @date 2018年1月19日
     */
    public <T> List<T> scan(Class<T> clazz, int querySize);
}
