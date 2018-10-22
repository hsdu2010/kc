package com.sf.shiva.oms.psm.service.tasktimer;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.shiva.oms.psm.cfgmanage.PackageStatusSendLoadSpoutProperties;
import com.sf.shiva.oms.psm.common.entity.PackageStatusSendLoadEntity;
import com.sf.shiva.oms.psm.common.utils.SpringContext;
import com.sf.shiva.oms.psm.dao.PackageStatusSendLoadDao;

import backtype.storm.tuple.Values;

/**
 * 描述：读取包裹下发加载任务
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年1月12日      80002517         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 80002517
 * @since 1.0
 */
public class ReadPackageStatusSendLoadTask implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(ReadPackageStatusSendLoadTask.class);
    private PackageStatusSendLoadDao packageStatusSendLoadDao;
    private PackageStatusSendLoadSpoutProperties packageStatusSendLoadSpoutProperties;
    private BlockingQueue<Values> messageQueue; 
    public ReadPackageStatusSendLoadTask(BlockingQueue<Values> queue) {
        this.messageQueue = queue;
        packageStatusSendLoadSpoutProperties = SpringContext.getInstance().getBean(PackageStatusSendLoadSpoutProperties.class);
        packageStatusSendLoadDao = SpringContext.getInstance().getBean(PackageStatusSendLoadDao.class);
    }

    @Override
    public void run() {
        try {
            if(messageQueue.size() <  packageStatusSendLoadSpoutProperties.getMessageQueueSize()){ //队列消息是否大于队列大小，小于才查询数据库
                List<PackageStatusSendLoadEntity> packageStatusSendLoadEntityList = packageStatusSendLoadDao.list(packageStatusSendLoadSpoutProperties.getMessageQueueSize());
                for (PackageStatusSendLoadEntity packageStatusSendLoadEntity : packageStatusSendLoadEntityList) {
                    messageQueue.put(new Values(packageStatusSendLoadEntity));
                }
            }
        } catch (Exception e) {
            logger.error("hbase scan error.{}", e);
        }
    }

}
