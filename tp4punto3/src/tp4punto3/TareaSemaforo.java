/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4punto3;

import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Tarea la cual activa un semaforo; cambiando de color rojo a amarillo y luego verde en base a los segundos del reloj.
 * Cuando los segundos del reloj estan en 00 cambia a verde, cuando estan en 30 cambia a rojo
 * @author Fran
 */
public class TareaSemaforo extends Thread {
    private boolean activo;
    private int tiempoPausa, segsNum;
    javax.swing.JLabel rojo,amarillo,verde,seg;
    
    /**
     * @param r Recibe el label que contiene la luz roja del semaforo a modificar
     * @param a Recibe el label que contiene la luz amarilla del semaforo a modificar
     * @param v Recibe el label que contiene la luz verde del semaforo a modificar
     * @param s Recibe el label que contiene los segundos del reloj
     * @param tP Tiempo entre un cambio de color y otro 
     */
    public TareaSemaforo(JLabel r, JLabel a, JLabel v, JLabel s, int tP){
        this.activo = true;
        this.tiempoPausa = tP;
        this.rojo = r;
        this.amarillo = a;
        this.verde = v;
        this.seg = s;
    }
    
    /**
     * Desactiva la tarea
     */
    public void desactivar(){
        activo = false;
    }
    
    @Override
    public void run(){
        while(activo==true){
            try {
                segsNum = Integer.parseInt(seg.getText());
                System.out.println(segsNum); //por algun motivo si no muestro los segundos en la consola no realiza los cambios de color.
                if(Integer.parseInt(seg.getText()) == 00){
                    rojo.setIcon(null);
                    rojo.setText(null);
                    amarillo.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("amarillo.png")).getImage()));
                    Thread.sleep(tiempoPausa);
                    amarillo.setIcon(null);
                    amarillo.setText(null);
                    verde.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("verde.png")).getImage()));
                }else if(Integer.parseInt(seg.getText()) == 30){
                    verde.setIcon(null);
                    verde.setText(null);
                    amarillo.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("amarillo.png")).getImage()));
                    Thread.sleep(tiempoPausa);
                    amarillo.setIcon(null);
                    amarillo.setText(null);
                    rojo.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("rojo.png")).getImage()));
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(TareaSemaforo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}