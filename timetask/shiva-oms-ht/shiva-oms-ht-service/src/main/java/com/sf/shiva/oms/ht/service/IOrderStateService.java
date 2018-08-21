package com.sf.shiva.oms.ht.service;

import com.sf.shiva.oms.ht.dto.CommonOrderStatusDto;

/**
 * 
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID    DATE            PERSON            REASON
 *  1     2017年12月6日         835897           Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 835897
 * @version 1.0
 */
public interface IOrderStateService {

    
    /**
     * 生成订单状态
     * @param dto
     * @author 835897-2017年12月6日
     */
    void createOrderState(CommonOrderStatusDto dto);
    
}
