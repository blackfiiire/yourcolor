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
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;


/**
 *
 * @author Kampux
 */
public class torta extends javax.swing.JInternalFrame {

    static BufferedImage createBufferedImage(int width, int height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Creates new form estadistica
     */
    public torta() {
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

        jButton2 = new javax.swing.JButton();
        panelGraficoTorta = new javax.swing.JDesktopPane();
        lblTorta = new javax.swing.JLabel();
        btnpdf = new javax.swing.JButton();

        jButton2.setText("Mostrar Grafico");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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
                .addGap(0, 268, Short.MAX_VALUE))
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
            .addComponent(panelGraficoTorta)
            .addGroup(layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(jButton2)
                .addGap(27, 27, 27)
                .addComponent(btnpdf)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelGraficoTorta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(btnpdf))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        DefaultPieDataset porciones;
        JFreeChart torta;
         porciones=new DefaultPieDataset();
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


            rs = s.executeQuery("SELECT COUNT( sexo ) AS CANTIDAD, SEXO FROM usuarios WHERE perfil =1 GROUP BY sexo");
        } catch (SQLException ex) {
            Logger.getLogger(ventasmensuales.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
           
            while(rs.next())
            {
               
                porciones.setValue(rs.getString("SEXO")+"= "+Integer.parseInt(rs.getString("CANTIDAD")),Integer.parseInt(rs.getString("CANTIDAD")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ventasmensuales.class.getName()).log(Level.SEVERE, null, ex);
        }

        

        
        
        torta=ChartFactory.createPieChart3D("USUARIOS/SEXO",porciones,false,true,false);
        BufferedImage graficoTorta = torta.createBufferedImage(panelGraficoTorta.getWidth(), panelGraficoTorta.getHeight());
        lblTorta.setSize(panelGraficoTorta.getSize());
        lblTorta.setIcon(new ImageIcon(graficoTorta));
        panelGraficoTorta.updateUI();
        
        try {
            ChartUtilities.saveChartAsJPEG(new File("ImagenEstadistica1Uso.jpg"), torta, graficoTorta.getWidth(), graficoTorta.getHeight());
            btnpdf.setEnabled(true);
        } catch (IOException ex) {
          JOptionPane.showMessageDialog(this, "Se ha producido un error al intentar guardar","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel lblTorta;
    private javax.swing.JDesktopPane panelGraficoTorta;
    // End of variables declaration//GEN-END:variables
}
