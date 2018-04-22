package com.tn.shiro.dao.impl;

import com.tn.shiro.dao.GenericDAO;
import com.tn.shiro.dao.ItemDAO;
import com.tn.shiro.model.Item;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository(value = "itemDao")
public class ItemDAOImpl extends GenericDAOImpl<Item, Long>
        implements ItemDAO {
    private static final Logger log = LogManager.getLogger(ItemDAOImpl.class);

    public ItemDAOImpl() {
        super(Item.class);
    }

    @Override
    public List<Item> findAll(boolean withBids) {
        return null;
    }

    @Override
    public List<Item> findByName(String name, boolean fuzzy) {
        return null;
    }


    /**
     * 批量保存
     *
     * @param tableName
     * @param fields
     * @param datas
     */
    @Override
    public void SaveBatch(final String tableName, final String[] fields, final List<Map> datas) {
        Session sess = this.getCurrentSession();
        StringBuilder fieldStr = new StringBuilder().append(" (id,");
        StringBuilder valueStr = new StringBuilder().append(" (seq_pt_msg.nextval,");
        for (int i = 0; i < fields.length; i++) {
            if (i != fields.length - 1) {
                fieldStr.append(fields[i]).append(",");
                valueStr.append("?").append(",");
            } else {
                fieldStr.append(fields[i]);
                valueStr.append("?");
            }
        }
        fieldStr.append(") ");
        valueStr.append(") ");
        final String sql = "insert into " + tableName + fieldStr.toString() + " values" + valueStr.toString();
        sess.doWork(new Work() {
            public void execute(Connection arg0) throws SQLException {//需要注意的是，不需要调用close()方法关闭这个连接
                PreparedStatement ps = arg0.prepareStatement(sql);
                for (int i = 0; i < datas.size(); i++) {
                    Map mapT = datas.get(i);
                    for (int j = 0; j < fields.length; j++) {
                        ps.setObject(j + 1, mapT.get(fields[j]));
                    }
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        });
    }
}