package com.sf.shiva.oms.psm.common.utils;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.shiva.oms.psm.common.exception.BusinessException;

/**
 * 
 * 描述：XML转换工具类
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月18日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 * @since 1.0
 */
public class XMLUtils {
	
	private XMLUtils() {
		super();
	}
	
    private static final Logger logger = LoggerFactory.getLogger(XMLUtils.class);
    private static final String DEFAULT_ENCODING = "UTF-8";// xml编码格式
    private static Map<Class<?>, JAXBContext> contextMapCache = new HashMap<>();// JAXBContext容器

    /** 将泛型对象解析为xml格式
     * @param bean 对象
     * @param beanClass 对象类型
     * @return xml报文
     * @author 01369610
     * @date 2018年1月11日
     */
    public static <T> String toXml(Object bean, Class<T> beanClass) {
        try {
            JAXBContext context = getJaxbContext(beanClass);
            Marshaller marshaller = context.createMarshaller();
            //marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true); //去掉xml消息头
            //marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);//格式化
            marshaller.setProperty(Marshaller.JAXB_ENCODING, DEFAULT_ENCODING);
            StringWriter writer = new StringWriter();
            marshaller.marshal(bean, writer);
            return writer.toString();
        } catch (JAXBException e) {
            logger.error("toXml error", e);
            return null;
        }
    }

    /**
     * 将xml格式数据解析为泛型对象
     * 
     * @param xml
     *            xml格式数据
     * @param clazz
     *            字节码对象<T>
     * @return 具体对象
     * @author 80002110
     */
    @SuppressWarnings("unchecked")
    public static <T> T toBean(String xml, Class<T> beanClass) {
        try {
            JAXBContext context = getJaxbContext(beanClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            logger.error("formXML error", e);
            throw new BusinessException(e);
        }
    }

    /**
     * 将xml数据对应的字节码对象缓存在JAXBContext容器中
     * 
     * @param beanCalss
     *            字节码对象
     * @return JAXBContext容器
     * @throws JAXBException
     * @author 80002110
     */
    private static <T> JAXBContext getJaxbContext(Class<T> beanClass) throws JAXBException {
        JAXBContext jaxbContext = contextMapCache.get(beanClass);
        if (jaxbContext == null) {
            synchronized (XMLUtils.class) {
                jaxbContext = contextMapCache.get(beanClass);
                if (jaxbContext == null) {
                    jaxbContext = JAXBContext.newInstance(beanClass);
                    contextMapCache.put(beanClass, jaxbContext);
                }
            }
        }
        return jaxbContext;
    }
}
