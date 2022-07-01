import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewItemsController implements Initializable {

    @FXML
    private Label ORDER_ID_LABEL;
    @FXML
    private TableColumn<Item_In_Order, String> ITEMCATEGORY;

    @FXML
    private TableColumn<Item_In_Order, String> ITEMNAME;

    @FXML
    private TableColumn<Item_In_Order, String> ITEMPRICE;

    @FXML
    private TableColumn<Item_In_Order, String> ITEMQUANTITY;

    @FXML
    private TableView<Item_In_Order> ITEM_IN_ORDER_TABLE;

    private ObservableList<Item_In_Order> ItemsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ORDER_ID_LABEL.setText(publicData.order_id);
        ////
        ITEMNAME.setCellValueFactory(cellData -> cellData.getValue().itemsProperty());
        ITEMPRICE.setCellValueFactory(cellData -> cellData.getValue().priceProperty());

        ITEMCATEGORY.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        ITEMQUANTITY.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());


        ITEM_IN_ORDER_TABLE.setItems(ItemsList);
    }
    public ViewItemsController() throws IOException {

        Client_Market c=new Client_Market();
        ArrayList<Order> result = new ArrayList<>();
        if(publicData.name.contains("admin")){
            result =c.HandleAdminOrderReport();
        }
        else {
            result =c.HandleViewHistory(publicData.name);
        }


        javafx.scene.image.ImageView Pic1 = new ImageView("/assets/Cucumber.png");
        if(result==null){
            BobUp.display("server is downnnnnnnnnn","./serverFall.fxml",1,"assets/exit.png");
        }else {
            for (int i=0;i<result.size();i++){
                if(new String(String.valueOf(result.get(i).orderId)).equals(publicData.order_id)){
                    for (int j=0;j<result.get(i).getItems().size();j++){
                        System.out.println(result.get(i).getItems().get(j).getName());
                        ItemsList.add(new Item_In_Order(result.get(i).getItems().get(j).getName(),
                                                   String.valueOf(result.get(i).getItems().get(j).getPrice()),
                                                   result.get(i).getItems().get(j).getCategory(),
                                                    String.valueOf(result.get(i).getItems().get(j).getQuantity())
                                                    ));

                    }

                }

            }

        }

    }


}
