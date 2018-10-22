package com.sf.shiva.oms.psm.service.datafilter;

import com.sf.shiva.oms.psm.common.dto.CosClaimDto;
/**
 * 
 * 描述：理赔清洗过滤
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月28日      01369521         Create
 * ****************************************************************************
 * </pre>
 * @author 01369521
 * @since 1.0
 */
@FunctionalInterface
public interface ClaimDataFilterService {
	/**
	 * 过滤cos理赔信息
	 * @return
	 * @author 01369521
	 * @date 2017年12月13日
	 */
	public boolean filterClaims(CosClaimDto cosClaimDto);
}
