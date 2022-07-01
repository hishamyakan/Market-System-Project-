import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;


public class Item_gui {
    private  SimpleStringProperty items;
    private  SimpleStringProperty Price;
    private  SimpleStringProperty Description;
    private  SimpleStringProperty Category;
    private ImageView Picture;
    private String serialnumber;
    private Button addtocart;
    private Spinner<Integer> spinbox;
    private SimpleStringProperty quantity;


    public Item_gui(String items, String price, String description, String category ,ImageView picture,String serialnumber) {
        this.items = new SimpleStringProperty(items);
        Price = new SimpleStringProperty(price);
        Description = new SimpleStringProperty(description);
        Category= new SimpleStringProperty(category);
        Picture = picture;
        this.spinbox = new Spinner<>(1,50,0);
        spinbox.getValueFactory().setValue(1);


        this.addtocart = new Button("Add To Cart");
        this.addtocart.setOnAction(e -> {
            Integer qu= spinbox.getValue();
            Item i = new Item( items, new Double(price), serialnumber, qu.shortValue(), description,
                    null,  "", "", category);
            publicData.cart.addItem(i);
            System.out.println("cart size: "+publicData.cart.getMyItems().size());
            System.out.println("quantity "+qu.shortValue());
        });

    }

    public Item_gui(String items, String price, String category ,String quantity) {
        this.items = new SimpleStringProperty(items);
        Price = new SimpleStringProperty(price);

        Category= new SimpleStringProperty(category);
        this.quantity=new SimpleStringProperty(quantity);

    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
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
    public void setBt (String ButtonText){
        this.addtocart =new Button(ButtonText);
    }

}
