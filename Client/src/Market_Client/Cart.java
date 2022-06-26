package Market_Client;
import java.util.ArrayList;

public class Cart {
    ArrayList<Item> myItems;

    public Cart() {
    }

    public Cart(ArrayList<Item> myItems) {
        this.myItems = myItems;
    }


    public short totalPayment()
    {
        short totalPayment = 0;

        for(Item i :this.myItems)
        {
           totalPayment += i.getPrice();
        }


        return totalPayment;
    }

    public void removeItem(Item item)
    {
        for(Item i :this.myItems)
        {
            if(i.getSerialNumber() == item.getSerialNumber())
            {

            }
        }

    }

}
