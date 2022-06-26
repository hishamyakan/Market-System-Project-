package Market_Client;

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
        return communicator.receiveMSG();
    }

    /*Function Name: Create New Account
     * Function Description : This Function is used to send account objet and receive reply
     *
     * */
    public String CreateNewAccount(Account acc){

        communicator.sendAccount(acc);
        return communicator.receiveMSG();
    }

    public Account ViewInformation(String UserName) {
        communicator.sendMSG(UserName);
        return communicator.receiveAccount() ;

    }

    public String DepositCash(String UserName , String CreditCardNumber, double  Amount) {
        communicator.sendMSG(UserName);
        communicator.sendMSG(CreditCardNumber);
        //communicator.sendMSG();
        return communicator.receiveMSG();
    }

    public ArrayList<Item> GetItems(String Category, String itemName ){

        communicator.sendMSG(Category);
        communicator.sendMSG(itemName);
        return communicator.receiveItems();


    }

}
