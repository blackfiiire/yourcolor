/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventas;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import yourcolor.Conexion;

/**
 *
 * @author Jhonny
 */
public class Facturar extends javax.swing.JFrame {
Conexion conexion = new Conexion();
Connection cn = conexion.conectar();
    /**
     * Creates new form Facturar
     */
    public Facturar() {
        initComponents();
        Total.setText(variables.Total);
        SNumeros(tfEfectivo);
    }

    //Metodo para validar solo numeros
    public void SNumeros (JTextField a){
    //permite hacer la llamada al evento
        //KeyAdapter es una clase abstracta que se adapta para recibir los eventos del teclado
        a.addKeyListener(new KeyAdapter() {
            //Evento a utilizar
            public void keyTyped(KeyEvent e){
            //La variable char extrae la variable que se ingresa
                char c=e.getKeyChar();
                if(Character.isLetter(c)){
                //Sonido en caso que se ingrese un caracter no admitido
                getToolkit().beep();
                e.consume();
                }
            }
        });
    }
    
    //Mtodo para crear Reportes 
    public void Reportes () throws DocumentException, FileNotFoundException{
        Document documento = new Document();
        FileOutputStream ficheroPdf = new FileOutputStream("C:\\Users\\Jhonny\\Documents\\NetBeansProjects\\yourcolor\\yourcolor\\src\\Ventas\\Boletas\\fichero.pdf");
        PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
        documento.open();
        //anadir parrafo
        documento.add(new Paragraph("Esto es el primer párrafo, normalito"));
        //cerrar documento
        documento.close();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Total = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Vuelto = new javax.swing.JLabel();
        tfEfectivo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jButton1.setFont(new java.awt.Font("Impact", 0, 80)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 102, 255));
        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tekton Pro", 1, 24)); // NOI18N
        jLabel1.setText("TOTAL");

        Total.setFont(new java.awt.Font("Impact", 0, 28)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tekton Pro", 1, 24)); // NOI18N
        jLabel3.setText("EFECTIVO");

        jLabel4.setFont(new java.awt.Font("Tekton Pro", 1, 24)); // NOI18N
        jLabel4.setText("VUELTO");

        Vuelto.setFont(new java.awt.Font("Tekton Pro", 1, 24)); // NOI18N
        Vuelto.setText("VUELTO");

        tfEfectivo.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        tfEfectivo.setForeground(new java.awt.Color(51, 102, 255));
        tfEfectivo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfEfectivoCaretUpdate(evt);
            }
        });
        tfEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEfectivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Vuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel3)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(tfEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(Vuelto))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            //total venta antigua
            //Abrir Conexion
            conexion.conectar();
            //hacer update de rut cliente
            String Query2 = "UPDATE ventas SET Total ='" + Total.getText() + "' WHERE numero ='" + variables.NumeroVenta + "';";
            Statement st2 = cn.createStatement();
            st2.executeUpdate(Query2);
            
            //Nueva Venta
            //abrir nueva conexion
            conexion.conectar();
            int TotalV = 0;
            String n = "Null";
            String pago = "Efectivo";
            //armar query
            System.out.println(variables.Rut);
            
            String Query = "INSERT INTO ventas (numero, fecha, rut_emple, rut_clien, Total, estado, Pago) VALUES (" + n + ",'" + variables.Fecha + "','" + variables.Rut + "','" + variables.Rut + "'," + TotalV + ",'6','"+pago+"');";
            //ejecutar query
            Statement st1 = cn.createStatement();
            st1.executeUpdate(Query);
            //cerrar conexion
            conexion.desconectar();
            
            try {
                //crear pdf
                Reportes ();
            } catch (DocumentException ex) {
                Logger.getLogger(Facturar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Facturar.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Cerrar ventana
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Facturar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tfEfectivoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfEfectivoCaretUpdate
        if (tfEfectivo.getText().equals(""))
        {
            Vuelto.setText("");
        }
        else
        {
            if (Double.parseDouble(tfEfectivo.getText()) < Double.parseDouble(Total.getText()))
            {
                Vuelto.setText("");
            }
            else
            {
                double cal = Double.parseDouble(tfEfectivo.getText()) - Double.parseDouble(Total.getText());
                Vuelto.setText(Double.toString(cal));
            }
        }
    }//GEN-LAST:event_tfEfectivoCaretUpdate

    private void tfEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEfectivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEfectivoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Facturar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Total;
    private javax.swing.JLabel Vuelto;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField tfEfectivo;
    // End of variables declaration//GEN-END:variables
}
