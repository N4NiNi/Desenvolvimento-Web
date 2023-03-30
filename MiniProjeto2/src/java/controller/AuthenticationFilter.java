/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package controller;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Vinicius
 */
public class AuthenticationFilter implements Filter {
    private ServletContext context;
    
    public void init(FilterConfig fConfig) throws ServletException{
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        String uri = req.getRequestURI();
        
        this.context.log("Requested Resource:"+uri);
        
        HttpSession session = req.getSession(false);
        System.out.println("TESTE:");
        System.out.println(session);
        System.out.println(uri);
        System.out.println("-----");
        if(session == null && (uri.endsWith("admin.jsp") || uri.endsWith("ServletAdmin"))){
            this.context.log("Unauthorized access request");
            res.sendRedirect("login.html");
        }else{
            chain.doFilter(request, response);
        }
    }
    
    public void destroy(){
        
    }
    
}
