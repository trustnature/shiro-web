package com.tn.shiro.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

@Service("shiroService")
public class ShiroService {

    @RequiresRoles(value = {"admin"})
    public void testMethod(){
        Session session = SecurityUtils.getSubject().getSession();
        String val = (String)session.getAttribute("key");
        System.out.println("session val:" + val);
        System.out.println("test service anontation");
    }
}
