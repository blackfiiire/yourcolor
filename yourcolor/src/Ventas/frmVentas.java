/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import yourcolor.Conexion;
import yourcolor.Login;

/**
 *
 * @author Jhonny
 */
public class frmVentas extends javax.swing.JInternalFrame {
    //Crear instancia de conexion de base de datos 
    Conexion conexion = new Conexion();
    Connection cn = conexion.conectar();
    
    // variables
    int NumVentas;
    /**
     * Creates new form frmVentas
     */
    public frmVentas() {
        initComponents();
        
        //foco a producto
        this.tfCodigo.requestFocus();
                
        VerNumero();
        

        //dar valor minimo a cantidad
        tfCantidad.setText("1");        
        
        //Crear instancia calendario
        Calendar cal = Calendar.getInstance();
        //Asignar fecha a label 
        labelFecha.setText( + cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE));
    }
    
    //Metodo para ver numero de ventas
    public void VerNumero()
    {
        try {
                //abrir conexion
                conexion.conectar();
                //Crear consulta para buscar ultimo numero de ventas
                String Query = "SELECT numero FROM ventas ORDER BY numero DESC LIMIT 1";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(Query);
                while(rs.next())
                {
                    NumVentas = rs.getInt("numero");
                }
                labelNVentas.setText(String.valueOf(NumVentas));
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
    }   
    
   //Metodo Agregar Productos
    public void AgregarProductos(){
            try {
                String codigo = tfCodigo.getText();
                conexion.conectar();
                String query = "SELECT pVenta FROM productos WHERE codigo = '" + codigo + "'";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(query);
                if(rs.next()==true)
                {
                    int valor       = Integer.parseInt(rs.getString("pVenta"));
                    int cantidad    = Integer.parseInt(tfCantidad.getText());
                    int total       = (valor * cantidad); 
                    
                    conexion.desconectar();
                    //abrir nueva conexion
                    conexion.conectar();
                    String n = "NULL";
                    //armar query
                    String Query = "INSERT INTO pedidos (id,id_venta,cod_producto,can_producto,total) values (" + n + ",'" + labelNVentas.getText() + "','" + tfCodigo.getText() + "','" + tfCantidad.getText() + "','" + total + "');";
                    //ejecutar query
                    Statement st1 = cn.createStatement();
                    st1.executeUpdate(Query);
                    //cerrar conexion
                    conexion.desconectar();
                    
                    //agregar datos a jTable
                    //Abrir conexion
                    conexion.conectar();
                    //query
                    String consulta = "SELECT nombre ,pVenta FROM productos WHERE codigo = '" + codigo + "';";
                    Statement st2 = cn.createStatement();
                    ResultSet rs2 = st2.executeQuery(consulta);
                    if(rs2.next()==true)
                    {
                        String numv = labelNVentas.getText();
                        String cod = tfCodigo.getText();
                        String descrip = rs2.getString("nombre");
                        String cant = tfCantidad.getText();
                        String val = rs2.getString("pVenta");
                        DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
                        Object fila[] = {numv,cod,descrip,cant,val,total};
                        m.addRow(fila);
                    }
                }
                conexion.desconectar();
                
                //limpiar tf 
                this.tfCodigo.setText("");
                this.tfCantidad.setText("1");
            } catch (SQLException ex) {
                Logger.getLogger(frmVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
    }  
    
    //metodo sumar total 
    public void SumarTotal(){
        double sumatoria1 = 0;
        DecimalFormat formatea = new DecimalFormat("###,###.##");
        int totalRow= jTable1.getRowCount();
        totalRow-=1;
        for(int i=0;i<=(totalRow);i++)
        {
             double sumatoria= Double.parseDouble(String.valueOf(jTable1.getValueAt(i,5)));
             sumatoria1+=sumatoria;
             labelTotal.setText(String.valueOf(formatea.format(sumatoria1)));
        }
    }
    
    //Quitar Productos
    public void QuitarPro(){
        try {
            //tomar modelo de la tabla
            DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
            //Seleccionar Fila
            int IdSelect = jTable1.getSelectedRow();
            //obtener valores de Codigo de producto y cantidad
            String codi = m.getValueAt(IdSelect, 1).toString();
            String canti = m.getValueAt(IdSelect, 3).toString();
            //abrir conexion
            conexion.conectar();
            //query
            String Query = "DELETE FROM pedidos WHERE (cod_producto = '" + codi + "') and (id_venta = '" + labelNVentas.getText() + "') and (can_producto = '" + canti + "')";
            Statement st = cn.createStatement();
            st.execute(Query);
            //cerrar conexion
            conexion.desconectar();
            //remover fila de jtable
            m.removeRow(IdSelect); 
            //Limpiar total
            labelTotal.setText("0");
        } catch (SQLException ex) {
            Logger.getLogger(frmVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Facturar(){
        variables.Total = labelTotal.getText();
        variables.NumeroVenta = labelNVentas.getText();
        variables.Fecha = labelFecha.getText();
        
        System.out.println(variables.Total);
        
        //Crear instancia facturar
        Facturar Fac = new Facturar();
        
        //Limpiar Tabla
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° venta", "Producto", "Detalle", "Cantidad", "Precio Unitario", "Total"
            }
        ));
        
        labelTotal.setText("0");
        
        //Limpiar campos
        //tfRutCliente.setText("");
        //tfNombreCliente.setText("");
        tfCantidad.setText("1");
        
        //Mostrar frame facturar
        Fac.setVisible(true);    
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        labelNVentas = new javax.swing.JLabel();
        tfCodigo = new javax.swing.JTextField();
        tfCantidad = new javax.swing.JTextField();
        btnAgreegar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        btnBorrar = new javax.swing.JButton();
        btnFacturar = new javax.swing.JButton();
        labelFecha = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1264, 493));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1264, 493));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° de Venta", "Cod. Producto", "Detalle", "Cantidad", "Precio", "Total"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jLabel1.setText("Numero");

        labelNVentas.setText("jLabel2");

        tfCodigo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfCodigoCaretUpdate(evt);
            }
        });
        tfCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCodigoActionPerformed(evt);
            }
        });
        tfCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfCodigoKeyPressed(evt);
            }
        });

        btnAgreegar.setText("+");
        btnAgreegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgreegarActionPerformed(evt);
            }
        });

        jLabel2.setText("Total");

        labelTotal.setText("jLabel3");

        btnBorrar.setText("-");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnFacturar.setText("Facturar");
        btnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarActionPerformed(evt);
            }
        });

        labelFecha.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelFecha)
                        .addGap(207, 207, 207)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(labelTotal))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(tfCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnAgregar))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAgreegar)
                                .addGap(18, 18, 18)
                                .addComponent(btnBorrar)
                                .addGap(18, 18, 18)
                                .addComponent(btnFacturar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelNVentas)))))
                .addContainerGap(764, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(labelNVentas)))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgreegar)
                    .addComponent(btnBorrar)
                    .addComponent(btnFacturar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(labelTotal)
                    .addComponent(labelFecha))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            // Prueba agregar
            conexion.conectar();
            //Variables
            String n = "NULL";
            String fecha = "2015-11-16";
            String emple = "18.379.633-k";
            String clien = "18.834.094-6";
            String total = "4860";
            String estado = "6";
            String pago = "Efectivo";
            
                    
            //crear query
            String query = "INSERT INTO ventas(numero,fecha,rut_emple,rut_clien,total,estado,pago) VALUES("+ n +",'2015-11-16','18.379.633-k','18.834.094-6','4860','6','Efectivo')";
            Statement st1 = cn.createStatement();
            st1.executeUpdate(query);
            //Desconectar
            conexion.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(frmVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tfCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCodigoActionPerformed

    private void btnAgreegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgreegarActionPerformed
        AgregarProductos();
        SumarTotal();
    }//GEN-LAST:event_btnAgreegarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        QuitarPro();
        SumarTotal();        
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarActionPerformed
        Facturar();
    }//GEN-LAST:event_btnFacturarActionPerformed

    private void tfCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCodigoKeyPressed
        char car=(char) evt.getKeyCode();
        if(car==evt.VK_ENTER){
            AgregarProductos();
            SumarTotal();
        }
        
        if(car==evt.VK_F1){
            Facturar();
        }
    }//GEN-LAST:event_tfCodigoKeyPressed

    private void tfCodigoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfCodigoCaretUpdate

    }//GEN-LAST:event_tfCodigoCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgreegar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnFacturar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelNVentas;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JTextField tfCantidad;
    private javax.swing.JTextField tfCodigo;
    // End of variables declaration//GEN-END:variables
}
