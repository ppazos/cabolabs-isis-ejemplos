
import client.TCPClient
import server.TCPServer

class Main {

   static main(args)
   {
      if (args.size() == 0 || args[0] == 'help')
      {
         println 'servidor: server port ip'
         println 'cliente: client server_port server_ip'
         System.exit(0)
      }
   
      if (args[0] == 'server')
      {
         // server port ip
         println "SERVER"
         
         int port = 0 // Con 0 escucha en cualquier puerto libre
         if (args.size() > 1)
         {
            port = Integer.parseInt(args[1])
         }
         
         def server = new TCPServer(port)
      }
      else // por defecto es client
      {
         println "CLIENT"
         
         // client serverPort serverIP
         if (args.size() < 2)
         {
            println "TCPClient error: debe especificar el puerto del servidor"
            println "TCPClient uso del comando: client serverPort serverIP"
            System.exit(0)
         }
         int serverPort = Integer.parseInt(args[1])
         
         String serverIP = 'localhost' // Direccion por defecto
         if (args.size() == 3)
         {
            serverIP = args[2]
         }
         
         def client = new TCPClient(serverPort, serverIP)
      }
   }
}