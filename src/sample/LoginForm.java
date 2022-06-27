package sample;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import java.io.IOException;

public class LoginForm {

    /*public LogIN(){

    }*/
    @FXML
    private Button LOGINBUTTON;
    @FXML
    private Label WRONGLOGIN;
    @FXML
    public TextField USERNAME;
    @FXML
    private PasswordField PASSWORD;

    public String username;


    //public String usernameAAA = USERNAME.getText().toString();


//    public String getUsernameAAA() {
//        return usernameAAA;
//    }

    public void userLogin(ActionEvent event) throws IOException {
        checkLogin();
    }

    public void createAccount(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("CreateNewAccount.fxml");
    }
    private void checkLogin() throws IOException {
        Main m = new Main();
        if ((USERNAME.getText().toString().equals("Nada Amgad") && PASSWORD.getText().toString().equals("12345@$")) || (USERNAME.getText().toString().equals("Nada Youssef") && PASSWORD.getText().toString().equals("54321@$"))) {

            username = USERNAME.getText().toString();

            m.changeScene("afterLogin.fxml");
        }
        else if(USERNAME.getText().isEmpty() && PASSWORD.getText().isEmpty()){
            WRONGLOGIN.setText("Please enter your Username & Password!");
        }
        else if(USERNAME.getText().isEmpty()){
            WRONGLOGIN.setText("Please enter your Username!");
        }
        else if(PASSWORD.getText().isEmpty()){
            WRONGLOGIN.setText("Please enter your Password!");
        }
        else{
            WRONGLOGIN.setText("Invalid Username or Password!");
        }
    }
}