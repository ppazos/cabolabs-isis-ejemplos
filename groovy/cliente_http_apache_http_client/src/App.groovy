import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

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
      CloseableHttpClient httpclient = HttpClients.createDefault();
      HttpGet httpget
      try
      {
         httpget = new HttpGet( domain )
      }
      catch (Exception e)
      {
         println "Error: url no valida"
         println "Parametros: host (ej. http://cabolabs.com)"
         System.exit(0)
      }
      
      System.out.println("Executing request " + httpget.getRequestLine());
      
      // Create a custom response handler
      ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

          @Override
          public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
              int status = response.getStatusLine().getStatusCode();
              if (status >= 200 && status < 300) {
                  HttpEntity entity = response.getEntity();
                  return entity != null ? EntityUtils.toString(entity) : null;
              } else {
                  throw new ClientProtocolException("Unexpected response status: " + status);
              }
          }

      };
      String responseBody = httpclient.execute(httpget, responseHandler);
      System.out.println("----------------------------------------");
      System.out.println(responseBody);
      
      httpclient.close();
   }
}