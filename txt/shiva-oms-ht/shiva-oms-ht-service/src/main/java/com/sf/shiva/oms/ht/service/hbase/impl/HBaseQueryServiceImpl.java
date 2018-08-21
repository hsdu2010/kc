package com.sf.shiva.oms.ht.service.hbase.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sf.shiva.oms.ht.common.utils.UUID22;
import com.sf.shiva.oms.ht.domain.HbaseQueryCfg;
import com.sf.shiva.oms.ht.domain.HbaseQueryCfgExample;
import com.sf.shiva.oms.ht.dto.HBaseQueryResultDto;
import com.sf.shiva.oms.ht.manager.HbaseQueryCfgManager;
import com.sf.shiva.oms.ht.manager.extend.hbase.HBaseQueryManager;
import com.sf.shiva.oms.ht.service.hbase.HBaseQueryService;

/**
 * 
 * 描述：hbase查询service实现
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
@Service
public class HBaseQueryServiceImpl implements HBaseQueryService{
    
    private static final String ROWKEY_QUERY_TYPE = "1";
    private static final String COLUMN_QUERY_TYPE = "2";
    private static final String FUZZY_SEARCH_FLAG = "1";
    
    @Autowired
    private HbaseQueryCfgManager hbaseQueryCfgManagerImpl;
    @Autowired
    private HBaseQueryManager hBaseQueryManagerImpl;

    @Override
    public void saveHBaseQueryCfg(HbaseQueryCfg cfg) {
        cfg.setId(UUID22.getUUID22());
        cfg.setCreateEmp((String)SecurityUtils.getSubject().getPrincipal());
        cfg.setCreateTm(new Date());
        cfg.setModifyEmp(cfg.getCreateEmp());
        cfg.setModifyTm(cfg.getCreateTm());
        hbaseQueryCfgManagerImpl.insert(cfg);
    }

    @Override
    public void updateHBaseQueryCfg(HbaseQueryCfg cfg) {
        cfg.setModifyEmp((String)SecurityUtils.getSubject().getPrincipal());
        cfg.setModifyTm(new Date());
        hbaseQueryCfgManagerImpl.updateByPrimaryKey(cfg);
    }

    @Override
    public void deleteHBaseQueryCfg(String id) {
        hbaseQueryCfgManagerImpl.deleteByPrimaryKey(id);
    }

    @Override
    public Page<HbaseQueryCfg> selectCfgs(HbaseQueryCfgExample example, Integer offset, Integer pageSize) {
        Integer pageNum = null;
        if(pageSize != null) {
            pageNum = (offset.intValue() / pageSize.intValue()) + 1;
        }
        PageHelper.startPage(pageNum, pageSize);
        return (Page<HbaseQueryCfg>)hbaseQueryCfgManagerImpl.selectByExample(example);
    }

    @Override
    public HbaseQueryCfg selectById(String id) {
        return hbaseQueryCfgManagerImpl.selectByPrimaryKey(id);
    }

    @Override
    public List<HBaseQueryResultDto> queryHbase(HbaseQueryCfg cfg) {
        boolean isFuzzySearch = FUZZY_SEARCH_FLAG.equals(cfg.getIsFuzzySearch());
        if(ROWKEY_QUERY_TYPE.equals(cfg.getQueryType())) {//根据rowkey查询
            return hBaseQueryManagerImpl.scanByRowKey(cfg.getTableName(), cfg.getQueryParam(), cfg.getLimitNum(), isFuzzySearch);
        }else if(COLUMN_QUERY_TYPE.equals(cfg.getQueryType())) {//根据列查询
            return hBaseQueryManagerImpl.findByColumn(cfg.getTableName(), cfg.getFilterFamily(), cfg.getFilterColumn(), cfg.getQueryParam(), cfg.getLimitNum(), isFuzzySearch);
        }
        return Collections.emptyList();
    }
    
}
