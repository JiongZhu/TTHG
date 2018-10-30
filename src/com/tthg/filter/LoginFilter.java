package com.tthg.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tthg.entity.User;

public class LoginFilter extends HttpServlet implements Filter {

	@Override
	public void doFilter(ServletRequest sRequest, ServletResponse sResponse,
			FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sRequest;      
        HttpServletResponse response = (HttpServletResponse) sResponse;      
        HttpSession session = request.getSession();      
        String url=request.getServletPath();  
        String contextPath=request.getContextPath(); 
        if(url.equals("")) url+="/";
        if((url.startsWith("/Background")&&!url.startsWith("/Background/login")&&
        		!url.contains("login")&&!url.contains("validateCode")&&
        		!url.endsWith(".css")&&!url.endsWith(".png")
        		&&!url.endsWith(".jpg")&&!url.endsWith(".js"))){//若访问后台资源 过滤到login  
           	 User user=(User)session.getAttribute("loginUser");  
                if(user==null){//转入后台登陆页面  
                     response.sendRedirect(contextPath+"/Background/login.jsp");
                     return;
                } 
         }
          filterChain.doFilter(sRequest, sResponse); 
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
