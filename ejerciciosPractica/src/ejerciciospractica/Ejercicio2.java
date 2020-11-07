/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciospractica;

/**
 *
 * @author Fran
 */
public class Ejercicio2 {
    public void menu(){
        Verificacion ver = new Verificacion();
        int opcion = -1;
        opcion = ver.verificar("Ingrese el numero de subEjercicio: ", 1, 8);
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
            case 8:
                ejercicio8();
                break;
            default:
                System.out.println("uwu");
        }
    }
    
    private void ejercicio1(){
        System.out.println("Ejercicio a");
    }
    private void ejercicio2(){
        System.out.println("Ejercicio b");
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
    private void ejercicio8(){
        System.out.println("Ejercicio h");
    }
}
