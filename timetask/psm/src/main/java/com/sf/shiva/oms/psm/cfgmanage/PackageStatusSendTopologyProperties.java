package com.sf.shiva.oms.psm.cfgmanage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 描述：包裹状态下发应用配置
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月26日      80002517         Create
 * ****************************************************************************
 * </pre>
 * @author 80002517
 * @since 1.0
 */
@Component
public class PackageStatusSendTopologyProperties {
    
    @Value("${javaOpts}")
    private String javaOpts;// 自定义JAVA配置
    @Value("${topology.packageStatusSend.debugFlag}")
    private boolean debugFlag;// 是否调试模式
    @Value("${topology.packageStatusSend.workerNum}")
    private int workerNum;// 包裹状态下发 worker数
    @Value("${topology.packageStatusSendSpout.threadNum}")
    private int spoutThreadNum;// 包裹状态下发计算Spout线程数
    @Value("${topology.packageStatusSendBolt.threadNum}")
    private int packageStatusSendBoltThreadNum;// 包裹状态下发Bolt线程数
    
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
    public int getWorkerNum() {
        return workerNum;
    }
    public void setWorkerNum(int workerNum) {
        this.workerNum = workerNum;
    }
    public int getSpoutThreadNum() {
        return spoutThreadNum;
    }
    public void setSpoutThreadNum(int spoutThreadNum) {
        this.spoutThreadNum = spoutThreadNum;
    }
    public int getPackageStatusSendBoltThreadNum() {
        return packageStatusSendBoltThreadNum;
    }
    public void setPackageStatusSendBoltThreadNum(int packageStatusSendBoltThreadNum) {
        this.packageStatusSendBoltThreadNum = packageStatusSendBoltThreadNum;
    }
    
    
}
