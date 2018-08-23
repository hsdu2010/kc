package com.sf.shiva.oms.pcm.timedtask.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.pcm.datacache.SystemCfgCache;
import com.sf.shiva.oms.pcm.service.kafka.producer.PushContainerService;
import com.sf.shiva.oms.pcm.threadutils.AbsThreadCalcDistr;
import com.sf.shiva.oms.pcm.timedtask.TaskInterface;

/**
 * 描述：容器信息重新推送定时任务
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年2月11日      80002517         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 80002517
 * @since 1.0
 */
@Service
public class PushContainerTaskService extends AbsThreadCalcDistr<String> implements TaskInterface {

    @Value("${task.pushContainer.threadCount:6}")
    private int threadCount;
    @Value("${task.pushContainer.limitNum:200}")
    private int limitNum;
    @Autowired
    private SystemCfgCache systemCfgCache;
    @Autowired
    private PushContainerService pushContainerService;

    @Override
    public void execute() {
        logger.debug("rePushContainerTaskService begin");
        super.executTask();
        logger.debug("rePushContainerTaskService end");
    }

    @Override
    protected String getThreadName() {
        return "Thread-PushContainerTaskService";
    }

    @Override
    public List<String> getLibrayCodes() {
        return systemCfgCache.getLibraryCodes();
    }

    @Override
    protected int getThreadCount() {
        return threadCount;
    }

    @Override
    protected int getLimitNum() {
        return limitNum;
    }

    @Override
    protected List<String> findDatas(List<String> librayCodes, int limitNum) {
        return librayCodes;
    }

    @Override
    protected void logicCalc(String libraryCode) {
        pushContainerService.timerBatchPush(libraryCode, getLimitNum());
    }

}
