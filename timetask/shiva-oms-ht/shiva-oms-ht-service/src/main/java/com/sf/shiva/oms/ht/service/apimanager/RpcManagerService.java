package com.sf.shiva.oms.ht.service.apimanager;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.RpcManager;
import com.sf.shiva.oms.ht.domain.RpcManagerExample;
import com.sf.shiva.oms.ht.dto.ClassInfoDto;
import com.sf.shiva.oms.ht.exception.BusinessException;

/**
 * UserRpcInfoService类
 *
 * @author 01369626
*/
public interface RpcManagerService {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return Integer 记录总数
	 *
    */
	Integer countByExample(RpcManagerExample example);

	/**
	 * 按主键删除
	 * 
	 * @param id 
	 * 
	 * @return Integer 成功条数  
	 *
    */
    Integer deleteByPrimaryKey(String id);

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return Integer 成功条数  
	 *
    */
    Integer insert(RpcManager record);
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * @param pageNum 页码
	 * @param pageSize 每页数量
	 * 
	 * @return  Page<UserRpcInfo> 记录列表 
	 *
    */
    Page<RpcManager> selectByExample(RpcManagerExample example, Integer pageNum, Integer pageSize);

	/**
	 * 按主键查询
	 * 
	 * @param id 
	 * 
	 * @return UserRpcInfo 记录 
	 *
    */
    RpcManager selectByPrimaryKey(String id);
	
	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
	Integer updateByPrimaryKeySelective(RpcManager record);

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
    Integer updateByPrimaryKey(RpcManager record);
    
    void insert(RpcManager record, InputStream src) throws BusinessException ;
    
    List<ClassInfoDto> queryJarInfo(String jarName)  throws ClassNotFoundException, IOException ;
    
    public Page<RpcManager> searchByAppName(String appName,Integer pageNum, Integer pageSize);
	
}