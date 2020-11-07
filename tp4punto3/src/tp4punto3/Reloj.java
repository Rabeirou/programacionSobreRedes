/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4punto3;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;



/**
 * Esta clase se encarga de ejecutar un hilo el cual instancia un reloj y lo muestra en los labels que recibe
 * @author Fran
 */
public class Reloj extends Thread{
    private boolean activado = true;
    private JLabel hora,min,seg;
    
    /**
     * @param h Label que contiene las horas
     * @param m Label que contiene los minutos
     * @param s Label que contiene los segundos
     */
    public Reloj(JLabel h, JLabel m, JLabel s)
    {
        this.hora = h;
        this.min = m;
        this.seg = s;
    }
    
    public void desactivar(){
        activado = false;
    }
    
    @Override
    public void run()
    {
        while(activado==true){
            try {
                Thread.sleep(1000);
                if(Integer.parseInt(seg.getText()) < 59){
                    seg.setText(String.format("%02d",Integer.parseInt(seg.getText())+1));
                }else{
                    seg.setText("00");
                    if(Integer.parseInt(min.getText()) < 59){
                        min.setText(String.format("%02d",Integer.parseInt(min.getText())+1));
                    }else{
                        min.setText("00");
                        hora.setText(String.format("%02d",Integer.parseInt(hora.getText())+1));
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Reloj.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
