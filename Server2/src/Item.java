//import java.awt.Image;
//import java.io.Serializable;
//
//
//public class Item implements Serializable {
//
//    private String Name;
//    private double Price;
//    private String  SerialNumber;
//    private short Quantity;
//    private String description;
//    private Image image;
//    private String numberOfSoldItems;
//    private String category;
//
//    public Item(String name, double price, String serialNumber, short quantity,
//                String description, Image image, String numberOfSoldItems, String category) {
//        Name = name;
//        Price = price;
//        SerialNumber = serialNumber;
//        Quantity = quantity;
//        this.description = description;
//        this.image = image;
//        this.numberOfSoldItems = numberOfSoldItems;
//        this.category = category;
//    }
//
//    public Item()
//    {
//
//    }
//
//    public String getName() {
//        return Name;
//    }
//
//    public void setName(String name) {
//        Name = name;
//    }
//
//    public double getPrice() {
//        return Price;
//    }
//
//    public void setPrice(double price) {
//        Price = price;
//    }
//
//    public String getSerialNumber() {
//        return SerialNumber;
//    }
//
//    public void setSerialNumber(String serialNumber) {
//        SerialNumber = serialNumber;
//    }
//
//    public short getQuantity() {
//        return Quantity;
//    }
//
//    public void setQuantity(short quantity) {
//        Quantity = quantity;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public Image getImage() {
//        return image;
//    }
//
//    public void setImage(Image image) {
//        this.image = image;
//    }
//
//    public String getNumberOfSoldItems() {
//        return numberOfSoldItems;
//    }
//
//    public void setNumberOfSoldItems(String numberOfSoldItems) {
//        this.numberOfSoldItems = numberOfSoldItems;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//}
/******************************************************************************
 *
 * Module: Cart
 *
 * File Name: Item.java
 *
 * Description: Implement a class to be used as an Item in the market system
 *
 * Author : Hisham Yakan AbuBakr
 *******************************************************************************/

import java.io.Serializable;
import java.util.ArrayList;


public class Item implements Serializable {

    private String Name;
    private double Price;
    private String  SerialNumber;
    private short Quantity;
    private String description;
    private ArrayList<String> imageEncoded;
    private String image;
    private String numberOfSoldItems;
    private String category;

    public Item(String name, double price, String serialNumber, short quantity, String description,
                ArrayList<String> imageEncoded, String image, String numberOfSoldItems, String category) {
        Name = name;
        Price = price;
        SerialNumber = serialNumber;
        Quantity = quantity;
        this.description = description;
        this.imageEncoded = imageEncoded;
        this.image = image;
        this.numberOfSoldItems = numberOfSoldItems;
        this.category = category;
    }

    public Item(){

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
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

    public ArrayList<String> getImageEncoded() {
        return imageEncoded;
    }

    public void setImageEncoded(ArrayList<String> imageEncoded) {
        this.imageEncoded = imageEncoded;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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
