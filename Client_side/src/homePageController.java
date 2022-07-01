import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.Spinner;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;


public class homePageController implements Initializable {

    @FXML
    private Label PLEASE;

    @FXML
    private Label PLEASE_SELECT;

    @FXML
    private Label PLEASE_SELECT_CATEGORY;

    @FXML
    private Button SEARCHBUTTON;
    @FXML
    private Button LOGOUT;
    @FXML
    private Button USERPROFILE;
    @FXML
    private Button VIEWCART;
    @FXML
    private ComboBox<String> CATEGORYCB;
    @FXML
    private TableColumn<Item_gui, String> ADDTOCART;
    @FXML
    private TableColumn<Item_gui, String> DESCRIPTION;
    @FXML
    private Button HOMEPAGE;
    @FXML
    private TableColumn<Item_gui, String> ITEM;
    @FXML
    private TableColumn<Item_gui, String> PICTURE;
    @FXML
    private TableColumn<Item_gui, String> PRICE;
    @FXML
    private TableColumn<Item_gui, String> QUANTITY;
    @FXML
    private TableColumn<Item_gui, String> CATEGORY;
    @FXML
    private TableView<Item_gui> TABLEVIEW;
    @FXML
    private TextField filterField;
    @FXML
    private Button AddToCart;
    @FXML
    private Spinner<Integer> SPINBOX;
    @FXML
    private Button HISTORY;

    @FXML
    void search_on_click(ActionEvent event) throws IOException {
        String category_selected=CATEGORYCB.getValue();
        String item_name=filterField.getText();
        System.out.println(category_selected);
        System.out.println(item_name);

        ArrayList<Item> result=new ArrayList<>();
        Client_Market c=new Client_Market(publicData.path);
        if( category_selected==null){
            PLEASE.setText("please");
            PLEASE_SELECT.setText("select");
            PLEASE_SELECT_CATEGORY.setText("category");
            PauseTransition pt=new PauseTransition();
            pt.setDuration(Duration.seconds(1));
            pt.setOnFinished(e->{
                PLEASE.setText("");
                PLEASE_SELECT.setText("");
                PLEASE_SELECT_CATEGORY.setText("");
            });
             pt.play();

        }else if(category_selected!=null && item_name!=""){
            result= c.HandleSearchForItems(category_selected,item_name);
        }else if(category_selected!=null && item_name=="" ) {
            result= c.HandleSearchForItems(category_selected);
        }


        if(result==null){
            BobUp.display("server is downnnnnnnnnn","./serverFall.fxml",1,"assets/exit.png");
        }else {
            if(result.size()!=0){
                dataList.clear();
                for (int i=0;i<result.size();i++){
                    ImageView Pic1 =new ImageView(result.get(i).getImage()) ;
                    Pic1.setFitHeight(120);
                    Pic1.setFitWidth(120);
                    dataList.add(new Item_gui(result.get(i).getName(), String.valueOf(result.get(i).getPrice()) , result.get(i).getDescription(), result.get(i).getCategory(), Pic1,result.get(i).getSerialNumber()));

                }
                TABLEVIEW.setItems(dataList);

            }
            }

    }
    private String[] food = {"Book","Electronics","Fashion","Home Furniture","Video Games"};
    private ObservableList<Item_gui> dataList = FXCollections.observableArrayList();
    private final FilteredList<Item_gui> filteredCategory = new FilteredList<>(dataList);

    public homePageController() throws IOException {



        Client_Market c=new Client_Market(publicData.path);
        ArrayList<Item> result= c.HandleSearchForItems("all");
        if(result==null){
            BobUp.display("server is downnnnnnnnnn","./serverFall.fxml",1,"assets/exit.png");
        }else {
            for (int i=0;i<result.size();i++){
                ImageView Pic1 =new ImageView(result.get(i).getImage()) ;
                Pic1.setFitHeight(120);
                Pic1.setFitWidth(120);
                dataList.add(new Item_gui(result.get(i).getName(), String.valueOf(result.get(i).getPrice()) , result.get(i).getDescription(), result.get(i).getCategory(), Pic1,result.get(i).getSerialNumber()));

            }

        }

    }




    public void userlogout(ActionEvent event) throws IOException {
        boolean result=logout.display("Log out","Sure, you want to log out ?");
        if(result==true){
            Main m=new Main();
            m.changeScene("./loginForm.fxml");
        }

        publicData.cart.emptyCart();

    }

    public void userprofileonclick(ActionEvent event) throws IOException {

        Client_Market c=new Client_Market();
        Account a = c.HandleViewInformation(publicData.name);
        if(a==null){
            //alertBox.display("downnnnnnnnn","server fall");
            BobUp.display("server is downnnnnnnnnn","./serverFall.fxml",1,"assets/exit.png");
        }else{
            publicData.account=a;
            Main m = new Main();
            m.changeScene("UserProfile.fxml");
        }

    }

    public void viewcartonclick(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("./ViewCart.fxml");

    }

    public void homepageonclick(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("homePage.fxml");

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CATEGORYCB.getItems().addAll(food);
        //CATEGORYCB.setOnAction(this::getfood);


        ITEM.setCellValueFactory(cellData -> cellData.getValue().itemsProperty());
        PRICE.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        DESCRIPTION.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        CATEGORY.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        PICTURE.setCellValueFactory(new PropertyValueFactory<>("Picture"));
        ADDTOCART.setCellValueFactory(new PropertyValueFactory<Item_gui, String>("addtocart"));
        QUANTITY.setCellValueFactory(new PropertyValueFactory<Item_gui, String>("spinbox"));

        TABLEVIEW.setItems(dataList);

    }
    @FXML
    void view_history_page(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("historyClient.fxml");
    }

    }







