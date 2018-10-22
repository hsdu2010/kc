package com.sf.shiva.oms.psm.common.entity;

import java.io.Serializable;
import java.util.Date;

import com.sf.shiva.oms.psm.common.annotation.ColumnFamily;
import com.sf.shiva.oms.psm.common.annotation.NameSpace;
import com.sf.shiva.oms.psm.common.annotation.TableName;

/**
 * 
 * 描述：包裹状态对象； RowKey=分区号_包裹号；分区号=hash(包裹号)%39
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月6日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 * @since 1.0
 */
@NameSpace("shiva_oms_psm")
@TableName("package_status")
@ColumnFamily("cf1")
public class PackageStatusEntity implements Serializable {

    private static final long serialVersionUID = 5440588096294947578L;

    private String id;// 包裹状态ID
    private String packageNo;// 包裹号
    private String packageStatus;// 包裹状态
    private Date operateTm;// 操作时间
    private Date createTm;// 创建时间
    private Date modifyTm;// 修改时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPackageNo() {
        return packageNo;
    }

    public void setPackageNo(String packageNo) {
        this.packageNo = packageNo;
    }

    public String getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(String packageStatus) {
        this.packageStatus = packageStatus;
    }

    public Date getOperateTm() {
        return operateTm;
    }

    public void setOperateTm(Date operateTm) {
        this.operateTm = operateTm;
    }

    public Date getCreateTm() {
        return createTm;
    }

    public void setCreateTm(Date createTm) {
        this.createTm = createTm;
    }

    public Date getModifyTm() {
        return modifyTm;
    }

    public void setModifyTm(Date modifyTm) {
        this.modifyTm = modifyTm;
    }

}
