package com.sf.shiva.oms.psm.dao;

import java.util.Date;

/**
 * 
 * 描述：hbase表RowKey接口类
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月5日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 * @since 1.0
 */
public interface TableRowKeyDao {

    /**
     * 根据包裹号获取RowKey
     * 
     * @param packageNo
     *            包裹号
     * @param clazz
     *            实体类对象
     * @return hbase表的RowKey，分区号（3位）_包裹号（12位）
     * @author 568677
     * @param <T>
     * @date 2017年12月5日
     */
    public <T> String getRowKey(String packageNo, Class<T> clazz);

    /**
     * 根据包裹号，操作时间获取RowKey
     * 
     * @param packageNo
     *            包裹号
     * @param operTm
     *            操作时间
     * @param entity
     *            实体类对象
     * @return hbase表的RowKey，分区号（3位）_包裹号（12位）_操作时间戳
     * @author 568677
     * @date 2017年12月6日
     */
    public <T> String getRowKey(String packageNo, Date operTm, Class<T> clazz);

    /**
     * 根据包裹号，包裹状态，操作时间获取RowKey
     * 
     * @param packageNo
     *            包裹号
     * @param packageStatus
     *            包裹状态
     * @param operTm
     *            操作时间
     * @param entity
     *            实体对象
     * @return hbase表的RowKey，分区号（3位）_包裹号（12位）_包裹状态_操作时间戳
     * @author 568677
     * @date 2017年12月6日
     */
    public <T> String getRowKey(String packageNo, String packageStatus, Date operTm, Class<T> clazz);

}
