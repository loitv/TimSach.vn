package control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/*
 * this class for storing book's description in XML file 
 */
public class XMLProcessing {
	private static ArrayList<String> ISBNs;
	private static ArrayList<String> titles;
	private static ArrayList<String> descs;

	// Read XML file to get information about: isbn, title and description
	public void readXML() {

		ISBNs = new ArrayList<String>();
		titles = new ArrayList<String>();
		descs = new ArrayList<String>();

		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("library1.xml");
		try {
			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			List<?> list = rootNode.getChildren("book");
			for (int i = 0; i < list.size(); i++) {
				Element node = (Element) list.get(i);
				ISBNs.add(node.getAttributeValue("ISBN"));
				titles.add(node.getChildText("title"));
				descs.add(node.getChildText("description"));
			}
		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		}
	}

	public void writeXML(String isbn, String title, String desc) {
		readXML();
		// System.out.println(ISBNs.size());

		try {
			Element library = new Element("library");
			Document doc = new Document(library);
			doc.setRootElement(library);

			for (int i = 0; i < ISBNs.size(); i++) {
				Element book = new Element("book");
				book.setAttribute(new Attribute("ISBN", ISBNs.get(i)));
				book.addContent(new Element("title").setText(titles.get(i)));
				book.addContent(new Element("description").setText(descs.get(i)));
				doc.getRootElement().addContent(book);
			}

			Element book = new Element("book");
			book.setAttribute(new Attribute("ISBN", isbn));
			book.addContent(new Element("title").setText(title));
			book.addContent(new Element("description").setText(desc));
			doc.getRootElement().addContent(book);

			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("library1.xml"));

			System.out.println("File Saved!");
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}
	}

	public static ArrayList<String> getISBNs() {
		return ISBNs;
	}

	public static ArrayList<String> getTitles() {
		return titles;
	}

	public static ArrayList<String> getDescs() {
		return descs;
	}
}
