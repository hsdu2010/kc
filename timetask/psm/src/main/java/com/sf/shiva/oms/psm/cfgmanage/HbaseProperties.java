package com.sf.shiva.oms.psm.cfgmanage;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * 描述：配置信息，hbase.properties
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
public class HbaseProperties implements Serializable {

    private static final long serialVersionUID = 4761176315286767303L;

    @Value("${shiva_oms_psm_package_status.par.num:0}")
    private int packageStatusParNum;// 包裹状态表分区数
    @Value("${shiva_oms_psm_package_status_record.par.num:0}")
    private int packageStatusEventParNum;// 包裹状态事件表分区数
    @Value("${shiva_oms_psm_package_status_send_load.par.num:0}")
    private int packageStatusSendLoadParNum;// 包裹状态发送加载表分区数
    @Value("${shiva_oms_psm_package_settle_claims.par.num:0}")
    private int packageSettleClaimsNum;// 包裹理赔信息表分区数

    public int getPackageStatusParNum() {
        return packageStatusParNum;
    }

    public void setPackageStatusParNum(int packageStatusParNum) {
        this.packageStatusParNum = packageStatusParNum;
    }

    public int getPackageStatusEventParNum() {
        return packageStatusEventParNum;
    }

    public void setPackageStatusEventParNum(int packageStatusEventParNum) {
        this.packageStatusEventParNum = packageStatusEventParNum;
    }

    public int getPackageStatusSendLoadParNum() {
        return packageStatusSendLoadParNum;
    }

    public void setPackageStatusSendLoadParNum(int packageStatusSendLoadParNum) {
        this.packageStatusSendLoadParNum = packageStatusSendLoadParNum;
    }

    public int getPackageSettleClaimsNum() {
        return packageSettleClaimsNum;
    }

    public void setPackageSettleClaimsNum(int packageSettleClaimsNum) {
        this.packageSettleClaimsNum = packageSettleClaimsNum;
    }

}
