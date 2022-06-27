import java.util.ArrayList;

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


//        String u = communicator.receiveMSG();
//        String p = communicator.receiveMSG();
//
//        Account account = new Account(u,p);

        AccountType type;
        if(account.getUserName().contains("admin")){

           type = AccountType.ADMIN;

        }

        else{

            type = AccountType.CLIENT;

        }

        String Password = manager.getPassword(account.getUserName() , type);
        //ezai hageb data mn el DB??(username is searched for in database)
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
        Account account = manager.getAccount(UserName);

//        account.setName("Hussam");
//        account.setPassword("1245");
//        account.setAddress("Madinty");

        communicator.sendAccount(account);
    }

    public void SearchForItem()
    {
        String Category = communicator.receiveMSG();
        String ItemName = communicator.receiveMSG();

        //search for item in Database and return the no. of items remaining and then
        ArrayList<Item> ItemList = new ArrayList<>();

//        Item myItem = new Item();
//        myItem.setName("Playstation");
//        ItemList.add(myItem);
//
//        myItem = new Item();
//        myItem.setName("T shirt");
//        ItemList.add(myItem);
//
//
//        myItem = new Item();
//        myItem.setName("Laptop");
//        ItemList.add(myItem);


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
        }
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



}
