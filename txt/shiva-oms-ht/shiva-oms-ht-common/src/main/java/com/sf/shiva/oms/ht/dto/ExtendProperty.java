package com.sf.shiva.oms.ht.dto;

/**
 * 描述：回传oms普通订单的状态信息中的拓展信息
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年10月12日        80002767          Create
 * ****************************************************************************
 * </pre>
 * @author 80002767
 * @since 1.0
 */
public class ExtendProperty {
    /**
     * 扩展字段的key
     */
    private String key;
    /**
     * 扩展字段的value
     */
    private String value;

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
