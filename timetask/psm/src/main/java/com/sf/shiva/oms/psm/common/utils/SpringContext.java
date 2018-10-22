package com.sf.shiva.oms.psm.common.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * 描述：spring容器上下文
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年9月22日      80002137         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 80002137
 * @since 1.0
 */
public class SpringContext {

    private SpringContext() {
    }

    public static ApplicationContext getInstance() {
        return SpringContextInit.instance;
    }

    private static class SpringContextInit {
    	private SpringContextInit(){
    		
    	}
        private static ApplicationContext instance = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/spring-*.xml");
    }

}
