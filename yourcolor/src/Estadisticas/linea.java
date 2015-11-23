/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estadisticas;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import org.jfree.chart.ChartUtilities;

/**
 *
 * @author Kampux
 */
public class linea extends javax.swing.JInternalFrame {

    /**
     * Creates new form linea
     */
    public linea() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGraficoTorta = new javax.swing.JDesktopPane();
        lblTorta = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtval = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnpdf = new javax.swing.JButton();

        javax.swing.GroupLayout panelGraficoTortaLayout = new javax.swing.GroupLayout(panelGraficoTorta);
        panelGraficoTorta.setLayout(panelGraficoTortaLayout);
        panelGraficoTortaLayout.setHorizontalGroup(
            panelGraficoTortaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGraficoTortaLayout.createSequentialGroup()
                .addComponent(lblTorta)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelGraficoTortaLayout.setVerticalGroup(
            panelGraficoTortaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGraficoTortaLayout.createSequentialGroup()
                .addComponent(lblTorta)
                .addGap(0, 571, Short.MAX_VALUE))
        );
        panelGraficoTorta.setLayer(lblTorta, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setText("Poner algo:");

        jButton1.setText("Mostrar Grafico");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnpdf.setText("PDF");
        btnpdf.setEnabled(false);
        btnpdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpdfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGraficoTorta)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtval, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(btnpdf)
                .addContainerGap(261, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(btnpdf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGraficoTorta)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        XYSeries series = null;
        XYDataset datos;
        JFreeChart linea = null;
         int x=0;
        series= new XYSeries("Popularidad de Blog");
   
        series.add(x,0);
        x++;
        
      try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException ex) {
            Logger.getLogger(ventasmensuales.class.getName()).log(Level.SEVERE, null, ex);
        }
 Connection conexion = null;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/yourcolor", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(ventasmensuales.class.getName()).log(Level.SEVERE, null, ex);
        }
 //Para ejecutar la consulta
 Statement s = null;
        try {
            s = conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ventasmensuales.class.getName()).log(Level.SEVERE, null, ex);
        }
 //Ejecutamos la consulta que escribimos en la caja de texto
 //y los datos lo almacenamos en un ResultSet
 ResultSet rs = null;
        try {


            rs = s.executeQuery("SELECT YEAR( fecha ) AS AÑO, MONTH( fecha ) AS MES, DAY( fecha ) AS DIA, MONTHNAME( fecha ) AS NOMBRE_MES, SUM( total ) AS TOTALGANANCIAMES, CASE WHEN MONTH( fecha ) =1 THEN  \"Ene\" WHEN MONTH( fecha ) =2 THEN  \"Feb\" WHEN MONTH( fecha ) =3 THEN  \"Mar\" WHEN MONTH( fecha ) =4 THEN  \"Abr\" WHEN MONTH( fecha ) =5 THEN  \"May\" WHEN MONTH( fecha ) =6 THEN  \"Jun\" WHEN MONTH( fecha ) =7 THEN  \"Jul\" WHEN MONTH( fecha ) =8 THEN  \"Ago\" WHEN MONTH( fecha ) =9 THEN  \"Sep\" WHEN MONTH( fecha ) =10 THEN  \"Oct\" WHEN MONTH( fecha ) =11 THEN  \"Nov\" WHEN MONTH( fecha ) =12 THEN  \"Dic\" ELSE  \"esto no es un mes\" END AS MESespañol FROM ventas where fecha like '%2015%' GROUP BY AÑO,MES");
        } catch (SQLException ex) {
            Logger.getLogger(ventasmensuales.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
           
            while(rs.next())
            {
                series.add(x,Integer.parseInt(rs.getString("TOTALGANANCIAMES")));
        x++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ventasmensuales.class.getName()).log(Level.SEVERE, null, ex);
        }

        series.add(x,0);
        x++;
       
       
        datos = new XYSeriesCollection(series);
        linea = ChartFactory.createXYLineChart("Ejemplo Grafico de Linea","MESES","GANANCIAS",datos,PlotOrientation.VERTICAL,false,true,true);
       
        BufferedImage graficoLinea=linea.createBufferedImage(panelGraficoTorta.getWidth(), panelGraficoTorta.getHeight());
       
        lblTorta.setSize(panelGraficoTorta.getSize());
        lblTorta.setIcon(new ImageIcon(graficoLinea));
      
        panelGraficoTorta.updateUI();
        
        try {
            ChartUtilities.saveChartAsJPEG(new File("ImagenEstadistica1Uso.jpg"), linea, graficoLinea.getWidth(), graficoLinea.getHeight());
            btnpdf.setEnabled(true);
        } catch (IOException ex) {
          JOptionPane.showMessageDialog(this, "Se ha producido un error al intentar guardar","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnpdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpdfActionPerformed
        // TODO add your handling code here:
          JFileChooser seleccionar_archivo = new JFileChooser();

     
      
             int opcion=seleccionar_archivo.showSaveDialog(null);
                if(opcion==seleccionar_archivo.APPROVE_OPTION){
                
                                        
                  
              OutputStream archivo = null;
            try {
                archivo = new FileOutputStream(seleccionar_archivo.getSelectedFile());
               
            } catch (FileNotFoundException ex) {
                Logger.getLogger(linea.class.getName()).log(Level.SEVERE, null, ex);
            }

            Document documento = new Document();
            try {
                PdfWriter.getInstance(documento, archivo);
            } catch (DocumentException ex) {
                Logger.getLogger(linea.class.getName()).log(Level.SEVERE, null, ex);
            }
            PdfPTable t = new PdfPTable(3);
            documento.open();
             try
{
    Image foto = Image.getInstance("logo.png");
    foto.scaleToFit(100, 100);
    foto.setAlignment(Chunk.ALIGN_LEFT);
    documento.add(foto);
}
catch ( Exception e )
{
    e.printStackTrace();
}
            try {
                documento.add(new Paragraph("                                                "));
            } catch (DocumentException ex) {
                Logger.getLogger(linea.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                documento.add(new Paragraph("ESTADOS"));
            } catch (DocumentException ex) {
                Logger.getLogger(linea.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                documento.add(new Paragraph(" "));
            } catch (DocumentException ex) {
                Logger.getLogger(linea.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                documento.add(new Paragraph(" "));
            } catch (DocumentException ex) {
                Logger.getLogger(linea.class.getName()).log(Level.SEVERE, null, ex);
            }
              try
{
    Image foto = Image.getInstance("ImagenEstadistica1Uso.jpg");
    foto.scaleToFit(400, 400);
    foto.setAlignment(Chunk.ALIGN_MIDDLE);
    documento.add(foto);
}
catch ( Exception e )
{
    e.printStackTrace();
}
            
              try {
                  documento.add(t);
              } catch (DocumentException ex) {
                  Logger.getLogger(linea.class.getName()).log(Level.SEVERE, null, ex);
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
    }//GEN-LAST:event_btnpdfActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnpdf;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblTorta;
    private javax.swing.JDesktopPane panelGraficoTorta;
    private javax.swing.JTextField txtval;
    // End of variables declaration//GEN-END:variables
}
