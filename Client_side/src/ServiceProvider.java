/******************************************************************************
 *
 * Module: Client_Market
 *
 * File Name: ServiceProvider.java
 *
 * Description: Implement a class to be used by the Client_Market Class to Handle sending and receiving
 * Protocols
 *
 * Author : Hisham Yakan AbuBakr
 *******************************************************************************/

import java.util.ArrayList;

public  class ServiceProvider {

    private Communicator communicator;

    public ServiceProvider(Communicator communicator) {
        this.communicator = communicator;
    }

    /*Function Name: Verify Account
     * Function Description : This Function is used to send account objet and receive reply
     *
     * */

    public String verifyAccount(Account acc){

        communicator.sendAccount(acc);
        System.out.println("Account Sent");
        String result = communicator.receiveMSG();
        System.out.println("Message recieved = "+result);
        communicator.sendMSG("Quit");
        communicator.terminateCommunication();
        return result;
    }

    /*Function Name: Create New Account
     * Function Description : This Function is used to send account objet and receive reply
     *
     * */
    public String CreateNewAccount(Account acc){

        communicator.sendAccount(acc);
        String result = communicator.receiveMSG();
        communicator.sendMSG("Quit");
        communicator.terminateCommunication();
        return result;
    }

    public Account ViewInformation(String UserName) {
        communicator.sendMSG(UserName);
        Account result = communicator.receiveAccount();
        communicator.sendMSG("Quit");
        communicator.terminateCommunication();
        return result;

    }

    public String DepositCash(String UserName , String CreditCardNumber, double  Amount) {
        communicator.sendMSG(UserName);
        communicator.sendMSG(CreditCardNumber);
        communicator.sendDouble(Amount);
        String result = communicator.receiveMSG();
        communicator.sendMSG("Quit");
        communicator.terminateCommunication();
        return result;
    }

    public ArrayList<Item> GetItems(String Category, String itemName ){

        communicator.sendMSG(Category);
        communicator.sendMSG(itemName);
        ArrayList<Item> result = communicator.receiveItems();
        communicator.sendMSG("Quit");
        communicator.terminateCommunication();
        return result;


    }

    public ArrayList<Order> GetOrders(String UserName){
        communicator.sendMSG(UserName);
        ArrayList<Order>result = communicator.receiveOrders();
        communicator.sendMSG("Quit");
        communicator.terminateCommunication();
        return result;

    }

    public ArrayList<Order> GetOrders(){
        ArrayList<Order>result = communicator.receiveOrders();
        communicator.sendMSG("Quit");
        communicator.terminateCommunication();
        return result;
    }

    public ArrayList<category> GetCategories(){

        ArrayList<category> result = communicator.receiveCategories();
        communicator.sendMSG("Quit");
        communicator.terminateCommunication();
        return result;
    }

    public String purchaseResult(String UserName, String flag, ArrayList<Item> requiredItems ){
        communicator.sendMSG(UserName);
        communicator.sendMSG(flag);
        communicator.sendItems(requiredItems);
        return communicator.receiveMSG();

    }

    public ArrayList<Item> purchaseItems(){

        ArrayList<Item> result = communicator.receiveItems();
        communicator.sendMSG("Quit");
        communicator.terminateCommunication();
        return result;

    }






}
