package com.sf.shiva.oms.psm.common.entity;

import java.io.Serializable;

/**
 * 
 * 描述：包裹状态事件扩展属性对象
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
public class PackageStatusRecordExtendEntity implements Serializable {

    private static final long serialVersionUID = -6781432890750769294L;

    private String key; // 属性
    private String value; // 属性值

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
