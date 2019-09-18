/**
 * 
 */
package lab8;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * @author fbb3628
 *
 */
public class XMLOpen {

	private String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	private String schema = "http://www.w3.org/2001/XMLSchema";
	private DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
	private DocumentBuilder builder;
	private Document document;
	/**
	 * 
	 */
	public XMLOpen() {

		createBuilder();
		parseXML();
	}
	
	public Node ObtainRootNode()
	{
		Node rootXMLNode = document.getDocumentElement();
		
		return rootXMLNode;
	}
	
	public void parseXML()
	{
		FileInputStream in;
		try {
			in = new FileInputStream("ComputerBooks.xml");
			document = builder.parse(in);
			document.getDocumentElement().normalize();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void createBuilder()
	{
		try {
			// TODO Auto-generated constructor stub
			
			builderFactory.setNamespaceAware(true);
			builderFactory.setValidating(true);
			builderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, schema);
			
			builder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		XMLOpen extractor = new XMLOpen();
		DOMUtilities dom = new DOMUtilities();
		Collection<Node> books;
		books = dom.getAllChildNodes(extractor.ObtainRootNode(), "book");

		Iterator<Node> it = books.iterator();
		while(it.hasNext())
		{
			Node book = it.next();
			Collection<Node> bookTitle = dom.getAllChildNodes(book, "title");
			System.out.println(dom.getAttributeString(book, "isbn"));
			
			Iterator<Node> itTitle = bookTitle.iterator();
			while (itTitle.hasNext())
			{
				System.out.println(dom.getTextContent(itTitle.next()));
			}
			
			Collection<Node> bookAuthor = dom.getAllChildNodes(book, "author");
			
			Iterator<Node> itAuthor = bookAuthor.iterator();
			while (itAuthor.hasNext())
			{
				System.out.println(dom.getTextContent(itAuthor.next()));
			}
			
			System.out.println();
		}
	}
}
