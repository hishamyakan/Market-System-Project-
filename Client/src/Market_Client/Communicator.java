/******************************************************************************
 *
 * Module: Communicator
 *
 * File Name: Communicator.java
 *
 * Description: Implementation of the Communicator Class that is responsible
 *              for sending high level objects through the socket.
 *
 * Author : Hussam Wael
 *******************************************************************************/
package Market_Client;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Communicator {

    private Transceiver myTransceiver;

    /*
     * Description :
     * Class Constructor.
     */
    public Communicator(Transceiver myTransceiver) {

        this.myTransceiver = myTransceiver;

    }

    /*
     * Description :
     * Class Constructor.
     */
    public Communicator(Socket mySocket) {
        try {
            this.myTransceiver = new Transceiver(mySocket);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /*
     * Description :
     * Transceiver Setter Method.
     */
    public void setMyTransceiver(Transceiver myTransceiver) {
        this.myTransceiver = myTransceiver;
    }



    /******************************************************************************
     Sending Methods
     *******************************************************************************/

    private void sendObject(Object o){

        try {
            myTransceiver.sendMSG(o);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    /*
     * Description :
     * Sends a String through the socket.
     */
    public void sendMSG(String str){

        sendObject(str);

    }


    /*
     * Description :
     * Sends a list of Item Objects.
     */
    public void sendItems(ArrayList<Item> items){

        sendObject(items);

    }

    /*
     * Description :
     * Sends an Account Object across the socket.
     */

    public void sendAccount(Account account){

        sendObject(account);

    }



    /******************************************************************************
     Receiving Methods
     *******************************************************************************/
    /*
     * Description :
     * Receives a string from the socket.
     */
    public String receiveMSG(){
        try {
            return (String) myTransceiver.receiveMSG();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Description :
     * Receives a string from the socket.
     */

    public Account receiveAccount(){
        try {
            return (Account) myTransceiver.receiveMSG();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Item> receiveItems(){
        try {
            return (ArrayList<Item>) myTransceiver.receiveMSG();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /*
     * Description :
     * Ends the communication between the client and the server.
     */
    public void terminateCommunication(){
        myTransceiver.terminateCommunication();
    }


    /*
    public void sendAccount(Account acc){

        myTransceiver.sendMSG(acc.getUserName());
        myTransceiver.sendMSG(acc.getPassword());


    }*/
}

