package com.convertxml.controller;
import com.convertxml.dao.AddressBookDAO;
import com.convertxml.ui.AddressBookView;
import com.convertxml.ui.UserIO;
import com.convertxml.ui.UserIOConsoleImpl;
import org.json.JSONException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class AddressBookController {
    private AddressBookDAO addressBookDAO;
    private AddressBookView view;
    private UserIO io = new UserIOConsoleImpl();

    public AddressBookController(AddressBookDAO addressBookDAO, AddressBookView view) {
        this.addressBookDAO = addressBookDAO;
        this.view = view;
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    public void run() {

        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {

                    case 1:
                        io.print("1. Convert XML file To JSON");
                        convertXML();
                        io.print("\n**New JSON file has been saved as JSON.json in root folder**\n");
                        break;
                    case 2:
                        io.print("2. Convert JSON file to XML");
                        convertJSON();
                        io.print("\n**New XML file has been saved as newXML.XML in root folder**\n");
                        break;

                    case 3:
                        keepGoing = false;
                        break;
                    default:
                        io.print("UNKNOWN COMMAND");
                        unknownCommand();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String addLocationPath() {
        return view.getFilePathLocation();
    }

    private void convertXML() throws IOException {
        String Location = addLocationPath();
        io.print(addressBookDAO.unmarshallItemsAndConvert(Location));
    }

    private void convertJSON() throws IOException, ParserConfigurationException, SAXException, JSONException {
        String Location = addLocationPath();
        addressBookDAO.convertJSONToXML(Location);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

}



