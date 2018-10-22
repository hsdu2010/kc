package com.sf.shiva.oms.psm.cfgmanage;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 描述：包裹状态重试Soput配置信息
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年1月16日      80002517         Create
 * ****************************************************************************
 * </pre>
 * @author 80002517
 * @since 1.0
 */
@Component
public class PackageStatusSendLoadSpoutProperties implements Serializable{

    /**  */
    private static final long serialVersionUID = 1L;
    @Value("${packageStatusSendLoadSpout.thread.initialDelay:10}")
    private int threadInitialDelay;
    @Value("${packageStatusSendLoadSpout.thread.delay:60}")
    private int threadDelay;
    @Value("${packageStatusSendLoadSpout.message.queueSize:1000}")
    private int messageQueueSize;
    
    public int getThreadInitialDelay() {
        return threadInitialDelay;
    }
    public void setThreadInitialDelay(int threadInitialDelay) {
        this.threadInitialDelay = threadInitialDelay;
    }
    public int getThreadDelay() {
        return threadDelay;
    }
    public void setThreadDelay(int threadDelay) {
        this.threadDelay = threadDelay;
    }
    public int getMessageQueueSize() {
        return messageQueueSize;
    }
    public void setMessageQueueSize(int messageQueueSize) {
        this.messageQueueSize = messageQueueSize;
    }
    
    
}
