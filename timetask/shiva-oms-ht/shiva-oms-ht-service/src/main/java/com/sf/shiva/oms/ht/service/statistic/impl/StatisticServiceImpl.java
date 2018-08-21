package com.sf.shiva.oms.ht.service.statistic.impl;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.ht.common.utils.WebUtil;
import com.sf.shiva.oms.ht.domain.OperationInfo;
import com.sf.shiva.oms.ht.manager.OperationInfoManager;
import com.sf.shiva.oms.ht.service.statistic.StatisticService;

/**
 * 
 * 描述：统计服务service实现
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年6月5日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
@Service
public class StatisticServiceImpl implements StatisticService{
    
    @Autowired
    private OperationInfoManager operationInfoManagerImpl;

    @Override
    public void recordOperation(OperationInfo info) {
        info.setIp(WebUtil.getRequestIp());
        info.setUsername((String)SecurityUtils.getSubject().getPrincipal());
        info.setOperateTime(new Date());
        operationInfoManagerImpl.insert(info);
    }

}
