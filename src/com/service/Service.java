package com.service;

import com.db.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Service {
    //注册
    public int register(String account, String nickname, String password) throws SQLException {
        DBManager dbManager = new DBManager();
        dbManager.initDB();
        ResultSet rs;

        //设置id
        rs = dbManager.executeQuery("SELECT MAX(id) FROM User;");
        int maxId, newId = 0;
        rs.next();
        maxId = rs.getInt(1);
        if (maxId != 0) newId = maxId + 1;

        //查重
        rs = dbManager.executeQuery("SELECT COUNT(*) FROM User WHERE Account='" + account + "';");
        rs.next();
        int repeat = rs.getInt(1);
        if (repeat > 0) return 2;

        //插入
        int ret = dbManager.executeUpdate(
                "insert into User values(" + newId + ",'"
                        + account + "','"
                        + nickname + "','"
                        + password + "')");
        dbManager.closeDB();
        return ret;
    }

    public ResultSet selectAll() {
        DBManager dbManager = new DBManager();
        dbManager.initDB();
        ResultSet rs = dbManager.executeQuery("select * from User");
        dbManager.closeDB();
        return rs;
    }

    public boolean login(String account, String password) throws SQLException {
        DBManager dbManager = new DBManager();
        dbManager.initDB();
        ResultSet rs;

        rs = dbManager.executeQuery("SELECT * FROM `User` WHERE Account='"
                + account + "'AND Password ='"
                + password + "';");
        rs.next();
        int repeat = rs.getInt(1);
        return repeat > 0;
    }

    public String userData(String account, String password) throws SQLException {
        DBManager dbManager = new DBManager();
        dbManager.initDB();
        ResultSet rs;

        rs = dbManager.executeQuery("select NickName from User WHERE Account='"
                + account + "'AND Password ='"
                + password + "';");
        rs.next();
        String nickName = rs.getString(1);

        return "" + nickName;
    }
}
