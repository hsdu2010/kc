package com.sf.shiva.oms.ht.domain.dto;

/**
 * 
 * 描述：redis操作dto类
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月3日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
public class RedisOperateDto {
	
	private String address;
	private String masterName;
	private Integer timeout;
	private Integer useSentinel;
	private String password;
	private String queryKeys;
	private String queryValues;
	private Integer operateType;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMasterName() {
		return masterName;
	}
	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}
	public Integer getTimeout() {
		return timeout;
	}
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	public Integer getUseSentinel() {
		return useSentinel;
	}
	public void setUseSentinel(Integer useSentinel) {
		this.useSentinel = useSentinel;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getQueryKeys() {
		return queryKeys;
	}
	public void setQueryKeys(String queryKeys) {
		this.queryKeys = queryKeys;
	}
	public Integer getOperateType() {
		return operateType;
	}
	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}
    public String getQueryValues() {
        return queryValues;
    }
    public void setQueryValues(String queryValues) {
        this.queryValues = queryValues;
    }
	
}
