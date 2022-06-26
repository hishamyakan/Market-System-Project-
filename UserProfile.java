package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;


public class UserProfile {

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


    LoginForm user = new LoginForm();
    public String userData = user.username;

    public void ViewData(String userData){
        if(userData.equals("Nada Amgad")){


            LBLNAME.setText("Nada Amgad Sayed");
            LBLUSERNAME .setText("Nada Amgad");
            LBLEMAIL.setText("ABCD@gmail.com");
            LBLPHONENUMBER .setText("0123456789");
            LBLBIRTHDATE.setText("dd/mm/yyyy");
            LBLADDRESS.setText("Cairo");
            LBLCREDITCARD.setText("12345");
            LBLCASHBALANCE.setText("25000");

        }
        if(userData.equals("Nada Youssef")){
            LBLNAME.setText("Nada Youssef Ahmed");
            LBLUSERNAME .setText("Nada Youssef");
            LBLEMAIL.setText("DCBA@gmail.com");
            LBLPHONENUMBER .setText("0123456789");
            LBLBIRTHDATE.setText("dd/mm/yyyy");
            LBLADDRESS.setText("Cairo");
            LBLCREDITCARD.setText("12345");
            LBLCASHBALANCE.setText("25000");

        }
    }


    public void userlogout(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene( "sample.fxml");

    }

    public void userprofileonclick(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene( "UserProfile.fxml");
        //ViewData(userData);

    }

    public void viewcartonclick(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene( "ViewCart.fxml");

    }
    public void homepageonclick(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene( "afterLogin.fxml");

    }


}
