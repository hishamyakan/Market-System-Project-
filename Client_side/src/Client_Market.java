/******************************************************************************
 *
 * Module: Client_Market
 *
 * File Name: Client_Market.java
 *
 * Description: Implement a class to be used by the GUI handling any request by connecting to the server
 *
 * Author : Hisham Yakan AbuBakr
 *******************************************************************************/

import java.net.Socket;
import java.util.ArrayList;


public class Client_Market {

    //IP Address of the server that will be constant all the time.
    private static final String IP = "192.168.1.17";

    // Port Number Chosen for the Program.
    private static final int port = 9090;

    // Communicator Variable used to communicate by sending and receiving messages.
    private Communicator communicator;

    //This path is used to set certain path for images to be saved in when images are received from the Server.
    private String defaultImagePath;

    //This String is used to determine if the purchase transaction was Successful or failed
    private String purchaseResult = "No Purchase";



    /*
     ***************************************** 1.Empty Constructor*********************************************
     * Function Name: Client_Market.
     * Function Type : Asynchronous Reentrant.
     * Function Inputs : None.
     * Function Outputs : Void.
     * Function Description: Empty Constructor for the class.
     * Used with: Client & Admin.
     * */
    public Client_Market() {

    }

    /*
     *************************** 2. Constructor to set the Default Image Path ***************************************
     * Function Name: Client_Market.
     * Function Type : Asynchronous Reentrant.
     * Function Inputs : String defaultImagePath to set the path at which the images of items will be saved.
     * Function Outputs : Void.
     * Function Description: Constructor for the class to set the path at which the images of items will be saved.
     * Used with: Client.
     * */

    public Client_Market(String defaultImagePath) {
        this.defaultImagePath = defaultImagePath;
    }


    /*
    ***************************************** 3.Set Default Image Path *********************************************
    * Function Name: setDefaultImagePath
    * Function Type : Asynchronous Reentrant
    * Function Inputs : String to set the path at which the images of items will be saved
    * Function Outputs : Void
    * Function Description: This Function is used to set the path at which the images of items will be saved.
    * You must use this function every time before searching for any item or you might use the constructor
    * (Leaving the default path uninitialized will cause the program to crash)
    * Used with: Client.
    * */

    public void setDefaultImagePath(String defaultImagePath) {
        this.defaultImagePath = defaultImagePath;
    }


    /*
     **************************************** 4.Establish Communication ******************************************
     * Function Name: establishCommunication
     * Function Type : Asynchronous Non-Reentrant
     * Function Inputs : String to Determine the command required to be sent to the server after establishing
     * communication
     * Function Outputs : boolean flag to indicate whether the communication is established or not
     * Function Description: This Function is used to try to connect to the server with two states:
     *State(A):Connects to server, sends the command required and returns a flag with true
     * State(B): Does not Connect to the server, no command is sent to server and returns a flag with false.
     * Used with: Client & Admin.
     * */

    private boolean establishCommunication(String command) {
        //flag to be returned initialized to true
        boolean flag = true;

        try {
            //Connecting to the server through the communicator class
            communicator = new Communicator(new Transceiver(new Socket(IP, port)));

            //Setting the flag with true
            flag = true;

            System.out.println("Connected To Server\n");

            //Sending the command to the server
            communicator.sendMSG(command);

        } catch (Exception e) {

            //Setting the flag with false indicating the server is down or connection problem
            flag = false;
        }

        return flag;
    }


    /*
     **************************************** 5.Handle Login Request ******************************************
     * Function Name: HandleLoginRequest
     * Function Type : Asynchronous Non-Reentrant
     * Function Inputs : 1. String For the user name of the client
     *                   2. String for the password of the client
     * Function Outputs : String indicating the result of the login request with the following results:
     *                  1. null: Indicating the server is down or connection problem
     *                  2."Invalid User Name": Indicating the user name doesn't exist in the DateBase
     *                  3."Access Granted": Indicating the user name exists in the DateBase and password is correct
     *                  4."Invalid Password":Indicating the user name exists in the DateBase but the password is
     *                    incorrect
     * Function Description: This function is used to handle the client login request.
     * Used with: Client & Admin.
     * */

