package com.sf.shiva.oms.psm.cfgmanage;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * 描述：配置信息，system.properties
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月13日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 * @since 1.0
 */
@Component
public class SystemProperties implements Serializable{

    private static final long serialVersionUID = -5121242588811055083L;

    @Value("${system.env:PRO}")
    private String systemEnv;// 系统运行环境配置（DEV、SIT、PRO）

    public String getSystemEnv() {
        return systemEnv;
    }

    public void setSystemEnv(String systemEnv) {
        this.systemEnv = systemEnv;
    }

}
