package com.sf.shiva.oms.psm.common.entity;

import java.io.Serializable;
import java.util.Date;

import com.sf.shiva.oms.psm.common.annotation.ColumnFamily;
import com.sf.shiva.oms.psm.common.annotation.NameSpace;
import com.sf.shiva.oms.psm.common.annotation.TableName;

/**
 * 
 * 描述：包裹理赔信息；RowKey=分区号_包裹号；分区号=MD5Hash.getMD5AsHex(包裹号).substring(0, 8)
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
@TableName("package_settle_claims")
@ColumnFamily("cf1")
public class PackageSettleClaimsEntity implements Serializable {

    private static final long serialVersionUID = 6292341142978336357L;

    private String packageNo;// 包裹号
    private String state;// 状态(1、新建；2、提交ECP；3、结案；4、放弃)
    private Date createTm;// 创建时间

    public String getPackageNo() {
        return packageNo;
    }

    public void setPackageNo(String packageNo) {
        this.packageNo = packageNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTm() {
        return createTm;
    }

    public void setCreateTm(Date createTm) {
        this.createTm = createTm;
    }

}
