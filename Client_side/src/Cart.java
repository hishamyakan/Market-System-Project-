/******************************************************************************
 *
 * Module: Cart
 *
 * File Name: Client_Market.java
 *
 * Description: Implement a class to be used by the GUI handling the view cart window
 *
 * Author : Hisham Yakan AbuBakr
 *******************************************************************************/



import java.util.ArrayList;

public class Cart {
    private ArrayList<Item> myItems;

    public Cart() {
    }

    public Cart(ArrayList<Item> myItems) {
        this.myItems = myItems;
    }

    public ArrayList<Item> getMyItems() {
        return myItems;
    }

    public double totalPayment() {
        double totalPayment = 0;

        for(Item i :this.myItems)
        {
           totalPayment += i.getPrice()*i.getQuantity();
        }


        return totalPayment;
    }

    public void addItem(Item item)
    {
        for (int i = 0 ; i< this.myItems.size(); i++){
            if(item.getSerialNumber().equals(this.myItems.get(i).getSerialNumber())){
                myItems.get(i).setQuantity((short) (myItems.get(i).getQuantity()+ item.getQuantity()));
                System.out.println(myItems.get(i).getQuantity());
                return;
            }
        }
        this.myItems.add(item);
        System.out.println(item.getQuantity());
    }

    public void removeItem(String serialNumber) {
        for(int i = 0 ; i < myItems.size() ; i++)
        {
            if(myItems.get(i).getSerialNumber().equals(serialNumber))
            {
                myItems.remove(i);
            }
        }

    }
//set quantity
    public void reduceQuantity(String serialNumber,short Quantity) {
        for(int i = 0 ; i < myItems.size() ; i++)
        {
            if(myItems.get(i).getSerialNumber().equals(serialNumber))
            {
                myItems.get(i).setQuantity(Quantity);
            }
        }


    }


    public boolean isEmpty(){
        return this.myItems.isEmpty();
    }


    public void emptyCart(){
        if(this.getMyItems().size()==0)return;
        this.myItems.clear();

    }
}
