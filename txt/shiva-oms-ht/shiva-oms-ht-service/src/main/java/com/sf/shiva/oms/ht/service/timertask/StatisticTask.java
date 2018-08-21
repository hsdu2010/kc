package com.sf.shiva.oms.ht.service.timertask;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.domain.AccessStatistic;
import com.sf.shiva.oms.ht.manager.AccessStatisticManager;
import com.sf.shiva.oms.ht.manager.extend.AccessStatisticExtendManager;

@Component
public class StatisticTask {
    
    private static final Logger logger = LoggerFactory.getLogger(StatisticTask.class);
    
    @Autowired
    private AccessStatisticManager accessStatisticManagerImpl;
    @Autowired
    private AccessStatisticExtendManager accessStatisticExtendManagerImpl;
    
    @Scheduled(cron = "0 0 1 * * ?")
    public void execute() {
        try {
            logger.info("StatisticTask execute begin");
            List<AccessStatistic> list = accessStatisticExtendManagerImpl.findAccessStatistics();
            if(CollectionUtils.isNotEmpty(list)) {
                list.forEach(statistic -> accessStatisticManagerImpl.insert(statistic));
            }else {
                logger.error("StatisticTask query empty result.");
            }
        }catch (Exception e) {
            logger.error("StatisticTask execute error.", e);
        }
    }
    
}
