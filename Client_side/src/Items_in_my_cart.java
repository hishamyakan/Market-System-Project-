import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;

import java.io.IOException;

public class Items_in_my_cart {

    private  SimpleStringProperty items;
    private  SimpleStringProperty Price;
    private  SimpleStringProperty Description;
    private  SimpleStringProperty Category;
    private ImageView Picture;
    private String serialnumber;
    private Button remove;
    private Spinner<Integer> spinbox;
    private int quantity;


    public Items_in_my_cart(String items, String price, String description, String category ,ImageView picture,String serialnumber,int q) {
        this.items = new SimpleStringProperty(items);
        Price = new SimpleStringProperty(price);
        Description = new SimpleStringProperty(description);
        Category= new SimpleStringProperty(category);
        Picture = picture;
        this.spinbox = new Spinner<>(1,1000,0);

        this.quantity=q;
        spinbox.getValueFactory().setValue(q);

        this.remove = new Button("Remove");
        /////
        this.remove.setOnAction(e ->{
            publicData.cart.removeItem(serialnumber);
            Main m=new Main();
            try {
                m.changeScene("./ViewCart.fxml");
            } catch (IOException ee) {
                ee.printStackTrace();
            }
        } );
        ///////
        spinbox.valueProperty().addListener((v,oldvalue,newvalue)->{
            publicData.cart.reduceQuantity(serialnumber,((Integer)newvalue).shortValue());
            Main m=new Main();
            try {
                m.changeScene("./ViewCart.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }

    public String getItems() {
        return items.get();
    }

    public SimpleStringProperty itemsProperty() {
        return items;
    }

    public void setItems(String items) {
        this.items.set(items);
    }

    public String getPrice() {
        return Price.get();
    }

    public SimpleStringProperty priceProperty() {
        return Price;
    }

    public void setPrice(String price) {
        this.Price.set(price);
    }

    public String getDescription() {
        return Description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description.set(description);
    }

    public String getCategory() {
        return Category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return Category;
    }

    public void setCategory(String category) {
        this.Category.set(category);
    }

    public ImageView getPicture() {
        return Picture;
    }

    public void setPicture(ImageView picture) {
        Picture = picture;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public Button getRemove() {
        return remove;
    }

    public void setRemove(Button remove) {
        this.remove = remove;
    }

    public Spinner<Integer> getSpinbox() {
        return spinbox;
    }

    public void setSpinbox(Spinner<Integer> spinbox) {
        this.spinbox = spinbox;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
