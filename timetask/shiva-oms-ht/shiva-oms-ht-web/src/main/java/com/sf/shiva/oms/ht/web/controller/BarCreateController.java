package com.sf.shiva.oms.ht.web.controller;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.sf.framework.domain.Result;
import com.sf.shiva.oms.ht.common.utils.JsonUtil;
import com.sf.shiva.oms.ht.dto.BarDto;
import com.sf.shiva.oms.ht.service.api.CommonApiService;
import com.sf.shiva.oms.ht.service.util.RestRequestUtil;

/**
 * 
 * 描述：巴枪生成控制器
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID    DATE            PERSON            REASON
 *  1     2017年10月30日         835897           Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 835897
 * @version 1.0
 */
@RequestMapping("barCreate")
@Controller
public class BarCreateController {
    
    private static final Logger logger = LoggerFactory.getLogger(BarCreateController.class);
    
    @Autowired
    private CommonApiService commonApiService;

	@Value("${fvp.bar.generate.msg.header}")
	private String msgHeader;
	@Value("${fvp.bar.generate.msg.footer}")
	private String msgFooter;
	@Value("${fvp.bar.generate.url}")
	private String url;
	private String genFullFvpBarUrl;
	
	/**
	 * 简单生成巴枪
	 * @param barDto	前端请求对象
	 * @return	成功或者失败
	 */
    @RequestMapping("simpleCreate")
    @ResponseBody
    public Result<String> simpleCreate(BarDto barDto) {
        barDto.setSubbillPieceQty(1);
        String msg = buildMsg(barDto);
        Result<String> result = new Result<>();
        try {            
            result.setObj(commonApiService.invokerWs(msg, url));
            result.setErrorMessage("生成成功");
            result.setSuccess(true);
        }catch (Exception e) {
            result.setErrorMessage("生成巴枪失败，请稍后重试");
            result.setSuccess(false);
        }
    	return result;
    }
    
    
    /**
     * 全量报文生成巴枪数据
     * @param allJson 前端请求全量报文
     * @return	成功或者失败
     */
    @RequestMapping("allJsonCreate")
    @ResponseBody
    public String allJsonCreate(String allJson) {
    	String result = RestRequestUtil.getInstance().request(allJson, genFullFvpBarUrl);
    	String success = JsonUtil.getJsonValueByKey(result, "success");
    	String errorMsg = JsonUtil.getJsonValueByKey(result, "errorMsg");
        if ("true".equals(success)) {
            return "生成成功!";
        }
        if (StringUtils.isNotEmpty(errorMsg)) {
            return errorMsg;
        } else {
            return "生成巴枪接口调用失败，请稍后再试！";
        }
    }
    
    /**
     * 根据dto构造ws消息报文
     * @param dto 巴枪对象数据
     * @return 消息报文
     * @author 01369626
     * @date 2018年3月2日
     */
    private String buildMsg(BarDto dto) {
        StringBuilder str = new StringBuilder(msgHeader);
        Class<? extends BarDto> clazz = dto.getClass();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Field[] fields = clazz.getDeclaredFields();
        for(Field f : fields) {
            f.setAccessible(true);
            try {
                if(f.getType() == Date.class) {
                    str.append("<").append(f.getName()).append(">").append(sdf.format(f.get(dto))).append("</").append(f.getName()).append(">");
                }else {
                    if(f.get(dto) != null) {
                        str.append("<").append(f.getName()).append(">").append(f.get(dto)).append("</").append(f.getName()).append(">");
                    }
                }
            } catch (Exception e) {
                logger.error("BarCreateController buildMsg error.{}", e);
            }
        }
        str.append(msgFooter);
        return str.toString();
    }
    
}
