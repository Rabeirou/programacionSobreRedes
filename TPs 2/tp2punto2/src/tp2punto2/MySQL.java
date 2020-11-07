/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2punto2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Fran
 * Clase para controlar una base de datos con MySQL
 */
public class MySQL {
    protected Connection conn;
    public String db;
    
    /**
     * Ejecuta la conexion con la base de datos
     * @param ruta Ruta donde se va a realizar la conexion
     * @param dataBase Nombre de la base de datos
     * @param user Usuario para conectarse con la db
     * @param pass Contraseña para conectarse con la db
     */
    public MySQL(String ruta, String dataBase, String user, String pass){
        this.db = dataBase;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String url = "jdbc:mysql://"+ruta+":3306/"+db+"?zeroDateTimeBehavior=convertToNull";
        
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet query(String query){
        ResultSet Rs;
        try {
            PreparedStatement Ps = conn.prepareStatement(query);
            Rs = Ps.executeQuery();
            return Rs;
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Select basico con tabla personalizada
     * @param tabla tabla a la cual se le van a pedir todos los datos
     * @return devuelve los registros encontrados. Si no encuentra ninguno devuelve null
     */
    public ResultSet select(String tabla){
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tabla);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Select basico con tabla personalizada y condicion
     * @param tabla tabla a la cual se le van a pedir todos los datos
     * @param condicion condicion utilizada a la hora de pedir los registros
     * @return devuelve los registros encontrados. Si no encuentra ninguno devuelve null
     */
    public ResultSet select(String tabla, String condicion){
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tabla + " WHERE " + condicion);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * delete basico con tabla y condicion
     * @param tabla tabla a la cual se le van a pedir todos los datos
     * @param condicion condicion utilizada a la hora de borrar los registros
     */
    public void delete(String tabla, String condicion){
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tabla + " WHERE " + condicion);
            if(ps.executeUpdate()==-1) System.err.println("\nError. No se ha podido eliminar ningun registro en la tabla "+tabla+".");
            else System.out.println("\nRegistro/s eliminado con exito en la tabla "+tabla+".");
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @param tabla tabla a la cual se le van a pedir todos los datos
     * @param campos ArrayList<String> el cual almacena los campos a modificar 
     * @param datos ArrayList<Object> el cual almacena los datos nuevos
     * @param condicion condicion utilizada a la hora de modificar los registros
     */
    public void update(String tabla, ArrayList<String> campos, ArrayList<Object> datos, String condicion){
        String query = "UPDATE " + tabla + " SET ";
        for(int i=0; i<campos.size(); i++){
            query = query + campos.get(i) + " = " + datos.get(i) + ",";
        }
        query = query.substring(0,query.length()-1);
        query = query + " WHERE " + condicion;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            if(ps.executeUpdate()==-1) System.err.println("\nError. No se ha podido modificar ningun registro en la tabla "+tabla+".");
            else System.out.println("\nRegistro/s modificado/s con exito en la tabla "+tabla+".");
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @param tabla tabla a la cual se le van a pedir todos los datos
     * @param campos ArrayList<String> el cual almacena los campos en los cuales se van a insertar los datos 
     * @param datos  ArrayList<Object> el cual almacena los datos a insertar
     */
    public void insert(String tabla, ArrayList<String> campos, ArrayList<Object> datos){
        String query = "INSERT INTO " + tabla + "(";
        for(String campo : campos){
            query = query + campo + ",";
        }
        query = query.substring(0,query.length()-1) + ") VALUES (";
        for(Object dato : datos){
            query = query + dato + ",";
        }
        query = query.substring(0,query.length()-1) + ")";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            if(ps.executeUpdate()==-1) System.err.println("\nError. No se ha podido insertar el registro en la tabla "+tabla+".");
            else System.out.println("\nRegistro insertado con exito en la tabla "+tabla+".");
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List nombreDeCampos(String tabla){
        String query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS "
                        + "WHERE TABLE_NAME = '" + tabla + "'";
        
        ResultSet rs = this.query(query);
        List campos = new ArrayList();
        
        try {
            while( rs.next() )
            {    
                campos.add(rs.getObject(1));
            }
            return campos;
        } catch (SQLException ex) {
                Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void SQLaXML(String ruta, String DB, String tabla, List columnas, ResultSet pRegistros){     
        Element root = new Element(DB);
        Document doc = new Document(root);
        
        try {  
            while(pRegistros.next()){
                Element registro = new Element( tabla );
                registro.setAttribute(new Attribute(columnas.get(0).toString(), pRegistros.getString(columnas.get(0).toString())));

                for (int i = 1; i < columnas.size() ; i++){   
                    Element aux = new Element(columnas.get(i).toString());
                    aux.setText(pRegistros.getString(columnas.get(i).toString()));
                    registro.addContent(aux);
                }
                
                doc.getRootElement().addContent(registro);      
            }
            XMLOutputter out = new XMLOutputter();
            out.setFormat(Format.getPrettyFormat());
            out.output(doc, new FileWriter(ruta));
        } catch (SQLException | IOException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void mostrarXML(File archivoXML){
        try {
            SAXBuilder constructor = new SAXBuilder();
            
            Document doc = (Document) constructor.build(archivoXML);
            Element root = doc.getRootElement();
                
            List<Element> lista = root.getChildren();
            
            for(Element item : lista ){
                if(item.getAttributes().isEmpty()){   
                    System.out.println(item.getName() + "\t" + item.getValue() + "\t");
                }else{
                    System.out.print("\n" + item.getName());
                    for(Attribute at : item.getAttributes()){
                        System.out.print("\t" + at.getName() + ": " + at.getValue());
                    }

                    for(Element subItem : item.getChildren()){
                        if(!subItem.getAttributes().isEmpty() ){
                            System.out.print("\t" + subItem.getName() + subItem.getAttributes().get(0).getName() + ": " + subItem.getAttributes().get(0).getValue());
                            
                            System.out.print("\t" + subItem.getName() + ": " + subItem.getValue());
                        }else{
                            System.out.print("\t" + subItem.getName() + ": " + subItem.getValue());
                        }
                    }                   
                }
            }
        } catch (JDOMException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
