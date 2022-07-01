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
        } catch (IOException e) {
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

    private void sendObject(Object o) {

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
    public void sendMSG(String str) {

        sendObject(str);

    }


    /*
     * Description :
     * Sends a list of Item Objects.
     */
    public void sendItems(ArrayList<Item> items) {

        sendObject(items);

    }

    /*
     * Description :
     * Sends an Account Object across the socket.
     */

    public void sendAccount(Account account) {

        sendObject(account);

    }


    public void sendDouble(double value) {
        sendObject(new Double(value));
    }


    public void sendCategories(ArrayList<category> cats) {sendObject(cats);}

    public void sendOrders(ArrayList<Order> orders) {sendObject(orders);}
    /******************************************************************************
     Receiving Methods
     *******************************************************************************/
    /*
     * Description :
     * Receives a string from the socket.
     */
    public String receiveMSG() {
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

    public Account receiveAccount() {
        Object result = null;
        try {
            result = myTransceiver.receiveMSG();
            return (Account) result;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println((String) result);
        }
        return null;
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


    public double receiveDouble(){

        double result = 0.0;
        try {
            result =  (Double)myTransceiver.receiveMSG();


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public ArrayList<category> receiveCategories(){

        ArrayList<category> arr = null;

        try {
            arr =  (ArrayList<category>) myTransceiver.receiveMSG();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return arr;
    }

    public ArrayList<Order> receiveOrders(){

        ArrayList<Order> arr = null;

        try {
            arr =  (ArrayList<Order>) myTransceiver.receiveMSG();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return arr;
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

