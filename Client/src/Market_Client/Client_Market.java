package Market_Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import SpecialException.ServerDownException;


public class Client_Market {

    private static final String IP= "127.0.0.1";
    private static final int port = 9090;
    private Communicator communicator;

    private String ContinueReply;
    private Account acc;



    Client_Market () {

    }

    public void setContinueReply(String continueReply) {
        ContinueReply = continueReply;
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

    boolean establishCommunication() throws ServerDownException {
        boolean flag = true;
        while (flag) {
            try {
                communicator = new Communicator(new Transceiver(new Socket(IP, port)));
                flag = true;
                System.out.println("Connected To Server\n");
                break;
            } catch (Exception e) {

                System.out.println("The server is down now. Would You Like To Try " +
                        "Again to Establish Communication? Yes/No");
                throw new ServerDownException();
                //Send Pop Up Message Through GUI

            }

            //Receive reply from GUI
            finally {

                if (this.ContinueReply.contains("Yes")) {

                    System.out.println("Trying To Connect to Server...........");
                    flag = true;
                    //Do Nothing
                } else {
                    flag = false;
                }
            }
        }


        return flag;
    }


    public String HandleLoginRequest(String UserName,String Pass)  {
        String reply = null;
        if(this.establishCommunication())
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

        if(this.establishCommunication()) {
            Account newAcc = new Account(UserName, Pass, Name,
                    Address, PhoneNumber);

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

        if(this.establishCommunication()) {

            ServiceProvider sp = new ServiceProvider(this.communicator);
            account = sp.ViewInformation(UserName);

        }

        return  account;

    }

    public String HandleDepositCash(String UserName , String CreditCardNumber, short  Amount) {
        ServiceProvider sp = new ServiceProvider(this.communicator);
        return sp.DepositCash(UserName ,CreditCardNumber,Amount);
    }

    public ArrayList<Item> HandleSearchForItems()
    {
        return null;
    }







}
