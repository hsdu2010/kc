package com.sf.shiva.oms.ht.web.listener;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.service.contants.Constants;
import com.sf.shiva.oms.ht.service.util.JarClassLoader;


@Component("bootstrapListener")
public class BootstrapListener implements ApplicationContextAware  {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        try {
            logger.info("加载上传的jar start.....");
            JarClassLoader.loadJarByDirectory(Constants.REDIS_JAR_HOME);
            logger.info("加载上传的jar end....");
        } catch (NoSuchMethodException | SecurityException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | MalformedURLException e) {
            e.printStackTrace();
        }
        
    }

}
