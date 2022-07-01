import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OutOfstockController implements Initializable {

    @FXML
    private TableColumn<OutOfStockItems, Integer> AVAILABLEQUANTITES;

    @FXML
    private TableColumn<OutOfStockItems, String> ITEMDESCRIPTION;

    @FXML
    private TableColumn<OutOfStockItems, String> ITEMNAME;

    @FXML
    private TableView<OutOfStockItems> OUTOFSTOCKTABLE;

    private ObservableList<OutOfStockItems> OutOfStockList = FXCollections.observableArrayList();


    public OutOfstockController(){
        ArrayList<Item>out=publicData.out_of_stock;
        for (int i=0;i<out.size();i++){
            OutOfStockList.add(new OutOfStockItems(out.get(i).getName(), out.get(i).getDescription(),  (int)out.get(i).getQuantity(), out.get(i).getSerialNumber()));
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AVAILABLEQUANTITES.setCellValueFactory(new PropertyValueFactory<>("available_quantity"));
        ITEMDESCRIPTION.setCellValueFactory(cellData -> cellData.getValue().item_descriptionProperty());
        ITEMNAME.setCellValueFactory(cellData -> cellData.getValue().item_nameProperty());

        OUTOFSTOCKTABLE.setItems(OutOfStockList);

    }
}
