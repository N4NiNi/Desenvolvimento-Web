package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;

import model.Pedido;
import model.Lanche;
import model.LanchePedido;

import database.VisualizaDao;

public class ServletAdmin extends HttpServlet {
    private VisualizaDao visualizaDao;
    
    public void init(){
        visualizaDao = new VisualizaDao();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        String operacao = request.getParameter("botao");
        
        try{
            if(operacao.equals("consultarPedidos")) consultarPedidos (request, response);
            else if(operacao.equals("detalhesPedido")) detalhesPedido(request, response);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    private void consultarPedidos(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        List<Pedido> listPedido = visualizaDao.selectAllPedidos();
        request.setAttribute("listaPedidos", listPedido);
        request.setAttribute("solicitar_login", "true");
        RequestDispatcher dispatcher = request.getRequestDispatcher("listarPedidos.jsp");
        dispatcher.forward(request,response);
    }
    

    private void detalhesPedido(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int idpedido = Integer.parseInt(request.getParameter("id_pedido"));
        
        List<LanchePedido> listLanchePedido = visualizaDao.selectLanchePedido(idpedido);
        request.setAttribute("lanches", listLanchePedido);
        
        Pedido pedido = visualizaDao.selectPedido(idpedido);
        request.setAttribute("pedido", pedido);
        request.setAttribute("solicitar_login", "true");
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("detalhesPedido.jsp");
        dispatcher.forward(request,response);
    }

}
