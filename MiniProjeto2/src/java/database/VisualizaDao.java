package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Pedido;
import model.LanchePedido;
import model.Lanche;

public class VisualizaDao {
    private String connection = "jdbc:mysql://localhost/dev_lanche";
    private String user="root", senha="";
    
    private String SELECT_ALL_Pedidos_SQL = "select * from pedido;";
    private String SELECT_Lanche_Pedido_ID_SQL = "select * from lanchepedido where Id_pedido = ?;";
    private String SELECT_Pedido_SQL = "select * from pedido where Id_pedido = ?;";
    private String SELECT_ALL_lanche_SQL = "select * from lanche;";

    protected Connection getConnection(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(connection,user,senha);
        }catch(SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return conn;
    }
    
    public List<Pedido> selectAllPedidos() throws SQLException{
        List<Pedido> pedido = new ArrayList<>();
        try{
            Connection conn = getConnection();
            
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_Pedidos_SQL);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                Pedido p=new Pedido();
                pedido.add(p);
                
                p.setId_pedido(rs.getInt("Id_pedido"));
                p.setValor_Total(rs.getFloat("Valor_Total"));
                p.setCpfcliente(rs.getString("cliente_CPF"));
                p.setDate(rs.getDate("Data_pedido"));
            }
        }catch(SQLException e){
            
            printSQLException(e);
            
        }
            return pedido;
    }

    public List<LanchePedido> selectLanchePedido(int id_pedido) throws SQLException{
        List<LanchePedido> lanchePedido = new ArrayList<>();
        try{
            Connection conn = getConnection();
            
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_Lanche_Pedido_ID_SQL);
            preparedStatement.setInt(1, id_pedido);
    
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                LanchePedido lp=new LanchePedido();
                lp.setIdpedido(rs.getInt("Id_pedido"));
                lp.setNomelanche(rs.getString("Nome_lanche"));
                lp.setObservacao(rs.getString("Observacao"));
                lp.setQuantidade(rs.getInt("Quantidade"));
                lanchePedido.add(lp);
            }
        }catch(SQLException e){
        
            printSQLException(e);
            
        }
        return lanchePedido;
    }

    public Pedido selectPedido(int id_pedido) throws SQLException{
        Pedido pedido = new Pedido();
        try{
            Connection conn = getConnection();
            
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_Pedido_SQL);
            preparedStatement.setInt(1, id_pedido);
    
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                pedido.setId_pedido(rs.getInt("Id_pedido"));
                pedido.setValor_Total(rs.getFloat("Valor_Total"));
                pedido.setCpfcliente(rs.getString("cliente_CPF"));
                pedido.setDate(rs.getDate("Data_pedido"));
                pedido.setEndereco(rs.getString("Endereco"));
            }
        }catch(SQLException e){
            printSQLException(e);
            
        }
        return pedido;
    }

    public List<Lanche> selectAllLanche() throws SQLException{
        List<Lanche> lanche = new ArrayList<>();
        try{
            Connection conn = getConnection();
            
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_lanche_SQL);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                Lanche l=new Lanche();
                lanche.add(l);
                
                l.setNomelanche(rs.getString("Nome_lanche"));
                l.setValor(rs.getFloat("Valor"));
                
            }
        }catch(SQLException e){
            
            printSQLException(e);
            
        }
            return lanche;
    }
    
    private void printSQLException(SQLException ex){
        for(Throwable e: ex){
               if(e instanceof SQLException){
                   e.printStackTrace(System.err);
                   System.err.println("SQLState" + ((SQLException)e).getSQLState());
                   System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                   System.err.println("Message: " + e.getMessage());
                   Throwable t = ex.getCause();
                   while(t != null){
                       System.out.println("Cause: " + t);
                       t = t.getCause();
                   }
               }
        }
    }
    
}
