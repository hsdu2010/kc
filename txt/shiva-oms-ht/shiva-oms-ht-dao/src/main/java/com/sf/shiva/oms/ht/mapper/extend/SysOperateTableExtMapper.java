package com.sf.shiva.oms.ht.mapper.extend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sf.shiva.oms.ht.domain.SysOperateTable;
import com.sf.shiva.oms.ht.domain.SysRoleTableInfo;

public interface SysOperateTableExtMapper {
	 
    int updateToDeleteStatus(Long id);
    
    int updateByPrimaryKeySelective(SysOperateTable recore);
    
    SysOperateTable selectByPrimaryKey(Long id);

	public List<SysRoleTableInfo> getRoleTableList(String roleId);

	int bindRoleTable(@Param("createtime") String createtime, @Param("updatetime") String updatetime,
			@Param("tableId") String tableId, @Param("roleId") String roleId);

	int unBindRoleTable(@Param("tableId") String tableId, @Param("roleId") String roleId);
}
