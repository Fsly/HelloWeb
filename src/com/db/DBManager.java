package com.db;

import java.io.PrintWriter;
import java.sql.*;

public class DBManager {

    // 数据库连接常量
    public static final String USER = "test";
    public static final String PASS = "123456";
    public static final String URL = "jdbc:mysql://39.97.188.42:3306/webtest" +
            "?serverTimezone=UTC" +
            "&useUnicode=true" +
            "&characterEncoding=utf8";
//                    "&useJDBCCompliantTimezoneShift=true" +
//                    "&useLegacyDatetimeCode=false"

    // 静态成员，支持单态模式
    private static DBManager per = null;
    private Connection con = null;
    private Statement stmt = null;

    // 单态模式-懒汉模式
    public DBManager() {
    }

    public static DBManager createInstance() {
        if (per == null) {
            per = new DBManager();
            per.initDB();
        }
        return per;
    }

    // 加载驱动
    public void initDB() {
        try {
            // 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 连接数据库
            con = DriverManager.getConnection(URL, USER, PASS);

            //获取句柄+对象
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 关闭数据库 关闭对象，释放句柄
    public void closeDB() {
        System.out.println("Close connection to database..");
        try {
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Close connection successful");
    }

    // 查询
    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // 增添/删除/修改
    public int executeUpdate(String sql) {
        int ret = 0;
        try {
            ret = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
}