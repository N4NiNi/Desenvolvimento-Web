/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Pedido;

/**
 *
 * @author Vinicius
 */
public class VisualizaDao {
    private String connection = "jdbc:mysql://localhost/dev_lanche";
    private String user="root", senha="";
    
    private String SELECT_ALL_Pedidos = "select * from pedido;";
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
            
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_Pedidos);
            
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