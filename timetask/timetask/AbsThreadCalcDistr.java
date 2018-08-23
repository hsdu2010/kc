package com.sf.shiva.oms.pcm.threadutils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.sf.shiva.oms.pcm.utils.thread.ThreadUtils;


/**
 * 
 * 描述：多线程计算分发器
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年6月2日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 * @since 1.0
 */
public abstract class AbsThreadCalcDistr<T> implements InitializingBean {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private ExecutorService executorService;// 线程服务类
    private List<Future<String>> futures;// 任务列表

    @Override
    public void afterPropertiesSet() throws Exception {
        executorService = ThreadUtils.newNamedExecutorService(getThreadName(), getThreadCount());
        futures = new ArrayList<>(getThreadCount());
    }

    /**
     * 任务执行
     * 
     * @author zhangYao 568677
     * @date 2015年6月16日
     */
    public void executTask() {
        List<T> datas = findDatas(getLibrayCodes(), getLimitNum());// 获取需要处理的数据集合
        if (null != datas && !datas.isEmpty()) {
            for (T t : datas) {// 使用多线程，分发任务，执行处理
                addFutures(t);// 添加任务列表
            }
            await();// 等待所有的任务完成
        }
    }

    /**
     * 添加任务列表
     * 
     * @param load
     *            任务对象
     * @author zhangYao 568677
     * @date 2016年5月18日
     */
    private void addFutures(T t) {
        futures.add(executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                logicCalc(t);
                return null;
            }
        }));
    }

    /**
     * 等待所有的任务完成
     * 
     * @author zhangYao 568677
     * @date 2015年6月16日
     */
    private void await() {
        for (Future<String> future : futures) {
            try {
                future.get(1, TimeUnit.MINUTES);// 设置超时时间为1分钟
            } catch (InterruptedException e) {// 如果当前的线程在等待时被中断
                logger.error("task thread break", e);
                future.cancel(true);// 取消任务
                Thread.currentThread().interrupt();//中断等待线程
            } catch (ExecutionException e) {// 如果计算抛出异常
                logger.error("task thread error", e);
                future.cancel(true);// 取消任务
            } catch (TimeoutException e) {// 线程超时
                logger.error("task thread timeout", e);
                future.cancel(true);// 取消任务
            }
        }
        futures.clear();
    }

    /**
     * 获取线程名称
     * 
     * @return 线程名称
     * @author zhangYao 568677
     * @date 2015年6月16日
     */
    protected abstract String getThreadName();

    /**
     * 获取节点处理的分片值集合
     * 
     * @return 节点处理的分片值集合
     * @author 568677
     * @date 2017年6月2日
     */
    public abstract List<String> getLibrayCodes();

    /**
     * 获取线程数量
     * 
     * @return 线程数量
     * @author zhangYao 568677
     * @date 2015年6月17日
     */
    protected abstract int getThreadCount();

    /**
     * 每次处理的数量
     * 
     * @return 处理数量
     * @author zhangYao 568677
     * @date 2015年10月9日
     */
    protected abstract int getLimitNum();

    /**
     * 获取计算数据加载列表
     * 
     * @param librayCodes
     *            节点处理的分片值集合
     * @param limitNum
     *            每次处理的数据量
     * @return 计算数据加载列表
     * @author 568677
     * @date 2017年6月2日
     */
    protected abstract List<T> findDatas(List<String> librayCodes, int limitNum);

    /**
     * 业务逻辑计算和保存结果
     * 
     * @param load
     *            当前处理的加载对象
     * @author zhangYao 568677
     * @date 2015年6月16日
     */
    protected abstract void logicCalc(T t);

}
