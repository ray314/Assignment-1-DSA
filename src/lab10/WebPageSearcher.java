package lab10;
/**

   A class that handles opening a URL, checking that robots are
   allowed, finding hyperlinks to other URLs and spawning a
   new WebPageSearcher in its own thread for each URL found
   @see WebCrawler.java
*/
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class WebPageSearcher implements Runnable
{
   private Vertex<URL> vertex;
   private static GraphADT<URL> webGraph;
   private static int numSearchers = 0;//total num of WebPageSearchers
   private static final int MAX_SEARCHERS = 20;
   private static final String DISALLOW_COMMAND = "Disallow:";

   // constructor for a new WebPageCrawler, the vertex is already
   // presumed to be added to webGraph
   public WebPageSearcher(Vertex<URL> vertex)
   {  this.vertex = vertex;
      System.out.println("New searcher created for "
         + vertex.getUserObject());
      numSearchers++;
   }

   // set the graph that is shared by all the WebPageCrawler objects
   public static void setGraph(GraphADT<URL> graph)
   {  webGraph = graph;
   }

   // open the URL, check if robots are allowed, read contents and
   // spawn a new WebPageSearcher for each hyperlink found
   public void run()
   {  // check valid protocol and robot allowed access
      URL url = vertex.getUserObject();
      System.out.println("Searcher starting for " + url);
      if (crawlerAllowedAccess(url))
      {  try
         {  // open the url and read content, then find all hyperlinks
            String pageContent = getContent(url);
            List<String> hyperlinks = getHyperlinks(pageContent);
            // create a set of threads, one thread per new searcher
            Set<Thread> threads = new HashSet<Thread>();
            // the following code is synchronized with webGraph as its
            // monitor, so that only one WebPageSearcher can be adding
            // to the graph at a time
            synchronized(webGraph)
            {  for (String hyperlink : hyperlinks)
               {  try
                  {  URL linkedURL = new URL(url, hyperlink);
                     // search for this url as element of some vertex
                     Iterator<Vertex<URL>>iterator
                        = webGraph.vertexSet().iterator();
                     Vertex<URL> foundVertex = null;
                     while (iterator.hasNext() && foundVertex==null)
                     {  Vertex<URL> nextVertex = iterator.next();
                        if (nextVertex.getUserObject().equals
                           (linkedURL))
                           foundVertex = nextVertex;
                     }
                     if (foundVertex==null)
                     {  // the hyperlinked page not yet visited
                        foundVertex = webGraph.addVertex(linkedURL);
                        if (numSearchers<MAX_SEARCHERS)
                        {  WebPageSearcher newSearcher
                              = new WebPageSearcher(foundVertex);
                           threads.add(new Thread(newSearcher));
                        }
                     }
                     if (!vertex.equals(foundVertex))//don't add loops
                        webGraph.addEdge(vertex, foundVertex);
                  }
                  catch (MalformedURLException e) // not valid protocol
                  {  System.out.println
                        ("Protocol not valid for " + url);
                  }
               }
            }
            // start all the WebPageSearcher threads
            for (Thread searcherThread : threads)
               searcherThread.start();
            //have this thread wait until each of the threads has died
            for (Thread searcherThread : threads)
            {  try
               {  // have this thread wait for searcherThread to die
                  searcherThread.join();
               }
               catch (InterruptedException e)
               {} // ignore
            }
         }
         catch (IOException e)
         {  System.out.println("I/O Exception in " + url);
         }
      }
      else
         System.out.println("Crawler disallowed at " + url);
      System.out.println("Searcher terminating for " + url);
   }

   // checks the protocol and robots.txt file at the url to see
   // whether the web site requests robots not to access it
   // note this doesn't check meta tags for such requests
   private boolean crawlerAllowedAccess(URL url)
   {  // check that the url is using http protocol
      if (url.getProtocol().compareTo("http")!=0)
      {  System.out.println("Invalid protocol for " + url);
         return false;
      }
      // read the contents of the robots.txt file (if exists)
      String robotFileContent = "";
      try
      {  URL robotFileURL = new URL("http://" + url.getHost()
            + "/robots.txt");
         robotFileContent = getContent(robotFileURL);
      }
      catch (IOException e)
      {  return true; // file can't be read or doesn't exist
      }
      // search for the "Disallow:" commands
      String webPageName = url.getFile().toLowerCase();
      int disallowIndex = 0;
      do
      {  // find index of next disallow command
         disallowIndex = robotFileContent.indexOf(DISALLOW_COMMAND,
            disallowIndex);
         if (disallowIndex >= 0)
         {  // a disallow command was found in robots.txt file
            // so obtain the page name that is disallowed
            disallowIndex += DISALLOW_COMMAND.length(); // go to name
            StringTokenizer tokenizer = new StringTokenizer
               (robotFileContent.substring
               (disallowIndex).toLowerCase());
            String disallowedPage = tokenizer.nextToken();
            if (webPageName.startsWith(disallowedPage))
               return false;
         }
      }
      while (disallowIndex >= 0);
      return true; // no mention anywhere of disallowing this page
   }

   // returns the content of the specified url
   private String getContent(URL pageURL) throws IOException
   {  String pageContent = "";
      try
      {  // set connection and read timeouts for the connection
         URLConnection connection = pageURL.openConnection();
         connection.setConnectTimeout(5000); // 5000ms
         connection.setReadTimeout(5000); // 5000ms
         // create an input stream and read all the lines
         InputStream is = pageURL.openStream();
         BufferedReader br = new BufferedReader
            (new InputStreamReader(is));
         String line = br.readLine();
         while (line != null)
         {  pageContent += line;
            line = br.readLine();
         }
         br.close();
      }
      catch (MalformedURLException e) // not valid protocol
      {  System.out.println("Protocol not valid for "+pageURL);
      }
      return pageContent;
   }

   // returns a list of all the hyperlinks found in the page content
   // by searching for "<a" followed by "href" and "="
   // then the hyperlink then eventually a ">"
   private List<String> getHyperlinks(String pageContent)
   {  String remainingContent = pageContent.toLowerCase();
      List<String> hyperlinkList = new LinkedList<String>();
      // declare temporary variables
      int startTagIndex, hrefIndex, endTagIndex;
      do
      {  // find index of the next anchor tag
         startTagIndex = remainingContent.indexOf("<a");
         if (startTagIndex >= 0)
         {  hrefIndex=remainingContent.indexOf("href", startTagIndex);
            if (hrefIndex>0)
               hrefIndex = remainingContent.indexOf("=", hrefIndex);
            endTagIndex=remainingContent.indexOf(">", startTagIndex);
            if (hrefIndex>startTagIndex && hrefIndex<endTagIndex)
            {  // valid anchor tag found with href inside tag
               remainingContent=remainingContent.substring(hrefIndex+1);
               StringTokenizer tokenizer = new StringTokenizer
                  (remainingContent, "\t\n\r\"'>#");
               hyperlinkList.add(tokenizer.nextToken());
            }
            else if (endTagIndex<remainingContent.length())
               remainingContent
                  = remainingContent.substring(endTagIndex);
            else // this anchor tag did not end
               remainingContent = "";
         }
         else // there are no further anchor tags
            remainingContent = "";
      }
      while (remainingContent.length()>0);
      return hyperlinkList;
   }
}
