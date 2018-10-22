package com.sf.shiva.oms.psm.service.datafilter;

import com.sf.fvp.dto.FactRouteDto;

/**
 * 
 * 描述：fvp对象过滤接口
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月13日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
@FunctionalInterface
public interface FvpBarDataFilterService {
	
	/**
	 * 对巴枪对象进行过滤
	 * @param factRouteDto
	 * @return true--数据合法，可以进行下一步操作;false--数据校验不通过，过滤数据
	 * @author 01369626
	 * @throws Exception 
	 * @date 2017年12月13日
	 */
	public boolean filterBar(FactRouteDto factRouteDto);
	
}
