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

        BufferedReader sc = new BufferedReader( new InputStreamReader(System.in));

        String ip;
        int puerto;

        System.out.println("---Cliente----");

        System.out.println("Ingrese la ip...");
        ip = sc.readLine();
        System.out.println("Ingrese el puerto...");
        puerto = Integer.parseInt(sc.readLine());

        try{
            socketCliente = new Socket(ip, puerto);
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);
        }
        catch(Exception e){
            System.out.println("La ip ingresada es inválida...");
        }

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
