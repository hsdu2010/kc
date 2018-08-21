package com.sf.shiva.oms.ht.dto;

public class HttpInvokerDto {
    private String requestHeader;
    private Boolean requestType;
    private String requestUrl;
    private String request;
    public String getRequestHeader() {
        return requestHeader;
    }
    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }
    
    public Boolean getRequestType() {
        return requestType;
    }
    public void setRequestType(Boolean requestType) {
        this.requestType = requestType;
    }
    public String getRequestUrl() {
        return requestUrl;
    }
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
    public String getRequest() {
        return request;
    }
    public void setRequest(String request) {
        this.request = request;
    }
    
}
