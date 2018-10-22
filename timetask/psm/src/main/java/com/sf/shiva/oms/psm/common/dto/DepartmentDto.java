package com.sf.shiva.oms.psm.common.dto;

import java.io.Serializable;

/**
 * 
 * 描述：机构信息对象
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月4日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 * @since 1.0
 */
public class DepartmentDto implements Serializable {

    private static final long serialVersionUID = -246588906808704469L;

    private Long deptId;// 机构ID
    private String divisionCode;// 分点部代码
    private String areaCode;// 区部代码
    private String hqCode;// 经营本部代码
    private String typeCode;// 机构类型
    private String deptName;// 机构名称
    private String deptCode;// 网络网点代码（机构代码）
    private String distCode;// 行政区部编码
    private String parentDeptCode;// 上级部门编码
    private Integer typeLevel;// 类型层次
    private String currencyCode;// 币别编码

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getHqCode() {
        return hqCode;
    }

    public void setHqCode(String hqCode) {
        this.hqCode = hqCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDistCode() {
        return distCode;
    }

    public void setDistCode(String distCode) {
        this.distCode = distCode;
    }

    public String getParentDeptCode() {
        return parentDeptCode;
    }

    public void setParentDeptCode(String parentDeptCode) {
        this.parentDeptCode = parentDeptCode;
    }

    public Integer getTypeLevel() {
        return typeLevel;
    }

    public void setTypeLevel(Integer typeLevel) {
        this.typeLevel = typeLevel;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

}
