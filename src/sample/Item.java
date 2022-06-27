package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;


public class Item {
    private final SimpleStringProperty items;
    private final SimpleStringProperty Price;
    private final SimpleStringProperty Description;
    private ImageView Picture;
    private Button addtocart;
    private Spinner spinbox;

    public Item(String items, String price, String description, ImageView picture) {
        this.items = new SimpleStringProperty(items);
        Price = new SimpleStringProperty(price);
        Description = new SimpleStringProperty(description);
        Picture = picture;
        this.addtocart = new Button("Add To Cart");
        this.spinbox = new Spinner(0,50,0);
    }

    public Button getAddtocart() {
        return addtocart;
    }

    public void setAddtocart(Button addtocart) {
        this.addtocart = addtocart;
    }

    public Spinner getSpinbox() {
        return spinbox;
    }

    public void setSpinbox(Spinner spinbox) {
        this.spinbox = spinbox;
    }

    public ImageView getPicture() {
        return Picture;
    }

    public void setPicture(ImageView picture) {
        Picture = picture;
    }

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

    public String getDescription() {
        return Description.get();
    }

    public StringProperty descriptionProperty() {
        return Description;
    }

    public void setItems(String items) {
        this.items.set(items);
    }

    public void setPrice(String price) {
        this.Price.set(price);
    }

    public void setDescription(String description) {
        this.Description.set(description);
    }
//    public Item(String item, String price, String description/*, ImageView picture*/) {
//        items = item;
//        Price = price;
//        Description = description;
//       // Picture = picture;
//    }
//
//    public String getItems() {
//        return items;
//    }
//
//    public String getPrice() {
//        return Price;
//    }
//
//    public String getDescription() {
//        return Description;
//    }
//
//    public void setItems(String items) {
//        this.items = items;
//    }
//
//    public void setPrice(String price) {
//        Price = price;
//    }
//
//    public void setDescription(String description) {
//        Description = description;
//    }
    //    public void setPicture(ImageView picture) {
//        Picture = picture;
//    }
}
