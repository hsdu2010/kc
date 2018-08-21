/*
* Copyright 2015-2020 SF-Express Tech Company. 
*/

package com.sf.shiva.oms.ht.web.controller.system;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sf.framework.domain.Result;
import com.sf.shiva.oms.ht.domain.SysUserInfo;
import com.sf.shiva.oms.ht.domain.vo.SideMenu;
import com.sf.shiva.oms.ht.service.system.SysResourceInfoService;
import com.sf.shiva.oms.ht.service.system.SysUserInfoService;
import com.sf.shiva.oms.ht.util.UserManager;

@Controller
@RequestMapping("system")
public class SystemManageController {

    @Autowired
    private SysResourceInfoService resourceService;
    
    @Autowired
    private SysUserInfoService sysUserInfoService;
    
    @RequestMapping(value = "loadSideMenus")
    @ResponseBody
    public Result<List<SideMenu>> loadSideMenus(Long id){
    	Result<List<SideMenu>> result = new Result<>();
    	List<SideMenu> sideMenuResult = new ArrayList<SideMenu>();
    	sideMenuResult = resourceService.getSubResources(id);
    	result.setObj(sideMenuResult);
    	return result;
    }
    
    
    @RequestMapping(value = "userInfo")
    @ResponseBody
    public Result<SysUserInfo> getUserByUserName(){

    	String userName = UserManager.getCurrentUserNo();
    	Result<SysUserInfo> result = new Result<SysUserInfo>();
    	SysUserInfo info = sysUserInfoService.selectUserByUserName(userName);
    	result.setSuccess(true);
    	result.setObj(info);
    	return result;
    }
    
    @RequestMapping(value = "index")
    public String toIndex(){
    	return "pages/index";
    }
    
}