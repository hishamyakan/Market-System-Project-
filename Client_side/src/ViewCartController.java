import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//import java.lang.*;
public class ViewCartController implements Initializable {
    @FXML
    private Label MONEY_LABEL;
    @FXML
    private Button PURCHASE_BUTTON;
    @FXML
    private Button HISTORY;
    @FXML
    private Button LOGOUT;
    @FXML
    private Button USERPROFILE;
    @FXML
    private Button VIEWCART;
    @FXML
    private Button HOMEPAGE;

    @FXML
    private TableColumn<Items_in_my_cart, String> REMOVE;
    @FXML
    private TableColumn<Items_in_my_cart, String> DESCRIPTION2;
    @FXML
    private TableColumn<Items_in_my_cart, String> ITEM2;

    @FXML
    private TableColumn<Items_in_my_cart, String> PRICE2;
    @FXML
    private TableColumn<Items_in_my_cart, String> QUANTITY2;
    @FXML
    private TableColumn<Items_in_my_cart, String> CATEGORY2;
    @FXML
    private TableView<Items_in_my_cart> TABLEVIEW2;
    private ObservableList<Items_in_my_cart> itemList = FXCollections.observableArrayList();


    public void userlogout(ActionEvent event) throws IOException {
        boolean result=logout.display("Log out","Sure, you want to log out ?");
        if(result==true){
            Main m=new Main();
            m.changeScene("./loginForm.fxml");
        }
        publicData.cart.emptyCart();

    }

    public void userprofileonclick(ActionEvent event) throws IOException{
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

    public void viewcartonclick(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene( "ViewCart.fxml");

    }
    public void homepageonclick(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("homePage.fxml");

    }
    @FXML
    void view_history_page(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("./historyClient.fxml");
    }



    public ViewCartController(){

        for(int i = 0; i<publicData.cart.getMyItems().size();i++){
            Item item = publicData.cart.getMyItems().get(i);
            itemList.add(new Items_in_my_cart(item.getName(),
                                             String.valueOf(item.getPrice()),
                                                item.getDescription(),
                                             item.getCategory(),
                                             null,
                                                item.getSerialNumber(),
                                            item.getQuantity()
                    ));

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MONEY_LABEL.setText(String.valueOf(publicData.cart.totalPayment()));
        ITEM2.setCellValueFactory(new PropertyValueFactory<>("items"));
        PRICE2.setCellValueFactory(new PropertyValueFactory<>("Price"));
        CATEGORY2.setCellValueFactory(new PropertyValueFactory<>("Category"));
        DESCRIPTION2.setCellValueFactory(new PropertyValueFactory<>("Description"));
        QUANTITY2.setCellValueFactory(new PropertyValueFactory<>("spinbox"));
        REMOVE.setCellValueFactory(new PropertyValueFactory<>("remove"));
        TABLEVIEW2.setItems(itemList);

    }


    public void PurchaseClicked(ActionEvent actionEvent) throws IOException {

        if(MONEY_LABEL.getText().equals("0.0")){

        }else {
            publicData.money_of_cart=MONEY_LABEL.getText();
            BobUp.display("Purchase","./Purchase.fxml",0,"assets/purchase.png");
        }



    }
}
