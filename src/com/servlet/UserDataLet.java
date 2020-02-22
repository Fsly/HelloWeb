package com.servlet;

import com.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class UserDataLet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO 自动生成的方法存根
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("here is no mao bing too! But please don't use get method, thank you!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO 自动生成的方法存根
        //接受客户端信息
        request.setCharacterEncoding("UTF-8");
        String account = request.getParameter("account");
        //username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
        String password = request.getParameter("password");

        //新建服务对象
        Service service = new Service();

        //验证处理
        String reg="";
        try {
            reg = service.userData(account, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //返回信息到客户端
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.print("" + reg);

        out.flush();
        out.close();
    }
}