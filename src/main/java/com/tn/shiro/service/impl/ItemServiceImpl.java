package com.tn.shiro.service.impl;

import com.tn.shiro.dao.ItemDAO;
import com.tn.shiro.model.Item;
import com.tn.shiro.service.ItemServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("itemServiceImpl")
public class ItemServiceImpl  implements ItemServiceI {
    private ItemDAO itemDAO;

    @Override
    public void findAll() {
       List<Item> lm =  itemDAO.findAll("from item ");
        for (Item item : lm) {
            System.out.println(item.toString());
        }
    }

    @Override
    @Transactional
    public void save(String name, String desc) {
        Item item = new Item();
        item.setId(1L);
        item.setName(name);
        item.setDescription(desc);
        itemDAO.save(item);
    }

    public ItemDAO getBaseDao() {
        return itemDAO;
    }

    @Resource(name = "itemDao")
    public void setBaseDao(ItemDAO baseDao) {
        this.itemDAO = baseDao;
    }
}
