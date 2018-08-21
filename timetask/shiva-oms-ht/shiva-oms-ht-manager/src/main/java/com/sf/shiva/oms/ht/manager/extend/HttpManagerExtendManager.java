package com.sf.shiva.oms.ht.manager.extend;

import java.util.List;

import com.sf.shiva.oms.ht.domain.HttpManager;

public interface HttpManagerExtendManager {
	
	/**
	 * 模糊查询记录
	 * @param remark
	 * @return
	 * @author 01369626
	 * @date 2017年12月4日
	 */
	public List<HttpManager> searchByRemark(String remark);
}
