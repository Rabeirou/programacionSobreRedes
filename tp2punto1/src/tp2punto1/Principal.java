/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2punto1;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
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
 */
public class Principal {
    
    static MySQL sql = new MySQL("localhost", "evaluaciones_online", "root", "");
    
    static ArrayList<String> campos = new ArrayList<>();
    static ArrayList<Object> datos = new ArrayList<>();
    /**
     * @param args the command line arguments
     * Se encarga de ejecutar el menu principal
     */
    public static void main(String[] args) {
        int opcion = 2;
        Verificacion ver = new Verificacion();
        while(opcion <= 4 && opcion >= 1){
            opcion = ver.verificar("[MENU PRINCIPAL]\n1- Registrar usuario\n2- Inscribir a examen\n3- Mostrar notas\n4- Cambiar contraseña\n5- Salir\nRespuesta: ", 1, 5);
            switch(opcion){
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    inscribirExamen();
                    break;
                case 3:
                    mostrarNotas();
                    break;
                case 4:
                    cambiarPass();
                    break;
                default:
                    System.out.println("\n\nAdeus!");
            }
        }
        
    }
    
    /**
     * Prepara la lista de campos para ser compatible con la tabla de usuarios
     */
    public static void iniciarCamposUsuarios(){
        campos.clear();
        campos.add("nombre");
        campos.add("apellido");
        campos.add("pass");
        campos.add("dni");
        campos.add("docente");
        campos.add("usuario");
    }
    
    /**
     * Prepara la lista de campos para ser compatible con la tabla de examenes
     */
    public static void iniciarCamposExamenes(){
        campos.clear();
        campos.add("usuario");
        campos.add("materia");
        campos.add("docenteid");
        campos.add("nota");
    }
    
    /**
     * Registra al usuario en la tabla de usuarios
     */
    public static void registrarUsuario(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        iniciarCamposUsuarios();
        datos.clear();
        
        try {
            System.out.println("\n[REGISTRO DE USUARIO]\n");
            System.out.println("Ingrese su nombre: ");
            datos.add("'"+br.readLine()+"'");
            System.out.println("Ingrese su apellido: ");
            datos.add("'"+br.readLine()+"'");
            System.out.println("Ingrese su contraseña: ");
            datos.add("'"+br.readLine()+"'");
            System.out.println("Ingrese su DNI: ");
            datos.add(parseInt(br.readLine()));
            System.out.println("Ingrese 1 si es docente, 0 si es estudiante: ");
            datos.add(parseInt(br.readLine()));
            System.out.println("Ingrese su usuario: ");
            datos.add("'"+br.readLine()+"'");
            
            sql.insert("usuarios", campos, datos);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Inscribe a un alumno en un examen, dejandolo elegir la materia y el profesor
     */
    public static void inscribirExamen(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        ResultSet rs = null, rs2 = null;
        String usuario,pass,materia;
        int docente;
        float nota;
        
        System.out.println("\n[INSCRIPCION A EXAMEN]");
        
        try {
            System.out.println("\nIngrese su usuario: ");
            usuario = "'"+br.readLine()+"'";
            System.out.println("Ingrese su contraseña: ");
            pass = "'"+br.readLine()+"'";
            
            rs = sql.select("usuarios", "usuario = " + usuario + " and pass = " + pass);
            
            if(rs.next()){
                System.out.println("\nIngrese la materia en la cual dara el examen: ");
                materia = "'"+br.readLine()+"'";
                rs2 = sql.select("usuarios", "docente = 1");
                System.out.println("\nDocentes disponibles: ");
                while(rs2.next()){
                    System.out.println("ID: " + rs2.getInt("id") + "\tNombre completo: " + rs2.getString("nombre") + " " + rs2.getString("apellido"));
                }
                System.out.println("\nIngrese la ID del docente que quiere para rendir el examen: ");
                docente = parseInt(br.readLine());
                nota = (float)Math.random() * 10;
                
                iniciarCamposExamenes();
                datos.clear();
                datos.add(usuario);
                datos.add(materia);
                datos.add(docente);
                datos.add(nota);
                
                sql.insert("examenes", campos, datos);
            }else{
                System.out.println("\nNo se ha encontrado ningun usuario.");
            }
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Muestra las notas de los examenes ordenadas por profesor y materia
     */
    public static void mostrarNotas(){
        System.out.println("\n[NOTAS DE EXAMENES]");
        ResultSet rs = sql.select("examenes", "1 ORDER BY docenteid, materia");
        ResultSet rs2 = null;
        try {
            while(rs.next()){
                rs2 = sql.select("usuarios", "id = " + rs.getInt("docenteid"));
                if(rs2.next())
                System.out.println("\nUsuario del alumno: " + rs.getString("usuario") + "\tDocente: " + rs2.getString("nombre") + " " + rs2.getString("apellido") + "\tMateria: " + rs.getString("materia") + "\tNota: " + rs.getFloat("nota"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Cambia la contraseña del usuario luego de que este entra con los datos de su cuenta
     */
    public static void cambiarPass(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        ResultSet rs = null;
        String usuario,pass,nuevaPass;
        
        datos.clear();
        campos.clear();
        
        System.out.println("\n[CAMBIAR CONTRASEÑA]");
        
        try {
            System.out.println("\nIngrese su usuario: ");
            usuario = "'"+br.readLine()+"'";
            System.out.println("Ingrese su contraseña: ");
            pass = "'"+br.readLine()+"'";
            rs = sql.select("usuarios", "usuario = " + usuario + " and pass = " + pass);
            if(rs.next()){
                System.out.println("\nIngrese su nueva contraseña: ");
                nuevaPass = "'"+br.readLine()+"'";
                campos.add("pass");
                datos.add(nuevaPass);
                sql.update("usuarios", campos, datos, "pass = " + pass);
            }else{
                System.out.println("\nNo se ha encontrado ningun usuario.");
            }
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
