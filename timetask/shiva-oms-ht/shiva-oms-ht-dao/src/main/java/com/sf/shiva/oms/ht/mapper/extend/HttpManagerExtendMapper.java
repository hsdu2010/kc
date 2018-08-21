package com.sf.shiva.oms.ht.mapper.extend;

import java.util.List;

import com.sf.shiva.oms.ht.domain.HttpManager;

public interface HttpManagerExtendMapper {
	
	/**
	 * 根据接口名模糊查询记录
	 * @param remark
	 * @return
	 * @author 01369626
	 * @date 2017年12月1日
	 */
	public List<HttpManager> searchByRemark(String remark);
	
}
 