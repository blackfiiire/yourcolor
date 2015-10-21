/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estadisticas;

import MantenedorEstados.frmVerTodosEstado;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 *
 * @author Kampux
 */
public class gananciasanuales extends javax.swing.JInternalFrame {

    /**
     * Creates new form barras
     */
    public gananciasanuales() {
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

        jButton1 = new javax.swing.JButton();
        panelGraficoTorta = new javax.swing.JDesktopPane();
        lblTorta = new javax.swing.JLabel();
        btnpdf = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1172, 937));

        jButton1.setText("Mostrar Grafico");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelGraficoTortaLayout = new javax.swing.GroupLayout(panelGraficoTorta);
        panelGraficoTorta.setLayout(panelGraficoTortaLayout);
        panelGraficoTortaLayout.setHorizontalGroup(
            panelGraficoTortaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGraficoTortaLayout.createSequentialGroup()
                .addComponent(lblTorta)
                .addGap(0, 660, Short.MAX_VALUE))
        );
        panelGraficoTortaLayout.setVerticalGroup(
            panelGraficoTortaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGraficoTortaLayout.createSequentialGroup()
                .addComponent(lblTorta)
                .addGap(0, 429, Short.MAX_VALUE))
        );
        panelGraficoTorta.setLayer(lblTorta, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGraficoTorta)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(272, 272, 272)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnpdf, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnpdf, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelGraficoTorta)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
          JFreeChart barra = null;
        DefaultCategoryDataset datos;
        datos = new DefaultCategoryDataset();
        
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
               /*  int año =txtAño.getYear();
                int mes =txtMes.getMonth();
                int mesn=mes+1;
                String mesnn="";
                
                switch(mesn)
        
                        {
            case 1:
            
                        mesnn= "01"; 
                break;
                case 2:
            
                        mesnn= "02"; break;
                    case 3:
            
                        mesnn= "03"; break;
                        case 4:
            
                        mesnn= "04"; break;
                            case 5:
            
                        mesnn= "05"; break;
                                case 6:
            
                        mesnn= "06"; break;
                                    case 7:
            
                        mesnn= "07"; break;
                                        case 8:
            
                        mesnn= "08"; break;
                                            case 9:
            
                        mesnn= "09"; 
                                                     case 10:
            
                        mesnn= "10"; break;
                                                      case 11:
            
                        mesnn= "11"; break;
                                                           case 12:
            
                        mesnn= "12"; break;
                                          
                  }
           */
           rs = s.executeQuery("SELECT YEAR( fecha ) AS AÑO, MONTH( fecha ) AS MES, DAY( fecha ) AS DIA, MONTHNAME( fecha ) AS NOMBRE_MES, SUM( total ) AS TOTALGANANCIAAÑO,COUNT(TOTAL) AS CANTIDADVENTAS, CASE WHEN MONTH( fecha ) =1 THEN  \"Enero\" WHEN MONTH( fecha ) =2 THEN  \"Febrero\" WHEN MONTH( fecha ) =3 THEN  \"Marzo\" WHEN MONTH( fecha ) =4 THEN  \"Abril\" WHEN MONTH( fecha ) =5 THEN  \"Mayo\" WHEN MONTH( fecha ) =6 THEN  \"Junio\" WHEN MONTH( fecha ) =7 THEN  \"Julio\" WHEN MONTH( fecha ) =8 THEN  \"Agosto\" WHEN MONTH( fecha ) =9 THEN  \"Septiembre\" WHEN MONTH( fecha ) =10 THEN  \"Octubre\" WHEN MONTH( fecha ) =11 THEN  \"Noviembre\" WHEN MONTH( fecha ) =12 THEN  \"Diciembre\" ELSE  \"esto no es un mes\" END AS MESespañol FROM ventas GROUP BY AÑO");

        } catch (SQLException ex) {
            Logger.getLogger(ventasmensuales.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
           
            
            while(rs.next())
            {
               datos.setValue(Integer.parseInt(rs.getString("TOTALGANANCIAAÑO")),"",rs.getString("año"));
                
          
            }
        } catch (SQLException ex) {
            Logger.getLogger(ventasmensuales.class.getName()).log(Level.SEVERE, null, ex);
        }
       /* int mes =txtMes.getMonth();
        int mesn=mes+1;
        String mesnn="";
         switch(mesn)
        
                        {
            case 1:
            
                        mesnn= "01"; 
                break;
                case 2:
            
                        mesnn= "02"; break;
                    case 3:
            
                        mesnn= "03"; break;
                        case 4:
            
                        mesnn= "04"; break;
                            case 5:
            
                        mesnn= "05"; break;
                                case 6:
            
                        mesnn= "06"; break;
                                    case 7:
            
                        mesnn= "07"; break;
                                        case 8:
            
                        mesnn= "08"; break;
                                            case 9:
            
                        mesnn= "09"; break;
                                                 case 10:
            
                        mesnn= "10"; break;
                                                      case 11:
            
                        mesnn= "11"; break;
                                                           case 12:
            
                        mesnn= "12"; break;
                                          
                              
                                                
                  }
        int año =txtAño.getYear();*/
            barra = ChartFactory.createBarChart3D("ANUALES/GANANCIAS"/*+año+"/"+mesnn+""*/, "AÑO","GANANCIAS",datos,PlotOrientation.VERTICAL,false,true,true);
        BufferedImage graficoBarra=barra.createBufferedImage(panelGraficoTorta.getWidth(), panelGraficoTorta.getHeight());

        lblTorta.setSize(panelGraficoTorta.getSize());
        lblTorta.setIcon(new ImageIcon(graficoBarra));

        panelGraficoTorta.updateUI();
        
        try {
            ChartUtilities.saveChartAsJPEG(new File("ImagenEstadistica1Uso.jpg"), barra, graficoBarra.getWidth(), graficoBarra.getHeight());
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
                Logger.getLogger(frmVerTodosEstado.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                documento.add(new Paragraph("ESTADISTICA MESES/GANANCIAS"));
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
            try
            {
                Image foto = Image.getInstance("ImagenEstadistica1Uso.jpg");
                foto.scaleToFit(520, 600);
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
    }//GEN-LAST:event_btnpdfActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnpdf;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblTorta;
    private javax.swing.JDesktopPane panelGraficoTorta;
    // End of variables declaration//GEN-END:variables
}
