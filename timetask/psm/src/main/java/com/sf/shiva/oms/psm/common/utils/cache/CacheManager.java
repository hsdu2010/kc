package com.sf.shiva.oms.psm.common.utils.cache;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.shiva.oms.psm.common.utils.thread.ThreadUtils;

/**
 * 描述：缓存对象管理器
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
public class CacheManager {
    private static final CacheManager instance = new CacheManager();

    private Map<String, ICache<?, ?>> caches = new ConcurrentHashMap<>();

    private Map<String, CacheTaskScheduler> cacheTasks = new ConcurrentHashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(CacheManager.class);

    private static Thread scheduleThread = null;

    private static long scheduleInterval = 10 * 1000L;

    private CacheManager() {
    }

    public static CacheManager getInstance() {
        return instance;
    }

    /** 在容器初始化完毕之后调用 */
    public synchronized void startScheduleThread() {
        if (!cacheTasks.isEmpty() && scheduleThread == null) {
            Thread t = new ScheduleThread();
            t.start();
            scheduleThread = t;
        }
    }

    public class ScheduleThread extends Thread {

        public ScheduleThread() {
            super("CacheTaskScheduleThread");
            super.setDaemon(true);
        }

        @Override
        public void run() {
            while (true) {
                // schedule all tasks
                try {
                    logger.debug("Cache task schedule thread running.");
                    doRun();
                } catch (Exception x) {
                    logger.error("Shedule cache task exception.", x);
                } finally {
                    try {
                        Thread.sleep(scheduleInterval);
                    } catch (InterruptedException e) {
                        logger.error("Shedule cache task InterruptedException.", e);
                    }
                }
            }
        }

        public void doRun() {
            long timeMillis = System.currentTimeMillis();
            Iterator<Map.Entry<String, CacheTaskScheduler>> it = cacheTasks.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, CacheTaskScheduler> entry = it.next();
                CacheTaskScheduler scheduler = entry.getValue();
                try {
                    if (scheduler.isTimeForSchedule(timeMillis)) {
                        logger.info("Time to schedule cache {}'s task.", entry.getKey());
                        ThreadUtils.execute(scheduler);
                    }
                } catch (Exception e) {
                    logger.error("Cache task " + entry.getKey() + " scheduler run exception.", e);
                }
            }
        }
    }

    void register(ICache<?, ?> cache) {
        if (cache instanceof ITaskSchedulerCache) {
            CacheTaskScheduler task = ((ITaskSchedulerCache) cache).getCacheTask();
            if (null != task) {
                cacheTasks.put(cache.getName(), task);
                startScheduleThread();
            }
        }
        caches.put(cache.getName(), cache);
    }

    /**
     * 根据缓存的注册名称，获取缓存对象
     * 
     * @param name
     *            缓存的注册名称
     * @return 缓存对象
     */
    @SuppressWarnings("unchecked")
    public <K, V> ICache<K, V> getCache(String name) {
        return (ICache<K, V>) caches.get(name);
    }

}
