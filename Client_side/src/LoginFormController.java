import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    @FXML
    private ImageView EYE;
    @FXML
    private Button EYE_BUTTON;

    @FXML
    private Button LOGINBUTTON;
    @FXML
    private Label WRONGLOGIN;
    @FXML
    public TextField USERNAME;
    @FXML
    private PasswordField PASSWORD;

    public String username;
    @FXML
    private ImageView loading;

    //int flag=0;

    public void userLogin(ActionEvent event) throws IOException {
        String name= USERNAME.getText().toString();
        String pass= PASSWORD.getText().toString();
        publicData.name=name;
        publicData.password=pass;
        boolean vaild= checkLogin(name,pass);
        String result;

        if(vaild){
            Client_Market c=new Client_Market();
            result=c.HandleLoginRequest(name,pass);
            System.out.println(result+" *************");
            if(result==null){
                BobUp.display("server is downnnnnnnnnn","./serverFall.fxml",1,"assets/exit.png");
            }
            else if(result.contains("Invalid Password")){
                WRONGLOGIN.setText("Invalid password");
            }else if(result.contains("Access Granted")){
                if(name.contains("admin")){
                    Account a = c.HandleViewInformation(publicData.name);
                    publicData.account=a;
                    Main m=new Main();
                    m.changeScene("./Admin.fxml");

                }else {
                    Main m=new Main();
                    m.changeScene("homePage.fxml");
                }
            }else if(result.contains("Invalid User Name")){
                WRONGLOGIN.setText("Invalid User Name");
            }
        }else{
            //
        }
//    Main m=new Main();
//    m.changeScene("./Admin.fxml");

    }

    public void createAccount(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("logUp.fxml");
    }
    private boolean checkLogin(String name,String pass) throws IOException {

        boolean result=true;
        if(name =="" && pass==""){
            WRONGLOGIN.setText("Please enter your Username & Password!");
            result=false;
        }
        else if(name =="" ){
            WRONGLOGIN.setText("Please enter your Username!");
            result=false;
        }
        else if(pass==""){
            WRONGLOGIN.setText("Please enter your Password!");
            result=false;
        }
        return result;
        ////////////////////////
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loading.setVisible(false);

    }
    @FXML
    void show_password(ActionEvent event) {
        PASSWORD.setPromptText(PASSWORD.getText());
        PASSWORD.setText("");
        PASSWORD.setDisable(true);
        USERNAME.setDisable(true);
        EYE_BUTTON.setDisable(true);
        PauseTransition pt=new PauseTransition();
        pt.setDuration(Duration.seconds(0.5));
        pt.setOnFinished(e->{
            PASSWORD.setText(PASSWORD.getPromptText());
            PASSWORD.setPromptText("");
            PASSWORD.setDisable(false);
            EYE_BUTTON.setDisable(false);
            USERNAME.setDisable(false);
        });
        pt.play();

    }

}
