import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ServiceProvider
{

    private Communicator communicator;

    private DatabaseManager manager;

    public ServiceProvider(Communicator communicator) {
        this.communicator = communicator;
        manager = new DatabaseManager();
    }

    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
    }

    public void login()
    {
        Account account = communicator.receiveAccount();

        AccountType type;
        if(account.getUserName().contains("admin")){

           type = AccountType.ADMIN;

        }

        else{

            type = AccountType.CLIENT;

        }

        String Password = manager.getPassword(account.getUserName() , type);

        if (Password == null)
        {
            communicator.sendMSG("Invalid UserName");
        }
        else if(Password.equals(account.getPassword()))
        {
            communicator.sendMSG("Access Granted");
        }
        else
        {
            communicator.sendMSG("Invalid Password");
        }


    }

    public void CreateNewAccount()
    {
        Account account = communicator.receiveAccount();

        //check if username is repeated or not (from DB)
        String pass = manager.getPassword(account.getUserName() , AccountType.CLIENT);

        if (pass ==null)
        {
            communicator.sendMSG("Account is Created");
            manager.StoreAccount(account);
        }else
        {
            communicator.sendMSG("User Name Already Exists");
        }
    }

    public void ViewAccountInformation()
    {
        String UserName = communicator.receiveMSG();
        //search in db

        AccountType type = AccountType.CLIENT;

        if(UserName.contains("admin")){
            type = AccountType.ADMIN;
        }

        Account account = manager.getAccount(UserName , type);

        if(account == null){
            communicator.sendAccount(new Account());
        }
        else
            communicator.sendAccount(account);
    }

    public void SearchForItem()
    {
        String Category = communicator.receiveMSG();
        String ItemName = communicator.receiveMSG();

        //search for item in Database and return the no. of items remaining and then
        ArrayList<Item> ItemList = new ArrayList<>();


        if (Category.equals("all"))
        {
//            myItem.setName("All Items");
//            ItemList.add(myItem);

                ItemList = manager.getAllItems();

        }

        else if(ItemName.equals("none") /*|| *doesn't exist in DB*/)
        {
//            myItem.setName("All same category items");
//            ItemList.add(myItem);

            ItemList = manager.getAllItems(Category);

        }

        else{
            ItemList = manager.getAllItems(Category , ItemName);

            if(ItemList.isEmpty()){
                ItemList = manager.getAllItems(Category);
            }
        }

        encodeAllItemImages(ItemList);

        communicator.sendItems(ItemList);
    }


    public void DepositCash()
    {
        String UserName = communicator.receiveMSG();
        String CreditCardNumber= communicator.receiveMSG();
        double DepositedAmount = communicator.receiveDouble(); //to be deposited
        if(manager.getPassword(UserName,AccountType.CLIENT) == null)
        {
            communicator.sendMSG("User Name Does not exists");
        }
        //access DB
        double CashBalance = manager.getCashBalance(UserName); //eli f elcredit card

        CashBalance+=DepositedAmount;

        manager.storeCashBalance(UserName,CashBalance);

        communicator.sendMSG("Deposit Is Stored");
    }


    public void sendCategoryReport(){

        try {
            ArrayList<category> arr = CategoryTable.Category_report();
            communicator.sendCategories(arr);


        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void sendOrderReport(){

        ArrayList<Order> orders = manager.getOrders(null);

        communicator.sendOrders(orders);

    }


    void purchaseItems(){

        String username = communicator.receiveMSG();

        String flag = communicator.receiveMSG();

        ArrayList<Item> items = communicator.receiveItems();

        ArrayList<Item> corresponding = manager.getCorrespondingItems(items);

        ArrayList<Item> mashakel = new ArrayList<>();

        for(int i = 0 ; i < items.size() ; i++){

            if(items.get(i).getQuantity() > corresponding.get(i).getQuantity()){

                Item temp = items.get(i);
                temp.setQuantity((short) (/*items.get(i).getQuantity() - */corresponding.get(i).getQuantity()));
                mashakel.add(temp);

            }

        }

        double balance = manager.getCashBalance(username);
        double paidAmount = calculatePayment(items);
        boolean enough = (balance >= paidAmount);


        if(mashakel.isEmpty() && (!flag.contains("credit") || enough)){

            communicator.sendMSG("Success");
            communicator.sendItems(new ArrayList<>());

//            double paidAmount = calculatePayment(items);

            Order newOrder = new Order();

            newOrder.setPaidAmount(paidAmount);

            newOrder.setUser_name(username);

            Date today = Calendar.getInstance().getTime();

            newOrder.setOrderDate(today);

            newOrder.setDeliveryDate(null);

            newOrder.setItems(items);

            manager.storeOrder(newOrder);

            if(flag.contains("credit")){

                manager.storeCashBalance(username , balance - paidAmount);

            }

        }

        else{
            String message = "";

            if(!mashakel.isEmpty()){

                message = "No Enough Quantities";

            }

            else if(flag.contains("credit")){

                message = "Not Enough Balance";

            }
            communicator.sendMSG(message);
            communicator.sendItems(mashakel);
        }

    }

    private double calculatePayment(ArrayList<Item> items) {

        double amount = 0.0 ;

        for(Item i : items){

            amount = amount + i.getQuantity()*i.getPrice();

        }

        return amount;

    }


    public void sendHistory() {

        String username = communicator.receiveMSG();

        if(manager.getPassword(username , AccountType.CLIENT) == null){

            communicator.sendOrders(new ArrayList<>());

        }
        else{

            ArrayList<Order> orders = manager.getOrders(username);
            communicator.sendOrders(orders);

        }



    }

    public static void encodeAllItemImages(ArrayList<Item>items){
        for(int i = 0 ; i < items.size() ; i++){
            try {
                items.get(i).setImageEncoded( ImageEncoder.encodeImage(items.get(i).getImage()));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
