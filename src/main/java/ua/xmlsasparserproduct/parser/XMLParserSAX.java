package ua.xmlsasparserproduct.parser;

import org.xml.sax.SAXException;
import ua.xmlsasparserproduct.Offers;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class XMLParserSAX {
    public static void main(String[] args) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try (FileWriter fileWriter = new FileWriter("parsing.txt")){
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyHandler handler = new MyHandler();
            File file = new File("d:\\product.xml");
            System.out.println(""+file.getAbsolutePath());
            System.out.println("---------------------");
            saxParser.parse(file, handler);
            //Get Employees list
            List<Offers> offersList = handler.getOffersList();
            //print employee information
            for(Offers offers : offersList) {
                System.out.println(offers);
                fileWriter.write(offers.toString()+'\n');
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
