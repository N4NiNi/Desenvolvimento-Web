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
        System.out.println(operacao);
        
        try{
            if(operacao.equals("registrarPedido")) registrarPedido(request,response);
            else if(operacao.equals("consultarPedidos")) consultarPedidos (request, response);
            else if(operacao.equals("registrarLanche")) registrarlanche(request, response);
            else if(operacao.equals("detalhesPedido")) detalhesPedido(request, response);
        }catch(SQLException e){
            response.sendRedirect("erro.jsp");
        }
    }
    
    private void registrarPedido (HttpServletRequest request, HttpServletResponse response){
        String[] nomeLanches = request.getParameterValues("lanches[]");
        
        String[] qtd_lanches = request.getParameterValues("qtd_lanches[]");
        int[] qtd = new int[nomeLanches.length];
        int cont = 0;
        if (qtd_lanches != null) {
            for (int i = 0; i < qtd_lanches.length; i++) {
                if(!qtd_lanches[i].equals("0")){
                    qtd[cont] = Integer.parseInt(qtd_lanches[i]);
                    cont ++;
                }
            }
        }
      
        String[] observacoes = request.getParameterValues("observacoes[]");
        String[] obs = new String[nomeLanches.length];
        cont = 0;
        if (observacoes != null) {
            for (int i = 0; i < observacoes.length; i++) {
                if(!observacoes[i].equals("/*")){
                    obs[cont] = observacoes[i];
                    cont++;
                }
             }
        }
        
        String cpfCliente = request.getParameter("cpf");
        String endereco = request.getParameter("endereco");
        float precoTotal = Float.parseFloat(request.getParameter("precototal"));
        
        Pedido ped = new Pedido();
        ped.setCpfcliente(cpfCliente);
        ped.setValor_Total(precoTotal);
        ped.setEndereco(endereco);       
        
        try{
            int idpedido = criaDao.inserePedido(ped);
            
            for (int i = 0; i < nomeLanches.length; i++) {
                LanchePedido lancheped = new LanchePedido();
                lancheped.setIdpedido(idpedido);
                lancheped.setNomelanche(nomeLanches[i]);
                lancheped.setObservacao(obs[i]);
                lancheped.setQuantidade(qtd[i]);
                
                criaDao.insereLanchePedido(lancheped);
            }
            response.sendRedirect("sucesso.html");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private void consultarPedidos(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        List<Pedido> listPedido = pedidoDao.selectAllPedidos();
        request.setAttribute("listaPedidos", listPedido);
       
        RequestDispatcher dispatcher = request.getRequestDispatcher("listarPedidos.jsp");
        dispatcher.forward(request,response);
    }
    
    private void registrarlanche(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        List<Lanche> listLanche = lancheDao.selectAllLanche();
        request.setAttribute("lanches", listLanche);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("criarPedido.jsp");
        dispatcher.forward(request,response);
    }

    private void detalhesPedido(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int idpedido = Integer.parseInt(request.getParameter("id_pedido"));
        
        List<LanchePedido> listLanchePedido = pedidoDao.selectLanchePedido(idpedido);
        request.setAttribute("lanches", listLanchePedido);
        
        Pedido pedido = pedidoDao.selectPedido(idpedido);
        request.setAttribute("pedido", pedido);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("detalhesPedido.jsp");
        dispatcher.forward(request,response);
    }

}
