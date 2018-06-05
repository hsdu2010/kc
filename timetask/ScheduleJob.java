package com.sf.shiva.oms.pcm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 该类与数据库中的表tm_schedule_job对应 
 * 
 * @author 01369521
 */
public class ScheduleJob implements Serializable {
    private String id;

    private String taskName;

    private String taskGroup;

    private String taskStatus;

    private String cronExpression;

    private String concurrent;

    private String remark;

    private String targetObject;

    private String targetMethod;

    private String springBeanFlag;

    private String targetClazz;

    private Date createTm;

    private Date modifyTm;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(String taskGroup) {
        this.taskGroup = taskGroup == null ? null : taskGroup.trim();
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus == null ? null : taskStatus.trim();
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression == null ? null : cronExpression.trim();
    }

    public String getConcurrent() {
        return concurrent;
    }

    public void setConcurrent(String concurrent) {
        this.concurrent = concurrent == null ? null : concurrent.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(String targetObject) {
        this.targetObject = targetObject == null ? null : targetObject.trim();
    }

    public String getTargetMethod() {
        return targetMethod;
    }

    public void setTargetMethod(String targetMethod) {
        this.targetMethod = targetMethod == null ? null : targetMethod.trim();
    }

    public String getSpringBeanFlag() {
        return springBeanFlag;
    }

    public void setSpringBeanFlag(String springBeanFlag) {
        this.springBeanFlag = springBeanFlag == null ? null : springBeanFlag.trim();
    }

    public String getTargetClazz() {
        return targetClazz;
    }

    public void setTargetClazz(String targetClazz) {
        this.targetClazz = targetClazz == null ? null : targetClazz.trim();
    }

    public Date getCreateTm() {
        return createTm;
    }

    public void setCreateTm(Date createTm) {
        this.createTm = createTm;
    }

    public Date getModifyTm() {
        return modifyTm;
    }

    public void setModifyTm(Date modifyTm) {
        this.modifyTm = modifyTm;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", taskName=").append(taskName);
        sb.append(", taskGroup=").append(taskGroup);
        sb.append(", taskStatus=").append(taskStatus);
        sb.append(", cronExpression=").append(cronExpression);
        sb.append(", concurrent=").append(concurrent);
        sb.append(", remark=").append(remark);
        sb.append(", targetObject=").append(targetObject);
        sb.append(", targetMethod=").append(targetMethod);
        sb.append(", springBeanFlag=").append(springBeanFlag);
        sb.append(", targetClazz=").append(targetClazz);
        sb.append(", createTm=").append(createTm);
        sb.append(", modifyTm=").append(modifyTm);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}