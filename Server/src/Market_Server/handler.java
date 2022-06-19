package Market_Server;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class handler implements Runnable{

    private static final int userNameNotExists = 1;
    private static final int incorrectPassword = 2;
    private static final int validLogin = 3;


    private Socket Client_To_Handle;
    private BufferedReader myIn;
    private PrintWriter myOut;

    public handler(Socket clientSocket) throws IOException
    {
        this.Client_To_Handle = clientSocket;
        this.myIn = new BufferedReader(new InputStreamReader(Client_To_Handle.getInputStream()));
        this.myOut = new PrintWriter(Client_To_Handle.getOutputStream(),true);
    }

    @Override
    public void run() {


        while(true)
        {
            String request = receiveClientMSG();

            //No Requests Arrived So Wait
            if(request == null)
            {
                continue;
            }


            else if (request.contains("Quit"))
            {
                System.out.println("Terminating This Thread");
                myOut.println("Quiting");
                Server_Market.terminateT(this);
                break;
            }

            else
            {
                handleClient(request);
            }


        }

        myOut.close();
        try {
            myIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


    private void handleClient(String request)
    {
        if(request.contains("Check Login"))
        {
            ValidateUserAccount();
        }

        else if (request.contains("Add New Account"))
        {
            CreateNewUserAccount();
        }

        else if (request.contains("Buy Product"))
        {
            //Enter Critical Section
            System.out.println("Buying Product");
            myOut.println("Buying Product");
            //Exit Critical Section
        }

        else if (request.contains("Quit"))
        {

        }

        else
        {
            System.out.println("Invalid Request");
            System.out.println(request);
            myOut.println("Invalid");
        }


    }


    private void sendClientMSG(String UserMessage)
    {
        myOut.println( UserMessage);

    }

    private String receiveClientMSG()
    {
        String Result = null;
        try {
            Result = myIn.readLine();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return Result;
    }



    private Account getUserNameAndPassword()
    {
        System.out.println("Waiting For U and P");
        String UN = receiveClientMSG();
        String Pass = receiveClientMSG();
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
        sendClientMSG("You Sent User Name: "+newAccountToValidate.getUserName());
        sendClientMSG("You Sent Password : "+newAccountToValidate.getPassword());
        //End Of For Testing

        //Call Function from Server Market
        //Get Result
        //Send Result

        //In Real Case Valid Or Invalid
        sendClientMSG("Finished Checking Login");

        System.out.println("Finished");
    }

    //Functions to Create New User Account
    private void CreateNewUserAccount()
    {
        int x = Server_Market.findClientID(this);
        System.out.println("Adding New Account for "+String.valueOf(x));
        myOut.println("Adding New Account");
    }




}
