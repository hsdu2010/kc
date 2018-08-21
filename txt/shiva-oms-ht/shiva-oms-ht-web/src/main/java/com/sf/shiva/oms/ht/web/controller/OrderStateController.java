package com.sf.shiva.oms.ht.web.controller;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sf.shiva.oms.ht.dto.CommonOrderStatusDto;
import com.sf.shiva.oms.ht.dto.OrderStateDto;
import com.sf.shiva.oms.ht.service.IOrderStateService;

/**
 * 
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID    DATE            PERSON            REASON
 *  1     2017年12月5日         835897           Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 835897
 * @version 1.0
 */
@RequestMapping("orderState")
@Controller
public class OrderStateController {

    private static final Logger logger = LoggerFactory.getLogger(OrderStateController.class);
    @Autowired
    private IOrderStateService orderStateService;
    /** 揽收成功状态码 */
    private static final String PICK_UP = "40003";
    
    @RequestMapping("createState")
    @ResponseBody
    public String createState(OrderStateDto dto) {
        // 根据来源系统订单号查询内部订单号
        try {
            if (StringUtils.isNotEmpty(dto.getOmsOrderNo())) {
                orderStateService.createOrderState(this.convertOrderStatusDto(dto));
                return "生成成功";
            }else {
                return "请输入来源系统订单号！";
            }
        } catch (Exception e) {
            logger.error("create state excute error! sysOrderNo:{}", dto.getSysOrderNo(), e);
            return "生成订单状态失败，请稍后再试！";
        }
    }
    
    /**
     * 转换订单状态生成DTO
     * @param orderNo
     * @param dto
     * @return
     * @author 835897-2017年12月6日
     */
    private CommonOrderStatusDto convertOrderStatusDto(OrderStateDto dto) {
        CommonOrderStatusDto stateDto = new CommonOrderStatusDto();
        stateDto.setOrderNO(dto.getOmsOrderNo());
        stateDto.setOriginOrderNO(dto.getSysOrderNo());
        stateDto.setWaybillNo(dto.getWaybillNo());
        stateDto.setOperTime(new Date().getTime());
        stateDto.setMsgType(dto.getStateCode());
        if (PICK_UP.equals(dto.getStateCode())) {
            stateDto.setBarOpCode("50");
        }
        stateDto.setDeptCode("755G");
        stateDto.setReceiverCode("680188");
        stateDto.setReceiverMobile("13925211101");
        stateDto.setReceiverName("山本一郎");
        return stateDto;
    }
}
