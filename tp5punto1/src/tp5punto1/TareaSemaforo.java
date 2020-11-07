/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5punto1;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Tarea la cual activa un semaforo; cambiando de color rojo a amarillo y luego verde.
 * @author Fran
 */
public class TareaSemaforo extends javax.swing.JFrame implements Runnable {
    private boolean activo;
    private int tiempoPausa;
    javax.swing.JLabel rojo,amarillo,verde;
    
    /**
     * @param r Recibe el label que contiene la luz roja del semaforo a modificar
     * @param a Recibe el label que contiene la luz amarilla del semaforo a modificar
     * @param v Recibe el label que contiene la luz verde del semaforo a modificar
     * @param tP Tiempo entre un cambio de color y otro
     */
    public TareaSemaforo(javax.swing.JLabel r, javax.swing.JLabel a, javax.swing.JLabel v, int tP){
        activo = true;
        tiempoPausa = tP;
        rojo = r;
        amarillo = a;
        verde = v;
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
                Thread.sleep(tiempoPausa);
                if(activo==true){
                    rojo.setForeground(Color.white);
                    amarillo.setForeground(Color.yellow);
                }
                Thread.sleep(tiempoPausa);
                if(activo==true){
                    amarillo.setForeground(Color.white);
                    verde.setForeground(Color.green);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(TareaAutos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}