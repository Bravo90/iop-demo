package com.sitech.billing.common.shiro;

import com.sitech.billing.system.rbac.model.Authc;
import com.sitech.billing.system.rbac.model.Role;
import com.sitech.billing.system.rbac.model.User;
import com.sitech.billing.system.rbac.service.AuthcService;
import com.sitech.billing.system.rbac.service.RoleService;
import com.sitech.billing.system.rbac.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.*;

/**
 * 自定义realm类：用于授权和认证
 *
 * @author sunzhen
 * @date 2018-11-30 16:23:12
 */
public class IopShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private AuthcService authcService;

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String username = (String) principals.getPrimaryPrincipal();
        User user = userService.getUserByUsername(username);

        List<Role> roleList = roleService.listRoleByUser(user);
        Set<String> roles = getRolesByName(roleList);

        List<Authc> authcList = authcService.listAuthcByRole(roleList);
        Set<String> permission = getPermissionByName(authcList);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permission);
        return info;
    }


    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1.通过subject获得用户名
        String userName = (String) token.getPrincipal();
        // 2.通过用户名获取凭证
        User user = userService.getUserByUsername(userName);
        if (user == null) {
            return null;
        }
        SimpleAuthenticationInfo authInfo =
                new SimpleAuthenticationInfo(userName, user.getPassword(), "iopShiroRealm");
        // authInfo.setCredentialsSalt(ByteSource.Util.bytes("cmd"));
        return authInfo;
    }

    private Set<String> getRolesByName(List<Role> roleList) {
        if (roleList != null && roleList.size() > 0) {
            Set<String> set = new HashSet<>();
            roleList.stream().forEach(role -> {
                set.add(role.getRoleName());
            });
            return set;
        } else {
            return null;
        }
    }

    private Set<String> getPermissionByName(List<Authc> authcList) {
        if (authcList.size() > 0) {
            Set<String> set = new HashSet<>();
            authcList.stream().forEach(authc -> {
                set.add(authc.getAuthcName());
            });
            return set;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456");
        System.out.println(md5Hash.toString());
    }

}
