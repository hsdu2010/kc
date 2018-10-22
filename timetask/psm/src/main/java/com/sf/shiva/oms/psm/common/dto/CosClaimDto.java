package com.sf.shiva.oms.psm.common.dto;

import java.io.Serializable;

/**
 * @Description 接收cos理赔运单信息实体类
 * 
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON       REASON    
 *  1    2016.8.8        879149       Create   
 * ****************************************************************************
 * 
 */
public class CosClaimDto implements Serializable{

    private static final long serialVersionUID = 3075617965876197773L;
    
    private String carryId; //运单号
    private Long claimJobId; //理赔工单号
    private String claimStatus; //理赔状态1、新建 2、提交ECP 3、结案 4、放弃
    
    private String claimProcessTimeString; // 理赔处理的时间
    private String claimProcessNetCode;// 理赔处理网点代码
    private String claimProcessUserName; // 理赔处理人
    
    public String getCarryId() {
        return carryId;
    }
    public void setCarryId(String carryId) {
        this.carryId = carryId;
    }
    public Long getClaimJobId() {
        return claimJobId;
    }
    public void setClaimJobId(Long claimJobId) {
        this.claimJobId = claimJobId;
    }
    public String getClaimStatus() {
        return claimStatus;
    }
    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }
    
    public String getClaimProcessTimeString() {
        return claimProcessTimeString;
    }
    public void setClaimProcessTimeString(String claimProcessTimeString) {
        this.claimProcessTimeString = claimProcessTimeString;
    }
    public String getClaimProcessNetCode() {
        return claimProcessNetCode;
    }
    public void setClaimProcessNetCode(String claimProcessNetCode) {
        this.claimProcessNetCode = claimProcessNetCode;
    }
    public String getClaimProcessUserName() {
        return claimProcessUserName;
    }
    public void setClaimProcessUserName(String claimProcessUserName) {
        this.claimProcessUserName = claimProcessUserName;
    }
}
