package com.sf.shiva.oms.ht.service.system;

import java.util.List;

import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.ResourceTreeNode;
import com.sf.shiva.oms.ht.domain.SysResourceInfo;
import com.sf.shiva.oms.ht.domain.SysResourceInfoExample;
import com.sf.shiva.oms.ht.domain.vo.SideMenu;

/**
 * ResourceService类
 *
 * @author 01139932
*/
public interface SysResourceInfoService {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return Integer 记录总数
	 *
    */
	Integer countByExample(SysResourceInfoExample example);

	/**
	 * 按主键删除
	 * 
	 * @param id 主键，自增
	 * 
	 * @return Integer 成功条数  
	 *
    */
    Integer deleteByPrimaryKey(Long id);
    
  /*  Integer deleteByUid(String sysResourceUid);*/

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return Integer 成功条数  
	 *
    */
    Integer insert(SysResourceInfo record);
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * @param pageNum 页码
	 * @param pageSize 每页数量
	 * 
	 * @return  Page<Resource> 记录列表 
	 *
    */
    Page<SysResourceInfo> selectByExample(SysResourceInfoExample example, Integer pageNum, Integer pageSize);

	/**
	 * 按主键查询
	 * 
	 * @param id 主键，自增
	 * 
	 * @return Resource 记录 
	 *
    */
	SysResourceInfo selectByPrimaryKey(Long id);
	
	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
	Integer updateByPrimaryKeySelective(SysResourceInfo record);

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
    Integer updateByPrimaryKey(SysResourceInfo record);
    
    int updateRoleResource(String roleId, List<String> resourceIdList);
    
   /* Boolean existSubResource(String parentResUrl);
    
    List<Resource> getSubResource(String parentResUrl);  

    List<Resource> getMainMenuByUserNo(String userNo);

    int deleteResource(String id);

    int updateResource(Resource resource);

    int addResource(Resource resource);
    
    List<Resource> getSubResByUserNoAndParent(String userNo, String parentResUrl);

    String getCurrentUserNo();
    
    public List<SideMenu> getSubResources(String userNo, String parentResUrl);
    
    int getParentById(Integer id);*/
    
    List<ResourceTreeNode> getResourceList();
    
    List<ResourceTreeNode> getResourceByRoleId(String roleId);
    
    Integer updateToDeleteStatus(Long id);
    
    public List<SideMenu> getSubResources(Long id);
	
}