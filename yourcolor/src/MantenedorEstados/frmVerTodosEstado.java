/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MantenedorEstados;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Image;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kampu
 */
public class frmVerTodosEstado extends javax.swing.JInternalFrame {

    /**
     * Creates new form Ver
     */
    public frmVerTodosEstado() {
        initComponents();
          try {
 //Para establecer el modelo al JTable
 DefaultTableModel modelo = new DefaultTableModel();
 this.tabla1.setModel(modelo);
 //Para conectarnos a nuestra base de datos
 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
 Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/yourcolor", "root", "");
 //Para ejecutar la consulta
 Statement s = conexion.createStatement();
 //Ejecutamos la consulta que escribimos en la caja de texto
 //y los datos lo almacenamos en un ResultSet
 ResultSet rs = s.executeQuery("select * from estados");
 //Obteniendo la informacion de las columnas que estan siendo consultadas
 ResultSetMetaData rsMd = rs.getMetaData();
 //La cantidad de columnas que tiene la consulta
 int cantidadColumnas = rsMd.getColumnCount();
 //Establecer como cabezeras el nombre de las colimnas
 for (int i = 1; i <= cantidadColumnas; i++) {
  modelo.addColumn(rsMd.getColumnLabel(i));
 }
 //Creando las filas para el JTable
 while (rs.next()) {
  Object[] fila = new Object[cantidadColumnas];
  for (int i = 0; i < cantidadColumnas; i++) {
    fila[i]=rs.getObject(i+1);
  }
  modelo.addRow(fila);
 }
 rs.close();
 conexion.close();
} catch (Exception ex) {
 ex.printStackTrace();
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
        tabla1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txttipo = new javax.swing.JComboBox();
        btnImprimir = new javax.swing.JButton();

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla1);

        jButton1.setText("ACTUALIZAR TODOS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Filtrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txttipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cuenta", "Compra", "Mensajes" }));

        btnImprimir.setText("Imprimir PDF");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(txttipo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(txttipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
 //Para establecer el modelo al JTable
 DefaultTableModel modelo = new DefaultTableModel();
 this.tabla1.setModel(modelo);
 //Para conectarnos a nuestra base de datos
 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
 Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/yourcolor", "root", "");
 //Para ejecutar la consulta
 Statement s = conexion.createStatement();
 //Ejecutamos la consulta que escribimos en la caja de texto
 //y los datos lo almacenamos en un ResultSet
 ResultSet rs = s.executeQuery("select * from estados");
 //Obteniendo la informacion de las columnas que estan siendo consultadas
 ResultSetMetaData rsMd = rs.getMetaData();
 //La cantidad de columnas que tiene la consulta
 int cantidadColumnas = rsMd.getColumnCount();
 //Establecer como cabezeras el nombre de las colimnas
 for (int i = 1; i <= cantidadColumnas; i++) {
  modelo.addColumn(rsMd.getColumnLabel(i));
 }
 //Creando las filas para el JTable
 while (rs.next()) {
  Object[] fila = new Object[cantidadColumnas];
  for (int i = 0; i < cantidadColumnas; i++) {
    fila[i]=rs.getObject(i+1);
  }
  modelo.addRow(fila);
 }
 rs.close();
 conexion.close();
} catch (Exception ex) {
 ex.printStackTrace();
}
          JOptionPane.showMessageDialog(this, "ACTUALIZADO");

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
           String tipo = (String) txttipo.getSelectedItem();
            
 //Para establecer el modelo al JTable
 DefaultTableModel modelo = new DefaultTableModel();
 this.tabla1.setModel(modelo);
 //Para conectarnos a nuestra base de datos
 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
 Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/yourcolor", "root", "");
 //Para ejecutar la consulta
 Statement s = conexion.createStatement();
 //Ejecutamos la consulta que escribimos en la caja de texto
 //y los datos lo almacenamos en un ResultSet
 ResultSet rs = s.executeQuery("select * from estados where tipo='"+tipo+"'");
 //Obteniendo la informacion de las columnas que estan siendo consultadas
 ResultSetMetaData rsMd = rs.getMetaData();
 //La cantidad de columnas que tiene la consulta
 int cantidadColumnas = rsMd.getColumnCount();
 //Establecer como cabezeras el nombre de las colimnas
 for (int i = 1; i <= cantidadColumnas; i++) {
  modelo.addColumn(rsMd.getColumnLabel(i));
 }
 //Creando las filas para el JTable
 while (rs.next()) {
  Object[] fila = new Object[cantidadColumnas];
  for (int i = 0; i < cantidadColumnas; i++) {
    fila[i]=rs.getObject(i+1);
  }
  modelo.addRow(fila);
 }
 rs.close();
 conexion.close();
} catch (Exception ex) {
 ex.printStackTrace();
}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
        JFileChooser seleccionar_archivo = new JFileChooser();

        DefaultTableModel m =(DefaultTableModel) tabla1.getModel();
      
             int opcion=seleccionar_archivo.showSaveDialog(null);
                if(opcion==seleccionar_archivo.APPROVE_OPTION){
                
                                        
                  
              OutputStream archivo = null;
            try {
                archivo = new FileOutputStream(seleccionar_archivo.getSelectedFile());
               
            } catch (FileNotFoundException ex) {
                Logger.getLogger(frmVerTodosEstado.class.getName()).log(Level.SEVERE, null, ex);
            }

            Document documento = new Document();
            try {
                PdfWriter.getInstance(documento, archivo);
            } catch (DocumentException ex) {
                Logger.getLogger(frmVerTodosEstado.class.getName()).log(Level.SEVERE, null, ex);
            }
            PdfPTable t = new PdfPTable(3);
            documento.open();
            try
{
    Image foto = Image.getInstance("logo.png");
    foto.scaleToFit(100, 100);
    foto.setAlignment(Chunk.ALIGN_MIDDLE);
    documento.add(foto);
}
catch ( Exception e )
{
    e.printStackTrace();
}
            try {
                documento.add(new Paragraph("                                                "));
            } catch (DocumentException ex) {
                Logger.getLogger(frmVerTodosEstado.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                documento.add(new Paragraph("ESTADOS"));
            } catch (DocumentException ex) {
                Logger.getLogger(frmVerTodosEstado.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                documento.add(new Paragraph(" "));
            } catch (DocumentException ex) {
                Logger.getLogger(frmVerTodosEstado.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                documento.add(new Paragraph(" "));
            } catch (DocumentException ex) {
                Logger.getLogger(frmVerTodosEstado.class.getName()).log(Level.SEVERE, null, ex);
            }
            int f=0;
            String id;
            String descripcion;
            String tipo;
            
            try {
                documento.add(t);
            } catch (DocumentException ex) {
                Logger.getLogger(frmVerTodosEstado.class.getName()).log(Level.SEVERE, null, ex);
            }
             t.addCell("    ID");
                 t.addCell("    DESCRIPCION");
                t.addCell("    TIPO");
            while (f < (m.getRowCount())){
                id = m.getValueAt(f, 0).toString();
                descripcion = m.getValueAt(f, 1).toString();
                tipo = m.getValueAt(f, 2).toString();
                
                //Escribir Fila en archivo
                t.addCell(id);
                 t.addCell(descripcion);
                t.addCell(tipo);
               
              
                f++;
            }
            try {
                documento.add(t);
            } catch (DocumentException ex) {
                Logger.getLogger(frmVerTodosEstado.class.getName()).log(Level.SEVERE, null, ex);
            }
            //
            documento.close();
             File JFC = seleccionar_archivo.getSelectedFile();
                 String PATH = JFC.getAbsolutePath();//obtenemos el path del archivo a guardar
                 if(!(PATH.endsWith(".pdf"))){
                        File temp = new File(PATH+".pdf");
                        JFC.renameTo(temp);//renombramos el archivo
                    }
          JOptionPane.showMessageDialog(this, "Documento PDF creado exitosamente!");
        }
    }//GEN-LAST:event_btnImprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla1;
    private javax.swing.JComboBox txttipo;
    // End of variables declaration//GEN-END:variables
}
