import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

import java.io.IOException;

public class Order_gui {

    private final SimpleStringProperty date;
    private final SimpleStringProperty orderID;
    private SimpleStringProperty clientName;
    private final SimpleStringProperty paidAmount;
    private Button viewItems;


    public Order_gui(String date, String orderID_, String clientName, String paidAmount) {
        this.date = new SimpleStringProperty(date);
        this.orderID = new SimpleStringProperty(orderID_);
        this.clientName = new SimpleStringProperty(clientName);
        this.paidAmount = new SimpleStringProperty(paidAmount);
        this.viewItems = new Button("View");

        this.viewItems.setOnAction(e -> {
            try {
                publicData.order_id=orderID_;
                BobUp.display("view items", "viewItems.fxml",0,"assets/greencart.png");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

    }




    public void setDate(String date) {
        this.date.set(date);
    }

    public void setOrderID(String orderID) {
        this.orderID.set(orderID);
    }

    public void setClientName(String clientName) {
        this.clientName.set(clientName);
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount.set(paidAmount);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public String getOrderID() {
        return orderID.get();
    }

    public SimpleStringProperty orderIDProperty() {
        return orderID;
    }

    public String getClientName() {
        return clientName.get();
    }

    public SimpleStringProperty clientNameProperty() {
        return clientName;
    }

    public String getPaidAmount() {
        return paidAmount.get();
    }

    public SimpleStringProperty paidAmountProperty() {
        return paidAmount;
    }

    public Button getViewItems() {
        return viewItems;
    }

    public void setViewItems(Button viewItems) {
        this.viewItems = viewItems;
    }
}