    public String HandleLoginRequest(String UserName, String Pass) {
        //Result to be sent to the GUI
        String reply = null;
        //Try to establish communication, if you can not a null will be returned
        if (this.establishCommunication("Check Login")) {

            //Creating a new object of type ServiceProvider and passing the communicator object to it
            ServiceProvider sp = new ServiceProvider(this.communicator);
            //Asking the service provider to send the account and send the result
            String ServerReply = sp.verifyAccount(new Account(UserName, Pass));

            if (ServerReply.contains("Invalid UserName")) {
                reply = "Invalid User Name";
            } else if (ServerReply.contains("Access Granted")) {
                reply = "Access Granted";
            } else if (ServerReply.contains("Invalid Password")) {
                reply = "Invalid Password";
            } else {
                System.out.println("Error Find the error");
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return reply;
    }

    /*
     **************************************** 6.Handle Create Account ******************************************
     * Function Name: HandleCreateAccount
     * Function Type : Asynchronous Non-Reentrant
     * Function Inputs : 1.String For the user name of the client
     *                   2.String for the password of the client
     *                   3.String for the Name of the client
     *                   4.String for the Address of the client
     *                   5.String for the Phone Number of the client
     * Function Outputs : String indicating the result of the login request with the following results:
     *                    1. null: Indicating the server is down or connection problem
     *                    2."User Name Already Exists": Indicating the user name already exists in the DateBase so we
     *                                                cannot create this account
     *                    3."Account Is Created": Indicating the account is created and now the user can
     *                                          use it to login.
     * Function Description: This function is used to handle the client Create Account request.
     * Used with: Client.
     * */

    public String HandleCreateAccount(String UserName, String Pass, String Name,
                                      String Address, String PhoneNumber) {

        //Result to be sent to the GUI
        String reply = null;

        //Try to establish communication, if you can not a null will be returned
        if (this.establishCommunication("Add New Account")) {

            //Creating a new object of type Account and setting its attributes
            Account newAcc = new Account();
            newAcc.setName(Name);
            newAcc.setPassword(Pass);
            newAcc.setUserName(UserName);
            newAcc.setAddress(Address);
            newAcc.setPhoneNumber(PhoneNumber);

            //Creating a new object of type ServiceProvider and passing the communicator object to it
            ServiceProvider sp = new ServiceProvider(this.communicator);
            //Asking the service provider to send the account and send the result
            String ServerReply = sp.CreateNewAccount(newAcc);

            if (ServerReply.contains("User Name Already Exists")) {
                //Display Message To GUI
                reply = "User Name Already Exists";
            } else if (ServerReply.contains("Account is Created")) {
                // Open New Window
                reply = "Account Is Created";
            } else {
                System.out.println("Error Find the error");
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return reply;

    }


    /*
     **************************************** 7.Handle View Information ******************************************
     * Function Name: HandleViewInformation
     * Function Type : Asynchronous Non-Reentrant
     * Function Inputs : 1.String For the user name of the client
     * Function Outputs : Account object with user information
     *                    1. null: Indicating the server is down or connection problem
     *                    2. Account object: The User information are encapsulated in it

     * Function Description: This function is used to handle the client View Information request
     * Used with: Client.
     * */
    public Account HandleViewInformation(String UserName) {

        //Result to be sent to the GUI
        Account account = null;

        if (this.establishCommunication("Get Client Information")) {

            //Creating a new object of type ServiceProvider and passing the communicator object to it
            ServiceProvider sp = new ServiceProvider(this.communicator);

            account = sp.ViewInformation(UserName);

        }

        return account;

    }


    /*
     **************************************** 8.Handle Deposit Cash ******************************************
     * Function Name: HandleDepositCash
     * Function Type : Asynchronous Non-Reentrant
     * Function Inputs : 1.String For the user name of the client
     *                   2.String for the Credit Card Number of the client
     *                   3.double for the amount to be increased Amount
     * Function Outputs : String indicating the result of the Deposit Cash request with the following results:
     *                    1. null: Indicating the server is down or connection problem
     *                    2."User Name Does not exists"": Indicating the user name doesn't exist in the DateBase
     *                    3.""Deposit Is Stored"": Indicating the deposit is added to the account
     * Function Description: This function is used to handle the client Create Account request.
     * Used with: Client.
     * */

    public String HandleDepositCash(String UserName, String CreditCardNumber, double Amount) {

        //Result to be sent to the GUI
        String reply = null;

        if (this.establishCommunication("Deposit Cash")) {
            //Creating a new object of type ServiceProvider and passing the communicator object to it
            ServiceProvider sp = new ServiceProvider(this.communicator);
            reply = sp.DepositCash(UserName, CreditCardNumber, Amount);

        }
        return reply;
    }


    /*
     **************************************** 9.Handle Search For Items ******************************************
     * Function Name: HandleSearchForItems (Note that there is an overloaded function with the same name).
     * Function Type : Asynchronous Non-Reentrant.
     * Function Inputs : String For the category of items required.
     * Function Outputs :ArrayList of Item objects
     *                    1.null: Indicating the server is down or connection problem.
     *                    2.ArrayList of Item objects that match the search category.
     * Function Description: This function is used to handle the client Search For Items request.
     * (Note the images are decoded and saved in the specified path ).
     * Used with: Client.
     * */

    public ArrayList<Item> HandleSearchForItems(String Category) {
        //Result to be sent to the GUI
        ArrayList<Item> items = null;
        if (this.establishCommunication("Search for Items")) {
            String itemName = "none";
            ServiceProvider sp = new ServiceProvider(this.communicator);
            items = sp.GetItems(Category, itemName);

        }
        this.decodeAllItemImages(items);
        return items;

    }

    /*
     **************************************** 10.Handle Search For Items ******************************************
     * Function Name: HandleSearchForItems (Note that there is an overloaded function with the same name).
     * Function Type : Asynchronous Non-Reentrant.
     * Function Inputs : 1.String For the category of items required.
     *                   2. String for the item name required.
     * Function Outputs :ArrayList of Item objects
     *                    1.null: Indicating the server is down or connection problem.
     *                    2.ArrayList of Item objects that match the search category and item name.
     * Function Description: This function is used to handle the client Search For Items request.
     * (Note the images are decoded and saved in the specified path ).
     * Used with: Client.
     * */


    public ArrayList<Item> HandleSearchForItems(String Category, String itemName) {
        //Result to be sent to the GUI
        ArrayList<Item> items = null;
        if (this.establishCommunication("Search for Items")) {
            //Creating a new object of type ServiceProvider and passing the communicator object to it
            ServiceProvider sp = new ServiceProvider(this.communicator);
            items = sp.GetItems(Category, itemName);
        }
        this.decodeAllItemImages(items);
        return items;
    }


    /*
     **************************************** 11.Handle Purchase ******************************************
     * Function Name: HandlePurchase.
     * Function Type : Asynchronous Non-Reentrant.
     * Function Inputs : 1.String For the user name of the client
     *                   2. Array List of items indicating the items in the cart required to purchase
     * Function Outputs :ArrayList of Item objects
     *                    1.null: Indicating the server is down or connection problem.
     *                    2.ArrayList of Item objects that is empty in case of success or ArrayList of Item
     *                     that failed to be purchased.
     * Function Description: This function is used to handle the client purchase request.
     * To use this function:
     * 1. Call the function passing the client user name and the required items in the cart using
     * CartObj.getMyItems()
     * 2.Receive the Array list of the output
     * 3.If the array list is null , then the server couldn't connect
     * 4. Then Check the variable purchaseResult using Clinet_MarketObj.getPurchaseResult() Function.
     * 5. If the purchaseResult was "Success" ,discard the ArrayList and just empty the cart using emptyCart() Function
     * 6. If the purchaseResult was "Failure" , Show the user the Items with problems (They are the output of this
     * function.
     * Used with: Client.
     * */


    public ArrayList<Item> HandlePurchase(String UserName,  String flag  ,ArrayList<Item> requiredItems ){
        //Result to be sent to the GUI
        ArrayList<Item> items = null;
        if (this.establishCommunication("Purchase")){
            //Creating a new object of type ServiceProvider and passing the communicator object to it
            ServiceProvider sp = new ServiceProvider(this.communicator);
            this.purchaseResult = sp.purchaseResult(UserName, flag ,requiredItems );
            items = sp.purchaseItems();

        }
        return items;
    }

    /*
     **************************************** 12.Handle View History ******************************************
     * Function Name: HandleViewHistory.
     * Function Type : Asynchronous Non-Reentrant.
     * Function Inputs : 1.String For the user name of the client.
     * Function Outputs :ArrayList of Order objects
     *                    1.null: Indicating the server is down or connection problem.
     *                    2.ArrayList of Order objects that shows all the orders purchased by the user.
     * Function Description: This function is used to handle the client  View History  request. It shows list of
     * order objects that shows all the users orders.
     * Used with: Client.
     * */


    public ArrayList<Order> HandleViewHistory(String UserName) {
        //Result to be sent to the GUI
        ArrayList<Order> Orders = null;
        if (this.establishCommunication("View User History")) {
            //Creating a new object of type ServiceProvider and passing the communicator object to it
            ServiceProvider sp = new ServiceProvider(this.communicator);
            Orders = sp.GetOrders(UserName);
        }

        return Orders;
    }

    /*
     *************************************** 13.Handle Admin Category Report ******************************************
     * Function Name: HandleAdminCategoryReport.
     * Function Type : Asynchronous Non-Reentrant.
     * Function Inputs : None.
     * Function Outputs :ArrayList of Category objects
     *                    1.null: Indicating the server is down or connection problem.
     *                    2.ArrayList of Category objects that was show all the Category in the market and their details.
     * Function Description: This function is used to handle the client  View History  request. It shows list of
     * Category objects  that shows the name of each category , number of sold items and number of remaining items.
     * Used with: Admin.
     * */


    public ArrayList<category> HandleAdminCategoryReport() {
        //Result to be sent to the GUI
        ArrayList<category> Categories = null;
        if (this.establishCommunication("Get Category Report")) {
            //Creating a new object of type ServiceProvider and passing the communicator object to it
            ServiceProvider sp = new ServiceProvider(this.communicator);
            Categories = sp.GetCategories();
        }

        return Categories;
    }

    /*
     *************************************** 14.Handle Admin Order Report ******************************************
     * Function Name: HandleAdminOrderReport.
     * Function Type : Asynchronous Non-Reentrant.
     * Function Inputs : None.
     * Function Outputs :ArrayList of Category objects
     *                    1.null: Indicating the server is down or connection problem.
     *                    2.ArrayList of Order objects that was show all the orders purchased during last month.
     * Function Description: This function is used to handle the client  Admin Order Report  request. It shows list of
     * Order objects  that shows the order number, and paid amount.
     * Used with: Admin.
     * */

    public ArrayList<Order> HandleAdminOrderReport() {
        //Result to be sent to the GUI
        ArrayList<Order> Orders = null;
        if (this.establishCommunication("Get Orders Report")) {
            //Creating a new object of type ServiceProvider and passing the communicator object to it
            ServiceProvider sp = new ServiceProvider(this.communicator);
            Orders = sp.GetOrders();
        }


        return  Orders;
    }


    /*
     ***************************************** 15.Get Purchase Result *********************************************
     * Function Name: getPurchaseResult.
     * Function Type : Asynchronous Reentrant
     * Function Inputs : None
     * Function Outputs : String Indicating the purchase result.
     * Function Description: This Function is used to get the purchase result.
     * You must use this function every time you want to purchase the cart items.
     * purchaseResult is either "Successful" or "Failed".
     * Used with: Client.
     * */

    public String getPurchaseResult() {
        String result = purchaseResult;

        purchaseResult = "No Purchase";

        return result;
    }


    /*
     ***************************************** 16.Decode All Item Images *********************************************
     * Function Name: decodeAllItemImages.
     * Function Type : Asynchronous Reentrant
     * Function Inputs : Array List of items
     * Function Outputs : void.
     * Function Description: This Function is used to decode all the Images received in the Item Object.
     * The function is used in the Search Use Case (HandleSearchForItems overloaded Functions).
     * Used with: Private for the class.
     * */

    private void decodeAllItemImages(ArrayList<Item>items){
        for(int i = 0 ; i < items.size() ; i++){
            try {
                ImageEncoder.decodeImage(items.get(i).getImageEncoded(), this.defaultImagePath);
                items.get(i).setImage(this.defaultImagePath +"\\"+items.get(i).getImageEncoded().get(1));

            } catch (Exception e) {
                //items.get(i).setImage(this.defaultImagePath +"\\"+items.get(i).getImageEncoded().get(1));    System.out.println(this.defaultImagePath +"\\"+items.get(i).getImageEncoded().get(1));
              e.printStackTrace();
            }
        }
    }



    /*
     ***************************************** 17.Encode All Item Images *********************************************
     * Function Name: encodeAllItemImages.
     * Function Type : Asynchronous Reentrant
     * Function Inputs : Array List of items
     * Function Outputs : void
     * Function Description: This Function is used to decode all the Images received in the Item Object.
     * The function is used in the Search Use Case (HandleSearchForItems overloaded Functions).
     * Used with: Private for the class.
     * */



    private void encodeAllItemImages(ArrayList<Item>items){
        for(Item i :items){
            try {
                i.setImageEncoded( ImageEncoder.encodeImage(i.getImage()));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }



}
