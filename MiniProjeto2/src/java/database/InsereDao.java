package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Pedido;
import model.LanchePedido;

public class InsereDao {
    private String connection = "jdbc:mysql://localhost/dev_lanche";
    private String user="root", senha="";
    
    private String INSERT_PEDIDOS_SQL = "INSERT INTO pedido" + " (Valor_Total, Cliente_CPF, endereco) VALUES " + " (?, ?, ?);";
    private String INSERT_LPEDIDOS_SQL = "INSERT INTO lanchepedido" + " (Nome_lanche, Id_pedido, observacao, quantidade) VALUES " + " (?, ?, ?, ?);";

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
    

    public int inserePedido(Pedido pedido) throws ClassNotFoundException{
        try{
            Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_PEDIDOS_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setFloat(1, pedido.getValor_Total());
            preparedStatement.setString(2, pedido.getCpfcliente());
            preparedStatement.setString(3, pedido.getEndereco());
            
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            int idPedido = 0;
            if (generatedKeys.next()) {
                idPedido = generatedKeys.getInt(1);
                System.out.println(idPedido);
            }
            return idPedido;
        }catch (SQLException e){
            printSQLException(e);
            return -1;
        }
    }

    public int insereLanchePedido(LanchePedido lancheped) throws ClassNotFoundException{
        try{
            Connection conn = getConnection();           
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_LPEDIDOS_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, lancheped.getNomelanche());
            preparedStatement.setInt(2, lancheped.getIdpedido());
            preparedStatement.setString(3, lancheped.getObservacao());
            preparedStatement.setInt(4, lancheped.getQuantidade());

            int result = preparedStatement.executeUpdate();
            return result;
        }catch (SQLException e){
            printSQLException(e);
            return -1;
        }
    }
   
    private void printSQLException(SQLException ex){
        for(Throwable e: ex)
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
