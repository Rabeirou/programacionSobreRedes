/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2punto1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
}
