import java.util.Scanner;
import Market_Client.*;

public class GUI_Stub {


    public static void main(String args[]) {

        boolean flag = true;
        boolean loggedIn = false;

        while (flag) {
            if (loggedIn) {
                Scanner sc = new Scanner(System.in);
                System.out.println("****************Welcome*****************");
                System.out.println("\n");
                System.out.println("1. View Information");
                System.out.println("2. Search For Items");

                int r = sc.nextInt();

                if (r == 1){
                    System.out.println("Please enter User Name");
                    String UN = sc.nextLine();
                    Client_Market c = new Client_Market();
                    Account a = c.HandleViewInformation(UN);
                    System.out.println(a.getName());
                    System.out.println(a.getUserName());
                    System.out.println(a.getAddress());
                    System.out.println(a.getCreditCardNumber());

                }
                else if (r == 2){

                }





            } 
            else {
                Scanner s = new Scanner(System.in);
                System.out.println("****************Welcome*****************");
                System.out.println("\n");
                System.out.println("1. Login");
                System.out.println("2. Create New Account");

                int r = s.nextInt();


                if(r == 1) {

                    System.out.println("Please enter User Name");

                    String UN = s.nextLine();
                    System.out.println("Please enter Password");
                    String Pass = s.nextLine();

                    Client_Market c = new Client_Market();
                    String Reply = c.HandleLoginRequest(UN,Pass);

                    if(Reply.contains("Invalid User Name")){
                        System.out.println("Invalid User Name");
                    }

                    else if(Reply.contains("Invalid Password")){
                        System.out.println("Invalid Password");
                    }

                    else if(Reply.contains("Access Granted")){
                        System.out.println("Access Granted");
                        loggedIn = true;
                    }


                }

                else if (r == 2){
                    System.out.println("Please enter User Name");
                    String UN = s.nextLine();

                    System.out.println("Please enter Name");
                    String Name = s.nextLine();

                    System.out.println("Please enter Password");
                    String Pass = s.nextLine();

                    System.out.println("Please enter Address");
                    String Add = s.nextLine();

                    System.out.println("Please enter Phone");
                    String Phone = s.nextLine();

                    Client_Market c = new Client_Market();
                   String res =  c.HandleCreateAccount(UN,Pass,Name,Add,Phone);

                   if(res.contains("Account Is Created"))
                   {
                       System.out.println("Account is Created");
                   }
                   else if(res.contains("User Name Already Exists"))
                   {
                       System.out.println("User Name Already Exists");
                   }




                }
                else
                {
                    System.out.println("Invalid Request");
                }


            }


        }
    }

}
