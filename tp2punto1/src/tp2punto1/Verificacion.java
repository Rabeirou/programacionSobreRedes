/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2punto1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Fran
 * Esta clase verifica que el dato que ingresa el usuario no sea ni mayor ni menor a lo pedido. Pidiendo una consigna, un valor menor y uno mayor.
 */
public class Verificacion {
    
    /**
     * 
     * @param consigna Es la consigna que se le va a dar al usuario {@link String[]}.
     * @param menor Valor menor que puede ingresar el usuario {@link int}.
     * @param mayor Valor mayor que puede ingresar el usuario {@link int}.
     * @return devuelve la respuesta ingresada por el usuario {@link int}
     */
    public int verificar(String consigna, int menor, int mayor){
        int opcion = -2;
        try {
            while(opcion > mayor || opcion < menor){
                InputStreamReader reader = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(reader);
                System.out.print("\n"+consigna);
                opcion = Integer.parseInt(br.readLine());
                if(opcion > mayor || opcion < menor){
                    System.out.println("\nOpcion incorrecta. Vuelva a intentarlo.");
                }
            } 
        } catch (IOException ex) {
            Logger.getLogger(Verificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return opcion;
    }
    
}
