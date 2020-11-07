/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Fran
 */
public class IniciarArchivo {
    private static File archivoProveedores = new File("proveedores.xml");
    private static File archivoStock = new File("stock.xml");
    private static File archivoClientes = new File("clientes.xml");
    private static File archivoVentas = new File("ventas.xml");
    
    /**
     * Este metodo se encarga de inicializar todos los archivos xml que no tengan ningun contenido.
     */
    public void Iniciar(){
        if(archivoProveedores.length()==0){
            Document doc = new Document();
            Element root = new Element("Proveedores");
            doc.setRootElement(root);
            try {
                XMLOutputter salidaXML = new XMLOutputter();
                salidaXML.setFormat(Format.getPrettyFormat());
                salidaXML.output(doc, new FileWriter(archivoProveedores,false));
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(archivoStock.length()==0){
            Document doc = new Document();
            Element root = new Element("Stock");
            doc.setRootElement(root);
            try {
                XMLOutputter salidaXML = new XMLOutputter();
                salidaXML.setFormat(Format.getPrettyFormat());
                salidaXML.output(doc, new FileWriter(archivoStock,false));
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(archivoClientes.length()==0){
            Document doc = new Document();
            Element root = new Element("Clientes");
            doc.setRootElement(root);
            try {
                XMLOutputter salidaXML = new XMLOutputter();
                salidaXML.setFormat(Format.getPrettyFormat());
                salidaXML.output(doc, new FileWriter(archivoClientes,false));
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(archivoVentas.length()==0){
            Document doc = new Document();
            Element root = new Element("Ventas");
            doc.setRootElement(root);
            try {
                XMLOutputter salidaXML = new XMLOutputter();
                salidaXML.setFormat(Format.getPrettyFormat());
                salidaXML.output(doc, new FileWriter(archivoVentas,false));
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
