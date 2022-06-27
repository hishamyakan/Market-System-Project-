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
        //communicator.sendMSG(acc.getUserName());
        //communicator.sendMSG(acc.getPassword());
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

}
