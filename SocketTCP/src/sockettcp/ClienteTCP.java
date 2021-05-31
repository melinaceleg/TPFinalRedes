/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockettcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author user
 */
public class ClienteTCP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       
       Socket socketCliente = null;
       
       BufferedReader entrada = null; //leer texto de secuencia de entrada
       PrintWriter salida = null; //crear y escribir archivos
       
       System.out.println("---Cliente----");
       try{
           socketCliente = new Socket("localhost", 3000);
           entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
           salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);
       }
       catch(Exception e){
           System.out.println(e);
       }
       
       BufferedReader sc = new BufferedReader( new InputStreamReader(System.in));
       String cad;
       try{
           while(true){
            cad = entrada.readLine();
            System.out.println(cad);
            cad = sc.readLine();
            salida.println(cad);
            
            if(cad.equalsIgnoreCase("x"))break;
           }
       }
       catch(Exception e){
       }
       
       salida.close();
       entrada.close();
       sc.close();
       socketCliente.close();
    }

    
}
