package com.sf.shiva.oms.ht.manager.extend;

import java.util.List;

import com.sf.shiva.oms.ht.domain.RpcManager;

public interface RcpManagerExtendManager {
    public List<RpcManager> selectByJarName(String jarName);
}
