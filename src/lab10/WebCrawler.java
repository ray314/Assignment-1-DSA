
package lab10;
/**

   A class that represents a web crawler which starts at a specified
   web address and follows hyperlinks to new pages, forming a graph
   @author Andrew Ensor
*/
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Set;

public class WebCrawler
{
   private static final String DEFAULT_START = "http://www.aut.ac.nz";

   public WebCrawler()
   {  // set the proxy host and port number for AUT's firewall
      // note this is not needed if no firewall present
      Properties props = new Properties(System.getProperties());
      props.put("http.proxySet", "true"); // true if using proxy
      props.put("http.proxyHost", "cache.aut.ac.nz"); // AUT specific
      props.put("http.proxyPort", "3128"); // AUT specific
      System.setProperties(props);
   }

   public GraphADT<URL> search() throws MalformedURLException
   {  return search(DEFAULT_START);
   }

   public GraphADT<URL> search(String startPage)
      throws MalformedURLException
   {  GraphADT<URL> webGraph = new AdjacencyListGraph<URL>();
      WebPageSearcher.setGraph(webGraph);
      Vertex<URL> startVertex =webGraph.addVertex(new URL(startPage));
      WebPageSearcher startPageSearcher
         = new WebPageSearcher(startVertex);
      startPageSearcher.run(); // first searcher is run in this thread
      return webGraph;
   }

   public static void main(String[] args)
   {  WebCrawler crawler = new WebCrawler();
      try
      {  GraphADT<URL> webGraph
            = crawler.search("http://java.sun.com/");
      }
      catch (MalformedURLException e) // not valid protocol
      {  System.out.println("Invalid protocol used:" + e.getMessage());
      }
   }
}
