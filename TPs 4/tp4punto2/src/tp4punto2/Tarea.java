/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4punto2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fran
 */
public class Tarea implements Runnable {
    private String texto;
    private boolean activo;
    private int tiempoPausa;
    
    public Tarea(String t, int tP){
        activo = true;
        this.texto = t;
        this.tiempoPausa = tP;
    }
    
    public void desactivar(){
        activo = false;
    }
    
    @Override
    public void run(){
        while(activo==true){
            System.out.println(texto);
            try {
                Thread.sleep(tiempoPausa);
            } catch (InterruptedException ex) {
                Logger.getLogger(Tarea.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
