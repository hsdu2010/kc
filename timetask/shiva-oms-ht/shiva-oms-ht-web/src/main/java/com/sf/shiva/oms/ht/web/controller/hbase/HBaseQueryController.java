package com.sf.shiva.oms.ht.web.controller.hbase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.sf.framework.domain.Result;
import com.sf.shiva.oms.ht.common.utils.ResultUtil;
import com.sf.shiva.oms.ht.domain.HbaseQueryCfg;
import com.sf.shiva.oms.ht.domain.HbaseQueryCfgExample;
import com.sf.shiva.oms.ht.dto.HBaseQueryResultDto;
import com.sf.shiva.oms.ht.service.hbase.HBaseQueryService;

/**
 * 
 * 描述：hbase查询controller
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年6月15日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
@RequestMapping("hbaseQuery")
@Controller
public class HBaseQueryController {
    
    private static final Logger logger = LoggerFactory.getLogger(HBaseQueryController.class);
    
    @Autowired
    private HBaseQueryService hbaseQueryServiceImpl;
    
    @RequestMapping("saveCfg")
    @ResponseBody
    public Boolean saveHBaseQueryCfg(HbaseQueryCfg cfg){
        try {
            hbaseQueryServiceImpl.saveHBaseQueryCfg(cfg);
            return true;
        }catch (Exception e) {
            logger.error("HBaseQueryController saveHBaseQueryCfg error.", e);
            return false;
        }
    }
    
    @RequestMapping("updateCfg")
    @ResponseBody
    public Boolean updateHBaseQueryCfg(HbaseQueryCfg cfg){
        try {
            hbaseQueryServiceImpl.updateHBaseQueryCfg(cfg);
            return true;
        }catch (Exception e) {
            logger.error("HBaseQueryController updateHBaseQueryCfg error.", e);
            return false;
        }
    }
    
    @RequestMapping("deleteCfg")
    @ResponseBody
    public Boolean deleteHBaseQueryCfg(String id){
        try {
            hbaseQueryServiceImpl.deleteHBaseQueryCfg(id);
            return true;
        }catch (Exception e) {
            logger.error("HBaseQueryController deleteHBaseQueryCfg error.", e);
            return false;
        }
    }
    
    @RequestMapping("selectCfgs")
    @ResponseBody
    public Map<String, Object> selectCfgs(String moduleName, Integer offset, Integer pageSize){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            HbaseQueryCfgExample example = new HbaseQueryCfgExample();
            if(StringUtils.isNotEmpty(moduleName)) {
                example.createCriteria().andModuleNameLike(moduleName);
            }
            example.setOrderByClause("create_tm desc");
            Page<HbaseQueryCfg> rows = hbaseQueryServiceImpl.selectCfgs(example, offset, pageSize);
            resultMap.put("rows", rows.getResult());
            resultMap.put("totalRecord", rows.getTotal());
        }catch (Exception e) {
            logger.error("hBaseQueryController selectCfgs error.", e);
        }
        return resultMap;
    }
    
    @RequestMapping("selectById")
    @ResponseBody
    public Result<HbaseQueryCfg> selectById(String id){
        try {
            return ResultUtil.createSuccessResult(hbaseQueryServiceImpl.selectById(id));
        }catch (Exception e) {
            logger.error("hbaseQueryController selectById error.", e);
            return ResultUtil.createErrorResult(null, null, null);
        }
    }
    
    @RequestMapping("queryHbase")
    @ResponseBody
    public Result<List<HBaseQueryResultDto>> queryHbase(HbaseQueryCfg cfg){
        try {
            return ResultUtil.createSuccessResult(hbaseQueryServiceImpl.queryHbase(cfg));
        }catch (Exception e) {
            logger.error("hbaseQueryController queryHbase error.", e);
            return ResultUtil.createErrorResult(null, null, null);
        }
    }

}
