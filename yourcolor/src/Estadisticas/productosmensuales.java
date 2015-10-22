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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 *
 * @author Kampux
 */
public class productosmensuales extends javax.swing.JInternalFrame {

    /**
     * Creates new form barras
     */
    public productosmensuales() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        panelGraficoTorta = new javax.swing.JDesktopPane();
        lblTorta = new javax.swing.JLabel();
        btnpdf = new javax.swing.JButton();
        txtAño = new com.toedter.calendar.JYearChooser();
        jLabel1 = new javax.swing.JLabel();
        txtMes = new com.toedter.calendar.JMonthChooser();
        txtcant = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        labelHeader1 = new org.edisoncor.gui.label.LabelHeader();
        jSeparator1 = new javax.swing.JSeparator();

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
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelGraficoTortaLayout.setVerticalGroup(
            panelGraficoTortaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGraficoTortaLayout.createSequentialGroup()
                .addComponent(lblTorta)
                .addGap(0, 401, Short.MAX_VALUE))
        );
        panelGraficoTorta.setLayer(lblTorta, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnpdf.setText("Generar documento PDF con Gráfico");
        btnpdf.setEnabled(false);
        btnpdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpdfActionPerformed(evt);
            }
        });

        jLabel1.setText("Top:");

        txtcant.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "3", "6","9","12" }));

        jButton2.setText("Generar documento PDF con Detalle General");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        labelHeader1.setText("TopProductos Mensuales Vendidos");
        labelHeader1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnpdf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtAño, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcant, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)))
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
                    .addComponent(jSeparator1)
                    .addComponent(panelGraficoTorta, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(txtcant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnpdf, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                 int año =txtAño.getYear();
                int mes =txtMes.getMonth();
                int mesn=mes+1;
                String mesnn="";
               String cant = (String) txtcant.getSelectedItem();
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
           
           rs = s.executeQuery("SELECT YEAR( ventas.fecha ) AS AÑO, MONTH( ventas.fecha ) AS MES, DAY( ventas.fecha ) AS DIA, MONTHNAME( ventas.fecha ) AS NOMBRE_MES,productos.codigo,productos.nombre,sum(pedidos.can_producto) as CANTIDAD, CASE WHEN MONTH( ventas.fecha ) =1 THEN  \"Enero\" WHEN MONTH( ventas.fecha ) =2 THEN  \"Febrero\" WHEN MONTH( ventas.fecha ) =3 THEN  \"Marzo\" WHEN MONTH( ventas.fecha ) =4 THEN  \"Abril\" WHEN MONTH( ventas.fecha ) =5 THEN  \"Mayo\" WHEN MONTH( ventas.fecha ) =6 THEN  \"Junio\" WHEN MONTH( ventas.fecha ) =7 THEN  \"Julio\" WHEN MONTH( ventas.fecha ) =8 THEN  \"Agosto\" WHEN MONTH( ventas.fecha ) =9 THEN  \"Septiembre\" WHEN MONTH( ventas.fecha ) =10 THEN  \"Octubre\" WHEN MONTH( ventas.fecha ) =11 THEN  \"Noviembre\" WHEN MONTH( ventas.fecha ) =12 THEN  \"Diciembre\" ELSE  \"esto no es un mes\" END AS MESespañol FROM ventas,productos,pedidos WHERE (ventas.numero=pedidos.id_venta)and(pedidos.cod_producto=productos.codigo)and(ventas.fecha like '%"+año+"-"+mesnn+"%') group by productos.codigo order by cantidad desc limit "+cant+"");
System.out.println(año+mesnn);
        } catch (SQLException ex) {
            Logger.getLogger(ventasmensuales.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
           
            
            while(rs.next())
            {
               datos.setValue(Integer.parseInt(rs.getString("CANTIDAD")),"",rs.getString("nombre"));
                
          
            }
        } catch (SQLException ex) {
            Logger.getLogger(ventasmensuales.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            
                        mesnn= "09"; break;
                                                 case 10:
            
                        mesnn= "10"; break;
                                                      case 11:
            
                        mesnn= "11"; break;
                                                           case 12:
            
                        mesnn= "12"; break;
                                          
                              
                                                
                  }
        int año =txtAño.getYear();
            barra = ChartFactory.createBarChart3D("MES/TOP PRODUCTOS VENDIDOS"+año+"/"+mesnn+"", "PRODUCTOS","CANTIDAD VENDIDO",datos,PlotOrientation.HORIZONTAL,false,true,true);
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
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Documento PDF","pdf");
seleccionar_archivo.setFileFilter(filtro);
        int opcion=seleccionar_archivo.showSaveDialog(null);
        
         System.out.println(seleccionar_archivo);
          File JFC = seleccionar_archivo.getSelectedFile();          
            String PATH = JFC.getAbsolutePath();//obtenemos el path del archivo a guardar           
            File file = new File(PATH+".pdf");
            boolean fileExists = file.exists();
 if(fileExists==true)
{
int seleccion = JOptionPane.showOptionDialog(
    this, // Componente padre
    "¿Desea sobreescribir el fichero con el mismo nombre?", //Mensaje
    "ADVERTENCIA", // Título
    JOptionPane.YES_NO_CANCEL_OPTION,
    JOptionPane.QUESTION_MESSAGE,
    null,    // null para icono por defecto.
    new Object[] { "Si", "No","Cancelar",},    // null para YES, NO y CANCEL
    "Si");
if (seleccion != -1)
{
   if((seleccion + 1)==1)
   {
       
        System.out.println("PRESIONO SI");
         if(opcion==seleccionar_archivo.APPROVE_OPTION){
         
          
            OutputStream archivo = null;
      
            try {
                archivo = new FileOutputStream(seleccionar_archivo.getSelectedFile()+".pdf");
            
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
 
            JOptionPane.showMessageDialog(this, "Documento PDF creado exitosamente!");
   }
   }
   else if((seleccion + 1)==2)
   {
             System.out.println("PRESIONO NO");
             
       seleccionar_archivo.showSaveDialog(null);
   }else{
                System.out.println("PRESIONO CANCELAR");
   }
}
}else{
        if(opcion==seleccionar_archivo.APPROVE_OPTION){
         
          
            OutputStream archivo = null;
      
            try {
                archivo = new FileOutputStream(seleccionar_archivo.getSelectedFile()+".pdf");
            
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
 
            JOptionPane.showMessageDialog(this, "Documento PDF creado exitosamente!");
 }
        }

    }//GEN-LAST:event_btnpdfActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
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
            
                        mesnn= "09"; break;
                                                 case 10:
            
                        mesnn= "10"; break;
                                                      case 11:
            
                        mesnn= "11"; break;
                                                           case 12:
            
                        mesnn= "12"; break;
                                          
                              
                                                
                  }
        int año =txtAño.getYear();
        DefaultTableModel modelo = new DefaultTableModel();
 this.tabla1.setModel(modelo);
 //Para conectarnos a nuestra base de datos
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException ex) {
            Logger.getLogger(productosmensuales.class.getName()).log(Level.SEVERE, null, ex);
        }
 Connection conexion = null;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/yourcolor", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(productosmensuales.class.getName()).log(Level.SEVERE, null, ex);
        }
 //Para ejecutar la consulta
 Statement s = null;
        try {
            s = conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(productosmensuales.class.getName()).log(Level.SEVERE, null, ex);
        }
 //Ejecutamos la consulta que escribimos en la caja de texto
 //y los datos lo almacenamos en un ResultSet
 ResultSet rs = null;
        try {
                       rs = s.executeQuery("SELECT YEAR( ventas.fecha ) AS AÑO, MONTH( ventas.fecha ) AS MES, DAY( ventas.fecha ) AS DIA, MONTHNAME( ventas.fecha ) AS NOMBRE_MES,productos.codigo,productos.nombre,sum(pedidos.can_producto) as CANTIDAD, CASE WHEN MONTH( ventas.fecha ) =1 THEN  \"Enero\" WHEN MONTH( ventas.fecha ) =2 THEN  \"Febrero\" WHEN MONTH( ventas.fecha ) =3 THEN  \"Marzo\" WHEN MONTH( ventas.fecha ) =4 THEN  \"Abril\" WHEN MONTH( ventas.fecha ) =5 THEN  \"Mayo\" WHEN MONTH( ventas.fecha ) =6 THEN  \"Junio\" WHEN MONTH( ventas.fecha ) =7 THEN  \"Julio\" WHEN MONTH( ventas.fecha ) =8 THEN  \"Agosto\" WHEN MONTH( ventas.fecha ) =9 THEN  \"Septiembre\" WHEN MONTH( ventas.fecha ) =10 THEN  \"Octubre\" WHEN MONTH( ventas.fecha ) =11 THEN  \"Noviembre\" WHEN MONTH( ventas.fecha ) =12 THEN  \"Diciembre\" ELSE  \"esto no es un mes\" END AS MESespañol FROM ventas,productos,pedidos WHERE (ventas.numero=pedidos.id_venta)and(pedidos.cod_producto=productos.codigo)and(ventas.fecha like '%"+año+"-"+mesnn+"%') group by productos.codigo order by cantidad desc");

        } catch (SQLException ex) {
            Logger.getLogger(productosmensuales.class.getName()).log(Level.SEVERE, null, ex);
        }
 //Obteniendo la informacion de las columnas que estan siendo consultadas
 ResultSetMetaData rsMd = null;
        try {
            rsMd = rs.getMetaData();
        } catch (SQLException ex) {
            Logger.getLogger(productosmensuales.class.getName()).log(Level.SEVERE, null, ex);
        }
 //La cantidad de columnas que tiene la consulta
 int cantidadColumnas = 0;
        try {
            cantidadColumnas = rsMd.getColumnCount();
        } catch (SQLException ex) {
            Logger.getLogger(productosmensuales.class.getName()).log(Level.SEVERE, null, ex);
        }
 //Establecer como cabezeras el nombre de las colimnas
 for (int i = 1; i <= cantidadColumnas; i++) {
            try {
                modelo.addColumn(rsMd.getColumnLabel(i));
            } catch (SQLException ex) {
                Logger.getLogger(productosmensuales.class.getName()).log(Level.SEVERE, null, ex);
            }
 }
        try {
            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    try {
                        fila[i]=rs.getObject(i+1);
                    } catch (SQLException ex) {
                        Logger.getLogger(productosmensuales.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                modelo.addRow(fila);
            }      } catch (SQLException ex) {
            Logger.getLogger(productosmensuales.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(productosmensuales.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(productosmensuales.class.getName()).log(Level.SEVERE, null, ex);
        }

        
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
            String cod_producto;
            String nombre;
            String cantidad;
            
            try {
                documento.add(t);
            } catch (DocumentException ex) {
                Logger.getLogger(frmVerTodosEstado.class.getName()).log(Level.SEVERE, null, ex);
            }
             t.addCell("    CODIGO");
                 t.addCell("    NOMBRE");
                t.addCell("    CANTIDAD");
            while (f < (m.getRowCount())){
                cod_producto = m.getValueAt(f, 4).toString();
                nombre = m.getValueAt(f, 5).toString();
                cantidad = m.getValueAt(f,6).toString();
                
                //Escribir Fila en archivo
                t.addCell(cod_producto);
                 t.addCell(nombre);
                t.addCell(cantidad);
               
              
                f++;
            }
            try {
                documento.add(t);
            } catch (DocumentException ex) {
                Logger.getLogger(frmVerTodosEstado.class.getName()).log(Level.SEVERE, null, ex);
            }
            //
            documento.close();
             
          JOptionPane.showMessageDialog(this, "Documento PDF creado exitosamente!");
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnpdf;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private org.edisoncor.gui.label.LabelHeader labelHeader1;
    private javax.swing.JLabel lblTorta;
    private javax.swing.JDesktopPane panelGraficoTorta;
    private javax.swing.JTable tabla1;
    private com.toedter.calendar.JYearChooser txtAño;
    private com.toedter.calendar.JMonthChooser txtMes;
    private javax.swing.JComboBox txtcant;
    // End of variables declaration//GEN-END:variables
}
