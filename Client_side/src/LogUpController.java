import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
public class LogUpController {

    @FXML
    private Button SIGNUP;
    @FXML
    private Label WEAKORMISMATCH;

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
    @FXML
    private Button BACK;

    public String fname , lname , address, email , phonenumber , pass , confirmpass;


    public void signup(ActionEvent event) throws IOException {
        WEAKORMISMATCH.setText("");
        emptyfeilds.setText("");
        matching1.setText("");
        matching.setText("");
       insertData();

    }
    private void insertData() throws IOException{
        fname = FIRSTNAME.getText().toString();
        lname = LASTNAME.getText().toString();
        phonenumber = PHONE.getText().toString();
        address = ADDRESS.getText().toString();
        email = EMAIL.getText().toString(); ///////////////change email to username
        pass = PASSWORD.getText().toString();
        confirmpass = CONFIRMPASSWORD.getText().toString();


        if(!(FIRSTNAME.getText().isEmpty() || LASTNAME.getText().isEmpty() || PHONE.getText().isEmpty() || ADDRESS.getText().isEmpty() || EMAIL.getText().isEmpty() || PASSWORD.getText().isEmpty() || CONFIRMPASSWORD.getText().isEmpty() )){

            if (PASSWORD.getText().toString().equals(CONFIRMPASSWORD.getText().toString())) {

                Client_Market c=new Client_Market();
                String result= c.HandleCreateAccount(email,pass,fname+" "+lname,address,phonenumber);
                if(result==null){
                    BobUp.display("server is downnnnnnnnnn","./serverFall.fxml",1,"assets/exit.png");
                }else if(result.contains("User Name Already Exists")){
                    System.out.println("User Name Already Exists");
                    WEAKORMISMATCH.setText("User Name Already Exists");
                }else if(result.contains("Account Is Created")){
                    Main m = new Main();
                    m.changeScene("loginForm.fxml");
                }

            } else {
                matching1.setText("Passwords don't match");
                matching.setText("");
            }


        }
        else{
            emptyfeilds.setText("Please fill in the empty fields");
        }
    }

    public void confirmPasswordWritten(ActionEvent event) throws IOException {
       CheckPasswords();
    }
    public void CheckPasswords(){

    if (PASSWORD.getText().toString().equals(CONFIRMPASSWORD.getText().toString())) {
        matching.setText("Passwords Match");
        matching1.setText("");
    } else {
        matching1.setText("Passwords don't match");
        matching.setText("");
    }
    }

    @FXML
    void back_to_login(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("./loginForm.fxml");
    }
}

