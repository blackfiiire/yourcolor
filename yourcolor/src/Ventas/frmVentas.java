/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
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
        
        //Iniciar Timer
        timer.start();
        
        //dar valor minimo a cantidad
        tfCantidad.setText("1");        
        
        //Crear instancia calendario
        Calendar cal = Calendar.getInstance();
        //Asignar fecha a label 
        labelFecha.setText( + cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE));
    }
    Timer timer = new Timer (1000, new ActionListener ()
    {
        public void actionPerformed(ActionEvent e)
        {
        //Ver NUmero de ventas
            VerNumero();
        }
    });
    
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
            
            //abrir Conexion
            conexion.conectar();
            //Crear Consulta
            String SQL = "UPDATE productos SET stock = stock + '" + canti + "' WHERE codigo = '" + codi + "'";
            Statement st1 = cn.createStatement();
            st1.executeUpdate(SQL);
            //Cerrar Conexion
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
        System.out.println(variables.Rut);
        //Crear instancia facturar
        Facturar Fac = new Facturar();
        
        //Limpiar Tabla
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° venta", "Cod. Producto", "Detalle", "Cantidad", "Precio unit", "SubTotal"
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
    
    //Metodo para buscar cliente
    public void BusCliente()
    {
        //abrir conexion
        conexion.conectar();
        try {
                //Crear consulta para buscar cliente segun rut
                String Query = "SELECT nombres,apellidos FROM usuarios WHERE rut = '"+tfRutCliente.getText()+"'";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(Query);
                while(rs.next())
                {
                    tfNombreCliente.setText(rs.getString("nombres")+ " " +  rs.getString("apellidos"));
                }
                conexion.desconectar();
                
                    if(tfNombreCliente.getText().equals("")){
                        JOptionPane.showMessageDialog(this,"El Cliente No existe");
                        tfNombreCliente.setText("");
                    }else{
                //Abrir Conexion
                conexion.conectar();
                //hacer update de rut cliente
                String Query1 = "UPDATE ventas SET rut_clien = '"+tfRutCliente.getText()+"' WHERE numero = '"+labelNVentas.getText()+"'";
                    try {
                        Statement st1 = cn.createStatement();
                        st1.executeUpdate(Query1);
                    } catch (SQLException ex) {
                        Logger.getLogger(frmVentas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }//Fin else
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,"El Cliente No existe" + ex);
            }
        
        
    }
    
    //Metodo para descontar productos de stock
    public void DescontarStock(){
            try {
                //variable almacenar cantidad de productos
                int CantPro = 0;
                //abrir conexion
                conexion.conectar();
                //Ver cantidad disponible de productos
                String query = "SELECT stock FROM productos where codigo = ('" + tfCodigo.getText() + "');";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(query);
                if(rs.next()==true)
                {
                    CantPro = rs.getInt("stock");
                }
                //cerrar conexion.
                conexion.desconectar();
                
                //restar productos
                int TotalStock = (CantPro - Integer.parseInt(tfCantidad.getText()));
                
                         //abrir nueva conexion
                        conexion.conectar();
                        String Query1 = "UPDATE productos SET stock = " + TotalStock + " where codigo ='" + tfCodigo.getText() + "'";
                        Statement st1 = cn.createStatement();
                        st1.executeUpdate(Query1);
                        
                        //cerrar conexion
                        conexion.desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(frmVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        jLabel1 = new javax.swing.JLabel();
        labelNVentas = new javax.swing.JLabel();
        btnAgreegar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        btnBorrar = new javax.swing.JButton();
        btnFacturar = new javax.swing.JButton();
        labelFecha = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        tfCodigo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfCantidad = new javax.swing.JTextField();
        tfRutCliente = new javax.swing.JTextField();
        tfNombreCliente = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();

        setMinimumSize(new java.awt.Dimension(1240, 570));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1240, 570));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° de Venta", "Cod. Producto", "Detalle", "Cantidad", "Precio Unit", "SubTotal"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tekton Pro", 0, 24)); // NOI18N
        jLabel1.setText("Numero de Venta");

        labelNVentas.setFont(new java.awt.Font("Tekton Pro", 0, 24)); // NOI18N
        labelNVentas.setText("jLabel2");

        btnAgreegar.setText("+");
        btnAgreegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgreegarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tekton Pro", 0, 72)); // NOI18N
        jLabel2.setText("Total");

        labelTotal.setFont(new java.awt.Font("Tekton Pro", 0, 72)); // NOI18N
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

        labelFecha.setFont(new java.awt.Font("Tekton Pro", 0, 24)); // NOI18N
        labelFecha.setText("jLabel3");

        jLabel3.setFont(new java.awt.Font("Tekton Pro", 0, 48)); // NOI18N
        jLabel3.setText("PRODUCTO");

        jLabel6.setFont(new java.awt.Font("Tekton Pro", 0, 48)); // NOI18N
        jLabel6.setText("CLIENTE");

        jLabel7.setFont(new java.awt.Font("Tekton Pro", 0, 48)); // NOI18N
        jLabel7.setText("MÉTODO DE PAGO");

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

        jLabel8.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N
        jLabel8.setText("Nombre");

        jLabel9.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N
        jLabel9.setText("Cantidad");

        tfNombreCliente.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N
        jLabel10.setText("Codigo");

        jLabel11.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N
        jLabel11.setText("Rut");

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Efectivo", "Cheque", "RedCompra" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(546, 546, 546)
                .addComponent(labelFecha)
                .addGap(330, 330, 330)
                .addComponent(jLabel1)
                .addGap(14, 14, 14)
                .addComponent(labelNVentas))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel3))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel10)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(tfCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnAgreegar)
                        .addGap(9, 9, 9)
                        .addComponent(btnBorrar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel7))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jLabel6))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel11)
                        .addGap(41, 41, 41)
                        .addComponent(tfRutCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel8)
                        .addGap(13, 13, 13)
                        .addComponent(tfNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(btnFacturar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(422, 422, 422)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(labelTotal))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFecha)
                    .addComponent(jLabel1)
                    .addComponent(labelNVentas))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgreegar)
                            .addComponent(btnBorrar))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfRutCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnFacturar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addComponent(labelTotal)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgreegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgreegarActionPerformed
        AgregarProductos();
        DescontarStock();
        SumarTotal();
    }//GEN-LAST:event_btnAgreegarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        QuitarPro();
        SumarTotal();        
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarActionPerformed
        Facturar();
    }//GEN-LAST:event_btnFacturarActionPerformed

    private void tfCodigoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfCodigoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCodigoCaretUpdate

    private void tfCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCodigoActionPerformed

    private void tfCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCodigoKeyPressed
        char car=(char) evt.getKeyCode();
        if(car==evt.VK_ENTER){
            AgregarProductos();
            DescontarStock();
            SumarTotal();
        }
        
        if(car==evt.VK_F1){
            this.Facturar();
        }
    }//GEN-LAST:event_tfCodigoKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        BusCliente();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgreegar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnFacturar;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelNVentas;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JTextField tfCantidad;
    private javax.swing.JTextField tfCodigo;
    private javax.swing.JTextField tfNombreCliente;
    private javax.swing.JTextField tfRutCliente;
    // End of variables declaration//GEN-END:variables
}
