package com.tn.shiro.realms;

import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

public class SecondRealm extends AuthenticatingRealm{

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("second");
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
		    credentials = "2eca95852e7d19d6de93eaede15d4b541d28370e";
        }
        if ("user".equals(username)) {
            credentials = "208747ec7bc0d6e115a8b4b9e44c06b416d62152";
        }
		Object principal = username;
		String realmName = getName();

        ByteSource credentialsalt = ByteSource.Util.bytes(username);
		SimpleAuthenticationInfo info = null ;//new SimpleAuthenticationInfo(principal,credentials,realmName);
        info = new SimpleAuthenticationInfo(principal, credentials, credentialsalt, realmName);

		return info;
	}

	public static void main(String[] args) {
        String hashAlgorithmName = "SHA1";
        Object credentials = "123456";
        Object salt = ByteSource.Util.bytes("user");
        int hashIteratiosn = 12;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIteratiosn);
        System.out.println(result);
	}
  
}
