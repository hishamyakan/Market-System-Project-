package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;


public class afterLoginScreen implements Initializable {

    @FXML
    private Button LOGOUT;
    @FXML
    private Button USERPROFILE;
    @FXML
    private Button VIEWCART;
    @FXML
    private ChoiceBox<String> cat;
    @FXML
    private Label l1;
    @FXML
    private TableColumn<Item, String> ADDTOCART;
    @FXML
    private TableColumn<Item, String> DESCRIPTION;
    @FXML
    private Button HOMEPAGE;
    @FXML
    private TableColumn<Item, String> ITEM;
    @FXML
    private TableColumn<Item, String> PICTURE;
    @FXML
    private TableColumn<Item, String> PRICE;
    @FXML
    private TableColumn<Item, String> QUANTITY;
    @FXML
    private TableView<Item> TABLEVIEW;
    @FXML
    private TextField filterField;
    @FXML
    private Button AddToCart;
    @FXML
    private Spinner<Integer> SPINBOX;


    private String[] food = {"Fruits", "Vegetables", "Clothes", "Books"};
    private ObservableList<Item> dataList = FXCollections.observableArrayList();

    public afterLoginScreen(){

        ImageView Pic1 = new ImageView("C:/Users/Nada Amgad/IdeaProjects/MarketPlaceSystem/src/sample/assets/Cucumber.png/");
        Pic1.setFitHeight(120);
        Pic1.setFitWidth(120);
        ImageView Pic2 = new ImageView("C:/Users/Nada Amgad/IdeaProjects/MarketPlaceSystem/src/sample/assets/tomato.png/");
        Pic2.setFitHeight(120);
        Pic2.setFitWidth(120);
        ImageView Pic3 = new ImageView("C:/Users/Nada Amgad/IdeaProjects/MarketPlaceSystem/src/sample/assets/lemon.png/");
        Pic3.setFitHeight(120);
        Pic3.setFitWidth(120);
        ImageView Pic4 = new ImageView("C:/Users/Nada Amgad/IdeaProjects/MarketPlaceSystem/src/sample/assets/sugar.png/");
        Pic4.setFitHeight(120);
        Pic4.setFitWidth(120);


        dataList.add(new Item("Cucumber", "29.5" , "Fresh Organic Cucumbers",  Pic1));
        dataList.add(new Item("Tomato", "14" , "Fresh Organic Tomatoes", Pic2));
        dataList.add(new Item("Lemon", "20" , "Fresh Organic Lemons",Pic3));
        dataList.add(new Item("Sugar", "25" , "White sugar",Pic4));

    }




    public void userlogout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("LogOut");
        alert.setHeaderText("Are you sure you want to Logout?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            Main m = new Main();
            m.changeScene("sample.fxml");
        }


    }

    public void userprofileonclick(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("UserProfile.fxml");
        /*UserProfile view = new UserProfile();
        view.ViewData(userData);*/

    }

    public void viewcartonclick(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("ViewCart.fxml");

    }

    public void homepageonclick(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("afterLogin.fxml");

    }


    public void getfood(ActionEvent event) {
        String s = cat.getValue();
        l1.setText(s);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cat.getItems().addAll(food);
        cat.setOnAction(this::getfood);


        ITEM.setCellValueFactory(cellData -> cellData.getValue().itemsProperty());
        PRICE.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        DESCRIPTION.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        PICTURE.setCellValueFactory(new PropertyValueFactory<>("Picture"));
        ADDTOCART.setCellValueFactory(new PropertyValueFactory<Item, String>("addtocart"));
        QUANTITY.setCellValueFactory(new PropertyValueFactory<Item, String>("spinbox"));
        FilteredList<Item> filteredData = new FilteredList<>(dataList, p->true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(item.getItems().toLowerCase().contains(lowerCaseFilter)){
                return true;
                }
                else if (item.getPrice().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (item.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else
                    return false; // Does not match.
            });
        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Item> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(TABLEVIEW.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        TABLEVIEW.setItems(sortedData);
    }

    }







