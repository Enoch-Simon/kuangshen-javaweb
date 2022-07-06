package com.kuang.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetPropertiesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        
        // 第一个斜杠表示当前 web项目
        InputStream inputStream = context.getResourceAsStream("/WEB-INF/classes/db.properties");

        Properties properties = new Properties();
        properties.load(inputStream);
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        resp.getWriter().println(username + "-" + password);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
