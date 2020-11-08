/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
        
        System.out.println("Branch TP1");
        
        /*
        String nums;
        int[] n = new int[5];
        File numeros = new File("numeros.txt");
        File resultados = new File("resultados.txt");
        File errores = new File("error.txt");
        InputStreamReader reader;
        FileReader fr;
        BufferedReader br,br2;
        FileWriter fw;
        PrintWriter pw;
        
        try {
            fw = new FileWriter(numeros,false);
            pw = new PrintWriter(fw);
            reader = new InputStreamReader(System.in);
            br = new BufferedReader(reader);
            fr = new FileReader(numeros);
            br2 = new BufferedReader(fr);
            
            //almacenamiento de 5 numeros en un vector
            System.out.print("Ingrese 5 numeros con un espacio de por medio (2 de ellos tienen que ser 0): ");
            nums = br.readLine();
            Pattern p = Pattern.compile("-?\\d+");
            Matcher m = p.matcher(nums);
            int i = 0;
            while(m.find()) {
                n[i] = Integer.parseInt(String.valueOf(m.group()));
                i++;
            }
            
            //almacenamiento de 5 numeros al archivo numeros.txt
            System.out.print("Ingrese nuevamente 5 numeros con un espacio de por medio (2 de ellos tienen que ser 0): ");
            nums = br.readLine();
            m = p.matcher(nums);
            while(m.find()) {
                pw.println(m.group());
            }
            pw.flush();

            fw = new FileWriter(resultados,false);
            pw = new PrintWriter(fw);
            
            //se guardan las cuentas con los resultados en el archivo resultados.txt y se cuentan los errores
            String aux;
            int cont = 0;
            int cont2 = 0;
            while(((aux=br2.readLine()) != null)){
                int aux2 = Integer.parseInt(String.valueOf(aux));
                if(aux2!=0){
                    pw.println(n[cont] + " / " + aux2 + " = " + ((double)n[cont]/aux2));
                }else{
                    cont2++;
                }
                cont++;
            }
            pw.flush();
            
            //se verifica si hay errores y los muestra en el archivo error.txt
            if(cont != 5 || cont2 != 0){
                fw = new FileWriter(errores,false);
                pw = new PrintWriter(fw);
                if(cont != 5) pw.println("Se ha producido un error:\nNo se lograron realizar las 5 cuentas debido a que no se ingresaron suficientes numeros.\nNumeros ingresados: "+cont);
                if(cont2 != 0) pw.println("\nSe ha producido un error:\nNo se lograron realizar las 5 cuentas debido a errores matematicos.\nErrores matematicos: "+cont2);
                pw.flush();
            }

            br.close(); br2.close(); fr.close(); reader.close(); pw.close(); fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }
    
}
