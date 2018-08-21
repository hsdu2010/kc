package com.sf.shiva.oms.ht.service.timertask;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.domain.OperationInfoExample;
import com.sf.shiva.oms.ht.manager.OperationInfoManager;

/**
 * 
 * 描述：数据清理定时任务
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年6月12日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
//@Component
public class DataClearTask {
    
    private static final Logger logger = LoggerFactory.getLogger(DataClearTask.class);
    
    @Autowired
    private OperationInfoManager operationInfoManagerImpl;
    
//    @Scheduled(cron = "0 0 2 * * ?")
    public void execute() {
        try {
            logger.info("DataClearTask execute begin");
            OperationInfoExample example = new OperationInfoExample();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -7);
            example.createCriteria().andOperateTimeLessThan(calendar.getTime());
            operationInfoManagerImpl.deleteByExample(example);
        }catch (Exception e) {
            logger.error("DataClearTask execute error.", e);
        }
    }
    
}
