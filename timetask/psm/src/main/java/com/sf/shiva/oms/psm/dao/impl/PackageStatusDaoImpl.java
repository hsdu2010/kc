package com.sf.shiva.oms.psm.dao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sf.fvp.dto.FactRouteDto;
import com.sf.shiva.oms.psm.common.dto.CosClaimDto;
import com.sf.shiva.oms.psm.common.dto.deliverystatus.DeliveryStatusRequest;
import com.sf.shiva.oms.psm.common.entity.PackageSettleClaimsEntity;
import com.sf.shiva.oms.psm.common.entity.PackageStatusEntity;
import com.sf.shiva.oms.psm.common.entity.PackageStatusRecordEntity;
import com.sf.shiva.oms.psm.common.utils.DateFormatUtil;
import com.sf.shiva.oms.psm.dao.PackageStatusDao;
import com.sf.shiva.oms.psm.dao.TableRowKeyDao;
import com.sf.shiva.oms.psm.dao.base.HBaseCommonDao;

/**
 * 
 * 描述：包裹状态hbase操作实现
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
@Repository
public class PackageStatusDaoImpl implements PackageStatusDao {
    
	@Autowired
	private TableRowKeyDao tableRowKeyDaoImpl;
	@Autowired
	private HBaseCommonDao hBaseCommonDaoImpl;

	@Override
	public PackageStatusEntity getPackageStatusEntity(String packageNo) {
		String rowKey = tableRowKeyDaoImpl.getRowKey(packageNo, PackageStatusEntity.class);
		return hBaseCommonDaoImpl.get(rowKey, PackageStatusEntity.class);
	}

	@Override
	public void savePackageStatusRecord(PackageStatusRecordEntity entity, String status, String packageNo, Date operateTm) {
		String rowKey = tableRowKeyDaoImpl.getRowKey(packageNo, status, operateTm, PackageStatusRecordEntity.class);
		hBaseCommonDaoImpl.saveAny(rowKey, entity);
	}

	@Override
	public void saveFvpBarPackageStatusAndRecord(PackageStatusEntity entity, PackageStatusRecordEntity recordEntity, FactRouteDto bar, String nextStatus) {
		String packageStatusRowKey = tableRowKeyDaoImpl.getRowKey(bar.getMainWaybillNo(), PackageStatusEntity.class);
		String packageRecordRowKey = tableRowKeyDaoImpl.getRowKey(bar.getMainWaybillNo(), nextStatus, bar.getBarScanTm(), PackageStatusRecordEntity.class);
		hBaseCommonDaoImpl.saveAny(packageRecordRowKey, recordEntity);
		hBaseCommonDaoImpl.saveAny(packageStatusRowKey, entity);
	}

	@Override
	public void saveSgsPkgStatusAndRecord(PackageStatusEntity entity, PackageStatusRecordEntity recordEntity, DeliveryStatusRequest deliveryStatus, String nextStatus) {
		String packageNo = deliveryStatus.getWaybill().getMainNo();// 包裹号
		Date operTm = new Date(deliveryStatus.getTimestamp());// 操作时间
		String packageStatusRowKey = tableRowKeyDaoImpl.getRowKey(packageNo, PackageStatusEntity.class);// 包裹状态表rowkey
		String packageRecordRowKey = tableRowKeyDaoImpl.getRowKey(packageNo, nextStatus, operTm, PackageStatusRecordEntity.class);// 包裹状态记录表rowkey
		hBaseCommonDaoImpl.saveAny(packageRecordRowKey, recordEntity);
		hBaseCommonDaoImpl.saveAny(packageStatusRowKey, entity);
	}

	@Override
	public void saveCosClaimPackageStatus(PackageStatusEntity entity, PackageStatusRecordEntity recordEntity, CosClaimDto cosClaimDto, String nextStatus) {
		String packageStatusRecordRowkey = tableRowKeyDaoImpl.getRowKey(cosClaimDto.getCarryId(), nextStatus,
				DateFormatUtil.format(cosClaimDto.getClaimProcessTimeString(), DateFormatUtil.D_YYYYMMDD_SPLIT, DateFormatUtil.T_HHMMSS_SPLIT, true), PackageStatusRecordEntity.class);
		String packageStatusRowkey = tableRowKeyDaoImpl.getRowKey(cosClaimDto.getCarryId(), PackageStatusEntity.class);
		hBaseCommonDaoImpl.saveAny(packageStatusRecordRowkey, recordEntity);
		hBaseCommonDaoImpl.saveAny(packageStatusRowkey, entity);
	}

	@Override
	public void savePackageSettleClaim(CosClaimDto cosClaimDto, PackageSettleClaimsEntity packageSettleClaimsEntity) {
		String packageSettleClaimsRowkey = tableRowKeyDaoImpl.getRowKey(cosClaimDto.getCarryId(), PackageSettleClaimsEntity.class);// 获取理赔信息存入hbase包裹状态表的rowKey
		hBaseCommonDaoImpl.saveAny(packageSettleClaimsRowkey, packageSettleClaimsEntity);
	}

}
