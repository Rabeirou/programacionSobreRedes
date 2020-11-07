/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2punto2;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Fran
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MySQL sql = new MySQL("localhost", "evaluaciones_online", "root", "");
        
        sql.SQLaXML("examenes.xml", "evaluaciones_online", "examenes", sql.nombreDeCampos("examenes"), sql.select("examenes"));
        sql.SQLaXML("usuarios.xml", "evaluaciones_online", "usuarioss", sql.nombreDeCampos("usuarioss"), sql.select("usuarioss"));
        
        File examenesXML =new File("examenes.xml");
        File usuariosXML =new File("usuarios.xml");
        
        sql.mostrarXML(usuariosXML);
        System.out.println("\n\n");
        sql.mostrarXML(examenesXML);
    }
    
}
