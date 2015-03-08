class App {

   // run http://domain.com
   static main(args) {
   
      if (args.size() == 0)
      {
         println "Error: url no presente"
         println "Parametros: host (ej. http://cabolabs.com)"
         System.exit(0)
      }
      
      def domain = args[0] // http://domain.com
      def url
      try
      {
         url = new URL( domain )
      }
      catch (Exception e)
      {
         println "Error: url no valida"
         println "Parametros: host (ej. http://cabolabs.com)"
         System.exit(0)
      }
      
      // Consulta DNS por todas las IPs asociadas al dominio
      InetAddress[] hosts = InetAddress.getAllByName( url.getHost() );
      
      for(InetAddress host : hosts)
      {
         //System.out.println("- " + host +" "+ host.getHostAddress());
         System.out.println("- " + host.getHostAddress()); // IP
         //new TestThread(url, host, interval).start();
      }
      
      //println "hilo ppal: " + Thread.currentThread().getId()
      
      // Usar la primera IP
      
      // Mensaje a enviar por TCP
      // GET http://www.somehost.com/path/file.html HTTP/1.0
      new TCPClientThread(url, hosts[0].getHostAddress()).start()
   }
   
   // Thread que realiza la llamada HTTP al servidor
   private static class TCPClientThread extends Thread
   {
      def url
      def serverPort
      def serverIP
      def get
      
      def TCPClientThread(URL url, String serverIP)
      {
         this.url        = url
         // Sino se pone puerto en la URL, toma el puerto por defecto: 80
         this.serverPort = (url.getPort() > 0) ?: url.getDefaultPort()
         this.serverIP   = serverIP
         this.get        = "GET "+ (url.getPath() ?: "/") +" HTTP/1.1\r\n"
      }
      
      void run()
      {
         //println "hilo socket: " + Thread.currentThread().getId()
      
         def socket = new Socket(InetAddress.getByName(this.serverIP), this.serverPort)
      
         println "TCPClientThread: conectado a " + socket.getRemoteSocketAddress()
         
         def input = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
         def output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
         
         try
         {
            def request = ""
            request += this.get
            request += "Host: "+ this.url.getHost() +"\r\n"
            request += "Accept: *.*\r\n"
            request += "\r\n"
            
            println "------------------------------"
            println "Enviando pedido HTTP:"
            println request
            output.write( request )
            output.flush()

            println "------------------------------"
            println "Respuesta HTTP:"
            
            // Cuidado: input.eachLine nunca termina de leer, usar en su lugar:
            def line
            while ( (line = input.readLine()) != null )
            {
               println line
            }
         }
         catch (Exception e)
         {
            println "Error: " + e.message
            this.interrupt()
         }
         finally
         {
            // Cierra el socket
            if (socket.isConnected() && !socket.isClosed())
            {
               socket.close()
            }
            //println "sale del socket thread"
            //this.interrupt()
         }
      }
   }
}