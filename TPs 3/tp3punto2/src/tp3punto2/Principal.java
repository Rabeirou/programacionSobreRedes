/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3punto2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
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
 */
public class Principal {

    private final static File agenda = new File("agenda.xml");
    private final static File agendaAux = new File("agendaAux.xml");
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        iniciar();
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
    
    public static void iniciar(){
        if(agenda.length()==0){
            Document doc = new Document();
            Element root = new Element("Agenda");
            doc.setRootElement(root);
            try {
                XMLOutputter salidaXML = new XMLOutputter();
                salidaXML.setFormat(Format.getPrettyFormat());
                salidaXML.output(doc, new FileWriter(agenda,false));
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(agendaAux.length()==0){
            Document doc = new Document();
            Element root = new Element("Agenda");
            doc.setRootElement(root);
            try {
                XMLOutputter salidaXML = new XMLOutputter();
                salidaXML.setFormat(Format.getPrettyFormat());
                salidaXML.output(doc, new FileWriter(agendaAux,false));
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
                System.out.println("\nIngrese todos los datos del contacto separados por un espacio en blanco en el siguiente orden (id nombre apellido telefono email)[Para salir escriba \"salir\"]: ");
                aux = br1.readLine();
                if(aux.split(" ").length == 5 && !aux.trim().equals("salir"))datosString = aux;
                datos = datosString.split(" ");
                if(datosString.split(" ").length != 5 && !aux.trim().equals("salir")){
                    System.out.println("\nCantidad erronea de datos ingresados. Vuelva a intentarlo.\n");
                }
            }
            //Agrega el contacto al archivo agenda.txt
            if(!aux.trim().equals("salir")){
                SAXBuilder constructor = new SAXBuilder();
                Document doc;
                doc = (Document) constructor.build(agenda);
            
                Element contacto = new Element("Contacto");
                contacto.setAttribute(new Attribute("id", datos[0]));
                contacto.addContent(new Element("nombre").setText(datos[1]));
                contacto.addContent(new Element("apellido").setText(datos[2]));
                contacto.addContent(new Element("telefono").setText(datos[3]));
                contacto.addContent(new Element("mail").setText(datos[4]));

                doc.getRootElement().addContent(contacto);

                XMLOutputter salidaXML = new XMLOutputter();
                salidaXML.setFormat(Format.getPrettyFormat());
                salidaXML.output(doc, new FileWriter(agenda));
                
                System.out.println("\nContacto agregado:\n" + getContacto(datos[0]));
            }
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JDOMException ex) {
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
            fw = new FileWriter(agendaAux, false);
            iniciar();
            scan = new Scanner(System.in);
            String id;
            System.out.println("\nIngrese la id del contacto que desea eliminar [Si desea salir, ingrese \"salir\"]:\n");
            id = scan.next();
            //Verifica que el usuario no quiera volver al menu
            if(!id.equals("salir")){
                SAXBuilder constructor = new SAXBuilder();
                Document doc = (Document) constructor.build(agenda);
                Document doc2 = (Document) constructor.build(agendaAux);
                List<Element> lista = doc.getRootElement().getChildren("Contacto");
                for(Element cont : lista){
                    Attribute a = cont.getAttribute("id");
                    if(!a.getValue().equals(id)) {
                        Element contacto = new Element("Contacto");
                        contacto.setAttribute(new Attribute("id", cont.getAttribute("id").getValue()));
                        contacto.addContent(new Element("nombre").setText(cont.getChildText("nombre")));
                        contacto.addContent(new Element("apellido").setText(cont.getChildText("apellido")));
                        contacto.addContent(new Element("telefono").setText(cont.getChildText("telefono")));
                        contacto.addContent(new Element("mail").setText(cont.getChildText("mail")));

                        doc2.getRootElement().addContent(contacto);
                    }
                }
                XMLOutputter salidaXML = new XMLOutputter();
                salidaXML.setFormat(Format.getPrettyFormat());
                salidaXML.output(doc2, new FileWriter(agendaAux));
                pasarAgenda();
            }
        } catch (JDOMException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    private static void mostrarAgenda(){
        SAXBuilder constructor = new SAXBuilder();
        try {
            Document doc = (Document) constructor.build(agenda);
            Element root = doc.getRootElement();
            List<Element> elementos = root.getChildren("Contacto");
            System.out.println("\n[LISTA DE CONTACTOS]\n");
            for(Element item : elementos){ 
                System.out.println("\nID: "+item.getAttribute("id").getValue()+
                       "\tNombre: "+item.getChildText("nombre")+
                       "\tApellido: "+item.getChildText("apellido")+
                       "\tTelefono: "+item.getChildText("telefono")+
                       "\tMail: "+item.getChildText("mail")+"\n");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JDOMException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Devuelve un contacto en un String en base a la ID que se le pasa
    private static String getContacto(String id){
        SAXBuilder constructor = new SAXBuilder();
        
        try {
            Document doc = (Document) constructor.build(agenda);
            Element root = doc.getRootElement();
            List<Element> elementos = root.getChildren("Contacto");
            for(Element item : elementos){
                if(item.getAttribute("id").getValue().equals(id)){
                    return "\nID: "+item.getAttribute("id").getValue()+
                           "\tNombre: "+item.getChildText("nombre")+
                           "\tApellido: "+item.getChildText("apellido")+
                           "\tTelefono: "+item.getChildText("telefono")+
                           "\tMail: "+item.getChildText("mail")+"\n";
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JDOMException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "\nHubo un error al buscar el contacto";
    }
    
}
