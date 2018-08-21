package com.sf.shiva.oms.ht.domain;

import java.util.Date;

/**
 * 该类与数据库中的表tm_hbase_query_cfg对应 
 * 
 * @author 01369626
 */
public class HbaseQueryCfg {
    private String id;

    private String moduleName;

    private String tableName;

    private String queryType;

    private String filterFamily;

    private String filterColumn;

    private String queryParam;

    private String isFuzzySearch;

    private Integer limitNum;

    private Date createTm;

    private String createEmp;

    private Date modifyTm;

    private String modifyEmp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getFilterFamily() {
        return filterFamily;
    }

    public void setFilterFamily(String filterFamily) {
        this.filterFamily = filterFamily;
    }

    public String getFilterColumn() {
        return filterColumn;
    }

    public void setFilterColumn(String filterColumn) {
        this.filterColumn = filterColumn;
    }

    public String getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(String queryParam) {
        this.queryParam = queryParam;
    }

    public String getIsFuzzySearch() {
        return isFuzzySearch;
    }

    public void setIsFuzzySearch(String isFuzzySearch) {
        this.isFuzzySearch = isFuzzySearch;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public Date getCreateTm() {
        return createTm;
    }

    public void setCreateTm(Date createTm) {
        this.createTm = createTm;
    }

    public String getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp;
    }

    public Date getModifyTm() {
        return modifyTm;
    }

    public void setModifyTm(Date modifyTm) {
        this.modifyTm = modifyTm;
    }

    public String getModifyEmp() {
        return modifyEmp;
    }

    public void setModifyEmp(String modifyEmp) {
        this.modifyEmp = modifyEmp;
    }
}