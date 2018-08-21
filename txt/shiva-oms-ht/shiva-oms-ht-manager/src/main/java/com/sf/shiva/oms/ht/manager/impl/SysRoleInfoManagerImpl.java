package com.sf.shiva.oms.ht.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.ColumnUtils;
import com.sf.shiva.oms.ht.domain.SysRoleInfo;
import com.sf.shiva.oms.ht.domain.SysRoleInfoExample;
import com.sf.shiva.oms.ht.domain.SysUserRoleInfo;
import com.sf.shiva.oms.ht.manager.SysRoleInfoManager;
import com.sf.shiva.oms.ht.mapper.SysRoleInfoMapper;
import com.sf.shiva.oms.ht.mapper.extend.SysRoleInfoExtMapper;
import com.sf.shiva.oms.ht.util.UserManager;

/**
 * RoleManager实现类
 *
 * @author 01139932
 */
@Component
public class SysRoleInfoManagerImpl implements SysRoleInfoManager {

	@Autowired
	private SysRoleInfoMapper roleMapper;

	@Autowired
	private SysRoleInfoExtMapper roleExtMapper;

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
	public int countByExample(SysRoleInfoExample example) {
		return roleMapper.countByExample(example);
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
	public int deleteByExample(SysRoleInfoExample example) {
		return roleMapper.deleteByExample(example);
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
		return roleMapper.deleteByPrimaryKey(id);
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
	public int insert(SysRoleInfo record) {
		String dateStr = ColumnUtils.nowDateString();
		record.setSysRoleUid(ColumnUtils.generatorFK());
		record.setCreatetime(dateStr);
		record.setUpdatetime(dateStr);
		record.setUpdateuid(UserManager.getCurrentUserNo());
		record.setDelStatus(false);
		return roleMapper.insert(record);
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
	public int insertSelective(SysRoleInfo record) {
		return roleMapper.insertSelective(record);
	}

	/**
	 * 按条件查询
	 * 
	 * @param example
	 *            条件
	 * 
	 * @return List<Role> 记录列表
	 *
	 */
	@Override
	public List<SysRoleInfo> selectByExample(SysRoleInfoExample example) {
		return roleMapper.selectByExample(example);
	}

	/**
	 * 按主键查询
	 * 
	 * @param id
	 *            主键，自增
	 * 
	 * @return Role 记录
	 *
	 */
	@Override
	public SysRoleInfo selectByPrimaryKey(Long id) {
		return roleExtMapper.selectByPrimaryKey(id);
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
	public int updateByExampleSelective(SysRoleInfo record, SysRoleInfoExample example) {
		return roleMapper.updateByExampleSelective(record, example);
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
	public int updateByExample(SysRoleInfo record, SysRoleInfoExample example) {
		return roleMapper.updateByExample(record, example);
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
	public int updateByPrimaryKeySelective(SysRoleInfo record) {
		record.setUpdatetime(ColumnUtils.nowDateString());
		record.setUpdateuid(UserManager.getCurrentUserNo());
		return roleExtMapper.updateByPrimaryKeySelective(record);
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
	public int updateByPrimaryKey(SysRoleInfo record) {
		return roleMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SysUserRoleInfo> getUserRoleList(String userNo) {
		return roleExtMapper.getUserRoleByUserNo(userNo);
	}

	@Override
	public int changeUserRole(String roleId, Boolean state, String userNo) {
		int result = 0;
		String dateStr = ColumnUtils.nowDateString();
		if (state) { // 绑定
			roleExtMapper.unBindUserRole(roleId, userNo);
			result = roleExtMapper.bindUserRole(dateStr, dateStr, roleId, userNo);
		} else { // 解除绑定
			result = roleExtMapper.unBindUserRole(roleId, userNo);
		}
		return result;
	}

	@Override
	public int updateToDeleteStatus(Long id) {
		return roleExtMapper.updateToDeleteStatus(id);
	}

}