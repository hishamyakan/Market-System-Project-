package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
public class CreateNewAccount {

    @FXML
    private Button SIGNUP;
    @FXML
    private Label WEAKORMISMATCH;
    @FXML
    private Label MODERATE;
    @FXML
    private Label STRONGORMATCH;
    @FXML
    private Label emptyfeilds;
    @FXML
    private Label matching;
    @FXML
    private Label matching1;
    @FXML
    private TextField FIRSTNAME;
    @FXML
    private TextField LASTNAME;
    @FXML
    private TextField PHONE;
    @FXML
    private TextField ADDRESS;
    @FXML
    private TextField EMAIL;
    @FXML
    private PasswordField PASSWORD;
    @FXML
    private PasswordField CONFIRMPASSWORD;

    public String fname , lname , address, email , phonenumber , pass , confirmpass;

    public void signup(ActionEvent event) throws IOException {
       insertData();

    }
    private void insertData() throws IOException{
        fname = FIRSTNAME.getText().toString();
        lname = LASTNAME.getText().toString();
        phonenumber = PHONE.getText().toString();
        address = ADDRESS.getText().toString();
        email = EMAIL.getText().toString();
        pass = PASSWORD.getText().toString();
        confirmpass = CONFIRMPASSWORD.getText().toString();

       if(!(PASSWORD.getText().isEmpty() || CONFIRMPASSWORD.getText().isEmpty())) {
           if (pass.length() <= 7) {
               WEAKORMISMATCH.setText("Password is very weak");
               MODERATE.setText("");
               STRONGORMATCH.setText("");
           }
           else if (pass.length() < 12 && pass.length() >= 8) {
               MODERATE.setText("Password is Moderate");
               STRONGORMATCH.setText("");
               WEAKORMISMATCH.setText("");
           }
           else {
               STRONGORMATCH.setText("Password is Strong");
               WEAKORMISMATCH.setText("");
               MODERATE.setText("");
           }

           if (PASSWORD.getText().toString().equals(CONFIRMPASSWORD.getText().toString())) {
               matching.setText("Passwords Match");
               matching1.setText("");
           } else {
               matching1.setText("Passwords don't match");
               matching.setText("");
           }
       }
        if(!(FIRSTNAME.getText().isEmpty() || LASTNAME.getText().isEmpty() || PHONE.getText().isEmpty() || ADDRESS.getText().isEmpty() || EMAIL.getText().isEmpty() || PASSWORD.getText().isEmpty() || CONFIRMPASSWORD.getText().isEmpty() )){

            if (PASSWORD.getText().toString().equals(CONFIRMPASSWORD.getText().toString())) {

                Main m = new Main();
                m.changeScene("sample.fxml");

            } else {
                matching1.setText("Passwords don't match");
                matching.setText("");
            }


        }
        else{
            emptyfeilds.setText("Please fill in the empty fields");
        }
    }

}

