import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {

    int orderId;

    Date orderDate;

    Date DeliveryDate;

    Double paidAmount;


    public Order() {
    }

    public Order(int orderId, Date orderDate, Date deliveryDate, Double paidAmount) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        DeliveryDate = deliveryDate;
        this.paidAmount = paidAmount;
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

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }
}
