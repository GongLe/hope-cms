package org.lework.core.service.account;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * shiro授权类 抽象出授权类,使得验证{@link JdbcAuthenticationRealm}与授权解耦
 *
 * @author Gongle
 */
public abstract class JdbcAuthorizationRealm extends AuthorizingRealm {
    private static Logger logger = LoggerFactory.getLogger(JdbcAuthorizationRealm.class);
    /**
     * 实体中权限值属性名
     */
    public final static String PROPERTY_PERMISSION_NAME = "permission";

    /**
     * 实体中角色值属性名
     */
    public final static String PROPERTY_ROLE_NAME = "code";

    /**
     * shiro权限值
     */
    private List<String> defaultPermissions;

    /**
     * shiro角色值
     */
    private List<String> defaultRoles;


    /**
     * 授权回调方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //TODO 用户授权
        return info;
    }

    public List<String> getDefaultRoles() {
        return defaultRoles;
    }

    public void setDefaultRoles(List<String> defaultRoles) {
        this.defaultRoles = defaultRoles;
    }

    public List<String> getDefaultPermissions() {
        return defaultPermissions;
    }

    public void setDefaultPermissions(List<String> defaultPermissions) {
        this.defaultPermissions = defaultPermissions;
    }
}
