/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yourcolor;

import Conexion.ConexionDB;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class Yourcolor {


    public static void main(String[] args)
    {
 //Verificar Conexion...
        Connection miConexion;
        miConexion=ConexionDB.GetConnection();
      
        if(miConexion!=null)
        {
            JOptionPane.showMessageDialog(null, "Conexión Realizada Correctamente");
        }
    }
}
