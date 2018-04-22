package com.tn.shiro.factory;

import java.util.LinkedHashMap;

public class filterChainDefinitionMapBuilder {

    public LinkedHashMap<String,String> buildFiterChainDefinitionMap(){
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("/login.jsp", "anon");
        map.put("/static/**","anon");
        map.put("/shiro/login", "anon");
        map.put("/shiro/logout","logout");
        map.put("/user.jsp","authc,roles[user]");
        map.put("/admin.jsp", "authc,roles[admin]");
        map.put("/**", "authc");
        return map;
    }
}
