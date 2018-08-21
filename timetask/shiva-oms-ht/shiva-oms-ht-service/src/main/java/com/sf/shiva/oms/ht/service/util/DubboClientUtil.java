package com.sf.shiva.oms.ht.service.util;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;

@SuppressWarnings("rawtypes")
public class DubboClientUtil {
    
    private static ConcurrentHashMap<String, Object> hmService = new ConcurrentHashMap<>();
    private String zk;
    private static final int TIMEOUT = 20000;
    
    private DubboClientUtil(String zk){
        this.zk = zk;
    }

    public static DubboClientUtil getInstance(String zk){
        return DubboClientSinglet.getInstance(zk);
    }
    
    /** 
     * 获取连接
     * @author 80002517
     * @date 2017年10月19日
     */
    @SuppressWarnings("unchecked")
    private ReferenceConfig acquireReference(Class<?> cls, String version, String applicationName){
        ReferenceConfig reference = new ReferenceConfig(); // 该类很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        RegistryConfig registryConfig = new RegistryConfig(zk);
        registryConfig.setCheck(false);
        registryConfig.setTimeout(TIMEOUT);
        registryConfig.setId(zk);
        reference.setRegistry(registryConfig);
        reference.setRetries(0);
        reference.setApplication(new ApplicationConfig(applicationName));
        reference.setInterface(cls);
        reference.setVersion(version);
        return reference;
   }
   
   public  Object getBean(Class<?> cls, String version, String applicationName) {
       try {
            String className = cls.getSimpleName();
            Object service =  hmService.get(className);
            if (service != null) {
                return service;
            }
            ReferenceConfig reference = acquireReference(cls, version, applicationName);
            service = reference.get();// 获取远程服务代理;
            
            return service;
        
        } catch (Exception e) {
            throw e;
        }
   }
  
  public static void cached(String className, Object service){
      hmService.put(className, service);
  }
  
  private static class DubboClientSinglet{
      
      private static ConcurrentHashMap<String, DubboClientUtil> clients = new ConcurrentHashMap<>();
      
      private DubboClientSinglet(){
      }
      
      private static DubboClientUtil getInstance(String zk){
          if(!clients.containsKey(zk)){
              clients.put(zk, new DubboClientUtil(zk));
          }
          return clients.get(zk);
      }
  }
}
