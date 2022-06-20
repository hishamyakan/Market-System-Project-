package Market_Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Transceiver {

    private Socket Client_To_Handle;
    private BufferedReader myIn;
    private PrintWriter myOut;


    public Transceiver(Socket clientSocket) throws IOException
    {
        this.Client_To_Handle = clientSocket;
        this.myIn = new BufferedReader(new InputStreamReader(Client_To_Handle.getInputStream()));
        this.myOut = new PrintWriter(Client_To_Handle.getOutputStream(),true);
    }


    public void sendMSG(Object obj)
    {
        myOut.println(obj.toString());

    }
    public String receiveMSG()
    {
        String Result = null;
        try {
            Result = myIn.readLine();
        } catch (IOException e) {
            //e.printStackTrace();
            Result = "Server Is Down";

        }
        return Result;
    }


    public void sendList(ArrayList<Object> Messages){

        for(Object msg : Messages){

            sendMSG(msg.toString());

        }

    }






    public ArrayList<String> receiveList(int numberOfMessages){

    ArrayList<String> result = new ArrayList<String>();

    for(int i = 0 ; i < numberOfMessages ; i++){

        result.add(receiveMSG());


    }

    return result;


    }


    void terminateCommunication(){


        myOut.close();
        try {
            myIn.close();
            Client_To_Handle.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
