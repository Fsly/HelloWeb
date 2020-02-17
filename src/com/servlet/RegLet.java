package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.Service;

public class RegLet extends HttpServlet {

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
        String nickname = request.getParameter("nickname");
        //username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
        String password = request.getParameter("password");

        //新建服务对象
        Service service = new Service();

        //验证处理
        boolean reg = false;
        try {
            reg = service.register(account, nickname, password);
            if (reg) {
                System.out.println("reg success");
                //request.getSession().setAttribute("username", username);
            } else {
                System.out.println("reg fail");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //返回信息到客户端
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (reg) {
            out.print("true");
        } else {
            out.print("false");
        }

        out.flush();
        out.close();
    }

}