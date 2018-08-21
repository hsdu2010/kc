package com.sf.shiva.oms.ht.web.shiro.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sf.shiva.oms.ht.domain.SysUserInfo;

/**
 * Created by Michael on 5/13/16.
 */

public class MyCasRealm extends CasRealm {

    private static Logger log = LoggerFactory.getLogger(MyCasRealm.class);


/*    @Autowired
    SysUserInfo userService;
*/
    /**
     * 权限验证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //根据自己系统规则的需要编写获取授权信息，这里为了快速入门只获取了用户对应角色的资源url信息
       /* String userNo = (String) principalCollection.getPrimaryPrincipal();
        if (!userNo.isEmpty()) {
            List<String> pers = userService.getPermissionsByUserName(userNo);
            if (pers != null && !pers.isEmpty()) {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                for (String each : pers) {
                    //将权限资源添加到用户信息中  
                    info.addStringPermission(each);
                }
                Set<String> roles = new HashSet<String>();
                roles = userService.getCurrentUserRoles(userNo);
                info.setRoles(roles);
                return info;
            }
        }*/
        return null;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        CasToken casToken = (CasToken) authenticationToken;
        if (authenticationToken == null) {
            return null;
        } else {
            String ticket = (String) casToken.getCredentials();
            if (!StringUtils.hasText(ticket)) {
                return null;
            } else {
                TicketValidator ticketValidator = this.ensureTicketValidator();

                try {
                    Assertion e = ticketValidator.validate(ticket, this.getCasService());
                    AttributePrincipal casPrincipal = e.getPrincipal();
                    String userId = casPrincipal.getName();
                    // 需要验证用户是否在本环境中存在
                   /* Users user = userService.getUsersByUserNo(userId);
                    if (user == null) { // 如果没有此用户, 则添加一个用户
                        user = userService.addNewUser(userId);
                    } else if (user.getIsDeleted()) {
                        throw new UnknownAccountException("已通过域认证，用户被锁定，请联系管理员。");// 账号锁定
                    }

                    if (user == null) {
                        throw new UnknownAccountException("已通过域认证，创建用户失败。");
                    }*/
                    log.warn(" 用户【" + userId + "】登陆成功！");
                    Map attributes = casPrincipal.getAttributes();
                    casToken.setUserId(userId);

                    List principals = CollectionUtils.asList(new Object[]{userId, attributes});
                    SimplePrincipalCollection principalCollection = new SimplePrincipalCollection(principals, this.getName());
                    return new SimpleAuthenticationInfo(principalCollection, ticket);
                } catch (TicketValidationException e) {
                    throw new CasAuthenticationException("Unable to validate ticket [" + ticket + "]", e);
                }
            }
        }
    }
}
