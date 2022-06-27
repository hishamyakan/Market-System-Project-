package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.IOException;
//import java.lang.*;
public class ViewCart {

    @FXML
    private Button LOGOUT;
    @FXML
    private Button USERPROFILE;
    @FXML
    private Button VIEWCART;
    @FXML
    private Button HOMEPAGE;

    /*LoginForm user = new LoginForm();
    String userData = user.username;*/


    public void userlogout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("LogOut");
        alert.setHeaderText("Are you sure you want to Logout?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            Main m = new Main();
            m.changeScene("sample.fxml");
        }

    }

    public void userprofileonclick(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene( "UserProfile.fxml");
        /*UserProfile view = new UserProfile();
        view.ViewData(userData);*/

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
