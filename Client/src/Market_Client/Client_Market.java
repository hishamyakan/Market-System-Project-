package Market_Client;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;



public class Client_Market {

    private static final String IP= "127.0.0.1";
    private static final int port = 9090;

    private Communicator communicator;
    Client_Market ()
    {
        try  {

            BufferedReader ClientInput = new BufferedReader(new InputStreamReader(System.in));
            communicator = new Communicator(new Transceiver(new Socket(IP,port)));

            while(true)
            {
                System.out.println("Please Enter a Request");
                String Command = ClientInput.readLine();

                communicator.sendMSG(Command);


                if(Command.contains("Check Login"))
                {
                    System.out.println("Please enter U AND P");
                    //(From GUI)
                    String UserName = ClientInput.readLine();
                    String Pass = ClientInput.readLine();
                    //End of (From GUI)

                    communicator.sendAccount(new Account(UserName,Pass));


                    //For Testing

                    //System.out.println( serverTransceiver.receiveMSG() );
                   // System.out.println( ServerCommunicator.receiveMSG() );

                    //End of For Testing



                }


                String ServerReply = communicator.receiveMSG();
                System.out.println(ServerReply);

                if(Command.equals("Quit"))
                {
                    break;
                }

            }

            System.out.println("Quitting");

            /*mySocket.close();*/
            communicator.terminateCommunication();



        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void main (String[] args)
    {

        Client_Market  c = new Client_Market ();

    }



}
