package Market_Server;

import java.io.IOException;
import java.net.Socket;

public class Communicator {

    private Transceiver myTransceiver;

    public Communicator(Transceiver myTransceiver) {
        this.myTransceiver = myTransceiver;
    }

    public Communicator(Socket mySocket) {
        try {
            this.myTransceiver = new Transceiver(mySocket);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setMyTransceiver(Transceiver myTransceiver) {
        this.myTransceiver = myTransceiver;
    }

    public void sendMSG(Object obj){
        myTransceiver.sendMSG(obj);
    }

    public String receiveMSG(){
        return myTransceiver.receiveMSG();
    }


    public void terminateCommunication(){
        myTransceiver.terminateCommunication();
    }
    public Account getClientAccount()
    {
        System.out.println("Waiting For U and P");

        String UN = myTransceiver.receiveMSG();
        String Pass = myTransceiver.receiveMSG();

        Account AccountToValidate = new Account(UN,Pass);
        return  AccountToValidate;
    }
}
