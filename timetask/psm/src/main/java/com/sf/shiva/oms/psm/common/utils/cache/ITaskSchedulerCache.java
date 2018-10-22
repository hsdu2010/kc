package com.sf.shiva.oms.psm.common.utils.cache;

/**
 * 描述：缓存定时任务调度接口
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2014年10月16日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 */
@FunctionalInterface
public interface ITaskSchedulerCache {

    /**
     * @return 定时调度任务对象
     */
    CacheTaskScheduler getCacheTask();
}
