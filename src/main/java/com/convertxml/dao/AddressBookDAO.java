package com.convertxml.dao;
import com.convertxml.dto.AddressBook;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.*;
import org.json.XML;

public class AddressBookDAO {

    FileWriter file=new FileWriter("JSON.json");
    FileWriter toXMLFile=new FileWriter("newXML.xml");

    public AddressBookDAO() throws IOException {
    }

    public String unmarshallItemsAndConvert(String pathLocation ) throws IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        AddressBook addressBook = new AddressBook();
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> list = new ArrayList<>();
        String json ="";

        try {

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(pathLocation);
            NodeList addressList = doc.getElementsByTagName("Contact");
            for (int i = 0; i < addressList.getLength(); i++) {
                Element addressElement = (Element) addressList.item(i);

                if (addressElement.getNodeName().contains("Contact")  ) {


                    addressBook.setCustomerId(addressElement.getElementsByTagName("CustomerID").item(0).getTextContent());

                    addressBook.setCompanyName(addressElement.getElementsByTagName("CompanyName").item(0).getTextContent());
                    addressBook.setContactName(addressElement.getElementsByTagName("ContactName").item(0).getTextContent());
                    addressBook.setContactTitle(addressElement.getElementsByTagName("ContactTitle").item(0).getTextContent());
                    addressBook.setAddress(addressElement.getElementsByTagName("Address").item(0).getTextContent());
                    addressBook.setCity(addressElement.getElementsByTagName("City").item(0).getTextContent());
                    addressBook.setEmail(addressElement.getElementsByTagName("Email").item(0).getTextContent());
                    if(addressElement.getElementsByTagName("Region").item(0)!= null) {
                        addressBook.setRegion(addressElement.getElementsByTagName("Region").item(0).getTextContent());
                    }

                    else {
                        addressBook.setRegion("null");
                    }
                    if(addressElement.getElementsByTagName("PostalCode").item(0)!= null) {
                        addressBook.setPostalCode(addressElement.getElementsByTagName("PostalCode").item(0).getTextContent());
                    }
                    else {
                        addressBook.setPostalCode("null");
                    }
                    addressBook.setCountry(addressElement.getElementsByTagName("Country").item(0).getTextContent());
                    addressBook.setPhone(addressElement.getElementsByTagName("Phone").item(0).getTextContent());
                    if(addressElement.getElementsByTagName("Fax").item(0)!= null) {
                        addressBook.setFax(addressElement.getElementsByTagName("Fax").item(0).getTextContent());
                    }
                    else {
                        addressBook.setFax("null");
                    }
                     json = objectMapper.writeValueAsString(addressBook);
                    list.add(json);


                }
            }
        } catch(ParserConfigurationException | SAXException e){
            e.printStackTrace();
        }
//write files to json.json
        for(int k=0;k< list.size();k++) {
            file.write(list.get(k) + System.lineSeparator());
        }
        file.close();

       return  Arrays.toString(list.toArray()).replace("[", "").replace("]", "") ;
    }


    public String convertJSONToXML(String pathLocation ) throws IOException, ParserConfigurationException, SAXException, JSONException {

        String file = "JSON.json";

        ArrayList<String> list = new ArrayList();
        Scanner scanner = null;
        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(file)));
        } catch (FileNotFoundException e) {

        }

        JSONObject j = null;
        String currentLine = "";
        String xml="";
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            list.add(currentLine);
            Collections.sort(list);

            {
                for (int i = 0; i < list.size(); i++) {
                    JSONObject jso = new JSONObject(list.get(i).substring(list.get(i).indexOf('{')));
                    j = jso;
                }
            }
             xml= XML.toString(j);

            //write files to newxml.xml
            toXMLFile.write(xml + System.lineSeparator());
        }
        return xml;
    }

}
