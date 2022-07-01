import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;





public class Server_Market {

    private static final int port = 9090;
    private static ArrayList<handler>ClientsInServer = new ArrayList<>();
    private static final int maxNumThreads = 15;
    private ExecutorService pool = Executors.newFixedThreadPool(maxNumThreads);

    Server_Market() throws IOException
    {


        //Establish Connection
        ServerSocket myServer = new ServerSocket(port);
        // Iam Waiting for the client to Connect With Me
        System.out.println("The Server is Waiting to Connect ...........");

        //The Server Is Awake
        while (true){

            // Iam Waiting for any Request

            Socket myClient = myServer.accept();

            //The Client Have Connected With Me
            System.out.println("Connected to the Client");

            System.out.println("The Server is Waiting for request...........");



            handler ClientThread = new handler(myClient);

            ClientsInServer.add(ClientThread);

            pool.execute(ClientThread);


        }


    }


    public static int findClientID(handler ClientThread)
    {

        return ClientsInServer.indexOf(ClientThread);
    }

    public static void terminateT(handler ClientThread)
    {
        ClientsInServer.remove(ClientThread);

    }
    public static void main (String[] args)
    {

        try {
            DatabaseConfiguration.connectDB();
            Server_Market s = new Server_Market();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

}
