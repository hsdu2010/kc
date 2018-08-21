package com.sf.shiva.oms.ht.manager.extend.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





import com.sf.shiva.oms.ht.domain.RpcManager;
import com.sf.shiva.oms.ht.domain.RpcManagerExample;
import com.sf.shiva.oms.ht.manager.RpcManagerManager;
import com.sf.shiva.oms.ht.manager.extend.RcpManagerExtendManager;


@Service
public class RpcManagerExtendManager implements RcpManagerExtendManager{
    
    @Autowired
    private RpcManagerManager rpcManagerManager;
    @Override
    public List<RpcManager> selectByJarName(String jarName) {
        RpcManagerExample example = new RpcManagerExample();
        example.createCriteria().andJarNameEqualTo(jarName);
        return rpcManagerManager.selectByExample(example);
    }

}
