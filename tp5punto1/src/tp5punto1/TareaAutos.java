/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5punto1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La tarea mueve 2 autos luego de que se active un semaforo.
 * Uno de los autos va de derecha a izquierda
 * El otro va de izquierda a derecha
 * @author Fran
 */
public class TareaAutos extends javax.swing.JFrame implements Runnable {
    private boolean activo;
    private int tiempoPausa, tiempoSemaforo;
    javax.swing.JLabel autoDer, autoIzq;
    
    /**
     * @param der Label contenedor del auto derecho.
     * @param izq Label contenedor del auto izquierdo.
     * @param tP Tiempo de pausa entre un movimiento del auto y otro. Suelen ser tiempos cortos para que de la sensacion de fluidez.
     * @param tS Tiempo del semaforo. Luego de que este acabe, los autos se empezaran a mover.
     */
    public TareaAutos(javax.swing.JLabel der, javax.swing.JLabel izq, int tP, int tS){
        activo = true;
        tiempoPausa = tP;
        autoDer = der;
        autoIzq = izq;
        tiempoSemaforo = tS;
    }
    
    /**
     * Desactiva la tarea
     */
    public void desactivar(){
        activo = false;
    }
    
    @Override
    public void run(){
        if(activo==true){
            try {
                Thread.sleep(tiempoSemaforo);
                while(activo==true){
                    autoDer.setLocation(autoDer.getX()-10, autoDer.getY());
                    autoIzq.setLocation(autoIzq.getX()+10, autoIzq.getY());
                    Thread.sleep(tiempoPausa);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(TareaAutos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
