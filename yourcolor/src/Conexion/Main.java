package Conexion;

import Conexion.ConexionDB;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * *
 * @author Kampu
 */ 

public class Main {
    public static void main(String[] args)
    {
        Connection miConexion;
        miConexion=ConexionDB.GetConnection();
      
        if(miConexion!=null)
        {
            JOptionPane.showMessageDialog(null, "Conexión Realizada Correctamente");
        }
    }
}
