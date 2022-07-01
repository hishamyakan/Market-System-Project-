/******************************************************************************
 *
 * Module: Transceiver
 *
 * File Name: Transceiver.java
 *
 * Description: Implementation of the Transceiver Class that is responsible for Socket Communication
 *
 * Author : Hussam Wael
 *******************************************************************************/

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Transceiver {

    private Socket Client_To_Handle;
    private ObjectOutputStream myOut;
    private ObjectInputStream myIn;


    /*
     * Description :
     * Class Constructor.
     */
    public Transceiver(Socket clientSocket) throws IOException
    {
        this.Client_To_Handle = clientSocket;
        /*this.myIn = new ObjectInputStream(clientSocket.getInputStream());
        this.myOut = new ObjectOutputStream(clientSocket.getOutputStream());*/
    }



    /*
     * Description :
     * Sends an object over the socket.
     */
    public void sendMSG(Object o) throws IOException {

        this.myIn = null;

        this.myOut = new ObjectOutputStream(Client_To_Handle.getOutputStream());

        myOut.writeObject(o);

    }


    /*
     * Description :
     * Returns an object that is received from the socket.
     */
    public Object receiveMSG() throws ClassNotFoundException, IOException {

        this.myOut = null;

        this.myIn = new ObjectInputStream(Client_To_Handle.getInputStream());


        Object Result = null;
        try {
            Result = myIn.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Client Terminated UnExpectedly");
            Result = "Quit";

        }
        return Result;
    }



    /*
     * Description :
     * Sends an array list of objects through the socket.
     */
    public void sendList(ArrayList<Object> Messages) throws IOException {

        myOut.writeObject(Messages);

    }





    /*
     * Description :
     * Returns an array list of objects received from the socket.
     */
    public ArrayList<Object> receiveList() throws IOException, ClassNotFoundException {

        return (ArrayList<Object>)myIn.readObject();



    }

    /*
     * Description :
     * Ends the communication between the client and the server.
     */
    void terminateCommunication(){
        try {
            if(myOut != null)
                myOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if(myIn != null)
                myIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
