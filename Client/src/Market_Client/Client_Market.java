package Market_Client;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.Socket;



public class Client_Market {

    private static final String IP= "127.0.0.1";
    private static final int port = 9090;
    Client_Market ()
    {
        try  {
            Socket mySocket= new Socket(IP,port);

            BufferedReader ServerInput = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            BufferedReader ClientInput = new BufferedReader(new InputStreamReader(System.in));

            PrintWriter OutToServer = new PrintWriter(mySocket.getOutputStream(), true);

            while(true)
            {
                System.out.println("Please Enter a Request");
                String Command = ClientInput.readLine();
                OutToServer.println(Command);


                if(Command.contains("Check Login"))
                {
                    System.out.println("Please enter U AND P");
                    //(From GUI)
                    String UserName = ClientInput.readLine();
                    String Pass = ClientInput.readLine();
                    //End of (From GUI)

                    sendUserNameAndPassword(UserName , Pass, OutToServer);

                    //For Testing
                    System.out.println( receiveServerMSG(ServerInput) );
                    System.out.println( receiveServerMSG(ServerInput) );
                    //End of For Testing



                }




                String ServerReply = ServerInput.readLine();
                System.out.println(ServerReply);

                if(Command.equals("Quit"))
                {
                    break;
                }

            }

            System.out.println("Quitting");

            mySocket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void sendServerMSG(PrintWriter OutToServer,String UserMessage)
    {
        OutToServer.println( UserMessage);

    }

    private String receiveServerMSG(BufferedReader ServerInput)
    {
        String Result = null;
        try {
            Result = ServerInput.readLine();
//            while (Result == null)
//            {
//                Result = ServerInput.readLine();
//            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return Result;
    }

    private void sendUserNameAndPassword(String UN ,
                                         String Pass,
                                         PrintWriter OutToServer)
    {



        sendServerMSG(OutToServer,UN);
        sendServerMSG(OutToServer,Pass);

    }




    public static void main (String[] args)
    {

        Client_Market  c = new Client_Market ();

    }



}
