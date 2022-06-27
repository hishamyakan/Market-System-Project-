import java.io.IOException;
import java.net.Socket;


public class handler implements Runnable{

    private static final int userNameNotExists = 1;
    private static final int incorrectPassword = 2;
    private static final int validLogin = 3;

    private Communicator communicator;

    public handler(Socket clientSocket) throws IOException
    {

        this.communicator = new Communicator(clientSocket);
    }

    @Override
    public void run() {


        while(true)
        {

            String request = communicator.receiveMSG();
            //No Requests Arrived So Wait
            if(request == null)
            {
                continue;
            }


            else if (request.contains("Quit"))
            {
                System.out.println("Terminating This Thread");
                communicator.sendMSG("Quiting");
                Server_Market.terminateT(this);
                break;
            }

            else
            {
                handleClient(request);
            }


        }

        communicator.terminateCommunication();

    }


    private void handleClient(String request)
    {
        ServiceProvider provider = new ServiceProvider(communicator);

        if(request.contains("Check Login"))
        {
            System.out.println("Logging In");
            provider.login();
        }

        else if (request.contains("Add New Account"))
        {
            System.out.println("Add new account");
            provider.CreateNewAccount();
        }


        else if (request.contains("Get Client Information"))
        {
            System.out.println("Getting Client Information");
            provider.ViewAccountInformation();
        }


        else if (request.contains("Buy Product"))
        {
            //Enter Critical Section
            System.out.println("Buying Product");
            communicator.sendMSG("Buying Product");

            //Exit Critical Section
        }

        else if(request.contains("Search for Items")){
            System.out.println("Searching for Items");
            provider.SearchForItem();

        }
        //"Deposit Cash"

        else if (request.contains("Deposit Cash"))
        {
            System.out.println("Depositing Cash");
            provider.DepositCash();
        }
        else if (request.contains("Quit"))
        {

        }

        else
        {
            System.out.println("Invalid Request");
            System.out.println(request);
            communicator.sendMSG("Invalid");
        }


    }

    private Account getUserNameAndPassword()
    {
        System.out.println("Waiting For U and P");

        String UN = communicator.receiveMSG();
        String Pass = communicator.receiveMSG();

        Account AccountToValidate = new Account(UN,Pass);
        return  AccountToValidate;
    }



    //Functions to Validate The User Account

    private void ValidateUserAccount()
    {
        int x = Server_Market.findClientID(this);
        System.out.println("Checking Login for "+String.valueOf(x));
        Account newAccountToValidate = getUserNameAndPassword();
        //For Testing

        communicator.sendMSG("You Sent User Name: "+newAccountToValidate.getUserName());
        communicator.sendMSG("You Sent Password : "+newAccountToValidate.getPassword());
        //End Of For Testing

        //Call Function from Server Market
        //Get Result
        //Send Result

        //In Real Case Valid Or Invalid
        communicator.sendMSG("Finished Checking Login");
        System.out.println("Finished");
    }

    //Functions to Create New User Account
    private void CreateNewUserAccount()
    {
        int x = Server_Market.findClientID(this);
        System.out.println("Adding New Account for "+String.valueOf(x));
        communicator.sendMSG("Adding New Account");

    }




}
