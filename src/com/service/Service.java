package com.service;

import com.db.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Service {
    public boolean register(String account, String nickname, String password) throws SQLException {
        DBManager dbManager = new DBManager();
        dbManager.initDB();
        ResultSet rs;

        rs = dbManager.executeQuery("SELECT MAX(id) FROM User;");
        int maxId = 0, newId = 0;
        rs.next();
        maxId = rs.getInt(1);
        if (maxId != 0) newId = maxId + 1;

        int ret = dbManager.executeUpdate(
                "insert into User values(" + newId + ","
                        + account + ","
                        + nickname + ","
                        + password + ")");
        dbManager.closeDB();
        return ret > 0;
    }

    public ResultSet selectAll() {
        DBManager dbManager = new DBManager();
        dbManager.initDB();
        ResultSet rs = dbManager.executeQuery("select * from User");
        dbManager.closeDB();
        return rs;
    }

    public boolean login(String username, String password) {
        return false;
    }
}
