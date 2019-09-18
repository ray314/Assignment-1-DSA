package lab8;

/**

   A class which demonstrates how SAX can be used to parse an
   XML document to count the number of <book> tags and
   collect the isbn attribute of each book
   @author Andrew Ensor
   @see books.xsd for the XML Schema
*/
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Java 1.5 equivalent of cs1.Keyboard
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXCounter
{
   private int numBooks;
   private List<String> isbnList;
   private static final String JAXP_SCHEMA_LANGUAGE
      = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
   private static final String W3C_XML_SCHEMA 
      = "http://www.w3.org/2001/XMLSchema"; 
   
   public SAXCounter(String fileName)
   {  numBooks = 0;
      isbnList = new ArrayList<String>();
      ParserHandler handler = new ParserHandler();
      try
      {  // create a validating SAX parser using default parser
         SAXParserFactory saxFactory = SAXParserFactory.newInstance();
         saxFactory.setNamespaceAware(true);
         saxFactory.setValidating(true);
         SAXParser parser = saxFactory.newSAXParser();
         parser.setProperty(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
         parser.parse(fileName, handler);
      }
      catch (FactoryConfigurationError e)
      {  System.out.println("Can't get factory:"+e);
      }
      catch (ParserConfigurationException e)
      {  System.out.println("Can't get builder:"+e);
      }
      catch (SAXException e)
      {  System.out.println("Exception parsing document:"+e);
      }
      catch (IOException e)
      {  System.out.println("IO exception:"+e);
      }
   }

   public static void main(String[] args)
   {  // obtain name of XML document to process
      Scanner keyboardScanner = new Scanner(System.in);
      System.out.print("Please enter name of an XML book document:");
      String fileName = keyboardScanner.nextLine();
      SAXCounter counter = new SAXCounter(fileName);
      // output the results
      System.out.println("There are "+counter.numBooks+" books");
      int numISBN = counter.isbnList.size();
      for (int i=0; i<numISBN; i++)
      {  if (i==0) 
            System.out.print("ISBN are:");
         else
            System.out.print(", ");
         System.out.print(counter.isbnList.get(i));
      }
      System.out.println();
   }
   
   // An inner class that handles events generated by the SAX parser
   private class ParserHandler extends DefaultHandler
   {
      public void startDocument() throws SAXException
      {  System.out.println("Starting parse of document");
      }
      
      public void endDocument() throws SAXException
      {  System.out.println("Ending parse of document");
      }
      
      public void startElement(String uri, String localName,
         String qName, Attributes attributes) throws SAXException
      {  if ("book".equalsIgnoreCase(qName))
         {  // another book tag has been found
            numBooks++;
            if (attributes!=null)
            {  String isbn = attributes.getValue("isbn");
               isbnList.add(isbn);
            }
         }
      }
   }
}
