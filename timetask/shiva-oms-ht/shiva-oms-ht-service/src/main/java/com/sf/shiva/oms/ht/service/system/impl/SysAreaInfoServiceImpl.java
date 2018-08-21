package com.sf.shiva.oms.ht.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sf.shiva.oms.ht.domain.ResourceTreeNode;
import com.sf.shiva.oms.ht.domain.SysAreaInfo;
import com.sf.shiva.oms.ht.domain.SysAreaInfoExample;
import com.sf.shiva.oms.ht.manager.SysAreaInfoManager;
import com.sf.shiva.oms.ht.service.system.SysAreaInfoService;
/**
 * AreaService实现类
 *
 * @author 01139932
*/
@Service
public class SysAreaInfoServiceImpl implements SysAreaInfoService {

	private Logger logger = LoggerFactory.getLogger(getClass());
			
	@Autowired
	SysAreaInfoManager areaManager;
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return Integer 记录总数
	 *
    */
	public Integer countByExample(SysAreaInfoExample example) {
		int count = 0;
		try{
			count = areaManager.countByExample(example);
		}
		catch(Exception e){
			logger.error("countByExample error", e);
		}
		return count;
	}

	/**
	 * 按主键删除
	 * 
	 * @param id 主键，自增
	 * 
	 * @return Integer 成功条数  
	 *
    */
    public Integer deleteByPrimaryKey(Long id){
    	if(id == null){
    		throw new IllegalStateException("参数id不能为空");
    	}
		int count = 0;
		try{
			count = areaManager.deleteByPrimaryKey(id);
		}
		catch(Exception e){
			logger.error("deleteByPrimaryKey error", e);
		}
		return count;
	}

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return Integer 成功条数  
	 *
    */
    public Integer insert(SysAreaInfo record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
		int count = 0;
		try{
			count = areaManager.insert(record);
		}
		catch(Exception e){
			logger.error("insert error", e);
		}
		return count;
	}
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * @param pageNum 页码
	 * @param pageSize 每页数量
	 * 
	 * @return  Page 
	 *
    */
    public Page<SysAreaInfo> selectByExample(SysAreaInfoExample example, Integer pageNum, Integer pageSize){
		if(pageNum != null && pageNum < 1){
			throw new IllegalStateException("参数pageNum不能小于1");
		}
		if(pageSize != null && pageSize < 1){
			throw new IllegalStateException("参数pageSize不能小于1");
		}
		if((pageNum == null && pageSize != null)
			||(pageNum != null && pageSize == null)){
			throw new IllegalStateException("pageNum、pageSize必须同时为null或不为null");
		}
		if(pageNum == null && pageSize == null){ //一次查所有数据
			pageNum = 1;
			pageSize = 0;
		}
		Page<SysAreaInfo> records = new Page<SysAreaInfo>();
		PageHelper.startPage(pageNum, pageSize);
		try{
			records = (Page<SysAreaInfo>)areaManager.selectByExample(example);
		}
		catch(Exception e){
			logger.error("selectByExample error", e);
		}
		return records;
	}

	/**
	 * 按主键查询
	 * 
	 * @param id 主键，自增
	 * 
	 * @return  List 
	 *
    */
	public SysAreaInfo selectByPrimaryKey(Long id){
    	if(id == null){
    		throw new IllegalStateException("参数id不能为空");
    	}
		SysAreaInfo record = null;
		try{
			record = areaManager.selectByPrimaryKey(id);
		}
		catch(Exception e){
			logger.error("selectByPrimaryKey error", e);
		}
		return record;
	}

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return  List 
	 *
    */
	public Integer updateByPrimaryKeySelective(SysAreaInfo record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	if(record.getId() == null){
    		throw new IllegalStateException("主键id属性不能为空");
    	}
		int count = 0;
		try{
			count = areaManager.updateByPrimaryKeySelective(record);
		}
		catch(Exception e){
			logger.error("updateByPrimaryKeySelective error", e);
		}
		return count;
	}

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
    public Integer updateByPrimaryKey(SysAreaInfo record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	if(record.getId() == null){
    		throw new IllegalStateException("主键id属性不能为空");
    	}
		int count = 0;
		try{
			count = areaManager.updateByPrimaryKey(record);
		}
		catch(Exception e){
			logger.error("updateByPrimaryKey error", e);
		}
		return count;
	}

	@Override
	public Integer updateToDeleteStatus(Long id) {
		return areaManager.updateToDeleteStatus(id);
	}

	@Override
	public List<ResourceTreeNode> getAreaList() {
		List<SysAreaInfo> list = areaManager.getAreaList();
		return getChildren("",list);
	}
	
	List<ResourceTreeNode> getChildren(String pId,List<SysAreaInfo> list){
		List<ResourceTreeNode> result = new ArrayList<ResourceTreeNode>();
		for (SysAreaInfo sysAreaInfo : list) {
			if (pId.equals(checkRoot(sysAreaInfo.getSysParentUid()))){
				ResourceTreeNode temp = new ResourceTreeNode();
				temp.setId(sysAreaInfo.getId());
				temp.setIsParent(checkParent(sysAreaInfo,list));
				temp.setName(sysAreaInfo.getSysAreaName());
				temp.setResourceLevel(sysAreaInfo.getSysAreaLevel());
				temp.setUid(sysAreaInfo.getSysAreaUid());
				temp.setChildren(getChildren(sysAreaInfo.getSysAreaUid(), list));
				result.add(temp);
			}
		}
		return result;
	}
	
	String checkRoot(String pid){
		if (pid == null|| pid.trim().length()<=0) {
			return "";
		}
		else return pid;
	}
	
	Boolean checkParent(SysAreaInfo sysAreaInfo ,List<SysAreaInfo> list){
		Boolean result = false;
		for (SysAreaInfo sysAreaInfo2 : list) {
			if (sysAreaInfo2.getSysParentUid().equals(sysAreaInfo.getSysAreaUid())) {
				result = true ;
				break;
			}
		}
		return result;
	}
	
	
}