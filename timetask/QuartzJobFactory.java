package com.sf.shiva.oms.pcm.timedtask.base;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.sf.sgs.sflog.SfLogger;
import com.sf.sgs.sflog.SfLoggerFactory;
import com.sf.sgs.sflog.common.ComponentName;
import com.sf.shiva.oms.pcm.managerextend.ScheduleJobExtendManager;
import com.sf.shiva.oms.pcm.model.ScheduleJob;

/**
 * 
 * 描述：定时任务调度工具
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年2月9日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
public class QuartzJobFactory implements ApplicationContextAware{
    
    public static final SfLogger logger = SfLoggerFactory.getLogger(ComponentName.MS, ConfigUtils.getProperty("dubbo.application.name","shiva-oms-pcm"),QuartzJobFactory.class);
    
    private static final String JOB_STATUS_STOP = "0";// 任务状态 0禁用 1启用 2删除
    private static final String JOB_STATUS_BEGIN = "1";// 任务状态 0禁用 1启用 2删除
    private static final String JOB_STATUS_IGNORE = "2";// 任务状态 0禁用 1启用 2删除
    // 是否是Spring中定义的Bean 0不是 1是 如果是0需要设置全类名,测试CLAZZ字段需要配置
    private static final String IS_SPRING_BEAN = "1";
    private static final String IS_CONCURRENT = "1";// 是否并发启动任务 0禁用 1启用
    private static final String DEFAULT_METHOD = "executeTask";
    
    @Value("${system.name:shiva-oms-pcm}")
    private String systemName;
    
    private ApplicationContext applicationContext;
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    @Autowired
    private ScheduleJobExtendManager scheduleJobExtendManager;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    
    /**
     * 定时任务开始接口 TaskName 和 TaskGroup为任务管理器中的联合主键 但是创建时按照TaskName为主键插入任务管理器
     * 
     * @author 833899
     * @date 2017年3月23日
     */
    public void arrageScheduleJob() {
        try {
            List<ScheduleJob> jobList = findScheduleJobList();// 获取数据库配置文件
            if (CollectionUtils.isNotEmpty(jobList)) {
                for (ScheduleJob scheduleJob : jobList) {
                    doArrageScheduleJob(scheduleJob);
                }
            }
        } catch (Exception e) {
            logger.info("QuartzJobFactory.arrageScheduleJob error", e);
        }
    }
    
    /**
     * 进行任务定时添加，删除，修改
     * 
     * @param scheduleJob定时任务配置属性
     * @throws SchedulerException
     * @author 833899
     * @date 2017年3月23日
     */
    private void doArrageScheduleJob(ScheduleJob scheduleJob) throws SchedulerException {
        if (!StringUtils.equals(scheduleJob.getTaskStatus(), JOB_STATUS_IGNORE)) {
            TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getTaskName(), scheduleJob.getTaskGroup());
            CronTrigger trigger = (CronTrigger) schedulerFactoryBean.getScheduler().getTrigger(triggerKey);
            if (null == trigger) {
                createSheduler(schedulerFactoryBean.getScheduler(), scheduleJob);
            } else {// Trigger已存在，那么更新相应的定时设置
                updateScheduler(schedulerFactoryBean.getScheduler(), scheduleJob, triggerKey, trigger);
            }
        }
    }

    /**
     * 由数据库查询数据库中的定时任务配置
     * 
     * @Note : 扫面数据库,查看是否有计划任务的变动
     * @return
     * @author 833899
     * @date 2017年3月23日
     */
    private List<ScheduleJob> findScheduleJobList() {
        return scheduleJobExtendManager.findScheduleJobList(systemName);
    }

    /**
     * 更新相应的定时设置 根据job_status做相应的处理
     * 
     * @param scheduler
     * @param job
     * @param triggerKey
     * @param trigger
     * @author 833899
     * @date 2017年3月23日
     */
    public void updateScheduler(Scheduler scheduler, ScheduleJob job, TriggerKey triggerKey, CronTrigger trigger) {
        try {
            if (StringUtils.equals(job.getTaskStatus(), JOB_STATUS_BEGIN)) {// 1启用时候,修改定时任务的信息
                doUpdateScheduler(scheduler, job, triggerKey, trigger);
            } else if (StringUtils.equals(job.getTaskStatus(), JOB_STATUS_STOP)) {// 0禁用时候，删除定时任务信息
                doDeleteScheduler(scheduler, job, triggerKey, trigger);
            }
        } catch (Exception e) {
            logger.info("QuartzJobFactory.updateScheduler error", e);
        }
    }

    /**
     * 更新相应的定时设置 根据job_status做相应的处理
     * 
     * @param scheduler
     * @param job
     * @param triggerKey
     * @param trigger
     * @throws SchedulerException
     * @author 833899
     * @date 2017年3月23日
     */
    private static void doUpdateScheduler(Scheduler scheduler, ScheduleJob job, TriggerKey triggerKey, CronTrigger trigger) throws SchedulerException {
        if (!trigger.getCronExpression().equalsIgnoreCase(job.getCronExpression())) {
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
            logger.info(job.getTaskGroup() + "." + job.getTaskName() + " 更新完毕,目前cron表达式为:" + job.getCronExpression() + " isSpringBean：" + job.getSpringBeanFlag() + " concurrent: " + job.getConcurrent());
        }
    }

    /**
     * 
     * @param scheduler
     * @param job
     * @param triggerKey
     * @param trigger
     * @throws SchedulerException
     * @author 833899
     * @date 2017年3月23日
     */
    private static void doDeleteScheduler(Scheduler scheduler, ScheduleJob job, TriggerKey triggerKey, CronTrigger trigger) throws SchedulerException {
        scheduler.pauseTrigger(triggerKey);// 停止触发器
        scheduler.unscheduleJob(triggerKey);// 移除触发器
        scheduler.deleteJob(trigger.getJobKey());// 删除任务
        logger.info(job.getTaskGroup() + "." + job.getTaskName() + "删除完毕");
    }

    /**
     * 创建定时任务
     * 
     * @param scheduler
     *            定时任务管理器
     * @param job
     *            对象信息
     * @author 833899
     * @date 2017年3月23日
     */
    public void createSheduler(Scheduler scheduler, ScheduleJob job) {
        try {
            if (StringUtils.equals(job.getTaskStatus(), JOB_STATUS_BEGIN)) {
                doCreateSheduler(scheduler, job);
            }
        } catch (Exception e) {
            logger.info("QuartzJobFactory.createSheduler error", e);
        }

    }

    /**
     * 由定时任务数据库
     * 
     * @param job
     * @return
     * @throws Exception
     * @author 833899
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @date 2017年3月23日
     */
    private MethodInvokingJobDetailFactoryBean createDetailFactoryBean(ScheduleJob job) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
      MethodInvokingJobDetailFactoryBean methodInvJobDetailFB = new MethodInvokingJobDetailFactoryBean();
        // 定义的任务类为Spring的定义的Bean则调用 getBean方法
        Object targetObject = getTargetObjectByJob(job);
        if(null==targetObject){
            return null;
        }
        methodInvJobDetailFB.setName(job.getTaskName());// 设置Job名称
        methodInvJobDetailFB.setTargetObject(targetObject);
        methodInvJobDetailFB.setTargetMethod(StringUtils.isNotEmpty(job.getTargetMethod()) ? job.getTargetMethod() : DEFAULT_METHOD);// 设置任务方法
        methodInvJobDetailFB.setConcurrent(StringUtils.equals(job.getConcurrent(), IS_CONCURRENT) ? true : false);// 并发设置
        methodInvJobDetailFB.afterPropertiesSet();// 将管理Job类提交到计划管理类
        return methodInvJobDetailFB;
    }

    /**
     * 获取job的具体执行类
     * @param job 数据库中配置的job
     * @return job的执行类
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @author 01159741
     * @date 2017年9月19日
     */
    private Object getTargetObjectByJob(ScheduleJob job) throws InstantiationException, IllegalAccessException {
        try {
            if (StringUtils.equals(job.getSpringBeanFlag(), IS_SPRING_BEAN)) {// 是Spring中定义的Bean
                return applicationContext.getBean(job.getTargetObject());
            } else {// 不是
                return Class.forName(job.getTargetClazz()).newInstance();
            }
        } catch (ClassNotFoundException e) {
            logger.error("job taskName:"+job.getTaskName(),e);
        } catch (NoSuchBeanDefinitionException e2) {
            logger.error("job taskName:"+job.getTaskName(),e2);
        }
        return null;
    }

    /**
     * 实际创建一个定时任务，并使用数据库配置属性，初始化定时任务
     * 
     * @param scheduler
     * @param job
     *            数据库属性
     * @throws SchedulerException
     * @throws Exception
     * @author 833899
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @date 2017年3月23日
     */
    private void doCreateSheduler(Scheduler scheduler, ScheduleJob job) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SchedulerException {
        // 在工作状态可用时,即job_status = 1 ,开始创建
        // 新建一个基于Spring的管理Job类
        MethodInvokingJobDetailFactoryBean methodInvJobDetailFB = createDetailFactoryBean(job);
        if(null==methodInvJobDetailFB){
            return;
        }
        JobDetail jobDetail = methodInvJobDetailFB.getObject();// 动态
        jobDetail.getJobDataMap().put("scheduleJob", job);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());// 表达式调度构建器
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(job.getTaskName(), job.getTaskGroup()).withSchedule(scheduleBuilder).build();// 按新的cronExpression表达式构建一个新的trigger
        scheduler.scheduleJob(jobDetail, trigger);// 注入到管理类
        logger.info(job.getTaskGroup() + "." + job.getTaskName() + "创建完毕");
    }
}
