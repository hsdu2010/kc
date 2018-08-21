package com.sf.shiva.oms.ht.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.ColumnUtils;
import com.sf.shiva.oms.ht.domain.SysAreaInfo;
import com.sf.shiva.oms.ht.domain.SysAreaInfoExample;
import com.sf.shiva.oms.ht.manager.SysAreaInfoManager;
import com.sf.shiva.oms.ht.mapper.SysAreaInfoMapper;
import com.sf.shiva.oms.ht.mapper.extend.SysAreaInfoExtMapper;
import com.sf.shiva.oms.ht.util.UserManager;


/**
 * AreaManager实现类
 *
 * @author 01139932
 */
@Component
public class SysAreaInfoManagerImpl implements SysAreaInfoManager {

	@Autowired
	private SysAreaInfoMapper areaMapper;

	@Autowired
	private SysAreaInfoExtMapper areaExtMapper;

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
	public int countByExample(SysAreaInfoExample example) {
		return areaMapper.countByExample(example);
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
	public int deleteByExample(SysAreaInfoExample example) {
		return areaMapper.deleteByExample(example);
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
		return areaMapper.deleteByPrimaryKey(id);
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
	public int insert(SysAreaInfo record) {
		record.setSysAreaUid(ColumnUtils.generatorFK());
		String dateStr = ColumnUtils.nowDateString();
		record.setCreatetime(dateStr);
		record.setUpdatetime(dateStr);
		record.setUpdateuid(UserManager.getCurrentUserNo());
		record.setDelStatus(false);
		return areaMapper.insert(record);
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
	public int insertSelective(SysAreaInfo record) {
		return areaMapper.insertSelective(record);
	}

	/**
	 * 按条件查询
	 * 
	 * @param example
	 *            条件
	 * 
	 * @return List<Area> 记录列表
	 *
	 */
	@Override
	public List<SysAreaInfo> selectByExample(SysAreaInfoExample example) {
		return areaMapper.selectByExample(example);
	}

	/**
	 * 按主键查询
	 * 
	 * @param id
	 *            主键，自增
	 * 
	 * @return Area 记录
	 *
	 */
	@Override
	public SysAreaInfo selectByPrimaryKey(Long id) {
		return areaExtMapper.selectByPrimaryKey(id);
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
	public int updateByExampleSelective(SysAreaInfo record, SysAreaInfoExample example) {
		return areaMapper.updateByExampleSelective(record, example);
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
	public int updateByExample(SysAreaInfo record, SysAreaInfoExample example) {
		return areaMapper.updateByExample(record, example);
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
	public int updateByPrimaryKeySelective(SysAreaInfo record) {
		record.setUpdatetime(ColumnUtils.nowDateString());
		record.setUpdateuid(UserManager.getCurrentUserNo());
		return areaExtMapper.updateByPrimaryKeySelective(record);
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
	public int updateByPrimaryKey(SysAreaInfo record) {
		return areaMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateToDeleteStatus(Long id) {
		return areaExtMapper.updateToDeleteStatus(id);
	}

	@Override
	public List<SysAreaInfo> getAreaList() {
		return areaExtMapper.getAreaList();
	}

}