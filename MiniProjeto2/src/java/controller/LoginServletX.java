package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import database.VisualizaDao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import database.InsereDao;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/LoginServlet")
public class LoginServletX extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
        private VisualizaDao visualizaDao;
        private InsereDao insereDao;
        
        public void init(){
            insereDao = new InsereDao();
            visualizaDao = new VisualizaDao();
        }

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

            try {
                // get request parameters for userID and password
                String user = request.getParameter("user");
                String pwd = request.getParameter("pwd");
    
                
                
                Usuario usr = visualizaDao.selectuser(user, pwd);
         
                if(usr.getNome() != null){
                    HttpSession session = request.getSession();
                    session.setAttribute("user", usr.getNome());
                    //setting session to expiry in 30 mins
                    session.setMaxInactiveInterval(30*60);
                    Cookie userName = new Cookie("user", user);
                    userName.setMaxAge(30*60);
                    response.addCookie(userName);
                    String sessionID = session.getId();
                    usr.setSession(sessionID);
                    int r = insereDao.updateSession(usr);
                    response.sendRedirect("admin.jsp");
                }else{
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
                    PrintWriter out= response.getWriter();
                    out.println("<font color=red>Erro: Username ou senha incorreto!</font>");
                    rd.include(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginServletX.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginServletX.class.getName()).log(Level.SEVERE, null, ex);
            }

	}

}
