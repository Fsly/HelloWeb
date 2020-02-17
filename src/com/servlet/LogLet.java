package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.Service;

public class LogLet extends HttpServlet {

    private static final long serialVersionUID = 9036889586892331384L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://39.97.188.42:3306/webtest" +
                    "?serverTimezone=UTC";
//                    "&useUnicode=true" +
//                    "&useJDBCCompliantTimezoneShift=true" +
//                    "&useLegacyDatetimeCode=false"
            String user = "test";
            String pw = "123456";

            // 连接数据库
            con = DriverManager.getConnection(url, user, pw);

            //获取句柄+对象
            stmt = con.createStatement();

            rs = stmt.executeQuery("select * from User");

            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            while (rs.next()) {
                out.println(rs.getInt(1) + "," +
                        rs.getString(2) + "," + rs.getString(3));
            }

            out.flush();
            out.close();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    assert rs != null;
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO 自动生成的方法存根

    }

}