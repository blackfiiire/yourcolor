/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.exceptions.MySQLDataException;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


/**
 *
 * kampu
 */
public abstract class ConexionDB {
    
    protected static Connection Conexion()
    {
        Connection conexion=null;
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String servidor="jdbc:mysql://localhost/yourcolor";
            String usuario="root";
            String pass="";
            conexion=(Connection)DriverManager.getConnection(servidor, usuario, pass);
        }
        catch(ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        catch(MySQLDataException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        finally
        {
            return conexion;
        }
    }
}
