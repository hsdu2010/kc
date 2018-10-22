package com.sf.shiva.oms.psm.common.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 描述：当前状态与下一个状态关系配置类
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月29日      01159741         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01159741
 * @since 1.0
 */
public class CurToNextStatusEntity implements Serializable {

    private static final long serialVersionUID = -2014725682022737559L;
    private String curStatus;// 当前状态
    private List<String> nextStatusList;// 当前状态对应可翻转的下一个状态集合

    public String getCurStatus() {
        return curStatus;
    }

    public void setCurStatus(String curStatus) {
        this.curStatus = curStatus;
    }

    public List<String> getNextStatusList() {
        return nextStatusList;
    }

    public void setNextStatusList(List<String> nextStatusList) {
        this.nextStatusList = nextStatusList;
    }

}