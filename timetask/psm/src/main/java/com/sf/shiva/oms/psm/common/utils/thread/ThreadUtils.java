package com.sf.shiva.oms.psm.common.utils.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.shiva.oms.psm.common.exception.BusinessException;

/**
 * 
 * 描述：线程工具类
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年3月8日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 * @since 1.0
 */
public class ThreadUtils {
    private static final Logger logger = LoggerFactory.getLogger(ThreadUtils.class);
    private static ExecutorService threadPool;

    private ThreadUtils() {
    }

    public static void sleep(long millis) {
        if (millis <= 0L)
            return;
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            logger.error("errorMsg:{}", e);
            throw new BusinessException(e);
        }
    }

    public static void sleepNoException(long millis) {
        if (millis <= 0L)
            return;
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            logger.error("errorMsg:{}", e);
        }
    }

    public static void sleep(int millis, String beanName, String reason) {
        String logStr = String.format("%1$s sleeping {}ms as {}", new Object[] { beanName });
        logger.info(logStr, millis, reason);

        sleep(millis);
    }

    public static double elapsedTime(long startTime) {
        long time = System.currentTimeMillis() - startTime;
        return (int) (time / 1000.0D * 100.0D) / 100.0D;
    }

    public static void execute(Runnable runnable) {
        getThreadPoolExecutor().execute(runnable);
    }

    public static ExecutorService newNamedExecutorService(String namePrefix) {
        return newNamedExecutorService(namePrefix, 0);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static ExecutorService newNamedExecutorService(String namePrefix, int nThreads) {
        if (nThreads <= 0) {
            nThreads = Runtime.getRuntime().availableProcessors();
            if (nThreads < 6) {
                nThreads = 6;
            }
        }
        return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new NamedThreadFactory(namePrefix), new ThreadPoolExecutor.AbortPolicy());
    }

    public static synchronized ExecutorService getThreadPoolExecutor() {
        if (threadPool == null) {
            int threadPoolSize = Runtime.getRuntime().availableProcessors();
            if (threadPoolSize < 6) {
                threadPoolSize = 6;
            }
            threadPool = Executors.newFixedThreadPool(threadPoolSize);
        }

        return threadPool;
    }
}