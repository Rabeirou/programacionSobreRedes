/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3punto1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fran
 */
public class Principal {

    private final static File agenda = new File("agenda.txt");
    private final static File agendaAux = new File("agendaAux.txt");
    private final static File idHolder = new File("id.txt");
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opcion = 3;
        Verificacion ver = new Verificacion();
        while(opcion <= 3 && opcion >= 1){
            opcion = ver.verificar("[MENU AGENDA]\n1- Agregar contacto\n2- Eliminar contacto\n3- Mostrar agenda\n4- Salir\nRespuesta: ", 1, 4);
            switch(opcion){
                case 1:
                    agregarContacto();
                    break;
                case 2:
                    eliminarContacto();
                    break;
                case 3:
                    mostrarAgenda();
                    break;
                default:
                    System.out.println("\n\nAdeus!");
            }
        }
    }
    
    //Se encarga de identificar la ultima ID disponible, la ID para el siguiente contacto y de aumentar la ID para el que sigue
    private static String identificarId(boolean aumentar, boolean disponible){
        FileWriter fw,fw2;
        PrintWriter pw;
        FileReader fr;
        BufferedReader br;
        String aux;
        String[] aux2;
        int id = 0;
        try {
            //Busca la ID mas alta disponible entre los contactos
            if(disponible){
                fw = new FileWriter(agenda, true);
                fr = new FileReader(agenda);
                br = new BufferedReader(fr);
                while((aux=br.readLine()) != null){
                    aux2 = aux.split("<-\\*->");
                    id = Integer.parseInt(aux2[1]);
                }
            //Busca la ultima ID que fue utilizada(No necesariamente tiene que ser la mas alta disponible en los contactos)    
            }else{
                fw2 = new FileWriter(idHolder, true);
                fr = new FileReader(idHolder);
                br = new BufferedReader(fr); 
                if((aux=br.readLine()) != null){
                    id = Integer.parseInt(aux);
                }
                //Aumenta en 1 la ID para el siguiente contacto
                if(aumentar){
                    fw = new FileWriter(idHolder, false);
                    pw = new PrintWriter(fw);
                    id++;
                    pw.print(Integer.toString(id));
                    pw.flush();
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Integer.toString(id);
    }
    
    private static void agregarContacto(){
        String[] datos = new String[5];
        String datosString = "";
        String aux = "";
        InputStreamReader reader1;
        BufferedReader br1;
        FileWriter fw;
        PrintWriter pw;
        try {
            fw = new FileWriter(agenda,true);
            pw = new PrintWriter(fw);
            reader1 = new InputStreamReader(System.in);
            br1 = new BufferedReader(reader1);
            //Verifica que los datos ingresados sean correctos y si el usuario quiere volver al menu
            while(datosString.split(" ").length != 5 && !aux.trim().equals("salir")){
                System.out.println("\nIngrese todos los datos del contacto separados por un espacio en blanco en el siguiente orden (nombre apellido telefono email)[Para salir escriba \"salir\"]: ");
                aux = br1.readLine();
                if(aux.split(" ").length == 4 && !aux.trim().equals("salir"))datosString = identificarId(true,false) + " " + aux;
                datos = datosString.split(" ");
                if(datosString.split(" ").length != 5 && !aux.trim().equals("salir")){
                    System.out.println("\nCantidad erronea de datos ingresados. Vuelva a intentarlo.\n");
                }
            }
            //Agrega el contacto al archivo agenda.txt
            if(!aux.trim().equals("salir")){
                pw.print("<-*->");
                for(int i = 0; i < datos.length; i++){
                    pw.print(datos[i]+"<-*->");
                }
                pw.println();
                pw.flush();
                System.out.println("\nContacto agregado:\n" + getContacto(identificarId(false,false)));
            }
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //Se encarga de hacer la copia de agendaAux.txt al original (agenda.txt)
    private static void pasarAgenda(){
        FileWriter fw;
        PrintWriter pw;
        Scanner scan;
        try {
            fw = new FileWriter(agenda, false);
            pw = new PrintWriter(fw);
            scan = new Scanner(agendaAux);
            while(scan.hasNextLine()){
                pw.println(scan.nextLine());
            }
            pw.flush();
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void eliminarContacto(){
        FileWriter fw;
        PrintWriter pw;
        Scanner scan, scan2;
        Verificacion ver;
        try {
            //Verifica si hay contactos disponibles para eliminar
            if(Integer.parseInt(identificarId(false,true)) > 0){
                fw = new FileWriter(agendaAux, false);
                pw = new PrintWriter(fw);
                scan2 = new Scanner(agenda);
                scan = new Scanner(agenda);
                ver = new Verificacion();
                int id;
                id = ver.verificar("\nIngrese la id del contacto que desea eliminar (maxima id disponible: "+(Integer.parseInt(identificarId(false,true)))+")[Si desea salir, ingrese 0]:\n", 0, Integer.parseInt(identificarId(false,true)));
                //Verifica que el usuario no quiera volver al menu
                if(id != 0){
                    String idString = Integer.toString(id);
                    String contactoEliminado = getContacto(idString); //Guarda el contacto a eliminar para luego mostrarlo en mensaje una vez eliminado
                    scan.useDelimiter("<-\\*->");
                    String aux, aux2, aux3;
                    boolean verificador = false; //Verifica que el contacto se haya eliminado correctamente
                    while(scan.hasNext()){
                        aux = scan.next();
                        //Si entra al if se pasan todos los datos de agenda.txt a agendaAux.txt exceptuando el contacto a eliminar
                        if(aux.trim().equals(idString)){
                            aux2 = "<-*->"+aux + scan.nextLine();
                            while(scan2.hasNextLine()){
                                aux3 = scan2.nextLine();
                                if(!aux3.trim().equals(aux2)){
                                    pw.println(aux3);
                                    pw.flush();
                                }
                            }
                            pasarAgenda(); //Llamada al metodo para volver a pasar los datos en limpio a agenda.txt
                            verificador = true; //El contacto se elimino correctamente
                        }
                    }
                    if(verificador) System.out.println("\nContacto eliminado:\n" + contactoEliminado);
                    else System.out.println("\nHubo un error al eliminar el contacto. Puede que el contacto no exista.\n");;
                }
            }else System.out.println("\nNo hay contactos.");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void mostrarAgenda(){
        FileWriter fw;
        Scanner scan;
        try {
            fw = new FileWriter(agenda, true);
            scan = new Scanner(agenda);
            scan.useDelimiter("<-\\*->");
            System.out.println("\nLISTA DE CONTACTOS:\n");
            while(scan.hasNextLine()){
                System.out.println("ID: "+scan.next()+"\tNombre: "+scan.next()+"\tApellido: "+scan.next()+"\tTelefono: "+scan.next()+"\tMail: "+scan.next()+"\n");
                scan.next();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Devuelve un contacto en un String en base a la ID que se le pasa
    private static String getContacto(String id){
        Scanner scan;
        try {
            scan = new Scanner(agenda);
            scan.useDelimiter("<-\\*->");
            String aux, aux2, aux3;
            while(scan.hasNext()){
                aux = scan.next();
                if(aux.trim().equals(id)){
                    return "\nID: "+aux+"\tNombre: "+scan.next()+"\tApellido: "+scan.next()+"\tTelefono: "+scan.next()+"\tMail: "+scan.next()+"\n";
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "\nHubo un error al buscar el contacto";
    }
    
}
