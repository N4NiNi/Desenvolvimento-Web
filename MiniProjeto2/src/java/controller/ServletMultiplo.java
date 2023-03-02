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
//
import database.VisualizaDao;
import database.CriaDao;
import database.LancheDao;

public class ServletMultiplo extends HttpServlet {

    private VisualizaDao pedidoDao;
    private CriaDao criaDao;
    private LancheDao lancheDao;
    
    public void init(){
        lancheDao = new LancheDao();
        pedidoDao = new VisualizaDao();
        criaDao = new CriaDao();
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        String operacao = request.getParameter("botao");
        
        try{
            if(operacao.equals("registrar")) registrar(request,response);
            else if(operacao.equals("consultar")) consultar (request, response);
            else response.sendRedirect("erro.jsp");
        }catch(SQLException e){
            response.sendRedirect("erro.jsp");
        }
    }
    
    private void registrar (HttpServletRequest request, HttpServletResponse response){
        String nomeLanche = request.getParameter("nomelanche");
        String cpfCliente = request.getParameter("cpfcliente");
        float precoTotal = Float.parseFloat(request.getParameter("precototal"));
        
        Pedido ped = new Pedido();
        Lanche lanche = new Lanche(); 
        
        ped.setCpfcliente(cpfCliente);
        ped.setValor_Total(precoTotal);
        lanche.setNomelanche(nomeLanche);
       
        
        try{
            criaDao.Criapedido(ped);
            response.sendRedirect("criapedido.jsp");
            
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    private void consultar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        List<Pedido> listPedido = pedidoDao.selectAllPedidos();
        request.setAttribute("listaPedidos", listPedido);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listarPedidos.jsp");
        dispatcher.forward(request,response);
    }
    
    private void consultarlanche(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
       List<Lanche> listLanche = lancheDao.selectAllLanche();
        request.setAttribute("criapedidos", listLanche);
        RequestDispatcher dispatcher = request.getRequestDispatcher("criapedidos.jsp");
        dispatcher.forward(request,response);
    }

}
