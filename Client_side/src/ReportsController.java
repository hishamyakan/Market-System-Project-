import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {

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
    private Label books_remaining;

    @FXML
    private Label books_sold;

    @FXML
    private Label electronics_remaining;

    @FXML
    private Label electronics_sold;

    @FXML
    private Label fashion__remaining;

    @FXML
    private Label fashion_sold;

    @FXML
    private Label games_remaining;

    @FXML
    private Label games_sold;

    @FXML
    private Label home_furnature_remaining;

    @FXML
    private Label home_furnature_sold;

    @FXML
    private Button REPORTS;
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

    public ReportsController(){
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
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
        Client_Market c=new Client_Market();
        ArrayList<category>result=c.HandleAdminCategoryReport();
        if(result==null){
            try {
                BobUp.display("server is downnnnnnn","./serverFall.fxml",1,"assets/exit.png");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            books_remaining.setText(String.valueOf(result.get(0).getQuantity()));
            books_sold.setText(String.valueOf(result.get(0).getNo_of_sold_items()));
            electronics_remaining.setText(String.valueOf(result.get(1).getQuantity()));
            electronics_sold.setText(String.valueOf(result.get(1).getNo_of_sold_items()));
            fashion__remaining.setText(String.valueOf(result.get(2).getQuantity()));
            fashion_sold.setText(String.valueOf(result.get(2).getNo_of_sold_items()));
            games_remaining.setText(String.valueOf(result.get(4).getQuantity()));
            games_sold.setText(String.valueOf(result.get(4).getNo_of_sold_items()));
            home_furnature_remaining.setText(String.valueOf(result.get(3).getQuantity()));
             home_furnature_sold.setText(String.valueOf(result.get(3).getNo_of_sold_items()));

        }
    }
}

