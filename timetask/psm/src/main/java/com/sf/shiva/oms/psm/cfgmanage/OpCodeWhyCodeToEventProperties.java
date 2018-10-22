package com.sf.shiva.oms.psm.cfgmanage;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 描述：配置信息opcode-whycode-to-event.properties
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月14日      01369610         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369610
 * @since 1.0
 */
@Component
public class OpCodeWhyCodeToEventProperties implements Serializable {
	/**  */
	private static final long serialVersionUID = 279549224043531255L;

	@Value("${opcode.whycode.to.event.config}")
	private String opCodeWhyCodeToEventConfig;

	public String getOpCodeWhyCodeToEventConfig() {
		return opCodeWhyCodeToEventConfig;
	}

	public void setOpCodeWhyCodeToEventConfig(String opCodeWhyCodeToEventConfig) {
		this.opCodeWhyCodeToEventConfig = opCodeWhyCodeToEventConfig;
	}
}
