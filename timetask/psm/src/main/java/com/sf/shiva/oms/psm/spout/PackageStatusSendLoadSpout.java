package com.sf.shiva.oms.psm.spout;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.sf.shiva.oms.psm.cfgmanage.PackageStatusSendLoadSpoutProperties;
import com.sf.shiva.oms.psm.common.enumtype.FieldKeyEnum;
import com.sf.shiva.oms.psm.common.enumtype.StreamIdEnum;
import com.sf.shiva.oms.psm.common.utils.SpringContext;
import com.sf.shiva.oms.psm.service.tasktimer.ReadPackageStatusSendLoadTask;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

/**
 * 描述：包裹状态重试Spout
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月12日      80002517         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 80002517
 * @since 1.0
 */
public class PackageStatusSendLoadSpout extends BaseRichSpout {

    private static final long serialVersionUID = -7294353528624944171L;
    /** 默认线程数 1，每个spout默认只有一个线程去执行定时任务 */
    private static final int HBASE_SPOUT_DEFAULT_THREAD_NUM = 1;
    private BlockingQueue<Values> queue;
    private SpoutOutputCollector collector;
    private PackageStatusSendLoadSpoutProperties packageStatusSendLoadSpoutProperties;

    @Override
    public void open(@SuppressWarnings("rawtypes") Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
        packageStatusSendLoadSpoutProperties = SpringContext.getInstance().getBean(PackageStatusSendLoadSpoutProperties.class);
        queue = new LinkedBlockingQueue<>(packageStatusSendLoadSpoutProperties.getMessageQueueSize());
        // 创建一个延时定时线程
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(HBASE_SPOUT_DEFAULT_THREAD_NUM);
        // 使用延时定时线程执行定时任务 HBaseTask
        scheduledExecutorService.scheduleWithFixedDelay(new ReadPackageStatusSendLoadTask(queue), packageStatusSendLoadSpoutProperties.getThreadInitialDelay(),
                packageStatusSendLoadSpoutProperties.getThreadDelay(), TimeUnit.SECONDS);

    }

    @Override
    public void nextTuple() {
        try {
            // 从队列中取出 hbase查出的数据,发送元组
            collector.emit(StreamIdEnum.PACKAGE_STATUS_RESEND.getStreamId(), queue.take());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declareStream(StreamIdEnum.PACKAGE_STATUS_RESEND.getStreamId(), new Fields(FieldKeyEnum.FIELD_PACKAGE_STATUS_SEND_BOLT.getKey()));
    }

}
