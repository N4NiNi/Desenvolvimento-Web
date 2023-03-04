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
            else if(operacao.equals("registrarLanche")) registrarlanche(request, response);
            else response.sendRedirect("erro.jsp");
        }catch(SQLException e){
            response.sendRedirect("erro.jsp");
        }
    }
    
    private void registrar (HttpServletRequest request, HttpServletResponse response){
        String[] nomeLanches = request.getParameterValues("lanches[]");
        int idpedido;
        if (nomeLanches != null) {
            for (int i = 0; i < nomeLanches.length; i++) {
                System.out.println("Valor " + (i+1) + ": " + nomeLanches[i]);
            }
        }
        
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
        for (int i = 0; i < qtd.length; i++) {
            System.out.println("Valor " + (i+1) + ": " + qtd[i]);
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
        for (int i = 0; i < obs.length; i++) {
            System.out.println("Valor " + (i+1) + ": " + obs[i]);
        }
        
        String cpfCliente = request.getParameter("cpf");
        System.out.println(cpfCliente);
        
        float precoTotal = Float.parseFloat(request.getParameter("precototal"));
        System.out.println(precoTotal);
        
        String endereco = request.getParameter("endereco");
        System.out.println(endereco);
        
        
        Pedido ped = new Pedido();
        Lanche lanche = new Lanche(); 
        
        
        ped.setCpfcliente(cpfCliente);
        ped.setValor_Total(precoTotal);
        ped.setEndereco(endereco);       
        
        try{
            idpedido = criaDao.Criapedido(ped);
            
            for (int i = 0; i < nomeLanches.length; i++) {
                LanchePedido lancheped = new LanchePedido();
                lancheped.setIdpedido(idpedido);
                lancheped.setNomelanche(nomeLanches[i]);
                lancheped.setObservacao(obs[i]);
                lancheped.setQuantidade(qtd[i]);
                
                criaDao.CriaLanchePedido(lancheped);
                
            }
            
            response.sendRedirect("sucesso.html");
            
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
    
    private void registrarlanche(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        List<Lanche> listLanche = lancheDao.selectAllLanche();
        request.setAttribute("lanches", listLanche);
        RequestDispatcher dispatcher = request.getRequestDispatcher("criarPedido.jsp");
        dispatcher.forward(request,response);
    }

}
