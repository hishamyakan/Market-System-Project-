import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class UserProfileController implements Initializable {

    @FXML
    private Button LOGOUT;
    @FXML
    private Button USERPROFILE;
    @FXML
    private Button VIEWCART;
    @FXML
    private Button HOMEPAGE;
    @FXML
    private Label LBLNAME ;
    @FXML
    private Label LBLUSERNAME ;
    @FXML
    private Label LBLEMAIL;
    @FXML
    private Label LBLPHONENUMBER ;
    @FXML
    private Label LBLBIRTHDATE ;
    @FXML
    private Label LBLADDRESS;
    @FXML
    private Label LBLCREDITCARD ;
    @FXML
    private Label LBLCASHBALANCE;
    @FXML
    private Button ADD_DEPOSITE;

    @FXML
    private Button HISTORY;

    public void userlogout(ActionEvent event) throws IOException {
        boolean result=logout.display("Log out","Sure, you want to log out ?");
        if(result==true){
            Main m=new Main();
            m.changeScene("./loginForm.fxml");
        }
        publicData.cart.emptyCart();
    }


    public void viewcartonclick(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene( "ViewCart.fxml");

    }
    public void homepageonclick(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("homePage.fxml");

    }

    public void userprofileonclick(ActionEvent event) throws IOException {

        Client_Market c=new Client_Market();
        Account a = c.HandleViewInformation(publicData.name);
        if(a==null){

            BobUp.display("server is downnnnnnnnnn","./serverFall.fxml",1,"assets/exit.png");
        }else{
            publicData.account=a;
            Main m = new Main();
            m.changeScene("UserProfile.fxml");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Account p= publicData.account;

        LBLNAME.setText(p.getName());
        LBLUSERNAME .setText(p.getUserName());

        LBLPHONENUMBER .setText(p.getPhoneNumber());

        LBLADDRESS.setText(p.getAddress());

        LBLCASHBALANCE.setText(new String(String.valueOf(p.getCashBalance()))); ////////////////check

    }

    @FXML
    void Add_deposite_on_click(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("./add_deposite.fxml"));
        Scene scene = new Scene(root);
        Stage window = new Stage();
        window.getIcons().add(new Image("assets/deposit3.png"));
        window.setResizable(false);
        window.setTitle("Add deposite");
        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);

        window.setScene(scene);
        window.showAndWait();
        LBLCASHBALANCE.setText(new String(String.valueOf(( Double.parseDouble(LBLCASHBALANCE.getText())+publicData.added_money))));
        publicData.added_money=0;
    }
    @FXML
    void view_history_page(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("./historyClient.fxml");
    }
}