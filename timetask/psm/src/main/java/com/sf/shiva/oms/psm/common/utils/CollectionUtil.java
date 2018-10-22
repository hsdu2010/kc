package com.sf.shiva.oms.psm.common.utils;

import java.util.Collection;
import java.util.List;

/**
 * 
 * 描述：集合工具类
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON            REASON
 *  1    2016年9月13日        866321            Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 866321
 */
public final class CollectionUtil {

    /**
     * 私有构造方法
     */
    private CollectionUtil() {}

    /**
     * 判断是否为空
     * 
     * @param collection
     *            集合
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        if (null == collection || collection.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否不为空
     * 
     * @param collection
     *            集合
     * @return 是否不为空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 获取列表中最后一个数据
     * 
     * @param <E>
     *            类
     * @param list
     *            列表
     * @return 最后一个数据
     */
    public static <E> E getLastData(List<E> list) {
        if (CollectionUtil.isEmpty(list)) {
            throw new IllegalArgumentException("List cannot be null.");
        }
        return list.get(list.size() - 1);
    }

    /**
     * 将源集合合并到目标集合中，并去重
     * 
     * @param dest
     *            源集合
     * @param source
     *            目标集合
     * @author 866321-2016年12月22日
     */
    public static <E> void union(Collection<E> dest, Collection<E> source) {
        if (CollectionUtil.isNotEmpty(source)) {
            for (E data : source) {
                if (!dest.contains(data)) {
                    dest.add(data);
                }
            }
        }
    }
}
