import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

//import SpecialException.ServerDownException;


public class Client_Market {

    private static final String IP= "127.0.0.1";
    private static final int port = 9090;
    private Communicator communicator;

    private String GUIReply = "NoReply";
    private String ServerState = "Down";
    private Account acc;



    public Client_Market () {

    }

    public void setContinueReply(String continueReply) {
        GUIReply = continueReply;
    }


    boolean establishCommunication(BufferedReader ClientInput) throws IOException {
        boolean flag = true;
        while (flag)
        {
            try {
                communicator = new Communicator(new Transceiver(new Socket(IP,port)));
                flag = true;
                System.out.println("Connected To Server\n");
                break;
            }
            catch (Exception e)
            {
                //Send Message Through GUI
                System.out.println("The server is down now. Would You Like To Try " +
                        "Again to Establish Communication? Yes/No");

                //Receive reply from GUI
                String ReplyOnServerDown = ClientInput.readLine();
                if(ReplyOnServerDown.contains("Yes"))
                {
                    System.out.println("Trying To Connect to Server...........");
                    flag = true;
                    //Do Nothing
                }
                else
                {
                    flag =false;
                }
            }
        }

        return flag;
    }

    public String getServerState() {
        return ServerState;
    }

    private boolean establishCommunication(String command)  {
        boolean flag = true;

            try {
                communicator = new Communicator(new Transceiver(new Socket(IP, port)));
                flag = true;
                System.out.println("Connected To Server\n");
                communicator.sendMSG(command);

            } catch (Exception e) {

                System.out.println("The server is down now. Would You Like To Try " +
                        "Again to Establish Communication? Yes/No");

                //Send Pop Up Message Through GUI


                flag = false;
                //while (GUIReply.contains("NoReply"));

                }

        return flag;
    }

    public String HandleLoginRequest(String UserName,String Pass)  {
        String reply = null;
        if(this.establishCommunication("Check Login"))
        {

            ServiceProvider sp = new ServiceProvider(this.communicator);
            String ServerReply =  sp.verifyAccount(new Account(UserName,Pass));
            if (ServerReply.contains("Invalid Username"))
            {
                //Display Message To GUI
                System.out.println("User Is Not Registered in the Systems");
                reply = "Invalid User Name";
            }
            else if(ServerReply.contains("Access Granted"))
            {
                // Open New Window
                System.out.println("Open New Window");
                reply = "Access Granted";
            }
            else if(ServerReply.contains("Invalid Password"))
            {
                //Send Invalid Password Message To GUI
                System.out.println("Invalid Password");
                reply = "Invalid Password";
            }

            else
            {
                System.out.println("Error Find the error");
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return reply;
    }

    public String HandleCreateAccount(String UserName,String Pass,String Name,
                                      String Address,String PhoneNumber) {

        String reply = null;

        if(this.establishCommunication("Add New Account")) {
            Account newAcc = new Account();
            newAcc.setName(Name);
            newAcc.setPassword(Pass);
            newAcc.setUserName(UserName);
            newAcc.setAddress(Address);
            newAcc.setPhoneNumber(PhoneNumber);

            ServiceProvider sp = new ServiceProvider(this.communicator);
            String ServerReply = sp.CreateNewAccount(newAcc);

            if (ServerReply.contains("User Name Already Exists")) {
                //Display Message To GUI
                System.out.println("User Name Already Exists");
                reply = "User Name Already Exists";
            } else if (ServerReply.contains("Account Is Created")) {
                // Open New Window
                System.out.println("Account Is Created");
                reply = "Account Is Created";
            } else {
                System.out.println("Error Find the error");
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return reply;

    }

    public Account HandleViewInformation(String UserName) {

        Account account = null;

        if(this.establishCommunication("Get Client Information")) {

            ServiceProvider sp = new ServiceProvider(this.communicator);
            account = sp.ViewInformation(UserName);

        }

        return  account;

    }

    public String HandleDepositCash(String UserName , String CreditCardNumber, double Amount) {

        String reply = "Down";

        if(this.establishCommunication("Deposit Cash")) {
            ServiceProvider sp = new ServiceProvider(this.communicator);
            reply =sp.DepositCash(UserName, CreditCardNumber, Amount);

        }
        return reply;
    }

    public ArrayList<Item> HandleSearchForItems(String Category ) {
        ArrayList<Item> items = null;
        if(this.establishCommunication("Search for Items")) {
            String itemName = "none";
            ServiceProvider sp = new ServiceProvider(this.communicator);
            items = sp.GetItems(Category, itemName);

        }
        return items;

    }

    public ArrayList<Item> HandleSearchForItems(String Category, String itemName ) {
        ArrayList<Item> items = null;
        if(this.establishCommunication("Search for Items")) {

            ServiceProvider sp = new ServiceProvider(this.communicator);
            items = sp.GetItems(Category, itemName);

        }
        return items;
    }


}
