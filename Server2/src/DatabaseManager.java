import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseManager {

    AccountTable accountTable = new AccountTable();
    ItemTable itemTable = new ItemTable();

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
        try {
            DatabaseConfiguration.connectDB();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Account account = accountTable.findBy(username,type);
        if (account != null) return account.getPassword();

        return null;
    }

    /*
     * Description :
     * Stores tha data of an account object in the database.
     * Input: Account Object.
     * It uses INSERT Command to insert the data of the user in the Customer table.
     */

    public synchronized void StoreAccount(Account user){

        try {
            DatabaseConfiguration.connectDB();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        accountTable.insert(user,AccountType.CLIENT);
    }

    /*
     * Description :
     * Searches in the database using the username and returns an Account object after filling it with the required parameters.
     * Input: Username
     * If the username does not exist in the database, return null.
     */
    public Account getAccount(String username , AccountType type){

        try {
            DatabaseConfiguration.connectDB();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return accountTable.findBy(username,type);
    }


    /*
     * Description :
     * Returns all Items in the database after storing them in Item Objects.
     */
    public ArrayList<Item> getAllItems(){

        try {
            DatabaseConfiguration.connectDB();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return itemTable.findAll();
    }

    /*
     * Description :
     * Returns all Items of the given category in the database after storing them in Item Objects.
     */
    public ArrayList<Item> getAllItems(String category){

        try {
            DatabaseConfiguration.connectDB();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return itemTable.findBy(category);
    }

    /*
     * Description :
     * Returns all Items of the given category and name in the database after storing them in Item Objects.
     */
    public ArrayList<Item> getAllItems(String category , String itemName){

        try {
            DatabaseConfiguration.connectDB();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return itemTable.findBy(category,itemName);
    }


    /*
     * Description :
     * Returns the cash balance of a certain customer given his username.
     * If the username does not exist, return 0.0.
     */
    public double getCashBalance(String userName) {

        try {
            DatabaseConfiguration.connectDB();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Account account = accountTable.findBy(userName,AccountType.CLIENT);
        if (account != null) return account.getCashBalance();

        return -1;
    }


    /*
     * Description :
     * Modifies the cash balance of a customer to be equal to the given parameter using UPDATE command.
     * Given the username, modifies the cash balance.
     */
    public synchronized void storeCashBalance(String username,double cashBalance){

        try {
            DatabaseConfiguration.connectDB();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        accountTable.updateAccountBalance(username,cashBalance);
    }

//
//    public ArrayList<Order> getOrders(String username){
//
//        try {
//            DatabaseConfiguration.connectDB();
//        } catch (InstantiationException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return OrderTable.getOrders(username);
//
//    }


    public ArrayList<category> getCategoryReport(){

        try {
            DatabaseConfiguration.connectDB();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            return CategoryTable.Category_report();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Item> getCorrespondingItems(ArrayList<Item> items){

        try {
            DatabaseConfiguration.connectDB();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Item> arr = new ArrayList<>();

        for(Item i : items){


            Item newItem = itemTable.findById(i.getSerialNumber());

            arr.add(newItem);

        }

        return arr;


    }

    public ArrayList<Order> getOrders(String username){

            ArrayList<Order> array = OrderTable.getOrders(username);

            ArrayList<Item> items;

            for(int i = 0 ; i < array.size() ; i++){

                items = OrderTable.getItemsFromOrder(String.valueOf(array.get(i).getOrderId()));

                array.get(i).setItems(items);

            }

            return array;


    }

    public void storeOrder(Order newOrder) {

        try {
            DatabaseConfiguration.connectDB();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        OrderTable.storeOrder(newOrder);


    }
}
