package com.sf.shiva.oms.ht.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.sf.shiva.oms.ht.common.utils.UUID22;
import com.sf.shiva.oms.ht.domain.dto.PackageStatusDto;
import com.sf.shiva.oms.ht.domain.dto.WaybillPackageOperate;
import com.sf.shiva.oms.ht.domain.dto.WaybillPackageOperateProperty;
import com.sf.shiva.oms.ht.service.PackageStatusService;
import com.sf.shiva.oms.ht.service.util.CustomKafkaProducer;
import com.sf.shiva.oms.ht.service.util.ObjectMapperUtil;


/**
 * 
 * 描述：包裹状态Service实现类
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月29日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
@Service
public class PackageStatusServiceImpl implements PackageStatusService{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String CLUSTER_NAME = "other";
	private static final String TOPIC = "SHIVA_OMS_PACKAGE_STATUS_LOG";
	private static final String TOPIC_TOKENS = "SHIVA_OMS_PACKAGE_STATUS_LOG:O2@pb8u1";
	private static final String MONITOR_URL = "http://mommon_other.intsit.sfdc.com.cn:1080/mom-mon/monitor/requestService.pub";

	@Override
	public boolean sendPackageStatusMsg(String packageNo, String zoneCode, String packageStatus, String opCode, String orderNo, String operateEmpCode) {
		PackageStatusDto packageStatusDto = new PackageStatusDto();
		fillPackageStatus(packageStatusDto, orderNo, packageNo, packageStatus);
		packageStatusDto.setWaybillPackageOperates(getWaybillPackageOperate(opCode, zoneCode, operateEmpCode, packageStatusDto));
		return sendMsg(packageStatusDto);
	}
	
	private boolean sendMsg(PackageStatusDto dto) {
		CustomKafkaProducer producer = new CustomKafkaProducer();
		producer.setClusterName(CLUSTER_NAME);
		producer.setMonitorUrl(MONITOR_URL);
		producer.setTopic(TOPIC);
		producer.setTopicTokens(TOPIC_TOKENS);
		producer.setProducerPoolSize(4);
		try {
			producer.initProducer();
			return producer.sendString(ObjectMapperUtil.toJson(dto));
		}catch (Exception e) {
			logger.error("PackageStatusServiceImpl sendMsg error. {}", e);
			return false;
		}
	}
	
	private void fillPackageStatus(PackageStatusDto dto, String orderNo, String packageNo, String packageStatus) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dto.setCreateTime(sdf.format(date));
		sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
		dto.setCreateTimeSub(sdf.format(date));
		dto.setOrderNo(orderNo);
		dto.setPackageNo(packageNo);
		dto.setWaybillNo(packageNo);
		dto.setPackageStatus(packageStatus);
		dto.setPackageStatusId(UUID22.getUUID22());
	}
	
	private List<WaybillPackageOperate> getWaybillPackageOperate(String opCode, String zoneCode, String operateEmpCode, PackageStatusDto dto){
		List<WaybillPackageOperate> list = new ArrayList<>();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		WaybillPackageOperate waybillPackageOperate = new WaybillPackageOperate();
		setOpAndReasonCode(waybillPackageOperate, opCode);
		waybillPackageOperate.setCreateTime(dto.getCreateTime());
		waybillPackageOperate.setOperateTime(sdf.format(date));
		waybillPackageOperate.setZoneCode(zoneCode);
		waybillPackageOperate.setOrderNo(dto.getOrderNo());
		waybillPackageOperate.setPackageStatusId(dto.getPackageStatusId());
		waybillPackageOperate.setOperateEmpCode(operateEmpCode);
		if("80".equals(opCode)) {
			waybillPackageOperate.setUserDefList(getUserDefList(dto.getOrderNo(), dto.getPackageStatusId(), waybillPackageOperate.getOperateId(), dto.getCreateTime()));
		}else {
			waybillPackageOperate.setUserDefList(new ArrayList<>());
		}
		list.add(waybillPackageOperate);
		return list;
	}
	
	private void setOpAndReasonCode(WaybillPackageOperate waybillPackageOperate, String opCode) {
		if(!StringUtils.isEmpty(opCode) && opCode.indexOf("+") != -1) {
			String[] codes = opCode.split("\\+");
			waybillPackageOperate.setOperateCode(codes[0]);
			waybillPackageOperate.setReasonCode(codes[1]);
			return;
		}
		waybillPackageOperate.setOperateCode(opCode);
	}
	
	private List<WaybillPackageOperateProperty> getUserDefList(String orderNo, String packageStatusId, String operateId, String createTime){
		List<WaybillPackageOperateProperty> list = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		map.put("courierCode", "000212");
		map.put("phone", "13512345678");
		map.put("phoneZone", "000212");
		map.put("extendAttach10", "aaa");
		map.put("extendAttach11", "13512345678");
		map.forEach((key,value) -> {
			WaybillPackageOperateProperty obj = new WaybillPackageOperateProperty();
			obj.setOperateId(operateId);
			obj.setCreateTm(createTime);
			obj.setAppointIntrNo(orderNo);
			obj.setPackageStatusId(packageStatusId);
			obj.setKey(key);
			obj.setValue(value);
			list.add(obj);
		});
		return list;
	}
}
