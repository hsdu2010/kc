package com.sf.shiva.oms.psm.cfgmanage;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * 描述：配置信息：event-to-logic-service-properties
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月14日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
@Component
public class EventToLogicServiceProperties implements Serializable{

	/**  */
	private static final long serialVersionUID = 8169962067299460826L;
	
	@Value("${event.to.logic.service.config}")
	private String eventToLogicServiceConfig;

	public String getEventToLogicServiceConfig() {
		return eventToLogicServiceConfig;
	}

	public void setEventToLogicServiceConfig(String eventToLogicServiceConfig) {
		this.eventToLogicServiceConfig = eventToLogicServiceConfig;
	}
	
}
