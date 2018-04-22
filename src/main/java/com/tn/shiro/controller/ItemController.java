package com.tn.shiro.controller;

import com.tn.shiro.service.ItemServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/item")
public class ItemController {

    //添加一个日志器
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemServiceI itemservice;

    //映射一个action
    @RequestMapping("/findAll")
    public String index() {
        logger.info("item findall");
        itemservice.findAll();
        return "index";
    }

    @RequestMapping("/save")
    public void login(@RequestParam("name") String name, @RequestParam("desc") String desc) {
        logger.info("item save");
        itemservice.save(name, desc);
    }
}