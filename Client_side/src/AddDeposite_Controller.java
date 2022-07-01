import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddDeposite_Controller {
    @FXML
    private Button ADD_DEPOSITE;//ok button

    @FXML
    private Button BACK;

    @FXML
    private Label pass_check;

    @FXML
    private TextField CREDIT_CARD_NUM_INPUT;

    @FXML
    private TextField MONEY_INPUT;

    @FXML
    private PasswordField PASSWORD_INPUT;


    @FXML
    void Add_deposite_on_click(ActionEvent event) throws IOException {
        if(!PASSWORD_INPUT.getText().isEmpty() || !MONEY_INPUT.getText().isEmpty() || !CREDIT_CARD_NUM_INPUT.getText().isEmpty() ){
            String pass=PASSWORD_INPUT.getText();
            String mone=MONEY_INPUT.getText();
            String credit_number=CREDIT_CARD_NUM_INPUT.getText();
            boolean check1=check_password(pass);
            boolean check2=check_money_correctiness(mone);

            if(check1==false){
                pass_check.setText("invaild password");
            }else if(check2==false){
                pass_check.setText("please, enter vaild money");
            }else if (check1==true && check2==true){
                pass_check.setText("");
                double money=Double.parseDouble(mone);

                Client_Market c=new Client_Market();
                String Result=c.HandleDepositCash(publicData.name,credit_number,money);

                if(Result==null){
                    BobUp.display("server is downnnnnnnnnn","./serverFall.fxml",1,"assets/exit.png");

                }else if(Result.contains("Deposit Is Stored")){
                    publicData.added_money=money;
                    ADD_DEPOSITE.getScene().getWindow().hide();
                }
            }

        }else {
            pass_check.setText("fill empty data");
        }


    }
    boolean check_password(String pass){

        return pass.equals(publicData.password);
    }
    boolean check_money_correctiness(String m){
        double money;
        try {
            money=Double.parseDouble(m);
        }catch (Exception e){
            return false;
        }
        if(money<=0 ){
            return false;
        }
        return true;
    }

    @FXML
    void back_to_user_profile(ActionEvent event) {
        ADD_DEPOSITE.getScene().getWindow().hide();
    }

}

