package com.sf.shiva.oms.ht.dto;

/**
 * 描述：  RPC 基础信息
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年10月26日      80002517         Create
 * ****************************************************************************
 * </pre>
 * @author 80002517
 * @since 1.0
 */
public class RpcInvokerDto {
    /** zk地址 */
    private String zkUrl;
    /** 接口名 */
    private String interfaceName;
    /** 方法下标 */
    private String methodIndex;
    /** 版本号 */
    private String version;
    /** 参数 */
    private String methodParam;
    /** 应用名 */
    private String applicationName;
    /** jar名 */
    private String jar;
    
    public String getZkUrl() {
        return zkUrl;
    }
    public void setZkUrl(String zkUrl) {
        this.zkUrl = zkUrl;
    }
    public String getInterfaceName() {
        return interfaceName;
    }
    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }
    public String getMethodIndex() {
        return methodIndex;
    }
    public void setMethodIndex(String methodIndex) {
        this.methodIndex = methodIndex;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
   
    public String getMethodParam() {
        return methodParam;
    }
    public void setMethodParam(String methodParam) {
        this.methodParam = methodParam;
    }
    public String getApplicationName() {
        return applicationName;
    }
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
    public String getJar() {
        return jar;
    }
    public void setJar(String jar) {
        this.jar = jar;
    }
    
}
