package com.sf.shiva.oms.ht.dto;

import java.util.List;

/**
 * 类信息
 * **/
public class ClassInfoDto{
    private String className;
    private List<String> methods;
    
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public List<String> getMethods() {
        return methods;
    }
    public void setMethods(List<String> methods) {
        this.methods = methods;
    }
    @Override
    public String toString() {
        return "ClassInfoDto [className=" + className + ", methods=" + methods
                + "]";
    }
    
}
