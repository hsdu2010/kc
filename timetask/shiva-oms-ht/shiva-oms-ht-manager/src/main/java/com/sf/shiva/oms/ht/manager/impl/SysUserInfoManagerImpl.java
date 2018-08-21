package com.sf.shiva.oms.ht.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.ColumnUtils;
import com.sf.shiva.oms.ht.domain.SysUserInfo;
import com.sf.shiva.oms.ht.domain.SysUserInfoExample;
import com.sf.shiva.oms.ht.manager.SysUserInfoManager;
import com.sf.shiva.oms.ht.mapper.SysUserInfoMapper;
import com.sf.shiva.oms.ht.mapper.extend.SysUserInfoExtMapper;
import com.sf.shiva.oms.ht.util.UserManager;

/**
 * UserManager实现类
 *
 * @author 01139932
*/
@Component
public class SysUserInfoManagerImpl implements SysUserInfoManager {

	@Autowired
	private SysUserInfoMapper userMapper;
	
	@Autowired
	private SysUserInfoExtMapper userExtMapper;
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
	@Override
    public int countByExample(SysUserInfoExample example){
		return userMapper.countByExample(example);
	}

	/**
	 * 按条件删除
	 * 
	 * @param example 条件
	 * 
	 * @return int 成功条数  
	 *
    */
	@Override
    public int deleteByExample(SysUserInfoExample example){
		return userMapper.deleteByExample(example);
	}
	
	/**
	 * 按主键删除
	 * 
	 * @param id 主键，自增
	 * 
	 * @return int 成功条数  
	 *
    */
	@Override
    public int deleteByPrimaryKey(Long id){
		return userMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
	@Override
    public int insert(SysUserInfo record){
		//格式转换
		record.setSysUserUid(ColumnUtils.generatorFK());
    	String dateStr = ColumnUtils.nowDateString();
    	record.setCreatetime(dateStr);
    	record.setUpdatetime(dateStr);
    	record.setUpdateuid(UserManager.getCurrentUserNo());
    	record.setDelStatus(false);
	    return userMapper.insert(record);
	}
	
	/**
	 * 新增,null字段不插入
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
	@Override
    public int insertSelective(SysUserInfo record){
		return userMapper.insertSelective(record);
	}
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<User> 记录列表
	 *
    */
	@Override
    public List<SysUserInfo> selectByExample(SysUserInfoExample example){
		return userMapper.selectByExample(example);
	}

	/**
	 * 按主键查询
	 * 
	 * @param id 主键，自增
	 * 
	 * @return User 记录 
	 *
    */
	@Override
    public SysUserInfo selectByPrimaryKey(Long id){
		return userExtMapper.selectByPrimaryKey(id);
	}

	/**
	 * 按条件更新,null字段不更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
	@Override
    public int updateByExampleSelective(SysUserInfo record, SysUserInfoExample example){
		return userMapper.updateByExampleSelective(record, example);
	}

	
	/**
	 * 按条件更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
	@Override
    public int updateByExample(SysUserInfo record,  SysUserInfoExample example){
		return userMapper.updateByExample(record, example);
	}

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKeySelective(SysUserInfo record){
    	record.setUpdatetime(ColumnUtils.nowDateString());
    	record.setUpdateuid(UserManager.getCurrentUserNo());
		return userExtMapper.updateByPrimaryKeySelective(record);
	}


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKey(SysUserInfo record){
		return userMapper.updateByPrimaryKey(record);
	}
	
	
	@Override
	public int updateToDeleteStatus(Long id) {
		return userExtMapper.updateToDeleteStatus(id);
	}

	@Override
	public SysUserInfo selectUserByUserName(String userName) {
		return userExtMapper.selectByUserName(userName);
	}
	
}