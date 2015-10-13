/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yourcolor;
import java.sql.*;

/**
 *
 * @author Administrador
 */
public class Conexion {
    /**Parametros de conexion*/
   static String bd = "yourcolor";
   static String login = "root";
   static String password = "";
   static String url = "jdbc:mysql://localhost/"+bd;
   
   Connection connection = null;
   
   public Conexion(){
       try{
           Class.forName("com.mysql.jdbc.Driver");
           connection = DriverManager.getConnection(url,login,password);
           
           if(connection!=null){
           System.out.println("Conexión a base de datos "+bd+" OK\n");
           }
       
       }
       catch(SQLException e){
         System.out.println(e);
      }catch(ClassNotFoundException e){
         System.out.println(e);
      }catch(Exception e){
         System.out.println(e);
      }

   }
   
   public Connection conectar(){
      return connection;
   }
 
   public void desconectar(){
      connection = null;
   }
    
}
