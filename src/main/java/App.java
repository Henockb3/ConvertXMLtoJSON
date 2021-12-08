import com.convertxml.controller.AddressBookController;
import com.convertxml.dao.AddressBookDAO;
import com.convertxml.ui.AddressBookView;
import com.convertxml.ui.UserIO;
import com.convertxml.ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) throws Exception {

        UserIO userIO = new UserIOConsoleImpl();
        AddressBookView view = new AddressBookView(userIO);
        AddressBookDAO addressBook = new AddressBookDAO();
        AddressBookController controller = new AddressBookController(addressBook, view);

        controller.run();


    }
}

