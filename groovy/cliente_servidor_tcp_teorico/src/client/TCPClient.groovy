package client
class TCPClient {

   def TCPClient(int serverPort, String serverIP)
   {        
      // Crea socket de conexion con el servidor,
      // especificando direccion IP y puerto del servidor.
      // "localhost" equivale a la IP 127.0.0.1
      def socket = new Socket(InetAddress.getByName(serverIP), serverPort)
      
      println "TCPClient: conectado a " + socket.getRemoteSocketAddress()
      
      
      def input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      def output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      
      try
      {
         // Escribe en el socket
         output.writeLine( "Pablo" ) // Agrega \n al final, el server hace readLine.
         output.flush()

         println "TCPClient recibe: " + input.readLine()
      }
      catch (Exception e)
      {
         println e.message
      }
      finally
      {
         input.close()
         output.close()
         
         // Cierra el socket
         if (socket.isConnected() && !socket.isClosed())
         {
            socket.close()
         }
      }
   }
}
