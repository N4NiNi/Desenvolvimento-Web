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

import database.VisualizaDao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vinicius
 */
public class AuthenticationFilter implements Filter {
    private ServletContext context;
    private VisualizaDao visualizaDao;
    
    public void init(FilterConfig fConfig) throws ServletException{
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
        visualizaDao = new VisualizaDao();
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        
        try {
            String sess = "0";
            String sess_id = "0";
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            
            String uri = req.getRequestURI();
            
            this.context.log("Requested Resource:"+uri);
            
            HttpSession session = req.getSession(false);
            if(session != null){
                String sec = (String) req.getSession().getAttribute("username");

                sess = visualizaDao.selectsession(sec);
                System.out.println("TESTE->>>");
                System.out.println(sess);
                sess_id = req.getSession().getId();
            }
            
            if((session == null && (uri.endsWith("admin.jsp") || uri.endsWith("ServletAdmin"))) || !(sess.equals(sess_id))){
                
                this.context.log("Unauthorized access request");
                res.sendRedirect("login.html");
            }else{
                
                chain.doFilter(request, response);
                
                //System.out.println(req.getSession().getAttribute("username"));
                //System.out.println(req.getSession().getId());
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void destroy(){
        
    }
    
}
