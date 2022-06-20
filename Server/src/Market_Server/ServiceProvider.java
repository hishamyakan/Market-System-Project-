package Market_Server;

import java.util.ArrayList;

public class ServiceProvider {


    ArrayList<Account> temp;

    private Communicator communicator;

    public ServiceProvider(Communicator communicator) {
        this.communicator = communicator;
    }

    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
    }

    public void validateUserAccount(){

        Account toValidate = communicator.getClientAccount();

        temp = new ArrayList<>();
        temp.add(new Account("Hussam" , "123456"));
        temp.add(new Account("Ali" , "123456"));
        temp.add(new Account("Seif" , "123456"));
        temp.add(new Account("Omar" , "123456"));
        temp.add(new Account("Khaled" , "123456"));

        for(Account acc : temp){

            if(acc.getUserName().contains(toValidate.getUserName()) && acc.getPassword().contains(toValidate.getPassword())){

                communicator.sendMSG("Access Granted");
                return;

            }

        }
        communicator.sendMSG("Access Denied");

    }


}
