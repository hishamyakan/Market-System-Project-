import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
public class Item_In_Order {
    private  SimpleStringProperty items;
    private  SimpleStringProperty Price;
   // private  SimpleStringProperty Description;
    private  SimpleStringProperty Category;

    private SimpleStringProperty quantity;


    public Item_In_Order(String items_name, String price,String category ,String quantity) {
        this.items = new SimpleStringProperty(items_name);
        Price = new SimpleStringProperty(price);
        Category= new SimpleStringProperty(category);

        this.quantity=new SimpleStringProperty(quantity);
    }


    public String getQuantity() {
        return quantity.get();
    }

    public SimpleStringProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity.set(quantity);
    }

    public String getCategory() { return Category.get(); }

    public SimpleStringProperty categoryProperty() { return Category; }

    public void setCategory(String category) { this.Category.set(category); }


    public String getItems() {
        return items.get();
    }

    public StringProperty itemsProperty() {
        return items;
    }

    public String getPrice() {
        return Price.get();
    }

    public StringProperty priceProperty() {
        return Price;
    }


    public void setItems(String items) {
        this.items.set(items);
    }

    public void setPrice(String price) {
        this.Price.set(price);
    }


}
