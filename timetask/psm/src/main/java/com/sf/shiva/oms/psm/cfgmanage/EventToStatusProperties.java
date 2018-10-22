package com.sf.shiva.oms.psm.cfgmanage;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 描述：配置信息event-to-status.properties
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
public class EventToStatusProperties implements Serializable {
	/**  */
	private static final long serialVersionUID = 1649208921747879724L;

	@Value("${event.to.status.config}")
	private String eventToStatusConfig;

	public String getEventToStatusConfig() {
		return eventToStatusConfig;
	}

	public void setEventToStatusConfig(String eventToStatusConfig) {
		this.eventToStatusConfig = eventToStatusConfig;
	}
}
