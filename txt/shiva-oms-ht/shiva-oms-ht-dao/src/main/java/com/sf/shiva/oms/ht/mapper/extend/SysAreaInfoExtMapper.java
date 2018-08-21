package com.sf.shiva.oms.ht.mapper.extend;

import java.util.List;

import com.sf.shiva.oms.ht.domain.SysAreaInfo;
/**
 * 
 * @author 01139932
 */
public interface SysAreaInfoExtMapper {
    
    int updateToDeleteStatus(Long id);
    
    int updateByPrimaryKeySelective(SysAreaInfo recore);
    
    SysAreaInfo selectByPrimaryKey(Long id);
    
    public List<SysAreaInfo> getAreaList();
}