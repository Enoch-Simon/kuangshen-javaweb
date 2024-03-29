package com.kuang.filter;

import com.kuang.pojo.User;
import com.kuang.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain Chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        //过滤器：从Session中获取用户。
        User user = (User)request.getSession().getAttribute(Constants.USER_SESSION);

        if (user==null){//已经被移除或者注销了，或者为登陆。
            response.sendRedirect("/SMBMS/error.jsp");
        }else{
            Chain.doFilter(req,resp);
        }
    }

    @Override
    public void destroy() {

    }
}
