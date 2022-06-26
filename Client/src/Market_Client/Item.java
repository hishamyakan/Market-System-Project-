package Market_Client;
import java.awt.Image;



public class Item {

    String Name;
    short Price;
    String  SerialNumber;
    short Quantity;
    String description;
    Image image;
    String numberOfSoldItems;
    String category;

    public Item(String name, short price, String serialNumber, short quantity,
                String description, Image image, String numberOfSoldItems, String category) {
        Name = name;
        Price = price;
        SerialNumber = serialNumber;
        Quantity = quantity;
        this.description = description;
        this.image = image;
        this.numberOfSoldItems = numberOfSoldItems;
        this.category = category;
    }

    public Item()
    {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public short getPrice() {
        return Price;
    }

    public void setPrice(short price) {
        Price = price;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public short getQuantity() {
        return Quantity;
    }

    public void setQuantity(short quantity) {
        Quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getNumberOfSoldItems() {
        return numberOfSoldItems;
    }

    public void setNumberOfSoldItems(String numberOfSoldItems) {
        this.numberOfSoldItems = numberOfSoldItems;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
