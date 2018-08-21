package com.sf.shiva.oms.ht.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sf.shiva.oms.ht.common.utils.UUID22;
import com.sf.shiva.oms.ht.domain.UploadjarManager;
import com.sf.shiva.oms.ht.domain.UploadjarManagerExample;
import com.sf.shiva.oms.ht.exception.BusinessException;
import com.sf.shiva.oms.ht.manager.UploadjarManagerManager;
import com.sf.shiva.oms.ht.service.UploadjarManagerService;
import com.sf.shiva.oms.ht.service.contants.Constants;
import com.sf.shiva.oms.ht.service.util.JarClassLoader;
import com.sf.shiva.oms.ht.util.UserManager;
/**
 * UploadjarManagerService实现类
 *
 * @author 01369626
*/
@Service
public class UploadjarManagerServiceImpl implements UploadjarManagerService {

	private Logger logger = LoggerFactory.getLogger(getClass());
			
	@Autowired
	UploadjarManagerManager uploadjarManagerManager;
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return Integer 记录总数
	 *
    */
	public Integer countByExample(UploadjarManagerExample example) {
		int count = 0;
		try{
			count = uploadjarManagerManager.countByExample(example);
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
			count = uploadjarManagerManager.deleteByPrimaryKey(id);
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
    public Integer insert(UploadjarManager record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
		int count = 0;
		try{
		    record.setId(UUID22.getUUID22());
		    record.setCreateEmp(UserManager.getCurrentUserNo());
		    record.setCreateTime(new Date());
			count = uploadjarManagerManager.insert(record);
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
    public Page<UploadjarManager> selectByExample(UploadjarManagerExample example, Integer pageNum, Integer pageSize){
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
		Page<UploadjarManager> records = new Page<UploadjarManager>();
		PageHelper.startPage(pageNum, pageSize);
		try{
			records = (Page<UploadjarManager>)uploadjarManagerManager.selectByExample(example);
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
	public UploadjarManager selectByPrimaryKey(String id){
    	if(id == null || "".equals(id.trim())){
    		throw new IllegalStateException("参数id不能为空");
    	}
		UploadjarManager record = null;
		try{
			record = uploadjarManagerManager.selectByPrimaryKey(id);
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
	public Integer updateByPrimaryKeySelective(UploadjarManager record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	if(record.getId() == null || "".equals(record.getId().trim())){
    		throw new IllegalStateException("主键id属性不能为空");
    	}
		int count = 0;
		try{
			count = uploadjarManagerManager.updateByPrimaryKeySelective(record);
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
    public Integer updateByPrimaryKey(UploadjarManager record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	if(record.getId() == null || "".equals(record.getId().trim())){
    		throw new IllegalStateException("主键id属性不能为空");
    	}
		int count = 0;
		try{
			count = uploadjarManagerManager.updateByPrimaryKey(record);
		}
		catch(Exception e){
			logger.error("updateByPrimaryKey error", e);
		}
		return count;
	}

    @Override
    public Integer insert(UploadjarManager record, InputStream src)
            throws BusinessException {
        try {
            //查询数据库， 是否有jar存在
            UploadjarManagerExample example = new UploadjarManagerExample();
            example.createCriteria().andJarNameEqualTo(record.getJarName());
            int count = this.countByExample(example);
            if(count > 0){
                throw new BusinessException("文件已存在");
            }
            File dest = new File(Constants.REDIS_JAR_HOME + File.separator + record.getJarName());
            FileUtils.copyInputStreamToFile(src, dest);
            //加载jar
            JarClassLoader.loadJar(dest.getAbsolutePath());
            return insert(record);
        } catch (Exception e) {
            logger.error("redis upload jarFile fail", e);
            throw new BusinessException("文件上传失败");
        }
    }

    @Override
    public Integer deleteByPrimaryKey(String id, String jarName) {
        JarClassLoader.unloadJarFile(jarName); //关闭jar文件流
        FileUtils.deleteQuietly(new File(Constants.REDIS_JAR_HOME + File.separator + jarName));//删除文件
        return deleteByPrimaryKey(id);
    }
	
}