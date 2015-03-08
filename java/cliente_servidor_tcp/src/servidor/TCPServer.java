/**
 * 
 */
package servidor;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase que implementa un servidor TCP.
 * 
 * Compilar y ejecutar desde consola:
 *   - javac TCPServer.java (genera el .class)
 *   - java servidor.TCPServer (ejecuta el .class)
 * 
 * @author Pablo Pazos Gutierrez <pablo@openehr.org.es>
 *
 */
public class TCPServer {

   static int defaultPort = 1255;
   
   /**
    * Mejoras posibles:
    * 
    *  - Recibir como parametro el puerto donde se atienden conexiones.
    *  - No cerrar el servidor luego de atender a un cliente, para atender otros clientes.
    *  - Permitir recibir datos del cliente, por ejemplo comandos a ejecutar.
    *  - Soportar mas de un usuario simultaneamente (threading con multiples sockets de conexion).
    */
   
   /**
    * @param args parametros de entrada.
    */
   public static void main(String[] args)
   {
      System.out.println("Bienvenid@ al servidor TCP!");
      
      try
      {
         // Se crea el socket donde se escuchan conexiones de clientes
         // http://docs.oracle.com/javase/6/docs/api/java/net/ServerSocket.html
         ServerSocket server = new ServerSocket(defaultPort);
         
         System.out.println(" - escuchando en el puerto " + defaultPort);
         
         // Se espera por una nueva conexion de un cliente,
         // para la que se crea un socket de intercambio de datos
         // http://docs.oracle.com/javase/6/docs/api/java/net/Socket.html
         Socket socket = server.accept();
         
         System.out.println(" - se ha conectado un cliente!");
         
         // Escritor asociado al socket conectado, donde se le envian datos
         // al cliente conectado
         // http://docs.oracle.com/javase/6/docs/api/java/io/PrintWriter.html
         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
         
         System.out.println(" - enviado datos al cliente");
         
         // Se le envia "ping" al cliente
         out.print("ping\n");
         
         System.out.println(" - cerrando servidor");
         
         // Se cierra el escritor
         out.close();
         
         // Se cierra la conexion con el cliente
         socket.close();
         
         // Se cierra el servidor
         server.close();
         
         System.out.println("Adios!");
      }
      catch (Exception e)
      {
         // Si recibes un error "Address already in use: JVM_Bind" debes cambiar el numero de puerto.
         System.out.println("Ha ocurrido un error: " + e.getMessage());
      }
   }
}