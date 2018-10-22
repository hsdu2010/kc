package com.sf.shiva.oms.psm.dao;

import java.util.Date;

import com.sf.fvp.dto.FactRouteDto;
import com.sf.shiva.oms.psm.common.dto.CosClaimDto;
import com.sf.shiva.oms.psm.common.dto.deliverystatus.DeliveryStatusRequest;
import com.sf.shiva.oms.psm.common.entity.PackageSettleClaimsEntity;
import com.sf.shiva.oms.psm.common.entity.PackageStatusEntity;
import com.sf.shiva.oms.psm.common.entity.PackageStatusRecordEntity;

/**
 * 
 * 描述：包裹状态hbase操作接口
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年1月9日      01369626         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369626
 * @since 1.0
 */
public interface PackageStatusDao {

	/**
	 * 根据包裹号(获取RowKey)查询包裹状态表PackageStatusEntity
	 * 
	 * @param packageNo
	 *            包裹号
	 * @return 包裹状态表PackageStatusEntity
	 * @author 01369610
	 * @date 2018年1月9日
	 */
	public PackageStatusEntity getPackageStatusEntity(String packageNo);

	/**
	 * 保存包裹操作流水记录
	 * 
	 * @param entity
	 *            包裹操作流水记录
	 * @param status
	 *            包裹状态
	 * @param packageNo
	 *            包裹号
	 * @param operateTm
	 *            操作时间
	 * @author 01369626
	 * @date 2018年1月9日
	 */
	public void savePackageStatusRecord(PackageStatusRecordEntity entity, String status, String packageNo,
			Date operateTm);

	/**
	 * 保存包裹状态和对应流水操作：<br/>
	 * 1、计算Rowkey;<br/>
	 * 2、保存包裹状态和流水记录
	 * 
	 * @param entity
	 *            包裹状态
	 * @param recordEntity
	 *            流水记录
	 * @param bar
	 *            巴枪轨迹对象
	 * @param nextStatus
	 *            包裹状态
	 * @author 01369626
	 * @date 2018年1月9日
	 */
	public void saveFvpBarPackageStatusAndRecord(PackageStatusEntity entity, PackageStatusRecordEntity recordEntity,
			FactRouteDto bar, String nextStatus);

	/**
	 * SGS派件状态可翻转下，保存包裹翻转状态和对应流水操作
	 * 
	 * @param entity
	 *            包裹状态
	 * @param recordEntity
	 *            流水记录
	 * @param deliveryStatus
	 *            派件状态
	 * @param nextStatus
	 *            翻转状态
	 * @author 01369610
	 * @date 2018年1月9日
	 */
	public void saveSgsPkgStatusAndRecord(PackageStatusEntity entity, PackageStatusRecordEntity recordEntity,
			DeliveryStatusRequest deliveryStatus, String nextStatus);

	/**
	 * 保存理赔包裹状态及对应流水记录
	 * 
	 * @param entity
	 *            包裹状态
	 * @param recordEntity
	 *            流水记录
	 * @param cosClaimDto
	 *            理赔任务
	 * @param nextStatus
	 *            包裹状态
	 * @author 01369521
	 * @date 2018年1月9日
	 */
	public void saveCosClaimPackageStatus(PackageStatusEntity entity, PackageStatusRecordEntity recordEntity,
			CosClaimDto cosClaimDto, String nextStatus);
	/**
	 * 保存理赔表
	 * @param PackageSettleClaimsEntity 理赔表实体
	 * @param cosClaimDto 理赔Dto
	 * @author 01369521
	 * @date 2018年1月10日
	 */
	public void savePackageSettleClaim(CosClaimDto cosClaimDto, PackageSettleClaimsEntity packageSettleClaimsEntity);

}