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

import model.Lanche;

/**
 *
 * @author Vinicius
 */
public class LancheDao {
        private String connection = "jdbc:mysql://localhost/dev_lanche";
    private String user="root", senha="";
    
    private String SELECT_ALL_lanche = "select * from lanche;";
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
    public List<Lanche> selectAllLanche() throws SQLException{
        List<Lanche> lanche = new ArrayList<>();
        try{
            Connection conn = getConnection();
            
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_lanche);
            
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
