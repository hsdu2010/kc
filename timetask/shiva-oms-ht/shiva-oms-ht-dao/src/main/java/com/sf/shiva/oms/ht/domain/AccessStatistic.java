package com.sf.shiva.oms.ht.domain;

/**
 * 该类与数据库中的表tt_access_statistic对应 
 * 
 * @author 01369626
 */
public class AccessStatistic extends AccessStatisticKey {
    private Integer userNum;

    private Integer usageCount;

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }

    public Integer getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(Integer usageCount) {
        this.usageCount = usageCount;
    }
}