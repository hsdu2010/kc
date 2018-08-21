package com.sf.shiva.oms.ht.web.shiro.realm;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.ht.domain.SysUserInfo;
import com.sf.shiva.oms.ht.service.system.impl.SysUserInfoServiceImpl;
import com.sf.shiva.oms.ht.web.shiro.LdapAuthentication;

/**
 * Created by Michael on 5/13/16.
 */
@Service
public class UserRealm extends AuthorizingRealm {
    
    private static Logger log = LoggerFactory.getLogger(UserRealm.class);
    
    @Autowired
    SysUserInfoServiceImpl userService;

  /*  @Autowired
    LdapAuthentication ldapAuthentication;
    */
    /**
     * 权限验证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

//        if(!SecurityUtils.getSubject().isAuthenticated()){
//            SecurityUtils.getSubject().logout();
//        }
//        return null;
        
      //根据自己系统规则的需要编写获取授权信息，这里为了快速入门只获取了用户对应角色的资源url信息  
    	/*Users user = (Users) principalCollection.getPrimaryPrincipal(); 
        if (user != null) {  
            List<String> pers = userService.getPermissionsByUserName(user.getUserNo());  
            if (pers != null && !pers.isEmpty()) {  
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
                for (String each : pers) {  
                    //将权限资源添加到用户信息中  
                    info.addStringPermission(each);  
                }  
                return info;  
            }  
        }  */
        return null;  
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        log.info("登录认证");
        // 通过表单接收的用户名
        String username = token.getUsername();
        String password = token.getPassword().toString();
        if (token.getPassword() != null) {
            password = new String(token.getPassword());
        }
        SysUserInfo user = userService.selectUserByUserName(username);
        if (user != null) {
            //是否通过 域认证
            //boolean flag = ldapAuthentication.authentication(username, password);
        	boolean flag = username.equals(user.getSysUsername())&&password.equals(user.getSysPwd());/**/
        	if (flag) {
                // 需要验证用户是否在本环境中存在
               //SysUserInfo user = userService.selectUserByUserName(username);
               /*  if (user == null) { // 如果没有此用户, 则添加一个用户
                    user = userService.addNewUser(username);
                } else if (user.getIsDeleted()) {
                    throw new UnknownAccountException("已通过域认证，用户被锁定，请联系管理员。");// 账号锁定
                }
                if (user == null) {
                    throw new UnknownAccountException("已通过域认证，创建用户失败。");
                }*/
                //如果身份认证验证成功，返回一个AuthenticationInfo实现；  
                SimpleAuthenticationInfo info = null;
                try {
                    info = new SimpleAuthenticationInfo(user, password.toCharArray(), user.getSysUsername());
                } catch (Exception e) {
                	log.error(e.getMessage(), e);
                }
                return info;
            } else {
                log.info(username+"用户名或密码错误。");
                throw new UnknownAccountException("用户名或密码错误");
            }
        }else{
        	 log.info(username+"用户名不存在");
             throw new UnknownAccountException("用户名或密码错误");
        }
    }
    
    public  String getResult(String inputStr) {
		System.out.println("=======加密前的数据:" + inputStr);
		BigInteger bigInteger = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] inputData = inputStr.getBytes();
			md.update(inputData);
			bigInteger = new BigInteger(md.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("MD5加密后:" + bigInteger.toString(16));
		return bigInteger.toString(16);
	}
}