package com.sf.shiva.oms.psm.common.entity;

import java.io.Serializable;
import java.util.Date;

import com.sf.shiva.oms.psm.common.annotation.ColumnFamily;
import com.sf.shiva.oms.psm.common.annotation.NameSpace;
import com.sf.shiva.oms.psm.common.annotation.TableName;

/**
 * 
 * 描述：包裹状态发送加载对象；RowKey=分区号_包裹号_创建时间戳；分区号=MD5Hash.getMD5AsHex(包裹号).substring(0, 8)
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
@TableName("package_status_send_load")
@ColumnFamily("cf1")
public class PackageStatusSendLoadEntity implements Serializable {

    private static final long serialVersionUID = 2247190668797043848L;

    private String id;// 主键id
    private String packageNo;// 包裹号
    private String msg;// 发送报文
    private Date createTm;// 创建时间
    private Date modifyTm; //更新时间

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
