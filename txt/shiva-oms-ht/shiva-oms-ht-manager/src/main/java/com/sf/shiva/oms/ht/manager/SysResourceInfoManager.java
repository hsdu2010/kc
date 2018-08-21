package com.sf.shiva.oms.ht.manager;

import java.util.List;

import com.sf.shiva.oms.ht.domain.SysResourceInfo;
import com.sf.shiva.oms.ht.domain.SysResourceInfoExample;
import com.sf.shiva.oms.ht.domain.SysRoleResourceInfo;

/**
 * ResourceManager类
 *
 * @author 01139932
*/
public interface SysResourceInfoManager {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
    int countByExample(SysResourceInfoExample example);

	/**
	 * 按条件删除
	 * 
	 * @param example 条件
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByExample(SysResourceInfoExample example);
	
	/**
	 * 按主键删除
	 * 
	 * @param id 主键，自增
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByPrimaryKey(Long id);

    
   /* int deleteByUid(String sysResourceUid);*/
	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insert(SysResourceInfo record);
	
	/**
	 * 新增,null字段不插入
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insertSelective(SysResourceInfo record);
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<Resource> 记录列表
	 *
    */
    List<SysResourceInfo> selectByExample(SysResourceInfoExample example);

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
	 * 按条件更新,null字段不更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExampleSelective(SysResourceInfo record, SysResourceInfoExample example);

	
	/**
	 * 按条件更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExample(SysResourceInfo record,  SysResourceInfoExample example);

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKeySelective(SysResourceInfo record);


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
   int updateByPrimaryKey(SysResourceInfo record);
   /* 
    Boolean existSubResource(String parentResUrl);

    List<Resource> getSubResource(String parentResUrl);

    List<Resource> getResourceByUserNo(String userNo);

    List<Resource> getResourceList();*/
   
    int updateToDeleteStatus(Long id);

    List<SysRoleResourceInfo> getResourceByRoleId(String roleId);

	List<SysResourceInfo> getResourceList();

    int updateRoleResource(String roleId, List<String> resourceIdList);
    
    List<SysResourceInfo> getSideMenu(Long id );
    
    List<SysResourceInfo> getChildMenu(Long id );

    /* int deleteResource(String id);

    int updateResource(Resource resource);

    int addResource(Resource resource);
    
    List<Resource> getSideMenu(String userNo, String parentResUrl);
    
    List<Resource> getChildMenu(String userNo, String parentResUrl);
    
    int getParentById(Integer id);*/
	
}