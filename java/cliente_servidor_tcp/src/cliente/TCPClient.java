/**
 * 
 */
package cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Clase que implementa un cliente TCP.
 * 
 *  * Compilar y ejecutar desde consola:
 *   - javac TCPClient.java (genera el .class)
 *   - java cliente.TCPClient (ejecuta el .class)
 * 
 * @author  Pablo Pazos Gutierrez <pablo@openehr.org.es>
 *
 */
public class TCPClient {

   static int serverPort = 1255;
   
   /**
    * Mejoras posibles:
    * 
    *   - Enviar datos al servidor, por ejemplo comandos.
    *   - Recibir como parametro la IP y puerto del servidor.
    */
   
   /**
    * @param args parametros de entrada.
    */
   public static void main(String[] args)
   {
      System.out.println("Bienvenid@ al cliente TCP!");
      
      try
      {
         // Crea socket de conexion con el servidor, 
         // especificando direccion IP y puerto del servidor.
         // "localhost" equivale a la IP 127.0.0.1
         // http://docs.oracle.com/javase/6/docs/api/java/net/Socket.html
         Socket socket = new Socket("localhost", serverPort);
         
         // Lector asociado al socket para recibir los datos
         // que envia el servidor cuando nos conectamos
         // http://docs.oracle.com/javase/6/docs/api/java/io/BufferedReader.html
         // http://docs.oracle.com/javase/6/docs/api/java/io/InputStreamReader.html
         BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         
         // Lee datos del servidor
         String datosRecibidos = in.readLine();
         
         System.out.println(" - el servidor dice: " + datosRecibidos);
         
         System.out.println(" - cerrando servidor");
         
         // Cierra el lector del socket
         in.close();
         
         // Cierra la conexion con el servidor
         socket.close();
         
         System.out.println("Adios!");
      }
      catch (Exception e)
      {
         System.out.println("Ha ocurrido un error: " + e.getMessage());
      }
   }
}