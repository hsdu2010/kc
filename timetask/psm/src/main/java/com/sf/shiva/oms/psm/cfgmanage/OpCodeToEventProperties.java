package com.sf.shiva.oms.psm.cfgmanage;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 描述：配置信息opcode-to-event.properties
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
public class OpCodeToEventProperties implements Serializable {
	/**  */
	private static final long serialVersionUID = -8360693579798008071L;

	@Value("${opcode.to.event.config}")
	private String opCodeToEventConfig;

	public String getOpCodeToEventConfig() {
		return opCodeToEventConfig;
	}

	public void setOpCodeToEventConfig(String opCodeToEventConfig) {
		this.opCodeToEventConfig = opCodeToEventConfig;
	}

}
