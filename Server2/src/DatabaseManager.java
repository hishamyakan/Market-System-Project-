import java.util.ArrayList;

public class DatabaseManager {

    /*
     * Description :
     * Returns the password of a given username.
     * Input: 1- Username
     *        2- Type of account
     *
     * If the user type is AccountType.CLIENT, it searches in the Customer table.
     * Otherwise, It searches in the Admin table.
     * If the username does not exist in the database , return null.
     * Otherwise, return the password.
     */
    public String getPassword(String username , AccountType type){

        return null;


    }

    /*
     * Description :
     * Stores tha data of an account object in the database.
     * Input: Account Object.
     * It uses INSERT Command to insert the data of the user in the Customer table.
     */

    public synchronized void StoreAccount(Account user){



    }

    /*
     * Description :
     * Searches in the database using the username and returns an Account object after filling it with the required parameters.
     * Input: Username
     * If the username does not exist in the database, return null.
     */
    public Account getAccount(String username){

        return new Account();
    }


    /*
     * Description :
     * Returns all Items in the database after storing them in Item Objects.
     */
    public ArrayList<Item> getAllItems(){
        return new ArrayList<>();
    }

    /*
     * Description :
     * Returns all Items of the given category in the database after storing them in Item Objects.
     */
    public ArrayList<Item> getAllItems(String Category){
        return new ArrayList<>();
    }

    /*
     * Description :
     * Returns all Items of the given category and name in the database after storing them in Item Objects.
     */
    public ArrayList<Item> getAllItems(String Category , String ItemName){
        return new ArrayList<>();
    }


    /*
     * Description :
     * Returns the cash balance of a certain customer given his username.
     * If the username does not exist, return 0.0.
     */
    public double getCashBalance(String userName) {

        return 0.0;


    }


    /*
     * Description :
     * Modifies the cash balance of a customer to be equal to the given parameter using UPDATE command.
     * Given the username, modifies the cash balance.
     */
    public void storeCashBalance(String username,double cashBalance){


    }


}
