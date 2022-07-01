import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PurchaseController implements Initializable {

    @FXML
    private Button BACK;

    @FXML
    private RadioButton CASH_BALACE_RADIO;

    @FXML
    private RadioButton COD_RADIO;

    @FXML
    private Button CONFIRM;

    @FXML
    private Label MONEY_AT_PURCHASE;

    @FXML
    private ToggleGroup method;

    @FXML
    private Label NO_Balance_label;

    @FXML
    void back_on_click(ActionEvent event) {

        BACK.getScene().getWindow().hide();
    }

    @FXML
    void on_confirm_click(ActionEvent event) throws IOException {
        int flag=0;
        Client_Market c=new Client_Market();
        String option;
        if(CASH_BALACE_RADIO.isSelected()){
            option="credit";

        }else {
           option="delivery";

        }
        ArrayList<Item>result=c.HandlePurchase(publicData.name,option,publicData.cart.getMyItems());
        if(result==null){
            BobUp.display("server is downnnnnn","./serverFall.fxml",1,"assets/exit.png");
        }else {
            String s = c.getPurchaseResult();
            if(s.equals("Success")){
                publicData.cart.emptyCart();

            }else if(s.equals("No Enough Quantities")){
                publicData.out_of_stock=result;
                BobUp.display("Out of stock","./outOfstock.fxml",0,"assets/out-of-stock.png");

            }else if(s.equals("Not Enough Balance")){
                NO_Balance_label.setText("Not Enough Balance");
                flag=1;
            }
        }
        if(flag==0){
            BACK.getScene().getWindow().hide();
            Main m=new Main();
            m.changeScene("./ViewCart.fxml");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MONEY_AT_PURCHASE.setText(publicData.money_of_cart);
    }
}
