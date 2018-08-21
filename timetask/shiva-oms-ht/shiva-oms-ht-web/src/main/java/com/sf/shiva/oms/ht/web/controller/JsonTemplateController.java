package com.sf.shiva.oms.ht.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.vo.JsonTemplate;
import com.sf.shiva.oms.ht.service.db.JsonTemplateCreateService;

/**
 * 
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID    DATE            PERSON            REASON
 *  1     2017年11月2日         835897           Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 835897
 * @version 1.0
 */
@RequestMapping("jsonTemplate")
@Controller
public class JsonTemplateController {

    @Autowired
    private JsonTemplateCreateService jsonTemplateCreateService;
    
    @RequestMapping("findAllTemplate")
    @ResponseBody
    public Map<String, Object> selectOrderTemplate(Integer offset, Integer pageSize, String templateType){
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
        Page<JsonTemplate> rows = jsonTemplateCreateService.findAllTemplate(pageNum, pageSize, templateType);
        resultMap.put("rows", rows.getResult());
        resultMap.put("totalRecord", rows.getTotal());
        return resultMap;
    }
    
    @RequestMapping("updateTemplate")
    @ResponseBody
    public boolean update(JsonTemplate orderCreateTemplate) {
        return jsonTemplateCreateService.update(orderCreateTemplate);
    }
    
    @RequestMapping("insertTemplate")
    @ResponseBody
    public boolean insert(JsonTemplate orderCreateTemplate) {
        return jsonTemplateCreateService.insert(orderCreateTemplate);
    }
    
    @RequestMapping("deleteTemplate")
    @ResponseBody
    public boolean deleteById(String id) {
        return jsonTemplateCreateService.deleteById(id);
    }
    
    @RequestMapping("findTemplateById")
    @ResponseBody
    public JsonTemplate findTemplateById(String id) {
        return jsonTemplateCreateService.findTemplateById(id);
    }
}
