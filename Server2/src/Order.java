import java.util.ArrayList;
import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable
{
    int orderId;

    Date orderDate;

    Date DeliveryDate;

    Double paidAmount;

    String user_name;

    ArrayList<Item> items;

    public Order() {
        this.items = new ArrayList<>();
    }

    public Order(int orderId, Date orderDate, Date deliveryDate, Double paidAmount) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        DeliveryDate = deliveryDate;
        this.paidAmount = paidAmount;
        this.items = new ArrayList<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        DeliveryDate = deliveryDate;
    }


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}