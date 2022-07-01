import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private Button ADMINPROFILE;

    @FXML
    private Label LBLADDRESS;

    @FXML
    private ImageView LBLHISTORY;

    @FXML
    private Label LBLNAME;

    @FXML
    private Label LBLPHONENUMBER;

    @FXML
    private Label LBLUSERNAME;

    @FXML
    private Button LOGOUT;

    @FXML
    private Button ORDERS;

    @FXML
    private Button REPORTS;

    @FXML
    void Orders_on_click(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("./orders.fxml");

    }

    @FXML
    void REPORTS_on_click(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("./reports.fxml");
    }

    @FXML
    void adminlogout(ActionEvent event) throws IOException {
        boolean result=logout.display("Log out","Sure, you want to log out ?");
        if(result==true){
            Main m=new Main();
            m.changeScene("./loginForm.fxml");
        }
        publicData.cart.emptyCart();
    }

    @FXML
    void adminprofileonclick(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("./Admin.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Account p= publicData.account;

        LBLNAME.setText(p.getName());
        LBLUSERNAME .setText(p.getUserName());

    }
}
