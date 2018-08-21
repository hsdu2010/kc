package com.sf.shiva.oms.ht.domain;

/**
 * 该类与数据库中的表tt_operation_info对应 
 * 
 * @author 01369626
 */
public class OperationInfo extends OperationInfoKey {
    private String moduleName;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}