/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author Vinicius
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Pedido;
import model.LanchePedido;

public class CriaDao {
    public int Criapedido(Pedido pedido) throws ClassNotFoundException{
        String INSERT_PEDIDOS_SQL = "INSERT INTO pedido" + " (Valor_Total, Cliente_CPF, status, endereco) VALUES " + " (?, ?, 0, ?);";
        int idPedido = 0;
        int result = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            String connection = "jdbc:mysql://localhost/dev_lanche";
            String user="root", senha="";
            
            Connection conn = DriverManager.getConnection(connection, user, senha);
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_PEDIDOS_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setFloat(1, pedido.getValor_Total());
            preparedStatement.setString(2, pedido.getCpfcliente());
            preparedStatement.setString(3, pedido.getEndereco());
            

            result = preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                idPedido = generatedKeys.getInt(1);
                System.out.println(idPedido);
                // faça o que precisar com o idPedido
            }
            
            
            
        }catch (SQLException e){
            printSQLException(e);
        }
        return idPedido;
    }
    public int CriaLanchePedido(LanchePedido lancheped) throws ClassNotFoundException{
        String INSERT_LPEDIDOS_SQL = "INSERT INTO lanchepedido" + " (Nome_lanche, Id_pedido, observacao, quantidade) VALUES " + " (?, ?, ?, ?);";
        int result = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            String connection = "jdbc:mysql://localhost/dev_lanche";
            String user="root", senha="";
            
            Connection conn = DriverManager.getConnection(connection, user, senha);
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_LPEDIDOS_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, lancheped.getNomelanche());
            preparedStatement.setInt(2, lancheped.getIdpedido());
            preparedStatement.setString(3, lancheped.getObservacao());
            preparedStatement.setInt(4, lancheped.getQuantidade());

            result = preparedStatement.executeUpdate();
        }catch (SQLException e){
            printSQLException(e);
        }
        return result;
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
