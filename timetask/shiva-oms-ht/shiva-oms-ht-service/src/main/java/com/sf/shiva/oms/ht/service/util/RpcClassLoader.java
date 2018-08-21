package com.sf.shiva.oms.ht.service.util;

import java.io.File;

import com.alibaba.dubbo.rpc.RpcException;
import com.sf.shiva.oms.ht.service.contants.Constants;

/**
 * 描述： 动态加载Jar
 * 我是做的spring在容器启动的情况下，动态加载classpath以外的jar包资源。本来什么问题都解决了，就是spring使用annotation扫描的时候，
 * 怎么都读取不到我用ClassLoader加载的类。后来通过读源码发现，是我的ClassLoader是新建的，而spring默认就用的系统的ClassLoader，
 * 两个ClassLoader不一致，导致以上的问题。而用你的这种反射原理，恰好就解决了这个问题。
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年10月26日      80002517         Create
 * ****************************************************************************
 * </pre>
 * @author 80002517
 * @since 1.0
 */
public class RpcClassLoader {
    
    private RpcClassLoader(){}
    
    public static Class<?> loadClass(String className, String jarName){
        try {
            JarClassLoader.loadJar(Constants.JAR_HOME + File.separator + jarName);
            return Class.forName(className);            
        } catch (Exception e) {
            throw new RpcException(e);
        }
    }
}
