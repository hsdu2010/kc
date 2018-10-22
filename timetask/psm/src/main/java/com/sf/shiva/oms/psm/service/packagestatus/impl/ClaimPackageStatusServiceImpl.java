package com.sf.shiva.oms.psm.service.packagestatus.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.psm.common.dto.CosClaimDto;
import com.sf.shiva.oms.psm.common.entity.PackageStatusEntity;
import com.sf.shiva.oms.psm.common.entity.PackageStatusRecordEntity;
import com.sf.shiva.oms.psm.dao.impl.PackageStatusDaoImpl;
import com.sf.shiva.oms.psm.service.eventmanager.EventManager;
import com.sf.shiva.oms.psm.service.packagestatus.AbstractPackageStatusService;
import com.sf.shiva.oms.psm.service.statusmachine.StatusMachineManager;

/**
 * 
 * 描述：理赔包裹状态接口实现
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月10日      01159741         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01159741
 * @since 1.0
 */
@Service
public class ClaimPackageStatusServiceImpl extends AbstractPackageStatusService<CosClaimDto> {

	@Autowired
	private EventManager<CosClaimDto> claimEventManagerImpl;
	@Autowired
	private StatusMachineManager statusMachineManagerImpl;
	@Autowired
	private PackageStatusDaoImpl packageStatusDaoImpl;

	@Override
	protected String queryEventCode(CosClaimDto obj) {
		return claimEventManagerImpl.queryEventCode(obj);
	}

	/**
	 * 查询包裹状态对象并和理赔申请，当前状态一起传输给状态机，
	 * 
	 * @param cosClaimDto
	 *            理赔请求
	 * @param eventCode
	 *            事件代码
	 * @return
	 * @see com.sf.shiva.oms.psm.service.packagestatus.AbstractPackageStatusService#cal(java.lang.Object,
	 *      java.lang.String)
	 * @author 01369521
	 * @date 2018年1月8日
	 */
	@Override
	protected PackageStatusRecordEntity cal(CosClaimDto cosClaimDto, String eventCode) {
		PackageStatusEntity packageStatusEntity = packageStatusDaoImpl.getPackageStatusEntity(cosClaimDto.getCarryId());// 根据包号获取包裹状态表实体
		return statusMachineManagerImpl.excute(eventCode, getCurrentStatus(packageStatusEntity), fillObjs(cosClaimDto, packageStatusEntity));
	}

	/**
	 * 从数据库获取实体的当前状态
	 * 
	 * @param packageStatusEntity
	 *            包裹状态实体
	 * @return 当前状态
	 * @author 01369521
	 * @date 2018年1月9日
	 */
	private static String getCurrentStatus(PackageStatusEntity packageStatusEntity) {
		return packageStatusEntity != null ? packageStatusEntity.getPackageStatus() : null;
	}

	/**
	 * 组建传输参数对象
	 * 
	 * @param cosClaimDto
	 *            巴枪对象
	 * @param packageStatusEntity
	 *            包裹状态对象
	 * @return objs参数对象
	 * @author 01369521
	 * @date 2018年1月8日
	 */
	private static List<Object> fillObjs(CosClaimDto cosClaimDto, PackageStatusEntity packageStatusEntity) {
		List<Object> objs = new ArrayList<>(); // 需要用到的对象信息
		objs.add(cosClaimDto);
		objs.add(packageStatusEntity);
		return objs;
	}
}
