import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OutOfStockItems {

private SimpleStringProperty item_name;
private SimpleStringProperty item_description;
private SimpleIntegerProperty available_quantity;
private String serial_number;

    public OutOfStockItems(String item_name, String item_description, Integer available_quantity, String serial_number) {
        this.item_name = new SimpleStringProperty(item_name);
        this.item_description = new SimpleStringProperty(item_description);
        this.available_quantity = new SimpleIntegerProperty(available_quantity);
        this.serial_number = serial_number;
    }

    public String getItem_name() {
        return item_name.get();
    }

    public SimpleStringProperty item_nameProperty() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name.set(item_name);
    }

    public String getItem_description() {
        return item_description.get();
    }

    public SimpleStringProperty item_descriptionProperty() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description.set(item_description);
    }

    public int getAvailable_quantity() {
        return available_quantity.get();
    }

    public SimpleIntegerProperty available_quantityProperty() {
        return available_quantity;
    }

    public void setAvailable_quantity(int available_quantity) {
        this.available_quantity.set(available_quantity);
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }
}
