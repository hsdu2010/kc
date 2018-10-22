package com.sf.shiva.oms.psm.service.datasend.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.psm.common.entity.PackageStatusRecordEntity;
import com.sf.shiva.oms.psm.common.entity.PackageStatusSendLoadEntity;
import com.sf.shiva.oms.psm.common.kafka.CustomKafkaProducer;
import com.sf.shiva.oms.psm.common.utils.ObjectMapperUtil;
import com.sf.shiva.oms.psm.common.utils.UUID22;
import com.sf.shiva.oms.psm.dao.PackageStatusSendLoadDao;
import com.sf.shiva.oms.psm.service.datasend.PackageStatusSendService;

/**
 * 描述：包裹状态发送
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月12日      80002517         Create
 * ****************************************************************************
 * </pre>
 * @author 80002517
 * @since 1.0
 */
@Service
public class PackageStatusSendServiceImpl implements PackageStatusSendService{
    
    private static final Logger logger = LoggerFactory.getLogger(PackageStatusSendServiceImpl.class);
    
    @Autowired
    private CustomKafkaProducer packageStatusSendProducer;
    @Autowired
    private PackageStatusSendLoadDao packageStatusSendLoadDao;
    @Override
    public void sendPackageStatus(Object param){
        String message = ObjectMapperUtil.toJson(param);
        try{
            packageStatusSendProducer.sendString(message);
        }catch(Exception e){
            logger.error("sendPackageStatus error msg:{}", message, e);
            handleSendPackageStatusException(param, message);
        }
    }

    @Override
    public void reSendPackageStatus(Object param){
        PackageStatusSendLoadEntity packageStatusSendLoadEntity = (PackageStatusSendLoadEntity) param;
        String packageNo = packageStatusSendLoadEntity.getPackageNo();
        try{
            logger.info("PackageStatusSendServiceImpl reSendPackageStatus. packageNo={}", packageNo);
            packageStatusSendProducer.sendString(packageStatusSendLoadEntity.getMsg());
            packageStatusSendLoadDao.removePackageStatusSendLoad(packageStatusSendLoadEntity); //发送成功，删除load记录
        }catch(Exception e){
            logger.error("reSendPackageStatus error packageNo:{}",  packageNo, e);//发送失败，更新下次发送时间
            handleReSendPackageStatusException(packageStatusSendLoadEntity);
        }
    }
    

    /** 
     * 处理下发异常
     * @param param 下发对象
     * @param message 下发报文
     * @author 80002517
     * @date 2018年1月10日
     */
    private void handleSendPackageStatusException(Object param, String message){
        PackageStatusRecordEntity packageStatusRecordEntity = (PackageStatusRecordEntity)param;
        PackageStatusSendLoadEntity packageStatusSendLoadEntity = buildPackageStatusSendLoadEntity(message, packageStatusRecordEntity);
        packageStatusSendLoadDao.savePackageStatusSendLoad(packageStatusSendLoadEntity);
    }
    
    /** 
     * 处理重发异常
     * @param packageStatusSendLoadEntity 包裹状态发送加载对象
     * @author 80002517
     * @date 2018年1月10日
     */
    private void handleReSendPackageStatusException( PackageStatusSendLoadEntity packageStatusSendLoadEntity){
        packageStatusSendLoadEntity.setModifyTm(new Date());
        packageStatusSendLoadDao.savePackageStatusSendLoad(packageStatusSendLoadEntity);
    }
    
    /** 
     * 包裹状态load实体类转换
     * @param message 报文信息
     * @param packageStatusRecordEntity 包裹流水实体类
     * @return 包裹状态Load实体类
     * @author 80002517
     * @date 2017年12月15日
     */
    private static PackageStatusSendLoadEntity buildPackageStatusSendLoadEntity(String message, PackageStatusRecordEntity packageStatusRecordEntity){
        PackageStatusSendLoadEntity packageStatusSendLoadEntity = new PackageStatusSendLoadEntity();
        packageStatusSendLoadEntity.setCreateTm(new Date());
        packageStatusSendLoadEntity.setMsg(message);
        packageStatusSendLoadEntity.setId(UUID22.getUUID22());
        packageStatusSendLoadEntity.setPackageNo(packageStatusRecordEntity.getPackageNo());
        return packageStatusSendLoadEntity;
    }
}
