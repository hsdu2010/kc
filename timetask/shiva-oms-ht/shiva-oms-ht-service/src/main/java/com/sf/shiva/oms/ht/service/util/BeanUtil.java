package com.sf.shiva.oms.ht.service.util;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import com.alibaba.dubbo.common.json.ParseException;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.sf.shiva.oms.ht.dto.ClassInfoDto;
import com.sf.shiva.oms.ht.service.contants.Constants;

/**
 * 描述：bean 对象帮助类
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年10月26日      80002517         Create
 * ****************************************************************************
 * </pre>
 * @author 80002517
 * @since 1.0
 */
public class BeanUtil {
    
    /** 缓存类 */
    public static final Map<String, Class<?>> CACHEDCLASS = new ConcurrentHashMap<>();
    private static final String RPC_SUFFIX = "Service.class";
    private static final String CLASS_SUFFIX = ".class";
    private static final String REST_SUFFIX = "RestService.class";
    
    /** 
     * 载入类
     * @param className
     * @return
     * @throws ClassNotFoundException
     * @author 80002517
     * @date 2017年10月26日
     */
    public static Class<?> loadClass(String className, String jarName){
        if(CACHEDCLASS.containsKey(className)){
            return CACHEDCLASS.get(className);
        }
        Class<?> cls = null;
        try {
            cls = RpcClassLoader.class.getClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            if(jarName != null) //加载jar
                cls =  RpcClassLoader.loadClass(className, jarName);
        }
        if(cls != null){
            CACHEDCLASS.put(className, cls); //缓存
        }
        return cls;
    }
    
    /** 
     * 将String字符串转换为指定的paramCls类型
     * @param paramCls 指定转换类型
     * @param value 值
     * @return
     * @throws ParseException
     * @throws java.text.ParseException
     * @author 80002517
     * @date 2017年10月26日
     */
    public static Object convertType(Class<?> paramCls, String value) throws ParseException, java.text.ParseException {
        if (StringUtils.isBlank(value)){
            return null;
        }
        if(paramCls.equals(Integer.class)){
            return Integer.parseInt(value);
        }
        if (paramCls.equals(String.class)){
            return ParameterUtil.argToString(value);
        }
        if ((paramCls.equals(List.class))
                || (paramCls.isAssignableFrom(Collection.class))){
            return ParameterUtil.argToList(value, paramCls);
        }
        if (paramCls.isEnum()){
            return ParameterUtil.argToEnum(value, paramCls);
        }
        if (paramCls.equals(Date.class)){
            return ParameterUtil.argToDate(value);
        }
        return ParameterUtil.argToPojo(value, paramCls);
    }
    
    /** 
     * 根据方法参数信息和string信息， 得到方法参数值
     * @param method
     * @param tuples
     * @return
     * @throws ParseException
     * @throws java.text.ParseException
     * @author 80002517
     * @date 2017年10月26日
     */
    public static Object[] getMethodArgsValue(Method method, String ... values) throws ParseException, java.text.ParseException{
        Class<?>[] paramAndTypes = method.getParameterTypes();
        Object[] args = new Object[paramAndTypes.length];
        for (int i = 0; i < paramAndTypes.length; i++) {
            String value = values[i];
            args[i] = BeanUtil.convertType(paramAndTypes[i], value);
        }
        return args;
    }
    
    public static Method[] getMethods(String interfaceName) throws ClassNotFoundException{
        Class<?> interfaceClass = loadClass(interfaceName, null);;
        return interfaceClass.getMethods();
    }
    
    /** 
     * 根据接口，获取方法信息
     * @param interfaceClass
     * @return
     * @author 80002517
     * @throws ClassNotFoundException 
     * @date 2017年10月26日
     */
    public static List<String> getMethodInfo(String interfaceName, String jarName) throws ClassNotFoundException{
        Class<?> interfaceClass = loadClass(interfaceName, jarName);
        if(interfaceClass != null){
            List<String> methodInfos = new ArrayList<>();
            for(Method method : interfaceClass.getMethods()){
                StringBuilder methodStr = new StringBuilder(method.getName()).append("(");
                Class<?>[] paramTypes = method.getParameterTypes();
                int paramLength = paramTypes.length;
                for(int i = 0; i<paramLength; i++){
                    if(i == paramLength - 1){
                        methodStr.append(paramTypes[i].getSimpleName());
                    }else{
                        methodStr.append(paramTypes[i].getSimpleName()).append(", ");
                    }
                }
                methodStr.append(")");
                methodInfos.add(methodStr.toString());
            }
            return methodInfos;
        }
        return Collections.emptyList();
    }
    
    public static List<ClassInfoDto> getInterfaceInfoList(String jarName) throws ClassNotFoundException, IOException{
        File file = new File(Constants.JAR_HOME + File.separator + jarName);
        List<ClassInfoDto> dtos = new ArrayList<>();
        JarFile jarFile = null;
        try{
            jarFile = new JarFile(file);
            Enumeration<JarEntry> ele = jarFile.entries();
            while(ele.hasMoreElements()){
                JarEntry entry =   ele.nextElement();
                String name = entry.getName();
                if(name.endsWith(RPC_SUFFIX) && !name.endsWith(REST_SUFFIX)){
                    int suffixIndex = name.indexOf(CLASS_SUFFIX);
                    ClassInfoDto dto = new ClassInfoDto();
                    String className = name.substring(0, suffixIndex).replace('/', '.');
                    dto.setMethods(getMethodInfo(className, jarName));
                    dto.setClassName(className);
                    dtos.add(dto);
                }
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(jarFile != null){
                jarFile.close();
            }
        }
        return dtos;
    }
    
}
