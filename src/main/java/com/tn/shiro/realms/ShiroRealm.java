package com.tn.shiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm{

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("first");
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		System.out.println("从数据库中获取 username:" + username + " 所对应的用户信息。");
        String credentials = null;
        if("unknown".equals(username)) {
			throw new UnknownAccountException("用户不存在");
		}
		if("monster".equals(username)) {
			throw new LockedAccountException("用户被锁定");
		}
        if ("admin".equals(username)) {
		    credentials = "a08cf85fe005695dcaac4cfa24a09e76";
        }
        if ("user".equals(username)) {
            credentials = "693067a9f7dd0ed70b7a3c3fbf0ec4da";
        }
		Object principal = username;
		String realmName = getName();

        ByteSource credentialsalt = ByteSource.Util.bytes(username);
		SimpleAuthenticationInfo info = null ;//new SimpleAuthenticationInfo(principal,credentials,realmName);
        info = new SimpleAuthenticationInfo(principal, credentials, credentialsalt, realmName);

		return info;
	}

	public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        Object credentials = "123456";
        Object salt = ByteSource.Util.bytes("admin");
        int hashIteratiosn = 12;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIteratiosn);
        System.out.println(result);
	}

	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("authorization");
		Object principal = principals.getPrimaryPrincipal();
		Set<String> roles = new HashSet<String>();
		roles.add("user");
		if ("admin".equals(principal)) {
			roles.add("admin");
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		return info;
	}
}
