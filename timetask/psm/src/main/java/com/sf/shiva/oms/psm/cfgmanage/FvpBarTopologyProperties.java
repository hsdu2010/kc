package com.sf.shiva.oms.psm.cfgmanage;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * 描述：配置信息，topology.properties
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
public class FvpBarTopologyProperties implements Serializable {

    private static final long serialVersionUID = -6064278366699059431L;

    @Value("${fvp.topology.max.spout.pending:500}")
    private int maxSpoutPending;// 一次读取kafka会读取多少数据
    @Value("${javaOpts}")
    private String javaOpts;// 自定义JAVA配置
    @Value("${topology.debugFlag}")
    private boolean debugFlag;// 是否调试模式
    @Value("${topology.fvpBar.workerNum}")
    private int fvpBarWorkerNum;// FVP巴枪worker数，topology.fvpBar.workerNum
    @Value("${topology.fvpBarSpout.threadNum}")
    private int fvpBarSpoutThreadNum;// FVP巴枪计算Spout线程数，topology.fvpBarSpout.threadNum
    @Value("${topology.fvpBarCalBolt.threadNum}")
    private int fvpBarCalBoltThreadNum;// FVP巴枪计算bolt线程数，topology.fvpBarCalBolt.threadNum
    @Value("${topology.fvpPackageStatusSendBolt.threadNum}")
    private int packageStatusSendBoltThreadNum;// 包裹状态发送Bolt线程数，topology.pcakageStatusSendBolt.threadNum

    public int getMaxSpoutPending() {
        return maxSpoutPending;
    }

    public void setMaxSpoutPending(int maxSpoutPending) {
        this.maxSpoutPending = maxSpoutPending;
    }

    public String getJavaOpts() {
        return javaOpts;
    }

    public void setJavaOpts(String javaOpts) {
        this.javaOpts = javaOpts;
    }

    public boolean isDebugFlag() {
        return debugFlag;
    }

    public void setDebugFlag(boolean debugFlag) {
        this.debugFlag = debugFlag;
    }

    public int getFvpBarWorkerNum() {
        return fvpBarWorkerNum;
    }

    public void setFvpBarWorkerNum(int fvpBarWorkerNum) {
        this.fvpBarWorkerNum = fvpBarWorkerNum;
    }

    public int getFvpBarSpoutThreadNum() {
        return fvpBarSpoutThreadNum;
    }

    public void setFvpBarSpoutThreadNum(int fvpBarSpoutThreadNum) {
        this.fvpBarSpoutThreadNum = fvpBarSpoutThreadNum;
    }

    public int getFvpBarCalBoltThreadNum() {
        return fvpBarCalBoltThreadNum;
    }

    public void setFvpBarCalBoltThreadNum(int fvpBarCalBoltThreadNum) {
        this.fvpBarCalBoltThreadNum = fvpBarCalBoltThreadNum;
    }

    public int getPackageStatusSendBoltThreadNum() {
        return packageStatusSendBoltThreadNum;
    }

    public void setPackageStatusSendBoltThreadNum(int packageStatusSendBoltThreadNum) {
        this.packageStatusSendBoltThreadNum = packageStatusSendBoltThreadNum;
    }

}
