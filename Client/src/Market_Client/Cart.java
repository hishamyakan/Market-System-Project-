package Market_Client;
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
           totalPayment += i.getPrice();
        }


        return totalPayment;
    }

    public void addItem(Item item)
    {
        this.myItems.add(item);
    }

    public void removeItem(Item item) {
        for(Item i :this.myItems)
        {
            if(i.getName() == item.getName())
            {

                myItems.remove(i);
            }
        }

    }

    public void reduceQuantity(Item item,short Quantity) {
        for(Item i :this.myItems)
        {
            if(i.getName() == item.getName())
            {


                i.setQuantity(Quantity);
            }
        }


    }
}
