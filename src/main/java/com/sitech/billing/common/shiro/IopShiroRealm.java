package com.sitech.billing.common.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * 自定义realm类：用于授权和认证
 * @author sunzhen
 * @date 2018-11-30 16:23:12
 */
public class IopShiroRealm extends AuthorizingRealm {

    Map<String, String> users = new HashMap<String, String>();
    {
        users.put("sunzhen", "123456");
        users.put("admin", "admin");
        super.setName("iopShiroRealm");
    }

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String username = (String) principals.getPrimaryPrincipal();
        Set<String> roles = getRolesByName(username);
        Set<String> permission = getPermissionByName(username);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permission);
        return info;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1.通过subject获得用户名
        String userName = (String) token.getPrincipal();
        // 2.通过用户名获取凭证
        String password = getPassword(userName);

        if (password == null) {

            return null;
        }
        SimpleAuthenticationInfo authInfo = new SimpleAuthenticationInfo(userName, password, "iopShiroRealm");
        // authInfo.setCredentialsSalt(ByteSource.Util.bytes("cmd"));
        return authInfo;
    }


    private Set<String> getRolesByName(String username) {

        Set<String> sets = new HashSet<String>();
        sets.add("admin");
        sets.add("user");
        return sets;
    }

    private Set<String> getPermissionByName(String username) {

        Set<String> sets = new HashSet<String>();
        sets.add("user:delete");
        sets.add("user:update");
        sets.add("user:insert");
        return sets;
    }
    private String getPassword(String userName) {
        return users.get(userName);
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("112233", "cmd");
        System.out.println(md5Hash.toString());
    }

}
