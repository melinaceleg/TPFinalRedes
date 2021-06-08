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
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author user
 */
public class ServidorTCP extends Thread{

    ServerSocket socketServidor = null;
    Socket socketCliente = null;
    BufferedReader entrada = null;
    PrintWriter salida = null;

    public ServidorTCP(Socket s){
        this.socketCliente = s;
    }

    public void run() {


        System.out.println("---Servidor----");

        try{
            while(true){

                int a, b;

                //socketCliente = socketServidor.accept();
                entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);
                while(true){
                    //bienvenida
                    String mensaje = "Bienvenido a la calculadora, ingrese alguna de las siguientes opciones para continuar:"
                            +" 1) Suma (a+b)"
                            +" 2) Resta (a-b)"
                            +" 3) División (a/b)"
                            +" 4) Multiplicación (a*b)"
                            +" x) Salir";

                    salida.println(mensaje);
                    System.out.println("Enviado a "+socketCliente.getRemoteSocketAddress()+": "+mensaje);
                    //respuesta
                    String rta = entrada.readLine();
                    System.out.println(socketCliente.getRemoteSocketAddress()+": "+rta);

                    if(rta.equalsIgnoreCase("x")){
                        socketCliente.close();
                        System.out.println(socketCliente.getRemoteSocketAddress()+" close session ");
                        break;
                    }

                    //primer numero
                    mensaje = "Ingrese el número a";
                    salida.println(mensaje);
                    System.out.println("Enviado a "+socketCliente.getRemoteSocketAddress()+": "+mensaje);
                    a = Integer.parseInt(entrada.readLine());
                    System.out.println(socketCliente.getRemoteSocketAddress()+": "+a);

                    //segundo numero
                    mensaje = "Ingrese el número b";
                    salida.println(mensaje);
                    System.out.println("Enviado a "+socketCliente.getRemoteSocketAddress()+": "+mensaje);
                    b = Integer.parseInt(entrada.readLine());
                    System.out.println(socketCliente.getRemoteSocketAddress()+": "+b);

                    //calculo
                    switch(rta){
                        case "1":
                            mensaje="La suma de ambos es: "+(a+b)+". Envía algo para volver al menú principal";
                            salida.println(mensaje);
                            System.out.println("Enviado a "+socketCliente.getRemoteSocketAddress()+": "+mensaje);
                            break;
                        case "2":
                            mensaje="La resta de ambos es: "+(a-b)+". Envía algo para volver al menú principal";
                            salida.println(mensaje);
                            System.out.println("Enviado a "+socketCliente.getRemoteSocketAddress()+": "+mensaje);
                            break;
                        case "3":
                            mensaje="La división de ambos es: "+(a/b)+". Envía algo para volver al menú principal";
                            salida.println(mensaje);
                            System.out.println("Enviado a "+socketCliente.getRemoteSocketAddress()+": "+mensaje);
                            break;
                        case "4":
                            mensaje="La multiplicación entre ambos es: "+(a*b)+". Envía algo para volver al menú principal";
                            salida.println(mensaje);
                            System.out.println("Enviado a "+socketCliente.getRemoteSocketAddress()+": "+mensaje);
                            break;
                        default:
                            return;
                    }


                }

            }
        }
        catch(Exception e){

        }finally {

            try {
                if(entrada!= null){
                    entrada.close();
                }
                if(salida!= null){
                    salida.close();
                }
                if(socketServidor!=null){
                    socketServidor.close();
                }
                if(socketCliente!=null){
                    socketCliente.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}