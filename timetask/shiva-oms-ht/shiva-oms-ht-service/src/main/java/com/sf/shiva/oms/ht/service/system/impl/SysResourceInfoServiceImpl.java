package com.sf.shiva.oms.ht.service.system.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sf.shiva.oms.ht.domain.ResourceTreeNode;
import com.sf.shiva.oms.ht.domain.SysResourceInfo;
import com.sf.shiva.oms.ht.domain.SysResourceInfoExample;
import com.sf.shiva.oms.ht.domain.SysRoleResourceInfo;
import com.sf.shiva.oms.ht.domain.vo.SideMenu;
import com.sf.shiva.oms.ht.manager.SysResourceInfoManager;
import com.sf.shiva.oms.ht.service.system.SysResourceInfoService;

/**
 * ResourceService实现类
 *
 * @author 01139932
 */
@Service
public class SysResourceInfoServiceImpl implements SysResourceInfoService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	SysResourceInfoManager resourceManager;

	/**
	 * 按条件计数
	 * 
	 * @param example
	 *            条件
	 * 
	 * @return Integer 记录总数
	 *
	 */
	public Integer countByExample(SysResourceInfoExample example) {
		int count = 0;
		try {
			count = resourceManager.countByExample(example);
		} catch (Exception e) {
			logger.error("countByExample error", e);
		}
		return count;
	}

	/**
	 * 按主键删除
	 * 
	 * @param id
	 *            主键，自增
	 * 
	 * @return Integer 成功条数
	 *
	 */
	public Integer deleteByPrimaryKey(Long id) {
		if (id == null) {
			throw new IllegalStateException("参数id不能为空");
		}
		int count = 0;
		try {
			count = resourceManager.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.error("deleteByPrimaryKey error", e);
		}
		return count;
	}

	/**
	 * 新增
	 * 
	 * @param record
	 *            记录
	 * 
	 * @return Integer 成功条数
	 *
	 */
	public Integer insert(SysResourceInfo record) {
		if (record == null) {
			throw new IllegalStateException("参数record不能为空");
		}
		int count = 0;
		try {
			count = resourceManager.insert(record);
		} catch (Exception e) {
			logger.error("insert error", e);
		}
		return count;
	}

	/**
	 * 按条件查询
	 * 
	 * @param example
	 *            条件
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            每页数量
	 * 
	 * @return Page
	 *
	 */
	public Page<SysResourceInfo> selectByExample(SysResourceInfoExample example, Integer pageNum, Integer pageSize) {
		if (pageNum != null && pageNum < 1) {
			throw new IllegalStateException("参数pageNum不能小于1");
		}
		if (pageSize != null && pageSize < 1) {
			throw new IllegalStateException("参数pageSize不能小于1");
		}
		if ((pageNum == null && pageSize != null) || (pageNum != null && pageSize == null)) {
			throw new IllegalStateException("pageNum、pageSize必须同时为null或不为null");
		}
		if (pageNum == null && pageSize == null) { // 一次查所有数据
			pageNum = 1;
			pageSize = 0;
		}
		Page<SysResourceInfo> records = new Page<SysResourceInfo>();
		PageHelper.startPage(pageNum, pageSize);
		try {
			records = (Page<SysResourceInfo>) resourceManager.selectByExample(example);
		} catch (Exception e) {
			logger.error("selectByExample error", e);
		}
		return records;
	}

	/**
	 * 按主键查询
	 * 
	 * @param id
	 *            主键，自增
	 * 
	 * @return List
	 *
	 */
	public SysResourceInfo selectByPrimaryKey(Long id) {
		if (id == null) {
			throw new IllegalStateException("参数id不能为空");
		}
		SysResourceInfo record = null;
		try {
			record = resourceManager.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.error("selectByPrimaryKey error", e);
		}
		return record;
	}

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record
	 *            记录
	 * @return List
	 *
	 */
	public Integer updateByPrimaryKeySelective(SysResourceInfo record) {
		if (record == null) {
			throw new IllegalStateException("参数record不能为空");
		}
		if (record.getId() == null) {
			throw new IllegalStateException("主键id属性不能为空");
		}
		int count = 0;
		try {
			count = resourceManager.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			logger.error("updateByPrimaryKeySelective error", e);
		}
		return count;
	}

	/**
	 * 按主键更新
	 * 
	 * @param record
	 *            记录
	 * @return Integer 成功条数
	 *
	 */
	public Integer updateByPrimaryKey(SysResourceInfo record) {
		if (record == null) {
			throw new IllegalStateException("参数record不能为空");
		}
		if (record.getId() == null) {
			throw new IllegalStateException("主键id属性不能为空");
		}
		int count = 0;
		try {
			count = resourceManager.updateByPrimaryKey(record);
		} catch (Exception e) {
			logger.error("updateByPrimaryKey error", e);
		}
		return count;
	}

	@Override
	public List<ResourceTreeNode> getResourceList() {
		List<ResourceTreeNode> result = new ArrayList<ResourceTreeNode>();
		List<SysResourceInfo> list = new ArrayList<SysResourceInfo>();
		
		try {
			list = resourceManager.getResourceList();
		} catch (Exception e) {
			logger.error("getResourceList error", e);
		}
		
		for (SysResourceInfo resource : list) {
			Integer resourceLevel = resource.getSysResourceLevel();
			if (1 == resourceLevel) {// 一级
				ResourceTreeNode rtn = new ResourceTreeNode();
				String uid = resource.getSysResourceUid();
				String name = resource.getSysResourceName();
				rtn.setId(resource.getId());
				rtn.setUid(resource.getSysResourceUid());
				rtn.setName(name);
				rtn.setOpen(true);
				rtn.setIsParent(true);
				rtn.setResourceUrl(resource.getSysResourcePath());
				rtn.setResourceLevel(resourceLevel);
				List<ResourceTreeNode> children = new ArrayList<ResourceTreeNode>(10);
				for (SysResourceInfo res : list) {
					if (uid.equals(res.getSysParentUid())) {
						children.add(getChildren(res));
					}
				}
				rtn.setChildren(children);
				result.add(rtn);
			}

		}
		return result;
	}

	private ResourceTreeNode getChildren(SysResourceInfo resource) {

		ResourceTreeNode rtn = new ResourceTreeNode();
		Integer resourceLevel = resource.getSysResourceLevel();
		String name = resource.getSysResourceName();

		rtn.setId(resource.getId());
		rtn.setUid(resource.getSysResourceUid());
		rtn.setName(name);
		rtn.setOpen(true);
		rtn.setResourceUrl(resource.getSysResourcePath());
		rtn.setResourceLevel(resourceLevel);
		return rtn;
	}

	@Override
	public List<ResourceTreeNode> getResourceByRoleId(String roleId) {
		List<ResourceTreeNode> result = new ArrayList<ResourceTreeNode>();
		
		List<SysRoleResourceInfo> list = new ArrayList<SysRoleResourceInfo>();
		try {
			list = resourceManager.getResourceByRoleId(roleId);
		} catch (Exception e) {
			logger.error("getResourceByRoleId error", e);
		}
		
		for (SysRoleResourceInfo resource : list) {
			ResourceTreeNode rtn = new ResourceTreeNode();
			Integer resourceLevel = resource.getSysResourceLevel();

			if (1 == resourceLevel) {
				rtn.setId(resource.getId());
				rtn.setUid(resource.getSysResourceUid());
				rtn.setName(resource.getSysResourceName());
				rtn.setOpen(true);
				rtn.setIsParent(true);
				rtn.setResourceUrl(resource.getSysResourcePath());
				rtn.setResourceLevel(resourceLevel);
				rtn.setChecked(resource.getBindState());
				List<ResourceTreeNode> children = new ArrayList<ResourceTreeNode>(10);
				for (SysRoleResourceInfo res : list) {
					if (resource.getSysResourceUid().equals(res.getSysParentUid())) {
						ResourceTreeNode rtnCh = new ResourceTreeNode();

						rtnCh.setId(res.getId());
						rtnCh.setUid(res.getSysResourceUid());
						rtnCh.setName(res.getSysResourceName());
						rtnCh.setOpen(true);
						rtnCh.setResourceUrl(res.getSysResourcePath());
						rtnCh.setResourceLevel(res.getSysResourceLevel());
						rtnCh.setChecked(res.getBindState());

						children.add(rtnCh);
					}
				}
				rtn.setChildren(children);
				result.add(rtn);
			}
		}
		return result;
	}

	@Override
	public int updateRoleResource(String roleId, List<String> resourceIdList) {
		int count = 0 ;
		try {
			count = resourceManager.updateRoleResource(roleId, resourceIdList);
		} catch (Exception e) {
			logger.error("updateRoleResource error", e);
		}
		return count;
	}
	
	@Override
	public Integer updateToDeleteStatus(Long id) {
		return resourceManager.updateToDeleteStatus(id);
	}

	@Override
	public List<SideMenu> getSubResources(Long id) {
		List<SideMenu> subMenuList = new ArrayList<SideMenu>();
		
		List<SysResourceInfo> sideMenu = new ArrayList<SysResourceInfo>();
		List<SysResourceInfo> childMenu = new ArrayList<SysResourceInfo>();
		sideMenu = resourceManager.getSideMenu(id);
		childMenu = resourceManager.getChildMenu(id);
		
		for(SysResourceInfo res : sideMenu) {
			SideMenu temp = new SideMenu();
			String  parent = res.getSysResourceUid();
			temp.setResourceName(res.getSysResourceName());
			temp.setResourceUrl(res.getSysResourcePath());
			LinkedHashMap<String, String> cm = new LinkedHashMap<String, String>();
			for(SysResourceInfo child : childMenu) {
				if(parent.equals(child.getSysParentUid())) {
					cm.put(child.getSysResourceName(), child.getSysResourcePath());
				}
			}
			temp.setChildMenu(cm);
			subMenuList.add(temp);
		}
		return subMenuList;
	}

	/*@Override
	public Integer deleteByUid(String sysResourceUid) {
		if (sysResourceUid == null || sysResourceUid.length() <= 0) {
			throw new IllegalStateException("参数sysResourceUid不能为空");
		}
		int count = 0;
		try {
			count = resourceManager.deleteByUid(sysResourceUid);
		} catch (Exception e) {
			logger.error("deleteByUid error", e);
		}
		return count;
	}*/

}