package com.sf.shiva.oms.ht.manager.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.ColumnUtils;
import com.sf.shiva.oms.ht.domain.SysResourceInfo;
import com.sf.shiva.oms.ht.domain.SysResourceInfoExample;
import com.sf.shiva.oms.ht.domain.SysRoleResourceInfo;
import com.sf.shiva.oms.ht.manager.SysResourceInfoManager;
import com.sf.shiva.oms.ht.mapper.SysResourceInfoMapper;
import com.sf.shiva.oms.ht.mapper.extend.SysResourceInfoExtMapper;
import com.sf.shiva.oms.ht.util.UserManager;

/**
 * ResourceManager实现类
 *
 * @author 01139932
 */
@Component
public class SysResourceInfoManagerImpl implements SysResourceInfoManager {

	@Autowired
	private SysResourceInfoMapper resourceMapper;

	@Autowired
	private SysResourceInfoExtMapper resourceExtMapper;

	/**
	 * 按条件计数
	 * 
	 * @param example
	 *            条件
	 * 
	 * @return int 记录总数
	 *
	 */
	@Override
	public int countByExample(SysResourceInfoExample example) {
		return resourceMapper.countByExample(example);
	}

	/**
	 * 按条件删除
	 * 
	 * @param example
	 *            条件
	 * 
	 * @return int 成功条数
	 *
	 */
	@Override
	public int deleteByExample(SysResourceInfoExample example) {
		return resourceMapper.deleteByExample(example);
	}

	/**
	 * 按主键删除
	 * 
	 * @param id
	 *            主键，自增
	 * 
	 * @return int 成功条数
	 *
	 */
	@Override
	public int deleteByPrimaryKey(Long id) {
		return resourceMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 新增
	 * 
	 * @param record
	 *            记录
	 * 
	 * @return int 成功条数
	 *
	 */
	@Override
	public int insert(SysResourceInfo record) {
		record.setSysResourceUid(ColumnUtils.generatorFK());
		String dateStr = ColumnUtils.nowDateString();
		record.setCreatetime(dateStr);
		record.setUpdatetime(dateStr);
		record.setUpdateuid(UserManager.getCurrentUserNo());
		record.setDelStatus(false);
		return resourceMapper.insert(record);
	}

	/**
	 * 新增,null字段不插入
	 * 
	 * @param record
	 *            记录
	 * 
	 * @return int 成功条数
	 *
	 */
	@Override
	public int insertSelective(SysResourceInfo record) {
		return resourceMapper.insertSelective(record);
	}

	/**
	 * 按条件查询
	 * 
	 * @param example
	 *            条件
	 * 
	 * @return List<Resource> 记录列表
	 *
	 */
	@Override
	public List<SysResourceInfo> selectByExample(SysResourceInfoExample example) {
		return resourceMapper.selectByExample(example);
	}

	/**
	 * 按主键查询
	 * 
	 * @param id
	 *            主键，自增
	 * 
	 * @return Resource 记录
	 *
	 */
	@Override
	public SysResourceInfo selectByPrimaryKey(Long id) {
		return resourceExtMapper.selectByPrimaryKey(id);
	}

	/**
	 * 按条件更新,null字段不更新
	 * 
	 * @param record
	 *            记录
	 * @param example
	 *            条件
	 * @return int 成功条数
	 *
	 */
	@Override
	public int updateByExampleSelective(SysResourceInfo record, SysResourceInfoExample example) {
		return resourceMapper.updateByExampleSelective(record, example);
	}

	/**
	 * 按条件更新
	 * 
	 * @param record
	 *            记录
	 * @param example
	 *            条件
	 * @return int 成功条数
	 *
	 */
	@Override
	public int updateByExample(SysResourceInfo record, SysResourceInfoExample example) {
		return resourceMapper.updateByExample(record, example);
	}

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record
	 *            记录
	 * @return int 成功条数
	 *
	 */
	@Override
	public int updateByPrimaryKeySelective(SysResourceInfo record) {
		record.setUpdatetime(ColumnUtils.nowDateString());
		record.setUpdateuid(UserManager.getCurrentUserNo());
		return resourceExtMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 按主键更新
	 * 
	 * @param record
	 *            记录
	 * @return int 成功条数
	 *
	 */
	@Override
	public int updateByPrimaryKey(SysResourceInfo record) {
		return resourceMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SysResourceInfo> getResourceList() {
		return resourceExtMapper.getResourceList();
	}

	@Override
	public List<SysRoleResourceInfo> getResourceByRoleId(String roleId) {
		List<SysRoleResourceInfo> result = new LinkedList<SysRoleResourceInfo>();
		result = resourceExtMapper.getResourceByRoleId(roleId);
		return result;
	}

	@Override
	public int updateRoleResource(String roleId, List<String> resourceIdList) {
		if (resourceIdList.size() == 0) {
			resourceExtMapper.deleteByRoleId(roleId);
		} else {
			resourceExtMapper.deleteByRoleId(roleId);
			resourceExtMapper.insertRoleResourceList(roleId, resourceIdList);
		}
		return 0;
	}

	@Override
	public int updateToDeleteStatus(Long id) {
		return resourceExtMapper.updateToDeleteStatus(id);
	}

	@Override
	public List<SysResourceInfo> getSideMenu(Long id) {
		return resourceExtMapper.selectSideMenu(id);
	}

	@Override
	public List<SysResourceInfo> getChildMenu(Long id) {
		return  resourceExtMapper.selectChildMenu(id);
	}

}