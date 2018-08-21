package com.sf.shiva.oms.ht.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.UploadjarManager;
import com.sf.shiva.oms.ht.domain.UploadjarManagerExample;
import com.sf.shiva.oms.ht.exception.BusinessException;
import com.sf.shiva.oms.ht.service.UploadjarManagerService;

/**
 * UploadjarManagerController类
 *
 * @author 01369626
*/
@RequestMapping("uploadjarManager")
@Controller
public class UploadjarManagerController {
    
    private static final Logger logger = LoggerFactory.getLogger(UploadjarManagerController.class);

	@Autowired
    private UploadjarManagerService uploadjarManagerService;
    
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 *
	 * @return int 记录总数
	 *
    */
    @RequestMapping("countByExample")
    @ResponseBody
	public Integer countByExample(UploadjarManagerExample example){
		int count = 0;
	    count = uploadjarManagerService.countByExample(example);
	    return count;
	}

	/**
	 * 按主键删除
	 * 
	 * @param id 
	 * 
	 * @return int 成功条数  
	 *
    */
	@RequestMapping("deleteByPrimaryKey")
    @ResponseBody
    public Boolean deleteByPrimaryKey(String id, String jarName){
    	if(id == null || "".equals(id.trim())){
    		throw new IllegalStateException("参数id不能为空");
    	}
    	int count = 0;
    	count = uploadjarManagerService.deleteByPrimaryKey(id);
        return  count > 0;
    }

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
	@RequestMapping("insert")
    @ResponseBody
    public Boolean insert(@RequestParam("uploadjar") MultipartFile file, UploadjarManager record){
    	if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	int count = 0;
    	if(file.getSize() == 0){
    	    return false;
        }
    	try {
            count = uploadjarManagerService.insert(record, file.getInputStream());
            return  count > 0;
        } catch (BusinessException e) {
            logger.error("UploadjarManagerController insert error.", e);
        } catch (Exception e) {
            logger.error("UploadjarManagerController insert error.", e);
        }
    	return false;
    }
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * @param offset 起始条数偏移量
	 * @param pageSize  每页数量
	 * 
	 * @return  List<UploadjarManager> 记录列表 
	 *
    */	
	@RequestMapping("selectByExample")
    @ResponseBody
    public Map<String, Object> selectByExample(UploadjarManager record, Integer offset, Integer pageSize){
    	if(offset != null && offset < 0){
			throw new IllegalStateException("参数offset不能小于0");
		}
		if(pageSize != null && pageSize < 1){
			throw new IllegalStateException("参数pageSize不能小于1");
		}
		if((offset == null && pageSize != null)
			||(offset != null && pageSize == null)){
			throw new IllegalStateException("offset、pageSize必须同时为null或不为null");
		}
		Integer pageNum = null;
		if(pageSize != null){
			pageNum = (offset.intValue() / pageSize.intValue()) + 1;
		}
   		Map<String, Object> resultMap = new HashMap<String, Object>();
    	UploadjarManagerExample example = new UploadjarManagerExample(); 
    	Page<UploadjarManager> rows = null;
		rows = uploadjarManagerService.selectByExample(example, pageNum, pageSize);
		resultMap.put("rows", rows.getResult());
		resultMap.put("totalRecord", rows.getTotal());
        return resultMap;
    }

	/**
	 * 按主键查询
	 * 
	 * @param id 
	 * 
	 * @return UploadjarManager 记录 
	 *
    */	
	@RequestMapping("selectByPrimaryKey")
    @ResponseBody
	public UploadjarManager selectByPrimaryKey(String id){
    	if(id == null || "".equals(id.trim())){
    		throw new IllegalStateException("参数id不能为空");
    	}
	    return uploadjarManagerService.selectByPrimaryKey(id);
	}
	
	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
     *
	 * @return int 成功条数  
	 *
    */
	@RequestMapping("updateByPrimaryKeySelective")
    @ResponseBody
	public Boolean updateByPrimaryKeySelective(UploadjarManager record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	if(record.getId() == null || "".equals(record.getId().trim())){
    		throw new IllegalStateException("主键id属性不能为空");
    	}
	    int count = 0;
		count = uploadjarManagerService.updateByPrimaryKeySelective(record);
	    return count > 0; 
	}

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
     *
	 * @return int 成功条数  
	 *
    */
	@RequestMapping("updateByPrimaryKey")
    @ResponseBody
    public Boolean updateByPrimaryKey(UploadjarManager record){
    	if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	if(record.getId() == null || "".equals(record.getId().trim())){
    		throw new IllegalStateException("主键id属性不能为空");
    	}
    	int count = 0;
    	count = uploadjarManagerService.updateByPrimaryKey(record);
        return count > 0;
    }
	
}