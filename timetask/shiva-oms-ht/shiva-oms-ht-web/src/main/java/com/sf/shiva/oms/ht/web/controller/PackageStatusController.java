package com.sf.shiva.oms.ht.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sf.framework.domain.Result;
import com.sf.shiva.oms.ht.service.PackageStatusService;

/**
 * 
 * 描述：包裹状态控制器
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
@Controller
@RequestMapping("pkgStatus")
public class PackageStatusController {
	
	@Autowired
	private PackageStatusService packageStatusServiceImpl;
	
	@RequestMapping("sendPkgStatus")
	@ResponseBody
	public Result<String> sendPkgStatus(String packageNo, String zoneCode, String packageStatus, String opCode, String orderNo, String operateEmpCode){
		Result<String> result = new Result<>();
		try {
			if(packageStatusServiceImpl.sendPackageStatusMsg(packageNo, zoneCode, packageStatus, opCode, orderNo, operateEmpCode)) {
				result.setSuccess(true);
			}else {
				result.setSuccess(false);
				result.setErrorMessage("发送失败");
			}
		}catch (Exception e) {
			result.setErrorMessage("写入Kafka异常");
			result.setSuccess(false);
		}
		return result;
	}
	
}
