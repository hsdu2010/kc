package com.sf.shiva.oms.psm.service.datafilter.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.psm.common.dto.CosClaimDto;
import com.sf.shiva.oms.psm.common.entity.PackageSettleClaimsEntity;
import com.sf.shiva.oms.psm.common.enumtype.StatusEnum;
import com.sf.shiva.oms.psm.dao.TableRowKeyDao;
import com.sf.shiva.oms.psm.dao.base.HBaseCommonDao;
import com.sf.shiva.oms.psm.service.datafilter.ClaimDataFilterService;
import com.sf.shiva.oms.psm.service.datafilter.PackageNoCheckService;

/**
 * 理赔过滤实现类 描述： 满足条件不被过滤：1.package_settle_claims表中不存在当前包裹。2.当前状态为结案状态（state=3）
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年1月8日      01369521         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369521
 * @since 1.0
 */
@Service
public class ClaimDataFilterServiceImpl implements ClaimDataFilterService {
    @Autowired
    private HBaseCommonDao hBaseCommonDaoImpl;
    @Autowired
    private TableRowKeyDao tableRowKeyDaoImpl;
    @Autowired
    private PackageNoCheckService packageNoCheckServiceImpl;

    /**
     * 过滤理赔请求 同时满足下列条件不过滤 1.在package_settle_claims中不存在相应数据 2.理赔状态为3（结案）
     * 
     * @param cosClaimDto
     *            cos理赔dto
     * @return true:过滤 false:不过滤
     * @see com.sf.shiva.oms.psm.service.datafilter.ClaimDataFilterService#filterClaims(com.sf.shiva.oms.psm.common.dto.CosClaimDto)
     * @author 01369521
     * @date 2017年12月14日
     */
    @Override
    public boolean filterClaims(CosClaimDto cosClaimDto) {
        PackageSettleClaimsEntity packageSettleClaimsEntity;
        if (null != cosClaimDto) {
            packageSettleClaimsEntity = hBaseCommonDaoImpl.get(tableRowKeyDaoImpl.getRowKey(cosClaimDto.getCarryId(), PackageSettleClaimsEntity.class), PackageSettleClaimsEntity.class);
            return null != packageSettleClaimsEntity || !StringUtils.equals(StatusEnum.STATUS3.getStatusCode(), cosClaimDto.getClaimStatus()) || !packageNoCheckServiceImpl.validPackageNo(cosClaimDto.getCarryId());
        }
        return true;
    }
}
