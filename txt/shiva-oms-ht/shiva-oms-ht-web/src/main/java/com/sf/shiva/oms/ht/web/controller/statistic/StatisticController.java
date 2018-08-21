package com.sf.shiva.oms.ht.web.controller.statistic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sf.framework.domain.Result;
import com.sf.shiva.oms.ht.domain.OperationInfo;
import com.sf.shiva.oms.ht.service.statistic.StatisticService;

@Controller
@RequestMapping("statistic")
public class StatisticController {
    
    private static final Logger logger = LoggerFactory.getLogger(StatisticController.class);
    
    @Autowired
    private StatisticService statisticServiceImpl;
    
    @RequestMapping("recordOperation")
    @ResponseBody
    public Result<String> recordOperation(OperationInfo info){
        try {
            statisticServiceImpl.recordOperation(info);
        }catch (Exception e) {
            logger.error("StatisticController recordOperation error.", e);
        }
        return new Result<>();
    }
    
}
