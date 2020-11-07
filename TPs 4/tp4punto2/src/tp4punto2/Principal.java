/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4punto2;

/**
 *
 * @author Fran
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tarea tarea1 = new Tarea("hola", 3000);
        Tarea tarea2 = new Tarea("1000", 1000);
        Tarea tarea3 = new Tarea("Soy un hilo y me ejecuto en 2do plano", 10000);
        
        Thread th1 = new Thread(tarea1);
        Thread th2 = new Thread(tarea2);
        Thread th3 = new Thread(tarea3);
        
        th1.start();
        th2.start();
        th3.start();
    }
    
}
