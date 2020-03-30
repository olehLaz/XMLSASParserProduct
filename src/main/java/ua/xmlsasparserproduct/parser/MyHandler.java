package ua.xmlsasparserproduct.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ua.xmlsasparserproduct.domain.Offers;

import java.util.ArrayList;
import java.util.List;

public class MyHandler extends DefaultHandler {

    // List to Offer object
    private List<Offers> offersList = null;
    private Offers offers = null;
    private StringBuilder data = null;

    public List<Offers> getOffersList() {
        return offersList;
    }

    boolean bUrl = false;
    boolean bPrice = false;
    boolean bVendorCode =false;
    boolean bCurrencyId = false;
    boolean bCategoryId = false;
    boolean bPicture = false;
    boolean bDelivery = false;
    boolean bName = false;
    boolean bDescription = false;
    boolean bVendor = false;
    boolean bCode = false;
    boolean bParam = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("Offer")) {
            // create a new Employee and put it in Map
            String id = attributes.getValue("id");
            String available = attributes.getValue("available");
            String group_id = attributes.getValue("group_id");
            // initialize Offer object and set id attribute
            offers = new Offers();
            offers.setId(Integer.parseInt(id));
            offers.setAvailable(Boolean.parseBoolean(available));
            offers.setGroup_id(group_id);
            // initialize list
            if (offersList == null)
                offersList = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("url")) {
            // set boolean values for fields
            bUrl = true;
        } else if (qName.equalsIgnoreCase("price")) {
            bPrice = true;
        } else if (qName.equalsIgnoreCase("vendorCode")) {
            bVendorCode = true;
        } else if (qName.equalsIgnoreCase("currencyId")) {
            bCurrencyId = true;
        }

        else if (qName.equalsIgnoreCase("param")) {
            String name = attributes.getValue("name");
            offers.setParam(name);
            bParam = true;
        }


        // create the data container
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (bUrl) {
            //
            offers.setUrl(data.toString());
            bUrl = false;
        } else if (bPrice) {
            offers.setPrice(Double.parseDouble(data.toString()));
            bPrice = false;
        } else if (bVendorCode) {
            offers.setVendorCode(data.toString());
            bVendorCode = false;
        } else if (bCurrencyId) {
            offers.setCurrencyId(data.toString());
            bCurrencyId = false;
        }

        else if (bParam) {
            offers.setParam(data.toString());
            bParam = false;
        }


        if (qName.equalsIgnoreCase("Offer")) {
            // add Employee object to list
            offersList.add(offers);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }


}
