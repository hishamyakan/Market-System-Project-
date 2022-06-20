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
            boolean flag = establishCommunication(ClientInput);


            while(flag)
            {
                //GUI Waiting For Request
                System.out.println("Please Enter a Request");

                //Blocked On GUI
                String Command = ClientInput.readLine();
                //Got Message From GUI

                communicator.sendMSG(Command);

                boolean QuitFlag = handleGUI(Command,ClientInput);

                if(Command.equals("Quit"))
                {
                    break;
                }
                String ServerReply = communicator.receiveMSG();

                if(ServerReply.contains("Server Is Down"))
                {
                    flag = establishCommunication(ClientInput);

                }
                else
                {
                    System.out.println(ServerReply);
                }





            }

            System.out.println("Quitting");

            /*mySocket.close();*/
            try {
                communicator.terminateCommunication();
            }
            catch (NullPointerException e)
            {
                //e.printStackTrace();
                System.out.println("Aborting");
            }




        } catch (IOException e) {
            e.printStackTrace();
            //Send GUI A Message That the Server Is Down And We Can't Establish Connection
            //We Will Ask The User If He Wants to Continue
            //Must Get Handled In the Main Of the Program
            System.out.println("Server Is Already Down Try Again Later");
        }


    }

    boolean handleGUI(String Command,BufferedReader ClientInput) throws IOException {
        boolean ServerDown = false;
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

        else
        {

        }
            return ServerDown;

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
                System.out.println("The server is down now. Would You Like To Try Again to Establish Communication? " +
                        "Yes/No");
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
//
//    boolean ServerIsDownHandling(String ServerReply)
//    {
//        Boolean QuitProgram = false;
//        if(ServerReply.contains("Server Is Down"))
//        {
//
//        }
//
//    }


    public String HandleLoginRequest(BufferedReader ClientInput) throws IOException
    {
        System.out.println("Please enter U AND P");
        //(From GUI)
        String UserName = ClientInput.readLine();
        String Pass = ClientInput.readLine();
        //End of (From GUI)

        communicator.sendAccount(new Account(UserName,Pass));
return null;

    }



    public static void main (String[] args)
    {
            Client_Market  c = new Client_Market ();



    }



}
