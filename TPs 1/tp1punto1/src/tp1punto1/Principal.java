/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1punto1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Fran
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Branch TP 1.1");
        /*
        int resultado,opcion = 0;
        String suma;
        File archivo = new File("resultado.txt");
        InputStreamReader reader;
        BufferedReader br;
        PrintStream ps;
        try {
            reader = new InputStreamReader(System.in);
            br = new BufferedReader(reader);
            ps = new PrintStream(new FileOutputStream(archivo,false));
            System.out.print("\nIngrese la operacion con el simbolo correspondiente incluido: ");
            suma = br.readLine();
            for(int i=0; i<suma.length(); i++){
                if(suma.charAt(i) == '+') opcion=1;
                else if(suma.charAt(i) == '-') opcion=2;
                else if(suma.charAt(i) == '*') opcion=3;
                else if(suma.charAt(i) == '/') opcion=4;
            }
            resultado = realizarOperacion(suma, opcion);
            ps.print(suma + " = " + resultado);
            ps.flush();
            br.close(); reader.close(); ps.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }
    
    public static int realizarOperacion(String suma, int opcion){
        int aux = 0;
        int[] n = new int[2];
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(suma);
        int i = 0;
        while(m.find()) {
            n[i] = Integer.parseInt(String.valueOf(m.group()));
            i++;
        }
        switch (opcion) {
            case 1: //suma
                return n[0] + n[1];
            case 2: //resta
                return n[0] - n[1];
            case 3: //multiplicacion
                return n[0] * n[1];
            case 4: //division
                return n[0] / n[1];
            default:
                return 0;
        }
    }
    
    
    
}
