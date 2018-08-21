package com.sf.shiva.oms.ht.service.apimanager.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sf.shiva.oms.ht.common.utils.UUID22;
import com.sf.shiva.oms.ht.domain.RpcManager;
import com.sf.shiva.oms.ht.domain.RpcManagerExample;
import com.sf.shiva.oms.ht.dto.ClassInfoDto;
import com.sf.shiva.oms.ht.exception.BusinessException;
import com.sf.shiva.oms.ht.manager.RpcManagerManager;
import com.sf.shiva.oms.ht.manager.extend.RcpManagerExtendManager;
import com.sf.shiva.oms.ht.manager.extend.RpcMgrExtendManger;
import com.sf.shiva.oms.ht.service.apimanager.RpcManagerService;
import com.sf.shiva.oms.ht.service.contants.Constants;
import com.sf.shiva.oms.ht.service.util.BeanUtil;
import com.sf.shiva.oms.ht.util.UserManager;
/**
 * RpcManagerService实现类
 *
 * @author 01369626
*/
@Service
public class RpcManagerServiceImpl implements RpcManagerService {
    
	private Logger logger = LoggerFactory.getLogger(getClass());
			
	@Autowired
	RpcManagerManager rpcManager;
    @Autowired
    private RcpManagerExtendManager userRpcInfoExtendManager;
    @Autowired
    private RpcMgrExtendManger rpcMgrExtendManager;
    
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return Integer 记录总数
	 *
    */
	public Integer countByExample(RpcManagerExample example) {
		int count = 0;
		try{
			count = rpcManager.countByExample(example);
		}
		catch(Exception e){
			logger.error("countByExample error", e);
		}
		return count;
	}

	/**
	 * 按主键删除
	 * 
	 * @param id 
	 * 
	 * @return Integer 成功条数  
	 *
    */
    public Integer deleteByPrimaryKey(String id){
    	if(id == null || "".equals(id.trim())){
    		throw new IllegalStateException("参数id不能为空");
    	}
		int count = 0;
		try{
		   count = rpcManager.deleteByPrimaryKey(id);
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
    public Integer insert(RpcManager record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
		int count = 0;
		try{
            record.setId(UUID22.getUUID22());
		    Date now = new Date();
            record.setCreatetime(now);
            record.setUpdatetime(now);
            record.setCreateEmp(UserManager.getCurrentUserNo());
			count = rpcManager.insert(record);
		}
		catch(Exception e){
			logger.error("insert error", e);
			throw e;
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
    public Page<RpcManager> selectByExample(RpcManagerExample example, Integer pageNum, Integer pageSize){
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
		Page<RpcManager> records = new Page<RpcManager>();
		PageHelper.startPage(pageNum, pageSize);
		try{
			records = (Page<RpcManager>)rpcManager.selectByExample(example);
		}
		catch(Exception e){
			logger.error("selectByExample error", e);
		}
		return records;
	}

	/**
	 * 按主键查询
	 * 
	 * @param id 
	 * 
	 * @return  List 
	 *
    */
	public RpcManager selectByPrimaryKey(String id){
    	if(id == null || "".equals(id.trim())){
    		throw new IllegalStateException("参数id不能为空");
    	}
		RpcManager record = null;
		try{
			record = rpcManager.selectByPrimaryKey(id);
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
	public Integer updateByPrimaryKeySelective(RpcManager record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	if(record.getId() == null || "".equals(record.getId().trim())){
    		throw new IllegalStateException("主键id属性不能为空");
    	}
		int count = 0;
		try{
		    record.setUpdatetime(new Date());
		    record.setModifyEmp(UserManager.getCurrentUserNo());
			count = rpcManager.updateByPrimaryKeySelective(record);
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
    public Integer updateByPrimaryKey(RpcManager record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	if(record.getId() == null || "".equals(record.getId().trim())){
    		throw new IllegalStateException("主键id属性不能为空");
    	}
		int count = 0;
		try{
		    record.setUpdatetime(new Date());
			count = rpcManager.updateByPrimaryKey(record);
		}
		catch(Exception e){
			logger.error("updateByPrimaryKey error", e);
		}
		return count;
	}

    @Override
    public void insert(RpcManager record, InputStream src)
            throws BusinessException {
        try {
            //查询数据库， 是否有jar存在
            List<RpcManager> datas = userRpcInfoExtendManager.selectByJarName(record.getJarName());
            if(CollectionUtils.isNotEmpty(datas)){
                throw new BusinessException("文件已存在");
            }
            File dest = new File(Constants.JAR_HOME + File.separator + record.getJarName());
            FileUtils.copyInputStreamToFile(src, dest);
            insert(record);
        } catch (IOException e) {
            logger.error("rpc upload jarFile fail", e);
            throw new BusinessException("文件上传失败");
        }
    }

    @Override
    public List<ClassInfoDto> queryJarInfo(String jarName)
            throws ClassNotFoundException, IOException {
        return BeanUtil.getInterfaceInfoList(jarName);
    }

	@Override
	public Page<RpcManager> searchByAppName(String appName, Integer pageNum, Integer pageSize) {
		Page<RpcManager> records = new Page<RpcManager>();
		PageHelper.startPage(pageNum, pageSize);
		try {
			records = (Page<RpcManager>)rpcMgrExtendManager.searchByAppName(appName);
		}catch (Exception e) {
			logger.error("RpcManagerServiceImpl searchByAppName error.",e);
		}
		return records;
	}
	
}