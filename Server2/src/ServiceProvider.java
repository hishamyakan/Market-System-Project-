import java.util.ArrayList;

public class ServiceProvider
{

    private Communicator communicator;

    public ServiceProvider(Communicator communicator) {
        this.communicator = communicator;
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

        String Password = "12346";
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
        String UserName = "Nadine";
        if (UserName ==null)
        {
            communicator.sendMSG("Account is Created");
            //and then store it into DB
        }else
        {
            communicator.sendMSG("User Name Already Exists");
        }
    }

    public void ViewAccountInformation()
    {
        String UserName = communicator.receiveMSG();
        //search in db
        Account account = new Account();

        account.setName("Hussam");
        account.setPassword("1245");
        account.setAddress("Madinty");

        communicator.sendAccount(account);
    }

    public void SearchForItem()
    {
        String Category = communicator.receiveMSG();
        String ItemName = communicator.receiveMSG();

        //search for item in Database and return the no. of items remaining and then
        ArrayList<Item> I = new ArrayList<>();

        Item myItem = new Item();
        myItem.setName("Playstation");
        I.add(myItem);

        myItem = new Item();
        myItem.setName("T shirt");
        I.add(myItem);


        myItem = new Item();
        myItem.setName("Laptop");
        I.add(myItem);





        if (Category.equals("all"))
        {
            myItem.setName("All Items");
            I.add(myItem);
        }
        if(ItemName.equals("none") /*|| *doesn't exist in DB*/)
        {
            myItem.setName("All same category items");
            I.add(myItem);
        }
        communicator.sendItems(I);
    }


    public void DepositCash()
    {
        String UserName = communicator.receiveMSG();
        String CreditCardNumber= communicator.receiveMSG();
        double DepositedAmount = communicator.receiveDouble(); //to be deposited
        if(UserName == null)
        {
            communicator.sendMSG("User Name Does not exists");
        }
        //access DB
        double CashBalance =0.0; //eli f elcredit card
        CashBalance+=DepositedAmount;

        communicator.sendMSG("Deposit Is Stored");
    }



}
