package com.convertxml.ui;

public class AddressBookView {
    private UserIO io;
    public AddressBookView(UserIO io) {
        this.io = io;
    }
    public int printMenuAndGetSelection() {
        io.print("Main Menu\nPlace the xml or json File that needs Conversion in the root folder of the program");
        io.print("1. Convert XML file to JSON");
        io.print("2. Convert JSON file to XML");

        io.print("3. Exit");
        return io.readInt("Please select from the above choices.", 1, 3);
    }
    public String getFilePathLocation(){
        return io.readString("Enter File Name from the root Folder");
    }
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
}
