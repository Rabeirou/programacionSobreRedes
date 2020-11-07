/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciospractica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fran
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Verificacion ver = new Verificacion();
        int opcion = -1;
        Ejercicio1 ej1 = new Ejercicio1();
        Ejercicio2 ej2 = new Ejercicio2();
        Ejercicio3 ej3 = new Ejercicio3();
        opcion = ver.verificar("Ingrese el numero de ejercicio: ", 1, 3);
        switch(opcion){
            case 1:
                ej1.menu();
                break;
            case 2:
                ej2.menu();
                break;
            case 3:
                ej3.menu();
                break;
            default:
                System.out.println("uwu");
        }
        
    }
    
}
