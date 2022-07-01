import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class OrdersController implements Initializable {

    @FXML
    private Label time_label;

    @FXML
    private Button ADMINPROFILE;

    @FXML
    private ImageView LBLHISTORY;

    @FXML
    private Button LOGOUT;

    @FXML
    private Button ORDERS;

    @FXML
    private Button REPORTS;

    @FXML
    private TableColumn<Order_gui,String> CLIENTNAME;

    @FXML
    private TableColumn<Order_gui,String> ORDERDATE;

    @FXML
    private TableColumn<Order_gui,String> ORDERID;

    @FXML
    private TableColumn<Order_gui,String> PAIDAMOUNT;

    @FXML
    private TableColumn<Order_gui,String> VIEWITEMS;
    @FXML
    private TableView<Order_gui> ORDERSTABLE;




    private ObservableList<Order_gui> OrderList = FXCollections.observableArrayList();


    //final Label clock = new Label();
    final DateFormat format = DateFormat.getInstance();
    final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),
            new EventHandler()
            {
                @Override
                public void handle(Event event) {
                    final Calendar cal = Calendar.getInstance();
                    time_label.setText(format.format(cal.getTime()));

                }

            }));

    public OrdersController() throws IOException {
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Client_Market  c=new Client_Market();
        ArrayList<Order> result =c.HandleAdminOrderReport();
        if(result==null){
            BobUp.display("server is downnnnnnnnnn","./serverFall.fxml",1,"assets/exit.png");
        }else {
            for (int i=0;i<result.size();i++){
                OrderList.add(new Order_gui( result.get(i).orderDate.toString() , new String(String.valueOf(result.get(i).orderId)),result.get(i).user_name, result.get(i).paidAmount.toString()));
            }

        }

    }

    @FXML
    void Orders_on_click(ActionEvent event) throws IOException {
            Main m=new Main();
            m.changeScene("./orders.fxml");
    }

    @FXML
    void REPORTS_on_click(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("./reports.fxml");
    }

    @FXML
    void adminlogout(ActionEvent event) throws IOException {
        boolean result=logout.display("Log out","Sure, you want to log out ?");
        if(result==true){
            Main m=new Main();
            m.changeScene("./loginForm.fxml");
        }
        publicData.cart.emptyCart();
    }

    @FXML
    void adminprofileonclick(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("./Admin.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CLIENTNAME.setCellValueFactory(cellData -> cellData.getValue().clientNameProperty());
        ORDERDATE.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        ORDERID.setCellValueFactory(cellData -> cellData.getValue().orderIDProperty());
        PAIDAMOUNT.setCellValueFactory(cellData -> cellData.getValue().paidAmountProperty());
        VIEWITEMS.setCellValueFactory(new PropertyValueFactory<Order_gui, String>("viewItems"));

        ORDERSTABLE.setItems(OrderList);
    }


}
