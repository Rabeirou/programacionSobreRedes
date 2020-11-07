/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciospractica;

import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fran
 */
public class Ejercicio1 {
    public void menu(){
        Verificacion ver = new Verificacion();
        int opcion = -1;
        opcion = ver.verificar("Ingrese el numero de subEjercicio: ", 1, 7);
        switch(opcion){
            case 1:
                ejercicio1();
                break;
            case 2:
                ejercicio2();
                break;
            case 3:
                ejercicio3();
                break;
            case 4:
                ejercicio4();
                break;
            case 5:
                ejercicio5();
                break;
            case 6:
                ejercicio6();
                break;
            case 7:
                ejercicio7();
                break;
            default:
                System.out.println("uwu");
        }
    }
    
    private void ejercicio1(){
        System.out.println("\nEjercicio a:");
        double valor, sueldo;
        int horas = 0;
        PrintStream ps = new PrintStream(System.out);
        ps.print("\nIngrese la cantidad de horas trabajadas: ");
        int aux = -1;
        String s = "";
        try {
            while((aux=System.in.read()) != '\n'){
                s += Character.toString((char)aux);
            }
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        }
        horas = Integer.parseInt(String.valueOf(s));
        ps.print("\nIngrese el valor de cada hora: ");
        s = "";
        try {
            while((aux=System.in.read()) != '\n'){
                s += Character.toString((char)aux);
            }
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        }
        valor = Double.parseDouble(String.valueOf(s));
        ps.println("\nEl sueldo bruto es: $"+ (double)horas * valor);
    }
    private void ejercicio2(){
        System.out.println("\nEjercicio b:");
        int angulo1,angulo2;
        PrintStream ps = new PrintStream(System.out);
        ps.print("\nIngrese el tamaño del primer angulo: ");
        int aux = -1;
        String s = "";
        try {
            while((aux=System.in.read()) != '\n'){
                s += Character.toString((char)aux);
            }
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        }
        angulo1 = Integer.parseInt(String.valueOf(s));
        ps.print("\nIngrese el tamaño del segundo angulo: ");
        s = "";
        try {
            while((aux=System.in.read()) != '\n'){
                s += Character.toString((char)aux);
            }
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        }
        angulo2 = Integer.parseInt(String.valueOf(s));
        ps.println("\nEl tamaño del angulo restante es: " + (180-(angulo1+angulo2)));
    }
    private void ejercicio3(){
        System.out.println("Ejercicio c");
    }
    private void ejercicio4(){
        System.out.println("Ejercicio d");
    }
    private void ejercicio5(){
        System.out.println("Ejercicio e");
    }
    private void ejercicio6(){
        System.out.println("Ejercicio f");
    }
    private void ejercicio7(){
        System.out.println("Ejercicio g");
    }
}
