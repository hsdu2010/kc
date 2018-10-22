package com.sf.shiva.oms.psm.cfgmanage;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * 描述：配置信息，curstatus-to-nextstatus.properties
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月13日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369521
 * @since 1.0
 */

@Component
public class CurToNextStatusProperties implements Serializable {

	private static final long serialVersionUID = 8933776582371105777L;

	@Value("${curstatus.to.nextstatus.config}")
	private String curToNextStatusConfig;

	public String getCurToNextStatusConfig() {
		return curToNextStatusConfig;
	}

	public void setCurToNextStatusConfig(String curToNextStatusConfig) {
		this.curToNextStatusConfig = curToNextStatusConfig;
	}
}
