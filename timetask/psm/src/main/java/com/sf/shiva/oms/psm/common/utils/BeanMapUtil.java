package com.sf.shiva.oms.psm.common.utils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

/**
 * 
 * 描述：Bean和Map转换工具类
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月17日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 * @since 1.0
 */
public class BeanMapUtil {

    private static final Logger logger = LoggerFactory.getLogger(BeanMapUtil.class);

    /** 缓存对象的Field反射，可减少耗时 */
    private static Map<Class<?>, Field[]> fieldsCache = new ConcurrentHashMap<>();

    private BeanMapUtil() {
    }

    /**
     * 获取Bean对象声明的所有字段
     * 
     * @param beanClass
     *            bean对象反射类型
     * @return 声明的所有字段
     * @author 568677
     * @date 2017年11月29日
     */
    private static <T> Field[] getFieldsCache(Class<T> beanClass) {
        Field[] fields = fieldsCache.get(beanClass);
        if (null == fields) {
            fields = beanClass.getDeclaredFields();// 获取所声明的所有字段
            fieldsCache.put(beanClass, fields);
        }
        return fields;
    }

    /**
     * 将对象装换为Map<br>
     * 【异常信息说明 】<br>
     * IntrospectionException - getBeanInfo如果在内省期间发生异常。 <br>
     * IllegalAccessException - 如果此 Method 对象强制执行 Java 语言访问控制，并且底层方法是不可访问的。<br>
     * IllegalArgumentException - 如果该方法是实例方法，且指定对象参数不是声明底层方法的类或接口（或其中的子类或实现程序）的实例
     * ；如果实参和形参的数量不相同；如果基本参数的解包转换失败；如果在解包后，无法通过方法调用转换将参数值转换为相应的形参类型。<br>
     * InvocationTargetException - 如果底层方法抛出异常。 <br>
     * NullPointerException - 如果指定对象为null，且该方法是一个实例方法。<br>
     * ExceptionInInitializerError - 如果由此方法引起的初始化失败。<br>
     * 
     * @param bean
     *            待转换Bean对象
     * @return Map对象
     * @author 568677
     * @date 2017年11月17日
     */
    public static Map<String, String> beanToMap(Object bean) {
        Map<String, String> resultMap = new HashMap<>();
        try {
            PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();// 获取Bean中所有属性数组
            for (PropertyDescriptor property : propertyDescriptors) {// 便利属性数组，将其转换为Map对象，如果值为非String对象，则将其转换为JSON字符串
                Object value = property.getReadMethod().invoke(bean);// 对带有指定参数的指定对象调用由此Method对象表示的底层方法
                if (null == value || "class".equals(property.getName())) {
                    continue;
                } else if (value instanceof String) {
                    resultMap.put(property.getName(), String.valueOf(value));
                } else if (value instanceof Date) {
                    resultMap.put(property.getName(), DateFormatUtil.format((Date) value, DateFormatUtil.D_YYYYMMDD_SPLIT, DateFormatUtil.T_HHMMSSSSS_SPLIT, true));
                } else {
                    resultMap.put(property.getName(), ObjectMapperUtil.toJson(value));
                }
            }
        } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            logger.error("BeanMapUtil beanToMap error", e);
        }
        return resultMap;
    }

    /**
     * 将List<T>转换为List<Map<String, String>>
     * 
     * @param objList
     *            Bean对象集合
     * @return 结果Map集合对象
     * @author 568677
     * @date 2017年11月17日
     */
    public static List<Map<String, String>> beansToMaps(List<Object> beans) {
        List<Map<String, String>> beanMaps = new ArrayList<>();
        if (!CollectionUtils.isEmpty(beans)) {
            beans.forEach(bean -> beanMaps.add(beanToMap(bean)));
        }
        return beanMaps;
    }

    /**
     * 将map装换为javabean对象
     * 
     * @param map
     *            待转换Map对象
     * @param bean
     *            要转换的目标Bean对象
     * @return 转换结果对象
     * @author 568677
     * @date 2017年11月17日
     */
    public static <T> T mapToBean(Map<String, String> map, Class<T> beanClass) {
        T entity = null;
        if (MapUtils.isNotEmpty(map)) {
            try {
                entity = beanClass.newInstance();
                fillFields(map, entity);
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("BeanMapUtil mapToBean error", e);
            }
        }
        return entity;
    }

    /**
     * 填充所有属性名称和值
     * 
     * @param map
     *            待转换Map对象
     * @param bean
     *            要转换的目标Bean对象
     * @throws IllegalAccessException
     * @author 568677
     * @date 2017年12月29日
     */
    private static <T> void fillFields(Map<String, String> map, T entity) throws IllegalAccessException {
        Field[] fields = getFieldsCache(entity.getClass());// 获取声明的所有字段
        for (Field field : fields) {
            String value = map.get(field.getName());// 获取字段对应的值
            if (null != value) {
                field.setAccessible(true);// 设置字段访问权限
                setBeanFieldValue(entity, field, value);// 设置字段的值
            }
        }
    }

    /**
     * 设置字段的值，根据字段类型进行对应的转换后在设值
     * 
     * @param entity
     *            待设置的实体对象
     * @param field
     *            字段对象
     * @param value
     *            内容
     * @throws IllegalAccessException
     * @author 568677
     * @date 2017年11月29日
     */
    private static <T> void setBeanFieldValue(T entity, Field field, String value) throws IllegalAccessException {
        field.setAccessible(true);// 设置字段访问权限
        Class<?> fieldClazz = field.getType(); // 获取field的class及类型全路径
        if (fieldClazz.isAssignableFrom(String.class)) {
            field.set(entity, value);
        } else if (fieldClazz.isAssignableFrom(Date.class)) {
            field.set(entity, DateFormatUtil.format(value, DateFormatUtil.D_YYYYMMDD_SPLIT, DateFormatUtil.T_HHMMSSSSS_SPLIT, true));
        } else if (fieldClazz.isAssignableFrom(List.class)) {
            setBeanFieldValueForList(entity, field, fieldClazz, value);
        } else if (fieldClazz.isAssignableFrom(Map.class)) {
            setBeanFieldValueForMap(entity, field, fieldClazz, value);
        } else {
            field.set(entity, ObjectMapperUtil.toObject(value, fieldClazz));
        }
    }

    /**
     * 设置Map对象的值，将Map JSON字符串转换为Map对象；适用范围：Map《String,String》
     * 、Map《String,Bean》;非适用范围：Map《String,List《String》》、Map《String,List《Bean》》
     * 
     * @param entity
     *            待设置的实体对象
     * @param field
     *            字段对象
     * @param fieldClazz
     *            field的class及类型全路径
     * @param value
     *            内容
     * @throws IllegalAccessException
     *             当应用程序试图反射性地创建一个实例（而不是数组）、设置或获取一个字段，或者调用一个方法，但当前正在执行的方法无法访问指定类
     *             、字段、方法或构造方法的定义时，抛出
     * @author 568677
     * @date 2017年11月29日
     */
    private static <T> void setBeanFieldValueForMap(T entity, Field field, Class<?> fieldClazz, String value) throws IllegalAccessException {
        ParameterizedType pt = (ParameterizedType) field.getGenericType();
        Class<?> genericClazz1 = (Class<?>) pt.getActualTypeArguments()[0];
        Class<?> genericClazz2 = (Class<?>) pt.getActualTypeArguments()[1];
        field.set(entity, ObjectMapperUtil.toObject(value, fieldClazz, genericClazz1, genericClazz2));
    }

    /**
     * 设置List对象的值，将List JSON字符串转换为List对象
     * 
     * @param entity
     *            待设置的实体对象
     * @param field
     *            字段对象
     * @param fieldClazz
     *            field的class及类型全路径
     * @param value
     *            内容
     * @throws IllegalAccessException
     *             当应用程序试图反射性地创建一个实例（而不是数组）、设置或获取一个字段，或者调用一个方法，但当前正在执行的方法无法访问指定类
     *             、字段、方法或构造方法的定义时，抛出
     * @author 568677
     * @date 2017年11月29日
     */
    private static <T> void setBeanFieldValueForList(T entity, Field field, Class<?> fieldClazz, String value) throws IllegalAccessException {
        ParameterizedType pt = (ParameterizedType) field.getGenericType();
        Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[0];
        field.set(entity, ObjectMapperUtil.toObject(value, fieldClazz, genericClazz));
    }

    /**
     * 将List《Map《String,String》》转换为List《Bean》(将Map集合转换为对象集合)
     * 
     * @param maps
     *            Map集合对象
     * @param beanClass
     *            Bean对象
     * @return 对象集合
     * @author 568677
     * @date 2017年11月17日
     */
    public static <T> List<T> mapsToBeans(List<Map<String, String>> maps, Class<T> beanClass) {
        List<T> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(maps)) {
            for (Map<String, String> beanMap : maps) {
                if (MapUtils.isNotEmpty(beanMap)) {
                    resultList.add(mapToBean(beanMap, beanClass));
                }
            }
        }
        return resultList;
    }
}
