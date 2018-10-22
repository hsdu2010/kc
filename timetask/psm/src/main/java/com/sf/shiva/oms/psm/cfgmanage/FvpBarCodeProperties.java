package com.sf.shiva.oms.psm.cfgmanage;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * 
 * 描述：巴枪码配置类 配置信息：fvpBarCode.config
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月14日      01369521         Create
 * ****************************************************************************
 * </pre>
 * @author 01369521
 * @since 1.0
 */
@Component
public class FvpBarCodeProperties implements Serializable {	
    private static final long serialVersionUID = 8933776582371105777L;

    @Value("${fvpBarCode.config}")
    private String fvpBarCodeConfig;

	public String getFvpBarCodeConfig() {
		return fvpBarCodeConfig;
	}

	public void setFvpBarCodeConfig(String fvpBarCodeConfig) {
		this.fvpBarCodeConfig = fvpBarCodeConfig;
	}
}
