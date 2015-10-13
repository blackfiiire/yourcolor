/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenedores;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.io.*;


/**
 *
 * @author Kampu
 */
public class PDFtodos extends JFrame{
    
    JButton guardarPDF;
    JPanel panel;
    JFileChooser seleccionar_archivo;
    JEditorPane editor;
    
    public PDFtodos(){
        panel=new JPanel();
        seleccionar_archivo=new JFileChooser();
        editor=new JEditorPane();
        guardarPDF=new JButton("Guardar PDF...");
        
        guardarPDF.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            
                int opcion=seleccionar_archivo.showSaveDialog(null);
                if(opcion==seleccionar_archivo.APPROVE_OPTION){
                
                    try{                       
                        OutputStream texto_salida=new FileOutputStream(seleccionar_archivo.getSelectedFile());
                        Document doc=new Document();
                        PdfWriter.getInstance(doc, texto_salida);
                        doc.open();
                        doc.add(new Paragraph(editor.getText()));
                        doc.close();
                        texto_salida.close();
                     }catch(Exception ex){}
                        
                }
            }
            });
   panel.add(guardarPDF);
   this.add(panel,BorderLayout.NORTH);
   
   this.add(editor,BorderLayout.CENTER);
   this.setSize(300,400);
   this.setVisible(true);
   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
    
    public static void main(String[] args){
        new PDFtodos();
    }
}
